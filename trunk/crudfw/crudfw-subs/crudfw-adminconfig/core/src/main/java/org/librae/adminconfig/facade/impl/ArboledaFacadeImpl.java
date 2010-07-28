package org.librae.adminconfig.facade.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.dao.IBibliotecaDAO;
import org.librae.adminconfig.dao.IBibliotecaViewDAO;
import org.librae.adminconfig.facade.IArboledaFacade;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.BibliotecaView;
import org.librae.common.model.Arbol;
import org.librae.common.util.StringUtil;

/**
 * Implementacion de la interfaz IArboledaFacade.
 * 
 * @author jcisneros
 */
public class ArboledaFacadeImpl implements IArboledaFacade {

	protected final Log         log = LogFactory.getLog(ArboledaFacadeImpl.class);
	public IBibliotecaDAO bibliotecaDao = null;
	public IBibliotecaViewDAO bibliotecaViewDao = null;

	public List<BibliotecaView> descendientesBibliotecas(
			List<BibliotecaView> bibliotecasAsignadas, List<BibliotecaView> bibliotecas) {

		if (!memberRootAsignadas(bibliotecasAsignadas, getRoot(bibliotecas))) {
			final List<Arbol> padreHijos = getPadreHijos(bibliotecas);

			List<Arbol> bibliotecasLocalizadas = new ArrayList<Arbol>();
			List<Arbol> bibliotecasFinalesArbol = new ArrayList<Arbol>();
			final List<BibliotecaView> bibliotecasFinales = new ArrayList<BibliotecaView>();

			for (final BibliotecaView nodo : bibliotecasAsignadas) {
				bibliotecasLocalizadas = descendientesBibliotecasRecurrente(
						nodo, padreHijos);

				if (bibliotecasFinalesArbol.size() != 0) {
					bibliotecasFinalesArbol = insertarNoRepetidos(
							bibliotecasFinalesArbol, bibliotecasLocalizadas);
				} else {
					bibliotecasFinalesArbol.addAll(bibliotecasLocalizadas);
				}

			}

			for (final Arbol nodo : bibliotecasFinalesArbol) {
				bibliotecasFinales.add(bibliotecas.get(nodo.getPos()));
			}

			return bibliotecasFinales;
		} else {
			return bibliotecas;
		}
	}

	public List<Long> descendientesBibliotecasLong(
			List<BibliotecaView> bibliotecasAsignadas, List<BibliotecaView> bibliotecas) {

		if (!memberRootAsignadas(bibliotecasAsignadas, getRoot(bibliotecas))) {
			final List<Arbol> padreHijos = getPadreHijos(bibliotecas);

			List<Long> bibliotecasLocalizadas = new ArrayList<Long>();
			List<Long> bibliotecasFinalesId = new ArrayList<Long>();
			for (final BibliotecaView nodo : bibliotecasAsignadas) {
				bibliotecasLocalizadas = descendientesBibliotecasRecurrenteLong(
						nodo, padreHijos);

				if (bibliotecasFinalesId.size() != 0) {
					bibliotecasFinalesId = insertarNoRepetidosLong(
							bibliotecasFinalesId, bibliotecasLocalizadas);
				} else {
					bibliotecasFinalesId.addAll(bibliotecasLocalizadas);
				}

			}
			return bibliotecasFinalesId;

		} else {
			return convertBibliotecasToIds(bibliotecas);
		}
	}

	private List<Long> convertBibliotecasToIds(List<BibliotecaView> bibliotecas) {
		final List<Long> bibliotecasId = new ArrayList<Long>();
		for (final BibliotecaView nodo : bibliotecas) {
			bibliotecasId.add(nodo.getId());
		}
		return bibliotecasId;
	}

	private Long getRoot(List<BibliotecaView> bibliotecas) {
		for (final BibliotecaView nodo : bibliotecas) {
			if (nodo.getPadre() == null) {
				return nodo.getId();
			}
		}
		return null;
	}

	private boolean memberRootAsignadas(List<BibliotecaView> bibliotecas,
			Long idRoot) {

		for (final BibliotecaView nodo : bibliotecas) {
			if (nodo.getId().equals(idRoot)) {
				return true;
			}
		}
		return false;
	}

	private List<Arbol> insertarNoRepetidos(List<Arbol> bibliotecasFinales,
			List<Arbol> bibliotecasLocalizadas) {
		boolean esta = false;

		for (final Arbol bibliotecaL : bibliotecasLocalizadas) {
			for (final Arbol bibliotecaF : bibliotecasFinales) {

				if (bibliotecaL.getNodo().equals(bibliotecaF.getNodo())) {
					esta = true;
					break;
				}
			}
			if (!esta) {
				bibliotecasFinales.add(bibliotecaL);
			}
			esta = false;
		}

		return bibliotecasFinales;
	}

	private List<Long> insertarNoRepetidosLong(List<Long> bibliotecasFinales,
			List<Long> bibliotecasLocalizadas) {
		boolean esta = false;

		for (final Long bibliotecaL : bibliotecasLocalizadas) {
			for (final Long bibliotecaF : bibliotecasFinales) {

				if (bibliotecaL.equals(bibliotecaF)) {
					esta = true;
				}
			}
			if (!esta) {
				bibliotecasFinales.add(bibliotecaL);
			}
		}

		return bibliotecasFinales;
	}

