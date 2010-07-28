package org.librae.common.dao.hibernate.support;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * LibraeHibernateDaoSupport.
 * 
 * @author ingenia
 */
public abstract class LibraeHibernateDaoSupport extends HibernateDaoSupport {

    /**
     * hibernateTemplate.
     */
    private LibraeHibernateTemplate hibernateTemplate;

    /**
     * getLibraeHibernateTemplate.
     * 
     * @return the hibernateTemplate
     */
    public final HibernateTemplate getLibraeHibernateTemplate() {
        return this.hibernateTemplate;
    }

}
