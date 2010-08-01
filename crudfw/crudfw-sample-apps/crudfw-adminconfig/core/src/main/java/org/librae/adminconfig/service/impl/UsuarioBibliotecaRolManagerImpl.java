package org.librae.adminconfig.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.IBibliotecaDAO;
import org.librae.adminconfig.dao.IUsuarioBibliotecaRolDAO;
import org.librae.adminconfig.dao.IUsuarioDAO;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.service.IUsuarioBibliotecaRolManager;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>
 * DAO: IRolDAO <br/>
 * Entidad: Rol
 * 
 * @author asantamaria
 * @author jcisneros, aropero
 */
public class UsuarioBibliotecaRolManagerImpl extends
        GenericManagerImpl<UsuarioBibliotecaRol, Long> implements
        IUsuarioBibliotecaRolManager, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    IUsuarioBibliotecaRolDAO  usuarioBibliotecaRolDao;
    IBibliotecaDAO            bibliotecaDao;
    IUsuarioDAO               usuarioDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public UsuarioBibliotecaRolManagerImpl(
            final IUsuarioBibliotecaRolDAO usuarioBibliotecaRolDao) {
        super(usuarioBibliotecaRolDao);
        this.usuarioBibliotecaRolDao = usuarioBibliotecaRolDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<UsuarioBibliotecaRol> buscar(final Map<String, Object> criterios) {
        return usuarioBibliotecaRolDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<UsuarioBibliotecaRol> buscarNonLazy(
            final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    /**
     * @see org.librae.adminconfig.service.IRolManager#getUsuarioBibliotecaRolByIds(Long
     *      idUsuario, Long idBiblioteca, Long idRol)
     */
    public UsuarioBibliotecaRol getUsuarioBibliotecaRolByIds(Long idUsuario,
            Long idBiblioteca, Long idRol) {

        UsuarioBibliotecaRol result = null;

        final List<UsuarioBibliotecaRol> ubrs = usuarioBibliotecaRolDao
                .getUsuarioBibliotecaRolByIds(idUsuario, idBiblioteca, idRol);

        if (ubrs != null && ubrs.size() == 1) {
            result = ubrs.get(0);
        }

        return result;
    }

    // Getters && Setters

}
