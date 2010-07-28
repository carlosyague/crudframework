package org.librae.circulacion.model;

import java.util.Date;

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
import javax.persistence.FetchType;

import org.hibernate.annotations.ForeignKey;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.catalogacion.model.Ejemplar;
import org.librae.catalogacion.model.EjemplarEstado;
import org.librae.catalogacion.model.EjemplarLocalizacion;
import org.librae.catalogacion.model.EjemplarSoporte;
import org.librae.catalogacion.model.EjemplarTipo;
import org.librae.catalogacion.model.Registro;
import org.librae.common.model.parampoliticas.AbstractParamPolPrestamo;
import org.librae.common.util.DateUtil;
import org.librae.lectores.model.Lector;
import org.librae.lectores.model.TipoLector;

/**
 * Préstamos activos.<br>
 * Un préstamo es un para (lector, ejemplar). Si el lector obtiene varios
 * ejemplares en préstamo del mismo registro, son varios préstamos (esta es
 * además la asunción del estándar NCIP), incluso si es un lector institucional.<br>
 * Este pojo también contempla el caso de prestamos en red consorciados:<br>
 * Préstamos activos, realizados en la modalidad de préstamo en red, con la
 * aplicación APRC. Los préstamos en red también se guardan en la aplicación de
 * circulación de las bibliotecas consorciadas, pero dado que las bibliotecas de
 * un consorcio pueden tener diferentes SIGB, no podemos asumir que todos ellos
 * estén en la tabla PRESTAMOS. En el caso de bibliotecas consorciadas que estén
 * registradas en la tabla BIBLIOTECAS, sus préstamos en red activos están tanto
 * en esta tabla como en la tabla PRESTAMOS.
 * 
 * @author asantamaría
 * @author cyague
 */
@Entity(name = Prestamo.ENTITY_NAME)
@Table(name = Prestamo.TABLE_NAME)
public class Prestamo extends AbstractParamPolPrestamo {

    /**
     * BaseObject is Serializable, so Prestamo needs a Serial Version UID
     */
    private static final long     serialVersionUID                                       = 6245974682779963953L;

