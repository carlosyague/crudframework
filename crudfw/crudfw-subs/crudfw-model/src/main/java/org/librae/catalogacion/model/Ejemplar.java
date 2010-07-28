package org.librae.catalogacion.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.librae.adminconfig.model.AsignacionValorCodigo;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.circulacion.model.EstadoCirculacion;
import org.librae.circulacion.model.Prestamo;
import org.librae.circulacion.model.PrestamoHistorico;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar un Ejemplar
 *
 * @author jcdiaz
 */
@Entity(name = Ejemplar.ENTITY_NAME)
@Table(name = Ejemplar.TABLE_NAME)
public class Ejemplar extends SpringRemotableLazyEntity<Ejemplar> {

    /**
     * BaseObject es Serializable, por lo tanto Ejemplar necesita un Serial
     * Version UID
     */
    private static final long           serialVersionUID                      = -7849559178756090962L;

    public static final String          ENTITY_NAME                           = "org.librae.catalogacion.model.Ejemplar";

    /**
     * Constantes para referencia de los atributos de Ejemplar
     */
    public static final String          PROPTY_NAME_ID                        = "id";
    public static final String          PROPTY_NAME_NOMBRE                    = "nombre";
    public static final String          PROPTY_NAME_FECHA_INGRESO_INVENTARIO  = "fechaIngresoInventario";
    public static final String          PROPTY_NAME_FECHA_REGISTRO            = "fechaRegistro";
    public static final String          PROPTY_NAME_FECHA_ULTIMA_DEVOLUCION   = "fechaUltimaDevolucion";
    public static final String          PROPTY_NAME_IMPRIMIR_ETIQUETA         = "imprimirEtiqueta";
    public static final String          PROPTY_NAME_IMPRIMIR_TEJUELO          = "imprimirTejuelo";
    public static final String          PROPTY_NAME_VISIBLE_OPAC              = "visibleOpac";
    public static final String          PROPTY_NAME_CODIGO_BARRAS             = "codigoBarras";
    public static final String          PROPTY_NAME_SIGNATURA                 = "signatura";
    public static final String          PROPTY_NAME_SIGNATURA_SUPLEMENTARIA   = "signaturaSuplementaria";
    public static final String          PROPTY_NAME_VOLUMEN                   = "volumen";
    public static final String          PROPTY_NAME_NUMERO_REGISTRO           = "numeroRegistro";
    public static final String          PROPTY_NAME_TEXTO                     = "texto";
    public static final String          PROPTY_NAME_NUMERO_PRESTAMOS          = "numeroPrestamos";
    public static final String          PROPTY_NAME_IMPRIMIR_CODIGO_BARRAS    = "imprimirCodigoBarras";
    public static final String          PROPTY_NAME_FECHA_MODIFICACION        = "fechaModificacion";
    public static final String          PROPTY_NAME_FECHA_CAMBIO_SITUACION    = "fechaCambioSituacion";
    public static final String          PROPTY_NAME_FECHA_PRESTAMO            = "fechaPrestamo";
    public static final String          PROPTY_NAME_FECHA_DEVOLUCION          = "fechaDevolucion";
    public static final String          PROPTY_NAME_ESTADO                    = "ejemplarEstado";
    public static final String          PROPTY_NAME_TIPO                      = "ejemplarTipo";
    public static final String          PROPTY_NAME_SOPORTE                   = "ejemplarSoporte";
    public static final String          PROPTY_NAME_SITUACION                 = "ejemplarSituacion";
    public static final String          PROPTY_NAME_PROCEDENCIA               = "procedencia";
    public static final String          PROPTY_NAME_LOCALIZACION              = "ejemplarLocalizacion";
    public static final String          PROPTY_NAME_RESTRICCION_USO           = "restriccionUso";
    public static final String          PROPTY_NAME_BIBLIOTECA                = "biblioteca";
    public static final String          PROPTY_NAME_ESTADO_CIRCULACION        = "estadoCirculacion";
    public static final String          PROPTY_NAME_AUTOR                     = "autor";
    public static final String          PROPTY_NAME_ID_REGISTRO               = "idRegistro";
    public static final String          PROPTY_NAME_TITULO                    = "titulo";
    public static final String          PROPTY_NAME_MATERIA                   = "materia";
    public static final String          PROPTY_NAME_EDITORIAL                 = "editorial";
    public static final String          PROPTY_NAME_INTERNACIONAL_NUMBER      = "internacionalNumber";
    public static final String          PROPTY_NAME_PRESTAMO                  = "prestamo";

    // public static final String PROPTY_NAME_REGISTRO = "registro";
    public static final String          PROPTY_NAME_REGISTROS_EJEMPLARES      = "registrosEjemplares";

    public static final String          PROPTY_NAME_PRESTAMOS                 = "prestamos";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String          TABLE_NAME                            = "CAT_EJEMPLARES";
    private static final String         ID_GENERATOR_NAME                     = "generator_cat_ejemplares";
    private static final String         ID_SEQUENCE_NAME                      = "SEQ_CAT_EJEMPLARES";

