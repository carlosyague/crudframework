DROP TABLE ADM_VIEW_BIBLIOTECA;

CREATE OR REPLACE FORCE VIEW ADM_VIEW_BIBLIOTECA (X_VIEW_BIBLIOTECA, BIBLIOTECA_X_PADRE, T_DESCRIPCION,T_CODIGO, TIPO_BIBLIOTECA)
                        AS
  SELECT b.X_BIBLIOTECA AS X_VIEW_BIBLIOTECA,
    b.BIB_X_BIBLIOTECA  AS BIBLIOTECA_X_PADRE,
    b.T_BIBLIOTECA      AS T_DESCRIPCION,
	b.C_BIBLIOTECA		AS T_CODIGO,
    t.C_TIPO_BIBLIOTECA AS TIPO_BIBLIOTECA
  FROM ADM_BIBLIOTECAS b,
    ADM_TIPOS_BIBLIOTECA t
  WHERE b.TIP_X_TIPO_BIBLIOTECA=t.X_TIPO_BIBLIOTECA;

DROP TABLE CIR_VIEW_PRESTAMOS_DIA;

CREATE OR REPLACE VIEW CIR_VIEW_PRESTAMOS_DIA AS

select concat(p.X_PRESTAMO, 'N') as X_CIR_VIEW_PRESTAMOS_DIA, p.EJEMPLAR as EJEMPLAR_X_EJEMPLAR, tp.X_TIPO_PRESTAMO AS TIPO_PRESTAMO_X_TIPO_PRESTAMO, 'N' as L_DEVUELTO
from CIR_PRESTAMOS p, CIR_TIPO_PRESTAMO tp
where tp.X_TIPO_PRESTAMO = p.T_PRESTAMO_X_T_PRESTAMO
and tp.C_TIPO_PRESTAMO = 'SA'

UNION ALL

select concat(ph.X_PRESTAMO_HIST, 'S') as X_CIR_VIEW_PRESTAMOS_DIA, ph.EJE_X_EJEMPAR as EJEMPLAR_X_EJEMPLAR, tp.X_TIPO_PRESTAMO AS TIPO_PRESTAMO_X_TIPO_PRESTAMO, 'S' as L_DEVUELTO
from CIR_PRESTAMOS_HIST ph, CIR_TIPO_PRESTAMO tp
where tp.X_TIPO_PRESTAMO = ph.T_PRESTAMO_X_T_PRESTAMO
and tp.C_TIPO_PRESTAMO = 'SA'
and to_char(ph.F_DEVOLUCION,'DD/MM/YYYY') = to_char(CURRENT_DATE,'DD/MM/YYYY');


DROP TABLE VIEW_CAT_LISTADO_EJEMPLARES;

CREATE OR REPLACE VIEW VIEW_CAT_LISTADO_EJEMPLARES
(X_VIEW_CAT_LISTADO_EJEMPLARES, T_SITUACION, N_CODIGO_BARRAS, R_AUTOR, R_TITULO,
 T_BIBLIOTECA, T_LOCALIZACION, T_TIPO, T_SIGNATURA, N_NUMERO_LECTOR,
 T_NOMBRE, L_PRINCIPAL, BIB_X_BIBLIOTECA, X_EJEMPLAR_SITUACION, X_EJEMPLAR_TIPO,
 EJE_X_EJEMPLAR_ESTADO, PRO_X_PROCEDENCIA, EJE_X_EJEMPLAR_SOPORTE, T_SIGNATURA_SUPLEMENTARIA, C_VOLUMEN,
 F_FECHA_REGISTRO, F_FECHA_INGRESO_INVENTARIO, F_FECHA_CAMBIO_SITUACION, F_FECHA_PRESTAMO, R_EDITORIAL,
 R_MATERIA, R_INTERNACIONAL_NUMBER, L_VISIBLE_OPAC, F_FECHA_DEVOLUCION, X_EJEMPLAR_LOCALIZACION,
 T_ESTADO, T_PROCEDENCIA, T_SOPORTE, N_NUMERO_PRESTAMOS, N_NUMERO_REGISTRO,
 N_TELEFONO, PRE_X_PRESTAMO, RES_X_RESERVA)
