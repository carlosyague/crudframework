package org.librae.common.exception;

/**
 * Excepción de errores de aplicación. Utilizada para capturar excepciones
 * "checked" que se consideren errores del sistema o de programación, pero no
 * bloqueantes: - Ficheros no encontrados o sin permisos de acceso - Errores de
 * comunicaciones - Errores al realizar operaciones - Acceso a entidades en base
 * de datos que han dejado de existir
 * 
 * @author cayetano
 * @version 1.0
 */
public class LibraeAppException extends LibraeException {

    /**
     * LibraeAppException serial version uid.
     */
    private static final long serialVersionUID = -8301169235740866711L;

    /**
     * Crea una nueva instancia vacia de la excepción.
     */
    public LibraeAppException() {
        super();
    }

    /**
     * Crea una nueva instancia de la excepción.
     * 
     * @param codigo
     *            Clave del mensaje de error en el fichero de properties.
     */
    public LibraeAppException(final String codigo) {
        super(codigo);
        this.codigo = codigo;
    }

    /**
     * Crea una nueva instancia de la excepción.
     * 
     * @param codigo
     *            Clave del mensaje de error en el fichero de properties.
     * @param causa
     *            Excepción original que irá encapsulada.
     */
    public LibraeAppException(final String codigo, final Throwable causa) {
        super(codigo, causa);
        this.codigo = codigo;
    }

    /**
     * Crea una nueva instancia de la excepción.
     * 
     * @param codigo
     *            Código de error asociado.
     * @param mensaje
     *            Mensaje de error asociado.
     * @param causa
     *            Excepción original que irá encapsulada.
     */
    public LibraeAppException(final String codigo, final String mensaje,
            final Throwable causa) {
        super(mensaje, causa);
        this.codigo = codigo;
    }

    /**
     * Crea una nueva instancia de la excepción.
     * 
     * @param codigo
     *            Código de error asociado.
     * @param mensaje
     *            Mensaje de error asociado.
     */
    public LibraeAppException(final String codigo, final String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }
}
