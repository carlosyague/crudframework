package org.librae.adminconfig.webapp.form;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.model.SelectItem;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.form.IBaseForm;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;
import org.librae.common.webapp.util.LibraeUploadedFile;
import org.springframework.util.FileCopyUtils;

/**
 * Clase para controlar la página del formulario de bibliotecas.
 * 
 * @author aropero
 */
public class BibliotecaForm implements Serializable, IBaseForm<Biblioteca> {
    /**
     * Serial Version UID
     */
    private static final long    serialVersionUID                   = 8522912923345207875L;

    /**
     * pestañas
     */
    private static final Integer ID_TAB_GROUP                       = 1;
    private static final Integer ID_SUBTAB_GROUP                    = 2;

    private TabGroup             tabGroup                           = new TabGroup(
                                                                            MensajesError.PROPERTI_ADMINCONFIG,
                                                                            ID_TAB_GROUP);
    private Tab                  tabBiblioteca                      = new Tab(
                                                                            tabGroup,
                                                                            "biblioteca.pestanya.biblioteca");
    private Tab                  tabPolPrest                        = new Tab(
                                                                            tabGroup,
                                                                            "biblioteca.pestanya.polPrest");
    private Tab                  tabCodigos                         = new Tab(
                                                                            tabGroup,
                                                                            "biblioteca.pestanya.codigos");
    private Tab                  tabParametros                      = new Tab(
                                                                            tabGroup,
                                                                            "biblioteca.pestanya.parametros");

    /**
     * subpestañas
     */
    private SubTabGroup          subTabGroup                        = new SubTabGroup(
                                                                            MensajesError.PROPERTI_ADMINCONFIG,
                                                                            ID_SUBTAB_GROUP);
    private SubTab               subTabCodigos                      = new SubTab(
                                                                            subTabGroup,
                                                                            "biblioteca.pestanya.codigos");

    private Long                 idTipo                             = null;
    private String               codigo;
    private String               nombre;
    private String               domicilio;
    private String               personaContacto;
    private String               correoElectronico;
    private String               telefono;
    private Long                 fax;
    private String               urlWeb;
    private Long                 idBiblioteca;

    // logotipo
    private LibraeUploadedFile   logotipo;
    private String               fileName;

    private Date                 fechaActualizacion;

    private Date                 fechaActualizacionH;

    // Política de préstamos
    private Boolean              cumplimentarReservasEnDevoluciones = true;
    private Boolean              emitirReciboEnDevoluciones;
    private Boolean              emitirReciboEnReservas;
    private Boolean              emitirReciboEnRenovaciones;
    private Boolean              prestamoRenovacionSiSuspendido;
    private Boolean              reservaSiSuspendido;
    private Long                 numeroMaxPrestamosDom              = new Long(
                                                                            50);
    private Long                 numeroMaxReservasPorCola           = new Long(
                                                                            50);
    private Boolean              admiteDevolucionMismaBiblioteca    = true;
    private Boolean              admitePrestamoMismaBiblioteca      = true;
    private Boolean              admiteRenovacionMismaBiblioteca;
    private Boolean              permiteDevolucionMismaBiblioteca   = true;
    private Boolean              permitePrestamoMismaBiblioteca     = true;
    private Boolean              permiteRenovacionMismaBiblioteca;
    // Códigos

    // Parámetros
    private Long                 iva                                = new Long(
                                                                            18);
    private Long                 latenciaNovedades                  = new Long(
                                                                            0);
    private Long                 desideratasDesdeOpac               = new Long(
                                                                            1);
    private Boolean              permiteDesideratas                 = true;

    private List<SelectItem>     listaTipos                         = new ArrayList<SelectItem>();
    private List<String>         valores                            = new ArrayList();
    private String               idValoresSeleccionados;

    /** modo de Edición o Lectura de una biblioteca */
    private Boolean              readMode                           = false;