AS
SELECT e.x_ejemplar, es.t_situacion, e.n_codigo_barras, r.r_autor,
          r.r_titulo, b.t_biblioteca, el.t_localizacion, et.t_tipo,
          e.t_signatura,
          (SELECT l.n_numero_lector
             FROM cir_prestamos p, lec_lectores l
            WHERE e.x_ejemplar = p.ejemplar
              AND p.lec_x_lector = l.x_lector) n_numero_lector,
          (SELECT    l.t_nombre
                  || ' '
                  || l.t_primer_apellido
                  || ' '
                  || l.t_segundo_apellido
             FROM cir_prestamos p, lec_lectores l
            WHERE e.x_ejemplar = p.ejemplar AND p.lec_x_lector = l.x_lector)
                                                                     t_nombre,
          rxe.l_principal, e.bib_x_biblioteca, es.x_ejemplar_situacion,
          et.x_ejemplar_tipo, e.eje_x_ejemplar_estado, e.pro_x_procedencia,
          e.eje_x_ejemplar_soporte, e.t_signatura_suplementaria, e.c_volumen,
          e.f_fecha_registro, e.f_fecha_ingreso_inventario,
          e.f_fecha_cambio_situacion,
          (SELECT p.f_prestamo
             FROM cir_prestamos p, lec_lectores l
            WHERE e.x_ejemplar = p.ejemplar
              AND p.lec_x_lector = l.x_lector) f_fecha_prestamo,
          r.r_editorial, r.r_materia, r.r_internacional_number,
          e.l_visible_opac,
          (SELECT p.f_devolucion_pol
             FROM cir_prestamos p, lec_lectores l
            WHERE e.x_ejemplar = p.ejemplar
              AND p.lec_x_lector = l.x_lector) f_fecha_devolucion,
          el.x_ejemplar_localizacion,
          (SELECT ces.t_estado
             FROM cat_ejemplar_estados ces
            WHERE x_ejemplar_estado = e.eje_x_ejemplar_estado) t_estado,
          (SELECT ces.t_procedencia
             FROM cat_procedencias ces
            WHERE x_procedencia = e.pro_x_procedencia) t_procedencia,
          (SELECT ces.t_soporte
             FROM cat_ejemplar_soportes ces
            WHERE x_ejemplar_soporte = e.eje_x_ejemplar_soporte) t_soporte,
          e.n_numero_prestamos, e.n_numero_registro,
          (SELECT (SELECT ll.t_telefono
                     FROM lec_lector_telefonos ll
                    WHERE ll.n_orden = 1
                      AND ll.lec_x_lector = l.x_lector)
                                                       n_telefono
             FROM cir_prestamos p, lec_lectores l
            WHERE e.x_ejemplar = p.ejemplar
              AND p.lec_x_lector = l.x_lector) n_telefono,
           (SELECT p.x_prestamo
             FROM cir_prestamos p, lec_lectores l
             WHERE e.x_ejemplar = p.ejemplar
             AND p.lec_x_lector = l.x_lector) pre_x_prestamo,
           (SELECT p.x_reserva
             FROM cir_reservas p, lec_lectores l
             WHERE e.x_ejemplar = p.eje_x_ejemplar_id
             AND p.lec_x_lector = l.x_lector) res_x_reserva
     FROM cat_ejemplares e,
          cat_registro r,
          cat_registro_x_cat_ejemplar rxe,
          cat_ejemplar_localizaciones el,
          cat_ejemplar_situaciones es,
          cat_ejemplar_tipo et,
          adm_bibliotecas b
    WHERE rxe.reg_x_registro = r.x_registro
      AND e.x_ejemplar = rxe.eje_x_ejemplar
      AND e.bib_x_biblioteca = b.x_biblioteca
      AND e.eje_x_ejemplar_tipo = et.x_ejemplar_tipo
      AND e.eje_x_ejemplar_localizacion = el.x_ejemplar_localizacion
      AND e.eje_x_ejemplar_situacion = es.x_ejemplar_situacion
      AND rxe.l_principal = 1;


