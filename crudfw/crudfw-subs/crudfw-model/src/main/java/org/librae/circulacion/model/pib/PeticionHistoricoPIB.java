package org.librae.circulacion.model.pib;

import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.librae.lectores.model.Lector;

/**
 * Histórico de peticiones<br>
 * Mismas columnas que CIR_PIB_PETICIONES<br>
 * Una fila de CIR_PIB_PETICIONES se mueve a esta tabla cuando se termina el
 * workflow de la petición.<br>
 * 
 * @author cyague
 */
// @Entity(name = PeticionHistoricoPIB.ENTITY_NAME)
// @Table(name = PeticionHistoricoPIB.TABLE_NAME)
public class PeticionHistoricoPIB extends AbstractPeticionPIB {

    /**
     * BaseObject is Serializable, so PeticionPIB needs a Serial Version UID
     */
    private static final long   serialVersionUID  = -7939948998053510695L;

    public static final String  ENTITY_NAME       = "org.librae.circulacion.model.PeticionHistoricoPIB";
    public static final String  TABLE_NAME        = "CIR_PIB_PETICIONES_HIST";

    private static final String ID_GENERATOR_NAME = "generator_cir_pib_peticiones_hist";
    private static final String ID_SEQUENCE_NAME  = "SEQ_CIR_PIB_PETICIONES_HIST";

    /**
     * Clave primaria artificial.<br>
     * Identificador único de petición para el bibliotecario y el lector
     */
    protected Long              id;

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * constructors<br>
     * ============
     */

    protected PeticionHistoricoPIB() {
        super();
    }

    public PeticionHistoricoPIB(Lector lector) {
        super();
        this.lector = lector;
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

    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof PeticionHistoricoPIB)) {
            return false;
        }

        final PeticionHistoricoPIB other = (PeticionHistoricoPIB) obj;

        return super.equals(other);
    }

}