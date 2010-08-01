package org.librae.catalogacion.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar un Registro
 * 
 * @author jcdiaz
 * @version 0.3 se establece Registro.registroFormato == nullable, por
 *          necesidades de la migración del microUniverso y a cuentas de que en
 *          ABSYS, en su primera versión de la migración, no queda reflejado.
 */
@Entity(name = Registro.ENTITY_NAME)
@Table(name = Registro.TABLE_NAME)
public class Registro extends SpringRemotableLazyEntity<Registro> {

    /**
     * BaseObject es Serializable, por lo tanto Registro necesita un Serial
     * Version UID
     */
    private static final long             serialVersionUID                 = 4885829162672044630L;

    public static final String            ENTITY_NAME                      = "org.librae.catalogacion.model.Registro";

    /**
     * Constantes para referencia de los atributos de Autoridad
     */
    public static final String            PROPTY_NAME_ID                   = "id";
    public static final String            PROPTY_NAME_VISIBLE_OPAC         = "visibleOpac";
    public static final String            PROPTY_NAME_CABECERA             = "cabecera";
    public static final String            PROPTY_NAME_REGISTRO_ESTADO      = "registroEstado";
    public static final String            PROPTY_NAME_REGISTRO_TIPO        = "registroTipo";
    public static final String            PROPTY_NAME_REGISTRO_FORMATO     = "registroFormato";
    public static final String            PROPTY_NAME_AUTOR                = "autor";
    public static final String            PROPTY_NAME_TITULO               = "titulo";
    public static final String            PROPTY_NAME_EDITORIAL            = "editorial";
    public static final String            PROPTY_NAME_MATERIA              = "materia";
    public static final String            PROPTY_NAME_INTERNACIONAL_NUMBER = "internacionalNumber";
    public static final String            PROPTY_NAME_REGISTRO_IMAGEN      = "registroImagen";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String            TABLE_NAME                       = "CAT_REGISTRO";
    private static final String           ID_GENERATOR_NAME                = "generator_cat_registro";
    private static final String           ID_SEQUENCE_NAME                 = "SEQ_CAT_REGISTRO";

    public static final String            COLUMN_NAME_ID                   = "X_REGISTRO";
    public static final String            COLUMN_NAME_VISIBLE_OPAC         = "L_VISIBLE_OPAC";
    public static final String            COLUMN_NAME_CABECERA             = "T_CABECERA";

    public static final String            COLUMN_NAME_REGISTRO_ESTADO_FK   = "REG_X_REGISTRO_ESTADO";
    public static final String            COLUMN_NAME_REGISTRO_TIPO_FK     = "REG_X_REGISTRO_TIPO";
    public static final String            COLUMN_NAME_REGISTRO_FORMATO_FK  = "REG_X_REGISTRO_FORMATO";
    public static final String            COLUMN_NAME_REGISTRO_IMAGEN_FK   = "REG_X_REGISTRO_IMAGEN";
    public static final String            COLUMN_NAME_TINTIT               = "X_TINTIT";
    public static final String            COLUMN_NAME_AUTOR                = "R_AUTOR";
    public static final String            COLUMN_NAME_TITULO               = "R_TITULO";
    public static final String            COLUMN_NAME_MATERIA              = "R_MATERIA";
    public static final String            COLUMN_NAME_EDITORIAL            = "R_EDITORIAL";
    public static final String            COLUMN_NAME_INTERNACIONAL_NUMBER = "R_INTERNACIONAL_NUMBER";

    /*
     * Constantes para definir tamaños de campos bb.dd para las etiquetas
     * principales
     */
    public static final int               TAM_AUTOR                        = 1000;
    public static final int               TAM_TITULO                       = 1000;
    public static final int               TAM_EDITORIAL                    = 255;
    public static final int               TAM_MATERIA                      = 1000;
    public static final int               TAM_INTERNACIONAL_NUMBER         = 1500;

    private String                        idClass                          = "org.librae.catalogacion.model.Registro";

    /*
     * Clave autonumérica secuencial sin significado funcional
     */
    // @Field
    private Long                          id;

