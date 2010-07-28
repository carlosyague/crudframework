package org.librae.catalogacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.parampoliticas.AbstractParamPolEjemplarTipo;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * Bean para almacenar una EjemplarTipo
 *
 * @author jcdiaz
 */
@Entity(name = EjemplarTipo.ENTITY_NAME)
@Table(name = EjemplarTipo.TABLE_NAME)
public class EjemplarTipo extends AbstractParamPolEjemplarTipo {

    /**
     * BaseObject es Serializable, por lo tanto EjemplarTipo necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID   = 3603488453564779662L;

    public static final String  ENTITY_NAME        = "org.librae.catalogacion.model.EjemplarTipo";

    /**
     * Constantes para referencia de los atributos de EjemplarTipo
     */
    public static final String  PROPTY_NAME_ID     = "id";
    public static final String  PROPTY_NAME_CODIGO = "codigo";
    public static final String  PROPTY_NAME_TIPO   = "tipo";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME           = "CAT_EJEMPLAR_TIPO";
    private static final String ID_GENERATOR_NAME    = "generator_cat_ejemplar_tipo";
    private static final String ID_SEQUENCE_NAME     = "SEQ_CAT_EJEMPLAR_TIPO";
    public static final String   PROPTY_NAME_BORRADO = "borrado";

    public static final String  COLUMN_NAME_ID       = "X_EJEMPLAR_TIPO";
    public static final String  COLUMN_NAME_CODIGO   = "C_CODIGO";
    public static final String  COLUMN_NAME_TIPO     = "T_TIPO";
    public static final String  COLUMN_NAME_BORRADO  = "L_BORRADO";
    // FIXME Falta FK con Biblioteca

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * �nica cada fila. N�mero secuencial
     */
    private Long                id;

    /**
     * C�digo �nico (dentro de una misma biblioteca) del tipo de ejemplares,
     * asignado por el usuario.
     */
    private String              codigo;

    /**
     * Descripci�n o nombre del tipo de ejemplares
     */
    private String              tipo;

    /**
     * True si el tipo de ejemplar esta borrado.
     * False si el tipo de ejemplar es válido.
     */
    private Boolean               borrado;
    
    /**
     * Constructor con parámetros
     */
    protected EjemplarTipo() {
        super();
    }

    /**
     * Constructor sin parámetros
     *
     * @param codigo
     * @param tipo
     */
    public EjemplarTipo(final String codigo, final String tipo) {
        super();
        this.codigo = codigo;
        this.tipo = tipo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = EjemplarTipo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = EjemplarTipo.ID_GENERATOR_NAME, sequenceName = EjemplarTipo.ID_SEQUENCE_NAME)
    @Column(name = EjemplarTipo.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = EjemplarTipo.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 20)
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
     * @return the tipo
     */
    @Column(name = EjemplarTipo.COLUMN_NAME_TIPO, nullable = false, length = 80)
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     *            the tipo to set
     */
    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    @Column(name = EjemplarTipo.COLUMN_NAME_BORRADO)
    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
    
    /**
     * Traduce el campo descripcionTipoLector.
     *
     * @return
     */
    @Transient
    public String getTipoLocale() {
        final String s = ComboLocaleManager.get(tipo.replace(
                "#", ""));
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
        result = prime * result
                + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((tipo == null) ? 0 : tipo.hashCode());
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
        if (!(obj instanceof EjemplarTipo)) {
            return false;
        }
        final EjemplarTipo other = (EjemplarTipo) obj;
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
        if (tipo == null) {
            if (other.tipo != null) {
                return false;
            }
        } else if (!tipo.equals(other.tipo)) {
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

        .append(EjemplarTipo.PROPTY_NAME_ID, id)

        .append(EjemplarTipo.PROPTY_NAME_CODIGO,
                (codigo == null) ? 0 : codigo)

        .append(EjemplarTipo.PROPTY_NAME_TIPO,
                (tipo == null) ? 0 : tipo)

        .toString();
    }

    @Override
    public AbstractParamPolEjemplarTipo newInstance() {
        return new EjemplarTipo();
    }
}
