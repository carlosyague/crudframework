package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.webapp.form.ISearchForm;

/**
 * Criteria para las busquedas de codigos por el tipo de biblioteca.
 *
 * @author jcisneros
 */
public class GestionCodigoCriteriaForm implements ISearchForm, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 1367513816013105643L;

    /**
     * Identificador del tipo de codigo.
     */
    private Long              idTipo           = null;
    private List<SelectItem>  valores          = null;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final HashMap<String, Object> criterios = new HashMap<String, Object>();

        criterios.put(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA, this.getIdTipo());

        return criterios;
    }

    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String, Object>)
     */
    public void fromMap(Map<String, Object> criterios){
        if (criterios!=null){
            if (criterios.get(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA)!=null){
                setIdTipo(new Long(criterios.get(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA).toString()));
            }
        }
    }

    // Getters && Setter

    public Long getIdTipo() {
        return this.idTipo;
    }

    public void setIdTipo(final Long idTipo) {
        this.idTipo = idTipo;
    }

    public List<SelectItem> getValores() {
        return this.valores;
    }

    public void setValores(final List<SelectItem> valores) {
        this.valores = valores;
    }

}