    public static final String          COLUMN_NAME_ID                        = "X_EJEMPLAR";
    public static final String          COLUMN_NAME_FECHA_INGRESO_INVENTARIO  = "F_FECHA_INGRESO_INVENTARIO";
    public static final String          COLUMN_NAME_FECHA_REGISTRO            = "F_FECHA_REGISTRO";
    public static final String          COLUMN_NAME_FECHA_ULTIMA_DEVOLUCION   = "F_FECHA_ULTIMA_DEVOLUCION";
    public static final String          COLUMN_NAME_IMPRIMIR_ETIQUETA         = "L_IMPRESO_ETIQUETA";
    public static final String          COLUMN_NAME_IMPRIMIR_TEJUELO          = "L_IMPRESO_TEJUELO";
    public static final String          COLUMN_NAME_VISIBLE_OPAC              = "L_VISIBLE_OPAC";
    public static final String          COLUMN_NAME_CODIGO_BARRAS             = "N_CODIGO_BARRAS";
    public static final String          COLUMN_NAME_SIGNATURA                 = "T_SIGNATURA";
    public static final String          COLUMN_NAME_SIGNATURA_SUPLEMENTARIA   = "T_SIGNATURA_SUPLEMENTARIA";
    public static final String          COLUMN_NAME_VOLUMEN                   = "C_VOLUMEN";
    public static final String          COLUMN_NAME_NUMERO_REGISTRO           = "N_NUMERO_REGISTRO";
    public static final String          COLUMN_NAME_TEXTO                     = "T_TEXTO";
    public static final String          COLUMN_NAME_NUMERO_PRESTAMOS          = "N_NUMERO_PRESTAMOS";
    public static final String          COLUMN_NAME_CAMBIO_SITUACION          = "F_FECHA_CAMBIO_SITUACION";
    public static final String          COLUMN_NAME_FECHA_MODIFICACION        = "F_FECHA_MODIFICACION";
    public static final String          COLUMN_NAME_FECHA_PRESTAMO            = "F_FECHA_PRESTAMO";
    public static final String          COLUMN_NAME_FECHA_DEVOLUCION          = "F_FECHA_DEVOLUCION";
    public static final String          COLUMN_NAME_IMPRIMIR_CODIGO_BARRA     = "L_IMPRESO_CODIGO_BARRA";

    public static final String          COLUMN_NAME_EJEMPLAR_ESTADO_FK        = "EJE_X_EJEMPLAR_ESTADO";
    public static final String          COLUMN_NAME_EJEMPLAR_TIPO_FK          = "EJE_X_EJEMPLAR_TIPO";
    public static final String          COLUMN_NAME_EJEMPLAR_SOPORTE_FK       = "EJE_X_EJEMPLAR_SOPORTE";
    public static final String          COLUMN_NAME_EJEMPLAR_SITUACION_FK     = "EJE_X_EJEMPLAR_SITUACION";
    public static final String          COLUMN_NAME_PROCEDENCIA_FK            = "PRO_X_PROCEDENCIA";
    public static final String          COLUMN_NAME_EJEMPLAR_LOCALIZACION_FK  = "EJE_X_EJEMPLAR_LOCALIZACION";
    public static final String          COLUMN_NAME_RESTRICCION_USO_FK        = "RES_X_RESTRICCION_USO";
    // public static final String COLUMN_NAME_REGISTRO_FK = "REG_X_REGISTRO";
    public static final String          COLUMN_NAME_EJEMPLAR_FK               = "EJE_X_EJEMPLAR";
    public static final String          COLUMN_NAME_BIBLIOTECA_FK             = "BIB_X_BIBLIOTECA";
    public static final String          COLUMN_NAME_ESTADO_CIRCULACION_FK     = "EST_X_ESTADO_CIRCULACION";

    public static final String          JOIN_EJEMPLAR_X_EJEMPLAR_CODIGO_VALOR = "CAT_EJEMP_X_CAT_EJEMP_COD_VAL";

    /**
     * Clave autonumérica sin signficado funcional
     */
    private Long                        id;

    /**
     * Fecha Inventario -cofinv
     */
    private Date                        fechaIngresoInventario;

    /**
     * Fecha Registro -cofrec
     */
    private Date                        fechaRegistro;

    /**
     * Fecha de la última devolución - cofult
     */
    private Date                        fechaUltimaDevolucion;

    /**
     * Indicativo de si el ejemplar está marcado para imprimir como etiqueta
     * pendiente
     */
    private Boolean                     imprimirEtiqueta;

    /**
     * Indicativo de si el ejemplar está marcado para imprimir como tejuelo
     * pendiente
     */
    private Boolean                     imprimirTejuelo;

    /**
     * Indica si el ejemplar va a ser visible desde el OPAC
     */
    private Boolean                     visibleOpac                           = true;

    /**
     * Código de barras - cobarc
     */
    private String                      codigoBarras;

    /**
     * Signatura -cosign
     */
    private String                      signatura;

    /**
     * Signatura suplementaria -cosigs
     */
    private String                      signaturaSuplementaria;

    /**
     * Identificador de volumen - coidvo
     */
    private String                      volumen;

    /**
     * Nº Registro
     */
    private Integer                     numeroRegistro;

    /**
     * Texto - cotext
     */
    private String                      texto;

    /**
     * Nº Prestamos
     */
    private Integer                     numeroPrestamos;

    /**
    *
    */
    private Boolean                     imprimirCodigoBarras;

    /**
    *
    */
    private Date                        fechaModificacion;

    /**
    *
    */
    private Date                        fechaCambioSituacion;

    /**
     * Fecha en la cual el registro fue prestado. Circulación se encarga de
     * setearla y borrarla mediante un servicio.
     */
    private Date                        fechaPrestamo;

