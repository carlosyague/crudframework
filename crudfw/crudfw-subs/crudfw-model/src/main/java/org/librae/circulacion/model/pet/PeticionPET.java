package org.librae.circulacion.model.pet;

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
import org.hibernate.annotations.ForeignKey;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Moneda;
import org.librae.adquisicion.model.Desiderata;
import org.librae.catalogacion.model.Ejemplar;
import org.librae.catalogacion.model.Registro;
import org.librae.circulacion.model.Prestamo;

import org.librae.circulacion.model.Reserva;

import org.librae.circulacion.model.pib.ConceptoTransaccionPIB;
import org.librae.circulacion.model.pib.MetodoPagoTransaccionPIB;
import org.librae.circulacion.model.pib.PeticionPIB;
import org.librae.circulacion.model.pib.TipoAccionTransaccionPIB;
import org.librae.circulacion.model.pib.TipoResolucionPIB;
import org.librae.common.model.BaseObject;
import org.librae.lectores.model.Lector;

/**
 * Cada fila es una petición realizada a una biblioteca.<br>
 * Cada fila representa también los datos de un workflow que va avanzando en su
 * tramitación.<br>
 * <br>
 * Además de los campos para recoger la aceptación o desestimiento del lector a
 * las condiciones económicas y restricciones del suministro de materiales no
 * retornables (L_LECTOR_ACEPTA, F_ACEPTA_DESISTE,
 * T_URL_DOCUMENTO_ACEPTA_DESISTE, ...), también existen campos para recoger la
 * anulación de la petición (F_ANULACION, T_URL_DOCUMENTO_ANULACION,
 * T_NOTAS_ANULACION), debido q que el lector puede aceptar costes y
 * restricciones y posteriormente anular la petición, por la razón que sea<br>
 * <br>
 * Una petición se puede convertir en una de estas operaciones de circulación:<br>
 * <br>
 * - Suministro de materiales no retornables (fila en CIR_PRESTAMOS_HIST con
 * T_TIPO_PRESTAMO == 'SU' ). Condición: SUM_X_SUMINISTRO_NO_RETORNABLES IS NOT
 * NULL<br>
 * <br>
 * - Desiderata (no es una op. de circulación)<br>
 * Condición: DES_X_DESIDERATA IS NOT NULL<br>
 * <br>
 * - Petición de Préstamo Interbibliotecario (fila en CIR_PIB_PETICIONES).
 * Condición: PET_X_PIB_PETICION IS NOT NULL<br>
 * Si L_PIB_PETICION_FINALIZADA == 1, la petición de PIB está finalizada
 * (tramitada), por lo que su fila está en CIR_PIB_PETICIONES_HIST<br>
 * <br>
 * - Reserva (fila en CIR_RESERVAS). Condición: RES_X_RESERVA IS NOT NULL<br>
 * Si L_RESERVA_FINALIZADA == 1, la reserva ha finalizado, por lo que su fila
 * está en CIR_RESERVAS_HIST<br>
 * <br>
 * Restricción:<br>
 * Una petición no se puede transformar en mas de una de estas operaciones, por
 * lo que solo uno de los campos PET_X_PIB_PETICION,
 * SUM_X_SUMINISTRO_NO_RETORNABLES, DES_X_DESIDERATA, RES_X_RESERVA ó
 * DEP_X_PETICION_DEPOSITO es NOT NULL<br>
 * 
 * @author asantamaría
 */
@Entity(name = PeticionPET.ENTITY_NAME)
@Table(name = PeticionPET.TABLE_NAME)
public class PeticionPET extends BaseObject {

    /**
     * BaseObject is Serializable, so Peticion needs a Serial Version UID
     */
    private static final long        serialVersionUID                         = 953180530635912918L;

