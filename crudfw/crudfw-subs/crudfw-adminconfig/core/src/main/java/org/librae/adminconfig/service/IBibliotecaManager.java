package org.librae.adminconfig.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.BibliotecaView;
import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.Direccion;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.common.service.GenericManager;
import org.librae.common.service.ITreeManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager <br/> DAO: IBibliotecaDAO <br/> Entidad: Biblioteca
 * 
 * @author aropero
 */
public interface IBibliotecaManager extends GenericManager<Biblioteca, Long>,
        ITreeManager {

    /**
     * Método que obtiene una Biblioteca a través de su id.
     * 
     * @param id
     *            , identificador de la biblioteca.
     * @return Biblioteca
     */
    Biblioteca obtenerBiblioteca(Long id);

    /**
     * Método encargado de obtener todos los tipos de Biblioteca existentes en
     * la BBDD.
     * 
     * @return lista de tipos de biblioteca
     */
    List<TipoBiblioteca> obtenerTiposBiblioteca();

    /**
     * Método que obtiene un Tipo de Biblioteca a través de su id.
     * 
     * @param id
     *            , identificador del Tipo de Biblioteca.
     * @return TipoBiblioteca
     */
    TipoBiblioteca obtenerTipoBiblioteca(Long id);

    /**
     * Obtiene las bibliotecas de tipo grupo de bibliotecas de un usuario.
     * 
     * @param idUsuario
     * @return listado de bibliotecas
     */
    List<Biblioteca> getBibliotecasTipoGrupoBiblioteca(Long idUsuario);

    /**
     * Obtiene las bibliotecas de tipo biblioteca de un usuario.
     * 
     * @param idUsuario
     * @return listado de bibliotecas
     */
    List<Biblioteca> getListBibliotecasTipoBiblioteca(Long idUsuario);

    /**
     * Obtiene las bibliotecas de tipo sucursal de un usuario.
     * 
     * @param idUsuario
     * @return listado de bibliotecas
     */
    List<Biblioteca> getListBibliotecasTipoSucursal(Long idUsuario);

    /**
     * @param idBibliotecaPadre
     * @return listado de bibliotecas
     */
    List<Biblioteca> getListBibliotecasTipoBibliotecaConPadre(
            Long idBibliotecaPadre);

    /**
     * @param idBibliotecaPadre
     * @return listado de bibliotecas
     */
    List<Biblioteca> getListBibliotecasTipoSucursalConPadre(
            Long idBibliotecaPadre);

    /**
     * Obtiene las bibliotecas donde tiene algun rol el usuario y bibliotecas
     * descendientes.
     * 
     * @param id
     * @return listado de bibliotecas
     */
    List<BibliotecaView> getBibliotecasByUsuario(Long id);

    /**
     * Obtiene las bibliotecas donde tiene algun rol el usuario.
     * 
     * @param id
     * @return listado de bibliotecas
     */
    List<Biblioteca> getBibliotecasByUsuarioSinDescendientes(Long id);

    /**
     * Método encargado de buscar las bibliotecas que coinciden con los
     * parámetros de búsqueda.
     * 
     * @param biblioteca
     *            , parametros de busqueda.
     * @return listado de bibliotecas encontradas.
     */
    List<Biblioteca> buscar(Map<String, Object> criterios);

    /**
     * Obtiene una lista de identificadores de una lista de biblioteca a traves
     * de un codigo.
     * 
     * @param aplicaGBS
     * @return lista de identificadores de una lista de tipos biblioteca.
     */
    List<String> getTiposBibliotecaByCodigos(final String aplicaGBS);

    /**
     * Asocia una lista de valores-codigo a la biblioteca.
     * 
     * @param valores
     *            , listado de String.
     * @param biblioteca
     *            , biblioteca a la que se le asocian los valores.
     */
    @Transactional(readOnly = false)
    void updateAsignarValCodBiblioteca(List<String> valores,
            Biblioteca biblioteca);

    /**
     * Método que devuelve una lista de valores correspondiente al valor
     * seleccionado en la pestaña de codigos de la edición de una Biblioteca.
     * 
     * @param codigos
     *            , listado de codigos.
     * @param biblioteca
     * @return String con los valores asignados a los codigos.
     */
    @Transactional(readOnly = false)
    String asignarCodigosValores(List<Codigo> codigos, Biblioteca biblioteca);

    /**
     * Metodo que comprueba si se esta intentando eliminar la biblioteca actual
     * o por defecto, lanzando si es el caso la excepcion correspondiente.
     * 
     * @param idBiblioteca
     *            , ID de la Biblioteca que se intenta eliminar.
     * @param usuario
     *            , usuario actual de la session.
     */
    void comprobacionBibliotecaAEliminar(Long idBiblioteca, Usuario usuario);

    /**
     * Método que actualiza los días laborables según el Calendario que posee la
     * Biblioteca (modo edición)
     * 
     * @param idBiblioteca
     *            , ID de la biblioteca.
     * @return String, con los días de la semana.
     */
    String actLabSegunCalendario(Long idBiblioteca);

    /**
     * Obtiene el arbol de bibliotecas por ejemplar.
     * 
     * @param treeNodeBase
     * @param idEjemplar
     * @return
     * @throws Exception
     */
    Object getTreeDataByEjemplar(Class treeNodeBase, Long idEjemplar)
            throws Exception;

    /**
     * Obtiene el arbol de bibliotecas por ejemplar.
     * 
     * @param treeNodeBase
     * @param idRegistro
     * @return
     * @throws Exception
     */
    Object getTreeDataByRegistro(Class treeNodeBase, Long idRegistro)
            throws Exception;

    /**
     * Monta el arbol a partir de las bibliotecas que tiene asignada el usuario.
     * 
     * @param idUsuario
     * @return
     */
    Object getTreeData(Class treeNodeBase, Long idUsuario, String permiso,
            List<String> codigosBibliotecas) throws Exception;

    /**
     * Obtiene la biblioteca por su codigo
     * 
     * @param codigo
     *            de la biblioteca.
     * @return Biblioteca
     */
    Biblioteca getBibliotecaByCodigo(String codigo);

    /**
     * Obtiene el tipo de la biblioteca por el codigo.
     * 
     * @param tipoBiblioteca
     * @return
     */
    TipoBiblioteca getTipoBibliotecaByCodigo(String tipoBiblioteca);

    /**
     * Guarda la biblioteca.
     * 
     * @param biblioteca
     * @param dates
     * @return
     */
    Biblioteca saveBiblioteca(final Biblioteca biblioteca, Date[] dates);

    /**
     * Obtiene el codigo de la biblioteca principal de la aplicacion.
     * 
     * @return
     */
    String getNodoPrincipal();

    /**
     * Obtiene la biblioteca principal de la aplicacion.
     * 
     * @return
     */
    Biblioteca getBibliotecaPrincipal();

    /**
     * Obtiene el tipo de identificacion por su identificador.
     * 
     * @param idTipoBiblioteca
     * @return
     */
    TipoBiblioteca getTipoBiblioteca(Long idTipoBiblioteca);

    /**
     * Devulve true si la biblioteca contiene un registro de tipo.
     * 
     * @param idBiblioteca
     * @param idRegistro
     * @return
     */
    boolean contieneRegistro(Long idBiblioteca, Long idRegistro);

    /**
     * Obtiene la direccion de la biblioteca.
     * 
     * @param idBiblioteca
     *            identificador de la biblioteca.
     * @return
     */
    Direccion getDireccionByIdBiblioteca(Long idBiblioteca);

    Long getIdBibliotecaByDescripcion(String nodoDescripcionClicked);

    Long getIdBibliotecaByCodigo(String nodoDescripcionClicked);

}
