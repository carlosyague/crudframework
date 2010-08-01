package org.librae.catalogacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.librae.common.model.BaseObject;

/**
 * Bean para almacena la información entre un Registro bibliográfico y un
 * Ejemplar para poder diferenciar títulos primarios y secundarios.
 *
 * @author jcdiaz
 */
@Entity(name = RegistroEjemplar.ENTITY_NAME)
@Table(name = RegistroEjemplar.TABLE_NAME)
public class RegistroEjemplar extends BaseObject {

    /**
     * SERIAL VERSION UID
     */
    private static final long   serialVersionUID        = 8607962184117270987L;

    public static final String  ENTITY_NAME             = "org.librae.catalogacion.model.RegistroEjemplar";

    /**
     * Constantes para referencia de los atributos de RegistroEjemplar
     */
    public static final String  PROPTY_NAME_ID          = "id";
    public static final String  PROPTY_NAME_REGISTRO    = "registro";
    public static final String  PROPTY_NAME_EJEMPLAR    = "ejemplar";
    public static final String  PROPTY_NAME_PRINCIPAL   = "principal";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME              = "CAT_REGISTRO_X_CAT_EJEMPLAR";
    private static final String ID_GENERATOR_NAME       = "generator_cat_registro_x_cat_ejemplar";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CAT_REGISTRO_X_CAT_EJEMPL";

    public static final String  COLUMN_NAME_ID          = "X_REGISTRO_EJEMPLAR";
    public static final String  COLUMN_NAME_PRINCIPAL   = "L_PRINCIPAL";

    public static final String  COLUMN_NAME_REGISTRO_FK = "REG_X_REGISTRO";
    public static final String  COLUMN_NAME_EJEMPLAR_FK = "EJE_X_EJEMPLAR";

    /**
     * Clave autonumérica secuencial sin significado funcional.
     */
    private Long                id;

    /**
     * Booleano que indica si es título principal de un ejemplar (true) o es
     * título secundario (false).
     */
    private Boolean             principal;

    /**
     * Clave foranea al registro con el que está asociado
     */
    private Registro            registro;

    /**
     * Clave foranea al ejemplar con el que está asociado
     */
    private Ejemplar            ejemplar;


    private String strBiblioteca;
    /**
     * Constructor sin parámetros
     */
    protected RegistroEjemplar() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param principal
     * @param registro
     */
    public RegistroEjemplar(final Boolean principal, final Registro registro,
            final Ejemplar ejemplar) {
        super();
        this.principal = principal;
        this.registro = registro;
        this.ejemplar = ejemplar;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = RegistroEjemplar.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = RegistroEjemplar.ID_SEQUENCE_NAME)
    @Column(name = RegistroEjemplar.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the principal
     */
    @Column(name = RegistroEjemplar.COLUMN_NAME_PRINCIPAL)
    public Boolean getPrincipal() {
        return principal;
    }

    /**
     * @param principal
     *            the principal to set
     */
    public void setPrincipal(final Boolean principal) {
        this.principal = principal;
    }

    /**
     * @return the registro
     */
    @ManyToOne(targetEntity = org.librae.catalogacion.model.Registro.class, fetch = FetchType.LAZY)
    @JoinColumn(name = RegistroEjemplar.COLUMN_NAME_REGISTRO_FK)
    public Registro getRegistro() {
        return registro;
    }

    /**
     * @param registro
     *            the registro to set
     */
    public void setRegistro(final Registro registro) {
        this.registro = registro;
    }

    /**
     * @return the ejemplar
     */
    @ManyToOne(targetEntity = org.librae.catalogacion.model.Ejemplar.class, fetch = FetchType.LAZY)
    @JoinColumn(name = RegistroEjemplar.COLUMN_NAME_EJEMPLAR_FK)
    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    /**
     * @param ejemplar
     *            the ejemplar to set
     */
    public void setEjemplar(final Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }



    @Transient
    public String getStrBiblioteca() {
		return strBiblioteca;
	}

	public void setStrBiblioteca(String strBiblioteca) {
		this.strBiblioteca = strBiblioteca;
	}


    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((principal == null) ? 0 : principal.hashCode());
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
        if (!(obj instanceof RegistroEjemplar)) {
            return false;
        }
        final RegistroEjemplar other = (RegistroEjemplar) obj;
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
        return true;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}