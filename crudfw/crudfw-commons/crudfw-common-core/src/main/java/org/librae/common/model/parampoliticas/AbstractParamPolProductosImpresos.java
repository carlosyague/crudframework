/**
 *
 */
package org.librae.common.model.parampoliticas;

import org.librae.common.model.BaseObject;

/**
 * @author amDelcampo
 */
public class AbstractParamPolProductosImpresos extends BaseObject {

    /**
     * Constantes para los tipos de productos impresos:carnet, tejuelos, etc.
     */
    public static final int VALUE_PRD_CARNET                   = 1;
    public static final int VALUE_PRD_CODIGOBARRAS             = 2;
    public static final int VALUE_PRD_RECIBOPRESTAMO					 = 3;
    public static final int VALUE_PRD_RECLAMACIONPRESTAMO      = 4;
    public static final int VALUE_PRD_TEJUELO                  = 5;
    public static final int VALUE_PRD_CODIGOBARRAS_TEJUELO     = 6;
    public static final int VALUE_PRD_RECIBODEVOLUCION				 = 7;

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
