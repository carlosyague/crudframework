package org.librae.adminconfig.webapp.action;

import java.io.Serializable;

import org.librae.adminconfig.webapp.delegate.IDireccionDelegate;
import org.librae.adminconfig.webapp.form.DireccionForm;
import org.librae.common.Constants;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado al formulario de la creación de direcciones. Debido a que
 * se trata de una pantalla auxiliar a una pantalla principal (edicion de
 * bibliotecas por ejemplo) sus datos no deben guardarse en BD hasta que se
 * confirme la pantalla principal. De manera que esta pantalla requiere que
 * exista en session un posible objeto DireccionForm con los datos de una
 * posible edicion de una direccion existente. Su método save actualizará este
 * objeto de session con nombre Constants.DIRECCION_SESSION_NAME
 * 
 * @author aropero
 */
public class DireccionAction extends BasePage implements Serializable {

    private static final long serialVersionUID = 1L;

    /** formulario de datos de direccion */
    private DireccionForm     form             = new DireccionForm();

    /** delegate de direccion */
    IDireccionDelegate        delegate;

    /**
     * Método invocado cuando se entra en la pantalla de calendario Se encargará
     * de obtener de session el posible objeto DireccionForm para inicializar
     * los datos
     * 
     * @return cadena vacia
     */
    public String getInit() {
        if (getSessionManager() != null
                && getSessionManager().getAttribute(
                        Constants.DIRECCION_SESSION_NAME) != null) {
            form = (DireccionForm) getSessionManager().getAttribute(
                    Constants.DIRECCION_SESSION_NAME);
        }

        return "";
    }

    /**
     * Método invocado al pinchar en cancelar devuelve la regla de navegacion
     * que la pantalla llamante haya especificado en
     * DireccionForm.navigationRuleBack
     * 
     * @return String
     */
    public String cancel() {
        String res = null;

        if (form.getNavigationRuleBack() != null) {
            res = form.getNavigationRuleBack();
        }

        return res;
    }

    /**
     * Método invocado al pinchar en guardar. Guarda en session un objeto
     * DireccionForm con los datos y la key Constants.DIRECCION_SESSION_NAME a
     * la espera de que la pantalla llamante lo gestione.
     * 
     * @return String
     */
    public String save() {
        String res = null;

        try {
            final String codeError = delegate.validar(form);
            if ("".equals(codeError)) {
                if (getSessionManager() != null) {
                    getSessionManager().setAttribute(
                            Constants.DIRECCION_SESSION_NAME, form);
                    res = cancel();
                }
            } else {
                final WindowMessages wm = new WindowMessages();
                wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                        codeError);
                setWindowMessages(wm);
                getSessionManager().setAttribute(
                        Constants.DIRECCION_SESSION_NAME, form);
            }
        } catch (final Exception e) {
            log.error("Error inesperado al salvar la direccion: ", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode("ERROR_NO_DESEADO");
            setWindowMessages(wm);
            res = null;
        }

        return res;
    }

    /** ============ Getters && Setters =============== */

    public DireccionForm getForm() {
        return form;
    }

    public void setForm(DireccionForm form) {
        this.form = form;
    }

    public IDireccionDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(IDireccionDelegate delegate) {
        this.delegate = delegate;
    }

}
