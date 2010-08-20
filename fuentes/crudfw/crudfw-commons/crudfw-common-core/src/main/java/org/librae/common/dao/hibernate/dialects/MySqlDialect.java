package org.librae.common.dao.hibernate.dialects;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.exception.TemplatedViolatedConstraintNameExtracter;
import org.hibernate.exception.ViolatedConstraintNameExtracter;


/**
 * Clase que representa el dialecto MySql para hibernate
 * Hace la misma funcion que la estantdar MySQL5InnoDBDialect
 * pero permire obtener el nombre la posible constraint visolada
 * en el casod e excepciones de borrado
 *
 */
public class MySqlDialect extends org.hibernate.dialect.MySQL5InnoDBDialect{

    public MySqlDialect() {
        super();
        registerFunction("date_add_interval", new SQLFunctionTemplate(
                Hibernate.DATE, "date_add(?1, INTERVAL ?2 DAY)"));
    }
    
    public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter() {
        return extracter;
    }

    private static ViolatedConstraintNameExtracter extracter = new TemplatedViolatedConstraintNameExtracter() {
        protected Log          log           = LogFactory.getLog(this.getClass());
        /**
         * Extract the name of the violated constraint from the given SQLException.
         *
         * @author Denny Bartelt
         *
         * @param sqle The exception that was the result of the constraint violation.
         * @return The extracted constraint name.
         */
        public String extractConstraintName(SQLException sqle) {
            StringBuilder res = new StringBuilder();
            
            final int errorCode = JDBCExceptionHelper.extractErrorCode(sqle);            
            final String constraintName = sqle.getMessage().toUpperCase();

            if (errorCode == 1451) {
                res.append( extractUsingTemplate( "CONSTRAINT `", "`", constraintName) );
                if (res!=null && !"".equals(res)){
                    switch (errorCode) {
                        case 1451: res.insert(0, "D.") ; // res= "D."+res;
                                   break;
                    }
                }
            }
            
            if (res == null) {
                log.debug("Error [code="+errorCode+"] extrayendo el constraintName de "+ constraintName);
            }

            return res.toString();
        }
    };

}
