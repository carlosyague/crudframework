package org.tennisclub.manager.model;

import java.util.Date;

import javax.persistence.Entity;

import es.uma.crudframework.model.BaseObject;
import es.uma.crudframework.model.annotations.SemanticTypeField;
import es.uma.crudframework.model.annotations.CrudSemanticTag;

@Entity
public class Factura extends BaseObject {
	/**
	 * BaseObject implements Serializable
	 */
	private static final long serialVersionUID = 486740873523309165L;

	/**
	 * fields
	 */
	private String orden;
	private Date fechaPago;
	private String numeroTjtaCredito;

	@CrudSemanticTag(type= SemanticTypeField.NOT_NULLABLE)
	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	@CrudSemanticTag(type= SemanticTypeField.NOT_NULLABLE)
	public String getNumeroTjtaCredito() {
		return numeroTjtaCredito;
	}

	public void setNumeroTjtaCredito(String numeroTjtaCredito) {
		this.numeroTjtaCredito = numeroTjtaCredito;
	}
}
