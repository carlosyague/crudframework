package ${basepackage}.dao.hibernate;

import ${basepackage}.model.${pojo.shortName};
import ${basepackage}.dao.I${pojo.shortName}DAO;
import ${commonpackage}.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad ${pojo.shortName}
 *
 * @author ${authorName}
 */
public class ${pojo.shortName}DAOImpl extends GenericDAOImpl<${pojo.shortName}, ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}> implements I${pojo.shortName}DAO {

    /**
     * Constructor del DAO
     */
    public ${pojo.shortName}DAOImpl() {
        super(${pojo.shortName}.class);
    }
}
