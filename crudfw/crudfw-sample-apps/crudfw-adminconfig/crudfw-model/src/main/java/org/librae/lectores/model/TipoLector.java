package org.librae.lectores.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.parampoliticas.AbstractParamPolTipoLector;
import org.librae.common.service.impl.ComboLocaleManager;

@Entity(name = TipoLector.ENTITY_NAME)
@Table(name = TipoLector.TABLE_NAME)
public class TipoLector extends AbstractParamPolTipoLector {

    /**
     * BaseObject is Serializable, por lo tanto necesita un Serial Version UID
     */
    private static final long     serialVersionUID                         = 9056347266154265270L;

    public static final String    ENTITY_NAME                              = "org.librae.lectores.model.TipoLector";

    /**
     * Constantes para referencia de los atributos de TipoLector
     */
    public static final String    PROPTY_NAME_ID                           = "id";
    public static final String    PROPTY_NAME_CODIGO_TIPO_LECTOR           = "codigoTipoLector";
    public static final String    PROPTY_NAME_CODIGO_NCIP_LECTOR           = "ncipValor";
    public static final String    PROPTY_NAME_DESCRIPCION_TIPO_LECTOR      = "descripcionTipoLector";
    public static final String    PROPTY_NAME_DESCRIPCION_TIPO_LECTOR_ALT  = "descripcionTipoLectorAlt";
    public static final String    PROPTY_NAME_ORDEN                        = "orden";
    public static final String    PROPTY_NAME_SELECCIONADO                 = "seleccionado";
    public static final String    PROPTY_NAME_PLAZO_CADUCIDAD              = "plazoCaducidad";
    public static final String    PROPTY_NAME_PLAZO_RENOVACION             = "plazoRenovacion";
    public static final String    PROPTY_NAME_CARTA_RECLAMACION_OPCION     = "cartaReclamacionOpcion";
    public static final String    PROPTY_NAME_PRIORIDAD_RESERVA_DEFECTO    = "prioridadReservaDefecto";
    public static final String    PROPTY_NAME_DIAS_RETIRADA_RESERVA        = "diasRetiradaReserva";
    public static final String    PROPTY_NAME_CARTA_DISPON_RESERVAS_OPCION = "cartaDisponReservasOpcion";
    public static final String    PROPTY_NAME_BORRADO                      = "borrado";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String    TABLE_NAME                               = "LEC_TIPO_LECTORES";
    public static final String    ID_GENERATOR_NAME                        = "generator_lec_tipo_lectores";
    public static final String    ID_SEQUENCE_NAME                         = "SEQ_LEC_TIPO_LECTOR";

    public static final String    COLUMN_NAME_ID                           = "X_TIPO_LECTOR";
    public static final String    COLUMN_NAME_CODIGOTIPOLECTOR             = "N_CODIGO_TIPO_LECTOR";
    public static final String    COLUMN_NAME_NCIPVALOR                    = "N_NCIP_VALOR";
    public static final String    COLUMN_NAME_DESCRIPCIONTIPOLECTOR        = "T_DESCRIPCION_TIPO_LECTOR";
    public static final String    COLUMN_NAME_DESCRIPCIONTIPOLECTORALT     = "T_DESCRIP_TIPO_LECTOR_ALT";
    public static final String    COLUMN_NAME_ORDEN                        = "N_ORDEN";
    public static final String    COLUMN_NAME_PLAZOCADUCIDAD               = "N_PLAZO_CADUCIDAD";
    public static final String    COLUMN_NAME_PLAZORENOVACION              = "N_PLAZO_RENOVACION";
    public static final String    COLUMN_NAME_CARTARECLAMACION             = "N_CARTA_RECLAMACION_OPCIONES";
    public static final String    COLUMN_NAME_PRIORIDADRESERVAS            = "N_PRIOR_RESERVAS_POR_DEFECTO";
    public static final String    COLUMN_NAME_DIASRETIRADARESERVA          = "N_DIAS_RETIRADA_RESERVA";
    public static final String    COLUMN_NAME_CARTADISPONIBILIDAD          = "N_CARTA_DISPON_RESERV_OPCIONS";
    public static final String    COLUMN_NAME_BORRADO                      = "L_BORRADO";

