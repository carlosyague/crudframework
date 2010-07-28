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
import org.hibernate.annotations.ForeignKey;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.catalogacion.model.Ejemplar;
import org.librae.catalogacion.model.Registro;

import org.librae.common.model.SpringRemotableLazyEntity;
import org.librae.common.util.DateUtil;
import org.librae.lectores.model.Lector;

import org.librae.lectores.model.TipoLector;

/**
 * Reservas de los lectores.<br>
 * <br>
 * Restriccion 1:<br>
 * D_TIPO_RESERVA == "R" AND EJE_X_EJEMPLAR_ID IS NULL => F_INI_DISPONIBLE IS
 * NULL<br>
 * <br>
 * REstricción 2:<br>
 * F_INI_DISPONIBLE <= F_FIN_DISPONIBLE<br>
 * <br>
 * Esta tabla contiene todas las colas de reservas.<br>
 * <br>
 * Si la reserva es por ejmeplar (D_TIPO_RESERVA == "E"), entonces cada par
 * distinto de (EJE_X_EJEMPLAR_ID, BIB_X_BIBLIOTECA_REG) define una cola de
 * reservas.<br>
 * <br>
 * Si la reserva es por registro (D_TIPO_RESERVA == "R"), entonces cada terna
 * distinta de (REG_X_REGISTRO, BIB_X_BIBLIOTECA_REG, T_ID_VOLUMEN) define una
 * cola de reservas.<br>
 * <br>
 * Dentro de cada cola, las reservas se ordenan por N_PRIORIDAD primero y por
 * N_POSICION_RELATIVA después. Las reservas con N_PRIORIDAD mas bajo son las
 * que más cerca están de la cabecera de la cola.
 * 
 * @author asantamaria
 */
@Entity(name = Reserva.ENTITY_NAME)
@Table(name = Reserva.TABLE_NAME)
public class Reserva extends SpringRemotableLazyEntity<Reserva> {

    /**
     * BaseObject is Serializable, so Reserva needs a Serial Version UID
     */
    private static final long   serialVersionUID                       = -5100155092910964704L;

    public static final String  ENTITY_NAME                            = "org.librae.circulacion.model.Reserva";
    public static final String  TABLE_NAME                             = "CIR_RESERVAS";
    public static final String  ID_GENERATOR_NAME                      = "generator_cir_reservas";
    private static final String ID_SEQUENCE_NAME                       = "SEQ_CIR_RESERVAS";
    public static final String  COLUMN_NAME_ID                         = "X_RESERVA";
    public static final String  COLUMN_NAME_FECHA_RESERVA              = "F_RESERVA";
    public static final String  COLUMN_NAME_FECHA_DETECTA_SUSPENSION   = "F_DETECTA_SUSPENSION";
    public static final String  COLUMN_NAME_FECHA_INICIO_DISPONIBLE    = "F_INI_DISPONIBLE";
    public static final String  COLUMN_NAME_FECHA_FIN_DISPONIBLE       = "F_FIN_DISPONIBLE";
    public static final String  COLUMN_NAME_FECHA_INICIO_SUSPENSION    = "F_INI_SUSPENSION";
    public static final String  COLUMN_NAME_FECHA_FIN_SUSPENSION       = "F_FIN_SUSPENSION";
    public static final String  COLUMN_NAME_POSICION_RELATIVA          = "N_POSICION_RELATIVA";
    public static final String  COLUMN_NAME_DIAS_SUSPENSION            = "N_DIAS_SUSPENSION";
    public static final String  COLUMN_NAME_DIAS_RETIRADA_RESERVA      = "N_DIAS_RETIRADA_RESERVA";
    public static final String  COLUMN_NAME_DIAS_PERMANENCIA_EN_COLA   = "N_DIAS_PERMANENCIA_EN_COLA";
    public static final String  COLUMN_NAME_PRIORIDAD                  = "N_PRIORIDAD";
    public static final String  COLUMN_NAME_TIPO_RESERVA               = "T_RESERVA_X_T_RESERVA";
    public static final String  COLUMN_NAME_USUARIO_BIBLIOTECARIO      = "USU_X_BIBLIOTECARIO";
    public static final String  COLUMN_NAME_REGISTRO                   = "REG_X_REGISTRO";
    public static final String  COLUMN_NAME_BIBLIOTECA_REG             = "BIB_X_BIBLIOTECA_REG";
    public static final String  COLUMN_NAME_EJEMPLAR                   = "EJE_X_EJEMPLAR_ID";
    public static final String  COLUMN_NAME_LECTOR                     = "LEC_X_LECTOR";
    public static final String  COLUMN_NAME_LECTOR_TIPO                = "LEC_TIPO_X_LECTOR_TIPO";
    public static final String  COLUMN_NAME_PRESTAMO                   = "PRE_X_PRESTAMO";
    public static final String  COLUMN_NAME_ESTADO_RESERVA             = "EST_X_ESTADO_RESERVA";
    public static final String  COLUMN_NAME_ID_VOLUMEN                 = "T_ID_VOLUMEN";
    public static final String  COLUMN_NAME_PRESTAMO_HISTORICO         = "PRE_HIST_X_PRESTAMO_HISTORICO";
    public static final String  COLUMN_NAME_AUTOR                      = "T_AUTOR";
    public static final String  COLUMN_NAME_TITULO                     = "T_TITULO";

