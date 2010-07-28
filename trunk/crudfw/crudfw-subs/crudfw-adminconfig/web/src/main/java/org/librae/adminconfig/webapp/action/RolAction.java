package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Rol;

import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.webapp.delegate.IRolSearchDelegate;

import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a las operciones con la entidad Rol
 * 
 * @author jcisneros
 */
public class RolAction extends BasePage implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long  serialVersionUID = -8930564567651768148L;

    /** Delegate del rol */
    private IRolSearchDelegate delegate;
    private List<Rol>          roles            = null;
    private String             idListado        = null;

    /**
     * Constructor.
     */
    public RolAction() {
        setSortColumn("id"); // sets the default sort column
    }

    /**
     * Devuelve el listado de todos los roles ordenado.
     * 
     * @return listado de roles
     */
    public List<Rol> getRoles() {

        if (roles == null) {
            roles = sort(delegate.getAll());
        }

        return roles;
    }

    /**
     * Devuelve el tamaño del listado de roles.
     * 
     * @return Integer, tamaño del listado
     */
    public Integer getRolesSize() {
        int result = 0;

        if (roles != null) {
            result = roles.size();
        }

        return result;
    }

    /**
     * Método invocado para duplicar los roles seleccionados de base de datos 1.
     * Obtener los identificadores seleccionados 2. Invocar al método delegate
     * 
     * @return String con el identificador de navigation rule (duplicar)
     */
    public String duplicar() {
        try {
            final List<Rol> listaRoles = obtenerRolesSeleccionados(idListado);
            delegate.duplicar(listaRoles);
        } catch (final LibraeException e) {
            log.info("No se puede duplicar el rol: ", e);
            final WindowMessages wm = new WindowMessages(e);
            setWindowMessages(wm);
            return "";
        } catch (final Exception e) {
            log.error("No se puede duplicar el rol: ", e);
            final WindowMessages wm = new WindowMessages(ExceptionUtil
                    .crearLibraeException("ERROR_DUPLICAR_ROL",
                            MensajesError.PROPERTI_ADMINCONFIG, e));
            setWindowMessages(wm);
            return "";
        }

        getSessionManager().setAttribute(BasePage.PREFIJO + idListado, null);
        getSessionManager().setAttribute(BasePage.PREFIJOALL + idListado, null);

        return "duplicar";
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

                listaRoles = delegate.buscar(filtros);
            } else {
                final List<String> listadoId = super.obtenerMarcados(idListado);

                /* Comprobamos que hemos seleccionado algún Rol */
                if ((listadoId == null) || (listadoId.isEmpty())) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_ADMINCONFIG,
                            "ERROR_NINGUNA_SELECCION");
                }

                listaRoles = delegate.obtenerRolesById(listadoId);
            }
        }

        return listaRoles;

    }

    /**
     * Método invocado para asignar los roles al usuario en la biblioteca
     * apropiada.
     * 
     * @return String con el identificador de navigation rule
     */
    public String asignarUsuario() {
        try {
            final List<Rol> listaRoles = obtenerRolesSeleccionados("rolesNoAsignados");
            List<UsuarioBibliotecaRol> rolesAsignados = (List<UsuarioBibliotecaRol>) getSessionManager()
                    .getAttribute("rolesAsignados");
            if (rolesAsignados == null) {
                rolesAsignados = new ArrayList<UsuarioBibliotecaRol>();
            }
            final Long idBiblioteca = (Long) getSessionManager().getAttribute(
                    "idBiblioteca_asignarRoles");
            if (idBiblioteca != null) {
                final Biblioteca biblioteca = getDelegate().getBiblioteca(
                        idBiblioteca);
                for (final Rol rol : listaRoles) {
                    final UsuarioBibliotecaRol usuarioBibliotecaRol = new UsuarioBibliotecaRol(
                            null, rol, biblioteca);
                    rolesAsignados.add(usuarioBibliotecaRol);
                }
                getSessionManager().setAttribute("rolesAsignados",
                        rolesAsignados);
            }
            getSessionManager().setAttribute("rolesNoAsignados", null);
            getSessionManager().setAttribute(
                    BasePage.PREFIJO + "rolesNoAsignados", null);
            getSessionManager().setAttribute(
                    BasePage.PREFIJOALL + "rolesNoAsignados", null);

        } catch (final Exception e) {

        }
        return "";
    }

    // =========== getters & setters ==================

    public void setRoles(final List<Rol> roles) {
        this.roles = roles;
    }

    public IRolSearchDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(final IRolSearchDelegate delegate) {
        this.delegate = delegate;
    }

    public String getIdListado() {
        return idListado;
    }

    public void setIdListado(final String idListado) {
        this.idListado = idListado;
    }
}