    public static final String    ENTITY_NAME                                            = "org.librae.circulacion.model.Prestamo";
    public static final String    TABLE_NAME                                             = "CIR_PRESTAMOS";
    public static final String    ID_GENERATOR_NAME                                      = "generator_cir_prestamos";
    private static final String   ID_SEQUENCE_NAME                                       = "SEQ_CIR_PRESTAMOS";
    public static final String    COLUMN_NAME_ID                                         = "X_PRESTAMO";
    public static final String    COLUMN_NAME_TIPO_PRESTAMO                              = "T_PRESTAMO_X_T_PRESTAMO";
    public static final String    COLUMN_NAME_EJEMPLAR_ID                                = "T_EJEMPLAR_ID";
    public static final String    COLUMN_NAME_EJEMPLAR                                   = "EJEMPLAR";
    public static final String    COLUMN_NAME_FECHA_PRESTAMO                             = "F_PRESTAMO";
    public static final String    COLUMN_NAME_DIAS_ANTES_SUSPENSION                      = "N_DIAS_ANTES_SUSPENSION";
    public static final String    COLUMN_NAME_DIAS_SUSPENSION                            = "N_DIAS_SUSPENSION";
    public static final String    COLUMN_NAME_FECHA_DEVOLUCION_POL                       = "F_DEVOLUCION_POL";
    public static final String    COLUMN_NAME_PERIODO_INDETERMINADO                      = "L_PERIODO_INDETERMINADO";
    public static final String    COLUMN_NAME_MAX_DIAS_SUSPENSION_EJE                    = "N_MAX_DIAS_SUSPENSION_EJE";
    public static final String    COLUMN_NAME_DIAS_RECLAMACION_1A                        = "N_DIAS_RECLAMACION_1A";
    public static final String    COLUMN_NAME_DIAS_RECLAMACION_2A                        = "N_DIAS_RECLAMACION_2A";
    public static final String    COLUMN_NAME_DIAS_RECLAMACION_3A                        = "N_DIAS_RECLAMACION_3A";
    public static final String    COLUMN_NAME_FECHA_RENOVACION                           = "F_RENOVACION";
    public static final String    COLUMN_NAME_RENOVACION_NUM                             = "N_RENOVACION_NUM";
    public static final String    COLUMN_NAME_MAX_RENOVACIONES                           = "N_MAX_RENOVACIONES";
    public static final String    COLUMN_NAME_DIAS_RENOVACION                            = "N_DIAS_RENOVACION";
    public static final String    COLUMN_NAME_DIAS_ANTES_FINAL                           = "N_DIAS_ANTES_FINAL";
    public static final String    COLUMN_NAME_RECLAMACION_NUM                            = "N_RECLAMACION_NUM";
    public static final String    COLUMN_NAME_EJE_AUTOR                                  = "T_EJE_AUTOR";
    public static final String    COLUMN_NAME_EJE_AUTOR_DE_COMPONENTE                    = "T_EJE_AUTOR_DE_COMPONENTE";
    public static final String    COLUMN_NAME_EJE_COD_IDENTIFICADOR_REGIST               = "T_EJE_COD_IDENT_REGISTRO";
    public static final String    COLUMN_NAME_EJE_IDENTIFICADOR_REGISTRO                 = "T_EJE_IDENTIFICADOR_REGISTRO";
    public static final String    COLUMN_NAME_EJE_BIBLIOGRAPHIC_LEVEL                    = "T_EJE_BIBLIOGRAPHIC_LEVEL";
    public static final String    COLUMN_NAME_EJE_COMPONENTE_ID                          = "T_EJE_COMPONENTE_ID";
    public static final String    COLUMN_NAME_EJE_COMPONENTE_ID_TIPO                     = "T_EJE_COMPONENTE_ID_TIPO";
    public static final String    COLUMN_NAME_EJE_EDICION                                = "T_EJE_EDICION";
    public static final String    COLUMN_NAME_EJE_IDIOMA                                 = "T_EJE_IDIOMA";
    public static final String    COLUMN_NAME_EJE_FECHA_PUBLICACION                      = "T_EJE_FECHA_PUBLICACION";
    public static final String    COLUMN_NAME_EJE_EDITOR                                 = "T_EJE_EDITOR";
    public static final String    COLUMN_NAME_EJE_TITULO                                 = "T_EJE_TITULO";
    public static final String    COLUMN_NAME_EJE_TITULO_COMPONENTE                      = "T_EJE_TITULO_COMPONENTE";
    public static final String    COLUMN_NAME_FECHA_RECLAMACION_1A                       = "F_RECLAMACION_1A";
    public static final String    COLUMN_NAME_FECHA_RECLAMACION_2A                       = "F_RECLAMACION_2A";
    public static final String    COLUMN_NAME_FECHA_RECLAMACION_3A                       = "F_RECLAMACION_3A";
    public static final String    COLUMN_NAME_BIBLIOTECARIO                              = "USU_X_BIBLIOTECARIO";
    public static final String    COLUMN_NAME_BIBLIOTECA_REN                             = "BIB_X_BIBLIOTECA_REN";
    public static final String    COLUMN_NAME_BIBLIOTECA_PRESTAMISTA                     = "BIB_X_BIBLIOTECA_PRESTAMISTA";
    public static final String    COLUMN_NAME_BIBLIOTECA_CONS                            = "BIB_X_CONSORCIO_BIBLIOTECA";
    public static final String    COLUMN_NAME_LECTOR                                     = "LEC_X_LECTOR";
    public static final String    COLUMN_NAME_LECTOR_TIPO                                = "LEC_TIPO_X_LECTOR_TIPO";
    public static final String    COLUMN_NAME_CONSORCIO                                  = "CON_X_CONSORCIO";
    public static final String    COLUMN_NAME_BIBLIOTECA_CONS_REN                        = "BIB_X_BIBLIOTECA_CONS_REN";
    public static final String    COLUMN_NAME_BIBLIOTECA_CONS_LEC                        = "BIB_X_BIBLIOTECA_CONS_LEC";
    public static final String    COLUMN_NAME_BIBLIOTECA_CONS_EJE                        = "BIB_X_BIBLIOTECA_CONS_EJE";
    public static final String    COLUMN_NAME_SENSITIZATION_FLAG_EJ                      = "L_EJEMPLAR_SENSITIZATION_FLAG";
    public static final String    COLUMN_NAME_T_EJE_BIBLIOGRAPHIC_DESC                   = "T_EJEM_BIBLIOGRAPHIC_DESC";
    public static final String    COLUMN_NAME_T_EJEMPLAR_ESTADO_DETALLE                  = "T_EJEMPLAR_ESTADO_DETALLE";
    public static final String    COLUMN_NAME_T_EJEMPLAR_LOC_DESDE                       = "T_EJEMPLAR_LOC_DESDE";
    public static final String    COLUMN_NAME_T_EJEMPLAR_LOC_HASTA                       = "T_EJEMPLAR_LOC_HASTA";
    public static final String    COLUMN_NAME_T_EJEMPLAR_LOC_NOMBRE                      = "T_EJEMPLAR_LOC_NOMBRE";
    public static final String    COLUMN_NAME_T_EJEM_SCHEME_CIR_STATUS                   = "T_EJEM_SCHEME_CIR_STATUS";
    public static final String    COLUMN_NAME_T_EJEM_SCHEME_LOC_TIPO                     = "T_EJEMPLAR_SCHEME_LOC_TIPO";
    public static final String    COLUMN_NAME_T_EJEM_SCHEME_MARCA_SEG                    = "T_EJEM_SCHEME_MARCA_SEG";
    public static final String    COLUMN_NAME_T_EJEM_SCHEME_REST_TYPE                    = "T_EJEM_SCHEME_REST_TYPE";
    public static final String    COLUMN_NAME_T_EJEM_VALUE_CIR_STATUS                    = "T_EJEM_VALUE_CIR_STATUS";
    public static final String    COLUMN_NAME_T_EJEM_VALUE_LOC_TIPO                      = "T_EJEM_VALUE_LOC_TIPO";
    public static final String    COLUMN_NAME_T_EJEM_VALUE_MARCA_SEG                     = "T_EJEM_VALUE_MARCA_SEG";
    public static final String    COLUMN_NAME_T_EJEM_VALUE_RESTR_TYPE                    = "T_EJEM_VALUE_RESTR_TYPE";
    public static final String    COLUMN_NAME_NCIP_LECTOR                                = "NCIP_LECTOR";
    public static final String    COLUMN_NAME_NCIP_BIB_SOLICITANTE                       = "NCIP_BIB_SOLICITANTE";
    public static final String    COLUMN_NAME_NCIP_BIB_EJEMPLAR                          = "NCIP_BIB_EJEMPLAR";
    public static final String    COLUMN_NAME_NCIP_BIB_LECTOR                            = "NCIP_BIB_LECTOR";

