package es.uma.crudframework.model.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)


public @interface CrudSemanticTag {
	
	/**
     * Devuelve el validador del campo
     * @return
     */
	String type() default SemanticTypeField.NONE;
    
    /**
     * Devuelve true si un campo de tipo String debe corresponderse a un textarea, en otro caso, será un inputText
     * @return
     */
    boolean textArea() default false;
    
    /**
     * Indica si un campo en la vista debe ser de sólo lectura
     * @return
     */
    boolean readonly() default false;
    
    /**
     * Indica si una campo de texto corresponde a un input password o no
     * @return
     */
    boolean password() default false;
    
    /**
     * Indica si está deshabilitado o no
     * @return
     */
    boolean disabled() default false;
    
    /**
     * Indica si está deshabilitado o no al crear una nueva entidad
     * @return
     */
    boolean disabledOnAdd() default false;
    
    /**
     * Indica si está deshabilitado o no al editar una entidad existente
     * @return
     */
    boolean disabledOnEdit() default false;
    
}
