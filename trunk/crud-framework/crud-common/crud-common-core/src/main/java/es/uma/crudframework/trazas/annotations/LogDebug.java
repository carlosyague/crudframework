package es.uma.crudframework.trazas.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for declarative debug logging
 * 
 * @author Jose Luis Noheda
 *         http://internna.blogspot.com/2007/01/one-of-questions
 *         -that-arise-time-and.html
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface LogDebug {

    /**
     * Devuelve la clase de logging.
     */
    @SuppressWarnings("unchecked")
    Class loggerClass();
}