/*
Empresa desarrolladora: INGENIA S.A.

Autor: Junta de Andalucía

Derechos de explotación propiedad de la Junta de Andalucía.

Éste programa es software libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los términos de la Licencia EUPL European Public License publicada por el organismo IDABC de la Comisión Europea, en su versión 1.0. o posteriores.

Éste programa se distribuye de buena fe, pero SIN NINGUNA GARANTÍA, incluso sin las presuntas garantías implícitas de USABILIDAD o ADECUACIÓN A PROPÓSITO CONCRETO. Para mas información consulte la Licencia EUPL European Public License.

Usted recibe una copia de la Licencia EUPL European Public License junto con este programa, si por algún motivo no le es posible visualizarla, puede consultarla en la siguiente URL: http://ec.europa.eu/idabc/servlets/Doc?id=31099

You should have received a copy of the EUPL European Public License along with this program. If not, see http://ec.europa.eu/idabc/servlets/Doc?id=31096

Vous devez avoir reçu une copie de la EUPL European Public License avec ce programme. Si non, voir http://ec.europa.eu/idabc/servlets/Doc?id=31205

Sie sollten eine Kopie der EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919

*/ 
package org.librae.common.trazas.annotations.spring;

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