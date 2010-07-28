package org.librae.common.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.model.SelectItem;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.librae.common.Constants;
import org.librae.common.exception.LibraeException;
import org.librae.common.util.ReflectionUtil;
import org.librae.common.webapp.delegate.ISearchDelegate;
import org.librae.common.webapp.form.ISearchForm;
import org.librae.common.webapp.session.SessionManager;
import org.librae.common.webapp.util.ExportUtils;
import org.librae.common.webapp.util.WindowMessages;

public class BaseSearchPage extends BasePage implements Serializable {

    private static final long serialVersionUID         = -3132519096602670177L;

    private ISearchForm       form                     = null;
    private String            nameForm                 = null;
    private ISearchDelegate   delegate                 = null;
    protected List            listado;
    protected String          sortColumn;
    protected boolean         ascending;
    protected boolean         buscando                 = false;
    private Boolean           selectedAll              = false;
    private List<SelectItem>  listadoIndicesTbl        = new ArrayList<SelectItem>();
    private Boolean           radio                    = Boolean.FALSE;
    private Boolean           mostrarAlertSinResultado = Boolean.FALSE;

    // identificador del listado
    private String            idListado;

    private Integer           currentPage              = 1;
    private int               totalBusqueda            = 0;
    private int               numeroPaginas;
    private Integer           pageSize                 = 20;

    /**
     * numero máximo de páginas que se muestran simultanemante en la botonera de
     * paginación
     */
    private int               ventana                  = 11;

    /**
     * Obtiene el numero de paginas.
     * 
     * @return
     */
    public int getNumeroPaginas() {
        numeroPaginas = totalBusqueda / getPageSize();
        if ((totalBusqueda % getPageSize()) != 0) {
            numeroPaginas++;
        }

        return numeroPaginas;
    }

    /**
     * Obtiene el numero de paginas.
     * 
     * @return
     */
    public List getPaginas() {
        final List res = new ArrayList();
        for (int i = 1; i <= numeroPaginas; i++) {
            res.add(Integer.toString(i));
        }
        return res;
    }

    /**
     * Devuelve un listado con las páginas visibles, por si la paginación
     * muestra demasiadas páginas.
     * 
     * @return
     */
    public List getPaginasVentana() {
        List result;

        if (numeroPaginas <= ventana) {
            result = getPaginas();
        } else {
            final List res = new ArrayList();
            int ini = 1;
            int fin = numeroPaginas;

            if ((currentPage - (new Double(ventana / 2).intValue())) > 2) {
                ini = (currentPage - (new Double(ventana / 2).intValue()));
            }

            if ((currentPage + (new Double(ventana / 2).intValue())) < numeroPaginas - 1) {
                fin = (currentPage + (new Double(ventana / 2).intValue()));
            }

            if (ini == 1) {
                if (ventana < numeroPaginas) {
                    fin = ventana;
                } else {
                    fin = numeroPaginas;
                }
            }

            if (fin == numeroPaginas) {
                if (ventana < numeroPaginas) {
                    ini = numeroPaginas - ventana;
                } else {
                    ini = 1;
                }
            }

            for (int i = ini; i <= fin; i++) {
                res.add(Integer.toString(i));
            }
            result = res;
        }

        return result;

    }

    public Boolean getPintarPrimero() {
        return new Boolean(
                (currentPage - (new Double(ventana / 2).intValue())) > 2);
    }

    public Boolean getPintarUltimo() {
        return new Boolean(
                (currentPage + (new Double(ventana / 2).intValue())) < numeroPaginas - 1);
    }

    /**
     * Cambia el tamaño de la pagina.
     */
    public void cambiarPageSize() {
        currentPage = 1;
        if (pageSize < 0) {
            pageSize = totalBusqueda + 1;
        }
    }

    public BaseSearchPage() {
        setSortColumn("id");
    }

