delete from CIR_NCIP_SERVICIOS;
alter table CIR_NCIP_SERVICIOS modify (C_NCIP_SERVICIO NUMBER(19));
ALTER TABLE CIR_NCIP_SERVICIOS add CONSTRAINT FK_C_NCIP_SERVICIO FOREIGN KEY(C_NCIP_SERVICIO) REFERENCES CIR_CODIGO_NCIP_SERVICIOS(X_NCIP_SERVICIOS);