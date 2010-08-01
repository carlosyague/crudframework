package org.librae.adquisicion.model;

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
import org.librae.adminconfig.model.Usuario;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un EstadoPedido
 *
 * @author jcdiaz
 */
@Entity(name = EstadoPedido.ENTITY_NAME)
@Table(name = EstadoPedido.TABLE_NAME)
public class EstadoPedido extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Presupuesto necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID       = 6772725583756309487L;

    public static final String  ENTITY_NAME            = "org.librae.adquisicion.model.EstadoPedido";
    public static final String  TABLE_NAME             = "ADQ_ESTADOS_PEDIDOS";
    private static final String ID_GENERATOR_NAME      = "generator_adq_estados_pedidos";
    private static final String ID_SEQUENCE_NAME       = "SEQ_ADQ_ESTADOS_PEDIDOS";
    public static final String  COLUMN_NAME_ID         = "X_ESTADO_PEDIDO";
    public static final String  COLUMN_NAME_FECHA      = "F_FECHA";
    public static final String  COLUMN_NAME_NOMBRE     = "T_NOMBRE";
    public static final String  COLUMN_NAME_PEDIDO_FK  = "PED_X_PEDIDO";
    public static final String  COLUMN_NAME_USUARIO_FK = "USU_X_USUARIO";
    public static final String  PEDIDO                 = "pedido";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * fecha del estado del pedido
     */
    private Date                fecha;

    /**
     * nombre del estado del pedido
     */
    private String              nombre;

    /**
     * referencia al pedido asociado
     */
    private Pedido              pedido;

    /**
     * referencia al pedido asociado
     */
    private Usuario             usuario;

    /**
     * Constructor sin parámetros
     */
    protected EstadoPedido() {
    }

    /**
     * Constructor con parámetros
     *
     * @param fecha
     * @param nombre
     */
    public EstadoPedido(Date fecha, String nombre) {
        this.fecha = fecha;
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = EstadoPedido.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = EstadoPedido.ID_SEQUENCE_NAME)
    @Column(name = EstadoPedido.COLUMN_NAME_ID)
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
     * @return the fecha
     */
    @Column(name = EstadoPedido.COLUMN_NAME_FECHA)
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha
     *            the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the nombre
     */
    @Column(name = EstadoPedido.COLUMN_NAME_NOMBRE, length = 40)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the pedido
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.Pedido.class)
    @JoinColumn(name = EstadoPedido.COLUMN_NAME_PEDIDO_FK)
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

    /**
     * @return the usuario
     */
    @OneToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adminconfig.model.Usuario.class)
    @JoinColumn(name = EstadoPedido.COLUMN_NAME_USUARIO_FK)
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     *            the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
        if (!(obj instanceof EstadoPedido)) {
            return false;
        }
        EstadoPedido other = (EstadoPedido) obj;
        if (fecha == null) {
            if (other.fecha != null) {
                return false;
            }
        } else if (!fecha.equals(other.fecha)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (pedido == null) {
            if (other.pedido != null) {
                return false;
            }
        } else if (!pedido.equals(other.pedido)) {
            return false;
        }
        if (usuario == null) {
            if (other.usuario != null) {
                return false;
            }
        } else if (!usuario.equals(other.usuario)) {
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
        return new ToStringBuilder(this).append(EstadoPedido.COLUMN_NAME_ID,
                this.id).append(EstadoPedido.COLUMN_NAME_FECHA, this.fecha)
                .append(EstadoPedido.COLUMN_NAME_NOMBRE, this.nombre)
                .toString();
    }

}
