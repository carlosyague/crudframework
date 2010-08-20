package org.librae.common.exception;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Clase que contiene los mensajes de error que se usar&aacute;n en las
 * excepciones.
 * 
 * @author cayetano
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
    public static final String PROPERTI_GENERAL               = "org.librae.common.exception.messages";

    /**
     * Constante PROPERTI_COMBOS.
     */
    public static final String PROPERTI_COMBOS                = "org.librae.common.messageCombo";

    /**
     * Constante PROPERTI_ADMINCONFIG.
     */
    public static final String PROPERTI_ADMINCONFIG           = "org.librae.adminconfig.messages";

    /**
     * Constante PROPERTI_CIRCULACION.
     */
    public static final String PROPERTI_CIRCULACION           = "org.librae.circulacion.messages";

    /**
     * Constante Workflow de PIB
     */
    public static final String PROPERTI_PIB_CIRCULACION       = "org.librae.circulacion.osworkflow.messages";

    /**
     * Constante PROPERTI_CATALOGACION.
     */
    public static final String PROPERTI_CATALOGACION          = "org.librae.catalogacion.messages";

    /**
     * Constante PROPERTI_IMPORTEXPORT.
     */
    public static final String PROPERTI_IMPORTEXPORT          = "org.librae.importexport.messages";

    /**
     * Constante PROPERTI_LECTORES.
     */
    public static final String PROPERTI_LECTORES              = "org.librae.lectores.messages";

    /**
     * Constante PROPERTI_OPAC.
     */
    public static final String PROPERTI_OPAC                  = "org.librae.opac.messages";

    /**
     * Constante PROPERTI_PUB_SERIADAS
     */
    public static final String PROPERTI_PUB_SERIADAS          = "org.librae.pubseriadas.messages";

    /**
     * Constante PROPERTI_ADQUISICION
     */
    public static final String PROPERTI_ADQUISICION           = "org.librae.adquisicion.messages";
    /**
     * constante PROPERTI_ESTADISTICAS_INFORMES
     */
    public static final String PROPERTI_ESTADISTICAS_INFORMES = "org.librae.estadisticas.messages";
    /**
     * constante PROPERTI_MENSAJERIA
     */
    public static final String PROPERTI_MENSAJERIA            = "org.librae.mensajeria.messages";

    /**
     * Constante PROPERTI_PROCPLANIFICADOS
     */
    public static final String PROPERTI_PROCPLANIFICADOS      = "org.librae.procplanificados.messages";

    /**
     * Constante PROPERTI_PRODIMPRESOS
     */
    public static final String PROPERTI_PRODIMPRESOS          = "org.librae.productos_impresos.messages";

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
            final ResourceBundle properti = ResourceBundle.getBundle(
                    propertiFile, locale);
            res = properti.getString(key);
        } catch (final Exception e) {
            // se devuelve null o ERROR_SIN_MENSAJE dependiendo de nullable
            if (nullable) {
                res = null;
            } else {
                res = get("ERROR_SIN_MENSAJE");
            }
        }

        return res;
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
     * @param locale
     *            locale enviado desde otra aplicación en ejecución
     * @return Mensaje de error.
     */
    public static String get(final String propertiFile, final String key,
            final boolean nullable, final Locale locale) {
        String res = "";

        try {
            final ResourceBundle properti = ResourceBundle.getBundle(
                    propertiFile, locale);
            res = properti.getString(key);
        } catch (final Exception e) {
            // se devuelve null o ERROR_SIN_MENSAJE dependiendo de nullable
            if (nullable) {
                res = null;
            } else {
                res = get("ERROR_SIN_MENSAJE");
            }
        }

        return res;
    }

    /**
     * Obtiene el mensaje con los argumentos que se les pase.
     * 
     * @param propertiFile
     * @param key
     * @param nullable
     * @param locale
     * @param argumentos
     * @return
     */
    public static String get(final String propertiFile, final String key,
            final boolean nullable, final Locale locale, String[] argumentos) {
        String res = "";
        try {
            final ResourceBundle properti = ResourceBundle.getBundle(
                    propertiFile, locale);
            String temp = properti.getString(key);
            java.text.MessageFormat formatter = new java.text.MessageFormat("");
            formatter.applyPattern(temp);
            res = formatter.format(argumentos);
        } catch (final Exception e) {
            if (nullable) {
                res = null;
            } else {
                res = get("ERROR_SIN_MENSAJE");
            }
        }
        return res;
    }

    public static String get(final String propertiFile, final String key,
            String[] argumentos) {
        return get(propertiFile, key, false, argumentos);
    }
    
    public static String get(final String propertiFile, final String key,
            final boolean nullable, String[] argumentos) {
        String res = "";
        final Locale locale = LocaleContextHolder.getLocale();
        try {
            final ResourceBundle properti = ResourceBundle.getBundle(
                    propertiFile, locale);
            String temp = properti.getString(key);
            java.text.MessageFormat formatter = new java.text.MessageFormat("");
            formatter.applyPattern(temp);
            res = formatter.format(argumentos);
        } catch (final Exception e) {
            if (nullable) {
                res = null;
            } else {
                res = get("ERROR_SIN_MENSAJE");
            }
        }
        return res;
    }
}
