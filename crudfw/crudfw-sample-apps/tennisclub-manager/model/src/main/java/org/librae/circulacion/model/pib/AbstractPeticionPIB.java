package org.librae.circulacion.model.pib;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Direccion;
import org.librae.adminconfig.model.Moneda;
import org.librae.catalogacion.model.Ejemplar;
import org.librae.catalogacion.model.Registro;
import org.librae.circulacion.model.Prestamo;
import org.librae.circulacion.model.PrestamoHistorico;

import org.librae.common.model.BaseObject;
import org.librae.lectores.model.Lector;

/**
 * Cada fila es una petición realizada a una biblioteca (por Mi Biblioteca) o
 * recibida de una biblioteca (para que la sirva Mi Biblioteca).<br>
 * Cada fila representa también los datos de un workflow que va avanzando en su
 * tramitación.
 * 
 * @author cyague
 */
@MappedSuperclass
public abstract class AbstractPeticionPIB extends BaseObject {

    /**
     * BaseObject is Serializable, so AbstractPeticionPIB needs a Serial Version
     * UID
     */
    private static final long          serialVersionUID                         = 291951426732029500L;

    public static final String         COLUMN_NAME_ID                           = "X_PIB_PETICION";
    public static final String         COLUMN_NAME_TEXTO_LECTOR                 = "T_TEXTO_LECTOR";
    public static final String         COLUMN_NAME_FECHA_CREACION               = "F_CREACION";
    public static final String         COLUMN_NAME_FECHA_HASTA_NO_NECESITA      = "F_HASTA_NO_NECESITA";
    public static final String         COLUMN_NAME_FECHA_ANTES_NECESITA         = "F_ANTES_NECESITA";
    public static final String         COLUMN_NAME_MOTIVACION_RESOLUCION        = "T_MOTIVACION_RESOLUCION";
    public static final String         COLUMN_NAME_AUTOR                        = "T_AUTOR";
    public static final String         COLUMN_NAME_TITULO                       = "T_TITULO";
    public static final String         COLUMN_NAME_PUBLICACION                  = "T_PUBLICACION";
    public static final String         COLUMN_NAME_MATERIA                      = "T_MATERIA";
    public static final String         COLUMN_NAME_ISBN                         = "T_ISBN";
    public static final String         COLUMN_NAME_EJEMPLAR_ID                  = "T_EJEMPLAR_ID";
    public static final String         COLUMN_NAME_RECIBIDA_VIA_WEB_SERVICE     = "L_RECIBIDA_VIA_WS";
    public static final String         COLUMN_NAME_LECTOR_BLOQUEADAS_PETICIONES = "L_LECTOR_BLOQUEADAS_PETICIONES";
    public static final String         COLUMN_NAME_DIRECCION_RESP               = "T_DIRECCION_RESP";
    public static final String         COLUMN_NAME_EMAIL_REQ                    = "T_EMAIL_REQ";
    public static final String         COLUMN_NAME_EMAIL_RESP                   = "T_EMAIL_RESP";
    public static final String         COLUMN_NAME_TIPO_DIR_POR_DEFECTO_REQ     = "C_TIPO_DIR_POR_DEFECTO_REQ";
    public static final String         COLUMN_NAME_NOTAS_REQ                    = "T_NOTAS_REQ";
    public static final String         COLUMN_NAME_INSTRUCCIONES_REQ            = "T_INSTRUCCIONES_REQ";
    public static final String         COLUMN_NAME_NOTAS_RESP                   = "T_NOTAS_RESP";
    public static final String         COLUMN_NAME_INSTRUCCIONES_RESP           = "T_INSTRUCCIONES_RESP";
    public static final String         COLUMN_NAME_DIR_FISICA_REQ_CAMBIADA      = "L_DIR_FISICA_REQ_CAMBIADA";
    public static final String         COLUMN_NAME_INSTR_ENVIO_RECIBID_VIA_NCIP = "L_INSTR_ENVIO_RECIB_VIA_NCIP";
    public static final String         COLUMN_NAME_FECHA_DISPONIBILIDAD         = "F_DISPONIBILIDAD";
    public static final String         COLUMN_NAME_FECHA_FIN_RETIRADA           = "F_FIN_RETIRADA";
    public static final String         COLUMN_NAME_IMPORTE                      = "N_IMPORTE";
    public static final String         COLUMN_NAME_ID_TRANSACCION               = "T_ID_TRANSACCION";
    public static final String         COLUMN_NAME_INFO_ADICIONAL_TRANSACCION   = "T_INFO_ADICIONAL_TRANSACCION";
    public static final String         COLUMN_NAME_DATOS_TRANS_ECONOM_VIA_NCIP  = "L_DATOS_TRANSACC_ECON_VIA_NCIP";
    public static final String         COLUMN_NAME_REQUIERE_ACEPTACION_LECTOR   = "L_REQUIERE_ACEPTACION_LECTOR";
    public static final String         COLUMN_NAME_LECTOR_ACEPTA                = "L_LECTOR_ACEPTA";
    public static final String         COLUMN_NAME_FECHA_ACEPTA_DESISTE         = "F_ACEPTA_DESISTE";
    public static final String         COLUMN_NAME_URL_DOCUMENTO_ACEPTA_DESISTE = "T_URL_DOCUMENTO_ACEPTA_DESISTE";
    public static final String         COLUMN_NAME_ACEPTA_DESISTE               = "T_ACEPTA_DESISTE";
    public static final String         COLUMN_NAME_PRESTAMO_DEVUELTO            = "L_PRESTAMO_DEVUELTO";
    public static final String         COLUMN_NAME_PRESTAMO                     = "PRE_X_PRESTAMO";
    public static final String         COLUMN_NAME_TIPO_PRESTAMO_PIB            = "C_TIPO_PRESTAMO_PIB";
    public static final String         COLUMN_NAME_PRESTAMO_HISTORICO           = "PRE_HIST_X_PRESTAMO_HISTORICO";
    public static final String         COLUMN_NAME_LECTOR                       = "LEC_X_LECTOR";
    public static final String         COLUMN_NAME_DIRECCION_REQ                = "DIR_X_DIRECCION_REQ";
    public static final String         COLUMN_NAME_BIBLIOTECA_BORROWER          = "BIB_X_BIB_BORROWER";
    public static final String         COLUMN_NAME_BIBLIOTECA_LENDER            = "BIB_X_BIB_LENDER";
    public static final String         COLUMN_NAME_BIBLIOTECA_EXTERNA_BORROWER  = "BIB_EXT_X_BIB_EXT_BORROWER";
    public static final String         COLUMN_NAME_BIBLIOTECA_EXTERNA_LENDER    = "BIB_EXT_X_BIB_EXT_LENDER";
    public static final String         COLUMN_NAME_MONEDA                       = "MON_X_MONEDA";
    public static final String         COLUMN_NAME_TIPO_PETICION                = "TIPO_PET_X_PIB_TIPO_PETICION";
    public static final String         COLUMN_NAME_ESTADO_PETICION              = "EST_PET_X_PIB_ESTADO_PETICION";
    public static final String         COLUMN_NAME_ALCANCE_PETICION             = "ALC_PET_X_PIB_ALCANCE_PETICION";
    public static final String         COLUMN_NAME_TIPO_ACCION_TRANSACCION      = "TIPO_ACC_X_TIPO_ACCION_TRANSAC";
    public static final String         COLUMN_NAME_CONCEPTO_TRANSACCION         = "CON_X_CONCEPTO_TRANSACCION";
    public static final String         COLUMN_NAME_METODO_PAGO_TRANSACCION      = "MET_PAGO_X_METODO_PAGO_TRANSAC";