    public static final String    PROPTY_NAME_ID                                         = "id";
    public static final String    PROPTY_NAME_TIPO_PRESTAMO                              = "tipoPrestamo";
    public static final String    PROPTY_NAME_EJEMPLAR_ID                                = "ejemplarId";
    public static final String    PROPTY_NAME_EJEMPLAR                                   = "ejemplar";
    public static final String    PROPTY_NAME_FECHA_PRESTAMO                             = "fechaPrestamo";
    public static final String    PROPTY_NAME_FECHA_DEVOLUCION_POL                       = "fechaDevolucionPol";
    public static final String    PROPTY_NAME_PERIODO_INDETERMINADO                      = "periodoIndeterminado";
    public static final String    PROPTY_NAME_FECHA_RENOVACION                           = "fechaRenovacion";
    public static final String    PROPTY_NAME_RENOVACION_NUM                             = "renovacionNum";
    public static final String    PROPTY_NAME_RECLAMACION_NUM                            = "reclamacionNum";
    public static final String    PROPTY_NAME_EJE_AUTOR                                  = "ejemplarAutor";
    public static final String    PROPTY_NAME_EJE_AUTOR_DE_COMPONENTE                    = "ejemplarAutorDeComponente";
    public static final String    PROPTY_NAME_EJE_COD_IDENTIFICADOR_REGIST               = "ejemplarCodIdentificadorRegistro";
    public static final String    PROPTY_NAME_EJE_IDENTIFICADOR_REGISTRO                 = "ejemplarIdentificadorRegistro";
    public static final String    PROPTY_NAME_EJE_BIBLIOGRAPHIC_LEVEL                    = "ejemplarBibliographicLevel";
    public static final String    PROPTY_NAME_EJE_COMPONENTE_ID                          = "ejemplarComponenteId";
    public static final String    PROPTY_NAME_EJE_COMPONENTE_ID_TIPO                     = "ejemplarComponenteIdTipo";
    public static final String    PROPTY_NAME_EJE_EDICION                                = "ejemplarEdicion";
    public static final String    PROPTY_NAME_EJE_IDIOMA                                 = "ejemplarIdioma";
    public static final String    PROPTY_NAME_EJE_FECHA_PUBLICACION                      = "ejemplarFechaPublicacion";
    public static final String    PROPTY_NAME_EJE_EDITOR                                 = "ejemplarEditor";
    public static final String    PROPTY_NAME_EJE_TITULO                                 = "ejemplarTitulo";
    public static final String    PROPTY_NAME_EJE_TITULO_COMPONENTE                      = "ejemplarTituloComponente";
    public static final String    PROPTY_NAME_FECHA_RECLAMACION_1A                       = "fechaReclamacion1a";
    public static final String    PROPTY_NAME_FECHA_RECLAMACION_2A                       = "fechaReclamacion2a";
    public static final String    PROPTY_NAME_FECHA_RECLAMACION_3A                       = "fechaReclamacion3a";
    public static final String    PROPTY_NAME_BIBLIOTECARIO                              = "usuarioBibliotecario";
    public static final String    PROPTY_NAME_BIBLIOTECA_REN                             = "bibliotecaRenovacion";
    public static final String    PROPTY_NAME_BIBLIOTECA_PRESTAMISTA                     = "bibliotecaPrestamista";
    public static final String    PROPTY_NAME_BIBLIOTECA_CONS                            = "bibliotecaConsorcio";
    public static final String    PROPTY_NAME_LECTOR                                     = "lector";
    public static final String    PROPTY_NAME_LECTOR_TIPO                                = "lectorTipoLectorTipo";
    public static final String    PROPTY_NAME_CONSORCIO                                  = "consorcio";
    public static final String    PROPTY_NAME_BIBLIOTECA_CONS_REN                        = "bibliotecaConsRenovacion";
    public static final String    PROPTY_NAME_BIBLIOTECA_CONS_LEC                        = "bibliotecaConsLector";
    public static final String    PROPTY_NAME_BIBLIOTECA_CONS_EJE                        = "bibliotecaConsEjemplar";
    public static final String    PROPTY_NAME_SENSITIZATION_FLAG_EJ                      = "sensitizationFlagEj";
    public static final String    PROPTY_NAME_T_EJE_BIBLIOGRAPHIC_DESC                   = "ejemBiblioDesc";
    public static final String    PROPTY_NAME_T_EJEMPLAR_ESTADO_DETALLE                  = "ejemEstadoDetalle";
    public static final String    PROPTY_NAME_T_EJEMPLAR_LOC_DESDE                       = "ejemLocDesde";
    public static final String    PROPTY_NAME_T_EJEMPLAR_LOC_HASTA                       = "ejemLocHasta";
    public static final String    PROPTY_NAME_T_EJEMPLAR_LOC_NOMBRE                      = "ejemLocNombre";
    public static final String    PROPTY_NAME_T_EJEM_SCHEME_CIR_STATUS                   = "ejemSchemeCirStatus";
    public static final String    PROPTY_NAME_T_EJEM_SCHEME_LOC_TIPO                     = "ejemSchemeLocTipo";
    public static final String    PROPTY_NAME_T_EJEM_SCHEME_MARCA_SEG                    = "ejemSchemeMarcaSeg";
    public static final String    PROPTY_NAME_T_EJEM_SCHEME_REST_TYPE                    = "ejemSchemeRestType";
    public static final String    PROPTY_NAME_T_EJEM_VALUE_CIR_STATUS                    = "ejemValueCirStatus";
    public static final String    PROPTY_NAME_T_EJEM_VALUE_LOC_TIPO                      = "ejemValueLocTipo";
    public static final String    PROPTY_NAME_T_EJEM_VALUE_MARCA_SEG                     = "ejemValueMarcaSeg";
    public static final String    PROPTY_NAME_T_EJEM_VALUE_RESTR_TYPE                    = "ejemValueRestrType";
    public static final String    PROPTY_NAME_NCIP_LECTOR                                = "ncipLectorId";
    public static final String    PROPTY_NAME_NCIP_BIB_SOLICITANTE                       = "ncipBibSolicitanteId";
    public static final String    PROPTY_NAME_NCIP_BIB_EJEMPLAR                          = "ncipBibEjemplarId";
    public static final String    PROPTY_NAME_NCIP_BIB_LECTOR                            = "ncipBibLectorId";

    public static final String    TIPO_PRESTAMO_DOMICILIO                                = "DO";
    public static final String    TIPO_PRESTAMO_SALA                                     = "SA";
    public static final String    TIPO_PRESTAMO_SEGURIDAD                                = "SE";
    public static final String    TIPO_PRESTAMO_RED                                      = "RE";
    public static final String    TIPO_PRESTAMO_INTERBIBLIOTECARIO                       = "PIB";

    public static final int       ESTADO_EJEMPLAR_RENOVABLE                              = 0;
    public static final int       ESTADO_EJEMPLAR_FUERA_PLAZO_SIN_SANCION                = 1;
    public static final int       ESTADO_EJEMPLAR_FUERA_PLAZO_SANCIONADO                 = 2;

