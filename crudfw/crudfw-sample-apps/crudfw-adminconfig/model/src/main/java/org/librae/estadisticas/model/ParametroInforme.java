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

import org.hibernate.engine.Cascade;
import org.librae.common.model.BaseObject;

/**
 * @author jVillegas
 */
@Entity(name = ParametroInforme.NAME_ENTITY)
@Table(name = ParametroInforme.TABLE_NAME)
public class ParametroInforme extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID           = 1L;

    public static final String  NAME_ENTITY                = "org.librae.estadisticas.model.ParametroInforme";
    public static final String  TABLE_NAME                 = "EIL_PARAMETROS_X_INFORME";
    private static final String ID_GENERATOR_NAME          = "generator_eil_parametros_x_informe";
    private static final String ID_SEQUENCE_NAME           = "SEQ_EIL_PARAMETROS_X_INFORME";
    public static final String  COLUMN_NAME_ID             = "X_PARAMETROS_X_INFORME";
    public static final String  COLUMN_NAME_VALOR          = "T_VALOR";
    public static final String  COLUMN_NAME_ACTIVO         = "L_ACTIVO";
    public static final String  COLUMN_NAME_OCULTO         = "L_OCULTO";
    public static final String  COLUMN_NAME_MULTIVALUADO   = "L_MULTIVALUDAO";
    public static final String  COLUMN_NAME_INFORME        = "EIL_INFORMES_X_INFORME";
    public static final String  COLUMN_NAME_PARAMETRO      = "EIL_PARAMETROS_X_PARAMETRO";

    public static final String  PROPERTY_NAME_ID           = "id";
    public static final String  PROPERTY_NAME_VALOR        = "valorDefecto";
    public static final String  PROPERTY_NAME_ACTIVO       = "activo";
    public static final String  PROPERTY_NAME_OCULTO       = "oculto";
    public static final String  PROPERTY_NAME_MULTIVALUADO = "multivaluado";
    public static final String  PROPERTY_NAME_INFORME_FK   = "informe";
    public static final String  PROPERTY_NAME_PARAMETRO_FK = "parametro";

    /**
     * Clave primaria.
     */
    private Long                id;
    /**
     * Informe asociado.
     */
    private Informe             informe;
    /**
     * Parametro asociado.
     */
    private Parametro           parametro;
    /**
     * Indica si el parametro está activo o no.
     */
    private Boolean             activo;
    /**
     * Indica si el parametro es multivaluado o no.
     */
    private Boolean             multivaluado;
    /**
     * Especifica si el parámetro está oculto o no.
     */
    private Boolean             oculto;
    /**
     * Valor por defecto.
     */
    private String              valorDefecto;

    /**
     * Constructor sin parametros
     */
    protected ParametroInforme() {

    }

    /**
     * @param informeFinal
     * @param parametroFinal
     * @param activoFinal
     * @param multivaluadoFinal
     * @param ocultoFinal
     */
    public ParametroInforme(Informe informeFinal, Parametro parametroFinal,
            Boolean activoFinal, Boolean multivaluadoFinal,
            Boolean ocultoFinal, String valorDefectoFinal) {
        super();
        this.informe = informeFinal;
        this.parametro = parametroFinal;
        this.activo = activoFinal;
        this.multivaluado = multivaluadoFinal;
        this.oculto = ocultoFinal;
        this.valorDefecto = valorDefectoFinal;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = ParametroInforme.COLUMN_NAME_ID)
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
     * @return the informe
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.estadisticas.model.Informe.class)
    @JoinColumn(name = ParametroInforme.COLUMN_NAME_INFORME)
    public Informe getInforme() {
        return informe;
    }

    /**
     * @param informe
     *            the informe to set
     */
    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    /**
     * @return the parametro
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, targetEntity = org.librae.estadisticas.model.Parametro.class)
    @JoinColumn(name = ParametroInforme.COLUMN_NAME_PARAMETRO)
    public Parametro getParametro() {
        return parametro;
    }

    /**
     * @param parametro
     *            the parametro to set
     */
    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    /**
     * @return the activo
     */
    @Column(name = ParametroInforme.COLUMN_NAME_ACTIVO)
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
     * @return the multivaluado
     */
    @Column(name = ParametroInforme.COLUMN_NAME_MULTIVALUADO)
    public Boolean getMultivaluado() {
        return multivaluado;
    }

    /**
     * @param multivaluado
     *            the multivaluado to set
     */
    public void setMultivaluado(Boolean multivaluado) {
        this.multivaluado = multivaluado;
    }

    /**
     * @return the oculto
     */
    @Column(name = ParametroInforme.COLUMN_NAME_OCULTO)
    public Boolean getOculto() {
        return oculto;
    }

    /**
     * @param oculto
     *            the oculto to set
     */
    public void setOculto(Boolean oculto) {
        this.oculto = oculto;
    }

    /**
     * @return the valorDefecto
     */
    @Column(name = ParametroInforme.COLUMN_NAME_VALOR, length = 20)
    public String getValorDefecto() {
        return valorDefecto;
    }

    /**
     * @param valorDefecto
     *            the valorDefecto to set
     */
    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
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
        if (!(o instanceof ParametroInforme)) {
            return false;
        }
        final ParametroInforme other = (ParametroInforme) o;
        if (activo == null) {
            if (other.activo != null) {
                return false;
            }
        } else if (!activo.equals(other.activo)) {
            return false;
        }
        if (valorDefecto == null) {
            if (other.valorDefecto != null) {
                return false;
            }
        } else if (!valorDefecto.equals(other.valorDefecto)) {
            return false;
        }
        if (oculto == null) {
            if (other.oculto != null) {
                return false;
            }
        } else if (!oculto.equals(other.oculto)) {
            return false;
        }
        if (multivaluado == null) {
            if (other.multivaluado != null) {
                return false;
            }
        } else if (!multivaluado.equals(other.multivaluado)) {
            return false;
        }
        if (informe == null) {
            if (other.informe != null) {
                return false;
            }
        } else if (!informe.equals(other.informe)) {
            return false;
        }
        if (parametro == null) {
            if (other.parametro != null) {
                return false;
            }
        } else if (!parametro.equals(other.parametro)) {
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
        result = prime * result
                + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((valorDefecto == null) ? 0 : valorDefecto.hashCode());
        result = prime * result + ((oculto == null) ? 0 : oculto.hashCode());
        result = prime * result + ((activo == null) ? 0 : activo.hashCode());
        result = prime * result
                + ((multivaluado == null) ? 0 : multivaluado.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}