    public static final String         COLUMN_NAME_PETICION_VINCULADA_A         = "PET_X_PIB_PETICION_VINCULADA_A";
    public static final String         COLUMN_NAME_TIPO_RESOLUCION              = "RES_X_PIB_TIPO_RESOLUCION";

    public static final String         COLUMN_NAME_FECHA_ANULACION              = "F_ANULACION";
    public static final String         COLUMN_NAME_NOTAS_ANULACION              = "T_NOTAS_ANULACION";
    public static final String         COLUMN_NAME_DOCUMENTO_ANULACION          = "B_DOCUMENTO_ANULACION";
    public static final String         COLUMN_NAME_DOCUMENTO_NOMBRE             = "T_FICHERO_NOMBRE";
    public static final String         COLUMN_NAME_DOCUMENTO_CONTENT_TYPE       = "T_FICHERO_CONTENT_TYPE";

    public static final String         COLUMN_NAME_REGISTRO                     = "REG_X_REGISTRO";
    public static final String         COLUMN_NAME_EJEMPLAR                     = "EJE_X_EJEMPLAR";

    public static final String         PROPTY_NAME_ID                           = "id";
    public static final String         PROPTY_NAME_TEXTO_LECTOR                 = "textoLector";
    public static final String         PROPTY_NAME_FECHA_CREACION               = "fechaCreacion";
    public static final String         PROPTY_NAME_FECHA_HASTA_NO_NECESITA      = "fechaHastaNoNecesita";
    public static final String         PROPTY_NAME_FECHA_ANTES_NECESITA         = "fechaAntesNecesita";
    public static final String         PROPTY_NAME_MOTIVACION_RESOLUCION        = "motivacionResolucion";
    public static final String         PROPTY_NAME_AUTOR                        = "autor";
    public static final String         PROPTY_NAME_TITULO                       = "titulo";
    public static final String         PROPTY_NAME_PUBLICACION                  = "publicacion";
    public static final String         PROPTY_NAME_MATERIA                      = "materia";
    public static final String         PROPTY_NAME_ISBN                         = "isbn";
    public static final String         PROPTY_NAME_EJEMPLAR_ID                  = "ejemplarId";
    public static final String         PROPTY_NAME_RECIBIDA_VIA_WEB_SERVICE     = "recibidaViaWebService";
    public static final String         PROPTY_NAME_LECTOR_BLOQUEADAS_PETICIONES = "lectorBloqueadasPeticiones";
    public static final String         PROPTY_NAME_DIRECCION_RESP               = "direccionResp";
    public static final String         PROPTY_NAME_EMAIL_REQ                    = "emailReq";
    public static final String         PROPTY_NAME_EMAIL_RESP                   = "emailResp";
    public static final String         PROPTY_NAME_TIPO_DIR_POR_DEFECTO_REQ     = "tipoDirPorDefectoReq";
    public static final String         PROPTY_NAME_NOTAS_REQ                    = "notasReq";
    public static final String         PROPTY_NAME_INSTRUCCIONES_REQ            = "instruccionesReq";
    public static final String         PROPTY_NAME_NOTAS_RESP                   = "notasResp";
    public static final String         PROPTY_NAME_INSTRUCCIONES_RESP           = "instruccionesResp";
    public static final String         PROPTY_NAME_DIR_FISICA_REQ_CAMBIADA      = "dirFisicaReqCambiada";
    public static final String         PROPTY_NAME_INSTR_ENVIO_RECIBID_VIA_NCIP = "instrEnvioRecibidasViaNcip";
    public static final String         PROPTY_NAME_FECHA_DISPONIBILIDAD         = "fechaDisponibilidad";
    public static final String         PROPTY_NAME_FECHA_FIN_RETIRADA           = "fechaFinRetirada";
    public static final String         PROPTY_NAME_IMPORTE                      = "importe";
    public static final String         PROPTY_NAME_ID_TRANSACCION               = "idTransaccion";
    public static final String         PROPTY_NAME_INFO_ADICIONAL_TRANSACCION   = "infoAdicionalTransaccion";
    public static final String         PROPTY_NAME_DATOS_TRANS_ECONOM_VIA_NCIP  = "datosTransaccionEconomicaViaNcip";
    public static final String         PROPTY_NAME_REQUIERE_ACEPTACION_LECTOR   = "requiereAceptacionLector";
    public static final String         PROPTY_NAME_LECTOR_ACEPTA                = "lectorAcepta";
    public static final String         PROPTY_NAME_FECHA_ACEPTA_DESISTE         = "fechaAceptaDesiste";
    public static final String         PROPTY_NAME_URL_DOCUMENTO_ACEPTA_DESISTE = "urlDocumentoAceptaDesiste";
    public static final String         PROPTY_NAME_ACEPTA_DESISTE               = "aceptaDesiste";
    public static final String         PROPTY_NAME_PRESTAMO_DEVUELTO            = "prestamoDevuelto";
    public static final String         PROPTY_NAME_PRESTAMO                     = "prestamo";
    public static final String         PROPTY_NAME_TIPO_PETICION_PIB            = "tipoPrestamoPIB";
    public static final String         PROPTY_NAME_PRESTAMO_HISTORICO           = "prestamoHistorico";
    public static final String         PROPTY_NAME_LECTOR                       = "lector";
    public static final String         PROPTY_NAME_DIRECCION_REQ                = "direccionReq";
    public static final String         PROPTY_NAME_BIBLIOTECA                   = "bibliotecaBorrower";
    public static final String         PROPTY_NAME_BIBLIOTECA_LENDER            = "bibliotecaLender";
    public static final String         PROPTY_NAME_MONEDA                       = "moneda";
    public static final String         PROPTY_NAME_TIPO_PETICION                = "tipoPeticion";
    public static final String         PROPTY_NAME_ALCANCE_PETICION             = "alcancePeticion";
    public static final String         PROPTY_NAME_TIPO_ACCION_TRANSACCION      = "tipoAccionTransaccion";
    public static final String         PROPTY_NAME_CONCEPTO_TRANSACCION         = "conceptoTransaccion";
    public static final String         PROPTY_NAME_METODO_PAGO_TRANSACCION      = "metodoPagoTransaccion";
    public static final String         PROPTY_NAME_BIBLIOTECA_EXTERNA_BORROWER  = "bibliotecaExternaBorrower";
    public static final String         PROPTY_NAME_BIBLIOTECA_EXTERNA_LENDER    = "bibliotecaExternaLender";
    public static final String         PROPTY_NAME_PETICION_VINCULADA_A         = "peticionVinculadaA";
    public static final String         PROPTY_NAME_TIPO_RESOLUCION              = "tipoResolucion";

