package es.uma.crudframework.webapp.jsf;

import java.util.Iterator;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Custom LabelRenderer component that adds asterisks for required fields. Based
 * off of an example from David Geary on the MyFaces Mailing list.
 * 
 * @author Matt Raible
 */
public class LabelRenderer extends Renderer {
    protected final Log log = LogFactory.getLog(LabelRenderer.class);

    public boolean getRendersChildren() {
        return false;
    }

    public void encodeBegin(FacesContext context, UIComponent component)
            throws java.io.IOException {
        final ResponseWriter writer = context.getResponseWriter();

        final Map attrs = component.getAttributes();
        final String id = (String) attrs.get("for");

        UIInput input = null;

        if (!StringUtils.isEmpty(id)) {
            input = (UIInput) component.findComponent(id);
        }

        writer.startElement("label", component);

        StringBuilder styleClass = null;

        final String styleClassStr = (String) component.getAttributes().get(
                "styleClass");

        if (styleClassStr == null) {
            styleClass = new StringBuilder("");
        } else {
            styleClass = new StringBuilder(styleClassStr);
        }

        final boolean hasErrors = hasMessages(context, input);

        if ((styleClassStr == null) && hasErrors) {
            writer.writeAttribute("class", "error", null);
        }

        if ((styleClassStr != null) && hasErrors) {
            styleClass.append(" error");
        }

        if (styleClass != null) {
            writer.writeAttribute("class", styleClass.toString(), null);
        }

        final String renderedId = (input == null) ? component
                .getClientId(context) : input.getClientId(context);
        writer.writeAttribute("for", renderedId, null);
        writer.write(attrs.get("value").toString());
    }

    public void encodeEnd(FacesContext context, UIComponent component)
            throws java.io.IOException {
        final ResponseWriter writer = context.getResponseWriter();

        final Map attrs = component.getAttributes();
        final String id = (String) attrs.get("for");

        UIInput input = null;

        if (!StringUtils.isEmpty(id)) {
            input = (UIInput) component.findComponent(id);
        }

        if ((input != null) && input.isRequired()) {
            writer.write(" <span class=\"req\">*</span>");
        }

        writer.endElement("label");
    }

    private boolean hasMessages(FacesContext context, UIComponent component) {
        final Iterator it = context.getClientIdsWithMessages();
        boolean found = false;

        while (it.hasNext()) {
            final String id = (String) it.next();

            if ((component != null)
                    && id.equals(component.getClientId(context))) {
                found = true;
                break;
            }
        }

        return found;
    }
}