DROP TABLE VIEW_CAT_REGISTRO_BIBLIOTECAS;

CREATE OR REPLACE VIEW VIEW_CAT_REGISTRO_HIJOS AS
    SELECT DISTINCT(B.X_BIBLIOTECA) HIJO,
           B.BIB_X_BIBLIOTECA PADRE,
           RE.REG_X_REGISTRO ID_REGISTRO
    FROM CAT_REGISTRO_X_CAT_EJEMPLAR RE,
         CAT_EJEMPLARES E,
         ADM_BIBLIOTECAS B
    WHERE RE.EJE_X_EJEMPLAR=E.X_EJEMPLAR
    AND   E.BIB_X_BIBLIOTECA=B.X_BIBLIOTECA;


CREATE OR REPLACE VIEW VIEW_CAT_REGISTRO_ABUELOS AS
    SELECT V.HIJO,
           V.PADRE,
           AB.BIB_X_BIBLIOTECA ABUELO,
           V.ID_REGISTRO
    FROM ADM_BIBLIOTECAS AB RIGHT OUTER JOIN
         VIEW_CAT_REGISTRO_HIJOS V
    ON V.PADRE=AB.X_BIBLIOTECA;


CREATE OR REPLACE VIEW VIEW_CAT_REGISTRO_BISABUELOS AS
    SELECT  Z.HIJO,
            Z.PADRE,
            Z.ABUELO,
            ABI.BIB_X_BIBLIOTECA BISABUELO,
            Z.ID_REGISTRO
    FROM ADM_BIBLIOTECAS ABI RIGHT OUTER JOIN
        VIEW_CAT_REGISTRO_ABUELOS Z
    ON Z.ABUELO=ABI.X_BIBLIOTECA;



CREATE OR REPLACE VIEW VIEW_CAT_REGISTRO_TATARABUELOS AS
    SELECT F.HIJO,
           F.PADRE,
           F.ABUELO,
           F.BISABUELO,
           ADM.BIB_X_BIBLIOTECA TATARABUELO,
           F.ID_REGISTRO
    FROM ADM_BIBLIOTECAS ADM RIGHT OUTER JOIN
        VIEW_CAT_REGISTRO_BISABUELOS F
    ON F.BISABUELO=ADM.X_BIBLIOTECA;



CREATE OR REPLACE VIEW VIEW_CAT_REG_MASTARABUELOS AS
   SELECT G.HIJO,
          G.PADRE,
          G.ABUELO,
          G.BISABUELO,
          G.TATARABUELO,
          ABIB.BIB_X_BIBLIOTECA MATUSALEN,
          G.ID_REGISTRO
   FROM ADM_BIBLIOTECAS ABIB RIGHT OUTER JOIN
       VIEW_CAT_REGISTRO_TATARABUELOS G
   ON G.TATARABUELO=ABIB.X_BIBLIOTECA;


CREATE OR REPLACE VIEW VIEW_CAT_REGISTRO_BIBLIOTECAS
(ID_REGISTRO, RAMA_COMPLETA)
AS
   SELECT  L.ID_REGISTRO,
          CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(L.HIJO,' '),L.PADRE),' '),L.ABUELO),' '),L.BISABUELO),' '),L.TATARABUELO),' '),L.MATUSALEN),' '),CAS.BIB_X_BIBLIOTECA) RAMA_COMPLETA
   FROM ADM_BIBLIOTECAS CAS RIGHT OUTER JOIN
        VIEW_CAT_REG_MASTARABUELOS L
   ON L.MATUSALEN=CAS.X_BIBLIOTECA;
