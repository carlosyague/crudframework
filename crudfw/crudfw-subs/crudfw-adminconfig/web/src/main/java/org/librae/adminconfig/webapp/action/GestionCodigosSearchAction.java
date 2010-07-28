package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.Map;
import java.util.List;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.webapp.action.BaseSearchPage;

/**
 * Action-JSF para la gestion de codigos.
 * 
 * @author jcisneros
 */
public class GestionCodigosSearchAction extends BaseSearchPage implements
        Serializable {

    private Long              idTipo           = null;

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 9193282532222506427L;

    protected void completarFilter(final Map<String, Object> filtros) {
        filtros.put(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA, idTipo);
    }

    protected void marcarSeleccionados(final List listado) {
        // este listado no se selecciona
    }

    // Getters && Setters

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(final Long idTipo) {
        this.idTipo = idTipo;
    }

}
