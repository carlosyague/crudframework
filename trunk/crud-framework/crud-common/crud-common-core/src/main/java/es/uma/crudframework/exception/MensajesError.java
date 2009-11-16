package es.uma.crudframework.exception;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Clase que contiene los mensajes de error que se usar&aacute;n en las
 * excepciones.
 * 
 * @author cyague
 * @version 1.0
 */
public final class MensajesError {

    /***************************************************************************
     * TABLA DE CODIFICACION. Los caracteres Latin1 tienen que sustituirse por
     * los correspondientes en UTF-16 de codificación interna de Java. Latin1 -
     * JAVA ª \u00aa º \u00ba Á \u00c1 É \u00c9 Í \u00cd Ó \u00d3 Ú \u00da Ñ
     * \u00d1 á \u00e1 é \u00e9 í \u00ed ó \u00f3 ú \u00fa ñ \u00f1 ¡ \u00a1 ¿
     * \u00bf
     **************************************************************************/

    private MensajesError() {
        super();
    }

    /**
     * Constante PROPERTI_GENERAL.
     */
    public static final String PROPERTI_GENERAL     = "es.uma.crudframework.exception.messages";

    /**
     * Devuelve el mensaje de error correspondiente a un c\u00f3digo dado. del
     * fichero de mensajes general
     * 
     * @param key
     *            C\u00f3digo del mensaje.
     * @return Mensaje de error. Si no se encuentra el c\u00f3digo, se devuelve
     *         un mensaje genérico.
     */
    public static String get(final String key) {
        return get(PROPERTI_GENERAL, key, false);
    }

    /**
     * Devuelve el mensaje de error correspondiente a un c\u00f3digo dado. del
     * fichero de mensajes general
     * 
     * @param key
     *            Código del mensaje.
     * @param nullable
     *            boolean que indica si puede ser null o no
     * @return Mensaje de error.
     */
    public static String get(final String key, final boolean nullable) {
        return get(PROPERTI_GENERAL, key, nullable);
    }

    /**
     * Devuelve el mensaje de error correspondiente a un c\u00f3digo dado. del
     * fichero de mensajes indicado
     * 
     * @param propertiFile
     *            fichero del que tomar los mensajes
     * @param key
     *            C\u00f3digo del mensaje.
     * @return Mensaje de error. Si no se encuentra el c\u00f3digo, se devuelve
     *         un mensaje genérico.
     */
    public static String get(final String propertiFile, final String key) {
        return get(propertiFile, key, false);
    }

    /**
     * Devuelve el mensaje de error correspondiente a un c\u00f3digo dado. del
     * fichero de mensajes indicado
     * 
     * @param propertiFile
     *            fichero del que tomar los mensajes
     * @param key
     *            Código del mensaje.
     * @param nullable
     *            boolean que indica si puede ser null o no
     * @return Mensaje de error.
     */
    public static String get(final String propertiFile, final String key,
            final boolean nullable) {
        String res = "";
        final Locale locale = LocaleContextHolder.getLocale();

        try {
            System.out.println("propertiFile="+propertiFile);
            System.out.println("key="+key);
            System.out.println("nullable="+nullable);
        	final ResourceBundle properti = ResourceBundle.getBundle(
                    propertiFile, locale);
            res = properti.getString(key);
        } catch (Exception e) {
            // se devuelve null o ERROR_SIN_MENSAJE dependiendo de nullable
            if (nullable) {
                res = null;
            } else {
                res = get("ERROR_SIN_MENSAJE");
            }
        }

        return res;
    }

}