    public static final String       ENTITY_NAME                              = "org.librae.circulacion.model.PeticionPET";
    public static final String       TABLE_NAME                               = "CIR_PET_PETICIONES";
    public static final String       ID_GENERATOR_NAME                        = "generator_cir_pet_peticiones";
    private static final String      ID_SEQUENCE_NAME                         = "SEQ_CIR_PET_PETICIONES";
    public static final String       COLUMN_NAME_ID                           = "X_PET_PETICION";
    public static final String       COLUMN_NAME_TEXTO_LECTOR                 = "T_TEXTO_LECTOR";
    public static final String       COLUMN_NAME_FECHA_CREACION               = "F_CREACION";
    public static final String       COLUMN_NAME_MOTIVACION_RESOLUCION        = "T_MOTIVACION_RESOLUCION";
    public static final String       COLUMN_NAME_RECIBIDA_VIA_WS              = "L_RECIBIDA_VIA_WS";
    public static final String       COLUMN_NAME_LECTOR_BLOQUEADAS_PETICIONES = "L_LECTOR_BLOQUEADAS_PETICIONES";
    public static final String       COLUMN_NAME_IMPORTE                      = "N_IMPORTE";
    public static final String       COLUMN_NAME_ID_TRANSACCION               = "T_ID_TRANSACCION";
    public static final String       COLUMN_NAME_INFO_ADICIONAL_TRANSACCION   = "T_INFO_ADICIONAL_TRANSACCION";
    public static final String       COLUMN_NAME_REQUIERE_ACEPTACION_LECTOR   = "L_REQUIERE_ACEPTACION_LECTOR";
    public static final String       COLUMN_NAME_LECTOR_ACEPTA                = "L_LECTOR_ACEPTA";
    public static final String       COLUMN_NAME_FECHA_ACEPTA_DESISTE         = "F_ACEPTA_DESISTE";
    public static final String       COLUMN_NAME_URL_DOCUMENTO_ACEPTA_DESISTE = "T_URL_DOCUMENTO_ACEPTA_DESISTE";
    public static final String       COLUMN_NAME_ACEPTA_DESISTE               = "T_ACEPTA_DESISTE";
    public static final String       COLUMN_NAME_FECHA_ANULACION              = "F_ANULACION";
    public static final String       COLUMN_NAME_URL_DOCUMENTO_ANULACION      = "T_URL_DOCUMENTO_ANULACION";
    public static final String       COLUMN_NAME_NOTAS_ANULACION              = "T_NOTAS_ANULACION";
    public static final String       COLUMN_NAME_PIB_PETICION_FINALIZADA      = "L_PIB_PETICION_FINALIZADA";
    public static final String       COLUMN_NAME_RESERVA_FINALIZADA           = "L_RESERVA_FINALIZADA";
    public static final String       COLUMN_NAME_PRESTAMO_FINALIZADO          = "L_PRESTAMO_FINALIZADO";
    public static final String       COLUMN_NAME_TIPO_PETICION                = "T_TIPO_PETICION";
    public static final String       COLUMN_NAME_PETICION_PIB                 = "PET_X_PIB_PETICION";
    public static final String       COLUMN_NAME_PETICION_VINCULADA_A         = "PET_X_PET_PETICION_VINCULADA_A";
    public static final String       COLUMN_NAME_BIBLIOTECA                   = "BIB_X_BIBLIOTECA";
    public static final String       COLUMN_NAME_MONEDA                       = "MON_X_MONEDA";
    public static final String       COLUMN_NAME_LECTOR                       = "LEC_X_LECTOR";
    public static final String       COLUMN_NAME_PETICION_DEPOSITO            = "DEP_X_PETICION_DEPOSITO";
    public static final String       COLUMN_NAME_PETICION_DEPOSITO_HIST       = "DEP_HIST_X_PET_DEPOSITO_HIST";
    public static final String       COLUMN_NAME_PIB_TIPO_RESOLUCION          = "RES_X_PIB_TIPO_RESOLUCION";
    public static final String       COLUMN_NAME_REGISTRO                     = "REG_X_REGISTRO";
    public static final String       COLUMN_NAME_TIPO_ACCION_TRANSACCION      = "TIPO_ACC_X_TIPO_ACCION_TRANSAC";
    public static final String       COLUMN_NAME_CONCEPTO_TRANSACCION         = "CON_X_CONCEPTO_TRANSACCION";
    public static final String       COLUMN_NAME_METODO_PAGO_TRANSACCION      = "MET_PAGO_X_MET_PAGO_TRANSACC";
    public static final String       COLUMN_NAME_SUMINITRO_NO_RETORNABLES     = "SUM_X_SUMINITRO_NO_RETORNABLES";
    public static final String       COLUMN_NAME_EJEMPLAR                     = "EJE_X_EJEMPLAR";
    public static final String       COLUMN_NAME_DESIDERATA                   = "DES_X_DESIDERATA";
    public static final String       COLUMN_NAME_RESERVA                      = "RES_X_RESERVA";
    public static final String       COLUMN_NAME_RESERVA_HIST                 = "RES_HIST_X_RESERVA_HIST";