    private boolean              creacion                           = true;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(final Biblioteca biblioteca) {
        idBiblioteca = biblioteca.getId();
        if (biblioteca.getTipoBiblioteca() != null) {
            idTipo = biblioteca.getTipoBiblioteca().getId();
        }
        codigo = biblioteca.getCodigo();
        nombre = biblioteca.getDescripcion();
        personaContacto = biblioteca.getPersonaContacto();
        correoElectronico = biblioteca.getEmailExterno();
        telefono = biblioteca.getTelefono();
        fax = biblioteca.getFax();
        urlWeb = biblioteca.getUrlWeb();

        if (biblioteca.getLogotipo() != null) {
            this.setLogotipo(biblioteca.getLogotipoNombreFichero(), biblioteca
                    .getLogotipo(), biblioteca.getLogotipoContentType());
        }

        if ((biblioteca.getCalendario() != null)
                && (biblioteca.getCalendario().getFechaActualizacion() != null)) {
            fechaActualizacion = biblioteca.getCalendario()
                    .getFechaActualizacion();
        }

        if ((biblioteca.getHorario() != null)
                && (biblioteca.getHorario().getFechaActualizacion() != null)) {
            fechaActualizacionH = biblioteca.getHorario()
                    .getFechaActualizacion();
        }

        if ((biblioteca.getDireccion() != null)
                && (biblioteca.getDireccion().getNombreVia() != null)) {
            domicilio = biblioteca.getDireccion().getNombreVia();
        }

        // Política de Préstamos
        cumplimentarReservasEnDevoluciones = biblioteca
                .getCumplimentarReservasEnDevoluciones();
        emitirReciboEnDevoluciones = biblioteca.getEmitirReciboEnDevoluciones();
        emitirReciboEnReservas = biblioteca.getEmitirReciboEnReservas();
        emitirReciboEnRenovaciones = biblioteca.getEmitirReciboEnRenovaciones();
        prestamoRenovacionSiSuspendido = biblioteca
                .getPrestamoRenovacionSiSuspendido();
        reservaSiSuspendido = biblioteca.getReservaSiSuspendido();
        numeroMaxPrestamosDom = biblioteca.getNumeroMaxPrestamosDom();
        numeroMaxReservasPorCola = biblioteca.getNumeroMaxReservasPorCola();
        admiteDevolucionMismaBiblioteca = biblioteca
                .getAdmiteDevolucionMismaBiblioteca();
        admitePrestamoMismaBiblioteca = biblioteca
                .getAdmitePrestamoMismaBiblioteca();
        admiteRenovacionMismaBiblioteca = biblioteca
                .getAdmiteRenovacionMismaBiblioteca();
        permiteDevolucionMismaBiblioteca = biblioteca
                .getPermiteDevolucionMismaBiblioteca();
        permitePrestamoMismaBiblioteca = biblioteca
                .getPermitePrestamoMismaBiblioteca();
        permiteRenovacionMismaBiblioteca = biblioteca
                .getPermiteRenovacionMismaBiblioteca();

        // Parámetros
        if (biblioteca.getIva()!=null) {
            biblioteca.getIva();
        }
        if(biblioteca.getLatenciaNovedades()!=null){
           biblioteca.getLatenciaNovedades();
        }
        final Long desideratasDesdeOpacAux = biblioteca
                .getDesideratasDesdeOpac();
        if (desideratasDesdeOpacAux == null || desideratasDesdeOpacAux == 0) {
            desideratasDesdeOpac = 0L;
            permiteDesideratas = false;
        } else {
            desideratasDesdeOpac = desideratasDesdeOpacAux;
        }

        if (!permiteDesideratas) {
            desideratasDesdeOpac = 0L;
        }
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public Biblioteca toEntity() {
        final Biblioteca biblioteca = new Biblioteca(codigo, nombre);
        toEntity(biblioteca);
        return biblioteca;
    }

    public Biblioteca toEntity(final Biblioteca biblioteca) {
        biblioteca.setCodigo(codigo);
        biblioteca.setDescripcion(nombre);
        biblioteca.setPersonaContacto(personaContacto);
        biblioteca.setEmailExterno(correoElectronico);
        biblioteca.setTelefono(telefono);
        biblioteca.setFax(fax);
        biblioteca.setUrlWeb(urlWeb);

        // logotipo
        if (logotipo != null && logotipo.getUploadedFile() != null) {
            try {
                final InputStream inputS = logotipo.getUploadedFile()
                        .getInputStream();
                if (inputS != null) {
                    biblioteca.setLogotipo(FileCopyUtils
                            .copyToByteArray(inputS));
                }
            } catch (final IOException e) {
                // se ignora
            }
            fileName = logotipo.getFilename();
            fileName = fileName.substring(fileName.lastIndexOf('/') + 1)
                    .substring(fileName.lastIndexOf('\\') + 1);
            biblioteca.setLogotipoNombreFichero(fileName);
            biblioteca.setLogotipoContentType(logotipo.getContentType());
        }

        // Política de Préstamos
        biblioteca
                .setCumplimentarReservasEnDevoluciones(cumplimentarReservasEnDevoluciones);
        biblioteca.setEmitirReciboEnDevoluciones(emitirReciboEnDevoluciones);
        biblioteca.setEmitirReciboEnReservas(emitirReciboEnReservas);
        biblioteca.setEmitirReciboEnRenovaciones(emitirReciboEnRenovaciones);
        biblioteca
                .setPrestamoRenovacionSiSuspendido(prestamoRenovacionSiSuspendido);
        biblioteca.setReservaSiSuspendido(reservaSiSuspendido);
        biblioteca.setNumeroMaxPrestamosDom(numeroMaxPrestamosDom);
        biblioteca.setNumeroMaxReservasPorCola(numeroMaxReservasPorCola);
        biblioteca
                .setAdmiteDevolucionMismaBiblioteca(admiteDevolucionMismaBiblioteca);
        biblioteca
                .setAdmitePrestamoMismaBiblioteca(admitePrestamoMismaBiblioteca);
        biblioteca
                .setAdmiteRenovacionMismaBiblioteca(admiteRenovacionMismaBiblioteca);
        biblioteca
                .setPermiteDevolucionMismaBiblioteca(permiteDevolucionMismaBiblioteca);
        biblioteca
                .setPermitePrestamoMismaBiblioteca(permitePrestamoMismaBiblioteca);
        biblioteca
                .setPermiteRenovacionMismaBiblioteca(permiteRenovacionMismaBiblioteca);

        // Parámetros
        biblioteca.setIva(iva);
        biblioteca.setLatenciaNovedades(latenciaNovedades);

        if (!permiteDesideratas) {
            desideratasDesdeOpac = 0L;
        }
        biblioteca.setDesideratasDesdeOpac(desideratasDesdeOpac);

        return biblioteca;
    }

    /**
     * Método encargadod e rellenar el List de valores con los valores de
     * idValoresSeleccionados: xx:xx:xxx
     */
    private void rellenarListaValores() {
        if (idValoresSeleccionados != null) {
            valores = new ArrayList();
            final StringTokenizer tokens = new StringTokenizer(
                    idValoresSeleccionados, ":");
            while (tokens.hasMoreTokens()) {
                final String id = tokens.nextToken();
                if (!"".equals(id)) {
                    valores.add(id);
                }
            }
        }
    }

    // ==================== pestañas y subpestañas =======================

    /**
     * @return the tabGroup
     */
    public TabGroup getTabGroup() {
        return tabGroup;
    }

    /**
     * @return the tabBiblioteca
     */
    public Tab getTabBiblioteca() {
        return tabBiblioteca;
    }

    /**
     * @return the tabPolPrest
     */
    public Tab getTabPolPrest() {
        return tabPolPrest;
    }

    /**
     * @return the tabCodigos
     */
    public Tab getTabCodigos() {
        return tabCodigos;
    }

    /**
     * @return the tabParametros
     */
    public Tab getTabParametros() {
        return tabParametros;
    }

    /**
     * @param tabGroup
     *            the tabGroup to set
     */
    public void setTabGroup(final TabGroup tabGroup) {
        this.tabGroup = tabGroup;
    }

    /**
     * @param tabBiblioteca
     *            the tabBiblioteca to set
     */
    public void setTabBiblioteca(final Tab tabBiblioteca) {
        this.tabBiblioteca = tabBiblioteca;
    }

    /**
     * @param tabPolPrest
     *            the tabPolPrest to set
     */
    public void setTabPolPrest(final Tab tabPolPrest) {
        this.tabPolPrest = tabPolPrest;
    }

    /**
     * @param tabCodigos
     *            the tabCodigos to set
     */
    public void setTabCodigos(final Tab tabCodigos) {
        this.tabCodigos = tabCodigos;
    }

    /**
     * @param tabParametros
     *            the tabParametros to set
     */
    public void setTabParametros(final Tab tabParametros) {
        this.tabParametros = tabParametros;
    }

    /**
     * @return the subTabGroup
     */
    public SubTabGroup getSubTabGroup() {
        return subTabGroup;
    }

    /**
     * @param subTabGroup
     *            the subTabGroup to set
     */
    public void setSubTabGroup(final SubTabGroup subTabGroup) {
        this.subTabGroup = subTabGroup;
    }

    /**
     * @return the subTabCodigos
     */
    public SubTab getSubTabCodigos() {
        return subTabCodigos;
    }

    /**
     * @param subTabCodigos
     *            the subTabCodigos to set
     */
    public void setSubTabCodigos(final SubTab subTabCodigos) {
        this.subTabCodigos = subTabCodigos;
    }

    // ======================= getter & setter ============================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public Long getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(final Long idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(final String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    public Long getFax() {
        return fax;
    }

