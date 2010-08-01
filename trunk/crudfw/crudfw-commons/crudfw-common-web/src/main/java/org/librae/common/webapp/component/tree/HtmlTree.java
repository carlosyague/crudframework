package org.librae.common.webapp.component.tree;

import javax.faces.el.ValueBinding;
import javax.faces.context.FacesContext;

// Generated from class org.apache.myfaces.custom.tree2.AbstractHtmlTree.
//
// WARNING: This file was automatically generated. Do not edit it directly,
//          or you will lose your changes.
public class HtmlTree extends AbstractHtmlTree {

	static public final String COMPONENT_FAMILY = "org.librae.common.webapp.component.tree.HtmlTree";
	static public final String COMPONENT_TYPE = "org.librae.common.webapp.component.tree.HtmlTree";
	static public final String DEFAULT_RENDERER_TYPE = "org.librae.common.webapp.component.tree.HtmlTree";

	public HtmlTree() {
		setRendererType("org.librae.common.webapp.component.HtmlTree");
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	// Property: showNav
	private boolean _showNav;

	private boolean _showNavSet;

	public boolean isShowNav() {
		if (_showNavSet) {
			return _showNav;
		}
		ValueBinding vb = getValueBinding("showNav");
		if (vb != null) {
			return ((Boolean) vb.getValue(getFacesContext())).booleanValue();
		}
		return true;
	}

	public void setShowNav(boolean showNav) {
		this._showNav = showNav;
		this._showNavSet = true;
	}

	// Property: showLines
	private boolean _showLines;

	private boolean _showLinesSet;

	public boolean isShowLines() {
		if (_showLinesSet) {
			return _showLines;
		}
		ValueBinding vb = getValueBinding("showLines");
		if (vb != null) {
			return ((Boolean) vb.getValue(getFacesContext())).booleanValue();
		}
		return true;
	}

	public void setShowLines(boolean showLines) {
		this._showLines = showLines;
		this._showLinesSet = true;
	}

	// Property: showRootNode
	private boolean _showRootNode;

	private boolean _showRootNodeSet;

	public boolean isShowRootNode() {
		if (_showRootNodeSet) {
			return _showRootNode;
		}
		ValueBinding vb = getValueBinding("showRootNode");
		if (vb != null) {
			return ((Boolean) vb.getValue(getFacesContext())).booleanValue();
		}
		return true;
	}

	public void setShowRootNode(boolean showRootNode) {
		this._showRootNode = showRootNode;
		this._showRootNodeSet = true;
	}

	// Property: preserveToggle
	private boolean _preserveToggle;

	private boolean _preserveToggleSet;

	public boolean isPreserveToggle() {
		if (_preserveToggleSet) {
			return _preserveToggle;
		}
		ValueBinding vb = getValueBinding("preserveToggle");
		if (vb != null) {
			return ((Boolean) vb.getValue(getFacesContext())).booleanValue();
		}
		return true;
	}

	public void setPreserveToggle(boolean preserveToggle) {
		this._preserveToggle = preserveToggle;
		this._preserveToggleSet = true;
	}

	// Property: javascriptLocation
	private String _javascriptLocation;

	public String getJavascriptLocation() {
		if (_javascriptLocation != null) {
			return _javascriptLocation;
		}
		ValueBinding vb = getValueBinding("javascriptLocation");
		if (vb != null) {
			return (String) vb.getValue(getFacesContext()).toString();
		}
		return null;
	}

	public void setJavascriptLocation(String javascriptLocation) {
		this._javascriptLocation = javascriptLocation;
	}

	// Property: imageLocation
	private String _imageLocation;

	public String getImageLocation() {
		if (_imageLocation != null) {
			return _imageLocation;
		}
		ValueBinding vb = getValueBinding("imageLocation");
		if (vb != null) {
			return (String) vb.getValue(getFacesContext()).toString();
		}
		return null;
	}

	public void setImageLocation(String imageLocation) {
		this._imageLocation = imageLocation;
	}

	// Property: styleLocation
	private String _styleLocation;

	public String getStyleLocation() {
		if (_styleLocation != null) {
			return _styleLocation;
		}
		ValueBinding vb = getValueBinding("styleLocation");
		if (vb != null) {
			return (String) vb.getValue(getFacesContext()).toString();
		}
		return null;
	}

	public void setStyleLocation(String styleLocation) {
		this._styleLocation = styleLocation;
	}

	public Object saveState(FacesContext facesContext) {
		Object[] values = new Object[12];
		values[0] = super.saveState(facesContext);
		values[1] = Boolean.valueOf(_showNav);
		values[2] = Boolean.valueOf(_showNavSet);
		values[3] = Boolean.valueOf(_showLines);
		values[4] = Boolean.valueOf(_showLinesSet);
		values[5] = Boolean.valueOf(_showRootNode);
		values[6] = Boolean.valueOf(_showRootNodeSet);
		values[7] = Boolean.valueOf(_preserveToggle);
		values[8] = Boolean.valueOf(_preserveToggleSet);
		values[9] = _javascriptLocation;
		values[10] = _imageLocation;
		values[11] = _styleLocation;
		return values;
	}

	public void restoreState(FacesContext facesContext, Object state) {
		Object[] values = (Object[]) state;
		super.restoreState(facesContext, values[0]);
		_showNav = ((Boolean) values[1]).booleanValue();
		_showNavSet = ((Boolean) values[2]).booleanValue();
		_showLines = ((Boolean) values[3]).booleanValue();
		_showLinesSet = ((Boolean) values[4]).booleanValue();
		_showRootNode = ((Boolean) values[5]).booleanValue();
		_showRootNodeSet = ((Boolean) values[6]).booleanValue();
		_preserveToggle = ((Boolean) values[7]).booleanValue();
		_preserveToggleSet = ((Boolean) values[8]).booleanValue();
		_javascriptLocation = (java.lang.String) values[9];
		_imageLocation = (java.lang.String) values[10];
		_styleLocation = (java.lang.String) values[11];
	}
}
