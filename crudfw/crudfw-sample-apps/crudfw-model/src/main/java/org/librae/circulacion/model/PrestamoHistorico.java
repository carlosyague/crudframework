package org.librae.circulacion.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.catalogacion.model.Ejemplar;
import org.librae.catalogacion.model.EjemplarEstado;
import org.librae.catalogacion.model.EjemplarLocalizacion;
import org.librae.catalogacion.model.EjemplarSoporte;
import org.librae.catalogacion.model.EjemplarTipo;
import org.librae.catalogacion.model.Registro;
import org.librae.common.model.BaseObject;
import org.librae.common.util.DateUtil;
import org.librae.lectores.model.Lector;
import org.librae.lectores.model.TipoLector;

/**
 * Préstamos activos.<br>
 * Un préstamo es un para (lector, ejemplar). Si el lector obtiene varios
 * ejemplares en préstamo del mismo registro, son varios préstamos (esta es
 * además la asunción del estándar NCIP), incluso si es un lector institucional.<br>
 * <br>
 * Los suministros de materiales no retornables se guardan directamente en esta
 * tabla con T_TIPO_PRESTAMO = 'SU'.<br>
 * <br>
 * Este pojo también contempla el caso de prestamos en red:<br>
 * las filas de esta tabla corresponden a préstamos en red que han sido
 * devueltos, o en las que el ejemplar del préstamo lo perdió el lector.
 *
 * @author asantamaria
 */
@Entity(name = PrestamoHistorico.ENTITY_NAME)
@Table(name = PrestamoHistorico.TABLE_NAME)
public class PrestamoHistorico extends BaseObject {

    /**
     * BaseObject is Serializable, so PrestamoHistorico needs a Serial Version
     * UID
     */
    private static final long    serialVersionUID                         = 2702824746269559520L;

    public static final String   ENTITY_NAME                              = "org.librae.circulacion.model.PrestamoHistorico";
    public static final String   TABLE_NAME                               = "CIR_PRESTAMOS_HIST";
    public static final String   ID_GENERATOR_NAME                        = "generator_cir_prestamos_hist";
    private static final String  ID_SEQUENCE_NAME                         = "SEQ_CIR_PRESTAMOS_HIST";
    public static final String   COLUMN_NAME_ID                           = "X_PRESTAMO_HIST";
    public static final String   COLUMN_NAME_TIPO_PRESTAMO                = "T_PRESTAMO_X_T_PRESTAMO";
    public static final String   COLUMN_NAME_EJEMPLAR_EXT_ID              = "T_EJEMPLAR_ID";
    public static final String   COLUMN_NAME_EJEMPLAR                     = "EJE_X_EJEMPAR";
    public static final String   COLUMN_NAME_FECHA_PRESTAMO               = "F_PRESTAMO";
    public static final String   COLUMN_NAME_FECHA_DEVOLUCION_POL         = "F_DEVOLUCION_POL";
    public static final String   COLUMN_NAME_PERIODO_INDETERMINADO        = "L_PERIODO_INDETERMINADO";
    public static final String   COLUMN_NAME_DIAS_ANTES_SUSPENSION        = "N_DIAS_ANTES_SUSPENSION";
    public static final String   COLUMN_NAME_DIAS_SUSPENSION              = "N_DIAS_SUSPENSION";
    public static final String   COLUMN_NAME_FECHA_RENOVACION             = "F_RENOVACION";
    public static final String   COLUMN_NAME_RENOVACION_NUM               = "N_RENOVACION_NUM";
    public static final String   COLUMN_NAME_DIAS_RENOVACION              = "N_DIAS_RENOVACION";
    public static final String   COLUMN_NAME_DIAS_ANTES_FINAL             = "N_DIAS_ANTES_FINAL";
    public static final String   COLUMN_NAME_RECLAMACION_NUM              = "N_RECLAMACION_NUM";
    public static final String   COLUMN_NAME_DIAS_RECLAMACION_1A          = "N_DIAS_RECLAMACION_1A";
    public static final String   COLUMN_NAME_DIAS_RECLAMACION_2A          = "N_DIAS_RECLAMACION_2A";
    public static final String   COLUMN_NAME_DIAS_RECLAMACION_3A          = "N_DIAS_RECLAMACION_3A";
    public static final String   COLUMN_NAME_EJEMPLAR_AUTOR               = "T_EJE_AUTOR";
    public static final String   COLUMN_NAME_EJEMPLAR_AUTOR_DE_COMPONENTE = "T_EJE_AUTOR_DE_COMPONENTE";
    public static final String   COLUMN_NAME_EJEMPLAR_COD_IDENTIFICAD_REG = "T_EJE_COD_IDENT_REGISTRO";
    public static final String   COLUMN_NAME_EJEMPLAR_IDENTIFICADOR_REGIS = "T_EJE_IDENTIFICADOR_REGISTRO";
    public static final String   COLUMN_NAME_EJEMPLAR_BIBLIOGRAPHIC_LEVEL = "T_EJE_BIBLIOGRAPHIC_LEVEL";
    public static final String   COLUMN_NAME_EJEMPLAR_COMPONENTE_ID       = "T_EJE_COMPONENTE_ID";
    public static final String   COLUMN_NAME_EJEMPLAR_COMPONENTE_ID_TIPO  = "T_EJE_COMPONENTE_ID_TIPO";
    public static final String   COLUMN_NAME_EJEMPLAR_EDICION             = "T_EJE_EDICION";
    public static final String   COLUMN_NAME_EJEMPLAR_IDIOMA              = "T_EJE_IDIOMA";
    public static final String   COLUMN_NAME_EJEMPLAR_FECHA_PUBLICACION   = "T_EJE_FECHA_PUBLICACION";
    public static final String   COLUMN_NAME_EJEMPLAR_EDITOR              = "T_EJE_EDITOR";
    public static final String   COLUMN_NAME_EJEMPLAR_TITULO              = "T_EJE_TITULO";
    public static final String   COLUMN_NAME_EJEMPLAR_TITULO_COMPONENTE   = "T_EJE_TITULO_COMPONENTE";
    public static final String   COLUMN_NAME_FECHA_DEVOLUCION             = "F_DEVOLUCION";
    public static final String   COLUMN_NAME_MAX_RENOVACIONES             = "N_MAX_RENOVACIONES";
    public static final String   COLUMN_NAME_MAX_DIAS_SUSPENSION_EJEMPLAR = "N_MAX_DIAS_SUSPENSION_EJE";
    public static final String   COLUMN_NAME_NOTA_SUMINISTRO_NO_RETORNABL = "T_NOTA_SUMINISTRO_NO_RETORN";
    public static final String   COLUMN_NAME_FECHA_RECLAMACION_1A         = "F_RECLAMACION_1A";
    public static final String   COLUMN_NAME_FECHA_RECLAMACION_2A         = "F_RECLAMACION_2A";
    public static final String   COLUMN_NAME_FECHA_RECLAMACION_3A         = "F_RECLAMACION_3A";
    public static final String   COLUMN_NAME_FECHA_DETECTA_SUSPENSION     = "F_DETECTA_SUSPENSION";
    public static final String   COLUMN_NAME_FECHA_INICIO_SUSPENSION      = "F_INI_SUSPENSION";
    public static final String   COLUMN_NAME_FECHA_FIN_SUSPENSION         = "F_FIN_SUSPENSION";
    public static final String   COLUMN_NAME_USUARIO_BIBLIOTECARIO        = "USU_X_BIBLIOTECARIO";
    public static final String   COLUMN_NAME_USUARIO_BIBLIOTECARIO_EXT    = "T_BIBLIOTECARIO_EXT";
    public static final String   COLUMN_NAME_BIBLIOTECA_EJEMPLAR          = "BIB_X_BIBLIOTECA_EJE";
    public static final String   COLUMN_NAME_BIBLIOTECA_CONSORCIO_EJE     = "BIB_X_BIBLIOTECA_CONSORCIO_EJE";
    public static final String   COLUMN_NAME_BIBLIOTECA_RENOVACION        = "BIB_X_BIBLIOTECA_REN";
    public static final String   COLUMN_NAME_BIBLIOTECA_CONSORCIO_RENOVA  = "BIB_X_BIBLIOTECA_CONSORCIO_REN";
    public static final String   COLUMN_NAME_BIBLIOTECA_DEVOLUCION        = "BIB_X_BIBLIOTECA_DEV";
    public static final String   COLUMN_NAME_BIBLIOTECA_CONSORCIO_DEVOLUC = "BIB_X_BIBLIOTECA_CONSORCIO_DEV";
    public static final String   COLUMN_NAME_BIBLIOTECA_LECTOR            = "BIB_X_BIBLIOTECA_LEC";
    public static final String   COLUMN_NAME_BIBLIOTECA_CONSORCIO_LECTOR  = "BIB_X_BIBLIOTECA_CONSORCIO_LEC";
    public static final String   COLUMN_NAME_BIBLIOTECA_PRESTAMISTA       = "BIB_X_BIBLIOTECA_PRESTAMISTA";
    public static final String   COLUMN_NAME_LECTOR                       = "LEC_X_LECTOR";
    public static final String   COLUMN_NAME_LECTOR_EXT_ID                = "T_LECTOR_EXT_ID";
    public static final String   COLUMN_NAME_TIPO_LECTOR                  = "LEC_TIPO_X_TIPO_LECTOR";
    public static final String   COLUMN_NAME_EJEMPLAR_ESTADO_DEVOLUCION   = "EJE_ESTA_X_EJEM_ESTADO_DEV";
    public static final String   COLUMN_NAME_EJEMPLAR_LOCALIZACION_DEVOLU = "EJE_LOCA_X_EJEM_LOCALIZ_DEV";
    public static final String   COLUMN_NAME_CONSORCIO                    = "CON_X_CONSORCIO";
    public static final String   COLUMN_NAME_SENSITIZATION_FLAG_EJ        = "L_EJEMPLAR_SENSITIZATION_FLAG";
    public static final String   COLUMN_NAME_T_EJE_BIBLIOGRAPHIC_DESC     = "T_EJEM_BIBLIOGRAPHIC_DESC";
    public static final String   COLUMN_NAME_T_EJEMPLAR_ESTADO_DETALLE    = "T_EJEMPLAR_ESTADO_DETALLE";
    public static final String   COLUMN_NAME_T_EJEMPLAR_LOC_DESDE         = "T_EJEMPLAR_LOC_DESDE";
    public static final String   COLUMN_NAME_T_EJEMPLAR_LOC_HASTA         = "T_EJEMPLAR_LOC_HASTA";
    public static final String   COLUMN_NAME_T_EJEMPLAR_LOC_NOMBRE        = "T_EJEMPLAR_LOC_NOMBRE";
    public static final String   COLUMN_NAME_T_EJEM_SCHEME_CIR_STATUS     = "T_EJEM_SCHEME_CIR_STATUS";
    public static final String   COLUMN_NAME_T_EJEM_SCHEME_LOC_TIPO       = "T_EJEMPLAR_SCHEME_LOC_TIPO";
    public static final String   COLUMN_NAME_T_EJEM_SCHEME_MARCA_SEG      = "T_EJEM_SCHEME_MARCA_SEG";
    public static final String   COLUMN_NAME_T_EJEM_SCHEME_REST_TYPE      = "T_EJEM_SCHEME_REST_TYPE";
    public static final String   COLUMN_NAME_T_EJEM_VALUE_CIR_STATUS      = "T_EJEM_VALUE_CIR_STATUS";
    public static final String   COLUMN_NAME_T_EJEM_VALUE_LOC_TIPO        = "T_EJEM_VALUE_LOC_TIPO";
    public static final String   COLUMN_NAME_T_EJEM_VALUE_MARCA_SEG       = "T_EJEM_VALUE_MARCA_SEG";
    public static final String   COLUMN_NAME_T_EJEM_VALUE_RESTR_TYPE      = "T_EJEM_VALUE_RESTR_TYPE";

