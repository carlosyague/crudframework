package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.webapp.delegate.IGestionRolesDelegate;

import org.librae.common.Constants;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.action.BaseSearchPage;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los listados de la entidad Rol.
 * 
 * @author jcisneros
 */
public class RolListAction extends BaseSearchPage implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1256882991554232240L;

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

    private String            buscarPor        = null;

    /**
     * Constructor.
     */
    public RolListAction() {
        super();
    }

    public String getInit() {
        removeSessionManagerParam(Constants.ROL_SESSION_NAME);
        removeSessionManagerParam("rolesAsignados");
        return "";
    }

    /**
     * Obtiene el listado
     * 
     * @return listado que se desea buscar.
     */
    public List getListado() {
        final WindowMessages wm = new WindowMessages();
        final Map<String, Object> filtros = obtenerFiltros(true);
        final Long idBiblioteca = (Long) getSessionManagerParam("idBiblioteca_asignarRoles");
        try {
            if (filtros != null) {
                if ((("noAsignados".equals(getBuscarPor())))
                        && (idBiblioteca == null)) {
                    listado = new ArrayList();
                    setTotalBusqueda(listado.size());
                } else {
                    if ("noAsignados".equals(getBuscarPor())) {
                        filtros.put("asignados",
                                calcularAsignados(idBiblioteca));
                        filtros.put("usuario", getUsuario());
                    }
                    listado = getDelegate().buscar(filtros);
                    setTotalBusqueda((Integer) filtros
                            .get(Constants.RESULTADO_BUSQUEDA));
                    if ((getTotalBusqueda() == 0)
                            && (getMostrarAlertSinResultado())) {
                        wm.addInfoMessageByCode("ERROR_NINGUN_RESULTADO");
                        setWindowMessages(wm);
                    }
                }
            }
        } catch (final Exception e) {
            log.error("Error al buscar...", e);
            wm.addErrorMessageByCode("ERROR_BUSCAR");
            setWindowMessages(wm);
        }
        marcarSeleccionados(listado);
        return listado;
    }

    /**
     * Limpia el formulario de la sesion y el listado.
     */
    public String limpiar() {
        try {
            super.limpiar();
            if (getSessionManager() != null) {
                removeSessionManagerParam(BasePage.PREFIJO + getIdListado());
                removeSessionManagerParam(BasePage.PREFIJOALL + getIdListado());
            }
        } catch (final Exception e) {
            log.error("Error al limpiar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage("ERROR_LIMPIAR");
            setWindowMessages(wm);
        }
        return Constants.PAGE_REFRESH;
    }

    private List<Rol> calcularAsignados(Long idBiblioteca) {
        final List<Rol> asignados = new ArrayList();
        final List<UsuarioBibliotecaRol> rolesAsignados = (List<UsuarioBibliotecaRol>) getSessionManagerParam("rolesAsignados");
        if ((idBiblioteca != null) && (rolesAsignados != null)) {
            for (final UsuarioBibliotecaRol usuarioBibliotecaRol : rolesAsignados) {
                if (idBiblioteca.equals(usuarioBibliotecaRol.getBiblioteca()
                        .getId())) {
                    asignados.add(usuarioBibliotecaRol.getRol());
                }
            }
        }
        return asignados;
    }

    public Usuario getUsuario() {
        return (Usuario) getSessionManagerParam(Constants.USUARIO_SESSION_PARAM);
    }

    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtros = obtenerFiltros(false);
        final Map<String, Object> filtrosPdf = new LinkedHashMap<String, Object>();
        final Long idBiblioteca = (Long) getSessionManagerParam("idBiblioteca_asignarRoles");

        if (idBiblioteca != null) {
            final Biblioteca biblioteca = ((IGestionRolesDelegate) getDelegate())
                    .getBiblioteca(idBiblioteca);
            filtrosPdf.put(ComboLocaleManager.get("usuario.biblioteca"),
                    biblioteca.getDescripcion());
        }

        if (filtros != null) {

            if (!StringUtil.esVacia(filtros.get("codigo"))) {
                filtrosPdf.put(ComboLocaleManager.get("rol.codigo"), filtros
                        .get("codigo"));
            }
            if (!StringUtil.esVacia(filtros.get("nombre"))) {
                filtrosPdf.put(ComboLocaleManager.get("rol.descripcion"),
                        filtros.get("nombre"));
            }
        }
        return filtrosPdf;
    }

    // Getters & setters ==================

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

    /**
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor
     *            the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

}
