package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

import org.librae.adminconfig.webapp.delegate.IComboDelegate;
import org.librae.adminconfig.webapp.form.DireccionForm;
import org.librae.common.Constants;
import org.librae.common.service.impl.ComboLocaleManager;

import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.action.ConvertUtil;

/**
 * Action-JSF para la gestion de combos.
 * 
 * @author jcisneros
 */
public class CombosAction extends BasePage implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID    = 1L;

    /**
     * Interfaz de los combos.
     */
    private IComboDelegate    admComboDelegate    = null;

    /**
     * Lista con los tipos de codigo.
     */
    private List<SelectItem>  tiposCodigo         = new ArrayList<SelectItem>();

    /**
     * Lista con los tipos de identificacion.
     */
    private List<SelectItem>  tiposIdentificacion = new ArrayList<SelectItem>();

    /**
     * Lista con los tratamientos.
     */
    private List<SelectItem>  tratamientos        = new ArrayList<SelectItem>();

    /**
     * Lista con los roles.
     */
    private List<SelectItem>  roles               = new ArrayList<SelectItem>();

    /**
     * Lista de tipos de biblioteca.
     */
    private List<SelectItem>  tiposBiblioteca     = new ArrayList<SelectItem>();

    /**
     * Lista de tipos de biblioteca.
     */
    private List<SelectItem>  politicas           = new ArrayList<SelectItem>();

    /**
     * Lista de tipos de biblioteca.
     */
    private List<SelectItem>  tiposEjemplar       = new ArrayList<SelectItem>();

    /**
     * Lista de tipos de biblioteca.
     */
    private List<SelectItem>  tiposLector         = new ArrayList<SelectItem>();

    /**
     * Lista de tipos de biblioteca.
     */
    private List<SelectItem>  nivelesRol          = new ArrayList<SelectItem>();

    /**
     * Lista de categorias de permisos.
     */
    private List<SelectItem>  categoriasPermiso   = new ArrayList<SelectItem>();

    /**
     * Lista de horas.
     */
    private List<SelectItem>  horas               = new ArrayList<SelectItem>();

    /**
     * Lista de minutos.
     */
    private List<SelectItem>  minutos             = new ArrayList<SelectItem>();

    /**
     * Lista de paises
     */
    private List<SelectItem>  paises              = new ArrayList<SelectItem>();

    /** ID del pais para buscar las comunidades */
    private Long              idPais;

    /**
     * Lista de comunidades Autonomas
     */
    private List<SelectItem>  comAutonomas        = new ArrayList<SelectItem>();

    /** ID de comunidad autonoma para buscar las provincias */
    private Long              idComAutonoma;

    /**
     * Lista de provincias
     */
    private List<SelectItem>  provincias          = new ArrayList<SelectItem>();

    /** ID de provincia para buscar los municipios */
    private Long              idProvincia;

    /**
     * Lista de municipios
     */
    private List<SelectItem>  municipios          = new ArrayList<SelectItem>();

    /** ID de municipio para buscar las localidades */
    private Long              idMunicipio;

    /**
     * Lista de localidades
     */
    private List<SelectItem>  localidades         = new ArrayList<SelectItem>();

    /**
     * Lista de tipos de via
     */
    private List<SelectItem>  tiposVia            = new ArrayList<SelectItem>();

    /**
     * Pone el campo por defecto si es TRUE.
     */
    private Boolean           campoDefecto        = Boolean.FALSE;

    /**
     * Lista de tipos de via
     */
    private List<SelectItem>  estadosUsuario      = new ArrayList<SelectItem>();

    /** Activar/desactivar los combos de direccion */
    private Boolean           comunidadesDisabled = true;

    private Boolean           provinciasDisabled  = true;

    private Boolean           municipiosDisabled  = true;

    private Boolean           localidadesDisabled = true;

    public String getInit() {
        final DireccionForm form = (DireccionForm) getSessionManager()
                .getAttribute(Constants.DIRECCION_SESSION_NAME);
        if (form != null) {
            setIdPais(form.getIdPais());
            if ((form.getIdPais() != null)
                    && (!(form.getIdPais().equals(new Long(0))))) {
                setComunidadesDisabled(false);
            }
            setIdComAutonoma(form.getIdComAutonoma());
            if ((form.getIdComAutonoma() != null)
                    && (!(form.getIdComAutonoma().equals(new Long(0))))) {
                setProvinciasDisabled(false);
            }
            setIdProvincia(form.getIdProvincia());
            if ((form.getIdProvincia() != null)
                    && (!(form.getIdProvincia().equals(new Long(0))))) {
                setMunicipiosDisabled(false);
            }
            setIdMunicipio(form.getIdMunicipio());
            if ((form.getIdMunicipio() != null)
                    && (!(form.getIdMunicipio().equals(new Long(0))))) {
                setLocalidadesDisabled(false);
            }
        }
        return "";
    }

    /**
     * Obtiene los tipos de codigos.
     * 
     * @return
     */
    public List<SelectItem> getTiposCodigo() {
        if (tiposCodigo.isEmpty()) {
            tiposCodigo = ConvertUtil.convertListItem(admComboDelegate
                    .getTiposCodigo(), "id", "descripcion");
            ordenar(tiposCodigo);
        }
        return tiposCodigo;
    }

    /**
     * Obtiene los tipos de financiacion.
     * 
     * @return
     */
    public List<SelectItem> getTiposIdentificacion() {
        if (tiposIdentificacion.isEmpty()) {
            tiposIdentificacion = ConvertUtil.convertListItem(admComboDelegate
                    .getTiposIdentificacion(), "id", "codigo");
            ordenar(tiposIdentificacion);
        }
        return tiposIdentificacion;
    }

    /**
     * Obtiene los roles de la base de datos.
     * 
     * @return
     */
    public List<SelectItem> getRoles() {
        if (roles.isEmpty()) {
            roles = ConvertUtil.convertListItem(admComboDelegate.getRoles(),
                    "id", "codigo");
            ordenar(roles);
        }
        return roles;
    }

    protected List<SelectItem> ordenar(final List<SelectItem> list) {
        final Comparator comparator = new BeanComparator("label",
                new NullComparator(nullsAreHigh));
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Obtiene los tratamientos de la base de datos.
     * 
     * @return
     */
    public List<SelectItem> getTratamientos() {
        if (tratamientos.isEmpty()) {
            tratamientos = ConvertUtil.convertListItem(admComboDelegate
                    .getTratamientos(), "id", "codigo");
            ordenar(tratamientos);
        }
        return tratamientos;
    }

    /**
     * Obtiene los tratamientos de la base de datos.
     * 
     * @return
     */
    public List<SelectItem> getTiposBiblioteca() {
        if (tiposBiblioteca.isEmpty()) {
            tiposBiblioteca = ConvertUtil.convertListItem(admComboDelegate
                    .getTiposBiblioteca(), "id", "nombre");
            ordenar(tiposBiblioteca);
        }
        return tiposBiblioteca;
    }

    /**
     * Obtiene las politicas de la base de datos.
     * 
     * @return
     */
    public List<SelectItem> getPoliticas() {
        if (politicas.isEmpty()) {
            politicas = ConvertUtil.convertListItem(admComboDelegate
                    .getPolicas(), "id", "codigo");
            ordenar(politicas);
        }
        return politicas;
    }

    /**
     * Obtiene los tipos de lector de la base de datos.
     * 
     * @return
     */
    public List<SelectItem> getTiposLector() {
        if (tiposLector.isEmpty()) {
            tiposLector = ConvertUtil.convertListItem(admComboDelegate
                    .getTiposLector(), "id", "codigoTipoLector");
            ordenar(tiposLector);
        }
        return tiposLector;
    }

    /**
     * @return the categoriasPermiso
     */
    public List<SelectItem> getCategoriasPermiso() {
        if (categoriasPermiso.isEmpty()) {
            categoriasPermiso = ConvertUtil.convertListItem(admComboDelegate
                    .getTiposPermiso(), "id", "codigo");
            ordenar(categoriasPermiso);
        }
        return categoriasPermiso;
    }

    /**
     * Obtiene los tipos de ejemplar de la base de datos.
     * 
     * @return
     */
    public List<SelectItem> getTiposEjemplar() {
        if (tiposEjemplar.isEmpty()) {
            tiposEjemplar = ConvertUtil.convertListItem(admComboDelegate
                    .getTiposEjemplar(), "id", "codigo");
            ordenar(tiposEjemplar);
        }
        return tiposEjemplar;
    }

    /**
     * Obtiene los tipos de Via de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> getTiposVia() {
        if (tiposVia.isEmpty()) {
            tiposVia = ConvertUtil.convertListItem(admComboDelegate
                    .getTiposVia(), "id", "nombre");
            ordenar(tiposVia);
        }
        return tiposVia;
    }

    /**
     * Obtiene los minutos posibles (00..59).
     * 
     * @return listado de minutos.
     */
    public List<SelectItem> getMinutos() {
        minutos = new ArrayList<SelectItem>();

        minutos.add(new SelectItem("00"));
        minutos.add(new SelectItem("01"));
        minutos.add(new SelectItem("02"));
        minutos.add(new SelectItem("03"));
        minutos.add(new SelectItem("04"));
        minutos.add(new SelectItem("05"));
        minutos.add(new SelectItem("06"));
        minutos.add(new SelectItem("07"));
        minutos.add(new SelectItem("08"));
        minutos.add(new SelectItem("09"));

        for (int i = 10; i <= 59; i++) {
            final SelectItem hora = new SelectItem(String.valueOf(i));
            minutos.add(hora);
        }

        return minutos;
    }

    /**
     * Obtiene las horas posibles (00..23)
     * 
     * @return listado de horas.
     */
    public List<SelectItem> getHoras() {
        horas = new ArrayList<SelectItem>();

        horas.add(new SelectItem("00"));
        horas.add(new SelectItem("01"));
        horas.add(new SelectItem("02"));
        horas.add(new SelectItem("03"));
        horas.add(new SelectItem("04"));
        horas.add(new SelectItem("05"));
        horas.add(new SelectItem("06"));
        horas.add(new SelectItem("07"));
        horas.add(new SelectItem("08"));
        horas.add(new SelectItem("09"));

        for (int i = 10; i <= 23; i++) {
            final SelectItem hora = new SelectItem(String.valueOf(i));
            horas.add(hora);
        }

        return horas;
    }

    /**
     * Obtiene los países de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> getPaises() {
        if (paises.isEmpty()) {
            paises = ConvertUtil.convertListItem(admComboDelegate.getPaises(),
                    "id", "nombre");
            ordenar(paises);
        }
        return paises;
    }

    /**
     * Obtiene las comunidades autonomas de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> getComAutonomas() {
        if ((idPais != null) && (!idPais.equals(new Long(0)))) {
            comAutonomas = ConvertUtil.convertListItem(admComboDelegate
                    .getComAutonomas(idPais), "id", "nombre");
            ordenar(comAutonomas);
        }
        return comAutonomas;
    }

    /**
     * Obtiene las provincias de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> getProvincias() {
        if ((idComAutonoma != null) && (!idComAutonoma.equals(new Long(0)))) {
            provincias = ConvertUtil.convertListItem(admComboDelegate
                    .getProvincias(idComAutonoma), "id", "nombre");
            ordenar(provincias);
        }
        return provincias;
    }

    /**
     * Obtiene los municipios de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> getMunicipios() {
        if ((idProvincia != null) && (!idProvincia.equals(new Long(0)))) {
            municipios = ConvertUtil.convertListItem(admComboDelegate
                    .getMunicipios(idProvincia), "id", "nombre");
            ordenar(municipios);
        }
        return municipios;
    }

    /**
     * Obtiene las localidades de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> getLocalidades() {
        if ((idMunicipio != null) && (!idMunicipio.equals(new Long(0)))) {
            localidades = ConvertUtil.convertListItem(admComboDelegate
                    .getLocalidades(idMunicipio), "id", "nombre");
            ordenar(localidades);
        }
        return localidades;
    }

    /**
     * Obtiene las comunidades autónomas de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> obtenerComunidades() {
        if ((idPais != null) && (!idPais.equals(new Long(0)))) {
            comAutonomas = ConvertUtil.convertListItem(admComboDelegate
                    .getComAutonomas(idPais), "id", "nombre");
            ordenar(comAutonomas);
        }
        setComunidadesDisabled(false);
        setProvinciasDisabled(true);
        setMunicipiosDisabled(true);
        setLocalidadesDisabled(true);
        return comAutonomas;
    }

    /**
     * Obtiene las provincias de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> obtenerProvincias() {
        if ((idComAutonoma != null) && (!idComAutonoma.equals(new Long(0)))) {
            provincias = ConvertUtil.convertListItem(admComboDelegate
                    .getProvincias(idComAutonoma), "id", "nombre");
            ordenar(provincias);
        }
        setProvinciasDisabled(false);
        setMunicipiosDisabled(true);
        setLocalidadesDisabled(true);
        return provincias;
    }

    /**
     * Obtiene las municipios de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> obtenerMunicipios() {
        if ((idProvincia != null) && (!idProvincia.equals(new Long(0)))) {
            municipios = ConvertUtil.convertListItem(admComboDelegate
                    .getMunicipios(idProvincia), "id", "nombre");
            ordenar(municipios);
        }
        setMunicipiosDisabled(false);
        setLocalidadesDisabled(true);
        return municipios;
    }

    /**
     * Obtiene las localidades de la base de datos.
     * 
     * @return List<SelectItem>
     */
    public List<SelectItem> obtenerLocalidades() {
        if ((idMunicipio != null) && (!idMunicipio.equals(new Long(0)))) {
            localidades = ConvertUtil.convertListItem(admComboDelegate
                    .getLocalidades(idMunicipio), "id", "nombre");
            ordenar(localidades);
        }
        setLocalidadesDisabled(false);
        return localidades;
    }

    /**
     * Lista de estados del usuario.
     * 
     * @return the estadosUsuario
     */
    public List<SelectItem> getEstadosUsuario() {
        if (estadosUsuario.isEmpty()) {
            final SelectItem selecItem2 = new SelectItem();
            selecItem2.setLabel(ComboLocaleManager.get("estadoUsuario.todos"));
            selecItem2.setValue(new Long(2));
            estadosUsuario.add(selecItem2);

            final SelectItem selecItem1 = new SelectItem();
            selecItem1.setLabel(ComboLocaleManager
                    .get("estadoUsuario.activados"));
            selecItem1.setValue(new Long(1));
            estadosUsuario.add(selecItem1);

            final SelectItem selecItem0 = new SelectItem();
            selecItem0.setLabel(ComboLocaleManager
                    .get("estadoUsuario.desactivados"));
            selecItem0.setValue(new Long(0));
            estadosUsuario.add(selecItem0);
        }
        return estadosUsuario;
    }

    /**
     * Lista de estados del usuario.
     * 
     * @return the estadosUsuario
     */
    public List<SelectItem> getNivelesRol() {
        if (nivelesRol.isEmpty()) {
            for (Long contador = new Long(1); contador <= 10; contador = contador + 1) {
                final SelectItem selecItem = new SelectItem();
                selecItem.setLabel(contador.toString());
                selecItem.setValue(contador);
                nivelesRol.add(selecItem);
            }
        }
        return nivelesRol;
    }

    // Getters && Setters

    public void setTratamientos(final List<SelectItem> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public void setTiposCodigo(final List<SelectItem> tiposCodigo) {
        this.tiposCodigo = tiposCodigo;
    }

    public void setTiposIdentificacion(
            final List<SelectItem> tiposIdentificacion) {
        this.tiposIdentificacion = tiposIdentificacion;
    }

    public void setRoles(final List<SelectItem> roles) {
        this.roles = roles;
    }

    public Boolean getCampoDefecto() {
        return campoDefecto;
    }

    public IComboDelegate getAdmComboDelegate() {
        return admComboDelegate;
    }

    public void setAdmComboDelegate(final IComboDelegate admComboDelegate) {
        this.admComboDelegate = admComboDelegate;
    }

    public void setCampoDefecto(final Boolean campoDefecto) {
        this.campoDefecto = campoDefecto;
    }

    public void setTiposBiblioteca(final List<SelectItem> tiposBiblioteca) {
        this.tiposBiblioteca = tiposBiblioteca;
    }

    public void setPoliticas(final List<SelectItem> politicas) {
        this.politicas = politicas;
    }

    public void setTiposEjemplar(final List<SelectItem> tiposEjemplar) {
        this.tiposEjemplar = tiposEjemplar;
    }

    public void setTiposLector(final List<SelectItem> tiposLector) {
        this.tiposLector = tiposLector;
    }

    public void setHoras(final List<SelectItem> horas) {
        this.horas = horas;
    }

    public void setMinutos(final List<SelectItem> minutos) {
        this.minutos = minutos;
    }

    public void setComAutonomas(List<SelectItem> comAutonomas) {
        this.comAutonomas = comAutonomas;
    }

    public void setProvincias(List<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public void setMunicipios(List<SelectItem> municipios) {
        this.municipios = municipios;
    }

    public void setLocalidades(List<SelectItem> localidades) {
        this.localidades = localidades;
    }

    public void setPaises(List<SelectItem> paises) {
        this.paises = paises;
    }

    public Long getIdComAutonoma() {
        return idComAutonoma;
    }

    public void setIdComAutonoma(Long idComAutonoma) {
        this.idComAutonoma = idComAutonoma;
    }

    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public Boolean getComunidadesDisabled() {
        return comunidadesDisabled;
    }

    public void setComunidadesDisabled(Boolean comunidadesDisabled) {
        this.comunidadesDisabled = comunidadesDisabled;
    }

    public Boolean getProvinciasDisabled() {
        return provinciasDisabled;
    }

    public void setProvinciasDisabled(Boolean provinciasDisabled) {
        this.provinciasDisabled = provinciasDisabled;
    }

    public Boolean getMunicipiosDisabled() {
        return municipiosDisabled;
    }

    public void setMunicipiosDisabled(Boolean municipiosDisabled) {
        this.municipiosDisabled = municipiosDisabled;
    }

    public Boolean getLocalidadesDisabled() {
        return localidadesDisabled;
    }

    public void setLocalidadesDisabled(Boolean localidadesDisabled) {
        this.localidadesDisabled = localidadesDisabled;
    }

    public void setTiposVia(List<SelectItem> tiposVia) {
        this.tiposVia = tiposVia;
    }

    /**
     * @param estadosUsuario
     *            the estadosUsuario to set
     */
    public void setEstadosUsuario(List<SelectItem> estadosUsuario) {
        this.estadosUsuario = estadosUsuario;
    }

    /**
     * @param categoriasPermiso
     *            the categoriasPermiso to set
     */
    public void setCategoriasPermiso(List<SelectItem> categoriasPermiso) {
        this.categoriasPermiso = categoriasPermiso;
    }

    /**
     * @param nivelesRol
     *            the nivelesRol to set
     */
    public void setNivelesRol(List<SelectItem> nivelesRol) {
        this.nivelesRol = nivelesRol;
    }

}
