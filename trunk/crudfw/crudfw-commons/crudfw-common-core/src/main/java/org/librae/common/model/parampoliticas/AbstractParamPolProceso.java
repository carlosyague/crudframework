package org.librae.common.model.parampoliticas;

import org.librae.common.model.BaseObject;

public class AbstractParamPolProceso extends BaseObject {

    /**
	 *
	 */
    private static final long  serialVersionUID       = 1L;
     /**
     * Constantes para Tipo Patrón del Proceso: Nunca.
     */
    public static final Long VALUE_PATRON_NUNCA     = Long.valueOf(0);
    /**
     * Constantes para Tipo Patrón del Proceso: Intervalos Regulares.
     */
    public static final Long VALUE_PATRON_INTERVALO = Long.valueOf(1);
    /**
     * Constantes para Tipo Patrón del Proceso: Una vez.
     */
    public static final Long VALUE_PATRON_UNICO     = Long.valueOf(2);
    /**
     * Constantes para Tipo Patrón del Proceso: Planificación Semanal.
     */
    public static final Long VALUE_PATRON_SEMANAL   = Long.valueOf(3);
    /**
     * Constantes para Tipo Patrón del Proceso: Planificación Mensual.
     */
    public static final Long VALUE_PATRON_MENSUAL   = Long.valueOf(4);
    /**
     * Constantes para Tipo Patrón del Proceso: Patrón Cron.
     */
    public static final Long VALUE_PATRON_CRON      = Long.valueOf(5);


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