    /**
     * Constantes de referencia a los posibles valores de carta de reclamación
     */
    public static final Integer[] CARTA_RECLAMACION_OPCIONES_VALUES        = {
            0, 1, 2, 3                                                    };
    public static final String[]  CARTA_RECLAMACION_OPCION_KEYS            = {
            "CARTA_RECLAMACION_OPCION_KEY_0", "CARTA_RECLAMACION_OPCION_KEY_1",
            "CARTA_RECLAMACION_OPCION_KEY_2", "CARTA_RECLAMACION_OPCION_KEY_3" };

    /**
     * Constantes de referencia a los posibles valores de carta de
     * disponibilidad de reservas
     */
    public static final Integer[] CARTA_DISPONIBILIDAD_OPCIONES_VALUES     = {
            0, 1, 2, 3                                                    };
    public static final String[]  CARTA_DISPONIBILIDAD_OPCION_KEYS         = {
            "CARTA_DISPONIBILIDAD_OPCION_KEY_0",
            "CARTA_DISPONIBILIDAD_OPCION_KEY_1",
            "CARTA_DISPONIBILIDAD_OPCION_KEY_2",
            "CARTA_DISPONIBILIDAD_OPCION_KEY_3"                           };

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                  id;

    /**
     * C�digo �nico (dentro de una misma biblioteca) del tipo de lectores,
     * asignado por el usuario.
     */
    private String                codigoTipoLector;

    /**
     * Indica a que valor de un Open Enumerated Type de NCIP corresponde este
     * tipo de lector Restricci�n: Los valores de esta columna deben ser �nicos:
     * no puede haber dos filas de esta tabla que referencien al mismo valor de
     * NCIP
     */
    private Integer               ncipValor;

    /**
     * Descripci�n o nombre del tipo de lectores
     */
    private String                descripcionTipoLector;

    /**
     * Descripci�n o nombre alternativo del tipo de ejemplares
     */
    private String                descripcionTipoLectorAlt;

    /**
     * Indica el orden de presentación del tipo de lector en el listado de la
     * pantalla en función de los más habituales.
     */
    private Integer               orden;

    /**
    *
    */
    private List<LectorTipo>      lectorTipos;

    /**
     * Este valor se usara para indicar que el tipo de lector ha sido
     * seleccionado. No es de la base de datos
     */
    private Boolean               seleccionado;

    /**
     * Indica en d�as el plazo que inicialmente tiene de validez el carnet de un
     * usuario cuando se da de alta. Un valor 0 significa que no caduca nunca
     */
    private Integer               plazoCaducidad;

    /**
     * Indica en d�as la duraci�n del carnet cuando se renueve. Un valor 0
     * significa que no caduca nunca el carnet y no aplica la renovaci�n
     */
    private Integer               plazoRenovacion;

    /**
     * Indica si se env�a carta de reclamaci�n de pr�stamos sobrepasados a los
     * lectores a los que se aplica esta pol�tica. Valores: 0 = Nos e env�an
     * notificaciones de reclamacion de pr�stamos 1 = Se env�an cartas s�lo via
     * email, a los lectores que tengan correo-e 2 = Se env�an cartas a los
     * lectores que NO tengan correo-e (en papel) 3 = Se env�an cartas a todos
     * los lectores, v�a papel a los que no tengan correo-e, y v�a email y papel
     * a los que si tengan correo-e NOT NULL
     */

    private Integer               cartaReclamacionOpcion;

    /**
     * Prioridad por defecto de las reservas que se hacen para lectores de este
     * tipo 0 es la prioridad m�s alta, y 10 la m�s baja
     */
    private Integer               prioridadReservaDefecto                  = Integer
                                                                                   .valueOf(10);

    /**
     * Cuantos d�as, contados a partir de la fecha-hora de vinculaci�n del
     * ejemplar devuelto, con la reserva de la cola, tiene el lector propietario
     * de la reserva para hacer efectivo el pr�stamo
     */
    private Integer               diasRetiradaReserva                      = Integer
                                                                                   .valueOf(5);

