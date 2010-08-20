package org.librae.adminconfig.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IMenuDAO;
import org.librae.adminconfig.model.Menu;
import org.librae.common.dao.hibernate.GenericDAOImpl;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Implementación del DAO para la entidad Menu
 * 
 * @author asantamaria
 */
public class MenuDAOImpl extends GenericDAOImpl<Menu, Long> implements IMenuDAO {

    /**
     * Constructor del DAO
     */
    public MenuDAOImpl() {
        super(Menu.class);
    }

    /**
     * @see org.librae.adminconfig.dao.IMenuDAO#getPrincipales()
     */
    @SuppressWarnings("unchecked")
    public List<Menu> getPrincipales() {
        List<Menu> nodosPrincipalesMenu = new ArrayList<Menu>();
        final DetachedCriteria criteriaMenus = DetachedCriteria
                .forClass(Menu.class);
        criteriaMenus.add(Restrictions.isNull(Menu.PROPERTY_NAME_PADRE));
        criteriaMenus.addOrder(Order.asc(Menu.PROPERTY_NAME_ORDEN));
        HibernateTemplate ht = this.getHibernateTemplate();
        ht.setCacheQueries(true);
        nodosPrincipalesMenu = ht.findByCriteria(criteriaMenus);
        return nodosPrincipalesMenu;
    }
}
