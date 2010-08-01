package org.librae.adminconfig.service;

import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.common.service.ISearchManager;

/**
 * Interfaz del Manager para la gestion de bibliboteca, usuario, roles.
 * 
 * @author jcisneros
 */
public interface IUsuarioBibliotecaRolManager extends
        ISearchManager<UsuarioBibliotecaRol, Long> {

    public UsuarioBibliotecaRol getUsuarioBibliotecaRolByIds(Long idUsuario,
            Long idBiblioteca, Long idRol);

}
