DROP TABLE BIB_DIG_REGISTRO_ACTIVO;

CREATE TABLE BIB_DIG_ACTIVO_DIGITAL (X_ACTIVO_DIGITAL NUMBER(19),
                                     T_AREA VARCHAR2(255),
                                     L_ESTADO NUMBER(1),
                                     F_FECHA_CREACION TIMESTAMP(6),
                                     F_FECHA_VINCULACION TIMESTAMP(6),
                                     T_MOD_CONTENIDO VARCHAR2(255),
                                     T_PID VARCHAR2(255),
                                     T_UID VARCHAR2(255),
                                     T_URL VARCHAR2(255),
                                     L_VISIBILIDAD NUMBER(1),
                                     REG_X_REGISTRO NUMBER(19));

ALTER TABLE BIB_DIG_ACTIVO_DIGITAL ADD (
  PRIMARY KEY
 (X_ACTIVO_DIGITAL)
    USING INDEX
    TABLESPACE TS_LIBRAE_DATOS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
               ));


ALTER TABLE BIB_DIG_ACTIVO_DIGITAL ADD (
  CONSTRAINT FK_REG_X_REGISTRO
 FOREIGN KEY (REG_X_REGISTRO)
 REFERENCES CAT_REGISTRO (X_REGISTRO));
