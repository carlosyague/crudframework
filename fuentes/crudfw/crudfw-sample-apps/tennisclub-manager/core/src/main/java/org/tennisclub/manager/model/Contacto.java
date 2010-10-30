package org.tennisclub.manager.model;

import javax.persistence.Entity;

import es.uma.crudframework.model.BaseObject;
import es.uma.crudframework.model.annotations.SemanticTypeField;
import es.uma.crudframework.model.annotations.CrudSemanticTag;

@Entity
public class Contacto extends BaseObject {
	/**
	 * BaseObject implements Serializable
	 */
	private static final long serialVersionUID = 486740873523309165L;	 
    
	/**
	 * fields
	 */
	private String tfno;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String codigoPostal;
	private String poblacion;	
	private String provincia;
	private String notas;
	
	/**
	 * @return the tfno
	 */
	@CrudSemanticTag(type=SemanticTypeField.NOT_NULLABLE)
	public String getTfno() {
		return tfno;
	}

	/**
	 * @param tfno the tfno to set
	 */
	public void setTfno(String tfno) {
		this.tfno = tfno;
	}

	/**
	 * @return the nombre
	 */
	@CrudSemanticTag(type=SemanticTypeField.NOT_NULLABLE)
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the notas
	 */
	@CrudSemanticTag(textArea=true)
	public String getNotas() {
		return notas;
	}

	/**
	 * @param notas the notas to set
	 */
	public void setNotas(String notas) {
		this.notas = notas;
	}

}
