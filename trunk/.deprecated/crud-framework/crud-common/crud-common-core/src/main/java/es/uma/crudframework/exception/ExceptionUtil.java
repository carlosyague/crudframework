package es.uma.crudframework.exception;

import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;

/**
 * Esta clase contiene una serie de métodos para la creación de excepciones.
 *
 * @author cyague
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
        } catch (Exception e) {
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
            result = (String) MensajesError.get(key);
        } catch (Exception e) {
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
            result = (String) MensajesError.get(propertiFile, key);
        } catch (Exception e) {
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
        return getMessage(getKey(dae));
    }

    /**
     * Obtiene el mensaje de error genérico.
     *
     * @return Mensaje de error genérico.
     */
    public static String getGenericMessage() {
        return getMessage((String) MensajesError.get("ERROR_GENERICO"));
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
    public static CrudException crearCrudException(
            final String codigoDefecto, final DataAccessException dae) {
        String codigo = getKey(dae);
        String mensaje = getMessage(codigo);
        if (mensaje == null) {
            codigo = null;
            mensaje = getMessage(codigoDefecto);
        }
        return new CrudException(mensaje, codigo, dae);
    }

    /**
     * Creación de una excepción lanzada durante la ejecución de la aplicación
     * (no asociada a otra expceción).
     *
     * @param codigo
     *            Código del error.
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static CrudException crearCrudException(final String codigo) {
        String mensaje = getMessage(codigo);
        if (mensaje == null) {
            mensaje = getGenericMessage();
        }
        return new CrudException(codigo, mensaje);
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
    public static CrudException crearCrudException(
            final String propertiFile, final String codigo) {
        String mensaje = getMessage(propertiFile, codigo);
        if (mensaje == null) {
            mensaje = getGenericMessage();
        }
        return new CrudException(codigo, mensaje);
    }


    /**
     * Creación de una excepción a partir de una DataAccessException
     *
     * @param DataAccessException
     *
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static CrudException crearCrudException(final DataAccessException dae) {
        String codigo = getKey(dae);
        String mensaje = getMessage(codigo);
        if (mensaje == null) {
            mensaje = getGenericMessage();
        }
        return new CrudException(codigo, mensaje);
    }


    /**
     * Creación de una excepción a partir de una DataAccessException
     * con el texto del properti indicado
     *
     * @param DataAccessException
     * @param propertiFile nombre del properti del que obtener los mensajes
     *
     * @return Nueva excepción de tipo {@link LibraeException}.
     */
    public static CrudException crearCrudException(final DataAccessException dae,
                                                                    final String propertiFile) {
        String codigo = getKey(dae);
        String mensaje = getMessage(propertiFile, codigo);
        if (mensaje == null) {
            mensaje = getGenericMessage();
        }
        return new CrudException(codigo, mensaje);
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
    public static CrudException crearCrudException(final String codigo,
            final Throwable cause) {
        String mensaje = getMessage(codigo);
        if (mensaje == null) {
            mensaje = getGenericMessage();
        }
        return new CrudException(mensaje, codigo, cause);
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
    public static CrudException crearCrudException(final String codigo,
            final String propertiFile, final Throwable cause) {
        String mensaje = getMessage(propertiFile, codigo);
        if (mensaje == null) {
            mensaje = getGenericMessage();
        }
        return new CrudException(codigo, mensaje, cause);
    }
}
