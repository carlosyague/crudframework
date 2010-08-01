package org.librae.common.model.parampoliticas;

import java.io.Serializable;

public interface IParamPolEjemplarTipo extends Serializable {

    public static final String  PROPTY_NAME_MAX_PRESTAMOS                = "numMaximoPrestamos";
    public static final String  COLUMN_NAME_MAXPRESTAMOS                 = "N_MAX_PRESTAMOS";
    public static final Integer DEFAULT_VALUE_MAXPRESTAMOS               = 5;

    public static final String  PROPTY_NAME_RENOVAR_EJEMPLAR_RESERVADO   = "renovarEjemplarReservado";
    public static final String  COLUMN_NAME_RENOVAR_EJEMPLAR_RESERVADO   = "L_RENOVAR_EJEMPLAR_RESERVADO";
    public static final Boolean DEFAULT_VALUE_RENOVAR_EJEMPLAR_RESERVADO = Boolean.FALSE;
}
