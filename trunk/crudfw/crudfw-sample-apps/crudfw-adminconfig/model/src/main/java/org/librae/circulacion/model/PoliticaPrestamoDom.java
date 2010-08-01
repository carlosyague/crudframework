package org.librae.circulacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.parampoliticas.AbstractParamPolPrestamo;

/**
 * Políticas de préstamos a domicilio. Cada fila es una política de préstamos a
 * domicilio con sus propiedades.<br>
 * <br>
 * Nota:<br>
 * En Ab*NET las columnas correspondientes a propiedades de las políticas de
 * préstamo siguen una nomenclatura consistente en que la primera letra indica
 * el tipo de préstamo a que se refiere la propiedad:<br>
 * <br>
 * d - Préstamo a domicilio<br>
 * s - Préstamo en sala<br>
 * p - Peticiones<br>
 * r - Reservas
 * 
 * @author asantamaría
 * @author cyague
 */
@Entity(name = PoliticaPrestamoDom.ENTITY_NAME)
@Table(name = PoliticaPrestamoDom.TABLE_NAME)
public class PoliticaPrestamoDom extends AbstractParamPolPrestamo {

	/**
	 * BaseObject is Serializable, so PrecedenciaParametrosDom needs a Serial
	 * Version UID
	 */
	private static final long serialVersionUID = -2398879942797816645L;

	public static final String ENTITY_NAME = "org.librae.circulacion.model.PoliticaPrestamoDom";
	public static final String TABLE_NAME = "CIR_DOM_POLITICAS";
	private static final String ID_GENERATOR_NAME = "generator_cir_dom_politicas";
	private static final String ID_SEQUENCE_NAME = "SEQ_CIR_DOM_POLITICAS";
	public static final String COLUMN_NAME_ID = "X_POLITICA_DOM";

	public static final String PROPTY_NAME_ID = "id";

	/**
	 * Clave primaria artificial, asignada por el SGBD, que identifica de forma
	 * única cada fila. Número secuencial
	 */
	private Long id;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = PoliticaPrestamoDom.ID_GENERATOR_NAME)
	@SequenceGenerator(name = PoliticaPrestamoDom.ID_GENERATOR_NAME, sequenceName = PoliticaPrestamoDom.ID_SEQUENCE_NAME)
	@Column(name = PoliticaPrestamoDom.COLUMN_NAME_ID)
	public Long getId() {
		return id;
	}

	public PoliticaPrestamoDom() {
		super();
	}

	public PoliticaPrestamoDom(final String codigo, final String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	protected void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		// if they are the same instance
		if (this == obj) {
			return true;
		}

		// if they are classify in different classes
		if (!(obj instanceof PoliticaPrestamoDom)) {
			return false;
		}

		final PoliticaPrestamoDom other = (PoliticaPrestamoDom) obj;

		if (!getId().equals(other.getId())) {
			return false;
		}
		if (!getNombre().equals(other.getNombre())) {
			return false;
		}
		if (!getDescripcionAlternativa().equals(
				other.getDescripcionAlternativa())) {
			return false;
		}

		return true;

	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result += ((id == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getNombre() == null) ? 0 : getNombre().hashCode());
		result = prime
				* result
				+ ((getDescripcionAlternativa() == null) ? 0
						: getDescripcionAlternativa().hashCode());

		return result;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(PoliticaPrestamoDom.PROPTY_NAME_ID, getId())
				.append(AbstractParamPolPrestamo.PROPTY_NAME_NOMBRE,
						getNombre())
				.append(
						AbstractParamPolPrestamo.PROPTY_NAME_DESCRIPCION_ALTERNATIVA,
						getDescripcionAlternativa()).toString();
	}

	/**
	 * Devuelve un bean con todos los datos necesarios para guardar en un
	 * préstamo
	 */
	@Transient
	public AbstractParamPolPrestamo getData() {
		return this;
	}

	@Override
	public AbstractParamPolPrestamo newInstance() {
		return new PoliticaPrestamoDom();
	}
}
