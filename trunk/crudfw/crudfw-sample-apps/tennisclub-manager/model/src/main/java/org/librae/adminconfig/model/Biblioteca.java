package org.librae.adminconfig.model;

import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Cascade;
import org.librae.common.model.parampoliticas.AbstractParamPolBiblioteca;

/**
 * Grupos de bibliotecas, bibliotecas y sucursales.<br>
 * Corresponde a las tablas BIBLIO, BIBGRU y SUCURS de Ab*NET.
 *
 * @author asantamaria
 */
@Entity(name = Biblioteca.ENTITY_NAME)
@Table(name = Biblioteca.TABLE_NAME)
public class Biblioteca extends AbstractParamPolBiblioteca {

    /**
     * BaseObject is Serializable, so Biblioteca needs a Serial Version UID
     */
    private static final long           serialVersionUID                       = 6245974682779963953L;                     // TODO

    public static final String          ENTITY_NAME                            = "org.librae.adminconfig.model.Biblioteca";
    public static final String          TABLE_NAME                             = "ADM_BIBLIOTECAS";
    private static final String         ID_GENERATOR_NAME                      = "generator_adm_bibliotecas";
    private static final String         ID_SEQUENCE_NAME                       = "SEQ_ADM_BIBLIOTECAS";
    public static final String          COLUMN_NAME_ID                         = "X_BIBLIOTECA";
    public static final String          COLUMN_NAME_CODIGO                     = "C_BIBLIOTECA";
    public static final String          COLUMN_NAME_FECHA_HORA_ULTIMA_CONEXION = "FH_CONEXION_ULT";

    public static final String          PROPTY_NAME_ID                         = "id";
    public static final String          PROPTY_NAME_CODIGO                     = "codigo";
    public static final String          PROPTY_NAME_FECHA_HORA_ULTIMA_CONEXION = "fechaHoraUltimaConexion";
    public static final String          PROPTY_NAME_DESIDERATAS_DESDE_OPAC     = "desideratasDesdeOpac";
    public static final String          PROPTY_NAME_IVA                        = "iva";
    public static final String          PROPTY_NAME_LATENCIA_NOVEDADES         = "latenciaNovedades";
    public static final String          PROPTY_NAME_DESCRIPCION                = "descripcion";
    public static final String          PROPTY_NAME_DESCRIPCION_ALTERNATIVA    = "descripcionAlternativa";
    public static final String          PROPTY_NAME_PERSONA_CONTACTO           = "personaContacto";
    public static final String          PROPTY_NAME_EMAIL_EXTERNO              = "emailExterno";
    public static final String          PROPTY_NAME_EMAIL_INTERNO              = "emailInterno";
    public static final String          PROPTY_NAME_FAX                        = "fax";
    public static final String          PROPTY_NAME_TEXTO                      = "texto";
    public static final String          PROPTY_NAME_TELEFONO                   = "telefono";
    public static final String          PROPTY_NAME_URL_WEB                    = "urlWeb";
    public static final String          PROPTY_NAME_LOGOTIPO                   = "logotipo";
    public static final String          PROPTY_NAME_LOGOTIPO_NOMBRE_FICHERO    = "logotipoNombreFichero";
    public static final String          PROPTY_NAME_LOGOTIPO_CONTENT_TYPE      = "logotipoContentType";
    public static final String          PROPTY_NAME_DIRECCION                  = "direccion";
    public static final String          PROPTY_NAME_PADRE                      = "padre";
    public static final String          PROPTY_NAME_TIPO_BIBLIOTECA            = "tipoBiblioteca";
    public static final String          PROPTY_NAME_CALENDARIO                 = "calendario";
    public static final String          PROPTY_NAME_HORARIO                    = "horario";

