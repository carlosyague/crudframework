package es.uma.crudframework.webapp.action;

import es.uma.crudframework.model.BaseObject;
import es.uma.crudframework.service.GenericManager;
import es.uma.crudframework.webapp.action.BasePage;

public abstract class AbstractCrudFormAction<T extends BaseObject> extends BasePage {
	
	protected GenericManager<T,Long> manager;
	protected T entity;
	protected Long id;
	
	/**
     * navigation tokens<br>
     * =================
     */
	protected final static String NAVIGATION_TOKEN_LIST = "list";
	protected final static String NAVIGATION_TOKEN_EDIT = "edit";
	
	/**
     * abstract methods<br>
     * ================
     */
	
	protected abstract T createEmptyEntity();
	protected abstract T getEntity();
	
	/**
     * actions<br>
     * =======
     */

    public String delete() {
    	manager.remove(entity.getId());
        addMessage("contacto.deleted");

        return NAVIGATION_TOKEN_LIST;
    }

    public String edit() {
        if (id != null && id != 0) {
            entity = manager.get(id);
        } else {
        	entity = createEmptyEntity();
        }

        return NAVIGATION_TOKEN_EDIT;
    }

    public String save() {
        boolean isNew = (entity.getId() == null || entity.getId() == 0);
        manager.save(entity);

        String key = (isNew) ? "contacto.added" : "contacto.updated";
        addMessage(key);

        if (isNew) {
            return NAVIGATION_TOKEN_LIST;
        } else {
            return NAVIGATION_TOKEN_EDIT;
        }
    }       
	
	/**
     * getter & setters<br>
     * ================
     */
    
    public GenericManager<T,Long> getManager() {
		return manager;
	}
	
	public void setManager(GenericManager<T,Long> manager) {
		this.manager = manager;
	}

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Boolean getExistsEntity() {
    	return this.entity != null && entity.getId() != null;
    }

}