	public List<Long> descendientesBibliotecasRecurrenteLong(
			BibliotecaView biblioteca, List<Arbol> bibliotecas) {

		int contador = 0;
		Long idBiblioteca = null;
		Long padre = null;
		Boolean terminar = Boolean.FALSE;

		final List<Long> bibliotecasLocalizadas = new ArrayList<Long>();
		bibliotecasLocalizadas.add(biblioteca.getId());

		while ((!bibliotecasLocalizadas.isEmpty()) && (!terminar)) {
			idBiblioteca = bibliotecasLocalizadas.get(contador);

			for (final Arbol bibliotecaAux : bibliotecas) {
				padre = bibliotecaAux.getPadre();
				if ((padre != null) && (idBiblioteca.equals(padre))) {
					bibliotecasLocalizadas.add(bibliotecaAux.getNodo());
				}
			}
			contador++;
			if (contador == bibliotecasLocalizadas.size()) {
				terminar = Boolean.TRUE;
			}
		}
		return bibliotecasLocalizadas;
	}

	public List<Arbol> descendientesBibliotecasRecurrente(
			BibliotecaView biblioteca, List<Arbol> bibliotecas) {

		int contador = 0;
		Long padre = null;
		Boolean terminar = Boolean.FALSE;

		final List<Arbol> bibliotecasLocalizadas = new ArrayList<Arbol>();
		final Arbol bibliotecaArbol = encontrarNodo(biblioteca, bibliotecas);
		bibliotecasLocalizadas.add(bibliotecaArbol);

		while ((!bibliotecasLocalizadas.isEmpty()) && (!terminar)) {
			final Arbol nodo = bibliotecasLocalizadas.get(contador);
			for (final Arbol bibliotecaAux : bibliotecas) {
				padre = bibliotecaAux.getPadre();
				if ((padre != null) && (nodo.getNodo().equals(padre))) {
					bibliotecasLocalizadas.add(bibliotecaAux);
				}
			}
			contador++;
			if (contador == bibliotecasLocalizadas.size()) {
				terminar = Boolean.TRUE;
			}
		}
		return bibliotecasLocalizadas;
	}

	private Arbol encontrarNodo(BibliotecaView biblioteca, List<Arbol> bibliotecas) {
		final Arbol nodo = null;
		final Long idBiblioteca = biblioteca.getId();
		for (final Arbol bibliotecaAux : bibliotecas) {
			if (idBiblioteca.equals(bibliotecaAux.getNodo())) {
				return bibliotecaAux;
			}
		}

		return nodo;
	}

	public List<Arbol> getPadreHijos(List<BibliotecaView> bibliotecas) {

		final List<Arbol> padreHijos = new ArrayList<Arbol>();
		Integer contador = new Integer(0);
		for (final BibliotecaView biblioteca : bibliotecas) {
			final Long padre = biblioteca.getPadre();
			Arbol nodo = null;
			if (padre != null) {
				nodo = new Arbol(biblioteca.getId(), padre, contador);

			} else {
				nodo = new Arbol(biblioteca.getId(), null, contador);
			}
			padreHijos.add(nodo);
			contador++;
		}

		return padreHijos;
	}

	public List<Biblioteca> buscarPorString(
			Map<String, Object> criteriosBiblioteca) {

		return bibliotecaDao.buscarPorString(criteriosBiblioteca);
	}

	public void getBibliotecasByCriterios(Map<String, Object> criterios) {
		if ((!StringUtil.esVacia(criterios.get("codigoBiblioteca")))
				|| (!StringUtil.esVacia(criterios
						.get(Biblioteca.PROPTY_NAME_DESCRIPCION)))
				|| criterios.get("idBiblioteca") != null) {
			final List<BibliotecaView> listaBibliotecasFinal = new ArrayList<BibliotecaView>();
			List<Long> listaBibliotecasIds = new ArrayList<Long>();
			if (!StringUtil.esVacia(criterios.get("codigoBiblioteca"))) {
				final Map<String, Object> criteriosBiblioteca = new HashMap<String, Object>();
				criteriosBiblioteca.put(Biblioteca.PROPTY_NAME_CODIGO,
						criterios.get("codigoBiblioteca"));
				listaBibliotecasFinal.addAll(bibliotecaViewDao
						.buscarPorString(criteriosBiblioteca));
				listaBibliotecasIds = convertBibliotecasToIds(listaBibliotecasFinal);

			}
			if (!StringUtil.esVacia(criterios
					.get(Biblioteca.PROPTY_NAME_DESCRIPCION))) {
				final Map<String, Object> criteriosBiblioteca = new HashMap<String, Object>();
				criteriosBiblioteca.put(Biblioteca.PROPTY_NAME_DESCRIPCION,
						criterios.get(Biblioteca.PROPTY_NAME_DESCRIPCION));
				listaBibliotecasFinal.addAll(bibliotecaViewDao
						.buscarPorString(criteriosBiblioteca));
				listaBibliotecasIds = convertBibliotecasToIds(listaBibliotecasFinal);
			}

			final Long idBiblioteca = (Long) criterios.get("idBiblioteca");
			if (idBiblioteca != null) {
				listaBibliotecasFinal.add(bibliotecaViewDao.get(idBiblioteca));
				listaBibliotecasIds = descendientesBibliotecasLong(
						listaBibliotecasFinal, bibliotecaViewDao.getBibliotecas());
			}
			if (!listaBibliotecasIds.isEmpty()) {
				criterios.put("bibliotecas", listaBibliotecasIds);
			}
		}
	}

	/* getters and setters */

	public IBibliotecaDAO getBibliotecaDao() {
		return bibliotecaDao;
	}

	public void setBibliotecaDao(IBibliotecaDAO bibliotecaDao) {
		this.bibliotecaDao = bibliotecaDao;
	}

	public IBibliotecaViewDAO getBibliotecaViewDao() {
		return bibliotecaViewDao;
	}

	public void setBibliotecaViewDao(IBibliotecaViewDAO bibliotecaViewDao) {
		this.bibliotecaViewDao = bibliotecaViewDao;
	}

}
