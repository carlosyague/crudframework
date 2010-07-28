package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.librae.adminconfig.model.Catalogo;
import org.librae.common.Constants;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.action.BaseSearchPage;
import org.librae.common.webapp.form.ISearchForm;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los listados de la entidad Catalogo.
 * 
 * @author impena
 */
public class CatalogoListAction extends BaseSearchPage implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 1L;

    /**
     * pestañas
     */
    private final TabGroup    tabGroup         = new TabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);
    private final Tab         tabListado       = new Tab(tabGroup,
                                                       "catalogoList.tab01Text");

    /**
     * subpestañas
     */
    private final SubTabGroup subTabGroup      = new SubTabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);
    private final SubTab      subTabResults    = new SubTab(subTabGroup,
                                                       "catalogo.resultados");

    /**
     * Constructor.
     */
    public CatalogoListAction() {
        super();
        setSortColumn("codigo");
    }

    public String getInit() {
        removeSessionManagerParam(Constants.CATALOGO_SESSION_NAME);
        return null;
    }

    /**
     * Limpia el formulario de la sesion y el listado.
     */
    public String limpiar() {
        try {
            setListado(new ArrayList<Catalogo>());
            setForm((ISearchForm) Class.forName(getNameForm()).newInstance());
            if (getSessionManager() != null) {
                removeSessionManagerParam(getIdListado());
                removeSessionManagerParam("idCatalogo_listadoCatalogo");
            }
        } catch (final Exception e) {
            log.error("Error al limpiar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage("ERROR_LIMPIAR");
            setWindowMessages(wm);
        }
        return Constants.PAGE_REFRESH;
    }

    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtros = obtenerFiltros(false);
        final Map<String, Object> filtrosPdf = new HashMap<String, Object>();
        if (filtros != null) {
            if (!StringUtil.esVacia(filtros.get("codigo"))) {
                filtrosPdf.put(ComboLocaleManager.get("catalogo.codigo"),
                        filtros.get("codigo"));
            }
            if (!StringUtil.esVacia(filtros.get("nombre"))) {
                filtrosPdf.put(ComboLocaleManager.get("catalogo.descripcion"),
                        filtros.get("nombre"));
            }
        }
        return filtrosPdf;
    }

    // ================== Getters && Setter ==============================

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