    public static final String         PROPTY_NAME_FECHA_ANULACION              = "fechaAnulacion";
    public static final String         PROPTY_NAME_NOTAS_ANULACION              = "notasAnulacion";
    public static final String         PROPTY_NAME_DOCUMENTO_ANULACION          = "docAnulacion";
    public static final String         PROPTY_NAME_DOCUMENTO_NOMBRE_FICHERO     = "docAnulacionNombre";
    public static final String         PROPTY_NAME_DOCUMENTO_CONTENT_TYPE       = "docAnulacionContentType";

    public static final String         PROPERTY_NAME_REGISTRO                   = "registro";
    public static final String         PROPERTY_NAME_EJEMPLAR                   = "ejemplar";

    public static final String         TIPO_DIR_POR_DEFECTO_REQ_EMAIL           = "E";
    public static final String         TIPO_DIR_POR_DEFECTO_REQ_FISICA          = "F";

    /**
     * Texto de la petición, tal como la ha solicitado el lector
     * (independientemente de que el texto haya sido introducido directamente
     * por el lector desde el OPAC o de que el texto haya sido introducidopor el
     * bibliotecario en nombre del lector)
     */
    protected String                   textoLector;

    /**
     * Fecha y hora de creación de la petición
     */
    protected Date                     fechaCreacion;

    /**
     * Fecha y hora hasta la que el lector dice no necesitar el material objeto
     * de la petición.<br>
     * Se corresponde con el elemento de dato EarliestDateNeeded de NCIP
     */
    protected Date                     fechaHastaNoNecesita;

    /**
     * Fecha límite hasta antes de la cual el lector dice necesitar el material
     * objeto de la petición<br>
     * Corresponde al elemento de dato NeedBeforeDate de NCIP
     */
    protected Date                     fechaAntesNecesita;

    /**
     * Texto con el que la biblioteca justifica la aceptación o denegación de la
     * petición realizada por el usuario
     */
    protected String                   motivacionResolucion;

    /**
     * Autor del material objeto de la petición
     */
    protected String                   autor;

    /**
     * Titulo del material al que corresponde la petición
     */
    protected String                   titulo;

    /**
     * Publicación al que corresponde la petición
     */
    protected String                   publicacion;

    /**
     * Materia del material objeto de la petición
     */
    protected String                   materia;

    /**
     * ISBN del material objeto de la petición
     */
    protected String                   isbn;

    /**
     * Identificación de ejemplar en su biblioteca (esto es, en
     * BIB_X_BIBLIOTECA_LENDER o en OTRA_BIB_X_PIB_OTRA_BIBLIOTECA_LENDER)
     */
    protected String                   ejemplarId;

    /**
     * true: La petición se recibió vía una llamada a un web service (por
     * ejemplo desde el OPAC)<br>
     * false: en caso contrario
     */
    protected Boolean                  recibidaViaWebService;

    /**
     * true si el lector tiene bloqueada la posibilidad de realizar peticiones<br>
     * false en caso contrario
     */
    protected Boolean                  lectorBloqueadasPeticiones;

    /**
     * Dirección de envío que responde la biblioteca a la que se le hace la
     * petición (RESP == Response).<br>
     * El valor de este dato se puede recibir vía NCIP de una de estas dos
     * formas: dirección estructurada o dirección no estructurada.<br>
     * Si se recibe vía NCIP en forma structurada, convertir a no estructurada
     * antes de guardar el valor en este campo.<br>
     * Es un campo de texto y no una clave ajena a DIRECCIONES porque la
     * dirección se puede recibir de forma desestructurada o estructurada vía
     * NCIP, y en el primer caso no se puede asumir que sea posible parsearla
     * para convertirla en una fila de DIRECCIONES. Tratándola como un campo de
     * texto cubrimos los dos casos.<br>
     */
    protected String                   direccionResp;

    /**
     * Dirección de email que se especifica en los mensajes de petición de NCIP
     * o correos enviados a la biblioteca prestamista
     */
    protected String                   emailReq;

    /**
     * Dirección de email correspondiente a las instrucciones de envío con la
     * que contesta la biblioteca pretamista en respuestas de NCIP o correo-e
     */
    protected String                   emailResp;

