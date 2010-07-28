package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.librae.common.webapp.form.ISearchForm;

/**
 * Criteria para las busquedas de codigos.
 *
 * @author jcisneros
 */
public class ValorCodigoCriteriaForm implements ISearchForm, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Numero de serializacion.
     */

    /**
     * Identificador del tipo de codigo.
     */
    private Long              tipoCodigo       = null;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final HashMap<String, Object> criterios = new HashMap<String, Object>();

        return criterios;
    }


    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String, Object>)
     */
    public void fromMap(Map<String, Object> criterios){
        //TODO
    }

    // Getters && Setter

    public Long getTipoCodigo() {
        return this.tipoCodigo;
    }

    public void setTipoCodigo(final Long tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

}
