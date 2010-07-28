package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.TipoPermiso;
import org.librae.adminconfig.webapp.delegate.IPermisoSearchDelegate;
import org.librae.adminconfig.webapp.form.PermisoCriteriaForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.action.BaseSearchPage;

import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los listados de la Permiso.
 * 
 * @author jcisneros
 */
public class PermisoSearchAction extends BaseSearchPage implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = -3669168259497448808L;

    /**
     * Constructor.
     */
    public PermisoSearchAction() {
        super();
    }

    /**
     * Método invocado para eliminar los favoritos seleccionados de base de
     * datos 1. Obtener los identificadores seleccionados 2. Invocar al método
     * delegate
     */
    public String delete() {
        try {
            final List<Permiso> favoritos = obtenerFavoritosSeleccionados(getIdListado());
            ((IPermisoSearchDelegate) getDelegate()).eliminar(favoritos);
            limpiarMarcados(getIdListado());
        } catch (final LibraeException e) {
            log.info("No se puede eliminar el favorito: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("No se puede eliminar el favorito: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_ELIMINAR_FAVORITO",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }

        getSessionManager().setAttribute(getIdListado(), null);
        getSessionManager().setAttribute(BasePage.PREFIJO + getIdListado(),
                null);
        getSessionManager().setAttribute(BasePage.PREFIJOALL + getIdListado(),
                null);

        return null;
    }

    /**
     * Método que comprueba los favoritos que se han seleccionado para operar
     * con ellos y devuelve un listado de los mismos.
     * 
     * @param idListado
     *            , identificador del listado de roles
     * @return Lista de favoritos seleccionados
     */
    private List<Permiso> obtenerFavoritosSeleccionados(final String idListado) {
        List<Permiso> listaFavoritos = null;

        if (getSessionManager() != null) {
            if (super.todosMarcados(idListado)) {
                final Map<String, Object> filtros = (Map<String, Object>) getSessionManager()
                        .getAttribute(idListado);
                if (filtros != null) {
                    filtros.remove(Constants.SORTCOLUMN);
                    filtros.remove(Constants.ASCENDING);
                    filtros.remove(Constants.PAGESIZE);
                    filtros.remove(Constants.CURRENTPAGE);
                }
                listaFavoritos = ((IPermisoSearchDelegate) getDelegate())
                        .buscar(filtros);
            } else {
                final List<String> listadoId = super.obtenerMarcados(idListado);
                if ((listadoId == null) || (listadoId.isEmpty())) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_ADMINCONFIG,
                            "ERROR_NINGUNA_SELECCION");
                }
                listaFavoritos = ((IPermisoSearchDelegate) getDelegate())
                        .getPermiso(listadoId);
            }
        }
        return listaFavoritos;
    }

    /**
     * Método que comprueba los roles que se han seleccionado para operar con
     * ellos y devuelve el Rol seleccionado o lanza una excepción si no se ha
     * seleccionado ninguno o se han seleccionado más de uno.
     * 
     * @param idListado
     *            , identificador del listado de roles
     * @return identificador del Rol seleccionado.
     */
    private List<Permiso> obtenerPermisosSeleccionados(final String idListado) {
        List<Permiso> permisos = new ArrayList<Permiso>();

        if (getSessionManager() != null) {
            if (super.todosMarcados(idListado)) {
                final Map<String, Object> filtros = obtenerFiltros(false);
                if (filtros != null) {
                    filtros.remove(Constants.SORTCOLUMN);
                    filtros.remove(Constants.ASCENDING);
                    filtros.remove(Constants.PAGESIZE);
                    filtros.remove(Constants.CURRENTPAGE);
                }
                permisos = getDelegate().buscar(filtros);
            } else {
                final List<String> listadoId = super.obtenerMarcados(idListado);

                /* Comprobamos que hemos seleccionado algún Rol */
                if ((listadoId != null) && (!listadoId.isEmpty())) {
                    permisos = ((IPermisoSearchDelegate) getDelegate())
                            .getPermisoByListadoId(listadoId);
                }
            }
        }

        return permisos;

    }

    /**
     * Asigna el permisos al rol.
     * 
     * @return pagina de asignar permisos.
     */
    public String asignar() {
        try {
            final Map<String, Object> filtros = obtenerFiltros(false);
            final Long idRol = (Long) filtros.get("idRol");
            ((IPermisoSearchDelegate) getDelegate()).asignar(idRol,
                    obtenerPermisosSeleccionados(getIdListado()));
            limpiarSeleccionados();
        } catch (final LibraeException e) {
            log.info("No se puede asignar permiso a rol...", e);
        } catch (final Exception e) {
            log.error("No se puede asignar permiso a rol...", e);
        }
        return "";
    }

    /**
     * Desasigna un permiso a un rol.
     * 
     * @return pagina de asignar permisos.
     */
    public String desasignar() {
        try {
            final Map<String, Object> filtros = obtenerFiltros(false);
            final Long idRol = (Long) filtros.get("idRol");
            ((IPermisoSearchDelegate) getDelegate()).desasignar(idRol,
                    obtenerPermisosSeleccionados(getIdListado()));
            limpiarSeleccionados();
        } catch (final LibraeException e) {
            log.info("No se desasignar permiso a rol...", e);
        } catch (final Exception e) {
            log.error("No se desasignar permiso a rol...", e);
        }
        return "";
    }

    /**
     * Limpia el formulario de roles.
     * 
     * @return pagina de asignar permisos.
     */
    public String limpiarSeleccionados() {
        getSessionManager().removeAttribute(
                BasePage.PREFIJO + "permisosNoAsignados");
        getSessionManager().removeAttribute(
                BasePage.PREFIJOALL + "permisosNoAsignados");
        getSessionManager().removeAttribute(
                BasePage.PREFIJO + "permisosAsignados");
        getSessionManager().removeAttribute(
                BasePage.PREFIJOALL + "permisosAsignados");
        return "";
    }

    public boolean repitiendoBusqueda(final Map<String, Object> filtros) {
        return false;
    }

    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtros = obtenerFiltros(false);
        final Map<String, Object> filtrosPdf = new LinkedHashMap<String, Object>();

        if (filtros != null) {
            if (filtros.get("idRol") != null) {
                final Rol rol = ((IPermisoSearchDelegate) getDelegate())
                        .getRol(new Long(filtros.get("idRol").toString()));
                filtrosPdf.put(ComboLocaleManager.get("permiso.idRol"), rol
                        .getCodigo());
                if (!StringUtil.esVacia(rol.getNombre())) {
                    filtrosPdf.put(ComboLocaleManager.get("rol.descripcion"),
                            rol.getNombre());
                }
            }
            if (!StringUtil.esVacia(filtros.get(Permiso.PROPTY_NAME_CODIGO))) {
                filtrosPdf.put(ComboLocaleManager.get("permiso.codigo"),
                        filtros.get(Permiso.PROPTY_NAME_CODIGO));
            }
            if (!StringUtil.esVacia(filtros.get(Permiso.PROPTY_NAME_NOMBRE))) {
                filtrosPdf.put(ComboLocaleManager.get("permiso.nombre"),
                        filtros.get(Permiso.PROPTY_NAME_NOMBRE));
            }
            if (!filtros.get(Permiso.PROPTY_NAME_CATEGORIA).equals(new Long(0))) {
                final TipoPermiso tipoPermiso = ((IPermisoSearchDelegate) getDelegate())
                        .getTipoPermiso((Long) filtros
                                .get(Permiso.PROPTY_NAME_CATEGORIA));
                filtrosPdf.put(ComboLocaleManager.get("permiso.categoria"),
                        tipoPermiso.getNombre());
            }
        }
        /*
         * if (!StringUtil.esVacia(filtros.get("idTransformJob"))) { final
         * TransformJob transformJob = ((IEjecutarTransformacionesJobDelegate)
         * getDelegate()) .getTransformJob(new
         * Long(filtros.get("idTransformJob") .toString()));
         * filtrosPdf.put(ComboLocaleManager.get("log.nombre"),
         * transformJob.getDescripcion()); }
         */

        return filtrosPdf;
    }

    public String limpiar() {
        try {
            final PermisoCriteriaForm form = (PermisoCriteriaForm) getForm();
            form.setCategoriaPermiso(new Long(0));
            form.setCodigo("");
            form.setNombre("");
            if (getSessionManager() != null) {
                getSessionManager().removeAttribute(getIdListado());
            }
            setCurrentPage(1);
            setTotalBusqueda(0);
        } catch (final Exception e) {
            log.error("Error al limpiar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage("ERROR_LIMPIAR");
            setWindowMessages(wm);
        }
        return Constants.PAGE_REFRESH;
    }

    // Getters && Setter

}