    /**
     * Tipo de dirección que se especifica en las instrucciones de envío por
     * parte de la biblioteca prestataria (en mensajes de NCIP o email).
     * Valores:<br>
     * E : Dirección de email<br>
     * F : Dirección física
     */
    protected String                   tipoDirPorDefectoReq;

    /**
     * Notas adicionales a las instrucciones de envío.<br>
     * Corresponde al elemento de dato ShippingInformation.ShippingNote de NCIP
     */
    protected String                   notasReq;

    /**
     * Instrucciones de envío en formato legible por un humano.<br>
     * Corresponde al elemento de dato ShippingInformation.ShippingIntructions
     * de NCIP
     */
    protected String                   instruccionesReq;

    /**
     * Notas adicionales a las instrucciones de envío.<br>
     * Corresponde al elemento de dato ShippingInformation.ShippingNote de NCIP
     */
    protected String                   notasResp;

    /**
     * Instrucciones de envío en formato legible por un humano.<br>
     * Corresponde al elemento de dato ShippingInformation.ShippingIntructions
     * de NCIP
     */
    protected String                   instruccionesResp;

    /**
     * true: En las instrucciones de envío que envía MI BIblioteca en las
     * peticiones de NCIP o envío de emails se ha editado la dirección de la
     * biblioteca, por lo que el campo que aplica es DIR_X_DIRECCION_REQ (en
     * lugar de la dirección a la que apunta la biblioteca<br>
     * false en caso contrario
     */
    protected Boolean                  dirFisicaReqCambiada;

    /**
     * true: Las instrucciones de envío recibidas de la biblioteca prestamista
     * (campos T_EMAIL_RESP, T_DIRECCION_RESP, T_INSTRUCCIONES_RESP y
     * T_NOTAS_RESP) han sido recibidas vía NCIP (las asignó el servidor de
     * NCIP), por lo que son NO EDITABLES desde la interfaz de usuario<br>
     * false: Caso contrario
     */
    protected Boolean                  instrEnvioRecibidasViaNcip;

    /**
     * INformado por la biblioteca prestamista<br>
     * Fecha de disponibilidad informada por la biblioteca prestamista<br>
     * Corresponde al dato DateAvailable del mensaje de NCIP RequestItemResponse
     */
    protected Date                     fechaDisponibilidad;

    /**
     * Informado por la biblioteca prestamista.<br>
     * Hasta cuando como máximo tiene el lector para retirar el material
     * solicitado<br>
     * Corresponde al elemento de dato HoldPickupDate de RequestItemResponse
     */
    protected Date                     fechaFinRetirada;

    /**
     * Informado por la biblioteca prestamista<br>
     * Importe que el lector ha de abonar a la biblioteca prestamista por el
     * servicio realizado<br>
     * Según el estándar ISO 4217 si el valor de este campo es x, entonces el
     * valor del importe es x * 10 ** (-M) siendo M el valor de
     * MONEDAS.N_POTENCIA_M referenciado por MON_X_MONEDA.<br>
     * Por ejemplo, si este campo se refiere a la moneda EUR (euros) en la que M
     * == 2, entonces un valor x == 547 en este campo significan 547 * 10 **
     * (-2) = 5.47 Euros <br>
     * Corresponde al elemento de dato
     * FiscalTransactionInformation.Amount.MonetaryValue de NCIP
     */
    protected Long                     importe;

    /**
     * Identificación de la transacción económica<br>
     * Corresponde al elemento de dato
     * FiscalTransactionInformation.FiscalTransaction
     * .ReferenceId.FiscalTRansactionIdentifierValue de NCIP<br>
     * Informado por la biblioteca prestamista
     */
    protected String                   idTransaccion;

    /**
     * Información adicional sobre la transacción.<br>
     * Corresponde al elemento de dato
     * FiscalTransactionInformation.FiscalTransactionDescription de NCIP<br>
     * Informado por la biblioteca prestamista
     */
    protected String                   infoAdicionalTransaccion;

    /**
     * true: Los datos de la transacción económica fueron asignados vía NCIP
     * (N_IMPORTE, MON_X_MONEDA, T_ID_TRANSACCION,
     * TIPO_ACC_X_TIPO_ACCION_TRANSACCION, CON_X_CONCEPTO_TRANSACCION,
     * MET_PAGO_X_METODO_PAGO_TRANSACCION, T_INFO_ADICIONAL_TRANSACCION,
     * correspondientes a los elementos de datos de
     * FiscalTransactionInformation), por lo que no son editables por el
     * usuario.<br>
     * false: en caso contrario
     */
    protected Boolean                  datosTransaccionEconomicaViaNcip;

    /**
     * true: La biblioteca prestamista requiere la aceptación del lector a las
     * condiciones económicas y/o a las restricciones de uso para resolver la
     * petición<br>
     * false en caso contrario
     */
    protected Long                     requiereAceptacionLector;

    /**
     * true si lector acepta las condiciones económicas y/o las restricciones de
     * uso, y false en caso contrario
     */
    protected Long                     lectorAcepta;

    /**
     * Fecha de aceptación o desestimiento del lector a las condiciones
     * económicas y restricciones de uso
     */
    protected Date                     fechaAceptaDesiste;

    /**
     * URL a un documento remitido por el lector que refleja su aceptación o
     * desestimiento (p.e. copia de un mail, una carta, ...) de las condiciones
     * económicas y restricciones de uso
     */
    protected String                   urlDocumentoAceptaDesiste;

    /**
     * Texto libre con el que el usuario puede documentar información adicional
     * sobre la aceptación o desestimiento del lector de las condiciones
     * ecómicas y restricciones de uso, por ejemplo, resultado de una
     * conversación telefónica, ...
     */
    protected String                   aceptaDesiste;

    /**
     * true si el préstamo asociado a esta petición está en el histórico de
     * préstamos CIR_PRESTAMOS_HIST (PRE_X_PRESTAMO referencia a una fila de esa
     * tabla), es decir, si fue devuelto.<br>
     * false en caso contrario.
     */
    protected Boolean                  prestamoDevuelto;

