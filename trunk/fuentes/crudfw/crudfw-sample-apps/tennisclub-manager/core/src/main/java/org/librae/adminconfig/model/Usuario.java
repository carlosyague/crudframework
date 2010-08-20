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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.librae.common.model.BaseObject;

/**
 * Tabla con los usuarios del Sistema de Gestión Bibliotecario y incluidos los
 * usuarios bibliotecarios y los lectores como usuarios de Liferay
 *
 * @author asantamaria,cyague(simulación-usuario)
 */
@Entity(name = Usuario.ENTITY_NAME)
@Table(name = Usuario.TABLE_NAME)
public class Usuario extends BaseObject {

	/**
     * BaseObject is Serializable, so Usuario needs a Serial Version UID
     */
	private static final long serialVersionUID = 2927639804284359289L;

    public static final String         ENTITY_NAME                         = "org.librae.adminconfig.model.Usuario";
    public static final String         TABLE_NAME                          = "ADM_USUARIO";
    private static final String        ID_GENERATOR_NAME                   = "generator_adm_usuario";
    private static final String        ID_SEQUENCE_NAME                    = "SEQ_ADM_USUARIO";
    public static final String         COLUMN_NAME_ID                      = "X_USUARIO";
    public static final String         COLUMN_NAME_CODIGO_LDAP             = "C_UUID";
    public static final String         COLUMN_NAME_NOMBRE                  = "T_NOMBRE";
    public static final String         COLUMN_NAME_APELLIDO1               = "T_APELLIDO1";
    public static final String         COLUMN_NAME_APELLIDO2               = "T_APELLIDO2";
    public static final String         COLUMN_NAME_USUARIO                 = "T_USUARIO";
    public static final String         COLUMN_NAME_CLAVE                   = "T_CLAVE";
    public static final String         COLUMN_NAME_TIPO_IDENTIFICACION     = "TIP_X_TIPO_IDENTIFICACION";
    public static final String         COLUMN_NAME_NUMERO_IDENTIFICACION   = "N_IDENTIFICACION";
    public static final String         COLUMN_NAME_TRATAMIENTO             = "TRA_X_TRATAMIENTO";
    public static final String         COLUMN_NAME_SEXO                    = "D_SEXO";
    public static final String         COLUMN_NAME_EMAIL                   = "T_EMAIL";
    public static final String         COLUMN_NAME_FAX                     = "T_TELEF_FAX";
    public static final String         COLUMN_NAME_TELEFONO                = "T_TELEF_FIJO";
    public static final String         COLUMN_NAME_MOVIL                   = "T_TELEF_MOVIL";
    public static final String         COLUMN_NAME_FECHA_ALTA              = "F_ALTA";
    public static final String         COLUMN_NAME_FECHA_BAJA              = "F_BAJA";
    public static final String         COLUMN_NAME_FECHA_MODIFICACION      = "F_MODIFICACION";
    public static final String         COLUMN_NAME_ACTIVO                  = "L_ACTIVO";
    public static final String         COLUMN_NAME_PORTAL_LOCALIZACION     = "N_PORTALLOC";
    public static final String         COLUMN_NAME_PORTAL_ORGANIZACION     = "N_PORTALORG";
    public static final String         COLUMN_NAME_PORTAL_USUARIO          = "N_PORTALUSR";
    public static final String         COLUMN_NAME_A                       = "a";
    public static final String         COLUMN_NAME_BIBLIOTECA_POR_DEFECTO  = "L_BIBLIOTECA_POR_DEFECTO";
    public static final String         COLUMN_NAME_BIBLIOTECA_ACTUAL       = "L_BIBLIOTECA_ACTUAL";