    /**
     * Indica si el registro va a ser visible desde el OPAC
     */
    private Boolean                       visibleOpac;

    /**
     * Cabecera común del registro
     */
    // @Field
    private String                        cabecera;

    /**
     * Campo identificativo del registro, necesario para la migracion
     */

    private Long                          tintit;

    /**
     *
     */
    private List<RegistroEtiqueta>        registroEtiquetas;

    /**
     *
     */
    private List<RegistroEtiquetaControl> registroEtiquetaControles;

    /**
     *
     */
    private List<Novedad>                 novedades;

    /**
     *
     */
    // private List<AutoridadEtiqueta> autoridadEtiquetas;
    /**
     *
     */
    private RegistroEstado                registroEstado;

    /**
     *
     */
    private RegistroTipo                  registroTipo;

    /**
     *
     */
    private RegistroFormato               registroFormato;

    /**
     * Clave foranea a la tabla que contiene las imagenes de los registros. Se
     * decide desacoplar la imagen debido a la carga de datos que supone para
     * aquellas pantallas donde no es necesario la imagen, de esta manera, se
     * evita la carga y se requiere sólo en aquellas necesarias.
     */
    private RegistroImagen                registroImagen;

    /**
     * Listado que contiene los objetos de la tabla intermedia entre registros y
     * ejemplares que direfencian el uso de un registro como titulo primario y
     * titulo secundario,
     */
    private List<RegistroEjemplar>        registrosEjemplares;

    /**
     * Campos principales definidos por Dublin Core, que serán los que se
     * almacenen en BB.DD
     */
    /*
     * Autor del registro
     */
    private String                        autor;
    /*
     * Título del registro ()
     */
    private String                        titulo;
    /*
     * Según proceda : ISBN o ISSN (en el caso de seriadas)
     */
    private String                        internacionalNumber;

    /*
     * editorial
     */
    private String                        editorial;
    /*
     * Materia
     */
    private String                        materia;
    /**
     * Campos principales definidos por Dublin Core, que serán los que se usen
     * para las búsquedas en SOLR
     */
    @Transient
    private String                        autorSearch;
    @Transient
    private String                        tituloSearch;
    @Transient
    private String                        internacionalNumberSearch;
    @Transient
    private String                        editorialSearch;
    @Transient
    private String                        materiaSearch;

    /**
     *
     */
    private List<ActivoDigital>           registrosActivos;

    /**
     * Constructor sin parámetros
     */
    protected Registro() {
        super();
        this.registroEtiquetas = new ArrayList<RegistroEtiqueta>();
        this.registroEtiquetaControles = new ArrayList<RegistroEtiquetaControl>();
        this.novedades = new ArrayList<Novedad>();
        this.registrosEjemplares = new ArrayList<RegistroEjemplar>();
        this.registrosActivos = new ArrayList<ActivoDigital>();
        this.autor = "";
        this.titulo = "";
        this.editorial = "";
        this.internacionalNumber = "";
        this.materia = "";
    }

    /**
     * Constructor con parámetros
     * 
     * @param visibleOpac
     * @param cabecera
     */
    public Registro(final Boolean visibleOpac, final String cabecera) {
        super();
        this.visibleOpac = visibleOpac;
        this.cabecera = cabecera;
        this.registroEtiquetas = new ArrayList<RegistroEtiqueta>();
        this.registroEtiquetaControles = new ArrayList<RegistroEtiquetaControl>();
        this.novedades = new ArrayList<Novedad>();
        this.registrosEjemplares = new ArrayList<RegistroEjemplar>();
        this.registrosActivos = new ArrayList<ActivoDigital>();
        this.autor = "";
        this.titulo = "";
        this.editorial = "";
        this.internacionalNumber = "";
        this.materia = "";
    }

