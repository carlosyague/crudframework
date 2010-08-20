package org.librae.adminconfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Catálogos vigentes en la aplicación. Cada fila representa un catálogo. Hay
 * varios catálogos por defecto, y además de ellos el usuario puede añadir los
 * que desee:<br>
 * + CATA - Contiene las descripciones bibliográficas del fonde de la biblioteca<br>
 * + ADQT - Contiene los registros bibliográficos correspondiente s a ejemplares
 * pedidos en adquisiciones<br>
 * + CANC - Contiene registros que no están activos dentro de la aplicación<br>
 * + ...
 * 
 * @author asantamaria
 */
@Entity(name = Catalogo.ENTITY_NAME)
@Table(name = Catalogo.TABLE_NAME)
public class Catalogo extends BaseObject {

    /**
     * BaseObject is Serializable, so Catalogo needs a Serial Version UID
     */
    private static final long   serialVersionUID          = 5590562617906529482L;

    public static final String  ENTITY_NAME               = "org.librae.adminconfig.model.Catalogo";
    public static final String  TABLE_NAME                = "ADM_CATALOGO";
    private static final String ID_GENERATOR_NAME         = "generator_adm_catalogo";
    private static final String ID_SEQUENCE_NAME          = "SEQ_ADM_CATALOGO";
    public static final String  COLUMN_NAME_ID            = "X_CATALOGO";
    public static final String  COLUMN_NAME_CODIGO        = "C_CATALOGO";
    public static final String  COLUMN_NAME_NOMBRE        = "T_CATALOGO";
    public static final String  COLUMN_NAME_DESC_ALT      = "T_CATALOGO_ALT";

    public static final String  PROPERTY_NAME_ID          = "id";
    public static final String  PROPERTY_NAME_CODIGO      = "codigo";
    public static final String  PROPERTY_NAME_NOMBRE      = "nombre";
    public static final String  PROPERTY_NAME_DESCRIPCION = "descripcion";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila.
     */
    private Long                id;

    /**
     * Código identificativo del catálogo introducido por el usuario.<br>
     * Valores únicos
     */
    private String              codigo;

    /**
     * Descripción del catálogo introducida por el usuario
     */
    private String              nombre;

    /**
     * Descripción alternativa o ampliada del catálogo, introducida por el
     * usuario
     */
    private String              descripcion;

    protected Catalogo() {
        super();
    }

    public Catalogo(String codigo, String nombre) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Catalogo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Catalogo.ID_GENERATOR_NAME, sequenceName = Catalogo.ID_SEQUENCE_NAME)
    @Column(name = Catalogo.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @return the codigo
     */
    @Column(name = Catalogo.COLUMN_NAME_CODIGO, length = 40)
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
    @Column(name = Catalogo.COLUMN_NAME_NOMBRE, length = 80)
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
     * @return the descripcion
     */
    @Column(name = Catalogo.COLUMN_NAME_DESC_ALT, length = 120)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Catalogo)) {
            return false;
        }

        final Catalogo other = (Catalogo) obj;

        if (!getCodigo().equals(other.getCodigo())) {
            return false;
        }

        return true;

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime * result
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Catalogo.COLUMN_NAME_ID,
                getId()).append(Catalogo.COLUMN_NAME_CODIGO, getCodigo())
                .append(Catalogo.COLUMN_NAME_NOMBRE, getNombre()).toString();
    }

}