    /**
     * Obtiene el listado
     * 
     * @return listado que se desea buscar.
     */
    public List getListado() {
        final WindowMessages wm = new WindowMessages();
        final Map<String, Object> filtros = obtenerFiltros(true);
        try {
            if (filtros != null) {
                listado = delegate.buscar(filtros);
                totalBusqueda = (Integer) filtros
                        .get(Constants.RESULTADO_BUSQUEDA);
                if ((radio) && (listado != null)) {
                    for (int i = 0; i < listado.size(); i++) {
                        listadoIndicesTbl.add(new SelectItem(String.valueOf(i),
                                ""));
                    }
                }
                if ((totalBusqueda == 0) && (getMostrarAlertSinResultado())) {
                    wm.addInfoMessageByCode("ERROR_NINGUN_RESULTADO");
                    setWindowMessages(wm);
                }
            }
        } catch (final Exception e) {
            log.error("Error al buscar...", e);
            wm.addErrorMessageByCode("ERROR_BUSCAR");
            setWindowMessages(wm);
        }

        marcarSeleccionados(listado);

        return listado;
    }

    /**
     * Metodo encargado de consultar los check marcados anteriormente para para
     * indicarlo en la lista a mostrar
     * 
     * @param listado
     */
    protected void marcarSeleccionados(final List listado) {
        if (listado != null) {
            final Iterator it = listado.iterator();
            while (it.hasNext()) {
                final Object o = it.next();
                final Object id = super.ejecutarMetodoReflexion("getId", o);
                if (id != null) {
                    try {
                        final Boolean b = comprobarMarcado(id.toString());
                        final Class[] clase = { Class
                                .forName("java.lang.Boolean") };
                        final Object[] argumento = { b };
                        super.ejecutarMetodoReflexion("setSelected", o, clase,
                                argumento);
                    } catch (final Exception e) {
                        log.error("Error al marcarSeleccionados...", e);
                    }
                }
            }
        }
    }

    /**
     * Metodo encargado de comprobar si todos los elementos de la página actual
     * estan marcados
     * 
     * @return Boolean, true si estan marcados todos, false en caso contrario.
     */
    public Boolean getSelectedPage() {
        Boolean res = Boolean.TRUE;
        if (listado == null) {
            res = Boolean.FALSE;
        } else {
            if (listado.isEmpty()) {
                res = Boolean.FALSE;
            }
            final Iterator it = listado.iterator();
            while (it.hasNext() && res.booleanValue()) {
                final Object o = it.next();
                final Object id = super.ejecutarMetodoReflexion("getId", o);
                if (id != null) {
                    try {
                        res = comprobarMarcado(id.toString());
                    } catch (final Exception e) {
                        log.error("Error al getSelectedPage...", e);
                    }
                }
            }
        }
        return res;
    }

