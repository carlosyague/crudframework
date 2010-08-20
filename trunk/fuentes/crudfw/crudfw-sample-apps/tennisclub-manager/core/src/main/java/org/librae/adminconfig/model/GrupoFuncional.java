/**
 *
 */
package org.librae.adminconfig.model;

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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * @author amDelcampo
 */
@Entity(name = GrupoFuncional.ENTITY_NAME)
@Table(name = GrupoFuncional.TABLE_NAME)
public class GrupoFuncional extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID        = -2481417075503016036L;

    public static final String  ENTITY_NAME             = "org.librae.adminconfig.model.GrupoFuncional";
    public static final String  TABLE_NAME              = "ADM_GRUPOS_FUNCIONALES";

    private static final String ID_GENERATOR_NAME       = "generator_adm_grupos_funcional";
    private static final String ID_SEQUENCE_NAME        = "SEQ_ADM_GRUPO_FUNCIONAL";

    public static final String  COLUMN_NAME_ID          = "X_GRUPOS_FUNCIONALES";
    public static final String  COLUMN_NAME_NOMBRE      = "T_NOMBRE";
    public static final String  COLUMN_NAME_MODULO_FK   = "MOD_X_MODULO";

    public static final String  PROPERTY_NAME_ID        = "id";
    public static final String  PROPERTY_NAME_NOMBRE    = "nombre";
    public static final String  PROPERTY_NAME_MODULO_FK = "modulo";

    /**
     * Clave identificativa del grupo funcional.
     */
    private Long                id;

    /**
     * Nombre del grupo funcional.
     */
    private String              nombre;

    /**
     * Referencia al módulo al que pertenece el grupo funcional representado.
     */
    private Modulo              modulo;

    /**
     * Constructor sin parámetros
     */
    public GrupoFuncional() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = GrupoFuncional.ID_GENERATOR_NAME)
    @SequenceGenerator(name = GrupoFuncional.ID_GENERATOR_NAME, sequenceName = GrupoFuncional.ID_SEQUENCE_NAME)
    @Column(name = GrupoFuncional.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = GrupoFuncional.COLUMN_NAME_NOMBRE)
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
     * @return the modulo
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Modulo.class)
    @JoinColumn(name = GrupoFuncional.COLUMN_NAME_MODULO_FK, nullable = false)
    public Modulo getModulo() {
        return modulo;
    }

    /**
     * @param modulo
     *            the modulo to set
     */
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
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
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }
        // if they are classify in different classes
        if (!(obj instanceof GrupoFuncional)) {
            return false;
        }

        final GrupoFuncional other = (GrupoFuncional) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime * result
                + ((getNombre() == null) ? 0 : getNombre().hashCode());
        result = prime * result
                + ((getModulo() == null) ? 0 : getModulo().hashCode());

        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(GrupoFuncional.COLUMN_NAME_ID,
                getId()).append(GrupoFuncional.COLUMN_NAME_NOMBRE, getNombre())
                .toString();
    }

}