    /**
     * Indica si se env�a a los lectores de este tipo una carta de informando de
     * que la reserva est� diponible para retirar en pr�stamo el ejemplar
     * Valores: 0 = Nos se env�an notificaciones 1 = Se env�an cartas s�lo via
     * email, a los lectores que tengan correo-e 2 = Se env�an cartas a los
     * lectores que NO tengan correo-e (en papel) 3 = Se env�an cartas a todos
     * los lectores, v�a papel a los que no tengan correo-e, y v�a email y papel
     * a los que si tengan correo-e NOT NULL
     */
    private Integer               cartaDisponReservasOpcion;

    /**
     * True si el tipo de lector esta borrado.
     * False si el tipo de lector es válido.
     */
    private Boolean               borrado;
    
    /**
     * Constructor sin parámetros
     */
    protected TipoLector() {
        super();
        lectorTipos = new ArrayList<LectorTipo>();
    }

    /**
     * Constructor con parámetros
     *
     * @param codigoTipoLector
     * @param descripcionTipoLector
     */
    public TipoLector(final String codigoTipoLector,
            final String descripcionTipoLector) {
        super();
        this.codigoTipoLector = codigoTipoLector;
        this.descripcionTipoLector = descripcionTipoLector;
    }

    /**
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TipoLector.ID_GENERATOR_NAME)
    @SequenceGenerator(name = TipoLector.ID_GENERATOR_NAME, sequenceName = TipoLector.ID_SEQUENCE_NAME)
    @Column(name = TipoLector.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return codigoTipoLector
     */
    @Column(name = TipoLector.COLUMN_NAME_CODIGOTIPOLECTOR, nullable = false, unique = true, length = 40)
    public String getCodigoTipoLector() {
        return codigoTipoLector;
    }

    /**
     * @param codigoTipoLector
     */
    public void setCodigoTipoLector(final String codigoTipoLector) {
        this.codigoTipoLector = codigoTipoLector;
    }

    /**
     * @return ncipValor
     */
    @Column(name = TipoLector.COLUMN_NAME_NCIPVALOR)
    public Integer getNcipValor() {
        return ncipValor;
    }

    /**
     * @param ncipValor
     */
    public void setNcipValor(final Integer ncipValor) {
        this.ncipValor = ncipValor;
    }

    /**
     * @return descripcionTipoLector
     */
    @Column(name = TipoLector.COLUMN_NAME_DESCRIPCIONTIPOLECTOR, nullable = false, unique = true, length = 50)
    public String getDescripcionTipoLector() {
        return descripcionTipoLector;
    }

    /**
     * @param descripcionTipoLector
     */
    public void setDescripcionTipoLector(final String descripcionTipoLector) {
        this.descripcionTipoLector = descripcionTipoLector;
    }

    /**
     * @return descripcionTipoLectorAlt
     */
    @Column(name = TipoLector.COLUMN_NAME_DESCRIPCIONTIPOLECTORALT, length = 50)
    public String getDescripcionTipoLectorAlt() {
        return descripcionTipoLectorAlt;
    }

    /**
     * @param descripcionTipoLectorAlt
     */
    public void setDescripcionTipoLectorAlt(
            final String descripcionTipoLectorAlt) {
        this.descripcionTipoLectorAlt = descripcionTipoLectorAlt;
    }

    /**
     * @return the orden
     */
    @Column(name = TipoLector.COLUMN_NAME_ORDEN)
    public Integer getOrden() {
        return orden;
    }

    /**
     * @param orden
     *            the orden to set
     */
    public void setOrden(final Integer orden) {
        this.orden = orden;
    }

