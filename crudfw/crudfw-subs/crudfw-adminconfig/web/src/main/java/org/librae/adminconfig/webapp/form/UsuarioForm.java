package org.librae.adminconfig.webapp.form;

import java.util.Date;
import java.util.GregorianCalendar;

import org.librae.adminconfig.model.Usuario;
import org.librae.common.exception.MensajesError;
import org.librae.common.util.ConvertUtil;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.form.IBaseForm;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;

/**
 * Formulario para la edicion de un usuario.
 * 
 * @author jcisneros
 */
public class UsuarioForm implements IBaseForm<Usuario> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1234123412341234L;

	private TabGroup          tabGroup    = new TabGroup(
                                                  MensajesError.PROPERTI_ADMINCONFIG);

    private Tab               tabUsuario  = new Tab(tabGroup,
                                                  "usuarioForm.pestanya.usuario");

    private Tab               tabAsignar  = new Tab(tabGroup,
                                                  "usuarioForm.pestanya.asignar");

    /**
     * subpestañas
     */
    private final SubTabGroup subTabGroup = new SubTabGroup(
                                                  MensajesError.PROPERTI_ADMINCONFIG);

    private final SubTab      subTabRoles = new SubTab(subTabGroup,
                                                  "usuarioForm.pestanya.roles");

    /**
     * Id del usuario de la aplicación Librea.
     */
    private Long              idUsuario;

    /**
     * Nombre del usuario de la aplicación Librea.
     */
    private String            usuario;

    /**
     * Clave antigua de acceso del usuario de Librea.
     */
    private String            clave;

    /**
     * Clave nueva de acceso del usuario de Librea.
     */
    private String            claveNueva;

    /**
     * Clave nueva repetida de acceso del usuario de Librea.
     */
    private String            claveRepetida;

    /**
     * Nombre del usuario.
     */
    private String            nombre;

    /**
     * Primer apellido del usuario.
     */
    private String            apellido1;

    /**
     * Segundo apellido del usuario.
     */
    private String            apellido2;

    /**
     * Fax del usuario.
     */
    private String            fax;

    /**
     * Telefono del usuario.
     */
    private String            telefono;

    /**
     * Movil del usuario.
     */
    private String            movil;

    /**
     * Email del usuario.
     */
    private String            email;

    /**
     * Numero de identificacion.
     */
    private String            numeroIdentificacion;

    /**
     * Tratamiento del usuario.
     */
    private Long              tratamiento;

    /**
     * Tratamiento del usuario.
     */
    private Long              tipoIdentificacion;

    /**
     * Sexo del usuario.
     */
    private Long              sexo;

    /** modo de Edición o Lectura de una política de reserva */
    private Boolean           readMode    = false;

    /** modo de Edición o Creación de una política de reserva */
    private Boolean           creacion    = true;

    private Boolean           activo      = Boolean.TRUE;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(final Usuario usuario) {
        setUsuario(usuario.getUsuario());
        setNombre(usuario.getNombre());
        setApellido1(usuario.getApellido1());
        setApellido2(usuario.getApellido2());
        setMovil(usuario.getMovil());
        setTelefono(usuario.getTelefono());
        setFax(usuario.getFax());
        setEmail(usuario.getEmail());
        setNumeroIdentificacion(usuario.getNumeroIdentificacion());
        setSexo(usuario.getSexo());
        if (usuario.getTratamiento() != null) {
            setTratamiento(usuario.getTratamiento().getId());
        }
        if (usuario.getTipoIdentificacion() != null) {
        	setTipoIdentificacion(usuario.getTipoIdentificacion().getId());
        }
        setActivo(usuario.isActivo());
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public Usuario toEntity() {
        final Usuario usuario = new Usuario(getUsuario());
        usuario.setFechaAlta(new Date(GregorianCalendar.getInstance()
                .getTimeInMillis()));

        toEntity(usuario);
        return usuario;
    }

    public Usuario toEntity(final Usuario usuario) {
        usuario.setUsuario(getUsuario());
        if (!StringUtil.esVacia(getClave())) {
            usuario.setClave(ConvertUtil.encodeMD5(getClave()));
        }
        usuario.setNombre(getNombre());
        usuario.setApellido1(getApellido1());
        usuario.setApellido2(getApellido2());
        usuario.setMovil(getMovil());
        usuario.setTelefono(getTelefono());
        usuario.setFax(getFax());
        usuario.setEmail(getEmail());
        usuario.setNumeroIdentificacion(getNumeroIdentificacion());
        usuario.setSexo(getSexo());
        usuario.setActivo(getActivo());
        return usuario;
    }

    // ================ Getters && Setter ===================

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(final String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(final String clave) {
        this.clave = clave;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(final String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getClaveRepetida() {
        return claveRepetida;
    }

    public void setClaveRepetida(final String claveRepetida) {
        this.claveRepetida = claveRepetida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(final String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(final String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(final String fax) {
        this.fax = fax;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(final String movil) {
        this.movil = movil;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(final String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Long getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(final Long tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Long getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(final Long tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Long getSexo() {
        return sexo;
    }

    public void setSexo(final Long sexo) {
        this.sexo = sexo;
    }

    public Boolean getReadMode() {
        return readMode;
    }

    public void setReadMode(final Boolean readMode) {
        this.readMode = readMode;
    }

    public Boolean getCreacion() {
        return creacion;
    }

    public void setCreacion(final Boolean creacion) {
        this.creacion = creacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the activo
     */
    public Boolean getActivo() {
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
     * @return the tabGroup
     */
    public TabGroup getTabGroup() {
        return tabGroup;
    }

    /**
     * @param tabGroup
     *            the tabGroup to set
     */
    public void setTabGroup(TabGroup tabGroup) {
        this.tabGroup = tabGroup;
    }

    /**
     * @return the tabUsuario
     */
    public Tab getTabUsuario() {
        return tabUsuario;
    }

    /**
     * @param tabUsuario
     *            the tabUsuario to set
     */
    public void setTabUsuario(Tab tabUsuario) {
        this.tabUsuario = tabUsuario;
    }

    /**
     * @return the tabAsignar
     */
    public Tab getTabAsignar() {
        return tabAsignar;
    }

    /**
     * @param tabAsignar
     *            the tabAsignar to set
     */
    public void setTabAsignar(Tab tabAsignar) {
        this.tabAsignar = tabAsignar;
    }

    /**
     * @return the subTabGroup
     */
    public SubTabGroup getSubTabGroup() {
        return subTabGroup;
    }

    /**
     * @return the subTabRoles
     */
    public SubTab getSubTabRoles() {
        return subTabRoles;
    }

}
