package es.uma.crudframework.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.uma.crudframework.Constants;
import es.uma.crudframework.webapp.util.BreadCrumbItem;
import es.uma.crudframework.webapp.util.ConstantesBreadCrumb;

/**
 * Clase encargada de obtener el breadCrumb a mostrar al usuario
 */
public class BreadCrumbAction extends BasePage implements Serializable {

    protected final Log       log              = LogFactory
                                                       .getLog(BreadCrumbAction.class);

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

        try {
            if (this.getSession() == null
                    || getSession().getAttribute(
                            Constants.BREADCRUMB_SESSION_PARAM) == null) {
                res = createBreadCrumb();
            } else {
                res = (List<BreadCrumbItem>) getSession().getAttribute(
                        Constants.BREADCRUMB_SESSION_PARAM);
                log.info("[BreadCrumbAction] existe un path en sesion");
            }
        } catch (Exception e) {
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
     * @return List<BreadCrumbItem> con un sólo item de la página actual
     */
    private List<BreadCrumbItem> createBreadCrumb() {
        List<BreadCrumbItem> path = new ArrayList<BreadCrumbItem>();

        try {
            if (getSession() != null) {
                final String viewId = getFacesContext().getCurrentInstance()
                        .getViewRoot().getViewId();
                log
                        .info("[BreadCrumbAction] no existe un path es sesion. se crea con wiewId: "
                                + viewId);
                if (viewId != null && !viewId.equals("")) {
                    addItems(viewId, path);
                }
                getSession().setAttribute(
                        Constants.BREADCRUMB_SESSION_PARAM, path);
            }
        } catch (Exception e) {
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
    private void addItems(String viewId, List<BreadCrumbItem> path) {
        if (!contiene(path, viewId)) {
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
    private boolean contiene(List<BreadCrumbItem> path, String viewId) {
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

        try {
            if (getSession() == null
                    || getSession().getAttribute(
                            Constants.BREADCRUMB_SESSION_PARAM) == null) {
                path = createBreadCrumb();
            } else {
                path = (List<BreadCrumbItem>) getSession().getAttribute(
                        Constants.BREADCRUMB_SESSION_PARAM);

                final String viewId = getFacesContext().getCurrentInstance()
                        .getViewRoot().getViewId();
                if (viewId != null && !viewId.equals("")) {
                    addItems(viewId, path);
                }

                getSession().setAttribute(
                        Constants.BREADCRUMB_SESSION_PARAM, path);
            }
        } catch (Exception e) {
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
            if (getSession() != null) {
                getSession().removeAttribute(
                        Constants.BREADCRUMB_SESSION_PARAM);
            }
        } catch (Exception e) {
            log.error("Error al vaciar el breadcrumb...", e);
        }

        return "";
    }
}
