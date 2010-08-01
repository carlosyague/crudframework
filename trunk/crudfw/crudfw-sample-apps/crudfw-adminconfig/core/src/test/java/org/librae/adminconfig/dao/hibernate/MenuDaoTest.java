package org.librae.adminconfig.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.librae.adminconfig.dao.IMenuDAO;
import org.librae.adminconfig.model.Menu;
import org.librae.common.dao.BaseDAOTestCase;

public class MenuDaoTest extends BaseDAOTestCase {
    private IMenuDAO dao;

    public void setMenuDAO(final IMenuDAO dao) {
        this.dao = dao;
    }

    //
    // @Test
    // public void testAddAndRemoveMenu() throws Exception {
    // Menu menu = new Menu("Menu 55", "/pages/menu/list.html", 1L);
    //
    // // enter all required fields
    //
    // log.debug("adding menu...");
    // menu = dao.save(menu);
    // //
    // menu = dao.get(menu.getId());
    //
    // assertNotNull(menu.getId());
    //
    // log.debug("removing menu...");
    //
    // dao.remove(menu.getId());
    // //
    // try {
    // dao.get(menu.getId());
    // fail("Menu found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //

    /**
     * test para el metodo "getPrincipales()". Obtiene los nodos principales del
     * menú
     */

    public void testGetPrincipales() {// this.log.debug("Entrada al metodo: getPrincipales");
        /* Listado de comunidades recogidas de BBDD */

        List<Menu> nodosPrincipalesMenu = null;
        nodosPrincipalesMenu = this.dao.getPrincipales();
        this.log.debug("Hemos recogido: " + nodosPrincipalesMenu.size()
                + " nodos ... ");
        this.log.debug("***********  " + nodosPrincipalesMenu);
        Assert.assertNotNull("Debe existir un nodo del menú ...",
                nodosPrincipalesMenu.size());
        this.log.debug("Salida del metodo: testObtenerComunidadesByPais");
    }
}
