package org.librae.common.model.parampoliticas;

import java.io.Serializable;

public interface IParamPolTipoLector extends Serializable {

    public static final String  PROPTY_NAME_MAX_DIAS_SUSPENSION          = "maxDiasSuspension";
    public static final String  COLUMN_NAME_MAX_DIAS_SUSPENSION          = "N_MAX_DIAS_SUSPENSION";
    public static final Integer DEFAULT_VALUE_MAX_DIAS_SUSPENSION        = 30;

    public static final String  PROPTY_NAME_PRESTAMO_RENOV_SOBREPASADO   = "prestamoRenovSobrepasado";
    public static final String  COLUMN_NAME_PRESTAMO_RENOV_SOBREPASADO   = "L_PRESTAMO_RENOVACION_SI_SOBRE";
    public static final Boolean DEFAULT_VALUE_PRESTAMO_RENOV_SOBREPASADO = Boolean.FALSE;

    public static final String  PROPTY_NAME_PRESTAMO_EJEMPLARES_IDENT    = "prestamoEjemplaresIdent";
    public static final String  COLUMN_NAME_PRESTAMO_EJEMPLARES_IDENT    = "L_PRESTAMO_EJEMPLARES_IDENTICS";
    public static final Boolean DEFAULT_VALUE_PRESTAMO_EJEMPLARES_IDENT  = Boolean.FALSE;

    public static final String  PROPTY_NAME_MAX_PRESTAMOS_DOM            = "maxPrestamosDom";
    public static final String  COLUMN_NAME_MAX_PRESTAMOS_DOM            = "N_MAX_PRESTAMOS_DOM";
    public static final Integer DEFAULT_VALUE_MAX_PRESTAMOS_DOM          = 5;

    public static final String  PROPTY_NAME_RESERVA_SI_SOBREPASADOS      = "reservaSobrepasados";
    public static final String  COLUMN_NAME_RESERVA_SI_SOBREPASADOS      = "L_RESERVA_SI_SOBREPASADOS";
    public static final Boolean DEFAULT_VALUE_RESERVA_SI_SOBREPASADOS    = Boolean.FALSE;

    public static final String  PROPTY_NAME_MAX_RESERVAS                 = "maxReservas";
    public static final String  COLUMN_NAME_MAX_RESERVAS                 = "N_MAX_RESERVAS";
    public static final Integer DEFAULT_VALUE_MAX_RESERVAS               = 50;

    public static final String  PROPTY_NAME_DIAS_RES_EJEMPLARES_IDENT    = "reservaEjemplaresIdent";
    public static final String  COLUMN_NAME_RESERVA_EJEMPLARES_IDENT     = "L_RESERVA_EJEMPLARES_IDENTICOS";
    public static final Boolean DEFAULT_VALUE_RESERVA_EJEMPLARES_IDENT   = Boolean.TRUE;
}
