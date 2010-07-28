package org.librae.adminconfig.webapp.action;

import java.io.Serializable;

import org.librae.adminconfig.model.Catalogo;
import org.librae.adminconfig.webapp.delegate.ICatalogoDelegate;
import org.librae.adminconfig.webapp.form.CatalogoForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los formularios de la entidad Catalogo
 *
 * @author impena
 */
public class CatalogoFormAction extends BasePage implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1609850678909357049L;
    private ICatalogoDelegate catalogoDelegate;
    private CatalogoForm      catalogoForm     = new CatalogoForm();
    private Long              idCatalogo       = null;

    /**
     * Método invocado cuando se entra en la pantalla de creación/edición de un
     * Catalogo. Se encargará de obtener de session el posible objeto
     * CatalogoForm para inicializar los datos
     *
     * @return cadena vacia
     */
    public String getInit() {
        SessionManager sesion = getSessionManager();
        if (sesion != null) {
            final CatalogoForm form = (CatalogoForm) sesion
                    .getAttribute(Constants.CATALOGO_SESSION_NAME);
            if (form != null) {
                catalogoForm = form;
            }
            if ((catalogoForm != null) && (catalogoForm.getCodigo() != null)) {
                sesion.setAttribute(Constants.CONTEXTO_CODIGO_CATALOGO,
                        catalogoForm.getCodigo());
            }
        }
        return "";
    }

    /**
     * Método invocado cuando se entra en el formulario de edición de un
     * Catalogo. Se encargará de preparar los datos para su correcta
     * visualización invocando a su método delegado prepararDatosVista
     *
     * @return String con el identificador de navigation rule (edit)
     */

    public String edit() {
        String page = "form";
        try {
            catalogoForm.setId(idCatalogo);
            catalogoDelegate.prepararDatosVista(catalogoForm);
            catalogoForm.setReadMode(true);
            catalogoForm.setCreacion(false);
            setSessionManagerParam(Constants.CATALOGO_SESSION_NAME,
                    catalogoForm);
        } catch (final LibraeException e) {
            log.info("No se puede editar el catalogo: ", e);
            page = "list";
        } catch (final Exception e) {
            log.error("No se puede editar el catalogo: ", e);
            page = "list";
        }
        return page;
    }

    /**
     * Método invocado al pulsar en el botón guardar de la pestaña Catalogo.
     * Guardará en BD los datos de esa pestaña invocando a su método delegate.
     * Vuelve al listado.
     *
     * @return String con el identificador de navigation rule (save)
     */
    public String saveAceptar() {
        try {
            catalogoDelegate.guardarCatalogo(catalogoForm);
            if (catalogoForm.getCodigo() != null) {
                setSessionManagerParam(Constants.CONTEXTO_CODIGO_CATALOGO,
                        catalogoForm.getCodigo());
            }
        } catch (final LibraeException e) {
            log.info("No se puede guardar el catalogo: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "form";
        } catch (final Exception e) {
            log.error("No se puede guardar el catalogo: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GUARDAR_CATALOGO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "form";
        }
        catalogoForm.setReadMode(true);
        return "list";
    }

    /**
     * Método invocado al pulsar en el botón guardar de la pestaña Catalogo.
     * Guardará en BD los datos de esa pestaña invocando a su método delegate.
     * Se queda en la misma página.
     *
     * @return String con el identificador de navigation rule (guardar)
     */
    public String saveGuardar() {
        String page = "form";
        Catalogo catalogo = null;
        try {
            catalogo = catalogoDelegate.guardarCatalogo(catalogoForm);
            catalogoForm.setId(catalogo.getId());
            setMensajeExito("MENSAJE_EXITO_CATALOGO",
                    MensajesError.PROPERTI_ADMINCONFIG);
            catalogoForm.setReadMode(true);
            catalogoForm.setCreacion(false);
        } catch (final LibraeException e) {
            log.info("No se puede guardar el Catalogo: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            page = "";
        } catch (final Exception e) {
            log.error("Error al guardar el Catalogo: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GUARDAR_CATALOGO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            page = "";
        }

        // Metemos el formulario de catalogo en session para la vuelta

        setSessionManagerParam(Constants.CATALOGO_SESSION_NAME, catalogoForm);

        return page;
    }

    /**
     * Método invocado al pulsar en el botón eliminar de la pestaña de Catalogo.
     *
     * @return String con el identificador de navigation rule (list)
     */
    public String delete() {

        try {
            catalogoDelegate.eliminar(catalogoForm.getId());
        } catch (final LibraeException e) {
            log.info("No se puede eliminar el Catalogo: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "form";
        } catch (final Exception e) {
            log.error("Error al eliminar el Catalogo: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_ELIMINAR_CATALOGO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "form";
        }

        catalogoForm.setReadMode(true);
        return "list";
    }

    /**
     * Método que devuelve el formulario en forma editable. Responde al clic en
     * el boton Editar en la pantalla de Detalle de Catalogo.
     */
    public void editable() {
        final CatalogoForm form = (CatalogoForm) getSessionManagerParam(Constants.CATALOGO_SESSION_NAME);
        if (form != null) {
            form.setReadMode(false);
            setSessionManagerParam(Constants.CATALOGO_SESSION_NAME, form);
            catalogoForm = form;
        }
    }

    // ==================== Getters && Setters ===================

    public ICatalogoDelegate getCatalogoDelegate() {
        return catalogoDelegate;
    }

    public void setCatalogoDelegate(ICatalogoDelegate catalogoDelegate) {
        this.catalogoDelegate = catalogoDelegate;
    }

    public CatalogoForm getCatalogoForm() {
        return catalogoForm;
    }

    public Long getIdCatalogo() {
        return idCatalogo;
    }

    public void setCatalogoForm(CatalogoForm catalogoForm) {
        this.catalogoForm = catalogoForm;
    }

    public void setIdCatalogo(Long idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

}