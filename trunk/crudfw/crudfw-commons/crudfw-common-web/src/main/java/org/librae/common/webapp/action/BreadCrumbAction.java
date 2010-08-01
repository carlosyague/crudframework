package org.librae.common.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.Constants;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.BreadCrumbItem;
import org.librae.common.webapp.util.ConstantesBreadCrumb;

/**
 * Clase encargada de obtener el breadCrumb a mostrar al usuario
 */
public class BreadCrumbAction extends BasePage implements Serializable {

	protected final Log log = LogFactory.getLog(BreadCrumbAction.class);

	/**
	 * Numero de serializacion.
	 */
	private static final long serialVersionUID = -1382876932678358393L;

	/**
	 * Método encargado de obtener la lista de BreadCrumbItem que representan el
	 * path a mostrar al usuario 1. Compruebo si en SessionManager hay ya un
	 * BreadCrumbMenu. En tal caso lo devuelvo 2. Si no existe en SessionManager
	 * devuelvo una estructura nueva invocando al método createBreadCrumb()
	 *
	 * @return List<BreadCrumbItem> que representa el path a mostrar al usuario
	 */
	public List<BreadCrumbItem> getPath() {
		List<BreadCrumbItem> res = new ArrayList<BreadCrumbItem>();
		SessionManager sessionManager = getSessionManager();
		try {
			if (sessionManager != null) {
				List<BreadCrumbItem> breadCrumb = (List<BreadCrumbItem>) sessionManager
						.getAttribute(Constants.BREADCRUMB_SESSION_PARAM);
				if (breadCrumb == null) {
					res = createBreadCrumb(sessionManager);
				} else {
					res = breadCrumb;
					log.info("[BreadCrumbAction] existe un path en sesion");
				}
			}
		} catch (final Exception e) {
			log.error("Error al obtener el breadcrumb...", e);
			res = new ArrayList<BreadCrumbItem>();
		}

		return res;
	}

	/**
	 * Método encargado de crear un nuevo BreadCrumb añadiendole la página
	 * actual y guardandolo en sesiónManager 1. Creo una nueva lista de
	 * BreadCrumbItem 2. Obtengo el viewId de la página actual accediendo a la
	 * instancia actual de Facecontext 3. Si viewId es nulo o vacío ir a 5 4.
	 * Añado a la lista creada los items asociados a ese viewId haciendo uso del
	 * método addItems() 5. Guardo en SessionManager el path 6. devuelvo el path
	 *
	 * @param sessionManager
	 *
	 * @return List<BreadCrumbItem> con un sólo item de la página actual
	 */
	public List<BreadCrumbItem> createBreadCrumb(SessionManager sessionManager) {
		List<BreadCrumbItem> path = new ArrayList<BreadCrumbItem>();
		try {
			if (sessionManager != null) {
				final String viewId = getFacesContext().getCurrentInstance()
						.getViewRoot().getViewId();
				log
						.info("[BreadCrumbAction] no existe un path es sesion. se crea con wiewId: "
								+ viewId);
				if (viewId != null && !viewId.equals("")) {
					addItems(viewId, path);
				}
				if (sessionManager.getAttribute(Constants.ID_USUARIO_LOGADO)!=null) {
				    sessionManager.setAttribute(Constants.BREADCRUMB_SESSION_PARAM,
						path, false);
				}
			}
		} catch (final Exception e) {
			log.error("Error creando el breadcrumb...", e);
			path = new ArrayList<BreadCrumbItem>();
		}

		return path;
	}

	/**
	 * Método encargado de añadir al path indicado la lista de BreadCrumbItem
	 * asociados al viewId de una página 1. Compruebo que viewId no se encuentra
	 * ya en el path, si es así trunco la lista y termino 2. Obtengo la lista de
	 * viewIds que deberían aparecer en el path antes del pasado como parámetro
	 * haciendo uso del método ConstentesBreadCrumb.getRuta() y añado al final
	 * de la ruta el viewId recibido como parámetro 4. Convierto cada viewId de
	 * listaViewIds en un objeto BreadCrumbItem haciendo uso del método
	 * ConstentesBreadCrumb.getItem() el cual añado al path
	 *
	 * @param viewId
	 *            identificador de la página a añadir al path
	 * @param path
	 *            actual donde añadir viewId
	 */
	public void addItems(String viewId, List<BreadCrumbItem> path) {
		log.debug("addItems del padre");
		if ((path != null) && !contiene(path, viewId)) {
			// obtengo la lista de viewId que deberían aparecer antes
			// y añado al final el nuevo
			List<String> anteriores = ConstantesBreadCrumb.getRuta(viewId);
			if (anteriores == null) {
				anteriores = new ArrayList<String>();
			}
			anteriores.add(viewId);

			// convierto los viewId en BreadCrumItem
			final Iterator<String> it = anteriores.iterator();
			while (it.hasNext()) {
				final String id = it.next();
				final BreadCrumbItem item = ConstantesBreadCrumb.getItem(id);
				if (item != null) {
					item.setPos(path.size() + 1);
					path.add(item);
				}
			}
		}
	}

