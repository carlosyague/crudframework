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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un Adquisicion
 *
 * @author jcdiaz
 */
@Entity(name = Pedido.ENTITY_NAME)
@Table(name = Pedido.TABLE_NAME)
public class Pedido extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Desiderata necesita un Serial
     * Version UID
     */
    private static final long     serialVersionUID                      = -3346671609231452538L;

    public static final String    ENTITY_NAME                           = "org.librae.adquisicion.model.Pedido";
    public static final String    TABLE_NAME                            = "ADQ_PEDIDOS";
    private static final String   ID_GENERATOR_NAME                     = "generator_adq_pedidos";
    private static final String   ID_SEQUENCE_NAME                      = "SEQ_ADQ_PEDIDOS";
    public static final String    COLUMN_NAME_ID                        = "X_PEDIDO";
    public static final String    COLUMN_NAME_NUMERO_PEDIDO             = "N_NUMERO_PEDIDO";
    public static final String    COLUMN_NAME_ESTADO                    = "T_ESTADO";
    public static final String    COLUMN_NAME_REFERENCIA                = "T_REFERENCIA";
    public static final String    COLUMN_NAME_FECHA_PEDIDO              = "F_FECHA_PEDIDO";
    public static final String    COLUMN_NAME_FECHA_PREVISTA            = "F_FECHA_PREVISTA";
    public static final String    COLUMN_NAME_FECHA_RECLAMACION         = "F_FECHA_RECLAMACION";
    public static final String    COLUMN_NAME_RECLAMADO                 = "L_RECLAMADO";
    public static final String    COLUMN_NAME_NOTAS                     = "T_NOTAS";
    public static final String    COLUMN_NAME_NOTAS_PROVEEDOR           = "T_NOTAS_PROVEEDOR";
    public static final String    COLUMN_NAME_BIBLIOTECA_FK             = "BIB_X_BIBLIOTECA";
    public static final String    COLUMN_NAME_PROVEEDOR_FK              = "PRO_X_PROVEEDOR";
    public static final String    COLUMN_NAME_PARTIDA_PRESUPUESTARIA_FK = "PAR_X_PARTIDA_PRESUPUESTARIA";
    public static final String    COLUMN_NAME_DIVISA_FK                 = "DIV_X_DIVISA";
    public static final String    COLUMN_NAME_TIPO_PEDIDO               = "TIP_X_TIPO_PEDIDO";

    public static final String    PROPERTY_NAME_PROVEEDOR_FK            = "proveedor";
    public static final String    PROPERTY_NAME_RECLAMACIONES           = "reclamaciones";
    public static final String    PROPERTY_NAME_DEVOLUCIONES            = "devoluciones";
    public static final String    PROPERTY_NAME_TIPO_PEDIDO             = "tipoPedido";
    public static final String    PROPERTY_NAME_ID                      = "id";
    public static final String    PROPERTY_NAME_BIBLIOTECA              = "biblioteca";
    public static final String    PROPERTY_NAME_PROVEEDOR               = "proveedor";
    public static final String    PROPERTY_NAME_NUMERO_PEDIDO           = "numeroPedido";
    public static final String    PROPERTY_NAME_ESTADO                  = "estado";
    public static final String    PROPERTY_NAME_REFERENCIA              = "referencia";
    public static final String    PROPERTY_NAME_FECHA_PEDIDO            = "fechaPedido";
    public static final String    PROPERTY_NAME_FECHA_PREVISTA          = "fechaPrevista";
    public static final String    PROPERTY_NAME_FECHA_RECLAMACION       = "fechaReclamacion";
    public static final String    PROPERTY_NAME_RECLAMADO               = "reclamado";
    public static final String    PROPERTY_NAME_NOTAS                   = "notas";
    public static final String    PROPERTY_NAME_NOTAS_PROVEEDOR         = "notasProveedor";
    public static final String    PROPERTY_NAME_FECHA_DESIDERATAS       = "desideratas";
    public static final String    PROPERTY_NAME_FECHA_HISTORICO_ESTADOS = "historicoEstados";
    public static final String    PROPERTY_NAME_NOTAS_PARTIDA_PRESUPUESTARIA        = "partidaPresupuestaria";
    public static final String    PROPERTY_NAME_DIVISA       = "divisa";
    public static final String    PROPERTY_NAME_DETALLES_PEDIDO = "detallesPedido";


    /**
     * clave primaria
     */
    private Long                  id;

    /**
     * numero del pedido
     */
    private Integer               numeroPedido;

    /**
     * estado del pedido
     */
    private String                estado;

    /**
     *
     */
    private String                referencia;

    /**
     * fecha de alta del pedido
     */
    private Date                  fechaPedido;

    /**
     * fecha prevista de recepción
     */
    private Date                  fechaPrevista;

    /**
     * fecha prevista de recepción
     */
    private Date                  fechaReclamacion;

    /**
     *
     */
    private Boolean               reclamado;

    /**
     * notas del pedido
     */
    private String                notas;

    /**
     * notas para el proveedor del pedido
     */
    private String                notasProoveedor;

    /**
     *
     */
    private Biblioteca            biblioteca;

    /**
     * referencia al proveedor asociado
     */
    private Proveedor             proveedor;

    /**
     *
     */
    private List<Desiderata>      desideratas;

    /**
    *
    */
    private List<EstadoPedido>    historicoEstados;

    /**
     *
     */
    private PartidaPresupuestaria partidaPresupuestaria;

    /**
     * referencia a la divisa asociada
     */
    private Divisa                divisa;

    /**
     * referencia al tipo de pedido.
     */
    private TipoPedido            tipoPedido;

    /**
     * lista de reclamaciones.
     */
    private List<Reclamacion>     reclamaciones;

    /**
     * lista de devoluciones.
     */
    private List<Devolucion>      devoluciones;

    /**
     * lista de devoluciones.
     */
    private List<DetallePedido>   detallesPedido;

    /**
     * Contructor sin parámetros
     */
    protected Pedido() {
        desideratas = new ArrayList<Desiderata>();
        historicoEstados = new ArrayList<EstadoPedido>();
    }

    /**
     * Constructor con parámetros
     *
     * @param estado
     * @param fechaPedido
     * @param fechaPrevista
     * @param fechaReclamacion
     * @param notas
     * @param notsaProoveedor
     * @param numeroPedido
     * @param reclamado
     * @param referencia
     */
    public Pedido(String estado, Date fechaPedido, Date fechaPrevista,
            Date fechaReclamacion, String notas, String notsaProoveedor,
            Integer numeroPedido, Boolean reclamado, String referencia) {
        this.estado = estado;
        this.fechaPedido = fechaPedido;
        this.fechaPrevista = fechaPrevista;
        this.fechaReclamacion = fechaReclamacion;
        this.notas = notas;
        notasProoveedor = notsaProoveedor;
        this.numeroPedido = numeroPedido;
        this.reclamado = reclamado;
        this.referencia = referencia;
        desideratas = new ArrayList<Desiderata>();
        historicoEstados = new ArrayList<EstadoPedido>();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Pedido.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Pedido.ID_SEQUENCE_NAME)
    @Column(name = Pedido.COLUMN_NAME_ID)
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
     * @return the numeroPedido
     */
    @Column(name = Pedido.COLUMN_NAME_NUMERO_PEDIDO)
    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    /**
     * @param numeroPedido
     *            the numeroPedido to set
     */
    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    /**
     * @return the estado
     */
    @Column(name = Pedido.COLUMN_NAME_ESTADO, length = 40)
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
     * @return the fechaPedido
     */
    @Column(name = Pedido.COLUMN_NAME_FECHA_PEDIDO)
    public Date getFechaPedido() {
        return fechaPedido;
    }

    /**
     * @param fechaPedido
     *            the fechaPedido to set
     */
    @Column(name = Pedido.COLUMN_NAME_FECHA_PEDIDO)
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * @return the fechaPrevista
     */
    public Date getFechaPrevista() {
        return fechaPrevista;
    }

    /**
     * @param fechaPrevista
     *            the fechaPrevista to set
     */
    @Column(name = Pedido.COLUMN_NAME_FECHA_PREVISTA)
    public void setFechaPrevista(Date fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
    }

    /**
     * @return the fechaReclamacion
     */
    @Column(name = Pedido.COLUMN_NAME_FECHA_RECLAMACION)
    public Date getFechaReclamacion() {
        return fechaReclamacion;
    }

    /**
     * @param fechaReclamacion
     *            the fechaReclamacion to set
     */
    public void setFechaReclamacion(Date fechaReclamacion) {
        this.fechaReclamacion = fechaReclamacion;
    }

    /**
     * @return the referencia
     */
    @Column(name = Pedido.COLUMN_NAME_REFERENCIA, length = 25)
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia
     *            the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the reclamado
     */
    @Column(name = Pedido.COLUMN_NAME_RECLAMADO)
    public Boolean getReclamado() {
        return reclamado;
    }

    /**
     * @param reclamado
     *            the reclamado to set
     */
    public void setReclamado(Boolean reclamado) {
        this.reclamado = reclamado;
    }

    /**
     * @return the notas
     */
    @Column(name = Pedido.COLUMN_NAME_NOTAS, length = 400)
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
     * @return the notasProoveedor
     */
    @Column(name = Pedido.COLUMN_NAME_NOTAS_PROVEEDOR, length = 400)
    public String getNotasProoveedor() {
        return notasProoveedor;
    }

    /**
     * @param notasProoveedor
     *            the notasProoveedor to set
     */
    public void setNotasProoveedor(String notasProoveedor) {
        this.notasProoveedor = notasProoveedor;
    }

    /**
     * @return the detallesPedido
     */
    @OneToMany(mappedBy = DetallePedido.PROPERTY_NAME_PEDIDO, cascade = { CascadeType.ALL })
    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    /**
     * @param detallesPedido
     *            the detallesPedido to set
     */
    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = Pedido.COLUMN_NAME_BIBLIOTECA_FK)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_PED")
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
     * @return the proveedor
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.Proveedor.class)
    @JoinColumn(name = Pedido.COLUMN_NAME_PROVEEDOR_FK)
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
     * @return the desideratas
     */
    @OneToMany(mappedBy = Desiderata.PEDIDO)
    public List<Desiderata> getDesideratas() {
        return desideratas;
    }

    /**
     * @param desideratas
     *            the desideratas to set
     */
    public void setDesideratas(List<Desiderata> desideratas) {
        this.desideratas = desideratas;
    }

    /**
     * @return the historicoEstados
     */
    @OneToMany(mappedBy = EstadoPedido.PEDIDO)
    public List<EstadoPedido> getHistoricoEstados() {
        return historicoEstados;
    }

    /**
     * @param historicoEstados
     *            the historicoEstados to set
     */
    public void setHistoricoEstados(List<EstadoPedido> historicoEstados) {
        this.historicoEstados = historicoEstados;
    }

    /**
     * @return the partidaPresupuestaria
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.PartidaPresupuestaria.class)
    @JoinColumn(name = Pedido.COLUMN_NAME_PARTIDA_PRESUPUESTARIA_FK)
    public PartidaPresupuestaria getPartidaPresupuestaria() {
        return partidaPresupuestaria;
    }

    /**
     * @param partidaPresupuestaria
     *            the partidaPresupuestaria to set
     */
    public void setPartidaPresupuestaria(
            PartidaPresupuestaria partidaPresupuestaria) {
        this.partidaPresupuestaria = partidaPresupuestaria;
    }

    /**
     * @return the divisa
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.Divisa.class)
    @JoinColumn(name = Pedido.COLUMN_NAME_DIVISA_FK)
    public Divisa getDivisa() {
        return divisa;
    }

    /**
     * @return the tipoPedido
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.TipoPedido.class)
    @JoinColumn(name = Pedido.COLUMN_NAME_TIPO_PEDIDO)
    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    /**
     * @param tipoPedido
     *            the tipoPedido to set
     */
    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    /**
     * @param divisa
     *            the divisa to set
     */
    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

    /**
     * @return the reclamaciones
     */
    @OneToMany(mappedBy = Reclamacion.PROPERTY_NAME_PEDIDO, cascade = { CascadeType.ALL })
    public List<Reclamacion> getReclamaciones() {
        return reclamaciones;
    }

    /**
     * @param reclamaciones
     *            the reclamaciones to set
     */
    public void setReclamaciones(List<Reclamacion> reclamaciones) {
        this.reclamaciones = reclamaciones;
    }

    /**
     * @return the devoluciones
     */
    @OneToMany(mappedBy = Devolucion.PROPERTY_NAME_PEDIDO, cascade = { CascadeType.ALL })
    public List<Devolucion> getDevoluciones() {
        return devoluciones;
    }

    /**
     * @param devoluciones
     *            the devoluciones to set
     */
    public void setDevoluciones(List<Devolucion> devoluciones) {
        this.devoluciones = devoluciones;
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
                + ((desideratas == null) ? 0 : desideratas.hashCode());
        result = prime * result + ((divisa == null) ? 0 : divisa.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result
                + ((fechaPedido == null) ? 0 : fechaPedido.hashCode());
        result = prime * result
                + ((fechaPrevista == null) ? 0 : fechaPrevista.hashCode());
        result = prime
                * result
                + ((fechaReclamacion == null) ? 0 : fechaReclamacion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((notas == null) ? 0 : notas.hashCode());
        result = prime * result
                + ((notasProoveedor == null) ? 0 : notasProoveedor.hashCode());
        result = prime * result
                + ((numeroPedido == null) ? 0 : numeroPedido.hashCode());
        result = prime * result
                + ((reclamado == null) ? 0 : reclamado.hashCode());
        result = prime * result
                + ((referencia == null) ? 0 : referencia.hashCode());
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
        if (!(obj instanceof Pedido)) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (biblioteca == null) {
            if (other.biblioteca != null) {
                return false;
            }
        } else if (!biblioteca.equals(other.biblioteca)) {
            return false;
        }
        if (desideratas == null) {
            if (other.desideratas != null) {
                return false;
            }
        } else if (!desideratas.equals(other.desideratas)) {
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
        if (fechaPedido == null) {
            if (other.fechaPedido != null) {
                return false;
            }
        } else if (!fechaPedido.equals(other.fechaPedido)) {
            return false;
        }
        if (fechaPrevista == null) {
            if (other.fechaPrevista != null) {
                return false;
            }
        } else if (!fechaPrevista.equals(other.fechaPrevista)) {
            return false;
        }
        if (fechaReclamacion == null) {
            if (other.fechaReclamacion != null) {
                return false;
            }
        } else if (!fechaReclamacion.equals(other.fechaReclamacion)) {
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
        if (notasProoveedor == null) {
            if (other.notasProoveedor != null) {
                return false;
            }
        } else if (!notasProoveedor.equals(other.notasProoveedor)) {
            return false;
        }
        if (numeroPedido == null) {
            if (other.numeroPedido != null) {
                return false;
            }
        } else if (!numeroPedido.equals(other.numeroPedido)) {
            return false;
        }
        if (reclamado == null) {
            if (other.reclamado != null) {
                return false;
            }
        } else if (!reclamado.equals(other.reclamado)) {
            return false;
        }
        if (referencia == null) {
            if (other.referencia != null) {
                return false;
            }
        } else if (!referencia.equals(other.referencia)) {
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
        return new ToStringBuilder(this).append(Pedido.COLUMN_NAME_ID, id)
                .append(Pedido.COLUMN_NAME_NUMERO_PEDIDO, numeroPedido).append(
                        Pedido.COLUMN_NAME_ESTADO, estado).append(
                        Pedido.COLUMN_NAME_REFERENCIA, referencia).append(
                        Pedido.COLUMN_NAME_FECHA_PEDIDO, fechaPedido).append(
                        Pedido.COLUMN_NAME_FECHA_PREVISTA, fechaPrevista)
                .append(Pedido.COLUMN_NAME_FECHA_RECLAMACION, fechaReclamacion)
                .append(Pedido.COLUMN_NAME_RECLAMADO, reclamado).append(
                        Pedido.COLUMN_NAME_NOTAS, notas).append(
                        Pedido.COLUMN_NAME_NOTAS_PROVEEDOR, notasProoveedor)
                .toString();
    }

}
