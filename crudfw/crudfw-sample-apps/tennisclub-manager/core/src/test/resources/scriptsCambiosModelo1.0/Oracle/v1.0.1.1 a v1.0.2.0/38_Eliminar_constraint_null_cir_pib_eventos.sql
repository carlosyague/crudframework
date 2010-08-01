DECLARE
    nombreConstraint varchar2(100);
BEGIN
    select constraint_name into nombreConstraint
    from all_cons_columns
    where table_name = 'CIR_PIB_EVENTOS'
    and column_name = 'PET_X_PIB_PETICION'
    and CONSTRAINT_NAME like '%SYS_%';

    EXECUTE IMMEDIATE 'ALTER TABLE cir_pib_eventos DROP CONSTRAINT '||nombreConstraint;
END;