    public static final String         PROPERTY_NAME_ID                    = "id";
    public static final String         PROPERTY_NAME_CODIGO_LDAP           = "codigoLDAP";
    public static final String         PROPERTY_NAME_NOMBRE                = "nombre";
    public static final String         PROPERTY_NAME_APELLIDO1             = "apellido1";
    public static final String         PROPERTY_NAME_APELLIDO2             = "apellido2";
    public static final String         PROPERTY_NAME_USUARIO               = "usuario";
    public static final String         PROPERTY_NAME_CLAVE                 = "clave";
    public static final String         PROPERTY_NAME_TIPO_IDENTIFICACION   = "tipoIdentificacion";
    public static final String         PROPERTY_NAME_NUMERO_IDENTIFICACION = "numeroIdentificacion";
    public static final String         PROPERTY_NAME_TRATAMIENTO           = "tratamiento";
    public static final String         PROPERTY_NAME_SEXO                  = "sexo";
    public static final String         PROPERTY_NAME_EMAIL                 = "email";
    public static final String         PROPERTY_NAME_FAX                   = "fax";
    public static final String         PROPERTY_NAME_TELEFONO              = "telefono";
    public static final String         PROPERTY_NAME_MOVIL                 = "movil";
    public static final String         PROPERTY_NAME_FECHA_ALTA            = "fechaAlta";
    public static final String         PROPERTY_NAME_FECHA_BAJA            = "fechaBaja";
    public static final String         PROPERTY_NAME_FECHA_MODIFICACION    = "fechaModificacion";
    public static final String         PROPERTY_NAME_ACTIVO                = "activo";
    public static final String         PROPERTY_NAME_PORTAL_LOCALIZACION   = "portalLocalizacion";
    public static final String         PROPERTY_NAME_PORTAL_ORGANIZACION   = "portalOrganizacion";
    public static final String         PROPERTY_NAME_PORTAL_USUARIO        = "portalUsuario";
    public static final String         PROPERTY_NAME_A                     = "a";
    public static final String         PROPERTY_NAME_PRIVILEGIOS           = "privilegios";
    public static final String         PROPERTY_NAME_BIB_POR_DEFECTO       = "bibliotecaPorDefecto";
    public static final String         PROPERTY_NAME_BIB_ACTUAL            = "bibliotecaActual";

    public static final Long           SEXO_SD                             = 0L;
    public static final Long           SEXO_HOMBRE                         = 1L;
    public static final Long           SEXO_MUJER                          = 2L;

    /**
     * Clave primaria autonumérica sin significado
     */
    private Long                       id;

    /**
     * Código unívoco en LDAP
     */
    private String                     codigoLDAP;

    /**
     * Nombre de la persona
     */
    private String                     nombre;

    /**
     * Apellido 1 de la persona
     */
    private String                     apellido1;

    /**
     * Apellido 2 de la persona
     */
    private String                     apellido2;

    /**
     * Nombre del usuario de la aplicación Librea
     */
    private String                     usuario;

    /**
     * Clave de acceso del usuario de Librea
     */
    private String                     clave;

    /**
     * 0:NIF, 1:NIE, 2:DNI (Existen en esta clase constantes para esto)
     */
    private TipoIdentificacion         tipoIdentificacion;

    /**
     * Número de identificación
     */
    private String                     numeroIdentificacion;

    /**
     * 0: Sr D. , 1:Sra Dña, 2:Ilmo:Sr, etc. (Existen en esta clase constantes
     * para esto)
     */
    private Tratamiento                tratamiento;

    /**
     * 0:SD, 1:Hombre, 2:Mujer (Existen en esta clase constantes para esto)
     */
    private Long                       sexo;

    /**
     * Correo
     */
    private String                     email;

    /**
     * Fax
     */
    private String                     fax;

    /**
     * Teléfono fijo
     */
    private String                     telefono;

    /**
     * Teléfono movil
     */
    private String                     movil;

    /**
     * Fecha de alta del usuario
     */
    private Date                       fechaAlta;

    /**
     * Fecha de baja del usuario
     */
    private Date                       fechaBaja;

    /**
     * Fecha de última modificacion del usuario
     */
    private Date                       fechaModificacion;

    /**
     * true si el usuario está activo, y flase en caso contrario.<br>
     * Se corresponde con el campo user_.active_ de Liferay. Un cambio de valor
     * en este campo debe implicar un cambio de valor en el campo
     * correspondiente de Liferay, vía llamada a servicio web.
     */
    private Boolean                    activo;

    /**
     * Localización de Liferay, es decir, sucursal de biblioteca, a la que está
     * asignado el usuario.
     */
    private Long                       portalLocalizacion;

    /**
     * Organización de Liferay, es decir, biblioteca u grupo de bibliotecas, a
     * la que está asociado el usuario
     */
    private Long                       portalOrganizacion;

