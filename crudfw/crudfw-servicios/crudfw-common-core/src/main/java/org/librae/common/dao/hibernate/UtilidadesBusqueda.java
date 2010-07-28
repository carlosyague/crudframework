package org.librae.common.dao.hibernate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Utilidades de busqueda.
 *
 * @author Sopra Profit
 * @author ingenia
 */
public final class UtilidadesBusqueda {

    /**
     * Constructor por defecto, privado, para no permitir instanciación.
     */
    private UtilidadesBusqueda() {
        super();
    }

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging.
     */
    private static final Log LOG = LogFactory
                                         .getLog("org.librae.common.dao.hibernate.UtilidadesBusqueda");

    /**
     * Recupera el Criterio para buscar un objeto por su ID. Equivale a un
     * SELECT * FROM [nombre_tabla] WHERE [id_tabla] = id. El id que va a tomar
     * para hacer la consulta es el valor del atributo marcado con la notación @Id
     * en el objeto pasado.
     *
     * @param sesion
     *            objeto de tipo Session que representa la sesión activa para
     *            hibernate.
     * @param objeto
     *            objeto de la entidad que representa la tabla que se desea
     *            consultar. Debe tener el id lleno con el valor del id que se
     *            desea recuperar.
     * @return Object, objeto recuperado.
     */
    @SuppressWarnings("unchecked")
    public static Object obtenerObjetoPorID(final Session sesion,
            final Object objeto) {
        final Class clase = objeto.getClass();
        Criteria criterio = null;
        final List<Field> atributos = new ArrayList<Field>();
        Object valorAtributo;
        if (clase.getDeclaredFields() == null
                || clase.getDeclaredFields().length == 0) {
            return criterio;
        } else {
            atributos.addAll(Arrays.asList(clase.getDeclaredFields()));
        }
        for (Field atributo : atributos) {
            if (atributo.isAnnotationPresent(javax.persistence.Id.class)) {
                valorAtributo = obtenerAtributo(atributo, objeto);
                if (valorAtributo != null) {
                    criterio = sesion.createCriteria(clase).add(
                            Restrictions.eq(atributo.getName(), valorAtributo));
                }
                break;
            }
        }
        valorAtributo = null;
        if (criterio != null) {
            final List listaResultado = criterio.list();
            if (listaResultado != null && !listaResultado.isEmpty()) {
                valorAtributo = listaResultado.get(0);
            }
        }
        return valorAtributo;
    }

    /**
     * Recupera toda la lista de objetos de la tabla representada por la clase
     * clase. Equivale a un SELECT * FROM [nombre_tabla].
     *
     * @param sesion
     *            objeto de tipo Session que representa la sesión activa para
     *            hibernate.
     * @param clase
     *            objeto de tipo Class que representa la tabla que deseamos
     *            consultar.
     * @return List, lista con todos los objetos de la tabla representada por la
     *         clase indicada.
     */
    @SuppressWarnings("unchecked")
    public static List obtenerTodos(final Session sesion, final Class clase) {
        List resultado = null;
        final Criteria criterio = sesion.createCriteria(clase);
        if (criterio != null) {
            resultado = criterio.list();
        }
        return resultado;
    }

    /**
     * Recupera la lista de objetos de acuerdo a los parametros de busqueda
     * indicados.
     *
     * @param sesion
     *            objeto de tipo Session que representa la sesión activa para
     *            hibernate.
     * @param objeto
     *            objeto con condiciones de busqueda especificada
     * @param mapCondiciones
     *            objeto de tipo Map que tiene la dupla de atributo al que se
     *            aplica la condición y un vector de dos posiciones [condición a
     *            evaluar, vector con los valores a aplicar en la condición]. La
     *            condición a evaluar es un objeto de tipo TipoCondicion.
     * @return List, lista con todos los objetos de la tabla representada por la
     *         clase indicada, que cumplen con los criterios de busqueda.
     */
    @SuppressWarnings("unchecked")
    public static List obtenerObjetosPorParametros(final Session sesion,
            final Object objeto, final Map mapCondiciones) {
        List listaResultado = null;
        final Criteria criterio = getCriteriosBusqueda(sesion, objeto,
                mapCondiciones, null, null);
        if (criterio != null) {
            listaResultado = criterio.list();
        }
        return listaResultado;
    }

