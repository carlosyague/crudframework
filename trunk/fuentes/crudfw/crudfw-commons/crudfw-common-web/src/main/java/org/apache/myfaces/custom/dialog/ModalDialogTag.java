package org.apache.myfaces.custom.dialog;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;
import javax.faces.context.FacesContext;

public class ModalDialogTag
    extends javax.faces.webapp.UIComponentTag
{
    public ModalDialogTag()
    {    
    }
    
    public String getComponentType()
    {
        return "org.apache.myfaces.ModalDialog";
    }

    public String getRendererType()
    {
        return "org.apache.myfaces.ModalDialog";
    }

 
    private String _dialogAttr;
    
    public void setDialogAttr(String dialogAttr)
    {
        _dialogAttr = dialogAttr;
    }
 
    private String _dialogId;
    
    public void setDialogId(String dialogId)
    {
        _dialogId = dialogId;
    }
 
    private String _dialogVar;
    
    public void setDialogVar(String dialogVar)
    {
        _dialogVar = dialogVar;
    }
 
    private String _hiderIds;
    
    public void setHiderIds(String hiderIds)
    {
        _hiderIds = hiderIds;
    }
 
    private String _viewId;
    
    public void setViewId(String viewId)
    {
        _viewId = viewId;
    }
 
    private String _contentURL;
    
    public void setContentURL(String contentURL)
    {
        _contentURL = contentURL;
    }
 
    private String _style;
    
    public void setStyle(String style)
    {
        _style = style;
    }
 
    private String _styleClass;
    
    public void setStyleClass(String styleClass)
    {
        _styleClass = styleClass;
    }
 
    private String _widgetId;
    
    public void setWidgetId(String widgetId)
    {
        _widgetId = widgetId;
    }
 
    private String _dialogTitle;
    
    public void setDialogTitle(String dialogTitle)
    {
        _dialogTitle = dialogTitle;
    }
 
    private String _closeButton;
    
    public void setCloseButton(String closeButton)
    {
        _closeButton = closeButton;
    }
 
    private String _widgetVar;
    
    public void setWidgetVar(String widgetVar)
    {
        _widgetVar = widgetVar;
    }

    protected void setProperties(UIComponent component)
    {
        if (!(component instanceof org.apache.myfaces.custom.dialog.ModalDialog))
        {
            throw new IllegalArgumentException("Component "+
                component.getClass().getName() +" is no org.apache.myfaces.custom.dialog.ModalDialog");
        }
        
        org.apache.myfaces.custom.dialog.ModalDialog comp = (org.apache.myfaces.custom.dialog.ModalDialog) component;
        
        super.setProperties(component);
        
        FacesContext context = getFacesContext();

        if (_dialogAttr != null)
        {
            if (isValueReference(_dialogAttr))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_dialogAttr);
                comp.setValueBinding("dialogAttr", vb);
            }
            else
            {
                comp.getAttributes().put("dialogAttr", _dialogAttr);
            }
        } 
        if (_dialogId != null)
        {
            if (isValueReference(_dialogId))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_dialogId);
                comp.setValueBinding("dialogId", vb);
            }
            else
            {
                comp.getAttributes().put("dialogId", _dialogId);
            }
        } 
        if (_dialogVar != null)
        {
            if (isValueReference(_dialogVar))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_dialogVar);
                comp.setValueBinding("dialogVar", vb);
            }
            else
            {
                comp.getAttributes().put("dialogVar", _dialogVar);
            }
        } 
        if (_hiderIds != null)
        {
            if (isValueReference(_hiderIds))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_hiderIds);
                comp.setValueBinding("hiderIds", vb);
            }
            else
            {
                comp.getAttributes().put("hiderIds", _hiderIds);
            }
        } 
        if (_viewId != null)
        {
            if (isValueReference(_viewId))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_viewId);
                comp.setValueBinding("viewId", vb);
            }
            else
            {
                comp.getAttributes().put("viewId", _viewId);
            }
        } 
        if (_contentURL != null)
        {
            if (isValueReference(_contentURL))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_contentURL);
                comp.setValueBinding("contentURL", vb);
            }
            else
            {
                comp.getAttributes().put("contentURL", _contentURL);
            }
        } 
        if (_style != null)
        {
            if (isValueReference(_style))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_style);
                comp.setValueBinding("style", vb);
            }
            else
            {
                comp.getAttributes().put("style", _style);
            }
        } 
        if (_styleClass != null)
        {
            if (isValueReference(_styleClass))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_styleClass);
                comp.setValueBinding("styleClass", vb);
            }
            else
            {
                comp.getAttributes().put("styleClass", _styleClass);
            }
        } 
        if (_widgetId != null)
        {
            if (isValueReference(_widgetId))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_widgetId);
                comp.setValueBinding("widgetId", vb);
            }
            else
            {
                comp.getAttributes().put("widgetId", _widgetId);
            }
        } 
        if (_dialogTitle != null)
        {
            if (isValueReference(_dialogTitle))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_dialogTitle);
                comp.setValueBinding("dialogTitle", vb);
            }
            else
            {
                comp.getAttributes().put("dialogTitle", _dialogTitle);
            }
        } 
        if (_closeButton != null)
        {
            if (isValueReference(_closeButton))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_closeButton);
                comp.setValueBinding("closeButton", vb);
            }
            else
            {
                comp.getAttributes().put("closeButton", Boolean.valueOf(_closeButton));
            }
        } 
        if (_widgetVar != null)
        {
            if (isValueReference(_widgetVar))
            {
                ValueBinding vb = context.getApplication().createValueBinding(_widgetVar);
                comp.setValueBinding("widgetVar", vb);
            }
            else
            {
                comp.getAttributes().put("widgetVar", _widgetVar);
            }
        } 
    }

    public void release()
    {
        super.release();
        _dialogAttr = null;
        _dialogId = null;
        _dialogVar = null;
        _hiderIds = null;
        _viewId = null;
        _contentURL = null;
        _style = null;
        _styleClass = null;
        _widgetId = null;
        _dialogTitle = null;
        _closeButton = null;
        _widgetVar = null;
    }
}
