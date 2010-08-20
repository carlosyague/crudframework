package org.librae.adminconfig.dao.hibernate;

import java.io.Serializable;

import org.librae.adminconfig.dao.ITratamientoDAO;
import org.librae.adminconfig.model.Tratamiento;
import org.librae.common.dao.hibernate.GenericDAOImpl;

/**
 * Implementación del DAO para la entidad Tratamiento
 *
 * @author asantamaria
 */
public class TratamientoDAOImpl extends GenericDAOImpl<Tratamiento, Long>
        implements ITratamientoDAO, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = -3181313532312586483L;

    /**
     * Constructor del DAO
     */
    public TratamientoDAOImpl() {
        super(Tratamiento.class);
    }
}
