package org.librae.pubseriadas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.BaseObject;

@Entity(name = EstadoNumero.ENTITY_NAME)
@Table(name = EstadoNumero.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class EstadoNumero extends BaseObject{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	 public static final String  ENTITY_NAME             = "org.librae.pubseriadas.model.EstadoNumero";
	    public static final String  TABLE_NAME              = "SER_ESTADOS_NUMEROS";
	    public static final String  ID_GENERATOR_NAME       = "generator_ser_estados_numeros";
	    private static final String ID_SEQUENCE_NAME        = "SEQ_SER_ESTADOS_NUMEROS";
	    public static final String  COLUMN_NAME_ID          = "X_ESTADO_NUMERO";
	    public static final String  COLUMN_NAME_CODIGO      = "C_ESTADO_NUMERO";
	    public static final String  COLUMN_NAME_NOMBRE      = "T_ESTADO_NUMERO";
	    public static final String  COLUMN_NAME_DESCRIPCION = "T_ESTADO_NUMERO_ALT";

	    public static final String  PROPTY_NAME_ID          = "id";
	    public static final String  PROPTY_NAME_CODIGO      = "codigo";
	    public static final String  PROPTY_NAME_NOMBRE      = "nombre";
	    public static final String  PROPTY_NAME_DESCRIPCION = "descripcion";

	    /**
	     * Clave primaria artificial
	     */
	    private Long                id;

	    /**
	     * Nombre del estado de circulación. Por ejmeplo: 'Prestado'
	     */
	    private String              nombre;

	    /**
	     * Descripción explicativa del estado de circulación
	     */
	    private String              descripcion;

	    /**
	     * Código asignado por el usuario
	     */
	    private String              codigo;

	    protected EstadoNumero(){
	    	super();
	    }

	    public EstadoNumero(String codigo) {
	    	super();
	    	this.codigo = codigo;
	    }

	    public EstadoNumero(String codigo, String nombre, String descripcion) {
	    	super();
	    	this.codigo = codigo;
	    	this.nombre = nombre;
	    	this.descripcion = descripcion;
	    }


	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
	    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
	    @Column(name = EstadoNumero.COLUMN_NAME_ID)
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@Column(name = EstadoNumero.COLUMN_NAME_NOMBRE, length = 40)
		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		@Column(name = EstadoNumero.COLUMN_NAME_DESCRIPCION, length = 40)
		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		@Column(name = EstadoNumero.COLUMN_NAME_CODIGO, length = 40)
		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = prime;
			result = prime * result
					+ ((codigo == null) ? 0 : codigo.hashCode());
			result = prime * result
					+ ((descripcion == null) ? 0 : descripcion.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result
					+ ((nombre == null) ? 0 : nombre.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (getClass() != obj.getClass())
				return false;
			EstadoNumero other = (EstadoNumero) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			if (descripcion == null) {
				if (other.descripcion != null)
					return false;
			} else if (!descripcion.equals(other.descripcion))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			return true;
		}

		  /**
	     * @see java.lang.Object#toString()
	     */
	    @Override
	    public String toString() {
	        return new ToStringBuilder(this)

	        .append(EstadoNumero.COLUMN_NAME_ID, this.getId())

	        .append(EstadoNumero.COLUMN_NAME_CODIGO,
	                (this.getCodigo() == null) ? 0 : this.getCodigo().toString())

	        .append(EstadoNumero.COLUMN_NAME_NOMBRE,
	                (this.getNombre() == null) ? 0 : this.getNombre().toString())
	        .toString();
	    }




}
