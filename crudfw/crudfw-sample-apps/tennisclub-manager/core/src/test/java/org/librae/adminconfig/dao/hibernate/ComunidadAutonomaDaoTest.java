/*
 * // Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andaluc�a Derechos de
 * // explotaci�n propiedad de la Junta de Andaluc�a. �ste programa es software
 * // libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los
 * t�rminos // de la Licencia EUPL European Public License publicada por el
 * organismo IDABC // de la Comisi�n Europea, en su versi�n 1.0. o posteriores.
 * �ste programa se // distribuye de buena fe, pero SIN NINGUNA GARANT�A,
 * incluso sin las presuntas // garant�as impl�citas de USABILIDAD o ADECUACI�N
 * A PROP�SITO CONCRETO. Para // mas informaci�n consulte la Licencia EUPL
 * European Public License. Usted // recibe una copia de la Licencia EUPL
 * European Public License junto con este // programa, si por alg�n motivo no le
 * es posible visualizarla, puede // consultarla en la siguiente URL: //
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a //
 * copy of the EUPL European Public License along with this program. If not, see
 * // http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reXXu une
 * // copie de la EUPL European Public License avec ce programme. Si non, voir
 * // http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * // EUPL European Public License zusammen mit diesem Programm. Wenn nicht,
 * finden // Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919 //
 */
//
package org.librae.adminconfig.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.librae.adminconfig.dao.IComunidadAutonomaDAO;
import org.librae.common.dao.BaseDAOTestCase;

public class ComunidadAutonomaDaoTest extends BaseDAOTestCase {
    private IComunidadAutonomaDAO dao;

    public void setComunidadAutonomaDAO(final IComunidadAutonomaDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveComunidadAutonoma() throws Exception {
    // ComunidadAutonoma comunidadAutonoma = new ComunidadAutonoma("EXT",
    // "Extremadura");
    //
    // // enter all required fields
    //
    // log.debug("adding comunidadAutonoma...");
    // comunidadAutonoma = dao.save(comunidadAutonoma);
    // //
    // comunidadAutonoma = dao.get(comunidadAutonoma.getId());
    //
    // assertNotNull(comunidadAutonoma.getId());
    //
    // log.debug("removing comunidadAutonoma...");
    //
    // dao.remove(comunidadAutonoma.getId());
    // //
    // try {
    // dao.get(comunidadAutonoma.getId());
    // fail("ComunidadAutonoma found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    // @Test
    // public void testGetExistingComunidad() throws Exception {
    // // Segun datos cargados en el sample-data.xml
    // ComunidadAutonoma com01 = dao.get(-1L);
    //
    // assertNotNull(com01);
    //
    // log.debug("mun01.getNombre() :: " + com01.getNombre());
    //
    // // assertNotNull(com01.getProvincias());
    //
    // // log.debug("com01.getProvincias().get(0).getNombre() :: "
    // // + com01.getProvincias().get(0).getNombre());
    //
    // }
    //
    // @Test
    // public void testAddAndRemoveComunidadAutonomaXProvincias() throws
    // Exception {
    // Provincia malaga = new Provincia("MA", "Málaga");
    // Provincia almeria = new Provincia("AL", "Almería");
    // Provincia granada = new Provincia("GR", "Granada");
    // ComunidadAutonoma andalucia = new ComunidadAutonoma("AND", "Andalucia");
    //
    // // enter all required fields
    //
    // log.debug("adding comunidadAutonoma...");
    // andalucia = dao.save(andalucia);
    //
    // andalucia = dao.get(andalucia.getId());
    //
    // assertNotNull(andalucia.getId());
    //
    // log.debug("removing comunidadAutonoma...");
    //
    // dao.remove(andalucia.getId());
    //
    // try {
    // dao.get(andalucia.getId());
    // fail("ComunidadAutonoma found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    /**
     * test para el metodo "obtenerComunidadesByPais()". Comprueba que se
     * obtienen todos los objetos comunidades autónomas relacionados con el ID
     * del Pais pasado como parámetro.
     */
    @SuppressWarnings("unchecked")
    public void testObtenerComunidadesByPais() {
        this.log.debug("Entrada al metodo: testObtenerComunidadesByPais");
        /**
         * En el sample-data existe esta comunidad(con id=-1) y esta relacionada
         * con un pais.
         */
        final Long idPais = new Long(-1);
        this.log
                .debug("Recogeremos las comunidades autonomas relacionadas con el pais de id ... "
                        + idPais);

        /* Listado de comunidades recogidas de BBDD */
        List listadoComunidadesRecogidas = null;

        listadoComunidadesRecogidas = this.dao.obtenerComunidadesByPais(idPais);

        this.log.debug("Hemos recogido: " + listadoComunidadesRecogidas.size()
                + " comunidades ... ");
        this.log.debug("***********  " + listadoComunidadesRecogidas);
        Assert.assertNotNull(
                "Debe existir una comunidad relacionada con este pais ...",
                listadoComunidadesRecogidas.size());

        this.log.debug("Salida del metodo: testObtenerComunidadesByPais");
    }

}