package org.librae.adquisicion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bean para almacenar un TipoOperacion:Cargo
 * 
 * @author jcdiaz
 */
@Entity(name = Cargo.ENTITY_NAME)
@Table(name = Cargo.TABLE_NAME)
abstract class Cargo extends TipoOperacion {

    /**
     * BaseObject es Serializable, por lo tanto Cargo necesita un Serial Version
     * UID
     */
    private static final long   serialVersionUID  = 4795027923982976400L;

    public static final String  ENTITY_NAME       = "org.librae.adquisicion.model.Cargo";
    public static final String  TABLE_NAME        = "ADQ_CARGOS";
    private static final String ID_GENERATOR_NAME = "generator_adq_cargos";
    private static final String ID_SEQUENCE_NAME  = "SEQ_ADQ_CARGOS";
    public static final String  COLUMN_NAME_ID    = "X_CARGO";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Cargo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Cargo.ID_SEQUENCE_NAME)
    @Column(name = Cargo.COLUMN_NAME_ID)
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