    public static final String    STYLE_CLASS_EST_EJE_RENOVABLE                          = "verde";
    public static final String    STYLE_CLASS_EST_EJE_FUERA_PLAZO_SIN_SANC               = "amarillo";
    public static final String    STYLE_CLASS_EST_EJE_FUERA_PLAZO_SANCIONA               = "rojo";

    /**
     * combo Tipos de préstamo contemplados en PIB
     */
    public static final int       TIPO_PRESTAMO_PIB_SUMINISTRO_MATERIALES_NO_RETORNABLES = 0;
    public static final int       TIPO_PRESTAMO_PIB_DOMICILIO                            = 1;
    public static final int       TIPO_PRESTAMO_PIB_SALA                                 = 2;

    public static final Integer[] TIPO_PRESTAMO_PIB_VALUES                               = {
            /* TIPO_PRESTAMO_PIB_SUMINISTRO_MATERIALES_NO_RETORNABLES, */
            TIPO_PRESTAMO_PIB_DOMICILIO, TIPO_PRESTAMO_PIB_SALA                         };
    public static final String[]  TIPO_PRESTAMO_PIB_KEYS                                 = {
            /* "prestamo.PIB.select.suministro.materiales.no.retornables", */
            "prestamo.PIB.select.prestamo.domicilio",
            "prestamo.PIB.select.prestamo.sala"                                         };

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                  id;

    /**
     * Tipo de préstamo<br>
     * Valores:<br>
     * DO (Domicilio).<br>
     * SA (Sala)<br>
     * SE (Seguridad)<br>
     * RE (Préstamo en Red)<br>
     */
    private TipoPrestamo          tipoPrestamo;

    /**
     * Código único de un ejemplar en su biblioteca.<br>
     * Si el ejemplar pertenece a una de las bibliotecas registradas en
     * BIBLIOTECAS, entonces es una clave ajena a FONDOS.<br>
     * Si el ejemplar es de otra biblioteca no registrada en BIBLIOTECAS (caso
     * del préstamo en red), no es clave ajena. <br>
     * Ver BIB_X_BIBLIOTECA_EJE y T_TIPO_PRESTAMO
     */
    private String                ejemplarId;

    private Ejemplar              ejemplar;

    /**
     * Fecha y hora en que tuvo lugar el préstamo
     */
    private Date                  fechaPrestamo;

    /**
     * Fecha-hora a la que corresponde devolver el préstamo según la política de
     * préstamos aplicada.
     */
    private Date                  fechaDevolucionPol;

    /**
     * Fecha-hora de la última renovación del préstamo
     */
    private Date                  fechaRenovacion;

    /**
     * 1 si el préstamo es por un periodo indeterminado. Si está activado, tiene
     * más precedencia que F_DEVOLUCION_POL<br>
     * 0 en caso contrario (valor por defecto)
     */
    private Boolean               periodoIndeterminado                                   = Boolean.FALSE;

    /**
     * Número de renovaciones realizadas para el préstamo: 1 cuando se ha
     * realizado una renovación, 2 cuando se han realizado dos renovaciones, ...<br>
     * Valor 0 cuando se crea el préstamo (valor por defecto)
     */
    private Long                  renovacionNum                                          = 0L;

    /**
     * Número de veces que se ha reclamado la devolución del préstamo al lector.
     * 0 cuando se crea el préstamo.
     */
    private Long                  reclamacionNum                                         = 0L;

    /**
     * Elemento de dato Autor<br>
     * En NCIP corresponde al elemento de dato Author<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarAutor;

    /**
     * Autor de una parte de la obra<br>
     * En NCIP corresponde al dato Author Of Component "author-of-article" en
     * ISO 1016-1<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarAutorDeComponente;

    /**
     * ISBN, ISSN, ISRC, ISMN, ... <br>
     * En NCIP equivale al BIbliographic Item Identifier Code<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarCodIdentificadorRegistro;

    /**
     * Valor del código de identificación del registro bibliográfico En NCIP es
     * el elemento de dato Bibliographic Item Identifier Valor por defecto: NULL
     */
    private String                ejemplarIdentificadorRegistro;

    /**
     * Elemento de dato Bibliographic Level de NCIP (ver NCIP pag. 41), e
     * "Item-type" en ISO 10161-1<br>
     * Existe también el campo EJE_TIPO_X_EJEMPLAR_TIPO, pero este es sólo para
     * los tipos considerados por la aplicación, mientras que en este campo se
     * guarda el tipo de ejemplar que comunica la biblioteca propietaria del
     * mismo vía NCIP Sólo se cumplimenta en préstamos en red o
     * interbibliotecarios, si la bioblioteca propietaria del ejemplar lo
     * informa.<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarBibliographicLevel;

    /**
     * Identifica una parte componente de un registro bibliográfico<br>
     * En NCIP corresponde al elemento de dato Component Identifier<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarComponenteId;

    /**
     * Tipo de identificador de componente de registro bibliográfico<br>
     * En NCIP corresponde al elemento de dato Component Identifier Type<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarComponenteIdTipo;

    /**
     * String que identifica la edición a la que pertenece el ejemplar<br>
     * En NCIP corresponde al elemento de dato Edition<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarEdicion;

    /**
     * Idioma del ejemplar En NCIP corresponde al elemento de dato Language.<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarIdioma;

    /**
     * Cadena que indica la fecha de publicación.<br>
     * En NCIP corresponde al elemento de dato Publication Date<br>
     * No es de tipo F_... porque en el caso de que sea informado por otra
     * biblioteca, vía NCIP puede tener cualquier formato<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarFechaPublicacion;

    /**
     * Editor del ejemplar. <br>
     * En NCIP corresponde al elemento de dato Publisher<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarEditor;

    /**
     * Título del ejemplar.<br>
     * En NCIP corresponde al elemento de dato Title<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarTitulo;

    /**
     * Título de una parte de un ejemplar, por ejemplo título de un capítulo de
     * un libro o nombre de un artículo de una serie.<br>
     * En NCIP corresponde al elemento de dato Title Of Component.<br>
     * Valor por defecto: NULL
     */
    private String                ejemplarTituloComponente;

