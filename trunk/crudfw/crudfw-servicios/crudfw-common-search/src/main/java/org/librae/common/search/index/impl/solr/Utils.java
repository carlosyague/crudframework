package org.librae.common.search.index.impl.solr;

import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;

/**
 * @author cyague
 */
public final class Utils {

    public final static String PREFIX_METHOD_GETTER = "get";
    public final static String PREFIX_METHOD_SETTER = "set";

    /**
     * creates the argument values array to invoke method
     * 
     * @param fieldType
     * @param value
     * @return
     */
    public static Object[] createArgsParam(final Class fieldType, final String value)
            throws LibraeException {
        Object[] result = null;

        // String
        if (fieldType.equals(java.lang.String.class)) {
            result = new String[] { value };

            // Long
        } else if (fieldType.equals(java.lang.Long.class)) {
            result = new Long[] { Long.parseLong(value) };

            // Integer
        } else if (fieldType.equals(java.lang.Integer.class)) {
            result = new Integer[] { Integer.parseInt(value) };

            // Float
        } else if (fieldType.equals(java.lang.Float.class)) {
            result = new Float[] { Float.parseFloat(value) };

            // Double
        } else if (fieldType.equals(java.lang.Double.class)) {
            result = new Double[] { Double.parseDouble(value) };

            // Boolean
        } else if (fieldType.equals(java.lang.Boolean.class)) {
            result = new Boolean[] { Boolean.parseBoolean(value) };

        } else {
            throw ExceptionUtil
                    .crearLibraeException("\nEl tipo "
                            + fieldType
                            + " no está contemplado en librae-common-search. Es preciso añadir una caso adicional para "
                            + fieldType
                            + " en el método Object[] createArgsParam() de "
                            + Utils.class);
        }

        return result;
    }

    /**
     * returns a method name of a getter operation
     * 
     * @param fieldName
     * @return
     */
    public static String createGetterMethodName(final String fieldName) {

        return Utils.createMethodNameByPrefix(fieldName, Utils.PREFIX_METHOD_GETTER);
    }

    /**
     * returns a method name of a setter operation
     * 
     * @param fieldName
     * @return
     */
    public static String createSetterMethodName(final String fieldName) {

        return Utils.createMethodNameByPrefix(fieldName, Utils.PREFIX_METHOD_SETTER);
    }

    /**
     * returns a method name by a prefix
     * 
     * @param fieldName
     * @param methodType
     * @return
     */
    public static String createMethodNameByPrefix(final String fieldName,
            final String prefix) {
        final StringBuilder result = new StringBuilder();

        if (fieldName != null && fieldName.length() >= 1) {

            result.append(prefix).append(
                    fieldName.substring(0, 1).toUpperCase());

            if (fieldName.length() > 1) {
                result.append(fieldName.substring(1));
            }
        }

        return result.toString();
    }
}
