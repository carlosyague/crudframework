package org.librae.common.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para modelar los items de los men�s
 * 
 * @author asantamaría
 */
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1170444095379310351L;
    private String            description;
    private String            url;
    private String            target;
    private String            arrowImg;
    private List<MenuItem>    sons;

    /**
     * Constructor sin parametros. No es el que se debe usar. Rellenar� las
     * propiedades del objeto con valores de ejemplo
     */
    public MenuItem() {
        this.description = "menu item";
        this.url = "#";
        this.target = "_self";
        this.arrowImg = "";
        this.sons = new ArrayList<MenuItem>();
    }

    /**
     * Constructor.
     * 
     * @param description
     * @param url
     * @param target
     * @param arrowImg
     */
    public MenuItem(String description, String url, String target,
            String arrowImg) {
        this.description = description;
        this.url = url;
        this.target = target;
        this.arrowImg = arrowImg;
        this.sons = new ArrayList<MenuItem>();
    }

    /**
     * getter de la descripci�n
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter de la descripci�n
     * 
     * @return
     */
    public void setDescription(String description) {
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
    public void setTarget(String target) {
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
    public void setUrl(String url) {
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
    public void setSons(List<MenuItem> sons) {
        this.sons = sons;
    }

    /**
     * getter de un hijo en concreto con una descripci�n concreta
     * 
     * @param description
     * @return
     */
    public MenuItem getSon(String description) {
        for (MenuItem son : sons) {
            if (son.getDescription().equals(description)) {
                return son;
            }
        }
        return null;
    }

    /**
     * getter de la imagen de la frecha
     * 
     * @return
     */
    public String getArrowImg() {
        return arrowImg;
    }

    /**
     * setter de la imagen de la frecha
     * 
     * @return
     */
    public void setArrowImg(String arrowImg) {
        this.arrowImg = arrowImg;
    }

    /**
     * Comprueba si tiene hijos o no
     * 
     * @return verdadero si el elemneto tiene hijos.
     */
    private boolean hasSons() {
        return ((this.sons != null) && (this.sons.size() > 0));
    }

    /**
     * A�ade un hijo al elemento
     * 
     * @param son
     */
    public void addSon(MenuItem son) {
        this.sons.add(son);
    }

    /**
     * Devuelve el men� en forma de cadena de caracteres, con el formato
     * necesario para crear el objeto javascript de un JSCookMenu
     */
    public String toString() {
        final StringBuilder result = new StringBuilder();

        result.append("[null, '");
        result.append(this.description);
        if (hasSons() && !"".equals(arrowImg)) {
            result.append(" <img src=\"");
            result.append(arrowImg);
            result.append("\"/>");
        }
        result.append("', ");
        if ("".equals(url)) {
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
            for (MenuItem son : sons) {
                result.append(",\n");
                result.append(son.toString());
            }
            result.append("\n");
        }
        result.append(']');

        return result.toString();
    }

}