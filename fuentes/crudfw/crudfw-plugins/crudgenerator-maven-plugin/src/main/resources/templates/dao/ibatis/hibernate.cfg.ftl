<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
    "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <mapping class="${commonpackage}.model.User"/>
        <mapping class="${commonpackage}.model.Role"/>
    </session-factory>
</hibernate-configuration>