    /**
     * Fecha en la cual el registro será devuelto. Circulación se encarga de
     * setearla y borrarla mediante un servicio.
     */
    private Date                        fechaDevolucion;

    /**
     *
     */
    private EjemplarEstado              ejemplarEstado;

    /**
     *
     */
    private EjemplarTipo                ejemplarTipo;

    /**
     *
     */
    private EjemplarSoporte             ejemplarSoporte;

    /**
    *
    */
    private EjemplarSituacion           ejemplarSituacion;

    /**
     *
     */
    private Procedencia                 procedencia;

    /**
     *
     */
    private EjemplarLocalizacion        ejemplarLocalizacion;

    /**
     *
     */
    private RestriccionUso              restriccionUso;

    /**
     *
     */
    // private Registro registro;
    /**
     * Listado que contiene los objetos de la tabla intermedia entre registros y
     * ejemplares que direfencian el uso de un registro como titulo primario y
     * titulo secundario,
     */
    private List<RegistroEjemplar>      registrosEjemplares;

    /**
    *
    */
    private Biblioteca                  biblioteca;

    /**
     *
     */
    private EstadoCirculacion           estadoCirculacion;

    /**
     *
     */
    private List<AsignacionValorCodigo> ejemplarCodigos;

    /**
    *
    */
    private Prestamo                    prestamo;

    private List<Prestamo>              prestamos;

    private List<PrestamoHistorico>     prestamosHistoricos;

