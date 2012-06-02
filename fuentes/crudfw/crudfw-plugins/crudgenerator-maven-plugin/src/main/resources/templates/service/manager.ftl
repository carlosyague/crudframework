package ${basepackage}.service;

import ${commonpackage}.service.GenericManager;
import ${basepackage}.model.${pojo.shortName};

/**
 * Interfaz del Manager <br/>DAO: I${pojo.shortName}DAO <br/>Entidad: ${pojo.shortName}
 *
 * @author ${authorName}
 */
public interface I${pojo.shortName}Manager extends GenericManager<${pojo.shortName}, ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}> {

}