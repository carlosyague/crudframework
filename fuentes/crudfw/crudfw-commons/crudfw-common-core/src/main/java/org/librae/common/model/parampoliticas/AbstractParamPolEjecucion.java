package org.librae.common.model.parampoliticas;

import org.librae.common.model.BaseObject;

public class AbstractParamPolEjecucion extends BaseObject {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constantes para Fecha de Inicio Hasta
     */
    public static final String CRITERIA_FECHA_INICIO_HASTA = "fechaInicioHasta";
    /**
     * Constantes para Fecha de Inicio Desde
     */
    public static final String CRITERIA_FECHA_INICIO_DESDE = "fechaInicioDesde";
    /**
     * Constantes para Fecha de Fin Hasta
     */
    public static final String CRITERIA_FECHA_FIN_HASTA = "fechaFinHasta";
    /**
     * Constantes para Fecha de Fin Desde
     */
    public static final String CRITERIA_FECHA_FIN_DESDE = "fechaFinDesde";


    /**
     * Constantes para Estado de la Ejecución: terminado sin errores.
     */
    public static final String VALUE_ESTADO_OK        = "Terminado sin errores";
    /**
     * Constantes para Estado de la Ejecución: terminado con errores.
     */
    public static final String VALUE_ESTADO_KO        = "Terminado con errores";
    /**
     * Constantes para Estado de la Ejecución: En ejecución.
     */
    public static final String VALUE_ESTADO_EJECUCION = "En Ejecución";

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
