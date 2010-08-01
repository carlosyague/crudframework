package org.librae.circulacion.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.catalogacion.model.Ejemplar;
import org.librae.catalogacion.model.Registro;
import org.librae.common.model.BaseObject;
import org.librae.lectores.model.Lector;
import org.librae.lectores.model.LectorTipo;
import org.librae.lectores.model.TipoLector;

/**
 * Histórico de reservas de los lectores.<br>
 * <br>
 * Las reservas de esta tabla son:<br>
 * <br>
 * Reservas de CIR_RESERVAS en las que el lector formalizó el préstamo, o bien<br>
 * Reservas de CIR_RESERVAS en las que el lector no llegó a formalizar el
 * préstamo, por lo que al cabo de un tiempo N_DIAS_PERMANENCIA_EN_COLA, fueron
 * eliminadas de CIR_RESERVAS<br>
 * <br>
 * Véanse las restricciones documentadas en CIR_RESERVAS.
 * 
 * @author asantamaria
 */
// @Entity(name = ReservaHistorico.ENTITY_NAME)
// @Table(name = ReservaHistorico.TABLE_NAME)
public class ReservaHistorico extends BaseObject {

    /**
     * BaseObject is Serializable, so ReservaHistorico needs a Serial Version
     * UID
     */
    private static final long   serialVersionUID                         = 5931605594702558961L;

    public static final String  ENTITY_NAME                              = "org.librae.circulacion.model.ReservaHistorico";
    public static final String  TABLE_NAME                               = "CIR_RESERVAS_HIST";
    public static final String  ID_GENERATOR_NAME                        = "generator_cir_reservas_hist";
    private static final String ID_SEQUENCE_NAME                         = "SEQ_CIR_RESERVAS_HIST";
    public static final String  COLUMN_NAME_ID                           = "X_RESERVA";
    public static final String  COLUMN_NAME_FECHA_RESERVA                = "F_RESERVA";
    public static final String  COLUMN_NAME_FECHA_INICIO_DISPONIBLE      = "F_INI_DISPONIBLE";
    public static final String  COLUMN_NAME_FECHA_FIN_DISPONIBLE         = "F_FIN_DISPONIBLE";
    public static final String  COLUMN_NAME_DIAS_SUSPENSION              = "N_DIAS_SUSPENSION";
    public static final String  COLUMN_NAME_DIAS_RETIRADA_RESERVA        = "N_DIAS_RETIRADA_RESERVA";
    public static final String  COLUMN_NAME_DIAS_PERMANENCIA_EN_COLA     = "N_DIAS_PERMANENCIA_EN_COLA";
    public static final String  COLUMN_NAME_TIPO_RESERVA                 = "T_RESERVA_X_T_RESERVA";
    public static final String  COLUMN_NAME_FECHA_SUSPENSION_DETECTADA   = "F_SUSPENSION_DETECTADA";
    public static final String  COLUMN_NAME_USUARIO_BIBLIOTECARIO        = "USU_X_BIBLIOTECARIO";
    public static final String  COLUMN_NAME_REGISTRO                     = "REG_X_REGISTRO";
    public static final String  COLUMN_NAME_BIBLIOTECA_REG               = "BIB_X_BIBLIOTECA_REG";
    public static final String  COLUMN_NAME_EJEMPLAR                     = "EJE_X_EJEMPLAR";
    public static final String  COLUMN_NAME_LECTOR                       = "LEC_X_LECTOR";
    public static final String  COLUMN_NAME_TIPO_LECTOR                  = "LEC_TIPO_X_LECTOR_TIPO";
    public static final String  COLUMN_NAME_PRESTAMO_HISTORICO           = "PRE_HIST_X_PRESTAMO_HISTORICO";
    public static final String  COLUMN_NAME_FECHA_HISTORICO              = "F_HISTORICO";
    public static final String  COLUMN_NAME_ID_VOLUMEN                   = "T_ID_VOLUMEN";

    public static final String  PROPERTY_NAME_ID                         = "id";
    public static final String  PROPERTY_NAME_FECHA_RESERVA              = "fechaReserva";
    public static final String  PROPERTY_NAME_FECHA_INICIO_DISPONIBLE    = "fechaInicioDisponible";
    public static final String  PROPERTY_NAME_FECHA_FIN_DISPONIBLE       = "fechaFinDisponible";
    public static final String  PROPERTY_NAME_DIAS_SUSPENSION            = "diasSuspension";
    public static final String  PROPERTY_NAME_DIAS_RETIRADA_RESERVA      = "diasRetiradaReserva";
    public static final String  PROPERTY_NAME_DIAS_PERMANENCIA_EN_COLA   = "diasPermanenciaEnCola";
    public static final String  PROPERTY_NAME_TIPO_RESERVA               = "tipoReserva";
    public static final String  PROPERTY_NAME_FECHA_SUSPENSION_DETECTADA = "fechaSuspensionDetectada";
    public static final String  PROPERTY_NAME_USUARIO_BIBLIOTECARIO      = "usuarioBibliotecario";
    public static final String  PROPERTY_NAME_REGISTRO                   = "registro";
    public static final String  PROPERTY_NAME_BIBLIOTECA_REG             = "bibliotecaReg";
    public static final String  PROPERTY_NAME_EJEMPLAR                   = "ejemplar";
    public static final String  PROPERTY_NAME_LECTOR                     = "lector";
    public static final String  PROPERTY_NAME_LECTOR_TIPO                = "lectorTipo";
    public static final String  PROPERTY_NAME_PRESTAMO_HISTORICO         = "prestamoHistorico";
    public static final String  PROPERTY_NAME_FECHA_HISTORICO            = "fechaHistorico";
    public static final String  PROPERTY_NAME_ID_VOLUMEN                 = "idVolumen";

