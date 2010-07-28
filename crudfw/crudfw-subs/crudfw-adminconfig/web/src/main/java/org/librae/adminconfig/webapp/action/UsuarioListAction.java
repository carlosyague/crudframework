package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.myfaces.custom.tree2.TreeNode;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate;
import org.librae.adminconfig.webapp.delegate.IUsuarioDelegate;
import org.librae.adminconfig.webapp.form.UsuarioCriteriaForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.DateUtil;
import org.librae.common.util.Propiedades;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.action.BaseSearchPage;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los listados de la entidad Usuario.
 * 
 * @author jcisneros
 */
public class UsuarioListAction extends BaseSearchPage implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID       = 4886737877428644706L;

    private Long              nodoClicked            = null;
    private String            nodoDescripcionClicked = null;
    private Boolean           arbolActivo            = false;

    TreeNode                  treeData               = null;

    private Boolean           orbeActivo             = Boolean.FALSE;

    /**
     * Guarda en session el nodo seleccionado.
     */
    public void nodoClick() {
        setSessionManagerParam("idBiblioteca_listadoUsuario", getNodoClicked());
    }

    /**
     * Completa el formulario
     */
    protected void completarForm() {
        final Long idBibliotecaActual = null;
        Long idBiblioteca = (Long) getSessionManagerParam("idBiblioteca_listadoUsuario");
        final UsuarioCriteriaForm form = (UsuarioCriteriaForm) getForm();
        if (form.getBusqueda().equalsIgnoreCase("arbol")) {
            if (!arbolActivo) {
                idBiblioteca = ((IUsuarioDelegate) getDelegate())
                        .getIdBibliotecaByDescripcion(nodoDescripcionClicked
                                .trim());
                if (idBiblioteca == null) {
                    idBiblioteca = ((IUsuarioDelegate) getDelegate())
                            .getIdBibliotecaByCodigo(nodoDescripcionClicked
                                    .trim());
                }
                if (idBiblioteca == null) {
                    throw ExceptionUtil
                            .crearLibraeException("ERROR_NO_ENCONTRADA");
                }
            }
            form.setIdBiblioteca(idBiblioteca);
            setSessionManagerParam("idBiblioteca_listadoBiblioteca",
                    idBiblioteca);
            nodoClicked = idBiblioteca;
            if (form.getIdBiblioteca() == null) {
                form.setIdBiblioteca(idBibliotecaActual);
            } else {
                setSessionManagerParam("idBiblioteca_listadoBiblioteca",
                        idBiblioteca);
            }
        }
        if (form.getBusqueda().equalsIgnoreCase("codigo")) {
            form.setIdBiblioteca(null);
            removeSessionManagerParam("idBiblioteca_listadoUsuario");
        }
        if (form.getBusqueda().equalsIgnoreCase("descripcion")) {
            form.setIdBiblioteca(null);
            removeSessionManagerParam("idBiblioteca_listadoUsuario");
        }
        final Long idUsuario = (Long) getSessionManagerParam(Constants.ID_BIBLIOTECA_ACTUAL);
        form.setIdBibliotecaActual(idUsuario);
    }

    /**
     * Constructor.
     */
    public UsuarioListAction() {
        super();
    }

    public Long getNodoClicked() {
        final Object idNodo = getSessionManagerParam("idBiblioteca_listadoUsuario");
        if ((nodoClicked == null) && (idNodo != null)) {
            return (Long) idNodo;
        } else {
            return nodoClicked;
        }
    }

    /**
     * Limpia el formulario de la sesion y el listado.
     */
    public String limpiar() {
        try {
            super.limpiar();
            if (getSessionManager() != null) {
                removeSessionManagerParam("idBiblioteca_listadoUsuario");
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

    public String getInit() {
        final SessionManager sesion = getSessionManager();
        if (sesion != null) {
            final Long idBiblioteca = (Long) sesion
                    .getAttribute("idBiblioteca_listadoUsuario");
            final UsuarioCriteriaForm form = (UsuarioCriteriaForm) getForm();
            if ((idBiblioteca == null)
                    && (StringUtil.esVacia(form.getCodigoBiblioteca()))) {
                final Long idBibliotecaActual = (Long) sesion
                        .getAttribute(Constants.ID_BIBLIOTECA_ACTUAL);

                if (form.getBusqueda().equalsIgnoreCase("arbol")) {
                    form.setBusqueda("arbol");
                }
                form.setIdBiblioteca(idBibliotecaActual);
                setNodoClicked(form.getIdBiblioteca());
                sesion.setAttribute("idBiblioteca_listadoUsuario", form
                        .getIdBiblioteca());
                form.setIdBibliotecaActual(idBibliotecaActual);
            }

            sesion.removeAttribute("rolesAsignados");
            sesion.removeAttribute(Constants.USUARIO_SESSION_NAME);
            final Propiedades properties = Propiedades.getInstance();

            final String orbeActivoStr = properties
                    .getPropiedad(Constants.ORBE_ACTIVO);

            if (orbeActivoStr != null && orbeActivoStr.equals("true")) {
                orbeActivo = Boolean.TRUE;
            }
        }

        limpiarFormulario();

        return "";
    }

    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtros = obtenerFiltros(false);
        final Map<String, Object> filtrosPdf = new LinkedHashMap<String, Object>();
        if (filtros != null) {
            if (!StringUtil.esVacia(filtros.get("apellido1"))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.apellido1"),
                        filtros.get("apellido1"));
            }
            if (!StringUtil.esVacia(filtros.get("apellido2"))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.apellido2"),
                        filtros.get("apellido2"));
            }
            if (!StringUtil.esVacia(filtros.get("nombre"))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.nombre"),
                        filtros.get("nombre"));
            }
            if (filtros.get("fechaAltaDesde") != null) {
                final String fecha = DateUtil
                        .convertDateToString((Date) filtros
                                .get("fechaAltaDesde"));
                filtrosPdf
                        .put(ComboLocaleManager.get("usuario.fechaAltaDesde"),
                                fecha);
            }
            if (filtros.get("fechaAltaHasta") != null) {
                final String fecha = DateUtil
                        .convertDateToString((Date) filtros
                                .get("fechaAltaHasta"));
                filtrosPdf
                        .put(ComboLocaleManager.get("usuario.fechaAltaHasta"),
                                fecha);
            }
            if (filtros.get("fechaBajaDesde") != null) {
                final String fecha = DateUtil
                        .convertDateToString((Date) filtros
                                .get("fechaBajaDesde"));
                filtrosPdf
                        .put(ComboLocaleManager.get("usuario.fechaBajaDesde"),
                                fecha);
            }
            if (filtros.get("fechaBajaHasta") != null) {
                final String fecha = DateUtil
                        .convertDateToString((Date) filtros
                                .get("fechaBajaHasta"));
                filtrosPdf
                        .put(ComboLocaleManager.get("usuario.fechaBajaHasta"),
                                fecha);
            }
            if (!StringUtil.esVacia(filtros.get("numeroIdentificacion"))) {
                filtrosPdf.put(ComboLocaleManager
                        .get("usuario.numeroIdentificacion"), filtros
                        .get("numeroIdentificacion"));
            }
            if (!StringUtil.esVacia(filtros.get("email"))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.email"), filtros
                        .get("email"));
            }
            if (!StringUtil.esVacia(filtros.get("telefono"))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.telefono"),
                        filtros.get("telefono"));
            }
            if (!StringUtil.esVacia(filtros.get("movil"))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.movil"), filtros
                        .get("movil"));
            }
            if (!StringUtil.esVacia(filtros.get("fax"))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.fax"), filtros
                        .get("fax"));
            }
            if (!StringUtil.esVacia(filtros.get("codigoBiblioteca"))) {
                filtrosPdf.put(ComboLocaleManager
                        .get("usuario.codigoBiblioteca"), filtros
                        .get("codigoBiblioteca"));
            }
            if (!StringUtil.esVacia(filtros.get("descripcionBiblioteca"))) {
                filtrosPdf.put(ComboLocaleManager
                        .get("usuario.descripcionBiblioteca"), filtros
                        .get("descripcionBiblioteca"));
            }
            if (!StringUtil.esVacia(filtros.get("usuario"))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.usuario"),
                        filtros.get("usuario"));
            }
            if (!(new Long(0).equals(filtros
                    .get(Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION)))
                    && filtros.get(Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION) != null) {
                final TipoIdentificacion tipoIdentificacion = ((IUsuarioDelegate) getDelegate())
                        .getTipoIdentificacion(new Long(filtros.get(
                                Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION)
                                .toString()));
                filtrosPdf.put(ComboLocaleManager
                        .get("usuario.tipoIdentificacion"), tipoIdentificacion
                        .getCodigo());
            }
            if ((new Long(2)).equals(filtros.get(Usuario.PROPERTY_NAME_ACTIVO))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.estado"),
                        ComboLocaleManager.get("estadoUsuario.todos"));
            } else if ((new Long(1)).equals(filtros
                    .get(Usuario.PROPERTY_NAME_ACTIVO))) {
                filtrosPdf.put(ComboLocaleManager.get("usuario.estado"),
                        ComboLocaleManager.get("estadoUsuario.activados"));
            } else {
                filtrosPdf.put(ComboLocaleManager.get("usuario.estado"),
                        ComboLocaleManager.get("estadoUsuario.desactivados"));
            }

            if (filtros.get("idBiblioteca") != null) {
                final Biblioteca biblioteca = ((IUsuarioDelegate) getDelegate())
                        .getBiblioteca(new Long(filtros.get("idBiblioteca")
                                .toString()));
                filtrosPdf.put(ComboLocaleManager.get("usuario.biblioteca"),
                        biblioteca.getDescripcion());
            }
            if ((filtros.get("idRoles") != null)
                    && !(((List) (filtros.get("idRoles"))).isEmpty())) {
                final List<String> roles = (List<String>) filtros
                        .get("idRoles");
                final String nombresRoles = ((IUsuarioDelegate) getDelegate())
                        .getNombresFromRoles(roles);
                filtrosPdf.put(ComboLocaleManager.get("usuario.roles"),
                        nombresRoles);
            } else {
                filtrosPdf.put(ComboLocaleManager.get("usuario.roles"),
                        ComboLocaleManager.get("todos"));
            }
        }
        return filtrosPdf;
    }

    private void limpiarFormulario() {
        /* Calendario de la Biblioteca */
        removeSessionManagerParam(Constants.CALENDARIO_SESSION_NAME);

        /* Horario de la Biblioteca */
        removeSessionManagerParam(Constants.HORARIO_SESSION_NAME);

        /* Horario intervalo de la Biblioteca */
        removeSessionManagerParam(Constants.HORARIOINT_SESSION_NAME);

        /* Direccion de la Biblioteca */
        removeSessionManagerParam(Constants.DIRECCION_SESSION_NAME);

        /* Formulario de la Biblioteca */
        removeSessionManagerParam(Constants.BIBLIOTECA_SESSION_NAME);
    }

    /**
     * Devuelve el arbol que debe de tener el usuario.
     * 
     * @return
     */
    public TreeNode getTreeData() {
        Usuario usuario = null;
        if (treeData == null) {
            try {
                usuario = (Usuario) getUserFromSessionManager();
                if (usuario != null) {
                    treeData = ((IUsuarioDelegate) getDelegate())
                            .getTreeData(usuario.getId());
                }
            } catch (final Exception e) {
                log.error("Fallo al hacer el arbol...", e);
            }
        }
        return treeData;
    }

    // Getters && Setter

    public void setNodoClicked(final Long nodoClicked) {
        this.nodoClicked = nodoClicked;
        setSessionManagerParam("idBiblioteca_listadoUsuario", nodoClicked);
    }

    /**
     * @return the orbeActivo
     */
    public Boolean getOrbeActivo() {
        return orbeActivo;
    }

    /**
     * @param orbeActivo
     *            the orbeActivo to set
     */
    public void setOrbeActivo(Boolean orbeActivo) {
        this.orbeActivo = orbeActivo;
    }

    public void setNodoDescripcionClicked(String nodoDescripcionClicked) {
        this.nodoDescripcionClicked = nodoDescripcionClicked;
    }

    public String getNodoDescripcionClicked() {
        final Long idNodo = getNodoClicked();
        if (idNodo != null) {
            final Biblioteca biblio = ((IUsuarioDelegate) getDelegate())
                    .getBiblioteca(idNodo);
            nodoDescripcionClicked = biblio.getDescripcion();
        }
        return nodoDescripcionClicked;
    }

    public Boolean getArbolActivo() {
        return arbolActivo;
    }

    public void setArbolActivo(Boolean arbolActivo) {
        this.arbolActivo = arbolActivo;
    }
}