    public static final String  PROPERTY_NAME_ID                       = "id";
    public static final String  PROPERTY_NAME_FECHA_RESERVA            = "fechaReserva";
    public static final String  PROPTY_NAME_FECHA_DETECTA_SUSPENSION   = "fechaDetectaSuspension";
    public static final String  PROPERTY_NAME_FECHA_INICIO_DISPONIBLE  = "fechaInicioDisponible";
    public static final String  PROPERTY_NAME_FECHA_FIN_DISPONIBLE     = "fechaFinDisponible";
    public static final String  PROPERTY_NAME_FECHA_INI_SUSPENSION     = "fechaIniSuspension";
    public static final String  PROPERTY_NAME_FECHA_FIN_SUSPENSION     = "fechaFinSuspension";
    public static final String  PROPERTY_NAME_POSICION_RELATIVA        = "posicionRelativa";
    public static final String  PROPERTY_NAME_PRESTAMO_FINALIZADO      = "prestamoFinalizado";
    public static final String  PROPERTY_NAME_DIAS_SUSPENSION          = "diasSuspension";
    public static final String  PROPERTY_NAME_DIAS_RETIRADA_RESERVA    = "diasRetiradaReserva";
    public static final String  PROPERTY_NAME_DIAS_PERMANENCIA_EN_COLA = "diasPermanenciaEnCola";
    public static final String  PROPERTY_NAME_PRIORIDAD                = "prioridad";
    public static final String  PROPERTY_NAME_TIPO_RESERVA             = "tipoReserva";
    public static final String  PROPERTY_NAME_USUARIO_BIBLIOTECARIO    = "usuarioBibliotecario";
    public static final String  PROPERTY_NAME_REGISTRO                 = "registro";
    public static final String  PROPERTY_NAME_BIBLIOTECA_REG           = "bibliotecaReg";
    public static final String  PROPERTY_NAME_EJEMPLAR                 = "ejemplar";
    public static final String  PROPERTY_NAME_LECTOR                   = "lector";
    public static final String  PROPERTY_NAME_LECTOR_TIPO              = "lectorTipo";
    public static final String  PROPERTY_NAME_PRESTAMO                 = "prestamo";
    public static final String  PROPERTY_NAME_ESTADO_RESERVA           = "estadoReserva";
    public static final String  PROPERTY_NAME_ID_VOLUMEN               = "idVolumen";
    public static final String  PROPERTY_NAME_PRESTAMO_HISTORICO       = "prestamoHistorico";
    public static final String  PROPERTY_NAME_TITULO                   = "titulo";
    public static final String  PROPERTY_NAME_AUTOR                    = "autor";

    public static final String  TIPO_RESERVA_REGISTRO_BIBLIOGRAFICO    = "R";
    public static final String  TIPO_RESERVA_EJEMPLAR                  = "E";

    /**
     * Identificador interno del parámetro
     */
    private Long                id;

    /**
     * Fecha y hora en que se creó la reserva
     */
    private Date                fechaReserva;

    /**
     * Fecha-hora a la que un ejemplar del registro bibliográfico al que
     * corresponde la reserva, pasó a situación de disponible para que lo retire
     * en préstamo el lector propietario de esta reserva.
     */
    private Date                fechaInicioDisponible;

