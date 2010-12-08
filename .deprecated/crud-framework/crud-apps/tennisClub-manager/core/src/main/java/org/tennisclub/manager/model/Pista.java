package org.tennisclub.manager.model;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import es.uma.crudframework.model.BaseObject;

@Entity(name = Pista.ENTITY_NAME)
public class Pista extends BaseObject {

	/**
	 * BaseObject implements Serializable
	 */
	private static final long serialVersionUID = -4719587662761533400L;
	
	/**
	 * POJO-JPA Constants
	 */
	public static final String ENTITY_NAME = "org.tennisclub.manager.model.Pista";	
    
    public static final String PROPERTY_FIELD_TIPOPISTA = "tipoPista";
    public static final String PROPERTY_FIELD_NUMERO = "numero";    
    
	/**
	 * fields
	 */
	private Integer tipoPista;
	private Integer numero;
	

	/**
	 * INICIO getter & setters
	 */
	
	public Integer getTipoPista() {
		return tipoPista;
	}

	public void setTipoPista(Integer tipoPista) {
		this.tipoPista = tipoPista;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
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
        if (!(obj instanceof Pista)) {
            return false;
        }

        final Pista other = (Pista) obj;

        if (!this.getTipoPista().equals(other.getTipoPista())) {
            return false;
        }
        
        if (!this.getNumero().equals(other.getNumero())) {
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
                + ((this.getTipoPista() == null) ? 0 : this.getTipoPista()
                        .hashCode());
        result = prime
        * result
        + ((this.getNumero() == null) ? 0 : this.getNumero()
                .hashCode());

        return result;
	}

	@Override
	public String toString() {
		final ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append(Pista.PROPERTY_FIELD_ID, this.getId());
		tsb.append(Pista.PROPERTY_FIELD_TIPOPISTA, this.getTipoPista());
		tsb.append(Pista.PROPERTY_FIELD_NUMERO, this.getNumero());		
		return tsb.toString();
	}
	
	/**
	 * FIN override methods
	 */	

}
