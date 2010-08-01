package org.librae.lectores.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.librae.adminconfig.model.AsignacionValorCodigo;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.Tratamiento;
import org.librae.adquisicion.model.Desiderata;
import org.librae.circulacion.model.Prestamo;
import org.librae.circulacion.model.Reserva;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar un Lector
 * 
 * @author jcdiaz
 */
@Entity(name = Lector.ENTITY_NAME)
@Table(name = Lector.TABLE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = { Lector.COLUMN_NAME_NUMEROLECTOR }) })
public class Lector extends SpringRemotableLazyEntity<Lector> {

    /**
     * BaseObject is Serializable, por lo tanto Lector necesita un Serial
     * Version UID
     */
    private static final long           serialVersionUID                      = -5069994644504491820L;

    public static final String          ENTITY_NAME                           = "org.librae.lectores.model.Lector";

    /**
     * Constantes para referencia de los atributos de Lector
     */
    public static final String          PROPTY_NAME_ID                        = "id";
    public static final String          PROPTY_NAME_NOMBRE                    = "nombre";
    public static final String          PROPTY_NAME_PRIMER_APELLIDO           = "primerApellido";
    public static final String          PROPTY_NAME_SEGUNDO_APELLIDO          = "segundoApellido";
    public static final String          PROPTY_NAME_SEXO                      = "sexo";
    public static final String          PROPTY_NAME_FECHA_CADUCIDAD           = "fechaCaducidad";
    public static final String          PROPTY_NAME_FECHA_ALTA                = "fechaAlta";
    public static final String          PROPTY_NAME_FECHA_NACIMIENTO          = "fechaNacimiento";
    public static final String          PROPTY_NAME_FECHA_RENOVACION          = "fechaRenovacion";
    public static final String          PROPTY_NAME_FECHA_SUSPENSION_DESDE    = "fechaSuspensionDesde";
    public static final String          PROPTY_NAME_FECHA_SUSPENSION_HASTA    = "fechaSuspensionHasta";
    public static final String          PROPTY_NAME_FECHA_ULTIMO_USO          = "fechaUltimoUso";
    public static final String          PROPTY_NAME_FECHA_ULTIMO_USO_B        = "fechaUltimoUsoB";
    public static final String          PROPTY_NAME_BORRADO_DATOS             = "borradoDatos";
    public static final String          PROPTY_NAME_PENDIENTE_IMPRIMIR_CODIGO = "pendienteImprimirCodigo";
    public static final String          PROPTY_NAME_CODIGO_BARRAS             = "codigoBarras";
    public static final String          PROPTY_NAME_NUMERO_LECTOR             = "numeroLector";
    public static final String          PROPTY_NAME_CLAVE                     = "clave";
    public static final String          PROPTY_NAME_DNI                       = "dni";
    public static final String          PROPTY_NAME_DNI_ALTERNATIVO           = "dniAlternativo";
    public static final String          PROPTY_NAME_FAX                       = "fax";
    public static final String          PROPTY_NAME_MENSAJE                   = "mensaje";
    public static final String          PROPTY_NAME_MENSAJE_CIRCULACION       = "mensajeCirculacion";
    public static final String          PROPTY_NAME_USUARIO_PORTAL            = "usuarioPortal";
    public static final String          PROPTY_NAME_USUARIO_ASOCIADO          = "usuarioAsociado";
    public static final String          PROPTY_NAME_ESTADO                    = "estado";
    public static final String          PROPTY_NAME_TIPO_IDENTIFICACION       = "tipoIdentificacion";
    public static final String          PROPTY_NAME_TRATAMIENTO               = "tratamiento";
    public static final String          PROPTY_NAME_MOTIVO_BLOQUEO            = "motivoBloqueo";
    public static final String          PROPTY_NAME_BIBLIOTECA                = "biblioteca";
    public static final String          PROPTY_NAME_LECTOR                    = "lectorAsociado";
    public static final String          PROPTY_NAME_LECTOR_TELEFONOS          = "lectorTelefonos";
    public static final String          PROPTY_NAME_LECTOR_CORREOS            = "lectorCorreos";
    public static final String          PROPTY_NAME_LECTOR_DIRECCIONES        = "lectorDirecciones";
    public static final String          PROPTY_NAME_LECTOR_TIPOS              = "lectorTipos";
    public static final String          PROPTY_NAME_LECTOR_CODIGOS            = "lectorCodigos";
    public static final String          PROPTY_NAME_PRESTAMOS                 = "prestamos";
    public static final String          PROPTY_NAME_RESERVAS                  = "reservas";
    public static final String          PROPTY_NAME_DESIDERATAS               = "desideratas";
    public static final String          PROPTY_NAME_IMAGEN                    = "imagen";
    public static final String          PROPTY_NAME_IMAGEN_NOMBRE_FICHERO     = "imagenNombreFichero";
    public static final String          PROPTY_NAME_IMAGEN_CONTENT_TYPE       = "imagenContentType";

