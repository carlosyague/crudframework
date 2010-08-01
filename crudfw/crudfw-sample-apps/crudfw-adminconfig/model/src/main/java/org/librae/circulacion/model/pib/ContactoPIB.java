package org.librae.circulacion.model.pib;

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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Personas de contactos de una biblioteca con la que se interacciona para
 * operaciones de PIB<br>
 * Un contacto en una biblioteca puede ser o no ser un bibliotecario con una
 * fila en la tabla USUARIOS (por ejemplo, puede ser un becario, un estudiante
 * en prácticas, ...).<br>
 * 
 * @author cyague
 */
@Entity(name = ContactoPIB.ENTITY_NAME)
@Table(name = ContactoPIB.TABLE_NAME)
public class ContactoPIB extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID                 = -8304452089358238096L;

    public static final String  ENTITY_NAME                      = "org.librae.circulacion.model.ContactoPIB";
    public static final String  TABLE_NAME                       = "CIR_PIB_CONTACTOS";

    public static final String  ID_GENERATOR_NAME                = "generator_cir_pib_contactos";
    private static final String ID_SEQUENCE_NAME                 = "SEQ_CIR_PIB_CONTACTO";
    public static final String  COLUMN_NAME_ID                   = "X_PIB_CONTACTO";
    public static final String  PROPERTY_NAME_ID                 = "id";

    public static final String  COLUMN_NAME_BIBLIOTECA_EXTERNA   = "BIB_EXT_X_PIB_BBLTCA_EXT";
    public static final String  PROPERTY_NAME_BIBLIOTECA_EXTERNA = "bibliotecaExterna";

    public static final String  COLUMN_NAME_NOMBRE               = "T_NOMBRE";
    public static final String  PROPERTY_NAME_NOMBRE             = "nombre";

    public static final String  COLUMN_NAME_PRIMER_APELLIDO      = "T_PRIMER_APELLIDO";
    public static final String  PROPERTY_NAME_PRIMER_APELLIDO    = "primerApellido";

    public static final String  COLUMN_NAME_SEGUNDO_APELLIDO     = "T_SEGUNDO_APELLIDO";
    public static final String  PROPERTY_NAME_SEGUNDO_APELLIDO   = "segundoApellido";

    public static final String  COLUMN_NAME_CORREO               = "T_CORREO";
    public static final String  PROPERTY_NAME_CORREO             = "correoElectronico";

    public static final String  COLUMN_NAME_FAX                  = "T_FAX";
    public static final String  PROPERTY_NAME_FAX                = "fax";

    public static final String  COLUMN_NAME_IM_AIM               = "T_IM_AIM";
    public static final String  PROPERTY_NAME_IM_AIM             = "cuentaAim";

    public static final String  COLUMN_NAME_IM_GT                = "T_IM_GT";
    public static final String  PROPERTY_NAME_IM_GT              = "cuentaGoogleTalk";

    public static final String  COLUMN_NAME_IM_ICQ               = "T_IM_ICQ";
    public static final String  PROPERTY_NAME_IM_ICQ             = "cuentaIcq";

    public static final String  COLUMN_NAME_IM_JABBER            = "T_IM_JABBER";
    public static final String  PROPERTY_NAME_IM_JABBER          = "cuentaJabber";

    public static final String  COLUMN_NAME_IM_MSN               = "T_IM_MSN";
    public static final String  PROPERTY_NAME_IM_MSN             = "cuentaMsn";

    public static final String  COLUMN_NAME_IM_SKYPE             = "T_IM_SKYPE";
    public static final String  PROPERTY_NAME_IM_SKYPE           = "cuentaSkype";

    public static final String  COLUMN_NAME_IM_YAHOO             = "T_IM_YAHOO";
    public static final String  PROPERTY_NAME_IM_YAHOO           = "cuentaYahoo";

    public static final String  COLUMN_NAME_NOTAS                = "T_NOTAS";
    public static final String  PROPERTY_NAME_NOTAS              = "NOTAS";

    public static final String  COLUMN_NAME_TFNO_FIJO1           = "T_TELEFONO_FIJO1";
    public static final String  PROPERTY_NAME_TFNO_FIJO1         = "tfnoFijo1";

    public static final String  COLUMN_NAME_TFNO_FIJO2           = "T_TELEFONO_FIJO2";
    public static final String  PROPERTY_NAME_TFNO_FIJO2         = "tfnoFijo2";

    public static final String  COLUMN_NAME_TFNO_FIJO3           = "T_TELEFONO_FIJO3";
    public static final String  PROPERTY_NAME_TFNO_FIJO3         = "tfnoFijo3";

    public static final String  COLUMN_NAME_TFNO_MOVIL           = "T_TELEFONO_MOVIL";
    public static final String  PROPERTY_NAME_TFNO_MOVIL         = "tfnoMovil";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila
     */
    private Long                id;

    /**
     * Restricción: Este campo es excluyente con BIB_X_BIBLIOTECA: sólo uno de
     * los dos puede contener un valor <> null Además, si este campo es <> NULL,
     * los datos del contacto están SOLO en esta fila, mientras que si
     * BIB_X_BIBLIOTECA es <> NULL, los datos del contacto que figuren en esta
     * fila tienen preferencia sobre los asociados al bibliotecario en la tabla
     * correspondiente >> CIR_PIB_BIBLIOTECAS_EXTERNAS Indica de que biblioteca
     * externa a la aplicación es el contacto.
     */
    private BibliotecaExterna   bibliotecaExterna                = null;

    /**
     * ATENCIÓN !!! CLAVE AJENA A UNA TABLA QUE TODAVÍA NO EXISTE, EN EL
     * SUBSISTEMA MEM (Mensajería y Comunicaciones), que recogerá los datos de
     * contacto (tlf, email, direcciones de IM, ...) de un usuario de la tabla
     * USUARIOS (es decir, de un bibliotecario)
     */
    // private Long CON_X_CONTACTOS_BIBLIOTECARIO;
    /**
     * Nombre de la persona de contacto
     */
    private String              nombre                           = null;

    /**
     * Primero apellido de la persona de contacto.
     */
    private String              primerApellido                   = null;
    /**
     * Segundo apellido de la persona de contacto.
     */
    private String              segundoApellido                  = null;
    /**
     * Correo electrónico de la persona de contacto
     */
    private String              correoElectronico                = null;
    /**
     * Fax de la persona de contacto
     */
    private String              fax                              = null;
    /**
     * Cuenta de mensajería instantanea del contacto en el servicio AIM (America
     * Instant Messagging)
     */
    private String              chatAim                          = null;
    /**
     * Cuenta de mensajerÍa instantanea del contacto en el servicio Google Talk
     */
    private String              cuentaGoogleTalk                 = null;
    /**
     * Cuenta de mensajería instantánea del contacto en el servicio ICQ
     */
    private String              cuentaIcq                        = null;
    /**
     * Cuenta de mensajería instantanea del contacto en un servidor compatible
     * Jabber/XMPP
     */
    private String              cuentaJabber                     = null;
    /**
     * Cuenta de mensajería instantánea del contacto en el servicio MSN
     * (Microsoft Nentwork)
     */
    private String              cuentaMsn                        = null;
    /**
     * Cuenta de mensajería instantanea del contacto en el servicio Skype
     */
    private String              cuentaSkype                      = null;
    /**
     * Cuenta de mensajería instantanea del contacto en el servicio Yahoo
     * Instant Messagging
     */
    private String              cuentaYahoo                      = null;

    /**
     * Texto libre que el bibliotecario puede adjuntar al contacto, por ejmeplo
     * para recordar su rol en los PIB si hay varios contactos en una
     * biblioteca. Por ejemplo:
     * "Se encarga de los PIB de materiales no retornables"
     */
    private String              notas                            = null;
    /**
     * Primer teléfono de la persona de contacto
     */
    private String              tfnoFijo1                        = null;
    /**
     * Segundo teléfono de la persona de contacto
     */
    private String              tfnoFijo2                        = null;
    /**
     * Tercer teléfono de la persona de contacto
     */
    private String              tfnoFijo3                        = null;
    /**
     * Teléfono móvil de la persona de contacto
     */
    private String              tfnoMovil                        = null;

    /**
     * constructors<br>
     * ================
     */

    protected ContactoPIB() {
        super();
    }

    public ContactoPIB(BibliotecaExterna bibliotecaExterna) {
        super();
        this.bibliotecaExterna = bibliotecaExterna;
    }

    public ContactoPIB(String nombre) {
        super();
        this.nombre = nombre;
    }

    /**
     * getter & setters<br>
     * ================
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = BibliotecaExterna.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = COLUMN_NAME_BIBLIOTECA_EXTERNA)
    public BibliotecaExterna getBibliotecaExterna() {
        return bibliotecaExterna;
    }

    public void setBibliotecaExterna(BibliotecaExterna bibliotecaExterna) {
        this.bibliotecaExterna = bibliotecaExterna;
    }

    @Column(name = COLUMN_NAME_NOMBRE, length = 80)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = COLUMN_NAME_CORREO, length = 80)
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Column(name = COLUMN_NAME_FAX, length = 80)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = COLUMN_NAME_IM_AIM)
    public String getChatAim() {
        return chatAim;
    }

    public void setChatAim(String chatAim) {
        this.chatAim = chatAim;
    }

    @Column(name = COLUMN_NAME_IM_GT)
    public String getCuentaGoogleTalk() {
        return cuentaGoogleTalk;
    }

    public void setCuentaGoogleTalk(String cuentaGoogleTalk) {
        this.cuentaGoogleTalk = cuentaGoogleTalk;
    }

    @Column(name = COLUMN_NAME_IM_ICQ)
    public String getCuentaIcq() {
        return cuentaIcq;
    }

    public void setCuentaIcq(String cuentaIcq) {
        this.cuentaIcq = cuentaIcq;
    }

    @Column(name = COLUMN_NAME_IM_JABBER)
    public String getCuentaJabber() {
        return cuentaJabber;
    }

    public void setCuentaJabber(String cuentaJabber) {
        this.cuentaJabber = cuentaJabber;
    }

    @Column(name = COLUMN_NAME_IM_MSN)
    public String getCuentaMsn() {
        return cuentaMsn;
    }

    public void setCuentaMsn(String cuentaMsn) {
        this.cuentaMsn = cuentaMsn;
    }

    @Column(name = COLUMN_NAME_IM_SKYPE)
    public String getCuentaSkype() {
        return cuentaSkype;
    }

    public void setCuentaSkype(String cuentaSkype) {
        this.cuentaSkype = cuentaSkype;
    }

    @Column(name = COLUMN_NAME_IM_YAHOO)
    public String getCuentaYahoo() {
        return cuentaYahoo;
    }

    public void setCuentaYahoo(String cuentaYahoo) {
        this.cuentaYahoo = cuentaYahoo;
    }

    @Column(name = COLUMN_NAME_NOTAS)
    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Column(name = COLUMN_NAME_TFNO_FIJO1, length = 80)
    public String getTfnoFijo1() {
        return tfnoFijo1;
    }

    public void setTfnoFijo1(String tfnoFijo1) {
        this.tfnoFijo1 = tfnoFijo1;
    }

    @Column(name = COLUMN_NAME_TFNO_FIJO2, length = 80)
    public String getTfnoFijo2() {
        return tfnoFijo2;
    }

    public void setTfnoFijo2(String tfnoFijo2) {
        this.tfnoFijo2 = tfnoFijo2;
    }

    @Column(name = COLUMN_NAME_TFNO_FIJO3, length = 80)
    public String getTfnoFijo3() {
        return tfnoFijo3;
    }

    public void setTfnoFijo3(String tfnoFijo3) {
        this.tfnoFijo3 = tfnoFijo3;
    }

    @Column(name = COLUMN_NAME_TFNO_MOVIL, length = 80)
    public String getTfnoMovil() {
        return tfnoMovil;
    }

    public void setTfnoMovil(String tfnoMovil) {
        this.tfnoMovil = tfnoMovil;
    }

    /**
     * @return the primerApellido
     */
    @Column(name = COLUMN_NAME_SEGUNDO_APELLIDO, length = 80)
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
    @Column(name = COLUMN_NAME_PRIMER_APELLIDO, length = 80)
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

    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof ContactoPIB)) {
            return false;
        }

        final ContactoPIB other = (ContactoPIB) obj;

        if (getBibliotecaExterna() != null) {
            if (getBibliotecaExterna().equals(other.getBibliotecaExterna())) {
                return false;
            }
        }

        if (!getNombre().equals(other.getNombre())) {
            return false;
        }
        if (!getPrimerApellido().equals(other.getPrimerApellido())) {
            return false;
        }
        if (!getSegundoApellido().equals(other.getSegundoApellido())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());

        if (getBibliotecaExterna() != null) {
            result = prime
                    * result
                    + ((getBibliotecaExterna() == null) ? 0
                            : getBibliotecaExterna().hashCode());
        }

        result = prime * result
                + ((getNombre() == null) ? 0 : getNombre().hashCode());

        result = prime
                * result
                + ((getPrimerApellido() == null) ? 0 : getPrimerApellido()
                        .hashCode());

        result = prime
                * result
                + ((getSegundoApellido() == null) ? 0 : getSegundoApellido()
                        .hashCode());

        return result;
    }

    @Override
    public String toString() {
        final ToStringBuilder tsb = new ToStringBuilder(this);

        tsb.append(PROPERTY_NAME_ID, getId());
        tsb.append(PROPERTY_NAME_NOMBRE, getNombre());
        tsb.append(PROPERTY_NAME_PRIMER_APELLIDO, getPrimerApellido());
        tsb.append(PROPERTY_NAME_PRIMER_APELLIDO, getSegundoApellido());

        if (getBibliotecaExterna() != null) {
            tsb.append(ContactoPIB.PROPERTY_NAME_BIBLIOTECA_EXTERNA,
                    getBibliotecaExterna());
        }

        return tsb.toString();
    }
}
