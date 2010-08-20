package org.librae.common.webapp.component.tag;

import org.apache.myfaces.taglib.html.ext.HtmlDataTableTag;

/**
 * Renderer para el listado en Librae.
 * 
 * @author jcisneros
 */
public class HtmlListadoTag extends HtmlDataTableTag {

    public String getComponentType()
    {
        return "org.librae.common.webapp.component.HtmlListado";
    }

    public String getRendererType()
    {
        return "org.librae.common.webapp.component.renderer.HtmlListadoRenderer";
    }
	
}
