package org.librae.common.exception;

/**
 * Excepción que encapsulará los errores de validación que se produzcan en la
 * capa de negocio o modelo de sistema.
 * 
 * @author cayetano
 * @version 1.0
 */
public class LibraeValidationException extends LibraeException {

    /**
     * LibraeValidationException serial version uid.
     */
    private static final long serialVersionUID = 6705860933992172703L;

    /**
     * Crea una nueva instancia vacia de la excepción.
     */
    public LibraeValidationException() {
        super();
    }

    /**
     * Crea una nueva instancia de la excepción.
     * 
     * @param codigo
     *            Clave del mensaje de error en el fichero de properties.
     */
    public LibraeValidationException(final String codigo) {
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
    public LibraeValidationException(final String codigo, final Throwable causa) {
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
    public LibraeValidationException(final String codigo, final String mensaje,
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
    public LibraeValidationException(final String codigo, final String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }
}
