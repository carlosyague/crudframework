package org.librae.adquisicion.model;

import java.math.BigDecimal;
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
 * Bean para almacenar un Divisa
 *
 * @author jcdiaz
 */
@Entity(name = Divisa.NAME_ENTITY)
@Table(name = Divisa.TABLE_NAME)
public class Divisa extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Divisa necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID              = 4662636472841727588L;

    public static final String  NAME_ENTITY                   = "org.librae.adquisicion.model.Divisa";
    public static final String  TABLE_NAME                    = "ADQ_DIVISAS";
    private static final String ID_GENERATOR_NAME             = "generator_adq_divisas";
    private static final String ID_SEQUENCE_NAME              = "SEQ_ADQ_DIVISAS";
    public static final String  COLUMN_NAME_ID                = "X_DIVISA";
    public static final String  COLUMN_NAME_CODIGO            = "T_CODIGO";
    public static final String  COLUMN_NAME_DESCRIPCION       = "T_DESCRIPCION";
    public static final String  COLUMN_NAME_VALOR             = "I_VALOR";
    public static final String  COLUMN_NAME_FECHA_PUBLICACION = "F_FECHA_PUBLICACION";
    public static final String  COLUMN_NAME_ESCALAS_DECIMALES = "N_ESCALA_DECIMALES";
    public static final String  COLUMN_NAME_BIBLIOTECA_FK     = "BIB_X_BIBLIOTECA";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * codigo de la divisa
     */
    private String              codigo;

    /**
     * descripción de la divisa
     */
    private String              descripcion;

    /**
     * importe del valor
     */
    private BigDecimal          valor;

    /**
     * descripcion del motivo
     */
    private Date                fechaPublicacion;

    /**
     * cantidad de decimales
     */
    private Integer             escalasDecimales;

    /**
     * referencia a la biblioteca que gestiona el motivo
     */
    private Biblioteca          biblioteca;

    /**
     * Constructor sin parámetros
     */
    protected Divisa() {
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param descripcion
     * @param escalasDecimales
     * @param fechaPublicacion
     * @param valor
     */
    public Divisa(String codigo, String descripcion, Integer escalasDecimales,
            Date fechaPublicacion, BigDecimal valor) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.escalasDecimales = escalasDecimales;
        this.fechaPublicacion = fechaPublicacion;
        this.valor = valor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Divisa.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Divisa.ID_SEQUENCE_NAME)
    @Column(name = Divisa.COLUMN_NAME_ID)
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
     * @return the codigo
     */
    @Column(name = Divisa.COLUMN_NAME_CODIGO, length = 40)
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
     * @return the descripcion
     */
    @Column(name = Divisa.COLUMN_NAME_DESCRIPCION, length = 80)
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
     * @return the valor
     */
    @Column(name = Divisa.COLUMN_NAME_VALOR)
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the fechaPublicacion
     */
    @Column(name = Divisa.COLUMN_NAME_FECHA_PUBLICACION)
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion
     *            the fechaPublicacion to set
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return the escalasDecimales
     */
    @Column(name = Divisa.COLUMN_NAME_ESCALAS_DECIMALES)
    public Integer getEscalasDecimales() {
        return escalasDecimales;
    }

    /**
     * @param escalasDecimales
     *            the escalasDecimales to set
     */
    public void setEscalasDecimales(Integer escalasDecimales) {
        this.escalasDecimales = escalasDecimales;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = Divisa.COLUMN_NAME_BIBLIOTECA_FK)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_DIV")
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
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime
                * result
                + ((escalasDecimales == null) ? 0 : escalasDecimales.hashCode());
        result = prime
                * result
                + ((fechaPublicacion == null) ? 0 : fechaPublicacion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
        if (!(obj instanceof Divisa)) {
            return false;
        }
        final Divisa other = (Divisa) obj;
        if (biblioteca == null) {
            if (other.biblioteca != null) {
                return false;
            }
        } else if (!biblioteca.equals(other.biblioteca)) {
            return false;
        }
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (descripcion == null) {
            if (other.descripcion != null) {
                return false;
            }
        } else if (!descripcion.equals(other.descripcion)) {
            return false;
        }
        if (escalasDecimales == null) {
            if (other.escalasDecimales != null) {
                return false;
            }
        } else if (!escalasDecimales.equals(other.escalasDecimales)) {
            return false;
        }
        if (fechaPublicacion == null) {
            if (other.fechaPublicacion != null) {
                return false;
            }
        } else if (!fechaPublicacion.equals(other.fechaPublicacion)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (valor == null) {
            if (other.valor != null) {
                return false;
            }
        } else if (!valor.equals(other.valor)) {
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
        return new ToStringBuilder(this).append(Divisa.COLUMN_NAME_ID, id)
                .append(Divisa.COLUMN_NAME_CODIGO, codigo).append(
                        Divisa.COLUMN_NAME_DESCRIPCION, descripcion)
                .append(Divisa.COLUMN_NAME_VALOR, valor).append(
                        Divisa.COLUMN_NAME_FECHA_PUBLICACION,
                        fechaPublicacion).append(
                        Divisa.COLUMN_NAME_ESCALAS_DECIMALES,
                        escalasDecimales).toString();
    }
}