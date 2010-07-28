declare
cursor c_constraint is
select table_name,constraint_name
from user_constraints
where constraint_type = 'R';

-- Variables
--
cnt_activado       number:=0;     -- OJO!!, cuenta realx las desactivadas
cnt_activado_saved number:=0;     -- Para guardar copia de la anterior
sql_string         varchar2(100);

BEGIN

select count(*) into cnt_activado
from user_constraints
where constraint_type='R' and status='DISABLED';

-- Mientras haya restricciones desactivadas, vamos activando
--
while cnt_activado>0 loop

    for cursor_constraint in c_constraint loop
        begin
        sql_string:='alter table '||cursor_constraint.table_name||' enable constraint '||cursor_constraint.constraint_name;
        execute immediate sql_string;

        -- Si ocurre un error de dependencias,lo ignoramos y seguimos activando hasta que termine el proceso
        --
        exception
            when others then
                null;
        end;
    end loop;

    -- Salvamos el valor que tenia cnt_activado al principio del for anterior
    --
    cnt_activado_saved := cnt_activado;


    -- Recontamos el num. restricciones que quedan por activar
    --
    select count(*) into cnt_activado
    from user_constraints
    where constraint_type='R' and status='DISABLED'
    ;

    -- Si en el anterior for no se consigui? activar ninguna restricci?n,
    -- salimos con error (1)
    --

end loop;

-- Si llega hasta aqui es que se han desactivado todas las restricciones de integridad
-- referencial (cnt_activado = 0). Salimos con exito.
--

exception
    when others then
        rollback;

END reactiva_constraints;
/