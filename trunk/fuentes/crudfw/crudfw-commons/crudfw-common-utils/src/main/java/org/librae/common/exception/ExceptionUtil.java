package org.librae.common.exception;

import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;

/**
 * Esta clase contiene una serie de métodos para la creación de excepciones.
 * 
 * @author cayetano
 */
public class ExceptionUtil {

    /**
     * Construye la clave del mensaje de error que se usará en el fichero de
     * internacionalización.
     * 
     * @param dae
     *            Excepción lanzada por la base de datos.
     * @return Clave del mensaje de error.
     */
    public static String getKey(final DataAccessException dae) {
        String result = null;
        HibernateException he = null;

        try {
            he = (HibernateException) dae.getCause();
            if (he instanceof ConstraintViolationException) {
                result = ((ConstraintViolationException) he)
                        .getConstraintName();
            } else if (he instanceof PropertyValueException) {
                result = Constantes.ERR_REQ + "."
                        + ((PropertyValueException) he).getPropertyName();
            }
        } catch (final Exception e) {
            // Error general de base de datos.
            result = "ERROR_GENERAL_BD";
        }

        if (result == null) {
            result = "ERROR_GENERAL_BD";
        }

        return result;
    }

    /**
     * Obtiene el mensaje a partir de su clave.
     * 
     * @param key
     *            Clave del mensaje.
     * @return Texto del mensaje.
     */
    public static String getMessage(final String key) {
        String result;
        try {
            result = MensajesError.get(key);
        } catch (final Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * Obtiene el mensaje del properti indicado a partir de su clave.
     * 
     * @param key
     *            Clave del mensaje.
     * @param propertiFile
     *            Nombre del archivo properti de los mensajes
     * @return Texto del mensaje.
     */
    public static String getMessage(final String propertiFile, final String key) {
        String result;
        try {
            result = MensajesError.get(propertiFile, key);
        } catch (final Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * Obtiene el mensaje del properti indicado a partir de su clave.
     * 
     * @param key
     *            Clave del mensaje.
     * @param propertiFile
     *            Nombre del archivo properti de los mensajes
     * @return Texto del mensaje.
     */
    public static String getMessage(final String propertiFile, final String key, String[] argumentos) {
        String result;
        try {
            result = MensajesError.get(propertiFile, key, argumentos);
        } catch (final Exception e) {
            result = null;
        }
        return result;
    }
    
    /**
     * Obtiene el mensaje de error correspondiente a la excepción dada.
     * 
     * @param dae
     *            Excepción lanzada por la base de datos.
     * @return Mensaje de error.
     */
    public static String getMessage(final DataAccessException dae) {
        return ExceptionUtil.getMessage(ExceptionUtil.getKey(dae));
    }

    /**
     * Obtiene el mensaje de error genérico.
     * 
     * @return Mensaje de error genérico.
     */
    public static String getGenericMessage() {
        return ExceptionUtil.getMessage(MensajesError.get("ERROR_GENERICO"));
    }

    /**
     * Crea una nueva excepción LibraeException. Intentará obtener un mensaje de
     * error a partir de la excepción original. Si no se consigue, se usará el
     * mensaje de error por defecto, recuperado usando
     * <param>codigoDefecto</param>.
     * 
     * @param codigoDefecto
     *            Código del mensaje de error por defecto.
     * @param dae
     *            Excepción lanzada por la base de datos.
     * @return Nueva excepción de tipo LibraeException.
     */
    public static LibraeException crearLibraeException(
            final String codigoDefecto, final DataAccessException dae) {
        String codigo = ExceptionUtil.getKey(dae);
        String mensaje = ExceptionUtil.getMessage(codigo);
        if (mensaje == null) {
            codigo = null;
            mensaje = ExceptionUtil.getMessage(codigoDefecto);
        }
        return new LibraeException(mensaje, codigo, dae);
    }

    /**
     * Creación de una excepción lanzada durante la ejecución de la aplicación
     * (no asociada a otra expceción).
     * 
     * @param codigo
     *            Código del error.
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static LibraeException crearLibraeException(final String codigo) {
        String mensaje = ExceptionUtil.getMessage(codigo);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }
        return new LibraeException(codigo, mensaje);
    }

    /**
     * Creación de una excepción lanzada durante la ejecución de la aplicación
     * (no asociada a otra expceción).
     * 
     * @param codigo
     *            Código del error.
     * @param msg
     *            Mensaje estático. Este mensaje será el devuelto por la
     *            exception de partida. Se deberá usar
     *            <code>getLocalizedMessage()</code>
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static LibraeException crearLibraeException(final String codigo,
            final StringBuilder msg) {
        String mensaje = ExceptionUtil.getMessage(codigo);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }

        if (msg != null) {
            if (mensaje == null) {
                mensaje = msg.toString();
            } else {
                mensaje += msg.toString();
            }
        }

        return new LibraeException(codigo, mensaje);
    }

    /**
     * Creación de una excepción lanzada durante la ejecución de la aplicación
     * (no asociada a otra expceción).
     * 
     * @param codigo
     *            Código del error.
     * @param propertiFile
     *            nombre del prooerti del que obtener los mensajes
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static LibraeException crearLibraeException(
            final String propertiFile, final String codigo) {
        String mensaje = ExceptionUtil.getMessage(propertiFile, codigo);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }
        return new LibraeException(codigo, mensaje);
    }
    
    /**
     * Creación de una excepción lanzada durante la ejecución de la aplicación
     * (no asociada a otra expceción).
     * 
     * @param codigo
     *            Código del error.
     * @param propertiFile
     *            nombre del prooerti del que obtener los mensajes
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static LibraeException crearLibraeException(
            final String propertiFile, final String codigo, String[] argumentos) {
        String mensaje = ExceptionUtil.getMessage(propertiFile, codigo, argumentos);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }
        return new LibraeException(codigo, mensaje);
    }

    /**
     * Creación de una excepción lanzada durante la ejecución de la aplicación
     * (no asociada a otra excepción).
     * 
     * @param codigo
     *            Código del error.
     * @param propertiFile
     *            nombre del prooerti del que obtener los mensajes
     * @param msg
     *            Mensaje estático. Este mensaje será el devuelto por la
     *            exception de partida. Se deberá usar
     *            <code>getLocalizedMessage()</code>
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static LibraeException crearLibraeException(
            final String propertiFile, final String codigo,
            final StringBuilder msg) {
        String mensaje = ExceptionUtil.getMessage(propertiFile, codigo);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }

        if (msg != null) {
            if (mensaje == null) {
                mensaje = msg.toString();
            } else {
                mensaje += msg.toString();
            }
        }

        return new LibraeException(codigo, mensaje);
    }

    /**
     * Creación de una excepción a partir de una DataAccessException
     * 
     * @param DataAccessException
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static LibraeException crearLibraeException(
            final DataAccessException dae) {
        final String codigo = ExceptionUtil.getKey(dae);
        String mensaje = ExceptionUtil.getMessage(codigo);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }
        return new LibraeException(codigo, mensaje);
    }

    /**
     * Creación de una excepción a partir de una DataAccessException con el
     * texto del properti indicado
     * 
     * @param DataAccessException
     * @param propertiFile
     *            nombre del properti del que obtener los mensajes
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static LibraeException crearLibraeException(
            final DataAccessException dae, final String propertiFile) {
        final String codigo = ExceptionUtil.getKey(dae);
        String mensaje = ExceptionUtil.getMessage(propertiFile, codigo);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }
        return new LibraeException(codigo, mensaje);
    }

    /**
     * Creación de una excepción lanzada para encapsular otra producida.
     * 
     * @param codigo
     *            Código del error.
     * @param cause
     *            Nueva excepción de tipo {@link Moad}.
     * @return una excepción lanzada para encapsular otra producida
     */
    public static LibraeException crearLibraeException(final String codigo,
            final Throwable cause) {
        String mensaje = ExceptionUtil.getMessage(codigo);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }
        return new LibraeException(mensaje, codigo, cause);
    }

    /**
     * Creación de una excepción lanzada para encapsular otra producida.
     * 
     * @param codigo
     *            Código del error.
     * @param propertiFile
     *            , nombre del properti del que obtener los mensajes
     * @param cause
     *            Nueva excepción de tipo {@link Moad}.
     * @return
     */
    public static LibraeException crearLibraeException(final String codigo,
            final String propertiFile, final Throwable cause) {
        String mensaje = ExceptionUtil.getMessage(propertiFile, codigo);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }
        return new LibraeException(codigo, mensaje, cause);
    }

    /**
     * Crea y lanza una <code>LibraeException</code> en base a una condición
     * booleana. Se usa el fichero de properties por defecto
     * 'org.librae.common.exception.messages'
     * 
     * @param condition
     *            Condición de lanzamiento.
     * @param keyProperty
     *            property que se deberá mostrar en la excepción
     * @return
     */
    public static void createAndThrowLibraeExceptionByCondition(
            final Boolean condition, final String keyProperty)
            throws LibraeException {

        ExceptionUtil.createAndThrowLibraeExceptionByCondition(condition,
                MensajesError.PROPERTI_GENERAL, keyProperty);
    }

    /**
     * Crea y lanza una <code>LibraeException</code> en base a una condición
     * booleana
     * 
     * @param condition
     *            Condición de lanzamiento.
     * @param propertiesFile
     *            Fichero de properties multidioma que contiene los
     *            <code>messages</code>
     * @param keyProperty
     *            property que se deberá mostrar en la excepción
     * @return
     */
    public static void createAndThrowLibraeExceptionByCondition(
            final Boolean condition, final String propertiesFile,
            final String keyProperty) throws LibraeException {
        if (condition) {
            throw ExceptionUtil.crearLibraeException(propertiesFile,
                    keyProperty);
        }
    }

    /**
     * Creación de una excepción lanzada durante la ejecución de la aplicación
     * (no asociada a otra expceción).
     * 
     * @param codigo
     *            Código del error.
     * @param propertiFile
     *            nombre del prooerti del que obtener los mensajes
     * @return Nueva excepción de tipo {@link LibraeValidationException}.
     */
    public static LibraeValidationException crearLibraeValidationException(
            final String propertiFile, final String codigo) {
        String mensaje = ExceptionUtil.getMessage(propertiFile, codigo);
        if (mensaje == null) {
            mensaje = ExceptionUtil.getGenericMessage();
        }
        return new LibraeValidationException(codigo, mensaje);
    }

}
