package org.librae.adminconfig.dao.hibernate;

import org.librae.adminconfig.model.ParametroBiblioteca;
import org.librae.adminconfig.dao.IParametroBibliotecaDAO;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad ParametroBiblioteca
 *
 * @author asantamaria
 */
public class ParametroBibliotecaDAOImpl extends GenericDAOImpl<ParametroBiblioteca, Long> implements IParametroBibliotecaDAO {

    /**
     * Constructor del DAO
     */
    public ParametroBibliotecaDAOImpl() {
        super(ParametroBiblioteca.class);
    }
}
