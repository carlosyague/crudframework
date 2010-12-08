<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<!--${pojo.shortName}-nav-START-->
    <navigation-rule>
        <from-view-id>/pages/${pojoNameLower}/list.xhtml</from-view-id>
        <navigation-case>
            <from-outcome>add</from-outcome>
            <to-view-id>/pages/${pojoNameLower}/form.xhtml</to-view-id>
        </navigation-case>
        <navigation-case>
            <from-outcome>edit</from-outcome>
            <to-view-id>/pages/${pojoNameLower}/form.xhtml</to-view-id>
        </navigation-case>
    </navigation-rule>
    <navigation-rule>
        <from-view-id>/pages/${pojoNameLower}/form.xhtml</from-view-id>
        <navigation-case>
            <from-outcome>cancel</from-outcome>
            <to-view-id>/pages/${pojoNameLower}/list.xhtml</to-view-id>
            <redirect/>
        </navigation-case>
        <navigation-case>
            <from-outcome>list</from-outcome>
            <to-view-id>/pages/${pojoNameLower}/list.xhtml</to-view-id>
            <redirect/>
        </navigation-case>
    </navigation-rule>
    <!--${pojo.shortName}-nav-END-->
    <!-- Add additional rules here -->