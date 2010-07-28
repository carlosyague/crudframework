package org.librae.adquisicion.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.adminconfig.model.Direccion;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un DireccionPostal
 *
 * @author jcdiaz
 */
@Entity(name = DireccionPostal.NAME_ENTITY)
@Table(name = DireccionPostal.TABLE_NAME)
public class DireccionPostal extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Proveedor necesita un
     * DireccionPostal Version UID
     */
    private static final long   serialVersionUID              = 5271359988563316448L;

    public static final String  NAME_ENTITY                   = "org.librae.adquisicion.model.DireccionPostal";
    public static final String  TABLE_NAME                    = "ADQ_DIRECCIONES_POSTALES";
    private static final String ID_GENERATOR_NAME             = "generator_adq_direcciones_postales";
    private static final String ID_SEQUENCE_NAME              = "SEQ_ADQ_DIRECCIONES_POSTALES";
    public static final String  COLUMN_NAME_ID                = "X_DIRECCION_POSTAL";
    public static final String  COLUMN_NAME_TELEFONO          = "T_TELEFONO";
    public static final String  COLUMN_NAME_FAX               = "T_FAX";
    public static final String  COLUMN_NAME_EMAIL             = "T_EMAIL";
    public static final String  COLUMN_NAME_PRINCIPAL         = "L_PRINCIPAL";
    public static final String  COLUMN_NAME_COMENTARIOS       = "T_COMENTARIOS";
    public static final String  COLUMN_NAME_DIRECCION_FK      = "DIR_X_DIRECCION";
    public static final String  COLUMN_NAME_PROVEEDOR_FK      = "PRO_X_PROVEEDOR";


    public static final String  PROPERTY_NAME_ID                = "id";
    public static final String  PROPERTY_NAME_TELEFONO          = "telefono";
    public static final String  PROPERTY_NAME_FAX               = "fax";
    public static final String  PROPERTY_NAME_EMAIL             = "email";
    public static final String  PROPERTY_NAME_PRINCIPAL         = "principal";
    public static final String  PROPERTY_NAME_COMENTARIOS       = "comentarios";
    public static final String  PROPERTY_NAME_PROVEEDOR_FK      = "proveedor";
    public static final String  PROPERTY_NAME_DIRECCION_FK      = "direccion";
    public static final String  PROPERTY_NAME_CONTACTOS_DIRECCIONES = "contactosDirecciones";

    /**
     * clave principal
     */
    private Long                id;

    /**
     * telefono de la sucursal
     */
    private String              telefono;

    /**
     * fax de la sucursal
     */
    private String              fax;

    /**
     * email de la sucursal
     */
    private String              email;

    /**
     * indica si es principal o no
     */
    private Boolean             principal;

    /**
     * Comentarios
     */
    private String              comentarios;


	/**
     * coleccion de contactos asociados a la direccion
     */
    List<ContactoDireccion> 				contactosDirecciones;

    /**
    * referencia al proveedor
    */
    private Proveedor           proveedor;

    /**
     * referencia a los contactos
     */

    /**
     * referencia a la direccion
     */
    private Direccion direccion;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Direccion.class)
    @JoinColumn(name = DireccionPostal.COLUMN_NAME_DIRECCION_FK)
    public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	/**
     * Constructor sin parametros
     */
    protected DireccionPostal() {
    }

    /**
     * Constructor con parámetros
     *
     * @param email
     * @param emailContacto
     * @param fax
     * @param nombreContacto
     * @param principal
     * @param telefono
     * @param telefonoContacto
     */
    public DireccionPostal(String email, String fax,
           Boolean principal, String telefono) {
        this.email = email;
        this.fax = fax;
        this.principal = principal;
        this.telefono = telefono;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = DireccionPostal.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = DireccionPostal.ID_SEQUENCE_NAME)
    @Column(name = DireccionPostal.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * /**
     *
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the telefono
     */
    @Column(name = DireccionPostal.COLUMN_NAME_TELEFONO, length = 20)
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono
     *            the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the fax
     */
    @Column(name = DireccionPostal.COLUMN_NAME_FAX, length = 19)
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the email
     */
    @Column(name = DireccionPostal.COLUMN_NAME_EMAIL, length = 80)
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }




    /**
     * @return the comentarios
     */
    @Column(name = DireccionPostal.COLUMN_NAME_COMENTARIOS, length = 400)
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the principal
     */
    @Column(name = DireccionPostal.COLUMN_NAME_PRINCIPAL)
    public Boolean getPrincipal() {
        return principal;
    }

    /**
     * @param principal
     *            the principal to set
     */
    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    /**
     * @return the proveedor
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.Proveedor.class)
    @JoinColumn(name = DireccionPostal.COLUMN_NAME_PROVEEDOR_FK)
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor
     *            the proveedor to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    /**
     *
     * @return contactosDirecciones
     */
    @OneToMany(mappedBy = ContactoDireccion.PROPERTY_NAME_DIRECCION_POSTAL_FK, cascade = { CascadeType.ALL })
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<ContactoDireccion> getContactosDirecciones() {
		return contactosDirecciones;
	}

    /**
     *
     * @param contactosDirecciones
     */
	public void setContactosDirecciones(List<ContactoDireccion> contactosDirecciones) {
		this.contactosDirecciones = contactosDirecciones;
	}


    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fax == null) ? 0 : fax.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((principal == null) ? 0 : principal.hashCode());
        result = prime * result
                + ((proveedor == null) ? 0 : proveedor.hashCode());
        result = prime * result
                + ((telefono == null) ? 0 : telefono.hashCode());
        result = prime * result + ((comentarios == null) ? 0 : comentarios.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DireccionPostal)) {
            return false;
        }
        DireccionPostal other = (DireccionPostal) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }

        if (fax == null) {
            if (other.fax != null) {
                return false;
            }
        } else if (!fax.equals(other.fax)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        if (principal == null) {
            if (other.principal != null) {
                return false;
            }
        } else if (!principal.equals(other.principal)) {
            return false;
        }
        if (proveedor == null) {
            if (other.proveedor != null) {
                return false;
            }
        } else if (!proveedor.equals(other.proveedor)) {
            return false;
        }
        if (telefono == null) {
            if (other.telefono != null) {
                return false;
            }
        } else if (!telefono.equals(other.telefono)) {
            return false;
        }

        if (comentarios == null) {
            if (other.comentarios != null) {
                return false;
            }
        } else if (!comentarios.equals(other.comentarios)) {
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(DireccionPostal.COLUMN_NAME_ID,
                this.id).append(DireccionPostal.COLUMN_NAME_TELEFONO,
                this.telefono)
                .append(DireccionPostal.COLUMN_NAME_FAX, this.fax).append(
                        DireccionPostal.COLUMN_NAME_EMAIL, this.email).append(
                        DireccionPostal.COLUMN_NAME_PRINCIPAL, this.principal).append(
                        DireccionPostal.COLUMN_NAME_COMENTARIOS, this.comentarios).toString();
    }

}