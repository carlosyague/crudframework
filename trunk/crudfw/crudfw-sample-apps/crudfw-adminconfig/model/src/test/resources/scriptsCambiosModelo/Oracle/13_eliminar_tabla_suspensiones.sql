ALTER TABLE cir_prestamos DROP COLUMN SUS_X_SUSPENSION;
ALTER TABLE cir_prestamos DROP COLUMN F_DETECTA_SUSPENSION;
ALTER TABLE cir_prestamos DROP COLUMN F_FIN_SUSPENSION;
ALTER TABLE cir_prestamos DROP COLUMN F_INI_SUSPENSION;

ALTER TABLE cir_reservas DROP CONSTRAINT FK_RESERVA_SUSPENSION;
ALTER TABLE cir_reservas DROP COLUMN SUS_X_SUSPENSION;
ALTER TABLE cir_reservas ADD (F_FIN_SUSPENSION TIMESTAMP(6),
                                     F_INI_SUSPENSION TIMESTAMP(6));
ALTER TABLE cir_reservas ADD (F_DETECTA_SUSPENSION TIMESTAMP(6));

ALTER TABLE cir_prestamos_hist DROP COLUMN SUS_X_SUSPENSION;
ALTER TABLE cir_prestamos_hist ADD (F_FIN_SUSPENSION TIMESTAMP(6),
								    F_INI_SUSPENSION TIMESTAMP(6));


DECLARE
    nombreConstraint varchar2(100);
BEGIN
    select constraint_name into nombreConstraint
    from all_cons_columns
    where table_name = 'CIR_PIB_PETICIONES'
    and column_name = 'SUS_X_SUSPENSION';

    EXECUTE IMMEDIATE 'ALTER TABLE cir_pib_peticiones DROP CONSTRAINT '||nombreConstraint;
END;

ALTER TABLE cir_pib_peticiones DROP COLUMN	SUS_X_SUSPENSION;

DECLARE
    nombreConstraint varchar2(100);
BEGIN
    select constraint_name into nombreConstraint
    from all_cons_columns
    where table_name = 'CIR_PET_PETICIONES'
    and column_name = 'SUS_X_SUSPENSION';

    EXECUTE IMMEDIATE 'ALTER TABLE cir_pet_peticiones DROP CONSTRAINT '||nombreConstraint;
END;

ALTER TABLE cir_pet_peticiones DROP COLUMN	SUS_X_SUSPENSION;

DROP TABLE cir_suspensiones;