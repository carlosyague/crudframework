/**
 *
 */
package org.librae.estadisticas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * @author jVillegas
 */
@Entity(name = Parametro.NAME_ENTITY)
@Table(name = Parametro.TABLE_NAME)
public class Parametro extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID           = 1L;

    public static final String  NAME_ENTITY                = "org.librae.estadisticas.model.Parametro";
    public static final String  TABLE_NAME                 = "EIL_PARAMETROS";
    private static final String ID_GENERATOR_NAME          = "generator_eil_parametros";
    private static final String ID_SEQUENCE_NAME           = "SEQ_EIL_PARAMETROS";
    public static final String  COLUMN_NAME_ID             = "X_PARAMETRO";
    public static final String  COLUMN_NAME_ACTIVO         = "L_ACTIVO";
    public static final String  COLUMN_NAME_METODO         = "T_METODO";
    public static final String  COLUMN_NAME_NOMBRE         = "T_NOMBRE";
    public static final String  COLUMN_NAME_TIPO           = "T_TIPO";
    public static final String  COLUMN_NAME_TIPO_ENTIDAD   = "T_TIPO_ENTIDAD";
    public static final String  COLUMN_NAME_VALOR          = "T_VALOR";

    public static final String  PROPERTY_NAME_ID           = "id";
    public static final String  PROPERTY_NAME_ACTIVO       = "activo";
    public static final String  PROPERTY_NAME_METODO       = "metodo";
    public static final String  PROPERTY_NAME_NOMBRE       = "nombre";
    public static final String  PROPERTY_NAME_TIPO         = "tipo";
    public static final String  PROPERTY_NAME_TIPO_ENTIDAD = "tipoEntidad";
    public static final String  PROPERTY_NAME_VALOR        = "valorPorDefecto";

    /**
     * Clave primaria
     */
    private Long                id;
    /**
     * Indica si el parametro está activo o no.
     */
    private Boolean             activo;
    /**
     * Metodo del tipo de entidad para asociar al parámetro.
     */
    private String              metodo;
    /**
     * Nombre del parametro.
     */
    private String              nombre;
    /**
     * Tipo de dato parametro.
     */
    private String              tipo;
    /**
     * Tipo de entidad del parámetro.
     */
    private String              tipoEntidad;
    /**
     * valor por defecto.
     */
    private String              valorPorDefecto;

    /**
     * Constructor sin parametros.
     */
    protected Parametro() {

    }
    /**
     * Constructor con parametros.
     * @param nombre
     *          the nombre.
     * @param tipo
     *          the tipo.
     * @param tipoEntidad
     *          the tipoEntidad
     * @param metodo
     *          the metodo.
     * @param activo
     *          the activo.
     */
    public Parametro(String nombre, String tipo, String tipoEntidad, String metodo, Boolean activo){
        this.nombre = nombre;
        this.tipo = tipo;
        this.tipoEntidad = tipoEntidad;
        this.metodo = metodo;
        this.activo = activo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Parametro.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the activo
     */
    @Column(name = Parametro.COLUMN_NAME_ACTIVO)
    public Boolean getActivo() {
        return activo;
    }

    /**
     * @param activo
     *            the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the metodo
     */
    @Column(name = Parametro.COLUMN_NAME_METODO, length = 50)
    public String getMetodo() {
        return metodo;
    }

    /**
     * @param metodo
     *            the metodo to set
     */
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    /**
     * @return the nombre
     */
    @Column(name = Parametro.COLUMN_NAME_NOMBRE, length = 255)
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

    /**
     * @return the tipo
     */
    @Column(name = Parametro.COLUMN_NAME_TIPO, length = 40)
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     *            the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the tipoEntidad
     */
    @Column(name = Parametro.COLUMN_NAME_TIPO_ENTIDAD, length = 80)
    public String getTipoEntidad() {
        return tipoEntidad;
    }

    /**
     * @param tipoEntidad
     *            the tipoEntidad to set
     */
    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    /**
     * @return the valorPorDefecto
     */
    @Column(name = Parametro.COLUMN_NAME_VALOR, length = 20)
    public String getValorPorDefecto() {
        return valorPorDefecto;
    }

    /**
     * @param valorPorDefecto
     *            the valorPorDefecto to set
     */
    public void setValorPorDefecto(String valorPorDefecto) {
        this.valorPorDefecto = valorPorDefecto;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parametro)) {
            return false;
        }
        final Parametro other = (Parametro) o;
        if (activo == null) {
            if (other.activo != null) {
                return false;
            }
        } else if (!activo.equals(other.activo)) {
            return false;
        }
        if (metodo == null) {
            if (other.metodo != null) {
                return false;
            }
        } else if (!metodo.equals(other.metodo)) {
            return false;
        }
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (tipo == null) {
            if (other.tipo != null) {
                return false;
            }
        } else if (!tipo.equals(other.tipo)) {
            return false;
        }
        if (tipoEntidad == null) {
            if (other.tipoEntidad != null) {
                return false;
            }
        } else if (!tipoEntidad.equals(other.tipoEntidad)) {
            return false;
        }
        if (valorPorDefecto == null) {
            if (other.valorPorDefecto != null) {
                return false;
            }
        } else if (!valorPorDefecto.equals(other.valorPorDefecto)) {
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((metodo == null) ? 0 : metodo.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((activo == null) ? 0 : activo.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + ((tipoEntidad == null) ? 0 : tipoEntidad.hashCode());
        result = prime * result + ((valorPorDefecto == null) ? 0 : valorPorDefecto.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Parametro.COLUMN_NAME_ID, id)
                .append(Parametro.COLUMN_NAME_ACTIVO, activo).append(
                        Parametro.COLUMN_NAME_METODO, metodo).append(
                        Parametro.COLUMN_NAME_NOMBRE, nombre).append(
                        Parametro.COLUMN_NAME_TIPO, tipo).append(
                        Parametro.COLUMN_NAME_TIPO_ENTIDAD, tipoEntidad).append(
                        Parametro.COLUMN_NAME_VALOR, valorPorDefecto).toString();
    }

}
