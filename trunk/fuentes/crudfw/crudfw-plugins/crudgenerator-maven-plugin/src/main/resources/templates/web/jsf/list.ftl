<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>

package ${basepackage}.webapp.action;

import java.io.Serializable;
import java.util.List;

import ${commonpackage}.webapp.action.BasePage;
<#if genericcore>
import ${pojo.packageName}.${pojo.shortName};
import ${commonpackage}.service.GenericManager;
<#else>
import ${basepackage}.service.I${pojo.shortName}Manager;
</#if>

/**
 * Action-JSF asociado a los listados de la entidad ${pojo.shortName}
 *
 * @author ${authorName}
 */
public class ${pojo.shortName}ListAction extends BasePage implements Serializable {
<#if genericcore>
    private GenericManager<${pojo.shortName}, ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}> ${pojoNameLower}Manager;
<#else>
    private I${pojo.shortName}Manager ${pojoNameLower}Manager;
</#if>

<#if genericcore>
    public GenericManager<${pojo.shortName}, ${identifierType}> get${pojo.shortName}Manager() {
<#else>
    public I${pojo.shortName}Manager get${pojo.shortName}Manager() {
</#if>
        return this.${pojoNameLower}Manager;
    }

<#if genericcore>
    public void set${pojo.shortName}Manager(GenericManager<${pojo.shortName}, ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}> ${pojoNameLower}Manager) {
<#else>
    public void set${pojo.shortName}Manager(I${pojo.shortName}Manager ${pojoNameLower}Manager) {
</#if>
        this.${pojoNameLower}Manager = ${pojoNameLower}Manager;
    }

    public ${pojo.shortName}ListAction() {
        setSortColumn("${pojo.identifierProperty.name}"); // sets the default sort column
    }

    public List get${util.getPluralForWord(pojo.shortName)}() {
        return sort(${pojoNameLower}Manager.getAll());
    }
}

