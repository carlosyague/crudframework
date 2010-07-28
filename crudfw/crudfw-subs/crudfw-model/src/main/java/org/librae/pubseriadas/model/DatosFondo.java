package org.librae.pubseriadas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

@Entity(name = DatosFondo.ENTITY_NAME)
@Table(name = DatosFondo.TABLE_NAME)
public class DatosFondo extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 3011406782442570171L;

	public static final String	ENTITY_NAME								= "org.librae.pubseriadas.model.DatosFondo";

	/**
     * Constantes para referencia de los atributos de DatosFondo
     */
    public static final String        PROPTY_NAME_ID                        = "id";
    public static final String		  PROPTY_NAME_ORDEN						= "nOrden";
    public static final String		  PROPTY_NAME_CRONOLOGIA				= "cronologia";
    public static final String		  PROPTY_NAME_NOTA						= "nota";
    public static final String		  PROPTY_NAME_NOTA_PRIVADA				= "notaPrivada";
    public static final String		  PROPTY_NAME_NOTA_PUBLICA				= "notaPublica";
    public static final String		  PROPTY_NAME_NOTACION_FONDOS			= "notacionFondos";

    public static final String	TABLE_NAME								= "SER_DATOS_FONDO";
	private static final String ID_GENERATOR_NAME						= "generator_ser_datosfondo";
    private static final String	ID_SEQUENCE_NAME						= "SEQ_SER_DATOS_FONDO";
	public static final String COLUMN_NAME_ID 							= "X_DATOS_FONDO";
	public static final String COLUMN_NAME_ORDEN 						= "N_ORDEN";
	public static final String COLUMN_NAME_CRONOLOGIA 					= "T_CRONOLOGIA";
	public static final String COLUMN_NAME_NOTA 						= "T_NOTA";
	public static final String COLUMN_NAME_NOTA_PRIVADA 				= "T_NOTA_PRIVADA";
	public static final String COLUMN_NAME_NOTA_PUBLICA 				= "T_NOTA_PUBLICA";
	public static final String COLUMN_NAME_NOTACION_FONDOS				= "T_NOTACION_FONDOS";

	/**
	 * clave primaria
	 */
	private Long id;

	/**
	 *
	 */
	private Long nOrden;

	/**
	 *
	 */
	private String cronologia;

	/**
	 *
	 */
	private String nota;

	/**
	 *
	 */
	private String notaPrivada;

	/**
	 *
	 */
	private String notaPublica;

	private String notacionFondos;

	protected DatosFondo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatosFondo(String cronologia, Long orden, String nota,
			String notaPrivada, String notaPublica) {
		super();
		this.cronologia = cronologia;
		nOrden = orden;
		this.nota = nota;
		this.notaPrivada = notaPrivada;
		this.notaPublica = notaPublica;
	}

	public DatosFondo(String nota, String notacionFondos) {
		super();
		this.nota = nota;
		this.notacionFondos = notacionFondos;
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

	@Column(name = COLUMN_NAME_ORDEN)
	public Long getNOrden() {
		return nOrden;
	}

	public void setNOrden(Long orden) {
		nOrden = orden;
	}

	@Column(name = COLUMN_NAME_CRONOLOGIA, length = 80)
	public String getCronologia() {
		return cronologia;
	}

	public void setCronologia(String cronologia) {
		this.cronologia = cronologia;
	}

	@Column(name = COLUMN_NAME_NOTA, length = 400)
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	@Column(name = COLUMN_NAME_NOTA_PRIVADA, length = 400)
	public String getNotaPrivada() {
		return notaPrivada;
	}

	public void setNotaPrivada(String notaPrivada) {
		this.notaPrivada = notaPrivada;
	}

	@Column(name = COLUMN_NAME_NOTA_PUBLICA, length = 400)
	public String getNotaPublica() {
		return notaPublica;
	}

	public void setNotaPublica(String notaPublica) {
		this.notaPublica = notaPublica;
	}

	@Column(name = COLUMN_NAME_NOTACION_FONDOS, length = 80)
	public String getNotacionFondos() {
		return notacionFondos;
	}

	public void setNotacionFondos(String notacionFondos) {
		this.notacionFondos = notacionFondos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DatosFondo))
			return false;
		DatosFondo other = (DatosFondo) obj;
		if (cronologia == null) {
			if (other.cronologia != null)
				return false;
		} else if (!cronologia.equals(other.cronologia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nOrden == null) {
			if (other.nOrden != null)
				return false;
		} else if (!nOrden.equals(other.nOrden))
			return false;
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		if (notaPrivada == null) {
			if (other.notaPrivada != null)
				return false;
		} else if (!notaPrivada.equals(other.notaPrivada))
			return false;
		if (notaPublica == null) {
			if (other.notaPublica != null)
				return false;
		} else if (!notaPublica.equals(other.notaPublica))
			return false;
		if (notacionFondos == null) {
			if (other.notacionFondos != null)
				return false;
		} else if (!notacionFondos.equals(other.notacionFondos))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result = prime * result
				+ ((cronologia == null) ? 0 : cronologia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nOrden == null) ? 0 : nOrden.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime * result
				+ ((notaPrivada == null) ? 0 : notaPrivada.hashCode());
		result = prime * result
				+ ((notaPublica == null) ? 0 : notaPublica.hashCode());
		result = prime * result
				+ ((notacionFondos == null) ? 0 : notacionFondos.hashCode());
		return result;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation
	 * of this object.
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder(this)
				.append("id", this.getId())
				.append("nOrden", this.getNOrden())
				.append("cronologia", this.getCronologia())
				.append("nota", this.getNota())
				.append("notaPrivada", this.getNotaPrivada())
				.append("notaPublica", this.getNotaPublica())
				.append("notacionFondos", this.getNotacionFondos())
				.toString();
	}

}
