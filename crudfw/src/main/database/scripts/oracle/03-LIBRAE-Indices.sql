set serveroutput on
declare

cursor c_indices_mover
is	select index_name from user_indexes where index_type <> 'LOB';

indice_mover varchar2(200);
	
begin

for cur_indices_mover in c_indices_mover loop

	dbms_output.put_line('Indice reconstruido: '|| cur_indices_mover.index_name);

	indice_mover:='alter index '||cur_indices_mover.index_name ||' rebuild tablespace TS_LIBRAE_INDICES';


	execute immediate indice_mover;

	
end loop;

exception
	when others then
		rollback;
		dbms_output.put_line(sqlerrm);
end;
/ 