    public static final String          COLUMN_NAME_DESIDERATAS_DESDE_OPAC     = "N_DESIDERATAS_DESDE_OPAC";
    public static final String          COLUMN_NAME_IVA                        = "N_IVA";
    public static final String          COLUMN_NAME_LATENCIA_NOVEDADES         = "N_LATENCIA_NOVEDADES";
    public static final String          COLUMN_NAME_DESCRIPCION                = "T_BIBLIOTECA";
    public static final String          COLUMN_NAME_DESCRIPCION_ALTERNATIVA    = "T_BIBLIOTECA_ALT";
    public static final String          COLUMN_NAME_PERSONA_CONTACTO           = "T_CONTACTO";
    public static final String          COLUMN_NAME_EMAIL_EXTERNO              = "T_EMAIL_EXT";
    public static final String          COLUMN_NAME_EMAIL_INTERNO              = "T_EMAIL_INT";
    public static final String          COLUMN_NAME_FAX                        = "T_FAX";
    public static final String          COLUMN_NAME_TEXTO                      = "T_TEXTO";
    public static final String          COLUMN_NAME_TELEFONO                   = "T_TLF";
    public static final String          COLUMN_NAME_URL_WEB                    = "T_URLW";
    public static final String          COLUMN_NAME_LOGOTIPO                   = "T_LOGOTIPO";
    public static final String          COLUMN_NAME_LOGOTIPO_NOMBRE_FICHERO    = "T_LOGOTIPO_NOMBRE_FICHERO";
    public static final String          COLUMN_NAME_LOGOTIPO_CONTENT_TYPE      = "T_LOGOTIPO_CONTENT_TYPE";
    public static final String          COLUMN_NAME_DIRECCION                  = "DIR_X_DIRECCION";
    public static final String          COLUMN_NAME_PADRE                      = "BIB_X_BIBLIOTECA";
    public static final String          COLUMN_NAME_TIPO_BIBLIOTECA            = "TIP_X_TIPO_BIBLIOTECA";
    public static final String          COLUMN_NAME_CALENDARIO                 = "CAL_X_CALENDARIO";
    public static final String          COLUMN_NAME_HORARIO                    = "HOR_X_HORARIO";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                        id;

    /**
     * Codigo asignado por el usuario
     */
    private String                      codigo;

    /**
     * Fecha y hora de la última conexión
     */
    private Date                        fechaHoraUltimaConexion;

    /**
     * Determina la posibilidad o no de que los lectores puedan realizar
     * desideratas desde el OPAC, así como el número máximo de las mismas que
     * pueden realizar. Para el valor CERO no se permiten desideratas, y
     * cualquier otro valor positivo determina el máximo número de desideratas
     * permitidas por lector.
     */
    private Long                        desideratasDesdeOpac;

    /**
     * Parámetro de IVA de la biblioteca
     */
    private Long                        iva;

    /**
     * Número de días que un artículo permanece como novedad
     */
    private Long                        latenciaNovedades;

    /**
     * Descripción (nombre) asignada por el usuario
     */
    private String                      descripcion;

    /**
     * Descripción alternativa asignada por el usuario
     */
    private String                      descripcionAlternativa;

    /**
     * Persona de contacto
     */
    private String                      personaContacto;

    /**
     * Email externo: el que se suministra a los lectores que no son de la
     * propia biblioteca, por ejemplo el que se publica en el OPAC
     */
    private String                      emailExterno;

    /**
     * Email interno: el que se suministra a los lectores de la propia
     * biblioteca
     */
    private String                      emailInterno;

    /**
     * Número de fax de contacto
     */
    private Long                        fax;

    /**
     * Cualquier texto que se quiera adjuntar a la entidad
     */
    private String                      texto;

    /**
     * Lista de teléfonos de contacto
     */
    private String                      telefono;

    /**
     * URL de la página web en el OPAC
     */
    private String                      urlWeb;

    /**
     * >> DIRECCIONES :: Dirección de la biblioteca
     */
    private Direccion                   direccion;

    /**
     * Referencia a un id dentro de la misma tabla, que es el padre de la fila.
     * Es el campo que implementa la jerarquía grupo de bibliotecas ->
     * bibliotecas -> sucursales
     */
    private Biblioteca                  padre;

    /**
     * No presente en los requisitos de almacenamiento. Definido por la
     * necesidad en Administración del Listado de Bibliotecas (admin-config)
     */
    private TipoBiblioteca              tipoBiblioteca;

    /**
     * Código identificativo (id) del calendario laboral de la biblioteca
     */
    private Calendario                  calendario;

    /**
     * Código identificativo (id) del horario laboral de la biblioteca
     */
    private Horario                     horario;

    /**
     *Fichero que identifica el logotipo de la biblioteca
     */
    private byte[]                      logotipo;

    /**
     * Nombre del fichero que identifica el logotipo de la biblioteca
     */
    private String                      logotipoNombreFichero;

    /**
     * ContentType del fichero que identifica el logotipo de la biblioteca
     */
    private String                      logotipoContentType;

    /**
     * Listado de valores
     */
    private List<AsignacionValorCodigo> valores;

    protected Biblioteca() {
        super();
    }

