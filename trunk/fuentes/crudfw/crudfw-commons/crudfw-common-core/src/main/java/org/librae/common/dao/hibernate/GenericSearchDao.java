package org.librae.common.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.librae.common.Constants;
import org.librae.common.dao.IGenericSearchDAO;
import org.librae.common.util.CollectionsUtil;
import org.librae.common.util.StringUtil;

/**
 * Clase para el comportamiento comun de los listados.
 * 
 * @author jcisneros
 * @param <T>
 *            tipo sobre el que trabajará el DAO
 * @param <PK>
 *            tipo de la Clave Primaria
 */
public abstract class GenericSearchDao<T, PK extends Serializable> extends
        GenericDAOImpl<T, PK> implements IGenericSearchDAO<T, PK> {

    /**
     * Constructor.
     * 
     * @param persistentClass
     *            clase persistente con la que se trabajara
     */
    public GenericSearchDao(final Class<T> persistentClass) {
        super(persistentClass);
    }

    /**
     * Método encargado de obtener el listado de entidades según los filtros
     * indicados en el Map que será convertido en un objeto criteria por las
     * clases hijas
     * 
     * @param criterios
     *            con los criterios de búsqueda
     * @return lista de entidades del modelo
     */
    public List<T> buscar(final Map<String, Object> criterios) {
        List<T> lista = new ArrayList<T>();

        // Se obtiene el número de resultados
        final DetachedCriteria criteriaCount = obtenerCriteria(criterios);
        obtenerTotal(criteriaCount, criterios);

        // Es necesario volver a crear el criterio.
        final DetachedCriteria criteria = obtenerCriteria(criterios);

        setOrdenacion(criteria, criterios);

        if (criterios.get(Constants.PAGESIZE) == null
                || criterios.get(Constants.CURRENTPAGE) == null) {
            lista = getHibernateTemplate().findByCriteria(criteria);
        } else {
            // obtenemos la posicion del primer registro a devolver
            final int pageSize = (Integer) criterios.get(Constants.PAGESIZE);
            final int currentPage = (Integer) criterios
                    .get(Constants.CURRENTPAGE);
            final int firtResult = obtenerPosPrimerRegistro(pageSize,
                    currentPage);

            // Se obtiene la lista de resultados
            lista = getHibernateTemplate().findByCriteria(criteria, firtResult,
                    pageSize);
        }

        return lista;
    }

    /**
     * Método encargado de obtener la posición del primer registro a devolver al
     * usuario
     */
    private int obtenerPosPrimerRegistro(final Integer pageSize,
            final Integer currentPage) {
        return pageSize * (currentPage - 1);
    }

    /**
     * Método abstracto a implementar por las clases hijas encargado de
     * convertir el hasMap de filtros en un objeto criteria.
     * 
     * @param criterios
     *            con los criterios de búsqueda
     * @return DetachedCriteria resultante
     */
    protected abstract DetachedCriteria obtenerCriteria(
            Map<String, Object> criterios);

    /**
     * Establece el criterio de ordenación de la búsqueda hibernate.
     * 
     * @param criteria
     *            Criterio de búsqueda hibernate.
     * @param criterios
     *            Criterio de búsqueda propio de la aplicación.
     */
    protected void setOrdenacion(final DetachedCriteria criteria,
            final Map<String, Object> criterios) {

        boolean ascendente = true;

        final String orden = (String) criterios.get(Constants.SORTCOLUMN);
        if (orden != null) {
            if (criterios.get(Constants.ASCENDING) == null) {
                ascendente = Boolean.TRUE;
            } else {
                ascendente = (Boolean) criterios.get(Constants.ASCENDING);
            }

            // Se traspasa el criterio al formato que usa hibernate
            if (criteria != null && !StringUtil.esVacia(orden)) {
                if (ascendente) {
                    criteria.addOrder(Order.asc(orden));
                } else {
                    criteria.addOrder(Order.desc(orden));
                }
            }
        }
    }

    /**
     * Método encargado de obtener el total de registros de la consulta
     * independientemente de la pagina solicitada guardando en resultado en la
     * tabla Map
     */
    protected void obtenerTotal(final DetachedCriteria criteria,
            final Map<String, Object> filtros) {
        criteria.setProjection(Projections.rowCount());
        final int count = ((Integer) getHibernateTemplate().findByCriteria(
                criteria).get(0)).intValue();
        filtros.put(Constants.RESULTADO_BUSQUEDA, count);
    }

    /**
     * Completa el criteria con los datos de la direccion.
     * 
     * @param criteria
     * @param criterios
     */
    protected void obtenerDireccion(final DetachedCriteria criteria,
            final Map<String, Object> criterios) {
        if (((criterios.get("idPais") != null) && (!(new Long(0))
                .equals(criterios.get("idPais"))))
                || ((criterios.get("idComAutonoma") != null) && (!(new Long(0))
                        .equals(criterios.get("idComAutonoma"))))
                || ((criterios.get("idProvincia") != null) && (!(new Long(0))
                        .equals(criterios.get("idProvincia"))))
                || ((criterios.get("idMunicipio") != null) && (!(new Long(0))
                        .equals(criterios.get("idMunicipio"))))
                || ((criterios.get("idLocalidad") != null) && (!(new Long(0))
                        .equals(criterios.get("idLocalidad"))))
                || (!StringUtil.esVacia(criterios.get("codPostal")))
                || (!StringUtil.esVacia(criterios.get("edificio")))
                || (!StringUtil.esVacia(criterios.get("nombreVia")))) {
            criteria.createAlias("direccion", "direccion");
            criteria.setFetchMode("direccion", FetchMode.JOIN);
        }
        /* PAIS */
        if ((criterios.get("idPais") != null)
                && (!(new Long(0)).equals(criterios.get("idPais")))) {
            criteria.add(Restrictions.eq("direccion.pais.id", criterios
                    .get("idPais")));
        }
        /* COMUNIDAD AUTONOMA */
        if ((criterios.get("idComAutonoma") != null)
                && (!(new Long(0)).equals(criterios.get("idComAutonoma")))) {
            criteria.add(Restrictions.eq("direccion.comAutonoma.id", criterios
                    .get("idComAutonoma")));
        }
        /* PROVINCIA */
        if ((criterios.get("idProvincia") != null)
                && (!(new Long(0)).equals(criterios.get("idProvincia")))) {
            criteria.add(Restrictions.eq("direccion.provincia.id", criterios
                    .get("idProvincia")));
        }
        /* MUNICIPIO */
        if ((criterios.get("idMunicipio") != null)
                && (!(new Long(0)).equals(criterios.get("idMunicipio")))) {
            criteria.add(Restrictions.eq("direccion.municipio.id", criterios
                    .get("idMunicipio")));
        }
        /* LOCALIDAD */
        if ((criterios.get("idLocalidad") != null)
                && (!(new Long(0)).equals(criterios.get("idLocalidad")))) {
            criteria.add(Restrictions.eq("direccion.localidad.id", criterios
                    .get("idLocalidad")));
        }

        /* CODIGO POSTAL */
        if (!StringUtil.esVacia(criterios.get("codPostal"))) {
            criteria.add(Restrictions.ilike("direccion.codigoPostal", criterios
                    .get("codPostal").toString(), MatchMode.ANYWHERE));
        }
        /* EDIFICIO */
        if (!StringUtil.esVacia(criterios.get("edificio"))) {
            criteria.add(Restrictions.ilike("direccion.nombreEdificio",
                    criterios.get("edificio").toString(), MatchMode.ANYWHERE));
        }
        /* DIRECCION -- NOMBREVIA */
        if (!StringUtil.esVacia(criterios.get("nombreVia"))) {
            criteria.add(Restrictions.ilike("direccion.nombreVia", criterios
                    .get("nombreVia").toString(), MatchMode.ANYWHERE));
        }
        if (!StringUtil.esVacia(criterios.get("direccion"))) {
            criteria.add(Restrictions.ilike("direccion.nombreVia", criterios
                    .get("direccion").toString(), MatchMode.ANYWHERE));
        }

    }

    protected Boolean isValidCriterion(String propertyName,
            Map<String, Object> criterios) {
        return criterios != null && criterios.get(propertyName) != null;
    }

    protected Boolean isValidCriterionString(String propertyName,
            Map<String, Object> criterios) {
        return criterios != null && criterios.get(propertyName) != null
                && (!StringUtil.esVacia(criterios.get(propertyName)));
    }

    protected Criterion getCriterion(String propertyName,
            Map<String, Object> criterios) {
        return Restrictions.ilike(propertyName, criterios.get(propertyName)
                .toString());
    }

    protected Criterion getCriterionAnyWhere(String propertyName,
            Map<String, Object> criterios) {
        return Restrictions.ilike(propertyName, criterios.get(propertyName)
                .toString(), MatchMode.ANYWHERE);
    }

    protected DetachedCriteria getCriterionWithAlias(DetachedCriteria criteria,
            String propertyName, String propertyNameField,
            Map<String, Object> criterios) {

        criteria.createAlias(propertyName, propertyName);

        final StringBuilder sb = new StringBuilder();
        sb.append(propertyName).append(".").append(propertyNameField);

        criteria.add(Restrictions
                .eq(sb.toString(), criterios.get(propertyName)));

        return criteria;
    }

    public Integer contarTotal(Map<String, Object> criterios) {
        final DetachedCriteria criteria = obtenerCriteria(criterios);
        criteria.setProjection(Projections.rowCount());
        final Integer count = ((Integer) getHibernateTemplate().findByCriteria(
                criteria).get(0));
        return count;
    }

    public static void listadoToInExpression(DetachedCriteria criteria,
            String propertyName, List<Long> listado) {
        final List<List<Long>> listadoPartido = CollectionsUtil
                .listToInExpresion(listado);
        final Disjunction disjunction = Restrictions.disjunction();
        for (final List<Long> lista : listadoPartido) {
            disjunction.add(Restrictions.in(propertyName, lista));
        }
        criteria.add(disjunction);
    }
}
