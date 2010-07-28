package org.librae.common.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.librae.common.Constants;
import org.librae.common.webapp.delegate.ISearchDelegate;
import org.librae.common.webapp.form.ISearchForm;
import org.librae.common.webapp.util.WindowMessages;

public class HomeBean extends BasePage implements Serializable {

    private static final long serialVersionUID = -3132519096602670177L;

    private ISearchForm       form             = null;
    private ISearchDelegate   delegate         = null;
    private List              listado;
    protected String          sortColumn;
    protected boolean         ascending;

    // identificador del listado
    private String            idListado;

    private int               currentPage      = 0;
    private int               totalBusqueda    = 0;
    private int               numeroPaginas;
    private int               pageSize         = 2;

    // jor
    public int getNumeroPaginas() {
        numeroPaginas = totalBusqueda / getPageSize();
        if ((totalBusqueda % getPageSize()) != 0) {
            numeroPaginas++;
        }

        return numeroPaginas;
    }

    // jor
    public List getPaginas() {
        final List res = new ArrayList();
        for (int i = 1; i <= numeroPaginas; i++) {
            res.add(Integer.toString(i));
        }
        return res;
    }

    public void cambiarPageSize() {
        currentPage = 1;
        if (pageSize < 0) {
            pageSize = totalBusqueda + 1;
        }
    }

    public HomeBean() {
        setSortColumn("id");
    }

    public List getListado() {
        final Map<String, Object> filtros = obtenerFiltros();
        if (filtros != null) {
            // if (!isBusquedaRealizada()) {
            listado = delegate.buscar(filtros);
            totalBusqueda = (Integer) filtros.get(Constants.RESULTADO_BUSQUEDA);
            // setBusquedaRealizada(true);
            // }
        }
        return listado;
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
            Map<String, Object> filtros = new HashMap<String, Object>();
            if (form != null) {
                filtros = form.toMap();
            }
            getSessionManager().setAttribute(idListado, filtros);
        } catch (final Exception e) {
            log.error("Error al buscar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode("org.librae.circulacion.messages",
                    "ERROR_GENERAL_CIRCULACION");
            setWindowMessages(wm);
            page = "prestamoDom";
        }
        return page;
    }

    /**
     * Método encargado de obtener de sesion los criterios de busqueda del
     * usuario y añadirles la ordenacion establecida por el componente dataTable
     * y la página a mostrar
     */
    private Map<String, Object> obtenerFiltros() {
        final HashMap<String, Object> filtros = (HashMap<String, Object>) getSessionManager()
                .getAttribute(idListado);

        if (filtros != null) {
            filtros.put(Constants.SORTCOLUMN, getSortColumn());
            filtros.put(Constants.ASCENDING, isAscending());
            filtros.put(Constants.PAGESIZE, getPageSize());
            filtros.put(Constants.CURRENTPAGE, getCurrentPage());
        }
        return filtros;
    }

    /**
     * Modifica la pagina actual en caso de que sea encesario.
     * 
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        final int paginas = getNumeroPaginas();
        if (currentPage > 0) {
            this.currentPage = currentPage;
        }

        if (currentPage > paginas) {
            this.currentPage = paginas;
        }
    }

    // Getters && Setters

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    final public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public ISearchDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(ISearchDelegate delegate) {
        this.delegate = delegate;
    }

    public ISearchForm getForm() {
        return form;
    }

    public void setForm(ISearchForm form) {
        this.form = form;
    }

    public String getIdListado() {
        return idListado;
    }

    public void setIdListado(String idListado) {
        this.idListado = idListado;
    }

    public void setListado(List listado) {
        this.listado = listado;
    }

    public int getTotalBusqueda() {
        return totalBusqueda;
    }

    public void setTotalBusqueda(int totalBusqueda) {
        this.totalBusqueda = totalBusqueda;
    }

    public void setPaginas(List paginas) {
        log
                .debug("Este setter no sirve para nada, ya que el getter devuleve un valor calculado y no interviene lo establecido en el getter.");
    }

}
