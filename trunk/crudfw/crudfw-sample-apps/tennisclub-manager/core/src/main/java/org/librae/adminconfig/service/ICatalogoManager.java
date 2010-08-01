package org.librae.adminconfig.service;

import java.util.List;
import java.util.Map;
import org.librae.common.service.ISearchManager;
import org.librae.adminconfig.model.Catalogo;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz del Manager <br/>
 * DAO: ICatalogoDAO <br/>
 * Entidad: Catalogo
 * 
 * @author asantamaria
 * @author impena
 */
public interface ICatalogoManager extends ISearchManager<Catalogo, Long> {

    /**
     * Busca una lista de Catalogos que cumplan las mismas caracteristicas que
     * los criterios pasados como parámetro.
     * 
     * @param criterios
     *            de busqueda del catálogo.
     * @return listado de catalogos.
     */
    List<Catalogo> buscar(Map<String, Object> criterios);

    /**
     * Obtiene el catálogo a partir del código
     * 
     * @param codigo
     *            , codigo.
     * @return Parámetro obtenido.
     */
    Catalogo getCatalogoByCodigo(String codigo);

    /**
     * Elimina al catalogo en BBDD.
     * 
     * @param catalogo
     *            , a eliminar
     * @return Catalogo eliminado.
     */
    @Transactional(readOnly = false)
    void eliminar(Long catalogo);

    /**
     * Guarda al catalogo en BBDD.
     * 
     * @param catalogo
     *            , a guardar
     * @return Catalogo guardado.
     */
    @Transactional(readOnly = false)
    Catalogo salvarCatalogo(Catalogo catalogo);

}