    public static final String       PROPTY_NAME_ID                           = "id";
    public static final String       PROPTY_NAME_TEXTO_LECTOR                 = "textoLector";
    public static final String       PROPTY_NAME_FECHA_CREACION               = "fechaCreacion";
    public static final String       PROPTY_NAME_MOTIVACION_RESOLUCION        = "motivacionResolucion";
    public static final String       PROPTY_NAME_RECIBIDA_VIA_WS              = "recibidaViaWebService";
    public static final String       PROPTY_NAME_LECTOR_BLOQUEADAS_PETICIONES = "lectorBloqueadasPeticiones";
    public static final String       PROPTY_NAME_IMPORTE                      = "importe";
    public static final String       PROPTY_NAME_ID_TRANSACCION               = "idTransaccion";
    public static final String       PROPTY_NAME_INFO_ADICIONAL_TRANSACCION   = "infoAdicionalTransaccion";
    public static final String       PROPTY_NAME_REQUIERE_ACEPTACION_LECTOR   = "requiereAceptacionLector";
    public static final String       PROPTY_NAME_LECTOR_ACEPTA                = "lectorAcepta";
    public static final String       PROPTY_NAME_FECHA_ACEPTA_DESISTE         = "fechaAceptaDesiste";
    public static final String       PROPTY_NAME_URL_DOCUMENTO_ACEPTA_DESISTE = "urlDocumentoAceptaDesiste";
    public static final String       PROPTY_NAME_ACEPTA_DESISTE               = "aceptaDesiste";
    public static final String       PROPTY_NAME_FECHA_ANULACION              = "fechaAnulacion";
    public static final String       PROPTY_NAME_URL_DOCUMENTO_ANULACION      = "urlDocumentoAnulacion";
    public static final String       PROPTY_NAME_NOTAS_ANULACION              = "notasAnulacion";
    public static final String       PROPTY_NAME_PIB_PETICION_FINALIZADA      = "peticionPIBFinalizada";
    public static final String       PROPTY_NAME_RESERVA_FINALIZADA           = "reservaFinalizada";
    public static final String       PROPTY_NAME_PRESTAMO_FINALIZADO          = "prestamoFinalizado";
    public static final String       PROPTY_NAME_TIPO_PETICION                = "tipoPeticion";
    public static final String       PROPTY_NAME_PETICION_PIB                 = "peticionPIB";
    public static final String       PROPTY_NAME_PETICION_VINCULADA_A         = "peticionVinculadaA";
    public static final String       PROPTY_NAME_BIBLIOTECA                   = "biblioteca";
    public static final String       PROPTY_NAME_MONEDA                       = "moneda";
    public static final String       PROPTY_NAME_LECTOR                       = "lector";
    public static final String       PROPTY_NAME_PETICION_DEPOSITO            = "peticionDeposito";
    public static final String       PROPTY_NAME_PETICION_DEPOSITO_HIST       = "peticionDepositoHistorico";
    public static final String       PROPTY_NAME_PIB_TIPO_RESOLUCION          = "tipoResolucionCaracter";
    public static final String       PROPTY_NAME_REGISTRO                     = "registro";
    public static final String       PROPTY_NAME_TIPO_ACCION_TRANSACCION      = "tipoAccionTransaccion";
    public static final String       PROPTY_NAME_CONCEPTO_TRANSACCION         = "conceptoTransaccion";
    public static final String       PROPTY_NAME_METODO_PAGO_TRANSACCION      = "metodoPagoTransaccion";
    public static final String       PROPTY_NAME_SUMINITRO_NO_RETORNABLES     = "suminitroNoRetornables";
    public static final String       PROPTY_NAME_EJEMPLAR                     = "ejemplar";
    public static final String       PROPTY_NAME_DESIDERATA                   = "desiderata";
    public static final String       PROPTY_NAME_RESERVA                      = "reserva";
    public static final String       PROPTY_NAME_RESERVA_HIST                 = "reservaHistorico";

    public static final String       TIPO_PETICION_SUMINISTRO                 = "SU";
    public static final String       TIPO_PETICION_PIB                        = "PIB";
    public static final String       TIPO_PETICION_RESERVA                    = "RES";
    public static final String       TIPO_PETICION_DEPOSITO                   = "DEP";
    public static final String       TIPO_PETICION_DESIDERATA                 = "DES";

    /**
     * Clave primaria artificial.<br>
     * Identificador único de petición para el bibliotecario y el lector
     */
    private Long                     id;

    /**
     * Texto de la petición, tal como la ha solicitado el lector
     * (independientemente de que el texto haya sido introducido directamente
     * por el lector desde el OPAC o de que el texto haya sido introducidopor el
     * bibliotecario en nombre del lector)
     */
    private String                   textoLector;

    /**
     * Fecha y hora de creación de la petición
     */
    private Date                     fechaCreacion;

    /**
     * Texto con el que la biblioteca justifica la aceptación o denegación de la
     * petición realizada por el usuario
     */
    private String                   motivacionResolucion;

    /**
     * true: La petición se recibió vía una llamada a un web service (por
     * ejemplo desde el OPAC) false: en caso contrario
     */
    private Boolean                  recibidaViaWebService;

    /**
     * true si el lector tiene bloqueada la posibilidad de realizar peticiones
     * false en caso contrario
     */
    private Boolean                  lectorBloqueadasPeticiones;

    /**
     * Importe que el lector ha de abonar a la biblioteca prestamista por el
     * servicio realizado Según el estándar ISO 4217 si el valor de este campo
     * es x, entonces el valor del importe es x * 10 ** (-M) siendo M el valor
     * de MONEDAS.N_POTENCIA_M referenciado por MON_X_MONEDA. Por ejemplo, si
     * este campo se refiere a la moneda EUR (euros) en la que M == 2, entonces
     * un valor x == 547 en este campo significan 547 * 10 ** (-2) = 5.47 Euros
     * Corresponde al elemento de dato FiscalTransactionInformation.Amount.
     * MonetaryValue de NCIP
     */
    private Long                     importe;