    /**
     * Clave foránea a la tabla de registros para almacenar los registros que
     * actuan como título secundario para el ejemplar
     */
    // private List<Registro> registros;
    /**
     * Constructor sin parámetros
     */
    protected Ejemplar() {
        super();
        ejemplarCodigos = new ArrayList<AsignacionValorCodigo>();
        // this.registros = new ArrayList<Registro>();
        this.registrosEjemplares = new ArrayList<RegistroEjemplar>();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigoBarras
     */
    public Ejemplar(final String codigoBarras) {
        this.codigoBarras = codigoBarras;
        ejemplarCodigos = new ArrayList<AsignacionValorCodigo>();
        this.registrosEjemplares = new ArrayList<RegistroEjemplar>();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigoBarras
     * @param ejemplarEstado
     * @param ejemplarLocalizacion
     * @param ejemplarSituacion
     * @param ejemplarSoporte
     * @param ejemplarTipo
     * @param imprimirCodigoBarras
     * @param imprimirEtiqueta
     * @param imprimirTejuelo
     * @param procedencia
     * @param visibleOpac
     */
    public Ejemplar(final String codigoBarras,
            final EjemplarEstado ejemplarEstado,
            final EjemplarLocalizacion ejemplarLocalizacion,
            final EjemplarSituacion ejemplarSituacion,
            final EjemplarSoporte ejemplarSoporte,
            final EjemplarTipo ejemplarTipo,
            final Boolean imprimirCodigoBarras, final Boolean imprimirEtiqueta,
            final Boolean imprimirTejuelo, final Procedencia procedencia,
            final Boolean visibleOpac) {
        super();
        this.codigoBarras = codigoBarras;
        this.ejemplarEstado = ejemplarEstado;
        this.ejemplarLocalizacion = ejemplarLocalizacion;
        this.ejemplarSituacion = ejemplarSituacion;
        this.ejemplarSoporte = ejemplarSoporte;
        this.ejemplarTipo = ejemplarTipo;
        this.imprimirCodigoBarras = imprimirCodigoBarras;
        this.imprimirEtiqueta = imprimirEtiqueta;
        this.imprimirTejuelo = imprimirTejuelo;
        this.procedencia = procedencia;
        this.visibleOpac = visibleOpac;
        ejemplarCodigos = new ArrayList<AsignacionValorCodigo>();
        // this.registros = new ArrayList<Registro>();
        this.registrosEjemplares = new ArrayList<RegistroEjemplar>();
    }

    /**
     * Constructor con parámetros
     *
     * @param fechaIngresoInventario
     * @param fechaRegistro
     * @param fechaUltimaDevolucion
     * @param imprimirEtiqueta
     * @param imprimirTejuelo
     * @param visibleOpac
     * @param codigoBarras
     * @param signatura
     * @param signaturaSuplementaria
     * @param volumen
     * @param numeroRegistro
     * @param texto
     * @param numeroPrestamos
     */
    @Deprecated
    public Ejemplar(Date fechaIngresoInventario, Date fechaRegistro,
            Date fechaUltimaDevolucion, Boolean imprimirEtiqueta,
            Boolean imprimirTejuelo, Boolean visibleOpac, String codigoBarras,
            String signatura, String signaturaSuplementaria, String volumen,
            Integer numeroRegistro, String texto, Integer numeroPrestamos,
            Date fechaCambioSituacion, Boolean imprimirCodigoBarras,
            Date fechaModificacion) {
        this.fechaIngresoInventario = fechaIngresoInventario;
        this.fechaRegistro = fechaRegistro;
        this.fechaUltimaDevolucion = fechaUltimaDevolucion;
        this.imprimirEtiqueta = imprimirEtiqueta;
        this.imprimirTejuelo = imprimirTejuelo;
        this.visibleOpac = visibleOpac;
        this.codigoBarras = codigoBarras;
        this.signatura = signatura;
        this.signaturaSuplementaria = signaturaSuplementaria;
        this.volumen = volumen;
        this.numeroRegistro = numeroRegistro;
        this.texto = texto;
        this.numeroPrestamos = numeroPrestamos;
        this.fechaCambioSituacion = fechaCambioSituacion;
        this.imprimirCodigoBarras = imprimirCodigoBarras;
        this.fechaModificacion = fechaModificacion;
        ejemplarCodigos = new ArrayList<AsignacionValorCodigo>();
        // this.registros = new ArrayList<Registro>();
        this.registrosEjemplares = new ArrayList<RegistroEjemplar>();

    }

    /**
     * constructor que crea una instancia de Ejemplar que se utilizará en sesión
     * para mostrar la información obtenida de NCIP
     */
    public Ejemplar(Long idNcip) {
        // establecemos el identificador remoto NCIP
        setIdNcip(idNcip);

        // establecemos la propiedad temporal para que este POJO
        // no se pueda guardar en BBDD, genericDao.save(lectorTemporal);
        // devolvería una excepcion
        setTemporal(true);
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Ejemplar.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Ejemplar.ID_SEQUENCE_NAME)
    @Column(name = Ejemplar.COLUMN_NAME_ID, nullable = false)
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
     * @return the fechaIngresoInventario
     */
    @Column(name = Ejemplar.COLUMN_NAME_FECHA_INGRESO_INVENTARIO)
    public Date getFechaIngresoInventario() {
        return fechaIngresoInventario;
    }

    /**
     * @param fechaIngresoInventario
     *            the fechaIngresoInventario to set
     */
    public void setFechaIngresoInventario(final Date fechaIngresoInventario) {
        this.fechaIngresoInventario = fechaIngresoInventario;
    }

    /**
     * @return the fechaRegistro
     */
    @Column(name = Ejemplar.COLUMN_NAME_FECHA_REGISTRO)
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro
     *            the fechaRegistro to set
     */
    public void setFechaRegistro(final Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaUltimaDevolucion
     */
    @Column(name = Ejemplar.COLUMN_NAME_FECHA_ULTIMA_DEVOLUCION)
    public Date getFechaUltimaDevolucion() {
        return fechaUltimaDevolucion;
    }

    /**
     * @param fechaUltimaDevolucion
     *            the fechaUltimaDevolucion to set
     */
    public void setFechaUltimaDevolucion(final Date fechaUltimaDevolucion) {
        this.fechaUltimaDevolucion = fechaUltimaDevolucion;
    }

    /**
     * @return the imprimirEtiqueta
     */
    @Column(name = Ejemplar.COLUMN_NAME_IMPRIMIR_ETIQUETA, nullable = false)
    public Boolean getImprimirEtiqueta() {
        return imprimirEtiqueta;
    }

    /**
     * @param imprimirEtiqueta
     *            the imprimirEtiqueta to set
     */
    public void setImprimirEtiqueta(final Boolean imprimirEtiqueta) {
        this.imprimirEtiqueta = imprimirEtiqueta;
    }

    /**
     * @return the imprimirTejuelo
     */
    @Column(name = Ejemplar.COLUMN_NAME_IMPRIMIR_TEJUELO, nullable = false)
    public Boolean getImprimirTejuelo() {
        return imprimirTejuelo;
    }

    /**
     * @param imprimirTejuelo
     *            the imprimirTejuelo to set
     */
    public void setImprimirTejuelo(final Boolean imprimirTejuelo) {
        this.imprimirTejuelo = imprimirTejuelo;
    }

    /**
     * @return the visibleOpac
     */
    @Column(name = Ejemplar.COLUMN_NAME_VISIBLE_OPAC, nullable = false)
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
     * @return the codigoBarras
     */
    @Column(name = Ejemplar.COLUMN_NAME_CODIGO_BARRAS, nullable = false, unique = true, length = 40)
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * @param codigoBarras
     *            the codigoBarras to set
     */
    public void setCodigoBarras(final String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    /**
     * @return the signatura
     */
    @Column(name = Ejemplar.COLUMN_NAME_SIGNATURA, length = 80)
    public String getSignatura() {
        return signatura;
    }

    /**
     * @param signatura
     *            the signatura to set
     */
    public void setSignatura(final String signatura) {
        this.signatura = signatura;
    }

    /**
     * @return the signaturaSuplementaria
     */
    @Column(name = Ejemplar.COLUMN_NAME_SIGNATURA_SUPLEMENTARIA, length = 80)
    public String getSignaturaSuplementaria() {
        return signaturaSuplementaria;
    }

    /**
     * @param signaturaSuplementaria
     *            the signaturaSuplementaria to set
     */
    public void setSignaturaSuplementaria(final String signaturaSuplementaria) {
        this.signaturaSuplementaria = signaturaSuplementaria;
    }

    /**
     * @return the volumen
     */
    @Column(name = Ejemplar.COLUMN_NAME_VOLUMEN, length = 80)
    public String getVolumen() {
        return volumen;
    }

    /**
     * @param volumen
     *            the volumen to set
     */
    public void setVolumen(final String volumen) {
        this.volumen = volumen;
    }

    /**
     * @return the numeroRegistro
     */
    @Column(name = Ejemplar.COLUMN_NAME_NUMERO_REGISTRO, nullable = false)
    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }

    /**
     * @param numeroRegistro
     *            the numeroRegistro to set
     */
    public void setNumeroRegistro(final Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    /**
     * @return the texto
     */
    @Column(name = Ejemplar.COLUMN_NAME_TEXTO, length = 80)
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
     * @return the numeroPrestamos
     */
    @Column(name = Ejemplar.COLUMN_NAME_NUMERO_PRESTAMOS)
    public Integer getNumeroPrestamos() {
        return numeroPrestamos;
    }

    /**
     * @param numeroPrestamos
     *            the numeroPrestamos to set
     */
    public void setNumeroPrestamos(final Integer numeroPrestamos) {
        this.numeroPrestamos = numeroPrestamos;
    }

    /**
     * @return the imprimirCodigoBarras
     */
    @Column(name = Ejemplar.COLUMN_NAME_IMPRIMIR_CODIGO_BARRA, nullable = false)
    public Boolean getImprimirCodigoBarras() {
        return imprimirCodigoBarras;
    }

    /**
     * @param imprimirCodigoBarras
     *            the imprimirCodigoBarras to set
     */
    public void setImprimirCodigoBarras(final Boolean imp) {
        imprimirCodigoBarras = imp;
    }

    /**
     * @return the fechaModificacion
     */
    @Column(name = Ejemplar.COLUMN_NAME_FECHA_MODIFICACION)
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion
     *            the fechaModificacion to set
     */
    public void setFechaModificacion(final Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the fechaCambioSituacion
     */
    @Column(name = Ejemplar.COLUMN_NAME_CAMBIO_SITUACION)
    public Date getFechaCambioSituacion() {
        return fechaCambioSituacion;
    }

    /**
     * @param fechaCambioSituacion
     *            the fechaCambioSituacion to set
     */
    public void setFechaCambioSituacion(final Date fechaCambioSituacion) {
        this.fechaCambioSituacion = fechaCambioSituacion;
    }

    /**
     * @return the fechaPrestamo
     */
    @Column(name = Ejemplar.COLUMN_NAME_FECHA_PRESTAMO)
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * @param fechaPrestamo
     *            the fechaPrestamo to set
     */
    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * @return the fechaDevolucion
     */
    @Column(name = Ejemplar.COLUMN_NAME_FECHA_DEVOLUCION)
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * @param fechaDevolucion
     *            the fechaDevolucion to set
     */
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * @return the ejemplarEstado
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = EjemplarEstado.class)
    @JoinColumn(name = Ejemplar.COLUMN_NAME_EJEMPLAR_ESTADO_FK, nullable = false)
    public EjemplarEstado getEjemplarEstado() {
        return ejemplarEstado;
    }

    /**
     * @param ejemplarEstado
     *            the ejemplarEstado to set
     */
    public void setEjemplarEstado(final EjemplarEstado ejemplarEstado) {
        this.ejemplarEstado = ejemplarEstado;
    }

    /**
     * @return the ejemplarTipo
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = EjemplarTipo.class)
    @JoinColumn(name = Ejemplar.COLUMN_NAME_EJEMPLAR_TIPO_FK, nullable = false)
    public EjemplarTipo getEjemplarTipo() {
        return ejemplarTipo;
    }

    /**
     * @param ejemplarTipo
     *            the ejemplarTipo to set
     */
    public void setEjemplarTipo(final EjemplarTipo ejemplarTipo) {
        this.ejemplarTipo = ejemplarTipo;
    }

    /**
     * @return the ejemplarSoporte
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = EjemplarSoporte.class)
    @JoinColumn(name = Ejemplar.COLUMN_NAME_EJEMPLAR_SOPORTE_FK)
    public EjemplarSoporte getEjemplarSoporte() {
        return ejemplarSoporte;
    }

    /**
     * @param ejemplarSoporte
     *            the ejemplarSoporte to set
     */
    public void setEjemplarSoporte(final EjemplarSoporte ejemplarSoporte) {
        this.ejemplarSoporte = ejemplarSoporte;
    }

    /**
     * @return the ejemplarSituacion
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = EjemplarSituacion.class)
    @JoinColumn(name = Ejemplar.COLUMN_NAME_EJEMPLAR_SITUACION_FK, nullable = false)
    public EjemplarSituacion getEjemplarSituacion() {
        return ejemplarSituacion;
    }

    /**
     * @param ejemplarSituacion
     *            the ejemplarSituacion to set
     */
    public void setEjemplarSituacion(final EjemplarSituacion ejemplarSituacion) {
        this.ejemplarSituacion = ejemplarSituacion;
    }

    /**
     * @return the procedencia
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = Procedencia.class)
    @JoinColumn(name = Ejemplar.COLUMN_NAME_PROCEDENCIA_FK, nullable = false)
    public Procedencia getProcedencia() {
        return procedencia;
    }

    /**
     * @param procedencia
     *            the procedencia to set
     */
    public void setProcedencia(final Procedencia procedencia) {
        this.procedencia = procedencia;
    }

    /**
     * @return the ejemplarLocalizacion
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = EjemplarLocalizacion.class)
    @JoinColumn(name = Ejemplar.COLUMN_NAME_EJEMPLAR_LOCALIZACION_FK, nullable = false)
    public EjemplarLocalizacion getEjemplarLocalizacion() {
        return ejemplarLocalizacion;
    }

    /**
     * @param ejemplarLocalizacion
     *            the ejemplarLocalizacion to set
     */
    public void setEjemplarLocalizacion(
            final EjemplarLocalizacion ejemplarLocalizacion) {
        this.ejemplarLocalizacion = ejemplarLocalizacion;
    }

    /**
     * @return the restriccionUso
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = RestriccionUso.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Ejemplar.COLUMN_NAME_RESTRICCION_USO_FK)
    public RestriccionUso getRestriccionUso() {
        return restriccionUso;
    }

    /**
     * @param restriccionUso
     *            the restriccionUso to set
     */
    public void setRestriccionUso(final RestriccionUso restriccionUso) {
        this.restriccionUso = restriccionUso;
    }

    /**
     * @return the registros
     */
    // @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST },
    // targetEntity = org.librae.catalogacion.model.RegistroEjemplar.class)
    // @JoinColumn(name = Ejemplar.COLUMN_NAME_REGISTRO_FK)
    // public Registro getRegistro() {
    // return registro;
    // }
    /**
     * @param registros
     *            the registros to set
     */
    // public void setRegistro(final Registro registro) {
    // this.registro = registro;
    // }
    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = Ejemplar.COLUMN_NAME_BIBLIOTECA_FK, nullable = false)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_EJ")
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
     * @return the estadoCirculacion
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = org.librae.circulacion.model.EstadoCirculacion.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Ejemplar.COLUMN_NAME_ESTADO_CIRCULACION_FK)
    public EstadoCirculacion getEstadoCirculacion() {
        return estadoCirculacion;
    }

    /**
     * @param estadoCirculacion
     *            the estadoCirculacion to set
     */
    public void setEstadoCirculacion(EstadoCirculacion estadoCirculacion) {
        this.estadoCirculacion = estadoCirculacion;
    }

    /**
     * @return the ejemplarCodigos
     */
    @OneToMany(mappedBy = AsignacionValorCodigo.PROPTY_NAME_EJEMPLAR, targetEntity = org.librae.adminconfig.model.AsignacionValorCodigo.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = AsignacionValorCodigo.COLUMN_NAME_EJEMPLAR)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<AsignacionValorCodigo> getEjemplarCodigos() {
        return ejemplarCodigos;
    }

    /**
     * @param ejemplarCodigos
     *            the ejemplarCodigos to set
     */
    public void setEjemplarCodigos(List<AsignacionValorCodigo> ejemplarCodigos) {
        this.ejemplarCodigos = ejemplarCodigos;
    }

    /**
     * @return the registrosEjemplares
     */
    @OneToMany(mappedBy = RegistroEjemplar.PROPTY_NAME_EJEMPLAR, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.catalogacion.model.RegistroEjemplar.class, fetch = FetchType.LAZY)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
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
     * @return the prestamo
     */
    // @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
    // targetEntity = org.librae.circulacion.model.Prestamo.class, fetch =
    // FetchType.LAZY)
    // @JoinColumn(name = Ejemplar.COLUMN_NAME_ID, nullable = false)
    // @ForeignKey(name = "EJEMPLAR")
    @Transient
    public Prestamo getPrestamo() {
        Prestamo pres = null;
        if (this.getPrestamos().size() > 0) {
            pres = this.getPrestamos().get(0);
        }
        return pres;
    }

    /**
     * @param prestamo
     *            the prestamo to set
     */
    public void setPrestamo(final Prestamo prestamo) {
        this.getPrestamos().add(prestamo);
        // this.prestamo = prestamo;
    }

    /**
     * @return the registrosEjemplares
     */
    @OneToMany(mappedBy = Prestamo.PROPTY_NAME_EJEMPLAR, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.circulacion.model.Prestamo.class, fetch = FetchType.LAZY)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    /**
     * @param registrosEjemplares
     *            the registrosEjemplares to set
     */
    public void setPrestamos(final List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @OneToMany(mappedBy = PrestamoHistorico.PROPTY_NAME_EJEMPLAR, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.circulacion.model.PrestamoHistorico.class, fetch = FetchType.LAZY)
    @Cascade( { org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    public List<PrestamoHistorico> getPrestamosHistoricos() {
        return prestamosHistoricos;
    }

    public void setPrestamosHistoricos(
            List<PrestamoHistorico> prestamosHistoricos) {
        this.prestamosHistoricos = prestamosHistoricos;
    }

    /**
     * Método que devuelve el identificador del registro que se corresponde con
     * el título principal del ejemplar.
     *
     * @param registrosEjemplares
     */
    @Transient
    public String getRegistroPrincipal() {
        String idRegistro = null;

        Registro registroPrincipal = getRegPrincipal();

        if (registroPrincipal != null && registroPrincipal.getId() != null) {
            idRegistro = String.valueOf(registroPrincipal.getId());
        }

        return idRegistro;
    }

    /**
     * Método que devuelve el registro que se corresponde con el título
     * principal del ejemplar.
     *
     * @param registrosEjemplares
     */
    @Transient
    public Registro getRegPrincipal() {
        Registro registroPrincipal = null;

        if (this.registrosEjemplares != null) {
            Iterator<RegistroEjemplar> it = this.registrosEjemplares.iterator();
            Boolean enc = Boolean.FALSE;

            while (it.hasNext() && !enc) {
                RegistroEjemplar registroEjemplar = it.next();
                enc = registroEjemplar.getPrincipal();

                if (enc) {
                    registroPrincipal = registroEjemplar.getRegistro();
                }
            }
        }

        return registroPrincipal;
    }

    /**
     * Método que devuelve el registroEjemplar principal correspondiente al
     * ejemplar this.
     *
     * @param registrosEjemplares
     */
    @Transient
    public RegistroEjemplar getRegistroEjemplar() {

        if (this.registrosEjemplares != null) {
            Iterator<RegistroEjemplar> it = this.registrosEjemplares.iterator();
            Boolean enc = Boolean.FALSE;

            while (it.hasNext() && !enc) {
                RegistroEjemplar registroEjemplar = it.next();
                if (registroEjemplar.getPrincipal()) {
                    return registroEjemplar;
                }
            }
        }
        return null;
    }

    /*
     * Propiedades transient para mostrarse en el listado de ejemplares
     */
    @Transient
    public String getAutor() {
        if (null != this.getRegPrincipal()
                && null != this.getRegPrincipal().getAutor())
            return this.getRegPrincipal().getAutor();
        else
            return "";
    }

    @Transient
    public String getTitulo() {
        if (null != this.getRegPrincipal()
                && null != this.getRegPrincipal().getTitulo())
            return this.getRegPrincipal().getTitulo();
        else
            return "";
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
                + ((biblioteca == null) ? 0 : biblioteca.hashCode());
        result = prime * result
                + ((codigoBarras == null) ? 0 : codigoBarras.hashCode());
        result = prime * result
                + ((ejemplarEstado == null) ? 0 : ejemplarEstado.hashCode());
        result = prime
                * result
                + ((ejemplarLocalizacion == null) ? 0 : ejemplarLocalizacion
                        .hashCode());
        result = prime
                * result
                + ((ejemplarSituacion == null) ? 0 : ejemplarSituacion
                        .hashCode());
        result = prime * result
                + ((ejemplarSoporte == null) ? 0 : ejemplarSoporte.hashCode());
        result = prime * result
                + ((ejemplarTipo == null) ? 0 : ejemplarTipo.hashCode());
        result = prime
                * result
                + ((fechaCambioSituacion == null) ? 0 : fechaCambioSituacion
                        .hashCode());
        result = prime
                * result
                + ((fechaIngresoInventario == null) ? 0
                        : fechaIngresoInventario.hashCode());
        result = prime
                * result
                + ((fechaModificacion == null) ? 0 : fechaModificacion
                        .hashCode());
        result = prime * result
                + ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
        result = prime
                * result
                + ((fechaUltimaDevolucion == null) ? 0 : fechaUltimaDevolucion
                        .hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime
                * result
                + ((imprimirCodigoBarras == null) ? 0 : imprimirCodigoBarras
                        .hashCode());
        result = prime
                * result
                + ((imprimirEtiqueta == null) ? 0 : imprimirEtiqueta.hashCode());
        result = prime * result
                + ((imprimirTejuelo == null) ? 0 : imprimirTejuelo.hashCode());
        result = prime * result
                + ((numeroPrestamos == null) ? 0 : numeroPrestamos.hashCode());
        result = prime * result
                + ((numeroRegistro == null) ? 0 : numeroRegistro.hashCode());
        result = prime * result
                + ((procedencia == null) ? 0 : procedencia.hashCode());
        result = prime * result
                + ((restriccionUso == null) ? 0 : restriccionUso.hashCode());
        result = prime * result
                + ((signatura == null) ? 0 : signatura.hashCode());
        result = prime
                * result
                + ((signaturaSuplementaria == null) ? 0
                        : signaturaSuplementaria.hashCode());
        result = prime * result + ((texto == null) ? 0 : texto.hashCode());
        result = prime * result
                + ((visibleOpac == null) ? 0 : visibleOpac.hashCode());
        result = prime * result + ((volumen == null) ? 0 : volumen.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ejemplar)) {
            return false;
        }
        final Ejemplar other = (Ejemplar) obj;
        if (biblioteca == null) {
            if (other.biblioteca != null) {
                return false;
            }
        } else if (!biblioteca.equals(other.biblioteca)) {
            return false;
        }
        if (codigoBarras == null) {
            if (other.codigoBarras != null) {
                return false;
            }
        } else if (!codigoBarras.equals(other.codigoBarras)) {
            return false;
        }
        if (ejemplarEstado == null) {
            if (other.ejemplarEstado != null) {
                return false;
            }
        } else if (!ejemplarEstado.equals(other.ejemplarEstado)) {
            return false;
        }
        if (ejemplarLocalizacion == null) {
            if (other.ejemplarLocalizacion != null) {
                return false;
            }
        } else if (!ejemplarLocalizacion.equals(other.ejemplarLocalizacion)) {
            return false;
        }
        if (ejemplarSituacion == null) {
            if (other.ejemplarSituacion != null) {
                return false;
            }
        } else if (!ejemplarSituacion.equals(other.ejemplarSituacion)) {
            return false;
        }
        if (ejemplarSoporte == null) {
            if (other.ejemplarSoporte != null) {
                return false;
            }
        } else if (!ejemplarSoporte.equals(other.ejemplarSoporte)) {
            return false;
        }
        if (ejemplarTipo == null) {
            if (other.ejemplarTipo != null) {
                return false;
            }
        } else if (!ejemplarTipo.equals(other.ejemplarTipo)) {
            return false;
        }
        if (fechaCambioSituacion == null) {
            if (other.fechaCambioSituacion != null) {
                return false;
            }
        } else if (!fechaCambioSituacion.equals(other.fechaCambioSituacion)) {
            return false;
        }
        if (fechaIngresoInventario == null) {
            if (other.fechaIngresoInventario != null) {
                return false;
            }
        } else if (!fechaIngresoInventario.equals(other.fechaIngresoInventario)) {
            return false;
        }
        if (fechaModificacion == null) {
            if (other.fechaModificacion != null) {
                return false;
            }
        } else if (!fechaModificacion.equals(other.fechaModificacion)) {
            return false;
        }
        if (fechaRegistro == null) {
            if (other.fechaRegistro != null) {
                return false;
            }
        } else if (!fechaRegistro.equals(other.fechaRegistro)) {
            return false;
        }
        if (fechaUltimaDevolucion == null) {
            if (other.fechaUltimaDevolucion != null) {
                return false;
            }
        } else if (!fechaUltimaDevolucion.equals(other.fechaUltimaDevolucion)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (imprimirCodigoBarras == null) {
            if (other.imprimirCodigoBarras != null) {
                return false;
            }
        } else if (!imprimirCodigoBarras.equals(other.imprimirCodigoBarras)) {
            return false;
        }
        if (imprimirEtiqueta == null) {
            if (other.imprimirEtiqueta != null) {
                return false;
            }
        } else if (!imprimirEtiqueta.equals(other.imprimirEtiqueta)) {
            return false;
        }
        if (imprimirTejuelo == null) {
            if (other.imprimirTejuelo != null) {
                return false;
            }
        } else if (!imprimirTejuelo.equals(other.imprimirTejuelo)) {
            return false;
        }
        if (numeroPrestamos == null) {
            if (other.numeroPrestamos != null) {
                return false;
            }
        } else if (!numeroPrestamos.equals(other.numeroPrestamos)) {
            return false;
        }
        if (numeroRegistro == null) {
            if (other.numeroRegistro != null) {
                return false;
            }
        } else if (!numeroRegistro.equals(other.numeroRegistro)) {
            return false;
        }
        if (procedencia == null) {
            if (other.procedencia != null) {
                return false;
            }
        } else if (!procedencia.equals(other.procedencia)) {
            return false;
        }
        if (restriccionUso == null) {
            if (other.restriccionUso != null) {
                return false;
            }
        } else if (!restriccionUso.equals(other.restriccionUso)) {
            return false;
        }
        if (signatura == null) {
            if (other.signatura != null) {
                return false;
            }
        } else if (!signatura.equals(other.signatura)) {
            return false;
        }
        if (signaturaSuplementaria == null) {
            if (other.signaturaSuplementaria != null) {
                return false;
            }
        } else if (!signaturaSuplementaria.equals(other.signaturaSuplementaria)) {
            return false;
        }
        if (texto == null) {
            if (other.texto != null) {
                return false;
            }
        } else if (!texto.equals(other.texto)) {
            return false;
        }
        if (visibleOpac == null) {
            if (other.visibleOpac != null) {
                return false;
            }
        } else if (!visibleOpac.equals(other.visibleOpac)) {
            return false;
        }
        if (volumen == null) {
            if (other.volumen != null) {
                return false;
            }
        } else if (!volumen.equals(other.volumen)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Ejemplar.COLUMN_NAME_ID, id)
                .append(Ejemplar.COLUMN_NAME_FECHA_INGRESO_INVENTARIO,
                        fechaIngresoInventario).append(
                        Ejemplar.COLUMN_NAME_FECHA_REGISTRO, fechaRegistro)
                .append(Ejemplar.COLUMN_NAME_FECHA_ULTIMA_DEVOLUCION,
                        fechaUltimaDevolucion).append(
                        Ejemplar.COLUMN_NAME_IMPRIMIR_ETIQUETA,
                        imprimirEtiqueta).append(
                        Ejemplar.COLUMN_NAME_IMPRIMIR_TEJUELO, imprimirTejuelo)
                .append(Ejemplar.COLUMN_NAME_VISIBLE_OPAC, visibleOpac).append(
                        Ejemplar.COLUMN_NAME_CODIGO_BARRAS, codigoBarras)
                .append(Ejemplar.COLUMN_NAME_SIGNATURA, signatura).append(
                        Ejemplar.COLUMN_NAME_ID, id).append(
                        Ejemplar.COLUMN_NAME_SIGNATURA_SUPLEMENTARIA,
                        signaturaSuplementaria).append(
                        Ejemplar.COLUMN_NAME_VOLUMEN, volumen).append(
                        Ejemplar.COLUMN_NAME_NUMERO_REGISTRO, numeroRegistro)
                .append(Ejemplar.COLUMN_NAME_TEXTO, texto).append(
                        Ejemplar.COLUMN_NAME_NUMERO_PRESTAMOS, numeroPrestamos)
                .append(Ejemplar.COLUMN_NAME_CAMBIO_SITUACION,
                        fechaCambioSituacion).append(
                        Ejemplar.COLUMN_NAME_IMPRIMIR_CODIGO_BARRA,
                        imprimirCodigoBarras).append(
                        Ejemplar.COLUMN_NAME_FECHA_MODIFICACION,
                        fechaModificacion).toString();
    }

    @Override
    public Ejemplar newInstance() {
        return new Ejemplar();
    }
}