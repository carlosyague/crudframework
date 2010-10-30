<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>    
    
    <!--${pojo.shortName}-START-->
    <li><a href="${'#'}{facesContext.externalContext.requestContextPath}/pages/${pojoNameLower}/list.html">${pojo.shortName}</a></li>
    <!--${pojo.shortName}-END-->
</ul>