package org.librae.catalogacion.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar una Novedad
 *
 * @author jcdiaz
 */
@Entity(name = Novedad.ENTITY_NAME)
@Table(name = Novedad.TABLE_NAME)
public class Novedad extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Novedad necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID            = -2939719946278047237L;

    public static final String  ENTITY_NAME                 = "org.librae.catalogacion.model.Novedad";
    public static final String  TABLE_NAME                  = "CAT_NOVEDADES";
    private static final String ID_GENERATOR_NAME           = "generator_cat_novedades";
    private static final String ID_SEQUENCE_NAME            = "SEQ_CAT_NOVEDADES";
    public static final String  COLUMN_NAME_ID              = "X_NOVEDAD";
    public static final String  COLUMN_NAME_PERIODO_DIAS    = "N_PERIODO_DIAS";
    public static final String  COLUMN_NAME_FECHA_PROMOCION = "F_FECHA_PROMOCION";
    public static final String  COLUMN_NAME_FECHA_CADUCIDAD = "F_FECHA_CADUCIDAD";
    public static final String  COLUMN_NAME_REGISTRO_FK     = "REG_X_REGISTRO";
    public static final String  COLUMN_NAME_BIBLIOTECA_FK   = "BIB_X_BIBLIOTECA";
    public static final String  REGISTRO                    = "registro";

    /**
     * clave principal
     */
    private Long                id;

    /**
     *
     */
    private Integer             periodoDias;

    /**
     *
     */
    private Date                fechaPromocion;

    /**
     *
     */
    private Date                fechaCaducidad;

    /**
     * referencia a la bilbioteca de la novedad
     */
    private Biblioteca          biblioteca;

    /**
     * referencia al registro de la novedad
     */
    private Registro            registro;

    /**
     * Constructor sin parámetros
     */
    protected Novedad() {
    }

    /**
     * Constructor con parámetros
     *
     * @param periodoDias
     * @param fechaPromocion
     * @param fechaCaducidad
     */
    public Novedad(Integer periodoDias, Date fechaPromocion, Date fechaCaducidad) {
        this.periodoDias = periodoDias;
        this.fechaPromocion = fechaPromocion;
        this.fechaCaducidad = fechaCaducidad;

    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Novedad.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Novedad.ID_SEQUENCE_NAME)
    @Column(name = Novedad.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the periodoDias
     */
    @Column(name = Novedad.COLUMN_NAME_PERIODO_DIAS, nullable = false)
    public Integer getPeriodoDias() {
        return periodoDias;
    }

    /**
     * @param periodoDias
     *            the periodoDias to set
     */
    public void setPeriodoDias(Integer periodoDias) {
        this.periodoDias = periodoDias;
    }

    /**
     * @return the fechaPromocion
     */
    @Column(name = Novedad.COLUMN_NAME_FECHA_PROMOCION, nullable = false)
    public Date getFechaPromocion() {
        return fechaPromocion;
    }

    /**
     * @param fechaPromocion
     *            the fechaPromocion to set
     */
    public void setFechaPromocion(Date fechaPromocion) {
        this.fechaPromocion = fechaPromocion;
    }

    /**
     * @return the fechaCaducidad
     */
    @Column(name = Novedad.COLUMN_NAME_FECHA_CADUCIDAD, nullable = false)
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * @param fechaCaducidad
     *            the fechaCaducidad to set
     */
    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * @return the registro
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.catalogacion.model.Registro.class)
    @JoinColumn(name = Novedad.COLUMN_NAME_REGISTRO_FK, nullable = false)
    public Registro getRegistro() {
        return registro;
    }

    /**
     * @param registro
     *            the registro to set
     */
    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = COLUMN_NAME_BIBLIOTECA_FK)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_NOV")
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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result
                + ((biblioteca == null) ? 0 : biblioteca.hashCode());
        result = prime * result
                + ((fechaCaducidad == null) ? 0 : fechaCaducidad.hashCode());
        result = prime * result
                + ((fechaPromocion == null) ? 0 : fechaPromocion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((periodoDias == null) ? 0 : periodoDias.hashCode());
        result = prime * result
                + ((registro == null) ? 0 : registro.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Novedad)) {
            return false;
        }
        final Novedad other = (Novedad) obj;
        if (biblioteca == null) {
            if (other.biblioteca != null) {
                return false;
            }
        } else if (!biblioteca.equals(other.biblioteca)) {
            return false;
        }
        if (fechaCaducidad == null) {
            if (other.fechaCaducidad != null) {
                return false;
            }
        } else if (!fechaCaducidad.equals(other.fechaCaducidad)) {
            return false;
        }
        if (fechaPromocion == null) {
            if (other.fechaPromocion != null) {
                return false;
            }
        } else if (!fechaPromocion.equals(other.fechaPromocion)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (periodoDias == null) {
            if (other.periodoDias != null) {
                return false;
            }
        } else if (!periodoDias.equals(other.periodoDias)) {
            return false;
        }
        if (registro == null) {
            if (other.registro != null) {
                return false;
            }
        } else if (!registro.equals(other.registro)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(Novedad.COLUMN_NAME_ID, id).append(
                        Novedad.COLUMN_NAME_PERIODO_DIAS, periodoDias)
                .append(Novedad.COLUMN_NAME_FECHA_PROMOCION,
                        fechaPromocion).append(
                        Novedad.COLUMN_NAME_FECHA_CADUCIDAD,
                        fechaCaducidad).toString();
    }
}