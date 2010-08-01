/*
 * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos de
 * explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es software
 * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los t�rminos
 * de la Licencia EUPL European Public License publicada por el organismo IDABC
 * de la Comisi�n Europea, en su versi�n 1.0. o posteriores. �ste programa se
 * distribuye de buena fe, pero SIN NINGUNA GARANT�A, incluso sin las presuntas
 * garant�as impl�citas de USABILIDAD o ADECUACI�N A PROP�SITO CONCRETO. Para
 * mas informaci�n consulte la Licencia EUPL European Public License. Usted
 * recibe una copia de la Licencia EUPL European Public License junto con este
 * programa, si por alg�n motivo no le es posible visualizarla, puede
 * consultarla en la siguiente URL:
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
 * copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu une
 * copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden
 * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */

package org.librae.adminconfig.dao.hibernate;

import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IGrupoFuncionalDAO;
import org.librae.adminconfig.model.GrupoFuncional;
import org.librae.adminconfig.model.Modulo;
import org.librae.common.dao.hibernate.GenericSearchDao;

/**
 * Implementación del DAO para la entidad GrupoFuncional
 *
 * @author jVillegas
 */
public class GrupoFuncionalDAOImpl extends GenericSearchDao <GrupoFuncional, Long > implements
        IGrupoFuncionalDAO {

    /**
     * alias modulo
     */
    private final String aliasModulo             = "modulo";

    /**
     * Constructor del DAO
     */
    public GrupoFuncionalDAOImpl() {
        super(GrupoFuncional.class);
    }

    @Override
    protected DetachedCriteria obtenerCriteria(Map<String, Object> criterios) {
        final DetachedCriteria criteria = DetachedCriteria
                .forClass(GrupoFuncional.class);
        if (criterios != null) {
            if (criterios
                    .get(GrupoFuncional.PROPERTY_NAME_MODULO_FK)!= null) {
                criteria.add(Restrictions.eq(GrupoFuncional.PROPERTY_NAME_MODULO_FK + "." + Modulo.PROPERTY_NAME_ID,
                        criterios.get(GrupoFuncional.PROPERTY_NAME_MODULO_FK)));
            }
            if (criterios
                    .get(GrupoFuncional.PROPERTY_NAME_ID)!= null) {
                criteria.add(Restrictions.eq(GrupoFuncional.PROPERTY_NAME_ID,
                        criterios.get(GrupoFuncional.PROPERTY_NAME_ID)));
            }
        }
        return criteria;

    }
}
