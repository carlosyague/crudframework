/**
 *
 */
package org.librae.adminconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * @author jVillegas
 */
@Entity(name = Modulo.NAME_ENTITY)
@Table(name = Modulo.TABLE_NAME)
public class Modulo extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID     = 1L;

    public static final String  NAME_ENTITY          = "org.librae.adminconfig.model.Modulo";
    public static final String  TABLE_NAME           = "ADM_MODULOS";
    private static final String ID_GENERATOR_NAME    = "generator_adm_modulos";
    private static final String ID_SEQUENCE_NAME     = "SEQ_ADM_MODULOS";
    public static final String  COLUMN_NAME_ID       = "X_MODULOS";
    public static final String  COLUMN_NAME_NOMBRE   = "T_NOMBRE";

    public static final String  PROPERTY_NAME_ID     = "id";
    public static final String  PROPERTY_NAME_NOMBRE = "nombre";

    /**
     * Clave primaria
     */
    private Long                id;
    /**
     * Nombre identificativo del módulo del aplicativo.
     */
    private String              nombre;

    /**
     * Constructor sin parametros
     */
    protected Modulo() {
        super();
    }

    /*
     * Constructor con parametros
     */
    public Modulo(String nombreFinal) {
        super();
        nombre = nombreFinal;

    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Modulo.COLUMN_NAME_ID)
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
     * @return the nombre
     */
    @Column(name = Modulo.COLUMN_NAME_NOMBRE)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Traduce el campo nombre.
     * 
     * @return
     */
    @Transient
    public String getNombreLocale() {
        return ComboLocaleManager.getOptional(nombre);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#hashCode()
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return new ToStringBuilder(this).append(Modulo.COLUMN_NAME_ID, id)
                .append(Modulo.COLUMN_NAME_NOMBRE, nombre).toString();
    }

}
