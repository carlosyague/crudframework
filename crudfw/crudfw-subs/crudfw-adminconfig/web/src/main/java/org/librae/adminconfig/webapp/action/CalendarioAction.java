/*
 * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos de
 * explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es software
 * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los t�rminos
 * de la Licencia EUPL European Public License publicada por el organismo IDABC
 * de la Comisi�n Europea, en su versi�n 1.0. o posteriores. �ste programa se
 * distribuye de buena fe, pero SIN NINGUNA GARANT�A, incluso sin las presuntas
 * garant�as impl�citas de USABILIDAD o ADECUACI�N A PROP�SITO CONCRETO. Para
 * mas informaci�n consulte la Licencia EUPL European Public License. Usted
 * recibe una copia de la Licencia EUPL European Public License junto con este
 * programa, si por alg�n motivo no le es posible visualizarla, puede
 * consultarla en la siguiente URL:
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
 * copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu une
 * copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden
 * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */

package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import org.librae.common.Constants;
import org.librae.common.webapp.action.BasePage;
import org.librae.adminconfig.webapp.form.BibliotecaForm;
import org.librae.adminconfig.webapp.form.CalendarioForm;
import org.librae.common.webapp.util.WindowMessages;
import org.librae.common.exception.MensajesError;
import java.util.Date;
import org.librae.adminconfig.webapp.delegate.ICalendarioDelegate;

/**
 * Action-JSF asociado al formulario de la creación de calendarios y días
 * festivos. Debido a que se trata de una pantalla auxiliar a una pantalla
 * principal (edicion de bibliotecas por ejemplo) sus datos no deben guardarse
 * en BD hasta que se confirme la pantalla principal. De manera que esta
 * pantalla requiere que exista en session un posible objeto CalendarioForm con
 * los datos de una posible edicion de un calendario existente. Su método save
 * actualizará este objeto de session con nombre
 * Constants.CALENDARIO_SESSION_NAME
 */
public class CalendarioAction extends BasePage implements Serializable {

    private static final long serialVersionUID = 1L;

    // formulario de datos
    private CalendarioForm    form             = new CalendarioForm();

    // delegate
    ICalendarioDelegate       delegate;

    /**
     * Método invocado cuando se entra en la pantalla de calendario Se encargará
     * de obtener de session el posible objeto CalendarioForm para inicializar
     * los datos
     * 
     * @return cadena vacia
     */
    public String getInit() {
        if (getSessionManager() != null
                && getSessionManager().getAttribute(
                        Constants.CALENDARIO_SESSION_NAME) != null) {
            form = (CalendarioForm) getSessionManager().getAttribute(
                    Constants.CALENDARIO_SESSION_NAME);
            final BibliotecaForm formularioBiblioteca = (BibliotecaForm) getSessionManager()
                    .getAttribute(Constants.BIBLIOTECA_SESSION_NAME);
            form.setNombreBiblioteca(formularioBiblioteca.getNombre());
        }

        return "";
    }

    /**
     * Método invocado al pinchar en cancelar devuelve la regla de navegacion
     * que la pantalla llamante haya especificado en
     * CalendarioForm.navigationRuleBack
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
     * CalendarioForm con los datos y la key Constants.CALENDARIO_SESSION_NAME a
     * la espera de que la pantalla llamante lo gestione.
     * 
     * @return String
     */
    public String save() {
        String res = null;
        CalendarioForm antiguoCalendario = null;

        try {
            if (getSessionManager() != null
                    && getSessionManager().getAttribute(
                            Constants.CALENDARIO_SESSION_NAME) != null) {
                antiguoCalendario = (CalendarioForm) getSessionManager()
                        .getAttribute(Constants.CALENDARIO_SESSION_NAME);
            }

            final String codeError = delegate.validar(form, antiguoCalendario);
            if ("".equals(codeError)) {
                if (getSessionManager() != null) {
                    form.setUltimaActualizacion(new Date());
                    getSessionManager().setAttribute(
                            Constants.CALENDARIO_SESSION_NAME, form);
                    res = cancel();
                }
            } else {
                final WindowMessages wm = new WindowMessages();
                wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                        codeError);
                setWindowMessages(wm);
            }
        } catch (final Exception e) {
            log.error("Error inesperado al salvar el calendario: ", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode("ERROR_NO_DESEADO");
            setWindowMessages(wm);
            res = null;
        }

        return res;
    }

    // Getters && Setters

    public CalendarioForm getForm() {
        return form;
    }

    public void setForm(final CalendarioForm form) {
        this.form = form;
    }

    public ICalendarioDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(final ICalendarioDelegate delegate) {
        this.delegate = delegate;
    }

}