    /**
     * Hasta cuando está disponible el ejemplar asociado a la reserva, para que
     * el lector lo retire en préstamo.<br>
     * El valor por defecto es: primer día laborable (según el calendario de la
     * sucursal), siguiente a F_INI_DISPONIBLE + N_DIAS_RETIRADA_RESERVA
     */
    private Date                fechaFinDisponible;

    /**
     * Posición relativa de la reserva en la cola, entre las reservas con mismo
     * valor de N_PRIORIDAD<br>
     * Para cada grupo de reservas de la cola con mismo valor de N_PRIORIDAD:
     * Valor 0 para la reserva del grupo mas cercana a la cabecera de la cola<br>
     * Sólo se modifica cuando una reserva es movida dentro de su cola o cuando
     * una o más reservas de la cabecera abandonan la cola.
     */
    private Long                posicionRelativa;

    /**
     * Días de suspensión (dssu en Ab*NET).<br>
     * Número de días con los que se va a sancional al lector que no retire en
     * préstamo la reserva cuando esta está diponible para hacer efectivo del
     * préstamo.<br>
     * Se aplica este nº de días de suspensión si la reserva no es retirada en
     * préstamo.<br>
     * El significado es número de días por reserva no retirada<br>
     * Un valor 0 en esta propiedad significa que según esta política de
     * reservas no se aplican días de suspensión por no retirar en préstamo las
     * reservas
     */
    private Long                diasSuspension;

    /**
     * Cuantos días, contados a partir de la fecha-hora de vinculación del
     * ejemplar devuelto, con la reserva de la cola, tiene el lector propietario
     * de la reserva para hacer efectivo el préstamo
     */
    private Long                diasRetiradaReserva;

    /**
     * Cuantos días, a partir de la fecha-hora en que se produzca la vinculación
     * de un ejemplar con la reserva, va a permanecer la reserva en la cola de
     * reservas, si el lector no hace efectivo el préstamo.<br>
     * Transcurrido este tiempo, la reserva pasará a CIR_RESERVAS_HIST
     */
    private Long                diasPermanenciaEnCola;

    /**
     * Prioridad de las reserva<br>
     * 0 es la prioridad más alta, y 10 la más baja<br>
     * <br>
     * Restricción:<br>
     * BETWEEN 0 AND 10
     */
    private Long                prioridad;

    /**
     * Valores: R ó E<br>
     * R: Es una reserva por registro bibliográfico: EJE_X_EJEMPLAR_ID IS NULL
     * mientras no se devuelva un ejemplar del registro bibliográfico
     * REG_X_REGISTRO. El primer ejemplar de este registro que se devuelva, se
     * vinculará a la reserva.<br>
     * <br>
     * E: Es una reserva por ejemplar: EJE_X_EJEMPLAR_ID IS NOT NULL e indica el
     * ejemplar para el que existe la cola de reservas. Para este tipo
     * REG_X_REGISTRO no es relevante.
     */
    private TipoReserva         tipoReserva;

    /**
     * Usuario bibliotecario que realiza la reserva.<br>
     * NULL si la reserva se hizo vía un servicio (p.e. de NCIP).
     */
    private Usuario             usuarioBibliotecario;

    /**
     * >> REGISTROS<br>
     * Registro bibliográfico al que se refiere la reserva
     */
    private Registro            registro;

    /**
     * >> BIBLIOTECAS<br>
     * Sucursal de la reserva (que es la misma que la propietaria del ejemplar)<br>
     * <br>
     * Restricción semántica:<br>
     * La especificación de reservas establece que estas se hacen por
     * sucursales, por tanto, esta clave ajena a de referenciar a una fila de
     * tipo sucursal
     */
    private Biblioteca          bibliotecaReg;

    /**
     * >> FONDOS<br>
     * <br>
     * Si D_TIPO_RESERVA == "R", ejemplar que se asoció a la reserva, cuando un
     * ejemplar del registro bibliográfico reservado fue devuelto.<br>
     * NULL mientras la reserva no pase a situación de activa<br>
     * Las reservas de este tipo en situación de activas, verifican ademas que
     * F_INI_DISPONIBLE IS NOT NULL<br>
     * <br>
     * Si D_TIPO_RESERVA == "E", ejemplar reservado. En este caso, la reserva
     * activa se distingue de las otras de la cola porque F_INI_DISPONIBLE IS
     * NOT NULL.
     */
    private Ejemplar            ejemplar;

