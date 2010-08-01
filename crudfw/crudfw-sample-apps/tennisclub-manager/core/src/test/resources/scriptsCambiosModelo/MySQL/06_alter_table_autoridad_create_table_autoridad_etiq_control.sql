CREATE TABLE cat_autoridad_etiqueta_control(
  X_AUTORIDAD_ETIQUETA_CONTROL number(19) NOT NULL,
  C_CODIGO varchar(255),
  T_VALOR varchar(255),
  AUT_X_AUTORIDAD number(19) NOT NULL,
  CONSTRAINT pk_cat_autoridad 
  PRIMARY KEY  (X_AUTORIDAD_ETIQUETA_CONTROL),
  CONSTRAINT fk_cat_autoridad 
  FOREIGN KEY (AUT_X_AUTORIDAD) 
  REFERENCES cat_autoridad (X_AUTORIDAD)
);
alter table cat_autoridad add (X_TINTIT number(19));
alter table cat_autoridad_etiqueta drop (T_XML);
alter table cat_autoridad_etiqueta add (T_XML clob);
alter table cat_registro_etiqueta drop (T_XML);
alter table cat_registro_etiqueta add (T_XML clob);
