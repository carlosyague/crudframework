package org.librae.common.exception;

/**
 * Excepción raíz del SIGB LibrÆ. Hereda de java.lang.RuntimeException, va a
 * contener las utilidades comunes de gestión de mensajes de log.
 * 
 * @author cayetano
 * @version 1.0 $Id$
 */
public class LibraeException extends RuntimeException {

    /**
     * Generated serialVersion.
     */
    private static final long serialVersionUID = -500359174841160574L;

    /**
     * Código de error.
     */
    protected String          codigo;

    /**
     * Crea una nueva instancia vacia de la excepción.
     */
    public LibraeException() {
        super();
    }

    /**
     * Crea una nueva instancia de la excepción.
     * 
     * @param codigo
     *            Clave del mensaje de error en el fichero de properties.
     */
    public LibraeException(final String codigo) {
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
    public LibraeException(final String codigo, final Throwable causa) {
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
    public LibraeException(final String codigo, final String mensaje,
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
    public LibraeException(final String codigo, final String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }

    // /////////////////////////////////////////////////////////////////////////
    // Getters y setters.
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Devuelve el código de error.
     * 
     * @return Código de error.
     */
    public final String getCodigo() {
        return codigo;
    }

}
