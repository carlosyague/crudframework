/**
 *
 */
package org.librae.mensajeria.model;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * @author jVillegas
 */
public class TareaPendienteDTO extends BaseObject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -580796375486539039L;

    /**
     * Subsistema que que genero la tarea.
     */
    String nombreSubsistema;

    /**
     * Descripcion de la tarea.
     */
    String descripcion;

    /**
     * Enlace al subsistema
     */
    String enlaceSubsistema;

    /**
    * Mapa con el key para poder levantar el property con la descripcion y el resultado
    * El tratamiento de descripciones lo hace la capa Web porque el Core no puede manejar .props
    */
    Map<String, Integer> valores;

    /**
     * @return the subsistema
     */
    public String getNombreSubsistema() {
        return nombreSubsistema;
    }

    /**
     * @param subsistema the subsistema to set
     */
    public void setNombreSubsistema(String nombreSubsistemaFinal) {
        this.nombreSubsistema = nombreSubsistemaFinal;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcionFinal) {
        this.descripcion = descripcionFinal;
    }

    /**
     * @return the enlaceSubsistema
     */
    public String getEnlaceSubsistema() {
        return enlaceSubsistema;
    }

    /**
     * @param enlaceSubsistema the enlaceSubsistema to set
     */
    public void setEnlaceSubsistema(String enlaceSubsistemaFinal) {
        this.enlaceSubsistema = enlaceSubsistemaFinal;
    }

    /**
     * @return the valores
     */
    public Map<String, Integer> getValores() {
        return valores;
    }

    /**
     * @param valMap
     */
    public void setValores(HashMap<String,Integer> valMap) {
        this.valores = valMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TareaPendienteDTO)) {
            return false;
        }

        final TareaPendienteDTO other = (TareaPendienteDTO) obj;

        if (!this.nombreSubsistema.equals(other.getNombreSubsistema())) {
            return false;
        }

        if (!this.descripcion.equals(other.getDescripcion())) {
            return false;
        }

        if (!this.enlaceSubsistema.equals(other.getEnlaceSubsistema())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((this.descripcion == null) ? 0 : this.descripcion.hashCode());
        result = prime * result + ((this.nombreSubsistema == null) ? 0 : this.nombreSubsistema.hashCode());
        result = prime * result + ((this.enlaceSubsistema == null) ? 0 : this.enlaceSubsistema.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(this.descripcion).append(getNombreSubsistema()).append(this.enlaceSubsistema).toString();
    }

}
