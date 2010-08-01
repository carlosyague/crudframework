
drop table adm_view_biblioteca;

CREATE OR REPLACE VIEW ADM_VIEW_BIBLIOTECA (X_VIEW_BIBLIOTECA, BIBLIOTECA_X_PADRE, T_DESCRIPCION,T_CODIGO, TIPO_BIBLIOTECA)
                        AS
  SELECT b.X_BIBLIOTECA AS X_VIEW_BIBLIOTECA,
    b.BIB_X_BIBLIOTECA  AS BIBLIOTECA_X_PADRE,
    b.T_BIBLIOTECA      AS T_DESCRIPCION,
	b.C_BIBLIOTECA		AS T_CODIGO,
    t.C_TIPO_BIBLIOTECA AS TIPO_BIBLIOTECA
  FROM ADM_BIBLIOTECAS b,
    ADM_TIPOS_BIBLIOTECA t
  WHERE b.TIP_X_TIPO_BIBLIOTECA=t.X_TIPO_BIBLIOTECA;


drop table CIR_VIEW_PRESTAMOS_DIA;

CREATE OR REPLACE VIEW CIR_VIEW_PRESTAMOS_DIA AS

select p.X_PRESTAMO || 'N' as X_CIR_VIEW_PRESTAMOS_DIA, e.X_EJEMPLAR as EJEMPLAR_X_EJEMPLAR, tp.X_TIPO_PRESTAMO AS TIPO_PRESTAMO_X_TIPO_PRESTAMO, 'N' as L_DEVUELTO
from CIR_PRESTAMOS p, CIR_TIPO_PRESTAMO tp, CAT_EJEMPLARES e
where tp.X_TIPO_PRESTAMO = p.T_PRESTAMO_X_T_PRESTAMO
and p.EJEMPLAR = e.X_EJEMPLAR
and tp.C_TIPO_PRESTAMO = 'SA'

UNION

select ph.X_PRESTAMO_HIST || 'S' as X_CIR_VIEW_PRESTAMOS_DIA, e.X_EJEMPLAR as EJEMPLAR_X_EJEMPLAR, tp.X_TIPO_PRESTAMO AS TIPO_PRESTAMO_X_TIPO_PRESTAMO, 'S' as L_DEVUELTO
from CIR_PRESTAMOS_HIST ph, CIR_TIPO_PRESTAMO tp, CAT_EJEMPLARES e
where tp.X_TIPO_PRESTAMO = ph.T_PRESTAMO_X_T_PRESTAMO
and ph.EJE_X_EJEMPAR = e.X_EJEMPLAR
and tp.C_TIPO_PRESTAMO = 'SA'
and ph.F_DEVOLUCION = CURRENT_DATE;

drop table VIEW_CAT_REGISTRO_BIBLIOTECAS;
