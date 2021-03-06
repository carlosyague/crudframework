<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<html jwcid="@Shell" title="message:${pojoNameLower}List.title">
<head>
    <meta name="heading" jwcid="@Any" content="message:${pojoNameLower}List.heading"/>
    <meta name="menu" content="${pojo.shortName}Menu"/>
</head>
<body jwcid="@Body">

<span jwcid="@ShowMessage"/>

<p>
    <input type="button" class="button" onclick="location.href='${pojo.shortName}Form.html'" jwcid="@Any" value="message:button.add"/>
    <input type="button" class="button" onclick="location.href='mainMenu.html'" jwcid="@Any" value="message:button.done"/>
</p>

<!-- todo: remove trailing comma from columns list -->
<table jwcid="table@contrib:Table" class="table contribTable ${pojoNameLower}List" id="${pojoNameLower}List"
    rowsClass="ognl:beans.rowsClass.next" row="ognl:row" source="ognl:${util.getPluralForWord(pojoNameLower)}"
    arrowUpAsset="asset:upArrow" arrowDownAsset="asset:downArrow"
    columns="<#foreach field in pojo.getAllPropertiesIterator()><#if !c2h.isCollection(field) && !c2h.isManyToOne(field) && !c2j.isComponent(field)>${pojoNameLower}.${field.name}:${field.name},</#if></#foreach>">
    <tr jwcid="${pojoNameLower}_${pojo.identifierProperty.name}ColumnValue@Block">
        <a jwcid="@DirectLink" listener="listener:edit" parameters="ognl:row.${pojo.identifierProperty.name}">
            <span jwcid="@Insert" value="ognl:row.${pojo.identifierProperty.name}"/>
        </a>
    </tr>
</table>

<input type="button" class="button" onclick="location.href='${pojo.shortName}Form.html'" jwcid="@Any" value="message:button.add"/>
<input type="button" class="button" onclick="location.href='mainMenu.html'" jwcid="@Any" value="message:button.done"/>

<script type="text/javascript">
    highlightTableRows("${pojoNameLower}List");
</script>

</body>
</html>