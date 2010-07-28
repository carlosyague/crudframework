/**
 *
 */
package org.librae.estadisticas.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * @author jVillegas
 */
@Entity(name = FormatoInforme.NAME_ENTITY)
@Table(name = FormatoInforme.TABLE_NAME)
public class FormatoInforme extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID              = 1L;

    public static final String  NAME_ENTITY                   = "org.librae.estadisticas.model.FormatoInforme";
    public static final String  TABLE_NAME                    = "EIL_FORMATOS_X_INFORME";
    private static final String ID_GENERATOR_NAME             = "generator_eil_formato_x_informe";
    private static final String ID_SEQUENCE_NAME              = "SEQ_EIL_FORMATOS_X_INFORME";
    public static final String  COLUMN_NAME_ID                = "X_FORMATO_INFORME";
    public static final String  COLUMN_NAME_INFORME           = "EIL_INFORMES_X_INFORME";
    public static final String  COLUMN_NAME_FORMATO           = "EIL_FORMATOS_X_FORMATO";
    public static final String  COLUMN_NAME_ACTIVO            = "L_ACTIVO";
    public static final String  COLUMN_NAME_FORMATO_DEFECTO   = "L_DEFECTO";

    public static final String  PROPERTY_NAME_ID              = "id";
    public static final String  PROPERTY_NAME_INFORME_FK      = "informe";
    public static final String  PROPERTY_NAME_FORMATO_FK      = "formato";
    public static final String  PROPERTY_NAME_ACTIVO          = "activo";
    public static final String  PROPERTY_NAME_FORMATO_DEFECTO = "formatoPorDefecto";

    /**
     * Clave primaria.
     */
    private Long                id;
    /**
     * Referencia al informe.
     */
    private Informe             informe;
    /**
     * Especifica si el formato va a estar activo para el informe o no.
     */
    private Boolean             activo;
    /**
     * Referencia al formato.
     */
    private Formato             formato;
    /**
     * Formato por defecto.
     */
    private Boolean             formatoPorDefecto;

    /**
     * Constructor sin parametros
     */
    protected FormatoInforme() {

    }

    /**
     * @param informe
     * @param activo
     * @param formato
     * @param formatoPorDefectoFinal
     *            formatoPorDefectoFinal
     */
    public FormatoInforme(Informe informeFinal, Boolean activoFinal,
            Formato formatoFinal, Boolean formatoPorDefectoFinal) {
        super();
        this.informe = informeFinal;
        this.activo = activoFinal;
        this.formato = formatoFinal;
        this.formatoPorDefecto = formatoPorDefectoFinal;
    }

    /**
     * @return the id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = FormatoInforme.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the informe.
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Informe.class)
    @JoinColumn(name = FormatoInforme.COLUMN_NAME_INFORME)
    public Informe getInforme() {
        return informe;
    }

    /**
     * @param informe
     *            the informe to set.
     */
    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    /**
     * @return the activo.
     */
    @Column(name = FormatoInforme.COLUMN_NAME_ACTIVO, nullable = false)
    public Boolean getActivo() {
        return activo;
    }

    /**
     * @param activo
     *            the activo to set.
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the formato.
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.estadisticas.model.Formato.class)
    @JoinColumn(name = FormatoInforme.COLUMN_NAME_FORMATO, nullable = false)
    public Formato getFormato() {
        return formato;
    }

    /**
     * @param formatoFinal
     *            the formato to set.
     */
    public void setFormato(Formato formatoFinal) {
        this.formato = formatoFinal;
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
        if (!(o instanceof FormatoInforme)) {
            return false;
        }
        final FormatoInforme other = (FormatoInforme) o;
        if (informe == null) {
            if (other.informe != null) {
                return false;
            }
        } else if (!informe.equals(other.informe)) {
            return false;
        }
        if (formato == null) {
            if (other.formato != null) {
                return false;
            }
        } else if (!formato.equals(other.formato)) {
            return false;
        }
        if (activo == null) {
            if (other.activo != null) {
                return false;
            }
        } else if (!activo.equals(other.activo)) {
            return false;
        }
        if (formatoPorDefecto == null) {
            if (other.formatoPorDefecto != null) {
                return false;
            }
        } else if (!formatoPorDefecto.equals(other.formatoPorDefecto)) {
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
        return new ToStringBuilder(this).append(FormatoInforme.COLUMN_NAME_ID,
                id).append(FormatoInforme.COLUMN_NAME_INFORME, informe).append(
                FormatoInforme.COLUMN_NAME_FORMATO, formato).append(
                FormatoInforme.COLUMN_NAME_ACTIVO, activo).append(
                FormatoInforme.COLUMN_NAME_FORMATO_DEFECTO, formatoPorDefecto)
                .toString();
    }

    /**
     * @return the formatoPorDefecto
     */
    @Column(name = FormatoInforme.COLUMN_NAME_FORMATO_DEFECTO, nullable = false)
    public Boolean getFormatoPorDefecto() {
        return formatoPorDefecto;
    }

    /**
     * @param formatoPorDefecto
     *            the formatoPorDefecto to set
     */
    public void setFormatoPorDefecto(Boolean formatoPorDefecto) {
        this.formatoPorDefecto = formatoPorDefecto;
    }

}