    /**
     * Recupera el Criteria con las restricciones especificadas para la
     * busqueda.
     *
     * @param sesion
     *            objeto de tipo Session que representa la sesión
     * @param objeto
     *            objeto con condiciones de busqueda especificada
     * @param mapCondiciones
     *            objeto de tipo Map que tiene la dupla de atributo al que se
     *            aplica la condición y un vector de dos posiciones [condición a
     *            evaluar, vector con los valores a aplicar en la condición]. La
     *            condición a evaluar es un objeto de tipo TipoCondicion.
     * @param criterioBase
     *            objeto de tipo Criteria al cual se debe agregrar un nuevo
     *            criterio de busqueda, este campo se pasa cuando se desea hacer
     *            join
     * @param aliasRelacion
     *            objeto de tipo String que debe llevar el nombre del alias que
     *            tiene la relación que se desea usar en el criterioBase
     * @return Criteria, criterio de busqueda a aplicar
     */
    @SuppressWarnings("unchecked")
    private static Criteria getCriteriosBusqueda(final Session sesion,
            final Object objeto, final Map mapCondiciones,
            final Criteria criterioBase, final String aliasRelacion) {
        if (objeto == null) {
            return null;
        }
        Criteria criterio;
        final Class clase = objeto.getClass();
        String alias;
        if (aliasRelacion == null) {
            alias = "t";
            criterio = sesion.createCriteria(clase, alias);
        } else {
            alias = aliasRelacion;
            criterio = criterioBase.createAlias(aliasRelacion, aliasRelacion);
        }
        Criterion restriccion;
        Object valorAtributo;
        Object[] mapeoCondicionAtributo = null;
        final StringBuilder aliasAtributoSB = new StringBuilder();
        Field atributo;
        Method metodoGet;
        final List<Field> atributos = new ArrayList<Field>();
        if (clase.getDeclaredFields() == null
                || clase.getDeclaredFields().length == 0) {
            return criterio;
        } else {
            atributos.addAll(Arrays.asList(clase.getDeclaredFields()));
        }
        // Campos con anotación Id, Column o ManyToOne
        for (int i = atributos.size() - 1; i >= 0; i--) {
            atributo = atributos.get(i);
            metodoGet = UtilidadesBusqueda.obtenerMetodoGetAtributo(atributo);
            if (metodoGet != null && (metodoGet.isAnnotationPresent(javax.persistence.Column.class)
    || metodoGet.isAnnotationPresent(javax.persistence.Id.class)
    || metodoGet.isAnnotationPresent(javax.persistence.ManyToOne.class)
    || metodoGet.isAnnotationPresent(javax.persistence.OneToOne.class))) {
                aliasAtributoSB.delete(0, aliasAtributoSB.length());
                aliasAtributoSB.append(atributo.getName());
                valorAtributo = obtenerAtributo(atributo, objeto);
                if (mapCondiciones != null) {
                    if (aliasRelacion != null) {
                        aliasAtributoSB.insert(0, ".");
                        aliasAtributoSB.insert(0, alias);
                    }
                    mapeoCondicionAtributo = (Object[]) mapCondiciones
                            .get(aliasAtributoSB.toString());
                }
                // Mirar si el campo tiene alguna condición particular en caso
                // de tenerla se utiliza la condicion especifica
                if (mapeoCondicionAtributo == null) {
                    if (valorAtributo != null) {
                        aliasAtributoSB.delete(0, aliasAtributoSB.length());
                        aliasAtributoSB.append(alias);
                        aliasAtributoSB.append(".");
                        aliasAtributoSB.append(atributo.getName());
                        if (valorAtributo instanceof String) {
                            if (!valorAtributo.toString().trim().equals("")) {
                                criterio.add(Restrictions.like(aliasAtributoSB
                                        .toString(), valorAtributo));
                            }
                        } else {
                            criterio.add(Restrictions.eq(aliasAtributoSB
                                    .toString(), valorAtributo));
                        }
                    }
                } else {
                    mapCondiciones.remove(aliasAtributoSB.toString());
                    restriccion = obtenerRestriccion(atributo,
                            mapeoCondicionAtributo, alias);
                    if (restriccion != null) {
                        criterio.add(restriccion);
                    }
                }
                atributos.remove(i);
            }
        }
        // Campos con anotación OneToMany, ManyToMany
        for (int i = atributos.size() - 1; i >= 0; i--) {
            atributo = atributos.get(i);
            metodoGet = UtilidadesBusqueda.obtenerMetodoGetAtributo(atributo);
            if (metodoGet != null && (
    metodoGet.isAnnotationPresent(javax.persistence.OneToMany.class)
    || metodoGet.isAnnotationPresent(javax.persistence.ManyToMany.class))) {
                aliasAtributoSB.delete(0, aliasAtributoSB.length());
                aliasAtributoSB.append(atributo.getName());
                if (mapCondiciones != null) {
                    mapeoCondicionAtributo = (Object[]) mapCondiciones
                            .get(aliasAtributoSB.toString());
                }
                if (mapeoCondicionAtributo != null) {
                    mapCondiciones.remove(aliasAtributoSB.toString());
                    // Validar que el mapeoCondicionAtributo tenga los dos
                    // campos que requiere para generar la restricción.
                    if (mapeoCondicionAtributo.length != 2) {
                        continue;
                    }
                    final TipoCondicion tipoCondicion = (TipoCondicion) mapeoCondicionAtributo[0];
                    switch (tipoCondicion) {
                        case JOIN:
                            final Object[] valoresCondicion = (Object[]) mapeoCondicionAtributo[1];
                            valorAtributo = valoresCondicion[0];
                            criterio = getCriteriosBusqueda(sesion,
                                    valorAtributo, mapCondiciones, criterio,
                                    aliasAtributoSB.toString());
                            break;
                        default:
                            LOG.warn(tipoCondicion);
                            break;
                    }
                }
            }
        }
        return criterio;
    }

