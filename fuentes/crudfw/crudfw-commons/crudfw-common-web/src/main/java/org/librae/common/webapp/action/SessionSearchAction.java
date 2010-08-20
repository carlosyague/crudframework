package org.librae.common.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.librae.common.Constants;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action-JSF asociado a los listados que no estan en base de datos.
 *
 * @author jcisneros
 */
public class SessionSearchAction extends BaseSearchPage implements Serializable {

    private Integer           pos              = -1;

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6157859925358439189L;

    /**
     * Constructor.
     */
    public SessionSearchAction() {
        super();
    }

    /**
     * Obtiene el listado
     *
     * @return listado que se desea buscar.
     */
    public List getListado() {
        List lista = new ArrayList();
        try {
            final List listadoOrdenar = (List) getSessionManagerParam(getIdListado());
            if (listadoOrdenar != null) {
                if (getSortColumn() != null) {
                    sort(listadoOrdenar);
                }
                lista = listadoOrdenar
                        .subList(
                                (getPageSize() * (getCurrentPage() - 1)),
                                Math
                                        .min(
                                                listadoOrdenar.size(),
                                                ((getPageSize() * (getCurrentPage() - 1)) + getPageSize())));
                setTotalBusqueda(listadoOrdenar.size());
            }
            if ((getRadio()) && (lista != null)) {
                for (int i = 0; i < lista.size(); i++) {
                    getListadoIndicesTbl().add(
                            new SelectItem(String.valueOf(i), ""));
                }
                setRadio(false);
            }
        } catch (final Exception e) {
            log.error("Error al buscar...", e);
            final WindowMessages wm = new WindowMessages();
            wm.addErrorMessageByCode("ERROR_BUSCAR");
            setWindowMessages(wm);
        }

        return lista;
    }

    /**
     * Ordena la lista.
     */
    protected List sort(final List list) {
        Comparator comparator = new BeanComparator(sortColumn,
                new NullComparator(nullsAreHigh));
        if (!ascending) {
            comparator = new ReverseComparator(comparator);
        }
        if (list != null) {
            Collections.sort(list, comparator);
        }
        return list;
    }

    /**
     * Metodo encargado de comprobar si todos los elementos de la página actual
     * estan marcados
     *
     * @return Boolean, true si estan marcados todos, false en caso contrario.
     */
    public Boolean getSelectedPage() {
        Boolean res = Boolean.TRUE;
        final List listado = getListadoEficiente();
        if (listado == null) {
            res = Boolean.FALSE;
        } else {
            if (listado.isEmpty()) {
                res = Boolean.FALSE;
            }
            if (!listado.isEmpty()) {
                final List lista = listado.subList((getCurrentPage() - 1)
                        * getPageSize(), Math.min(getCurrentPage()
                        * getPageSize(), getTotalBusqueda() - 1));
                final Iterator it = lista.iterator();
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
        }
        return res;
    }

    /**
     * Get para la posicion.
     *
     * @return
     */
    public Integer getPos() {
        pos = pos + 1;
        return pos;
    }

    public List getListadoSinPaginacion() {
        final List listadoOrdenar = (List) getSessionManager().getAttribute(
                getIdListado());
        if (listadoOrdenar != null) {
            if (getSortColumn() != null) {
                sort(listadoOrdenar);
            }
        }
        return listadoOrdenar;
    }

    // Getters && Setters

    public void setPos(Integer pos) {
        this.pos = pos;
    }

}
