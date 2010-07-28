package org.librae.common.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase para el comportamiento comun para los formularios de búsqueda con
 * direcciones.
 * 
 * @author jcisneros
 */
public class DireccionForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2616514579550019541L;

	private Long   idPais;

    private Long   idComAutonoma;

    private Long   idProvincia;

    private Long   idMunicipio;

    private Long   idLocalidad;

    private String codigoPostal;

    private String edificio;

    private String direccion;

    private Long   numero;

    private String piso;

    private Long   idTipoVia;

    private String aclarador;

    /**
     * Completa un map con los criterios de la direccion.
     * 
     * @param criterios
     * @return
     */
    public HashMap<String, Object> toMap(HashMap<String, Object> criterios) {
        criterios.put("idPais", getIdPais());
        if (!(new Long(0)).equals(getIdComAutonoma())) {
            criterios.put("idComAutonoma", getIdComAutonoma());
        }
        if (!(new Long(0)).equals(getIdProvincia())) {
            criterios.put("idProvincia", getIdProvincia());
        }
        if (!(new Long(0)).equals(getIdMunicipio())) {
            criterios.put("idMunicipio", getIdMunicipio());
        }
        if (!(new Long(0)).equals(getIdLocalidad())) {
            criterios.put("idLocalidad", getIdLocalidad());
        }
        criterios.put("codPostal", getCodigoPostal());
        criterios.put("edificio", getEdificio());
        criterios.put("direccion", getDireccion());
        criterios.put("piso", getPiso());
        criterios.put("idTipoVia", getIdTipoVia());
        criterios.put("aclarador", getAclarador());

        return criterios;
    }

    /**
     * Convierte el map a valores del formulario.
     * 
     * @param criterios
     */
    public void fromMap(final Map<String, Object> criterios) {
        if (criterios != null) {
            setIdPais((Long) criterios.get("idPais"));
            setIdComAutonoma((Long) criterios.get("idComAutonoma"));
            setIdProvincia((Long) criterios.get("idProvincia"));
            setIdMunicipio((Long) criterios.get("idMunicipio"));
            setIdLocalidad((Long) criterios.get("idLocalidad"));
            setCodigoPostal((String) criterios.get("codPostal"));
            setEdificio((String) criterios.get("edificio"));
            setDireccion((String) criterios.get("direccion"));
            setPiso((String) criterios.get("piso"));
            setIdTipoVia((Long) criterios.get("idTipoVia"));
            setAclarador((String) criterios.get("aclarador"));
        }
    }

    // Getters && Setters

    /**
     * @return the idPais
     */
    public Long getIdPais() {
        return idPais;
    }

    /**
     * @param idPais
     *            the idPais to set
     */
    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    /**
     * @return the idComAutonoma
     */
    public Long getIdComAutonoma() {
        return idComAutonoma;
    }

    /**
     * @param idComAutonoma
     *            the idComAutonoma to set
     */
    public void setIdComAutonoma(Long idComAutonoma) {
        this.idComAutonoma = idComAutonoma;
    }

    /**
     * @return the idProvincia
     */
    public Long getIdProvincia() {
        return idProvincia;
    }

    /**
     * @param idProvincia
     *            the idProvincia to set
     */
    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    /**
     * @return the idMunicipio
     */
    public Long getIdMunicipio() {
        return idMunicipio;
    }

    /**
     * @param idMunicipio
     *            the idMunicipio to set
     */
    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    /**
     * @return the idLocalidad
     */
    public Long getIdLocalidad() {
        return idLocalidad;
    }

    /**
     * @param idLocalidad
     *            the idLocalidad to set
     */
    public void setIdLocalidad(Long idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    /**
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal
     *            the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return the edificio
     */
    public String getEdificio() {
        return edificio;
    }

    /**
     * @param edificio
     *            the edificio to set
     */
    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion
     *            the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the numero
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @param numero
     *            the numero to set
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    /**
     * @return the piso
     */
    public String getPiso() {
        return piso;
    }

    /**
     * @param piso
     *            the piso to set
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * @return the aclarador
     */
    public String getAclarador() {
        return aclarador;
    }

    /**
     * @param aclarador
     *            the aclarador to set
     */
    public void setAclarador(String aclarador) {
        this.aclarador = aclarador;
    }

    /**
     * @return the idTipoVia
     */
    public Long getIdTipoVia() {
        return idTipoVia;
    }

    /**
     * @param idTipoVia
     *            the idTipoVia to set
     */
    public void setIdTipoVia(Long idTipoVia) {
        this.idTipoVia = idTipoVia;
    }

}
