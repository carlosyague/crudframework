package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.webapp.form.ISearchForm;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;

/**
 * Criteria para las busquedas de usuario.
 * 
 * @author jcisneros
 */
public class UsuarioCriteriaForm implements ISearchForm, Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID     = 8522996923345207875L;

    /**
     * pestañas
     */
    private TabGroup          tabGroup             = new TabGroup(
                                                           MensajesError.PROPERTI_ADMINCONFIG);
    private Tab               tabBuscarUsuario     = new Tab(tabGroup,
                                                           "usuarioSearch.tab");

    /**
     * subpestañas
     */
    private SubTabGroup       subTabGroup          = new SubTabGroup(
                                                           MensajesError.PROPERTI_ADMINCONFIG);
    private SubTab            subTabResultados     = new SubTab(subTabGroup,
                                                           "usuarioSearch.resultados");

    /**
     * Nombre de la persona
     */
    private String            nombre;

    /**
     * Apellido 1 de la persona.
     */
    private String            apellido1;

    /**
     * Apellido 2 de la persona.
     */
    private String            apellido2;

    /**
     * Fecha de alta del usuario.
     */
    private Date              fechaAltaDesdeCriteria;

    /**
     * Fecha de alta del usuario.
     */
    private Date              fechaAltaHastaCriteria;

    /**
     * Fecha de baja del usuario.
     */
    private Date              fechaBajaDesdeCriteria;

    /**
     * Fecha de baja del usuario.
     */
    private Date              fechaBajaHastaCriteria;

    /**
     * Tipo de identificacion del usuario.
     */
    private Long              tipoIdentificacion   = null;

    /**
     * Numero de identificacion del usuario.
     */
    private String            numeroIdentificacion = null;

    /**
     * Correo
     */
    private String            email;

    /**
     * Fax
     */
    private String            fax;

    /**
     * Telefono fijo
     */
    private String            telefono;

    /**
     * Telefono movil
     */
    private String            movil;

    /**
     * Identificador del rol.
     */
    private List<String>      idRoles              = null;

    /**
     * Identificador de la biblioteca.
     */
    private Long              idBiblioteca         = null;

    /**
     * Busqueda: Codigo de la Biblioteca.
     */
    private String            codigoBiblioteca;

    /**
     * Busqueda: Codigo de la Biblioteca.
     */
    private String            descripcionBiblioteca;

    /**
     * Seleccionamos buscar la Biblioteca por el Codigo o el arbol.
     */
    private List<SelectItem>  buscarPorBiblioteca;

    private String            busqueda             = "arbol";

    private Long              idBibliotecaActual   = null;

    /**
     * Nick del usuario.
     */
    private String            usuario              = null;

    /**
     * Estado del usuario.
     */
    private Long              estadoUsuario        = null;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final HashMap<String, Object> criterios = new HashMap<String, Object>();

        criterios.put(Usuario.PROPERTY_NAME_NOMBRE, getNombre());
        criterios.put(Usuario.PROPERTY_NAME_USUARIO, getUsuario());
        criterios.put(Usuario.PROPERTY_NAME_APELLIDO1, getApellido1());
        criterios.put(Usuario.PROPERTY_NAME_APELLIDO2, getApellido2());
        criterios.put(Usuario.PROPERTY_NAME_EMAIL, getEmail());
        criterios.put(Usuario.PROPERTY_NAME_MOVIL, getMovil());
        criterios.put(Usuario.PROPERTY_NAME_FAX, getFax());
        criterios.put(Usuario.PROPERTY_NAME_TELEFONO, getTelefono());
        criterios.put(Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION,
                getTipoIdentificacion());
        criterios.put(Usuario.PROPERTY_NAME_NUMERO_IDENTIFICACION,
                getNumeroIdentificacion());
        criterios.put(Usuario.PROPERTY_NAME_ACTIVO, getEstadoUsuario());

        criterios.put("fechaAltaDesde", getFechaAltaDesdeCriteria());
        criterios.put("fechaAltaHasta", getFechaAltaHastaCriteria());

        criterios.put("fechaBajaDesde", getFechaBajaDesdeCriteria());
        criterios.put("fechaBajaHasta", getFechaBajaHastaCriteria());

        criterios.put("idRoles", getIdRoles());
        criterios.put("busqueda", getBusqueda());
        criterios.put("idBibliotecaActual", getIdBibliotecaActual());

        if (busqueda.equalsIgnoreCase("codigo")) {
            criterios.put("codigoBiblioteca", codigoBiblioteca);
        } else if (busqueda.equalsIgnoreCase("descripcion")) {
            criterios.put(Biblioteca.PROPTY_NAME_DESCRIPCION,
                    descripcionBiblioteca);
        } else {
            criterios.put("idBiblioteca", getIdBiblioteca());
        }

        return criterios;
    }

    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String,
     *      Object>)
     */
    public void fromMap(final Map<String, Object> criterios) {
        if (criterios != null) {
            if (criterios.get(Usuario.PROPERTY_NAME_NOMBRE) != null) {
                setNombre(criterios.get(Usuario.PROPERTY_NAME_NOMBRE)
                        .toString());
            }
            if (criterios.get(Usuario.PROPERTY_NAME_USUARIO) != null) {
                setNombre(criterios.get(Usuario.PROPERTY_NAME_USUARIO)
                        .toString());
            }
            if (criterios.get(Usuario.PROPERTY_NAME_APELLIDO1) != null) {
                setApellido1(criterios.get(Usuario.PROPERTY_NAME_APELLIDO1)
                        .toString());
            }
            if (criterios.get(Usuario.PROPERTY_NAME_APELLIDO2) != null) {
                setApellido2(criterios.get(Usuario.PROPERTY_NAME_APELLIDO2)
                        .toString());
            }
            if (criterios.get(Usuario.PROPERTY_NAME_EMAIL) != null) {
                setEmail(criterios.get(Usuario.PROPERTY_NAME_EMAIL).toString());
            }
            if (criterios.get(Usuario.PROPERTY_NAME_MOVIL) != null) {
                setMovil(criterios.get(Usuario.PROPERTY_NAME_MOVIL).toString());
            }
            if (criterios.get(Usuario.PROPERTY_NAME_FAX) != null) {
                setFax(criterios.get(Usuario.PROPERTY_NAME_FAX).toString());
            }
            if (criterios.get(Usuario.PROPERTY_NAME_TELEFONO) != null) {
                setTelefono(criterios.get(Usuario.PROPERTY_NAME_TELEFONO)
                        .toString());
            }
            if (criterios.get(Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION) != null) {
                setTipoIdentificacion(new Long(criterios.get(
                        Usuario.PROPERTY_NAME_TIPO_IDENTIFICACION).toString()));
            }
            if (criterios.get(Usuario.PROPERTY_NAME_NUMERO_IDENTIFICACION) != null) {
                setNumeroIdentificacion(criterios.get(
                        Usuario.PROPERTY_NAME_NUMERO_IDENTIFICACION).toString());
            }
            if (criterios.get("fechaAltaDesde") != null) {
                setFechaAltaDesdeCriteria((Date) criterios
                        .get("fechaAltaDesde"));
            }
            if (criterios.get("fechaBajaDesde") != null) {
                setFechaBajaDesdeCriteria((Date) criterios
                        .get("fechaBajaDesde"));
            }
            if (criterios.get("fechaAltaHasta") != null) {
                setFechaAltaHastaCriteria((Date) criterios
                        .get("fechaAltaHasta"));
            }
            if (criterios.get("fechaBajaHasta") != null) {
                setFechaBajaHastaCriteria((Date) criterios
                        .get("fechaBajaHasta"));
            }
            if (criterios.get("idRoles") != null) {
                setIdRoles((List<String>) criterios.get("idRoles"));
            }
            if (criterios.get(Usuario.PROPERTY_NAME_ACTIVO) != null) {
                setEstadoUsuario((Long) criterios
                        .get(Usuario.PROPERTY_NAME_ACTIVO));
            }
            if (criterios.get("codigoBiblioteca") != null) {
                setCodigoBiblioteca(criterios.get("codigoBiblioteca")
                        .toString());
            }
            if (criterios.get("descripcionBiblioteca") != null) {
                setDescripcionBiblioteca(criterios.get("descripcionBiblioteca")
                        .toString());
            }
            if (criterios.get("idBiblioteca") != null) {
                setIdBiblioteca(new Long(criterios.get("idBiblioteca")
                        .toString()));
            }
            if (criterios.get("busqueda") != null) {
                setBusqueda(criterios.get("busqueda").toString());
            }
        }
    }

    /**
     * Listado de radios.
     * 
     * @return
     */
    public List<SelectItem> getBuscarPorBiblioteca() {
        buscarPorBiblioteca = new ArrayList<SelectItem>();

        final SelectItem codigo = new SelectItem();
        codigo.setValue("codigo");
        codigo.setLabel(ComboLocaleManager.get("biblioteca.codigo"));
        buscarPorBiblioteca.add(codigo);

        final SelectItem nombre = new SelectItem();
        nombre.setValue("descripcion");
        nombre.setLabel(ComboLocaleManager.get("biblioteca.descripcion"));
        buscarPorBiblioteca.add(nombre);

        final SelectItem arbol = new SelectItem();
        arbol.setValue("arbol");
        arbol.setLabel(ComboLocaleManager.get("biblioteca.arbol"));
        buscarPorBiblioteca.add(arbol);

        return buscarPorBiblioteca;
    }

    // Getters && Setter

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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
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

    public Long getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(final Long tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public List<String> getIdRoles() {
        return idRoles;
    }

    public Long getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdRol(final List<String> idRoles) {
        this.idRoles = idRoles;
    }

    public void setIdBiblioteca(final Long idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public void setIdRoles(final List<String> idRoles) {
        this.idRoles = idRoles;
    }

    /**
     * @return the tabGroup
     */
    public TabGroup getTabGroup() {
        return tabGroup;
    }

    /**
     * @return the tabBuscarUsuarios
     */
    public Tab getTabBuscarUsuario() {
        return tabBuscarUsuario;
    }

    /**
     * @param tabGroup
     *            the tabGroup to set
     */
    public void setTabGroup(final TabGroup tabGroup) {
        this.tabGroup = tabGroup;
    }

    /**
     * @param tabBuscarUsuarios
     *            the tabBuscarUsuarios to set
     */
    public void setTabBuscarUsuario(final Tab tabBuscarUsuarios) {
        tabBuscarUsuario = tabBuscarUsuarios;
    }

    /**
     * @return the subTabGroup
     */
    public SubTabGroup getSubTabGroup() {
        return subTabGroup;
    }

    /**
     * @return the subTabResultados
     */
    public SubTab getSubTabResultados() {
        return subTabResultados;
    }

    /**
     * @param subTabGroup
     *            the subTabGroup to set
     */
    public void setSubTabGroup(final SubTabGroup subTabGroup) {
        this.subTabGroup = subTabGroup;
    }

    /**
     * @param subTabResultados
     *            the subTabResultados to set
     */
    public void setSubTabResultados(final SubTab subTabResultados) {
        this.subTabResultados = subTabResultados;
    }

    public String getCodigoBiblioteca() {
        return codigoBiblioteca;
    }

    public void setCodigoBiblioteca(final String codigoBiblioteca) {
        this.codigoBiblioteca = codigoBiblioteca;
    }

    public void setBuscarPorBiblioteca(
            final List<SelectItem> buscarPorBiblioteca) {
        this.buscarPorBiblioteca = buscarPorBiblioteca;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(final String busqueda) {
        this.busqueda = busqueda;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(final String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    /**
     * @return the idBibliotecaActual
     */
    public Long getIdBibliotecaActual() {
        return idBibliotecaActual;
    }

    /**
     * @param idBibliotecaActual
     *            the idBibliotecaActual to set
     */
    public void setIdBibliotecaActual(Long idBibliotecaActual) {
        this.idBibliotecaActual = idBibliotecaActual;
    }

    /**
     * @return the usuario
     */
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

    public String getDescripcionBiblioteca() {
        return descripcionBiblioteca;
    }

    public void setDescripcionBiblioteca(String descripcionBiblioteca) {
        this.descripcionBiblioteca = descripcionBiblioteca;
    }

    /**
     * @return the fechaAltaDesdeCriteria
     */
    public Date getFechaAltaDesdeCriteria() {
        return fechaAltaDesdeCriteria;
    }

    /**
     * @param fechaAltaDesdeCriteria
     *            the fechaAltaDesdeCriteria to set
     */
    public void setFechaAltaDesdeCriteria(Date fechaAltaDesdeCriteria) {
        this.fechaAltaDesdeCriteria = fechaAltaDesdeCriteria;
    }

    /**
     * @return the fechaAltaHastaCriteria
     */
    public Date getFechaAltaHastaCriteria() {
        return fechaAltaHastaCriteria;
    }

    /**
     * @param fechaAltaHastaCriteria
     *            the fechaAltaHastaCriteria to set
     */
    public void setFechaAltaHastaCriteria(Date fechaAltaHastaCriteria) {
        this.fechaAltaHastaCriteria = fechaAltaHastaCriteria;
    }

    /**
     * @return the fechaBajaDesdeCriteria
     */
    public Date getFechaBajaDesdeCriteria() {
        return fechaBajaDesdeCriteria;
    }

    /**
     * @param fechaBajaDesdeCriteria
     *            the fechaBajaDesdeCriteria to set
     */
    public void setFechaBajaDesdeCriteria(Date fechaBajaDesdeCriteria) {
        this.fechaBajaDesdeCriteria = fechaBajaDesdeCriteria;
    }

    /**
     * @return the fechaBajaHastaCriteria
     */
    public Date getFechaBajaHastaCriteria() {
        return fechaBajaHastaCriteria;
    }

    /**
     * @param fechaBajaHastaCriteria
     *            the fechaBajaHastaCriteria to set
     */
    public void setFechaBajaHastaCriteria(Date fechaBajaHastaCriteria) {
        this.fechaBajaHastaCriteria = fechaBajaHastaCriteria;
    }

    /**
     * @return the estadoUsuario
     */
    public Long getEstadoUsuario() {
        return estadoUsuario;
    }

    /**
     * @param estadoUsuario
     *            the estadoUsuario to set
     */
    public void setEstadoUsuario(Long estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

}
