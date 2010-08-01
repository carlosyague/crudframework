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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.librae.common.model.BaseObject;

/**
 * @author jVillegas
 */
@Entity(name = TipoPedido.ENTITY_NAME)
@Table(name = TipoPedido.TABLE_NAME)
public class TipoPedido extends BaseObject {

    /**
     * serial version.
     */
    private static final long serialVersionUID = -6347399457902523507L;

    public static final String  ENTITY_NAME               = "org.librae.adquisicion.model.TipoPedido";
    public static final String  TABLE_NAME                = "ADQ_TIPO_PEDIDO";
    private static final String ID_GENERATOR_NAME         = "generator_adq_tipo_pedido";
    private static final String ID_SEQUENCE_NAME          = "SEQ_ADQ_TIPO_PEDIDO";
    public static final String  COLUMN_NAME_ID            = "X_TIPO_PEDIDO";
    public static final String  COLUMN_NAME_PARTIDA       = "PAR_X_PARTIDA";
    public static final String  COLUMN_NAME_NOMBRE        = "T_NOMBRE";

    public static final String  PROPERTY_NAME_ID          = "id";
    public static final String  PROPERTY_NAME_PARTIDA     = "partida";
    public static final String  PROPERTY_NAME_NOMBRE      = "nombre";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * divisa.
     */
    private PartidaPresupuestaria partida;

    /**
     * nombre.
     */
    private String nombre;


    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TipoPedido.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = TipoPedido.ID_SEQUENCE_NAME)
    @Column(name = TipoPedido.COLUMN_NAME_ID)
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
     * @return the partida
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.PartidaPresupuestaria.class)
    @JoinColumn(name = TipoPedido.COLUMN_NAME_PARTIDA)
    public PartidaPresupuestaria getPartida() {
        return partida;
    }

    /**
     * @param partida the partida to set
     */
    public void setPartida(PartidaPresupuestaria partida) {
        this.partida = partida;
    }

    /**
     * @return the nombre
     */
    @Column(name = TipoPedido.COLUMN_NAME_NOMBRE, length = 40)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
