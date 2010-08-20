package org.librae.common.webapp.action;

import java.util.List;

import org.librae.common.webapp.tabs.SubTab;

/**
 * Action-JSF asociado a los listados de la entidad que tiene una pestaña
 * asociada en la que se debe mostrar el tamaño del listado
 * 
 * @author cyague
 */
public abstract class AbstractSessionSearchTabAction extends
        SessionSearchAction {

    /**
     * SessionSearchTabAction is Serializable
     */
    private static final long serialVersionUID = -9165978980891281487L;

    private SubTab            tabListado       = null;

    /**
     * deberá obtener el objeto SubTab de session
     */
    public abstract void setupTab();

    public SubTab getTabListado() {
        return tabListado;
    }

    public void setTabListado(final SubTab tabListado) {
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
