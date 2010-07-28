DROP TABLE ADM_VIEW_BIBLIOTECA; 
 
CREATE OR REPLACE VIEW ADM_VIEW_BIBLIOTECA AS
  SELECT b.X_BIBLIOTECA AS X_VIEW_BIBLIOTECA,
    b.BIB_X_BIBLIOTECA  AS BIBLIOTECA_X_PADRE,
    b.T_BIBLIOTECA      AS T_DESCRIPCION,
	b.C_BIBLIOTECA		AS T_CODIGO,
    t.C_TIPO_BIBLIOTECA AS TIPO_BIBLIOTECA
 FROM ADM_BIBLIOTECAS b,ADM_TIPOS_BIBLIOTECA t
 WHERE b.TIP_X_TIPO_BIBLIOTECA=t.X_TIPO_BIBLIOTECA;

CREATE OR REPLACE VIEW CIR_VIEW_PRESTAMOS_DIA AS

SELECT CONCAT(P.X_PRESTAMO, 'N') AS X_CIR_VIEW_PRESTAMOS_DIA, P.EJEMPLAR AS EJEMPLAR_X_EJEMPLAR, TP.X_TIPO_PRESTAMO AS TIPO_PRESTAMO_X_TIPO_PRESTAMO, 'N' AS L_DEVUELTO
FROM CIR_PRESTAMOS P, CIR_TIPO_PRESTAMO TP
WHERE TP.X_TIPO_PRESTAMO = P.T_PRESTAMO_X_T_PRESTAMO
AND TP.C_TIPO_PRESTAMO = 'SA'

UNION

