package org.librae.pubseriadas.model;

import java.text.SimpleDateFormat;
import java.util.Date;

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

@Entity(name = EtiquetaVisualizacion.ENTITY_NAME)
@Table(name = EtiquetaVisualizacion.TABLE_NAME)
public class EtiquetaVisualizacion extends BaseObject {


	private static final long 	serialVersionUID = 1L;
	public static final String	ENTITY_NAME								= "org.librae.pubseriadas.model.EtiquetaVisualizacion";
	public static final String	TABLE_NAME 								= "SER_ETIQUETAS_VISUALIZACION";
	private static final String	ID_GENERATOR_NAME						= "generator_ser_etiquetas_visualizacion";
    private static final String	ID_SEQUENCE_NAME						= "SEQ_SER_ETIQUETAS_VISUALIZACIO";
    public static final String	COLUMN_NAME_ID 							= "X_ETIQUETA_VISUALIZACION";
    public static final String	COLUMN_NAME_PREFIJO 					= "T_PREFIJO";
    public static final String	COLUMN_NAME_SUFIJO						= "T_SUFIJO";
    public static final String	COLUMN_NAME_CAMPO						= "T_CAMPO";
    public static final String	COLUMN_NAME_MODO_VISUALIZACION_FK		= "MOD_X_MODO_VISUALIZACION";

	public static final String	PROPTY_NAME_ID 							= "id";
	public static final String	PROPTY_NAME_PREFIJO 					= "prefijo";
	public static final String	PROPTY_NAME_SUFIJO						= "sufijo";
	public static final String	PROPTY_NAME_MODO_VISUALIZACION			= "modoVisualizacion";
	public static final String	PROPTY_NAME_CAMPO						= "campo";

	public static final String CAMPO_CORRELATIVO						= "%%CORRELATIVO%%";

	private Long 				id;

	private String 				prefijo;

	private String 				sufijo;

	/**
	 * String que se utiliza como código para saber qué campo debe imprimirse
	 * en la visualización de un número:
	 *
	 * - Si el campo es un formato válido para java.text.SimpleDateFormat, se
	 * mostrará la fecha prevista de recepción del número en este formato.
	 * Ejemplos permitidos: "dd", "dd/MM/yyyy", " yyyy "...
	 *
	 * - Para campo == EtiquetaVisualizacion.CAMPO_CORRELATIVO, se mostrará un
	 * número entero correlativo:
	 * 1, 2, 3...
	 *
	 *
	 */
	private String	 			campo;

	private ModoVisualizacion 	modoVisualizacion;


	protected EtiquetaVisualizacion() {
		super();
	}

	public EtiquetaVisualizacion(String prefijo, String sufijo,
			String campo, ModoVisualizacion modoVisualizacion) {
		super();
		this.prefijo = prefijo;
		this.sufijo = sufijo;
		this.campo = campo;
		this.modoVisualizacion = modoVisualizacion;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = COLUMN_NAME_ID)
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = COLUMN_NAME_PREFIJO, length = 40)
	public String getPrefijo() {
		return prefijo;
	}
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	@Column(name = COLUMN_NAME_SUFIJO, length = 40)
	public String getSufijo() {
		return sufijo;
	}
	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
	}

	@Column(name = COLUMN_NAME_CAMPO, length = 255)
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}

	@ManyToOne(targetEntity = ModoVisualizacion.class)
    @JoinColumn(name = EtiquetaVisualizacion.COLUMN_NAME_MODO_VISUALIZACION_FK, referencedColumnName = ModoVisualizacion.COLUMN_NAME_ID)
	public ModoVisualizacion getModoVisualizacion() {
		return modoVisualizacion;
	}
	public void setModoVisualizacion(ModoVisualizacion modoVisualizacion) {
		this.modoVisualizacion = modoVisualizacion;
	}

	public String parseaCampo(final Date fecha, final int indexCorrelativo) {
		String res = null;
		
		if(campo == null) {
			res = "";
		} else {
			// Parseamos nuestros propios códigos (en este momento solo
			// EtiquetaVisualizacion.CAMPO_CORRELATIVO).
			res = campo.replaceAll(EtiquetaVisualizacion.CAMPO_CORRELATIVO, "" + (indexCorrelativo + 1));

			/*
			 * Parseamos palabra a palabra mediante SimpleDateFormat. Así evitamos
			 * tener que poner comillas simples entre las palabras literales.
			 */
			StringBuilder aux = new StringBuilder();
			for(String s : res.split(" ")) {
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat(s);
					// Formato válido: añadimos la fecha convertida
					aux.append(dateFormat.format(fecha) + " ");
				} catch (IllegalArgumentException e) {
					// No encaja como formato: añadimos literalmente
					aux.append(s + " ");
				}
			}
			res = aux.toString();
		}

		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof EtiquetaVisualizacion))
			return false;
		EtiquetaVisualizacion other = (EtiquetaVisualizacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((modoVisualizacion == null) ? 0 : modoVisualizacion
						.hashCode());
		result = prime * result + ((prefijo == null) ? 0 : prefijo.hashCode());
		result = prime * result + ((sufijo == null) ? 0 : sufijo.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(PROPTY_NAME_ID, this.getId())
			.append(PROPTY_NAME_PREFIJO, this.getPrefijo())
			.append(PROPTY_NAME_SUFIJO, this.getSufijo())
			.append(PROPTY_NAME_CAMPO, this.getCampo())
			.append(PROPTY_NAME_MODO_VISUALIZACION, this.getModoVisualizacion().getId())
			.toString();
	}

}
