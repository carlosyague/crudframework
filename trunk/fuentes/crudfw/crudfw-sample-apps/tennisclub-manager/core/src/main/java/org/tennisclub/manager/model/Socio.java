package org.tennisclub.manager.model;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import es.uma.crudframework.model.BaseObject;

@Entity
public class Socio extends BaseObject {

	/**
	 * BaseObject implements Serializable
	 */
	private static final long serialVersionUID = -4719587662761533400L;
	
	/**
	 * POJO-JPA Constants
	 */
    public static final String PROPERTY_FIELD_NAME = "name";
    public static final String PROPERTY_FIELD_FIRSTNAME = "firstName";    
    public static final String PROPERTY_FIELD_LASTNAME = "lastName";
    public static final String PROPERTY_FIELD_PHONE = "phone";
    
	/**
	 * fields
	 */
	private String name;
	private String firstName;
	private String lastName;
	private String phone;
	

	/**
	 * INICIO getter & setters
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * FIN getter & setters
	 */
	
	
	/**
	 * INICIO override methods
	 */	
	
	@Override
	public boolean equals(Object obj) {
		// if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Socio)) {
            return false;
        }

        final Socio other = (Socio) obj;

        if (!this.getName().equals(other.getName())) {
            return false;
        }
        
        if (!this.getFirstName().equals(other.getFirstName())) {
            return false;
        }
        
        if (!this.getLastName().equals(other.getLastName())) {
            return false;
        }

        return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : this.getId().hashCode());
        result = prime
                * result
                + ((this.getName() == null) ? 0 : this.getName()
                        .hashCode());
        result = prime
        * result
        + ((this.getFirstName() == null) ? 0 : this.getFirstName()
                .hashCode());

        return result;
	}

	@Override
	public String toString() {
		final ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append(Socio.PROPERTY_FIELD_ID, this.getId());
		tsb.append(Socio.PROPERTY_FIELD_NAME, this.getName());
		tsb.append(Socio.PROPERTY_FIELD_FIRSTNAME, this.getFirstName());
		tsb.append(Socio.PROPERTY_FIELD_LASTNAME, this.getLastName());
		tsb.append(Socio.PROPERTY_FIELD_PHONE, this.getPhone());		
		return tsb.toString();
	}
	
	/**
	 * FIN override methods
	 */	

}
