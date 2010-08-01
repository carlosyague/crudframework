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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.librae.common.model.BaseObject;

/**
 * @author jVillegas
 */
@Entity(name = DetalleReclamacion.ENTITY_NAME)
@Table(name = DetalleReclamacion.TABLE_NAME)
public class DetalleReclamacion extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID             = -8885046700773227950L;

    public static final String  ENTITY_NAME                  = "org.librae.adquisicion.model.DetalleReclamacion";
    public static final String  TABLE_NAME                   = "ADQ_DETALLES_RECLAMACIONES";
    private static final String ID_GENERATOR_NAME            = "generator_adq_detalles_reclamaciones";
    private static final String ID_SEQUENCE_NAME             = "SEQ_ADQ_DETALLES_RECLAMACIONES";
    public static final String  COLUMN_NAME_ID               = "X_DETALLE_RECLAMACION";
    public static final String  COLUMN_NAME_RECLAMADOS       = "DET_X_DETALLE_RECLAMADOS";
    public static final String  COLUMN_NAME_DETALLE_PEDIDO   = "DET_X_DETALLE_PEDIDO";
    public static final String  COLUMN_NAME_RECIBIDOS        = "N_RECIBIDOS";
    public static final String  COLUMN_NAME_RECLAMACION      = "REC_X_RECLAMACION";

    public static final String  PROPERTY_NAME_ID             = "id";
    public static final String  PROPERTY_NAME_DETALLE_PEDIDO = "detallePedido";
    public static final String  PROPERTY_NAME_RECIBIDOS      = "recibidos";
    public static final String  PROPERTY_NAME_RECLAMADOS     = "reclamados";

    public static final String PROPERTY_NAME_RECLAMACION = "reclamacion";


    /**
     * clave primaria
     */
    private Long                  id;

    /**
     * codigoDetallePedido.
     */
    private String                detallePedido;

    /**
     * codigoDetallePedido.
     */
    private Integer               reclamados;

    /**
     * codigoDetallePedido.
     */
    private Integer                recibidos;

    /**
     * reclamacion.
     */
    private Reclamacion              reclamacion;


    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = DetalleReclamacion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = DetalleReclamacion.ID_SEQUENCE_NAME)
    @Column(name = DetalleReclamacion.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the detallePedido
     */
   // @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.DetallePedido.class)
   // @JoinColumn(name = DetalleReclamacion.COLUMN_NAME_DETALLE_PEDIDO)
    public String getDetallePedido() {
        return detallePedido;
    }

    /**
     * @param detallePedido the detallePedido to set
     */
    public void setDetallePedido(String detallePedido) {
        this.detallePedido = detallePedido;
    }

    /**
     * @return the reclamados
     */
    @Column(name = DetalleReclamacion.COLUMN_NAME_RECLAMADOS)
    public Integer getReclamados() {
        return reclamados;
    }

    /**
     * @param reclamados the reclamados to set
     */
    public void setReclamados(Integer reclamados) {
        this.reclamados = reclamados;
    }

    /**
     * @return the recibidos
     */
    @Column(name = DetalleReclamacion.COLUMN_NAME_RECIBIDOS)
    public Integer getRecibidos() {
        return recibidos;
    }

    /**
     * @param recibidos the recibidos to set
     */
    public void setRecibidos(Integer recibidos) {
        this.recibidos = recibidos;
    }

    /**
     * @return the reclamacion
     */
    @ManyToOne
    @JoinColumn(name = DetalleReclamacion.COLUMN_NAME_RECLAMACION)
    public Reclamacion getReclamacion() {
        return reclamacion;
    }

    /**
     * @param reclamacion the reclamacion to set
     */
    public void setReclamacion(Reclamacion reclamacion) {
        this.reclamacion = reclamacion;
    }

    /**
     * /* (non-Javadoc)
     *
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
