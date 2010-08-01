package org.librae.adquisicion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un MotivoBloqueo
 *
 * @author jcdiaz
 */
@Entity(name = MotivoRechazo.NAME_ENTITY)
@Table(name = MotivoRechazo.TABLE_NAME)
public class MotivoRechazo extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto MotivoBloqueo necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID          = -6605698319225665616L;

    public static final String  NAME_ENTITY               = "org.librae.adquisicion.model.MotivoRechazo";
    public static final String  TABLE_NAME                = "ADQ_MOTIVO_RECHAZOS";
    private static final String ID_GENERATOR_NAME         = "generator_adq_motivo_rechazos";
    private static final String ID_SEQUENCE_NAME          = "SEQ_ADQ_MOTIVO_RECHAZOS";
    public static final String  COLUMN_NAME_ID            = "X_MOTIVO_RECHAZO";
    public static final String  COLUMN_NAME_DESCRIPCION   = "T_DESCRIPCION";
    public static final String  COLUMN_NAME_BIBLIOTECA_FK = "BIB_X_BIBLIOTECA";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * descripcion del motivo
     */
    private String              descripcion;

    /**
     * referencia a la biblioteca que gestiona el motivo
     */
    private Biblioteca          biblioteca;

    /**
     * Constructor sin parámetros
     */
    protected MotivoRechazo() {
    }

    /**
     * Constructor con parámetros
     *
     * @param biblioteca
     * @param descripcion
     * @param id
     */
    public MotivoRechazo(String descripcion) {
        this.descripcion = descripcion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = MotivoRechazo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = MotivoRechazo.ID_SEQUENCE_NAME)
    @Column(name = MotivoRechazo.COLUMN_NAME_ID)
    /*
     * @return the id
     */
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
     * @return the descripcion
     */
    @Column(name = MotivoRechazo.COLUMN_NAME_DESCRIPCION, length = 400)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the biblioteca
     */
    // FIXME MotivoRechazo -> Biblioteca
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
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (!(obj instanceof MotivoRechazo)) {
            return false;
        }
        MotivoRechazo other = (MotivoRechazo) obj;
        if (biblioteca == null) {
            if (other.biblioteca != null) {
                return false;
            }
        } else if (!biblioteca.equals(other.biblioteca)) {
            return false;
        }
        if (descripcion == null) {
            if (other.descripcion != null) {
                return false;
            }
        } else if (!descripcion.equals(other.descripcion)) {
            return false;
        }
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
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(MotivoRechazo.COLUMN_NAME_ID,
                this.id).append(MotivoRechazo.COLUMN_NAME_DESCRIPCION,
                this.descripcion).toString();
    }
}