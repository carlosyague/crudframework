package org.librae.adminconfig.dao.hibernate;

import java.util.List;

import org.librae.adminconfig.dao.ITipoBibliotecaDAO;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad TipoBiblioteca
 * 
 * @author asantamaria
 */
public class TipoBibliotecaDAOImpl extends GenericDAOImpl<TipoBiblioteca, Long>
        implements ITipoBibliotecaDAO {

    /**
     * Constructor del DAO
     */
    public TipoBibliotecaDAOImpl() {
        super(TipoBiblioteca.class);
    }

    /**
     * @see org.librae.adminconfig.dao.ITipoBibliotecaDAO#getTipoBibliotecaByCodigo(java.lang.String)
     */
    public TipoBiblioteca getTipoBibliotecaByCodigo(final String codigo) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select c from " + TipoBiblioteca.ENTITY_NAME
                + " c where c.codigo like  ? ");
        final Object[] parametros = { codigo };
        this.log.debug("HQL..." + sb.toString());
        final List<TipoBiblioteca> datos = getHibernateTemplate().find(
                sb.toString(), parametros);
        return (datos.isEmpty()) ? null : (TipoBiblioteca) datos.get(0);
    }
}
