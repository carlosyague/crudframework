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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un DetalleFactura
 *
 * @author jcdiaz
 */
@Entity(name = DetalleFactura.ENTITY_NAME)
@Table(name = DetalleFactura.TABLE_NAME)
public class DetalleFactura extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Factura necesita un Serial
     * Version UID
     */
    private static final long     serialVersionUID                      = 551248395788067945L;

    public static final String    ENTITY_NAME                           = "org.librae.adquisicion.model.DetalleFactura";
    public static final String    TABLE_NAME                            = "ADQ_DETALLES_FACTURAS";
    private static final String   ID_GENERATOR_NAME                     = "generator_adq_detalles_facturas";
    private static final String   ID_SEQUENCE_NAME                      = "SEQ_ADQ_DETALLES_FACTURAS";
    public static final String    COLUMN_NAME_ID                        = "X_DETALLE_FACTURA";
    public static final String    COLUMN_NAME_IMPORTE_REAL              = "I_IMPORTE_REAL";
    public static final String    COLUMN_NAME_DESCUENTO                 = "P_DESCUENTO";
    public static final String    COLUMN_NAME_IVA                       = "P_IVA";
    public static final String    COLUMN_NAME_EJEMPLARES                = "N_EJEMPLARES";
    public static final String    COLUMN_NAME_FECHA_DETALLE             = "F_FECHA_DETALLE";
    public static final String    COLUMN_NAME_FACTURA_FK                = "FAC_X_FACTURA";
    public static final String    COLUMN_NAME_TIPO_OPERACION_FK         = "TIP_X_TIPO_OPERACION_FACTURA";
    public static final String    COLUMN_NAME_PARTIDA_PRESUPUESTARIA_FK = "PAR_X_PARTIDA";
    public static final String    COLUMN_NAME_DETALLE_PEDIDO_FK         = "DET_X_DETALLE_PEDIDO";
    public static final String    FACTURA                               = "factura";

    /**
     * clave primaria
     */
    private Long                  id;

    /**
     * valor del importe del detalle
     */
    private BigDecimal            importeReal;

    /**
     * porcentaje de descuento
     */
    private BigDecimal            descuento;

    /**
     * iva del detalle
     */
    private BigDecimal            iva;

    /**
     * cantidad de ejemplares
     */
    private Integer               ejemplares;

    /**
     * fecha que se añade el detalle
     */
    private Date                  fechaDetalle;

    /**
     * referencia a la factura
     */
    private Factura               factura;

    /**
     * referencia al tipo de operación
     */
    private TipoOperacion         tipoOperacion;

    /**
     * referencia a la partida asociada
     */
    private PartidaPresupuestaria partida;

    /**
     * referencia al detalle de pedido asociado
     */
    // FIXME DetalleFactura -> DetallePedido
    // private DetallePedido detallePedido;
    /**
     * Constructor sin parámetros
     */
    protected DetalleFactura() {
    }

    /**
     * Constructor con parámetros
     *
     * @param descuento
     * @param ejemplares
     * @param factura
     * @param fechaDetalle
     * @param importeReal
     * @param iva
     */
    public DetalleFactura(BigDecimal descuento, Integer ejemplares,
            Factura factura, Date fechaDetalle, BigDecimal importeReal,
            BigDecimal iva) {
        this.descuento = descuento;
        this.ejemplares = ejemplares;
        this.fechaDetalle = fechaDetalle;
        this.importeReal = importeReal;
        this.iva = iva;
    }

    /*
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = DetalleFactura.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = DetalleFactura.ID_SEQUENCE_NAME)
    @Column(name = DetalleFactura.COLUMN_NAME_ID)
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
     * @return the importeReal
     */
    @Column(name = DetalleFactura.COLUMN_NAME_IMPORTE_REAL)
    public BigDecimal getImporteReal() {
        return importeReal;
    }

    /**
     * @param importeReal
     *            the importeReal to set
     */
    public void setImporteReal(BigDecimal importeReal) {
        this.importeReal = importeReal;
    }

    /**
     * @return the descuento
     */
    @Column(name = DetalleFactura.COLUMN_NAME_DESCUENTO)
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
     * @return the iva
     */
    @Column(name = DetalleFactura.COLUMN_NAME_IVA)
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva
     *            the iva to set
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * @return the ejemplares
     */
    @Column(name = DetalleFactura.COLUMN_NAME_EJEMPLARES)
    public Integer getEjemplares() {
        return ejemplares;
    }

    /**
     * @param ejemplares
     *            the ejemplares to set
     */
    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    /**
     * @return the fechaDetalle
     */
    @Column(name = DetalleFactura.COLUMN_NAME_FECHA_DETALLE)
    public Date getFechaDetalle() {
        return fechaDetalle;
    }

    /**
     * @param fechaDetalle
     *            the fechaDetalle to set
     */
    public void setFechaDetalle(Date fechaDetalle) {
        this.fechaDetalle = fechaDetalle;
    }

    /**
     * @return the factura
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.Factura.class)
    @JoinColumn(name = DetalleFactura.COLUMN_NAME_FACTURA_FK)
    public Factura getFactura() {
        return factura;
    }

    /**
     * @param factura
     *            the factura to set
     */
    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    /**
     * @return the tipoOperacion
     */
    @OneToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.TipoOperacion.class)
    @JoinColumn(name = DetalleFactura.COLUMN_NAME_TIPO_OPERACION_FK)
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * @param tipoOperacion
     *            the tipoOperacion to set
     */
    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * @return the partida
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.PartidaPresupuestaria.class)
    @JoinColumn(name = COLUMN_NAME_PARTIDA_PRESUPUESTARIA_FK)
    public PartidaPresupuestaria getPartida() {
        return partida;
    }

    /**
     * @param partida
     *            the partida to set
     */
    public void setPartida(PartidaPresupuestaria partida) {
        this.partida = partida;
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
                + ((descuento == null) ? 0 : descuento.hashCode());
        result = prime * result
                + ((ejemplares == null) ? 0 : ejemplares.hashCode());
        result = prime * result + ((factura == null) ? 0 : factura.hashCode());
        result = prime * result
                + ((fechaDetalle == null) ? 0 : fechaDetalle.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((importeReal == null) ? 0 : importeReal.hashCode());
        result = prime * result + ((iva == null) ? 0 : iva.hashCode());
        result = prime * result + ((partida == null) ? 0 : partida.hashCode());
        result = prime * result
                + ((tipoOperacion == null) ? 0 : tipoOperacion.hashCode());
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
        if (!(obj instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) obj;
        if (descuento == null) {
            if (other.descuento != null) {
                return false;
            }
        } else if (!descuento.equals(other.descuento)) {
            return false;
        }
        if (ejemplares == null) {
            if (other.ejemplares != null) {
                return false;
            }
        } else if (!ejemplares.equals(other.ejemplares)) {
            return false;
        }
        if (factura == null) {
            if (other.factura != null) {
                return false;
            }
        } else if (!factura.equals(other.factura)) {
            return false;
        }
        if (fechaDetalle == null) {
            if (other.fechaDetalle != null) {
                return false;
            }
        } else if (!fechaDetalle.equals(other.fechaDetalle)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (importeReal == null) {
            if (other.importeReal != null) {
                return false;
            }
        } else if (!importeReal.equals(other.importeReal)) {
            return false;
        }
        if (iva == null) {
            if (other.iva != null) {
                return false;
            }
        } else if (!iva.equals(other.iva)) {
            return false;
        }
        if (partida == null) {
            if (other.partida != null) {
                return false;
            }
        } else if (!partida.equals(other.partida)) {
            return false;
        }
        if (tipoOperacion == null) {
            if (other.tipoOperacion != null) {
                return false;
            }
        } else if (!tipoOperacion.equals(other.tipoOperacion)) {
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
        return new ToStringBuilder(this).append(DetalleFactura.COLUMN_NAME_ID,
                this.id).append(DetalleFactura.COLUMN_NAME_IMPORTE_REAL,
                this.importeReal).append(DetalleFactura.COLUMN_NAME_DESCUENTO,
                this.descuento)
                .append(DetalleFactura.COLUMN_NAME_IVA, this.iva).append(
                        DetalleFactura.COLUMN_NAME_EJEMPLARES, this.ejemplares)
                .append(DetalleFactura.COLUMN_NAME_FECHA_DETALLE,
                        this.fechaDetalle).toString();
    }
}