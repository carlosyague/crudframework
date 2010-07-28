package org.apache.myfaces.custom.dialog;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;
import javax.faces.context.FacesContext;


// Generated from class org.apache.myfaces.custom.dialog.AbstractModalDialog.
//
// WARNING: This file was automatically generated. Do not edit it directly,
//          or you will lose your changes.
public class ModalDialog extends org.apache.myfaces.custom.dialog.AbstractModalDialog
{

    static public final String COMPONENT_FAMILY =
        "javax.faces.Panel";
    static public final String COMPONENT_TYPE =
        "org.apache.myfaces.ModalDialog";
    static public final String DEFAULT_RENDERER_TYPE = 
        "org.apache.myfaces.ModalDialog";


    public ModalDialog()
    {
        setRendererType("org.apache.myfaces.ModalDialog");
    }

    public String getFamily()
    {
        return COMPONENT_FAMILY;
    }
    

    // Property: dialogAttr
    private String _dialogAttr;
    
    public String getDialogAttr()
    {
        if (_dialogAttr != null)
        {
            return _dialogAttr;
        }
        ValueBinding vb = getValueBinding("dialogAttr");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setDialogAttr(String dialogAttr)
    {
        this._dialogAttr = dialogAttr;
    }
    // Property: dialogId
    private String _dialogId;
    
    public String getDialogId()
    {
        if (_dialogId != null)
        {
            return _dialogId;
        }
        ValueBinding vb = getValueBinding("dialogId");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setDialogId(String dialogId)
    {
        this._dialogId = dialogId;
    }
    // Property: dialogVar
    private String _dialogVar;
    
    public String getDialogVar()
    {
        if (_dialogVar != null)
        {
            return _dialogVar;
        }
        ValueBinding vb = getValueBinding("dialogVar");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setDialogVar(String dialogVar)
    {
        this._dialogVar = dialogVar;
    }
    // Property: hiderIds
    private String _hiderIds;
    
    public String getHiderIds()
    {
        if (_hiderIds != null)
        {
            return _hiderIds;
        }
        ValueBinding vb = getValueBinding("hiderIds");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setHiderIds(String hiderIds)
    {
        this._hiderIds = hiderIds;
    }
    // Property: viewId
    private String _viewId;
    
    public String getViewId()
    {
        if (_viewId != null)
        {
            return _viewId;
        }
        ValueBinding vb = getValueBinding("viewId");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setViewId(String viewId)
    {
        this._viewId = viewId;
    }
    // Property: contentURL
    private String _contentURL;
    
    public String getContentURL()
    {
        if (_contentURL != null)
        {
            return _contentURL;
        }
        ValueBinding vb = getValueBinding("contentURL");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setContentURL(String contentURL)
    {
        this._contentURL = contentURL;
    }
    // Property: style
    private String _style;
    
    public String getStyle()
    {
        if (_style != null)
        {
            return _style;
        }
        ValueBinding vb = getValueBinding("style");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setStyle(String style)
    {
        this._style = style;
    }
    // Property: styleClass
    private String _styleClass;
    
    public String getStyleClass()
    {
        if (_styleClass != null)
        {
            return _styleClass;
        }
        ValueBinding vb = getValueBinding("styleClass");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setStyleClass(String styleClass)
    {
        this._styleClass = styleClass;
    }
    // Property: widgetId
    private String _widgetId;
    
    public String getWidgetId()
    {
        if (_widgetId != null)
        {
            return _widgetId;
        }
        ValueBinding vb = getValueBinding("widgetId");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setWidgetId(String widgetId)
    {
        this._widgetId = widgetId;
    }
    // Property: dialogTitle
    private String _dialogTitle;
    
    public String getDialogTitle()
    {
        if (_dialogTitle != null)
        {
            return _dialogTitle;
        }
        ValueBinding vb = getValueBinding("dialogTitle");
        if (vb != null)
        {
            return (String) vb.getValue(getFacesContext());
        }
        return null;
    }

    public void setDialogTitle(String dialogTitle)
    {
        this._dialogTitle = dialogTitle;
    }
    // Property: closeButton
    private Boolean _closeButton;
    
    public Boolean getCloseButton()
    {
        if (_closeButton != null)
        {
            return _closeButton;
        }
        ValueBinding vb = getValueBinding("closeButton");
        if (vb != null)
        {
            Object value = vb == null ? null : vb.getValue(getFacesContext());
            if (!(value instanceof Boolean)){
                value = Boolean.valueOf(value.toString());
            }            
            return (Boolean) value;
        }
        return null;
    }

    public void setCloseButton(Boolean closeButton)
    {
        this._closeButton = closeButton;
    }

    public Object saveState(FacesContext facesContext)
    {
        Object[] values = new Object[12];
        values[0] = super.saveState(facesContext);
        values[1] = _dialogAttr;
        values[2] = _dialogId;
        values[3] = _dialogVar;
        values[4] = _hiderIds;
        values[5] = _viewId;
        values[6] = _contentURL;
        values[7] = _style;
        values[8] = _styleClass;
        values[9] = _widgetId;
        values[10] = _dialogTitle;
        values[11] = _closeButton;
        return values; 
    }

    public void restoreState(FacesContext facesContext, Object state)
    {
        Object[] values = (Object[])state;
        super.restoreState(facesContext,values[0]);
        _dialogAttr = (java.lang.String) values[1];
        _dialogId = (java.lang.String) values[2];
        _dialogVar = (java.lang.String) values[3];
        _hiderIds = (java.lang.String) values[4];
        _viewId = (java.lang.String) values[5];
        _contentURL = (java.lang.String) values[6];
        _style = (java.lang.String) values[7];
        _styleClass = (java.lang.String) values[8];
        _widgetId = (java.lang.String) values[9];
        _dialogTitle = (java.lang.String) values[10];
        _closeButton = (java.lang.Boolean) values[11];
    }
}
