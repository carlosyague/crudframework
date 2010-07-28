package org.librae.adquisicion.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un Presupuesto
 *
 * @author jcdiaz
 */
@Entity(name = Presupuesto.NAME_ENTITY)
@Table(name = Presupuesto.TABLE_NAME)
public class Presupuesto extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Presupuesto necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID                 = -8664793251042721123L;

    public static final String  NAME_ENTITY                      = "org.librae.adquisicion.model.Presupuesto";
    public static final String  TABLE_NAME                       = "ADQ_PRESUPUESTOS";
    private static final String ID_GENERATOR_NAME                = "generator_adq_presupuestos";
    private static final String ID_SEQUENCE_NAME                 = "SEQ_ADQ_PRESUPUESTOS";
    public static final String  COLUMN_NAME_ID                   = "X_PRESUPUESTO";
    public static final String  COLUMN_NAME_DESCRIPCION          = "T_DESCRIPCION";
    public static final String  COLUMN_NAME_CODIGO               = "T_CODIGO";
    public static final String  COLUMN_NAME_PORCENTAJE_AVISO     = "P_PORCENTAJE_AVISO";
    public static final String  COLUMN_NAME_FECHA_LIMITE         = "F_FECHA_LIMITE";
    public static final String  COLUMN_NAME_NOTAS                = "T_NOTAS";
    public static final String  COLUMN_NAME_CUENTA_FINAL         = "L_CUENTA_FINAL";
    public static final String  COLUMN_NAME_BIBLIOTECA_FK        = "BIB_X_BIBLIOTECA";
    public static final String  COLUMN_NAME_PRESUPUESTO_PADRE_FK = "PRES_X_PRESUPUESTO";
    public static final String  PRESUPUESTOPADRE                 = "presupuestoPadre";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * Descripción del presupuesto
     */
    private String              descripcion;

    /**
     * codigo del presupuesto
     */
    private String              codigo;

    /**
     * valor de porcentaje de aviso para el presupuesto
     */
    private BigDecimal          porcentajeAviso;

    /**
     * fecha limite para el presupuesto
     */
    private Date                fechaLimite;

    /**
     * notas del presupuesto
     */
    private String              notas;

    /**
     * indica si es cuenta final o no
     */
    private Boolean             cuentaFinal;

    /**
     * referencia a la biblioteca asociada
     */
    private Biblioteca          biblioteca;

    /**
     *
     */
    private Presupuesto         presupuestoPadre;

    /**
     *
     */
    private List<Presupuesto>   presupuestosHijos;

    /**
     *
     */
    // Presupuesto -> ListaPartida
    // private List<ListaPartida> listaPartidas;
    /**
     *
     */
    private IFiltro             estrategiaFiltro;

    /**
     * Constructor sin parámetros
     */
    protected Presupuesto() {
        presupuestosHijos = new ArrayList<Presupuesto>();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param cuentaFinal
     * @param descripcion
     * @param fechaLimite
     * @param notas
     * @param porcentajeAviso
     */
    public Presupuesto(String codigo, Boolean cuentaFinal, String descripcion,
            Date fechaLimite, String notas, BigDecimal porcentajeAviso) {
        this.codigo = codigo;
        this.cuentaFinal = cuentaFinal;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.notas = notas;
        this.porcentajeAviso = porcentajeAviso;
        presupuestosHijos = new ArrayList<Presupuesto>();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Presupuesto.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Presupuesto.ID_SEQUENCE_NAME)
    @Column(name = Presupuesto.COLUMN_NAME_ID)
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
    @Column(name = Presupuesto.COLUMN_NAME_DESCRIPCION, length = 255)
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
     * @return the codigo
     */
    @Column(name = Presupuesto.COLUMN_NAME_CODIGO, length = 20)
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
     * @return the porcentajeAviso
     */
    @Column(name = Presupuesto.COLUMN_NAME_PORCENTAJE_AVISO)
    public BigDecimal getPorcentajeAviso() {
        return porcentajeAviso;
    }

    /**
     * @param porcentajeAviso
     *            the porcentajeAviso to set
     */
    public void setPorcentajeAviso(BigDecimal porcentajeAviso) {
        this.porcentajeAviso = porcentajeAviso;
    }

    /**
     * @return the fechaLimite
     */
    @Column(name = Presupuesto.COLUMN_NAME_FECHA_LIMITE)
    public Date getFechaLimite() {
        return fechaLimite;
    }

    /**
     * @param fechaLimite
     *            the fechaLimite to set
     */
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * @return the notas
     */
    @Column(name = Presupuesto.COLUMN_NAME_NOTAS, length = 400)
    public String getNotas() {
        return notas;
    }

    /**
     * @param notas
     *            the notas to set
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * @return the cuentaFinal
     */
    @Column(name = Presupuesto.COLUMN_NAME_CUENTA_FINAL)
    public Boolean getCuentaFinal() {
        return cuentaFinal;
    }

    /**
     * @param cuentaFinal
     *            the cuentaFinal to set
     */
    public void setCuentaFinal(Boolean cuentaFinal) {
        this.cuentaFinal = cuentaFinal;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = Presupuesto.COLUMN_NAME_BIBLIOTECA_FK)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_PRE")
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

    /**
     * @return the presupuestoPadre
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.Presupuesto.class)
    @JoinColumn(name = Presupuesto.COLUMN_NAME_PRESUPUESTO_PADRE_FK)
    public Presupuesto getPresupuestoPadre() {
        return presupuestoPadre;
    }

    /**
     * @param presupuestoPadre
     *            the presupuestoPadre to set
     */
    public void setPresupuestoPadre(Presupuesto presupuestoPadre) {
        this.presupuestoPadre = presupuestoPadre;
    }

    /**
     * @return the presupuestosHijos
     */
    @OneToMany(mappedBy = Presupuesto.PRESUPUESTOPADRE)
    public List<Presupuesto> getPresupuestosHijos() {
        return presupuestosHijos;
    }

    /**
     * @param presupuestosHijos
     *            the presupuestosHijos to set
     */
    public void setPresupuestosHijos(List<Presupuesto> presupuestosHijos) {
        this.presupuestosHijos = presupuestosHijos;
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
                + ((cuentaFinal == null) ? 0 : cuentaFinal.hashCode());
        result = prime * result
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result
                + ((fechaLimite == null) ? 0 : fechaLimite.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((notas == null) ? 0 : notas.hashCode());
        result = prime * result
                + ((porcentajeAviso == null) ? 0 : porcentajeAviso.hashCode());
        result = prime
                * result
                + ((presupuestoPadre == null) ? 0 : presupuestoPadre.hashCode());
        result = prime
                * result
                + ((presupuestosHijos == null) ? 0 : presupuestosHijos
                        .hashCode());
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
        if (!(obj instanceof Presupuesto)) {
            return false;
        }
        final Presupuesto other = (Presupuesto) obj;
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
        if (cuentaFinal == null) {
            if (other.cuentaFinal != null) {
                return false;
            }
        } else if (!cuentaFinal.equals(other.cuentaFinal)) {
            return false;
        }
        if (descripcion == null) {
            if (other.descripcion != null) {
                return false;
            }
        } else if (!descripcion.equals(other.descripcion)) {
            return false;
        }
        if (fechaLimite == null) {
            if (other.fechaLimite != null) {
                return false;
            }
        } else if (!fechaLimite.equals(other.fechaLimite)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (notas == null) {
            if (other.notas != null) {
                return false;
            }
        } else if (!notas.equals(other.notas)) {
            return false;
        }
        if (porcentajeAviso == null) {
            if (other.porcentajeAviso != null) {
                return false;
            }
        } else if (!porcentajeAviso.equals(other.porcentajeAviso)) {
            return false;
        }
        if (presupuestoPadre == null) {
            if (other.presupuestoPadre != null) {
                return false;
            }
        } else if (!presupuestoPadre.equals(other.presupuestoPadre)) {
            return false;
        }
        if (presupuestosHijos == null) {
            if (other.presupuestosHijos != null) {
                return false;
            }
        } else if (!presupuestosHijos.equals(other.presupuestosHijos)) {
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
        return new ToStringBuilder(this).append(Presupuesto.COLUMN_NAME_ID, id)
                .append(Presupuesto.COLUMN_NAME_DESCRIPCION, descripcion)
                .append(Presupuesto.COLUMN_NAME_CODIGO, codigo).append(
                        Presupuesto.COLUMN_NAME_FECHA_LIMITE, fechaLimite)
                .append(Presupuesto.COLUMN_NAME_NOTAS, notas).append(
                        Presupuesto.COLUMN_NAME_PORCENTAJE_AVISO,
                        porcentajeAviso).append(
                        Presupuesto.COLUMN_NAME_CUENTA_FINAL, cuentaFinal)
                .toString();
    }

}