    /**
     * Clave primaria artificial
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
     * el lector lo retire en préstamo.
     */
    private Date                fechaFinDisponible;

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
     * Fecha y hora en que se comunicó al subsistema de lectores la suspensión
     * del lector por no retirar en préstamo la reserva.<br>
     * Normalmente, este campo lo informa el proceso planificado que procesa las
     * reservas
     */
    private Date                fechaSuspensionDetectada;

    /**
     * Usuario bibliotecario que realiza la reserva. NULL si la reserva se hizo
     * vía un servicio (p.e. de NCIP).
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
     * Ejemplar que se asoció a la reserva, cuando un ejemplar del registro
     * bibliográfico reservado fue devuelto.<br>
     * NULL mientras la reserva no pase a situación de activa
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
    private LectorTipo          lectorTipo;

    /**
     * >> CIR_PRESTAMOS_HIST<br>
     * Según el valor de L_PRESTAMO FINALIZADO esará informado este campo o
     * prestamoHistorico. El otro será null
     */
    private PrestamoHistorico   prestamoHistorico;

    /**
     * Fecha-hora en que la reserva pasó de la tabla CIR_RESERVAS a esta tabla
     */
    private Date                fechaHistorico;

    /**
     * Identificador de volumen para distinguir diferentes volúmenes de un mismo
     * registro bibliográfico. Por ejemplo en el caso de las enciclopedias en
     * varios volúmenes.<br>
     * La cumplimentación de este campo es opcional.
     */
    private String              idVolumen;

    protected ReservaHistorico() {
        super();
    }

    public ReservaHistorico(final Lector lector, final Ejemplar ejemplar) {
        super();
        this.lector = lector;
        this.ejemplar = ejemplar;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = ReservaHistorico.COLUMN_NAME_ID)
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
    @Column(name = ReservaHistorico.COLUMN_NAME_FECHA_RESERVA)
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
    @Column(name = ReservaHistorico.COLUMN_NAME_FECHA_INICIO_DISPONIBLE)
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
    @Column(name = ReservaHistorico.COLUMN_NAME_FECHA_FIN_DISPONIBLE)
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
     * @return the diasSuspension
     */
    @Column(name = ReservaHistorico.COLUMN_NAME_DIAS_SUSPENSION)
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
    @Column(name = ReservaHistorico.COLUMN_NAME_DIAS_RETIRADA_RESERVA)
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
    @Column(name = ReservaHistorico.COLUMN_NAME_DIAS_PERMANENCIA_EN_COLA)
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
     * @return the tipoReserva
     */
    @ManyToOne(targetEntity = TipoReserva.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = ReservaHistorico.COLUMN_NAME_TIPO_RESERVA)
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
     * @return the fechaSuspensionDetectada
     */
    @Column(name = ReservaHistorico.COLUMN_NAME_FECHA_SUSPENSION_DETECTADA)
    public Date getFechaSuspensionDetectada() {
        return fechaSuspensionDetectada;
    }

    /**
     * @param fechaSuspensionDetectada
     *            the fechaSuspensionDetectada to set
     */
    public void setFechaSuspensionDetectada(final Date fechaSuspensionDetectada) {
        this.fechaSuspensionDetectada = fechaSuspensionDetectada;
    }

    /**
     * @return the usuarioBibliotecario
     */
    @ManyToOne(targetEntity = Usuario.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = ReservaHistorico.COLUMN_NAME_USUARIO_BIBLIOTECARIO)
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
    @ManyToOne(targetEntity = Registro.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = ReservaHistorico.COLUMN_NAME_REGISTRO)
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
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = ReservaHistorico.COLUMN_NAME_BIBLIOTECA_REG)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_RH_BR")
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
    @ManyToOne(targetEntity = Ejemplar.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = ReservaHistorico.COLUMN_NAME_EJEMPLAR)
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
    @ManyToOne(targetEntity = Lector.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = ReservaHistorico.COLUMN_NAME_LECTOR)
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
    @ManyToOne(targetEntity = TipoLector.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = ReservaHistorico.COLUMN_NAME_TIPO_LECTOR)
    public LectorTipo getLectorTipo() {
        return lectorTipo;
    }

    /**
     * @param lectorTipo
     *            the lectorTipo to set
     */
    public void setLectorTipo(final LectorTipo lectorTipo) {
        this.lectorTipo = lectorTipo;
    }

    /**
     * @return the prestamoHistorico
     */
    @ManyToOne(targetEntity = PrestamoHistorico.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
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
     * @return the fechaHistorico
     */
    @Column(name = ReservaHistorico.COLUMN_NAME_FECHA_HISTORICO)
    public Date getFechaHistorico() {
        return fechaHistorico;
    }

    /**
     * @param fechaHistorico
     *            the fechaHistorico to set
     */
    public void setFechaHistorico(final Date fechaHistorico) {
        this.fechaHistorico = fechaHistorico;
    }

    /**
     * @return the idVolumen
     */
    @Column(name = Reserva.COLUMN_NAME_ID_VOLUMEN)
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
        if (!(obj instanceof ReservaHistorico)) {
            return false;
        }

        final ReservaHistorico other = (ReservaHistorico) obj;

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
                + ((getLector() == null) ? 0 : getLector().hashCode());

        result = prime * result
                + ((getEjemplar() == null) ? 0 : getEjemplar().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, getId())

        .append(PROPERTY_NAME_LECTOR,
                (getLector() == null) ? "" : getLector().toString())

        .append(PROPERTY_NAME_EJEMPLAR,
                (getEjemplar() == null) ? "" : getEjemplar().toString())

                .append(
                        PROPERTY_NAME_FECHA_RESERVA,
                        (getFechaReserva() == null) ? "" : getFechaReserva()
                                .toString())

                .toString();
    }

}