    public void setFax(final Long fax) {
        this.fax = fax;
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(final Long idTipo) {
        this.idTipo = idTipo;
    }

    public List<SelectItem> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(final List<SelectItem> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(final String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(final String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public Boolean getCumplimentarReservasEnDevoluciones() {
        return cumplimentarReservasEnDevoluciones;
    }

    public void setCumplimentarReservasEnDevoluciones(
            final Boolean cumplimentarReservasEnDevoluciones) {
        this.cumplimentarReservasEnDevoluciones = cumplimentarReservasEnDevoluciones;
    }

    public Boolean getEmitirReciboEnDevoluciones() {
        return emitirReciboEnDevoluciones;
    }

    public void setEmitirReciboEnDevoluciones(
            final Boolean emitirReciboEnDevoluciones) {
        this.emitirReciboEnDevoluciones = emitirReciboEnDevoluciones;
    }

    public Boolean getEmitirReciboEnReservas() {
        return emitirReciboEnReservas;
    }

    public void setEmitirReciboEnReservas(final Boolean emitirReciboEnReservas) {
        this.emitirReciboEnReservas = emitirReciboEnReservas;
    }

    public Boolean getPrestamoRenovacionSiSuspendido() {
        return prestamoRenovacionSiSuspendido;
    }

    public void setPrestamoRenovacionSiSuspendido(
            final Boolean prestamoRenovacionSiSuspendido) {
        this.prestamoRenovacionSiSuspendido = prestamoRenovacionSiSuspendido;
    }

    public Boolean getReservaSiSuspendido() {
        return reservaSiSuspendido;
    }

    public void setReservaSiSuspendido(final Boolean reservaSiSuspendido) {
        this.reservaSiSuspendido = reservaSiSuspendido;
    }

    public Long getNumeroMaxPrestamosDom() {
        return numeroMaxPrestamosDom;
    }

    public void setNumeroMaxPrestamosDom(final Long numeroMaxPrestamosDom) {
        this.numeroMaxPrestamosDom = numeroMaxPrestamosDom;
    }

    public Long getNumeroMaxReservasPorCola() {
        return numeroMaxReservasPorCola;
    }

    public void setNumeroMaxReservasPorCola(final Long numeroMaxReservasPorCola) {
        this.numeroMaxReservasPorCola = numeroMaxReservasPorCola;
    }

    public Boolean getAdmiteDevolucionMismaBiblioteca() {
        return admiteDevolucionMismaBiblioteca;
    }

    public void setAdmiteDevolucionMismaBiblioteca(
            final Boolean admiteDevolucionMismaBiblioteca) {
        this.admiteDevolucionMismaBiblioteca = admiteDevolucionMismaBiblioteca;
    }

    public Boolean getAdmitePrestamoMismaBiblioteca() {
        return admitePrestamoMismaBiblioteca;
    }

    public void setAdmitePrestamoMismaBiblioteca(
            final Boolean admitePrestamoMismaBiblioteca) {
        this.admitePrestamoMismaBiblioteca = admitePrestamoMismaBiblioteca;
    }

    public Boolean getPermiteDevolucionMismaBiblioteca() {
        return permiteDevolucionMismaBiblioteca;
    }

    public void setPermiteDevolucionMismaBiblioteca(
            final Boolean permiteDevolucionMismaBiblioteca) {
        this.permiteDevolucionMismaBiblioteca = permiteDevolucionMismaBiblioteca;
    }

    public Boolean getPermiteRenovacionMismaBiblioteca() {
        return permiteRenovacionMismaBiblioteca;
    }

    public void setPermiteRenovacionMismaBiblioteca(
            final Boolean permiteRenovacionMismaBiblioteca) {
        this.permiteRenovacionMismaBiblioteca = permiteRenovacionMismaBiblioteca;
    }

    public Boolean getEmitirReciboEnRenovaciones() {
        return emitirReciboEnRenovaciones;
    }

    public void setEmitirReciboEnRenovaciones(
            final Boolean emitirReciboEnRenovaciones) {
        this.emitirReciboEnRenovaciones = emitirReciboEnRenovaciones;
    }

    public Boolean getAdmiteRenovacionMismaBiblioteca() {
        return admiteRenovacionMismaBiblioteca;
    }

    public void setAdmiteRenovacionMismaBiblioteca(
            final Boolean admiteRenovacionMismaBiblioteca) {
        this.admiteRenovacionMismaBiblioteca = admiteRenovacionMismaBiblioteca;
    }

    public Boolean getPermitePrestamoMismaBiblioteca() {
        return permitePrestamoMismaBiblioteca;
    }

    public void setPermitePrestamoMismaBiblioteca(
            final Boolean permitePrestamoMismaBiblioteca) {
        this.permitePrestamoMismaBiblioteca = permitePrestamoMismaBiblioteca;
    }

    public Long getIva() {
        return iva;
    }

    public void setIva(final Long iva) {
        this.iva = iva;
    }

    public Long getLatenciaNovedades() {
        return latenciaNovedades;
    }

    public void setLatenciaNovedades(final Long latenciaNovedades) {
        this.latenciaNovedades = latenciaNovedades;
    }

    public Long getDesideratasDesdeOpac() {
        return desideratasDesdeOpac;
    }

    public void setDesideratasDesdeOpac(final Long desideratasDesdeOpac) {
        this.desideratasDesdeOpac = desideratasDesdeOpac;
    }

    public Boolean getPermiteDesideratas() {
        if (desideratasDesdeOpac == null) {
            return false;
        } else {
            return ((desideratasDesdeOpac == null) || (desideratasDesdeOpac == 0)) ? false
                    : true;
        }
    }

    public void setPermiteDesideratas(final Boolean permiteDesideratas) {
        this.permiteDesideratas = permiteDesideratas;
    }

    public LibraeUploadedFile getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(final LibraeUploadedFile logotipo) {
        this.logotipo = logotipo;
    }

    public void setLogotipo(final String filename, final byte[] byteArray,
            final String contentType) {
        logotipo = new LibraeUploadedFile(filename, byteArray, contentType);
    }

    public UploadedFile getUploadedFileLogotipo() {

        UploadedFile result = null;

        if (logotipo != null) {
            result = logotipo.getUploadedFile();
        }

        return result;
    }

    public void setUploadedFileLogotipo(final UploadedFile logotipo) {
        this.logotipo = new LibraeUploadedFile(logotipo);
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(final Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Boolean getReadMode() {
        return readMode;
    }

    public void setReadMode(final Boolean readMode) {
        this.readMode = readMode;
    }

    public List<String> getValores() {
        return valores;
    }

    public void setValores(final List<String> valores) {
        this.valores = valores;
    }

    public String getIdValoresSeleccionados() {
        return idValoresSeleccionados;
    }

    public void setIdValoresSeleccionados(final String idValoresSeleccionados) {
        this.idValoresSeleccionados = idValoresSeleccionados;
        rellenarListaValores();
    }

    /**
     * Habilita/deshabilita el botón eliminar de la edición de bibliotecas según
     * estemos modificando/creando respectivamente.
     * 
     * @return true si estamos creando, false en caso contrario.
     */
    public boolean getCreacion() {
        return creacion;
    }

    public void setCreacion(final boolean creacion) {
        this.creacion = creacion;
    }

    public String getFileName() {
        if ((logotipo != null) && (logotipo.getFilename() != null)) {
            fileName = logotipo.getFilename();
        }
        return fileName;

    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
        if ((logotipo != null)
                && (!org.librae.common.util.StringUtil.esVacia(fileName))) {
            logotipo.setFilename(fileName);
        }
    }

    public Date getFechaActualizacionH() {
        return fechaActualizacionH;
    }

    public void setFechaActualizacionH(final Date fechaActualizacionH) {
        this.fechaActualizacionH = fechaActualizacionH;
    }
}