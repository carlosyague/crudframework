package org.librae.common.model.parampoliticas;

import org.librae.common.model.BaseObject;

public class AbstractParamPolNotificacion extends BaseObject {

    /**
	 *
	 */
    private static final long  serialVersionUID                  = 1L;

    public static final String CRITERIA_FECHA_ALTA_HASTA         = "filtroFechaAltaHasta";
    public static final String CRITERIA_FECHA_ALTA_DESDE         = "filtroFechaAltaDesde";
    public static final String CRITERIA_FECHA_COMUNICACION_HASTA = "filtroFechaComunicHasta";
    public static final String CRITERIA_FECHA_COMUNICACION_DESDE = "filtroFechaComunicDesde";
    public static final String CRITERIA_NOTIFICACION_PENDIENTE   = "filtroNotifPendientes";

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