    /**
     * Identificación de la transacción económica Corresponde al elemento de
     * dato FiscalTransactionInformation.FiscalTransaction.
     * ReferenceId.FiscalTRansactionIdentifierValue de NCIP Informado por la
     * biblioteca
     */
    private String                   idTransaccion;

    /**
     * Información adicional sobre la transacción. Corresponde al elemento de
     * dato FiscalTransactionInformation. FiscalTransactionDescription de NCIP
     * Informado por la biblioteca
     */
    private String                   infoAdicionalTransaccion;

    /**
     * true: La biblioteca requiere la aceptación del lector a las condiciones
     * económicas y/o a las restricciones de uso para resolver la petición false
     * en caso contrario
     */
    private Boolean                  requiereAceptacionLector;

    /**
     * true si lector acepta las condiciones económicas y/o las restricciones de
     * uso, y false en caso contrario
     */
    private Boolean                  lectorAcepta;

    /**
     * Fecha de aceptación o desestimiento del lector a las condiciones
     * económicas y restricciones de uso
     */
    private Date                     fechaAceptaDesiste;

    /**
     * URL a un documento remitido por el lector que refleja su aceptación o
     * desestimiento (p.e. copia de un mail, una carta, ...) de las condiciones
     * económicas y restricciones de uso
     */
    private String                   urlDocumentoAceptaDesiste;

    /**
     * Texto libre con el que el usuario puede documentar información adicional
     * sobre la aceptación o desestimiento del lector de las condiciones
     * ecómicas y restricciones de uso, por ejemplo, resultado de una
     * conversación telefónica, ...
     */
    private String                   aceptaDesiste;

    /**
     * Fecha en que el lector comunica que desea anular la petición
     */
    private Date                     fechaAnulacion;

    /**
     * URL a un documento remitido por el lector en el que comunica su desea de
     * que se anule le petición (p.e. copia de un mail, una carta, ...)
     */
    private String                   urlDocumentoAnulacion;

    /**
     * Texto libre introducido por el usuario Añade información adocional que el
     * usuario considere de interés sobre la anulación de la petición solicitada
     * por el lector
     */
    private String                   notasAnulacion;

    /**
     * true: PET_X_PIB_PETICION referencia a CIR_PIB_PETICIONES_HIST false:
     * referencia a CIR_PIB_PETICIONES
     */
    private Boolean                  peticionPIBFinalizada;

    /**
     * true: RES_X_RESERVA referencia a CIR_RESERVAS_HIST false: referencia a
     * CIR_RESERVAS
     */
    private Boolean                  reservaFinalizada;

    /**
     * true: DEP_X_PETICION_DEPOSITO referencia a CIR_PRESTAMOS_HIST false:
     * referencia a CIR_PRESTAMOS
     */
    private Boolean                  prestamoFinalizado;

    /**
     * Indica con que otra operación de circulación se sigue el trámite de esta
     * petición. Se asigna al pasar de la actividad del workflow Cumplimentar
     * petición a la siguiente actividad Valores: SU - La petición se sigue con
     * un suministro de materiales no retornables PIB - Idem con una Petición de
     * PIB RE - Idem con una Reserva DEP - Idem con una petición a depósito DES
     * - Idem, con una desiderata
     */
    private String                   tipoPeticion;

    /**
     * >> CIR_PIB_PETICIONES<br>
     * ó CIR_PIB_PETICIONES_HIST<br>
     * El campo L_PIB_PETICION_FINALIZADA indica a cual de las dos tablas
     * referencia Valor NOT NULL significa que la petición se ha transformado en
     * una petición de Préstamo Interbibliotecario<br>
     * Valor NULL significa que no se ha transformado en este tipo de operación
     * de circulación.
     */
    private PeticionPIB              peticionPIB;

    /**
     * >> CIR_PET_PETICIONES<br>
     * Petición a la que ésta está vinculada Por defecto no vinculada a ninguna
     */
    private PeticionPET              peticionVinculadaA;

    /**
     * >> BIBLIOTECAS<br>
     * Indica que biblioteca de las registradas en la aplicación es la
     * propietaria de esta petición (la biblioteca que tramita esta petición)
     */
    private Biblioteca               biblioteca;

    /**
     * >> MON_X_MONEDA<br>
     * La biblioteca prestamista informa del código de moneda con un valor de
     * MONEDAS. T_CODIGO_ALFABETICO. En esta clave ajena se guarda el valor de
     * MONEDAS.X_MONEDA tal que MONEDAS.T_CODIGO_ALFABETICO es el informado por
     * la biblioteca Y MONEDAS. F_OBSOLETA IS NULL Informado por la biblioteca
     */
    private Moneda                   moneda;

    /**
     * >> LEC_LECTORES<br>
     * La entidad LEC_LECTORES informa también de cual es la biblioteca a la que
     * esta afiliado el lector
     */
    private Lector                   lector;

