/*
 * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos de
 * explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es software
 * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los t�rminos
 * de la Licencia EUPL European Public License publicada por el organismo IDABC
 * de la Comisi�n Europea, en su versi�n 1.0. o posteriores. �ste programa se
 * distribuye de buena fe, pero SIN NINGUNA GARANT�A, incluso sin las presuntas
 * garant�as impl�citas de USABILIDAD o ADECUACI�N A PROP�SITO CONCRETO. Para
 * mas informaci�n consulte la Licencia EUPL European Public License. Usted
 * recibe una copia de la Licencia EUPL European Public License junto con este
 * programa, si por alg�n motivo no le es posible visualizarla, puede
 * consultarla en la siguiente URL:
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
 * copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu une
 * copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden
 * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */

package org.librae.adminconfig.service.impl;


import org.librae.adminconfig.dao.IComunidadAutonomaDAO;
import org.librae.adminconfig.dao.IDireccionDAO;
import org.librae.adminconfig.dao.ILocalidadDAO;
import org.librae.adminconfig.dao.IMunicipioDAO;
import org.librae.adminconfig.dao.IPaisDAO;
import org.librae.adminconfig.dao.IProvinciaDAO;
import org.librae.adminconfig.dao.ITipoViaDAO;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.ComunidadAutonoma;
import org.librae.adminconfig.model.Direccion;
import org.librae.adminconfig.model.Localidad;
import org.librae.adminconfig.model.Municipio;
import org.librae.adminconfig.model.Pais;
import org.librae.adminconfig.model.Provincia;
import org.librae.adminconfig.model.TipoVia;
import org.librae.adminconfig.service.IDireccionManager;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>DAO: IDireccionDAO <br/>Entidad: Direccion
 *
 * @author aropero
 */
public class DireccionManagerImpl extends GenericManagerImpl<Direccion, Long>
        implements IDireccionManager {

    /**
     * Interfaz del DAO de Direccion
     */
    IDireccionDAO           direccionDao;

    /**
     * Interfaz del DAO de Pais
     */
    IPaisDAO                paisDao;

    /**
     * Interfaz del DAO de ComunidadAutonoma
     */
    IComunidadAutonomaDAO   comunidadDao;

    /**
     * Interfaz del DAO de Provincia
     */
    IProvinciaDAO           provinciaDao;

    /**
     * Interfaz del DAO de Consorcio
     */
    IMunicipioDAO           municipioDao;

    /**
     * Interfaz del DAO de Localidad
     */
    ILocalidadDAO           localidadDao;

    /**
     * Interfaz del DAO de TipoVia
     */
    ITipoViaDAO             tipoViaDao;

    /**
     * Constructor del Manager
     *
     * @param dao
     *            objeto DAO a manejar
     */
    public DireccionManagerImpl(IDireccionDAO direccionDao) {
        super(direccionDao);
        this.direccionDao = direccionDao;
    }


    /**
     * {@inheritDoc}
     */
    public Biblioteca addDireccion(
            Biblioteca biblioteca, Long idPais,
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

        /** Actualizamos la direccion en la biblioteca  */
        biblioteca.setDireccion(dir);

        return biblioteca;
    }

    public Pais getPais(Long idPais) {
        return paisDao.get(idPais);
    }

    public ComunidadAutonoma getComAutonoma(Long idComAutonoma){
        return  comunidadDao.get(idComAutonoma);
    }

    public Provincia getProvincia(Long idProvincia){
        return provinciaDao.get(idProvincia);
    }

    public Municipio getMunicipio(Long idMunicipio){
        return municipioDao.get(idMunicipio);
    }

    public Localidad getLocalidad(Long idLocalidad){
        return localidadDao.get(idLocalidad);
    }

    /** ========== getters & setters ===========*/
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
}