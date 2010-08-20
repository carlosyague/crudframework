package org.librae.common.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.model.LabelValue;

/**
 * Utility class to convert one object to another.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public final class ConvertUtil {

    /**
     * the log.
     */
    private static final Log LOG = LogFactory.getLog(ConvertUtil.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor.
     */
    private ConvertUtil() {
    }

    /**
     * Method to convert a ResourceBundle to a Map object.
     * 
     * @param rb
     *            a given resource bundle
     * @return Map a populated map
     */
    public static Map<String, String> convertBundleToMap(final ResourceBundle rb) {
        final Map<String, String> map = new HashMap<String, String>();

        final Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }

        return map;
    }

    /**
     * Convert a java.util.List of LabelValue objects to a LinkedHashMap.
     * 
     * @param list
     *            the list to convert
     * @return the populated map with the label as the key
     */
    public static Map<String, String> convertListToMap(
            final List<LabelValue> list) {
        final Map<String, String> map = new LinkedHashMap<String, String>();

        for (final LabelValue option : list) {
            map.put(option.getLabel(), option.getValue());
        }

        return map;
    }

    /**
     * Method to convert a ResourceBundle to a Properties object.
     * 
     * @param rb
     *            a given resource bundle
     * @return Properties a populated properties object
     */
    public static Properties convertBundleToProperties(final ResourceBundle rb) {
        final Properties props = new Properties();

        for (final Enumeration<String> keys = rb.getKeys(); keys
                .hasMoreElements();) {
            final String key = keys.nextElement();
            props.put(key, rb.getString(key));
        }

        return props;
    }

    /**
     * Convenience method used by tests to populate an object from a
     * ResourceBundle.
     * 
     * @param obj
     *            an initialized object
     * @param rb
     *            a resource bundle
     * @return a populated object
     */
    public static Object populateObject(final Object obj,
            final ResourceBundle rb) {
        try {
            final Map<String, String> map = ConvertUtil.convertBundleToMap(rb);
            BeanUtils.copyProperties(obj, map);
        } catch (final Exception e) {
            ConvertUtil.LOG.error("Exception occurred populating object: "
                    + e.getMessage());
        }

        return obj;
    }

    /**
     * Metodo encargado de hacer el md5 de la password indicada
     * 
     * @param password
     * @return md5 del password
     */
    public static String encodeMD5(String password) {
        String md5Password = password;
        final char[] HEXADECIMAL = { '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            final byte[] bytes = md.digest(md5Password.getBytes());
            final StringBuilder sb = new StringBuilder(2 * bytes.length);
            for (final byte b : bytes) {
                final int low = (b & 0x0f);
                final int high = ((b & 0xf0) >> 4);
                sb.append(HEXADECIMAL[high]);
                sb.append(HEXADECIMAL[low]);
            }
            md5Password = sb.toString();
        } catch (final Exception e) {
            ConvertUtil.LOG.error("Exception occurred encoding md5 object: "
                    + e.getMessage());
        }

        return md5Password;
    }

    /**
     * Devuelve el valor de un <code>Object</code>, en el caso de que sea
     * <code>null</code>, devuelve <code>nullValue</code>
     * 
     * @param value
     * @return
     */
    public static Object nullToValue(final Object value, final Object nullValue) {
        return value == null ? nullValue : value;
    }

    /**
     * Devuelve el valor de un <code>String</code>, en el caso de que sea
     * <code>null</code>, devuelve <code>nullValue</code>
     * 
     * @param value
     * @return
     */
    public static String nullToValue(final String value, final String nullValue) {
        return value == null ? nullValue : value;
    }

    /**
     * Devuelve el valor de un <code>Boolean</code>, en el caso de que sea
     * <code>null</code>, devuelve <code>nullValue</code>
     * 
     * @param value
     * @return
     */
    public static Boolean nullToValue(final Boolean value,
            final Boolean nullValue) {
        return value == null ? nullValue : value;
    }

    /**
     * Devuelve el valor de un <code>String</code>, en el caso de que sea
     * <code>null</code>, devuelve la cadena vacía
     * 
     * @param value
     * @return
     */
    public static String nullToVacio(final String value) {
        return ConvertUtil.nullToValue(value, "");
    }

    /**
     * Devuelve el valor de un <code>Boolean</code>, en el caso de que sea
     * <code>null</code>, devuelve <code>false</code>
     * 
     * @param value
     * @return
     */
    public static Boolean nullToFalse(final Boolean value) {
        return ConvertUtil.nullToValue(value, Boolean.FALSE);
    }

    /**
     * Devuelve el valor de un <code>Boolean</code>, en el caso de que sea
     * <code>null</code>, devuelve <code>true</code>
     * 
     * @param value
     * @return
     */
    public static Boolean nullToTrue(final Boolean value) {
        return ConvertUtil.nullToValue(value, Boolean.TRUE);
    }

    /**
     * Convierte una lista de String a Longs.
     * 
     * @param listaString
     * @return
     */
    public static List<Long> converStringToLong(List<String> listaString) {
        final List<Long> listaLongs = new ArrayList<Long>();
        for (final String item : listaString) {
            listaLongs.add(new Long(item));
        }
        return listaLongs;
    }

}
