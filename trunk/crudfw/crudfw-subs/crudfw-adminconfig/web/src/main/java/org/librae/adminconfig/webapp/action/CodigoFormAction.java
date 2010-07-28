package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.webapp.delegate.IGestionCodigosDelegate;
import org.librae.adminconfig.webapp.form.CodigoForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los formularios de la entidad Rol.
 * 
 * @author jcisneros
 */
public class CodigoFormAction extends ValorCodigoSearchAction implements
        Serializable {

    public String getInit() {
        final CodigoForm form = (CodigoForm) getSessionManagerParam("codigo_form");
        if (form != null) {
            codigoForm = form;
        }
        return "";
    }

    /**
     * Numero de serializacion.
     */
    private static final long       serialVersionUID = 1L;
    private IGestionCodigosDelegate gestionCodigosDelegate;
    private CodigoForm              codigoForm       = new CodigoForm();
    private Long                    idCodigo         = null;

    public CodigoFormAction() {
        super();
        setSortColumn("codigo");
    }

    /**
     * Guarda un codigo.
     * 
     * @return vista de roles.
     */
    public String saveCodigo() {
        String page = "list";
        try {
            final Long idCodigo = (Long) getSessionManagerParam("idCodigo_form");
            final List<Object> valores = (List<Object>) getSessionManagerParam("valores");
            gestionCodigosDelegate.save(idCodigo, codigoForm, valores);
        } catch (final LibraeException e) {
            log.info("Error al salvar el codigo...", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            page = "form";
        } catch (final Exception e) {
            log.error("Error al salvar el  codigo...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode("ERROR_INSERTAR_CODIGO");
            setWindowMessages(wm);
            page = "form";
        }
        return page;
    }

    /**
     * Guarda un codigo.
     * 
     * @return vista de roles.
     */
    public String saveCodigoGuardar() {
        String page = "form";
        Codigo codigo = null;
        try {
            setSessionManagerParam("codigo_form", codigoForm);
            final Long idCodigo = (Long) getSessionManagerParam("idCodigo_form");
            final List<Object> valores = (List<Object>) getSessionManagerParam("valores");
            codigo = gestionCodigosDelegate.save(idCodigo, codigoForm, valores);
            codigoForm.fromEntity(codigo);
            codigoForm.setIdCodigo(codigo.getId());
            setIdCodigo(codigo.getId());
            codigoForm.setReadMode(true);
            setSessionManagerParam("idCodigo_form", idCodigo);
            setSessionManagerParam("codigo_form", codigoForm);
            setMensajeExito("MENSAJE_EXITO_CODIGO",
                    MensajesError.PROPERTI_ADMINCONFIG);
        } catch (final LibraeException e) {
            log.info("Error al salvar el codigo...", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            page = "form";
        } catch (final Exception e) {
            log.error("Error al salvar el codigo...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode("ERROR_INSERTAR_CODIGO");
            setWindowMessages(wm);
            page = "form";
        }
        return page;
    }

    /**
     * Guarda el formulario de codigo y llama al editar el valor codigo.
     * 
     * @return vista de codigos.
     */
    public String updateCodigo() {
        String page = "update";
        try {
            setSessionManagerParam("codigo_form", codigoForm);
            setSessionManagerParam("valor_codigo_key", getKey());
            setSessionManagerParam("valor_codigo_order", getSortColumn());
            setSessionManagerParam("valor_codigo_ascending", isAscending());
            return page;
        } catch (final Exception e) {
            log.error("Error al modificar el valor del codigo...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_MODIFICAR_VALOR_CODIGO");
            setWindowMessages(wm);
            page = "list";
        }
        return page;
    }

    /**
     * Guarda el formulario de codigo y llama al editar el codigo.
     * 
     * @return vista de codigos.
     */
    public String update() {
        String page = "add";
        try {
            final Codigo codigo = gestionCodigosDelegate
                    .getCodigoById(idCodigo);
            codigoForm.fromEntity(codigo);
            codigoForm.setTiposBiblioteca(gestionCodigosDelegate
                    .getTiposBibliotecaByCodigos(codigo.getAplicaGBS()));
            final int i = codigo.getValores().size();
            setSessionManagerParam("valores", codigo.getValores());
            codigoForm.setReadMode(true);
            setSessionManagerParam("idCodigo_form", idCodigo);
            setSessionManagerParam("codigo_form", codigoForm);
        } catch (final Exception e) {
            log.error("Error al modificar el valor del codigo...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_MODIFICAR_CODIGO");
            setWindowMessages(wm);
            page = "list";
        }
        return page;
    }

    /**
     * Modifica un codigo.
     * 
     * @return vista de codigos.
     */
    public String deleteCodigo() {
        String page = "list";
        try {
            gestionCodigosDelegate.delete(codigoForm.getIdCodigo());
        } catch (final LibraeException e) {
            log.info("Error al salvar el valor del codigo...", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            page = "form";
        } catch (final Exception e) {
            log.error("Error al salvar el valor del codigo...", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_ELIMINAR_CODIGO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            page = "form";
        }
        return page;
    }

    /**
     * Cancela el crear un codigo.
     * 
     * @return vista de codigos.
     */
    public String editable() {
        final String page = null;
        try {
            setCodigoForm((CodigoForm) getSessionManagerParam("codigo_form"));
            codigoForm.setReadMode(false);
            setSessionManagerParam("codigo_form", getCodigoForm());
        } catch (final Exception e) {
            log.error("Error al modificar el codigo...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode("ERROR_MODIFICAR_CODIGO");
            setWindowMessages(wm);
        }
        return page;
    }

    protected Map<String, Object> completarFormPdf() {
        final CodigoForm codigoForm = (CodigoForm) getSessionManagerParam("codigo_form");
        final Map<String, Object> filtrosPdf = new LinkedHashMap<String, Object>();

        if (codigoForm != null) {
            if (!StringUtil.esVacia(codigoForm.getCodigo())) {
                filtrosPdf.put(ComboLocaleManager.get("codigo.codigo"),
                        codigoForm.getCodigo());
            }
            if (!StringUtil.esVacia(codigoForm.getNombre())) {
                filtrosPdf.put(ComboLocaleManager.get("codigo.nombre"),
                        codigoForm.getNombre());
            }
        }
        return filtrosPdf;
    }

    /**
     * Cargar el valor.
     * 
     * @return siguiente pagina.
     */
    public String getLoadCodigo() {
        try {
            final CodigoForm form = (CodigoForm) getSessionManagerParam("codigo_form");
            if (form != null) {
                codigoForm = form;
            }
            setIdCodigo((Long) getSessionManagerParam("idCodigo_form"));
            removeSessionManagerParam("valor_codigo_key");
            removeSessionManagerParam("valorCodigoForm");
        } catch (final Exception e) {
            log.error("Error al cargar el codigo...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_INIT_CODIGO");
            setWindowMessages(wm);
        }
        return null;
    }

    // Getter && Setters

    public IGestionCodigosDelegate getGestionCodigosDelegate() {
        return gestionCodigosDelegate;
    }

    public void setGestionCodigosDelegate(
            final IGestionCodigosDelegate gestionCodigosDelegate) {
        this.gestionCodigosDelegate = gestionCodigosDelegate;
    }

    public CodigoForm getCodigoForm() {
        return codigoForm;
    }

    public void setCodigoForm(final CodigoForm codigoForm) {
        this.codigoForm = codigoForm;
    }

    public Long getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(final Long idCodigo) {
        this.idCodigo = idCodigo;
    }

}