package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.librae.adminconfig.model.HorarioInt;
import org.librae.common.webapp.tabs.SubTab;
import org.librae.common.webapp.tabs.SubTabGroup;
import org.librae.common.webapp.tabs.Tab;
import org.librae.common.webapp.tabs.TabGroup;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.form.ISearchForm;

/**
 * Criteria para las busquedas de usuario.
 * 
 * @author jcisneros
 */
public class HorarioIntCriteriaForm implements ISearchForm, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1367513816013105643L;

    /**
     * Titulo de las pestañas.
     */
    private final TabGroup    tabGroup         = new TabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);
    private final Tab         tabListado       = new Tab(tabGroup,
                                                       "horarioList.tab01Text");

    /**
     * Titulo de las subpestañas
     */
    private final SubTabGroup subTabGroup      = new SubTabGroup(
                                                       MensajesError.PROPERTI_ADMINCONFIG);
    private final SubTab      subTabResults    = new SubTab(subTabGroup,
                                                       "horarioList.resultados");

    /**
     * Identificador del horario.
     */
    private Long              horario          = null;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final HashMap<String, Object> criterios = new HashMap<String, Object>();

        criterios.put(HorarioInt.PROPTY_NAME_HORARIO, horario);

        return criterios;
    }

    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String,
     *      Object>)
     */
    public void fromMap(final Map<String, Object> criterios) {
        // TODO
    }

    // ================= Getters && Setter =================

    public TabGroup getTabGroup() {
        return tabGroup;
    }

    public Tab getTabListado() {
        return tabListado;
    }

    public SubTabGroup getSubTabGroup() {
        return subTabGroup;
    }

    public SubTab getSubTabResults() {
        return subTabResults;
    }

    public Long getHorario() {
        return horario;
    }

    public void setHorario(final Long horario) {
        this.horario = horario;
    }
}