    /**
     * Constantes para referencia de los nombres de la tabla, secuencia para el
     * id, y nombres de las columnas en la base de datos
     */
    public static final String          TABLE_NAME                            = "LEC_LECTORES";
    public static final String          ID_GENERATOR_NAME                     = "generator_lec_lectores";
    public static final String          ID_SEQUENCE_NAME                      = "SEQ_LEC_LECTORES";

    public static final String          COLUMN_NAME_ID                        = "X_LECTOR";
    public static final String          COLUMN_NAME_NOMBRE                    = "T_NOMBRE";
    public static final String          COLUMN_NAME_PRIMER_APELLIDO           = "T_PRIMER_APELLIDO";
    public static final String          COLUMN_NAME_SEGUNDO_APELLIDO          = "T_SEGUNDO_APELLIDO";
    public static final String          COLUMN_NAME_SEXO                      = "D_SEXO";
    public static final String          COLUMN_NAME_FECHACADUCIDAD            = "F_FECHA_CADUCIDAD";
    public static final String          COLUMN_NAME_FECHAALTA                 = "F_FECHA_ALTA";
    public static final String          COLUMN_NAME_FECHANACIMIENTO           = "F_FECHA_NACIMIENTO";
    public static final String          COLUMN_NAME_FECHARENOVACION           = "F_FECHA_RENOVACION";
    public static final String          COLUMN_NAME_FECHASUSPENSIONDESDE      = "F_FECHA_SUSPENSION_DESDE";
    public static final String          COLUMN_NAME_FECHASUSPENSIONHASTA      = "F_FECHA_SUSPENSION_HASTA";
    public static final String          COLUMN_NAME_FECHAULTIMOUSO            = "F_FECHA_ULTIMO_USO";
    public static final String          COLUMN_NAME_FECHAULTIMOUSOB           = "F_FECHA_ULTIMO_USO_BIBLIOTECA";
    public static final String          COLUMN_NAME_BORRADODATOS              = "L_BORRADO_DATOS";
    public static final String          COLUMN_NAME_INDICEBLOQUEADO           = "L_INDICE_BLOQUEADO";
    public static final String          COLUMN_NAME_PENDIENTEIMPRIMIRCODIGO   = "L_IND_PENDIENTE_IMPRIMIR";
    public static final String          COLUMN_NAME_CODIGOBARRAS              = "N_CODIGO_BARRAS";
    public static final String          COLUMN_NAME_NUMEROLECTOR              = "N_NUMERO_LECTOR";
    public static final String          COLUMN_NAME_CLAVE                     = "T_CLAVE";
    public static final String          COLUMN_NAME_DNI                       = "T_DNI";
    public static final String          COLUMN_NAME_DNIALTERNATIVO            = "T_DNI_ALTERNATIVO";
    public static final String          COLUMN_NAME_FAX                       = "T_FAX";
    public static final String          COLUMN_NAME_MENSAJE                   = "T_MENSAJE";
    public static final String          COLUMN_NAME_MENSAJECIRCULACION        = "T_MENSAJE_CIRCULACION";
    public static final String          COLUMN_NAME_USUARIO_PORTAL            = "T_USUARIO_PORTAL";
    public static final String          COLUMN_NAME_USUARIO_ASOCIADO          = "L_USUARIO_ASOCIADO";
    public static final String          COLUMN_NAME_IMAGEN                    = "T_IMAGEN";
    public static final String          COLUMN_NAME_IMAGEN_NOMBRE_FICHERO     = "T_IMAGEN_NOMBRE_FICHERO";
    public static final String          COLUMN_NAME_IMAGEN_CONTENT_TYPE       = "T_IMAGEN_CONTENT_TYPE";

    public static final String          COLUMN_NAME_LECTOR_FK                 = "LEC_X_LEC_LECTOR";
    public static final String          COLUMN_NAME_LECTOR_TIPO_FK            = "LEC_X_LEC_TIPO";
    public static final String          COLUMN_NAME_MOTIVOBLOQUEO_FK          = "LEC_X_LEC_MOTIVO_BLOQUEO";
    public static final String          COLUMN_NAME_ESTADO_FK                 = "LEC_X_LEC_ESTADO";
    public static final String          COLUMN_NAME_TIPOIDENTIFICACION_FK     = "ADM_X_ADM_TIPO_IDENTIFICACION";
    public static final String          COLUMN_NAME_TRATAMIENTO_FK            = "LEC_X_LEC_LECTOR_TRATAMIENTO";
    public static final String          COLUMN_NAME_BIBLIOTECA_FK             = "BIB_X_BIOBLIOTECA";

