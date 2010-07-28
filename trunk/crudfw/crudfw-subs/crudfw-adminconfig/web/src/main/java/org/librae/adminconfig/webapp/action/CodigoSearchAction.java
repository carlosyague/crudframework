package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.TipoCodigo;
import org.librae.adminconfig.model.ValorCodigo;

import org.librae.adminconfig.webapp.delegate.ICodigoSearchDelegate;

import org.librae.adminconfig.webapp.form.CodigoForm;
import org.librae.common.service.impl.ComboLocaleManager;

import org.librae.common.webapp.action.BaseSearchPage;

/**
 * Action-JSF para la gestion de codigos.
 * 
 * @author jcisneros
 */
public class CodigoSearchAction extends BaseSearchPage implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 9193482532222506427L;

    public String getInit() {
        setSessionManagerParam("valores", new ArrayList<ValorCodigo>());
        removeSessionManagerParam("idCodigo_form");
        removeSessionManagerParam("valor_codigo_key");
        setSessionManagerParam("codigo_form", new CodigoForm());

        return null;
    }

    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtros = obtenerFiltros(false);
        final Map<String, Object> filtrosPdf = new HashMap<String, Object>();
        if (filtros != null) {
            if (!(new Long(0)).equals(filtros.get("tipoCodigo"))) {
                final TipoCodigo tipoCodigo = ((ICodigoSearchDelegate) getDelegate())
                        .getTipoCodigo(new Long(filtros.get("tipoCodigo")
                                .toString()));
                final String transformacion = ComboLocaleManager.get(tipoCodigo
                        .getDescripcion().replace("#", ""));
                filtrosPdf.put(ComboLocaleManager.get("codigo.codigo"),
                        transformacion);
            }
        }
        return filtrosPdf;
    }

    protected Map<String, Object> completarColumn() {
        final Map<String, Object> mapa = new HashMap<String, Object>();
        final List<TipoBiblioteca> tiposBiblioteca = ((ICodigoSearchDelegate) getDelegate())
                .getTiposBilioteca();
        for (final TipoBiblioteca tipoBiblioteca : tiposBiblioteca) {
            mapa.put(tipoBiblioteca.getId().toString(), ComboLocaleManager
                    .get(tipoBiblioteca.getCodigo()));
        }
        return mapa;
    }

}