    /**
     * Recupera el valor para el atributo indicado en determinado objeto.
     *
     * @param atributo
     *            atributo del que se quiere buscar el valor.
     * @param objeto
     *            objeto de cual se desea recuperar el atributo indicado
     * @return Object, valor del atributo indicado en el objeto
     */
    private static Object obtenerAtributo(final Field atributo,
            final Object objeto) {
        Object valorAtributo = null;
        if (atributo == null || atributo.getName() == null || objeto == null) {
            return valorAtributo;
        }
        Method metodoGet;
        final String nombreAtributo = atributo.getName();
        final StringBuilder nombreMetodoSB = new StringBuilder("get");
        nombreMetodoSB.append(nombreAtributo.substring(0, 1).toUpperCase());
        if (nombreAtributo.length() > 1) {
            nombreMetodoSB.append(nombreAtributo.substring(1));
        }
        try {
            metodoGet = objeto.getClass().getMethod(nombreMetodoSB.toString(),
                    null);
            valorAtributo = metodoGet.invoke(objeto, null);
        } catch (NoSuchMethodException nsmex) {
            LOG.error(nsmex.getMessage());
        } catch (IllegalAccessException iacex) {
            LOG.error(iacex.getMessage());
        } catch (InvocationTargetException itaex) {
            LOG.error(itaex.getMessage());
        }
        return valorAtributo;
    }

    /**
     * Recupera el metodo get del atributo.
     *
     * @param atributo
     *            atributo del que se quiere obtener el metodo get
     * @return Method
     */
    private static Method obtenerMetodoGetAtributo(final Field atributo) {
        Method metodo = null;
        if (atributo == null || atributo.getName() == null) {
            return metodo;
        }
        final String nombreAtributo = atributo.getName();
        final StringBuilder nombreMetodoSB = new StringBuilder("get");
        nombreMetodoSB.append(nombreAtributo.substring(0, 1).toUpperCase());
        if (nombreAtributo.length() > 1) {
            nombreMetodoSB.append(nombreAtributo.substring(1));
        }
        try {
            metodo = atributo.getDeclaringClass().getMethod(
                    nombreMetodoSB.toString(), null);
        } catch (NoSuchMethodException nsmex) {
            LOG.error(nsmex.getMessage());
        } catch (SecurityException sqex) {
            LOG.error(sqex.getMessage());
        }
        return metodo;
    }

