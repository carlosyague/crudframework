package org.librae.catalogacion.model;

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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ForeignKey;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.model.SpringRemotableLazyEntity;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * Bean para almacenar una EjemplarLocalizacion
 *
 * @author jcdiaz
 */
@Entity(name = EjemplarLocalizacion.ENTITY_NAME)
@Table(name = EjemplarLocalizacion.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class EjemplarLocalizacion extends
        SpringRemotableLazyEntity<EjemplarLocalizacion> {

    /**
     * BaseObject es Serializable, por lo tanto EjemplarLocalizacion necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID          = 1971597989569343080L;

    public static final String  ENTITY_NAME               = "org.librae.catalogacion.model.EjemplarLocalizacion";

    /**
     * Constantes para referencia de los atributos de EjemplarLocalizacion
     */
    public static final String  PROPTY_NAME_ID            = "id";
    public static final String  PROPTY_NAME_CODIGO        = "codigo";
    public static final String  PROPTY_NAME_LOCALIZACION  = "localizacion";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME                = "CAT_EJEMPLAR_LOCALIZACIONES";
    private static final String ID_GENERATOR_NAME         = "generator_cat_ejemplar_localizaciones";
    private static final String ID_SEQUENCE_NAME          = "SEQ_CAT_EJEMPLAR_LOCALIZ";

    public static final String  COLUMN_NAME_ID            = "X_EJEMPLAR_LOCALIZACION";
    public static final String  COLUMN_NAME_CODIGO        = "C_CODIGO";
    public static final String  COLUMN_NAME_LOCALIZACION  = "T_LOCALIZACION";

    public static final String  COLUMN_NAME_BIBLIOTECA_FK = "BIB_X_BIBLIOTECA";

    /**
     * Clave autonumérica secuencial sin significado funcional
     */
    private Long                id;

    /**
     * Código identificativo de localización introducido por el usuario
     */
    private String              codigo;
    /**
     * Descripción de localización, introducida por el usuario
     */
    private String              localizacion;

    /**
     * id de la biblioteca o sucrsal a la que es aplicable la localización
     */
    private Biblioteca          biblioteca;

    /**
     * Constructor sin parámetros
     */
    protected EjemplarLocalizacion() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo
     * @param localizacion
     */
    public EjemplarLocalizacion(String codigo, String localizacion) {
        super();
        this.codigo = codigo;
        this.localizacion = localizacion;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = EjemplarLocalizacion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = EjemplarLocalizacion.ID_SEQUENCE_NAME)
    @Column(name = EjemplarLocalizacion.COLUMN_NAME_ID, nullable = false)
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
     * @return the codigo
     */
    @Column(name = EjemplarLocalizacion.COLUMN_NAME_CODIGO, nullable = false, length = 20)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the localizacion
     */
    @Column(name = EjemplarLocalizacion.COLUMN_NAME_LOCALIZACION, nullable = false, length = 80)
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * @param localizacion
     *            the localizacion to set
     */
    public void setLocalizacion(final String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = COLUMN_NAME_BIBLIOTECA_FK)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_EJEM")
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca
     *            the biblioteca to set
     */
    public void setBiblioteca(final Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    /**
     * Traduce el campo descripcionTipoLector.
     *
     * @return
     */
    @Transient
    public String getLocalizacionLocale() {
        final String s = ComboLocaleManager.get(localizacion.replace("#", ""));
        return (s == null) ? "" : s;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((localizacion == null) ? 0 : localizacion.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EjemplarLocalizacion)) {
            return false;
        }
        final EjemplarLocalizacion other = (EjemplarLocalizacion) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (localizacion == null) {
            if (other.localizacion != null) {
                return false;
            }
        } else if (!localizacion.equals(other.localizacion)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this)

        .append(EjemplarLocalizacion.PROPTY_NAME_ID, id)

        .append(EjemplarLocalizacion.PROPTY_NAME_CODIGO,
                (codigo == null) ? 0 : codigo)

        .append(EjemplarLocalizacion.PROPTY_NAME_LOCALIZACION,
                (localizacion == null) ? 0 : localizacion)

        .toString();
    }

    @Override
    public EjemplarLocalizacion newInstance() {
        return new EjemplarLocalizacion();
    }

}
