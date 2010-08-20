CREATE TABLE CAT_REGISTRO_IMAGEN
(
	X_REGISTRO_IMAGEN NUMBER(19),
	T_IMAGEN BLOB,
	T_IMAGEN_NOMBRE_FICHERO VARCHAR2(255),
	T_IMAGEN_CONTENT_TYPE VARCHAR2(255)
);


ALTER TABLE CAT_REGISTRO_IMAGEN ADD (
  CONSTRAINT CAT_REGISTRO_IMAGEN_PK
 PRIMARY KEY
 (X_REGISTRO_IMAGEN));

ALTER TABLE CAT_REGISTRO
ADD (REG_X_REGISTRO_IMAGEN NUMBER(19));


ALTER TABLE CAT_REGISTRO ADD
(
	CONSTRAINT FK_REG_X_REGISTRO_IMAGEN
	FOREIGN KEY (REG_X_REGISTRO_IMAGEN)
	REFERENCES CAT_REGISTRO_IMAGEN (X_REGISTRO_IMAGEN)
);


