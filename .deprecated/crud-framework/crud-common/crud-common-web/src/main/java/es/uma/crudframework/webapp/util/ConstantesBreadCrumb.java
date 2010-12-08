package es.uma.crudframework.webapp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

import edu.emory.mathcs.backport.java.util.Arrays;

public class ConstantesBreadCrumb {

    // estructura para almacenar la rutas anteriores a un viewId
    static Map<String, String[]> rutaCodes;

    // estructura para almacenar las propiedades de un viewId
    static Map<String, String[]> itemCodes;

    static {
        rutaCodes = new HashMap<String, String[]>();
        itemCodes = new HashMap<String, String[]>();

        // =======================RUTAS======================

        // Circulacion

        // antes de politica de prestamos debe aparecer Inicio->Circulación
        rutaCodes.put("/pages/prestamoDom/prestamoDom.xhtml", new String[] {
            "/index.xhtml", "/circulacion.xhtml" });

        rutaCodes.put("/pages/prestamoSala/prestamoSala.xhtml", new String[] {
            "/index.xhtml", "/circulacion.xhtml" });

        rutaCodes.put("/pages/politicaPrestamoDom/list.xhtml", new String[] {
            "/index.xhtml", "/circulacion.xhtml" });

        rutaCodes.put("/pages/asocPolPrestamo/list.xhtml", new String[] {
            "/index.xhtml", "/circulacion.xhtml" });

        rutaCodes.put("/pages/politicaReserva/list.xhtml", new String[] {
            "/index.xhtml", "/circulacion.xhtml" });

        rutaCodes.put("/pages/asocPolReserva/list.xhtml", new String[] {
            "/index.xhtml", "/circulacion.xhtml" });

        rutaCodes.put("/pages/reserva/list.xhtml", new String[] {
            "/index.xhtml", "/circulacion.xhtml" });

        // Administracion y configuracion

        // antes de cambio de clave debe aparecer Administracion y
        // configuracion->Usuarios
        rutaCodes.put("/pages/iaa/gestionUsuarios/cambiarClave.xhtml",
                new String[] { "/administracion.xhtml" });

        rutaCodes.put("/pages/usuario/list.xhtml",
                new String[] { "/administracion.xhtml" });

        rutaCodes.put("/pages/rol/list.xhtml",
                new String[] { "/administracion.xhtml" });

        rutaCodes.put("/pages/biblioteca/list.xhtml",
                new String[] { "/administracion.xhtml" });

        rutaCodes.put("/pages/codigos/list.xhtml",
                new String[] { "/administracion.xhtml" });

        rutaCodes.put("/pages/iaa/autorizar/autorizar.xhtml",
                new String[] { "/administracion.xhtml" });

        rutaCodes.put("/pages/catalogo/list.xhtml",
                new String[] { "/administracion.xhtml" });

        // Lectores

        rutaCodes.put("/pages/lector/listadoLectores.xhtml", new String[] {
            "/index.xhtml", "/lectores.xhtml" });

        // =======================PROPIEDADES ITEM======================
        // las propiedades se guardan en un array fijo de 3 posiciones:
        // [texto, disabled, url]

        itemCodes.put("/index.xhtml", new String[] { "breadcrumb.inicio",
            "true", null });

        // Circulacion

        itemCodes.put("/circulacion.xhtml", new String[] {
            "breadcrumb.circulacion", "true", null });

        itemCodes.put("/pages/prestamoDom/prestamoDom.xhtml", new String[] {
            "breadcrumb.prestamoDomicilio", "false",
            "/librae-circulacion/pages/prestamoDom/prestamoDom.html" });

        itemCodes.put("/pages/prestamoDom/prestamoDom.xhtml", new String[] {
            "breadcrumb.prestamoDomicilio", "true",
            "/librae-circulacion/pages/prestamoDom/prestamoDom.html" });

        itemCodes.put("/pages/politicaPrestamoDom/list.xhtml", new String[] {
            "breadcrumb.politicaPrestamoDom", "false",
            "/librae-circulacion/pages/politicaPrestamoDom/list.html" });

        itemCodes.put("/pages/asocPolPrestamo/form.xhtml", new String[] {
            "breadcrumb.detalleAsocPolPrestamo", "true",
            "/librae-circulacion/pages/asocPolPrestamo/form.html" });

        itemCodes.put("/pages/politicaPrestamoDom/form.xhtml", new String[] {
            "breadcrumb.detallePoliticaPrestamoDom", "true",
            "/librae-circulacion/pages/politicaPrestamoDom/form.html" });

        itemCodes.put("/pages/asocPolPrestamo/list.xhtml", new String[] {
            "breadcrumb.asocPolPrestamo", "false",
            "/librae-circulacion/pages/asocPolPrestamo/list.html" });

        itemCodes.put("/pages/prestamoSala/prestamoSala.xhtml", new String[] {
            "breadcrumb.prestamoSala", "false",
            "/librae-circulacion/pages/prestamoSala/prestamoSala.html" });

        itemCodes.put("/pages/politicaReserva/list.xhtml", new String[] {
            "breadcrumb.politicaReserva", "false",
            "/librae-circulacion/pages/politicaReserva/list.html" });

        itemCodes.put("/pages/asocPolReserva/list.xhtml", new String[] {
            "breadcrumb.asocPolReserva", "false",
            "/librae-circulacion/pages/asocPolReserva/list.html" });

        itemCodes.put("/pages/politicaReserva/list.xhtml", new String[] {
            "breadcrumb.detallePoliticaReserva", "true",
            "/librae-circulacion/pages/politicaReserva/form.html" });

        itemCodes.put("/pages/asocPolReserva/list.xhtml", new String[] {
            "breadcrumb.detalleAsocPolReserva", "true",
            "/librae-circulacion/pages/asocPolReserva/form.html" });

        itemCodes.put("/pages/reserva/list.xhtml", new String[] {
            "breadcrumb.reservas", "true",
            "/librae-circulacion/pages/reserva/list.xhtml" });

        // Administracion y configuracion

        itemCodes.put("/pages/biblioteca/calendario.xhtml", new String[] {
            "breadcrumb.calendario", "true",
            "/librae-adminconfig/pages/biblioteca/calendario.html" });

        itemCodes.put("/pages/prestamoDom/tipoLector.xhtml", new String[] {
            "breadcrumb.tipoLector", "true", null });

        itemCodes.put("/administracion.xhtml", new String[] {
            "breadcrumb.administracion", "true", null });

        itemCodes
                .put("/pages/iaa/gestionUsuarios/cambiarClave.xhtml",
                        new String[] { "breadcrumb.cambioClave", "false",
            "/librae-adminconfig/pages/iaa/gestionUsuarios/cambiarClave.html" });

        itemCodes.put("/pages/usuario/list.xhtml", new String[] {
            "breadcrumb.usuarios", "false",
            "/librae-adminconfig/pages/usuario/list.html" });

        itemCodes.put("/pages/biblioteca/list.xhtml", new String[] {
            "breadcrumb.bibliotecas", "false",
            "/librae-adminconfig/pages/biblioteca/list.html" });

        itemCodes.put("/pages/biblioteca/biblioteca.xhtml", new String[] {
            "breadcrumb.detalleBiblioteca", "true",
            "/librae-adminconfig/pages/biblioteca/biblioteca.html" });

        itemCodes.put("/pages/permisoRol/asignarPermisoRol.xhtml",
                new String[] { "breadcrumb.asignarPermisosARoles", "true",
            "/librae-adminconfig/pages/permisoRol/asignarPermisoRol.html" });

        itemCodes
                .put(
                        "/pages/usuarioBibliotecaRol/asignarUsuario.xhtml",
                        new String[] { "breadcrumb.asignarRolesUnUsuario",
            "true",
            "/librae-adminconfig/pages/usuarioBibliotecaRol/asignarUsuario.html" });

        itemCodes
                .put(
                        "/pages/usuarioBibliotecaRol/asignarUsuarios.xhtml",
                        new String[] { "breadcrumb.asignarRolesAUsuarios",
            "true",
            "/librae-adminconfig/pages/usuarioBibliotecaRol/asignarUsuarios.html" });

        itemCodes.put("/pages/rol/list.xhtml", new String[] {
            "breadcrumb.roles", "false",
            "/librae-adminconfig/pages/rol/list.html" });

        itemCodes.put("/pages/codigos/list.xhtml", new String[] {
            "breadcrumb.codigos", "false",
            "/librae-adminconfig/pages/codigos/list.html" });

        itemCodes.put("/pages/codigos/valores/list.xhtml", new String[] {
            "breadcrumb.detalleCodigo", "true",
            "/librae-adminconfig/pages/codigos/valores/list.html" });

        itemCodes.put("/pages/codigos/valores/form.xhtml", new String[] {
            "breadcrumb.detalleValorCodigo", "true",
            "/librae-adminconfig/pages/codigos/valores/form.html" });

        itemCodes.put("/pages/iaa/autorizar/autorizar.xhtml", new String[] {
            "breadcrumb.autorizar", "true",
            "/librae-adminconfig/pages/iaa/autorizar/autorizar.xhtml" });

        itemCodes.put("/pages/catalogo/list.xhtml", new String[] {
            "breadcrumb.catalogo", "true",
            "/librae-adminconfig/pages/catalogo/list.xhtml" });

        // Lectores

        itemCodes.put("/pages/lector/listadoLectores.xhtml", new String[] {
            "breadcrumb.lectores.listadoLectores", "false",
            "/librae-lectores/pages/lector/listadoLectores.html" });

        itemCodes.put("/pages/lector/edicionLectores.xhtml", new String[] {
            "breadcrumb.lectores.edicionLectores", "false",
            "librae-lectores/pages/lector/edicionLectores.html" });

        // Catalogación
        rutaCodes
                .put(
                        "/pages/registroBibliografico/listadoRegistroBibliografico.xhtml",
                        new String[] { "/index.xhtml" });

        rutaCodes
                .put("/pages/editorCatalogacion/editorCatalogacion.xhtml",
                        new String[] { "/index.xhtml", "/catalogacion.xhtml",
            "/pages/registroBibliografico/listadoRegistroBibliografico.xhtml" });

        itemCodes
                .put(
                        "/pages/registroBibliografico/listadoRegistroBibliografico.xhtml",
                        new String[] {
            "breadcrumb.catalogacion.listadoRegistro",
            "false",
            "/librae-catalogacion/pages/registroBibliografico/listadoRegistroBibliografico.html" });

        itemCodes
                .put(
                        "/pages/editorCatalogacion/editorCatalogacion.xhtml",
                        new String[] { "breadcrumb.catalogacion.editor",
            "true",
            "/librae-catalogacion/pages/editorCatalogacion/editorCatalogacion.html" });

        rutaCodes.put("/pages/autoridad/listadoAutoridad.xhtml",
                new String[] { "/index.xhtml" });

        rutaCodes.put(
                "/pages/editorCatalogacion/editorCatalogacionAutoridad.xhtml",
                new String[] { "/index.xhtml", "/catalogacion.xhtml",
            "/pages/autoridad/listadoAutoridad.xhtml" });

        itemCodes.put("/pages/autoridad/listadoAutoridad.xhtml", new String[] {
            "breadcrumb.catalogacion.listadoAutoridad", "false",
            "/librae-catalogacion/pages/autoridad/listadoAutoridad.html" });

        itemCodes
                .put(
                        "/pages/editorCatalogacion/editorCatalogacionAutoridad.xhtml",
                        new String[] {
            "breadcrumb.catalogacion.editorAutoridad",
            "true",
            "/librae-catalogacion/pages/editorCatalogacion/editorCatalogacionAutoridad.html" });

        rutaCodes.put("/pages/ejemplar/listadoEjemplares.xhtml",
                new String[] { "/index.xhtml" });

        itemCodes.put("/pages/ejemplar/listadoEjemplares.xhtml", new String[] {
            "breadcrumb.catalogacion.listadoEjemplares", "false",
            "/librae-catalogacion/pages/ejemplar/listadoEjemplares.html" });

        rutaCodes.put("/pages/ejemplar/verEjemplar.xhtml", new String[] {
            "/index.xhtml", "/catalogacion.xhtml",
            "/pages/ejemplar/listadoEjemplares.xhtml" });

        itemCodes.put("/pages/ejemplar/verEjemplar.xhtml", new String[] {
            "breadcrumb.catalogacion.verEjemplar", "true",
            "/librae-catalogacion/pages/ejemplar/verEjemplar.html" });

    }

