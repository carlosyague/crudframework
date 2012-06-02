<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>

package ${basepackage}.service.impl;

import ${basepackage}.dao.I${pojo.shortName}DAO;
import ${basepackage}.model.${pojo.shortName};
import ${basepackage}.service.I${pojo.shortName}Manager;
import ${commonpackage}.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: I${pojo.shortName}DAO <br/>Entidad: ${pojo.shortName}
 *
 * @author ${authorName}
 */
public class ${pojo.shortName}ManagerImpl extends GenericManagerImpl<${pojo.shortName}, ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}> implements I${pojo.shortName}Manager {
    I${pojo.shortName}DAO ${pojoNameLower}Dao;


    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public ${pojo.shortName}ManagerImpl(I${pojo.shortName}DAO ${pojoNameLower}Dao) {
        super(${pojoNameLower}Dao);
        this.${pojoNameLower}Dao = ${pojoNameLower}Dao;
    }
}