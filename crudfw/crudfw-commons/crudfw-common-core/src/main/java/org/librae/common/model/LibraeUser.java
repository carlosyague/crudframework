package org.librae.common.model;

import model.CodPer;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import javax.persistence.Transient;;

public class LibraeUser implements Serializable  {

    /**
     * fields
     */
    protected Long              id;
    protected String            usuario;

    public LibraeUser () {
    	super();
    }
    
    /**
     * Lista de permisos del usuario.
     */
    protected List<CodPer>      per;

    /**
     * constants
     */
    public static final String  PROPERTY_NAME_ID           = "id";
    public static final String  PROPERTY_NAME_USUARIO      = "usuario";

    /**
     * getter & setters
     */

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get username
     * 
     * @return
     */
    public String getUsuario() {
        return this.usuario;
    }

    /**
     * set username
     * 
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Transient
	public List<CodPer> getPer() {
		return per;
	}

	public void setPer(List<CodPer> per) {
		this.per = per;
	}

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof LibraeUser)) {
            return false;
        }

        final LibraeUser other = (LibraeUser) obj;

        if (!this.getUsuario().equals(other.getUsuario())) {
            return false;
        }

        return true;

    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : this.getId().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(this.PROPERTY_NAME_ID,
                this.getId()).toString();
    }
}
