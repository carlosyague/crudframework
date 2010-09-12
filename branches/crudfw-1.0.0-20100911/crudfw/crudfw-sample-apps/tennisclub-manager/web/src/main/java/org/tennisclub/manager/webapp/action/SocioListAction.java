
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;
import java.util.List;

import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.ISocioManager;

/**
 * Action-JSF asociado a los listados de la entidad Socio
 *
 * @author cyague@lcc.uma
 */
public class SocioListAction extends BasePage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7622900639219208079L;
	
	private ISocioManager socioManager;

    public ISocioManager getSocioManager() {
        return this.socioManager;
    }

    public void setSocioManager(ISocioManager socioManager) {    	
        this.socioManager = socioManager;
    }

    public SocioListAction() {
        setSortColumn("id"); // sets the default sort column
    }

    public List getSocioes() {
    	
    	return sort(socioManager.getAll());
    }
}

