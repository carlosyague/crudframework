package org.librae.common.webapp.component.tree;

public interface TreeWalker {
	/**
	 * Getter for the check state property. Indicates whether or not the
	 * TreeWalker should navigate over nodes that are not currently expanded.
	 * 
	 * @return boolean
	 */
	public boolean isCheckState();

	/**
	 * Setter for the check state property. Indicates whether or not the
	 * TreeWalker should navigate over nodes that are not currently expanded.
	 * 
	 * @param checkState
	 *            boolean
	 */
	public void setCheckState(boolean checkState);

	/**
	 * Walk the tree and set the current node to the next node.
	 * 
	 * @return boolean whether or not there was another node to walk
	 */
	public boolean next();

	/**
	 * Returns the id of the root node.
	 * 
	 * @return String
	 */
	public String getRootNodeId();

	/**
	 * This method allows the renderer to pass a reference to the tree object.
	 * With this reference the TreeWalker can set the current node as its
	 * walking the tree.
	 * 
	 * @param tree
	 *            Tree
	 */
	public void setTree(Tree tree);

	/**
	 * Reset the walker so the tree can be walked again starting from the root.
	 */
	public void reset();
}
