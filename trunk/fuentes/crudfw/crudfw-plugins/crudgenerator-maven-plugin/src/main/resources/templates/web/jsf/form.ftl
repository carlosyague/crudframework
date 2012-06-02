<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<#assign getIdMethodName = pojo.getGetterSignature(pojo.identifierProperty)>
<#assign setIdMethodName = 'set' + pojo.getPropertyName(pojo.identifierProperty)>
<#assign identifierType = pojo.getJavaTypeName(pojo.identifierProperty, jdk5)>

package ${basepackage}.webapp.action;

import java.io.Serializable;

import ${pojo.packageName}.${pojo.shortName};
import ${commonpackage}.webapp.action.BasePage;
<#if genericcore>
import ${commonpackage}.service.GenericManager;
<#else>
import ${basepackage}.service.I${pojo.shortName}Manager;
</#if>

/**
 * Action-JSF asociado a los formularios de la entidad ${pojo.shortName}
 *
 * @author ${authorName}
 */
public class ${pojo.shortName}FormAction extends BasePage implements Serializable {
<#if genericcore>
    private GenericManager<${pojo.shortName}, ${identifierType}> ${pojoNameLower}Manager;
<#else>
    private I${pojo.shortName}Manager ${pojoNameLower}Manager;
</#if>
    private ${pojo.shortName} ${pojoNameLower} = new ${pojo.shortName}();
    private ${identifierType} ${pojo.identifierProperty.name};

    // getter & setter del manager

<#if genericcore>
    public GenericManager<${pojo.shortName}, ${identifierType}> get${pojo.shortName}Manager() {
<#else>
    public I${pojo.shortName}Manager get${pojo.shortName}Manager() {
</#if>
        return this.${pojoNameLower}Manager;
    }

<#if genericcore>
    public void set${pojo.shortName}Manager(GenericManager<${pojo.shortName}, ${identifierType}> ${pojoNameLower}Manager) {
<#else>
    public void set${pojo.shortName}Manager(I${pojo.shortName}Manager ${pojoNameLower}Manager) {
</#if>
        this.${pojoNameLower}Manager = ${pojoNameLower}Manager;
    }

    public ${pojo.shortName} get${pojo.shortName}() {
        return ${pojoNameLower};
    }

    public void set${pojo.shortName}(${pojo.shortName} ${pojoNameLower}) {
        this.${pojoNameLower} = ${pojoNameLower};
    }

    public ${identifierType} ${getIdMethodName}() {
        return this.${pojo.identifierProperty.name};
    }

    public void ${setIdMethodName}(${identifierType} ${pojo.identifierProperty.name}) {
        this.${pojo.identifierProperty.name} = ${pojo.identifierProperty.name};
    }

    public String delete() {
        ${pojoNameLower}Manager.remove(${pojoNameLower}.${getIdMethodName}());
        addMessage("${pojoNameLower}.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (${pojo.identifierProperty.name} != null && ${pojo.identifierProperty.name} != 0) {
            ${pojoNameLower} = ${pojoNameLower}Manager.get(${pojo.identifierProperty.name});
        } else {
            ${pojoNameLower} = new ${pojo.shortName}();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (${pojoNameLower}.${getIdMethodName}() == null || ${pojoNameLower}.${getIdMethodName}() == 0);
        ${pojoNameLower}Manager.save(${pojoNameLower});

        String key = (isNew) ? "${pojoNameLower}.added" : "${pojoNameLower}.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}