package org.librae.common.util;


/**
 * Utilidades comunes para las cadenas.
 */
public final class StringUtil {

    /**
     * Constructor.
     */
    private StringUtil() {
        super();
    }

    /**
     * The log.
     */
    // private static Log log = LogFactory.getLog(StringUtil.class);
    /**
     * Confirma si la cadena esta vacia.
     * 
     * @param cadena
     *            valor que se quiere ver si es vacio
     * @return Confirma si la cadena esta vacia
     */
    public static boolean esVacia(final String cadena) {
        return cadena == null || cadena.length() == 0;
    }

    /**
     * Confirma si la cadena esta vacia.
     * 
     * @param cadena
     * @return
     */
    public static boolean esVacia(final Object cadena) {
        boolean vacia = true;
        if (cadena != null) {
            final String entrada = (String) cadena;
            if (entrada.length() != 0) {
                vacia = false;
            }
        }
        return vacia;
    }

    /**
     * Pone la primera letra de un String a mayusculas.
     * 
     * @param entrada
     *            String que se quiere procesar.
     * @return String con la primera letra en mayusculas.
     */
    public static String primeraLetraMayusculas(final String entrada) {
        String sufijo, prefijo;

        prefijo = entrada.substring(0, 1);
        prefijo = prefijo.toUpperCase();
        sufijo = entrada.substring(1);

        return prefijo + sufijo;
    }

}