    public static final String          TABLE_NAME_LECTOR_X_LECTORTIPO        = "LEC_LECTOR_X_LECTOR_TIPO";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                        id;

    /**
     * Nombre del lector (informado por el usuario) - lenomb
     */
    private String                      nombre;

    /**
     * Apellidos del lector (informado por el usuario) - leapel
     */
    private String                      primerApellido;

    /**
     * Apellidos del lector (informado por el usuario) - leapel
     */
    private String                      segundoApellido;

    /**
     * Sexo del lector H- Hombre, M- Mujer
     */
    private String                      sexo;

    /**
     * Fecha caducidad - lefcad
     */
    private Date                        fechaCaducidad;

    /**
     * Fecha registro -lefreg
     */
    private Date                        fechaAlta;

    /**
     * Fecha Nacimiento del lector (informado por el usuario) -lefnac
     */
    private Date                        fechaNacimiento;

    /**
     * Fecha renovación -lefren
     */
    private Date                        fechaRenovacion;

    /**
     * Fecha suspensión -lefsus
     */
    private Date                        fechaSuspensionDesde;

    /**
     * Fecha-hora de fin de suspensión
     */
    private Date                        fechaSuspensionHasta;

    /**
     * Fecha último uso - lefult
     */
    private Date                        fechaUltimoUso;

    /**
     * Fecha último uso de la biblioteca- lefubi
     */
    private Date                        fechaUltimoUsoB;

    /**
     * Indicativo de si el usuario decide que los datos personales relativos a
     * sus préstamos queden reflejado en el sistema (false) o no (true).
     */
    private Boolean                     borradoDatos;

    /**
     *Indica si el lector tiene pendiente de imprimir su código de barras
     */
    private Boolean                     pendienteImprimirCodigo;

    /**
     * Código Barras del Lector, formado por la concatenación de la red,
     * biblioteca y el número de lector
     */
    private String                      codigoBarras;

    /**
     * NúCódigo Barras del Lector, formado por la concatenación de la red,
     * biblioteca y el número de lector
     */
    private String                      numeroLector;

    /**
     * Número de lector, generado por el sistema automáticamente (lenlec)
     */
    private String                      clave;

    /**
     * DNI (informado por el usuario) - leddni
     */
    private String                      dni;

    /**
     * Nº Alternativo (informado por el usuario) - leddnn
     */
    private String                      dniAlternativo;

    /**
     * Fax del lector
     */
    private String                      fax;

    /**
     * Mensaje para el lector (lemess en modelo datos Absys)
     */
    private String                      mensaje;

    /**
     * Mensaje de circulación para el lector (lemeci en modelo datos Absys)
     */
    private String                      mensajeCirculacion;

    /**
     * Usuario de acceso al portal OPAC asociado al lector y proporcionado por
     * el propio portal durante la creación del lector en este.
     */
    private String                      usuarioPortal;

    /**
     * Booleano que indica si un usuario ha sido asociado a un usuario del OPAC.
     * Inicialmente se encuentra a False y cuando se realice la asociación se
     * seteará a True y se guardará el correo con el que se le ha asociado al
     * lector.
     */
    private Boolean                     usuarioAsociado                       = Boolean.FALSE;

    /**
     *Fichero que identifica la imagen del lector
     */
    private byte[]                      imagen;

    /**
     * Nombre del fichero que identifica la imagen del lector
     */
    private String                      imagenNombreFichero;

    /**
     * ContentType del fichero que identifica la imagen del lector
     */
    private String                      imagenContentType;

    /**
     * Clave foránea a la tabla de estados
     */
    private LectorEstado                estado;

    /**
     * Clave foránea a la tabla de tipos de identificación
     */
    private TipoIdentificacion          tipoIdentificacion;

    /**
     * Clave foránea a la tabla de tratamientos
     */
    private Tratamiento                 tratamiento;

    /**
     * Clave foránea a la tabla de motivos de bloqueo
     */
    private MotivoBloqueo               motivoBloqueo;

    /**
     * Clave foránea a la tabla de bibliotecas
     */
    private Biblioteca                  biblioteca;

    /**
     * Clave foránea a la misma tabla de Lectores para hacer referencia a los
     * lectores responsables de lectores infantiles
     */
    private Lector                      lectorAsociado;

    /**
     * Clave foránea a la tabla de teléfonos de un lector
     */
    private List<LectorTelefono>        lectorTelefonos;

    /**
     * Clave foránea a la tabla de correos de un lector
     */
    private List<LectorCorreo>          lectorCorreos;

    /**
     * Clave foránea a la tabla de direcciones de un lector
     */
    private List<LectorDireccion>       lectorDirecciones;

    /**
     * Clave foránea a la tabla de tipos de un lectores
     */
    private List<LectorTipo>            lectorTipos;

