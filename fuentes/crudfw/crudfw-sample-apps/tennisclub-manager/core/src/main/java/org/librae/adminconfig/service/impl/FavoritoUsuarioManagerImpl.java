package org.librae.adminconfig.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.IFavoritoUsuarioDAO;
import org.librae.adminconfig.model.FavoritoUsuario;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.service.IFavoritoUsuarioManager;
import org.librae.common.Constants;
import org.librae.common.service.impl.GenericManagerImpl;

/**
 * Implementación del Manager <br/>
 * DAO: IFavoritoUsuarioDAO <br/>
 * Entidad: FavoritoUsuario
 * 
 * @author jcisneros
 */
public class FavoritoUsuarioManagerImpl extends
        GenericManagerImpl<FavoritoUsuario, Long> implements
        IFavoritoUsuarioManager {

    IFavoritoUsuarioDAO favoritoUsuarioDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public FavoritoUsuarioManagerImpl(IFavoritoUsuarioDAO favoritoUsuarioDao) {
        super(favoritoUsuarioDao);
        this.favoritoUsuarioDao = favoritoUsuarioDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<FavoritoUsuario> buscar(Map<String, Object> criterios) {
        return favoritoUsuarioDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<FavoritoUsuario> buscarNonLazy(
            final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    public void bajarOrden(Long idBajarOrden, Usuario usuario) {
        final FavoritoUsuario favoritoUsuario = favoritoUsuarioDao
                .get(idBajarOrden);
        final FavoritoUsuario favoritoUsuarioSubir = favoritoUsuarioDao
                .getOrden(usuario.getId(), favoritoUsuario.getOrden() + 1);

        if (favoritoUsuarioSubir != null) {
            favoritoUsuario.setOrden(favoritoUsuarioSubir.getOrden());
            favoritoUsuarioSubir.setOrden(favoritoUsuario.getOrden() - 1);

            favoritoUsuarioDao.save(favoritoUsuario);
            favoritoUsuarioDao.save(favoritoUsuarioSubir);
        }

    }

    public void subirOrden(Long idSubirOrden, Usuario usuario) {
        final FavoritoUsuario favoritoUsuario = favoritoUsuarioDao
                .get(idSubirOrden);
        final FavoritoUsuario favoritoUsuarioBajar = favoritoUsuarioDao
                .getOrden(usuario.getId(), favoritoUsuario.getOrden() - 1);

        if (favoritoUsuarioBajar != null) {
            favoritoUsuario.setOrden(favoritoUsuarioBajar.getOrden());
            favoritoUsuarioBajar.setOrden(favoritoUsuario.getOrden() + 1);

            favoritoUsuarioDao.save(favoritoUsuario);
            favoritoUsuarioDao.save(favoritoUsuarioBajar);
        }
    }

    public void eliminar(List<FavoritoUsuario> favoritos) {
        final Map<String, Object> criterios = new HashMap<String, Object>();
        Long idUsuario = null;
        Long orden = new Long(0);
        for (final FavoritoUsuario favorito : favoritos) {
            idUsuario = favorito.getUsuario().getId();
            favoritoUsuarioDao.remove(favorito);
        }
        favoritoUsuarioDao.flush();
        favoritoUsuarioDao.clear();

        if (idUsuario != null) {
            criterios.put("idUsuario", idUsuario);
            criterios.put(Constants.SORTCOLUMN, "orden");
            criterios.put(Constants.ASCENDING, true);

            final List<FavoritoUsuario> favoritosUsuario = favoritoUsuarioDao
                    .buscar(criterios);
            for (final FavoritoUsuario favorito : favoritosUsuario) {
                favorito.setOrden(orden);
                favoritoUsuarioDao.save(favorito);
                orden++;
            }
        }
    }

    public List<FavoritoUsuario> getFavoritoUsuario(List<String> listadoId) {
        final List<FavoritoUsuario> favoritos = new ArrayList<FavoritoUsuario>();
        for (final String favorito : listadoId) {
            favoritos.add(favoritoUsuarioDao.get(new Long(favorito)));
        }
        return favoritos;
    }

    // Getters && Setters

    public IFavoritoUsuarioDAO getFavoritoUsuarioDao() {
        return favoritoUsuarioDao;
    }

    public void setFavoritoUsuarioDao(IFavoritoUsuarioDAO favoritoUsuarioDao) {
        this.favoritoUsuarioDao = favoritoUsuarioDao;
    }

}