    /**
     * >> CIR_PRESTAMOS<br>
     * El campo L_PRESTAMO_FINALIZADO indica a cual de las dos tablas referencia
     * Indica que la petición se ha gestionado como una petición a depósito que
     * se ha convertido en un préstamo
     */
    private Prestamo                 peticionDeposito;

    /**
     * >> CIR_PRESTAMOS_HIST<br>
     * El campo L_PRESTAMO_FINALIZADO indica a cual de las dos tablas referencia
     * Indica que la petición se ha gestionado como una petición a depósito que
     * se ha convertido en un préstamo
     */
    // private PrestamoHistorico peticionDepositoHistorico;
    /**
     * >> CIR_PIB_TIPOS_RESOLUCIONES
     */
    private TipoResolucionPIB        tipoResolucionCaracter;

    /**
     * >> CAT_REGISTROS<br>
     * Referencia al registro bibliográfico al que se refiere esta petición.
     * Puede ser NULL
     */
    private Registro                 registro;

    /**
     * >> CIR_PIB_TIPOS_ACCIONES_TRANSACCIONES<br>
     * Tipo de acción en la transacción (pago normalmente) Corresponde al
     * elemento de dato de NCIP FiscalTransactionInformation. FiscalActionType
     * INformado por la biblioteca
     */
    private TipoAccionTransaccionPIB tipoAccionTransaccion;

    /**
     * >> CIR_PIB_CONCEPTOS_TRANSACCIONES<br>
     * Tipo de transacción económica (cargo por PIB, ...) Corresponde al
     * elemento de dato FiscalTransaction Information. FiscalTransactionType de
     * NCIP INformado por la biblioteca
     */
    private ConceptoTransaccionPIB   conceptoTransaccion;

    /**
     * >> CIR_PIB_METODOS_PAGOS_TRANSACCIONES<br>
     * Método de pago del importe<br>
     * Informado por la biblioteca<br>
     * Corresponde al elemento de dato FiscalTransaction.PaymentMethodType de
     * NCIP
     */
    private MetodoPagoTransaccionPIB metodoPagoTransaccion;

    /**
     * >> CIR_PRESTAMOS_HIST<br>
     * Restricción: la fila referenciada debe tener T_TIPO_PRESTAMO == 'SU'<br>
     * Referencia a la fila del préstamo correspondiente al suministro de los
     * materiales no retornables.
     */
    // private PrestamoHistorico suminitroNoRetornables;
    /**
     * >> CAT_FONDOS
     */
    private Ejemplar                 ejemplar;

    /**
     * >> ADQ_DESIDERATAS
     */
    private Desiderata               desiderata;

    /**
     * >> CIR_RESERVAS ó CIR_RESERVAS_HIST<br>
     * El campo L_RESERVA_FINALIZADA indica a cual de la dos tablas referencia
     */
    private Reserva                  reserva;

    /**
     * >> CIR_RESERVAS ó CIR_RESERVAS_HIST<br>
     * El campo L_RESERVA_FINALIZADA indica a cual de la dos tablas referencia
     */
    // private ReservaHistorico reservaHistorico;
    protected PeticionPET() {
        super();
    }

