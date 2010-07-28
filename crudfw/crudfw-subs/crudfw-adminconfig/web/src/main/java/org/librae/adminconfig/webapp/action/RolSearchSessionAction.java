package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.action.SessionSearchAction;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;

/**
 * Action-JSF asociado a los listados de la entidad Rol.
 * 
 * @author jcisneros
 */
public class RolSearchSessionAction extends SessionSearchAction implements
        Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1256882991554232240L;

    private Long              nodoClicked      = null;

    /**
     * pestanas
     */
    private final TabGroup    tabGroup         = new TabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);

    private final Tab         tabListado       = new Tab(tabGroup,
                                                       "rolList.tab01Text");

    /**
     * subpestanas
     */
    private final SubTabGroup subTabGroup      = new SubTabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);

    private final SubTab      subTabResults    = new SubTab(subTabGroup,
                                                       "rolList.resultados");

    /**
     * Constructor.
     */
    public RolSearchSessionAction() {
        super();
    }

    public String getInit() {
        getSessionManager().removeAttribute(Constants.ROL_SESSION_NAME);
        return "";
    }

    /**
     * Método que comprueba los roles que se han seleccionado para operar con
     * ellos y devuelve un listado de los mismos.
     * 
     * @param idListado
     *            , identificador del listado de roles
     * @return Lista de roles seleccionados
     */
    private List<Rol> obtenerRolesSeleccionados(final String idListado) {
        List<Rol> listaRoles = null;

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

                listaRoles = getDelegate().buscar(filtros);
            } else {
                final List<String> listadoId = super.obtenerMarcados(idListado);

                /* Comprobamos que hemos seleccionado algún Rol */
                if ((listadoId == null) || (listadoId.isEmpty())) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_ADMINCONFIG,
                            "ERROR_NINGUNA_SELECCION");
                }

                listaRoles = ((IGestionRolesDelegate) getDelegate())
                        .obtenerRolesById(listadoId);
            }
        }

        return listaRoles;

    }

    /**
     * Asigna el roles al rol.
     * 
     * @return pagina de asignar roles.
     */
    public String asignar() {
        try {
            final Map<String, Object> filtros = obtenerFiltros(false);
            final Long idRol = (Long) filtros.get("idRol");
            // getDelegate().asignar(idBiblioteca, idRol,
            // obtenerRolesSeleccionados(getIdListado()));
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
     * @return pagina de asignar roles.
     */
    public String desasignar() {
        try {
            final Map<String, Object> filtros = obtenerFiltros(false);
            final Long idRol = (Long) filtros.get("idRol");
            // getDelegate().desasignar(idBiblioteca, idRol,
            // obtenerRolesSeleccionados(getIdListado()));
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
     * @return pagina de asignar roles.
     */
    public String limpiarSeleccionados() {
        getSessionManager().removeAttribute(
                BasePage.PREFIJO + "rolesNoAsignados");
        getSessionManager().removeAttribute(
                BasePage.PREFIJOALL + "rolesNoAsignados");
        getSessionManager()
                .removeAttribute(BasePage.PREFIJO + "rolesAsignados");
        getSessionManager().removeAttribute(
                BasePage.PREFIJOALL + "rolesAsignados");
        return "";
    }

    /**
     * Método encargado de obtener de sesion los criterios de busqueda del
     * usuario y añadirles la ordenacion establecida por el componente dataTable
     * y la página a mostrar según el parametro addParamSort
     * 
     * @param addParamSort
     *            indica si se debe añadir o no a los cirterios los parametros
     *            Constants.SORTCOLUMN, Constants.ASCENDING, Constants.PAGESIZE,
     *            Constants.CURRENTPAGE
     */
    protected Map<String, Object> obtenerFiltros(final boolean addParamSort) {
        Map<String, Object> filtros = null;

        if (getSessionManager() != null) {
            filtros = (Map<String, Object>) getSessionManager().getAttribute(
                    getIdListado());
        }

        if (filtros != null && addParamSort) {
            filtros.put(Constants.SORTCOLUMN, getSortColumn());
            filtros.put(Constants.ASCENDING, isAscending());
        }
        return filtros;
    }

    /**
     * Esta funcion se ejecuta cuando un nodo del arbol es seleccionado.
     */
    public void nodoClick() {
        // final AsignarPerfilesUsuarioForm formulario =
        // (AsignarPerfilesUsuarioForm) getSessionManager()
        // .getAttribute(asignarRolesForm);
        // if (formulario == null) {
        // getSessionManager().setAttribute("idBiblioteca_asignarRoles",
        // getNodoClicked());
        // } else {
        // formulario.setIdBiblioteca(getNodoClicked());
        // formulario
        // .setRolesAsignados(getDelegate().getRolesAsignados(formulario));
        // formulario.setRolesNoAsignados(getDelegate()
        // .getRolesNoAsignados(formulario));
        // form = formulario;
        // getSessionManager().setAttribute(asignarRolesForm, formulario);
        // }
    }

    /**
     * Obtiene el nodo seleccionado del arbol.
     * 
     * @return
     */
    public Long getNodoClicked() {
        // final AsignarPerfilesUsuarioForm formulario =
        // (AsignarPerfilesUsuarioForm) getSessionManager()
        // .getAttribute(asignarRolesForm);
        // if ((nodoClicked == null) && (formulario != null)) {
        // nodoClicked = formulario.getIdBiblioteca();
        // }
        // final Long idBibliotecaSeleccionada = (Long) getSessionManager()
        // .getAttribute("idBiblioteca_asignarRoles");
        // if ((nodoClicked == null) && (idBibliotecaSeleccionada != null)) {
        // nodoClicked = idBibliotecaSeleccionada;
        // }
        return nodoClicked;
    }

    public void setNodoClicked(final Long nodoClicked) {
        this.nodoClicked = nodoClicked;
    }

    // =========== getters & setters ==================
    public TabGroup getTabGroup() {
        return tabGroup;
    }

    public Tab getTabListado() {
        return tabListado;
    }

    public SubTabGroup getSubTabGroup() {
        return subTabGroup;
    }

    public SubTab getSubTabResults() {
        return subTabResults;
    }

}
