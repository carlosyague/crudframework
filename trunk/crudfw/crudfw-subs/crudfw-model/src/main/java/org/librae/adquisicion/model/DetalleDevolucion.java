/**
 *
 */
package org.librae.adquisicion.model;

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

import org.librae.common.model.BaseObject;

/**
 * @author jVillegas
 */
@Entity(name = DetalleDevolucion.ENTITY_NAME)
@Table(name = DetalleDevolucion.TABLE_NAME)
public class DetalleDevolucion extends BaseObject {

    /**
    *
    */
    private static final long   serialVersionUID             = -4232985212431657760L;

    public static final String  ENTITY_NAME                  = "org.librae.adquisicion.model.DetalleDevolucion";
    public static final String  TABLE_NAME                   = "ADQ_DETALLES_DEVOLUCIONES";
    private static final String ID_GENERATOR_NAME            = "generator_adq_detalles_devoluciones";
    private static final String ID_SEQUENCE_NAME             = "SEQ_ADQ_DETALLES_DEVOLUCIONES";
    public static final String  COLUMN_NAME_ID               = "X_DETALLE_DEVOLUCION";
    public static final String  COLUMN_NAME_DETALLE_PEDIDO   = "DET_X_DETALLE_PEDIDO";
    public static final String  COLUMN_NAME_DEVOLUCION       = "DEV_X_DEVOLUCION";
    public static final String  COLUMN_NAME_DEVUELTOS        = "N_DEVUELTOS";
    public static final String  COLUMN_NAME_RECIBIDOS        = "N_RECIBIDOS";

    public static final String  PROPERTY_NAME_ID             = "id";
    public static final String  PROPERTY_NAME_DETALLE_PEDIDO = "detallePedido";
    public static final String  PROPERTY_NAME_DEVOLUCION     = "devolucion";
    public static final String  PROPERTY_NAME_DEVUELTOS      = "devueltos";
    public static final String  PROPERTY_NAME_RECIBIDOS      = "recibidos";

    /**
     * clave primaria.
     */
    private Long                id;

    /**
     * detalle Pedido
     */
    private DetallePedido       detallePedido;

    /**
     * Devolucion.
     */
    private Devolucion          devolucion;

    /**
     * Devueltos.
     */
    private Integer             devueltos;

    /**
     * Recibidos.
     */
    private Integer             recibidos;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = DetalleDevolucion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = DetalleDevolucion.ID_SEQUENCE_NAME)
    @Column(name = DetalleDevolucion.COLUMN_NAME_ID)
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
     * @return the detallePedido
     */
    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    /**
     * @param detallePedido
     *            the detallePedido to set
     */
    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }

    /**
     * @return the devolucion
     */
    @ManyToOne
    @JoinColumn(name = DetalleDevolucion.COLUMN_NAME_DEVOLUCION)
    public Devolucion getDevolucion() {
        return devolucion;
    }

    /**
     * @param devolucion
     *            the devolucion to set
     */
    public void setDevolucion(Devolucion devolucion) {
        this.devolucion = devolucion;
    }

    /**
     * @return the devueltos
     */
    @Column(name = DetalleDevolucion.COLUMN_NAME_DEVUELTOS)
    public Integer getDevueltos() {
        return devueltos;
    }

    /**
     * @param devueltos
     *            the devueltos to set
     */
    public void setDevueltos(Integer devueltos) {
        this.devueltos = devueltos;
    }

    /**
     * @return the recibidos
     */
    @Column(name = DetalleDevolucion.COLUMN_NAME_RECIBIDOS)
    public Integer getRecibidos() {
        return recibidos;
    }

    /**
     * @param recibidos
     *            the recibidos to set
     */
    public void setRecibidos(Integer recibidos) {
        this.recibidos = recibidos;
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
