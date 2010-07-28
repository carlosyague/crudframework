package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.HashMap;

import java.util.Map;

import org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate;
import org.librae.adminconfig.webapp.form.AsignarPermisoRolForm;
import org.librae.adminconfig.webapp.form.RolForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;

import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Asigna o desasigna los permisos a los roles.
 * 
 * @author jcisneros
 */
public class AsignarPermisoRolAction extends BasePage implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long     serialVersionUID             = -8930564567651768148L;

    private IGestionRolesDelegate delegate;
    private AsignarPermisoRolForm form                         = new AsignarPermisoRolForm();
    private String                page                         = "asignar";
    private String                idListado                    = null;
    private String                idForm                       = null;
    private String                idListadoPermisosAsignados   = null;
    private String                idListadoPermisosNoAsignados = null;

    /**
     * Recibe el id del rol y prepara el formulario.
     * 
     * @return pagina de asignar permisos.
     */
    public String getInit() {
        try {

            final AsignarPermisoRolForm formulario = (AsignarPermisoRolForm) getSessionManager()
                    .getAttribute(getIdForm());

            if (formulario == null) {
                final RolForm rolForm = (RolForm) getSessionManager()
                        .getAttribute(Constants.ROL_SESSION_NAME);

                form.setIdRol(rolForm.getIdRol());
                delegate.snapshot(form);

                getSessionManager().setAttribute(getIdForm(), form);
            } else {
                form = formulario;
            }
        } catch (final LibraeException e) {
            log.info("No se puede entrar al asignar permiso a rol...", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            page = "";
        } catch (final Exception e) {
            log.error("No se puede entrar al asignar permiso a rol...", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GENERICO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            page = "";
        }
        return "";
    }

    /**
     * Recibe el id del rol y prepara el formulario.
     * 
     * @return pagina de asignar permisos.
     */
    public String init() {
        try {
            final RolForm rolForm = (RolForm) getSessionManager().getAttribute(
                    Constants.ROL_SESSION_NAME);

            final Map<String, Object> filtros = new HashMap<String, Object>();
            filtros.put("idRol", rolForm.getIdRol());
            filtros.put(Constants.SORTCOLUMN, "codigo");
            filtros.put(Constants.ASCENDING, "true");

            getSessionManager().setAttribute(getIdListadoPermisosAsignados(),
                    filtros);
            getSessionManager().setAttribute(getIdListadoPermisosNoAsignados(),
                    filtros);

            form.setIdRol(rolForm.getIdRol());
            delegate.snapshot(form);

            getSessionManager().setAttribute(getIdForm(), form);
        } catch (final LibraeException e) {
            log.info("No se puede entrar al asignar permiso a rol...", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            page = "";
        } catch (final Exception e) {
            log.error("No se puede entrar al asignar permiso a rol...", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_GENERICO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            page = "";
        }
        return page;
    }

    /**
     * Buscar los permisos no asignados.
     * 
     * @return pagina de asignar permisos.
     */
    public String buscar() {
        try {
            delegate.buscar(form);
        } catch (final LibraeException e) {
            log.info("No se puede buscar la lista de permisos...", e);
        } catch (final Exception e) {
            log.error("No se puede buscar la lista de permisos...", e);
        }
        return page;
    }

    /**
     * Asigna todos los permisos al rol.
     * 
     * @return pagina de asignar permisos.
     */
    public String asignarTodos() {
        try {
            delegate.asignarTodos(form);
        } catch (final LibraeException e) {
            log.info("No se puede asignar permiso a rol...", e);
        } catch (final Exception e) {
            log.error("No se puede asignar permiso a rol...", e);
        }
        return page;
    }

    /**
     * Desasigna todos los permisos.
     * 
     * @return pagina de asignar permisos.
     */
    public String desasignarTodos() {
        try {
            delegate.desasignarTodos(form);
        } catch (final LibraeException e) {
            log.info("No se puede desasignar todos los permiso a rol...", e);
        } catch (final Exception e) {
            log.error("No se puede desasignar todos los permiso a rol...", e);
        }
        return page;
    }

    /**
     * Asigna el permisos al rol.
     * 
     * @return pagina de asignar permisos.
     */
    public String asignar() {
        try {
            delegate.asignar(form);
        } catch (final LibraeException e) {
            log.info("No se puede asignar permiso a rol...", e);
        } catch (final Exception e) {
            log.error("No se puede asignar permiso a rol...", e);
        }
        return page;
    }

    /**
     * Desasigna un permiso a un rol.
     * 
     * @return pagina de asignar permisos.
     */
    public String desasignar() {
        try {
            delegate.desasignar(form);
        } catch (final LibraeException e) {
            log.info("No se desasignar permiso a rol...", e);
        } catch (final Exception e) {
            log.error("No se desasignar permiso a rol...", e);
        }
        return page;
    }

    /**
     * Guarda en base de datos la asignacion.
     * 
     * @return pagina del listado de roles.
     */
    public String save() {
        page = "list";
        try {
            delegate.save(form);
        } catch (final LibraeException e) {
            log.info("No se puede asignar el permiso a rol...", e);
            page = "asignar";
        } catch (final Exception e) {
            log.error("No se puede asignar el permiso a rol...", e);
            page = "asignar";
        }
        return page;
    }

    /**
     * Cancela la asignación de permisos a un Rol.
     * 
     * @return String con el identificador de navigation rule (list)
     */
    public String cancel() {
        return "form";
    }

    public String getLimpiarForm() {
        getSessionManager().removeAttribute(getIdForm());
        return null;
    }

    // Getters && Setters

    public IGestionRolesDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(final IGestionRolesDelegate delegate) {
        this.delegate = delegate;
    }

    public AsignarPermisoRolForm getForm() {
        return form;
    }

    public void setForm(final AsignarPermisoRolForm form) {
        this.form = form;
    }

    public String getIdListado() {
        return idListado;
    }

    public void setIdListado(final String idListado) {
        this.idListado = idListado;
    }

    /**
     * @return the idListadoPermisosAsignados
     */
    public String getIdListadoPermisosAsignados() {
        return idListadoPermisosAsignados;
    }

    /**
     * @param idListadoPermisosAsignados
     *            the idListadoPermisosAsignados to set
     */
    public void setIdListadoPermisosAsignados(String idListadoPermisosAsignados) {
        this.idListadoPermisosAsignados = idListadoPermisosAsignados;
    }

    /**
     * @return the idListadoPermisosNoAsignados
     */
    public String getIdListadoPermisosNoAsignados() {
        return idListadoPermisosNoAsignados;
    }

    /**
     * @param idListadoPermisosNoAsignados
     *            the idListadoPermisosNoAsignados to set
     */
    public void setIdListadoPermisosNoAsignados(
            String idListadoPermisosNoAsignados) {
        this.idListadoPermisosNoAsignados = idListadoPermisosNoAsignados;
    }

    /**
     * @return the idForm
     */
    public String getIdForm() {
        return idForm;
    }

    /**
     * @param idForm
     *            the idForm to set
     */
    public void setIdForm(String idForm) {
        this.idForm = idForm;
    }

}