    /**
     * Fecha en que el lector comunica que desea anular la petición
     */
    protected Date                     fechaAnulacion;

    /**
     * Documento remitido por el lector en el que comunica su deseo de que se
     * anule le petición (p.e. copia de un mail, una carta, ...)
     */
    protected byte[]                   docAnulacion;

    /**
     * Nombre del documento de anulación
     */
    private String                     docAnulacionNombre;

    /**
     * ContentType del fichero de anulación.
     */
    private String                     docAnulacionContentType;

    /**
     * Texto libre introducido por el usuario<br>
     * Añade información adocional que el usuario considere de interés sobre la
     * anulación de la petición solicitada por el lector
     */
    protected String                   notasAnulacion;

    /**
     * >> CIR_PRESTAMOS o CIR_PRESTAMOS_HIST,<br>
     * según el valor de L_PRESTAMO_DEVUELTO<br>
     * Valor NULL significa que no se ha realizado el préstamo
     */
    protected Prestamo                 prestamo;

    /**
     * Tipo de préstamo contemplado en el PIB
     */
    protected Long                     tipoPrestamoPIB;

    /**
     * >> CIR_PRESTAMOS o CIR_PRESTAMOS_HIST,<br>
     * según el valor de L_PRESTAMO_DEVUELTO<br>
     * Valor NULL significa que no se ha realizado el préstamo
     */
    protected PrestamoHistorico        prestamoHistorico;

    /**
     * >> LEC_LECTORES<br>
     * La entidad LEC_LECTORES informa también de cual es la biblioteca a la que
     * esta afiliado el lector
     */
    protected Lector                   lector;

    /**
     * >> DIRECCIONES<br>
     * Dirección de las instrucciones de envío que se envía en los mensajes de
     * NCIP (REQ == Request) o correos-e, si es distinta de la dirección de Mi
     * Biblioteca (L_DIR_FISICA_REQ_CAMBIADA == true)
     */
    protected Direccion                direccionReq;

    /**
     * Biblioteca Borrower (prestataria)<br>
     * =================================
     */

    /**
     * >> BIBLIOTECAS<br>
     * Indica que biblioteca de las registradas en la aplicación es la
     * propietaria de esta petición (la biblioteca que tramita esta petición)
     * NOT NULL
     */
    protected Biblioteca               bibliotecaBorrower;

    /**
     * >> CIR_PIB_BIBLIOTECAS_EXTERNAS<br>
     * Indica que biblioteca externa es la propietaria de esta petición (la
     * biblioteca que tramita esta petición<br>
     * <br>
     * Restricción:<br>
     * Este campo es excluyente con BIB_X_BIBLIOTECA: sólo se cumplimenta este
     * campo si la biblioteca NO es una de las registradas en la aplicación (RA
     * BIBLIOTECAS)
     */
    protected BibliotecaExterna        bibliotecaExternaBorrower;

    /**
     * Bibliotecas Lender (prestamistas)<br>
     * =================================
     */

    /**
     * >> BIBLIOTECAS<br>
     * Sólo se cumplimenta si la biblioteca objeto de la petición es una de las
     * registradas en BIBLIOTECAS<br>
     * <br>
     * Restricción:<br>
     * Excluyente con BIB_EXT_X_PIB_BIBLIOTECA_EXTERNA_LENDER
     */
    protected Biblioteca               bibliotecaLender;

    /**
     * >> CIR_PIB_BIBLIOTECAS_EXTERNAS<br>
     * Biblioteca a la que se realiza la petición de PIB, esto es, la
     * propietaria del ejemplar<br>
     * <br>
     * Restricción:<br>
     * Este campo es excluyente con BIB_X_BIBLIOTECA_LENDER: sólo se cumplimenta
     * este campo si la biblioteca NO es una de las registradas en la aplicación
     * (RA BIBLIOTECAS)
     */
    protected BibliotecaExterna        bibliotecaExternaLender;

    /**
     * >> MON_X_MONEDA<br>
     * La biblioteca prestamista informa del código de moneda con un valor de
     * MONEDAS.T_CODIGO_ALFABETICO. En esta clave ajena se guarda el valor de
     * MONEDAS.X_MONEDA tal que MONEDAS.T_CODIGO_ALFABETICO es el informado por
     * la biblioteca prestamista Y MONEDAS.F_OBSOLETA IS NULL<br>
     * Informado por la biblioteca prestamista
     */
    protected Moneda                   moneda;

    /**
     * >> CIR_PIB_TIPOS_PETICIONES
     */
    protected TipoPeticionPIB          tipoPeticion;

    /**
     * >> CIR_PIB_ESTADOS_PETICIONES
     */
    protected EstadoPeticionPIB        estadoPeticion;

    /**
     * >> CIR_PIB_ALCANCES_PETICIONES
     */
    protected AlcancePeticionPIB       alcancePeticion;

    /**
     * >> CIR_PIB_TIPOS_ACCIONES_TRANSACCIONES<br>
     * Tipo de acción en la transacción (pago normalmente)<br>
     * Corresponde al elemento de dato de NCIP
     * FiscalTransactionInformation.FiscalActionType<br>
     * INformado por la biblioteca prestamista
     */
    protected TipoAccionTransaccionPIB tipoAccionTransaccion;

    /**
     * >> CIR_PIB_CONCEPTOS_TRANSACCIONES<br>
     * Tipo de transacción económica (cargo por PIB, ...)<br>
     * Corresponde al elemento de dato FiscalTransaction
     * Information.FiscalTransactionType de NCIP<br>
     * INformado por la biblioteca prestamista
     */
    protected ConceptoTransaccionPIB   conceptoTransaccion;

    /**
     * >> CIR_PIB_METODOS_PAGOS_TRANSACCIONES<br>
     * Método de pago del importe<br>
     * Informado por la biblioteca prestamista<br>
     * Corresponde al elemento de dato FiscalTransaction.PaymentMethodType de
     * NCIP
     */
    protected MetodoPagoTransaccionPIB metodoPagoTransaccion;

    /**
     * >> CIR_PIB_PETICIONES<br>
     * Petición a la que ésta está vinculada<br>
     * Por defecto no vinculada a ninguna
     */
    protected PeticionPIB              peticionVinculadaA;