    /**
     * Fecha hora en que se ha generado la carta de reclamación o se ha enviado
     * por email
     */
    private Date                  fechaReclamacion1a;

    /**
     * Fecha hora en que se ha generado la carta de reclamación o se ha enviado
     * por email
     */
    private Date                  fechaReclamacion2a;

    /**
     * Fecha hora en que se ha generado la carta de reclamación o se ha enviado
     * por email
     */
    private Date                  fechaReclamacion3a;

    /**
     * Usuario bibliotecario que realiza el préstamo.<br>
     * No tiene equivalente en las tablas de Ab*NET (esa aplicación no recoge
     * este dato).<br>
     * Si el bibliotecario no es de una de las bibliotecas registradas en
     * BIBLIOTECAS (caso del préstamo en red), no es una clave ajena a la tabla
     * USUARIOS.<br>
     * Si el bibliotecario es de una de las bibliotecas registradas en
     * BIBLIOTECAS, es clave ajena a la tabla USUARIOS.<br>
     * En ambos casos se asume que la identificación de un bibliotecario es
     * única en su biblioteca.<br>
     * NULL si el préstamo se realiza vía un servicio (p.e. de NCIP)
     */
    private Usuario               usuarioBibliotecario;

    /**
     * BIblioteca en la que realizó la última renovación del préstamo. Si es un
     * préstamo en red, es una clave ajena a la tabla CONSORCIO_BIBLIOTECAS. Si
     * es un préstamo de otro tipo, es clave ajena a la tabla BIBLIOTECA.
     */
    private Biblioteca            bibliotecaRenovacion;

    /**
     * Biblioteca que realiza el préstamo
     */
    private Biblioteca            bibliotecaPrestamista;
    /**
     * Identificación del lector en su biblioteca.<br>
     * Este valor debe ser único para cada lector en su biblioteca.<br>
     * Si el lector es de una de las bibliotecas registradas en BIBLIOTECAS, es
     * una clave ajena a LECTORES Si el lector es de otra biblioteca no
     * registrada en esta BD (caso del préstamo en red), no es clave ajena.
     */
    private Lector                lector;

    /**
     * Clave ajena a LECTORES_TIPOS Tipo del lector que realiza el préstamo.
     * Sólo significativo para las modalidades de préstamo a domicilio, y en
     * sala.
     */
    private TipoLector            lectorTipoLectorTipo;

    // ============= prestamo en red consorciado ============
    /**
     * Clave ajena a CONSORCIOS Consorcio con cuya aplicación APRC se realizó el
     * préstamo
     */
    private Consorcio             consorcio;
    /**
     * Clave ajena a CONSORCIOS_BIBLIOTECAS Indica la biblioteca en la que se
     * realizó la última renovación del préstamo
     */
    private BibliotecaConsorcio   bibliotecaConsRenovacion;
    /**
     * Clave ajena a CONSORCIOS_BIBLIOTECAS Biblioteca o sucursal del lector que
     * realiza el préstamo. Elemento de dato Unique User Id.Unique Agency Id
     * recibido en Athenticate User Response
     */
    private BibliotecaConsorcio   bibliotecaConsLector;

    /**
     * Clave ajena a CONSORCIOS_BIBLIOTECAS Biblioteca propietaria del ejmeplar
     * que se presta. Elemento de dato Unique Item Id.Item Unique Agency Id que
     * se envía en Check Out Item y Checked Out Item Indica la biblioteca
     * consorciada, propietaria del ejemplar T_EJEMPLAR_ID a que se refiere el
     * préstamo en red.
     */
    private BibliotecaConsorcio   bibliotecaConsEjemplar;

    /**
     * 1 si existe el elemento de dato Item Optional Fields.Sensitization Flag
     * en el mensaje de respuesta Lookup Item Response 0 en caso contrario
     */
    private Boolean               sensitizationFlagEj;

    /**
     * Cadena XML correspondiente al elemento de dato Item Optional
     * Fields.Bibliographic Description, que se recibe en el mensaje Lookup Item
     * Response
     */
    private String                ejemBiblioDesc;

    /**
     * Elemento de dato Physical Condition.Physical Condition Details recibido
     * en el mensaje Lookup Item Response
     */
    public String                 ejemEstadoDetalle;

    /**
     * Elemento de dato Location.Valid From Date, recibido en el mensaje Lookup
     * Item Response
     */
    public String                 ejemLocDesde;

    /**
     * Elemento de dato Location.Valid To Date, recibido en el mensaje Lookup
     * Item Response
     */
    public String                 ejemLocHasta;

    /**
     * Elemento de dato Location.Location Name.Location Name Instance.Location
     * Name Value (con el Level mas alto), recibido en el mensaje Lookup Item
     * Response
     */
    public String                 ejemLocNombre;

    /**
     * Elemento de dato Item Optional Fields.Circulation Status.Scheme que se
     * recibe en el mensaje Lookup Item Response
     */
    public String                 ejemSchemeCirStatus;

    /**
     * Elemento de dato Location.Location Type.Scheme, recibido en el mensaje
     * Lookup Item Response
     */
    public String                 ejemSchemeLocTipo;

    /**
     * Elemento de dato Item Optional Fields.Security Marker.Scheme, recibido en
     * el mensaje Lookup Item Response
     */
    public String                 ejemSchemeMarcaSeg;

    /**
     * Elemento de dato Item Optional Fields.Item Use Restriction Type.Scheme
     * del mensaje Lookup Item Response
     */
    public String                 ejemSchemeRestType;

    /**
     * Elemento de dato Item Optional Fields.Circulation Status.Value que se
     * recibe en el mensaje Lookup Item Response
     */
    public String                 ejemValueCirStatus;

