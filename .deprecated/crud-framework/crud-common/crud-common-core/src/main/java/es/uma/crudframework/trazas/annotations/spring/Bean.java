package es.uma.crudframework.trazas.annotations.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Autowire;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.PACKAGE })
@Documented
/**
 * Annotation for registering a bean in the applicationContext
 * @author http://internna.blogspot.com/2007/01/one-of-questions-that-arise-time-and.html
 * 
 */
public @interface Bean {
    /**
     * The name for the Bean
     */
    String name() default "";

    /**
     * if this bean must be eager or lazy loaded
     */
    boolean lazy() default false;
    
    /**
     * the autowire behavior to use, default is autowire by name
     */
    Autowire autoWire() default Autowire.BY_NAME;
        
    /**
     * should the bean factory check for all the dependencies before instantiating a bean?
     */
    boolean dependencyCheck() default false;
        
    /**
     * the initialization method for this bean.
     */
    String initMethod() default "";
        
    /**
     * the destruction method for this bean.
     */
    String destroyMethod() default "";
        
    /**
     * if this is a factory bean, this is the method that will return an instance of the bean
     */
    String factoryMethod() default "";
        
    /**
     * the scope that will hold instances of this bean, does not use with the singleton property
     */
    Scope scope() default Scope.SINGLETON;
        
    /**
     * the scope that will hold instances of this bean, does not use with the singleton property or the scope property
     */
    String scopeName() default "";
}