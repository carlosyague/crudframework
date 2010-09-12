
package org.tennisclub.manager.webapp.action;

import java.io.Serializable;

import org.tennisclub.manager.model.Socio;
import es.uma.crudframework.webapp.action.BasePage;
import org.tennisclub.manager.service.ISocioManager;

/**
 * Action-JSF asociado a los formularios de la entidad Socio
 *
 * @author cyague@lcc.uma
 */
public class SocioFormAction extends BasePage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9008254563952236551L;
	
	private ISocioManager socioManager;
    private Socio socio = new Socio();
    private Long id;

    // getter & setter del manager

    public ISocioManager getSocioManager() {
        return this.socioManager;
    }

    public void setSocioManager(ISocioManager socioManager) {
        this.socioManager = socioManager;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        socioManager.remove(socio.getId());
        addMessage("socio.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            socio = socioManager.get(id);
        } else {
            socio = new Socio();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (socio.getId() == null || socio.getId() == 0);
        socioManager.save(socio);

        String key = (isNew) ? "socio.added" : "socio.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
}