    /**
     * Constructor con parámetros
     * 
     * @param visibleOpac
     * @param cabecera
     * @param formato
     * @param estado
     * @param tipo
     */
    public Registro(Boolean visibleOpac, String cabecera,
            RegistroFormato formato, RegistroEstado estado, RegistroTipo tipo) {
        this.visibleOpac = visibleOpac;
        this.cabecera = cabecera;
        this.registroEtiquetas = new ArrayList<RegistroEtiqueta>();
        this.registroEtiquetaControles = new ArrayList<RegistroEtiquetaControl>();
        this.novedades = new ArrayList<Novedad>();
        this.registrosEjemplares = new ArrayList<RegistroEjemplar>();
        this.registroFormato = formato;
        this.registroEstado = estado;
        this.registroTipo = tipo;
        this.registrosActivos = new ArrayList<ActivoDigital>();
        this.autor = "";
        this.titulo = "";
        this.editorial = "";
        this.internacionalNumber = "";
        this.materia = "";
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Registro.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Registro.ID_SEQUENCE_NAME)
    @Column(name = Registro.COLUMN_NAME_ID, nullable = false)
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
     * @return the visibleOpac
     */
    @Column(name = Registro.COLUMN_NAME_VISIBLE_OPAC, nullable = false)
    public Boolean getVisibleOpac() {
        return visibleOpac;
    }

    /**
     * @param visibleOpac
     *            the visibleOpac to set
     */
    public void setVisibleOpac(final Boolean visibleOpac) {
        this.visibleOpac = visibleOpac;
    }

