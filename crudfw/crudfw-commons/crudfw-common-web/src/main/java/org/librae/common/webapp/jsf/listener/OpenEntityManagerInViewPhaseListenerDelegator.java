package org.librae.common.webapp.jsf.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * This class is a PhaseListener that serves as a delegates to a spring
 * configured bean (also a PhaseListener) that will setup an entity manager when
 * JSF is going through its phases. It is configured through the web.xml file,
 * which must define a context-param name
 * (key:openEntityManagerInViewPhaseListenerBeanName) containing the name of the
 * spring configured PhaseListener bean to which to delegate. It is meant to
 * reproduce the same pattern as the
 * org.springframework.web.filter.DelegatingFilterProxy /
 * org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter in non-JSF
 * spring based JPA / WEB applications.
 * 
 * @author Vincent Gigu�re mailto:vincent.giguere@gmail.com --- CONFIGURATION
 *         EXAMPLE ---- web.xml <context-param>
 *         <param-name>openEntityManagerInViewPhaseListenerBeanName</param-name>
 *         <param-value>openEntityManagerInViewPhaseListener</param-value>
 *         </context-param> applicationContext.xml <bean
 *         id="openEntityManagerInViewPhaseListener" class=
 *         "com.covansys.infra.jpa.support.OpenEntityManagerInViewPhaseListener"
 *         > <property name="entityManagerFactoryBeanName"
 *         value="entityManagerFactory" /> </bean> faces-config.xml <lifecycle>
 *         <phase-listener>com.covansys.infra.jpa.support.
 *         OpenEntityManagerInViewPhaseListenerDelegator</phase-listener>
 *         </lifecycle>
 */

public class OpenEntityManagerInViewPhaseListenerDelegator implements
        PhaseListener {

    private static final long   serialVersionUID       = 6174762137905374162L;
    private final static Logger logger                 = Logger
                                                               .getLogger(OpenEntityManagerInViewPhaseListenerDelegator.class);

    // This is the key used in web.xml to identify the name of the spring bean
    // to which to delegate.
    public static final String  DELEGATE_BEAN_NAME_KEY = "openEntityManagerInViewPhaseListenerBeanName";
    private String              delegatingBeanName     = null;

    /**
     * Delegates the afterPhase to the delegate bean
     */
    public void afterPhase(PhaseEvent phaseEvent) {
        final PhaseListener delegate = getDelegatingBean();
        if (delegate == null) {
            logger
                    .error("OpenEntityManagerInViewPhaseListenerDelegator is registered in faces-config, but its delegate is either not presend or misconfigured.");
        } else {
            delegate.afterPhase(phaseEvent);
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * Delegates the beforePhase to the delegate bean
     */
    public void beforePhase(PhaseEvent phaseEvent) {

        final PhaseListener delegate = getDelegatingBean();
        if (delegate == null) {
            logger
                    .error("A OpenEntityManagerInViewPhaseListenerDelegator is registered in faces-config, but its delegate is either not presend or misconfigured.");
        } else {
            delegate.beforePhase(phaseEvent);
        }
    }

    /**
     * This method looks up Spring containers trying to find the bean that
     * matches the delegating bean name
     * 
     * @return A PhaseListener found under the specific name. Null if not found
     *         or not a PhaseListener
     */
    private PhaseListener getDelegatingBean() {
        final String beanName = getDelegatingBeanName();
        if (beanName == null) {
            logger
                    .error("OpenEntityManagerInViewPhaseListenerDelegator could not find OpenEntityManagerInViewPhaseListener bean. Read the javadoc on OpenEntityManagerInViewPhaseListenerDelegator and Review your web.xml, faces-config.xml and applicationContext.xml files.");
            return null;
        }

        final Object delegateBean = getWebApplicationContext()
                .getBean(beanName);
        if (!(delegateBean instanceof PhaseListener)) {
            logger
                    .error("OpenEntityManagerInViewPhaseListenerDelegator found a bean named ["
                            + beanName
                            + "] but it is not a javax.faces.event.PhaseListener. Review your configuration");
            return null;
        }
        return (PhaseListener) delegateBean;
    }

    /**
     * This method looks up the servlet context parameters to retrieve the name
     * of the delegate bean in Spring.
     * 
     * @return A String refering the the delegate bean name in Spring
     */
    private String getDelegatingBeanName() {
        if (this.delegatingBeanName == null) {
            final WebApplicationContext ctx = getWebApplicationContext();
            this.delegatingBeanName = ctx
                    .getServletContext()
                    .getInitParameter(
                            OpenEntityManagerInViewPhaseListenerDelegator.DELEGATE_BEAN_NAME_KEY);
        }
        return this.delegatingBeanName;

    }

    private WebApplicationContext getWebApplicationContext() {
        return FacesContextUtils.getRequiredWebApplicationContext(FacesContext
                .getCurrentInstance());
    }
}