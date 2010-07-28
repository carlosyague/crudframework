package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.webapp.form.ISearchForm;

/**
 * Formulario de busqueda para la entidad Biblioteca
 * 
 * @author aropero
 */
public class BibliotecaCriteriaForm implements ISearchForm, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5040203718284599134L;

    /**
     * Nombre de la biblioteca
     */
    private String            nombre;

    /**
     * Descripcion de la biblioteca
     */
    private String            descripcion;

    /**
     * Persona de contacto
     */
    private String            contacto;
    /**
     * Tipo de Biblioteca
     */
    private Long              tipoBiblioteca;

    /**
     * Seleccionamos buscar la Biblioteca por el codigo o el arbol.
     */
    private List<SelectItem>  buscarPorBiblioteca;

    private String            busqueda         = "arbol";

    /**
     * Identificador de la biblioteca.
     */
    private Long              idBiblioteca     = null;

    /**
     * @see org.librae.common.webapp.form.ISearchForm#toMap()
     */
    public Map<String, Object> toMap() {
        final HashMap<String, Object> criterios = new HashMap<String, Object>();
        criterios.put(Biblioteca.PROPTY_NAME_PERSONA_CONTACTO, contacto);
        criterios.put(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA, tipoBiblioteca);

        if (busqueda.equalsIgnoreCase(Biblioteca.PROPTY_NAME_CODIGO)) {
            criterios.put("codigoBiblioteca", nombre);
        } else if (busqueda.equalsIgnoreCase("descripcion")) {
            criterios.put(Biblioteca.PROPTY_NAME_DESCRIPCION, descripcion);
        } else {
            criterios.put("idBiblioteca", getIdBiblioteca());
        }
        criterios.put("busqueda", getBusqueda());
        return criterios;
    }

    /**
     * @see org.librae.common.webapp.form.ISearchForm#fromMap(Map<String,
     *      Object>)
     */
    public void fromMap(Map<String, Object> criterios) {
        if (criterios != null) {
            if (criterios.get("codigoBiblioteca") != null) {
                setNombre(criterios.get("codigoBiblioteca").toString());
            }

            if (criterios.get(Biblioteca.PROPTY_NAME_PERSONA_CONTACTO) != null) {
                setContacto(criterios.get(
                        Biblioteca.PROPTY_NAME_PERSONA_CONTACTO).toString());
            }

            if (criterios.get(Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA) != null) {
                setTipoBiblioteca(new Long(criterios.get(
                        Biblioteca.PROPTY_NAME_TIPO_BIBLIOTECA).toString()));
            }
            if (criterios.get("idBiblioteca") != null) {
                setIdBiblioteca(new Long(criterios.get("idBiblioteca")
                        .toString()));
            }
            if (criterios.get("busqueda") != null) {
                setBusqueda(criterios.get("busqueda").toString());
            }
        }
    }

    /**
     * Listado de radios.
     * 
     * @return
     */
    public List<SelectItem> getBuscarPorBiblioteca() {
        buscarPorBiblioteca = new ArrayList<SelectItem>();

        final SelectItem codigo = new SelectItem();
        codigo.setValue("codigo");
        codigo.setLabel(ComboLocaleManager.get("biblioteca.codigo"));
        buscarPorBiblioteca.add(codigo);

        final SelectItem nombre = new SelectItem();
        nombre.setValue("descripcion");
        nombre.setLabel(ComboLocaleManager.get("biblioteca.descripcion"));
        buscarPorBiblioteca.add(nombre);

        final SelectItem arbol = new SelectItem();
        arbol.setValue("arbol");
        arbol.setLabel(ComboLocaleManager.get("biblioteca.arbol"));
        buscarPorBiblioteca.add(arbol);

        return buscarPorBiblioteca;
    }

    // ==== getters & setters ====

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(final String contacto) {
        this.contacto = contacto;
    }

    public Long getTipoBiblioteca() {
        return tipoBiblioteca;
    }

    public void setTipoBiblioteca(final Long tipoBiblioteca) {
        this.tipoBiblioteca = tipoBiblioteca;
    }

    public void setBuscarPorBiblioteca(List<SelectItem> buscarPorBiblioteca) {
        this.buscarPorBiblioteca = buscarPorBiblioteca;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public Long getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(Long idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
