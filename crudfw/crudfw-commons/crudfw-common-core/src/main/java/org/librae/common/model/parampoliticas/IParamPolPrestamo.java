package org.librae.common.model.parampoliticas;

import java.io.Serializable;

public interface IParamPolPrestamo extends Serializable {

    public static final String  PROPTY_NAME_CODIGO                       = "codigo";
    public static final String  COLUMN_NAME_CODIGO                       = "C_POLITICA_DOM";

    public static final String  PROPTY_NAME_NOMBRE                       = "nombre";
    public static final String  COLUMN_NAME_NOMBRE                       = "T_POLITICA_DOM";

    public static final String  PROPTY_NAME_DESCRIPCION_ALTERNATIVA      = "descripcionAlternativa";
    public static final String  COLUMN_NAME_DESCRIPCION_ALTERNATIVA      = "T_POLITICA_DOM_ALT";

    public static final String  PROPTY_NAME_PERMITIR_AUNQUE_RESERVADO    = "permitirAunqueReservado";
    public static final String  COLUMN_NAME_PERMITIR_AUNQUE_RESERVADO    = "L_PERMITIR_AUNQUE_RESERVADO";
    public static final Boolean DEFAULT_VALUE_PERMITIR_AUNQUE_RESERVADO  = Boolean.FALSE;

    public static final String  PROPTY_NAME_DIAS_PRESTAMO                = "diasPrestamo";
    public static final String  COLUMN_NAME_DIAS_PRESTAMO                = "N_DIAS_PRESTAMO";

    public static final String  PROPTY_NAME_DIAS_RENOVACION              = "diasRenovacion";
    public static final String  COLUMN_NAME_DIAS_RENOVACION              = "N_DIAS_RENOVACION";

    public static final String  PROPTY_NAME_MAX_RENOVACIONES             = "maxRenovaciones";
    public static final String  COLUMN_NAME_MAX_RENOVACIONES             = "N_MAX_RENOVACIONES";
    public static final Long    DEFAULT_VALUE_MAX_RENOVACIONES           = 3L;

    public static final String  PROPTY_NAME_DIAS_ANTES_FINAL             = "diasAntesFinal";
    public static final String  COLUMN_NAME_DIAS_ANTES_FINAL             = "N_DIAS_ANTES_FINAL";

    public static final String  PROPTY_NAME_DIAS_ANTES_SUSPENSION        = "diasAntesSuspension";
    public static final String  COLUMN_NAME_DIAS_ANTES_SUSPENSION        = "N_DIAS_ANTES_SUSPENSION";
    public static final Long    DEFAULT_VALUE_DIAS_ANTES_SUSPENSION      = 0L;

    public static final String  PROPTY_NAME_DIAS_SUSPENSION              = "diasSuspension";
    public static final String  COLUMN_NAME_DIAS_SUSPENSION              = "N_DIAS_SUSPENSION";

    public static final String  PROPTY_NAME_MAX_DIAS_SUSPENSION_EJEMPLAR = "maxDiasSuspensionEjemplar";
    public static final String  COLUMN_NAME_MAX_DIAS_SUSPENSION_EJEMPLAR = "N_MAX_DIAS_SUSPENSION_EJE";

    public static final String  PROPTY_NAME_DIAS_RECLAMACION_1A          = "diasReclamacion1a";
    public static final String  COLUMN_NAME_DIAS_RECLAMACION_1A          = "N_DIAS_RECLAMACION_1A";
    public static final Long    DEFAULT_VALUE_DIAS_RECLAMACION_1A        = 5L;

    public static final String  PROPTY_NAME_DIAS_RECLAMACION_2A          = "diasReclamacion2a";
    public static final String  COLUMN_NAME_DIAS_RECLAMACION_2A          = "N_DIAS_RECLAMACION_2A";
    public static final Long    DEFAULT_VALUE_DIAS_RECLAMACION_2A        = 10L;

    public static final String  PROPTY_NAME_DIAS_RECLAMACION_3A          = "diasReclamacion3a";
    public static final String  COLUMN_NAME_DIAS_RECLAMACION_3A          = "N_DIAS_RECLAMACION_3A";
    public static final Long    DEFAULT_VALUE_DIAS_RECLAMACION_3A        = 15L;

    public static final String  PROPTY_NAME_PRESTAMO_SI_DEV_MISMO_DIA    = "prestamoSiDevueltoMismoDia";
    public static final String  COLUMN_NAME_PRESTAMO_SI_DEV_MISMO_DIA    = "L_PRESTAMO_SI_DEV_MISMO_DIA";
    public static final Boolean DEFAULT_VALUE_PRESTAMO_SI_DEV_MISMO_DIA  = Boolean.TRUE;
}
