package org.librae.adminconfig.model;

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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.BaseObject;

/**
 * Tabla para almacenar la gerarquía de menús.
 *
 * @author asantamaria
 */
@Entity(name = Menu.ENTITY_NAME)
@Table(name = Menu.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Menu extends BaseObject {

    /**
     * BaseObject is Serializable, so Menu needs a Serial Version UID
     */
    private static final long   serialVersionUID      = 6349409141947370148L;

    public static final String  ENTITY_NAME           = "org.librae.adminconfig.model.Menu";
    public static final String  TABLE_NAME            = "ADM_MENUS";
    public static final String  ID_GENERATOR_NAME     = "generator_adm_menus";
    private static final String ID_SEQUENCE_NAME      = "SEQ_ADM_MENUS";
    public static final String  COLUMN_NAME_ID        = "X_MENU";
    public static final String  COLUMN_NAME_TEXTO     = "T_MENU";
    public static final String  COLUMN_NAME_ALT       = "T_MENU_ALT";
    public static final String  COLUMN_NAME_URL       = "T_URL";
    public static final String  COLUMN_NAME_ORDEN     = "N_ORDEN";
    public static final String  COLUMN_NAME_PADRE     = "MENU_X_MENU";
    public static final String  COLUMN_NAME_PERMISO   = "PER_X_PERMISO";

    public static final String  PROPERTY_NAME_ID      = "id";
    public static final String  PROPERTY_NAME_TEXTO   = "texto";
    public static final String  PROPERTY_NAME_ALT     = "alt";
    public static final String  PROPERTY_NAME_URL     = "url";
    public static final String  PROPERTY_NAME_ORDEN   = "orden";
    public static final String  PROPERTY_NAME_PADRE   = "padre";
    public static final String  PROPERTY_NAME_HIJOS   = "hijos";
    public static final String  PROPERTY_NAME_PERMISO = "permiso";

    /**
     * Código interno del elemento de menú
     */
    private Long                id;

    /**
     * Texto del elemento de menú.
     */
    private String              texto;

    /**
     * Texto alternativo del elemento de menú
     */
    private String              alt;

    /**
     * Dirección URL relativa de la pantalla a la que se llega mediante el
     * elemento de menú
     */
    private String              url;

    /**
     * Orden en que el elemento de menú aparecerá en su nivel. En caso de
     * coincidencia se aplicará el orden alfabético ascendente por T_MENU
     */
    private Long                orden;

    /**
     * Identificador del elemento de menú padre.
     */
    private Menu                padre;

    /**
     * Identificadores de los hijos.
     */
    private List<Menu>          hijos;

    /**
     * Permiso necesario para ver este elemento del menú
     */
    private Permiso             permiso;

    protected Menu() {
        super();
    }

    public Menu(final String texto, final String url, final Long orden) {
        super();
        this.texto = texto;
        this.url = url;
        this.orden = orden;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Menu.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @return the texto
     */
    @Column(name = Menu.COLUMN_NAME_TEXTO,length=40)
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto
     *            the texto to set
     */
    public void setTexto(final String texto) {
        this.texto = texto;
    }

    /**
     * @return the alt
     */
    @Column(name = Menu.COLUMN_NAME_ALT,length=80)
    public String getAlt() {
        return alt;
    }

    /**
     * @param alt
     *            the alt to set
     */
    public void setAlt(final String alt) {
        this.alt = alt;
    }

    /**
     * @return the url
     */
    @Column(name = Menu.COLUMN_NAME_URL)
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * @return the orden
     */
    @Column(name = Menu.COLUMN_NAME_ORDEN)
    public Long getOrden() {
        return orden;
    }

    /**
     * @param orden
     *            the orden to set
     */
    public void setOrden(final Long orden) {
        this.orden = orden;
    }

    /**
     * @return the padre
     */
    @ManyToOne(targetEntity = Menu.class, cascade = { CascadeType.PERSIST,
            CascadeType.ALL })
    @JoinColumn(name = Menu.COLUMN_NAME_PADRE, nullable = true)
    public Menu getPadre() {
        return padre;
    }

    /**
     * @param padre
     *            the padre to set
     */
    public void setPadre(final Menu padre) {
        this.padre = padre;
    }

    /**
     * @return the hijos
     */
    @OneToMany(targetEntity = Menu.class, mappedBy = Menu.PROPERTY_NAME_PADRE)
    @OrderBy("orden")
    public List<Menu> getHijos() {
        return hijos;
    }

    /**
     * @param hijos
     *            the hijos to set
     */
    public void setHijos(final List<Menu> hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the permiso
     */
    @ManyToOne(targetEntity = Permiso.class, cascade = { CascadeType.PERSIST,
            CascadeType.ALL })
    @JoinColumn(name = Menu.COLUMN_NAME_PERMISO, nullable = true)
    public Permiso getPermiso() {
        return permiso;
    }

    /**
     * @param permiso
     *            the permiso to set
     */
    public void setPermiso(final Permiso permiso) {
        this.permiso = permiso;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Menu)) {
            return false;
        }

        final Menu other = (Menu) obj;

        if (getTexto() == null && other.getTexto() != null) {
            return false;
        }
        if (getTexto() != null && !getTexto().equals(other.getTexto())) {
            return false;
        }

        if (getUrl() == null && other.getUrl() != null) {
            return false;
        }
        if (getUrl() != null && !getUrl().equals(other.getUrl())) {
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
                + ((getTexto() == null) ? 0 : getTexto().hashCode());

        result = prime * result
                + ((getUrl() == null) ? 0 : getUrl().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(COLUMN_NAME_ID, getId())

        .append(COLUMN_NAME_TEXTO,
                (getTexto() == null) ? "" : getTexto().toString())

        .append(COLUMN_NAME_URL, (getUrl() == null) ? "" : getUrl().toString())

        .toString();
    }

}