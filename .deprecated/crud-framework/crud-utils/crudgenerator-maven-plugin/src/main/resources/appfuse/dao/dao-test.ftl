<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<#assign getIdMethodName = pojo.getGetterSignature(pojo.identifierProperty)>

package ${basepackage}.dao.hibernate;

import <#if daoframework == "jpa">javax.persistence.EntityNotFoundException<#else>${appfusepackage}.exception.CrudException</#if>;

import ${appfusepackage}.dao.BaseDAOTestCase;
import ${basepackage}.model.${pojo.shortName};
import ${basepackage}.dao.I${pojo.shortName}DAO;
import org.junit.Test;


public class ${pojo.shortName}DaoTest extends BaseDAOTestCase {
    private I${pojo.shortName}DAO dao;

    public void set${pojo.shortName}DAO(I${pojo.shortName}DAO dao) {
        this.dao = dao;
    }

    @Test
    public void testAddAndRemove${pojo.shortName}() throws Exception {
        ${pojo.shortName} ${pojoNameLower} = new ${pojo.shortName}();

        // enter all required fields
<#foreach field in pojo.getAllPropertiesIterator()>
    <#foreach column in field.getColumnIterator()>
        <#if !field.equals(pojo.identifierProperty) && !column.nullable && !c2h.isCollection(field) && !c2h.isManyToOne(field) && !c2j.isComponent(field)>
            <#lt/>        ${pojoNameLower}.set${pojo.getPropertyName(field)}(${data.getValueForJavaTest(column)});
        </#if>
    </#foreach>
</#foreach>

        log.debug("adding ${pojoNameLower}...");
        ${pojoNameLower} = dao.save(${pojoNameLower});
        //<#lt/><#if daoframework == "daoframework">flush();</#if><#rt/>

        ${pojoNameLower} = dao.get(${pojoNameLower}.${getIdMethodName}());

        assertNotNull(${pojoNameLower}.${getIdMethodName}());

        log.debug("removing ${pojoNameLower}...");

        dao.remove(${pojoNameLower}.${getIdMethodName}());
        //<#lt/><#if daoframework == "daoframework">flush();</#if><#rt/>

        try {
            dao.get(${pojoNameLower}.${getIdMethodName}());
            fail("${pojo.shortName} found in database");
        } catch (<#if daoframework == "jpa">EntityNotFoundException<#else>CrudException</#if> dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

}