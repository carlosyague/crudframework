package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.librae.adminconfig.model.Rol;
import org.librae.common.webapp.form.ISearchForm;

/**
 * Criteria para las busquedas de roles.
 * 
 * @author jcisneros
 */
public class RolCriteriaForm implements ISearchForm, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 8522996923345207875L;

    /**
     * Código alfanumérico unívoco del rol
     */
    private String            codigo;

    /**
     * Nombre del rol
     */
    private String            nombre;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final HashMap<String, Object> criterios = new HashMap<String, Object>();

        criterios.put(Rol.PROPTY_NAME_CODIGO, getCodigo());
        criterios.put(Rol.PROPTY_NAME_NOMBRE, getNombre());

        return criterios;
    }

    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String,
     *      Object>)
     */
    public void fromMap(Map<String, Object> criterios) {
        if (criterios != null) {
            if (criterios.get(Rol.PROPTY_NAME_CODIGO) != null) {
                setCodigo(criterios.get(Rol.PROPTY_NAME_CODIGO).toString());
            }
            if (criterios.get(Rol.PROPTY_NAME_NOMBRE) != null) {
                setNombre(criterios.get(Rol.PROPTY_NAME_NOMBRE).toString());
            }
        }
    }

    // Getters && Setters

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
