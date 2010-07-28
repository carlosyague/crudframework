package org.librae.circulacion.model.pib;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.circulacion.model.RestriccionUsoEjemplar;
import org.librae.common.model.BaseObject;

/**
 * Tabla que relaciona una petición con varias restricciones de uso aplicables
 * al material prestado (tablas CIR_PIB_PETICIONES y CIR_RESTRICCIONES_USO)
 * 
 * @author cyague
 */
@Entity(name = RestriccionUsoPIB.ENTITY_NAME)
@Table(name = RestriccionUsoPIB.TABLE_NAME)
public class RestriccionUsoPIB extends BaseObject {

    /**
     * BaseObject is Serializable
     */
    private static final long      serialVersionUID              = -7112376431926695029L;

    public static final String     ENTITY_NAME                   = "org.librae.circulacion.model.RestriccionUsoPIB";
    public static final String     TABLE_NAME                    = "CIR_PIB_RESTRICCIONES_USO";

    public static final String     ID_GENERATOR_NAME             = "generator_cir_pib_res_uso";
    private static final String    ID_SEQUENCE_NAME              = "SEQ_CIR_PIB_RES_USO";

    public static final String     COLUMN_NAME_ID                = "X_PIB_RESTRICCION_USO";
    public static final String     COLUMN_NAME_PETICION          = "PET_X_PIB_PETICION";
    public static final String     COLUMN_NAME_RESTRICCION_USO   = "RES_USO_X_RESTRICCION_USO";

    public static final String     PROPERTY_NAME_ID              = "id";
    public static final String     PROPERTY_NAME_PETICION        = "peticion";
    public static final String     PROPERTY_NAME_RESTRICCION_USO = "restriccionUso";

    /**
     * Clave primaria artificial
     */
    private Long                   id;

    /**
     * >> CIR_PIB_PETICIONES
     */
    private PeticionPIB            peticion;

    /**
     * >> CIR_RESTRICCIONES_USO
     */
    private RestriccionUsoEjemplar restriccionUso;

    /**
     * constructors<br>
     * ============
     */

    protected RestriccionUsoPIB() {
        super();
    }

    public RestriccionUsoPIB(RestriccionUsoEjemplar restriccionUso) {
        super();
        this.restriccionUso = restriccionUso;
    }

    /**
     * getter & setters<br>
     * ================
     */

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the peticion
     */
    @ManyToOne(targetEntity = PeticionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = COLUMN_NAME_PETICION)
    public PeticionPIB getPeticion() {
        return peticion;
    }

    /**
     * @param peticion
     *            the peticion to set
     */
    public void setPeticion(PeticionPIB peticion) {
        this.peticion = peticion;
    }

    /**
     * @return the restriccionUso
     */
    @ManyToOne(targetEntity = RestriccionUsoEjemplar.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = COLUMN_NAME_RESTRICCION_USO)
    public RestriccionUsoEjemplar getRestriccionUso() {
        return restriccionUso;
    }

    /**
     * @param restriccionUso
     *            the restriccionUso to set
     */
    public void setRestriccionUso(RestriccionUsoEjemplar restriccionUso) {
        this.restriccionUso = restriccionUso;
    }

    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof RestriccionUsoPIB)) {
            return false;
        }

        final RestriccionUsoPIB other = (RestriccionUsoPIB) obj;

        if (this.getPeticion() == null && other.getPeticion() != null) {
            return false;
        }

        if (this.getPeticion() != null
                && !this.getPeticion().equals(other.getPeticion())) {
            return false;
        }

        if (this.getRestriccionUso() == null
                && other.getRestriccionUso() != null) {
            return false;
        }

        if (this.getRestriccionUso() != null
                && !this.getRestriccionUso().equals(other.getRestriccionUso())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;

        result = prime * result
                + ((this.getId() == null) ? 0 : this.getId().hashCode());

        result = prime
                * result
                + ((this.getPeticion() == null) ? 0 : this.getPeticion()
                        .hashCode());

        result = prime
                * result
                + ((this.getRestriccionUso() == null) ? 0 : this
                        .getRestriccionUso().hashCode());

        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, this.id)

        .append(
                PROPERTY_NAME_PETICION,
                (this.getPeticion() == null) ? "" : this.getPeticion()
                        .toString())

        .append(
                PROPERTY_NAME_RESTRICCION_USO,
                (this.getRestriccionUso() == null) ? "" : this
                        .getRestriccionUso().toString())

        .toString();
    }

}