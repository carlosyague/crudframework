package org.librae.catalogacion.model;

import java.util.ArrayList;
import java.util.Iterator;
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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar una Autoridad
 * 
 * @author jcdiaz
 */
@Entity(name = Autoridad.ENTITY_NAME)
@Table(name = Autoridad.TABLE_NAME)
public class Autoridad extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Autoridad necesita un Serial
     * Version UID
     */
    private static final long              serialVersionUID              = 6282144797626322298L;

    public static final String             ENTITY_NAME                   = "org.librae.catalogacion.model.Autoridad";

    /**
     * Constantes para referencia de los atributos de Autoridad
     */
    public static final String             PROPTY_NAME_ID                = "id";
    public static final String             PROPTY_NAME_VISIBLE_OPAC      = "visibleOpac";
    public static final String             PROPTY_NAME_CABECERA          = "cabecera";
    public static final String             PROPTY_NAME_AUTORIDAD_TIPO    = "autoridadTipo";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String             TABLE_NAME                    = "CAT_AUTORIDAD";
    private static final String            ID_GENERATOR_NAME             = "generator_cat_autoridad";
    private static final String            ID_SEQUENCE_NAME              = "SEQ_CAT_AUTORIDAD";

    public static final String             COLUMN_NAME_ID                = "X_AUTORIDAD";
    public static final String             COLUMN_NAME_VISIBLE_OPAC      = "L_VISIBLE_OPAC";
    public static final String             COLUMN_NAME_CABECERA          = "T_CABECERA";

    public static final String             COLUMN_NAME_AUTORIDAD_TIPO_FK = "AUT_X_AUTORIDAD_TIPO";

    public static final String             PROPTY_NAME_TIPO              = "tipo";

    public static final String             COLUMN_NAME_TINTIT            = "X_TINTIT";
    /**
     * Clave autonumérica secuencial sin significado funcional
     */
    private Long                           id;

    /**
     * Cabecera de la autoridad
     */
    private String                         cabecera;

    /**
     *
     */
    private AutoridadTipo                  autoridadTipo;

    /**
     *
     */
    private List<AutoridadEtiqueta>        autoridadEtiquetas;

    /**
     *
     */
    private List<AutoridadEtiquetaControl> autoridadEtiquetaControles;

    /**
     * Campo identificativo del registro, necesario para la migracion
     */

    private Long                           tintit;

    /**
     * Combinar autoridades (AÚN NO SE ABORDA EL TEMA)
     */
    // private List<Autoridad> autoridades;
    /**
     * Constructor sin parámetros
     */
    protected Autoridad() {
        super();
        this.autoridadEtiquetas = new ArrayList<AutoridadEtiqueta>();
        this.autoridadEtiquetaControles = new ArrayList<AutoridadEtiquetaControl>();
    }

    /**
     * @param cabecera
     */
    public Autoridad(final String cabecera) {
        super();
        this.cabecera = cabecera;
        this.autoridadEtiquetas = new ArrayList<AutoridadEtiqueta>();
        this.autoridadEtiquetaControles = new ArrayList<AutoridadEtiquetaControl>();
    }

    /**
     * Constructor con parámetros
     * 
     * @param cabecera
     * @param tipo
     */
    public Autoridad(final String cabecera, final AutoridadTipo tipo) {
        super();
        this.cabecera = cabecera;
        this.autoridadEtiquetas = new ArrayList<AutoridadEtiqueta>();
        this.autoridadEtiquetaControles = new ArrayList<AutoridadEtiquetaControl>();
        this.autoridadTipo = tipo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Autoridad.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Autoridad.ID_SEQUENCE_NAME)
    @Column(name = Autoridad.COLUMN_NAME_ID, nullable = false)
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
     * @return the tintit
     */
    @Column(name = Autoridad.COLUMN_NAME_TINTIT)
    public Long getTintit() {
        return tintit;
    }

    /**
     * @param tintit
     *            the tintit to set
     */
    public void setTintit(Long tintit) {
        this.tintit = tintit;
    }

    /**
     * @return the cabecera
     */
    @Column(name = Autoridad.COLUMN_NAME_CABECERA, nullable = false, length = 255)
    public String getCabecera() {
        return cabecera;
    }

    /**
     * @param cabecera
     *            the cabecera to set
     */
    public void setCabecera(final String cabecera) {
        this.cabecera = cabecera;
    }

    /**
     * @return the autoridadTipo
     */
    @ManyToOne(cascade = { CascadeType.PERSIST }, targetEntity = AutoridadTipo.class)
    @JoinColumn(name = Autoridad.COLUMN_NAME_AUTORIDAD_TIPO_FK, nullable = false)
    public AutoridadTipo getAutoridadTipo() {
        return autoridadTipo;
    }

    /**
     * @param autoridadTipo
     *            the autoridadTipo to set
     */
    public void setAutoridadTipo(final AutoridadTipo autoridadTipo) {
        this.autoridadTipo = autoridadTipo;
    }

    /**
     * @return the autoridadEtiquetas
     */
    @OneToMany(mappedBy = AutoridadEtiqueta.PROPTY_NAME_AUTORIDAD, cascade = { CascadeType.ALL })
    @Cascade( { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    public List<AutoridadEtiqueta> getAutoridadEtiquetas() {
        return autoridadEtiquetas;
    }

    /**
     * @param autoridadEtiquetas
     *            the autoridadEtiquetas to set
     */
    public void setAutoridadEtiquetas(
            final List<AutoridadEtiqueta> autoridadEtiquetas) {
        this.autoridadEtiquetas = autoridadEtiquetas;
    }

    /**
     * A partir del código de la etiqueta (T100), se busca en el registro this
     * si existe tal etiqueta.
     * 
     * @param codigoEtiqueta
     *            nombre de la etiqueta
     * @param ordenRepetibilidad
     *            código de repetibilidad de la etiqueta (para identificar entre
     *            etiquetas repetibles)
     * @return RegistroEtiqueta con código codigoEtiqueta o null e.o.c.
     */
    @Transient
    public AutoridadEtiqueta getAutoridadEtiqueta(String codigoEtiqueta,
            String ordenRepetibilidad) {
        Iterator<AutoridadEtiqueta> it = getAutoridadEtiquetas().iterator();
        while (it.hasNext()) {
            AutoridadEtiqueta eti = it.next();
            if (eti.getCodigo().equalsIgnoreCase(codigoEtiqueta)
                    && eti.getOrdenRepetibilidad().toString().equalsIgnoreCase(
                            ordenRepetibilidad)) {
                return eti;
            }
        }
        return null;
    }

    /**
     * A partir del código de la etiqueta (T100), se busca en el registro this
     * si existe tal etiqueta.
     * 
     * @param codigoEtiqueta
     * @return AutoridadEtiqueta con código codigoEtiqueta o null e.o.c. NOTA:
     *         En el caso de etiquetas repetibles, solo retorna la primera
     *         instancia así que sería necesario incorporar una nueva columna
     *         que sería el ORDEN, dentro de las etiquetas repetibles
     */
    @Transient
    public AutoridadEtiqueta getAutoridadEtiqueta(String codigoEtiqueta) {
        return this.getAutoridadEtiqueta(codigoEtiqueta, new Integer(1)
                .toString());
    }

    /**
     * Retorna la etiqueta principal del conjunto de etiquetas de la autoridad
     * Aquella que se corresponde con 1xx. En caso de que no exista esta
     * etiqueta retorna null;
     * 
     * @return
     */
    @Transient
    public String getEtiquetaPpalAutoridad() {
        for (AutoridadEtiqueta autEtq : this.getAutoridadEtiquetas()) {
            if (autEtq.getCodigo().startsWith("T1")) {
                return autEtq.getCodigo();
            }
        }
        return "";
    }

    /**
     * @return the autoridadEtiquetaControles
     */
    @OneToMany(mappedBy = AutoridadEtiquetaControl.AUTORIDAD, cascade = { CascadeType.ALL })
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<AutoridadEtiquetaControl> getAutoridadEtiquetaControles() {
        return autoridadEtiquetaControles;
    }

    /**
     * @param autoridadEtiquetaControles
     *            the registroEtiquetaControles to set
     */
    public void setAutoridadEtiquetaControles(
            final List<AutoridadEtiquetaControl> autoridadEtiquetaControles) {
        this.autoridadEtiquetaControles = autoridadEtiquetaControles;
    }

    /**
     * @return the registroEtiquetaControles FIXME: en el caso de etiquetas
     *         repetibles, solo retorna la primera instancia así que sería
     *         necesario incorporar una nueva columna que sería el ORDEN, dentro
     *         de las etiquetas repetibles
     */
    @Transient
    public AutoridadEtiquetaControl getAutoridadEtiquetaControl(String etiqueta) {

        AutoridadEtiquetaControl resultado = null;
        for (AutoridadEtiquetaControl eti : this.autoridadEtiquetaControles) {
            if (eti.getCodigo().equalsIgnoreCase(etiqueta))
                resultado = eti;
        }
        // TODO a completar
        return resultado;
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
                + ((autoridadTipo == null) ? 0 : autoridadTipo.hashCode());
        result = prime * result
                + ((cabecera == null) ? 0 : cabecera.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (!(obj instanceof Autoridad)) {
            return false;
        }
        final Autoridad other = (Autoridad) obj;
        if (autoridadTipo == null) {
            if (other.autoridadTipo != null) {
                return false;
            }
        } else if (!autoridadTipo.equals(other.autoridadTipo)) {
            return false;
        }
        if (cabecera == null) {
            if (other.cabecera != null) {
                return false;
            }
        } else if (!cabecera.equals(other.cabecera)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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

        .append(Autoridad.PROPTY_NAME_ID, this.id)

        .append(Autoridad.PROPTY_NAME_CABECERA,
                (this.cabecera == null) ? 0 : this.cabecera)

        .append(
                Autoridad.PROPTY_NAME_AUTORIDAD_TIPO,
                (this.autoridadTipo == null) ? 0 : this.autoridadTipo
                        .toString())

        .toString();
    }

    public Object clone() {
        Autoridad autoridad = null;
        autoridad = new Autoridad(this.cabecera, this.autoridadTipo);

        List<AutoridadEtiqueta> lista = new ArrayList<AutoridadEtiqueta>();

        for (AutoridadEtiqueta auto : this.getAutoridadEtiquetas()) {
            lista.add(auto);
        }

        autoridad.setAutoridadEtiquetas(lista);

        /*
         * for (AutoridadEtiqueta etiqueta :this.autoridadEtiquetas){ }
         */

        return autoridad;

    }

}
