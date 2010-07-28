package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.action.BaseSearchPage;

/**
 * Action-JSF asociado a los listados de la entidad Parametro.
 * 
 * @author jcisneros
 */
public class ParametroSearchAction extends BaseSearchPage implements
        Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 6490725575102958007L;

    /**
     * Constructor.
     */
    public ParametroSearchAction() {
        super();
    }

    public String getInit() {
        // super.insertarHistorial(((IParametroSearchDelegate) getDelegate())
        // .getMenuItem(super.getUrl(), null, null));
        return "";
    }

    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtros = obtenerFiltros(false);
        final Map<String, Object> filtrosPdf = new HashMap<String, Object>();
        if (filtros != null) {
            if (!StringUtil.esVacia(filtros.get("codigo"))) {
                filtrosPdf.put(ComboLocaleManager.get("parametro.codigo"),
                        filtros.get("codigo"));
            }
            if (!StringUtil.esVacia(filtros.get("nombre"))) {
                filtrosPdf.put(ComboLocaleManager.get("parametro.descripcion"),
                        filtros.get("nombre"));
            }
        }
        return filtrosPdf;
    }
}
