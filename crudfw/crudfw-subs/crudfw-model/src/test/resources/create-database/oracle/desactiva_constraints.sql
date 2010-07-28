declare
cursor c_constraint is
select table_name,constraint_name
from user_constraints
where constraint_type = 'R';

-- Variables
--
cnt_activado       number:=0;
cnt_activado_saved number:=0;
sql_string         varchar2(100);

BEGIN

select count(*) into cnt_activado
from user_constraints
where constraint_type='R' and status='ENABLED';

-- Mientras haya constraints activadas, seguimos desactivando
--
while cnt_activado>0 loop

    for cursor_constraint in c_constraint loop
        begin
        sql_string:='alter table '||cursor_constraint.table_name||' disable constraint '||cursor_constraint.constraint_name;
        execute immediate sql_string;
        exception
            when others then
                null;
        end;
    end loop;

    cnt_activado_saved := cnt_activado;

    select count(*) into cnt_activado
    from user_constraints
    where constraint_type='R' and status='ENABLED';

end loop;

exception
    when others then
        rollback;
        dbms_output.put_line(sqlcode ||' '||sqlerrm);

END desactiva_constraints;
