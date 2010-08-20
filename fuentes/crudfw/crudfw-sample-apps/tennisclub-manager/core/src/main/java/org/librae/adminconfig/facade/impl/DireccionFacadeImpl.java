package org.librae.adminconfig.facade.impl;

import org.librae.adminconfig.dao.IComunidadAutonomaDAO;
import org.librae.adminconfig.dao.IDireccionDAO;
import org.librae.adminconfig.dao.ILocalidadDAO;
import org.librae.adminconfig.dao.IMunicipioDAO;
import org.librae.adminconfig.dao.IPaisDAO;
import org.librae.adminconfig.dao.IProvinciaDAO;
import org.librae.adminconfig.dao.ITipoViaDAO;
import org.librae.adminconfig.facade.IDireccionFacade;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.ComunidadAutonoma;
import org.librae.adminconfig.model.Direccion;
import org.librae.adminconfig.model.Localidad;
import org.librae.adminconfig.model.Municipio;
import org.librae.adminconfig.model.Pais;
import org.librae.adminconfig.model.Provincia;
import org.librae.adminconfig.model.TipoVia;

/**
 * Implementación de la interfaz IArboledaFacade.
 *
 * @author jcisneros
 */
public class DireccionFacadeImpl implements IDireccionFacade {

    /**
     * Interfaz del DAO de Direccion
     */
    IDireccionDAO         direccionDao;

    /**
     * Interfaz del DAO de Pais
     */
    IPaisDAO              paisDao;

    /**
     * Interfaz del DAO de ComunidadAutonoma
     */
    IComunidadAutonomaDAO comunidadDao;

    /**
     * Interfaz del DAO de Provincia
     */
    IProvinciaDAO         provinciaDao;

    /**
     * Interfaz del DAO de Consorcio
     */
    IMunicipioDAO         municipioDao;

    /**
     * Interfaz del DAO de Localidad
     */
    ILocalidadDAO         localidadDao;

    /**
     * Interfaz del DAO de TipoVia
     */
    ITipoViaDAO           tipoViaDao;

    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public DireccionFacadeImpl() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public Biblioteca addDireccion(Biblioteca biblioteca, Long idPais,
            Long idComAutonoma, Long idProvincia, Long idMunicipio,
            Long idLocalidad, Long idTipoVia, String codigoPostal,
            String edificio, String direccion, Long numero, String piso,
            String aclarador) {
        Direccion dir = null;

        if (biblioteca.getDireccion() != null) {
            dir = biblioteca.getDireccion();
            dir.setNombreVia(direccion);
            dir.setNumeroVia(numero);
        } else {
            dir = new Direccion(direccion, numero);
        }
        dir.setCodigoPostal(codigoPostal);
        dir.setNombreEdificio(edificio);
        dir.setPiso(piso);
        dir.setAclarador(aclarador);

        /* Pais */
        if ((idPais != null) && (!idPais.equals(new Long(0)))) {
            final Pais pais = paisDao.get(idPais);
            dir.setPais(pais);
        } else {
            dir.setPais(null);
        }
        /* Comunidad Autónoma */
        if ((idComAutonoma != null) && (!idComAutonoma.equals(new Long(0)))) {
            final ComunidadAutonoma comAutonoma = comunidadDao
                    .get(idComAutonoma);
            dir.setComAutonoma(comAutonoma);
        } else {
            dir.setComAutonoma(null);
        }

        /* Provincia */
        if ((idProvincia != null) && (!idProvincia.equals(new Long(0)))) {
            final Provincia provincia = provinciaDao.get(idProvincia);
            dir.setProvincia(provincia);
        } else {
            dir.setProvincia(null);
        }

        /* Municipio */
        if ((idMunicipio != null) && (!idMunicipio.equals(new Long(0)))) {
            final Municipio municipio = municipioDao.get(idMunicipio);
            dir.setMunicipio(municipio);
        } else {
            dir.setMunicipio(null);
        }

        /* Localidad */
        if ((idLocalidad != null) && (!idLocalidad.equals(new Long(0)))) {
            final Localidad localidad = localidadDao.get(idLocalidad);
            dir.setLocalidad(localidad);
        } else {
            dir.setLocalidad(null);
        }

        /* TipoVia */
        if ((idTipoVia != null) && (!idTipoVia.equals(new Long(0)))) {
            final TipoVia tipoVia = tipoViaDao.get(idTipoVia);
            dir.setTipoVia(tipoVia);
        } else {
            dir.setTipoVia(null);
        }

        /** Creamos/Actualizamos la direccion en BBDD */
        dir = direccionDao.save(dir);

        /** Actualizamos la direccion en la biblioteca */
        biblioteca.setDireccion(dir);

        return biblioteca;
    }

