package org.librae.adminconfig.webapp.action;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.FavoritoUsuario;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.webapp.delegate.IFavoritoUsuarioSearchDelegate;
import org.librae.adminconfig.webapp.form.FavoritoUsuarioCriteriaForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.action.BaseSearchPage;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los listados de la entidad Usuario.
 * 
 * @author jcisneros
 */
public class FavoritoUsuarioSearchAction extends BaseSearchPage implements
        Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 5074688574872136299L;

    private Long              idSubirOrden     = null;

    private Long              idBajarOrden     = null;

    /**
     *
     */
    protected void completarForm() {
        final Long idUsuario = (Long) getSessionManager().getAttribute(
                Constants.ID_USUARIO_LOGADO);
        setForm(new FavoritoUsuarioCriteriaForm());
        ((FavoritoUsuarioCriteriaForm) getForm()).setIdUsuario(idUsuario);
        setSortColumn("orden");
        setAscending(true);
    }

    /**
     * Constructor.
     */
    public FavoritoUsuarioSearchAction() {
        super();
        SessionManager sesion = getSessionManager();
        if (sesion != null) {
            completarForm();
            sesion.setAttribute("favoritos", getForm().toMap());
        }

    }

    /**
     * Sube el orden del favorito seleccionado.
     */
    public void subirOrden() {
        try {
            final Usuario usuario = (Usuario) getSessionManager().getAttribute(
                    Constants.USUARIO_SESSION_PARAM);
            ((IFavoritoUsuarioSearchDelegate) getDelegate()).subirOrden(
                    getIdSubirOrden(), usuario);
        } catch (final Exception e) {
            log.error("e...", e);
        }
    }

    /**
     * Baja el orden del favorito seleccionado.
     */
    public void bajarOrden() {
        try {
            final Usuario usuario = (Usuario) getSessionManager().getAttribute(
                    Constants.USUARIO_SESSION_PARAM);
            ((IFavoritoUsuarioSearchDelegate) getDelegate()).bajarOrden(
                    getIdBajarOrden(), usuario);
        } catch (final Exception e) {
            log.error("e...", e);
        }
    }

    /**
     * Método invocado para eliminar los favoritos seleccionados de base de
     * datos 1. Obtener los identificadores seleccionados 2. Invocar al método
     * delegate
     */
    public String delete() {
        try {
            final List<FavoritoUsuario> favoritos = obtenerFavoritosSeleccionados(getIdListado());
            ((IFavoritoUsuarioSearchDelegate) getDelegate())
                    .eliminar(favoritos);
            limpiarMarcados(getIdListado());
            removeSessionManagerParam(Constants.MENU_FAVORITO_LIBRAE);
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
    private List<FavoritoUsuario> obtenerFavoritosSeleccionados(
            final String idListado) {
        List<FavoritoUsuario> listaFavoritos = null;

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

                listaFavoritos = ((IFavoritoUsuarioSearchDelegate) getDelegate())
                        .buscar(filtros);
            } else {
                final List<String> listadoId = super.obtenerMarcados(idListado);
                if ((listadoId == null) || (listadoId.isEmpty())) {
                    throw ExceptionUtil.crearLibraeException(
                            MensajesError.PROPERTI_ADMINCONFIG,
                            "ERROR_NINGUNA_SELECCION");
                }
                listaFavoritos = ((IFavoritoUsuarioSearchDelegate) getDelegate())
                        .getFavoritoUsuario(listadoId);
            }
        }
        return listaFavoritos;

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
        if (filtros == null) {
            completarForm();
            filtros = getForm().toMap();
            getSessionManager().setAttribute("favoritos", filtros);
        }
        if (filtros != null && addParamSort) {
            filtros.put(Constants.SORTCOLUMN, getSortColumn());
            filtros.put(Constants.ASCENDING, isAscending());
            filtros.put(Constants.PAGESIZE, getPageSize());
            filtros.put(Constants.CURRENTPAGE, getCurrentPage());
        }

        return filtros;
    }

    // Getters && Setter

    /**
     * @return the idSubirOrden
     */
    public Long getIdSubirOrden() {
        return idSubirOrden;
    }

    /**
     * @param idSubirOrden
     *            the idSubirOrden to set
     */
    public void setIdSubirOrden(Long idSubirOrden) {
        this.idSubirOrden = idSubirOrden;
    }

    /**
     * @return the idBajarOrden
     */
    public Long getIdBajarOrden() {
        return idBajarOrden;
    }

    /**
     * @param idBajarOrden
     *            the idBajarOrden to set
     */
    public void setIdBajarOrden(Long idBajarOrden) {
        this.idBajarOrden = idBajarOrden;
    }

}
