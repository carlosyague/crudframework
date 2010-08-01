package org.librae.common.dao.hibernate.dialects;

import org.hibernate.Hibernate;
import org.hibernate.dialect.function.SQLFunctionTemplate;

/**
 * Clase que representa el dialecto MySql para hibernate Hace la misma funcion
 * que la estantdar MySQL5InnoDBDialect pero permire obtener el nombre la
 * posible constraint visolada en el casod e excepciones de borrado
 */
public class PostgreSQLDialect extends
        org.hibernate.dialect.MySQL5InnoDBDialect {

    public PostgreSQLDialect() {
        super();
        registerFunction("date_add_interval", new SQLFunctionTemplate(
                Hibernate.DATE, "date_add(?1, INTERVAL ?2 DAY)"));
    }

}
