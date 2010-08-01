package org.librae.adquisicion.model;

import java.math.BigDecimal;

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
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un ProveedorBiblioteca
 *
 * @author jcdiaz
 */
@Entity(name = ProveedorBiblioteca.ENTITY_NAME)
@Table(name = ProveedorBiblioteca.TABLE_NAME)
public class ProveedorBiblioteca extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Proveedor necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID               = -7903197559835954176L;

    public static final String  ENTITY_NAME                    = "org.librae.adquisicion.model.ProveedorBiblioteca";
    public static final String  TABLE_NAME                     = "ADQ_PROVEEDORES_BIBLIOTECAS";
    private static final String ID_GENERATOR_NAME              = "generator_adq_proveedores_bibliotecas";
    private static final String ID_SEQUENCE_NAME               = "SEQ_ADQ_PROVEED_BIBLIOTECAS";
    public static final String  COLUMN_NAME_ID                 = "X_PROVEEDOR_BIBLIOTECA";
    public static final String  COLUMN_NAME_DIAS_RECLAMACION   = "N_DIAS_DE_RECLAMACION";
    public static final String  COLUMN_NAME_BLOQUEADO          = "L_BLOQUEADO";
    public static final String  COLUMN_NAME_DESCUENTO          = "P_DESCUENTO";
    public static final String  COLUMN_NAME_NOTAS              = "T_NOTAS";
    public static final String  COLUMN_NAME_PROVEEDOR_FK       = "PRO_X_PROVEEDOR";
    public static final String  COLUMN_NAME_BIBLIOTECA_FK      = "BIB_X_BIBLIOTECA";

    public static final String  PROPERTY_NAME_ID               = "id";
    public static final String  PROPERTY_NAME_DIAS_RECLAMACION = "diasDeReclamacion";
    public static final String  PROPERTY_NAME_BLOQUEADO        = "bloqueado";
    public static final String  PROPERTY_NAME_DESCUENTO        = "descuento";
    public static final String  PROPERTY_NAME_NOTAS            = "notas";
    public static final String  PROPERTY_NAME_PROVEEDOR_FK     = "proveedor";
    public static final String  PROPERTY_NAME_BIBLIOTECA_FK    = "biblioteca";

    /**
     * clave principal
     */
    private Long                id;

    /**
     * valor de la cantidad de dias para realizar una reclamación de un pedido
     */
    private Integer             diasDeReclamacion;

    /**
     * indica si esta bloqueado o no
     */
    private Boolean             bloqueado;

    /**
     * porcentaje de descuento
     */
    private BigDecimal          descuento;

    /**
     * notas para el proveedor
     */
    private String              notas;

    /**
     * referencia al prooveedir de la biblioteca
     */
    private Proveedor           proveedor;

    /**
     * referencia a la biblioteca de la desiderata
     */
    private Biblioteca          biblioteca;

    /**
     * Constructor sin parametros
     */
    protected ProveedorBiblioteca() {
    }

    /**
     * Constructor con parámetros
     *
     * @param bloqueado
     * @param descuento
     * @param diasDeReclamacion
     * @param notas
     */
    public ProveedorBiblioteca(Boolean bloqueado, BigDecimal descuento,
            Integer diasDeReclamacion, String notas, Biblioteca biblioteca) {
        this.bloqueado = bloqueado;
        this.descuento = descuento;
        this.diasDeReclamacion = diasDeReclamacion;
        this.notas = notas;
        this.biblioteca = biblioteca;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ProveedorBiblioteca.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ProveedorBiblioteca.ID_SEQUENCE_NAME)
    @Column(name = ProveedorBiblioteca.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * /**
     *
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the diasDeReclamacion
     */
    @Column(name = ProveedorBiblioteca.COLUMN_NAME_DIAS_RECLAMACION)
    public Integer getDiasDeReclamacion() {
        return diasDeReclamacion;
    }

    /**
     * @param diasDeReclamacion
     *            the diasDeReclamacion to set
     */
    public void setDiasDeReclamacion(Integer diasDeReclamacion) {
        this.diasDeReclamacion = diasDeReclamacion;
    }

    /**
     * @return the bloqueado
     */
    @Column(name = ProveedorBiblioteca.COLUMN_NAME_BLOQUEADO)
    public Boolean getBloqueado() {
        return bloqueado;
    }

    /**
     * @param bloqueado
     *            the bloqueado to set
     */
    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    /**
     * @return the descuento
     */
    @Column(name = ProveedorBiblioteca.COLUMN_NAME_DESCUENTO)
    public BigDecimal getDescuento() {
        return descuento;
    }

    /**
     * @param descuento
     *            the descuento to set
     */
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the notas
     */
    @Column(name = ProveedorBiblioteca.COLUMN_NAME_NOTAS, length = 400)
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
     * @return the proveedor
     */
    @OneToOne(mappedBy = Proveedor.PROPERTY_NAME_PROVEEDOR_BIBLIOTECA_FK)
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor
     *            the proveedor to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
                + ((bloqueado == null) ? 0 : bloqueado.hashCode());
        result = prime * result
                + ((descuento == null) ? 0 : descuento.hashCode());
        result = prime
                * result
                + ((diasDeReclamacion == null) ? 0 : diasDeReclamacion
                        .hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((notas == null) ? 0 : notas.hashCode());
        result = prime * result
                + ((proveedor == null) ? 0 : proveedor.hashCode());
        result = prime * result
                + ((biblioteca == null) ? 0 : biblioteca.hashCode());
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
        if (!(obj instanceof ProveedorBiblioteca)) {
            return false;
        }
        ProveedorBiblioteca other = (ProveedorBiblioteca) obj;
        if (biblioteca == null) {
            if (other.biblioteca != null) {
                return false;
            }
        } else if (!biblioteca.equals(other.biblioteca)) {
            return false;
        }
        if (descuento == null) {
            if (other.descuento != null) {
                return false;
            }
        } else if (!descuento.equals(other.descuento)) {
            return false;
        }
        if (diasDeReclamacion == null) {
            if (other.diasDeReclamacion != null) {
                return false;
            }
        } else if (!diasDeReclamacion.equals(other.diasDeReclamacion)) {
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
        if (bloqueado == null) {
            if (other.bloqueado != null) {
                return false;
            }
        } else if (!bloqueado.equals(other.bloqueado)) {
            return false;
        }
        if (proveedor == null) {
            if (other.proveedor != null) {
                return false;
            }
        } else if (!proveedor.equals(other.proveedor)) {
            return false;
        }
        return true;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = ProveedorBiblioteca.COLUMN_NAME_BIBLIOTECA_FK)
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
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this).append(
                ProveedorBiblioteca.COLUMN_NAME_ID, this.id).append(
                ProveedorBiblioteca.COLUMN_NAME_DIAS_RECLAMACION,
                this.diasDeReclamacion).append(
                ProveedorBiblioteca.COLUMN_NAME_DESCUENTO, this.descuento)
                .append(ProveedorBiblioteca.COLUMN_NAME_NOTAS, this.notas)
                .append(ProveedorBiblioteca.COLUMN_NAME_BLOQUEADO,
                        this.bloqueado);
        if (getBiblioteca() != null) {
            sb.append(ProveedorBiblioteca.COLUMN_NAME_BIBLIOTECA_FK, getBiblioteca()
                    .getId());
        }
        return sb.toString();
    }

}