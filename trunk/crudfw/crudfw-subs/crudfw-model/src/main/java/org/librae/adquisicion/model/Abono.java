package org.librae.adquisicion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bean para almacenar un TipoOperacion:Abono
 * 
 * @author jcdiaz
 */
@Entity(name = Abono.ENTITY_NAME)
@Table(name = Abono.TABLE_NAME)
abstract class Abono extends TipoOperacion {

    /**
     * BaseObject es Serializable, por lo tanto Abono necesita un Serial Version
     * UID
     */
    private static final long   serialVersionUID  = 6596258231008773961L;

    public static final String  ENTITY_NAME       = "org.librae.adquisicion.model.Abono";
    public static final String  TABLE_NAME        = "ADQ_ABONOS";
    private static final String ID_GENERATOR_NAME = "generator_adq_abonos";
    private static final String ID_SEQUENCE_NAME  = "SEQ_ADQ_ABONOS";
    public static final String  COLUMN_NAME_ID    = "X_ABONO";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Abono.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Abono.ID_SEQUENCE_NAME)
    @Column(name = Abono.COLUMN_NAME_ID)
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