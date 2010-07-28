package org.librae.circulacion.model.pib;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.adminconfig.model.Direccion;
import org.librae.common.model.BaseObject;

/**
 * Bibliotecas externas (no representan bibliotecas de despliegue de la
 * aplicación) que se usan en operaciones de PIB
 *
 * @author asantamaria
 */
@Entity(name = BibliotecaExterna.ENTITY_NAME)
@Table(name = BibliotecaExterna.TABLE_NAME)
public class BibliotecaExterna extends BaseObject {

    /**
     * BaseObject is Serializable, so BibliotecaExterna needs a Serial Version
     * UID
     */
    private static final long   serialVersionUID                      = 3202204762084676880L;

    public static final String  ENTITY_NAME                           = "org.librae.circulacion.model.BibliotecaExterna";
    public static final String  TABLE_NAME                            = "CIR_PIB_BIBLIOTECAS_EXTERNAS";
    public static final String  ID_GENERATOR_NAME                     = "generator_cir_pib_bibliotecas_externas";
    private static final String ID_SEQUENCE_NAME                      = "SEQ_CIR_PIB_BIB_EXTERNAS";
    public static final String  COLUMN_NAME_ID                        = "X_PIB_BIBLIOTECA_EXTERNA";
    public static final String  COLUMN_NAME_CODIGO                    = "C_PIB_BIBLIOTECA_EXTERNA";
    public static final String  COLUMN_NAME_NOMBRE                    = "T_PIB_BIBLIOTECA_EXTERNA";
    public static final String  COLUMN_NAME_DESCRIPCION_ALTERNATIVA   = "T_PIB_BIBLIOTECA_EXTERNA_ALT";
    public static final String  COLUMN_NAME_DIRECCION                 = "DIR_X_DIRECCION";
    public static final String  COLUMN_NAME_CONTACTOS_PIB             = "CON_X_CONTACTOS_PIB";

    public static final String  PROPERTY_NAME_ID                      = "id";
    public static final String  PROPERTY_NAME_CODIGO                  = "codigo";
    public static final String  PROPERTY_NAME_NOMBRE                  = "nombre";
    public static final String  PROPERTY_NAME_DESCRIPCION_ALTERNATIVA = "descripcionAlternativa";
    public static final String  PROPERTY_NAME_DIRECCION               = "direccion";
    public static final String  PROPERTY_NAME_CONTACTOS_PIB           = "contactos";

    /**
     * Identificador interno del parámetro
     */
    private Long                id;

    /**
     * Idenificación que el usuario asigna a la biblioteca
     */
    private String              codigo;

    /**
     * Nombre de la biblioteca
     */
    private String              nombre;

    /**
     * Descripción extendida o alternativa de la biblioteca
     */
    private String              descripcionAlternativa;

    /**
     * >> DIRECCIONES<br>
     * Dirección de la biblioteca
     */
    private Direccion           direccion;

    /**
     * Listado de contactos
     */
    private List<ContactoPIB>   contactos;

    protected BibliotecaExterna() {
        super();
    }

    public BibliotecaExterna(String codigo, String nombre) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = BibliotecaExterna.COLUMN_NAME_ID)
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
     * @return the codigo
     */
    @Column(name = BibliotecaExterna.COLUMN_NAME_CODIGO,length=40)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    @Column(name = BibliotecaExterna.COLUMN_NAME_NOMBRE,length=80)
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
     * @return the descripcionAlternativa
     */
    @Column(name = BibliotecaExterna.COLUMN_NAME_DESCRIPCION_ALTERNATIVA)
    public String getDescripcionAlternativa() {
        return descripcionAlternativa;
    }

    /**
     * @param descripcionAlternativa
     *            the descripcionAlternativa to set
     */
    public void setDescripcionAlternativa(String descripcionAlternativa) {
        this.descripcionAlternativa = descripcionAlternativa;
    }

    /**
     * @return the direccion
     */
    @ManyToOne(targetEntity = Direccion.class, cascade = { CascadeType.PERSIST,
            CascadeType.ALL })
    @JoinColumn(name = BibliotecaExterna.COLUMN_NAME_DIRECCION)
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @param direccion
     *            the direccion to set
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the contactos
     */
    @OneToMany(targetEntity = ContactoPIB.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = BibliotecaExterna.COLUMN_NAME_CONTACTOS_PIB)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<ContactoPIB> getContactos() {
        return contactos;
    }

    /**
     * @param contactos
     *            the contactos to set
     */
    public void setContactos(List<ContactoPIB> contactos) {
        this.contactos = contactos;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof BibliotecaExterna)) {
            return false;
        }

        final BibliotecaExterna other = (BibliotecaExterna) obj;

        if (getNombre() == null && other.getNombre() != null) {
            return false;
        }
        if (getNombre() != null && !getNombre().equals(other.getNombre())) {
            return false;
        }

        if (getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (getCodigo() != null && !getCodigo().equals(other.getCodigo())) {
            return false;
        }

        return true;

    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime * result
                + ((getNombre() == null) ? 0 : getNombre().hashCode());

        result = prime * result
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, getId())

        .append(PROPERTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(PROPERTY_NAME_NOMBRE,
                (getNombre() == null) ? "" : getNombre().toString())

        .append(
                PROPERTY_NAME_DESCRIPCION_ALTERNATIVA,
                (getDescripcionAlternativa() == null) ? ""
                        : getDescripcionAlternativa().toString())

        .toString();
    }

}