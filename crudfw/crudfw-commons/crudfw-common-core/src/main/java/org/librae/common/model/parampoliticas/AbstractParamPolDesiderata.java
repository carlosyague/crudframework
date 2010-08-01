package org.librae.common.model.parampoliticas;

import org.librae.common.model.BaseObject;

public class AbstractParamPolDesiderata extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String CRITERIA_FECHA_SOLICITUD_HASTA = "fechaSolicitudHasta";
	public static final String CRITERIA_FECHA_SOLICITUD_DESDE = "fechaSolicitudDesde";

	public static final String VALUE_ESTADO_EN_ESTUDIO = "En estudio";
	public static final String VALUE_ESTADO_ACEPTADA = "Aceptada";
	public static final String VALUE_ESTADO_RECHAZADA = "Rechazada";
    public static final String VALUE_ESTADO_CANCELADA = "Cancelada";

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