    /**
     * Elemento de dato Location.Location Type.Value, recibido en el mensaje
     * Lookup Item Response
     */
    public String                 ejemValueLocTipo;

    /**
     * Elemento de dato Item Optional Fields.Security Marker.Value, recibido en
     * el mensaje Lookup Item Response
     */
    public String                 ejemValueMarcaSeg;

    /**
     * Elemento de dato Item Optional Fields.Item Use Restriction Type.Value del
     * mensaje Lookup Item Response
     */
    public String                 ejemValueRestrType;

    /**
     * Identificador del lector de otra biblioteca que será registrado cuando se
     * realice un préstamo en red.
     */
    public String                 ncipLectorId;

    /**
     * Identificador de la biblioteca que solicita el prestamo en red.
     */
    public String                 ncipBibSolicitanteId;

    /**
     * Identificador de la biblioteca propietaria del ejemplar y por tanto la
     * que presta el ejemplar.
     */
    public String                 ncipBibEjemplarId;

    /**
     * Identificador de la biblioteca del lector
     */
    public String                 ncipBibLectorId;

    protected Prestamo() {
        super();
    }

    /**
     * Constructor usado en préstamo en sala
     * 
     * @param lector
     * @param ejemplar
     */
    public Prestamo(final Lector lector, final Ejemplar ejemplar) {
        super();
        this.lector = lector;
        this.ejemplar = ejemplar;
    }

    /**
     * Constructor usado en préstamo en red
     * 
     * @param codBarrasEjemplarNCIP
     */
    public Prestamo(String codBarrasEjemplarNCIP) {
        ejemplarId = codBarrasEjemplarNCIP;
    }

    public Prestamo(final Ejemplar ejemplar) {
        super();
        this.ejemplar = ejemplar;
    }

    /**
     * constructor que crea una instancia de Prestamo que se utilizará en sesión
     * para mostrar la información obtenida de NCIP
     */
    public Prestamo(Long idNcip) {
        // establecemos el identificador remoto NCIP
        setIdNcip(idNcip);

        // establecemos la propiedad temporal para que este POJO
        // no se pueda guardar en BBDD, genericDao.save(lectorTemporal);
        // devolvería una excepcion
        setTemporal(true);
    }

    /**
     * Constructor usado en préstamo a domicilio
     * 
     * @param lector
     * @param ejemplar
     * @param politica
     */
    public Prestamo(final Lector lector, final Ejemplar ejemplar,
            final PoliticaPrestamoDom politica) {
        super();
        this.lector = lector;
        this.ejemplar = ejemplar;
        setPoliticaPrestamoDom(politica);
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Prestamo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Prestamo.ID_GENERATOR_NAME, sequenceName = Prestamo.ID_SEQUENCE_NAME)
    @Column(name = Prestamo.COLUMN_NAME_ID)
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
    @ManyToOne(targetEntity = TipoPrestamo.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = Prestamo.COLUMN_NAME_TIPO_PRESTAMO)
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
     * @return the ejemplarId
     */
    @Column(name = Prestamo.COLUMN_NAME_EJEMPLAR_ID)
    public String getEjemplarId() {
        return ejemplarId;
    }

    /**
     * @param ejemplarId
     *            the ejemplarId to set
     */
    public void setEjemplarId(final String ejemplarId) {
        this.ejemplarId = ejemplarId;
    }

