package org.librae.common.webapp.component.tree;

import java.util.Map;

import javax.faces.component.UICommand;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;

import org.apache.myfaces.component.LocationAware;

/**
 * Represents "tree data" in an HTML format. Also provides a mechanism for
 * maintaining expand/collapse state of the nodes in the tree.
 *
 * A component that provides an HTML-based tree from data supplied by a backing
 * bean. The tree is highly customizable and allows for fine-grained control
 * over the appearance of each of the nodes depending on their type.
 *
 * Almost any type of JSF component (text, image, checkbox, etc.) can be
 * rendered inside the nodes and there is an option for client-side or
 * server-side toggling of the expand/collapse state.
 *
 * Unless otherwise specified, all attributes accept static values or EL
 * expressions.
 *
 * @JSFComponent name = "t:tree2" class =
 *               "org.apache.myfaces.custom.tree2.HtmlTree" tagClass =
 *               "org.apache.myfaces.custom.tree2.TreeTag"
 * @since 1.1.7
 * @author Sean Schofield
 */
public abstract class AbstractHtmlTree extends UITreeData implements
		LocationAware {

	public static final String COMPONENT_TYPE = "org.librae.common.webapp.component.tree.HtmlTree";
	private static final String DEFAULT_RENDERER_TYPE = "org.librae.common.webapp.component.tree.HtmlTree";

	private UICommand _expandControl = null;

	// public abstract boolean isClientSideToggle();

	// Property: clientSideToggle
	private boolean _clientSideToggle = true;
	private boolean _clientSideToggleSet;

	/**
	 * Perform client-side toggling of expand/collapse state via javascript
	 * (default is true.)
	 *
	 * @JSFProperty defaultValue = "true"
	 * @return the new clientSideToggle value
	 */
	public boolean isClientSideToggle() {
		if (_clientSideToggleSet) {
			return _clientSideToggle;
		}
		ValueBinding expression = getValueBinding("clientSideToggle");
		if (expression != null) {
			return ((Boolean) expression.getValue(getFacesContext()))
					.booleanValue();
		}
		return true;
	}

	/**
	 * Sets
	 *
	 * @param clientSideToggle
	 *            the new clientSideToggle value
	 */
	public void setClientSideToggle(boolean clientSideToggle) {
		this._clientSideToggle = clientSideToggle;
		this._clientSideToggleSet = true;
	}

	/**
	 * @see org.apache.myfaces.custom.tree2.UITreeData#processNodes(javax.faces.context.FacesContext,
	 *      int, org.apache.myfaces.custom.tree2.TreeWalker)
	 */
	protected void processNodes(FacesContext context, int processAction,
			TreeWalker walker) {
		super.processNodes(context, processAction, walker);
	}

	public void setNodeId(String nodeId) {
		super.setNodeId(nodeId);

		if (_varNodeToggler != null) {
			Map requestMap = getFacesContext().getExternalContext()
					.getRequestMap();
			requestMap.put(_varNodeToggler, this);
		}
	}

	/**
	 * Gets the expand/collapse control that can be used to handle
	 * expand/collapse nodes. This is only used in server-side mode. It allows
	 * the nagivation controls (if any) to be clickable as well as any
	 * commandLinks the user has set up in their JSP.
	 *
	 * @return UICommand
	 */
	public UICommand getExpandControl() {
		if (_expandControl == null) {
			_expandControl = new HtmlCommandLink();
			_expandControl.setParent(this);
		}
		return _expandControl;
	}

	// Property: varNodeToggler
	private String _varNodeToggler;

	/**
	 * Gets
	 *
	 * @JSFProperty
	 * @return the new varNodeToggler value
	 */
	public String getVarNodeToggler() {
		return _varNodeToggler;
	}

	/**/// setVarNodeToggler
	public void setVarNodeToggler(String varNodeToggler) {
		_varNodeToggler = varNodeToggler;

		// create a method binding for the expand control
		String bindingString = "#{" + varNodeToggler + ".toggleExpanded}";
		MethodBinding actionBinding = FacesContext.getCurrentInstance()
				.getApplication().createMethodBinding(bindingString, null);
		getExpandControl().setAction(actionBinding);
	}

	/**
	 * Show the "plus" and "minus" navigation icons (default is true.) Value is
	 * ignored if clientSideToggle is true.
	 *
	 * @JSFProperty defaultValue = "true"
	 */
	public abstract boolean isShowNav();

	/**
	 * Show the connecting lines (default is true.)
	 *
	 * @JSFProperty defaultValue = "true"
	 */
	public abstract boolean isShowLines();

	/**
	 * Include the root node when rendering the tree (default is true.)
	 *
	 * @JSFProperty defaultValue = "true"
	 */
	public abstract boolean isShowRootNode();

	/**
	 * Preserve changes in client-side toggle information between requests
	 * (default is true.)
	 *
	 * @JSFProperty defaultValue = "true"
	 */
	public abstract boolean isPreserveToggle();

}
