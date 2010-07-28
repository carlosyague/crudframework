package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Calendario;
import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.Horario;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.service.IBibliotecaManager;
import org.librae.adminconfig.service.ICalendarioManager;
import org.librae.adminconfig.service.ICodigoManager;
import org.librae.adminconfig.service.IDireccionManager;
import org.librae.adminconfig.service.IHorarioIntManager;
import org.librae.adminconfig.service.IHorarioManager;
import org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate;
import org.librae.adminconfig.webapp.form.BibliotecaForm;
import org.librae.adminconfig.webapp.form.CalendarioForm;
import org.librae.adminconfig.webapp.form.DireccionForm;
import org.librae.adminconfig.webapp.form.HorarioForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.ConvertUtil;
import org.librae.common.webapp.delegate.impl.FavoritoDelegateImpl;
import org.springframework.dao.DataAccessException;

/**
 * Delegado para la gestión de bibliotecas.
 * 
 * @author aropero
 */
public class BibliotecaDelegateImpl extends FavoritoDelegateImpl implements
        IBibliotecaDelegate, Serializable {

    /**
     *Serial Version UID
     */
    private static final long  serialVersionUID = 1L;

    protected final Log        log              = LogFactory
                                                        .getLog(BibliotecaDelegateImpl.class);

    /**
     * Manager de biblioteca.
     */
    private IBibliotecaManager bibliotecaManager;

    /**
     * Manager de codigo.
     */
    private ICodigoManager     codigoManager;

    /**
     * Manager de Calendario.
     */
    private ICalendarioManager calendarioManager;

    /**
     * Manager de Direccion.
     */
    private IDireccionManager  direccionManager;

    /** Manager de Horario */
    private IHorarioManager    horarioManager;

    /** Manager de HorarioInt */
    private IHorarioIntManager horarioIntManager;

    /**
     * Método encargado de convertir una lista de tipos de biblioteca en una
     * lista de items para los combos.
     * 
     * @param listaTiposBiblioteca
     * @return List<SelectItem>
     */
    private List<SelectItem> listaTiposBiblioteca(
            final List<TipoBiblioteca> listaTiposBiblioteca) {
        List<SelectItem> tiposBiblioteca = null;
        try {
            tiposBiblioteca = ConvertUtil.convertListItem(listaTiposBiblioteca,
                    "id", "nombre");
        } catch (final Exception e) {
            log.error("Fallo al convertir los tipos de biblioteca... ", e);
        }
        return tiposBiblioteca;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate#eliminar(java.lang.Long,
     *      org.librae.adminconfig.model.Usuario)
     */
    public void eliminar(final Long idBiblioteca, final Usuario usuario) {
        final Biblioteca biblioteca = bibliotecaManager.get(idBiblioteca);

        bibliotecaManager
                .comprobacionBibliotecaAEliminar(idBiblioteca, usuario);

        biblioteca.setDireccion(null);
        biblioteca.setCalendario(null);
        biblioteca.setHorario(null);

        try {
            bibliotecaManager.remove(idBiblioteca);
        } catch (final LibraeException le) {
            throw le;
        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException(dae,
                    MensajesError.PROPERTI_ADMINCONFIG);
        } catch (final Exception e) {
            log
                    .error("[BibliotecaDelegateImpl.eliminar] ERROR eliminando una biblioteca "
                            + e);
            throw ExceptionUtil.crearLibraeException("ERROR_DELETE_BD", e);
        }

    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate#guardarBiblioteca(org.librae.adminconfig.webapp.form.BibliotecaForm,
     *      org.librae.adminconfig.model.Biblioteca,
     *      org.librae.adminconfig.webapp.form.CalendarioForm,
     *      org.librae.adminconfig.webapp.form.HorarioForm,
     *      org.librae.adminconfig.webapp.form.DireccionForm)
     */
    public Biblioteca guardarBiblioteca(final BibliotecaForm form,
            final Biblioteca padre, CalendarioForm calendarioForm,
            final HorarioForm horarioForm, final DireccionForm direccionForm,
            List<HorarioInt> horarios) {
        Biblioteca biblioteca = null;
        try {
            final Biblioteca bibliotecaRepetido = bibliotecaManager
                    .getBibliotecaByCodigo(form.getCodigo());
            if ((bibliotecaRepetido != null)
                    && (!bibliotecaRepetido.getId().equals(
                            form.getIdBiblioteca()))) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_ADMINCONFIG,
                        "ERROR_CODIGO_DUPLICADO_BIBLIOTECA");
            }
            if (form.getIdBiblioteca() == null) {
                /** Estamos creando */
                biblioteca = form.toEntity();
            } else {
                /** Estamos modificando */
                biblioteca = bibliotecaManager.get(form.getIdBiblioteca());
                form.toEntity(biblioteca);
            }
            /** Tipo Biblioteca */
            biblioteca = addTipo(biblioteca, form);
            biblioteca.setPadre(padre);

            /** Calendario de la Biblioteca */
            if (calendarioForm != null) {
                biblioteca.setCalendario(calendarioForm.toEntity());
            } else if (form.getFechaActualizacion() == null) {
                biblioteca.setCalendario(null);
                calendarioForm = new CalendarioForm();
            } else {
                calendarioForm = new CalendarioForm();
            }

            /** Horario de la Biblioteca */
            if (horarioForm != null) {
                Horario horario = null;

                if (horarioForm.getIdHorario() == null) {
                    /** CREAMOS horario */

                    horario = horarioManager.guardarHorario(horarioForm
                            .toEntitySinLista(), horarios);

                } else {
                    /** MODIFICAMOS horario */

                    /** Rescatamos el Horario de BBDD mediante su ID */
                    horario = horarioManager.get(horarioForm.getIdHorario());

                    /** Actualizamos el horario con todos sus intervalos */
                    horario = horarioManager.guardarHorario(horarioForm
                            .toEntitySinLista(horario), horarios);
                }
                biblioteca.setHorario(horario);
            } else if (form.getFechaActualizacionH() == null) {
                biblioteca.setHorario(null);
            }

            final String nodoPrincipal = bibliotecaManager.getNodoPrincipal();
            final Biblioteca bibliotecaNodoPrincipal = bibliotecaManager
                    .getBibliotecaByCodigo(nodoPrincipal);
            if ((bibliotecaNodoPrincipal != null)
                    && (bibliotecaNodoPrincipal.getId().equals(biblioteca
                            .getId()))
                    && (!nodoPrincipal.equals(biblioteca.getCodigo()))) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_ADMINCONFIG,
                        "ERROR_CODIGO_RED_BIBLIOTECA");
            }

            if (direccionForm != null) {
                /** Añadimos la direccion */
                final Biblioteca b = direccionManager.addDireccion(biblioteca,
                        direccionForm.getIdPais(), direccionForm
                                .getIdComAutonoma(), direccionForm
                                .getIdProvincia(), direccionForm
                                .getIdMunicipio(), direccionForm
                                .getIdLocalidad(),
                        direccionForm.getIdTipoVia(), direccionForm
                                .getCodigoPostal(),
                        direccionForm.getEdificio(), direccionForm
                                .getDireccion(), direccionForm.getNumero(),
                        direccionForm.getPiso(), direccionForm.getAclarador());
                biblioteca = bibliotecaManager.saveBiblioteca(b, calendarioForm
                        .getFestivos());
            } else if (form.getDomicilio() == null) {
                biblioteca.setDireccion(null);
                biblioteca = bibliotecaManager.saveBiblioteca(biblioteca,
                        calendarioForm.getFestivos());
            } else {
                biblioteca = bibliotecaManager.saveBiblioteca(biblioteca,
                        calendarioForm.getFestivos());
            }

            /* Valores */
            bibliotecaManager.updateAsignarValCodBiblioteca(form.getValores(),
                    biblioteca);

            return biblioteca;
        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException(
                    "ERROR_GUARDAR_BIBLIOTECA",
                    MensajesError.PROPERTI_ADMINCONFIG, dae);
        }
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate#obtenerBiblioteca(java.lang.Long)
     */
    public Biblioteca obtenerBiblioteca(final Long id) {
        return bibliotecaManager.obtenerBiblioteca(id);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate#prepararDatosVista(org.librae.adminconfig.webapp.form.BibliotecaForm)
     */
    public String prepararDatosVista(final BibliotecaForm form,
            CalendarioForm calendarioForm) {
        final List<TipoBiblioteca> listaTipos = bibliotecaManager
                .obtenerTiposBiblioteca();
        form.setListaTipos(listaTiposBiblioteca(listaTipos));

        Biblioteca biblioteca = null;
        if (form.getIdBiblioteca() != null) {
            biblioteca = bibliotecaManager.get(form.getIdBiblioteca());
            form.fromEntity(biblioteca);
            calendarioForm.fromEntity(biblioteca.getCalendario());
        }

        final Map<String, Object> criterios = new HashMap<String, Object>();
        criterios.put(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA, biblioteca
                .getTipoBiblioteca().getId());
        final List<Codigo> codigos = codigoManager.buscar(criterios);

        final String valores = bibliotecaManager.asignarCodigosValores(codigos,
                biblioteca);

        return valores;

    }

    /**
     * @see org.librae.common.webapp.delegate.ISearchDelegate#buscar(java.util.Map)
     */
    public List<Biblioteca> buscar(final Map<String, Object> criterios) {
        return bibliotecaManager.buscar(criterios);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate#obtenerBibliotecas()
     */
    public List<Biblioteca> obtenerBibliotecas() {
        return bibliotecaManager.getAll();
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate#obtenerTiposBiblioteca()
     */
    public List<TipoBiblioteca> obtenerTiposBiblioteca() {
        return bibliotecaManager.obtenerTiposBiblioteca();
    }

    /**
     * Método que completa la biblioteca con su tipo.
     * 
     * @param biblioteca
     *            , Biblioteca a completar.
     * @param form
     *            , formulario de la biblioteca que contiene el tipo.
     * @return Biblioteca
     */
    private Biblioteca addTipo(final Biblioteca biblioteca,
            final BibliotecaForm form) {

        if (form.getIdTipo() == null) {
            biblioteca.setTipoBiblioteca(null);
        } else {
            final TipoBiblioteca tBiblioteca = bibliotecaManager
                    .obtenerTipoBiblioteca(form.getIdTipo());
            biblioteca.setTipoBiblioteca(tBiblioteca);
        }

        return biblioteca;

    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate#obtenerCalendario(java.lang.Long)
     */
    public Calendario obtenerCalendario(final Long idCalendario) {
        return calendarioManager.get(idCalendario);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IBibliotecaDelegate#validarForm(org.librae.adminconfig.webapp.form.BibliotecaForm,
     *      java.lang.Long)
     */
    public Biblioteca validarForm(final BibliotecaForm formulario,
            final Long nodo, final long sizeMax) {
        final Long desideratas = formulario.getDesideratasDesdeOpac();
        final Long idTipo = formulario.getIdTipo();
        Biblioteca padre = null;
        final Biblioteca bibliotecaPrincipal = bibliotecaManager
                .getBibliotecaPrincipal();
        /*
         * El logtipo debe ser una imagen y no pasar del mega
         */
        if (formulario.getLogotipo() != null) {
            final String tipo = formulario.getLogotipo().getContentType();
            final long size = formulario.getLogotipo().getSize();
            if ((tipo != null)
                    && (tipo.indexOf("image") == -1 || size > sizeMax)) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_ADMINCONFIG,
                        "ERROR_LOGO_NO_VALIDO");
            }
        }

        /*
         * El numero de desideratas debe de ser un valor numerico mayor o igual
         * a cero.
         */
        if ((desideratas.compareTo(new Long(0))) < 0) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG, "DESIDERATA_NO_VALIDO");
        }

        if ((formulario.getIdBiblioteca() == null)
                || (!bibliotecaPrincipal.getId().equals(
                        formulario.getIdBiblioteca()))) {
            final TipoBiblioteca tipoB = bibliotecaManager
                    .obtenerTipoBiblioteca(idTipo);
            if ((nodo == null) && (tipoB.getCodigo().equals("B"))) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_ADMINCONFIG, "ARBOL_GB");
            } else if ((nodo == null) && ((tipoB.getCodigo().equals("S")))) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_ADMINCONFIG, "ARBOL_B");
            } else if ((nodo == null) && ((tipoB.getCodigo().equals("G")))) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_ADMINCONFIG, "ARBOL_GB");
            } else {
                if (nodo != null) {
                    padre = bibliotecaManager.get(nodo);
                    if ((tipoB.getCodigo().equals("B"))
                            && (!padre.getTipoBiblioteca().getCodigo().equals(
                                    "G"))) {
                        throw ExceptionUtil.crearLibraeException(
                                MensajesError.PROPERTI_ADMINCONFIG, "ARBOL_GB");
                    } else if ((tipoB.getCodigo().equals("S"))
                            && (!padre.getTipoBiblioteca().getCodigo().equals(
                                    "B"))) {
                        throw ExceptionUtil.crearLibraeException(
                                MensajesError.PROPERTI_ADMINCONFIG, "ARBOL_B");
                    } else if ((tipoB.getCodigo().equals("G"))
                            && (!padre.getTipoBiblioteca().getCodigo().equals(
                                    "G"))) {
                        throw ExceptionUtil.crearLibraeException(
                                MensajesError.PROPERTI_ADMINCONFIG, "ARBOL_GB");
                    }
                    if (nodo.equals(formulario.getIdBiblioteca())) {
                        throw ExceptionUtil.crearLibraeException(
                                MensajesError.PROPERTI_ADMINCONFIG,
                                "ARBOL_NODO_SI_MISMO");
                    }
                }
            }
        }
        return padre;
    }

    public TipoBiblioteca getTipoBibliotecaByCodigo(String tipoBiblioteca) {
        return bibliotecaManager.getTipoBibliotecaByCodigo(tipoBiblioteca);
    }

    public TipoBiblioteca getTipoBiblioteca(Long idTipoBiblioteca) {
        return bibliotecaManager.getTipoBiblioteca(idTipoBiblioteca);
    }

    public Long getIdBibliotecaByDescripcion(String nodoDescripcionClicked) {
        return bibliotecaManager
                .getIdBibliotecaByDescripcion(nodoDescripcionClicked);
    }

    public Long getIdBibliotecaByCodigo(String nodoDescripcionClicked) {
        return bibliotecaManager
                .getIdBibliotecaByCodigo(nodoDescripcionClicked);
    }

    // ======= getters & setters =======

    public IBibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    public void setBibliotecaManager(final IBibliotecaManager bibliotecaManager) {
        this.bibliotecaManager = bibliotecaManager;
    }

    public ICodigoManager getCodigoManager() {
        return codigoManager;
    }

    public void setCodigoManager(final ICodigoManager codigoManager) {
        this.codigoManager = codigoManager;
    }

    public ICalendarioManager getCalendarioManager() {
        return calendarioManager;
    }

    public void setCalendarioManager(final ICalendarioManager calendarioManager) {
        this.calendarioManager = calendarioManager;
    }

    public IHorarioManager getHorarioManager() {
        return horarioManager;
    }

    public void setHorarioManager(final IHorarioManager horarioManager) {
        this.horarioManager = horarioManager;
    }

    public IHorarioIntManager getHorarioIntManager() {
        return horarioIntManager;
    }

    public void setHorarioIntManager(IHorarioIntManager horarioIntManager) {
        this.horarioIntManager = horarioIntManager;
    }

    public IDireccionManager getDireccionManager() {
        return direccionManager;
    }

    public void setDireccionManager(IDireccionManager direccionManager) {
        this.direccionManager = direccionManager;
    }
}
