package org.librae.catalogacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean que ataca a una vista que dada la P.K. de un registro bibliografico
 * muestra tantas filas como bibliotecas tengan algún ejemplar de dicho
 * registro. La información que se muestra de las bibliotecas es una
 * concatenación de las PKs de la sucursal que posee el ejempalr y sus padres
 * hasta la raiz del árbol.
 * 
 * @author vmdiaz
 */
@Entity(name = ViewRegistroBibliotecas.ENTITY_NAME)
@Table(name = ViewRegistroBibliotecas.TABLE_NAME)
public class ViewRegistroBibliotecas extends SpringRemotableLazyEntity<ViewRegistroBibliotecas> {

	/**
	 * BaseObject es Serializable, por lo tanto Registro necesita un Serial
	 * Version UID
	 */
	private static final long serialVersionUID = 4885829162672044630L;

	/**
	 * Constante para referencia del nombre de la entidad
	 */
	public static final String ENTITY_NAME = "org.librae.catalogacion.model.ViewRegistroBibliotecas";

	/**
	 * Constante para referencia de la PK de la vista
	 */
	public static final String PROPTY_NAME_ID = "id";
	/**
	 * Constante para referencia de la PK de la biblioteca
	 */
	public static final String PROPTY_NAME_BIBLIOTECAS = "bibliotecas";

	/**
	 * Constante para referencia del nombre de la tabla
	 */
	public static final String TABLE_NAME = "VIEW_CAT_REGISTRO_BIBLIOTECAS";

	/**
	 * Constante para referencia del nombre de la PK del registro bibliográfico
	 * en la vista
	 */
	public static final String COLUMN_NAME_ID = "ID_REGISTRO";
	/**
	 * Constante para referencia del nombre del campo con la rama completa de
	 * IDS de las bibliotecas
	 */
	public static final String COLUMN_NAME_BIBLIOTECAS = "RAMA_COMPLETA";

	/*
	 * PK del registro bibliografico
	 */
	@Field
	private Long id;

	/**
	 * Concatenación de las PKs de la sucursal que posee el ejempalr y sus
	 * padres hasta la raiz del árbol.
	 */
	@Field
	private String bibliotecas;

	/**
	 * Constructor sin parámetros
	 */
	protected ViewRegistroBibliotecas() {
		super();
	}

	/**
	 * @return the id
	 */
	@Id
	@Column(name = ViewRegistroBibliotecas.COLUMN_NAME_ID, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	protected void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the bibliotecas
	 */
	@Column(name = ViewRegistroBibliotecas.COLUMN_NAME_BIBLIOTECAS, nullable = false, length = 1000)
	public String getBibliotecas() {
		return bibliotecas;
	}

	/**
	 * @param bibliotecas
	 *            the bibliotecas to set
	 */
	public void setBibliotecas(final String bibliotecas) {
		this.bibliotecas = bibliotecas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result = prime * result + ((bibliotecas == null) ? 0 : bibliotecas.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ViewRegistroBibliotecas)) {
			return false;
		}
		final ViewRegistroBibliotecas other = (ViewRegistroBibliotecas) obj;
		if (bibliotecas == null) {
			if (other.bibliotecas != null) {
				return false;
			}
		} else if (!bibliotecas.equals(other.bibliotecas)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this).append(ViewRegistroBibliotecas.PROPTY_NAME_ID, this.id).append(ViewRegistroBibliotecas.PROPTY_NAME_BIBLIOTECAS, (this.bibliotecas == null) ? 0 : this.bibliotecas).toString();
	}

	@Override
	public ViewRegistroBibliotecas newInstance() {

		return new ViewRegistroBibliotecas();
	}
}