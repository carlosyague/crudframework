package es.uma.crudframework.dao.hibernate.support;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * CrudHibernateDaoSupport.
 * 
 * @author cyague
 */
public abstract class CrudHibernateDaoSupport extends HibernateDaoSupport {

    /**
     * hibernateTemplate.
     */
    private CrudHibernateTemplate hibernateTemplate;

    /**
     * getLibraeHibernateTemplate.
     * 
     * @return the hibernateTemplate
     */
    public final HibernateTemplate getCrudHibernateTemplate() {
        return this.hibernateTemplate;
    }

}