    /**
     * {@inheritDoc}
     */
    public ComunidadAutonoma getComunidadAutonoma(Long idComunidadAutonoma) {
        return comunidadDao.get(idComunidadAutonoma);
    }

    /**
     * {@inheritDoc}
     */
    public Localidad getLocalidad(Long idLocalidad) {
        return localidadDao.get(idLocalidad);
    }

    /**
     * {@inheritDoc}
     */
    public Municipio getMunicipio(Long idMunicipio) {
        return municipioDao.get(idMunicipio);
    }

    /**
     * {@inheritDoc}
     */
    public Pais getPais(Long idPais) {
        return paisDao.get(idPais);
    }

    /**
     * {@inheritDoc}
     */
    public Provincia getProvincia(Long idProvincia) {
        return provinciaDao.get(idProvincia);
    }

    /**
     * {@inheritDoc}
     */
    public TipoVia getTipoVia(Long idTipoVia) {
        return tipoViaDao.get(idTipoVia);
    }

    public Direccion completarDireccion(Direccion direccion, Long idPais,
            Long idComunidad, Long idLocalidad, Long idMunicipio,
            Long idProvincia, Long idTipoVia) {
        if (idPais != null && !(new Long(0).equals(idPais))) {
            final Pais pais = paisDao.get(idPais);
            direccion.setPais(pais);
        }
        if (idComunidad != null && !(new Long(0).equals(idComunidad)) ) {
            final ComunidadAutonoma comunidadAutonoma = comunidadDao
                    .get(idComunidad);
            direccion.setComAutonoma(comunidadAutonoma);
        }
        if (idProvincia != null && !(new Long(0).equals(idProvincia))) {
            final Provincia provincia = provinciaDao.get(idProvincia);
            direccion.setProvincia(provincia);
        }
        if (idLocalidad != null && !(new Long(0).equals(idLocalidad))) {
            final Localidad localidad = localidadDao.get(idLocalidad);
            direccion.setLocalidad(localidad);
        }
        if (idMunicipio != null && !(new Long(0).equals(idMunicipio))) {
            final Municipio municipio = municipioDao.get(idMunicipio);
            direccion.setMunicipio(municipio);
        }
        if (idTipoVia != null && !(new Long(0).equals(idTipoVia))) {
            final TipoVia tipoVida = tipoViaDao.get(idTipoVia);
            direccion.setTipoVia(tipoVida);
        }
        return direccion;
    }

    // Getters & setters

    public IComunidadAutonomaDAO getComunidadDao() {
        return comunidadDao;
    }

    public void setComunidadDao(IComunidadAutonomaDAO comunidadDao) {
        this.comunidadDao = comunidadDao;
    }

    public IProvinciaDAO getProvinciaDao() {
        return provinciaDao;
    }

    public void setProvinciaDao(IProvinciaDAO provinciaDao) {
        this.provinciaDao = provinciaDao;
    }

    public IMunicipioDAO getMunicipioDao() {
        return municipioDao;
    }

    public void setMunicipioDao(IMunicipioDAO municipioDao) {
        this.municipioDao = municipioDao;
    }

    public ILocalidadDAO getLocalidadDao() {
        return localidadDao;
    }

    public void setLocalidadDao(ILocalidadDAO localidadDao) {
        this.localidadDao = localidadDao;
    }

    public IPaisDAO getPaisDao() {
        return paisDao;
    }

    public void setPaisDao(IPaisDAO paisDao) {
        this.paisDao = paisDao;
    }

    public ITipoViaDAO getTipoViaDao() {
        return tipoViaDao;
    }

    public void setTipoViaDao(ITipoViaDAO tipoViaDao) {
        this.tipoViaDao = tipoViaDao;
    }

    /**
     * @return the direccionDao
     */
    public IDireccionDAO getDireccionDao() {
        return direccionDao;
    }

    /**
     * @param direccionDao
     *            the direccionDao to set
     */
    public void setDireccionDao(IDireccionDAO direccionDao) {
        this.direccionDao = direccionDao;
    }

}