SELECT CONCAT(PH.X_PRESTAMO_HIST, 'S') AS X_CIR_VIEW_PRESTAMOS_DIA, E.X_EJEMPLAR AS EJEMPLAR_X_EJEMPLAR, TP.X_TIPO_PRESTAMO AS TIPO_PRESTAMO_X_TIPO_PRESTAMO, 'S' AS L_DEVUELTO
FROM CIR_PRESTAMOS_HIST PH, CIR_TIPO_PRESTAMO TP, CAT_EJEMPLARES E
WHERE TP.X_TIPO_PRESTAMO = PH.T_PRESTAMO_X_T_PRESTAMO
AND PH.EJE_X_EJEMPAR = E.X_EJEMPLAR
AND TP.C_TIPO_PRESTAMO = 'SA'
AND DATE_FORMAT(PH.F_DEVOLUCION,'%Y-%M-%D') = DATE_FORMAT(CURRENT_DATE,'%Y-%M-%D');



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
SELECT E.X_EJEMPLAR, ES.T_SITUACION, E.N_CODIGO_BARRAS, R.R_AUTOR,
          R.R_TITULO, B.T_BIBLIOTECA, EL.T_LOCALIZACION, ET.T_TIPO,
          E.T_SIGNATURA,
          (SELECT L.N_NUMERO_LECTOR
             FROM CIR_PRESTAMOS P, LEC_LECTORES L
            WHERE E.X_EJEMPLAR = P.EJEMPLAR
              AND P.LEC_X_LECTOR = L.X_LECTOR) N_NUMERO_LECTOR,
          (SELECT    L.T_NOMBRE
                  || ' '
                  || L.T_PRIMER_APELLIDO
                  || ' '
                  || L.T_SEGUNDO_APELLIDO
             FROM CIR_PRESTAMOS P, LEC_LECTORES L
            WHERE E.X_EJEMPLAR = P.EJEMPLAR AND P.LEC_X_LECTOR = L.X_LECTOR)
                                                                     T_NOMBRE,
          RXE.L_PRINCIPAL, E.BIB_X_BIBLIOTECA, ES.X_EJEMPLAR_SITUACION,
          ET.X_EJEMPLAR_TIPO, E.EJE_X_EJEMPLAR_ESTADO, E.PRO_X_PROCEDENCIA,
          E.EJE_X_EJEMPLAR_SOPORTE, E.T_SIGNATURA_SUPLEMENTARIA, E.C_VOLUMEN,
          E.F_FECHA_REGISTRO, E.F_FECHA_INGRESO_INVENTARIO,
          E.F_FECHA_CAMBIO_SITUACION,
          (SELECT P.F_PRESTAMO
             FROM CIR_PRESTAMOS P, LEC_LECTORES L
            WHERE E.X_EJEMPLAR = P.EJEMPLAR
              AND P.LEC_X_LECTOR = L.X_LECTOR) F_FECHA_PRESTAMO,
          R.R_EDITORIAL, R.R_MATERIA, R.R_INTERNACIONAL_NUMBER,
          E.L_VISIBLE_OPAC,
          (SELECT P.F_DEVOLUCION_POL
             FROM CIR_PRESTAMOS P, LEC_LECTORES L
            WHERE E.X_EJEMPLAR = P.EJEMPLAR
              AND P.LEC_X_LECTOR = L.X_LECTOR) F_FECHA_DEVOLUCION,
          EL.X_EJEMPLAR_LOCALIZACION,
          (SELECT CES.T_ESTADO
             FROM CAT_EJEMPLAR_ESTADOS CES
            WHERE X_EJEMPLAR_ESTADO = E.EJE_X_EJEMPLAR_ESTADO) T_ESTADO,
          (SELECT CES.T_PROCEDENCIA
             FROM CAT_PROCEDENCIAS CES
            WHERE X_PROCEDENCIA = E.PRO_X_PROCEDENCIA) T_PROCEDENCIA,
          (SELECT CES.T_SOPORTE
             FROM CAT_EJEMPLAR_SOPORTES CES
            WHERE X_EJEMPLAR_SOPORTE = E.EJE_X_EJEMPLAR_SOPORTE) T_SOPORTE,
          E.N_NUMERO_PRESTAMOS, E.N_NUMERO_REGISTRO,
          (SELECT (SELECT LL.T_TELEFONO
                     FROM LEC_LECTOR_TELEFONOS LL
                    WHERE LL.N_ORDEN = 1
                      AND LL.LEC_X_LECTOR = L.X_LECTOR)
                                                       N_TELEFONO
             FROM CIR_PRESTAMOS P, LEC_LECTORES L
            WHERE E.X_EJEMPLAR = P.EJEMPLAR
              AND P.LEC_X_LECTOR = L.X_LECTOR) N_TELEFONO,
           (SELECT P.X_PRESTAMO
             FROM CIR_PRESTAMOS P, LEC_LECTORES L
             WHERE E.X_EJEMPLAR = P.EJEMPLAR
             AND P.LEC_X_LECTOR = L.X_LECTOR) PRE_X_PRESTAMO,
           (SELECT P.X_RESERVA
             FROM CIR_RESERVAS P, LEC_LECTORES L
             WHERE E.X_EJEMPLAR = P.EJE_X_EJEMPLAR_ID
             AND P.LEC_X_LECTOR = L.X_LECTOR) RES_X_RESERVA
     FROM CAT_EJEMPLARES E,
          CAT_REGISTRO R,
          CAT_REGISTRO_X_CAT_EJEMPLAR RXE,
          CAT_EJEMPLAR_LOCALIZACIONES EL,
          CAT_EJEMPLAR_SITUACIONES ES,
          CAT_EJEMPLAR_TIPO ET,
          ADM_BIBLIOTECAS B
    WHERE RXE.REG_X_REGISTRO = R.X_REGISTRO
      AND E.X_EJEMPLAR = RXE.EJE_X_EJEMPLAR
      AND E.BIB_X_BIBLIOTECA = B.X_BIBLIOTECA
      AND E.EJE_X_EJEMPLAR_TIPO = ET.X_EJEMPLAR_TIPO
      AND E.EJE_X_EJEMPLAR_LOCALIZACION = EL.X_EJEMPLAR_LOCALIZACION
      AND E.EJE_X_EJEMPLAR_SITUACION = ES.X_EJEMPLAR_SITUACION
      AND RXE.L_PRINCIPAL = 1;



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


