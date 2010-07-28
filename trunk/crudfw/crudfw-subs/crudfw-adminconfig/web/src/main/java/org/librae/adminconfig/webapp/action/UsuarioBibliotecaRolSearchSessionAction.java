package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.TipoIdentificacion;

import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.webapp.delegate.IUsuarioBibliotecaRolSearchDelegate;

import org.librae.adminconfig.webapp.form.UsuarioForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;

import org.librae.common.util.StringUtil;
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
public class UsuarioBibliotecaRolSearchSessionAction extends
        SessionSearchAction implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1256882991554232240L;

    private final Long        nodoClicked      = null;

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
    public UsuarioBibliotecaRolSearchSessionAction() {
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
    private List<Integer> obtenerRolesSeleccionados(final String idListado,
            final List<UsuarioBibliotecaRol> rolesAsignados) {
        final List<Integer> idListaRoles = new ArrayList<Integer>();
        Integer i = 0;

        if (getSessionManager() != null) {
            if (super.todosMarcados(idListado)) {
                while (rolesAsignados.size() > i) {
                    idListaRoles.add(i);
                    i++;
                }
            } else {
                final List<String> idListaRolesString = super
                        .obtenerMarcados(idListado);
                for (final String idRol : idListaRolesString) {
                    idListaRoles.add(new Integer(idRol));
                }
                /* Comprobamos que hemos seleccionado algún Rol */
                if ((idListaRoles == null) || (idListaRoles.isEmpty())) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_ADMINCONFIG,
                            "ERROR_NINGUNA_SELECCION");
                }
            }
        }

        return idListaRoles;

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
        getSessionManager().removeAttribute(BasePage.PREFIJO + getIdListado());
        getSessionManager().removeAttribute(
                BasePage.PREFIJOALL + getIdListado());
        return "";
    }

    /**
     * Método invocado para asignar los roles al usuario en la biblioteca
     * apropiada.
     * 
     * @return String con el identificador de navigation rule
     */
    public String desasignar() {
        try {
            final List<UsuarioBibliotecaRol> rolesAsignados = (List<UsuarioBibliotecaRol>) getSessionManager()
                    .getAttribute(getIdListado());

            final List<Integer> idRoles = obtenerRolesSeleccionados(
                    getIdListado(), rolesAsignados);

            Collections.sort(idRoles, Collections.reverseOrder());

            for (final Integer idRol : idRoles) {
                rolesAsignados.remove(idRol.intValue());
            }

            getSessionManager().setAttribute(getIdListado(), rolesAsignados);
            getSessionManager().setAttribute("rolesNoAsignados", null);
            getSessionManager().removeAttribute(BasePage.PREFIJO + "roles");
            getSessionManager().removeAttribute(BasePage.PREFIJOALL + "roles");
            limpiarSeleccionados();
        } catch (final Exception e) {
            log.info(e);
        }
        return "";
    }

    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtrosPdf = new LinkedHashMap<String, Object>();
        final UsuarioForm form = (UsuarioForm) getSessionManager()
                .getAttribute(Constants.USUARIO_SESSION_NAME);

        if (form != null) {
            if (form.getTipoIdentificacion() != null) {
                final TipoIdentificacion tipoIdentificacion = ((IUsuarioBibliotecaRolSearchDelegate) getDelegate())
                        .getTipoIdentificacion(form.getTipoIdentificacion());
                filtrosPdf.put(ComboLocaleManager
                        .get("usuario.tipoIdentificacion"), tipoIdentificacion
                        .getNombre());
            }
            if (!StringUtil.esVacia(form.getNumeroIdentificacion())) {
                filtrosPdf.put(ComboLocaleManager
                        .get("usuario.numeroIdentificacion"), form
                        .getNumeroIdentificacion());
            }
            if (!StringUtil.esVacia(form.getUsuario())) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.usuario"), form
                        .getUsuario());
            }
            if (!StringUtil.esVacia(form.getNombre())) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.nombre"), form
                        .getNombre());
            }
            if (!StringUtil.esVacia(form.getApellido1())) {
                String apellidos = form.getApellido2();
                if (!StringUtil.esVacia(form.getApellido2())) {
                    apellidos = apellidos + " " + form.getApellido2();
                }
                filtrosPdf.put(ComboLocaleManager.get("usuario.apellidos"),
                        apellidos);
            }

        }

        return filtrosPdf;
    }

    // Getters & setters

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
