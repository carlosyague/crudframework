package org.librae.common.webapp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import model.CodPer;

import org.librae.common.Constants;
import org.librae.common.model.LibraeUser;
import org.librae.common.webapp.session.SessionManager;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.intercept.web.FilterSecurityInterceptor;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;

/**
 * Clase heredera de FilterSecurityInterceptor que fuerza la reobtencion de los
 * permisos del usuario logado en el caso de cambiar de subsistema
 */
public class SIGBFilterSecurityInterceptor extends FilterSecurityInterceptor {

    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

    	List<CodPer> permisos = new ArrayList<CodPer>();
        final Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        final LibraeUser usuarioLibrae = getLibraeUser(request);
        if ((usuarioLibrae!=null) && (usuarioLibrae.getPer()!=null)) {
        	permisos = usuarioLibrae.getPer();
        }

        // si la lista de permisos solo tiene el basico ROLE_USER
        // o bien no coincide con los del usuario librae
        // es posible que se haya producido un cambio de subsistema por lo
        // que forzamos la recuperacion de permisos
        if (authentication != null && authentication.isAuthenticated()
                && (authentication.getPrincipal() instanceof User)
                && authentication.getAuthorities() != null
                && userDetailsService != null && permisos != null
                && usuarioLibrae != null) {

            if (authentication.getAuthorities().length == 1
                    || (usuarioLibrae != null && !igualesPermisos(permisos,
                            authentication.getAuthorities()))) {

                // 1. Forzamos la reobtencion de sus permisos
                final GrantedAuthority[] permisosSession = new GrantedAuthority[permisos
                        .size()];
                for (int i = 0; i < permisos.size(); i++) {
                    permisosSession[i] = new GrantedAuthorityImpl(permisos.get(
                            i).getCod());
                }
                User nuevo = new User(usuarioLibrae.getUsuario(), "", true, false, false, true, permisosSession);
                // 2.Guardamos el nuevo usuario con los nuevos permisos
                final UsernamePasswordAuthenticationToken nuevoUsuario = new UsernamePasswordAuthenticationToken(
                		nuevo, authentication.getCredentials(), permisosSession);
                SecurityContextHolder.getContext().setAuthentication(
                        nuevoUsuario);
            }
        }
        super.doFilter(request, response, chain);
    }

    /**
     * Metodo encargado de obtener el usuario librae de la sesion compartida de
     * la aplicacion
     * 
     * @param request
     * @return
     */
    private LibraeUser getLibraeUser(ServletRequest request) {
    	LibraeUser res = null;
        try {
            if (request != null) {
                final HttpServletRequest req = (HttpServletRequest) request;
                final ServletContext context = req.getSession()
                        .getServletContext();
                SessionManager sesion = null;
                if (context != null) {
                    sesion = SessionManager.getSessionManager(req, context);
                    if (sesion != null) {
                    	res = (LibraeUser) sesion.getAttribute(Constants.LIBRAE_USUARIO_SESSION_PARAM);
                    }
                }
            }
        } catch (final IllegalStateException e) {
            res = null;
        }
        return res;
    }

    private boolean igualesPermisos(List<CodPer> permisosLibrae,
            GrantedAuthority[] permisosAcegi) {
        boolean iguales = true;

        if (permisosLibrae.size() == permisosAcegi.length) {
            for (int i = 0; i < permisosLibrae.size() && iguales; i++) {
                iguales = permisosLibrae.contains(permisosAcegi[i].toString());
            }
        } else {
            iguales = false;
        }

        return iguales;
    }

    protected UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
