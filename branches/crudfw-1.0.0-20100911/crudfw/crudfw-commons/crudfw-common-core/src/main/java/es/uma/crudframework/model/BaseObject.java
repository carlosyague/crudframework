package es.uma.crudframework.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode().
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@MappedSuperclass
public abstract class BaseObject implements Serializable {

	public static final String PROPERTY_FIELD_ID = "id";
	private static final String ID_GENERATOR_NAME = "id_generator";
    private static final String ID_SEQUENCE_NAME = "SEQ_ID";
	
	protected Long id;
	
    /**
     * usado en los checkboxes de los listados JSF.
     */
    
    protected Boolean     selected = Boolean.FALSE;
    
    private final boolean par      = Boolean.FALSE;

    /**
     * usado en la creación temporal de beans que no queremos que sean salvables
     */
    
    private Boolean       temporal = Boolean.FALSE;

    /**
     * getter & setters<br>
     * ================
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * @return the selected
     */
	@Transient
    public final Boolean getSelected() {
        return selected;
    }

    /**
     * @param selected
     *            the selected to set
     */
    public final void setSelected(final Boolean selected) {
        this.selected = selected;
    }

    /**
     * @return the temporal
     */
    @Transient
    public final Boolean getTemporal() {
        return temporal;
    }

    /**
     * @param temporal
     *            the temporal to set
     */
    public final void setTemporal(final Boolean temporal) {
        this.temporal = temporal;
    }

    /**
     * Metodo encargado de devolver la clase css que adornara el rowStyleClass
     * del t:dfatatable. En caso de devolver null se hara caso a lo indicado en
     * rowClasses
     * 
     * @return estilo css de tr
     */
    @Transient
    public String getEstilo() {
        String res = "";

        if (this.getSelected()) {
            res = "FilaSelec";
        }

        return res;
    }

    /**
     * Copia los parámetros de un POJO <code>sourcePojo</code> de tipo
     * <code>sourceClass</code> a la instancia actual (<code>this</code>) de
     * <code>BaseObject</code>
     * 
     * @param sourcePojo
     * @param sourceClass
     */
    protected void copyParamsPojo(final BaseObject sourcePojo,
            final Class sourceClass) {

        final Field[] fields = sourceClass.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
            final Field field = fields[i];
            final int modifiers = field.getModifiers();

            if (!Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers)) {

                try {
                    field.setAccessible(true);

                    final Object value = field.get(sourcePojo);
                    field.set(this, value);

                } catch (final IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * mandatory methods<br>
     * =================
     */

    /**
     * Returns a multi-line String with key=value pairs.
     * 
     * @return a String representation of this class.
     */
    @Override
    public String toString() {
		return reflectionToString();
    }
    
    /**
     * 
     * @return
     */
    protected String reflectionToString() {
    	final ToStringBuilder tsb = new ToStringBuilder(this);
    	
    	final Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            final int modifiers = field.getModifiers();

            if (!Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers)) {

                try {
                    field.setAccessible(true);
                    
                    /**
                     * toString() de cada campo
                     */
                    final String fieldName = field.getName();
                    final Object fieldValue = field.get(this);
                    tsb.append(fieldName, fieldValue);
                    
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return tsb.toString();
    }

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     * 
     * @param o
     *            object to compare to
     * @return true/false based on equality tests
     */
    @Override
    public boolean equals(Object obj) {
    	// if they are the same instance
        if (this == obj) {
            return true;
        }
        
        Class clazz = this.getClass();
        Object objCasted = null;
        
        try {
        	objCasted = clazz.cast(obj);
        } catch (ClassCastException e) {
        	objCasted = null;
        }
        
        if (objCasted != null) {
        	return reflectionEquals(objCasted);
        }
        
        return false;
    }
    
    /**
     * 
     * @param other
     * @return
     */
    protected boolean reflectionEquals(Object other) {
    	boolean result = true;

        for (Field field : this.getClass().getDeclaredFields()) {
            final int modifiers = field.getModifiers();

            if (!Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers) && 
            		!field.getName().equals("id")) {

                try {
                    field.setAccessible(true);
                    
                    /**
                     * equals() de cada campo
                     */
                    final Object fieldValueThis = field.get(this);
                    final Object fieldValueOther = field.get(other);

                    final boolean fieldEquals = (fieldValueThis == null && fieldValueOther == null) ||
                    	(fieldValueThis != null && fieldValueOther != null && fieldValueThis.equals(fieldValueOther));
                    result = result && fieldEquals;
                    
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return result;
    }

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     * 
     * @return hashCode
     */
    @Override
	public int hashCode() {
    	return reflectionHashCode();
	}
    
    /**
     * 
     * @return
     */
    protected int reflectionHashCode() {
    	
    	int result = 31;
    	for (Field field : this.getClass().getDeclaredFields()) {
            final int modifiers = field.getModifiers();

            if (!Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers)) {

                try {
                    field.setAccessible(true);
                    
                    /**
                     * hashCode() de cada campo
                     */
                    final Object fieldValue = field.get(this);

                    result += (fieldValue == null)? 0 : fieldValue.hashCode();
                    
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    	
    	return result;
    }
}