    /**
     * Método encargado de obtener un criterio de restricción (Criterion) a
     * partir del atributo y la condición y los valores a aplicar.
     *
     * @param atributo
     *            objeto de tipo Field que representa el atributo de la
     *            restricción.
     * @param mapeoCondicionAtributo
     *            es un Object que debe ser un vector de 2, donde el primer
     *            valor sera un objeto de tipo TipoCondicion y especifica la
     *            restricción a aplicar y el segundo sera un objeto del tipo
     *            requerido para aplicar la condicion.
     * @param alias
     *            objeto de tipo String que debe llevar el nombre del alias que
     *            tiene la relación que se desea usar en el criterioBase
     * @return Criterion, restricción a aplicar
     */
    private static Criterion obtenerRestriccion(final Field atributo,
            final Object mapeoCondicionAtributo, final String alias) {
        Criterion restriccion = null;
        TipoCondicion tipoCondicion;
        Object[] valoresCondicion;
        final StringBuilder nombreAtributoSB = new StringBuilder();
        final Method metodoGet = UtilidadesBusqueda
                .obtenerMetodoGetAtributo(atributo);
        final Object[] condicionAtributo = (Object[]) mapeoCondicionAtributo;
        // Validar que el mapeoCondicionAtributo tenga los dos campos que
        // requiere para generar la restricción.
        if (condicionAtributo.length != 2) {
            return restriccion;
        }
        tipoCondicion = (TipoCondicion) condicionAtributo[0];
        // Validar que el atributo tenga anotación de tipo Column lo que indica
        // que es un campo directo a la tabla
        if (atributo.isAnnotationPresent(javax.persistence.Column.class)
                || atributo.isAnnotationPresent(javax.persistence.Id.class)
                || (metodoGet != null && (metodoGet
                        .isAnnotationPresent(javax.persistence.Column.class) || metodoGet
                        .isAnnotationPresent(javax.persistence.Id.class)))) {
            if (alias != null && !alias.trim().equals("")) {
                nombreAtributoSB.append(alias);
                nombreAtributoSB.append(".");
            }
            nombreAtributoSB.append(atributo.getName());
            switch (tipoCondicion) {
                case BETWEEN:
                    valoresCondicion = (Object[]) condicionAtributo[1];
                    if (valoresCondicion != null
                            && valoresCondicion.length >= 2) {
                        restriccion = Restrictions.between(nombreAtributoSB
                                .toString(), valoresCondicion[0],
                                valoresCondicion[1]);
                    }
                    break;
                case EQUAL:
                    valoresCondicion = (Object[]) condicionAtributo[1];
                    if (valoresCondicion != null
                            && valoresCondicion.length >= 1) {
                        restriccion = Restrictions.eq(nombreAtributoSB
                                .toString(), valoresCondicion[0]);
                    }
                    break;
                case IN:
                    valoresCondicion = (Object[]) condicionAtributo[1];
                    if (valoresCondicion != null
                            && valoresCondicion.length >= 1) {
                        restriccion = Restrictions.in(nombreAtributoSB
                                .toString(), valoresCondicion);
                    }
                    break;
                case ISNOTNULL:
                    restriccion = Restrictions.isNotNull(nombreAtributoSB
                            .toString());
                    break;
                case ISNULL:
                    restriccion = Restrictions.isNull(nombreAtributoSB
                            .toString());
                    break;
                case MAYOR:
                    valoresCondicion = (Object[]) condicionAtributo[1];
                    if (valoresCondicion != null
                            && valoresCondicion.length >= 1) {
                        restriccion = Restrictions.gt(nombreAtributoSB
                                .toString(), valoresCondicion[0]);
                    }
                    break;
                case MAYORIGUAL:
                    valoresCondicion = (Object[]) condicionAtributo[1];
                    if (valoresCondicion != null
                            && valoresCondicion.length >= 1) {
                        restriccion = Restrictions.ge(nombreAtributoSB
                                .toString(), valoresCondicion[0]);
                    }
                    break;
                case MENOR:
                    valoresCondicion = (Object[]) condicionAtributo[1];
                    if (valoresCondicion != null
                            && valoresCondicion.length >= 1) {
                        restriccion = Restrictions.lt(nombreAtributoSB
                                .toString(), valoresCondicion[0]);
                    }
                    break;
                case MENORIGUAL:
                    valoresCondicion = (Object[]) condicionAtributo[1];
                    if (valoresCondicion != null
                            && valoresCondicion.length >= 1) {
                        restriccion = Restrictions.le(nombreAtributoSB
                                .toString(), valoresCondicion[0]);
                    }
                    break;
                case NOTEQUAL:
                    valoresCondicion = (Object[]) condicionAtributo[1];
                    if (valoresCondicion != null
                            && valoresCondicion.length >= 1) {
                        restriccion = Restrictions.ne(nombreAtributoSB
                                .toString(), valoresCondicion[0]);
                    }
                    break;
                default:
                    LOG.warn(tipoCondicion);
                    break;
            }
        }
        return restriccion;
    }
}