    /**
     * @return the ejemplar
     */
    @ManyToOne(targetEntity = Ejemplar.class)
    @JoinColumn(name = Prestamo.COLUMN_NAME_EJEMPLAR, nullable = true, unique = true)
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
    @Column(name = Prestamo.COLUMN_NAME_FECHA_PRESTAMO)
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
    @Column(name = Prestamo.COLUMN_NAME_FECHA_DEVOLUCION_POL)
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
    @Column(name = Prestamo.COLUMN_NAME_PERIODO_INDETERMINADO)
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
     * @return the fechaRenovacion
     */
    @Column(name = Prestamo.COLUMN_NAME_FECHA_RENOVACION)
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
    @Column(name = Prestamo.COLUMN_NAME_RENOVACION_NUM)
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
     * @return the reclamacionNum
     */
    @Column(name = Prestamo.COLUMN_NAME_RECLAMACION_NUM)
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
     * @return the ejemplarAutor
     */
    @Column(name = Prestamo.COLUMN_NAME_EJE_AUTOR)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_AUTOR_DE_COMPONENTE)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_COD_IDENTIFICADOR_REGIST)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_IDENTIFICADOR_REGISTRO)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_BIBLIOGRAPHIC_LEVEL)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_COMPONENTE_ID)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_COMPONENTE_ID_TIPO)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_EDICION)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_IDIOMA)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_FECHA_PUBLICACION)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_EDITOR)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_TITULO)
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
    @Column(name = Prestamo.COLUMN_NAME_EJE_TITULO_COMPONENTE)
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
     * @return the fechaReclamacion1a
     */
    @Column(name = Prestamo.COLUMN_NAME_FECHA_RECLAMACION_1A)
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
    @Column(name = Prestamo.COLUMN_NAME_FECHA_RECLAMACION_2A)
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
    @Column(name = Prestamo.COLUMN_NAME_FECHA_RECLAMACION_3A)
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
     * @return the usuarioBibliotecario
     */
    @ManyToOne(targetEntity = Usuario.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Prestamo.COLUMN_NAME_BIBLIOTECARIO, nullable = true)
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
     * @return the bibliotecaRenovacion
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Prestamo.COLUMN_NAME_BIBLIOTECA_REN, nullable = true)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_REN")
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
     * @return the bibliotecaPrestamista
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = Prestamo.COLUMN_NAME_BIBLIOTECA_PRESTAMISTA, nullable = true)
    @ForeignKey(name = "FK_BIB_X_BIB_PRESTAM")
    public Biblioteca getBibliotecaPrestamista() {
        return bibliotecaRenovacion;
    }

    /**
     * @param bibliotecaPrestamista
     *            the bibliotecaPrestamista to set
     */
    public void setBibliotecaPrestamista(final Biblioteca bibliotecaPrestamista) {
        this.bibliotecaPrestamista = bibliotecaPrestamista;
    }

    /**
     * @return the lector
     */
    @ManyToOne(targetEntity = Lector.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Prestamo.COLUMN_NAME_LECTOR, nullable = true)
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
     * @return the lectorTipoLectorTipo
     */
    @ManyToOne(targetEntity = TipoLector.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Prestamo.COLUMN_NAME_LECTOR_TIPO, nullable = true)
    public TipoLector getLectorTipoLectorTipo() {
        return lectorTipoLectorTipo;
    }

    /**
     * @param lectorTipoLectorTipo
     *            the lectorTipoLectorTipo to set
     */
    public void setLectorTipoLectorTipo(final TipoLector lectorTipoLectorTipo) {
        this.lectorTipoLectorTipo = lectorTipoLectorTipo;
    }

    /**
     * Transfiere los parámetros de un objeto de la entidad política a una
     * entidad préstamo
     * 
     * @param politica
     *            the politica to set
     */
    public void setPoliticaPrestamoDom(final PoliticaPrestamoDom politica) {
        copyParamsPojo(politica, AbstractParamPolPrestamo.class);
    }

    /**
     * @return the consorcio
     */
    @ManyToOne(targetEntity = Consorcio.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Prestamo.COLUMN_NAME_CONSORCIO, nullable = true)
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
     * @return the bibliotecaConsRenovacion
     */
    @ManyToOne(targetEntity = BibliotecaConsorcio.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Prestamo.COLUMN_NAME_BIBLIOTECA_CONS_REN, nullable = true)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_CONS_REN")
    public BibliotecaConsorcio getBibliotecaConsRenovacion() {
        return bibliotecaConsRenovacion;
    }

    /**
     * @param bibliotecaConsRenovacion
     *            the bibliotecaConsRenovacion to set
     */
    public void setBibliotecaConsRenovacion(
            BibliotecaConsorcio bibliotecaConsRenovacion) {
        this.bibliotecaConsRenovacion = bibliotecaConsRenovacion;
    }

    /**
     * @return the bibliotecaConsLector
     */
    @ManyToOne(targetEntity = BibliotecaConsorcio.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Prestamo.COLUMN_NAME_BIBLIOTECA_CONS_LEC, nullable = true)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_CONS_LEC")
    public BibliotecaConsorcio getBibliotecaConsLector() {
        return bibliotecaConsLector;
    }

    /**
     * @param bibliotecaConsLector
     *            the bibliotecaConsLector to set
     */
    public void setBibliotecaConsLector(BibliotecaConsorcio bibliotecaConsLector) {
        this.bibliotecaConsLector = bibliotecaConsLector;
    }

    /**
     * @return the bibliotecaConsEjemplar
     */
    @ManyToOne(targetEntity = BibliotecaConsorcio.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Prestamo.COLUMN_NAME_BIBLIOTECA_CONS_EJE, nullable = true)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_CONS_EJE")
    public BibliotecaConsorcio getBibliotecaConsEjemplar() {
        return bibliotecaConsEjemplar;
    }

    /**
     * @param bibliotecaConsEjemplar
     *            the bibliotecaConsEjemplar to set
     */
    public void setBibliotecaConsEjemplar(
            BibliotecaConsorcio bibliotecaConsEjemplar) {
        this.bibliotecaConsEjemplar = bibliotecaConsEjemplar;
    }

    /**
     * @return the sensitizationFlagEj
     */
    @Column(name = Prestamo.COLUMN_NAME_SENSITIZATION_FLAG_EJ)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJE_BIBLIOGRAPHIC_DESC)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEMPLAR_ESTADO_DETALLE)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEMPLAR_LOC_DESDE)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEMPLAR_LOC_HASTA)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEMPLAR_LOC_NOMBRE)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEM_SCHEME_CIR_STATUS)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEM_SCHEME_LOC_TIPO)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEM_SCHEME_MARCA_SEG)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEM_SCHEME_REST_TYPE)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEM_VALUE_CIR_STATUS)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEM_VALUE_LOC_TIPO)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEM_VALUE_MARCA_SEG)
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
    @Column(name = Prestamo.COLUMN_NAME_T_EJEM_VALUE_RESTR_TYPE)
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

    /**
     * @return the getNcipBibSolicitanteId
     */
    @Column(name = Prestamo.COLUMN_NAME_NCIP_BIB_SOLICITANTE)
    public String getNcipBibSolicitanteId() {
        return ncipBibSolicitanteId;
    }

    /**
     * @param ncipBibSolicitanteId
     *            the ncipBibSolicitanteId to set
     */
    public void setNcipBibSolicitanteId(String ncipBibSolicitanteId) {
        this.ncipBibSolicitanteId = ncipBibSolicitanteId;
    }

    /**
     * @return the getNcipBibSolicitanteId
     */
    @Column(name = Prestamo.COLUMN_NAME_NCIP_BIB_LECTOR)
    public String getNcipBibLectorId() {
        return ncipBibLectorId;
    }

    /**
     * @param ncipBibLectorId
     *            the ncipBibLectorId to set
     */
    public void setNcipBibLectorId(String ncipBibLectorId) {
        this.ncipBibLectorId = ncipBibLectorId;
    }

    /**
     * @return the getNcipBibEjemplarId
     */
    @Column(name = Prestamo.COLUMN_NAME_NCIP_BIB_EJEMPLAR)
    public String getNcipBibEjemplarId() {
        return ncipBibEjemplarId;
    }

    /**
     * @param ncipBibEjemplarId
     *            the ncipBibEjemplarId to set
     */
    public void setNcipBibEjemplarId(String ncipBibEjemplarId) {
        this.ncipBibEjemplarId = ncipBibEjemplarId;
    }

    /**
     * @return the getNcipLectorId
     */
    @Column(name = Prestamo.COLUMN_NAME_NCIP_LECTOR)
    public String getNcipLectorId() {
        return ncipLectorId;
    }

    /**
     * @param ncipLectorId
     *            the ncipLectorId to set
     */
    public void setNcipLectorId(String ncipLectorId) {
        this.ncipLectorId = ncipLectorId;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Prestamo)) {
            return false;
        }

        final Prestamo other = (Prestamo) obj;

        // equals de un pojo no debe comparar los ids
        /*
         * if (!getId().equals(other.getId())) { return false; }
         */
        if ((getLector() != null) && (!getLector().equals(other.getLector()))) {
            return false;
        }
        if ((getEjemplar() != null)
                && (!getEjemplar().equals(other.getEjemplar()))) {
            return false;
        }
        if ((getTipoPrestamo() != null)
                && (!getTipoPrestamo().equals(other.getTipoPrestamo()))) {
            return false;
        }
        if ((getFechaPrestamo() != null)
                && (!getFechaPrestamo().equals(other.getFechaPrestamo()))) {
            return false;
        }
        if ((getEjemplarAutor() != null)
                && (!getEjemplarAutor().equals(other.getEjemplarAutor()))) {
            return false;
        }
        if ((getEjemplarTitulo() != null)
                && (!getEjemplarTitulo().equals(other.getEjemplarTitulo()))) {
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
                + ((getLector() == null) ? 0 : getLector().hashCode());
        result = prime * result
                + ((getEjemplarId() == null) ? 0 : getEjemplarId().hashCode());
        result = prime
                * result
                + ((getTipoPrestamo() == null) ? 0 : getTipoPrestamo()
                        .hashCode());
        result = prime
                * result
                + ((getFechaPrestamo() == null) ? 0 : getFechaPrestamo()
                        .hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(Prestamo.PROPTY_NAME_ID, getId())
                .append(Prestamo.PROPTY_NAME_LECTOR, getLector())
                .append(Prestamo.PROPTY_NAME_EJEMPLAR_ID, getEjemplarId())
                .append(Prestamo.PROPTY_NAME_TIPO_PRESTAMO, getTipoPrestamo())
                .append(Prestamo.PROPTY_NAME_FECHA_PRESTAMO, getFechaPrestamo())
                .toString();
    }

    /**
     * @see org.librae.common.auditoria.model.IAuditable#getAuditMessage()
     */
    @Transient
    public String getAuditMessage() {
        return toString();
    }

    @Transient
    private boolean isEjemplarAvailable() {
        return ejemplar != null;
    }

    @Transient
    public String getCodigoDeBarrasEjemplar() {
        if (isEjemplarAvailable()) {
            return ejemplar.getCodigoBarras();
        } else {
            return ejemplarId;
        }
    }

    @Transient
    public String getSignaturaEjemplar() {
        if (isEjemplarAvailable()) {
            return ejemplar.getSignatura();
        } else {
            return "";
        }
    }

    @Transient
    public String getTituloEjemplar() {
        if (isEjemplarAvailable()) {
            return ejemplar.getRegPrincipal().getTitulo(); 
        } else {
            return getEjemplarTitulo();
        }
    }

    @Transient
    public String getPeriodo() {
        return DateUtil.calcularPeriodoLabel(fechaPrestamo, fechaDevolucionPol);
    }

    /**
     * devolverá: <br>
     * ESTADO_EJEMPLAR_RENOVABLE si el ejemplar se puede renovar. <br>
     * ESTADO_EJEMPLAR_FUERA_PLAZO_SIN_SANCION si el plazo de devolución se ha
     * sobrepasado, pero todavía no ha incurrido en sanción.<br>
     * ESTADO_EJEMPLAR_FUERA_PLAZO_SANCIONADO si se ha sobrepasado la fecha de
     * devolución y lleva aparejada una sancion
     * 
     * @return entero que indica el color de la columna
     */
    @Transient
    public int getEstado() {
        final Date ahora = new Date();
        if (fechaDevolucionPol==null) {
        	return Prestamo.ESTADO_EJEMPLAR_RENOVABLE;
        } else {
	        if (ahora.before(fechaDevolucionPol)) {
	            return Prestamo.ESTADO_EJEMPLAR_RENOVABLE;
	        } else {
	            final Date fechaSuspension = new Date();
	            final Long diasAntesSuspension = this.diasAntesSuspension
	                    * (1000 * 60 * 60 * 24);
	
	            fechaSuspension
	                    .setTime((fechaDevolucionPol.getTime() + (diasAntesSuspension)));

	            if (ahora.before(fechaSuspension)) {
	                return Prestamo.ESTADO_EJEMPLAR_FUERA_PLAZO_SIN_SANCION;
	            } else {
	                return Prestamo.ESTADO_EJEMPLAR_FUERA_PLAZO_SANCIONADO;
	            }
	        }
        }
    }

    @Transient
    public void incrementarRenovacionNum() {
        renovacionNum++;
    }

    /**
     * Devuelve el <code>styleClass</code> que debemos mostrar en el campo
     * <code>fechaDevolucionPol</code> fechaDevolucionPol
     * 
     * @return
     */
    @Transient
    public String getStyleClassFechaDevolucionPol() {
        String result = null;

        final int estado = getEstado();

        switch (estado) {
            case ESTADO_EJEMPLAR_RENOVABLE:
                result = Prestamo.STYLE_CLASS_EST_EJE_RENOVABLE;
                break;
            case ESTADO_EJEMPLAR_FUERA_PLAZO_SIN_SANCION:
                result = Prestamo.STYLE_CLASS_EST_EJE_FUERA_PLAZO_SIN_SANC;
                break;
            case ESTADO_EJEMPLAR_FUERA_PLAZO_SANCIONADO:
                result = Prestamo.STYLE_CLASS_EST_EJE_FUERA_PLAZO_SANCIONA;
                break;
        }

        return result;
    }

    @Transient
    public AbstractParamPolPrestamo getPoliticaPrestamoDomData() {
        return this;
    }

    @Override
    public AbstractParamPolPrestamo newInstance() {
        return new Prestamo();
    }
}