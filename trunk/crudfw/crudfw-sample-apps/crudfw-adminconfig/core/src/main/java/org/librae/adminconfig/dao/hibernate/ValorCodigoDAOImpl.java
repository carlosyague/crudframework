package org.librae.adminconfig.dao.hibernate;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IValorCodigoDAO;
import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.ValorCodigo;
import org.librae.common.dao.hibernate.GenericSearchDao;
import org.librae.common.util.StringUtil;

/**
 * Implementación del DAO para la entidad ValorCodigo
 * 
 * @author asantamaria
 */
public class ValorCodigoDAOImpl extends GenericSearchDao<ValorCodigo, Long>
        implements IValorCodigoDAO, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor del DAO
     */
    public ValorCodigoDAOImpl() {
        super(ValorCodigo.class);
    }

    @Override
    protected DetachedCriteria obtenerCriteria(
            final Map<String, Object> criterios) {
        final DetachedCriteria criteriaCodigosBiblioteca = DetachedCriteria
                .forClass(Codigo.class);
        if (criterios != null) {
            if (!StringUtil.esVacia(criterios
                    .get(Usuario.PROPERTY_NAME_APELLIDO1))) {
                criteriaCodigosBiblioteca.add(Restrictions.ilike(
                        Usuario.PROPERTY_NAME_APELLIDO1, criterios.get(
                                Usuario.PROPERTY_NAME_APELLIDO1).toString(),
                        MatchMode.ANYWHERE));
            }
        }
        return criteriaCodigosBiblioteca;
    }
}
