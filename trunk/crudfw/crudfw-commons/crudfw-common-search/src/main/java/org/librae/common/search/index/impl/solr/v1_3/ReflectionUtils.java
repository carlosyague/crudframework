package org.librae.common.search.index.impl.solr.v1_3;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.search.index.impl.solr.Utils;

/**
 * Métodos auxiliares necesarios para la creación dinámica de POJOS en Apache
 * Solr 1.3 <br/>
 * Para ello se utiliza los Java Reflections
 * 
 * @author cyague
 */
public final class ReflectionUtils {

    /**
     * creates a query lucene
     * 
     * @param obj
     * @param booleanOperator
     *            @
     * @return
     * @throws LibraeException
     */
    public static String createQueryLucene(final Object obj,
            final String booleanOperator) throws LibraeException {
        String result = null;

        // @TODO ..

        result = "*:*";

        return result;
    }

    /**
     * returns the sort lucene
     * 
     * @param obj
     * @return
     * @throws LibraeException
     */
    public static String getSortField(final Object obj) throws LibraeException {
        String result = null;

        // @TODO ..

        result = "titulo";

        return result;
    }

    /**
     * invokes a setter operation to an instance
     * 
     * @param instance
     * @param fieldName
     * @param fieldValue
     * @param object
     * @return
     * @throws LibraeException
     * @throws IllegalAccessException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void setFieldValue(Object instance,
            final String fieldName, final Object fieldValue, final Object object)
            throws LibraeException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        final Class clazz = object.getClass();

        try {

            final Field field = clazz.getDeclaredField(fieldName);
            final Class fieldType = field.getType();

            final String methodName = Utils.createSetterMethodName(fieldName);
            final Class[] paramTypes = new Class[] { fieldType };

            Method method = null;

            try {
                method = clazz.getMethod(methodName, paramTypes);

                final Object[] args = Utils.createArgsParam(fieldType,
                        (String) fieldValue);

                instance = method.invoke(instance, args);

            } catch (final IllegalArgumentException iae) {
                throw ExceptionUtil
                        .crearLibraeException("\nSearchIndexerSolr.setFieldValue::No se pudo instanciar el método "
                                + method
                                + " con el valor "
                                + fieldValue
                                + " en el objeto "
                                + instance
                                + " ERROR: "
                                + iae);
            } catch (final NoSuchMethodException nsume) {
                throw ExceptionUtil
                        .crearLibraeException("\nSearchIndexerSolr.setFieldValue::No se pudo establecer el valor "
                                + fieldValue
                                + " al campo "
                                + fieldName
                                + " en la instancia del objeto "
                                + object
                                + " ya que el método "
                                + methodName
                                + " no está declarado. ERROR: " + nsume);
            }

        } catch (final NoSuchFieldException nsfe) {
            /*
             * Excepción controlada. Si fieldName no está declarado en la clase
             * T de object, esto quiere decir que no es un campo anotado con
             * arroba Field
             */
        }
    }

}
