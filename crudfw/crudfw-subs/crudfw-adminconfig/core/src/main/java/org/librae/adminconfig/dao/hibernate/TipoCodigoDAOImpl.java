package org.librae.adminconfig.dao.hibernate;

import org.librae.adminconfig.dao.ITipoCodigoDAO;
import org.librae.adminconfig.model.TipoCodigo;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad TipoDeCodigo
 * 
 * @author asantamaria
 */
public class TipoCodigoDAOImpl extends GenericDAOImpl<TipoCodigo, Long>
        implements ITipoCodigoDAO {

    /**
     * Constructor del DAO
     */
    public TipoCodigoDAOImpl() {
        super(TipoCodigo.class);
    }
}
