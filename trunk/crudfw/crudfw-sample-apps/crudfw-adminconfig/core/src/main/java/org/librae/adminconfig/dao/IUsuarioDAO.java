package org.librae.adminconfig.dao;

import java.util.List;

import org.librae.adminconfig.model.Usuario;
import org.librae.common.dao.GenericDAO;
import org.librae.common.dao.IGenericSearchDAO;

/**
 * Interfaz del DAO para la entidad Usuario
 * 
 * @author asantamaria
 */
public interface IUsuarioDAO extends IGenericSearchDAO<Usuario, Long>,
        GenericDAO<Usuario, Long> {

    /**
     * Busca el usuario a partir de su codigo en Librae.
     * 
     * @param usuario
     * @return
     */
    Usuario getUsuarioByUsuario(String usuario);

    /**
     * Busca el usuario a partir de su NIF.
     * 
     * @param nif
     * @return
     */
    Usuario getUsuarioByNIF(String nif);

    /**
     * Obtiene los usuarios de la lista de identificadores.
     * 
     * @param listadoIds
     *            lista de identificadores.
     * @return lista de usuarios.
     */
    List<Usuario> getUsuariosById(List<String> listadoIds);

    /**
     * Obtiene la lista de usuarios de la biblioteca.
     * 
     * @param sucursalId
     * @return
     */
    List<Usuario> getUsuariosBySucursalId(String sucursalId);

    /**
     * @param sucursalId
     * @return
     */
    List<Usuario> getUsuariosBySucIdXaPrestSeg(String sucursalId);

    /**
     * @param codSucursal
     * @return
     */
    List<Usuario> getUsuariosByCodSucXaPrestSeg(String codSucursal);

    List<Usuario> getUsuariosUnRol();

}