    /**
     * Identificación del lector en su biblioteca. Este valor debe ser único
     * para cada lector en su biblioteca.
     */
    private Lector              lector;

    /**
     * >> LECTORES_TIPOS
     */
    private TipoLector          lectorTipo;

    /**
     * >> CIR_PRESTAMOS<br>
     * null o no Según el valor de L_PRESTAMO FINALIZADO
     */
    private Prestamo            prestamo;

    /**
     * Estado de la reserva.
     */
    private EstadoReserva       estadoReserva;

    /**
     * Identificador de volumen para distinguir diferentes volúmenes de un mismo
     * registro bibliográfico. Por ejemplo en el caso de las enciclopedias en
     * varios volúmenes.<br>
     * La cumplimentación de este campo es opcional.
     */
    private String              idVolumen;

    /**
     * >> CIR_PRESTAMOS_HIST<br>
     * Según el valor de L_PRESTAMO FINALIZADO esará informado este campo o
     * prestamoHistorico. El otro será null
     */
    private PrestamoHistorico   prestamoHistorico;

    /**
     * Autor del registro bibliografico.
     */
    private String              autor;

    /**
     * Titulo del registro bibliografico.
     */
    private String              titulo;

    /**
     * Fecha de inicio de una suspensión
     */
    private Date                fechaIniSuspension;

    /**
     * Fecha de fin de una suspensión
     */
    private Date                fechaFinSuspension;

    /**
     * Fecha en que se ha detectado la suspensión del lector, por el proceso
     * planificado correspondiente
     */
    private Date                fechaDetectaSuspension;

    protected Reserva() {
        super();
    }

    public Reserva(final Lector lector, final Ejemplar ejemplar) {
        super();
        this.lector = lector;
        this.ejemplar = ejemplar;
    }

    public Reserva(final Lector lector, final Registro registro) {
        super();
        this.lector = lector;
        this.registro = registro;
    }

    /**
     * constructor que crea una instancia de Reserva que se utilizará en sesión
     * para mostrar la información obtenida de NCIP
     */

    public Reserva(Long idNcip) {
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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Reserva.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Reserva.ID_GENERATOR_NAME, sequenceName = Reserva.ID_SEQUENCE_NAME)
    @Column(name = Reserva.COLUMN_NAME_ID)
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
     * @return the fechaReserva
     */
    @Column(name = Reserva.COLUMN_NAME_FECHA_RESERVA)
    public Date getFechaReserva() {
        return fechaReserva;
    }

