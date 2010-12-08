<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:c="http://java.sun.com/jstl/core"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:t="http://myfaces.apache.org/tomahawk"
	xmlns:v="http://corejsf.com/validator">


<ui:composition template="/templates/defaultTemplate.xhtml">
	<ui:define name="title">${'#'}{text['${pojoNameLower}Form.title']}</ui:define>
	<ui:define name="body.id">${pojoNameLower}FormAction</ui:define>

	<ui:define name="body">
		<f:view>
			<f:loadBundle var="text" basename="${'#'}{${pojoNameLower}FormAction.bundleName}"/>    

<h:form id="${pojoNameLower}Form" onsubmit="return validate${pojo.shortName}Form(this)">


				<!-- PATH -->
				<table border="0" cellpadding="0" cellspacing="0" class="ruta">
					<tr>
						<td class="path"><a
							href="${'#'}{facesContext.externalContext.requestContextPath}/"
							title="${'#'}{text['home.title']}"><h:outputText
							value="${'#'}{text['home.title']}" /></a> > <h:outputText
							value=" ${'#'}{text['${pojoNameLower}Form.pathText']}" /></td>
						<td nowrap="true" class="fechahora">2 SEPT 2008 | 17:25<a
							href="#" class="login">Martinruiz</a></td>
					</tr>
				</table>
				<!-- FIN PATH -->
				
				<!-- CONTENIDO -->
				<div id="contenido"><!-- PESTANA 1 -->
				<div id="pestana1">
				<table border="0" cellpadding="0" cellspacing="0" class="pestanas">
					<tbody>
						<tr>
							<td><h:graphicImage value="/images/p01b.gif" /></td>
							<td class="titulo_activa"><h:outputText
								value="${'#'}{text['${pojoNameLower}Form.tab01Text']}" /></td>
							<td><h:graphicImage value="/images/p02b.gif" /></td>
						</tr>
					</tbody>
				</table>
				<!-- CONTENIDO PESTANA -->
				<div class="pestana">

				<div class="cuadro">
				<div class="cuadro02">

				<table cellspacing="0" cellpadding="0" border="0" width="100%">
					<tbody>
						<!-- cabecera de la ficha -->
						<tr>
							<td class="esq01 titular" colspan="2"><h:outputText
								value=" ${'#'}{text['${pojoNameLower}Form.formTitle']}" /></td>
						</tr>


<#rt/>
<#foreach field in pojo.getAllPropertiesIterator()>
<#if field.equals(pojo.identifierProperty)>
    <#foreach column in field.getColumnIterator()>
    <#assign idFieldName = field.name>
    <#if field.value.identifierGeneratorStrategy == "assigned">
<tr>
	<th width="10%"><h:outputLabel for="${field.name}" value="${'#'}{text['${pojoNameLower}.${field.name}']}" /></th>
	<td width="90%">

    <h:outputLabel styleClass="desc" for="${field.name}" value="${'#'}{text['${pojoNameLower}.${field.name}']}"/>
    <h:inputText styleClass="text medium" id="${field.name}" value="${'#'}{${pojoNameLower}FormAction.${pojoNameLower}.${field.name}}" required="${(!column.nullable)?string}">
        <v:commonsValidator type="required" arg="${'#'}{text['${pojoNameLower}.${field.name}']}"/>
    </h:inputText>
    <t:message for="${field.name}" styleClass="fieldError"/>
    
    </td>
</tr>
    <#else>
        <#lt/><h:inputHidden value="${'#'}{${pojoNameLower}FormAction.${pojoNameLower}.${field.name}}" id="${field.name}"/>
    </#if>
    </#foreach>
