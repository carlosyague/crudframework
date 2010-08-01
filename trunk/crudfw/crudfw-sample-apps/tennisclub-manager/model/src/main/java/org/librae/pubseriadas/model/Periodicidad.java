package org.librae.pubseriadas.model;

import java.util.ArrayList;
import java.util.List;

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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

@Entity(name = Periodicidad.ENTITY_NAME)
@Table(name = Periodicidad.TABLE_NAME)
public class Periodicidad extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 4818894719495776985L;

	/**
	 * Constantes para referencia de los atributos de Ejemplar
	 */
	public static final String PROPTY_NAME_ID 							= "id";
	public static final String PROPTY_NAME_NUMERO 						= "numero";
	public static final String PROPTY_NAME_DESCRIPCION 					= "descripcion";
	public static final String PROPTY_NAME_NOTA 						= "nota";
	public static final String PROPTY_NAME_TITULO 						= "titulo";
	public static final String PROPTY_NAME_TIPO_PERIODICIDAD 			= "tipoPeriodicidad";
	public static final String PROPTY_NAME_MODO_VISUALIZACION			= "modoVisualizacion";
	public static final String PROPTY_NAME_EXCEPCIONES_MESES			= "excepcionesMeses";


	public static final String  ENTITY_NAME								= "org.librae.pubseriadas.model.Periodicidad";
    public static final String	TABLE_NAME								= "SER_PERIODICIDADES";
	private static final String ID_GENERATOR_NAME						= "generator_ser_periodicidad";
    private static final String	ID_SEQUENCE_NAME						= "SEQ_SER_PERIODICIDADES";
    public static final String COLUMN_NAME_ID 							= "X_PERIODICIDAD";
    public static final String COLUMN_NAME_NUMERO 						= "T_NUMERO";
    public static final String COLUMN_NAME_DESCRIPCION 					= "T_DESCRIPCION";
    public static final String COLUMN_NAME_NOTA 						= "T_NOTA";
    public static final String COLUMN_NAME_TITULO 						= "T_TITULO";
    public static final String COLUMN_NAME_TIPO_PERIODICIDAD_FK			= "TIP_X_TIPO_PERIODICIDAD";
    public static final String COLUMN_NAME_MODO_VISUALIZACION_FK		= "MOD_X_MODO_VISUALIZACION";
    public static final String COLUMN_NAME_EXCEPCIONES_MESES			= "T_EXCEPCIONES_MESES";



	/**
	 * clave primaria
	 */
	private Long id;

	/**
	 * Código que indica los días seleccionados. Su formato es
	 * dependiente del tipo de periodicidad elegida
	 * <p>
	 * Tipo de periodicidad - formato
	 * <p>
	 * dias de la semana - <b>REPETICIONES</b>:<b>Calendar.MONTH</b>, [<b>Calendar.MONTH</b>, ]
	 * <p>
	 * días del mes - <b>REPETICIONES</b>:<b>Calendar.DAY_OF_WEEK</b>, [<b>Calendar.DAY_OF_WEEK</b>, ]
	 * <p>
	 * días del año - <b>REPETICIONES</b>:<b>Calendar.MONTH</b>/<b>Calendar.DAY_OF_MONTH</b>,
	 * [<b>Calendar.MONTH</b>/<b>Calendar.DAY_OF_MONTH</b>, ]
	 *
	 */
	private String numero;

	/**
	 * Descripción de la periodicidad
	 */
	private String descripcion;

	/**
	 * Nota acerca de la periodicidad
	 */
	private String nota;

	/**
	 * Nombre de la periodicidad
	 */
	private String titulo;

	/**
	 * Tipo de la periodicidad
	 */
	private TipoPeriodicidad tipoPeriodicidad;

	private ModoVisualizacion modoVisualizacion;

	/**
	 * Campo extraído de AbsysNET que representa los 12 meses con un
	 * 0 - el mes es una excepción. No se generan números en ese periodo
	 * 1 - comportamiento normal durante ese mes.
	 *
	 * Si este campo es null, se interpreta como "111111111111". En otro
	 * caso, SIEMPRE debe tener una longitud de 12 caracteres. 
	 */
	private String excepcionesMeses;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = ModoVisualizacion.class)
    @JoinColumn(name = Periodicidad.COLUMN_NAME_MODO_VISUALIZACION_FK) //, nullable = false)
	public ModoVisualizacion getModoVisualizacion() {
		return modoVisualizacion;
	}

	public void setModoVisualizacion(ModoVisualizacion modoVisualizacion) {
		this.modoVisualizacion = modoVisualizacion;
	}

	protected Periodicidad() {
		super();
	}

	public Periodicidad(String descripcion, String nota, String numero,
			String titulo, TipoPeriodicidad tipoPeriodicidad) {
		super();
		this.descripcion = descripcion;
		this.nota = nota;
		this.numero = numero;
		this.titulo = titulo;
		this.tipoPeriodicidad = tipoPeriodicidad;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
	@SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
	@Column(name = COLUMN_NAME_ID)
	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	@Column(name = COLUMN_NAME_NUMERO, length = 120)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = COLUMN_NAME_DESCRIPCION, length = 255)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = COLUMN_NAME_NOTA, length = 400)
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	@Column(name = COLUMN_NAME_TITULO, length = 120)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setTipoPeriodicidad(TipoPeriodicidad tipoPeriodicidad) {
		this.tipoPeriodicidad = tipoPeriodicidad;
	}

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = TipoPeriodicidad.class)
    @JoinColumn(name = Periodicidad.COLUMN_NAME_TIPO_PERIODICIDAD_FK) //, nullable = false)
	public TipoPeriodicidad getTipoPeriodicidad() {
		return tipoPeriodicidad;
	}

    @Column(name = COLUMN_NAME_EXCEPCIONES_MESES, length = 12)
	public String getExcepcionesMeses() {
		return excepcionesMeses;
	}

	public void setExcepcionesMeses(String excepcionesMeses) {
		this.excepcionesMeses = excepcionesMeses;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Periodicidad))
			return false;
		Periodicidad other = (Periodicidad) obj;
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
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (tipoPeriodicidad == null) {
			if (other.tipoPeriodicidad != null)
				return false;
		} else if (!tipoPeriodicidad.equals(other.tipoPeriodicidad))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime
				* result
				+ ((tipoPeriodicidad == null) ? 0 : tipoPeriodicidad.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 *
	 * @return a <code>String</code> representation of this object.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId()).append(
				"numero", this.getNumero()).append("descripcion",
				this.getDescripcion()).append("nota", this.getNota()).append(
				"titulo", this.getTitulo()).toString();
	}

	/**
	 * Desde la cadena excepcionesMeses, en formato "111111111111", se crea y
	 * devuelve una lista de números, cada uno representando uno de los meses
	 * de excepción de esta periodicidad. Cada número es un String que representa
	 * un mes en formato Calendar.MONTH.
	 * 
	 * Si no hay excepciones, se devuelve la lista vacía.
	 * 
	 * @return la lista de meses
	 */
	public List<String> transformaExcepcionesMesesALista() {
		List<String> lista = new ArrayList<String>();
		
		/* Para null, o formato con menos caracteres de los debidos, la lista se
		 * deja vacía */
		if(excepcionesMeses != null && excepcionesMeses.length() > 11) {
			for(int i=0; i<12; i++) {
				if(excepcionesMeses.charAt(i) == '0') {
					lista.add(String.valueOf(i));
				}
			}
		}

		return lista;
	}
	
	/**
	 * Desde una lista de String representando meses en formato Calendar.MONTH,
	 * creamos y asignamos el String de meses de excepción de esta Periodicidad.
	 * 
	 * @param lista lista de meses, por ejemplo la obtenida de transformarExcepcionesMesesALista()
	 */
	public void asignaExcepcionesMesesDesdeLista(List<String> lista) {
		StringBuilder cadenaMeses = new StringBuilder(12);
		cadenaMeses.append("111111111111");
		try {
			for(String mes : lista) {
				Integer pos = Integer.valueOf(mes);
				cadenaMeses.replace(pos, pos + 1, "0");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		setExcepcionesMeses(cadenaMeses.toString());
	}
}
