package org.librae.common.webapp.jsf.listener.selection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.context.ExternalContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.librae.common.webapp.jsf.listener.AbstractPhaseListener;
import org.librae.common.webapp.session.SessionManager;

public class SelectionListPhaseListener extends AbstractPhaseListener implements
        PhaseListener {

    private static final long   serialVersionUID                 = -3172301010540479627L;
    private static final String IDLISTADO                        = "idListado";
    private static final String IDLISTADORADIOBUTTON             = "idListadoRadioButton";
    private static final String IDPARAMETROLOCALIZADORTYPE       = "idParametroLocalizadorType";
    private static final String IDPARAMETROLOCALIZADORSTYLECLASS = "idParametroLocalizadorStyleClass";
    private static final String IDPARAMETROLOCALIZADORHIDDEN     = "idParametroLocalizadorHidden";
    private static final String IDVALORCHECKEADO                 = "idValorChk";
    private static final String IDVALORCHECKEADO2                = "idValorChk2";
    private static final String CHECK                            = "check";
    private static final String CHECKTODOS                       = "checkTodos";
    private static final String IDCHK                            = "idChk";
    private static final String PREFIJO                          = "seleccion_";
    private static final String PREFIJOALL                       = "seleccionTodos_";
    private static final String PREFIJOPARAMETROSESION           = "sesion_";

    /**
     * Metodo encargado de comprobar si la request tiene los parametros
     * indicativos de una llamada ajax de actualizaciond e selecciond e un
     * listado
     *
     * @param event
     */
    public void afterPhase(PhaseEvent event) {
        try {
            final PhaseId phaseId = event.getPhaseId();
            if (phaseId.equals(PhaseId.RESTORE_VIEW)) {
                final ExternalContext ectx = event.getFacesContext()
                        .getExternalContext();
                final String idListado = ectx.getRequestParameterMap().get(
                        IDLISTADO);
                // Evento provocado por el radioButton
                final String idListadoRadioButton = ectx
                        .getRequestParameterMap().get(IDLISTADORADIOBUTTON);
                // Subir parametro a session por POST a traves de la propiedad
                // TYPE(localizador)
                final String idParametroType = ectx.getRequestParameterMap()
                        .get(IDPARAMETROLOCALIZADORTYPE);
                // Subir parametro a session por POST a traves de la propiedad
                // TYPE(localizador)
                final String idParametroStyleClass = ectx
                        .getRequestParameterMap().get(
                                IDPARAMETROLOCALIZADORSTYLECLASS);
                // Subir parametro a session por POST a traves de la propiedad
                // TYPE(localizador)
                final String idParametroHidden = ectx.getRequestParameterMap()
                        .get(IDPARAMETROLOCALIZADORHIDDEN);

                Boolean check = null;
                if (ectx.getRequestParameterMap().get(CHECK) != null) {
                    // cambio check=new
                    // Boolean(ectx.getRequestParameterMap().get(CHECK));
                    if ("true".equalsIgnoreCase(ectx.getRequestParameterMap()
                            .get(CHECK))) {
                        check = Boolean.TRUE;
                    } else {
                        check = Boolean.FALSE;
                    }
                }
                Boolean checkTodos = null;
                if (ectx.getRequestParameterMap().get(CHECKTODOS) != null) {
                    // cambio checkTodos = new
                    // Boolean(ectx.getRequestParameterMap().get(CHECKTODOS));
                    if ("true".equalsIgnoreCase(ectx.getRequestParameterMap()
                            .get(CHECKTODOS))) {
                        checkTodos = Boolean.TRUE;
                    } else {
                        checkTodos = Boolean.FALSE;
                    }
                }

                final String listaId = ectx.getRequestParameterMap().get(IDCHK);

                if (idListado != null && check != null && listaId != null) {
                    actualizarSeleccion(idListado, check, listaId, event);
                }

                if (idListado != null && checkTodos != null) {
                    actualizarSeleccionTodos(idListado, checkTodos, event);
                }

                if (idListadoRadioButton != null && check != null
                        && listaId != null) {
                    actualizarSeleccionRadioButton(idListadoRadioButton, check,
                            listaId, event);
                }

                final String idChecked = ectx.getRequestParameterMap().get(
                        IDVALORCHECKEADO);
                final String idChecked2 = ectx.getRequestParameterMap().get(
                        IDVALORCHECKEADO2);

                if (idParametroType != null && idChecked != null) {
                    subirParametroSession(idParametroType, idChecked, event);
                }

                if (idParametroHidden != null && idChecked != null) {
                    subirParametroSession(idParametroHidden, idChecked, event);
                }

                if (idParametroStyleClass != null && idChecked2 != null) {
                    subirParametroSession(idParametroStyleClass, idChecked2,
                            event);
                }
            }
        } catch (final Exception e) {
            log
                    .error("[SelectionListPhaseListener.afterPhase] Error actualizando seleccion: "
                            + e.getMessage());
        }
    }

    /**
     * Metodo encargado de actualizar la seleccion del listado indicado
     *
     * @param idListado
     *            identificador del listado
     * @param check
     *            true indica que la lista de id ha sido seleccionada false
     *            indica que ha sido deseleccionada
     * @param listaId
     *            lista de identificadores seleccionados puede ser un unico
     *            identificador o bien varios de la forma id_id_....
     * @param event
     */
    private void actualizarSeleccion(String idListado, Boolean check,
            String listaId, PhaseEvent event) {
        try {
            final SessionManager sesion = super.getSessionManager(event);
            if (sesion != null) {
                List seleccionados = new ArrayList();
                if (sesion.getAttribute(PREFIJO + idListado) != null) {
                    seleccionados = (ArrayList) sesion.getAttribute(PREFIJO
                            + idListado);
                }
                final List listaIdentificadores = obtenerListaIdentificadores(listaId);
                final Iterator it = listaIdentificadores.iterator();
                while (it.hasNext()) {
                    final String id = it.next().toString();
                    if (check.booleanValue()) {
                        if (!seleccionados.contains(id)) {
                            seleccionados.add(id);
                        }
                    } else {
                        seleccionados.remove(id);
                    }
                }

                sesion.setAttribute(PREFIJO + idListado, seleccionados);

                // si se actualiza individuclamnete cualquier check eliminamos
                // la posible opcion
                // de seleccionar todos
                actualizarSeleccionTodos(idListado, Boolean.FALSE, event);
            }
        } catch (final Exception e) {
            log
                    .error("[SelectionListPhaseListener.actualizarSeleccion] Error actualizando seleccion: "
                            + e.getMessage());
        }
    }

    /**
     * Metodo encargado de actualizar la seleccion del listado indicado
     *
     * @param idListado
     *            identificador del listado
     * @param check
     *            true indica que la lista de id ha sido seleccionada false
     *            indica que ha sido deseleccionada
     * @param listaId
     *            lista de identificadores seleccionados puede ser un unico
     *            identificador o bien varios de la forma id_id_....
     * @param event
     */
    private void actualizarSeleccionRadioButton(String idListadoRadioButton,
            Boolean check, String listaId, PhaseEvent event) {
        try {
            final SessionManager sesion = super.getSessionManager(event);
            if (sesion != null) {
                String seleccionado = new String();
                if (check.booleanValue()) {

                    seleccionado = listaId;
                }

                sesion.setAttribute(PREFIJO + idListadoRadioButton,
                        seleccionado);

                // si se actualiza individuclamnete cualquier check eliminamos
                // la posible opcion
                // de seleccionar todos
                actualizarSeleccionTodos(idListadoRadioButton, Boolean.FALSE,
                        event);
            }
        } catch (final Exception e) {
            log
                    .error("[SelectionListPhaseListener.actualizarSeleccionRadioButton] Error actualizando seleccion: "
                            + e.getMessage());
        }
    }

    /**
     * Metodo encargado de actualizar el flag de seleccionar todos de sesion
     *
     * @param idListado
     *            identificador del listado
     * @param checkTodos
     *            true indica que se desean seleccionar todos los del listado
     * @param event
     */
    private void actualizarSeleccionTodos(String idListado, Boolean checkTodos,
            PhaseEvent event) {
        try {
            final SessionManager sesion = super.getSessionManager(event);
            if (sesion != null) {
                if (checkTodos) {
                    sesion.setAttribute(PREFIJOALL + idListado, "true");
                } else {
                    sesion.removeAttribute(PREFIJOALL + idListado);
                }
            }
        } catch (final Exception e) {
            log
                    .error("[SelectionListPhaseListener.actualizarSeleccionTodos] Error actualizando seleccion: "
                            + e.getMessage());
        }
    }

    /**
     *
     */
    public void subirParametroSession(String idParametro, String idCheched,
            PhaseEvent event) {
        try {
            final SessionManager sesion = super.getSessionManager(event);
            String paramentro = new String();
            if (sesion != null) {

                sesion.setAttribute(PREFIJOPARAMETROSESION + idParametro,
                        idCheched);

            }
        } catch (final Exception e) {
            log
                    .error("[SelectionListPhaseListener.subirParamentroSession] Error subiendo parámetro: "
                            + e.getMessage());
        }
    }

    public void beforePhase(PhaseEvent pe) {
        // nada
    }

    /**
     * Metodo encargado de obtener una lista de identificaddores a partir del
     * String indicado
     *
     * @param listaId
     *            lista de identificadores seleccionados puede ser un unico
     *            identificador o bien varios de la forma id_id_....
     */
    private List obtenerListaIdentificadores(String listaId) {
        final List res = new ArrayList();

        if (!"".equals(listaId)) {
            if (listaId.indexOf('_') == -1) {
                // representa un solo id
                res.add(listaId);
            } else {
                // representa mas de un id
                final StringTokenizer tokens = new StringTokenizer(listaId, "_");
                while (tokens.hasMoreTokens()) {
                    final String token = tokens.nextToken().toString();
                    if (token != null && !"".equals(token)
                            && !res.contains(token)) {
                        res.add(token);
                    }
                }
            }
        }

        return res;
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
