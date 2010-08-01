package org.librae.adminconfig.service.impl;

import java.util.ArrayList;
import java.util.List;

import model.CodPer;

import org.librae.adminconfig.dao.IFavoritoDAO;
import org.librae.adminconfig.dao.IFavoritoUsuarioDAO;
import org.librae.adminconfig.dao.IMenuDAO;
import org.librae.adminconfig.model.FavoritoUsuario;
import org.librae.adminconfig.model.Menu;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.service.IMenuManager;
import org.librae.circulacion.model.Consorcio;
import org.librae.common.Constants;
import org.librae.common.dao.GenericDAO;
import org.librae.common.exception.MensajesError;
import org.librae.common.model.LibraeUser;
import org.librae.common.model.MenuItem;
import org.librae.common.service.impl.GenericManagerImpl;
import org.librae.common.util.Propiedades;

/**
 * Implementación del Manager <br/>
 * DAO: IMenuDAO <br/>
 * Entidad: Menu
 *
 * @author jcisneros
 */
public class MenuManagerImpl extends GenericManagerImpl<Menu, Long> implements
		IMenuManager, org.librae.common.service.IMenuManager {

	IMenuDAO menuDao;
	private GenericDAO<Consorcio, Long> consorcioDao;
	private IFavoritoDAO favoritoDao;
	private IFavoritoUsuarioDAO favoritoUsuarioDao;

	/**
	 * Constructor del Manager
	 *
	 * @param dao
	 *            objeto DAO a manejar
	 */
	public MenuManagerImpl(final IMenuDAO menuDao) {
		super(menuDao);
		this.menuDao = menuDao;
	}

	/**
	 * Convierte una lista de pojos Menu a una lista de MenuItem.
	 *
	 * @param menu
	 *            lista de pojos Menu.
	 * @param permisos
	 * @return lista de MenuItem para convertirlos a cadenas.
	 */
	private List<MenuItem> convertMenuToMenuItem(final List<Menu> menu,
			final List<CodPer> permisos, List<Consorcio> consorcios) {
		List<MenuItem> salidaHijos = null;
		final List<MenuItem> salida = new ArrayList<MenuItem>();
		for (final Menu nodo : menu) {
			salidaHijos = convertMenuToMenuItem(nodo.getHijos(), permisos,
					consorcios);
			if (("Consorcio".equals((nodo.getTexto())))
					&& (contienePermisos(permisos, nodo.getPermiso()))) {
				for (final Consorcio consorcio : consorcios) {
					final StringBuilder url = new StringBuilder(nodo.getUrl());
					url.append("&idConsorcio=");
					url.append(consorcio.getId());
					final MenuItem menuItem = new MenuItem(consorcio
							.getNombre(), url.toString(), "_self", salidaHijos,
							false);
					salida.add(menuItem);
				}
			} else if ((contienePermisos(permisos, nodo.getPermiso()))
					|| (!salidaHijos.isEmpty())) {
				final boolean pintarImagen = (!salidaHijos.isEmpty() && nodo
						.getPadre() != null);
				final MenuItem menuItem = new MenuItem(nodo.getTexto(), nodo
						.getUrl(), "_self", salidaHijos, pintarImagen);
				salida.add(menuItem);
			}
		}
		return salida;
	}

	private boolean contienePermisos(List<CodPer> codigosPermisos,
			Permiso permiso) {
		for (final CodPer codigoPermiso : codigosPermisos) {
			if ((permiso != null)
					&& (codigoPermiso.getCod().equals(permiso.getCodigo()))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see org.librae.common.service.IMenuManager#getMenu()
	 */
	public String getMenu(final LibraeUser usuario) {
		List<CodPer> permisos = new ArrayList<CodPer>();
		List<MenuItem> salida = new ArrayList<MenuItem>();
		if (usuario != null) {
			permisos = usuario.getPer();
		}
		final List<Menu> menu = menuDao.getPrincipales();
		final List<Consorcio> consorcios = consorcioDao.getAll();
		if (permisos != null) {
			salida = convertMenuToMenuItem(menu, permisos, consorcios);
		}
		return salida.toString();
	}

	public String getFavoritos(LibraeUser u) {
		MenuItem menuItemFinal = null;
		MenuItem menuItemAdministrar = null;
		String favoritosFinal = null;
		final List<MenuItem> salida = new ArrayList<MenuItem>();
		final List<FavoritoUsuario> favoritos = favoritoUsuarioDao
				.getFavoritosByUsuario(u.getId());
		String prueba = favoritos.toString();
		log.debug(prueba.toString());
		if ((favoritos!=null) && (!favoritos.isEmpty())) {
			for (final FavoritoUsuario favorito : favoritos) {
				final StringBuilder url = new StringBuilder(favorito.getUrl());
				final StringBuilder texto = new StringBuilder(favoritoDao
						.getFavorito(favorito.getUrl()));
				final MenuItem menuItem = new MenuItem(texto.toString(), url
						.toString(), "_self", null, false);
				salida.add(menuItem);
			}

			// "Administrar Mis Favoritos"
			menuItemAdministrar = new MenuItem(MensajesError
					.get(MensajesError.PROPERTI_ADMINCONFIG,
							"favorito.listado.menu"),
					"/librae-adminconfig/pages/favoritos/list.html", "_self",
					null, false);
			salida.add(0, menuItemAdministrar);
		}
		menuItemFinal = new MenuItem("Favoritos", null, "_self", salida, false);
		favoritosFinal = menuItemFinal.toString();
		return favoritosFinal;
	}

	public void insertarFavorito(Object usuario, String url) {
		final Usuario u = (Usuario) usuario;

		final Propiedades properties = Propiedades
				.getInstance(Constants.USER_WATCHDOG_PROPERTIES);
		final Long maximo = new Long(properties
				.getPropiedad(Constants.MAXIMO_MENU));

		final List<FavoritoUsuario> favoritos = favoritoUsuarioDao
				.getFavoritosByUsuario(u.getId());

		if (favoritos.size() < (new Long(maximo).intValue())) {
			final String favorito = favoritoDao.getFavorito(url);
			if ((favorito != null)
					&& (favoritoUsuarioDao.getFavoritoUsuario(u.getId(),
							url) == null)) {
				final FavoritoUsuario favoritoUsuario = new FavoritoUsuario(u,
						favorito);
				favoritoUsuario.setTexto(favorito);
				favoritoUsuario.setUrl(url);
				favoritoUsuario.setOrden(new Long(favoritos.size() + 1));
				favoritoUsuarioDao.save(favoritoUsuario);
			}
		}
	}

	// Getters && Setters

	public GenericDAO<Consorcio, Long> getConsorcioDao() {
		return consorcioDao;
	}

	public void setConsorcioDao(GenericDAO<Consorcio, Long> consorcioDao) {
		this.consorcioDao = consorcioDao;
	}

	/**
	 * @return the favoritoDao
	 */
	public IFavoritoDAO getFavoritoDao() {
		return favoritoDao;
	}

	/**
	 * @param favoritoDao
	 *            the favoritoDao to set
	 */
	public void setFavoritoDao(IFavoritoDAO favoritoDao) {
		this.favoritoDao = favoritoDao;
	}

	/**
	 * @return the favoritoUsuarioDao
	 */
	public IFavoritoUsuarioDAO getFavoritoUsuarioDao() {
		return favoritoUsuarioDao;
	}

	/**
	 * @param favoritoUsuarioDao
	 *            the favoritoUsuarioDao to set
	 */
	public void setFavoritoUsuarioDao(IFavoritoUsuarioDAO favoritoUsuarioDao) {
		this.favoritoUsuarioDao = favoritoUsuarioDao;
	}

}