    /**
     * >> CIR_PIB_TIPOS_RESOLUCIONES
     */
    protected TipoResolucionPIB        tipoResolucion;

    /**
     * Registro bibliográfico al que se refiere la reserva
     */
    protected Registro                 registro;

    /**
     * Ejemplar objeto de la peticion.
     */
    protected Ejemplar                 ejemplar;

    /**
     * getter & setters<br>
     * ================
     */

    /**
     * @return the textoLector
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_TEXTO_LECTOR)
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
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_FECHA_CREACION)
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
     * @return the fechaHastaNoNecesita
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_FECHA_HASTA_NO_NECESITA)
    public Date getFechaHastaNoNecesita() {
        return fechaHastaNoNecesita;
    }

    /**
     * @param fechaHastaNoNecesita
     *            the fechaHastaNoNecesita to set
     */
    public void setFechaHastaNoNecesita(Date fechaHastaNoNecesita) {
        this.fechaHastaNoNecesita = fechaHastaNoNecesita;
    }

    /**
     * @return the fechaAntesNecesita
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_FECHA_ANTES_NECESITA)
    public Date getFechaAntesNecesita() {
        return fechaAntesNecesita;
    }

    /**
     * @param fechaAntesNecesita
     *            the fechaAntesNecesita to set
     */
    public void setFechaAntesNecesita(Date fechaAntesNecesita) {
        this.fechaAntesNecesita = fechaAntesNecesita;
    }

    /**
     * @return the motivacionResolucion
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_MOTIVACION_RESOLUCION)
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
     * @return the autor
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_AUTOR)
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
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_TITULO)
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
     * @return the publicacion
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_PUBLICACION, length = 80)
    public String getPublicacion() {
        return publicacion;
    }

    /**
     * @param publicacion
     *            the publicacion to set
     */
    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    /**
     * @return the materia
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_MATERIA, length = 40)
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
     * @return the isbn
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_ISBN, length = 40)
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the ejemplarId
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_EJEMPLAR_ID, length = 40)
    public String getEjemplarId() {
        return ejemplarId;
    }

    /**
     * @param ejemplarId
     *            the ejemplarId to set
     */
    public void setEjemplarId(String ejemplarId) {
        this.ejemplarId = ejemplarId;
    }

    /**
     * @return the recibidaViaWebService
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_RECIBIDA_VIA_WEB_SERVICE)
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
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_LECTOR_BLOQUEADAS_PETICIONES)
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
     * @return the direccionResp
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_DIRECCION_RESP)
    public String getDireccionResp() {
        return direccionResp;
    }

    /**
     * @param direccionResp
     *            the direccionResp to set
     */
    public void setDireccionResp(String direccionResp) {
        this.direccionResp = direccionResp;
    }

    /**
     * @return the emailReq
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_EMAIL_REQ, length = 80)
    public String getEmailReq() {
        return emailReq;
    }

    /**
     * @param emailReq
     *            the emailReq to set
     */
    public void setEmailReq(String emailReq) {
        this.emailReq = emailReq;
    }

    /**
     * @return the emailResp
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_EMAIL_RESP, length = 80)
    public String getEmailResp() {
        return emailResp;
    }

    /**
     * @param emailResp
     *            the emailResp to set
     */
    public void setEmailResp(String emailResp) {
        this.emailResp = emailResp;
    }

    /**
     * @return the tipoDirPorDefectoReq
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_TIPO_DIR_POR_DEFECTO_REQ, length = 80)
    public String getTipoDirPorDefectoReq() {
        return tipoDirPorDefectoReq;
    }

    /**
     * @param tipoDirPorDefectoReq
     *            the tipoDirPorDefectoReq to set
     */
    public void setTipoDirPorDefectoReq(String tipoDirPorDefectoReq) {
        this.tipoDirPorDefectoReq = tipoDirPorDefectoReq;
    }

    /**
     * @return the notasReq
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_NOTAS_REQ)
    public String getNotasReq() {
        return notasReq;
    }

    /**
     * @param notasReq
     *            the notasReq to set
     */
    public void setNotasReq(String notasReq) {
        this.notasReq = notasReq;
    }

    /**
     * @return the instruccionesReq
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_INSTRUCCIONES_REQ)
    public String getInstruccionesReq() {
        return instruccionesReq;
    }

    /**
     * @param instruccionesReq
     *            the instruccionesReq to set
     */
    public void setInstruccionesReq(String instruccionesReq) {
        this.instruccionesReq = instruccionesReq;
    }

    /**
     * @return the notasResp
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_NOTAS_RESP)
    public String getNotasResp() {
        return notasResp;
    }

    /**
     * @param notasResp
     *            the notasResp to set
     */
    public void setNotasResp(String notasResp) {
        this.notasResp = notasResp;
    }

    /**
     * @return the instruccionesResp
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_INSTRUCCIONES_RESP)
    public String getInstruccionesResp() {
        return instruccionesResp;
    }

    /**
     * @param instruccionesResp
     *            the instruccionesResp to set
     */
    public void setInstruccionesResp(String instruccionesResp) {
        this.instruccionesResp = instruccionesResp;
    }

    /**
     * @return the dirFisicaReqCambiada
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_DIR_FISICA_REQ_CAMBIADA)
    public Boolean getDirFisicaReqCambiada() {
        return dirFisicaReqCambiada;
    }

    /**
     * @param dirFisicaReqCambiada
     *            the dirFisicaReqCambiada to set
     */
    public void setDirFisicaReqCambiada(Boolean dirFisicaReqCambiada) {
        this.dirFisicaReqCambiada = dirFisicaReqCambiada;
    }

    /**
     * @return the instrEnvioRecibidasViaNcip
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_INSTR_ENVIO_RECIBID_VIA_NCIP)
    public Boolean getInstrEnvioRecibidasViaNcip() {
        return instrEnvioRecibidasViaNcip;
    }

    /**
     * @param instrEnvioRecibidasViaNcip
     *            the instrEnvioRecibidasViaNcip to set
     */
    public void setInstrEnvioRecibidasViaNcip(Boolean instrEnvioRecibidasViaNcip) {
        this.instrEnvioRecibidasViaNcip = instrEnvioRecibidasViaNcip;
    }

