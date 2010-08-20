package es.uma.crudframework.trazas.interceptors;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.support.AopUtils;

/**
 * Clase que se usará para escribir las trazas al entrar y al salir de cada
 * método. Será invocada por Spring mediante AOP.
 */
public class LoggingInterceptor implements MethodBeforeAdvice,
        AfterReturningAdvice {

    /**
     * Usado para escribir trazas.
     */
    private static Log log = null;

    /**
     * Constructor por defecto.
     */
    public LoggingInterceptor() {
        // Default constructor.
    }

    /**
     * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method,
     *      java.lang.Object[], java.lang.Object)
     */
    public void before(Method metodo, Object[] argumentos, Object clase)
            throws Throwable {
        log = LogFactory.getLog(getClass(clase));
        log.debug("Comenzando la ejecución del método " + metodo.getName());
        if (argumentos != null) {
            for (int i = 0; i < argumentos.length; i++) {
                log.debug("Argumento " + i + ": " + argumentos[i]);
            }
        }
    }

    /**
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object,
     *      java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    public void afterReturning(Object resultado, Method metodo,
            Object[] argumentos, Object clase) throws Throwable {
        log = LogFactory.getLog(getClass(clase));
        log.debug("Finalizando la ejecución del método " + metodo.getName());
        if (resultado != null) {
            log.debug("Valor devuelto: " + resultado);
        }
    }

    /**
     * Devuelve la clase, teniendo en cuenta que puede ser un proxy.
     * 
     * @param clase
     *            Objeto que contiene la clase.
     * @return Clase contenida.
     */
    private Class getClass(Object clase) {
        return AopUtils.getTargetClass(clase);
    }

}
