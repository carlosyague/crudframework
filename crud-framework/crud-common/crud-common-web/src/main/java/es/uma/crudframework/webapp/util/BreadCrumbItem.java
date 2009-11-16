package es.uma.crudframework.webapp.util;

import java.io.Serializable;

public class BreadCrumbItem implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = -2123419603910556520L;

    // identificador unico de la vista asociada al item
    private String            viewId;

    // texto a mostrar al usuario en el path
    private String            texto;

    // false indica que este item no tendrá enlace en el path
    private String            disabled;

    // url absoluta del enlace del item en el path. Prevalece sobre el action
    private String            url;

    // posición dentro del path
    private int               pos;

    public BreadCrumbItem(String viewId, String texto, String disabled,
            String url) {
        super();
        this.disabled = disabled;
        this.texto = texto;
        this.url = url;
        this.viewId = viewId;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
