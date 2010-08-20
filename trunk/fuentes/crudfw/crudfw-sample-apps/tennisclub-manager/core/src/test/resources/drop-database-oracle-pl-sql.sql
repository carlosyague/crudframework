set serveroutput on

-- SELECT 'DROP TABLE '||table_name||' CASCADE CONSTRAINTS;' FROM user_tables
declare
cursor c_tablas_borrar is
select table_name from user_tables;
n_tablas_borrar number:=0;
tablas_borrar varchar2(200);

begin
select count(*) into n_tablas_borrar from user_tables;
while n_tablas_borrar>0 loop
	for cur_tablas_borrar in c_tablas_borrar loop
		--begin
		tablas_borrar:='DROP TABLE '||cur_tablas_borrar.table_name ||'  CASCADE CONSTRAINTS';
		execute immediate tablas_borrar;
		dbms_output.put_line('Tabla borrada: '|| cur_tablas_borrar.table_name);
		--exception
		--	when others then
		--		null;
		--end;
	end loop;
select count(*) into n_tablas_borrar from user_tables;
end loop;
exception
	when others then
		rollback;
		dbms_output.put_line(sqlerrm);
end;

-- SELECT 'DROP VIEW '||VIEW_NAME||';' FROM user_views
declare
cursor c_vistas_borrar is
select view_name from user_views;
n_vistas_borrar number:=0;
vistas_borrar varchar2(200);

begin
select count(*) into n_vistas_borrar from user_views;
while n_vistas_borrar>0 loop
	for cur_vistas_borrar in c_vistas_borrar loop
		--begin
		vistas_borrar:='DROP VIEW '||cur_vistas_borrar.view_name;
		execute immediate vistas_borrar;
		dbms_output.put_line('Vista borrada: '|| cur_vistas_borrar.view_name);
		--exception
		--	when others then
		--		null;
		--end;
	end loop;
select count(*) into n_vistas_borrar from user_views;
end loop;
exception
	when others then
		rollback;
		dbms_output.put_line(sqlerrm);
end;


-- SELECT 'DROP SEQUENCE '|| SEQUENCE_NAME||';' FROM user_sequences
declare
cursor c_secuencias_borrar is
select sequence_name from user_sequences;
n_secuencias_borrar number:=0;
secuencias_borrar varchar2(200);

begin
select count(*) into n_secuencias_borrar from user_sequences;
while n_secuencias_borrar>0 loop
	for cur_secuencias_borrar in c_secuencias_borrar loop
		--begin
		secuencias_borrar:='DROP SEQUENCE '||cur_secuencias_borrar.sequence_name;
		execute immediate secuencias_borrar;
		dbms_output.put_line('Secuencia borrada: '|| cur_secuencias_borrar.sequence_name);
		--exception
		--	when others then
		--		null;
		--end;
	end loop;
select count(*) into n_secuencias_borrar from user_sequences;
end loop;
exception
	when others then
		rollback;
		dbms_output.put_line(sqlerrm);
end;


-- SELECT 'DROP SYNONYM ' || SYNONYM_NAME ||';' FROM user_synonyms
declare
cursor c_sinonimos_borrar is
select synonym_name from user_synonyms;
n_sinonimos_borrar number:=0;
sinonimos_borrar varchar2(200);

begin
select count(*) into n_sinonimos_borrar from user_synonyms;
while n_sinonimos_borrar>0 loop
	for cur_sinonimos_borrar in c_sinonimos_borrar loop
		--begin
		sinonimos_borrar:='DROP SYNONYM '||cur_sinonimos_borrar.synonym_name;
		execute immediate sinonimos_borrar;
		dbms_output.put_line('Sinonimo borrado: '|| cur_sinonimos_borrar.synonym_name);
		--exception
		--	when others then
		--		null;
		--end;
	end loop;
select count(*) into n_sinonimos_borrar from user_synonyms;
end loop;
exception
	when others then
		rollback;
		dbms_output.put_line(sqlerrm);
end;

-- SELECT 'DROP FUNCTION ' || OBJECT_NAME ||';' FROM user_procedures
declare
cursor c_funciones_borrar is
select object_name from user_procedures;
n_funciones_borrar number:=0;
funciones_borrar varchar2(200);

begin
select count(*) into n_funciones_borrar from user_procedures;
while n_funciones_borrar>0 loop
	for cur_funciones_borrar in c_funciones_borrar loop
		--begin
		funciones_borrar:='DROP FUNCTION '||cur_funciones_borrar.object_name;
		execute immediate funciones_borrar;
		dbms_output.put_line('Funcion borrada: '|| cur_funciones_borrar.object_name);
		--exception
		--	when others then
		--		null;
		--end;
	end loop;
select count(*) into n_funciones_borrar from user_procedures;
end loop;
exception
	when others then
		rollback;
		dbms_output.put_line(sqlerrm);
end;

-- PURGE RECYCLEBIN;

declare
purge varchar2(20);

begin
	purge:='PURGE RECYCLEBIN';
	execute immediate purge;
	dbms_output.put_line('PURGE RECYCLEBIN realizado');
exception
	when others then
		rollback;
		dbms_output.put_line(sqlerrm);
end;


/ 
