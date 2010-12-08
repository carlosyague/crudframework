package es.uma.crudframework.exception;

/**
 * Excepción raíz del SIGB LibrÆ. Hereda de java.lang.RuntimeException, va a
 * contener las utilidades comunes de gestión de mensajes de log.
 * 
 * @author cyague
 * @version 1.0
 */
public class CrudException extends RuntimeException {

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
    public CrudException() {
        super();
    }

    /**
     * Crea una nueva instancia de la excepción.
     * 
     * @param codigo
     *            Clave del mensaje de error en el fichero de properties.
     */
    public CrudException(final String codigo) {
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
    public CrudException(final String codigo, final Throwable causa) {
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
    public CrudException(final String codigo, final String mensaje,
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
    public CrudException(final String codigo, final String mensaje) {
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
