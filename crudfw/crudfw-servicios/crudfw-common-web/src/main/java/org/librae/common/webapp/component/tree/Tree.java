package org.librae.common.webapp.component.tree;

import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.tree2.TreeNode;

/**
 * @author jcisneros
 */
public interface Tree {
	void setModel(Object model);

	Object getModel();

	void setVar(String var);

	String getVar();

	TreeNode getNode();

	String getNodeId();

	void setNodeId(String nodeId);

	String[] getPathInformation(String nodeId);

	boolean isLastChild(String nodeId);

	TreeModel getDataModel();

	void expandAll();

	void collapseAll();

	void expandPath(String[] nodePath);

	void collapsePath(String[] nodePath);

	void toggleExpanded();

	boolean isNodeExpanded();

	void setNodeSelected(ActionEvent event);

	boolean isNodeSelected();
}
