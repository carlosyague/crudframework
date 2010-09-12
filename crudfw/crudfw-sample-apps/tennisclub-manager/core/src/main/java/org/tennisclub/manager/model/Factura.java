package org.tennisclub.manager.model;

import java.util.Date;

import javax.persistence.Entity;

import es.uma.crudframework.model.BaseObject;
import es.uma.crudframework.model.annotations.CrudValidator;
import es.uma.crudframework.model.annotations.CrudViewParams;

@Entity(name = Factura.ENTITY_NAME)
public class Factura extends BaseObject {
	/**
	 * BaseObject implements Serializable
	 */
	private static final long serialVersionUID = 486740873523309165L;

	/**
	 * POJO-JPA Constants
	 */
	public static final String ENTITY_NAME = "org.tennisclub.manager.model.Factura";

	/**
	 * fields
	 */
	private String orden;
	private Date fechaPago;
	private String numeroTjtaCredito;

	@CrudViewParams(validator = CrudValidator.VALIDATOR_REQUIRED_STRING)
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

	@CrudViewParams(validator = CrudValidator.VALIDATOR_REQUIRED_STRING)
	public String getNumeroTjtaCredito() {
		return numeroTjtaCredito;
	}

	public void setNumeroTjtaCredito(String numeroTjtaCredito) {
		this.numeroTjtaCredito = numeroTjtaCredito;
	}
}