    public static final String   PROPTY_NAME_ID                           = "id";
    public static final String   PROPTY_NAME_TIPO_PRESTAMO                = "tipoPrestamo";
    public static final String   PROPTY_NAME_EJEMPLAR_EXT_ID              = "ejemplarExtId";
    public static final String   PROPTY_NAME_EJEMPLAR                     = "ejemplar";
    public static final String   PROPTY_NAME_FECHA_PRESTAMO               = "fechaPrestamo";
    public static final String   PROPTY_NAME_FECHA_DEVOLUCION_POL         = "fechaDevolucionPol";
    public static final String   PROPTY_NAME_PERIODO_INDETERMINADO        = "periodoIndeterminado";
    public static final String   PROPTY_NAME_DIAS_ANTES_SUSPENSION        = "diasAntesSuspension";
    public static final String   PROPTY_NAME_DIAS_SUSPENSION              = "diasSuspension";
    public static final String   PROPTY_NAME_FECHA_RENOVACION             = "fechaRenovacion";
    public static final String   PROPTY_NAME_RENOVACION_NUM               = "renovacionNum";
    public static final String   PROPTY_NAME_DIAS_RENOVACION              = "diasRenovacion";
    public static final String   PROPTY_NAME_DIAS_ANTES_FINAL             = "diasAntesFinal";
    public static final String   PROPTY_NAME_RECLAMACION_NUM              = "reclamacionNum";
    public static final String   PROPTY_NAME_DIAS_RECLAMACION_1A          = "diasReclamacion1a";
    public static final String   PROPTY_NAME_DIAS_RECLAMACION_2A          = "diasReclamacion2a";
    public static final String   PROPTY_NAME_DIAS_RECLAMACION_3A          = "diasReclamacion3a";
    public static final String   PROPTY_NAME_EJEMPLAR_AUTOR               = "ejemplarAutor";
    public static final String   PROPTY_NAME_EJEMPLAR_AUTOR_DE_COMPONENTE = "ejemplarAutorDeComponente";
    public static final String   PROPTY_NAME_EJEMPLAR_COD_IDENTIFICAD_REG = "ejemplarCodIdentificadorRegistro";
    public static final String   PROPTY_NAME_EJEMPLAR_IDENTIFICADOR_REGIS = "ejemplarIdentificadorRegistro";
    public static final String   PROPTY_NAME_EJEMPLAR_BIBLIOGRAPHIC_LEVEL = "ejemplarBibliographicLevel";
    public static final String   PROPTY_NAME_EJEMPLAR_COMPONENTE_ID       = "ejemplarComponenteId";
    public static final String   PROPTY_NAME_EJEMPLAR_COMPONENTE_ID_TIPO  = "ejemplarComponenteIdTipo";
    public static final String   PROPTY_NAME_EJEMPLAR_EDICION             = "ejemplarEdicion";
    public static final String   PROPTY_NAME_EJEMPLAR_IDIOMA              = "ejemplarIdioma";
    public static final String   PROPTY_NAME_EJEMPLAR_FECHA_PUBLICACION   = "ejemplarFechaPublicacion";
    public static final String   PROPTY_NAME_EJEMPLAR_EDITOR              = "ejemplarEditor";
    public static final String   PROPTY_NAME_EJEMPLAR_TITULO              = "ejemplarTitulo";
    public static final String   PROPTY_NAME_EJEMPLAR_TITULO_COMPONENTE   = "ejemplarTituloComponente";
    public static final String   PROPTY_NAME_FECHA_DEVOLUCION             = "fechaDevolucion";
    public static final String   PROPTY_NAME_MAX_RENOVACIONES             = "maxRenovaciones";
    public static final String   PROPTY_NAME_MAX_DIAS_SUSPENSION_EJEMPLAR = "maxDiasSuspensionEjemplar";
    public static final String   PROPTY_NAME_NOTA_SUMINISTRO_NO_RETORNABL = "notaSuministroNoRetornables";
    public static final String   PROPTY_NAME_FECHA_RECLAMACION_1A         = "fechaReclamacion1a";
    public static final String   PROPTY_NAME_FECHA_RECLAMACION_2A         = "fechaReclamacion2a";
    public static final String   PROPTY_NAME_FECHA_RECLAMACION_3A         = "fechaReclamacion3a";
    public static final String   PROPTY_NAME_FECHA_DETECTA_SUSPENSION     = "fechaDetectaSuspension";
    public static final String   PROPERTY_NAME_FECHA_INI_SUSPENSION       = "fechaIniSuspension";
    public static final String   PROPERTY_NAME_FECHA_FIN_SUSPENSION       = "fechaFinSuspension";
    public static final String   PROPTY_NAME_USUARIO_BIBLIOTECARIO        = "usuarioBibliotecario";
    public static final String   PROPTY_NAME_USUARIO_BIBLIOTECARIO_EXT    = "usuarioBibliotecarioExtId";
    public static final String   PROPTY_NAME_BIBLIOTECA_CONSORCIO_EJE     = "bibliotecaConsorcioEjemplar";
    public static final String   PROPTY_NAME_BIBLIOTECA_RENOVACION        = "bibliotecaRenovacion";
    public static final String   PROPTY_NAME_BIBLIOTECA_CONSORCIO_RENOVA  = "bibliotecaConsorcioRenovacion";
    public static final String   PROPTY_NAME_BIBLIOTECA_DEVOLUCION        = "bibliotecaDevolucion";
    public static final String   PROPTY_NAME_BIBLIOTECA_CONSORCIO_DEVOLUC = "bibliotecaConsorcioDevolucion";
    public static final String   PROPTY_NAME_BIBLIOTECA_CONSORCIO_LECTOR  = "bibliotecaConsorcioLector";
    public static final String   PROPTY_NAME_BIBLIOTECA_PRESTAMISTA       = "bibliotecaPrestamista";
    public static final String   PROPTY_NAME_LECTOR                       = "lector";
    public static final String   PROPTY_NAME_LECTOR_EXT_ID                = "lectorExtId";
    public static final String   PROPTY_NAME_LECTOR_TIPO                  = "lectorTipo";
    public static final String   PROPTY_NAME_EJEMPLAR_ESTADO_DEVOLUCION   = "ejemplarEstadoDevolucion";
    public static final String   PROPTY_NAME_EJEMPLAR_LOCALIZACION_DEVOLU = "ejemplarLocalizacionDevolucion";
    public static final String   PROPTY_NAME_CONSORCIO                    = "consorcio";
    public static final String   PROPTY_NAME_SENSITIZATION_FLAG_EJ        = "sensitizationFlagEj";
    public static final String   PROPTY_NAME_T_EJE_BIBLIOGRAPHIC_DESC     = "ejemBiblioDesc";
    public static final String   PROPTY_NAME_T_EJEMPLAR_ESTADO_DETALLE    = "ejemEstadoDetalle";
    public static final String   PROPTY_NAME_T_EJEMPLAR_LOC_DESDE         = "ejemLocDesde";
    public static final String   PROPTY_NAME_T_EJEMPLAR_LOC_HASTA         = "ejemLocHasta";
    public static final String   PROPTY_NAME_T_EJEMPLAR_LOC_NOMBRE        = "ejemLocNombre";
    public static final String   PROPTY_NAME_T_EJEM_SCHEME_CIR_STATUS     = "ejemSchemeCirStatus";
    public static final String   PROPTY_NAME_T_EJEM_SCHEME_LOC_TIPO       = "ejemSchemeLocTipo";
    public static final String   PROPTY_NAME_T_EJEM_SCHEME_MARCA_SEG      = "ejemSchemeMarcaSeg";
    public static final String   PROPTY_NAME_T_EJEM_SCHEME_REST_TYPE      = "ejemSchemeRestType";
    public static final String   PROPTY_NAME_T_EJEM_VALUE_CIR_STATUS      = "ejemValueCirStatus";
    public static final String   PROPTY_NAME_T_EJEM_VALUE_LOC_TIPO        = "ejemValueLocTipo";
    public static final String   PROPTY_NAME_T_EJEM_VALUE_MARCA_SEG       = "ejemValueMarcaSeg";
    public static final String   PROPTY_NAME_T_EJEM_VALUE_RESTR_TYPE      = "ejemValueRestrType";

