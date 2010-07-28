package org.librae.adminconfig.webapp.form;

import java.io.Serializable;

import org.librae.adminconfig.model.Direccion;
import org.librae.common.Constants;
import org.librae.common.util.Propiedades;
import org.librae.common.webapp.form.IBaseForm;

/**
 * Formulario que contiene los datos de direcciones comunes a todas las
 * pantallas.
 * 
 * @author aropero
 */
public class DireccionForm implements Serializable, IBaseForm<Direccion> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 572147680915059548L;

    /**
     * ID del pais
     */
    private Long              idPais;

    /**
     * ID de la Comunidad Autonoma
     */
    private Long              idComAutonoma;

    /**
     * ID de la Provincia
     */
    private Long              idProvincia;

    /**
     * ID del Municipio
     */
    private Long              idMunicipio;

    /**
     * ID de la Localidad
     */
    private Long              idLocalidad;

    /**
     * codigo postal
     */
    private String            codigoPostal;

    /**
     * ID del tipo de via
     */
    private Long              idTipoVia;

    /**
     * nombre del edificio
     */
    private String            edificio;

    /**
     * Nombre de via
     */
    private String            direccion;

    /**
     * Numero de via
     */
    private Long              numero;

    private String            piso;

    /**
     * Descripcion extra
     */
    private String            aclarador;

    /** modo de Edición o Lectura de una direccion */
    private Boolean           readMode         = false;

    /**
     * Regla de navegacion que se devolverá tras guardar la direccion
     */
    private String            navigationRuleBack;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(Direccion direccion) {
        if (direccion != null) {
            if (direccion.getPais() != null) {
                idPais = direccion.getPais().getId();
            }
            if (direccion.getComAutonoma() != null) {
                idComAutonoma = direccion.getComAutonoma().getId();
            }
            if (direccion.getProvincia() != null) {
                idProvincia = direccion.getProvincia().getId();
            }
            if (direccion.getMunicipio() != null) {
                idMunicipio = direccion.getMunicipio().getId();
            }
            if (direccion.getLocalidad() != null) {
                idLocalidad = direccion.getLocalidad().getId();
            }
            if (direccion.getTipoVia() != null) {
                idTipoVia = direccion.getTipoVia().getId();
            }
            codigoPostal = direccion.getCodigoPostal();
            edificio = direccion.getNombreEdificio();
            this.direccion = direccion.getNombreVia();
            numero = direccion.getNumeroVia();
            piso = direccion.getPiso();
            aclarador = direccion.getAclarador();
        }
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public Direccion toEntity() {
        return this.toEntity(new Direccion("", null));
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public Direccion toEntity(final Direccion direccion) {
        direccion.setCodigoPostal(codigoPostal);
        direccion.setNombreEdificio(edificio);
        direccion.setNombreVia(this.direccion);
        direccion.setNumeroVia(numero);
        direccion.setPiso(piso);
        direccion.setAclarador(aclarador);

        return direccion;
    }

    /**
     * Método que obtiene del profile.xml el código del país donde se ha
     * realizado la instalación de librae y necesario para el tratamiento de las
     * direcciones asociadas a una biblioteca.
     * 
     * @return
     */
    private Long obtenerIdPaisInstalacion() {
        final Propiedades propertiesProfile = Propiedades
                .getInstance(Constants.USER_WATCHDOG_PROPERTIES);
        final String codigoPais = propertiesProfile
                .getPropiedad(Constants.BIBLIOTECA_ID_PAIS);
        return new Long(codigoPais);
    }
    
    /**
     * Método que obtiene del profile.xml el código del país donde se ha
     * realizado la instalación de librae y necesario para el tratamiento de las
     * direcciones asociadas a una biblioteca.
     * 
     * @return
     */
    private Long obtenerIdComunidadInstalacion() {
        final Propiedades propertiesProfile = Propiedades
                .getInstance(Constants.USER_WATCHDOG_PROPERTIES);
        final String codigoComunidad = propertiesProfile
                .getPropiedad(Constants.BIBLIOTECA_ID_COMUNIDAD);
        return new Long(codigoComunidad);
    }
    
    // Getters & setters
    
    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public Long getIdPais() {
        if (idPais==null) {
            idPais = obtenerIdPaisInstalacion();
        }
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public Long getIdComAutonoma() {
        if (idComAutonoma==null) {
            idComAutonoma = obtenerIdComunidadInstalacion();
        }
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

    public Long getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Long idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Long getIdTipoVia() {
        return idTipoVia;
    }

    public void setIdTipoVia(Long idTipoVia) {
        this.idTipoVia = idTipoVia;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getAclarador() {
        return aclarador;
    }

    public void setAclarador(String aclarador) {
        this.aclarador = aclarador;
    }

    public Boolean getReadMode() {
        return readMode;
    }

    public void setReadMode(Boolean readMode) {
        this.readMode = readMode;
    }

    public String getNavigationRuleBack() {
        return navigationRuleBack;
    }

    public void setNavigationRuleBack(String navigationRuleBack) {
        this.navigationRuleBack = navigationRuleBack;
    }

}
