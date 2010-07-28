package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate;
import org.librae.adminconfig.webapp.form.BibliotecaCriteriaForm;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.action.BaseSearchPage;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los listados de la entidad Biblioteca
 * 
 * @author aropero
 */
public class BibliotecaListAction extends BaseSearchPage implements
        Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID       = -6290606354655722443L;

    protected final Log       log                    = LogFactory
                                                             .getLog(BibliotecaListAction.class);

    private Boolean           selectedAll            = false;

    private Long              nodoClicked            = null;
    private String            nodoDescripcionClicked = null;
    private Boolean           arbolActivo            = false;

    /**
     * pestañas
     */
    private final TabGroup    tabGroup               = new TabGroup(
                                                             MensajesError.PROPERTI_ADMINCONFIG);
    private final Tab         tabListado             = new Tab(tabGroup,
                                                             "bibliotecaList.tab01Text");

    /**
     * subpestañas
     */
    private final SubTabGroup subTabGroup            = new SubTabGroup(
                                                             MensajesError.PROPERTI_ADMINCONFIG);
    private final SubTab      subTabResults          = new SubTab(subTabGroup,
                                                             "biblioteca.resultados");

    public BibliotecaListAction() {
        super();
    }

    /**
     * Guarda en session el nodo seleccionado.
     */
    public void nodoClick() {
        setSessionManagerParam("idBiblioteca_listadoBiblioteca",
                getNodoClicked());
    }

    public String getInit() {
        final SessionManager sesion = getSessionManager();
        if (sesion != null) {
            final Long idBiblioteca = (Long) sesion
                    .getAttribute("idBiblioteca_listadoBiblioteca");
            final BibliotecaCriteriaForm form = (BibliotecaCriteriaForm) getForm();
            final String busqueda = form.getBusqueda();

            form.setIdBiblioteca(idBiblioteca);
            if ((idBiblioteca == null)) {
                final Long idBibliotecaActual = (Long) sesion
                        .getAttribute(Constants.ID_BIBLIOTECA_ACTUAL);
                if (form.getBusqueda().equalsIgnoreCase("arbol")) {
                    form.setBusqueda("arbol");
                }
                form.setIdBiblioteca(idBibliotecaActual);
                setNodoClicked(form.getIdBiblioteca());
                sesion.setAttribute("idBiblioteca_listadoBiblioteca", form
                        .getIdBiblioteca());
            }

            if (!(busqueda.equalsIgnoreCase("codigo"))
                    && !(busqueda.equalsIgnoreCase("descripcion"))) {
                form.setBusqueda("arbol");
                if (idBiblioteca == null) {
                    final Long idBibliotecaActual = (Long) sesion
                            .getAttribute(Constants.ID_BIBLIOTECA_ACTUAL);
                    form.setIdBiblioteca(idBibliotecaActual);
                    setNodoClicked(form.getIdBiblioteca());
                    sesion.setAttribute("idBiblioteca_listadoBiblioteca", form
                            .getIdBiblioteca());
                }
            }

            sesion.removeAttribute(Constants.CALENDARIO_SESSION_NAME);
            sesion.removeAttribute(Constants.HORARIO_SESSION_NAME);
            sesion.removeAttribute(Constants.DIRECCION_SESSION_NAME);
            sesion.removeAttribute(Constants.BIBLIOTECA_SESSION_NAME);
            sesion.removeAttribute(Constants.HORARIOINT_SESSION_NAME);
            sesion.removeAttribute("nodo_seleccionado");
            sesion.removeAttribute("isPrincipal");
            sesion.removeAttribute("intervalos");
            sesion.removeAttribute("horariosInt");
        }
        return "";
    }

    /**
     * Limpia el formulario de la sesion y el listado.
     */
    public String limpiar() {
        try {
            super.limpiar();
            if (getSessionManager() != null) {
                removeSessionManagerParam("idBiblioteca_listadoBiblioteca");
            }
        } catch (final Exception e) {
            log.error("Error al limpiar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage("ERROR_LIMPIAR");
            setWindowMessages(wm);
        }
        return Constants.PAGE_REFRESH;
    }

    public Long getNodoClicked() {
        final Object idNodo = getSessionManagerParam("idBiblioteca_listadoBiblioteca");
        if ((nodoClicked == null) && (idNodo != null)) {
            return (Long) idNodo;
        } else {
            return nodoClicked;
        }
    }

    public String getNodoDescripcionClicked() {
        final Long idNodo = getNodoClicked();
        if (idNodo != null) {
            final Biblioteca biblio = ((IBibliotecaDelegate) getDelegate())
                    .obtenerBiblioteca(idNodo);
            nodoDescripcionClicked = biblio.getDescripcion();
        }
        return nodoDescripcionClicked;
    }

    /**
     * Completa el formulario.
     */
    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtros = obtenerFiltros(false);
        final Map<String, Object> filtrosPdf = new HashMap<String, Object>();
        if (filtros != null) {
            if (!StringUtil.esVacia(filtros.get("personaContacto"))) {
                filtrosPdf.put(ComboLocaleManager
                        .get("biblioteca.personaContacto"), filtros
                        .get("personaContacto"));
            }
            if (!(new Long(0)).equals(filtros.get("tipoBiblioteca"))) {
                final TipoBiblioteca tipoBiblioteca = ((IBibliotecaDelegate) getDelegate())
                        .getTipoBiblioteca(new Long(filtros.get(
                                "tipoBiblioteca").toString()));
                filtrosPdf.put(ComboLocaleManager
                        .get("biblioteca.tipoBiblioteca.nombre"),
                        tipoBiblioteca.getNombre());
            }
            if (!StringUtil.esVacia(filtros.get("codigoBiblioteca"))) {
                filtrosPdf.put(ComboLocaleManager.get("biblioteca.codigo"),
                        filtros.get("codigoBiblioteca"));
            }
            if (!StringUtil.esVacia(filtros.get("descripcion"))) {
                filtrosPdf.put(
                        ComboLocaleManager.get("biblioteca.descripcion"),
                        filtros.get("descripcion"));
            }
            if (filtros.get("idBiblioteca") != null) {
                final Biblioteca biblioteca = ((IBibliotecaDelegate) getDelegate())
                        .obtenerBiblioteca(new Long(filtros.get("idBiblioteca")
                                .toString()));
                filtrosPdf.put(ComboLocaleManager.get("usuario.biblioteca"),
                        biblioteca.getDescripcion());
            }
        }
        return filtrosPdf;
    }

    /**
     * Completa el formulario.
     */
    protected void completarForm() {
        final Long idBibliotecaActual = null;
        Long idBiblioteca = (Long) getSessionManagerParam("idBiblioteca_listadoBiblioteca");
        final BibliotecaCriteriaForm form = (BibliotecaCriteriaForm) getForm();
        if (form.getBusqueda().equalsIgnoreCase("arbol")) {
            if (!arbolActivo) {
                idBiblioteca = ((IBibliotecaDelegate) getDelegate())
                        .getIdBibliotecaByDescripcion(nodoDescripcionClicked
                                .trim());
                if (idBiblioteca == null) {
                    idBiblioteca = ((IBibliotecaDelegate) getDelegate())
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
            removeSessionManagerParam("idBiblioteca_listadoBiblioteca");
        }
        if (form.getBusqueda().equalsIgnoreCase("descripcion")) {
            form.setIdBiblioteca(null);
            removeSessionManagerParam("idBiblioteca_listadoBiblioteca");
        }
        arbolActivo = false;
    }

    // ====== getters & setters

    /**
     * @return the tabGroup
     */
    public TabGroup getTabGroup() {
        return tabGroup;
    }

    /**
     * @return the tabListado
     */
    public Tab getTabListado() {
        return tabListado;
    }

    /**
     * @return the subTabResults
     */
    public SubTab getSubTabResults() {
        return subTabResults;
    }

    /**
     * @return the subTabGroup
     */
    public SubTabGroup getSubTabGroup() {
        return subTabGroup;
    }

    public Boolean getSelectedAll() {
        return selectedAll;
    }

    public void setSelectedAll(final Boolean selectedAll) {
        this.selectedAll = selectedAll;
    }

    public void setNodoClicked(Long nodoClicked) {
        this.nodoClicked = nodoClicked;
        setSessionManagerParam("idBiblioteca_listadoBiblioteca", nodoClicked);
    }

    public void setNodoDescripcionClicked(String nodoDescripcionClicked) {
        this.nodoDescripcionClicked = nodoDescripcionClicked;
    }

    public Boolean getArbolActivo() {
        return arbolActivo;
    }

    public void setArbolActivo(Boolean arbolActivo) {
        this.arbolActivo = arbolActivo;
    }
}