    /**
     * Clave primaria artificial
     */
    private Long                 id;

    /**
     * Tipo de préstamo<br>
     * Valores: <br>
     * DO (Domicilio).<br>
     * SA (Sala)<br>
     * SE (Seguridad)<br>
     * RE (Préstamo en Red)
     */
    private TipoPrestamo         tipoPrestamo;

    /**
     * Código único de un ejemplar en su biblioteca.<br>
     * Si el ejemplar pertenece a una de las bibliotecas registradas en
     * BIBLIOTECAS, este campo será null.<br>
     * Si el ejemplar es de otra biblioteca no registrada en BIBLIOTECAS (caso
     * del préstamo en red), será el id en dicha biblioteca. Elemento de dato
     * Unique Item Id.Item Identifier Value que se envía en Check Out Item y
     * Checked Out Item
     */
    private String               ejemplarExtId;

    /**
     * Si el ejemplar pertenece a una de las bibliotecas registradas en
     * BIBLIOTECAS, entonces es la referencia FONDOS.<br>
     * Si el ejemplar es de otra biblioteca no registrada en BIBLIOTECAS (caso
     * del préstamo en red), este campo será null.
     */
    private Ejemplar             ejemplar;

    /**
     * Fecha y hora en que tuvo lugar el préstamo
     */
    private Date                 fechaPrestamo;

    /**
     * Fecha-hora a la que corresponde devolver el préstamo según la política de
     * préstamos aplicada.
     */
    private Date                 fechaDevolucionPol;

    /**
     * true si el préstamo es por un periodo indeterminado. Si está activado,
     * tiene más precedencia que F_DEVOLUCION_POL<br>
     * false en caso contrario (valor por defecto)
     */
    private Boolean              periodoIndeterminado;

    /**
     * Días de suspensión (dssu en Ab*NET).<br>
     * Número de días con los que se va a sancional al lector que se retrase en
     * la devolución. El significado es número de días por ejemplar y día de
     * retraso en la devolución. Es decir, si un lector tiene en préstamo dos
     * ejemplares, y se retrasa en la devolución de ámbos dos días (despues de
     * ddsu) se le aplicará una suspensión de 4 días.<br>
     * <br>
     * Un valor 0 en esta propiedad sinifica que según esta política de
     * préstamos no se aplican días de suspensión por retrasos en las
     * devoluciones.
     */
    private Long                 diasSuspension;

    /**
     * Fecha-hora de la última renovación del préstamo
     */
    private Date                 fechaRenovacion;

    /**
     * Número de renovaciones realizadas para el préstamo: 1 cuando se ha
     * realizado una renovación, 2 cuando se han realizado dos renovaciones, ...<br>
     * Valor 0 cuando se crea el préstamo (valor por defecto)
     */
    private Long                 renovacionNum;

    /**
     * Por cuantos días se renueva el préstamo en cada renovación
     */
    private Long                 diasRenovacion;

    /**
     * Desde cuantos días antes de F_DEVOLUCION_POL, hasta esa misma fecha, se
     * puede realizar la renovación del préstamo.<br>
     * Si es la renovación n-sima, desde cuantos días antes de F_DEVOLUCION + n
     * * N_DIAS_RENOVACION se puede realizar la renovación del préstamo.
     */
    private Long                 diasAntesFinal;

    /**
     * Número de veces que se ha reclamado la devolución del préstamo al lector.<br>
     * 0 cuando se crea el préstamo.
     */
    private Long                 reclamacionNum;

    /**
     * Número de días después de la fecha de devolución (prevista) del ejemplar,
     * que se va a esperar la aplicación antes de emitir la primera carta de
     * reclamación.<br>
     * En definitiva, para el préstamo a domicilio, la primera reclamación no se
     * enviará antes de <br>
     * CIR_PRESTAMOS.F_DEVOLUCION_POL + N_DIAS_RECLAMACION_1A
     */
    private Long                 diasReclamacion1a;

    /**
     * Número de días después de la fecha de devolución (prevista) del ejemplar,
     * que se va a esperar la aplicación antes de emitir la segunda carta de
     * reclamación.<br>
     * En definitiva, para el préstamo a domicilio, la seunda reclamación no se
     * enviará antes de <br>
     * CIR_PRESTAMOS.F_DEVOLUCION_POL + N_DIAS_RECLAMACION_2A
     */
    private Long                 diasReclamacion2a;

    /**
     * Número de días después de la fecha de devolución (prevista) del ejemplar,
     * que se va a esperar la aplicación antes de emitir la tercera carta de
     * reclamación.<br>
     * En definitiva, para el préstamo a domicilio, la tercera reclamación no se
     * enviará antes de <br>
     * CIR_PRESTAMOS.F_DEVOLUCION_POL + N_DIAS_RECLAMACION_3A
     */
    private Long                 diasReclamacion3a;

    /**
     * Elemento de dato Autor<br>
     * En NCIP corresponde al elemento de dato Author<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarAutor;

    /**
     * Autor de una parte de la obra<br>
     * En NCIP corresponde al dato Author Of Component<br>
     * "author-of-article" en ISO 1016-1<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarAutorDeComponente;

    /**
     * ISBN, ISSN, ISRC, ISMN, ...<br>
     * En NCIP equivale al BIbliographic Item Identifier Code<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarCodIdentificadorRegistro;

    /**
     * Valor del código de identificación del registro bibliográfico<br>
     * En NCIP es el elemento de dato Bibliographic Item Identifier<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarIdentificadorRegistro;

    /**
     * Elemento de dato Bibliographic Level de NCIP (ver NCIP pag. 41), e
     * "Item-type" en ISO 10161-1<br>
     * Existe también el campo EJE_TIPO_X_EJEMPLAR_TIPO, pero este es sólo para
     * los tipos considerados por la aplicación, mientras que en este campo se
     * guarda el tipo de ejemplar que comunica la biblioteca propietaria del
     * mismo vía NCIP<br>
     * Sólo se cumplimenta en préstamos en red o interbibliotecarios, si la
     * bioblioteca propietaria del ejemplar lo informa.<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarBibliographicLevel;

    /**
     * Identifica una parte componente de un registro bibliográfico<br>
     * En NCIP corresponde al elemento de dato Component Identifier<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarComponenteId;

    /**
     * Tipo de identificador de componente de registro bibliográfico<br>
     * En NCIP corresponde al elemento de dato Component Identifier Type<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarComponenteIdTipo;

    /**
     * String que identifica la edición a la que pertenece el ejemplar<br>
     * En NCIP corresponde al elemento de dato Edition<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarEdicion;

    /**
     * Idioma del ejemplar<br>
     * En NCIP corresponde al elemento de dato Language.<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarIdioma;

    /**
     * Cadena que indica la fecha de publicación.<br>
     * En NCIP corresponde al elemento de dato Publication Date<br>
     * No es de tipo F_... porque en el caso de que sea informado por otra
     * biblioteca, vía NCIP puede tener cualquier formato<br>
     * Valor por defecto: NULL<br>
     */
    private String               ejemplarFechaPublicacion;

