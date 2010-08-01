package org.librae.adquisicion.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.librae.common.model.BaseObject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = ContactoDireccion.NAME_ENTITY)
@Table(name = ContactoDireccion.TABLE_NAME)
public class ContactoDireccion extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String  NAME_ENTITY                   = "org.librae.adquisicion.model.ContactoDireccion";
    public static final String  TABLE_NAME                    = "ADQ_CONTACTOS_DIRECCIONES";
    private static final String ID_GENERATOR_NAME             = "generator_adq_contactos_direcciones";
    private static final String ID_SEQUENCE_NAME              = "SEQ_ADQ_CONTACTOS_DIRECCIONES";
    public static final String  COLUMN_NAME_ID                = "X_CONTACTO_DIRECCION";
    public static final String  COLUMN_NAME_NOMBRES		      = "T_NOMBRES";
    public static final String  COLUMN_NAME_APELLIDOS	   	  = "T_APELLIDOS";
    public static final String  COLUMN_NAME_TELEFONO1         = "T_TELEFONO1";
    public static final String  COLUMN_NAME_TELEFONO2         = "T_TELEFONO2";
    public static final String  COLUMN_NAME_EMAIL             = "T_EMAIL";
    public static final String  COLUMN_NAME_DIRECCION_POSTAL_FK  = "DIR_X_DIRECCION_POSTAL";


    public static final String  PROPERTY_NAME_ID        		= "id";
    public static final String  PROPERTY_NAME_NOMBRES          	= "nombres";
    public static final String  PROPERTY_NAME_APELLIDOS         = "apellidos";
    public static final String  PROPERTY_NAME_TELEFONO1         = "telefono1";
    public static final String  PROPERTY_NAME_TELEFONO2         = "telefono2";
    public static final String  PROPERTY_NAME_EMAIL		        = "email";
    public static final String  PROPERTY_NAME_DIRECCION_POSTAL_FK = "direccionPostal";


    private Long				id;
    private String 				nombres;
    private String 				apellidos;
    private String 				telefono1;
    private String 				telefono2;
    private String 				email;
    private DireccionPostal		direccionPostal;

    /**
     * Constructor sin parametros
     */
    protected ContactoDireccion() {

    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono1
     * @param telefono2
     * @param email
     */

    public ContactoDireccion (String nombres, String apellidos, String telefono1,
    		String telefono2, String email) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ContactoDireccion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ContactoDireccion.ID_SEQUENCE_NAME)
    @Column(name = ContactoDireccion.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombres
	 */
	@Column(name = ContactoDireccion.COLUMN_NAME_NOMBRES, nullable = false, length = 80)
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the apellidos
	 */
	@Column(name = ContactoDireccion.COLUMN_NAME_APELLIDOS, nullable = false, length = 80)
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the telefono1
	 */
	@Column(name = ContactoDireccion.COLUMN_NAME_TELEFONO1, nullable = false, length = 20)
	public String getTelefono1() {
		return telefono1;
	}

	/**
	 * @param telefono1 the telefono1 to set
	 */

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	/**
	 * @return the telefono2
	 */
	@Column(name = ContactoDireccion.COLUMN_NAME_TELEFONO2, length = 20)
	public String getTelefono2() {
		return telefono2;
	}

	/**
	 * @param telefono2 the telefono2 to set
	 */
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	/**
	 * @return the email
	 */
	@Column(name = ContactoDireccion.COLUMN_NAME_EMAIL, length = 80)
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the direccionPostal
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.DireccionPostal.class)
    @JoinColumn(name = ContactoDireccion.COLUMN_NAME_DIRECCION_POSTAL_FK)
	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

	/**
	 * @param direccionPostal the direccionPostal to set
	 */
	public void setDireccionPostal(DireccionPostal direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
