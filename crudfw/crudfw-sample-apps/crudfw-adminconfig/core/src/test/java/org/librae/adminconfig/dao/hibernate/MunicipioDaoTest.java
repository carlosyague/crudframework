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

public class MunicipioDaoTest extends BaseDAOTestCase {
    private IMunicipioDAO municipioDao;
    @SuppressWarnings("unused")
    private IProvinciaDAO provinciaDao;

    public void setMunicipioDAO(final IMunicipioDAO dao) {
        this.municipioDao = dao;
    }

    public void setProvinciaDAO(final IProvinciaDAO dao) {
        this.provinciaDao = dao;
    }

    //
    // @Test
    // public void testAddAndRemoveMunicipio() throws Exception {
    // Municipio municipio = new Municipio("TRM", "Torremolinos");
    //
    // // enter all required fields
    //
    // log.debug("adding municipio...");
    // municipio = municipioDao.save(municipio);
    // //
    // municipio = municipioDao.get(municipio.getId());
    //
    // assertNotNull(municipio.getId());
    //
    // log.debug("removing municipio...");
    //
    // municipioDao.remove(municipio.getId());
    // //
    // try {
    // municipioDao.get(municipio.getId());
    // fail("Municipio found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    // @Test
    // public void testGetExistingMunicipio() throws Exception {
    // // Segun datos cargados en el sample-data.xml
    // Municipio mun01 = municipioDao.get(-1L);
    //
    // assertNotNull(mun01);
    //
    // log.debug("mun01.getNombre() :: " + mun01.getNombre());
    //
    // assertNotNull(mun01.getProvincia());
    //
    // log.debug("mun01.getProvincia().getNombre() :: "
    // + mun01.getProvincia().getNombre());
    //
    // }
    //
    // @Test
    // public void testAddAndRemoveMunicipioXProvincia() throws Exception {
    //
    // Provincia malaga = new Provincia("MA", "Málaga");
    //
    // Municipio churriana = new Municipio("CHU", "Churriana");
    // churriana.setProvincia(malaga);
    //
    // // enter all required fields
    //
    // log.debug("adding churriana...");
    // churriana = municipioDao.save(churriana);
    // //
    // churriana = municipioDao.get(churriana.getId());
    //
    // log.debug("churriana.getId() = " + churriana.getId());
    //
    // assertNotNull(churriana.getId());
    //
    // log.debug("removing churriana...");
    //
    // municipioDao.remove(churriana.getId());
    // //
    // try {
    // municipioDao.get(churriana.getId());
    // fail("Municipio found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    /**
     * test para el metodo "obtenerMunicipiosByProvincia()". Comprueba que se
     * obtienen todos los objetos municipios relacionados con el ID de la
     * provincia pasado como parámetro.
     */
    @SuppressWarnings("unchecked")
    public void testObtenerMunicipiosByProvincia() {
        this.log.debug("Entrada al metodo: testObtenerMunicipiosByProvincia");
        /*
         * En el sample-data existe esta provincia (con id=-1) y esta
         * relacionada con 2 municipios (con id=-1 y con id=-2)
         */
        final Long idProvincia = new Long(-1);
        this.log
                .debug("Recogeremos los municipios relacionados con la provincia de id ... "
                        + idProvincia);

        /* Listado de municipios recogidas de BBDD */
        List listadoMunicipiosRecogidos = null;

        listadoMunicipiosRecogidos = this.municipioDao
                .obtenerMunicipiosByProvincia(idProvincia);

        this.log.debug("Hemos recogido: " + listadoMunicipiosRecogidos.size()
                + " municipios ... ");
        this.log.debug("***********  " + listadoMunicipiosRecogidos);

        Assert
                .assertNotNull(
                        "Deben existir municipios relacionados con esta provincia ... ",
                        listadoMunicipiosRecogidos.size());

        this.log.debug("Salida del metodo: testObtenerMunicipiosByProvincia");
    }

}