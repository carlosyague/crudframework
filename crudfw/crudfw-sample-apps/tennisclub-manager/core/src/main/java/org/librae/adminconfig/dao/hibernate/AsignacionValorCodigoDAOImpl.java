package org.librae.adminconfig.dao.hibernate;

import org.librae.adminconfig.dao.IAsignacionValorCodigoDAO;
import org.librae.adminconfig.model.AsignacionValorCodigo;
import org.librae.common.dao.hibernate.GenericDAOImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

/**
 * Implementación del DAO para la entidad AsignacionValorCodigo
 * 
 * @author asantamaria
 * @author aropero
 */
public class AsignacionValorCodigoDAOImpl extends
        GenericDAOImpl<AsignacionValorCodigo, Long> implements
        IAsignacionValorCodigoDAO {

    /** atributo de trazas */
    private transient final Log log = LogFactory.getLog(this.getClass());

    /**
     * Constructor del DAO
     */
    public AsignacionValorCodigoDAOImpl() {
        super(AsignacionValorCodigo.class);
    }

    /**
     * @see org.librae.adminconfig.dao.IAsignacionValorCodigoDAO#deleteValoresByBiblioteca(java.lang.Long)
     */
    public void deleteValoresByBiblioteca(final Long id) {
        log.debug("Actualizamos la Biblioteca con Id : " + id);

        final StringBuilder sb = new StringBuilder();
        sb.append("delete from " + AsignacionValorCodigo.ENTITY_NAME
                + " a where a.biblioteca.id like " + id);

        log.debug("HQL : " + sb.toString());
        final Query query = getSession().createQuery(sb.toString());

        query.executeUpdate();

    }
}
