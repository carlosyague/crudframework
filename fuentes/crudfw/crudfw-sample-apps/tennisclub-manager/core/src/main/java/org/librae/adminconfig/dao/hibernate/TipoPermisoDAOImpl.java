package org.librae.adminconfig.dao.hibernate;

import org.librae.adminconfig.model.TipoPermiso;
import org.librae.adminconfig.dao.ITipoPermisoDAO;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad TipoPermiso
 *
 * @author asantamaria
 */
public class TipoPermisoDAOImpl extends GenericDAOImpl<TipoPermiso, Long> implements ITipoPermisoDAO {

    /**
     * Constructor del DAO
     */
    public TipoPermisoDAOImpl() {
        super(TipoPermiso.class);
    }
}
