package org.librae.adminconfig.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.librae.adminconfig.dao.IUsuarioBibliotecaRolDAO;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.common.dao.BaseDAOTestCase;

public class UsuarioBibliotecaRolDaoTest extends BaseDAOTestCase {
    private IUsuarioBibliotecaRolDAO dao;

    public void setUsuarioBibliotecaRolDAO(final IUsuarioBibliotecaRolDAO dao) {
        this.dao = dao;
    }

    // @Test
    // public void testAddAndRemoveUsuarioBibliotecaRol() throws Exception {
    //
    // Usuario usuario = new Usuario("usuario");
    //
    // Direccion direccion = new Direccion("Larios", 3L);
    //
    // Biblioteca biblioteca = new Biblioteca("BIB01", "Biblioteca");
    // biblioteca.setDireccion(direccion);
    //
    // Rol rol = new Rol("ROL53", "Rol 01");
    //
    // UsuarioBibliotecaRol usuarioBibliotecaRol = new UsuarioBibliotecaRol(
    // usuario, rol, biblioteca);
    //
    // // enter all required fields
    //
    // log.debug("adding usuarioBibliotecaRol...");
    //
    // usuarioBibliotecaRol = dao.save(usuarioBibliotecaRol);
    //
    // usuarioBibliotecaRol = dao.get(usuarioBibliotecaRol.getId());
    // assertNotNull(usuarioBibliotecaRol.getId());
    // log.debug("removing usuarioBibliotecaRol...");
    // dao.remove(usuarioBibliotecaRol.getId());
    //
    // try {
    // dao.get(usuarioBibliotecaRol.getId());
    // fail("UsuarioBibliotecaRol found in database");
    // } catch (LibraeException dae) {
    // log.debug("Expected exception: " + dae.getMessage());
    // assertNotNull(dae);
    // }
    // }
    //
    /**
     * test para el metodo "saveBibliotecaPorDefecto()". Actualizo los datos de
     * biblioteca por defecto.
     */

    /*
     * public void testSaveBibliotecaPorDefecto() throws Exception {
     * this.log.debug("Entrada al metodo: saveBibliotecaPorDefecto"); /** En el
     * sample-data existe un usuario (con id=-1) y esta relacionada con el
     * identificador de biblioteca id=-1. final Long idUsuario = new Long(-1);
     * final Long idBiblioteca = new Long(-1); final boolean defecto = true; try
     * { this.dao.saveBibliotecaPorDefecto(idUsuario, idBiblioteca, defecto); }
     * catch (final LibraeException dae) {
     * this.log.debug("No Expected exception: " + dae.getMessage());
     * Assert.assertNotNull(dae); } }
     */

    /**
     * test para el metodo "getUsuarioBibliotecaRolByIds()". Nos devuelve la
     * lista de usuarios de la biblioteca por roles.
     */
    public void testGetUsuarioBibliotecaRolByIds() throws Exception {
        this.log.debug("Entrada al metodo: getUsuarioBibliotecaRolByIds");
        /**
         * En el sample-data existe un usuario (con id=-1) y esta relacionada
         * con el identificador de biblioteca id=-1.
         */
        final Long idUsuario = new Long(-1);
        final Long idBiblioteca = new Long(-1);
        final Long idRol = new Long(-1);
        List<UsuarioBibliotecaRol> usuarioBibliotecaRo = null;
        usuarioBibliotecaRo = this.dao.getUsuarioBibliotecaRolByIds(idUsuario,
                idBiblioteca, idRol);
        Assert
                .assertNotNull(
                        "Debe existir un usuario de biblioteca con esa identificación y rol ...",
                        usuarioBibliotecaRo.size());

        this.log.debug("Salida del metodo: getUsuarioBibliotecaRolByIds");
    }
}