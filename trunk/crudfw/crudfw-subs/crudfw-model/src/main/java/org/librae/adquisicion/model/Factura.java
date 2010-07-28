package org.librae.adquisicion.model;

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
 * Bean para almacenar un Factura
 *
 * @author jcdiaz
 */
@Entity(name = Factura.ENTITY_NAME)
@Table(name = Factura.TABLE_NAME)
public class Factura extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Factura necesita un Serial
     * Version UID
     */
    private static final long     serialVersionUID                      = 551248395788067945L;

    public static final String    ENTITY_NAME                           = "org.librae.adquisicion.model.Factura";
    public static final String    TABLE_NAME                            = "ADQ_FACTURAS";
    private static final String   ID_GENERATOR_NAME                     = "generator_adq_facturas";
    private static final String   ID_SEQUENCE_NAME                      = "SEQ_ADQ_FACTURAS";
    public static final String    COLUMN_NAME_ID                        = "X_FACTURA";
    public static final String    COLUMN_NAME_NUMERO_FACTURA            = "N_NUMERO_FACTURA";
    public static final String    COLUMN_NAME_FECHA_ALTA                = "F_FECHA_ALTA";
    public static final String    COLUMN_NAME_FECHA_PAGO                = "F_FECHA_PAGO";
    public static final String    COLUMN_NAME_NOTA                      = "T_NOTAS";
    public static final String    COLUMN_NAME_ESTADO                    = "T_ESTADO";
    public static final String    COLUMN_NAME_BIBLIOTECA_FK             = "BIB_X_BIBLIOTECA";
    public static final String    COLUMN_NAME_PROVEEDOR_FK              = "PRO_X_PROVEEDOR";
    public static final String    COLUMN_NAME_DIVISA_FK                 = "DIV_X_DIVISA";
    public static final String    COLUMN_NAME_PARTIDA_PRESUPUESTARIA_FK = "PAR_X_PARTIDA";

    public static final String    PROPERTY_NAME_ID                        = "id";
    public static final String    PROPERTY_NAME_NUMERO_FACTURA            = "numeroFactura";
    public static final String    PROPERTY_NAME_FECHA_ALTA                = "fechaAlta";
    public static final String    PROPERTY_NAME_FECHA_PAGO                = "fechaPago";
    public static final String    PROPERTY_NAME_NOTA                      = "notas";
    public static final String    PROPERTY_NAME_ESTADO                    = "estado";
    public static final String    PROPERTY_NAME_BIBLIOTECA_FK             = "biblioteca";
    public static final String    PROPERTY_NAME_DIVISA_FK                 = "divisa";
    public static final String    PROPERTY_NAME_PARTIDA_PRESUPUESTARIA_FK = "partidaPresupuestaria";
    public static final String    PROPERTY_NAME_PROVEEDOR_FK 			= "proveedor";

    /**
     * clave primaria
     */
    private Long                  id;

    /**
     * numero de la factura
     */
    private String                numeroFactura;

    /**
     * fecha de alta de la factura
     */
    private Date                  fechaAlta;

    /**
     * fecha en que se ha pagado
     */
    private Date                  fechaPago;

    /**
     * notas de la factura
     */
    private String                nota;

    /**
     * estado de la factura
     */
    private String                estado;

    /**
     * referencia a la biblioteca de la factura
     */
    private Biblioteca            biblioteca;

    /**
     * referencia al proveedor asociado
     */
    private Proveedor             proveedor;

    /**
     * referencia a la divisa de la factura
     */
    private Divisa                divisa;

    /**
     * referencia al listado de detalles de la factura
     */
    private List<DetalleFactura>  detallesFactura;

    /**
     * referencia a la partida presupuestaria de la factura
     */
    private PartidaPresupuestaria partida;

    /**
     * Constructor sin parámetros
     */
    protected Factura() {
        detallesFactura = new ArrayList<DetalleFactura>();
    }

    /**
     * Constructor con parámetros
     *
     * @param estado
     * @param fechaAlta
     * @param fechaPago
     * @param notas
     * @param numeroFactura
     */
    public Factura(String estado, Date fechaAlta, Date fechaPago, String nota,
            String numeroFactura) {
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.fechaPago = fechaPago;
        this.nota = nota;
        this.numeroFactura = numeroFactura;
        detallesFactura = new ArrayList<DetalleFactura>();
    }

    /*
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Factura.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Factura.ID_SEQUENCE_NAME)
    @Column(name = Factura.COLUMN_NAME_ID)
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
     * @return the numeroFactura
     */
    @Column(name = Factura.COLUMN_NAME_NUMERO_FACTURA, length = 25)
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * @param numeroFactura
     *            the numeroFactura to set
     */
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * @return the fechaAlta
     */
    @Column(name = Factura.COLUMN_NAME_FECHA_ALTA)
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta
     *            the fechaAlta to set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the fechaPago
     */
    @Column(name = Factura.COLUMN_NAME_FECHA_PAGO)
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago
     *            the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the nota
     */
    @Column(name = Factura.COLUMN_NAME_NOTA, length = 400)
    public String getNota() {
        return nota;
    }

    /**
     * @param notas
     *            the notas to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

    /**
     * @return the estado
     */
    @Column(name = Factura.COLUMN_NAME_ESTADO, length = 40)
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the proveedor
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.Proveedor.class)
    @JoinColumn(name = Factura.COLUMN_NAME_PROVEEDOR_FK)
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

    /**
     * @return the divisa
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.Divisa.class)
    @JoinColumn(name = Factura.COLUMN_NAME_DIVISA_FK)
    public Divisa getDivisa() {
        return divisa;
    }

    /**
     * @param divisa
     *            the divisa to set
     */
    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = Divisa.COLUMN_NAME_BIBLIOTECA_FK)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_FAC")
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
     * @return the detallesFactura
     */
    @OneToMany(mappedBy = DetalleFactura.FACTURA, cascade = { CascadeType.ALL })
    public List<DetalleFactura> getDetallesFactura() {
        return detallesFactura;
    }

    /**
     * @param detallesFactura
     *            the detallesFactura to set
     */
    public void setDetallesFactura(List<DetalleFactura> detallesFactura) {
        this.detallesFactura = detallesFactura;
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
                + ((biblioteca == null) ? 0 : biblioteca.hashCode());
        result = prime * result
                + ((detallesFactura == null) ? 0 : detallesFactura.hashCode());
        result = prime * result + ((divisa == null) ? 0 : divisa.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result
                + ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
        result = prime * result
                + ((fechaPago == null) ? 0 : fechaPago.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nota == null) ? 0 : nota.hashCode());
        result = prime * result
                + ((numeroFactura == null) ? 0 : numeroFactura.hashCode());
        result = prime * result + ((partida == null) ? 0 : partida.hashCode());
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
        if (!(obj instanceof Factura)) {
            return false;
        }
        final Factura other = (Factura) obj;
        if (biblioteca == null) {
            if (other.biblioteca != null) {
                return false;
            }
        } else if (!biblioteca.equals(other.biblioteca)) {
            return false;
        }
        if (detallesFactura == null) {
            if (other.detallesFactura != null) {
                return false;
            }
        } else if (!detallesFactura.equals(other.detallesFactura)) {
            return false;
        }
        if (divisa == null) {
            if (other.divisa != null) {
                return false;
            }
        } else if (!divisa.equals(other.divisa)) {
            return false;
        }
        if (estado == null) {
            if (other.estado != null) {
                return false;
            }
        } else if (!estado.equals(other.estado)) {
            return false;
        }
        if (fechaAlta == null) {
            if (other.fechaAlta != null) {
                return false;
            }
        } else if (!fechaAlta.equals(other.fechaAlta)) {
            return false;
        }
        if (fechaPago == null) {
            if (other.fechaPago != null) {
                return false;
            }
        } else if (!fechaPago.equals(other.fechaPago)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (nota == null) {
            if (other.nota != null) {
                return false;
            }
        } else if (!nota.equals(other.nota)) {
            return false;
        }
        if (numeroFactura == null) {
            if (other.numeroFactura != null) {
                return false;
            }
        } else if (!numeroFactura.equals(other.numeroFactura)) {
            return false;
        }
        if (partida == null) {
            if (other.partida != null) {
                return false;
            }
        } else if (!partida.equals(other.partida)) {
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
        return new ToStringBuilder(this).append(Factura.COLUMN_NAME_ID, id)
                .append(Factura.COLUMN_NAME_NUMERO_FACTURA, numeroFactura)
                .append(Factura.COLUMN_NAME_FECHA_ALTA, fechaAlta).append(
                        Factura.COLUMN_NAME_FECHA_PAGO, fechaPago).append(
                        Factura.COLUMN_NAME_NOTA, nota).append(
                        Factura.COLUMN_NAME_ESTADO, estado).toString();
    }
}