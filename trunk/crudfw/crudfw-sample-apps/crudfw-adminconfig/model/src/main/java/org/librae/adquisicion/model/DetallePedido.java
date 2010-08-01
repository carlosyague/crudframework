/**
 *
 */
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.librae.catalogacion.model.Registro;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un DetallePedido
 *
 * @author jVillegas
 */
@Entity(name = DetallePedido.ENTITY_NAME)
@Table(name = DetallePedido.TABLE_NAME)
public class DetallePedido extends BaseObject {

    /**
     * serial version
     */
    private static final long     serialVersionUID              = -6497108095151667269L;

    public static final String    ENTITY_NAME                   = "org.librae.adquisicion.model.DetallePedido";
    public static final String    TABLE_NAME                    = "ADQ_DETALLES_PEDIDOS";
    private static final String   ID_GENERATOR_NAME             = "generator_adq_detalles_pedidos";
    private static final String   ID_SEQUENCE_NAME              = "SEQ_ADQ_DETALLES_PEDIDOS";
    public static final String    COLUMN_NAME_ID                = "X_DETALLE_PEDIDO";
    public static final String    COLUMN_NAME_DETALLE_FACTURA   = "DET_X_DETALLE_FACTURA";
    public static final String    COLUMN_NAME_FECHA             = "F_FECHA";
    public static final String    COLUMN_NAME_IMPORTE           = "I_IMPORTE";
    public static final String    COLUMN_NAME_PEDIDOS           = "N_PEDIDOS";
    public static final String    COLUMN_NAME_PEDIDO           = "PED_X_PEDIDOS";
    public static final String    COLUMN_NAME_CANCELADOS        = "N_CANCELADOS";
    public static final String    COLUMN_NAME_FACTURADOS        = "N_FACTURADOS";
    public static final String    COLUMN_NAME_RECIBIDOS         = "N_RECIBIDOS";
    public static final String    COLUMN_NAME_DESCUENTO         = "P_DESCUENTO";
    public static final String    COLUMN_NAME_IVA               = "P_IVA";
    public static final String    COLUMN_NAME_PARTIDA           = "PAR_X_PARTIDA";
    public static final String    COLUMN_NAME_REGISTRO          = "REG_X_REGISTRO";
    public static final String    COLUMN_NAME_NOTAS             = "T_NOTAS";
    public static final String    COLUMN_NAME_TIPO_PEDIDO       = "TIP_X_TIPO_PEDIDO";

    public static final String    PROPERTY_NAME_ID              = "id";
    public static final String    PROPERTY_NAME_DETALLE_FACTURA = "detalleFactura";
    public static final String    PROPERTY_NAME_FECHA           = "fecha";
    public static final String    PROPERTY_NAME_IMPORTE         = "importe";
    public static final String    PROPERTY_NAME_PEDIDOS         = "unidadesPedidas";
    public static final String    PROPERTY_NAME_CANCELADOS      = "unidadesCanceladas";
    public static final String    PROPERTY_NAME_FACTURADOS      = "unidadesFacturadas";
    public static final String    PROPERTY_NAME_RECIBIDOS       = "unidadesRecibidas";
    public static final String    PROPERTY_NAME_DESCUENTO       = "descuento";
    public static final String    PROPERTY_NAME_IVA             = "iva";
    public static final String    PROPERTY_NAME_PARTIDA         = "partida";
    public static final String    PROPERTY_NAME_REGISTRO        = "registro";
    public static final String    PROPERTY_NAME_NOTAS           = "notas";
    public static final String    PROPERTY_NAME_TIPO_PEDIDO     = "tipoPedido";
    public static final String    PROPERTY_NAME_PEDIDO     = "pedido";

    /**
     * clave primaria
     */
    private Long                  id;

    /**
     * unidadesSolicitadas.
     */
    private Integer               unidadesSolicitadas;

    /**
     * unidadesRecibidas.
     */
    private Integer               unidadesRecibidas;

    /**
     * unidadesCanceladas.
     */
    private Integer               unidadesCanceladas;

    /**
     * unidadesFacturadas.
     */
    private Integer               unidadesFacturadas;

    /**
     * importe.
     */
    private BigDecimal            importe;

