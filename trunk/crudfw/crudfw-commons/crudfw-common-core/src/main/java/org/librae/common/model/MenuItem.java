package org.librae.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.librae.common.Constants;

/**
 * Clase para modelar los items de los menus
 * 
 * @author jcisneros
 */
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 1170444095379310351L;
    private String            description;
    private String            url;
    private String            target;
    private String            arrowImg;
    private List<MenuItem>    sons;
    private boolean           printArrowImg;

    /**
     * Constructor sin parametros. No es el que se debe usar. Rellenara las
     * propiedades del objeto con valores de ejemplo
     */
    public MenuItem() {
        description = "menu item";
        url = "#";
        target = "_self";
        arrowImg = Constants.URL_IMG_MENU;
        sons = new ArrayList<MenuItem>();
    }

    /**
     * Constructor.
     * 
     * @param description
     * @param url
     * @param target
     * @param arrowImg
     */
    public MenuItem(final String description, final String url,
            final String target, final String arrowImg) {
        this.description = description;
        this.url = url;
        this.target = target;
        this.arrowImg = arrowImg;
        sons = new ArrayList<MenuItem>();
    }

    /**
     * Constructor.
     * 
     * @param description
     * @param url
     * @param target
     * @param arrowImg
     */
    public MenuItem(final String description, final String url,
            final String target, final List<MenuItem> listaHijos,
            final boolean printArrowImg) {
        this.description = description;
        this.url = url;
        this.target = target;
        sons = listaHijos;
        arrowImg = "";
        this.printArrowImg = printArrowImg;

        if (printArrowImg) {
            arrowImg = Constants.URL_IMG_MENU;
        }
    }

    /**
     * getter de la descripcion
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter de la descripcion
     * 
     * @return
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * getter del destino del enlace
     * 
     * @return
     */
    public String getTarget() {
        return target;
    }

    /**
     * setter del destino del enlace
     * 
     * @return
     */
    public void setTarget(final String target) {
        this.target = target;
    }

    /**
     * getter de la url
     * 
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * setter de la url
     * 
     * @return
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * getter de los hijos
     * 
     * @return
     */
    public List<MenuItem> getSons() {
        return sons;
    }

    /**
     * setter de los hijos
     * 
     * @return
     */
    public void setSons(final List<MenuItem> sons) {
        this.sons = sons;
    }

    /**
     * getter de un hijo en concreto con una descripcion concreta
     * 
     * @param description
     * @return
     */
    public MenuItem getSon(final String description) {
        for (final MenuItem son : sons) {
            if (son.getDescription().equals(description)) {
                return son;
            }
        }
        return null;
    }

    /**
     * getter de la imagen de la flecha
     * 
     * @return
     */
    public String getArrowImg() {
        return arrowImg;
    }

    /**
     * setter de la imagen de la flecha
     * 
     * @return
     */
    public void setArrowImg(final String arrowImg) {
        this.arrowImg = arrowImg;
    }

    /**
     * Comprueba si tiene hijos o no
     * 
     * @return verdadero si el elemneto tiene hijos.
     */
    private boolean hasSons() {
        return ((sons != null) && (!sons.isEmpty()));
    }

    /**
     * Añade un hijo al elemento
     * 
     * @param son
     */
    public void addSon(final MenuItem son) {
        sons.add(son);
    }

    /**
     * Devuelve el menu en forma de cadena de caracteres, con el formato
     * necesario para crear el objeto javascript de un JSCookMenu
     */
    public String toString() {
        final StringBuilder result = new StringBuilder();

        result.append("[null, '");
        result.append(description);
        if (!"".equals(arrowImg) && printArrowImg) {
            result.append(" <img src=\"");
            result.append(arrowImg);
            result.append("\"/>");
        }
        result.append("', ");
        if ((url == null) || (url.equals(""))) {
            result.append("null");
        } else {
            result.append('\'');
            result.append(url);
            result.append('\'');
        }
        result.append(", '");
        result.append(target);
        result.append("', null");
        if (hasSons()) {
            for (final MenuItem son : sons) {
                result.append(", ");
                result.append(son.toString());
            }
        }
        result.append(']');

        return result.toString();
    }
}