    /**
     * Nombre identificativo único del usuario en Liferay. Se corresponde con el
     * campo user_.screenName ó user_.userId en Liferay (el de los dos que sea
     * accesible vía servicios web, probablemente el primero).
     */
    private String                     portalUsuario;

    /**
     * SE corresponde con los campos users_orgs.organizationId y
     * organization_.organizationId de Liferay
     */
    private Long                       a;

    /**
     * Biblioteca referenciada es la de por defecto para el usuario<br>
     * false en caso contrario
     */
    private Biblioteca                 bibliotecaPorDefecto;

    /**
     * Biblioteca en la que esta asignado el usuario. Esta puede no ser la que
     * tiene por defecto.
     */
    private Biblioteca                 bibliotecaActual;

    /**
     * Listado de asignacion de roles.
     */
    private List<UsuarioBibliotecaRol> usuarioBibliotecaRol;

    protected Usuario() {
        super();
    }

    public Usuario(String usuario) {
        super();
        this.usuario = usuario;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Usuario.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @return the codigoLDAP
     */
    @Column(name = Usuario.COLUMN_NAME_CODIGO_LDAP)
    public String getCodigoLDAP() {
        return codigoLDAP;
    }

    /**
     * @param codigoLDAP
     *            the codigoLDAP to set
     */
    public void setCodigoLDAP(String codigoLDAP) {
        this.codigoLDAP = codigoLDAP;
    }

    /**
     * @return the nombre
     */
    @Column(name = Usuario.COLUMN_NAME_NOMBRE, length = 80)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido1
     */
    @Column(name = Usuario.COLUMN_NAME_APELLIDO1, length = 80)
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1
     *            the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    @Column(name = Usuario.COLUMN_NAME_APELLIDO2, length = 80)
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2
     *            the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the usuario
     */
    @Column(name = Usuario.COLUMN_NAME_USUARIO, unique = true, length = 80)
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     *            the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the clave
     */
    @Column(name = Usuario.COLUMN_NAME_CLAVE)
    public String getClave() {
        return clave;
    }

    /**
     * @param clave
     *            the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the tipoIdentificacion
     */
    // @Column(name = Usuario.COLUMN_NAME_TIPO_IDENTIFICACION)
    @ManyToOne(targetEntity = TipoIdentificacion.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = Usuario.COLUMN_NAME_TIPO_IDENTIFICACION)
    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    /**
     * @param tipoIdentificacion
     *            the tipoIdentificacion to set
     */
    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    /**
     * @return the numeroIdentificacion
     */
    @Column(name = Usuario.COLUMN_NAME_NUMERO_IDENTIFICACION, length = 40)
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * @param numeroIdentificacion
     *            the numeroIdentificacion to set
     */
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    /**
     * @return the tratamiento
     */
    // @Column(name = Usuario.COLUMN_NAME_TRATAMIENTO)
    @ManyToOne(targetEntity = Tratamiento.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = Usuario.COLUMN_NAME_TRATAMIENTO)
    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    /**
     * @param tratamiento
     *            the tratamiento to set
     */
    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * @return the sexo
     */
    @Column(name = Usuario.COLUMN_NAME_SEXO)
    public Long getSexo() {
        return sexo;
    }

    /**
     * @param sexo
     *            the sexo to set
     */
    public void setSexo(Long sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the email
     */
    @Column(name = Usuario.COLUMN_NAME_EMAIL, length = 80)
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fax
     */
    @Column(name = Usuario.COLUMN_NAME_FAX, length = 80)
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the telefono
     */
    @Column(name = Usuario.COLUMN_NAME_TELEFONO, length = 80)
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono
     *            the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the movil
     */
    @Column(name = Usuario.COLUMN_NAME_MOVIL, length = 80)
    public String getMovil() {
        return movil;
    }

    /**
     * @param movil
     *            the movil to set
     */
    public void setMovil(String movil) {
        this.movil = movil;
    }

    /**
     * @return the fechaAlta
     */
    @Column(name = Usuario.COLUMN_NAME_FECHA_ALTA)
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta
     *            the fechaAlta to set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the fechaBaja
     */
    @Column(name = Usuario.COLUMN_NAME_FECHA_BAJA)
    public Date getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja
     *            the fechaBaja to set
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * @return the fechaBaja
     */
    @Column(name = Usuario.COLUMN_NAME_FECHA_MODIFICACION)
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion
     *            the fechaModificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the activo
     */
    @Column(name = Usuario.COLUMN_NAME_ACTIVO)
    public Boolean isActivo() {
        return activo;
    }

    /**
     * @param activo
     *            the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the portalLocalizacion
     */
    @Column(name = Usuario.COLUMN_NAME_PORTAL_LOCALIZACION)
    public Long getPortalLocalizacion() {
        return portalLocalizacion;
    }

    /**
     * @param portalLocalizacion
     *            the portalLocalizacion to set
     */
    public void setPortalLocalizacion(Long portalLocalizacion) {
        this.portalLocalizacion = portalLocalizacion;
    }

    /**
     * @return the portalOrganizacion
     */
    @Column(name = Usuario.COLUMN_NAME_PORTAL_ORGANIZACION)
    public Long getPortalOrganizacion() {
        return portalOrganizacion;
    }

    /**
     * @param portalOrganizacion
     *            the portalOrganizacion to set
     */
    public void setPortalOrganizacion(Long portalOrganizacion) {
        this.portalOrganizacion = portalOrganizacion;
    }

    /**
     * @return the portalUsuario
     */
    @Column(name = Usuario.COLUMN_NAME_PORTAL_USUARIO)
    public String getPortalUsuario() {
        return portalUsuario;
    }

    /**
     * @param portalUsuario
     *            the portalUsuario to set
     */
    public void setPortalUsuario(String portalUsuario) {
        this.portalUsuario = portalUsuario;
    }

    /**
     * @return the a
     */
    @Column(name = Usuario.COLUMN_NAME_A)
    public Long getA() {
        return a;
    }

    /**
     * @param a
     *            the a to set
     */
    public void setA(Long a) {
        this.a = a;
    }

    /**
     * @return
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = Usuario.COLUMN_NAME_BIBLIOTECA_POR_DEFECTO)
    @ForeignKey(name = "FK_L_BIBLIOTECA_POR_DEFECTO")
    public Biblioteca getBibliotecaPorDefecto() {
        return bibliotecaPorDefecto;
    }

    /**
     * @param bibliotecaPorDefecto
     *            the bibliotecaPorDefecto to set
     */
    public void setBibliotecaPorDefecto(Biblioteca bibliotecaPorDefecto) {
        this.bibliotecaPorDefecto = bibliotecaPorDefecto;
    }

    /**
     * Devuelve la biblioteca actual.
     *
     * @return
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = Usuario.COLUMN_NAME_BIBLIOTECA_ACTUAL)
    @ForeignKey(name = "FK_L_BIBLIOTECA_ACTUAL")
    public Biblioteca getBibliotecaActual() {
        return bibliotecaActual;
    }

    /**
     * @param bibliotecaActual
     */
    public void setBibliotecaActual(Biblioteca bibliotecaActual) {
        this.bibliotecaActual = bibliotecaActual;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Usuario)) {
            return false;
        }

        final Usuario other = (Usuario) obj;
        if (!getUsuario().equals(other.getUsuario())) {
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
                + ((getCodigoLDAP() == null) ? 0 : getCodigoLDAP().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(COLUMN_NAME_ID, getId())
                .append(COLUMN_NAME_CODIGO_LDAP, getCodigoLDAP()).append(
                        COLUMN_NAME_NOMBRE, getNombre()).append(
                        COLUMN_NAME_APELLIDO1, getApellido1()).append(
                        COLUMN_NAME_APELLIDO2, getApellido2()).toString();
    }

    /**
     * @return the usuarioBibliotecaRol
     */
    @OneToMany(targetEntity = UsuarioBibliotecaRol.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = UsuarioBibliotecaRol.COLUMN_NAME_USUARIO)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<UsuarioBibliotecaRol> getUsuarioBibliotecaRol() {
        return usuarioBibliotecaRol;
    }

    /**
     * @param usuarioBibliotecaRol
     *            the usuarioBibliotecaRol to set
     */
    public void setUsuarioBibliotecaRol(
            List<UsuarioBibliotecaRol> usuarioBibliotecaRol) {
        this.usuarioBibliotecaRol = usuarioBibliotecaRol;
    }
}