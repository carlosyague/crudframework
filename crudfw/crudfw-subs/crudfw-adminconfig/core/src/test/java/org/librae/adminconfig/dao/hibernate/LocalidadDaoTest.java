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

import org.librae.adminconfig.dao.ILocalidadDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class LocalidadDaoTest extends BaseDAOTestCase {
    private ILocalidadDAO dao;

    public void setLocalidadDAO(final ILocalidadDAO dao) {
        this.dao = dao;
    }

    //
    // @Test
    // public void testAddAndRemoveLocalidad() throws Exception {
    // Localidad localidad = new Localidad("TRM", "Torremolinos");
    //
    // // enter all required fields
    //
    // log.debug("adding localidad...");
    // localidad = dao.save(localidad);
    // //
    // localidad = dao.get(localidad.getId());
    //
    // assertNotNull(localidad.getId());
    //
    // log.debug("removing localidad...");
    //
    // dao.remove(localidad.getId());
    // //
    // try {
    // dao.get(localidad.getId());
    // fail("Localidad found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    // @Test
    // public void testGetExistingLocalidad() throws Exception {
    // // Segun datos cargados en el sample-data.xml
    // Localidad loc01 = dao.get(-1L);
    //
    // assertNotNull(loc01);
    //
    // log.debug("mun01.getNombre() :: " + loc01.getNombre());
    //
    // assertNotNull(loc01.getMunicipio());
    //
    // log.debug("loc01.getMunicipio().getNombre() :: "
    // + loc01.getMunicipio().getNombre());
    //
    // }
    //
    // @Test
    // public void testAddAndRemoveLocalidadXMunicipio() throws Exception {
    //
    // Municipio pancrudo = new Municipio("PAN", "Pancrudo");
    //
    // Localidad portalrubio = new Localidad("PORTR", "Portalrubio");
    // portalrubio.setBaja(false);
    // portalrubio.setEsMunicipio(false);
    // portalrubio.setMunicipio(pancrudo);
    //
    // // enter all required fields
    //
    // log.debug("adding churriana...");
    // portalrubio = dao.save(portalrubio);
    // //
    // portalrubio = dao.get(portalrubio.getId());
    //
    // log.debug("portalrubio.getId() = " + portalrubio.getId());
    //
    // assertNotNull(portalrubio.getId());
    //
    // log.debug("removing portalrubio...");
    //
    // dao.remove(portalrubio.getId());
    // //
    // try {
    // dao.get(portalrubio.getId());
    // fail("Localidad found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    /**
     * M&eacute;todo de test para el m&eacute;todo
     * "obtenerLocalidadesByMunicipio()". Comprueba que se obtienen todos los
     * objetos localidades relacionados con el ID del Municipio pasado como
     * parámetro.
     */
    @SuppressWarnings("unchecked")
    public void testObtenerLocalidadesByMunicipio() {
        this.log
                .debug("Entrada al metodo: testObtenerLocalidadesByMunicipio()");
        /**
         * En el sample-data existe este municipio (con id=-1) y esta
         * relacionado con una localidad (con id=-1)
         */
        final Long idMunicipio = new Long(-1);
        this.log
                .debug("Recogeremos las localidades relacionadas con el municipio de id ... "
                        + idMunicipio);

        /* Listado de localidades recogidas de BBDD */
        List listadoLocalidadesRecogidas = null;

        listadoLocalidadesRecogidas = this.dao
                .obtenerLocalidadesByMunicipio(idMunicipio);

        this.log.debug("Hemos recogido: " + listadoLocalidadesRecogidas.size()
                + " localidades ... ");
        this.log.debug("***********  " + listadoLocalidadesRecogidas);

        Assert
                .assertNotNull(
                        "Deben existir 1 localidad relacionadas con este municipio ... ",
                        listadoLocalidadesRecogidas.size());

        this.log.debug("Salida del metodo: testObtenerLocalidadesByMunicipio");
    }

}