    /**
     * Editor del ejemplar<br>
     * En NCIP corresponde al elemento de dato Publisher<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarEditor;

    /**
     * Título del ejemplar<br>
     * En NCIP corresponde al elemento de dato Title<br>
     * Valor por defecto: NULL
     */
    private String               ejemplarTitulo;

    /**
     * Título de una parte de un ejemplar, por ejemplo título de un capítulo de
     * un libro o nombre de un artículo de una serie<br>
     * En NCIP corresponde al elemento de dato Title Of Component<br>
     * Valor por defecto: NULL<br>
     */
    private String               ejemplarTituloComponente;

    /**
     * Fecha-hora en que se devolvió el préstamo
     */
    private Date                 fechaDevolucion;

    /**
     * Número máximo de renovaciones que admite el préstamo: cuando
     * N_RENOVACION_NUM == N_MAX_RENOVACIONES, el préstamo no se puede renovar
     */
    private Long                 maxRenovaciones;

    /**
     * Días máximos de suspensión por ejemplar (dmsu en Ab*NET).<br>
     * <br>
     * El significado de esta propiedad es por ejemplar: máximo número de días
     * por ejemplar que va a durar la suspensión.<br>
     * Por ejemplo, si un lector se retrasa en el préstamo de 2 ejemplares, y el
     * retraso es de 8 días en ambos, si N_DIAS_SUSPENSION = 1 se aplicaría una
     * suspensión de :<br>
     * <br>
     * para el primer ejemplar de N_DIAS_SUSPENSION x 8 = 8 días<br>
     * para el segundo ejemplar de N_DIAS_SUSPENSION x 8 = 8 días<br>
     * <br>
     * En teoría le correspondería una suspensión de 8 días por cada ejemplar.<br>
     * Pero si suponemos que el valor esta propiedad es 6, entonces la formula
     * para calcular la suspensión real por ejemplar es:<br>
     * <br>
     * min( 8 , N_DIAS_MAX_SUSPENSION= 6 ) = 6 para el primer ejemplar<br>
     * min( 8 , N_DIAS_MAX_SUSPENSION= 6 ) = 6 para el segundo ejemplar<br>
     * Total: 12 días de suspensión<br>
     * <br>
     * Con que la suspensión real a aplicar serían 12 días ( 6 + 6) en lugar de
     * 16.
     */
    private Long                 maxDiasSuspensionEjemplar;

    /**
     * Días antes de suspensión.<br>
     * Número de días (de gracia) posteriores a la fecha de devolución prevista,
     * antes<br>
     * de que se haga efectiva la sanción de suspensión. Si el lector realiza la<br>
     * devolución con retraso pero dentro de este periodo, no se le aplica
     * sanción.<br>
     * <br>
     * Un valor 0 para este campo significa que la suspensión se aplica en
     * cuanto<br>
     * exista un retraso en la devolución, por pequeño que sea, sin conceder
     * días de<br>
     * gracia.
     */
    private Long                 diasAntesSuspension;

    /**
     * Texto libre introducido por el bibliotecario para recoger cualquier tipo
     * de comentario en la entrega de matriales no retornables al lector.<br>
     * Este campo no existe en CIR_PRESTAMOS porque los suministros de
     * materiales no retornables vienen directamente a esta tabla como préstamos
     * devueltos
     */
    private String               notaSuministroNoRetornables;

    /**
     * Fecha hora en que se ha generado la carta de reclamación o se ha enviado
     * por email
     */
    private Date                 fechaReclamacion1a;

    /**
     * Fecha hora en que se ha generado la carta de reclamación o se ha enviado
     * por email
     */
    private Date                 fechaReclamacion2a;

    /**
     * Fecha hora en que se ha generado la carta de reclamación o se ha enviado
     * por email
     */
    private Date                 fechaReclamacion3a;

    /**
     * Fecha en que se ha detectado la suspensión del lector, por el proceso
     * planificado correspondiente
     */
    private Date                 fechaDetectaSuspension;

    /**
     * Usuario bibliotecario que realiza el préstamo. Registrado en nuestra
     * aplicacion<br>
     * No tiene equivalente en las tablas de Ab*NET (esa aplicación no recoge
     * este dato).<br>
     * Si el bibliotecario no es de una de las bibliotecas registradas en
     * BIBLIOTECAS (caso del préstamo en red), no es una clave ajena a la tabla
     * USUARIOS.<br>
     * Si el bibliotecario es de una de las bibliotecas registradas en
     * BIBLIOTECAS, es clave ajena a la tabla USUARIOS.<br>
     * En ambos casos se asume que la identificación de un bibliotecario es
     * única en su biblioteca.
     */
    private Usuario              usuarioBibliotecario;

    /**
     * Usuario bibliotecario que realiza el préstamo. No registrado en nuestra
     * aplicacion<br>
     * No tiene equivalente en las tablas de Ab*NET (esa aplicación no recoge
     * este dato).<br>
     * Si el bibliotecario no es de una de las bibliotecas registradas en
     * BIBLIOTECAS (caso del préstamo en red), no es una clave ajena a la tabla
     * USUARIOS.<br>
     * Si el bibliotecario es de una de las bibliotecas registradas en
     * BIBLIOTECAS, es clave ajena a la tabla USUARIOS.<br>
     * En ambos casos se asume que la identificación de un bibliotecario es
     * única en su biblioteca.
     */
    private String               usuarioBibliotecarioExtId;

    /**
     * BIblioteca en la que realizó la última renovación del préstamo.<br>
     * Si es un préstamo en red, es una clave ajena a la tabla
     * CONSORCIO_BIBLIOTECAS.<br>
     * Si es un préstamo de otro tipo, es clave ajena a la tabla BIBLIOTECA.
     */
    private Biblioteca           bibliotecaRenovacion;

    /**
     * Biblioteca en la que se realizó la devolución del préstamo.<br>
     * Los préstamos en red pueden devolverse en otra biblioteca del consorcio
     * distinta a aquella en la que se realizó el préstamo.
     */
    private Biblioteca           bibliotecaDevolucion;

    /**
     * Identificación del lector en su biblioteca. Este valor debe ser único
     * para cada lector en su biblioteca.<br>
     * Si el lector es de una de las bibliotecas registradas en BIBLIOTECAS, es
     * una clave ajena a LECTORES<br>
     * Si el lector es de otra biblioteca no registrada en esta BD (caso del
     * préstamo en red), no es clave ajena.
     */
    private Lector               lector;

    /**
     * Identificación del lector en su biblioteca. Este valor debe ser único
     * para cada lector en su biblioteca.<br>
     * Si el lector es de una de las bibliotecas registradas en BIBLIOTECAS, es
     * una clave ajena a LECTORES<br>
     * Si el lector es de otra biblioteca no registrada en esta BD (caso del
     * préstamo en red), no es clave ajena. Elemento de dato Unique User Id.User
     * Identifier Value recibido en Athenticate User Response
     */
    private String               lectorExtId;

    /**
     * Clave ajena a LECTORES_TIPOS<br>
     * Tipo del lector que realiza el préstamo. Sólo significativo para las
     * modalidades de préstamo a domicilio, y en sala.
     */
    private TipoLector           tipoLector;

    /**
     * >> EJEMPLARES_ESTADOS<br>
     * Estado físico (= de conservación) del ejemplar en la devolución
     */
    private EjemplarEstado       ejemplarEstadoDevolucion;

    /**
     * >> EJEMPLARES_LOCALIZACIONES<br>
     * Para los préstamos en sala o a adomicilio, mismo valor que
     * EJE_LOC_X_EJEMPLAR_LOCALIZACION, la localización por defecto del ejemplar
     * en la biblioteca.<br>
     * Para las devoluciones de préstamos en red, este valor se copia del
     * parámetro CONSORCIOS_BIBLIOTECAS.EJE_LOC_X_EJEMPLARES_LOCALIZACIONES
     */
    private EjemplarLocalizacion ejemplarLocalizacionDevolucion;

    // ============= prestamo en red consorciado ============

    /**
     *Clave ajena a CONSORCIOS Consorcio con cuya aplicación APRC se realizó el
     * préstamo
     */
    private Consorcio            consorcio;

    /**
     * Clave ajena a CONSORCIOS_BIBLIOTECAS Biblioteca propietaria del ejmeplar
     * que se presta. Elemento de dato Unique Item Id.Item Unique Agency Id que
     * se envía en Check Out Item y Checked Out Item Indica la biblioteca
     * consorciada, propietaria del ejemplar T_EJEMPLAR_ID a que se refiere el
     * préstamo en red.
     */
    private BibliotecaConsorcio  bibliotecaConsorcioEjemplar;

    /**
     * Clave ajena a CONSORCIOS_BIBLIOTECAS Indica la biblioteca en la que se
     * realizó la última renovación del préstamo
     */
    private BibliotecaConsorcio  bibliotecaConsorcioRenovacion;

