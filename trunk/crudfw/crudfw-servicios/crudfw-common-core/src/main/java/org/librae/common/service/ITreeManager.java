package org.librae.common.service;

import java.util.List;
import java.util.Map;

/**
 * Interfaz para la utilizacion de arboles.
 * 
 * @author jcisneros
 */
public interface ITreeManager {

    /**
     * Obtiene un Map, con el par id, codigo, de las bibliotecas de tipo grupo
     * de bibliotecas.
     * 
     * @return devuelve las bibliotecas de tipo grupo de bibliotecas
     */
    Map<Long, String> getBibliotecasTipoGrupoBiblioteca();

    /**
     * Obtiene un Map, con el par id, codigo, de las bibliotecas de tipo grupo
     * de bibliotecas.
     * 
     * @param idBibliotecaPadre
     *            la biblioteca padre
     * @return devuelve las bibliotecas de tipo grupo de bibliotecas
     */
    Map<Long, String> getBibliotecasTipoSucursal(Long idBibliotecaPadre);

    /**
     * Obtiene un Map, con el par id, codigo, de las bibliotecas de tipo grupo
     * de bibliotecas.
     * 
     * @param idBibliotecaPadre
     *            la biblioteca padre
     * @return devuelve las bibliotecas de tipo grupo de bibliotecas
     */
    Map<Long, String> getBibliotecasTipoBiblioteca(Long idBibliotecaPadre);

    /**
     * Obtiene el arbol de bibliotecas.
     * 
     * @return
     */
    Object getTreeData(Class treeNodeBase) throws Exception;

    /**
     * Obtiene el arbol de bibliotecas.
     * 
     * @param idUsuario
     * @return
     */
    Object getTreeData(Class treeNodeBase, Long idUsuario, String permiso, List<String> codigosBibliotecas)
            throws Exception;

}
