<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<!--${pojo.shortName}Manager-START-->
    <bean id="${pojoNameLower}Manager" class="${commonpackage}.service.impl.GenericManagerImpl">
        <constructor-arg>
            <#if daoframework == "hibernate">
            <bean class="${commonpackage}.dao.hibernate.GenericDAOImpl">
                <constructor-arg value="${pojo.packageName}.${pojo.shortName}"/>
                <property name="sessionFactory" ref="sessionFactory"/>
            </bean>
            <#elseif daoframework == "ibatis">
            <bean class="${commonpackage}.dao.ibatis.GenericDaoiBatis">
                <constructor-arg value="${pojo.packageName}.${pojo.shortName}"/>
                <property name="dataSource" ref="dataSource"/>
                <property name="sqlMapClient" ref="sqlMapClient"/>
            </bean>
            <#elseif daoframework == "jpa">
            <bean class="${commonpackage}.dao.jpa.GenericDaoJpa">
                <constructor-arg value="${pojo.packageName}.${pojo.shortName}"/>
            </bean>
            </#if>
        </constructor-arg>
    </bean>
    <!--${pojo.shortName}Manager-END-->

    <!-- Add new Managers here -->