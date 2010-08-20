package es.uma.crudframework.exception;

import org.springframework.aop.ThrowsAdvice;

/**
 * Excepción que se elevará al producirse un error no esperado en la aplicación.
 * Implementa la interfaz ThrowsAdvice de Spring que permitirá definirla como
 * aspecto. Se usará para controlar errores críticos que no permita el buen
 * funcionamiento del sistema: - Fallos de configuración e instalación - Caídas
 * de alguno de los subsistemas externos o de la base de datos
 * 
 * @author cyague
 * @version 1.0
 */
public class CrudFaultException extends CrudException implements
        ThrowsAdvice {

    /**
     * LibraeFaultException serial version uid.
     */
    private static final long serialVersionUID = 1311975517474105041L;

    /**
     * Crea una nueva instancia vacia de la excepción.
     */
    public CrudFaultException() {
        super();
    }

    /**
     * Crea una nueva instancia de la excepción.
     * 
     * @param codigo
     *            Clave del mensaje de error en el fichero de properties.
     */
    public CrudFaultException(final String codigo) {
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
    public CrudFaultException(final String codigo, final Throwable causa) {
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
    public CrudFaultException(final String codigo, final String mensaje,
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
    public CrudFaultException(final String codigo, final String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }
}