    /**
     * Clave foránea a la tabla de códigos
     */
    private List<AsignacionValorCodigo> lectorCodigos;

    /**
     * Clave foránea a la tabla de prestamos de un lector
     */
    private List<Prestamo>              prestamos;

    /**
     * Clave foránea a la tabla de reservas de un lector
     */
    private List<Reserva>               reservas;

    /**
     * Clave foránea a la tabla de reservas de un lector
     */
    private List<Desiderata>            desideratas;

    /**
     * Constructor sin parámetros
     */
    protected Lector() {
        super();
        lectorTelefonos = new ArrayList<LectorTelefono>();
        lectorCorreos = new ArrayList<LectorCorreo>();
        lectorDirecciones = new ArrayList<LectorDireccion>();
        lectorTipos = new ArrayList<LectorTipo>();
        lectorCodigos = new ArrayList<AsignacionValorCodigo>();
        prestamos = new ArrayList<Prestamo>();
        desideratas = new ArrayList<Desiderata>();
    }

    /**
     * Constructor con parámetros
     * 
     * @param apellidos
     * @param biblioteca
     * @param dni
     * @param lectorDirecciones
     * @param lectorTipos
     * @param nombre
     * @param tipoIdentificacion
     */
    public Lector(String primerApellido, Biblioteca biblioteca, String dni,
            List<LectorDireccion> lectorDirecciones,
            List<LectorTipo> lectorTipos, String nombre,
            TipoIdentificacion tipoIdentificacion) {
        super();
        this.primerApellido = primerApellido;
        this.biblioteca = biblioteca;
        this.dni = dni;
        this.lectorDirecciones = lectorDirecciones;
        this.lectorTipos = lectorTipos;
        this.nombre = nombre;
        this.tipoIdentificacion = tipoIdentificacion;
        lectorTelefonos = new ArrayList<LectorTelefono>();
        lectorCorreos = new ArrayList<LectorCorreo>();
        this.lectorDirecciones = new ArrayList<LectorDireccion>();
        this.lectorTipos = new ArrayList<LectorTipo>();
        lectorCodigos = new ArrayList<AsignacionValorCodigo>();
        prestamos = new ArrayList<Prestamo>();
    }

