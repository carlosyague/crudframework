package org.librae.common.webapp.component.tree;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;
import javax.faces.event.PhaseId;

import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.shared_tomahawk.util.MessageUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TreeData is a {@link UIComponent} that supports binding data stored in a tree
 * represented by a {@link TreeNode} instance. During iterative processing over
 * the tree nodes in the data model, the object for the current node is exposed
 * as a request attribute under the key specified by the <code>var</code>
 * property. {@link javax.faces.render.Renderer}s of this component should use
 * the appropriate facet to assist in rendering.
 *
 * @JSFComponent
 * @author Sean Schofield
 * @author Hans Bergsten (Some code taken from an example in his O'Reilly
 *         JavaServer Faces book. Copied with permission)
 * @version $Revision: 703742 $ $Date: 2008-10-11 17:10:36 -0500 (Sat, 11 Oct
 *          2008) $
 */
public class UITreeData extends UIComponentBase implements NamingContainer,
		Tree {
	private Log log = LogFactory.getLog(UITreeData.class);

	public static final String COMPONENT_TYPE = "org.librae.common.webapp.component.tree.UITree";
	public static final String COMPONENT_FAMILY = "org.librae.common.webapp.component.tree.HtmlTree";
	// private static final String DEFAULT_RENDERER_TYPE =
	// "org.apache.myfaces.Tree2";
	private static final String MISSING_NODE = "org.apache.myfaces.tree2.MISSING_NODE";
	private static final int PROCESS_DECODES = 1;
	private static final int PROCESS_VALIDATORS = 2;
	private static final int PROCESS_UPDATES = 3;

	private TreeModel _cachedModel;
	private String _nodeId;
	private TreeNode _node;

	private Object _value;
	private String _var;

	/**
	 * Constructor
	 */
	public UITreeData() {

	}

	// see superclass for documentation
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	// see superclass for documentation
	public Object saveState(FacesContext context) {
		Object values[] = new Object[3];
		values[0] = super.saveState(context);
		values[1] = _var;
		return ((Object) (values));
	}

	// see superclass for documentation
	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);

		_var = (String) values[1];
	}

	public void encodeEnd(FacesContext context) throws IOException {
		super.encodeEnd(context);
	}

	public void queueEvent(FacesEvent event) {
		super.queueEvent(new FacesEventWrapper(event, getNodeId(), this));
	}

	public void broadcast(FacesEvent event) throws AbortProcessingException {
		if (event instanceof FacesEventWrapper) {
			FacesEventWrapper childEvent = (FacesEventWrapper) event;
			String currNodeId = getNodeId();
			setNodeId(childEvent.getNodeId());
			FacesEvent nodeEvent = childEvent.getFacesEvent();
			nodeEvent.getComponent().broadcast(nodeEvent);
			setNodeId(currNodeId);
			return;
		} else if (event instanceof ToggleExpandedEvent) {
			ToggleExpandedEvent toggleEvent = (ToggleExpandedEvent) event;
			String currentNodeId = getNodeId();
			setNodeId(toggleEvent.getNodeId());
			toggleExpanded();
			setNodeId(currentNodeId);
		} else {
			super.broadcast(event);
			return;
		}
	}

	// see superclass for documentation
	public void processDecodes(FacesContext context) {
	}

	// see superclass for documentation
	public void processValidators(FacesContext context) {
	}

	// see superclass for documentation
	public void processUpdates(FacesContext context) {
	}

	// see superclass for documentation
	public String getClientId(FacesContext context) {
		String ownClientId = super.getClientId(context);
		if (_nodeId != null) {
			return ownClientId + NamingContainer.SEPARATOR_CHAR + _nodeId;
		} else {
			return ownClientId;
		}
	}

	// see superclass for documentation
	public void setValueBinding(String name, ValueBinding binding) {
		if ("value".equals(name)) {
			_cachedModel = null;
		} else if ("nodeVar".equals(name) || "nodeId".equals(name)
				|| "treeVar".equals(name)) {
			throw new IllegalArgumentException("name " + name);
		}
		super.setValueBinding(name, binding);
	}

	// see superclass for documentation
	public void encodeBegin(FacesContext context) throws IOException {
		/**
		 * The renderer will handle most of the encoding, but if there are any
		 * error messages queued for the components (validation errors), we do
		 * want to keep the saved state so that we can render the node with the
		 * invalid value.
		 */
		_cachedModel = null;
		super.encodeBegin(context);
	}

	/**
	 * Sets the value of the TreeData.
	 *
	 * @param value
	 *            The new value
	 *
	 * @deprecated
	 */
	public void setValue(Object value) {
		_cachedModel = null;
		_value = value;
	}

	/**
	 * Gets the model of the TreeData - due to backwards-compatibility, this can
	 * also be retrieved by getValue.
	 *
	 * @return The value
	 */
	public Object getModel() {
		return getValue();
	}

	/**
	 * Sets the model of the TreeData - due to backwards-compatibility, this can
	 * also be set by calling setValue.
	 *
	 * @param model
	 *            The new model
	 */
	public void setModel(Object model) {
		setValue(model);
	}

	/**
	 * Gets the value of the TreeData.
	 *
	 * @JSFProperty required="true"
	 * @return The value
	 *
	 * @deprecated
	 */
	public Object getValue() {
		if (_value != null)
			return _value;
		ValueBinding vb = getValueBinding("value");
		return vb != null ? vb.getValue(getFacesContext()) : null;
	}

	/**
	 * Set the request-scope attribute under which the data object for the
	 * current node wil be exposed when iterating.
	 *
	 * @param var
	 *            The new request-scope attribute name
	 */
	public void setVar(String var) {
		_var = var;
	}

	/**
	 * Return the request-scope attribute under which the data object for the
	 * current node will be exposed when iterating. This property is not enabled
	 * for value binding expressions.
	 *
	 * @JSFProperty
	 * @return The iterator attribute
	 */
	public String getVar() {
		return _var;
	}

	/**
	 * Calls through to the {@link TreeModel} and returns the current
	 * {@link TreeNode} or <code>null</code>.
	 *
	 * @return The current node
	 */
	public TreeNode getNode() {
		return _node;
	}

	public String getNodeId() {
		return _nodeId;
	}

	public void setNodeId(String nodeId) {
		_nodeId = nodeId;

		TreeModel model = getDataModel();
		if (model == null) {
			return;
		}

		try {
			_node = model.getNodeById(nodeId);
		}
		// TODO: change to an own exception
		catch (IndexOutOfBoundsException aob) {
			/**
			 * This might happen if we are trying to process a commandLink for a
			 * node that node that no longer exists. Instead of allowing a
			 * RuntimeException to crash the application, we will add a warning
			 * message so the user can optionally display the warning. Also, we
			 * will allow the user to provide their own value binding method to
			 * be called so they can handle it how they see fit.
			 */
			FacesMessage message = MessageUtils.getMessage(MISSING_NODE,
					new String[] { nodeId });
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(getId(), message);

			/** @todo call hook */
			/** @todo figure out whether or not to abort this method gracefully */
		}

		if (_var != null) {
			Map requestMap = getFacesContext().getExternalContext()
					.getRequestMap();

			if (nodeId == null) {
				requestMap.remove(_var);
			} else {
				requestMap.put(_var, getNode());
			}
		}
	}

	/**
	 * Gets an array of String containing the ID's of all of the
	 * {@link TreeNode}s in the path to the specified node. The path information
	 * will be an array of <code>String</code> objects representing node ID's.
	 * The array will starting with the ID of the root node and end with the ID
	 * of the specified node.
	 *
	 * @param nodeId
	 *            The id of the node for whom the path information is needed.
	 * @return String[]
	 */
	public String[] getPathInformation(String nodeId) {
		return getDataModel().getPathInformation(nodeId);
	}

	/**
	 * Indicates whether or not the specified {@link TreeNode} is the last child
	 * in the <code>List</code> of children. If the node id provided corresponds
	 * to the root node, this returns <code>true</code>.
	 *
	 * @param nodeId
	 *            The ID of the node to check
	 * @return boolean
	 */
	public boolean isLastChild(String nodeId) {
		return getDataModel().isLastChild(nodeId);
	}

	/**
	 * Returns a previously cached {@link TreeModel}, if any, or sets the cache
	 * variable to either the current value (if its a {@link TreeModel}) or to a
	 * new instance of {@link TreeModel} (if it's a {@link TreeNode}) with the
	 * provided value object as the root node.
	 *
	 * @return TreeModel
	 */
	public TreeModel getDataModel() {
		if (_cachedModel != null) {
			return _cachedModel;
		}

		Object value = getValue();
		if (value != null) {
			if (value instanceof TreeModel) {
				_cachedModel = (TreeModel) value;
			} else if (value instanceof TreeNode) {
				_cachedModel = new TreeModelBase((TreeNode) value);
			} else {
				throw new IllegalArgumentException(
						"Value must be a TreeModel or TreeNode");
			}
		}
		return _cachedModel;
	}

	/**
	 * Epands all nodes by default.
	 */
	public void expandAll() {
	}

	/**
	 * Collapse all nodes by default.
	 */
	public void collapseAll() {
	}

	/**
	 * Expands all of the nodes in the specfied path.
	 *
	 * @param nodePath
	 *            The path to expand.
	 */
	public void expandPath(String[] nodePath) {
		getDataModel().getTreeState().expandPath(nodePath);
	}

	/**
	 * Expands all of the nodes in the specfied path.
	 *
	 * @param nodePath
	 *            The path to expand.
	 */
	public void collapsePath(String[] nodePath) {
		getDataModel().getTreeState().collapsePath(nodePath);
	}

	protected void processNodes(FacesContext context, int processAction,
			TreeWalker walker) {
		UIComponent facet = null;
		walker.reset();
		walker.setTree(this);

		while (walker.next()) {
			TreeNode node = getNode();
			facet = getFacet(node.getType());

			if (facet == null) {
				log.warn("Unable to locate facet with the name: "
						+ node.getType());
				continue;
			}
		}
	}

	/**
	 * Inner class used to wrap the original events produced by child components
	 * in the tree. This will allow the tree to find the appropriate component
	 * later when its time to broadcast the events to registered listeners. Code
	 * is based on a similar private class for UIData.
	 */
	private static class FacesEventWrapper extends FacesEvent {
		private static final long serialVersionUID = -3056153249469828447L;
		private FacesEvent _wrappedFacesEvent;
		private String _nodeId;

		public FacesEventWrapper(FacesEvent facesEvent, String nodeId,
				UIComponent component) {
			super(component);
			_wrappedFacesEvent = facesEvent;
			_nodeId = nodeId;
		}

		public PhaseId getPhaseId() {
			return _wrappedFacesEvent.getPhaseId();
		}

		public void setPhaseId(PhaseId phaseId) {
			_wrappedFacesEvent.setPhaseId(phaseId);
		}

		public void queue() {
			_wrappedFacesEvent.queue();
		}

		public String toString() {
			return _wrappedFacesEvent.toString();
		}

		public boolean isAppropriateListener(FacesListener faceslistener) {
			// this event type is only intended for wrapping a real event
			return false;
		}

		public void processListener(FacesListener faceslistener) {
			throw new UnsupportedOperationException(
					"This event type is only intended for wrapping a real event");
		}

		public FacesEvent getFacesEvent() {
			return _wrappedFacesEvent;
		}

		public String getNodeId() {
			return _nodeId;
		}
	}

	/**
	 * Toggle the expanded state of the current node.
	 */
	public void toggleExpanded() {
		getDataModel().getTreeState().toggleExpanded(getNodeId());
	}

	/**
	 * Indicates whether or not the current {@link TreeNode} is expanded.
	 *
	 * @return boolean
	 */
	public boolean isNodeExpanded() {
		return getDataModel().getTreeState().isNodeExpanded(getNodeId());
	}

	/**
	 * Implements the {@link javax.faces.event.ActionListener} interface.
	 * Basically, this method is used to listen for node selection events (when
	 * a user has clicked on a leaf node.)
	 *
	 * @param event
	 *            ActionEvent
	 */
	public void setNodeSelected(ActionEvent event) {
		getDataModel().getTreeState().setSelected(getNodeId());
	}

	/**
	 * Indicates whether or not the current {@link TreeNode} is selected.
	 *
	 * @return boolean
	 */
	public boolean isNodeSelected() {
		return (getNodeId() != null) ? getDataModel().getTreeState()
				.isSelected(getNodeId()) : false;
	}
}
