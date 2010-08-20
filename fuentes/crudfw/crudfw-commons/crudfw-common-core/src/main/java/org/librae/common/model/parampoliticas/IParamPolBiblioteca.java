package org.librae.common.model.parampoliticas;

import java.io.Serializable;

public interface IParamPolBiblioteca extends Serializable {

    public static final String  PROPTY_NAME_ADMITE_DEVOLUCION_MISMA_BI   = "admiteDevolucionMismaBiblioteca";
    public static final String  COLUMN_NAME_ADMITE_DEVOLUCION_MISMA_BI   = "L_ADMITE_DEVOLUCION_MISMA_BIB";
    public static final Boolean DEFAULT_VALUE_ADMITE_DEVOLUCION_MISMA_BI = Boolean.TRUE;

    public static final String  PROPTY_NAME_ADMITE_PRESTAMO_MISMA_BIBL   = "admitePrestamoMismaBiblioteca";
    public static final String  COLUMN_NAME_ADMITE_PRESTAMO_MISMA_BIBL   = "L_ADMITE_PRESTAMO_MISMA_BIB";
    public static final Boolean DEFAULT_VALUE_ADMITE_PRESTAMO_MISMA_BIBL = Boolean.TRUE;

    public static final String  PROPTY_NAME_ADMITE_RENOVACION_MISMA_BI   = "admiteRenovacionMismaBiblioteca";
    public static final String  COLUMN_NAME_ADMITE_RENOVACION_MISMA_BI   = "L_ADMITE_RENOVACION_MISMA_BIB";
    public static final Boolean DEFAULT_VALUE_ADMITE_RENOVACION_MISMA_BI = Boolean.FALSE;

    public static final String  PROPTY_NAME_CUMPLIMENTAR_RESERV_EN_DEV   = "cumplimentarReservasEnDevoluciones";
    public static final String  COLUMN_NAME_CUMPLIMENTAR_RESERV_EN_DEV   = "L_CUMPLIMENTAR_RESERVAS_EN_DEV";
    public static final Boolean DEFAULT_VALUE_CUMPLIMENTAR_RESERV_EN_DEV = Boolean.TRUE;

    public static final String  PROPTY_NAME_EMITIR_RECIBO_EN_DEVOLUCIO   = "emitirReciboEnDevoluciones";
    public static final String  COLUMN_NAME_EMITIR_RECIBO_EN_DEVOLUCIO   = "L_EMITIR_RECIBO_EN_DEV";
    public static final Boolean DEFAULT_VALUE_EMITIR_RECIBO_EN_DEVOLUCIO = Boolean.FALSE;

    public static final String  PROPTY_NAME_EMITIR_RECIBO_EN_RENOVACIO   = "emitirReciboEnRenovaciones";
    public static final String  COLUMN_NAME_EMITIR_RECIBO_EN_RENOVACIO   = "L_EMITIR_RECIBO_EN_RENOV";
    public static final Boolean DEFAULT_VALUE_EMITIR_RECIBO_EN_RENOVACIO = Boolean.FALSE;

    public static final String  PROPTY_NAME_EMITIR_RECIBO_EN_RESERVAS    = "emitirReciboEnReservas";
    public static final String  COLUMN_NAME_EMITIR_RECIBO_EN_RESERVAS    = "L_EMITIR_RECIBO_EN_RESERVAS";
    public static final Boolean DEFAULT_VALUE_EMITIR_RECIBO_EN_RESERVAS  = Boolean.FALSE;

    public static final String  PROPTY_NAME_PERMITE_DEV_MISMA_BIB        = "permiteDevolucionMismaBiblioteca";
    public static final String  COLUMN_NAME_PERMITE_DEV_MISMA_BIB        = "L_PERMITE_DEVOLUCION_MISMA_BIB";
    public static final Boolean DEFAULT_VALUE_PERMITE_DEV_MISMA_BIB      = Boolean.TRUE;

    public static final String  PROPTY_NAME_PERMITE_PRESTAMO_MISMA_BIB   = "permitePrestamoMismaBiblioteca";
    public static final String  COLUMN_NAME_PERMITE_PRESTAMO_MISMA_BIB   = "L_PERMITE_PRESTAMO_MISMA_BIB";
    public static final Boolean DEFAULT_VALUE_PERMITE_PRESTAMO_MISMA_BIB = Boolean.TRUE;

    public static final String  PROPTY_NAME_PERMITE_RENOV_MISMA_BIB      = "permiteRenovacionMismaBiblioteca";
    public static final String  COLUMN_NAME_PERMITE_RENOV_MISMA_BIB      = "L_PERMITE_RENOVACION_MISMA_BIB";
    public static final Boolean DEFAULT_VALUE_PERMITE_RENOV_MISMA_BIB    = Boolean.FALSE;

    public static final String  PROPTY_NAME_PRESTAMO_RENOVACIO_SI_SUSP   = "prestamoRenovacionSiSuspendido";
    public static final String  COLUMN_NAME_PRESTAMO_RENOVACIO_SI_SUSP   = "L_PRESTAMO_RENOV_SI_SUSPENDIDO";
    public static final Boolean DEFAULT_VALUE_PRESTAMO_RENOVACIO_SI_SUSP = Boolean.FALSE;

    public static final String  PROPTY_NAME_RESERVA_SI_SUSPENDIDO        = "reservaSiSuspendido";
    public static final String  COLUMN_NAME_RESERVA_SI_SUSPENDIDO        = "L_RESERVA_SI_SUSPENDIDO";
    public static final Boolean DEFAULT_VALUE_RESERVA_SI_SUSPENDIDO      = Boolean.FALSE;

    public static final String  PROPTY_NAME_NUMERO_MAX_PRESTAMOS_DOM     = "numeroMaxPrestamosDom";
    public static final String  COLUMN_NAME_NUMERO_MAX_PRESTAMOS_DOM     = "N_MAX_PRESTAMOS_DOM";
    public static final Long    DEFAULT_VALUE_NUMERO_MAX_PRESTAMOS_DOM   = null;

    public static final String  PROPTY_NAME_NUMERO_MAX_RESERV_POR_COLA   = "numeroMaxReservasPorCola";
    public static final String  COLUMN_NAME_NUMERO_MAX_RESERV_POR_COLA   = "N_MAX_RESERVAS_POR_COLA";
    public static final Long    DEFAULT_VALUE_NUMERO_MAX_RESERV_POR_COLA = 50L;
}
