package org.librae.adminconfig.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.webapp.bean.IntervaloHorario;
import org.librae.adminconfig.webapp.delegate.IHorarioIntSearchDelegate;
import org.librae.adminconfig.webapp.form.BibliotecaForm;
import org.librae.adminconfig.webapp.form.HorarioForm;
import org.librae.common.Constants;
import org.librae.common.exception.LibraeException;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.DateUtil;

import org.librae.common.webapp.action.SessionSearchAction;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.WindowMessages;

/**
 *Action-JSF asociado al formulario de la creación de horarios y intervalos de
 * festivos. Debido a que se trata de una pantalla auxiliar a una pantalla
 * principal (edicion de bibliotecas) sus datos no deben guardarse en BD hasta
 * que se confirme la pantalla principal. De manera que esta pantalla requiere
 * que exista en session un posible objeto HorarioForm con los datos de una
 * posible edicion de un calendario existente. Su método save actualizará este
 * objeto de session con nombre Constants.HORARIO_SESSION_NAME
 * 
 * @author aropero
 */
public class HorarioIntListAction extends SessionSearchAction implements
        Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6157859925358439189L;

    /** formulario de datos de Horario */
    private HorarioForm       formHorario      = new HorarioForm();
    private String            key;
    private Integer           pos              = -1;

    /**
     * Constructor.
     */
    public HorarioIntListAction() {
        super();
    }

    /**
     * Método invocado cuando se entra en la pantalla de horario. Se encargará
     * de obtener de session el posible objeto HorarioForm para inicializar los
     * datos
     * 
     * @return cadena vacia
     */
    public String getInit() {
        final SessionManager sesion = getSessionManager();
        if (sesion != null
                && sesion.getAttribute(Constants.HORARIO_SESSION_NAME) != null) {
            formHorario = (HorarioForm) sesion
                    .getAttribute(Constants.HORARIO_SESSION_NAME);
            final List<IntervaloHorario> intervalos = (List<IntervaloHorario>) getSessionManagerParam("intervalos");
            if (formHorario.getIdHorario() != null) {
                /* Horario en BBDD */
                if ((intervalos != null)
                        && ((intervalos.size() > 0))) {
                    /* Modificar */
                    sesion.setAttribute(getIdListado(), intervalos);
                } else {
                    buscar();
                }
            } else {
                /* Horario en session */
                final Biblioteca biblioteca = (Biblioteca) sesion
                        .getAttribute(Constants.BIBLIOTECA_HORARIO_SESSION_NAME);
                if (biblioteca != null) {
                    if (biblioteca.getHorario() != null) {
                        /* Modificamos horario */
                        formHorario.setIdHorario(biblioteca.getHorario()
                                .getId());
                        formHorario = ((IHorarioIntSearchDelegate) getDelegate())
                                .prepararDatosVista(formHorario, biblioteca
                                        .getCodigo());
                        buscar();
                    } else {
                        /* Creamos horario */
                        if (biblioteca != null) {
                            formHorario.setBiblioteca(biblioteca.getCodigo());
                        }
                        formHorario.setFechaActualizacion(new Date());
                        sesion.setAttribute(Constants.HORARIO_SESSION_NAME,
                                formHorario);
//                        if (intervalos != null) {
//                            sesion.setAttribute(getIdListado(), intervalos);
//                        }
                    }
                }
            }
            if (sesion!=null) {
                final BibliotecaForm formularioBiblioteca = (BibliotecaForm) sesion
                    .getAttribute(Constants.BIBLIOTECA_SESSION_NAME);
                formHorario.setBiblioteca(formularioBiblioteca.getNombre());
                sesion.removeAttribute("intervalos");
                sesion.removeAttribute(Constants.HORARIOINT_SESSION_NAME);
            }
        }
        return "";
    }

    /**
     * Método encargado de obtener el listado de entidades según unos criterios
     * de busqueda recogidos de session.
     * 
     * @return pagina de vuelta rule "search"
     */
    public String buscar() {
        final WindowMessages wm = new WindowMessages();

        try {
            if ((getSessionManager() != null && getSessionManagerParam(Constants.LISTADO_HORARIOS_ACT) != null)) {

                setSessionManagerParam(
                        getIdListado(),
                        (getSessionManagerParam(Constants.LISTADO_HORARIOS_ACT)));
            } else {
                final Map<String, Object> filtros = new HashMap<String, Object>();
                final Biblioteca biblioteca = (Biblioteca) getSessionManagerParam(Constants.BIBLIOTECA_HORARIO_SESSION_NAME);

                if ((biblioteca != null) && (biblioteca.getHorario() != null)) {
                    filtros.put(HorarioInt.PROPTY_NAME_HORARIO, biblioteca
                            .getHorario().getId());
                }

                List<HorarioInt> lista = (List<HorarioInt>) getSessionManagerParam(getIdListado());

                lista = ((IHorarioIntSearchDelegate) getDelegate())
                        .buscar(filtros);

                if (lista == null) {
                    lista = new ArrayList<HorarioInt>();
                }

                setSessionManagerParam(getIdListado(), lista);
                setSessionManagerParam(Constants.LISTADO_HORARIOS_ACT, lista);
            }

            setSessionManagerParam(Constants.HORARIO_SESSION_NAME, formHorario);

        } catch (final LibraeException e) {
            log.info("Error al buscar...", e);
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("Error al buscar...", e);
            wm.addErrorMessage("ERROR_BUSQUEDA_HORARIO");
            setWindowMessages(wm);
        }
        return null;
    }

    /**
     * Método encargado de crear/modificar un horario
     * 
     * @return String, con el identificador de navigation rule (biblioteca)
     */
    public String aceptar() {
        String res = null;

        if (formHorario.getIdHorario() == null) {
            if (getSessionManager() != null
                    && getSessionManagerParam(Constants.HORARIO_SESSION_NAME) != null) {
                formHorario = (HorarioForm) getSessionManagerParam(Constants.HORARIO_SESSION_NAME);
            }
        }

        /* Pagina de vuelta */
        if (formHorario.getNavigationRuleBack() != null) {
            res = formHorario.getNavigationRuleBack();
        }

        /*
         * Metemos el formulario de Horario en session para la vuelta a
         * Biblioteca
         */
        setSessionManagerParam(Constants.HORARIO_SESSION_NAME, formHorario);

        // Se guarda en session el horario de la biblioteca.
        final BibliotecaForm bibliotecaForm = (BibliotecaForm) getSessionManagerParam(Constants.BIBLIOTECA_SESSION_NAME);
        bibliotecaForm.setFechaActualizacionH(DateUtil.getCurrentDate());
        setSessionManagerParam(Constants.BIBLIOTECA_SESSION_NAME,
                bibliotecaForm);

        /*
         * Borramos la Biblioteca de session.
         */
        removeSessionManagerParam(Constants.BIBLIOTECA_HORARIO_SESSION_NAME);

        return res;
    }

    /**
     * Método invocado al pinchar en cancelar devuelve la regla de navegacion
     * que la pantalla llamante haya especificado en
     * HorarioForm.navigationRuleBack
     * 
     * @return String, con el identificador de navigation rule (biblioteca)
     */
    public String cancel() {
        String res = null;

        /* Pagina de vuelta */
        if (formHorario.getNavigationRuleBack() != null) {
            res = formHorario.getNavigationRuleBack();
        }

        return res;
    }

    protected Map<String, Object> completarFormPdf() {
        final Map<String, Object> filtrosPdf = new LinkedHashMap<String, Object>();
        final HorarioForm horarioForm = (HorarioForm) getSessionManager()
                .getAttribute(Constants.HORARIO_SESSION_NAME);

        if (horarioForm != null) {
            if (horarioForm.getBiblioteca() != null) {
                filtrosPdf.put(ComboLocaleManager.get("horario.bilioteca"),
                        horarioForm.getBiblioteca());
            }
            if (horarioForm.getFechaActualizacion() != null) {
                final String fecha = DateUtil.convertDateToString(horarioForm
                        .getFechaActualizacion());
                filtrosPdf.put(ComboLocaleManager
                        .get("horario.fechaActualizacion"), fecha);
            }
        }

        return filtrosPdf;
    }

    // =================== getter & setter ===================
    public HorarioForm getFormHorario() {
        return formHorario;
    }

    public void setFormHorario(final HorarioForm formHorario) {
        this.formHorario = formHorario;
    }

    public String getKey() {
        return ((key == null) ? (String) getSessionManagerParam("valor_codigo_key")
                : key);
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public Integer getPos() {
        pos = pos + 1;
        return pos;
    }

    public void setPos(final Integer pos) {
        this.pos = pos;
    }
}