<#elseif !c2h.isCollection(field) && !c2h.isManyToOne(field) && !c2j.isComponent(field)>
	    
        
    <#foreach column in field.getColumnIterator()>
		<tr>
		<th width="10%"><h:outputLabel for="${field.name}" value="${'#'}{text['${pojoNameLower}.${field.name}']}" /></th>
		<td width="90%">
        <#if field.value.typeName == "java.util.Date">
            <#lt/>    <t:inputCalendar monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader" id="${field.name}"
            currentDayCellClass="currentDayCell" value="${'#'}{${pojoNameLower}FormAction.${pojoNameLower}.${field.name}}"
            renderAsPopup="true" addResources="true" required="${(!column.nullable)?string}">
        <#if !column.nullable>
        <v:commonsValidator type="required" arg="${'#'}{text['${pojoNameLower}.${field.name}']}"/>
        </#if>
    </t:inputCalendar>
        <#elseif field.value.typeName == "boolean" || field.value.typeName == "java.lang.Boolean">
            <#lt/>    <h:selectBooleanCheckbox value="${'#'}{${pojoNameLower}FormAction.${pojoNameLower}.${field.name}}" id="${field.name}" styleClass="checkbox"/>
        <#else>
            <#lt/>    <h:inputText styleClass="text medium" id="${field.name}" value="${'#'}{${pojoNameLower}FormAction.${pojoNameLower}.${field.name}}" required="${(!column.nullable)?string}"<#if (column.length > 0)> maxlength="${column.length?c}"</#if>>
        <#if !column.nullable>
        <v:commonsValidator type="required" arg="${'#'}{text['${pojoNameLower}.${field.name}']}"/>
        </#if>
    </h:inputText>
        </#if>
        <t:message for="${field.name}" styleClass="fieldError"/>
    	</td>
		</tr>
    </#foreach>
    
	
<#elseif c2h.isManyToOne(field)>
    <#foreach column in field.getColumnIterator()>
    	<tr>
		<th width="10%"><h:outputLabel for="${field.name}" value="${'#'}{text['${pojoNameLower}.${field.name}']}" /></th>
		<td width="90%">
            <#lt/>    <!-- todo: change this to read the identifier field from the other pojo -->
            <#lt/>    <h:selectOneMenu value="${'#'}{${pojoNameLower}FormAction.${pojoNameLower}.${field.name}}" id="${field.name}" required="${(!column.nullable)?string}" styleClass="select">
        <f:selectItems value="${'#'}{${pojoNameLower}FormAction.${pojoNameLower}.${field.name}}"/>
        <#if !column.nullable>
        <v:commonsValidator type="required" arg="${'#'}{text['${pojoNameLower}.${field.name}']}"/>
        </#if>
    </h:selectOneMenu>
    
    	</td>
		</tr>
    </#foreach>
</#if>
</#foreach>

    		<tr>
    			<td class="esq02" colspan="2">
    			
    			<h:commandLink action="cancel" id="cancel" immediate="true" styleClass="boton_rojo" title="${'#'}{text['button.cancel']}" onclick="bCancel=true">
        			<span> <h:outputText value="${'#'}{text['button.cancel']}" /> </span>
        		</h:commandLink>
        		
        		<c:if test="${'$'}{not empty ${pojoNameLower}FormAction.${pojoNameLower}.${idFieldName}}">        			            		
            		<h:commandLink action="${'#'}{${pojoNameLower}FormAction.delete}" id="delete"
						styleClass="boton" title="${'#'}{text['button.delete']}"
						onclick="bCancel=true; return confirmDelete('${pojo.shortName}')">
						<span> <h:outputText value="${'#'}{text['button.delete']}" /> </span>
					</h:commandLink>
        		</c:if>
            	
            	<h:commandLink action="${'#'}{${pojoNameLower}FormAction.save}" id="save"
					styleClass="boton" title="${'#'}{text['button.save']}">
					<span> <h:outputText value="${'#'}{text['button.save']}" /> </span>
				</h:commandLink>
				</td>
    		</tr>

		</tbody>
	</table>
	</div>
	</div>
	</div>
	</div>

	<div class="pie_pestana">
		<table cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td width="5"><h:graphicImage value="/images/pie01.png" /></td>
				<td class="blanco">&#x00A0;</td>
				<td width="5"><h:graphicImage value="/images/pie02.png" /></td>
			</tr>
		</tbody>
		</table>
	</div>

	</div>
</h:form>

	<v:validatorScript functionName="validate${pojo.shortName}Form" />

	<script type="text/javascript">
	    Form.focusFirstElement($('${pojoNameLower}Form'));
	    highlightFormElements();
	</script>

		</f:view>
	</ui:define>
</ui:composition>
</html>
