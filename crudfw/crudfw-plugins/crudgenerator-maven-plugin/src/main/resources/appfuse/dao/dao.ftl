package ${basepackage}.dao;

<#assign classbody>
<#assign pojoName = pojo.importType(pojo.getDeclarationName())>
import ${pojo.packageName}.${pojoName};

/**
 * Interfaz del DAO para la entidad ${pojoName}
 *
 * @author ${authorName}
 */
public interface I${pojoName}DAO extends ${pojo.importType("${appfusepackage}.dao.GenericDAO")}<${pojoName}, ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}> {
</#assign>
${pojo.generateImports()
}
${classbody}
}