    public Biblioteca(final String codigo, final String descripcion) {
        super();
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    /**
     * constructor que crea una instancia de Biblioteca que se utilizará en
     * sesión para mostrar la información obtenida de NCIP
     */
    public Biblioteca(Long idNcip) {
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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Biblioteca.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Biblioteca.ID_GENERATOR_NAME, sequenceName = Biblioteca.ID_SEQUENCE_NAME)
    @Column(name = Biblioteca.COLUMN_NAME_ID)
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
     * @return the codigo
     */
    @Column(name = Biblioteca.COLUMN_NAME_CODIGO, nullable = false, length = 40)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the fechaHoraUltimaConexion
     */
    @Column(name = Biblioteca.COLUMN_NAME_FECHA_HORA_ULTIMA_CONEXION)
    public Date getFechaHoraUltimaConexion() {
        return fechaHoraUltimaConexion;
    }

    /**
     * @param fechaHoraUltimaConexion
     *            the fechaHoraUltimaConexion to set
     */
    public void setFechaHoraUltimaConexion(final Date fechaHoraUltimaConexion) {
        this.fechaHoraUltimaConexion = fechaHoraUltimaConexion;
    }

    /**
     * @return the desideratasDesdeOpac
     */
    @Column(name = Biblioteca.COLUMN_NAME_DESIDERATAS_DESDE_OPAC)
    public Long getDesideratasDesdeOpac() {
        return desideratasDesdeOpac;
    }

    /**
     * @param desideratasDesdeOpac
     *            the desideratasDesdeOpac to set
     */
    public void setDesideratasDesdeOpac(final Long desideratasDesdeOpac) {
        this.desideratasDesdeOpac = desideratasDesdeOpac;
    }

    /**
     * @return the iva
     */
    @Column(name = Biblioteca.COLUMN_NAME_IVA)
    public Long getIva() {
        return iva;
    }

    /**
     * @param iva
     *            the iva to set
     */
    public void setIva(final Long iva) {
        this.iva = iva;
    }

    /**
     * @return the latenciaNovedades
     */
    @Column(name = Biblioteca.COLUMN_NAME_LATENCIA_NOVEDADES)
    public Long getLatenciaNovedades() {
        return latenciaNovedades;
    }

    /**
     * @param latenciaNovedades
     *            the latenciaNovedades to set
     */
    public void setLatenciaNovedades(final Long latenciaNovedades) {
        this.latenciaNovedades = latenciaNovedades;
    }

    /**
     * @return the descripcion
     */
    @Column(name = Biblioteca.COLUMN_NAME_DESCRIPCION, length = 80)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the descripcionAlternativa
     */
    @Column(name = Biblioteca.COLUMN_NAME_DESCRIPCION_ALTERNATIVA)
    public String getDescripcionAlternativa() {
        return descripcionAlternativa;
    }

    /**
     * @param descripcionAlternativa
     *            the descripcionAlternativa to set
     */
    public void setDescripcionAlternativa(final String descripcionAlternativa) {
        this.descripcionAlternativa = descripcionAlternativa;
    }

    /**
     * @return the personaContacto
     */
    @Column(name = Biblioteca.COLUMN_NAME_PERSONA_CONTACTO, length = 40)
    public String getPersonaContacto() {
        return personaContacto;
    }

    /**
     * @param personaContacto
     *            the personaContacto to set
     */
    public void setPersonaContacto(final String personaContacto) {
        this.personaContacto = personaContacto;
    }

    /**
     * @return the emailExterno
     */
    @Column(name = Biblioteca.COLUMN_NAME_EMAIL_EXTERNO, length = 80)
    public String getEmailExterno() {
        return emailExterno;
    }

    /**
     * @param emailExterno
     *            the emailExterno to set
     */
    public void setEmailExterno(final String emailExterno) {
        this.emailExterno = emailExterno;
    }

    /**
     * @return the emailInterno
     */
    @Column(name = Biblioteca.COLUMN_NAME_EMAIL_INTERNO, length = 80)
    public String getEmailInterno() {
        return emailInterno;
    }

    /**
     * @param emailInterno
     *            the emailInterno to set
     */
    public void setEmailInterno(final String emailInterno) {
        this.emailInterno = emailInterno;
    }

    /**
     * @return the fax
     */
    @Column(name = Biblioteca.COLUMN_NAME_FAX)
    public Long getFax() {
        return fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(final Long fax) {
        this.fax = fax;
    }

    /**
     * @return the texto
     */
    @Column(name = Biblioteca.COLUMN_NAME_TEXTO)
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
     * @return the telefono
     */
    @Column(name = Biblioteca.COLUMN_NAME_TELEFONO, length = 80)
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono
     *            the telefono to set
     */
    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the urlWeb
     */
    @Column(name = Biblioteca.COLUMN_NAME_URL_WEB)
    public String getUrlWeb() {
        return urlWeb;
    }

    /**
     * @param urlWeb
     *            the urlWeb to set
     */
    public void setUrlWeb(final String urlWeb) {
        this.urlWeb = urlWeb;
    }

    /**
     * @return the direccion
     */
    @ManyToOne(targetEntity = Direccion.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = Biblioteca.COLUMN_NAME_DIRECCION, nullable = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @param direccion
     *            the direccion to set
     */
    public void setDireccion(final Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the padre
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = Biblioteca.COLUMN_NAME_PADRE)
    @ForeignKey(name = "FK_SIGB_X_BIBLIOTECA")
    public Biblioteca getPadre() {
        return padre;
    }

    /**
     * @param padre
     *            the padre to set
     */
    public void setPadre(final Biblioteca padre) {
        this.padre = padre;
    }

    /**
     * @return the tipoBiblioteca
     */
    @ManyToOne(targetEntity = TipoBiblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = Biblioteca.COLUMN_NAME_TIPO_BIBLIOTECA)
    public TipoBiblioteca getTipoBiblioteca() {
        return tipoBiblioteca;
    }

    /**
     * @param padre
     *            the padre to set
     */
    public void setTipoBiblioteca(final TipoBiblioteca tipoBiblioteca) {
        this.tipoBiblioteca = tipoBiblioteca;
    }

    /**
     * @return the calendario
     */
    @ManyToOne(targetEntity = Calendario.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = Biblioteca.COLUMN_NAME_CALENDARIO)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public Calendario getCalendario() {
        return calendario;
    }

    /**
     * @param calendario
     *            the calendario to set
     */
    public void setCalendario(final Calendario calendario) {
        this.calendario = calendario;
    }

    /**
     * @return the horario
     */
    @ManyToOne(targetEntity = Horario.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = Biblioteca.COLUMN_NAME_HORARIO, nullable = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario
     *            the horario to set
     */
    public void setHorario(final Horario horario) {
        this.horario = horario;
    }

    /**
     * @return the logotipo
     */
    @Column(name = Biblioteca.COLUMN_NAME_LOGOTIPO)
    @Lob
    public byte[] getLogotipo() {
        return logotipo;
    }

    /**
     * @param logotipo
     *            the logotipo to set
     */
    public void setLogotipo(final byte[] logotipo) {
        this.logotipo = logotipo;
    }

    /**
     * @return the nombreFichero
     */
    @Column(name = Biblioteca.COLUMN_NAME_LOGOTIPO_NOMBRE_FICHERO)
    public String getLogotipoNombreFichero() {
        return logotipoNombreFichero;
    }

    /**
     * @param nombreFichero
     *            the nombreFichero to set
     */
    public void setLogotipoNombreFichero(final String nombreFichero) {
        logotipoNombreFichero = nombreFichero;
    }

    /**
     * @return the contentTypeFichero
     */
    @Column(name = Biblioteca.COLUMN_NAME_LOGOTIPO_CONTENT_TYPE, length = 80)
    public String getLogotipoContentType() {
        return logotipoContentType;
    }

    /**
     * @param contentType
     *            the contentType to set
     */
    public void setLogotipoContentType(final String contentType) {
        logotipoContentType = contentType;
    }

    /**
     * @return the valores
     */
    @OneToMany(targetEntity = AsignacionValorCodigo.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = AsignacionValorCodigo.COLUMN_NAME_BIBLIOTECA)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<AsignacionValorCodigo> getValores() {
        return valores;
    }

    /**
     * @param valores
     *            the valores to set
     */
    public void setValores(final List<AsignacionValorCodigo> valores) {
        this.valores = valores;
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
        if (!(obj instanceof Biblioteca)) {
            return false;
        }

        final Biblioteca other = (Biblioteca) obj;

        if (getCodigo() != null && !getCodigo().equals(other.getCodigo())) {
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
        result += ((codigo == null) ? 0 : getCodigo().hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(Biblioteca.PROPTY_NAME_ID, getId())

        .append(Biblioteca.PROPTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(Biblioteca.PROPTY_NAME_DESCRIPCION,
                (getDescripcion() == null) ? "" : getDescripcion().toString())

        .append(Biblioteca.PROPTY_NAME_DIRECCION,
                (getDireccion() == null) ? "" : getDireccion().toString())

        .toString();

    }

    @Override
    public AbstractParamPolBiblioteca newInstance() {
        return new Biblioteca();
    }
}