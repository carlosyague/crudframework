package org.librae.common.webapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.util.CommonUtils;
import org.librae.common.webapp.session.SessionManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.AuthenticationException;
import org.springframework.security.AuthenticationTrustResolver;
import org.springframework.security.AuthenticationTrustResolverImpl;
import org.springframework.security.InsufficientAuthenticationException;
import org.springframework.security.SpringSecurityException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.AbstractProcessingFilter;
import org.springframework.security.ui.AccessDeniedHandler;
import org.springframework.security.ui.AccessDeniedHandlerImpl;
import org.springframework.security.ui.AuthenticationEntryPoint;
import org.springframework.security.ui.FilterChainOrder;
import org.springframework.security.ui.SpringSecurityFilter;
import org.springframework.security.ui.savedrequest.SavedRequest;
import org.springframework.security.util.PortResolver;
import org.springframework.security.util.PortResolverImpl;
import org.springframework.security.util.ThrowableAnalyzer;
import org.springframework.security.util.ThrowableCauseExtractor;
import org.springframework.util.Assert;

/**
 * Clase heredera de ExceptionTranslationFilter que comprueba si la sesion ha
 * caducado. Si esta caducada la envia a logOut.html
 *
 * @author jcisneros
 */
public class SIGBExceptionTranslationFilter extends SpringSecurityFilter
        implements InitializingBean {

    // ~ Instance fields
    // ========================================================
    // ========================================

    private AccessDeniedHandler         accessDeniedHandler         = new AccessDeniedHandlerImpl();
    private AuthenticationEntryPoint    authenticationEntryPoint;
    private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
    private PortResolver                portResolver                = new PortResolverImpl();
    private ThrowableAnalyzer           throwableAnalyzer           = new DefaultThrowableAnalyzer();
    private boolean                     createSessionAllowed        = true;
    private String                      urlSessionExpired           = null;

    // ~ Methods
    // ================================================================
    // ========================================

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(authenticationEntryPoint,
                "authenticationEntryPoint must be specified");
        Assert.notNull(portResolver, "portResolver must be specified");
        Assert.notNull(authenticationTrustResolver,
                "authenticationTrustResolver must be specified");
        Assert
                .notNull(throwableAnalyzer,
                        "throwableAnalyzer must be specified");
    }

    public void doFilterHttp(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
            if (logger.isDebugEnabled()) {
                logger.debug("Chain processed normally");
            }
        } catch (IOException ex) {
            throw ex;
        } catch (Exception ex) {
            // Try to extract a SpringSecurityException from the stacktrace
            Throwable[] causeChain = this.throwableAnalyzer
                    .determineCauseChain(ex);
            SpringSecurityException ase = (SpringSecurityException) this.throwableAnalyzer
                    .getFirstThrowableOfType(SpringSecurityException.class,
                            causeChain);

            if (ase != null) {
                handleException(request, response, chain, ase);
            } else {
                // Rethrow ServletExceptions and RuntimeExceptions as-is
                if (ex instanceof ServletException) {
                    throw (ServletException) ex;
                } else if (ex instanceof RuntimeException) {
                    throw (RuntimeException) ex;
                }

                // Wrap other Exceptions. These are not expected to happen
                throw new RuntimeException(ex);
            }
        }
    }

    public AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return authenticationEntryPoint;
    }

    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return authenticationTrustResolver;
    }

    public PortResolver getPortResolver() {
        return portResolver;
    }

    private void handleException(ServletRequest request,
            ServletResponse response, FilterChain chain,
            SpringSecurityException exception) throws IOException,
            ServletException {
        if (sesionCaducada(request)) {
            redirect(response);
        } else if (exception instanceof AuthenticationException) {
            if (logger.isDebugEnabled()) {
                logger
                        .debug(
                                "Authentication exception occurred; redirecting to authentication entry point",
                                exception);
            }

            sendStartAuthentication(request, response, chain,
                    (AuthenticationException) exception);
        } else if (exception instanceof AccessDeniedException) {
            if (authenticationTrustResolver.isAnonymous(SecurityContextHolder
                    .getContext().getAuthentication())) {
                if (logger.isDebugEnabled()) {
                    logger
                            .debug(
                                    "Access is denied (user is anonymous); redirecting to authentication entry point",
                                    exception);
                }

                sendStartAuthentication(
                        request,
                        response,
                        chain,
                        new InsufficientAuthenticationException(
                                "Full authentication is required to access this resource"));
            } else {
                if (logger.isDebugEnabled()) {
                    logger
                            .debug(
                                    "Access is denied (user is not anonymous); delegating to AccessDeniedHandler",
                                    exception);
                }

                accessDeniedHandler.handle(request, response,
                        (AccessDeniedException) exception);
            }
        }
    }

    /**
     * If <code>true</code>, indicates that
     * <code>SecurityEnforcementFilter</code> is permitted to store the target
     * URL and exception information in the <code>HttpSession</code> (the
     * default). In situations where you do not wish to unnecessarily create
     * <code>HttpSession</code>s - because the user agent will know the failed
     * URL, such as with BASIC or Digest authentication - you may wish to set
     * this property to <code>false</code>. Remember to also set the
     * {@link org.springframework.security.context.HttpSessionContextIntegrationFilter#allowSessionCreation}
     * to <code>false</code> if you set this property to <code>false</code>.
     *
     * @return <code>true</code> if the <code>HttpSession</code> will be used to
     *         store information about the failed request, <code>false</code> if
     *         the <code>HttpSession</code> will not be used
     */
    public boolean isCreateSessionAllowed() {
        return createSessionAllowed;
    }

    protected void sendStartAuthentication(ServletRequest request,
            ServletResponse response, FilterChain chain,
            AuthenticationException reason) throws ServletException,
            IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        SavedRequest savedRequest = new SavedRequest(httpRequest, portResolver);

        if (logger.isDebugEnabled()) {
            logger
                    .debug("Authentication entry point being called; SavedRequest added to Session: "
                            + savedRequest);
        }

        if (createSessionAllowed) {
            // Store the HTTP request itself. Used by AbstractProcessingFilter
            // for redirection after successful authentication (SEC-29)
            httpRequest.getSession().setAttribute(
                    AbstractProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY,
                    savedRequest);
        }

        // SEC-112: Clear the SecurityContextHolder's Authentication, as the
        // existing Authentication is no longer considered valid
        SecurityContextHolder.getContext().setAuthentication(null);

        authenticationEntryPoint.commence(httpRequest, response, reason);
    }

    public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
        Assert.notNull(accessDeniedHandler, "AccessDeniedHandler required");
        this.accessDeniedHandler = accessDeniedHandler;
    }

    public void setAuthenticationEntryPoint(
            AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    public void setAuthenticationTrustResolver(
            AuthenticationTrustResolver authenticationTrustResolver) {
        this.authenticationTrustResolver = authenticationTrustResolver;
    }

    public void setCreateSessionAllowed(boolean createSessionAllowed) {
        this.createSessionAllowed = createSessionAllowed;
    }

    public void setPortResolver(PortResolver portResolver) {
        this.portResolver = portResolver;
    }

    public void setThrowableAnalyzer(ThrowableAnalyzer throwableAnalyzer) {
        this.throwableAnalyzer = throwableAnalyzer;
    }

    public int getOrder() {
        return FilterChainOrder.EXCEPTION_TRANSLATION_FILTER;
    }

    /**
     * Default implementation of <code>ThrowableAnalyzer</code> which is capable
     * of also unwrapping <code>ServletException</code>s.
     */
    private static final class DefaultThrowableAnalyzer extends
            ThrowableAnalyzer {
        /**
         * @see org.springframework.security.util.ThrowableAnalyzer#initExtractorMap()
         */
        protected void initExtractorMap() {
            super.initExtractorMap();

            registerExtractor(ServletException.class,
                    new ThrowableCauseExtractor() {
                        public Throwable extractCause(Throwable throwable) {
                            ThrowableAnalyzer.verifyThrowableHierarchy(
                                    throwable, ServletException.class);
                            return ((ServletException) throwable)
                                    .getRootCause();
                        }
                    });
        }

    }

    /**
     * Comprueba si la sesion esta caducada.
     *
     * @param request
     * @return
     */
    private boolean sesionCaducada(ServletRequest request) {
        boolean caducada = false;
        try {
            if (request != null) {
                final HttpServletRequest req = (HttpServletRequest) request;
                final ServletContext context = req.getSession()
                        .getServletContext();
                HttpSession sesion = null;
                if (context != null) {
                    sesion = SessionManager.getSession(req, context);
                    if (sesion != null) {
                        sesion.getAttribute("");
                    }
                }
            }
        } catch (final IllegalStateException e) {
            caducada = true;
        }
        return caducada;
    }

    public void redirect(final ServletResponse servletResponse) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect(urlSessionExpired);
    }

    public String getUrlSessionExpired() {
        return urlSessionExpired;
    }

    public void setUrlSessionExpired(String urlSessionExpired) {
        this.urlSessionExpired = urlSessionExpired;
    }

}
