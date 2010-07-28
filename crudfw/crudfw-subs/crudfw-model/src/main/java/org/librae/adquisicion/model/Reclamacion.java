/**
 *
 */
package org.librae.adquisicion.model;

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

import org.librae.common.model.BaseObject;


/**
 * @author jVillegas
 */
@Entity(name = Reclamacion.ENTITY_NAME)
@Table(name = Reclamacion.TABLE_NAME)
public class Reclamacion extends BaseObject {

    /**
     * Serial version.
     */
    private static final long   serialVersionUID               = 5983642732356549933L;

    public static final String  ENTITY_NAME                    = "org.librae.adquisicion.model.Reclamacion";
    public static final String  TABLE_NAME                     = "ADQ_RECLAMACIONES";
    private static final String ID_GENERATOR_NAME              = "generator_adq_reclamaciones";
    private static final String ID_SEQUENCE_NAME               = "SEQ_ADQ_RECLAMACIONES";
    public static final String  COLUMN_NAME_ID                 = "X_RECLAMACION";
    public static final String  COLUMN_NAME_FECHA_RECIBIDO     = "F_FECHA_RECIBIDO";
    public static final String  COLUMN_NAME_FECHA_PEDIDO       = "F_FECHA_PEDIDO";
    public static final String  COLUMN_NAME_PEDIDO             = "PED_X_PEDIDO";
    public static final String  COLUMN_NAME_NOTAS              = "T_NOTAS";

    public static final String  PROPERTY_NAME_ID               = "id";
    public static final String  PROPERTY_NAME_FECHA_RECIBIDO   = "fechaRecibido";
    public static final String  PROPERTY_NAME_FECHA_PEDIDO     = "fechaPedido";
    public static final String  PROPERTY_NAME_PEDIDO           = "pedido";
    public static final String  PROPERTY_NAME_NOTAS            = "notas";
    public static final String  PROPERTY_NAME_DETALLES_RECLAMA = "detallesReclamacion";

    /**
     * clave primaria.
     */
    private Long                id;

    /**
     * fecha recibido.
     */
    private Date                fechaRecibido;

    /**
     * fecha pedido
     */
    private Date                fechaPedido;

    /**
     * pedido.
     */
    private Pedido              pedido;

    /**
     * notas.
     */
    private String              notas;

    /**
     * lista de detalles reclamacion.
     */
    private List<DetalleReclamacion>                detallesReclamacion;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Reclamacion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Reclamacion.ID_SEQUENCE_NAME)
    @Column(name = Reclamacion.COLUMN_NAME_ID)
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
     * @return the fechaRecibido
     */
    @Column(name = Reclamacion.COLUMN_NAME_FECHA_RECIBIDO)
    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    /**
     * @param fechaRecibido
     *            the fechaRecibido to set.
     */
    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    /**
     * @return the fechaPedido.
     */
    @Column(name = Reclamacion.COLUMN_NAME_FECHA_PEDIDO)
    public Date getFechaPedido() {
        return fechaPedido;
    }

    /**
     * @param fechaPedido
     *            the fechaPedido to set.
     */
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * @return the pedido.
     */
    @ManyToOne
    @JoinColumn(name=Reclamacion.COLUMN_NAME_PEDIDO)
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido
     *            the pedido to set.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the notas.
     */
    @Column(name = Reclamacion.COLUMN_NAME_NOTAS, length = 400)
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
     * @return the detallesReclamacion.
     */
    @OneToMany(mappedBy = DetalleReclamacion.PROPERTY_NAME_RECLAMACION, cascade = { CascadeType.ALL })
    public List<DetalleReclamacion>  getDetallesReclamacion() {
        return detallesReclamacion;
    }

    /**
     * @param detallesReclamacion
     *            the detallesReclamacion to set.
     */

    public void setDetallesReclamacion(List<DetalleReclamacion> detallesReclamacion) {
        this.detallesReclamacion = detallesReclamacion;
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
