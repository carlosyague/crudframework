package org.librae.adminconfig.service.impl;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.IAsignacionValorCodigoDAO;
import org.librae.adminconfig.dao.IBibliotecaDAO;

import org.librae.adminconfig.dao.IBibliotecaViewDAO;
import org.librae.adminconfig.dao.IDireccionDAO;
import org.librae.adminconfig.dao.IFestivoDAO;
import org.librae.adminconfig.dao.ITipoBibliotecaDAO;
import org.librae.adminconfig.dao.IValorCodigoDAO;
import org.librae.adminconfig.facade.IArboledaFacade;
import org.librae.adminconfig.model.AsignacionValorCodigo;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.BibliotecaView;
import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.Direccion;
import org.librae.adminconfig.model.Festivo;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.ValorCodigo;
import org.librae.adminconfig.service.IBibliotecaManager;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.model.Arbol;
import org.librae.common.service.impl.GenericManagerImpl;
import org.librae.common.util.DateUtil;
import org.librae.common.util.Propiedades;
import org.librae.common.util.ReflectionUtil;

/**
 * Implementación del Manager <br/> DAO: IBibliotecaDAO <br/> Entidad:
 * Biblioteca
 * 
 * @author aropero
 */
public class BibliotecaManagerImpl extends GenericManagerImpl<Biblioteca, Long>
        implements IBibliotecaManager, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3214324015585284861L;
    IBibliotecaDAO            bibliotecaDao;
    IBibliotecaViewDAO        bibliotecaViewDao;
    ITipoBibliotecaDAO        tipoBibliotecaDao;
    IValorCodigoDAO           valorCodigoDao;
    IAsignacionValorCodigoDAO asignacionValorCodigoDao;
    IArboledaFacade           arboledaFacade;
    IFestivoDAO               festivoDao;
    IDireccionDAO             direccionDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public BibliotecaManagerImpl(final IBibliotecaDAO bibliotecaDao) {
        super(bibliotecaDao);
        this.bibliotecaDao = bibliotecaDao;
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#buscar(java.util.Map)
     */
    public List<Biblioteca> buscar(Map<String, Object> criteriosBiblioteca) {
        arboledaFacade.getBibliotecasByCriterios(criteriosBiblioteca);
        return bibliotecaDao.buscar(criteriosBiblioteca);
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#obtenerBiblioteca(java.lang.Long)
     */
    public Biblioteca obtenerBiblioteca(final Long id) {
        return bibliotecaDao.get(id);
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#obtenerTiposBiblioteca()
     */
    public List<TipoBiblioteca> obtenerTiposBiblioteca() {
        return tipoBibliotecaDao.getAll();
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#obtenerTipoBiblioteca(java.lang.Long)
     */
    public TipoBiblioteca obtenerTipoBiblioteca(final Long id) {
        return tipoBibliotecaDao.get(id);
    }

    /**
     * @see org.librae.common.service.ITreeManager#getBibliotecasTipoBiblioteca(java.lang.Long)
     */
    public Map<Long, String> getBibliotecasTipoBiblioteca(
            final Long idBibliotecaPadre) {
        final Map<Long, String> mapa = new HashMap<Long, String>();
        final List<Biblioteca> bibliotecas = bibliotecaDao
                .getBibliotecaByTipoConPadre(
                        Constants.TIPO_BIBLIOTECA_BIBLIOTECA, idBibliotecaPadre);
        for (final Iterator<Biblioteca> iterador = bibliotecas.listIterator(); iterador
                .hasNext();) {
            final Biblioteca biblioteca = iterador.next();
            mapa.put(biblioteca.getId(), biblioteca.getDescripcion());
        }

        return mapa;
    }

    /**
     * @see org.librae.common.service.ITreeManager#getBibliotecasTipoGrupoBiblioteca()
     */
    public Map<Long, String> getBibliotecasTipoGrupoBiblioteca() {
        final Map<Long, String> mapa = new HashMap<Long, String>();
        final List<Biblioteca> bibliotecas = bibliotecaDao
                .getBibliotecaByTipoConPadre(Constants.TIPO_BIBLIOTECA_GRUPO,
                        null);
        for (final Iterator<Biblioteca> iterador = bibliotecas.listIterator(); iterador
                .hasNext();) {
            final Biblioteca biblioteca = iterador.next();
            mapa.put(biblioteca.getId(), biblioteca.getDescripcion());
        }
        return mapa;
    }

    /**
     * @see org.librae.common.service.ITreeManager#getBibliotecasTipoSucursal(java.lang.Long)
     */
    public Map<Long, String> getBibliotecasTipoSucursal(
            final Long idBibliotecaPadre) {
        final Map<Long, String> mapa = new HashMap<Long, String>();
        final List<Biblioteca> bibliotecas = bibliotecaDao
                .getBibliotecaByTipoConPadre(
                        Constants.TIPO_BIBLIOTECA_SUCURSAL, idBibliotecaPadre);
        for (final Iterator<Biblioteca> iterador = bibliotecas.listIterator(); iterador
                .hasNext();) {
            final Biblioteca biblioteca = iterador.next();
            mapa.put(biblioteca.getId(), biblioteca.getDescripcion());
        }
        return mapa;
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#getBibliotecasTipoGrupoBiblioteca(java.lang.Long)
     */
    public List<Biblioteca> getBibliotecasTipoGrupoBiblioteca(
            final Long idUsuario) {
        return bibliotecaDao.getBibliotecaByTipoConUsuario(
                Constants.TIPO_BIBLIOTECA_GRUPO, idUsuario);
    }

    public Biblioteca saveBiblioteca(final Biblioteca biblioteca,
            Date[] festivos) {
        final List<Festivo> listadoFestivos = new ArrayList<Festivo>();
        if ((biblioteca.getCalendario() != null)
                && (biblioteca.getCalendario().getId() != null)) {
            festivoDao.eliminarByIdCalendario(biblioteca.getCalendario()
                    .getId());
            festivoDao.flush();
            biblioteca.getCalendario().setFestivos(new ArrayList<Festivo>());
        }

        if (festivos != null && festivos.length != 0) {
            if (biblioteca.getCalendario().getFestivos() == null) {
                biblioteca.getCalendario()
                        .setFestivos(new ArrayList<Festivo>());
            }
            for (final Date festivo : festivos) {
                final Festivo f = new Festivo(festivo);
                listadoFestivos.add(f);
            }
            biblioteca.getCalendario().setFestivos(listadoFestivos);
        }
        return bibliotecaDao.save(biblioteca);
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#getListBibliotecasTipoBiblioteca(java.lang.Long)
     */
    public List<Biblioteca> getListBibliotecasTipoBiblioteca(
            final Long idUsuario) {
        return bibliotecaDao.getBibliotecaByTipoConUsuario(
                Constants.TIPO_BIBLIOTECA_BIBLIOTECA, idUsuario);
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#getListBibliotecasTipoSucursal(java.lang.Long)
     */
    public List<Biblioteca> getListBibliotecasTipoSucursal(final Long idUsuario) {
        return bibliotecaDao.getBibliotecaByTipoConUsuario(
                Constants.TIPO_BIBLIOTECA_SUCURSAL, idUsuario);
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#getListBibliotecasTipoBibliotecaConPadre(java.lang.Long)
     */
    public List<Biblioteca> getListBibliotecasTipoBibliotecaConPadre(
            final Long idBibliotecaPadre) {
        return bibliotecaDao.getBibliotecaByTipoConPadre(
                Constants.TIPO_BIBLIOTECA_BIBLIOTECA, idBibliotecaPadre);
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#getListBibliotecasTipoSucursalConPadre(java.lang.Long)
     */
    public List<Biblioteca> getListBibliotecasTipoSucursalConPadre(
            final Long idBibliotecaPadre) {
        return bibliotecaDao.getBibliotecaByTipoConPadre(
                Constants.TIPO_BIBLIOTECA_SUCURSAL, idBibliotecaPadre);
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#getBibliotecasByUsuario(java.lang.Long)
     */
    public List<BibliotecaView> getBibliotecasByUsuario(final Long idUsuario) {
        List<BibliotecaView> bibliotecasDescendentes = new ArrayList<BibliotecaView>();
        final List<BibliotecaView> bibliotecas = bibliotecaViewDao
                .getBibliotecasByUsuario(idUsuario);
        bibliotecasDescendentes.addAll(bibliotecas);
        bibliotecasDescendentes = arboledaFacade.descendientesBibliotecas(
                bibliotecasDescendentes, bibliotecaViewDao.getBibliotecas());
        return bibliotecasDescendentes;
    }

    public List<Biblioteca> getBibliotecasByUsuarioSinDescendientes(
            final Long idUsuario) {
        return bibliotecaDao.getBibliotecasByUsuario(idUsuario);
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#getTiposBibliotecaByCodigos(java.lang.String)
     */
    public List<String> getTiposBibliotecaByCodigos(final String aplicaGBS) {
        final List<String> tiposBiblioteca = new ArrayList<String>();
        if (aplicaGBS != null) {
            for (final char tipoBiblioteca : aplicaGBS.toCharArray()) {
                final char[] cadena = { tipoBiblioteca };
                final TipoBiblioteca tipoBibliotecaObject = tipoBibliotecaDao
                        .getTipoBibliotecaByCodigo(new String(cadena));
                tiposBiblioteca.add(tipoBibliotecaObject.getId().toString());
            }
        }
        return tiposBiblioteca;
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#updateAsigarValCodBiblioteca(java.util.List,
     *      org.librae.adminconfig.model.Biblioteca)
     */
    public void updateAsignarValCodBiblioteca(final List<String> valores,
            final Biblioteca biblioteca) {

        /* Eliminamos todos los valores relacionados con la Biblioteca */
        asignacionValorCodigoDao.deleteValoresByBiblioteca(biblioteca.getId());
        /* Procedemos a actualizar los valores de la Biblioteca */
        final Iterator<String> it = valores.iterator();
        while (it.hasNext()) {
            final Long idValor = new Long(it.next());
            if (!idValor.equals(new Long(0))) {
                final ValorCodigo valor = valorCodigoDao.get(new Long(idValor));
                final AsignacionValorCodigo asigVCod = new AsignacionValorCodigo(
                        valor, biblioteca);
                asignacionValorCodigoDao.save(asigVCod);
            }
        }
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#asignarCodigosValores(java.util.List,
     *      org.librae.adminconfig.model.Biblioteca)
     */
    public String asignarCodigosValores(final List<Codigo> codigos,
            final Biblioteca biblioteca) {
        final Map<String, Object> valoresCodigos = new HashMap<String, Object>();
        AsignacionValorCodigo asigVCod = null;
        Long idValor = null;

        final Iterator<Codigo> it = codigos.iterator();
        while (it.hasNext()) {
            final Codigo cod = it.next();
            final Iterator<AsignacionValorCodigo> itAsig = biblioteca
                    .getValores().iterator();
            while (itAsig.hasNext()) {
                asigVCod = itAsig.next();
                if (asigVCod.getValorCodigo().getCodigo().getId().equals(
                        cod.getId())) {
                    idValor = asigVCod.getValorCodigo().getId();
                }
            }

            if (idValor == null) {
                valoresCodigos.put(cod.getId().toString(), new Long(0));
            } else {
                valoresCodigos.put(cod.getId().toString(), idValor);
                idValor = null;
            }
        }

        final String valores = getListadoValoresSelected(valoresCodigos,
                codigos);

        return valores;
    }

    /**
     * Le pasamos asociaciones de codigo-valor y nos devuelve un listado de
     * valores, para meterselos a la pestaña codigos en sus correspondientes
     * valores.
     * 
     * @param codigosValores
     *            , asociaciones codigo-valor.
     * @param codigos
     *            , listado de codigos.
     * @return listado de valores.
     */
    private String getListadoValoresSelected(
            final Map<String, Object> codigosValores, final List<Codigo> codigos) {
        final StringBuilder valores = new StringBuilder();

        for (int i = codigosValores.size() - 1; i > -1; i--) {
            final Long idValor = (Long) codigosValores.get((codigos.get(i))
                    .getId().toString());
            valores.append(idValor.toString() + ":");
        }
        return valores.toString();
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#comprobacionBibliotecaAEliminar(Long,
     *      Usuario)
     */
    public void comprobacionBibliotecaAEliminar(final Long idBiblioteca,
            final Usuario usuario) {

        if (usuario.getBibliotecaActual().getId().equals(idBiblioteca)) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ELIMINAR_BIBLIOTECA_USUARIO_ACT");
        } else if (usuario.getBibliotecaPorDefecto().getId().equals(
                idBiblioteca)) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_ELIMINAR_BIBLIOTECA_USUARIO_DEF");
        }
    }

    /**
     * @see org.librae.adminconfig.service.IHorarioIntManager#actLabSegunCalendario(java.lang.Long)
     */
    public String actLabSegunCalendario(Long idBiblioteca) {
        return bibliotecaDao.actLabSegunCalendario(idBiblioteca);
    }

    /**
     * Obtiene el arbol de bibliotecas.
     * 
     * @return arbol de bibliotecas.
     */
    public Object getTreeData(Class treeNodeBase) throws Exception {
        Object root = null;
        final List<Biblioteca> bibliotecas = bibliotecaDao.getBibliotecas();
        final Biblioteca bibliotecaRed = encontrarRed(bibliotecas);
        final Constructor constructor = ReflectionUtil
                .getConstructorTreeNodeBase4(treeNodeBase);
        root = constructor.newInstance("grupos", bibliotecaRed.getCodigo(),
                bibliotecaRed.getId().toString(), false);
        final List hijos = (List) ReflectionUtil.getBean(root, "children");
        hijos
                .addAll(obtenerHijosNodo(treeNodeBase, bibliotecaRed,
                        bibliotecas));
        return root;
    }

    private List<Object> obtenerHijosNodo(Class treeNodeBase,
            Biblioteca biblioteca, List<Biblioteca> bibliotecas)
            throws Exception {
        final List<Object> listaHijosList = new ArrayList<Object>();
        for (final Biblioteca nodo : bibliotecas) {
            if ((nodo.getPadre() != null)
                    && (biblioteca.getId().equals(nodo.getPadre()))) {
                if (Constants.TIPO_BIBLIOTECA_GRUPO.equals(nodo
                        .getTipoBiblioteca().getCodigo())) {
                    final Constructor constructor = ReflectionUtil
                            .getConstructorTreeNodeBase4(treeNodeBase);
                    final Object grupo = constructor.newInstance("grupos", nodo
                            .getDescripcion(), nodo.getId().toString(), false);
                    final List hijos = (List) ReflectionUtil.getBean(grupo,
                            "children");
                    hijos.addAll(obtenerHijosNodo(treeNodeBase, nodo,
                            bibliotecas));
                    listaHijosList.add(grupo);
                } else if (Constants.TIPO_BIBLIOTECA_BIBLIOTECA.equals(nodo
                        .getTipoBiblioteca().getCodigo())) {
                    final Constructor constructor = ReflectionUtil
                            .getConstructorTreeNodeBase4(treeNodeBase);
                    final Object tipobiblioteca = constructor.newInstance(
                            "biblioteca", nodo.getDescripcion(), nodo.getId()
                                    .toString(), false);
                    final List hijos = (List) ReflectionUtil.getBean(
                            tipobiblioteca, "children");
                    hijos.addAll(obtenerHijosNodo(treeNodeBase, nodo,
                            bibliotecas));
                    listaHijosList.add(tipobiblioteca);
                } else {
                    final Constructor constructor = ReflectionUtil
                            .getConstructorTreeNodeBase4(treeNodeBase);
                    final Object sucursal = constructor.newInstance("sucursal",
                            nodo.getDescripcion(), nodo.getId().toString(),
                            true);
                    final List hijos = (List) ReflectionUtil.getBean(sucursal,
                            "children");
                    listaHijosList.add(sucursal);
                }
            }
        }
        return listaHijosList;
    }

    private Biblioteca encontrarRed(List<Biblioteca> bibliotecasGrupos) {
        for (final Biblioteca nodoBiblioteca : bibliotecasGrupos) {
            if (nodoBiblioteca.getCodigo().equals(getNodoPrincipal())) {
                return nodoBiblioteca;
            }
        }
        return null;
    }

    public String getNodoPrincipal() {
        final Propiedades properties = Propiedades
                .getInstance(Constants.USER_WATCHDOG_PROPERTIES);
        return properties.getPropiedad(Constants.BIBLIOTECA_NODO_PRINCIPAL);
    }

    public Biblioteca getBibliotecaPrincipal() {
        final Biblioteca biblioteca = bibliotecaDao
                .getBibliotecaByCodigo(getNodoPrincipal());
        return biblioteca;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IAutorizarUsuarioDelegate#getTreeData(java.lang.Long)
     */
    public Object getTreeData(Class treeNodeBase, final Long idUsuario,
            String permiso, List<String> codigosBibliotecas) throws Exception {
        Object root = null;
        List<BibliotecaView> bibliotecasAsignadas = null;
        final List<BibliotecaView> bibliotecas = bibliotecaViewDao
                .getBibliotecas();
        final Constructor constructor = ReflectionUtil
                .getConstructorTreeNodeBase3(treeNodeBase);
        root = constructor.newInstance("bibliotecas", "biblioteca", false);
        if (permiso != null) {
            bibliotecasAsignadas = bibliotecaViewDao
                    .getBibliotecasByUsuarioPermiso(idUsuario, permiso);
        } else {
            bibliotecasAsignadas = bibliotecaViewDao
                    .getBibliotecasByUsuario(idUsuario);
        }
        getTreeAsignados(treeNodeBase, root, bibliotecasAsignadas, bibliotecas,
                codigosBibliotecas);
        return root;
    }

    private void getTreeAsignados(Class treeNodeBase, final Object root,
            List<BibliotecaView> bibliotecasAsignadas,
            List<BibliotecaView> bibliotecas, List<String> codigosBibliotecas)
            throws Exception {
        bibliotecasAsignadas = arboledaFacade.descendientesBibliotecas(
                bibliotecasAsignadas, bibliotecas);
        if (!bibliotecasAsignadas.isEmpty()) {
            final List hijos = (List) ReflectionUtil.getBean(root, "children");
            hijos.addAll(obtenerHijosNodoRecursivo(treeNodeBase,
                    bibliotecasAsignadas, codigosBibliotecas));
        }
    }

    private List<Object> obtenerHijosNodoRecursivo(Class treeNodeBase,
            List<BibliotecaView> bibliotecas, List<String> codigosBibliotecas)
            throws Exception {
        final List<Object> listaHijosList = new ArrayList<Object>();
        final List<Arbol> bibliotecasArbol = arboledaFacade
                .getPadreHijos(bibliotecas);
        for (final Arbol nodo : bibliotecasArbol) {
            Boolean tienePadre = false;
            for (final Arbol nodoPadre : bibliotecasArbol) {
                if ((nodo.getPadre() != null)
                        && (nodoPadre.getNodo().equals(nodo.getPadre()))) {
                    tienePadre = true;
                    break;
                }
            }
            if (!tienePadre) {
                final BibliotecaView biblioteca = bibliotecas
                        .get(nodo.getPos());
                if (biblioteca.getTipoBiblioteca().equals(
                        Constants.TIPO_BIBLIOTECA_GRUPO)) {
                    final Constructor constructor = ReflectionUtil
                            .getConstructorTreeNodeBase4(treeNodeBase);
                    final Object tree = constructor.newInstance("grupos",
                            biblioteca.getDescripcion(), biblioteca.getId()
                                    .toString(), false);
                    final List hijos = (List) ReflectionUtil.getBean(tree,
                            "children");
                    hijos.addAll(obtenerHijosNodoRecursivo(treeNodeBase,
                            biblioteca.getId(), bibliotecas, bibliotecasArbol,
                            codigosBibliotecas));
                    listaHijosList.add(tree);
                    codigosBibliotecas.add(biblioteca.getCodigo());
                } else if (biblioteca.getTipoBiblioteca().equals(
                        Constants.TIPO_BIBLIOTECA_BIBLIOTECA)) {
                    final Constructor constructor = ReflectionUtil
                            .getConstructorTreeNodeBase4(treeNodeBase);
                    final Object tipobiblioteca = constructor.newInstance(
                            "biblioteca", biblioteca.getDescripcion(),
                            biblioteca.getId().toString(), false);
                    final List hijos = (List) ReflectionUtil.getBean(
                            tipobiblioteca, "children");
                    hijos.addAll(obtenerHijosNodoRecursivo(treeNodeBase,
                            biblioteca.getId(), bibliotecas, bibliotecasArbol,
                            codigosBibliotecas));
                    listaHijosList.add(tipobiblioteca);
                    codigosBibliotecas.add(biblioteca.getCodigo());
                } else {
                    final Constructor constructor = ReflectionUtil
                            .getConstructorTreeNodeBase4(treeNodeBase);
                    final Object sucursal = constructor.newInstance("sucursal",
                            biblioteca.getDescripcion(), biblioteca.getId()
                                    .toString(), true);
                    listaHijosList.add(sucursal);
                    codigosBibliotecas.add(biblioteca.getCodigo());
                }
            }
        }
        return listaHijosList;
    }

    private List<Object> obtenerHijosNodoRecursivo(Class treeNodeBase, Long id,
            List<BibliotecaView> bibliotecas, List<Arbol> bibliotecasArbol,
            List<String> codigosBibliotecas) throws Exception {
        final List<Object> listaHijosList = new ArrayList<Object>();

        for (final Arbol nodo : bibliotecasArbol) {
            if ((nodo.getPadre() != null) && (id.equals(nodo.getPadre()))) {
                final BibliotecaView biblioteca = bibliotecas
                        .get(nodo.getPos());
                if (biblioteca.getTipoBiblioteca().equals(
                        Constants.TIPO_BIBLIOTECA_GRUPO)) {
                    final Constructor constructor = ReflectionUtil
                            .getConstructorTreeNodeBase4(treeNodeBase);
                    final Object tree = constructor.newInstance("grupos",
                            biblioteca.getDescripcion(), biblioteca.getId()
                                    .toString(), false);
                    final List hijos = (List) ReflectionUtil.getBean(tree,
                            "children");
                    hijos.addAll(obtenerHijosNodoRecursivo(treeNodeBase,
                            biblioteca.getId(), bibliotecas, bibliotecasArbol,
                            codigosBibliotecas));
                    listaHijosList.add(tree);
                    codigosBibliotecas.add(biblioteca.getCodigo());
                } else if (biblioteca.getTipoBiblioteca().equals(
                        Constants.TIPO_BIBLIOTECA_BIBLIOTECA)) {
                    final Constructor constructor = ReflectionUtil
                            .getConstructorTreeNodeBase4(treeNodeBase);
                    final Object tipobiblioteca = constructor.newInstance(
                            "biblioteca", biblioteca.getDescripcion(),
                            biblioteca.getId().toString(), false);
                    final List hijos = (List) ReflectionUtil.getBean(
                            tipobiblioteca, "children");
                    hijos.addAll(obtenerHijosNodoRecursivo(treeNodeBase,
                            biblioteca.getId(), bibliotecas, bibliotecasArbol,
                            codigosBibliotecas));
                    listaHijosList.add(tipobiblioteca);
                    codigosBibliotecas.add(biblioteca.getCodigo());
                } else {
                    final Constructor constructor = ReflectionUtil
                            .getConstructorTreeNodeBase4(treeNodeBase);
                    final Object sucursal = constructor.newInstance("sucursal",
                            biblioteca.getDescripcion(), biblioteca.getId()
                                    .toString(), true);
                    listaHijosList.add(sucursal);
                    codigosBibliotecas.add(biblioteca.getCodigo());
                }
            }
        }
        return listaHijosList;
    }

    /**
     * @see org.librae.adminconfig.service.IBibliotecaManager#getBibliotecaByCodigo(java.lang.String)
     */
    public Biblioteca getBibliotecaByCodigo(String codigo) {
        return bibliotecaDao.getBibliotecaByCodigo(codigo);
    }

    private Object setTreeDataSucursal(Class treeNodeBase, final Object root,
            final Long idUsuario, final List<Biblioteca> cargadas)
            throws Exception {
        final List<Biblioteca> sucursales = getListBibliotecasTipoSucursal(idUsuario);
        return setTreeDataSucursal(treeNodeBase, root, sucursales, cargadas);
    }

    /**
     * Para las sucursales.
     * 
     * @param root
     * @param idUsuario
     * @return
     */
    private Object setTreeDataSucursal(Class treeNodeBase, final Object root,
            final List<Biblioteca> sucursales, final List<Biblioteca> cargadas)
            throws Exception {
        for (final Biblioteca sucursal : sucursales) {
            if (!cargadas.contains(sucursal)) {

                final Constructor constructorSucursal = ReflectionUtil
                        .getConstructorTreeNodeBase4(treeNodeBase);
                final Object sucursalNode = constructorSucursal.newInstance(
                        "sucursal", sucursal.getDescripcion(), sucursal.getId()
                                .toString(), true);

                final Constructor constructorBiblioteca = ReflectionUtil
                        .getConstructorTreeNodeBase4(treeNodeBase);
                final Object bibliotecaNode = constructorBiblioteca
                        .newInstance("biblioteca", sucursal.getPadre()
                                .getDescripcion(), sucursal.getPadre().getId()
                                .toString(), false);

                final Constructor constructorGrupos = ReflectionUtil
                        .getConstructorTreeNodeBase4(treeNodeBase);
                final Object grupoNode = constructorGrupos.newInstance(
                        "grupos", sucursal.getPadre().getPadre()
                                .getDescripcion(), sucursal.getPadre()
                                .getPadre().getId().toString(), false);

                Object nodoYaInsertado = insertarEnArbol(treeNodeBase, root,
                        grupoNode);
                if (nodoYaInsertado == null) {
                    nodoYaInsertado = insertarEnArbol(treeNodeBase, grupoNode,
                            bibliotecaNode);
                } else {
                    nodoYaInsertado = insertarEnArbol(treeNodeBase,
                            nodoYaInsertado, bibliotecaNode);
                }
                if (nodoYaInsertado == null) {
                    insertarEnArbol(treeNodeBase, bibliotecaNode, sucursalNode);
                } else {
                    insertarEnArbol(treeNodeBase, nodoYaInsertado, sucursalNode);
                }
            }
        }
        return root;
    }

    /**
     * Inserta un nodo en el hijo, si esta en el padre ya devuelve el nodo.
     * 
     * @param nodoPadre
     * @param nodoHijo
     * @return
     */
    public Object insertarEnArbol(Class treeNodeBase, final Object nodoPadre,
            final Object nodoHijo) throws Exception {
        Object nodoEncontrado = null;
        boolean encontrado = false;
        final List listaNodosHijos = (List) ReflectionUtil.getBean(nodoPadre,
                "children");
        for (final Object nodo : listaNodosHijos) {
            final String identifier = (String) ReflectionUtil.getBean(nodoHijo,
                    "identifier");
            final String identifierNodo = (String) ReflectionUtil.getBean(nodo,
                    "identifier");
            if (identifier.equals(identifierNodo)) {
                encontrado = true;
                nodoEncontrado = nodo;
            }
        }
        if (!encontrado) {
            final List hijos = (List) ReflectionUtil.getBean(nodoPadre,
                    "children");
            hijos.add(nodoHijo);
        }
        return nodoEncontrado;
    }

    public TipoBiblioteca getTipoBibliotecaByCodigo(String tipoBiblioteca) {
        return tipoBibliotecaDao.getTipoBibliotecaByCodigo(tipoBiblioteca);
    }

    public TipoBiblioteca getTipoBiblioteca(Long idTipoBiblioteca) {
        return tipoBibliotecaDao.get(idTipoBiblioteca);
    }

    public Direccion getDireccionByIdBiblioteca(Long idBiblioteca) {
        return direccionDao.getDireccionByIdBiblioteca(idBiblioteca);
    }

    public Long getIdBibliotecaByDescripcion(String nodoDescripcionClicked) {
        return bibliotecaDao
                .getIdBibliotecaByDescripcion(nodoDescripcionClicked);
    }

    public Long getIdBibliotecaByCodigo(String nodoDescripcionClicked) {
        return bibliotecaDao.getIdBibliotecaByCodigo(nodoDescripcionClicked);
    }

    // Getters && Setters

    public IBibliotecaDAO getBibliotecaDao() {
        return bibliotecaDao;
    }

    public void setBibliotecaDao(final IBibliotecaDAO bibliotecaDao) {
        this.bibliotecaDao = bibliotecaDao;
    }

    public ITipoBibliotecaDAO getTipoBibliotecaDao() {
        return tipoBibliotecaDao;
    }

    public void setTipoBibliotecaDao(final ITipoBibliotecaDAO tipoBibliotecaDao) {
        this.tipoBibliotecaDao = tipoBibliotecaDao;
    }

    public IValorCodigoDAO getValorCodigoDao() {
        return valorCodigoDao;
    }

    public void setValorCodigoDao(final IValorCodigoDAO valorCodigoDao) {
        this.valorCodigoDao = valorCodigoDao;
    }

    public IAsignacionValorCodigoDAO getAsignacionValorCodigoDao() {
        return asignacionValorCodigoDao;
    }

    public void setAsignacionValorCodigoDao(
            final IAsignacionValorCodigoDAO asignacionValorCodigoDao) {
        this.asignacionValorCodigoDao = asignacionValorCodigoDao;
    }

    /**
     * @return the arboledaFacade
     */
    public IArboledaFacade getArboledaFacade() {
        return arboledaFacade;
    }

    /**
     * @param arboledaFacade
     *            the arboledaFacade to set
     */
    public void setArboledaFacade(IArboledaFacade arboledaFacade) {
        this.arboledaFacade = arboledaFacade;
    }

    /**
     * @return the festivoDao
     */
    public IFestivoDAO getFestivoDao() {
        return festivoDao;
    }

    /**
     * @param festivoDao
     *            the festivoDao to set
     */
    public void setFestivoDao(IFestivoDAO festivoDao) {
        this.festivoDao = festivoDao;
    }

    public IDireccionDAO getDireccionDao() {
        return direccionDao;
    }

    public void setDireccionDao(IDireccionDAO direccionDao) {
        this.direccionDao = direccionDao;
    }

    public IBibliotecaViewDAO getBibliotecaViewDao() {
        return bibliotecaViewDao;
    }

    public void setBibliotecaViewDao(IBibliotecaViewDAO bibliotecaViewDao) {
        this.bibliotecaViewDao = bibliotecaViewDao;
    }

}