    /**
     * constructor que crea una instancia de Lector que se utilizará en sesión
     * para mostrar la información obtenida de NCIP
     */
    public Lector(Long idNcip) {
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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Lector.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Lector.ID_GENERATOR_NAME, sequenceName = Lector.ID_SEQUENCE_NAME)
    @Column(name = Lector.COLUMN_NAME_ID, nullable = false)
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
     * @return the nombre
     */
    @Column(name = Lector.COLUMN_NAME_NOMBRE, nullable = false, length = 40)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the primerApellido
     */
    @Column(name = Lector.COLUMN_NAME_PRIMER_APELLIDO, length = 25)
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * @param primerApellido
     *            the primerApellido to set
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the segundoApellido
     */
    @Column(name = Lector.COLUMN_NAME_SEGUNDO_APELLIDO, length = 25)
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * @param segundoApellido
     *            the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return estado
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.LectorEstado.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Lector.COLUMN_NAME_ESTADO_FK)
    public LectorEstado getEstado() {
        return estado;
    }

    /**
     * @param estado
     */
    public void setEstado(final LectorEstado estado) {
        this.estado = estado;
    }

    /**
     * @return the sexo
     */
    @Column(name = Lector.COLUMN_NAME_SEXO, length = 1)
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo
     *            the sexo to set
     */
    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return tipoIdentificacion
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.TipoIdentificacion.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Lector.COLUMN_NAME_TIPOIDENTIFICACION_FK)
    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    /**
     * @param tipoIdentificacion
     */
    public void setTipoIdentificacion(
            final TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    /**
     * @return tratamiento
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Tratamiento.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Lector.COLUMN_NAME_TRATAMIENTO_FK)
    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    /**
     * @param tratamiento
     */
    public void setTratamiento(final Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * @return the fechaCaducidad
     */
    @Column(name = Lector.COLUMN_NAME_FECHACADUCIDAD)
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * @param fechaCaducidad
     *            the fechaCaducidad to set
     */
    public void setFechaCaducidad(final Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * @return the fechaAlta
     */
    @Column(name = Lector.COLUMN_NAME_FECHAALTA)
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta
     *            the fechaAlta to set
     */
    public void setFechaAlta(final Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the fechaNacimiento
     */
    @Column(name = Lector.COLUMN_NAME_FECHANACIMIENTO)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento
     *            the fechaNacimiento to set
     */
    public void setFechaNacimiento(final Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the fechaRenovacion
     */
    @Column(name = Lector.COLUMN_NAME_FECHARENOVACION)
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
     * @return the fechaSuspensionDesde
     */
    @Column(name = Lector.COLUMN_NAME_FECHASUSPENSIONDESDE)
    public Date getFechaSuspensionDesde() {
        return fechaSuspensionDesde;
    }

    /**
     * @param fechaSuspensionDesde
     *            the fechaSuspensionDesde to set
     */
    public void setFechaSuspensionDesde(final Date fechaSuspensionDesde) {
        this.fechaSuspensionDesde = fechaSuspensionDesde;
    }

    /**
     * @return the fechaSuspensionHasta
     */
    @Column(name = Lector.COLUMN_NAME_FECHASUSPENSIONHASTA)
    public Date getFechaSuspensionHasta() {
        return fechaSuspensionHasta;
    }

    /**
     * @param fechaSuspensionHasta
     *            the fechaSuspensionHasta to set
     */
    public void setFechaSuspensionHasta(final Date fechaSuspensionHasta) {
        this.fechaSuspensionHasta = fechaSuspensionHasta;
    }

    /**
     * @return the fechaUltimoUso
     */
    @Column(name = Lector.COLUMN_NAME_FECHAULTIMOUSO)
    public Date getFechaUltimoUso() {
        return fechaUltimoUso;
    }

    /**
     * @param fechaUltimoUso
     *            the fechaUltimoUso to set
     */
    public void setFechaUltimoUso(final Date fechaUltimoUso) {
        this.fechaUltimoUso = fechaUltimoUso;
    }

    /**
     * @return the fechaUltimoUsoB
     */
    @Column(name = Lector.COLUMN_NAME_FECHAULTIMOUSOB)
    public Date getFechaUltimoUsoB() {
        return fechaUltimoUsoB;
    }

    /**
     * @param fechaUltimoUsoB
     *            the fechaUltimoUsoB to set
     */
    public void setFechaUltimoUsoB(final Date fechaUltimoUsoB) {
        this.fechaUltimoUsoB = fechaUltimoUsoB;
    }

    /**
     * @return the borradoDatos
     */
    @Column(name = Lector.COLUMN_NAME_BORRADODATOS)
    public Boolean getBorradoDatos() {
        return borradoDatos;
    }

    /**
     * @param borradoDatos
     *            the borradoDatos to set
     */
    public void setBorradoDatos(final Boolean borradoDatos) {
        this.borradoDatos = borradoDatos;
    }

    /**
     * @return the pendienteImprimirCodigo
     */
    @Column(name = Lector.COLUMN_NAME_PENDIENTEIMPRIMIRCODIGO)
    public Boolean getPendienteImprimirCodigo() {
        return pendienteImprimirCodigo;
    }

    /**
     * @param pendienteImprimirCodigo
     *            the pendienteImprimirCodigo to set
     */
    public void setPendienteImprimirCodigo(final Boolean pendienteImprimirCodigo) {
        this.pendienteImprimirCodigo = pendienteImprimirCodigo;
    }

    /**
     * @return the codigoBarras
     */
    // FIXME El valor de la propiedad es único, no se límita al no existir en
    // este momento el algoritmo de creación del código
    @Column(name = Lector.COLUMN_NAME_CODIGOBARRAS, nullable = false, length = 40)
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
     * @return the numeroLector
     */
    // FIXME El valor de la propiedad es único, no se límita al no existir en
    // este momento el algoritmo de creación del número
    @Column(name = Lector.COLUMN_NAME_NUMEROLECTOR, length = 40)
    public String getNumeroLector() {
        return numeroLector;
    }

    /**
     * @param numeroLector
     *            the numeroLector to set
     */
    public void setNumeroLector(final String numeroLector) {
        this.numeroLector = numeroLector;
    }

    /**
     * @return the clave
     */
    @Column(name = Lector.COLUMN_NAME_CLAVE, length = 40)
    public String getClave() {
        return clave;
    }

    /**
     * @param clave
     *            the clave to set
     */
    public void setClave(final String clave) {
        this.clave = clave;
    }

    /**
     * @return the dni
     */
    @Column(name = Lector.COLUMN_NAME_DNI, length = 20)
    public String getDni() {
        return dni;
    }

    /**
     * @param dni
     *            the dni to set
     */
    public void setDni(final String dni) {
        this.dni = dni;
    }

    /**
     * @return the dniAlternativo
     */
    @Column(name = Lector.COLUMN_NAME_DNIALTERNATIVO, length = 20)
    public String getDniAlternativo() {
        return dniAlternativo;
    }

    /**
     * @param dniAlternativo
     *            the dniAlternativo to set
     */
    public void setDniAlternativo(final String dniAlternativo) {
        this.dniAlternativo = dniAlternativo;
    }

    /**
     * @return the fax
     */
    @Column(name = Lector.COLUMN_NAME_FAX, length = 19)
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(final String fax) {
        this.fax = fax;
    }

    /**
     * @return the mensaje
     */
    @Column(name = Lector.COLUMN_NAME_MENSAJE, length = 255)
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje
     *            the mensaje to set
     */
    public void setMensaje(final String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the mensajeCirculacion
     */
    @Column(name = Lector.COLUMN_NAME_MENSAJECIRCULACION, length = 255)
    public String getMensajeCirculacion() {
        return mensajeCirculacion;
    }

    /**
     * @param mensajeCirculacion
     *            the mensajeCirculacion to set
     */
    public void setMensajeCirculacion(final String mensajeCirculacion) {
        this.mensajeCirculacion = mensajeCirculacion;
    }

    /**
     * @return the motivoBloqueo
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.MotivoBloqueo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Lector.COLUMN_NAME_MOTIVOBLOQUEO_FK)
    public MotivoBloqueo getMotivoBloqueo() {
        return motivoBloqueo;
    }

    /**
     * @param motivoBloqueo
     *            the motivoBloqueo to set
     */
    public void setMotivoBloqueo(final MotivoBloqueo motivoBloqueo) {
        this.motivoBloqueo = motivoBloqueo;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Lector.COLUMN_NAME_BIBLIOTECA_FK, nullable = false)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_LEC")
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
     * @return the lectorAsociado
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.Lector.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Lector.COLUMN_NAME_LECTOR_FK)
    public Lector getLectorAsociado() {
        return lectorAsociado;
    }

    /**
     * @param lectorAsociado
     *            the lectorAsociado to set
     */
    public void setLectorAsociado(Lector lectorAsociado) {
        this.lectorAsociado = lectorAsociado;
    }

    /**
     * @return the usuarioPortal
     */
    @Column(name = Lector.COLUMN_NAME_USUARIO_PORTAL, length = 80)
    public String getUsuarioPortal() {
        return usuarioPortal;
    }

    /**
     * @param usuarioPortal
     *            the usuarioPortal to set
     */
    public void setUsuarioPortal(final String usuarioPortal) {
        this.usuarioPortal = usuarioPortal;
    }

    /**
     * @return the usuarioAsociado
     */
    @Column(name = Lector.COLUMN_NAME_USUARIO_ASOCIADO)
    public Boolean getUsuarioAsociado() {
        return usuarioAsociado;
    }

    /**
     * @param usuarioAsociado
     *            the usuarioAsociado to set
     */
    public void setUsuarioAsociado(final Boolean usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    /**
     * @return the imagen
     */
    @Column(name = Lector.COLUMN_NAME_IMAGEN)
    @Lob
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen
     *            the imagen to set
     */
    public void setImagen(final byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the nombreFichero
     */
    @Column(name = Lector.COLUMN_NAME_IMAGEN_NOMBRE_FICHERO, length = 255)
    public String getImagenNombreFichero() {
        return imagenNombreFichero;
    }

    /**
     * @param nombreFichero
     *            the nombreFichero to set
     */
    public void setimagenNombreFichero(final String nombreFichero) {
        imagenNombreFichero = nombreFichero;
    }

    /**
     * @return the contentTypeFichero
     */
    @Column(name = Lector.COLUMN_NAME_IMAGEN_CONTENT_TYPE, length = 255)
    public String getImagenContentType() {
        return imagenContentType;
    }

    /**
     * @param contentType
     *            the contentType to set
     */
    public void setImagenContentType(final String contentType) {
        imagenContentType = contentType;
    }

    /**
     * @return the lectorDirecciones
     */
    // @OneToMany(mappedBy = LectorDireccion.PROPTY_NAME_LECTOR, cascade = {
    // CascadeType.ALL }, fetch = FetchType.LAZY)
    @OneToMany(mappedBy = LectorDireccion.PROPTY_NAME_LECTOR, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.LectorDireccion.class, fetch = FetchType.LAZY)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    @OrderBy(LectorDireccion.PROPTY_NAME_ORDEN)
    public List<LectorDireccion> getLectorDirecciones() {
        return lectorDirecciones;
    }

    /**
     * @param lectorDirecciones
     *            the lectorDirecciones to set
     */
    public void setLectorDirecciones(
            final List<LectorDireccion> lectorDirecciones) {
        this.lectorDirecciones = lectorDirecciones;
    }

    /**
     * @return lectorTelefonos
     */
    @OneToMany(mappedBy = LectorTelefono.PROPTY_NAME_LECTOR, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.LectorTelefono.class, fetch = FetchType.LAZY)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    @OrderBy(LectorTelefono.PROPTY_NAME_ORDEN)
    public List<LectorTelefono> getLectorTelefonos() {
        return lectorTelefonos;
    }

    /**
     * @param lectorTelefonos
     */
    public void setLectorTelefonos(final List<LectorTelefono> lectorTelefonos) {
        this.lectorTelefonos = lectorTelefonos;
    }

    /**
     * @return lectorCorreos
     */
    @OneToMany(mappedBy = LectorCorreo.PROPTY_NAME_LECTOR, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.LectorCorreo.class, fetch = FetchType.LAZY)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    @OrderBy(LectorCorreo.PROPTY_NAME_ORDEN)
    public List<LectorCorreo> getLectorCorreos() {
        return lectorCorreos;
    }

    /**
     * @param lectorCorreos
     */
    public void setLectorCorreos(final List<LectorCorreo> lectorCorreos) {
        this.lectorCorreos = lectorCorreos;
    }

    /**
     * @return the lectorTipos
     */
    // FIXME Hay que establecer esta propiedad como nulleable = false
    @OneToMany(mappedBy = LectorTipo.PROPTY_NAME_LECTOR, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.LectorTipo.class, fetch = FetchType.LAZY)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    public List<LectorTipo> getLectorTipos() {
        return lectorTipos;
    }

    /**
     * @param lectorTipos
     */
    public void setLectorTipos(final List<LectorTipo> lectorTipos) {
        this.lectorTipos = lectorTipos;
    }

    /**
     * @return lectorCodigos
     */
    @OneToMany(mappedBy = AsignacionValorCodigo.PROPTY_NAME_LECTOR, targetEntity = org.librae.adminconfig.model.AsignacionValorCodigo.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = AsignacionValorCodigo.COLUMN_NAME_LECTOR)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<AsignacionValorCodigo> getLectorCodigos() {
        return lectorCodigos;
    }

    /**
     * @param lectorCodigos
     */
    public void setLectorCodigos(final List<AsignacionValorCodigo> lectorCodigos) {
        this.lectorCodigos = lectorCodigos;
    }

    /**
     * @return the prestamos
     */
    @OneToMany(mappedBy = "lector", targetEntity = org.librae.circulacion.model.Prestamo.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = Prestamo.COLUMN_NAME_LECTOR)
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    /**
     * @param prestamos
     *            the prestamos to set
     */
    public void setPrestamos(final List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    /**
     * @return the reservas
     */
    @OneToMany(mappedBy = Reserva.PROPERTY_NAME_LECTOR, targetEntity = org.librae.circulacion.model.Reserva.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = Reserva.COLUMN_NAME_LECTOR)
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * @param reservas
     *            the reservas to set
     */
    public void setReservas(final List<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * @return the desideratas
     */
    @OneToMany(mappedBy = Desiderata.PROPERTY_NAME_LECTOR_FK, targetEntity = org.librae.adquisicion.model.Desiderata.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = Desiderata.COLUMN_NAME_LECTOR_FK)
    public List<Desiderata> getDesideratas() {
        return desideratas;
    }

    /**
     * @param desideratas
     *            the desideratas to set
     */
    public void setDesideratas(List<Desiderata> desideratas) {
        this.desideratas = desideratas;
    }

    /**
     * @param lectorTelefono
     */
    public void addLectorTelefono(LectorTelefono lectorTelefono) {
        lectorTelefono.setLector(this);
        lectorTelefonos.add(lectorTelefono);
    }

    /**
     * @param lectorCorreo
     */
    public void addLectorCorreo(final LectorCorreo lectorCorreo) {
        lectorCorreo.setLector(this);
        lectorCorreos.add(lectorCorreo);
    }

    /**
     * @param lectorTipo
     */
    public void addLectorTipo(LectorTipo lectorTipo) {
        lectorTipo.setLector(this);
        lectorTipos.add(lectorTipo);
    }

    /**
     * @param lectorDireccion
     */
    public void addLectorDireccion(LectorDireccion lectorDireccion) {
        lectorDireccion.setLector(this);
        lectorDirecciones.add(lectorDireccion);
    }

    /**
     * Devuelve el <code>styleClass</code> que debemos mostrar en el campo
     * <code>fechaDevolucionPol</code> fechaDevolucionPol
     * 
     * @return
     */
    @Transient
    public String getStyleClassEstadoLector() {

        String estilo = null;

        if (null != fechaCaducidad) {
            final Date hoy = new Date();
            final Date fechaCaducidad = getFechaCaducidad();

            if (hoy.compareTo(fechaCaducidad) > 0) {
                estilo = "lectorCaducado";
            } else if (hoy.compareTo(sumaDiasFecha(fechaCaducidad, -7)) > 0) {
                estilo = "lectorProximo";
            } else {
                estilo = "null";
            }
        }

        return estilo;
    }

    /**
     * Método que devuelve una fecha Date resultado de la suma de un número int
     * de días a una fecha Date.
     * 
     * @param fecha
     *            fecha a la que se le va a realizar la suma
     * @param dias
     *            número de días para sumar a la fecha
     * @return Date fecha resultado de la suma
     */
    @Transient
    private Date sumaDiasFecha(Date fecha, int dias) {
        final Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fecha.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Método que crea un objeto Lector nuevo con las entidades y listas
     * cargadas para la búsqueda del OPAC.
     */
    @Override
    public Lector clone() {
        // Se crea el nuevo objeto con los datos del objeto persistente
        final Lector lector = new Lector(primerApellido, getBiblioteca(), dni,
                getLectorDirecciones(), getLectorTipos(), nombre,
                getTipoIdentificacion());
        // Se setea el id
        lector.setId(id);
        // Se setean los datos básicos
        lector.setSegundoApellido(segundoApellido);
        lector.setSexo(sexo);
        lector.setFechaNacimiento(fechaNacimiento);
        // Se setean los correos
        lector.getLectorCorreos().addAll(getLectorCorreos());
        // Se setea el estado
        lector.setEstado(getEstado());
        return lector;

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
                + ((primerApellido == null) ? 0 : primerApellido.hashCode());
        result = prime * result + ((dni == null) ? 0 : dni.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((lectorTipos == null) ? 0 : lectorTipos.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        // result = prime
        // * result
        // + ((tipoIdentificacion == null) ? 0 : tipoIdentificacion
        // .hashCode());
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
        if (!(obj instanceof Lector)) {
            return false;
        }
        final Lector other = (Lector) obj;
        if (primerApellido == null) {
            if (other.primerApellido != null) {
                return false;
            }
        } else if (!primerApellido.equals(other.primerApellido)) {
            return false;
        }
        if (dni == null) {
            if (other.dni != null) {
                return false;
            }
        } else if (!dni.equals(other.dni)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (lectorTipos == null) {
            if (other.lectorTipos != null) {
                return false;
            }
        } else if (!lectorTipos.equals(other.lectorTipos)) {
            return false;
        }
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (tipoIdentificacion == null) {
            if (other.tipoIdentificacion != null) {
                return false;
            }
        } else if (!tipoIdentificacion.equals(other.tipoIdentificacion)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Lector.COLUMN_NAME_ID, id)
                .append(Lector.COLUMN_NAME_NOMBRE, nombre).append(
                        Lector.COLUMN_NAME_PRIMER_APELLIDO, primerApellido)
                .append(Lector.COLUMN_NAME_SEXO, sexo).append(
                        Lector.COLUMN_NAME_FECHACADUCIDAD, fechaCaducidad)
                .append(Lector.COLUMN_NAME_FECHAALTA, fechaAlta).append(
                        Lector.COLUMN_NAME_FECHANACIMIENTO, fechaNacimiento)
                .append(Lector.COLUMN_NAME_FECHARENOVACION, fechaRenovacion)
                .append(Lector.COLUMN_NAME_FECHAULTIMOUSO, fechaUltimoUso)
                .append(Lector.COLUMN_NAME_FECHAULTIMOUSOB, fechaUltimoUsoB)
                .append(Lector.COLUMN_NAME_FECHASUSPENSIONDESDE,
                        fechaSuspensionDesde).append(
                        Lector.COLUMN_NAME_FECHASUSPENSIONHASTA,
                        fechaSuspensionHasta).append(
                        Lector.COLUMN_NAME_BORRADODATOS, borradoDatos).append(
                        Lector.COLUMN_NAME_CODIGOBARRAS, codigoBarras).append(
                        Lector.COLUMN_NAME_NUMEROLECTOR, numeroLector).append(
                        Lector.COLUMN_NAME_CLAVE, clave).append(
                        Lector.COLUMN_NAME_DNI, dni).append(
                        Lector.COLUMN_NAME_DNIALTERNATIVO, dniAlternativo)
                .append(Lector.COLUMN_NAME_FAX, fax).append(
                        Lector.COLUMN_NAME_MENSAJE, mensaje).append(
                        Lector.COLUMN_NAME_MENSAJECIRCULACION,
                        mensajeCirculacion).toString();
    }

    @Override
    public Lector newInstance() {
        return new Lector();
    }

    @Transient
    public String getNombreCompleto() {
        String completo = "";

        if (this.getNombre() != null) {
            completo = this.getNombre() + " ";
        }
        if (this.getPrimerApellido() != null) {
            completo = completo + this.getPrimerApellido() + " ";
        }
        if (this.getSegundoApellido() != null) {
            completo = completo + this.getSegundoApellido();
        }

        return completo;
    }

    @Transient
    public String getEmailPrincipal() {
        String email = "";
        Integer valorProridad = new Integer(1);

        List<LectorCorreo> listaCorreos = this.getLectorCorreos();

        for (int i = 0; i < listaCorreos.size(); i++) {
            if (listaCorreos.get(i).getOrden().intValue() == valorProridad
                    .intValue()) {
                email = listaCorreos.get(i).getCorreo();
            }
        }

        return email;
    }

}