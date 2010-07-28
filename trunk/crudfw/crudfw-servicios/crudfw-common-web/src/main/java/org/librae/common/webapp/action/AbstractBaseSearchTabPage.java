package org.librae.common.webapp.action;

import java.util.List;

import org.librae.common.webapp.tabs.SubTab;

/**
 * Action-JSF asociado a los listados de la entidad que tiene una pestaña
 * asociada en la que se debe mostrar el tamaño del listado
 * 
 * @author cyague
 */
public abstract class AbstractBaseSearchTabPage extends BaseSearchPage {

    /**
     * BaseSearchPage is Serializable
     */
    private static final long serialVersionUID = 5322053023549528792L;

    private SubTab            tabListado       = null;

    /**
     * deberá obtener el objeto SubTab de session
     */
    public abstract void setupTab();

    public SubTab getTabListado() {
        log.debug("tabListado=" + tabListado);
        return tabListado;
    }

    public void setTabListado(final SubTab tabListado) {
        log.debug("tabListado=" + tabListado);
        this.tabListado = tabListado;
    }

    @Override
    public List getListado() {

        final List result = super.getListado();

        if (tabListado != null) {
            tabListado.setAssignedList(result);
        }

        return result;
    }

}