    /**
     * Método encargado de obtener un listado de los identificadores de ruta
     * anteriores al especificado
     * 
     * @param viewId
     * @return Lista de identificadores
     */
    public static List<String> getRuta(final String viewId) {
        final List<String> res = new ArrayList<String>();

        final String[] rutas = rutaCodes.get(viewId);
        if (rutas != null) {
            res.addAll(Arrays.asList(rutas));
        }

        return res;
    }

    /**
     * Método encargado de construir un BreadCrumItem a partir de las
     * propiedades del viewId indicado
     * 
     * @param viewId
     * @return BreadCrumbItem
     */
    public static BreadCrumbItem getItem(final String viewId) {
        BreadCrumbItem res = null;

        final String[] atributos = itemCodes.get(viewId);
        if (atributos != null && atributos.length == 3) {
            res = new BreadCrumbItem(viewId, getLabel(atributos[0]),
                    atributos[1], atributos[2]);
        }

        return res;
    }

    private static String getLabel(final String key) {
        String res = "";
        final Locale locale = LocaleContextHolder.getLocale();

        try {
            final ResourceBundle properti = ResourceBundle.getBundle(
                    "org.librae.common.messages", locale);
            res = properti.getString(key);
        } catch (final Exception e) {
            // se ignora
        }

        return res;
    }
}