    /**
     * Método encargado de obtener el listado de entidades según unos criterios
     * de busqueda establecidos por el usuario.
     * 
     * @return pagina de vuelta rule "search"
     */
    public String buscar() {
        String page = "search";
        try {
            buscando = true;
            Map<String, Object> filtros = new HashMap<String, Object>();
            if (form != null) {
                completarForm();
                filtros = form.toMap();
                filtros.put(Constants.SORTCOLUMN, getSortColumn());
                filtros.put(Constants.ASCENDING, isAscending());
                filtros.put(Constants.PAGESIZE, getPageSize());
                filtros.put(Constants.CURRENTPAGE, getCurrentPage());
            }
            setSessionManagerParam(idListado, filtros);
        } catch (final LibraeException e) {
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("Error al buscar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage("ERROR_BUSCAR");
            setWindowMessages(wm);
        }
        return page;
    }

    /**
     * Limpia el formulario de la sesion y el listado.
     */
    public String limpiar() {
        try {
            listado = new ArrayList();
            // form = (ISearchForm) Class.forName(nameForm).newInstance();
            form = (ISearchForm) Thread.currentThread().getContextClassLoader()
                    .loadClass(nameForm).newInstance();
            final SessionManager session = getSessionManager();
            if (session != null) {
                session.removeAttribute(idListado);
                final StringBuilder seleccion = new StringBuilder("seleccion_");
                seleccion.append(getIdListado());
                final StringBuilder seleccionTodos = new StringBuilder(
                        "seleccionTodos_");
                seleccionTodos.append(getIdListado());
                session.removeAttribute(seleccion.toString());
                session.removeAttribute(seleccionTodos.toString());
            }
            setCurrentPage(1);
            setTotalBusqueda(0);
        } catch (final Exception e) {
            log.error("Error al limpiar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage("ERROR_LIMPIAR");
            setWindowMessages(wm);
        }
        return Constants.PAGE_REFRESH;
    }
    
    /**
     * Limpia el formulario de la sesion y el listado.
     */
    public String limpiarListado() {
        try {
            listado = new ArrayList();
            // form = (ISearchForm) Class.forName(nameForm).newInstance();
            form = (ISearchForm) Thread.currentThread().getContextClassLoader()
                    .loadClass(nameForm).newInstance();
            final SessionManager session = getSessionManager();
            if (session != null) {
                session.removeAttribute(idListado);
            }
            setCurrentPage(1);
            setTotalBusqueda(0);
        } catch (final Exception e) {
            log.error("Error al limpiar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage("ERROR_LIMPIAR");
            setWindowMessages(wm);
        }
        return Constants.PAGE_REFRESH;
    }

    /**
     * Limpia el formulario de la sesion y el listado.
     */
    public String limpiarRecargando() {
        limpiar();
        return Constants.PAGE_REFRESH;
    }

    /**
     * Completa las columnas de listados de tipo Checkbox
     * 
     * @return
     */
    protected Map<String, Object> completarColumn() {
        return null;
    }

    /**
     * Completa el formulario con valores del action.
     */
    protected void completarForm() {

    }

    /**
     * Completa el formulario con valores del action.
     */
    protected Map<String, Object> completarFormPdf() {
        return new HashMap<String, Object>();
    }

    /**
     * Método encargado de obtener de sesion los criterios de busqueda del
     * usuario y añadirles la ordenacion establecida por el componente dataTable
     * y la página a mostrar según el parametro addParamSort
     * 
     * @param addParamSort
     *            indica si se debe añadir o no a los cirterios los parametros
     *            Constants.SORTCOLUMN, Constants.ASCENDING, Constants.PAGESIZE,
     *            Constants.CURRENTPAGE
     */
    protected Map<String, Object> obtenerFiltros(final boolean addParamSort) {
        Map<String, Object> filtros = null;

        if (getSessionManager() != null) {
            final Object idListadoObj = getSessionManagerParam(idListado);

            if ((idListadoObj != null) && (idListadoObj instanceof Map)) {
                filtros = (Map<String, Object>) idListadoObj;
            }

        }

        if (filtros != null && addParamSort) {
            filtros.put(Constants.SORTCOLUMN, getSortColumn());
            filtros.put(Constants.ASCENDING, isAscending());
            filtros.put(Constants.PAGESIZE, getPageSize());
            filtros.put(Constants.CURRENTPAGE, getCurrentPage());
        }
        return filtros;
    }

    /**
     * Modifica la pagina actual en caso de que sea necesario.
     * 
     * @param currentPage
     */
    public void setCurrentPage(Integer currentPage) {
        final int paginas = getNumeroPaginas();
        if (currentPage == null) {
            currentPage = new Integer(1);
        } else {
            if (currentPage > 0) {
                this.currentPage = currentPage;
            }
            if (this.currentPage <= 0) {
                this.currentPage = 1;
            }
        }
    }

    /**
     * Metodo encargado de compòrbar si el id se encuntra marcado o no
     * 
     * @param String
     *            id
     * @retrun Boolean
     */
    protected Boolean comprobarMarcado(final String id) {
        Boolean res = Boolean.FALSE;

        final SessionManager sesion = getSessionManager();
        final String idListado = getIdListado();
        Boolean todosSeleccionados = Boolean.FALSE;
        List seleccionados = new ArrayList();
        if (sesion != null) {
            if (sesion.getAttribute("seleccion_" + idListado) != null) {
                final Object sessionAttribute = sesion
                        .getAttribute("seleccion_" + idListado);
                if (sessionAttribute instanceof ArrayList) {
                    seleccionados = (ArrayList) sessionAttribute;
                } else if (sessionAttribute instanceof String) {
                    seleccionados.add(sessionAttribute);
                }

            }
            if (sesion.getAttribute("seleccionTodos_" + idListado) != null) {

                if ("true".equalsIgnoreCase(sesion.getAttribute(
                        "seleccionTodos_" + idListado).toString())) {
                    todosSeleccionados = Boolean.TRUE;
                } else {
                    todosSeleccionados = Boolean.FALSE;
                }
            }
            if (todosSeleccionados.booleanValue() || seleccionados.contains(id)) {
                res = Boolean.TRUE;
            }
        }

        return res;
    }

    /**
     * Metodo encargado de limpiar las seleccionaes del listado marcado o no
     * 
     * @param idListado
     */
    public void limpiarMarcados(final String idListado) {
        final SessionManager sesion = getSessionManager();
        if (sesion != null) {
            sesion.removeAttribute("seleccion_" + idListado);
            sesion.removeAttribute("seleccionTodos_" + idListado);
        }
    }

    /**
     * Metodo utilizado por spring para inyectar el nombre del objeto form y a
     * partir de este crear el formulario y rellenarlo con los posibles
     * criterios anteriores
     * 
     * @param idListado
     */
    public void setNameForm(final String nameForm) {
        this.nameForm = nameForm;
        try {
            limpiarSiVienePorMenu();

            // form = (ISearchForm) Class.forName(nameForm).newInstance();
            form = (ISearchForm) Thread.currentThread().getContextClassLoader()
                    .loadClass(nameForm).newInstance();

            final Map<String, Object> filtros = obtenerFiltros(false);
            form.fromMap(filtros);
            if (filtros != null) {
                if (filtros.get(Constants.SORTCOLUMN) != null) {
                    setSortColumn(filtros.get(Constants.SORTCOLUMN).toString());
                }
                if (filtros.get(Constants.ASCENDING) != null) {
                    setAscending(Boolean.parseBoolean(filtros.get(
                            Constants.ASCENDING).toString()));
                }
                if (filtros.get(Constants.PAGESIZE) != null) {
                    setPageSize(Integer.parseInt(filtros
                            .get(Constants.PAGESIZE).toString()));
                }
            }
        } catch (final Exception e) {
            log.error("Error al setNameForm...", e);
        }

    }

    public void limpiarSiVienePorMenu() {
        final String limpiar = getParameter("limpiar");
        if ("true".equals(limpiar)) {
            removeSessionManagerParam(idListado);
        }
    }

    public void modificarPageSize() {
        setCurrentPage(1);
        Map<String, Object> filtros = null;
        final Object idListadoObj = getSessionManagerParam(idListado);
        if ((idListadoObj != null) && (idListadoObj instanceof Map)) {
            filtros = (Map<String, Object>) idListadoObj;
        }
        if (filtros != null) {
            filtros.put(Constants.PAGESIZE, getPageSize());
            filtros.put(Constants.CURRENTPAGE, getCurrentPage());
            setSessionManagerParam(idListado, filtros);
        }
    }

    public void generarDocumento(String tipo) throws Exception {
        byte[] logo = null;
        String nombreBiblioteca = null;
        Object padre = null;
        Object abuelo = null;
        Object sucursal = null;
        final StringBuilder sb = new StringBuilder();
        final Object usuario = getSessionManagerParam(Constants.USUARIO_SESSION_PARAM);
        if (usuario != null) {
            sucursal = ejecutarMetodoReflexion("getBibliotecaActual", usuario);
        }
        if (sucursal != null) {
            logo = (byte[]) ejecutarMetodoReflexion("getLogotipo", sucursal);
            nombreBiblioteca = (String) ejecutarMetodoReflexion(
                    "getDescripcion", sucursal);
            padre = ReflectionUtil.getBeanTokeniker(sucursal,
                    "padre.descripcion");
            abuelo = ReflectionUtil.getBeanTokeniker(sucursal,
                    "padre.padre.descripcion");
            if (abuelo != null && padre != null) {
                sb.append(abuelo).append(" / ").append(padre);
            } else if (abuelo == null && padre != null) {
                sb.append(padre);
            } else if (padre == null && abuelo != null) {
                sb.append(abuelo);
            } else {
                sb.append(" ");
            }
        }
        final HtmlDataTable dataTable = (HtmlDataTable) findComponentById(
                getFacesContext().getViewRoot(), getIdListado());
        final String url = this.getClass().getResource("/").toString()
                + "../../images/logo_pdf.gif";

        if (Constants.SUFFIX_EXCEL.equals(tipo)) {
            ExportUtils.generateEXCEL(getFacesContext(), getResponse(),
                    getIdListado(), dataTable, getListadoSinPaginacion(),
                    completarFormPdf(), sb.toString(), nombreBiblioteca, logo,
                    completarColumn());
        } else {
            ExportUtils.generatePDF(getFacesContext(), getResponse(),
                    getIdListado(), dataTable, getListadoSinPaginacion(), logo,
                    completarFormPdf(), url, nombreBiblioteca, sb.toString(),
                    completarColumn());
        }
    }

    public void generarExcel() {
        try {
            generarDocumento(Constants.SUFFIX_EXCEL);
        } catch (final LibraeException e) {
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("generarExcel...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode("ERROR_CREAR_EXCEL");
            setWindowMessages(wm);
        }
    }

    public void generarPdf() {
        try {
            generarDocumento(Constants.SUFFIX_PDF);
        } catch (final LibraeException e) {
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("generarPdf...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode("ERROR_CREAR_PDF");
            setWindowMessages(wm);
        }
    }

    public List getListadoSinPaginacion() {
        List listadoSinPaginacion = new ArrayList();
        final Map<String, Object> filtros = obtenerFiltros(true);
        if (filtros != null) {
            filtros.remove(Constants.CURRENTPAGE);
            listadoSinPaginacion = delegate.buscar(filtros);
        }
        return listadoSinPaginacion;
    }

    public UIComponent findComponentById(UIComponent root, String id) {
        UIComponent component = null;
        for (int i = 0; i < root.getChildCount() && component == null; i++) {
            final UIComponent child = root.getChildren().get(i);
            component = findComponentById(child, id);
        }
        if (root.getId() != null) {
            if (component == null && root.getId().equals(id)) {
                component = root;
            }
        }
        return component;
    }

    // Getters && Setters

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getVentana() {
        return ventana;
    }

    public void setVentana(int ventana) {
        this.ventana = ventana;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setNumeroPaginas(final int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String getSortColumn() {
        return sortColumn;
    }

    @Override
    public final void setSortColumn(final String sortColumn) {
        this.sortColumn = sortColumn;
    }

    @Override
    public boolean isAscending() {
        return ascending;
    }

    @Override
    public void setAscending(final boolean ascending) {
        this.ascending = ascending;
    }

    public ISearchDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(final ISearchDelegate delegate) {
        this.delegate = delegate;
    }

    public ISearchForm getForm() {
        return form;
    }

    public void setForm(final ISearchForm form) {
        this.form = form;
    }

    public String getIdListado() {
        return idListado;
    }

    public void setIdListado(final String idListado) {
        this.idListado = idListado;
    }

    public void setListado(final List listado) {
        this.listado = listado;
    }

    public int getTotalBusqueda() {
        return totalBusqueda;
    }

    public void setTotalBusqueda(final int totalBusqueda) {
        this.totalBusqueda = totalBusqueda;
    }

    public void setPaginas(final List paginas) {
        log
                .info("Este setter (setPaginas) no sirve para nada, ya que despues nunca se usa este valor."
                        + paginas);
    }

    public Boolean getSelectedAll() {
        return selectedAll;
    }

    public void setSelectedAll(final Boolean selectedAll) {
        this.selectedAll = selectedAll;
    }

    public void setSelectedPage(final Boolean b) {
        // se ignora
    }

    public String getNameForm() {
        return nameForm;
    }

    public int getInitialRangePage() {
        int resultado = (getCurrentPage() - 1) * getPageSize();
        if (totalBusqueda > 0) {
            resultado++;
        }
        return resultado;
    }

    public int getFinalRangePage() {
        final int valorTeorico = ((getCurrentPage()) * getPageSize());
        int finalRange;

        if (valorTeorico < getTotalBusqueda()) {
            finalRange = valorTeorico;
        } else {
            finalRange = getTotalBusqueda();
        }

        return finalRange;
    }

    /**
     * @return the listadoIndicesTbl
     */
    public List<SelectItem> getListadoIndicesTbl() {
        return listadoIndicesTbl;
    }

    /**
     * @param listadoIndicesTbl
     *            the listadoIndicesTbl to set
     */
    public void setListadoIndicesTbl(List<SelectItem> listadoIndicesTbl) {
        this.listadoIndicesTbl = listadoIndicesTbl;
    }

    /**
     * @return the radio
     */
    public Boolean getRadio() {
        return radio;
    }

    /**
     * @param radio
     *            the radio to set
     */
    public void setRadio(Boolean radio) {
        this.radio = radio;
    }

    /**
     * @return the lista
     */
    public List getLista() {
        return listado;
    }

    /**
     * @return the lista
     */
    public List getListadoEficiente() {
        return listado;
    }

    public Boolean getMostrarAlertSinResultado() {
        return mostrarAlertSinResultado && buscando;
    }

    public void setMostrarAlertSinResultado(Boolean mostrarAlertSinResultado) {
        this.mostrarAlertSinResultado = mostrarAlertSinResultado;
    }

}
