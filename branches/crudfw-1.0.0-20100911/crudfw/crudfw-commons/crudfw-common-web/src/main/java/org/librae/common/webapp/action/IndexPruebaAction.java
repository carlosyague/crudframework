package org.librae.common.webapp.action;

import org.librae.common.webapp.session.SessionManager;

/**
 * Action-JSF asociado al arbol de las bibliotecas.
 * 
 * @author jcisneros
 */
public class IndexPruebaAction extends BasePage {

    private static final String SESSION_PARAM         = "pp";
    private static final String SESSION_VALUE_ADM_CFG = "valor guardado por ADM-CFG";
    private static final String SESSION_VALUE_IMP_EXP = "valor guardado por IMP-EXP";

    public String getInit() {

        SessionManager.init(this.getRequest(), this.getResponse(), this
                .getServletContext());
        this.getSessionManager().setAttribute(SESSION_PARAM,
                SESSION_VALUE_ADM_CFG);
        final String res = this.getSessionManager().getAttribute(SESSION_PARAM)
                .toString();
        return res;
    }

    public Object getSessionParamPP() {

        final Object obj = this.getSessionManagerParam(SESSION_PARAM);
        return obj;
    }

    public String getSetSessionParamPPAdmCfg() {
        this.setSessionManagerParam(SESSION_PARAM, SESSION_VALUE_ADM_CFG);
        return "set-session-param-admcfg ejecutado";
    }

    public String getSetSessionParamPPImpExp() {
        this.setSessionManagerParam(SESSION_PARAM, SESSION_VALUE_IMP_EXP);
        return "set-session-param-impexp ejecutado";
    }

}
