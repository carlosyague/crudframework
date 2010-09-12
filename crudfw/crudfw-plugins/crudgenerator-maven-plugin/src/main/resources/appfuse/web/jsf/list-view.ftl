<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:c="http://java.sun.com/jstl/core"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:t="http://myfaces.apache.org/tomahawk"
	xmlns:crud="http://www.uma.es/crudframework/jsf/facelets">
	
<ui:composition template="/templates/defaultTemplate.xhtml">
	<ui:define name="title">${'#'}{text['${pojoNameLower}List.title']}</ui:define>
	<ui:define name="body.id">${pojoNameLower}ListAction</ui:define>

	<ui:define name="body">
		<f:view>
			<f:loadBundle var="text" basename="${'#'}{${pojoNameLower}ListAction.bundleName}"/>

			<h:form id="edit${pojo.shortName}">
				
				<!-- PATH -->
				<table border="0" cellpadding="0" cellspacing="0" class="ruta">
					<tr>
						<td class="path">
							<a href="${'#'}{facesContext.externalContext.requestContextPath}/"
								title="${'#'}{common['home.title']}">
								<h:outputText value="${'#'}{common['home.title']}" />
							</a> > <h:outputText value=" ${'#'}{text['${pojoNameLower}List.title']}" />
						</td>
					</tr>
				</table>
				<!-- FIN PATH -->
				
				<br/>
				<h1><h:outputText value="${'#'}{text['${pojoNameLower}List.heading']}" /></h1>
			
				<crud:breadCrumb />

				<crud:tab tabLabel="${'#'}{text['${pojoNameLower}List.title']}">

					<t:panelGrid styleClass="botones" border="0" cellpadding="0"
						cellspacing="0" columns="1" columnClasses="blank">

						<h:commandLink action="add" id="add" immediate="true" styleClass="btn_barra">
							<span><h:outputText value="${'#'}{common['button.add']}" /></span>
						</h:commandLink>
					</t:panelGrid>

				<t:dataTable id="${util.getPluralForWord(pojoNameLower)}" var="entity" style="margin-top: 10px"
    				value="${'#'}{${pojoNameLower}ListAction.${util.getPluralForWord(pojoNameLower)}}" rows="10" sortColumn="${'#'}{${pojoNameLower}ListAction.sortColumn}"
    				sortAscending="${'#'}{${pojoNameLower}ListAction.ascending}" styleClass="listado"
    				headerClass="standardTable_Header" rowClasses="impar,par">

	<#foreach field in pojo.getAllPropertiesIterator()>
		<#if field.equals(pojo.identifierProperty)>
        			<crud:dataTableColumnEditLink columnName="${field.name}"
						columnLabel="${'#'}{text['${pojoNameLower}.${field.name}']}" rowValue="${'#'}{entity.${field.name}}"
						crudFormAction="${'#'}{${pojoNameLower}FormAction}" />
		<#elseif !c2h.isCollection(field) && !c2h.isManyToOne(field) && !c2j.isComponent(field)>
    		<#if field.value.typeName == "java.util.Date">
        	<#lt/>    <h:outputText value="${'#'}{${pojoNameLower}.${field.name}}" escape="true"/>
    		<#elseif field.value.typeName == "boolean">
        	<#lt/>    <h:selectBooleanCheckbox value="${'#'}{${pojoNameLower}Form.${pojoNameLower}.${field.name}}" id="${field.name}" disabled="disabled"/>
    		<#else>
        	<#lt/>  
        			<crud:dataTableColumn columnName="${field.name}"
						columnLabel="${'#'}{text['${pojoNameLower}.${field.name}']}"
						rowValue="${'#'}{entity.${field.name}}" />
    		</#if>
		</#if>
	</#foreach>
				</t:dataTable>
				
				<crud:tableFooter tableName="${util.getPluralForWord(pojoNameLower)}" />

				</crud:tab>

			</h:form>
		</f:view>
	</ui:define>
</ui:composition>
</html>