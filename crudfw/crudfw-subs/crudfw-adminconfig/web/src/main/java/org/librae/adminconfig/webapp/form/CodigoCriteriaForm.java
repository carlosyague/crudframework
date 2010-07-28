package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.librae.adminconfig.model.Codigo;
import org.librae.common.webapp.form.ISearchForm;

/**
 * Criteria para las busquedas de codigos.
 *
 * @author jcisneros
 */
public class CodigoCriteriaForm implements ISearchForm, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 1367513816013105643L;

    /**
     * Identificador del tipo de codigo.
     */
    private Long              tipoCodigo       = null;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final HashMap<String, Object> criterios = new HashMap<String, Object>();

        criterios.put(Codigo.PROPTY_NAME_TIPO_CODIGO, this.getTipoCodigo());

        return criterios;
    }


    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String, Object>)
     */
    public void fromMap(Map<String, Object> criterios){
        if (criterios!=null){
            if (criterios.get(Codigo.PROPTY_NAME_TIPO_CODIGO)!=null){
                setTipoCodigo(new Long(criterios.get(Codigo.PROPTY_NAME_TIPO_CODIGO).toString()));
            }
        }
    }

    // Getters && Setter

    public Long getTipoCodigo() {
        return this.tipoCodigo;
    }

    public void setTipoCodigo(final Long tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

}