    /**
     * @return the lectorTipos
     */
    @OneToMany(mappedBy = LectorTipo.PROPTY_NAME_TIPO_LECTOR, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = LectorTipo.class)
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
     * @return the seleccionado
     */
    @Transient
    public Boolean getSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado
     *            the seleccionado to set
     */
    public void setSeleccionado(final Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * @return the plazoCaducidad
     */
    @Column(name = TipoLector.COLUMN_NAME_PLAZOCADUCIDAD)
    public Integer getPlazoCaducidad() {
        return plazoCaducidad;
    }

    /**
     * @param plazoCaducidad
     *            the plazoCaducidad to set
     */
    public void setPlazoCaducidad(final Integer plazoCaducidad) {
        this.plazoCaducidad = plazoCaducidad;
    }

    /**
     * @return the plazoRenovacion
     */
    @Column(name = TipoLector.COLUMN_NAME_PLAZORENOVACION)
    public Integer getPlazoRenovacion() {
        return plazoRenovacion;
    }

    /**
     * @param plazoRenovacion
     *            the plazoRenovacion to set
     */
    public void setPlazoRenovacion(final Integer plazoRenovacion) {
        this.plazoRenovacion = plazoRenovacion;
    }

    /**
     * @return the cartaReclamacionOpcion
     */
    @Column(name = TipoLector.COLUMN_NAME_CARTARECLAMACION)
    public Integer getCartaReclamacionOpcion() {
        return cartaReclamacionOpcion;
    }

    /**
     * @param cartaReclamacionOpcion
     *            the cartaReclamacionOpcion to set
     */
    public void setCartaReclamacionOpcion(final Integer cartaReclamacionOpcion) {
        this.cartaReclamacionOpcion = cartaReclamacionOpcion;
    }

    /**
     * @return the prioridadReservaDefecto
     */
    @Column(name = TipoLector.COLUMN_NAME_PRIORIDADRESERVAS)
    public Integer getPrioridadReservaDefecto() {
        return prioridadReservaDefecto;
    }

    /**
     * @param prioridadReservaDefecto
     *            the prioridadReservaDefecto to set
     */
    public void setPrioridadReservaDefecto(final Integer prioridadReservaDefecto) {
        this.prioridadReservaDefecto = prioridadReservaDefecto;
    }

    /**
     * @return the diasRetiradaReserva
     */
    @Column(name = TipoLector.COLUMN_NAME_DIASRETIRADARESERVA)
    public Integer getDiasRetiradaReserva() {
        return diasRetiradaReserva;
    }

    /**
     * @param diasRetiradaReserva
     *            the diasRetiradaReserva to set
     */
    public void setDiasRetiradaReserva(final Integer diasRetiradaReserva) {
        this.diasRetiradaReserva = diasRetiradaReserva;
    }

    /**
     * @return the cartaDisponReservasOpcion
     */
    @Column(name = TipoLector.COLUMN_NAME_CARTADISPONIBILIDAD)
    public Integer getCartaDisponReservasOpcion() {
        return cartaDisponReservasOpcion;
    }

    /**
     * @param cartaDisponReservasOpcion
     *            the cartaDisponReservasOpcion to set
     */
    public void setCartaDisponReservasOpcion(
            final Integer cartaDisponReservasOpcion) {
        this.cartaDisponReservasOpcion = cartaDisponReservasOpcion;
    }

    @Column(name = TipoLector.COLUMN_NAME_BORRADO)
    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    /**
     * Traduce el campo descripcionTipoLector.
     *
     * @return
     */
    @Transient
    public String getDescripcionTipoLectorLocale() {
        final String s = ComboLocaleManager.get(descripcionTipoLector.replace(
                "#", ""));
        return (s == null) ? "" : s;
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
                + ((codigoTipoLector == null) ? 0 : codigoTipoLector.hashCode());
        result = prime
                * result
                + ((descripcionTipoLector == null) ? 0 : descripcionTipoLector
                        .hashCode());
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
        if (!(obj instanceof TipoLector)) {
            return false;
        }
        final TipoLector other = (TipoLector) obj;
        if (codigoTipoLector == null) {
            if (other.codigoTipoLector != null) {
                return false;
            }
        } else if (!codigoTipoLector.equals(other.codigoTipoLector)) {
            return false;
        }
        if (descripcionTipoLector == null) {
            if (other.descripcionTipoLector != null) {
                return false;
            }
        } else if (!descripcionTipoLector.equals(other.descripcionTipoLector)) {
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

        .append(TipoLector.COLUMN_NAME_ID, id)

        .append(TipoLector.PROPTY_NAME_CODIGO_TIPO_LECTOR,
                (codigoTipoLector == null) ? 0 : codigoTipoLector)

        .append(TipoLector.PROPTY_NAME_DESCRIPCION_TIPO_LECTOR,
                (descripcionTipoLector == null) ? 0 : descripcionTipoLector)

        .toString();
    }

    @Override
    public AbstractParamPolTipoLector newInstance() {
        return new TipoLector();
    }

}