	/**
	 * Método encargado de comprobra si el path ya contiene al viewId y en tal
	 * caso truncar la lista
	 *
	 * @param viewId
	 *            identificador de la página a añadir al path
	 * @param path
	 *            actual donde añadir viewId
	 */
	public boolean contiene(List<BreadCrumbItem> path, String viewId) {
		boolean contiene = false;

		if (path != null) {
			final List<BreadCrumbItem> tmp = new ArrayList<BreadCrumbItem>();
			final Iterator<BreadCrumbItem> it = path.iterator();
			while (it.hasNext() && !contiene) {
				final BreadCrumbItem item = it.next();
				tmp.add(item);
				contiene = (item.getViewId().equalsIgnoreCase(viewId));
			}

			if (contiene) {
				log.info("[BreadCrumbAction] El path ya contenía a " + viewId);
				path.clear();
				path.addAll(tmp);
			}
		}

		return contiene;
	}

	/**
	 * Método encargado de registrar en el path existente la actual vista 1.
	 * Compruebo si en SessionManager hay ya una lista de BreadCrumbItem (path)
	 * 2. Si no existe en SessionManager invoco al método createBreadCrumb() y
	 * termino 3. Obtengo el viewId de la página actual accediendo a la
	 * instancia actual de Facecontext (FaceContext.getCurrentInstance). Si es
	 * vacío o nulo, termino 4. Añado a path los item asociados a viewId
	 * invocando a addItems() 5. Guardo en SessionManager el path
	 *
	 * @return cadena vacía
	 */
	public String getInsertarPath() {
		List<BreadCrumbItem> path = null;
		SessionManager sessionManager = getSessionManager();
		try {
			if (sessionManager != null) {
				path = (List<BreadCrumbItem>) sessionManager
						.getAttribute(Constants.BREADCRUMB_SESSION_PARAM);
				if (path == null) {
					path = createBreadCrumb(sessionManager);
				} else {
					final String viewId = getFacesContext()
							.getCurrentInstance().getViewRoot().getViewId();
					if (viewId != null && !viewId.equals("")) {
						addItems(viewId, path);
					}
					sessionManager.setAttribute(
							Constants.BREADCRUMB_SESSION_PARAM, path, false);
				}
			}
		} catch (final Exception e) {
			log.error("Error al insertar un item en el breadcrumb...", e);
		}
		return "";
	}

	/**
	 * Método encargado de limpiar el path actual
	 *
	 * @return cadena vacía
	 */
	public String getLimpiarPath() {
		try {
			SessionManager sessionManager = getSessionManager();
			if (sessionManager != null) {
				sessionManager
						.removeAttribute(Constants.BREADCRUMB_SESSION_PARAM);
			}
		} catch (final Exception e) {
			log.error("Error al vaciar el breadcrumb...", e);
		}
		return "";
	}

	/**
	 * Busca el path completo sin utilizar la sesion.
	 *
	 * @return
	 */
	public List<BreadCrumbItem> getPathEstatico() {
		List<BreadCrumbItem> res = new ArrayList<BreadCrumbItem>();
		try {
			SessionManager sessionManager = getSessionManager();
			if (sessionManager != null) {
				final String viewId = getFacesContext().getCurrentInstance()
						.getViewRoot().getViewId();
				if (viewId != null && !viewId.equals("")) {
					addItems(viewId, res);
				}
			}
		} catch (final Exception e) {
			log.error("Error al obtener el breadcrumb...", e);
			res = new ArrayList<BreadCrumbItem>();
		}
		return res;
	}
}
