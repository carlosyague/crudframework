package org.librae.adminconfig.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.librae.adminconfig.dao.IFavoritoUsuarioDAO;
import org.librae.adminconfig.model.FavoritoUsuario;

import org.librae.common.dao.hibernate.GenericSearchDao;

/**
 * Implementación del DAO para la entidad FavoritoUsuario.
 * 
 * @author jcisneros
 */
public class FavoritoUsuarioDAOImpl extends
        GenericSearchDao<FavoritoUsuario, Long> implements IFavoritoUsuarioDAO {

    /**
     * Constructor del DAO
     */
    public FavoritoUsuarioDAOImpl() {
        super(FavoritoUsuario.class);
    }

    public FavoritoUsuario getFavoritoUsuario(Long idUsuario, String url,
            Long identificador) {
        final Object[] parametros = { idUsuario, url, identificador };
        final StringBuilder sb = new StringBuilder();
        sb.append(" select favoritoUsuario");
        sb.append(" from ").append(FavoritoUsuario.ENTITY_NAME).append(
                " favoritoUsuario ");
        sb.append(" where favoritoUsuario.usuario.id = ? ");
        sb.append(" and favoritoUsuario.url = ? ");
        sb.append(" and favoritoUsuario.identificador = ? ");

        final List<FavoritoUsuario> favoritos = getHibernateTemplate().find(
                sb.toString(), parametros);
        return (favoritos.isEmpty()) ? null : (FavoritoUsuario) favoritos
                .get(0);
    }

    public FavoritoUsuario getFavoritoUsuario(Long idUsuario, String url) {
        final Object[] parametros = { idUsuario, url };
        final StringBuilder sb = new StringBuilder();
        sb.append(" select favoritoUsuario");
        sb.append(" from ").append(FavoritoUsuario.ENTITY_NAME).append(
                " favoritoUsuario ");
        sb.append(" where favoritoUsuario.usuario.id = ? ");
        sb.append(" and favoritoUsuario.url = ? ");

        final List<FavoritoUsuario> favoritos = getHibernateTemplate().find(
                sb.toString(), parametros);
        return (favoritos.isEmpty()) ? null : (FavoritoUsuario) favoritos
                .get(0);
    }

    public List<FavoritoUsuario> getFavoritosByUsuario(Long idUsuario) {
        final Object[] parametros = { idUsuario };
        final StringBuilder sb = new StringBuilder();
        sb.append(" select favoritoUsuario");
        sb.append(" from ").append(FavoritoUsuario.ENTITY_NAME).append(
                " favoritoUsuario ");
        sb.append(" where favoritoUsuario.usuario.id = ? ");
        final List<FavoritoUsuario> favoritos = getHibernateTemplate().find(
                sb.toString(), parametros);
        return favoritos;
    }

    @Override
    protected DetachedCriteria obtenerCriteria(Map<String, Object> criterios) {
        flush();
        clear();
        final DetachedCriteria criteriaFavoritos = DetachedCriteria
                .forClass(FavoritoUsuario.class);
        if (criterios != null) {
            if ((criterios.get("idUsuario") != null)
                    && (!(new Long(0)).equals(criterios.get("idUsuario")))) {
                criteriaFavoritos.createAlias(
                        FavoritoUsuario.PROPERTY_NAME_USUARIO,
                        FavoritoUsuario.PROPERTY_NAME_USUARIO);
                criteriaFavoritos.add(Restrictions.eq("usuario.id", criterios
                        .get("idUsuario")));
            }
        }
        return criteriaFavoritos;
    }

    public FavoritoUsuario getOrden(Long idUsuario, Long orden) {
        final Object[] parametros = { orden, idUsuario };
        final StringBuilder sb = new StringBuilder();
        sb.append(" select favoritoUsuario");
        sb.append(" from ").append(FavoritoUsuario.ENTITY_NAME).append(
                " favoritoUsuario ");
        sb.append(" where favoritoUsuario.orden = ? ");
        sb.append(" and favoritoUsuario.usuario.id = ? ");

        final List<FavoritoUsuario> favoritos = getHibernateTemplate().find(
                sb.toString(), parametros);
        return (favoritos.isEmpty()) ? null : (FavoritoUsuario) favoritos
                .get(0);
    }

    public Long getNumeroOrden(Long idUsuario) {
        final Object[] parametros = { idUsuario };
        final StringBuilder sb = new StringBuilder();
        sb.append(" select favoritoUsuario");
        sb.append(" from ").append(FavoritoUsuario.ENTITY_NAME).append(
                " favoritoUsuario ");
        sb.append(" where favoritoUsuario.orden = ? ");
        final List<FavoritoUsuario> favoritos = getHibernateTemplate().find(
                sb.toString(), parametros);
        return (favoritos.isEmpty()) ? null : (Long) new Long(favoritos.size());
    }

}