    /**
     * @return the fechaDisponibilidad
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_FECHA_DISPONIBILIDAD)
    public Date getFechaDisponibilidad() {
        return fechaDisponibilidad;
    }

    /**
     * @param fechaDisponibilidad
     *            the fechaDisponibilidad to set
     */
    public void setFechaDisponibilidad(Date fechaDisponibilidad) {
        this.fechaDisponibilidad = fechaDisponibilidad;
    }

    /**
     * @return the fechaFinRetirada
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_FECHA_FIN_RETIRADA)
    public Date getFechaFinRetirada() {
        return fechaFinRetirada;
    }

    /**
     * @param fechaFinRetirada
     *            the fechaFinRetirada to set
     */
    public void setFechaFinRetirada(Date fechaFinRetirada) {
        this.fechaFinRetirada = fechaFinRetirada;
    }

    /**
     * @return the importe
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_IMPORTE)
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
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_ID_TRANSACCION, length = 40)
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
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_INFO_ADICIONAL_TRANSACCION)
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
     * @return the datosTransaccionEconomicaViaNcip
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_DATOS_TRANS_ECONOM_VIA_NCIP)
    public Boolean getDatosTransaccionEconomicaViaNcip() {
        return datosTransaccionEconomicaViaNcip;
    }

    /**
     * @param datosTransaccionEconomicaViaNcip
     *            the datosTransaccionEconomicaViaNcip to set
     */
    public void setDatosTransaccionEconomicaViaNcip(
            Boolean datosTransaccionEconomicaViaNcip) {
        this.datosTransaccionEconomicaViaNcip = datosTransaccionEconomicaViaNcip;
    }

    /**
     * @return the requiereAceptacionLector
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_REQUIERE_ACEPTACION_LECTOR)
    public Long getRequiereAceptacionLector() {
        return requiereAceptacionLector;
    }

    /**
     * @param requiereAceptacionLector
     *            the requiereAceptacionLector to set
     */
    public void setRequiereAceptacionLector(Long requiereAceptacionLector) {
        this.requiereAceptacionLector = requiereAceptacionLector;
    }

    /**
     * @return the lectorAcepta
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_LECTOR_ACEPTA)
    public Long getLectorAcepta() {
        return lectorAcepta;
    }

    /**
     * @param lectorAcepta
     *            the lectorAcepta to set
     */
    public void setLectorAcepta(Long lectorAcepta) {
        this.lectorAcepta = lectorAcepta;
    }

    /**
     * @return the fechaAceptaDesiste
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_FECHA_ACEPTA_DESISTE)
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
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_URL_DOCUMENTO_ACEPTA_DESISTE)
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
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_ACEPTA_DESISTE)
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
     * @return the prestamoDevuelto
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_PRESTAMO_DEVUELTO)
    public Boolean getPrestamoDevuelto() {
        return prestamoDevuelto;
    }

    /**
     * @param prestamoDevuelto
     *            the prestamoDevuelto to set
     */
    public void setPrestamoDevuelto(Boolean prestamoDevuelto) {
        this.prestamoDevuelto = prestamoDevuelto;
    }

    /**
     * @return the fechaAnulacion
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_FECHA_ANULACION)
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
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_DOCUMENTO_ANULACION)
    @Lob
    public byte[] getDocAnulacion() {
        return docAnulacion;
    }

    /**
     * @param urlDocumentoAnulacion
     *            the urlDocumentoAnulacion to set
     */
    public void setDocAnulacion(byte[] documentoAnulacion) {
        docAnulacion = documentoAnulacion;
    }

    /**
     * @return the notasAnulacion
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_NOTAS_ANULACION)
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
     * @return the prestamo
     */
    @ManyToOne(targetEntity = Prestamo.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_PRESTAMO)
    public Prestamo getPrestamo() {
        return prestamo;
    }

    /**
     * @param prestamo
     *            the prestamo to set
     */
    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    /**
     * @return the prestamoHistorico
     */
    @ManyToOne(targetEntity = PrestamoHistorico.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_PRESTAMO_HISTORICO)
    public PrestamoHistorico getPrestamoHistorico() {
        return prestamoHistorico;
    }

    /**
     * @param prestamoHistorico
     *            the prestamoHistorico to set
     */
    public void setPrestamoHistorico(PrestamoHistorico prestamoHistorico) {
        this.prestamoHistorico = prestamoHistorico;
    }

    /**
     * @return the lector
     */
    @ManyToOne(targetEntity = Lector.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_LECTOR)
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
     * @return the direccionReq
     */
    @ManyToOne(targetEntity = Direccion.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_DIRECCION_REQ)
    public Direccion getDireccionReq() {
        return direccionReq;
    }

    /**
     * @param direccionReq
     *            the direccionReq to set
     */
    public void setDireccionReq(Direccion direccionReq) {
        this.direccionReq = direccionReq;
    }

    /**
     * @return the bibliotecaBorrower
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_BIBLIOTECA_BORROWER)
    public Biblioteca getBibliotecaBorrower() {
        return bibliotecaBorrower;
    }

    /**
     * @param bibliotecaBorrower
     *            the bibliotecaBorrower to set
     */
    public void setBibliotecaBorrower(Biblioteca bibliotecaBorrower) {
        this.bibliotecaBorrower = bibliotecaBorrower;
    }

    /**
     * @return the bibliotecaExternaBorrower
     */
    @ManyToOne(targetEntity = BibliotecaExterna.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_BIBLIOTECA_EXTERNA_BORROWER)
    public BibliotecaExterna getBibliotecaExternaBorrower() {
        return bibliotecaExternaBorrower;
    }

    /**
     * @param bibliotecaExternaBorrower
     *            the bibliotecaExternaBorrower to set
     */
    public void setBibliotecaExternaBorrower(
            BibliotecaExterna bibliotecaExternaBorrower) {
        this.bibliotecaExternaBorrower = bibliotecaExternaBorrower;
    }

