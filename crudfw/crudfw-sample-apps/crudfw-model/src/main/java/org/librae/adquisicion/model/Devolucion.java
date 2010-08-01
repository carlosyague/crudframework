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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.librae.common.model.BaseObject;

/**
 * @author jVillegas
 */
@Entity(name = Devolucion.ENTITY_NAME)
@Table(name = Devolucion.TABLE_NAME)
public class Devolucion extends BaseObject {

    private static final long   serialVersionUID               = -4315814163126477584L;

    public static final String  ENTITY_NAME                    = "org.librae.adquisicion.model.Devolucion";
    public static final String  TABLE_NAME                     = "ADQ_DEVOLUCION";
    private static final String ID_GENERATOR_NAME              = "generator_adq_devoluciones";
    private static final String ID_SEQUENCE_NAME               = "SEQ_ADQ_DEVOLUCIONES";
    public static final String  COLUMN_NAME_ID                 = "X_DEVOLUCION";
    public static final String  COLUMN_NAME_FECHA_DEVOLUCION   = "F_FECHA_DEVOLUCION";
    public static final String  COLUMN_NAME_FECHA_RECIBIDO       = "F_FECHA_RECIBIDO";
    public static final String  COLUMN_NAME_ESTADO             = "T_ESTADO";
    public static final String  COLUMN_NAME_NOTAS              = "T_NOTAS";
    public static final String  COLUMN_NAME_PEDIDO             = "PED_X_PEDIDO";

    public static final String  PROPERTY_NAME_ID               = "id";
    public static final String  PROPERTY_NAME_FECHA_DEVOLUCION = "fechaDevolucion";
    public static final String  PROPERTY_NAME_FECHA_RECIBO     = "fechaRecibo";
    public static final String  PROPERTY_NAME_ESTADO           = "estado";
    public static final String  PROPERTY_NAME_NOTAS            = "notas";
    public static final String  PROPERTY_NAME_PEDIDO           = "pedido";

    /**
     * clave primaria.
     */
    private Long                id;

    /**
     * fecha devolucion.
     */
    private Date                fechaDevolucion;

    /**
     * fechaRecibido.
     */
    private Date                fechaRecibido;

    /**
     * estado.
     */
    private String              estado;

    /**
     * detalles Devolucion.
     */

    private List<DetalleDevolucion>                detallesDevolucion;

    /**
     * notas.
     */
    private String              notas;

    /**
     *pedido.
     */
    private Pedido              pedido;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Devolucion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Devolucion.ID_SEQUENCE_NAME)
    @Column(name = Devolucion.COLUMN_NAME_ID)
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
     * @return the fechaDevolucion
     */
    @Column(name = Devolucion.COLUMN_NAME_FECHA_DEVOLUCION)
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * @param fechaDevolucion
     *            the fechaDevolucion to set
     */
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * @return the fechaRecibido
     */
    @Column(name = Devolucion.COLUMN_NAME_FECHA_RECIBIDO)
    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    /**
     * @param fechaRecibido
     *            the fechaRecibido to set
     */
    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    /**
     * @return the estado
     */
    @Column(name = Devolucion.COLUMN_NAME_ESTADO, length = 40)
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
     * @return the detallesDevolucion
     */
    @OneToMany(mappedBy = DetalleDevolucion.PROPERTY_NAME_DEVOLUCION, cascade = { CascadeType.ALL })
    public List<DetalleDevolucion> getDetallesDevolucion() {
        return detallesDevolucion;
    }

    /**
     * @param detallesDevolucion
     *            the detallesDevolucion to set
     */
    public void setDetallesDevolucion(List<DetalleDevolucion> detallesDevolucionFinal) {
        this.detallesDevolucion = detallesDevolucionFinal;
    }

    /**
     * @return the notas
     */
    @Column(name = Devolucion.COLUMN_NAME_NOTAS, length = 400)
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
     * @return the pedido
     */
    @ManyToOne
    @JoinColumn(name=Devolucion.COLUMN_NAME_PEDIDO)
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido
     *            the pedido to set
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
