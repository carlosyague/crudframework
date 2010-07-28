package org.librae.common.dao.hibernate.dialects;

import org.hibernate.MappingException;

/**
 * Clase que representa el dialecto Oracle para hibernate. Hace la misma funcion
 * que la estantdar Oracle10gDialect pero tiene mas funciones.
 */
public class OracleDialect extends org.hibernate.dialect.Oracle10gDialect {

    public OracleDialect() {
        super();
    }

    protected String getIdentityColumnString() throws MappingException {
        return "";
    }
}