    /**
     * @return the bibliotecaLender
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_BIBLIOTECA_LENDER)
    public Biblioteca getBibliotecaLender() {
        return bibliotecaLender;
    }

    /**
     * @param bibliotecaLender
     *            the bibliotecaLender to set
     */
    public void setBibliotecaLender(Biblioteca bibliotecaLender) {
        this.bibliotecaLender = bibliotecaLender;
    }

    /**
     * @return the bibliotecaExternaLender
     */
    @ManyToOne(targetEntity = BibliotecaExterna.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_BIBLIOTECA_EXTERNA_LENDER)
    public BibliotecaExterna getBibliotecaExternaLender() {
        return bibliotecaExternaLender;
    }

    /**
     * @param bibliotecaExternaLender
     *            the bibliotecaExternaLender to set
     */
    public void setBibliotecaExternaLender(
            BibliotecaExterna bibliotecaExternaLender) {
        this.bibliotecaExternaLender = bibliotecaExternaLender;
    }

    /**
     * @return the moneda
     */
    @ManyToOne(targetEntity = Moneda.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_MONEDA)
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
     * @return the tipoPeticion
     */
    @ManyToOne(targetEntity = TipoPeticionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_TIPO_PETICION)
    public TipoPeticionPIB getTipoPeticion() {
        return tipoPeticion;
    }

    /**
     * @param tipoPeticion
     *            the tipoPeticion to set
     */
    public void setTipoPeticion(TipoPeticionPIB tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }

    /**
     * @return the estadoPeticion
     */
    @ManyToOne(targetEntity = EstadoPeticionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_ESTADO_PETICION)
    public EstadoPeticionPIB getEstadoPeticion() {
        return estadoPeticion;
    }

    /**
     * @param estadoPeticion
     *            the estadoPeticion to set
     */
    public void setEstadoPeticion(EstadoPeticionPIB estadoPeticion) {
        this.estadoPeticion = estadoPeticion;
    }

    /**
     * @return the alcancePeticion
     */
    @ManyToOne(targetEntity = AlcancePeticionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_ALCANCE_PETICION)
    public AlcancePeticionPIB getAlcancePeticion() {
        return alcancePeticion;
    }

    /**
     * @param alcancePeticion
     *            the alcancePeticion to set
     */
    public void setAlcancePeticion(AlcancePeticionPIB alcancePeticion) {
        this.alcancePeticion = alcancePeticion;
    }

    /**
     * @return the tipoAccionTransaccion
     */
    @ManyToOne(targetEntity = TipoAccionTransaccionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_TIPO_ACCION_TRANSACCION)
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
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_CONCEPTO_TRANSACCION)
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
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_METODO_PAGO_TRANSACCION)
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
     * @return the peticionVinculadaA
     */
    @ManyToOne(targetEntity = PeticionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = PeticionPIB.COLUMN_NAME_PETICION_VINCULADA_A)
    public PeticionPIB getPeticionVinculadaA() {
        return peticionVinculadaA;
    }

    /**
     * @param peticionVinculadaA
     *            the peticionVinculadaA to set
     */
    public void setPeticionVinculadaA(PeticionPIB peticionVinculadaA) {
        this.peticionVinculadaA = peticionVinculadaA;
    }

    /**
     * @return the tipoResolucion
     */
    @ManyToOne(targetEntity = TipoResolucionPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = PeticionPIB.COLUMN_NAME_TIPO_RESOLUCION)
    public TipoResolucionPIB getTipoResolucion() {
        return tipoResolucion;
    }

    /**
     * @param tipoResolucion
     *            the tipoResolucion to set
     */
    public void setTipoResolucion(TipoResolucionPIB tipoResolucion) {
        this.tipoResolucion = tipoResolucion;
    }

    /**
     * @return the documentoNombre
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_DOCUMENTO_NOMBRE, length = 80)
    public String getDocAnulacionNombre() {
        return docAnulacionNombre;
    }

    /**
     * @param documentoNombre
     *            the documentoNombre to set
     */
    public void setDocAnulacionNombre(String documentoNombre) {
        docAnulacionNombre = documentoNombre;
    }

    /**
     * @return the documentoContentType
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_DOCUMENTO_CONTENT_TYPE, length = 80)
    public String getDocAnulacionContentType() {
        return docAnulacionContentType;
    }

    /**
     * @param documentoContentType
     *            the documentoContentType to set
     */
    public void setDocAnulacionContentType(String documentoContentType) {
        docAnulacionContentType = documentoContentType;
    }

    /**
     * @return the tipoPrestamoPIB
     */
    @Column(name = AbstractPeticionPIB.COLUMN_NAME_TIPO_PRESTAMO_PIB)
    public Long getTipoPrestamoPIB() {
        return tipoPrestamoPIB;
    }

    /**
     * @param tipoPrestamoPIB
     *            the tipoPrestamoPIB to set
     */
    public void setTipoPrestamoPIB(Long tipoPrestamoPIB) {
        this.tipoPrestamoPIB = tipoPrestamoPIB;
    }

    /**
     * @return the ejemplar
     */
    @ManyToOne(targetEntity = Ejemplar.class, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_EJEMPLAR)
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
     * @return the registro
     */
    @ManyToOne(targetEntity = Registro.class, fetch = FetchType.LAZY)
    @JoinColumn(name = AbstractPeticionPIB.COLUMN_NAME_REGISTRO)
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
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * @param other
     * @return
     */
    protected boolean equals(AbstractPeticionPIB other) {
        if (getLector() == null && other.getLector() != null) {
            return false;
        }
        if (getLector() != null && !getLector().equals(other.getLector())) {
            return false;
        }

        if (getEjemplarId() == null && other.getEjemplarId() != null) {
            return false;
        }
        if (getEjemplarId() != null
                && !getEjemplarId().equals(other.getEjemplarId())) {
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
        return new ToStringBuilder(this).append(PROPTY_NAME_LECTOR,
                (getLector() == null) ? "" : getLector().toString())

        .append(PROPTY_NAME_EJEMPLAR_ID,
                (getEjemplarId() == null) ? "" : getEjemplarId().toString())

        .append(
                PROPTY_NAME_FECHA_CREACION,
                (getFechaCreacion() == null) ? "" : getFechaCreacion()
                        .toString())

        .toString();
    }

}