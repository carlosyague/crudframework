/**
 *
 */
package org.librae.mensajeria.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.model.BaseObject;

/**
 * @author amDelcampo
 */
@Entity(name = Sucursal.NAME_ENTITY)
@Table(name = Sucursal.TABLE_NAME)
public class Sucursal extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID                  = -8173673867221914110L;

    public static final String  NAME_ENTITY                       = "org.librae.mensajeria.model.Sucursal";
    public static final String  TABLE_NAME                        = "MEN_DSI_BIBLIOTECA";

    private static final String ID_GENERATOR_NAME                 = "generator_men_dsi_biblioteca";
    private static final String ID_SEQUENCE_NAME                  = "SEQ_MEN_DSI_BIBLIOTECA";

    public static final String  COLUMN_NAME_ID                    = "X_DSI_BIBLIOTECA ";
    public static final String  COLUMN_NAME_TIPONOTIFICACION_FK   = "DSI_X_DSI_CATALOGO";
    public static final String  COLUMN_NAME_BIBLIOTECA_FK         = "BIB_X_BIBLIOTECA";

    public static final String  PROPERTY_NAME_ID                  = "id";
    public static final String  PROPERTY_NAME_TIPONOTIFICACION_FK = "tipoNotificacion";
    public static final String  PROPERTY_NAME_BIBLIOTECA_FK       = "biblioteca";

    /**
     * Clave primaria.
     */
    private Long                id;

    /**
     * Referencia al Tipo de Notificación.
     */
    private TipoNotificacion    tipoNotificacion;

    /**
     * Referencia a la biblioteca.
     */
    private Biblioteca          biblioteca;

    /**
     * Constructor sin parámetros.
     */
    public Sucursal() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Sucursal.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Sucursal.ID_SEQUENCE_NAME)
    @Column(name = Sucursal.COLUMN_NAME_ID, nullable = false)
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
     * @return the tipoNotificacion
     */
    @ManyToOne
    @JoinColumn(name = Sucursal.COLUMN_NAME_TIPONOTIFICACION_FK)
    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    /**
     * @param tipoNotificacion
     *            the tipoNotificacion to set
     */
    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    /**
     * @return the biblioteca
     */
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.adminconfig.model.Biblioteca.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Sucursal.COLUMN_NAME_BIBLIOTECA_FK)
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca
     *            the biblioteca to set
     */
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sucursal)) {
            return false;
        }
        final Sucursal other = (Sucursal) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(
                TipoNotificacion.COLUMN_NAME_ID, id).toString();
    }

}