    /**
     * Clave ajena a CONSORCIOS_BIBLIOTECAS Biblioteca en la que se realizó la
     * devolución del préstamo. Los préstamos en red pueden devolverse en otra
     * biblioteca del consorcio distinta a aquella en la que se realizó el
     * préstamo.
     */
    private BibliotecaConsorcio  bibliotecaConsorcioDevolucion;

    /**
     * Clave ajena a CONSORCIOS_BIBLIOTECAS Biblioteca o sucursal del lector que
     * realiza el préstamo. Elemento de dato Unique User Id.Unique Agency Id
     * recibido en Athenticate User Response
     */
    private BibliotecaConsorcio  bibliotecaConsorcioLector;

    /**
     * Biblioteca que realiza el préstamo
     */
    private Biblioteca           bibliotecaPrestamista;

    /**
     *1 si existe el elemento de dato Item Optional Fields.Sensitization Flag
     * en el mensaje de respuesta Lookup Item Response 0 en casoi contrario
     */
    private Boolean              sensitizationFlagEj;

    /**
     * Cadena XML correspondiente al elemento de dato Item Optional
     * Fields.Bibliographic Description, que se recibe en el mensaje Lookup Item
     * Response
     */
    private String               ejemBiblioDesc;

    /**
     * Elemento de dato Physical Condition.Physical Condition Details recibido
     * en el mensaje Lookup Item Response
     */
    public String                ejemEstadoDetalle;

    /**
     * Elemento de dato Location.Valid From Date, recibido en el mensaje Lookup
     * Item Response
     */
    public String                ejemLocDesde;

    /**
     * Elemento de dato Location.Valid To Date, recibido en el mensaje Lookup
     * Item Response
     */
    public String                ejemLocHasta;

    /**
     * Elemento de dato Location.Location Name.Location Name Instance.Location
     * Name Value (con el Level mas alto), recibido en el mensaje Lookup Item
     * Response
     */
    public String                ejemLocNombre;

    /**
     *Elemento de dato Item Optional Fields.Circulation Status.Scheme que se
     * recibe en el mensaje Lookup Item Response
     */
    public String                ejemSchemeCirStatus;

    /**
     * Elemento de dato Location.Location Type.Scheme, recibido en el mensaje
     * Lookup Item Response
     */
    public String                ejemSchemeLocTipo;

    /**
     *Elemento de dato Item Optional Fields.Security Marker.Scheme, recibido en
     * el mensaje Lookup Item Response
     */
    public String                ejemSchemeMarcaSeg;

    /**
     *Elemento de dato Item Optional Fields.Item Use Restriction Type.Scheme
     * del mensaje Lookup Item Response
     */
    public String                ejemSchemeRestType;

    /**
     * Elemento de dato Item Optional Fields.Circulation Status.Value que se
     * recibe en el mensaje Lookup Item Response
     */
    public String                ejemValueCirStatus;

    /**
     * Elemento de dato Location.Location Type.Value, recibido en el mensaje
     * Lookup Item Response
     */
    public String                ejemValueLocTipo;

    /**
     *Elemento de dato Item Optional Fields.Security Marker.Value, recibido en
     * el mensaje Lookup Item Response
     */
    public String                ejemValueMarcaSeg;

    /**
     * Elemento de dato Item Optional Fields.Item Use Restriction Type.Value del
     * mensaje Lookup Item Response
     */
    public String                ejemValueRestrType;

    /**
     * Fecha de inicio de una suspensión
     */
    private Date                 fechaIniSuspension;

    /**
     * Fecha de fin de una suspensión
     */
    private Date                 fechaFinSuspension;

    protected PrestamoHistorico() {
        super();
    }

    public PrestamoHistorico(final Ejemplar ejemplar) {
        super();
        this.ejemplar = ejemplar;
    }

    public PrestamoHistorico(final Lector lector, final Ejemplar ejemplar) {
        super();
        this.lector = lector;
        this.ejemplar = ejemplar;
    }