    /**
     * @param fechaReserva
     *            the fechaReserva to set
     */
    public void setFechaReserva(final Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * @return the fechaInicioDisponible
     */
    @Column(name = Reserva.COLUMN_NAME_FECHA_INICIO_DISPONIBLE)
    public Date getFechaInicioDisponible() {
        return fechaInicioDisponible;
    }

    /**
     * @param fechaInicioDisponible
     *            the fechaInicioDisponible to set
     */
    public void setFechaInicioDisponible(final Date fechaInicioDisponible) {
        this.fechaInicioDisponible = fechaInicioDisponible;
    }

    /**
     * @return the fechaFinDisponible
     */
    @Column(name = Reserva.COLUMN_NAME_FECHA_FIN_DISPONIBLE)
    public Date getFechaFinDisponible() {
        return fechaFinDisponible;
    }

    /**
     * @param fechaFinDisponible
     *            the fechaFinDisponible to set
     */
    public void setFechaFinDisponible(final Date fechaFinDisponible) {
        this.fechaFinDisponible = fechaFinDisponible;
    }

    /**
     * @return the posicionRelativa
     */
    @Column(name = Reserva.COLUMN_NAME_POSICION_RELATIVA)
    public Long getPosicionRelativa() {
        return posicionRelativa;
    }

    /**
     * @param posicionRelativa
     *            the posicionRelativa to set
     */
    public void setPosicionRelativa(final Long posicionRelativa) {
        this.posicionRelativa = posicionRelativa;
    }

    /**
     * @return the diasSuspension
     */
    @Column(name = Reserva.COLUMN_NAME_DIAS_SUSPENSION)
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
     * @return the diasRetiradaReserva
     */
    @Column(name = Reserva.COLUMN_NAME_DIAS_RETIRADA_RESERVA)
    public Long getDiasRetiradaReserva() {
        return diasRetiradaReserva;
    }

    /**
     * @param diasRetiradaReserva
     *            the diasRetiradaReserva to set
     */
    public void setDiasRetiradaReserva(final Long diasRetiradaReserva) {
        this.diasRetiradaReserva = diasRetiradaReserva;
    }

    /**
     * @return the diasPermanenciaEnCola
     */
    @Column(name = Reserva.COLUMN_NAME_DIAS_PERMANENCIA_EN_COLA)
    public Long getDiasPermanenciaEnCola() {
        return diasPermanenciaEnCola;
    }

    /**
     * @param diasPermanenciaEnCola
     *            the diasPermanenciaEnCola to set
     */
    public void setDiasPermanenciaEnCola(final Long diasPermanenciaEnCola) {
        this.diasPermanenciaEnCola = diasPermanenciaEnCola;
    }

    /**
     * @return the prioridad
     */
    @Column(name = Reserva.COLUMN_NAME_PRIORIDAD)
    public Long getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad
     *            the prioridad to set
     */
    public void setPrioridad(final Long prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the tipoReserva
     */
    @ManyToOne(targetEntity = TipoReserva.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Reserva.COLUMN_NAME_TIPO_RESERVA)
    public TipoReserva getTipoReserva() {
        return tipoReserva;
    }

    /**
     * @param tipoReserva
     *            the tipoReserva to set
     */
    public void setTipoReserva(final TipoReserva tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    /**
     * @return the usuarioBibliotecario
     */
    @ManyToOne(targetEntity = Usuario.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Reserva.COLUMN_NAME_USUARIO_BIBLIOTECARIO)
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
     * @return the registro
     */
    @ManyToOne(targetEntity = Registro.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Reserva.COLUMN_NAME_REGISTRO)
    public Registro getRegistro() {
        return registro;
    }

    /**
     * @param registro
     *            the registro to set
     */
    public void setRegistro(final Registro registro) {
        this.registro = registro;
    }

    /**
     * @return the bibliotecaReg
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Reserva.COLUMN_NAME_BIBLIOTECA_REG)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_REG")
    public Biblioteca getBibliotecaReg() {
        return bibliotecaReg;
    }

    /**
     * @param bibliotecaReg
     *            the bibliotecaReg to set
     */
    public void setBibliotecaReg(final Biblioteca bibliotecaReg) {
        this.bibliotecaReg = bibliotecaReg;
    }

    /**
     * @return the ejemplar
     */
    @ManyToOne(targetEntity = Ejemplar.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Reserva.COLUMN_NAME_EJEMPLAR)
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
     * @return the lector
     */
    @ManyToOne(targetEntity = Lector.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Reserva.COLUMN_NAME_LECTOR)
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
     * @return the lectorTipo
     */
    @ManyToOne(targetEntity = TipoLector.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Reserva.COLUMN_NAME_LECTOR_TIPO)
    public TipoLector getLectorTipo() {
        return lectorTipo;
    }

    /**
     * @param lectorTipo
     *            the lectorTipo to set
     */
    public void setLectorTipo(final TipoLector lectorTipo) {
        this.lectorTipo = lectorTipo;
    }

    /**
     * @return the prestamo
     */
    @ManyToOne(targetEntity = Prestamo.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = Reserva.COLUMN_NAME_PRESTAMO)
    public Prestamo getPrestamo() {
        return prestamo;
    }

    /**
     * @param prestamo
     *            the prestamo to set
     */
    public void setPrestamo(final Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    @ManyToOne(targetEntity = EstadoReserva.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = Reserva.COLUMN_NAME_ESTADO_RESERVA)
    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    /**
     * @return the idVolumen
     */
    @Column(name = Reserva.COLUMN_NAME_ID_VOLUMEN, length = 40)
    public String getIdVolumen() {
        return idVolumen;
    }

    /**
     * @param idVolumen
     *            the idVolumen to set
     */
    public void setIdVolumen(final String idVolumen) {
        this.idVolumen = idVolumen;
    }

    /**
     * @return the prestamoHistorico
     */
    @ManyToOne(targetEntity = PrestamoHistorico.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = ReservaHistorico.COLUMN_NAME_PRESTAMO_HISTORICO)
    public PrestamoHistorico getPrestamoHistorico() {
        return prestamoHistorico;
    }

    /**
     * @param prestamoHistorico
     *            the prestamoHistorico to set
     */
    public void setPrestamoHistorico(final PrestamoHistorico prestamoHistorico) {
        this.prestamoHistorico = prestamoHistorico;
    }

    /**
     * Estado de la Reserva - Todavia no Activa
     * 
     * @return true si la reserva todavia no esta activa, false en caso
     *         contrario.
     */
    @Transient
    public Boolean getTodaviaNoActiva() {
        return (fechaInicioDisponible == null) && (fechaFinDisponible == null);
    }

    /**
     * Estado de la Reserva - Activa
     * 
     * @return true si la reserva esta activa, false en caso contrario.
     */
    @Transient
    public Boolean getActiva() {
        if (fechaFinDisponible == null && fechaInicioDisponible == null) {
            return false;
        } else if (fechaFinDisponible == null && fechaInicioDisponible != null) {
            return true;
        } else {
            final int comparador = DateUtil.compararFechas(fechaFinDisponible,
                    new Date());
            return (fechaInicioDisponible != null)
                    && ((fechaFinDisponible != null) && (comparador != -1));
        }
    }

    /**
     * Estado de la Reserva - Expirada
     * 
     * @return true si la reserva ha expirado, false en caso contrario.
     */
    @Transient
    public Boolean getExpirada() {
        if (fechaFinDisponible == null) {
            return false;
        } else {
            final int comparador = DateUtil.compararFechas(fechaFinDisponible,
                    new Date());
            return (fechaInicioDisponible != null)
                    && ((fechaFinDisponible != null) && (comparador == -1));
        }
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
        if (!(obj instanceof Reserva)) {
            return false;
        }

        final Reserva other = (Reserva) obj;

        if (getLector() == null && other.getLector() != null) {
            return false;
        }
        if (getLector() != null && !getLector().equals(other.getLector())) {
            return false;
        }

        if (getEjemplar() == null && other.getEjemplar() != null) {
            return false;
        }
        if (getEjemplar() != null && !getEjemplar().equals(other.getEjemplar())) {
            return false;
        }

        if (getFechaReserva() == null && other.getFechaReserva() != null) {
            return false;
        }
        if (getFechaReserva() != null
                && !getFechaReserva().equals(other.getFechaReserva())) {
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
                + ((getEjemplar() == null) ? 0 : getEjemplar().hashCode());

        result = prime
                * result
                + ((getFechaReserva() == null) ? 0 : getFechaReserva()
                        .hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(Reserva.PROPERTY_NAME_ID, getId())

        .append(Reserva.PROPERTY_NAME_LECTOR,
                (getLector() == null) ? "" : getLector().toString())

        .append(Reserva.PROPERTY_NAME_EJEMPLAR,
                (getEjemplar() == null) ? "" : getEjemplar().toString())

                .append(
                        Reserva.PROPERTY_NAME_FECHA_RESERVA,
                        (getFechaReserva() == null) ? "" : getFechaReserva()
                                .toString())

                .toString();
    }

    @Override
    public Reserva newInstance() {
        return new Reserva();
    }

    /**
     * @return the autor
     */
    @Column(name = Reserva.COLUMN_NAME_AUTOR, length = 80)
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
    @Column(name = Reserva.COLUMN_NAME_TITULO, length = 80)
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
     * @return the fechaIniSuspension
     */
    @Column(name = Reserva.COLUMN_NAME_FECHA_INICIO_SUSPENSION, nullable = true)
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
    @Column(name = Reserva.COLUMN_NAME_FECHA_FIN_SUSPENSION, nullable = true)
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

    /**
     * @return the fechaDetectaSuspension
     */
    @Column(name = PrestamoHistorico.COLUMN_NAME_FECHA_DETECTA_SUSPENSION)
    public Date getFechaDetectaSuspension() {
        return fechaDetectaSuspension;
    }

    public void setFechaDetectaSuspension(Date fechaDetectaSuspension) {
        this.fechaDetectaSuspension = fechaDetectaSuspension;
    }

}