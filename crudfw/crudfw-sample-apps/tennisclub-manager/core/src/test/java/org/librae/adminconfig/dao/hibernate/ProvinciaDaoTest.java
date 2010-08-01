// /*
// * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos de
// * explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es software
// * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los
// t�rminos
// * de la Licencia EUPL European Public License publicada por el organismo
// IDABC
// * de la Comisi�n Europea, en su versi�n 1.0. o posteriores. �ste programa se
// * distribuye de buena fe, pero SIN NINGUNA GARANT�A, incluso sin las
// presuntas
// * garant�as impl�citas de USABILIDAD o ADECUACI�N A PROP�SITO CONCRETO. Para
// * mas informaci�n consulte la Licencia EUPL European Public License. Usted
// * recibe una copia de la Licencia EUPL European Public License junto con este
// * programa, si por alg�n motivo no le es posible visualizarla, puede
// * consultarla en la siguiente URL:
// * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
// * copy of the EUPL European Public License along with this program. If not,
// see
// * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu une
// * copie de la EUPL European Public License avec ce programme. Si non, voir
// * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
// * EUPL European Public License zusammen mit diesem Programm. Wenn nicht,
// finden
// * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
// */
//
package org.librae.adminconfig.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.librae.adminconfig.dao.IMunicipioDAO;
import org.librae.adminconfig.dao.IProvinciaDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class ProvinciaDaoTest extends BaseDAOTestCase {
    private IProvinciaDAO provinciaDao;
    private IMunicipioDAO municipioDao;

    public void setProvinciaDAO(final IProvinciaDAO dao) {
        this.provinciaDao = dao;
    }

    public void setMunicipioDAO(final IMunicipioDAO dao) {
        this.municipioDao = dao;
    }

    // @Test
    // public void testAddAndRemoveProvincia() throws Exception {
    // Provincia provincia = new Provincia("MA", "Málaga");
    //
    // // enter all required fields
    //
    // log.debug("adding provincia...");
    // provincia = provinciaDao.save(provincia);
    //
    // provincia = provinciaDao.get(provincia.getId());
    //
    // assertNotNull(provincia.getId());
    //
    // log.debug("removing provincia...");
    //
    // provinciaDao.remove(provincia.getId());
    //
    // try {
    // provinciaDao.get(provincia.getId());
    // fail("Provincia found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    // @Test
    // public void testAddAndRemoveProvinciaXComunidad() throws Exception {
    // Provincia malaga = new Provincia("MA", "Málaga");
    // ComunidadAutonoma andalucia = new ComunidadAutonoma("AND", "Andalucía");
    //
    // andalucia.setCodigo("AND");
    // andalucia.setNombre("Andalucia");
    //
    // malaga.setCodigo("MA");
    // malaga.setNombre("Málaga");
    // malaga.setComunidadAutonoma(andalucia);
    //
    // // enter all required fields
    //
    // log.debug("adding provincia...");
    // malaga = provinciaDao.save(malaga);
    //
    // malaga = provinciaDao.get(malaga.getId());
    //
    // assertNotNull(malaga.getId());
    //
    // log.debug("removing provincia...");
    //
    // provinciaDao.remove(malaga.getId());
    //
    // try {
    // provinciaDao.get(malaga.getId());
    // fail("Provincia found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    // @Test
    // public void testGetExistingProvincia() throws Exception {
    // // En el sample-data.xml se
    // // carga Torremolinos con el id
    // // = -1
    // Provincia pro01 = provinciaDao.get(-1L);
    //
    // assertNotNull(pro01);
    //
    // log.debug("pro01.getNombre() :: " + pro01.getNombre());
    //
    // assertNotNull(pro01.getComunidadAutonoma());
    //
    // log.debug("pro01.getComunidadAutonoma().getNombre() :: "
    // + pro01.getComunidadAutonoma().getNombre());
    // }
    //
    // @Test
    // public void testAddAndRemoveProvinciaXMunicipios() throws Exception {
    // Provincia malaga = new Provincia("MA", "Málaga");
    //
    // malaga.setCodigo("MA");
    // malaga.setNombre("Málaga");
    //
    // Municipio cartama = new Municipio("CART", "Cártama");
    // Municipio churriana = new Municipio("CHUR", "Churriana");
    // Municipio torremolinos = new Municipio("TORR", "Torremolinos");
    //
    // // ArrayList<Municipio> municipios = new ArrayList<Municipio>();
    // // municipios.add(cartama);
    // // municipios.add(churriana);
    // // municipios.add(torremolinos);
    //
    // // malaga.setMunicipios(municipios);
    //
    // // enter all required fields
    //
    // log.debug("adding malaga...");
    // malaga = provinciaDao.save(malaga);
    //
    // log.debug("cartama.getNombre() :: " + cartama.getNombre());
    // log.debug("cartama.getId() :: " + cartama.getId());
    //
    // malaga = provinciaDao.get(malaga.getId());
    //
    // log.debug("cartama.getNombre() :: " + cartama.getNombre());
    // log.debug("cartama.getId() :: " + cartama.getId());
    //
    // // log.debug("malaga.getMunicipios().get(0).getNombre() :: "
    // // + malaga.getMunicipios().get(0).getNombre());
    // // log.debug("malaga.getMunicipios().get(0).getId() :: "
    // // + malaga.getMunicipios().get(0).getId());
    //
    // // Long cod = malaga.getMunicipios().get(0).getId();
    //
    // // Municipio muni = municipioDao.get(cod);
    //
    // // assertNotNull(muni);
    //
    // // log.debug("muni.getNombre() :: " + muni.getNombre());
    // // log.debug("muni.getId() :: " + muni.getId());
    //
    // assertNotNull(malaga.getId());
    //
    // log.debug("malaga.getId() = " + malaga.getId());
    // // log.debug("malaga.getMunicipios().get(1).getNombre() = "
    // // + malaga.getMunicipios().get(1).getNombre());
    //
    // log.debug("removing malaga...");
    //
    // provinciaDao.remove(malaga.getId());
    //
    // try {
    // provinciaDao.get(malaga.getId());
    // fail("Provincia found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    /**
     * test para el metodo "obtenerProvinciasByComunidad()". Comprueba que se
     * obtienen todos los objetos provincias relacionados con el ID de la
     * comunidad pasado como parámetro.
     */
    @SuppressWarnings("unchecked")
    public void testObtenerProvinciasByComunidad() {
        this.log.debug("Entrada al metodo: testObtenerProvinciasByComunidad");
        /*
         * En el sample-data existe esta comunidad (con id=-1) y esta
         * relacionado con 2 provincias
         */
        final Long idComunidad = new Long(-1);
        this.log
                .debug("Recogeremos las provincias relacionadas con la comunidad de id ... "
                        + idComunidad);

        /* Listado de comunidades recogidas de BBDD */
        List listadoProvinciasRecogidas = null;

        listadoProvinciasRecogidas = this.provinciaDao
                .obtenerProvinciasByComunidad(idComunidad);

        this.log.debug("Hemos recogido: " + listadoProvinciasRecogidas.size()
                + " provincias ... ");
        this.log.debug("***********  " + listadoProvinciasRecogidas);

        Assert
                .assertEquals(
                        "Deben existir 2 provincias relacionadas con esta comunidad ... ",
                        listadoProvinciasRecogidas.size(), 2);

        this.log.debug("Salida del metodo: testObtenerProvinciasByComunidad");
    }
}