    public PeticionPET(Lector lector) {
        super();
        this.lector = lector;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = PeticionPET.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the textoLector
     */
    @Column(name = PeticionPET.COLUMN_NAME_TEXTO_LECTOR)
    public String getTextoLector() {
        return textoLector;
    }

    /**
     * @param textoLector
     *            the textoLector to set
     */
    public void setTextoLector(String textoLector) {
        this.textoLector = textoLector;
    }

    /**
     * @return the fechaCreacion
     */
    @Column(name = PeticionPET.COLUMN_NAME_FECHA_CREACION)
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion
     *            the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the motivacionResolucion
     */
    @Column(name = PeticionPET.COLUMN_NAME_MOTIVACION_RESOLUCION)
    public String getMotivacionResolucion() {
        return motivacionResolucion;
    }

    /**
     * @param motivacionResolucion
     *            the motivacionResolucion to set
     */
    public void setMotivacionResolucion(String motivacionResolucion) {
        this.motivacionResolucion = motivacionResolucion;
    }

    /**
     * @return the recibidaViaWebService
     */
    @Column(name = PeticionPET.COLUMN_NAME_RECIBIDA_VIA_WS)
    public Boolean getRecibidaViaWebService() {
        return recibidaViaWebService;
    }

    /**
     * @param recibidaViaWebService
     *            the recibidaViaWebService to set
     */
    public void setRecibidaViaWebService(Boolean recibidaViaWebService) {
        this.recibidaViaWebService = recibidaViaWebService;
    }

    /**
     * @return the lectorBloqueadasPeticiones
     */
    @Column(name = PeticionPET.COLUMN_NAME_LECTOR_BLOQUEADAS_PETICIONES)
    public Boolean getLectorBloqueadasPeticiones() {
        return lectorBloqueadasPeticiones;
    }

    /**
     * @param lectorBloqueadasPeticiones
     *            the lectorBloqueadasPeticiones to set
     */
    public void setLectorBloqueadasPeticiones(Boolean lectorBloqueadasPeticiones) {
        this.lectorBloqueadasPeticiones = lectorBloqueadasPeticiones;
    }

    /**
     * @return the importe
     */
    @Column(name = PeticionPET.COLUMN_NAME_IMPORTE)
    public Long getImporte() {
        return importe;
    }

    /**
     * @param importe
     *            the importe to set
     */
    public void setImporte(Long importe) {
        this.importe = importe;
    }

    /**
     * @return the idTransaccion
     */
    @Column(name = PeticionPET.COLUMN_NAME_ID_TRANSACCION, length = 20)
    public String getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * @param idTransaccion
     *            the idTransaccion to set
     */
    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    /**
     * @return the infoAdicionalTransaccion
     */
    @Column(name = PeticionPET.COLUMN_NAME_INFO_ADICIONAL_TRANSACCION)
    public String getInfoAdicionalTransaccion() {
        return infoAdicionalTransaccion;
    }

    /**
     * @param infoAdicionalTransaccion
     *            the infoAdicionalTransaccion to set
     */
    public void setInfoAdicionalTransaccion(String infoAdicionalTransaccion) {
        this.infoAdicionalTransaccion = infoAdicionalTransaccion;
    }

    /**
     * @return the requiereAceptacionLector
     */
    @Column(name = PeticionPET.COLUMN_NAME_REQUIERE_ACEPTACION_LECTOR)
    public Boolean getRequiereAceptacionLector() {
        return requiereAceptacionLector;
    }

    /**
     * @param requiereAceptacionLector
     *            the requiereAceptacionLector to set
     */
    public void setRequiereAceptacionLector(Boolean requiereAceptacionLector) {
        this.requiereAceptacionLector = requiereAceptacionLector;
    }

    /**
     * @return the lectorAcepta
     */
    @Column(name = PeticionPET.COLUMN_NAME_LECTOR_ACEPTA)
    public Boolean getLectorAcepta() {
        return lectorAcepta;
    }

    /**
     * @param lectorAcepta
     *            the lectorAcepta to set
     */
    public void setLectorAcepta(Boolean lectorAcepta) {
        this.lectorAcepta = lectorAcepta;
    }

    /**
     * @return the fechaAceptaDesiste
     */
    @Column(name = PeticionPET.COLUMN_NAME_FECHA_ACEPTA_DESISTE)
    public Date getFechaAceptaDesiste() {
        return fechaAceptaDesiste;
    }

    /**
     * @param fechaAceptaDesiste
     *            the fechaAceptaDesiste to set
     */
    public void setFechaAceptaDesiste(Date fechaAceptaDesiste) {
        this.fechaAceptaDesiste = fechaAceptaDesiste;
    }

    /**
     * @return the urlDocumentoAceptaDesiste
     */
    @Column(name = PeticionPET.COLUMN_NAME_URL_DOCUMENTO_ACEPTA_DESISTE)
    public String getUrlDocumentoAceptaDesiste() {
        return urlDocumentoAceptaDesiste;
    }

    /**
     * @param urlDocumentoAceptaDesiste
     *            the urlDocumentoAceptaDesiste to set
     */
    public void setUrlDocumentoAceptaDesiste(String urlDocumentoAceptaDesiste) {
        this.urlDocumentoAceptaDesiste = urlDocumentoAceptaDesiste;
    }

    /**
     * @return the aceptaDesiste
     */
    @Column(name = PeticionPET.COLUMN_NAME_ACEPTA_DESISTE, length = 10)
    public String getAceptaDesiste() {
        return aceptaDesiste;
    }

    /**
     * @param aceptaDesiste
     *            the aceptaDesiste to set
     */
    public void setAceptaDesiste(String aceptaDesiste) {
        this.aceptaDesiste = aceptaDesiste;
    }

    /**
     * @return the fechaAnulacion
     */
    @Column(name = PeticionPET.COLUMN_NAME_FECHA_ANULACION)
    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    /**
     * @param fechaAnulacion
     *            the fechaAnulacion to set
     */
    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    /**
     * @return the urlDocumentoAnulacion
     */
    @Column(name = PeticionPET.COLUMN_NAME_URL_DOCUMENTO_ANULACION)
    public String getUrlDocumentoAnulacion() {
        return urlDocumentoAnulacion;
    }

    /**
     * @param urlDocumentoAnulacion
     *            the urlDocumentoAnulacion to set
     */
    public void setUrlDocumentoAnulacion(String urlDocumentoAnulacion) {
        this.urlDocumentoAnulacion = urlDocumentoAnulacion;
    }

    /**
     * @return the notasAnulacion
     */
    @Column(name = PeticionPET.COLUMN_NAME_NOTAS_ANULACION)
    public String getNotasAnulacion() {
        return notasAnulacion;
    }

    /**
     * @param notasAnulacion
     *            the notasAnulacion to set
     */
    public void setNotasAnulacion(String notasAnulacion) {
        this.notasAnulacion = notasAnulacion;
    }

    /**
     * @return the peticionPIBFinalizada
     */
    @Column(name = PeticionPET.COLUMN_NAME_PIB_PETICION_FINALIZADA)
    public Boolean getPeticionPIBFinalizada() {
        return peticionPIBFinalizada;
    }

    /**
     * @param peticionPIBFinalizada
     *            the peticionPIBFinalizada to set
     */
    public void setPeticionPIBFinalizada(Boolean peticionPIBFinalizada) {
        this.peticionPIBFinalizada = peticionPIBFinalizada;
    }

    /**
     * @return the reservaFinalizada
     */
    @Column(name = PeticionPET.COLUMN_NAME_RESERVA_FINALIZADA)
    public Boolean getReservaFinalizada() {
        return reservaFinalizada;
    }

    /**
     * @param reservaFinalizada
     *            the reservaFinalizada to set
     */
    public void setReservaFinalizada(Boolean reservaFinalizada) {
        this.reservaFinalizada = reservaFinalizada;
    }

    /**
     * @return the prestamoFinalizado
     */
    @Column(name = PeticionPET.COLUMN_NAME_PRESTAMO_FINALIZADO)
    public Boolean getPrestamoFinalizado() {
        return prestamoFinalizado;
    }

    /**
     * @param prestamoFinalizado
     *            the prestamoFinalizado to set
     */
    public void setPrestamoFinalizado(Boolean prestamoFinalizado) {
        this.prestamoFinalizado = prestamoFinalizado;
    }

    /**
     * @return the tipoPeticion
     */
    @Column(name = PeticionPET.COLUMN_NAME_TIPO_PETICION)
    public String getTipoPeticion() {
        return tipoPeticion;
    }

    /**
     * @param tipoPeticion
     *            the tipoPeticion to set
     */
    public void setTipoPeticion(String tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }

    /**
     * @return the peticionPIB
     */
    @ManyToOne(targetEntity = PeticionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_PETICION_PIB)
    public PeticionPIB getPeticionPIB() {
        return peticionPIB;
    }

    /**
     * @param peticionPIB
     *            the peticionPIB to set
     */
    public void setPeticionPIB(PeticionPIB peticionPIB) {
        this.peticionPIB = peticionPIB;
    }

    /**
     * @return the peticionVinculadaA
     */
    @ManyToOne(targetEntity = PeticionPET.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_PETICION_VINCULADA_A)
    public PeticionPET getPeticionVinculadaA() {
        return peticionVinculadaA;
    }

    /**
     * @param peticionVinculadaA
     *            the peticionVinculadaA to set
     */
    public void setPeticionVinculadaA(PeticionPET peticionVinculadaA) {
        this.peticionVinculadaA = peticionVinculadaA;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_BIBLIOTECA)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_PET")
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca
     *            the biblioteca to set
     */
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    /**
     * @return the moneda
     */
    @ManyToOne(targetEntity = Moneda.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_MONEDA)
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda
     *            the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the lector
     */
    @ManyToOne(targetEntity = Lector.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_LECTOR)
    public Lector getLector() {
        return lector;
    }

    /**
     * @param lector
     *            the lector to set
     */
    public void setLector(Lector lector) {
        this.lector = lector;
    }

    /**
     * @return the peticionDeposito
     */
    @ManyToOne(targetEntity = Prestamo.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_PETICION_DEPOSITO)
    public Prestamo getPeticionDeposito() {
        return peticionDeposito;
    }

    /**
     * @param peticionDeposito
     *            the peticionDeposito to set
     */
    public void setPeticionDeposito(Prestamo peticionDeposito) {
        this.peticionDeposito = peticionDeposito;
    }

    /**
     * @return the peticionDepositoHistorico
     */
    // @ManyToOne(targetEntity = PrestamoHistorico.class, cascade = {
    // CascadeType.PERSIST, CascadeType.MERGE })
    // @JoinColumn(name = PeticionPET.COLUMN_NAME_PETICION_DEPOSITO_HIST)
    // public PrestamoHistorico getPeticionDepositoHistorico() {
    // return peticionDepositoHistorico;
    // }
    /**
     * @param peticionDepositoHistorico
     *            the peticionDepositoHistorico to set
     */
    // public void setPeticionDepositoHistorico(
    // PrestamoHistorico peticionDepositoHistorico) {
    // this.peticionDepositoHistorico = peticionDepositoHistorico;
    // }
    /**
     * @return the tipoResolucionCaracter
     */
    @ManyToOne(targetEntity = TipoResolucionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_PIB_TIPO_RESOLUCION)
    public TipoResolucionPIB getTipoResolucionCaracter() {
        return tipoResolucionCaracter;
    }

    /**
     * @param tipoResolucionCaracter
     *            the tipoResolucionCaracter to set
     */
    public void setTipoResolucionCaracter(
            TipoResolucionPIB tipoResolucionCaracter) {
        this.tipoResolucionCaracter = tipoResolucionCaracter;
    }

    /**
     * @return the registro
     */
    @ManyToOne(targetEntity = Registro.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_REGISTRO)
    public Registro getRegistro() {
        return registro;
    }

    /**
     * @param registro
     *            the registro to set
     */
    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    /**
     * @return the tipoAccionTransaccion
     */
    @ManyToOne(targetEntity = TipoAccionTransaccionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_TIPO_ACCION_TRANSACCION)
    public TipoAccionTransaccionPIB getTipoAccionTransaccion() {
        return tipoAccionTransaccion;
    }

    /**
     * @param tipoAccionTransaccion
     *            the tipoAccionTransaccion to set
     */
    public void setTipoAccionTransaccion(
            TipoAccionTransaccionPIB tipoAccionTransaccion) {
        this.tipoAccionTransaccion = tipoAccionTransaccion;
    }

    /**
     * @return the conceptoTransaccion
     */
    @ManyToOne(targetEntity = ConceptoTransaccionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_CONCEPTO_TRANSACCION)
    public ConceptoTransaccionPIB getConceptoTransaccion() {
        return conceptoTransaccion;
    }

    /**
     * @param conceptoTransaccion
     *            the conceptoTransaccion to set
     */
    public void setConceptoTransaccion(
            ConceptoTransaccionPIB conceptoTransaccion) {
        this.conceptoTransaccion = conceptoTransaccion;
    }

    /**
     * @return the metodoPagoTransaccion
     */
    @ManyToOne(targetEntity = MetodoPagoTransaccionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_METODO_PAGO_TRANSACCION)
    public MetodoPagoTransaccionPIB getMetodoPagoTransaccion() {
        return metodoPagoTransaccion;
    }

    /**
     * @param metodoPagoTransaccion
     *            the metodoPagoTransaccion to set
     */
    public void setMetodoPagoTransaccion(
            MetodoPagoTransaccionPIB metodoPagoTransaccion) {
        this.metodoPagoTransaccion = metodoPagoTransaccion;
    }

    /**
     * @return the suminitroNoRetornables
     */
    // @ManyToOne(targetEntity = PrestamoHistorico.class, cascade = {
    // CascadeType.PERSIST, CascadeType.MERGE })
    // @JoinColumn(name = PeticionPET.COLUMN_NAME_SUMINITRO_NO_RETORNABLES)
    // public PrestamoHistorico getSuminitroNoRetornables() {
    // return suminitroNoRetornables;
    // }
    /**
     * @param suminitroNoRetornables
     *            the suminitroNoRetornables to set
     */
    // public void setSuminitroNoRetornables(
    // PrestamoHistorico suminitroNoRetornables) {
    // this.suminitroNoRetornables = suminitroNoRetornables;
    // }
    /**
     * @return the ejemplar
     */
    @ManyToOne(targetEntity = Ejemplar.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_EJEMPLAR)
    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    /**
     * @param ejemplar
     *            the ejemplar to set
     */
    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    /**
     * @return the desiderata
     */
    @ManyToOne(targetEntity = Desiderata.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_DESIDERATA)
    public Desiderata getDesiderata() {
        return desiderata;
    }

    /**
     * @param desiderata
     *            the desiderata to set
     */
    public void setDesiderata(Desiderata desiderata) {
        this.desiderata = desiderata;
    }

    /**
     * @return the reserva
     */
    @ManyToOne(targetEntity = Reserva.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = PeticionPET.COLUMN_NAME_RESERVA)
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * @param reserva
     *            the reserva to set
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * @return the reservaHistorico
     */
    // @ManyToOne(targetEntity = ReservaHistorico.class, cascade = {
    // CascadeType.PERSIST, CascadeType.MERGE })
    // @JoinColumn(name = PeticionPET.COLUMN_NAME_RESERVA_HIST)
    // public ReservaHistorico getReservaHistorico() {
    // return reservaHistorico;
    // }
    /**
     * @param reservaHistorico
     *            the reservaHistorico to set
     */
    // public void setReservaHistorico(ReservaHistorico reservaHistorico) {
    // this.reservaHistorico = reservaHistorico;
    // }
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof PeticionPET)) {
            return false;
        }

        final PeticionPET other = (PeticionPET) obj;

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

        if (getFechaCreacion() == null && other.getFechaCreacion() != null) {
            return false;
        }
        if (getFechaCreacion() != null
                && !getFechaCreacion().equals(other.getFechaCreacion())) {
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

        result = prime
                * result
                + ((getFechaCreacion() == null) ? 0 : getFechaCreacion()
                        .hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPTY_NAME_ID, getId())

        .append(PROPTY_NAME_LECTOR,
                (getLector() == null) ? "" : getLector().toString())

        .append(PROPTY_NAME_EJEMPLAR,
                (getEjemplar() == null) ? "" : getEjemplar().toString())

        .append(
                PROPTY_NAME_FECHA_CREACION,
                (getFechaCreacion() == null) ? "" : getFechaCreacion()
                        .toString())

        .toString();
    }

}