    public PrestamoHistorico(final String lectorExtId,
            final String ejemplarExtId) {
        super();
        this.lectorExtId = lectorExtId;
        this.ejemplarExtId = ejemplarExtId;
    }

    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_BIBLIOTECA_PRESTAMISTA, nullable = true)
    @ForeignKey(name = "FK_BIB_X_BIB_PRESTAM_hist")
    public Biblioteca getBibliotecaPrestamista() {
        return bibliotecaPrestamista;
    }

    public void setBibliotecaPrestamista(Biblioteca bibliotecaPrestamista) {
        this.bibliotecaPrestamista = bibliotecaPrestamista;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = PrestamoHistorico.COLUMN_NAME_ID)
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
     * @return the tipoPrestamo
     */
    @ManyToOne(targetEntity = TipoPrestamo.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_TIPO_PRESTAMO)
    public TipoPrestamo getTipoPrestamo() {
        return tipoPrestamo;
    }

    /**
     * @param tipoPrestamo
     *            the tipoPrestamo to set
     */
    public void setTipoPrestamo(final TipoPrestamo tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    /**
     * @return the ejemplarExtId
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_EXT_ID)
    public String getEjemplarExtId() {
        return ejemplarExtId;
    }

    /**
     * @param ejemplarExtId
     *            the ejemplarExtId to set
     */
    public void setEjemplarExtId(final String ejemplarExtId) {
        this.ejemplarExtId = ejemplarExtId;
    }

    /**
     * @return the ejemplar
     */
    @ManyToOne(targetEntity = Ejemplar.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR)
    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    /**
     * @param ejemplar
     *            the ejemplar to set
     */
    public void setEjemplar(final Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    /**
     * @return the fechaPrestamo
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_PRESTAMO)
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * @param fechaPrestamo
     *            the fechaPrestamo to set
     */
    public void setFechaPrestamo(final Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * @return the fechaDevolucionPol
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_DEVOLUCION_POL)
    public Date getFechaDevolucionPol() {
        return fechaDevolucionPol;
    }

    /**
     * @param fechaDevolucionPol
     *            the fechaDevolucionPol to set
     */
    public void setFechaDevolucionPol(final Date fechaDevolucionPol) {
        this.fechaDevolucionPol = fechaDevolucionPol;
    }

    /**
     * @return the periodoIndeterminado
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_PERIODO_INDETERMINADO)
    public Boolean getPeriodoIndeterminado() {
        return periodoIndeterminado;
    }

    /**
     * @param periodoIndeterminado
     *            the periodoIndeterminado to set
     */
    public void setPeriodoIndeterminado(final Boolean periodoIndeterminado) {
        this.periodoIndeterminado = periodoIndeterminado;
    }

    /**
     * @return the diasAntesSuspension
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_DIAS_ANTES_SUSPENSION)
    public Long getDiasAntesSuspension() {
        return diasAntesSuspension;
    }

    /**
     * @param diasAntesSuspension
     *            the diasAntesSuspension to set
     */
    public void setDiasAntesSuspension(final Long diasAntesSuspension) {
        this.diasAntesSuspension = diasAntesSuspension;
    }

    /**
     * @return the diasSuspension
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_DIAS_SUSPENSION)
    public Long getDiasSuspension() {
        return diasSuspension;
    }

    /**
     * @param diasSuspension
     *            the diasSuspension to set
     */
    public void setDiasSuspension(final Long diasSuspension) {
        this.diasSuspension = diasSuspension;
    }

    /**
     * @return the fechaRenovacion
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_RENOVACION)
    public Date getFechaRenovacion() {
        return fechaRenovacion;
    }

    /**
     * @param fechaRenovacion
     *            the fechaRenovacion to set
     */
    public void setFechaRenovacion(final Date fechaRenovacion) {
        this.fechaRenovacion = fechaRenovacion;
    }

    /**
     * @return the renovacionNum
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_RENOVACION_NUM)
    public Long getRenovacionNum() {
        return renovacionNum;
    }

    /**
     * @param renovacionNum
     *            the renovacionNum to set
     */
    public void setRenovacionNum(final Long renovacionNum) {
        this.renovacionNum = renovacionNum;
    }

    /**
     * @return the diasRenovacion
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_DIAS_RENOVACION)
    public Long getDiasRenovacion() {
        return diasRenovacion;
    }

    /**
     * @param diasRenovacion
     *            the diasRenovacion to set
     */
    public void setDiasRenovacion(final Long diasRenovacion) {
        this.diasRenovacion = diasRenovacion;
    }

    /**
     * @return the diasAntesFinal
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_DIAS_ANTES_FINAL)
    public Long getDiasAntesFinal() {
        return diasAntesFinal;
    }

    /**
     * @param diasAntesFinal
     *            the diasAntesFinal to set
     */
    public void setDiasAntesFinal(final Long diasAntesFinal) {
        this.diasAntesFinal = diasAntesFinal;
    }

    /**
     * @return the reclamacionNum
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_RECLAMACION_NUM)
    public Long getReclamacionNum() {
        return reclamacionNum;
    }

    /**
     * @param reclamacionNum
     *            the reclamacionNum to set
     */
    public void setReclamacionNum(final Long reclamacionNum) {
        this.reclamacionNum = reclamacionNum;
    }

    /**
     * @return the diasReclamacion1a
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_DIAS_RECLAMACION_1A)
    public Long getDiasReclamacion1a() {
        return diasReclamacion1a;
    }

    /**
     * @param diasReclamacion1a
     *            the diasReclamacion1a to set
     */
    public void setDiasReclamacion1a(final Long diasReclamacion1a) {
        this.diasReclamacion1a = diasReclamacion1a;
    }

    /**
     * @return the diasReclamacion2a
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_DIAS_RECLAMACION_2A)
    public Long getDiasReclamacion2a() {
        return diasReclamacion2a;
    }

    /**
     * @param diasReclamacion2a
     *            the diasReclamacion2a to set
     */
    public void setDiasReclamacion2a(final Long diasReclamacion2a) {
        this.diasReclamacion2a = diasReclamacion2a;
    }

    /**
     * @return the diasReclamacion3a
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_DIAS_RECLAMACION_3A)
    public Long getDiasReclamacion3a() {
        return diasReclamacion3a;
    }

    /**
     * @param diasReclamacion3a
     *            the diasReclamacion3a to set
     */
    public void setDiasReclamacion3a(final Long diasReclamacion3a) {
        this.diasReclamacion3a = diasReclamacion3a;
    }

    /**
     * @return the ejemplarAutor
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_AUTOR)
    public String getEjemplarAutor() {
        return ejemplarAutor;
    }

    /**
     * @param ejemplarAutor
     *            the ejemplarAutor to set
     */
    public void setEjemplarAutor(final String ejemplarAutor) {
        this.ejemplarAutor = ejemplarAutor;
    }

    /**
     * @return the ejemplarAutorDeComponente
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_AUTOR_DE_COMPONENTE)
    public String getEjemplarAutorDeComponente() {
        return ejemplarAutorDeComponente;
    }

    /**
     * @param ejemplarAutorDeComponente
     *            the ejemplarAutorDeComponente to set
     */
    public void setEjemplarAutorDeComponente(
            final String ejemplarAutorDeComponente) {
        this.ejemplarAutorDeComponente = ejemplarAutorDeComponente;
    }

    /**
     * @return the ejemplarCodIdentificadorRegistro
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_COD_IDENTIFICAD_REG)
    public String getEjemplarCodIdentificadorRegistro() {
        return ejemplarCodIdentificadorRegistro;
    }

    /**
     * @param ejemplarCodIdentificadorRegistro
     *            the ejemplarCodIdentificadorRegistro to set
     */
    public void setEjemplarCodIdentificadorRegistro(
            final String ejemplarCodIdentificadorRegistro) {
        this.ejemplarCodIdentificadorRegistro = ejemplarCodIdentificadorRegistro;
    }

    /**
     * @return the ejemplarIdentificadorRegistro
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_IDENTIFICADOR_REGIS)
    public String getEjemplarIdentificadorRegistro() {
        return ejemplarIdentificadorRegistro;
    }

    /**
     * @param ejemplarIdentificadorRegistro
     *            the ejemplarIdentificadorRegistro to set
     */
    public void setEjemplarIdentificadorRegistro(
            final String ejemplarIdentificadorRegistro) {
        this.ejemplarIdentificadorRegistro = ejemplarIdentificadorRegistro;
    }

    /**
     * @return the ejemplarBibliographicLevel
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_BIBLIOGRAPHIC_LEVEL)
    public String getEjemplarBibliographicLevel() {
        return ejemplarBibliographicLevel;
    }

    /**
     * @param ejemplarBibliographicLevel
     *            the ejemplarBibliographicLevel to set
     */
    public void setEjemplarBibliographicLevel(
            final String ejemplarBibliographicLevel) {
        this.ejemplarBibliographicLevel = ejemplarBibliographicLevel;
    }

    /**
     * @return the ejemplarComponenteId
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_COMPONENTE_ID)
    public String getEjemplarComponenteId() {
        return ejemplarComponenteId;
    }

    /**
     * @param ejemplarComponenteId
     *            the ejemplarComponenteId to set
     */
    public void setEjemplarComponenteId(final String ejemplarComponenteId) {
        this.ejemplarComponenteId = ejemplarComponenteId;
    }

    /**
     * @return the ejemplarComponenteIdTipo
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_COMPONENTE_ID_TIPO)
    public String getEjemplarComponenteIdTipo() {
        return ejemplarComponenteIdTipo;
    }

    /**
     * @param ejemplarComponenteIdTipo
     *            the ejemplarComponenteIdTipo to set
     */
    public void setEjemplarComponenteIdTipo(
            final String ejemplarComponenteIdTipo) {
        this.ejemplarComponenteIdTipo = ejemplarComponenteIdTipo;
    }

    /**
     * @return the ejemplarEdicion
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_EDICION)
    public String getEjemplarEdicion() {
        return ejemplarEdicion;
    }

    /**
     * @param ejemplarEdicion
     *            the ejemplarEdicion to set
     */
    public void setEjemplarEdicion(final String ejemplarEdicion) {
        this.ejemplarEdicion = ejemplarEdicion;
    }

    /**
     * @return the ejemplarIdioma
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_IDIOMA)
    public String getEjemplarIdioma() {
        return ejemplarIdioma;
    }

    /**
     * @param ejemplarIdioma
     *            the ejemplarIdioma to set
     */
    public void setEjemplarIdioma(final String ejemplarIdioma) {
        this.ejemplarIdioma = ejemplarIdioma;
    }

    /**
     * @return the ejemplarFechaPublicacion
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_FECHA_PUBLICACION)
    public String getEjemplarFechaPublicacion() {
        return ejemplarFechaPublicacion;
    }

    /**
     * @param ejemplarFechaPublicacion
     *            the ejemplarFechaPublicacion to set
     */
    public void setEjemplarFechaPublicacion(
            final String ejemplarFechaPublicacion) {
        this.ejemplarFechaPublicacion = ejemplarFechaPublicacion;
    }

    /**
     * @return the ejemplarEditor
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_EDITOR)
    public String getEjemplarEditor() {
        return ejemplarEditor;
    }

    /**
     * @param ejemplarEditor
     *            the ejemplarEditor to set
     */
    public void setEjemplarEditor(final String ejemplarEditor) {
        this.ejemplarEditor = ejemplarEditor;
    }

    /**
     * @return the ejemplarTitulo
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_TITULO)
    public String getEjemplarTitulo() {
        return ejemplarTitulo;
    }

    /**
     * @param ejemplarTitulo
     *            the ejemplarTitulo to set
     */
    public void setEjemplarTitulo(final String ejemplarTitulo) {
        this.ejemplarTitulo = ejemplarTitulo;
    }

    /**
     * @return the ejemplarTituloComponente
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_TITULO_COMPONENTE)
    public String getEjemplarTituloComponente() {
        return ejemplarTituloComponente;
    }

    /**
     * @param ejemplarTituloComponente
     *            the ejemplarTituloComponente to set
     */
    public void setEjemplarTituloComponente(
            final String ejemplarTituloComponente) {
        this.ejemplarTituloComponente = ejemplarTituloComponente;
    }

    /**
     * @return the fechaDevolucion
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_DEVOLUCION)
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * @param fechaDevolucion
     *            the fechaDevolucion to set
     */
    public void setFechaDevolucion(final Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * @return the maxRenovaciones
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_MAX_RENOVACIONES)
    public Long getMaxRenovaciones() {
        return maxRenovaciones;
    }

    /**
     * @param maxRenovaciones
     *            the maxRenovaciones to set
     */
    public void setMaxRenovaciones(final Long maxRenovaciones) {
        this.maxRenovaciones = maxRenovaciones;
    }

    /**
     * @return the maxDiasSuspensionEjemplar
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_MAX_DIAS_SUSPENSION_EJEMPLAR)
    public Long getMaxDiasSuspensionEjemplar() {
        return maxDiasSuspensionEjemplar;
    }

    /**
     * @param maxDiasSuspensionEjemplar
     *            the maxDiasSuspensionEjemplar to set
     */
    public void setMaxDiasSuspensionEjemplar(
            final Long maxDiasSuspensionEjemplar) {
        this.maxDiasSuspensionEjemplar = maxDiasSuspensionEjemplar;
    }

    /**
     * @return the notaSuministroNoRetornables
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_NOTA_SUMINISTRO_NO_RETORNABL)
    public String getNotaSuministroNoRetornables() {
        return notaSuministroNoRetornables;
    }

    /**
     * @param notaSuministroNoRetornables
     *            the notaSuministroNoRetornables to set
     */
    public void setNotaSuministroNoRetornables(
            final String notaSuministroNoRetornables) {
        this.notaSuministroNoRetornables = notaSuministroNoRetornables;
    }

    /**
     * @return the fechaReclamacion1a
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_RECLAMACION_1A)
    public Date getFechaReclamacion1a() {
        return fechaReclamacion1a;
    }

    /**
     * @param fechaReclamacion1a
     *            the fechaReclamacion1a to set
     */
    public void setFechaReclamacion1a(final Date fechaReclamacion1a) {
        this.fechaReclamacion1a = fechaReclamacion1a;
    }

    /**
     * @return the fechaReclamacion2a
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_RECLAMACION_2A)
    public Date getFechaReclamacion2a() {
        return fechaReclamacion2a;
    }

    /**
     * @param fechaReclamacion2a
     *            the fechaReclamacion2a to set
     */
    public void setFechaReclamacion2a(final Date fechaReclamacion2a) {
        this.fechaReclamacion2a = fechaReclamacion2a;
    }

    /**
     * @return the fechaReclamacion3a
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_RECLAMACION_3A)
    public Date getFechaReclamacion3a() {
        return fechaReclamacion3a;
    }

    /**
     * @param fechaReclamacion3a
     *            the fechaReclamacion3a to set
     */
    public void setFechaReclamacion3a(final Date fechaReclamacion3a) {
        this.fechaReclamacion3a = fechaReclamacion3a;
    }

    /**
     * @return the fechaDetectaSuspension
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_DETECTA_SUSPENSION)
    public Date getFechaDetectaSuspension() {
        return fechaDetectaSuspension;
    }

    /**
     * @param fechaDetectaSuspension
     *            the fechaDetectaSuspension to set
     */
    public void setFechaDetectaSuspension(final Date fechaDetectaSuspension) {
        this.fechaDetectaSuspension = fechaDetectaSuspension;
    }

    /**
     * @return the usuarioBibliotecario
     */
    @ManyToOne(targetEntity = Usuario.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_USUARIO_BIBLIOTECARIO)
    public Usuario getUsuarioBibliotecario() {
        return usuarioBibliotecario;
    }

    /**
     * @param usuarioBibliotecario
     *            the usuarioBibliotecario to set
     */
    public void setUsuarioBibliotecario(final Usuario usuarioBibliotecario) {
        this.usuarioBibliotecario = usuarioBibliotecario;
    }

    /**
     * @return the usuarioBibliotecarioExtId
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_USUARIO_BIBLIOTECARIO_EXT, length = 80)
    public String getUsuarioBibliotecarioExtId() {
        return usuarioBibliotecarioExtId;
    }

    /**
     * @param usuarioBibliotecarioExtId
     *            the usuarioBibliotecarioExtId to set
     */
    public void setUsuarioBibliotecarioExtId(
            final String usuarioBibliotecarioExtId) {
        this.usuarioBibliotecarioExtId = usuarioBibliotecarioExtId;
    }

    /**
     * @return the bibliotecaConsorcioEjemplar
     */
    @ManyToOne(targetEntity = BibliotecaConsorcio.class, cascade = {
            CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_BIBLIOTECA_CONSORCIO_EJE)
    public BibliotecaConsorcio getBibliotecaConsorcioEjemplar() {
        return bibliotecaConsorcioEjemplar;
    }

    /**
     * @param bibliotecaConsorcioEjemplar
     *            the bibliotecaConsorcioEjemplar to set
     */
    public void setBibliotecaConsorcioEjemplar(
            final BibliotecaConsorcio bibliotecaConsorcioEjemplar) {
        this.bibliotecaConsorcioEjemplar = bibliotecaConsorcioEjemplar;
    }

    /**
     * @return the bibliotecaRenovacion
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_BIBLIOTECA_RENOVACION)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_PR_H_REN")
    public Biblioteca getBibliotecaRenovacion() {
        return bibliotecaRenovacion;
    }

    /**
     * @param bibliotecaRenovacion
     *            the bibliotecaRenovacion to set
     */
    public void setBibliotecaRenovacion(final Biblioteca bibliotecaRenovacion) {
        this.bibliotecaRenovacion = bibliotecaRenovacion;
    }

    /**
     * @return the bibliotecaConsorcioRenovacion
     */
    @ManyToOne(targetEntity = BibliotecaConsorcio.class, cascade = {
            CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_BIBLIOTECA_CONSORCIO_RENOVA)
    public BibliotecaConsorcio getBibliotecaConsorcioRenovacion() {
        return bibliotecaConsorcioRenovacion;
    }

    /**
     * @param bibliotecaConsorcioRenovacion
     *            the bibliotecaConsorcioRenovacion to set
     */
    public void setBibliotecaConsorcioRenovacion(
            final BibliotecaConsorcio bibliotecaConsorcioRenovacion) {
        this.bibliotecaConsorcioRenovacion = bibliotecaConsorcioRenovacion;
    }

    /**
     * @return the bibliotecaDevolucion
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_BIBLIOTECA_DEVOLUCION)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_PR_H_DEV")
    public Biblioteca getBibliotecaDevolucion() {
        return bibliotecaDevolucion;
    }

    /**
     * @param bibliotecaDevolucion
     *            the bibliotecaDevolucion to set
     */
    public void setBibliotecaDevolucion(final Biblioteca bibliotecaDevolucion) {
        this.bibliotecaDevolucion = bibliotecaDevolucion;
    }

    /**
     * @return the bibliotecaConsorcioDevolucion
     */
    @ManyToOne(targetEntity = BibliotecaConsorcio.class, cascade = {
            CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_BIBLIOTECA_CONSORCIO_DEVOLUC)
    public BibliotecaConsorcio getBibliotecaConsorcioDevolucion() {
        return bibliotecaConsorcioDevolucion;
    }

    /**
     * @param bibliotecaConsorcioDevolucion
     *            the bibliotecaConsorcioDevolucion to set
     */
    public void setBibliotecaConsorcioDevolucion(
            final BibliotecaConsorcio bibliotecaConsorcioDevolucion) {
        this.bibliotecaConsorcioDevolucion = bibliotecaConsorcioDevolucion;
    }

    /**
     * @return the bibliotecaConsorcioLector
     */
    @ManyToOne(targetEntity = BibliotecaConsorcio.class, cascade = {
            CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_BIBLIOTECA_CONSORCIO_LECTOR)
    public BibliotecaConsorcio getBibliotecaConsorcioLector() {
        return bibliotecaConsorcioLector;
    }

    /**
     * @param bibliotecaConsorcioLector
     *            the bibliotecaConsorcioLector to set
     */
    public void setBibliotecaConsorcioLector(
            final BibliotecaConsorcio bibliotecaConsorcioLector) {
        this.bibliotecaConsorcioLector = bibliotecaConsorcioLector;
    }

    /**
     * @return the lector
     */
    @ManyToOne(targetEntity = Lector.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_LECTOR)
    public Lector getLector() {
        return lector;
    }

    /**
     * @param lector
     *            the lector to set
     */
    public void setLector(final Lector lector) {
        this.lector = lector;
    }

    /**
     * @return the lectorExtId
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_LECTOR_EXT_ID)
    public String getLectorExtId() {
        return lectorExtId;
    }

    /**
     * @param lectorExtId
     *            the lectorExtId to set
     */
    public void setLectorExtId(final String lectorExtId) {
        this.lectorExtId = lectorExtId;
    }

    /**
     * @return the lectorTipo
     */
    @ManyToOne(targetEntity = TipoLector.class, cascade = {
            CascadeType.PERSIST})
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_TIPO_LECTOR)
    public TipoLector getTipoLector() {
        return tipoLector;
    }

    /**
     * @param lectorTipo
     *            the lectorTipo to set
     */
    public void setTipoLector(final TipoLector tipoLector) {
        this.tipoLector = tipoLector;
    }

    /**
     * @return the ejemplarEstadoDevolucion
     */
    @ManyToOne(targetEntity = EjemplarEstado.class, cascade = {
            CascadeType.PERSIST })
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_ESTADO_DEVOLUCION)
    public EjemplarEstado getEjemplarEstadoDevolucion() {
        return ejemplarEstadoDevolucion;
    }

    /**
     * @param ejemplarEstadoDevolucion
     *            the ejemplarEstadoDevolucion to set
     */
    public void setEjemplarEstadoDevolucion(
            final EjemplarEstado ejemplarEstadoDevolucion) {
        this.ejemplarEstadoDevolucion = ejemplarEstadoDevolucion;
    }

    /**
     * @return the ejemplarLocalizacionDevolucion
     */
    @ManyToOne(targetEntity = EjemplarLocalizacion.class, cascade = {
            CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_EJEMPLAR_LOCALIZACION_DEVOLU)
    public EjemplarLocalizacion getEjemplarLocalizacionDevolucion() {
        return ejemplarLocalizacionDevolucion;
    }

    /**
     * @param ejemplarLocalizacionDevolucion
     *            the ejemplarLocalizacionDevolucion to set
     */
    public void setEjemplarLocalizacionDevolucion(
            final EjemplarLocalizacion ejemplarLocalizacionDevolucion) {
        this.ejemplarLocalizacionDevolucion = ejemplarLocalizacionDevolucion;
    }

    /**
     * @return the consorcio
     */
    @ManyToOne(targetEntity = Consorcio.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = PrestamoHistorico.COLUMN_NAME_CONSORCIO, nullable = true)
    public Consorcio getConsorcio() {
        return consorcio;
    }

    /**
     * @param consorcio
     *            the consorcio to set
     */
    public void setConsorcio(Consorcio consorcio) {
        this.consorcio = consorcio;
    }

    /**
     * @return the sensitizationFlagEj
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_SENSITIZATION_FLAG_EJ)
    public Boolean getSensitizationFlagEj() {
        return sensitizationFlagEj;
    }

    /**
     * @param sensitizationFlagEj
     *            the sensitizationFlagEj to set
     */
    public void setSensitizationFlagEj(Boolean sensitizationFlagEj) {
        this.sensitizationFlagEj = sensitizationFlagEj;
    }

    /**
     * @return the ejemBiblioDesc
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJE_BIBLIOGRAPHIC_DESC)
    public String getEjemBiblioDesc() {
        return ejemBiblioDesc;
    }

    /**
     * @param ejemBiblioDesc
     *            the ejemBiblioDesc to set
     */
    public void setEjemBiblioDesc(String ejemBiblioDesc) {
        this.ejemBiblioDesc = ejemBiblioDesc;
    }

    /**
     * @return the ejemBiblioDesc
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEMPLAR_ESTADO_DETALLE)
    public String getEjemEstadoDetalle() {
        return ejemEstadoDetalle;
    }

    /**
     * @param ejemEstadoDetalle
     *            the ejemEstadoDetalle to set
     */
    public void setEjemEstadoDetalle(String ejemEstadoDetalle) {
        this.ejemEstadoDetalle = ejemEstadoDetalle;
    }

    /**
     * @return the ejemLocDesde
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEMPLAR_LOC_DESDE)
    public String getEjemLocDesde() {
        return ejemLocDesde;
    }

    /**
     * @param ejemLocDesde
     *            the ejemLocDesde to set
     */
    public void setEjemLocDesde(String ejemLocDesde) {
        this.ejemLocDesde = ejemLocDesde;
    }

    /**
     * @return the ejemLocDesde
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEMPLAR_LOC_HASTA)
    public String getEjemLocHasta() {
        return ejemLocHasta;
    }

    /**
     * @param ejemLocHasta
     *            the ejemLocHasta to set
     */
    public void setEjemLocHasta(String ejemLocHasta) {
        this.ejemLocHasta = ejemLocHasta;
    }

    /**
     * @return the ejemLocDesde
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEMPLAR_LOC_NOMBRE)
    public String getEjemLocNombre() {
        return ejemLocNombre;
    }

    /**
     * @param ejemLocNombre
     *            the ejemLocNombre to set
     */
    public void setEjemLocNombre(String ejemLocNombre) {
        this.ejemLocNombre = ejemLocNombre;
    }

    /**
     * @return the ejemSchemeCirStatus
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEM_SCHEME_CIR_STATUS)
    public String getEjemSchemeCirStatus() {
        return ejemSchemeCirStatus;
    }

    /**
     * @param ejemSchemeCirStatus
     *            the ejemSchemeCirStatus to set
     */
    public void setEjemSchemeCirStatus(String ejemSchemeCirStatus) {
        this.ejemSchemeCirStatus = ejemSchemeCirStatus;
    }

    /**
     * @return the ejemSchemeLocTipo
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEM_SCHEME_LOC_TIPO)
    public String getEjemSchemeLocTipo() {
        return ejemSchemeLocTipo;
    }

    /**
     * @param ejemSchemeLocTipo
     *            the ejemSchemeLocTipo to set
     */
    public void setEjemSchemeLocTipo(String ejemSchemeLocTipo) {
        this.ejemSchemeLocTipo = ejemSchemeLocTipo;
    }

    /**
     * @return the ejemSchemeMarcaSeg
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEM_SCHEME_MARCA_SEG)
    public String getEjemSchemeMarcaSeg() {
        return ejemSchemeMarcaSeg;
    }

    /**
     * @param ejemSchemeMarcaSeg
     *            the ejemSchemeMarcaSeg to set
     */
    public void setEjemSchemeMarcaSeg(String ejemSchemeMarcaSeg) {
        this.ejemSchemeMarcaSeg = ejemSchemeMarcaSeg;
    }

    /**
     * @return the ejemSchemeRestType
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEM_SCHEME_REST_TYPE)
    public String getEjemSchemeRestType() {
        return ejemSchemeRestType;
    }

    /**
     * @param ejemSchemeRestType
     *            the ejemSchemeRestType to set
     */
    public void setEjemSchemeRestType(String ejemSchemeRestType) {
        this.ejemSchemeRestType = ejemSchemeRestType;
    }

    /**
     * @return the ejemValueCirStatus
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEM_VALUE_CIR_STATUS)
    public String getEjemValueCirStatus() {
        return ejemValueCirStatus;
    }

    /**
     * @param ejemValueCirStatus
     *            the ejemValueCirStatus to set
     */
    public void setEjemValueCirStatus(String ejemValueCirStatus) {
        this.ejemValueCirStatus = ejemValueCirStatus;
    }

    /**
     * @return the ejemValueLocTipo
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEM_VALUE_LOC_TIPO)
    public String getEjemValueLocTipo() {
        return ejemValueLocTipo;
    }

    /**
     * @param ejemValueLocTipo
     *            the ejemValueLocTipo to set
     */
    public void setEjemValueLocTipo(String ejemValueLocTipo) {
        this.ejemValueLocTipo = ejemValueLocTipo;
    }

    /**
     * @return the ejemValueMarcaSeg
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEM_VALUE_MARCA_SEG)
    public String getEjemValueMarcaSeg() {
        return ejemValueMarcaSeg;
    }

    /**
     * @param ejemValueMarcaSeg
     *            the ejemValueMarcaSeg to set
     */
    public void setEjemValueMarcaSeg(String ejemValueMarcaSeg) {
        this.ejemValueMarcaSeg = ejemValueMarcaSeg;
    }

    /**
     * @return the ejemValueRestrType
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_T_EJEM_VALUE_RESTR_TYPE)
    public String getEjemValueRestrType() {
        return ejemValueRestrType;
    }

    /**
     * @param ejemValueRestrType
     *            the ejemValueRestrType to set
     */
    public void setEjemValueRestrType(String ejemValueRestrType) {
        this.ejemValueRestrType = ejemValueRestrType;
    }

    @Transient
    public String getPeriodo() {
        return DateUtil.calcularPeriodoLabel(fechaPrestamo, fechaDevolucionPol);
    }

    /**
     * @return the fechaIniSuspension
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_INICIO_SUSPENSION, nullable = true)
    public Date getFechaIniSuspension() {
        return fechaIniSuspension;
    }

    /**
     * @param fechaIniSuspension
     *            the fechaIniSuspension to set
     */
    public void setFechaIniSuspension(Date fechaIniSuspension) {
        this.fechaIniSuspension = fechaIniSuspension;
    }

    /**
     * @return the fechaFinSuspension
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_FIN_SUSPENSION, nullable = true)
    public Date getFechaFinSuspension() {
        return fechaFinSuspension;
    }

    /**
     * @param fechaFinSuspension
     *            the fechaFinSuspension to set
     */
    public void setFechaFinSuspension(Date fechaFinSuspension) {
        this.fechaFinSuspension = fechaFinSuspension;
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
        if (!(obj instanceof PrestamoHistorico)) {
            return false;
        }

        final PrestamoHistorico other = (PrestamoHistorico) obj;

        if (getEjemplar() == null && other.getEjemplar() != null) {
            return false;
        }
        if (getEjemplar() != null && !getEjemplar().equals(other.getEjemplar())) {
            return false;
        }

        if (getLector() == null && other.getLector() != null) {
            return false;
        }
        if (getLector() != null && !getLector().equals(other.getLector())) {
            return false;
        }

        if (getEjemplarExtId() == null && other.getEjemplarExtId() != null) {
            return false;
        }
        if (getEjemplarExtId() != null
                && !getEjemplarExtId().equals(other.getEjemplarExtId())) {
            return false;
        }

        if (getLectorExtId() == null && other.getLectorExtId() != null) {
            return false;
        }
        if (getLectorExtId() != null
                && !getLectorExtId().equals(other.getLectorExtId())) {
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
                + ((getEjemplar() == null) ? 0 : getEjemplar().hashCode());

        result = prime * result
                + ((getLector() == null) ? 0 : getLector().hashCode());

        result = prime
                * result
                + ((getEjemplarExtId() == null) ? 0 : getEjemplarExtId()
                        .hashCode());

        result = prime
                * result
                + ((getLectorExtId() == null) ? 0 : getLectorExtId().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPTY_NAME_ID, getId())

        .append(PROPTY_NAME_EJEMPLAR,
                (getEjemplar() == null) ? "" : getEjemplar().toString())

        .append(PROPTY_NAME_LECTOR,
                (getLector() == null) ? "" : getLector().toString())

        .append(
                PROPTY_NAME_EJEMPLAR_EXT_ID,
                (getEjemplarExtId() == null) ? "" : getEjemplarExtId()
                        .toString())

        .append(PROPTY_NAME_LECTOR_EXT_ID,
                (getLectorExtId() == null) ? "" : getLectorExtId().toString())

        .toString();
    }
}