    /**
     * notas.
     */
    private String                notas;


    /**
     * registro.
     */
    private Registro              registro;

    /**
     * partida.
     */
    private PartidaPresupuestaria partida;

    /**
     * detalleFactura.
     */
    private DetalleFactura        detalleFactura;

    /**
     * iva.
     */
    private BigDecimal            iva;

    /**
     * pedido.
     */
    private Pedido            pedido;

    /**
     * @return the id.
     */
    /*
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = DetallePedido.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = DetallePedido.ID_SEQUENCE_NAME)
    @Column(name = DetallePedido.COLUMN_NAME_ID)
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
     * @return the unidadesSolicitadas.
     */
    @Column(name = DetallePedido.COLUMN_NAME_PEDIDOS)
    public Integer getUnidadesSolicitadas() {
        return unidadesSolicitadas;
    }

    /**
     * @param unidadesSolicitadas
     *            the unidadesSolicitadas to set.
     */
    public void setUnidadesSolicitadas(Integer unidadesSolicitadas) {
        this.unidadesSolicitadas = unidadesSolicitadas;
    }

    /**
     * @return the unidadesRecibidas.
     */
    @Column(name = DetallePedido.COLUMN_NAME_RECIBIDOS)
    public Integer getUnidadesRecibidas() {
        return unidadesRecibidas;
    }

    /**
     * @param unidadesRecibidas
     *            the unidadesRecibidas to set.
     */
    public void setUnidadesRecibidas(Integer unidadesRecibidas) {
        this.unidadesRecibidas = unidadesRecibidas;
    }

    /**
     * @return the unidadesCanceladas.
     */
    @Column(name = DetallePedido.COLUMN_NAME_CANCELADOS)
    public Integer getUnidadesCanceladas() {
        return unidadesCanceladas;
    }

    /**
     * @param unidadesCanceladas
     *            the unidadesCanceladas to set.
     */
    public void setUnidadesCanceladas(Integer unidadesCanceladas) {
        this.unidadesCanceladas = unidadesCanceladas;
    }

    /**
     * @return the unidadesFacturadas.
     */
    @Column(name = DetallePedido.COLUMN_NAME_FACTURADOS)
    public Integer getUnidadesFacturadas() {
        return unidadesFacturadas;
    }

    /**
     * @param unidadesFacturadas
     *            the unidadesFacturadas to set.
     */
    public void setUnidadesFacturadas(Integer unidadesFacturadas) {
        this.unidadesFacturadas = unidadesFacturadas;
    }

    /**
     * @return the importe.
     */
    @Column(name = DetallePedido.COLUMN_NAME_IMPORTE)
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * @param importe
     *            the importe to set.
     */
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    /**
     * @return the notas.
     */
    @Column(name = DetallePedido.COLUMN_NAME_NOTAS, length = 400)
    public String getNotas() {
        return notas;
    }

    /**
     * @param notas
     *            the notas to set.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * @return the registro.
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.catalogacion.model.Registro.class)
    @JoinColumn(name = DetallePedido.COLUMN_NAME_REGISTRO)
    public Registro getRegistro() {
        return registro;
    }

    /**
     * @param registro
     *            the registro to set.
     */
    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    /**
     * @return the partida.
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.PartidaPresupuestaria.class)
    @JoinColumn(name = DetallePedido.COLUMN_NAME_PARTIDA)
    public PartidaPresupuestaria getPartida() {
        return partida;
    }

    /**
     * @param partida
     *            the partida to set.
     */
    public void setPartida(PartidaPresupuestaria partida) {
        this.partida = partida;
    }

    /**
     * @return the detalleFactura.
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.DetalleFactura.class)
    @JoinColumn(name = DetallePedido.COLUMN_NAME_DETALLE_FACTURA)
    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    /**
     * @param detalleFactura
     *            the detalleFactura to set.
     */
    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    /**
     * @return the iva.
     */
    @Column(name = DetallePedido.COLUMN_NAME_IVA)
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva
     *            the iva to set.
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * @return the pedido
     */
    @ManyToOne
    @JoinColumn(name = DetallePedido.COLUMN_NAME_PEDIDO)
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#hashCode()
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
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
