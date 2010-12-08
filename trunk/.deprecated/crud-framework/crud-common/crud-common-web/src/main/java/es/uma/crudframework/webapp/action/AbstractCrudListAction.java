package es.uma.crudframework.webapp.action;

import java.util.List;

import es.uma.crudframework.service.GenericManager;
import es.uma.crudframework.webapp.action.BasePage;

public abstract class AbstractCrudListAction<T> extends BasePage {
	
	protected GenericManager<T,Long> manager;
	
	public GenericManager<T,Long> getManager() {
		return manager;
	}
	
	public void setManager(GenericManager<T,Long> manager) {
		this.manager = manager;
	}
	
	public List getEntitiesList() {
		if (this.getSortColumn() == null) {
			setSortColumn("id");
		}
		return sort(manager.getAll());
	}
	
	

}
