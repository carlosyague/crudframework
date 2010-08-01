package org.librae.adquisicion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bean para almacenar un Adquisicion
 * 
 * @author jcdiaz
 */
@Entity(name = Adquisicion.ENTITY_NAME)
@Table(name = Adquisicion.TABLE_NAME)
abstract class Adquisicion extends TipoOperacion {

    /**
     * BaseObject es Serializable, por lo tanto Adquisicion necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID  = -2876062061882184160L;

    public static final String  ENTITY_NAME       = "org.librae.adquisicion.model.Adquisicion";
    public static final String  TABLE_NAME        = "ADQ_ADQUISICIONES";
    private static final String ID_GENERATOR_NAME = "generator_adq_adquisiciones";
    private static final String ID_SEQUENCE_NAME  = "SEQ_ADQ_ADQUISICIONES";
    public static final String  COLUMN_NAME_ID    = "X_ADQUISICION";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Adquisicion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Adquisicion.ID_SEQUENCE_NAME)
    @Column(name = Adquisicion.COLUMN_NAME_ID)
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

}