    /**
     * @return the cabecera
     */
    @Column(name = Registro.COLUMN_NAME_CABECERA, nullable = false, length = 255)
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
     * @return the tintit
     */
    @Column(name = Registro.COLUMN_NAME_TINTIT)
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
     * @return the registroEtiquetas
     */
    @OneToMany(mappedBy = RegistroEtiqueta.PROPTY_NAME_REGISTRO, cascade = { CascadeType.ALL })
    @Cascade( { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    public List<RegistroEtiqueta> getRegistroEtiquetas() {
        return registroEtiquetas;
    }

    /**
     * @param registroEtiquetas
     *            the registroEtiquetas to set
     */
    public void setRegistroEtiquetas(
            final List<RegistroEtiqueta> registroEtiquetas) {
        this.registroEtiquetas = registroEtiquetas;
    }

    /**
     * @return the registroEtiquetaControles
     */
    @OneToMany(mappedBy = RegistroEtiquetaControl.REGISTRO, cascade = { CascadeType.ALL })
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<RegistroEtiquetaControl> getRegistroEtiquetaControles() {
        return registroEtiquetaControles;
    }

    /**
     * @param registroEtiquetaControles
     *            the registroEtiquetaControles to set
     */
    public void setRegistroEtiquetaControles(
            final List<RegistroEtiquetaControl> registroEtiquetaControles) {
        this.registroEtiquetaControles = registroEtiquetaControles;
    }

    /**
     * @return the registroEtiquetaControles FIXME: en el caso de etiquetas
     *         repetibles, solo retorna la primera instancia así que sería
     *         necesario incorporar una nueva columna que sería el ORDEN, dentro
     *         de las etiquetas repetibles
     */
    @Transient
    public RegistroEtiquetaControl getRegistroEtiquetaControl(String etiqueta) {

        RegistroEtiquetaControl resultado = null;
        for (RegistroEtiquetaControl eti : this.registroEtiquetaControles) {
            if (eti.getCodigo().equalsIgnoreCase(etiqueta))
                resultado = eti;
        }
        // TODO a completar
        return resultado;
    }

    /**
     * @return the novedades
     */
    @OneToMany(mappedBy = Novedad.REGISTRO)
    public List<Novedad> getNovedades() {
        return novedades;
    }

    /**
     * @param novedades
     *            the novedades to set
     */
    public void setNovedades(final List<Novedad> novedades) {
        this.novedades = novedades;
    }

    /**
     * @return the registroEstado
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.catalogacion.model.RegistroEstado.class)
    @JoinColumn(name = Registro.COLUMN_NAME_REGISTRO_ESTADO_FK, nullable = false)
    public RegistroEstado getRegistroEstado() {
        return registroEstado;
    }

    /**
     * @param registroEstado
     *            the registroEstado to set
     */
    public void setRegistroEstado(final RegistroEstado registroEstado) {
        this.registroEstado = registroEstado;
    }

    /**
     * @return the registroTipo
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.catalogacion.model.RegistroTipo.class)
    @JoinColumn(name = Registro.COLUMN_NAME_REGISTRO_TIPO_FK, nullable = false)
    public RegistroTipo getRegistroTipo() {
        return registroTipo;
    }

    /**
     * @param registroTipo
     *            the registroTipo to set
     */
    public void setRegistroTipo(final RegistroTipo registroTipo) {
        this.registroTipo = registroTipo;
    }

    /**
     * @return the formatoRegistro
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.catalogacion.model.RegistroFormato.class)
    @JoinColumn(name = Registro.COLUMN_NAME_REGISTRO_FORMATO_FK, nullable = true)
    public RegistroFormato getRegistroFormato() {
        return registroFormato;
    }

    /**
     * @param formatoRegistro
     *            the formatoRegistro to set
     */
    public void setRegistroFormato(final RegistroFormato registroFormato) {
        this.registroFormato = registroFormato;
    }

    /**
     * @return the registroImagen
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.catalogacion.model.RegistroImagen.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Registro.COLUMN_NAME_REGISTRO_IMAGEN_FK)
    public RegistroImagen getRegistroImagen() {
        return registroImagen;
    }

    /**
     * @param registroImagen
     *            the registroImagen to set
     */
    public void setRegistroImagen(final RegistroImagen registroImagen) {
        this.registroImagen = registroImagen;
    }

    /**
     * @return the registrosEjemplares
     */
    // @OneToMany(mappedBy = RegistroEjemplar.PROPTY_NAME_REGISTRO)
    @OneToMany(mappedBy = RegistroEjemplar.PROPTY_NAME_REGISTRO, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.catalogacion.model.RegistroEjemplar.class, fetch = FetchType.LAZY)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    public List<RegistroEjemplar> getRegistrosEjemplares() {
        return registrosEjemplares;
    }

    /**
     * @param registrosEjemplares
     *            the registrosEjemplares to set
     */
    public void setRegistrosEjemplares(
            final List<RegistroEjemplar> registrosEjemplares) {
        this.registrosEjemplares = registrosEjemplares;
    }

    /**
     * @return the autor
     */
    @Column(name = Registro.COLUMN_NAME_AUTOR, length = Registro.TAM_AUTOR)
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor
     *            the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the titulo
     */
    @Column(name = Registro.COLUMN_NAME_TITULO, length = Registro.TAM_TITULO)
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo
     *            the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the internacionalNumber
     */
    @Column(name = Registro.COLUMN_NAME_INTERNACIONAL_NUMBER, length = Registro.TAM_INTERNACIONAL_NUMBER)
    public String getInternacionalNumber() {
        return internacionalNumber;
    }

    /**
     * @param internacionalNumber
     *            the internacionalNumber to set
     */
    public void setInternacionalNumber(String internacionalNumber) {
        this.internacionalNumber = internacionalNumber;
    }

    /**
     * @return the editorial
     */
    @Column(name = Registro.COLUMN_NAME_EDITORIAL, length = Registro.TAM_EDITORIAL)
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial
     *            the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * @return the materia
     */
    @Column(name = Registro.COLUMN_NAME_MATERIA, length = Registro.TAM_MATERIA)
    public String getMateria() {
        return materia;
    }

    /**
     * @param materia
     *            the materia to set
     */
    public void setMateria(String materia) {
        this.materia = materia;
    }

    /**
     * @return the autor para SOLR
     */
    @Transient
    public String getAutorSearch() {
        return autorSearch;
    }

    /**
     * @param autor
     *            the autor to set
     */
    @Transient
    public void setAutorSearch(String autor) {
        this.autorSearch = autor;
    }

    /**
     * @return the titulo para SOLR
     */
    @Transient
    public String getTituloSearch() {
        return tituloSearch;
    }

    /**
     * @param titulo
     *            the titulo to set
     */
    @Transient
    public void setTituloSearch(String titulo) {
        this.tituloSearch = titulo;
    }

    /**
     * @return the internacionalNumber
     */
    @Transient
    public String getInternacionalNumberSearch() {
        return internacionalNumberSearch;
    }

    /**
     * @param internacionalNumber
     *            the internacionalNumber to set
     */
    @Transient
    public void setInternacionalNumberSearch(String internacionalNumber) {
        this.internacionalNumberSearch = internacionalNumber;
    }

    /**
     * @return the editorial
     */
    @Transient
    public String getEditorialSearch() {
        return editorialSearch;
    }

    /**
     * @param editorial
     *            the editorial to set
     */
    @Transient
    public void setEditorialSearch(String editorial) {
        this.editorialSearch = editorial;
    }

    /**
     * @return the materia
     */
    @Transient
    public String getMateriaSearch() {
        return materiaSearch;
    }

    /**
     * @param materia
     *            the materia to set
     */
    @Transient
    public void setMateriaSearch(String materia) {
        this.materiaSearch = materia;
    }

    /**
     * @return the registrosActivos
     */
    @OneToMany(mappedBy = ActivoDigital.PROPTY_NAME_REGISTRO)
    public List<ActivoDigital> getRegistrosActivos() {
        return registrosActivos;
    }

    /**
     * @param registrosActivos
     *            the registrosActivos to set
     */
    public void setRegistrosActivos(final List<ActivoDigital> registrosActivos) {
        this.registrosActivos = registrosActivos;
    }

    /**
     * A partir del código de la etiqueta (T100), se busca en el registro this
     * si existe tal etiqueta. En el caso de que existan varias repetibles,
     * retornará todas ellas
     * 
     * @param codigoEtiqueta
     * @return RegistroEtiqueta con código codigoEtiqueta o null e.o.c.
     */
    @Transient
    public List<RegistroEtiqueta> getRegistroEtiquetasRepetibles(
            String codigoEtiqueta) {
        Iterator<RegistroEtiqueta> it = getRegistroEtiquetas().iterator();
        List<RegistroEtiqueta> resultado = new ArrayList<RegistroEtiqueta>();
        while (it.hasNext()) {
            RegistroEtiqueta eti = it.next();
            if (eti.getCodigo().equalsIgnoreCase(codigoEtiqueta)) {
                resultado.add(eti);
            }
        }
        return resultado;
    }

    /**
     * A partir del código de la etiqueta (T100), se busca en el registro this
     * si existe tal etiqueta, buscandola tanto por código como por
     * ordenRepetibilidad
     * 
     * @param codigoEtiqueta
     * @param ordenRepetibilidad
     * @return RegistroEtiqueta con código codigoEtiqueta o null e.o.c.
     */
    @Transient
    public RegistroEtiqueta getRegistroEtiqueta(String codigoEtiqueta,
            String ordenRepetibilidad) {
        Iterator<RegistroEtiqueta> it = getRegistroEtiquetas().iterator();
        while (it.hasNext()) {
            RegistroEtiqueta eti = it.next();
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
     * @return RegistroEtiqueta con código codigoEtiqueta o null e.o.c. NOTA: En
     *         el caso de etiquetas repetibles, solo retorna la primera
     *         instancia así que sería necesario incorporar una nueva columna
     *         que sería el ORDEN, dentro de las etiquetas repetibles
     */
    @Transient
    public RegistroEtiqueta getRegistroEtiqueta(String codigoEtiqueta) {
        return this.getRegistroEtiqueta(codigoEtiqueta, new Integer(1)
                .toString());
    }

    /**
     * @return autoridades vinculadas al registro objeto de trabajo (this) e.o.c
     *         : lista vacía.
     */
    @Transient
    public List<String> getAutoridades() {
        List<String> idsAutoridades = new ArrayList<String>();
        List<AutoridadEtiqueta> laet;
        for (RegistroEtiqueta regEtq : this.getRegistroEtiquetas()) {
            regEtq = (RegistroEtiqueta) regEtq.cloneNonLazy();
            laet = regEtq.getAutoridadEtiquetas();
            for (AutoridadEtiqueta autEtq : laet) {
                if (!idsAutoridades.contains(autEtq.getAutoridad().getId()
                        .toString())) {
                    idsAutoridades
                            .add(autEtq.getAutoridad().getId().toString());
                }
            }
        }
        return idsAutoridades;
    }

    /**
     * M�todo que crea un objeto Registro nuevo con las entidades almacenadas,
     * no en sesi�n. Necesario para OPAC
     */
    @Override
    public Object clone() {

        // se crea un nuevo registro que ser� el que tenga los datos del objeto
        // persistente
        Registro registro = new Registro(this.getVisibleOpac(), this
                .getCabecera(), this.getRegistroFormato(), this
                .getRegistroEstado(), this.getRegistroTipo());

        // se va a pasar el id del Registro original
        registro.setId(this.id);
        // se itera sobre las etiquetas del registro
        Iterator<RegistroEtiqueta> it = this.getRegistroEtiquetas().iterator();

        while (it.hasNext()) {

            RegistroEtiqueta etiqueta = it.next();
            // se crea una etiqueta a partir de los datos originales
            RegistroEtiqueta temp = new RegistroEtiqueta(etiqueta.getCodigo(),
                    etiqueta.getIndicador1(), etiqueta.getIndicador2(),
                    etiqueta.getOrdenRepetibilidad());

            // se itera sobre los subcampos
            Iterator<RegistroSubcampo> itSubcampo = etiqueta
                    .getRegistroSubcampos().iterator();

            while (itSubcampo.hasNext()) {
                RegistroSubcampo subcampo = itSubcampo.next();

                temp.getRegistroSubcampos().add(subcampo);

            }
            // se a�aden las etiquetas con sus respectivos subcampos
            registro.getRegistroEtiquetas().add(temp);

        }
        // se a�aden los ejemplares del registro original
        registro.getRegistrosEjemplares().addAll(this.getRegistrosEjemplares());

        return registro;

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
                + ((cabecera == null) ? 0 : cabecera.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((registroEstado == null) ? 0 : registroEstado.hashCode());
        result = prime * result
                + ((registroFormato == null) ? 0 : registroFormato.hashCode());
        result = prime * result
                + ((registroTipo == null) ? 0 : registroTipo.hashCode());
        result = prime * result
                + ((visibleOpac == null) ? 0 : visibleOpac.hashCode());
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
        if (!(obj instanceof Registro)) {
            return false;
        }
        final Registro other = (Registro) obj;
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
        if (registroEstado == null) {
            if (other.registroEstado != null) {
                return false;
            }
        } else if (!registroEstado.equals(other.registroEstado)) {
            return false;
        }
        if (registroFormato == null) {
            if (other.registroFormato != null) {
                return false;
            }
        } else if (!registroFormato.equals(other.registroFormato)) {
            return false;
        }
        if (registroTipo == null) {
            if (other.registroTipo != null) {
                return false;
            }
        } else if (!registroTipo.equals(other.registroTipo)) {
            return false;
        }
        if (visibleOpac == null) {
            if (other.visibleOpac != null) {
                return false;
            }
        } else if (!visibleOpac.equals(other.visibleOpac)) {
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

        .append(Registro.PROPTY_NAME_ID, this.id)

        .append(Registro.PROPTY_NAME_CABECERA,
                (this.cabecera == null) ? 0 : this.cabecera)

        .append(Registro.PROPTY_NAME_VISIBLE_OPAC,
                (this.visibleOpac == null) ? 0 : this.visibleOpac)

        .append(
                Registro.PROPTY_NAME_REGISTRO_ESTADO,
                (this.registroEstado == null) ? 0 : this.registroEstado
                        .toString())

        .append(
                Registro.PROPTY_NAME_REGISTRO_FORMATO,
                (this.registroFormato == null) ? 0 : this.registroFormato
                        .toString())

        .append(Registro.PROPTY_NAME_REGISTRO_TIPO,
                (this.registroTipo == null) ? 0 : this.registroTipo.toString())

        .toString();
    }

    @Override
    public Registro newInstance() {

        return new Registro();
    }

}