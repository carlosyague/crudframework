package org.librae.common.webapp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import edu.emory.mathcs.backport.java.util.Arrays;

public class ConstantesBreadCrumb {

    // estructura para almacenar la rutas anteriores a un viewId
    static Map<String, String[]> rutaCodes;

    // estructura para almacenar las propiedades de un viewId
    static Map<String, String[]> itemCodes;

    // estructura para almacenar los anclas de la ayuda
    static Map<String, String>   helpCodes;

    private static final String  CIRCULACION_XHTML                 = "/circulacion.xhtml";
    private static final String  INDEX_XHTML                       = "/index.xhtml";
    private static final String  ADMINISTRACION_XHTML              = "/administracion.xhtml";
    private static final String  IMPORTEXPORT_XHTML                = "/importexport.xhtml";
    private static final String  ADQUISICION_XHTML                 = "/adquisiciones.xhtml";
    private static final String  DESIDERATAS_XHTML                 = "/desideratas.xhtml";
    private static final String  DIRECCION_XHTML                   = "/direccion.xhtml";
    private static final String  PLANIFICACION_PROCESOS_XHTML      = "/planificaciones.xhtml";
    private static final String  PRESTAMO_RED_XHTML                = "/prestamoRed.xhtml";
    private static final String  USUARIO_XHTML                     = "/usuario.xhtml";
    private static final String  PRESTAMO_DOMICILIO_XHTML          = "/prestamoDom.xhtml";
    private static final String  RESERVAS_XHTML                    = "/reservas.xhtml";
    private static final String  PRESTAMO_INTERBIBLIOTECARIO_XHTML = "/prestamoInterbibliotecario.xhtml";
    private static final String  CATALOGACION_XHTML                = "/catalogacion.xhtml";
    private static final String  AUTORIDAD_XHTML                   = "/listadoAutoridad.xhtml";
    private static final String  PUBSERIADAS_XHTML                 = "/pubSeriadas.xhtml";
    private static final String  BIB_DIGITAL_XHTML                 = "/bibliotecaDigital.xhtml";

    private static final String  INFORMES_XHTML                    = "/informes.xhtml";
    private static final String  ADMINISTRACION_INFORMES_XHTML     = "/administrarInformes.xhtml";
    private static final String  GENERACION_INFORMES_XHTML         = "/generacionInformes.xhtml";
    private static final String  MENSAJERIA_COMUNICACIONES_XHTML   = "/mensajeriaComunicaciones.xhtml";
    private static final String  MENSAJERIA_XHTML                  = "/mensajeria.xhtml";
    private static final String  BUSCAR_CATALOGO_DSI               = "/crudCatalogoDSI.xhtml";
    private static final String  LISTADO_DESIDERATA_XHTML          = "/busquedaDesideratasBibliotecario.xhtml";

    private static final String  LISTADO_PROVEEDORES_XHTML         = "/busquedaProveedores.xhtml";
    private static final String  PROVEEDOR_XHTML                   = "/edicionProveedores.xhtml";
    private static final String  DIRECC_POSTAL_XHTML               = "/crudDireccionPostal.xhtml";

    protected final static Log   log                               = LogFactory
                                                                           .getLog(ConstantesBreadCrumb.class);

    static {
        rutaCodes = new HashMap<String, String[]>();
        itemCodes = new HashMap<String, String[]>();
        helpCodes = new HashMap<String, String>();

        // =======================RUTAS======================

        // Circulacion

        // antes de politica de prestamos debe aparecer Inicio->Circulación
        rutaCodes.put("/pages/prestamoDom/prestamoDom.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML });

        rutaCodes.put("/pages/prestamoSala/prestamoSala.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML });

        rutaCodes.put("/pages/politicaPrestamoDom/list.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML, PRESTAMO_DOMICILIO_XHTML });

        rutaCodes.put("/pages/asocPolPrestamo/list.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML, PRESTAMO_DOMICILIO_XHTML });

        rutaCodes.put("/pages/politicaReserva/list.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML, PRESTAMO_DOMICILIO_XHTML });

        rutaCodes.put("/pages/asocPolReserva/list.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML, PRESTAMO_DOMICILIO_XHTML });

        rutaCodes.put("/pages/reserva/list.xhtml", new String[] { INDEX_XHTML,
            CIRCULACION_XHTML });

        rutaCodes.put("/pages/consorcio/list.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML, PRESTAMO_RED_XHTML});

        rutaCodes.put("/pages/bibliotecaConsorciada/list.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML, PRESTAMO_RED_XHTML });

        rutaCodes.put("/pages/prestamoConsorcio/prestamoConsorcio.xhtml",
                new String[] { INDEX_XHTML, CIRCULACION_XHTML,
            PRESTAMO_RED_XHTML });

        rutaCodes
                .put("/pages/peticionPIB/list.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML, PRESTAMO_INTERBIBLIOTECARIO_XHTML });

        rutaCodes
                .put("/pages/bibliotecaExt/list.xhtml", new String[] {
            INDEX_XHTML, CIRCULACION_XHTML, PRESTAMO_INTERBIBLIOTECARIO_XHTML });

        // Administracion y configuracion

        // antes de cambio de clave debe aparecer Administracion y
        // configuracion->Usuarios
        rutaCodes.put("/pages/iaa/gestionUsuarios/cambiarClave.xhtml",
                new String[] { ADMINISTRACION_XHTML, USUARIO_XHTML });

        rutaCodes.put("/pages/usuario/list.xhtml", new String[] {
            ADMINISTRACION_XHTML, USUARIO_XHTML });

        rutaCodes.put("/pages/rol/list.xhtml",
                new String[] { ADMINISTRACION_XHTML });

        rutaCodes.put("/pages/biblioteca/list.xhtml",
                new String[] { ADMINISTRACION_XHTML });

        rutaCodes.put("/pages/codigos/list.xhtml",
                new String[] { ADMINISTRACION_XHTML });

        rutaCodes.put("/pages/iaa/autorizar/autorizar.xhtml",
                new String[] { ADMINISTRACION_XHTML });

        rutaCodes.put("/pages/catalogo/list.xhtml",
                new String[] { ADMINISTRACION_XHTML });

        rutaCodes.put("/pages/parametro/list.xhtml",
                new String[] { ADMINISTRACION_XHTML });

        rutaCodes.put("/pages/favoritos/list.xhtml", new String[] {});
        
        rutaCodes.put("/pages/tipoLector/list.xhtml",
                new String[] { ADMINISTRACION_XHTML });
        
        rutaCodes.put("/pages/tipoEjemplar/list.xhtml",
                new String[] { ADMINISTRACION_XHTML });

        // Localizadores

        rutaCodes.put("/pages/prestamoDom/mensajeCirculacion.xhtml", new String[] {
        		INDEX_XHTML,CIRCULACION_XHTML, "/pages/prestamoDom/prestamoDom.xhtml"});

        rutaCodes.put("/pages/biblioteca/listLocalizador.xhtml", new String[] {
        		INDEX_XHTML,CIRCULACION_XHTML ,PRESTAMO_INTERBIBLIOTECARIO_XHTML,
        		"/pages/peticionPIB/list.xhtml"});

        rutaCodes
                .put("/pages/rol/listLocalizador.xhtml", new String[] {
            ADMINISTRACION_XHTML, USUARIO_XHTML, "/pages/usuarios/form.xhtml" });

        rutaCodes.put("/pages/bibliotecaExt/listLocalizador.xhtml",
                new String[] { INDEX_XHTML,CIRCULACION_XHTML ,PRESTAMO_INTERBIBLIOTECARIO_XHTML,
        		"/pages/peticionPIB/list.xhtml" });

        rutaCodes.put("/pages/consorcio/listLocalizador.xhtml", new String[] {
        		INDEX_XHTML,CIRCULACION_XHTML,PRESTAMO_RED_XHTML, 
        		"/pages/bibliotecaConsorciada/list.xhtml" });

        rutaCodes.put("/pages/bibliotecaConsorciada/listLocalizador.xhtml",
                new String[] { INDEX_XHTML,CIRCULACION_XHTML,
        "/pages/prestamoCosorcio/prestamoConsorcio.xhtml" });
        
        rutaCodes.put("/pages/permiso/listLocalizador.xhtml",
                new String[] { ADMINISTRACION_XHTML, 
        		"/pages/rol/list.xhtml","/pages/rol/form.xhtml","/pages/permisoRol/asignarPermisoRol.xhtml" });

        // Lectores

        rutaCodes.put("/pages/lector/listadoLectores.xhtml", new String[] {
            INDEX_XHTML, "/lectores.xhtml" });

        // Importacion y exportacion

        rutaCodes.put("/pages/transformJob/list.xhtml", new String[] {
            ADMINISTRACION_XHTML, IMPORTEXPORT_XHTML });

        // Planificación de procesos.
        // rutaCodes.put("pages/gestionProcesos/buscar.xhtml", new String[]{
        // INDEX_XHTML, PLANIFICACION_PROCESOS_XHTML});

        rutaCodes.put(
                "/pages/planificacionProcesos/planificacionProcesos.xhtml",
                new String[] { INDEX_XHTML, PLANIFICACION_PROCESOS_XHTML });

        rutaCodes.put("/pages/gestionProcesos/buscar.xhtml", new String[] {
            INDEX_XHTML, PLANIFICACION_PROCESOS_XHTML });

        // =================================
        // RUTACODES PARA BIBLIOTECA DIGITAL
        // =================================

        // Proveedor

        rutaCodes.put("/pages/bibdigital/asociacionAutomatica.xhtml",
                new String[] { INDEX_XHTML, BIB_DIGITAL_XHTML });

        // ============================
        // RUTACODES PARA ADQUISICIONES
        // ============================

        // Proveedor

        rutaCodes.put("/pages/proveedores/busquedaProveedores.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML });

        rutaCodes.put("/pages/proveedores/edicionProveedores.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            LISTADO_PROVEEDORES_XHTML });

        rutaCodes.put(
                "/pages/proveedores/direccionPostal/crudDireccionPostal.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            LISTADO_PROVEEDORES_XHTML, PROVEEDOR_XHTML });

        rutaCodes
                .put(
                        "/pages/proveedores/direccionPostal/contactos/crudContactos.xhtml",
                        new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            LISTADO_PROVEEDORES_XHTML, PROVEEDOR_XHTML, DIRECC_POSTAL_XHTML });

        // Desiderata

        rutaCodes.put("/pages/desideratas/editarDesiderata.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            DESIDERATAS_XHTML, LISTADO_DESIDERATA_XHTML });

        // Inicio > Adquisiciones > Desideratas > Listado de motivos de rechazo
        rutaCodes.put("/pages/desideratas/motivosRechazo/listadoMotivos.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            DESIDERATAS_XHTML });
        // Inicio > Adquisiciones > Desideratas > Crud de motivos de rechazo
        rutaCodes.put(
                "/pages/desideratas/motivosRechazo/crudMotivosRechazo.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            DESIDERATAS_XHTML });

        // Inicio > Adquisiciones > Desideratas > Búsqueda de desideratas
        rutaCodes.put(
                "/pages/desideratas/busquedaDesideratasBibliotecario.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            DESIDERATAS_XHTML });

        // Inicio > Adquisiciones > Desideratas > Rechazar desideratas
        rutaCodes.put("/pages/desideratas/rechazarDesiderata.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            DESIDERATAS_XHTML });
        // Inicio > Adquisiciones > Desideratas > Aceptar desideratas
        rutaCodes.put("/pages/desideratas/aceptarDesiderata.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            DESIDERATAS_XHTML });
        // Inicio > Adquisiciones > Desideratas > Asociar desiderata a un pedido
        // existente
        rutaCodes.put("/pages/desideratas/asociarDesiderataAPedido.xhtml",
                new String[] { INDEX_XHTML, ADQUISICION_XHTML,
            DESIDERATAS_XHTML });

        // Estadisticas e informes
        // Inicio > Informes > Gestión de informes > Buscar informes
        rutaCodes.put("/pages/gestionInformes/buscarGenerarInformes.xhtml",
                new String[] { INDEX_XHTML, INFORMES_XHTML,
            GENERACION_INFORMES_XHTML });

        // Inicio > Informes > Gestión de informes > Introducir Parámetros
        rutaCodes.put("/pages/gestionInformes/introducirParametros.xhtml",
                new String[] { INDEX_XHTML, INFORMES_XHTML,
            GENERACION_INFORMES_XHTML });
        // Inicio > Informes > Administración de informes > Búsqueda de
        // parámetros
        rutaCodes.put("/pages/administrarInformes/buscarParametros.xhtml",
                new String[] { INDEX_XHTML, INFORMES_XHTML,
            ADMINISTRACION_INFORMES_XHTML });

        // Inicio > Informes > Administración de informes > Crud de informes
        rutaCodes.put("/pages/administrarInformes/crudInformes.xhtml",
                new String[] { INDEX_XHTML, INFORMES_XHTML,
            ADMINISTRACION_INFORMES_XHTML });
        // Inicio > Informes > Administración de informes > Crud de parámetros
        rutaCodes.put("/pages/administrarInformes/crudParametros.xhtml",
                new String[] { INDEX_XHTML, INFORMES_XHTML,
            ADMINISTRACION_INFORMES_XHTML });

        // Inicio > Informes > Administración de informes > Asignar parámetro
        rutaCodes.put(
                "/pages/administrarInformes/asignarParametroInforme.xhtml",
                new String[] { INDEX_XHTML, INFORMES_XHTML,
            ADMINISTRACION_INFORMES_XHTML });

        // Inicio > Informes > Administración de informes > buscar informes
        rutaCodes.put("/pages/administrarInformes/buscarInformes.xhtml",
                new String[] { INDEX_XHTML, INFORMES_XHTML,
            ADMINISTRACION_INFORMES_XHTML });

        // Inicio > Informes > Administración de informes > Asignar Plantilla
        rutaCodes.put("/pages/administrarInformes/crudPlantillas.xhtml",
                new String[] { INDEX_XHTML, INFORMES_XHTML,
            ADMINISTRACION_INFORMES_XHTML });

        // =========================
        // RUTACODES PARA MENSAJERIA
        // =========================

        rutaCodes.put("/pages/buscarCatalogoDSI.xhtml", new String[] {
            INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML, MENSAJERIA_XHTML });

        rutaCodes.put("/pages/crudCatalogoDSI.xhtml", new String[] {
            INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML, MENSAJERIA_XHTML,
            BUSCAR_CATALOGO_DSI });

        rutaCodes.put("/pages/detalleNotificacion.xhtml", new String[] {
            INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML, MENSAJERIA_XHTML });
        rutaCodes.put("/pages/buscarCatalogoDSI.xhtml", new String[] {
            INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML, MENSAJERIA_XHTML });
        rutaCodes.put("/pages/consultarNotificacionesDSIbibliotecario.xhtml",
                new String[] { INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML,
            MENSAJERIA_XHTML });

        rutaCodes.put("/pages/consultarTareasPendientes.xhtml", new String[] {
            INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML, MENSAJERIA_XHTML });

        rutaCodes.put("/pages/busquedaNotificacionesLector.xhtml",
                new String[] { INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML,
            MENSAJERIA_XHTML });
        rutaCodes.put("/pages/crudCatalogoDSI.xhtml", new String[] {
            INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML, MENSAJERIA_XHTML });

        rutaCodes.put("/pages/consultarNotificacionesSubsistemas.xhtml",
                new String[] { INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML,
            MENSAJERIA_XHTML });

        rutaCodes.put("/pages/detalleNotificacion.xhtml", new String[] {
            INDEX_XHTML, MENSAJERIA_COMUNICACIONES_XHTML, MENSAJERIA_XHTML });

        // =======================PROPIEDADES ITEM======================
        // las propiedades se guardan en un array fijo de 3 posiciones:
        // [texto, disabled, url]

        itemCodes.put(INDEX_XHTML, new String[] { "breadcrumb.inicio",
            Boolean.TRUE.toString(), null });

        // Circulacion

        itemCodes.put("/pages/prestamoCosorcio/prestamoConsorcio.xhtml",
                new String[] { "breadcrumb.prestamoRed",
            Boolean.TRUE.toString(), null });

        itemCodes.put("/pages/bibliotecaConsorciada/listLocalizador.xhtml",
               new String[] { "breadcrumb.consorciosBib.listado",
            Boolean.TRUE.toString(), null });

        itemCodes.put(CIRCULACION_XHTML, new String[] {
            "breadcrumb.circulacion", Boolean.TRUE.toString(), null });

        itemCodes.put(PRESTAMO_RED_XHTML, new String[] {
            "breadcrumb.prestamoRed", Boolean.TRUE.toString(), null });

        itemCodes.put(USUARIO_XHTML, new String[] { "breadcrumb.usuarios",
            Boolean.TRUE.toString(), null });

        itemCodes.put(RESERVAS_XHTML, new String[] { "breadcrumb.reservas",
            Boolean.TRUE.toString(), null });

        itemCodes.put(PRESTAMO_INTERBIBLIOTECARIO_XHTML, new String[] {
            "breadcrumb.prestamoInterbibliotecario", Boolean.TRUE.toString(),
            null });

        itemCodes.put(PRESTAMO_DOMICILIO_XHTML, new String[] {
            "breadcrumb.gestionPrestamo", Boolean.TRUE.toString(), null });

        itemCodes.put("/pages/prestamoDom/prestamoDom.xhtml", new String[] {
            "breadcrumb.prestamoDomicilio", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/prestamoDom/prestamoDom.html" });

        itemCodes.put("/pages/politicaPrestamoDom/list.xhtml", new String[] {
            "breadcrumb.politicaPrestamoDom", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/politicaPrestamoDom/list.html" });

        itemCodes.put("/pages/asocPolPrestamo/form.xhtml", new String[] {
            "breadcrumb.detalleAsocPolPrestamo", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/asocPolPrestamo/form.html" });

        itemCodes.put("/pages/prestamo/prestamo.xhtml", new String[] {
            "breadcrumb.detallePrestamoDomicilio", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/prestamo/prestamo.html" });

        itemCodes.put("/pages/prestamoSala/prestamo.xhtml", new String[] {
            "breadcrumb.detallePrestamoDomicilio", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/prestamoSala/prestamo.html" });

        itemCodes.put("/pages/politicaPrestamoDom/form.xhtml", new String[] {
            "breadcrumb.detallePoliticaPrestamoDom", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/politicaPrestamoDom/form.html" });

        itemCodes.put("/pages/asocPolPrestamo/list.xhtml", new String[] {
            "breadcrumb.asocPolPrestamo", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/asocPolPrestamo/list.html" });

        itemCodes.put("/pages/prestamoSala/prestamoSala.xhtml", new String[] {
            "breadcrumb.prestamoSala", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/prestamoSala/prestamoSala.html" });

        itemCodes.put("/pages/politicaReserva/list.xhtml", new String[] {
            "breadcrumb.politicaReserva", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/politicaReserva/list.html" });

        itemCodes.put("/pages/asocPolReserva/list.xhtml", new String[] {
            "breadcrumb.asocPolReserva", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/asocPolReserva/list.html" });

        itemCodes.put("/pages/politicaReserva/form.xhtml", new String[] {
            "breadcrumb.detallePoliticaReserva", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/politicaReserva/form.html" });

        itemCodes.put("/pages/asocPolReserva/form.xhtml", new String[] {
            "breadcrumb.detalleAsocPolReserva", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/asocPolReserva/form.html" });

        itemCodes.put("/pages/reserva/list.xhtml", new String[] {
            "breadcrumb.reservas", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/reserva/list.html" });

        itemCodes.put("/pages/consorcio/list.xhtml", new String[] {
            "breadcrumb.consorcios.listado", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/consorcio/list.html" });

        itemCodes.put("/pages/consorcio/form.xhtml", new String[] {
            "breadcrumb.consorcios.detalle", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/consorcio/form.html" });

        itemCodes.put("/pages/bibliotecaConsorciada/list.xhtml", new String[] {
            "breadcrumb.consorciosBib.listado", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/bibliotecaConsorciada/list.html" });

        itemCodes.put("/pages/bibliotecaConsorciada/form.xhtml", new String[] {
            "breadcrumb.consorciosBib.detalle", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/bibliotecaConsorciada/form.html" });

        itemCodes
                .put(
                        "/pages/prestamoConsorcio/prestamoConsorcio.xhtml",
                        new String[] { "breadcrumb.consorios",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/prestamoConsorcio/prestamoConsorcio.xhtml" });

        itemCodes
                .put("/pages/reserva/include/add/addEjemplar.xhtml",
                        new String[] { "breadcrumb.addReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/add/addEjemplar.xhtml" });

        itemCodes
                .put("/pages/reserva/include/add/addRegistro.xhtml",
                        new String[] { "breadcrumb.addReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/add/addRegistro.xhtml" });

        itemCodes
                .put(
                        "/pages/reserva/include/add/addEjemplarPrestamo.xhtml",
                        new String[] { "breadcrumb.addReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/add/addEjemplarPrestamo.xhtml" });

        itemCodes
                .put(
                        "/pages/reserva/include/add/addEjemplarSala.xhtml",
                        new String[] { "breadcrumb.addReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/add/addEjemplarSala.xhtml" });

        itemCodes
                .put("/pages/reserva/include/add/addRegistroPrestamo.xhtml",
                        new String[] { "breadcrumb.addReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/add/addEjemplar.xhtml" });

        itemCodes
                .put(
                        "/pages/reserva/include/add/addRegistroSala.xhtml",
                        new String[] { "breadcrumb.addReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/add/addRegistroSala.xhtml" });

        itemCodes
                .put(
                        "/pages/reserva/include/edit/editEjemplar.xhtml",
                        new String[] { "breadcrumb.detalleReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/edit/editEjemplar.xhtml" });

        itemCodes
                .put(
                        "/pages/reserva/include/edit/editRegistro.xhtml",
                        new String[] { "breadcrumb.detalleReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/edit/editRegistro.xhtml" });

        itemCodes
                .put(
                        "/pages/reserva/include/edit/editEjemplarPrestamo.xhtml",
                        new String[] { "breadcrumb.detalleReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/edit/editEjemplarPrestamo.xhtml" });

        itemCodes
                .put(
                        "/pages/reserva/include/edit/editEjemplarSala.xhtml",
                        new String[] { "breadcrumb.detalleReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/edit/editEjemplarSala.xhtml" });

        itemCodes
                .put(
                        "/pages/reserva/include/edit/editRegistroPrestamo.xhtml",
                        new String[] { "breadcrumb.detalleReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/edit/editRegistroPrestamo.xhtml" });

        itemCodes
                .put(
                        "/pages/reserva/include/edit/editRegistroSala.xhtml",
                        new String[] { "breadcrumb.detalleReserva",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/reserva/include/edit/editRegistroSala.xhtml" });

        itemCodes.put("/pages/peticionPIB/list.xhtml", new String[] {
            "breadcrumb.peticionPIB", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/peticionPIB/list.html" });

        itemCodes.put("/pages/bibliotecaExt/list.xhtml", new String[] {
            "breadcrumb.bibliotecaExt.listado", Boolean.FALSE.toString(),
            "/librae-circulacion/pages/bibliotecaExt/list.html" });

        itemCodes.put("/pages/bibliotecaExt/form.xhtml", new String[] {
            "breadcrumb.bibliotecaExt.detalle", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/bibliotecaExt/form.html" });

        itemCodes.put("/pages/contactoPIB/list.xhtml", new String[] {
            "breadcrumb.contactoPIB.listado", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/contactoPIB/list.html" });

        itemCodes.put("/pages/contactoPIB/form.xhtml", new String[] {
            "breadcrumb.contactoPIB.detalle", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/contactoPIB/form.html" });

        itemCodes.put("/pages/prestamosDom/mensajeCirculacion.xhtml",
                new String[] { "breadcrumb.mensajeCirculacion.detalle",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/prestamosDom/mensajeCirculacion.html" });

        itemCodes.put("/pages/prestamoDom/mensajeCirculacion.xhtml",
                new String[] { "breadcrumb.mensajeCirculacion.detalle",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/prestamoDom/mensajeCirculacion.html" });

        itemCodes.put("/pages/bibliotecaExt/listLocalizador.xhtml",
                new String[] { "breadcrumb.localizador.bibliotecaExterna",
            Boolean.TRUE.toString(),
            "/librae-circulacion/pages/bibliotecaExt/listLocalizador.html" });

        itemCodes.put("/pages/consorcio/listLocalizador.xhtml", new String[] {
            "breadcrumb.localizador.consorcio", Boolean.TRUE.toString(),
            "/librae-circulacion/pages/consorcio/listLocalizador.html" });
        
        itemCodes.put("/pages/tipoEjemplar/list.xhtml", new String[] {
                "breadcrumb.tipoEjemplar.listado", Boolean.FALSE.toString(),
                "/librae-circulacion/pages/tipoEjemplar/list.html" });

        itemCodes.put("/pages/tipoEjemplar/form.xhtml", new String[] {
               "breadcrumb.tipoEjemplar.detalle", Boolean.TRUE.toString(),
               "/librae-circulacion/pages/tipoEjemplar/form.html" });
        
        itemCodes.put("/pages/tipoLector/list.xhtml", new String[] {
                "breadcrumb.tipoLector.listado", Boolean.FALSE.toString(),
                "/librae-circulacion/pages/tipoLector/list.html" });

        itemCodes.put("/pages/tipoLector/form.xhtml", new String[] {
               "breadcrumb.tipoLector.detalle", Boolean.TRUE.toString(),
               "/librae-circulacion/pages/tipoLector/form.html" });

        // Administracion y configuracion

        itemCodes.put("/pages/biblioteca/calendario.xhtml", new String[] {
            "breadcrumb.calendario", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/biblioteca/calendario.html" });

        itemCodes.put("/pages/horariosInt/form.xhtml", new String[] {
            "breadcrumb.horarioInt", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/horariosInt/form.html" });

        itemCodes.put("/pages/horariosInt/list.xhtml", new String[] {
            "breadcrumb.horario", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/horariosInt/list.html" });

        itemCodes.put("/pages/direcciones/form.xhtml", new String[] {
            "breadcrumb.direccion", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/direcciones/form.html" });

        itemCodes.put("/pages/prestamoDom/tipoLector.xhtml", new String[] {
            "breadcrumb.tipoLector", Boolean.TRUE.toString(), null });

        itemCodes.put(ADMINISTRACION_XHTML, new String[] {
            "breadcrumb.administracion", Boolean.TRUE.toString(), null });

        itemCodes
                .put("/pages/iaa/gestionUsuarios/cambiarClave.xhtml",
                        new String[] { "breadcrumb.cambioClave",
            Boolean.FALSE.toString(),
            "/librae-adminconfig/pages/iaa/gestionUsuarios/cambiarClave.html" });

        itemCodes.put("/pages/usuario/list.xhtml", new String[] {
            "breadcrumb.usuarios", Boolean.FALSE.toString(),
            "/librae-adminconfig/pages/usuario/list.html" });

        itemCodes.put("/pages/biblioteca/list.xhtml", new String[] {
            "breadcrumb.bibliotecas", Boolean.FALSE.toString(),
            "/librae-adminconfig/pages/biblioteca/list.html" });

        itemCodes.put("/pages/biblioteca/biblioteca.xhtml", new String[] {
            "breadcrumb.detalleBiblioteca", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/biblioteca/biblioteca.html" });

        itemCodes
                .put("/pages/biblioteca/bibliotecaSoloLectura.xhtml",
                        new String[] { "breadcrumb.detalleBiblioteca",
            Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/biblioteca/bibliotecaSoloLectura.html" });

        itemCodes.put("/pages/permisoRol/asignarPermisoRol.xhtml",
                new String[] { "breadcrumb.asignarPermisosARoles",
            Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/permisoRol/asignarPermisoRol.html" });

        itemCodes
                .put(
                        "/pages/usuarioBibliotecaRol/asignarUsuario.xhtml",
                        new String[] { "breadcrumb.asignarRolesUnUsuario",
            Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/usuarioBibliotecaRol/asignarUsuario.html" });

        itemCodes
                .put(
                        "/pages/usuarioBibliotecaRol/asignarUsuarios.xhtml",
                        new String[] { "breadcrumb.asignarRolesAUsuarios",
            Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/usuarioBibliotecaRol/asignarUsuarios.html" });

        itemCodes.put("/pages/rol/list.xhtml", new String[] {
            "breadcrumb.roles", Boolean.FALSE.toString(),
            "/librae-adminconfig/pages/rol/list.html" });

        itemCodes.put("/pages/codigos/list.xhtml", new String[] {
            "breadcrumb.codigos", Boolean.FALSE.toString(),
            "/librae-adminconfig/pages/codigos/list.html" });

        itemCodes.put("/pages/codigos/valores/list.xhtml", new String[] {
            "breadcrumb.detalleCodigo", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/codigos/valores/list.html" });

        itemCodes.put("/pages/codigos/valores/form.xhtml", new String[] {
            "breadcrumb.detalleValorCodigo", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/codigos/valores/form.html" });

        itemCodes.put("/pages/iaa/autorizar/autorizar.xhtml", new String[] {
            "breadcrumb.autorizar", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/iaa/autorizar/autorizar.html" });

        itemCodes.put("/pages/catalogo/list.xhtml", new String[] {
            "breadcrumb.catalogos", Boolean.FALSE.toString(),
            "/librae-adminconfig/pages/catalogo/list.html" });

        itemCodes.put("/pages/catalogo/form.xhtml", new String[] {
            "breadcrumb.detalleCatalogo", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/catalogo/form.html" });

        itemCodes.put("/pages/parametro/list.xhtml", new String[] {
            "breadcrumb.parametro.listado", Boolean.FALSE.toString(),
            "/librae-adminconfig/pages/parametro/list.html" });

        itemCodes.put("/pages/parametro/form.xhtml", new String[] {
            "breadcrumb.parametro.detalle", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/parametro/form.html" });

        itemCodes.put("/pages/rol/form.xhtml", new String[] {
            "breadcrumb.rol.detalle", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/rol/form.html" });

        itemCodes.put("/pages/usuario/form.xhtml", new String[] {
            "breadcrumb.usuario.detalle", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/usuario/form.xhtml" });

        itemCodes.put("/pages/favoritos/list.xhtml", new String[] {
            "breadcrumb.favorito.listado", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/favoritos/list.html" });

        itemCodes.put("/pages/rol/listLocalizador.xhtml", new String[] {
            "breadcrumb.localizador.roles", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/rol/listLocalizador.html" });

        itemCodes.put("/pages/biblioteca/listLocalizador.xhtml", new String[] {
            "breadcrumb.localizador.biblioteca", Boolean.TRUE.toString(),
            "/librae-adminconfig/pages/biblioteca/listLocalizador.html" });
        
        itemCodes.put("/pages/permiso/listLocalizador.xhtml", new String[] {
                "breadcrumb.localizador.permiso.rol", Boolean.TRUE.toString(),
                "/librae-adminconfig/pages/permiso/listLocalizador.html" });

        // Lectores

        itemCodes.put("/pages/lector/listadoLectores.xhtml", new String[] {
            "breadcrumb.lectores.listadoLectores", Boolean.FALSE.toString(),
            "/librae-lectores/pages/lector/listadoLectores.html" });

        itemCodes.put("/pages/lector/edicionLectores.xhtml", new String[] {
            "breadcrumb.lectores.edicionLectores", Boolean.TRUE.toString(),
            "librae-lectores/pages/lector/edicionLectores.html" });

        itemCodes.put("/pages/lector/edicionLectoresSoloLectura.xhtml",
                new String[] { "breadcrumb.lectores.edicionLectores",
            Boolean.TRUE.toString(),
            "librae-lectores/pages/lector/edicionLectoresSoloLectura.html" });

        // Publicaciones seriadas
        rutaCodes.put("/pages/periodicidad/listadoPeriodicidad.xhtml",
                new String[] { INDEX_XHTML, PUBSERIADAS_XHTML });

        rutaCodes.put("/pages/suscripcion/listadoSuscripcion.xhtml",
                new String[] { INDEX_XHTML, PUBSERIADAS_XHTML });

        rutaCodes.put(
                "/pages/modoVisualizacion/listadoModoVisualizacion.xhtml",
                new String[] { INDEX_XHTML, PUBSERIADAS_XHTML });

        rutaCodes.put("/pages/periodicidad/form.xhtml", new String[] {
            INDEX_XHTML, PUBSERIADAS_XHTML,
            "/pages/periodicidad/listadoPeriodicidad.xhtml" });

        rutaCodes.put("/pages/suscripcion/form.xhtml", new String[] {
            INDEX_XHTML, PUBSERIADAS_XHTML,
            "/pages/suscripcion/listadoSuscripcion.xhtml" });

        rutaCodes.put("/pages/numero/form.xhtml", new String[] { INDEX_XHTML,
            PUBSERIADAS_XHTML, "/pages/suscripcion/listadoSuscripcion.xhtml",
            "/pages/suscripcion/form.xhtml" });

        rutaCodes.put("/pages/modoVisualizacion/form.xhtml", new String[] {
            INDEX_XHTML, PUBSERIADAS_XHTML,
            "/pages/modoVisualizacion/listadoModoVisualizacion.xhtml" });

        rutaCodes.put("/pages/suscripcion/arbolSuscripciones.xhtml",
                new String[] { INDEX_XHTML, PUBSERIADAS_XHTML,
            "/pages/suscripcion/listadoSuscripcion.xhtml",
            "/pages/suscripcion/form.xhtml" });

        itemCodes.put(PUBSERIADAS_XHTML, new String[] {
            "breadcrumb.pubSeriadas", Boolean.TRUE.toString(), null });

        itemCodes
                .put(
                        "/pages/periodicidad/listadoPeriodicidad.xhtml",
                        new String[] { "breadcrumb.searchPeriodicidades",
            Boolean.TRUE.toString(),
            "/librae-pubseriadas/pages/periodicidad/listadoPeriodicidad.xhtml" });

        itemCodes.put("/pages/suscripcion/listadoSuscripcion.xhtml",
                new String[] { "breadcrumb.searchSuscripciones",
            Boolean.TRUE.toString(),
            "/librae-pubseriadas/pages/suscripcion/listadoSuscripcion.xhtml" });

        itemCodes
                .put(
                        "/pages/modoVisualizacion/listadoModoVisualizacion.xhtml",
                        new String[] { "breadcrumb.searchModosVisualizacion",
            Boolean.TRUE.toString(),
            "/librae-pubseriadas/pages/modoVisualizacion/listadoModoVisualizacion.xhtml" });

        itemCodes.put("/pages/suscripcion/form.xhtml", new String[] {
            "breadcrumb.editSuscripciones", Boolean.TRUE.toString(),
            "/librae-pubseriadas/pages/suscripcion/form.xhtml" });

        itemCodes.put("/pages/numero/form.xhtml", new String[] {
            "breadcrumb.editNumeros", Boolean.TRUE.toString(),
            "/librae-pubseriadas/pages/numero/form.xhtml" });

        itemCodes.put("/pages/periodicidad/form.xhtml", new String[] {
            "breadcrumb.editPeriodicidades", Boolean.TRUE.toString(),
            "/librae-pubseriadas/pages/periodicidad/form.xhtml" });

        itemCodes.put("/pages/suscripcion/arbolSuscripciones.xhtml",
                new String[] { "breadcrumb.arbolSuscripciones",
            Boolean.TRUE.toString(),
            "/librae-pubseriadas/suscripcion/arbolSuscripciones.xhtml" });

        itemCodes.put("/pages/modoVisualizacion/form.xhtml", new String[] {
            "breadcrumb.editModosVisualizacion", Boolean.TRUE.toString(),
            "/librae-pubseriadas/pages/modoVisualizacion" });

        // Catalogacion
        itemCodes.put(CATALOGACION_XHTML, new String[] {
            "breadcrumb.catalogacion", Boolean.TRUE.toString(), null });

        itemCodes.put(AUTORIDAD_XHTML, new String[] {
            "breadcrumb.catalogacion.listadoAutoridad",
            Boolean.FALSE.toString(),
            "/librae-catalogacion/pages/autoridad/listadoAutoridad.html" });

        itemCodes.put("/pages/autoridad/listadoAutoridad.xhtml", new String[] {
            "breadcrumb.catalogacion.listadoAutoridad",
            Boolean.FALSE.toString(),
            "/librae-catalogacion/pages/autoridad/listadoAutoridad.html" });

        rutaCodes
                .put(
                        "/pages/registroBibliografico/listadoRegistroBibliografico.xhtml",
                        new String[] { INDEX_XHTML, CATALOGACION_XHTML });

        rutaCodes
                .put("/pages/editorCatalogacion/editorCatalogacion.xhtml",
                        new String[] { INDEX_XHTML, CATALOGACION_XHTML,
            "/pages/registroBibliografico/listadoRegistroBibliografico.xhtml" });

        itemCodes
                .put(
                        "/pages/registroBibliografico/listadoRegistroBibliografico.xhtml",
                        new String[] {
            "breadcrumb.catalogacion.listadoRegistro",
            Boolean.FALSE.toString(),
            "/librae-catalogacion/pages/registroBibliografico/listadoRegistroBibliografico.html" });

        itemCodes
                .put(
                        "/pages/editorCatalogacion/editorCatalogacion.xhtml",
                        new String[] { "breadcrumb.catalogacion.editor",
            Boolean.TRUE.toString(),
            "/librae-catalogacion/pages/editorCatalogacion/editorCatalogacion.html" });

        rutaCodes.put("/pages/autoridad/listadoAutoridad.xhtml", new String[] {
            INDEX_XHTML, CATALOGACION_XHTML });

        rutaCodes.put("/pages/autoridad/verAutoridad.xhtml", new String[] {
            INDEX_XHTML, CATALOGACION_XHTML, AUTORIDAD_XHTML });

        rutaCodes.put(
                "/pages/editorCatalogacion/editorCatalogacionAutoridad.xhtml",
                new String[] { INDEX_XHTML, CATALOGACION_XHTML,
            "/pages/autoridad/listadoAutoridad.xhtml" });

        itemCodes.put("/pages/autoridad/listadoAutoridad.xhtml", new String[] {
            "breadcrumb.catalogacion.listadoAutoridad",
            Boolean.TRUE.toString(),
            "/librae-catalogacion/pages/autoridad/listadoAutoridad.html" });

        itemCodes
                .put(
                        "/pages/editorCatalogacion/editorCatalogacionAutoridad.xhtml",
                        new String[] {
            "breadcrumb.catalogacion.editorAutoridad",
            Boolean.FALSE.toString(),
            "/librae-catalogacion/pages/editorCatalogacion/editorCatalogacionAutoridad.html" });

        /*
         * itemCodes.put(AUTORIDAD_XHTML, new String[] {
         * "breadcrumb.catalogacion.listadoAutoridad", Boolean.TRUE.toString(),
         * "/pages/autoridad/listadoAutoridad.html" });
         */

        itemCodes.put("/pages/autoridad/verAutoridad.xhtml", new String[] {
            "breadcrumb.catalogacion.verAutoridad", Boolean.TRUE.toString(),
            null });

        rutaCodes.put("/pages/ejemplar/listadoEjemplares.xhtml", new String[] {
            INDEX_XHTML, CATALOGACION_XHTML });

        itemCodes.put("/pages/ejemplar/listadoEjemplares.xhtml", new String[] {
            "breadcrumb.catalogacion.listadoEjemplares",
            Boolean.FALSE.toString(),
            "/librae-catalogacion/pages/ejemplar/listadoEjemplares.html" });

        rutaCodes.put("/pages/ejemplar/verEjemplar.xhtml", new String[] {
            INDEX_XHTML, CATALOGACION_XHTML,
            "/pages/ejemplar/listadoEjemplares.xhtml" });

        itemCodes.put("/pages/ejemplar/verEjemplar.xhtml", new String[] {
            "breadcrumb.catalogacion.verEjemplar", Boolean.TRUE.toString(),
            "/librae-catalogacion/pages/ejemplar/verEjemplar.html" });

        rutaCodes.put("/pages/ejemplar/verEjemplarSoloLectura.xhtml",
                new String[] { INDEX_XHTML, CATALOGACION_XHTML,
            "/pages/ejemplar/listadoEjemplares.xhtml" });

        itemCodes
                .put("/pages/ejemplar/verEjemplarSoloLectura.xhtml",
                        new String[] { "breadcrumb.catalogacion.verEjemplar",
            Boolean.TRUE.toString(),
            "/librae-catalogacion/pages/ejemplar/verEjemplarSoloLectura.html" });

        rutaCodes
                .put(
                        "/pages/registroBibliografico/verRegistroBibliografico.xhtml",
                        new String[] { INDEX_XHTML, CATALOGACION_XHTML,
            "/pages/registroBibliografico/listadoRegistroBibliografico.xhtml" });

        itemCodes
                .put(
                        "/pages/registroBibliografico/verRegistroBibliografico.xhtml",
                        new String[] {
            "breadcrumb.catalogacion.verRegistro",
            Boolean.TRUE.toString(),
            "/librae-catalogacion/pages/registroBibliografico/verRegistroBibliografico.xhtml" });

        rutaCodes
                .put(
                        "/pages/registroBibliografico/verRegistroBibliograficoSoloLectura.xhtml",
                        new String[] { INDEX_XHTML, CATALOGACION_XHTML,
            "/pages/registroBibliografico/listadoRegistroBibliografico.xhtml" });

        itemCodes
                .put(
                        "/pages/registroBibliografico/verRegistroBibliograficoSoloLectura.xhtml",
                        new String[] {
            "breadcrumb.catalogacion.verRegistro",
            Boolean.TRUE.toString(),
            "/librae-catalogacion/pages/registroBibliografico/verRegistroBibliograficoSoloLectura.html" });

        // Importacion y exportacion.

        itemCodes.put(IMPORTEXPORT_XHTML, new String[] {
            "breadcrumb.importexport", Boolean.TRUE.toString(), null });

        itemCodes.put("/pages/transformJob/list.xhtml", new String[] {
            "breadcrumb.importexport.transformJob.listado",
            Boolean.FALSE.toString(),
            "/librae-importexport/pages/transformJob/list.html" });

        itemCodes.put("/pages/ejecutarTransformJob/ejecutar.xhtml",
                new String[] { "breadcrumb.importexport.transformJob.ejecutar",
            Boolean.TRUE.toString(),
            "/librae-importexport/pages/ejecutarTransformJob/ejecutar.html" });

        itemCodes.put("/pages/transformJob/edit/detail.xhtml", new String[] {
            "breadcrumb.importexport.transformJob.detalle",
            Boolean.TRUE.toString(),
            "/librae-importexport/pages/transformJob/edit/detail.html" });

        // Procesos planificados

        itemCodes.put(PLANIFICACION_PROCESOS_XHTML, new String[] {
            "breadcrumb.procesosplanificados", Boolean.TRUE.toString(), null });

        itemCodes
                .put(
                        "/pages/planificacionProcesos/planificacionProcesos.xhtml",
                        new String[] {
            "breadcrumb.procesosplanificados.planificacionProcesos",
            Boolean.FALSE.toString(),
            "/librae-procplanificados/pages/planificacionProcesos/planificacionProcesos.html" });

        itemCodes.put("/pages/gestionProcesos/buscar.xhtml", new String[] {
            "breadcrumb.procesosplanificados.buscar", Boolean.FALSE.toString(),
            "/librae-procplanificados/pages/gestionProcesos/buscar.html" });

        // =================================
        // ITEMCODES PARA BIBLIOTECA DIGITAL
        // =================================

        // Inicio > Biblioteca Digital
        itemCodes.put("/pages/bibdigital/asociacionAutomatica.xhtml",
                new String[] { "breadcrumb.bibdigital.asociacionautomatica",
            Boolean.TRUE.toString(), null });

        // ============================
        // ITEMCODES PARA ADQUISICIONES
        // ============================

        // Proveedor

        itemCodes.put(LISTADO_PROVEEDORES_XHTML, new String[] {
            "breadcrumb.adquisiciones.listadoProveedores",
            Boolean.FALSE.toString(),
            "/librae-adquisicion/pages/proveedores/busquedaProveedores.html" });

        itemCodes.put(PROVEEDOR_XHTML, new String[] {
            "breadcrumb.adquisiciones.detalleProveedor",
            Boolean.TRUE.toString(), null });

        itemCodes.put(DIRECC_POSTAL_XHTML, new String[] {
            "breadcrumb.adquisiciones.detalleDireccPostal",
            Boolean.TRUE.toString(), null });

        // Inicio > Proveedor > Proveedores
        itemCodes.put("/pages/proveedores/busquedaProveedores.xhtml",
                new String[] { "breadcrumb.adquisiciones.listadoProveedores",
            Boolean.TRUE.toString(), null });

        // Inicio > Adquisiciones > Proveedores > Proveedor
        itemCodes.put("/pages/proveedores/edicionProveedores.xhtml",
                new String[] { "breadcrumb.adquisiciones.detalleProveedor",
            Boolean.TRUE.toString(), null });

        // Inicio > Adquisiciones > Proveedores > Proveedor > Dirección postal
        itemCodes.put(
                "/pages/proveedores/direccionPostal/crudDireccionPostal.xhtml",
                new String[] { "breadcrumb.adquisiciones.detalleDireccPostal",
            Boolean.TRUE.toString(), null });

        // Inicio > Adquisiciones > Proveedores > Proveedor > Dirección postal >
        // Contacto
        itemCodes
                .put(
                        "/pages/proveedores/direccionPostal/contactos/crudContactos.xhtml",
                        new String[] {
            "breadcrumb.adquisiciones.detallesContacto",
            Boolean.TRUE.toString(), null });

        // Desiderata

        itemCodes.put(ADQUISICION_XHTML, new String[] {
            "breadcrumb.adquisiciones", Boolean.TRUE.toString(), null });

        // Adquisiciones > Desideratas
        itemCodes.put(DESIDERATAS_XHTML, new String[] {
            "breadcrumb.desideratas", Boolean.TRUE.toString(), null });

        itemCodes
                .put(
                        LISTADO_DESIDERATA_XHTML,
                        new String[] { "breadcrumb.desideratas",
            Boolean.FALSE.toString(),
            "/librae-adquisicion/pages/desideratas/busquedaDesideratasBibliotecario.html" });
        itemCodes
                .put(
                        "/pages/desideratas/motivosRechazo/listadoMotivos.xhtml",
                        new String[] {
            "breadcrumb.adquisiciones.motivosRechazo.listadoMotivos",
            Boolean.FALSE.toString(),
            "/librae-adquisicion/pages/desideratas/motivosRechazo/listadoMotivos.html" });

        itemCodes
                .put(
                        "/pages/desideratas/motivosRechazo/crudMotivosRechazo.xhtml",
                        new String[] {
            "breadcrumb.adquisiciones.motivosRechazo.crudMotivos",
            Boolean.FALSE.toString(),
            "/librae-adquisicion/pages/desideratas/motivosRechazo/crudMotivosRechazo.html" });

        itemCodes
                .put(
                        "/pages/desideratas/busquedaDesideratasBibliotecario.xhtml",
                        new String[] {
            "breadcrumb.adquisiciones.busquedaDesideratasBibliotecario",
            Boolean.TRUE.toString(),
            "/librae-adquisicion/pages/desideratas/busquedaDesideratasBibliotecario.html" });

        itemCodes.put("/pages/desideratas/rechazarDesiderata.xhtml",
                new String[] { "breadcrumb.adquisiciones.rechazarDesiderata",
            Boolean.FALSE.toString(),
            "/librae-adquisicion/pages/desideratas/rechazarDesiderata.html" });

        itemCodes.put("/pages/desideratas/editarDesiderata.xhtml",
                new String[] { "breadcrumb.adquisiciones.editarDesiderata",
            Boolean.TRUE.toString(),
            "/librae-adquisicion/pages/desideratas/editarDesiderata.html" });

        itemCodes.put("/pages/desideratas/aceptarDesiderata.xhtml",
                new String[] { "breadcrumb.adquisiciones.aceptarDesiderata",
            Boolean.FALSE.toString(),
            "/librae-adquisicion/pages/desideratas/aceptarDesiderata.html" });

        itemCodes
                .put(
                        "/pages/desideratas/asociarDesiderataAPedido.xhtml",
                        new String[] {
            "breadcrumb.adquisiciones.asociarDesiderata",
            Boolean.FALSE.toString(),
            "/librae-adquisicion/pages/desideratas/asociarDesiderataAPedido.html" });

        // Estadisticas e informes
        itemCodes.put(INFORMES_XHTML, new String[] { "breadcrumb.informes",
            Boolean.TRUE.toString(), null });

        // generación informes
        itemCodes.put(GENERACION_INFORMES_XHTML, new String[] {
            "breadcrumb.informes.generacionInformes", Boolean.TRUE.toString(),
            null });

        itemCodes
                .put(
                        "/pages/gestionInformes/buscarGenerarInformes.xhtml",
                        new String[] {
            "breadcrumb.informes.gestionInformes.buscarGenerarInformes",
            Boolean.TRUE.toString(),
            "/librae-estadisticas/pages/gestionInformes/buscarGenerarInformes.html" });

        itemCodes
                .put(
                        "/pages/administrarInformes/buscarInformes.xhtml",
                        new String[] {
            "breadcrumb.informes.administracionInformes.buscarInformes",
            Boolean.TRUE.toString(),
            "/librae-estadisticas/pages/administrarInformes/buscarInformes.html" });

        itemCodes
                .put(
                        "/pages/gestionInformes/introducirParametros.xhtml",
                        new String[] {
            "breadcrumb.informes.gestionInformes.introducirParametros",
            Boolean.TRUE.toString(),
            "/librae-estadisticas/pages/gestionInformes/introducirParametros.html" });
        itemCodes.put(ADMINISTRACION_INFORMES_XHTML, new String[] {
            "breadcrumb.informes.administracionInformes",
            Boolean.TRUE.toString(), null });

        itemCodes
                .put(
                        "/pages/administrarInformes/buscarParametros.xhtml",
                        new String[] {
            "breadcrumb.informes.administracionInformes.buscarParametros",
            Boolean.TRUE.toString(),
            "/librae-estadisticas/pages/administrarInformes/buscarParametros.html" });
        itemCodes
                .put(
                        "/pages/administrarInformes/crudInformes.xhtml",
                        new String[] {
            "breadcrumb.informes.administracionInformes.crudInformes",
            Boolean.TRUE.toString(),
            "/librae-estadisticas/pages/administrarInformes/crudInformes.html" });
        itemCodes
                .put(
                        "/pages/administrarInformes/crudParametros.xhtml",
                        new String[] {
            "breadcrumb.informes.administracionInformes.crudParametros",
            Boolean.TRUE.toString(),
            "/librae-estadisticas/pages/administrarInformes/crudParametros.html" });

        itemCodes
                .put(
                        "/pages/gestionInformes/buscarGenerarInformes.xhtml",
                        new String[] {
            "breadcrumb.informes.gestionInformes.buscarGenerarInformes",
            Boolean.TRUE.toString(),
            "/librae-estadisticas/pages/gestionInformes/buscarGenerarInformes.html" });

        itemCodes
                .put(
                        "/pages/administrarInformes/asignarParametroInforme.xhtml",
                        new String[] {
            "breadcrumb.informes.administracionInformes.asignarParametroInforme",
            Boolean.TRUE.toString(),
            "/librae-estadisticas/pages/administrarInformes/asignarParametroInforme.html" });

        itemCodes
                .put(
                        "/pages/administrarInformes/crudPlantillas.xhtml",
                        new String[] {
            "breadcrumb.informes.administracionInformes.crudPlantillas",
            Boolean.TRUE.toString(),
            "/librae-estadisticas/pages/administrarInformes/crudPlantillas.html" });

        // =========================
        // ITEMCODES PARA MENSAJERIA
        // =========================

        itemCodes.put(MENSAJERIA_COMUNICACIONES_XHTML, new String[] {
            "breadcrumb.mensajeriacom", Boolean.TRUE.toString(), null });

        itemCodes.put(MENSAJERIA_XHTML, new String[] {
            "breadcrumb.mensajeriacom.mensajeria", Boolean.TRUE.toString(),
            null });

        itemCodes.put(BUSCAR_CATALOGO_DSI, new String[] {
            "breadcrumb.mensajeriacom.mensajeria.buscarCatalogoDSI",
            Boolean.FALSE.toString(),
            "/librae-mensajeria/pages/buscarCatalogoDSI.html" });

        // Inicio > Mensajería y comunicaciones > Mensajería > Tipo de
        // notificaciones del SIGB
        itemCodes.put("/pages/buscarCatalogoDSI.xhtml", new String[] {
            "breadcrumb.mensajeriacom.mensajeria.buscarCatalogoDSI",
            Boolean.TRUE.toString(), null });

        // Inicio > Mensajería y comunicaciones > Mensajería > Tipo de
        // notificaciones del SIGB > Tipo de notación
        itemCodes.put("/pages/crudCatalogoDSI.xhtml", new String[] {
            "breadcrumb.mensajeriacom.mensajeria.buscarCatalogoDSI.crud",
            Boolean.TRUE.toString(), null });

        // =======

        itemCodes.put("/pages/detalleNotificacion.xhtml", new String[] {
            "breadcrumb.mensajeria.detalleNotificacion",
            Boolean.TRUE.toString(),
            "/librae-mensajeria/pages/detalleNotificacion.html" });

        itemCodes.put("/pages/busquedaNotificacionesLector.xhtml",
                new String[] {
            "breadcrumb.mensajeriacom.mensajeria.busquedaNotificacionesLector",
            Boolean.TRUE.toString(),
            "/librae-mensajeria/pages/busquedaNotificacionesLector.html" });

        itemCodes
                .put(
                        "/pages/consultarNotificacionesDSIbibliotecario.xhtml",
                        new String[] {
            "breadcrumb.mensajeriacom.mensajeria.consultarNotificacionesDSIbibliotecario",
            Boolean.TRUE.toString(),
            "/librae-mensajeria/pages/consultarNotificacionesDSIbibliotecario.html" });

        itemCodes.put("/pages/consultarTareasPendientes.xhtml", new String[] {
            "breadcrumb.mensajeriacom.mensajeria.consultarTareasPendientes",
            Boolean.TRUE.toString(),
            "/librae-mensajeria/pages/consultarTareasPendientes.html" });

        itemCodes
                .put(
                        "/pages/consultarNotificacionesSubsistemas.xhtml",
                        new String[] {
            "breadcrumb.mensajeriacom.mensajeria.consultaNotificacionesSubsistemas",
            Boolean.TRUE.toString(),
            "/librae-mensajeria/pages/consultarNotificacionesSubsistemas.html" });

        itemCodes
                .put(
                        "/pages/detalleNotificacion.xhtml",
                        new String[] {
            "breadcrumb.mensajeriacom.mensajeria.consultaNotificacionesSubsistemas.detalle",
            Boolean.TRUE.toString(),
            "/librae-mensajeria/pages/detalleNotificacion.html" });

        // HelpCodes
        // Adminconfig

        // usuario
        helpCodes.put("/pages/usuario/list.xhtml", "#_Toc261877095");
        helpCodes.put("/pages/usuario/form.xhtml", "#_Toc261877095");
        helpCodes.put("/pages/usuarioBibliotecaRol/asignarUsuario.xhtml",
                "#_Toc261877095");
        helpCodes.put("/pages/usuarioBibliotecaRol/asignarUsuarios.xhtml",
                "#_Toc261877095");
        helpCodes.put("/pages/iaa/gestionUsuarios/cambiarClave.xhtml",
                "#_Toc261877105");

        // roles
        helpCodes.put("/pages/permiso/list.xhtml", "#_Toc261877072");
        helpCodes.put("/pages/permiso/listLocalizador.xhtml", "#_Toc261877072");
        helpCodes.put("/pages/rol/list.xhtml", "#_Toc261877072");
        helpCodes.put("/pages/rol/form.xhtml", "#_Toc261877072");
        helpCodes.put("/pages/rol/listLocalizador.xhtml", "#_Toc261877072");
        helpCodes.put("/pages/permisoRol/asignarPermisoRol.xhtml",
                "#_Toc261877072");
        // biblioteca
        helpCodes.put("/pages/biblioteca/list.xhtml", "#_Toc261877051");
        helpCodes.put("/pages/biblioteca/biblioteca.xhtml", "#_Toc261877051");
        helpCodes.put("/pages/biblioteca/bibliotecaSoloLectura.xhtml",
                "#_Toc261877051");
        helpCodes.put("/pages/biblioteca/calendario.xhtml", "#_Toc261877051");
        helpCodes.put("/pages/biblioteca/listLocalizador.xhtml",
                "#_Toc261877051");
        // codigos
        helpCodes.put("/pages/codigos/list.xhtml", "#_Toc261877065");
        helpCodes.put("/pages/codigos/form.xhtml", "#_Toc261877065");
        helpCodes.put("/pages/codigos/valores/list.xhtml", "#_Toc261877065");
        helpCodes.put("/pages/codigos/valores/form.xhtml", "#_Toc261877065");
        // catalogo
        helpCodes.put("/pages/catalogo/list.xhtml", "#_Toc261877081");
        helpCodes.put("/pages/catalogo/form.xhtml", "#_Toc261877081");

        // falta este ancla manual general
        helpCodes.put("/pages/iaa/gestionUsuarios/cambiarClaveUsuario.xhtml",
                "#Toc261877105");

        // direcciones falta poner ancla general
        helpCodes.put("/pages/direcciones/form.xhtml", "#_Toc261877065");
        // falta este ancla general
        helpCodes.put("/pages/iaa/autorizar/autorizar.xhtml", "#_Toc261877056");

        helpCodes.put("/pages/parametro/list.xhtml", "#_Toc261877088");
        helpCodes.put("/pages/parametro/form.xhtml", "#_Toc261877088");
        // falta este ancla general
        helpCodes.put("/pages/favoritos/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/favoritos/form.xhtml", "#_Toc261877059");
        // falta este ancla general
        helpCodes.put("/pages/horarios/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/horarios/form.xhtml", "#_Toc261877059");
        // falta este ancla general
        helpCodes.put("/pages/horariosInt/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/horariosInt/form.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/horariosInt/formIntervalo.xhtml",
                "#_Toc261877059");

        // import-exportacion
        // falta el ancla
        helpCodes.put("/pages/transformJob/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/transformJob/form.xhtml", "#_Toc261877059");

        // circulacion
        // faltan todas las anclas
        helpCodes.put("/pages/asocPolReserva/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/asocPolReserva/form.xhtml", "#_Toc261877059");

        helpCodes.put("/pages/bibliotecaConsorciada/list.xhtml",
                "#_Toc261877059");
        helpCodes.put("/pages/bibliotecaConsorciada/form.xhtml",
                "#_Toc261877059");

        helpCodes.put("/pages/bibliotecaExt/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/bibliotecaExt/form.xhtml", "#_Toc261877059");

        helpCodes.put("/pages/consorcio/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/consorcio/form.xhtml", "#_Toc261877059");

        helpCodes.put("/pages/contactoPIB/list.xhtml", "#_Toc261877059");

        helpCodes.put("/pages/peticion/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/peticion/form.xhtml", "#_Toc261877059");

        helpCodes.put("/pages/peticionPIB/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/peticionPIB/form.xhtml", "#_Toc261877059");

        helpCodes
                .put("/pages/politicaPrestamoDom/list.xhtml", "#_Toc261877059");
        helpCodes
                .put("/pages/politicaPrestamoDom/form.xhtml", "#_Toc261877059");

        helpCodes.put("/pages/politicaReserva/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/politicaReserva/form.xhtml", "#_Toc261877059");

        helpCodes.put("/pages/prestamoConsorcio/prestamoConsorcio.xhtml",
                "#_Toc261877059");

        helpCodes.put("/pages/reserva/list.xhtml", "#_Toc261877059");
        helpCodes.put("/pages/reserva/form.xhtml", "#_Toc261877059");

        // //// ADQUISICIONES
        // ADQUISICIONES proveedores
        helpCodes.put("/pages/proveedores/busquedaProveedores.xhtml",
                "#_Toc263762121");
        helpCodes.put("/pages/proveedores/edicionProveedores.xhtml",
                "#_Toc263762123");
        helpCodes.put(
                "/pages/proveedores/direccionPostal/crudDireccionPostal.xhtml",
                "#_Toc263762128");
        helpCodes
                .put(
                        "/pages/proveedores/direccionPostal/contactos/crudContactos.xhtml",
                        "#_Toc261877059");
        // ADQUISICIONES desideratas
        helpCodes.put("/pages/desideratas/aceptarDesiderata.xhtml",
                "#_Toc263762115");
        helpCodes.put(
                "/pages/desideratas/busquedaDesideratasBibliotecario.xhtml",
                "#_Toc263762111");
        helpCodes.put("/pages/desideratas/crearDesiderata.xhtml",
                "#_Toc263762112");
        helpCodes.put("/pages/desideratas/editarDesiderata.xhtml",
                "#_Toc263762113");
        helpCodes.put("/pages/desideratas/rechazarDesiderata.xhtml",
                "#_Toc263762117");
        helpCodes.put(
                "/pages/desideratas/motivosRechazo/crudMotivosRechazo.xhtml",
                "#_Toc263762105");
        helpCodes.put("/pages/desideratas/motivosRechazo/listadoMotivos.xhtml",
                "#_Toc263762104");

        /******************************************************************
         * ********************* CATALOGACION *****************************
         ****************************************************************/
        // autoridad
        helpCodes.put("/pages/autoridad/listadoAsociarAutoridad.xhtml", "#257");
        helpCodes.put("/pages/autoridad/listadoAsociarAutoridadVease.xhtml",
                "#_Toc263933947");
        helpCodes.put("/pages/autoridad/listadoAutoridad.xhtml",
                "#_Toc263933927");
        helpCodes.put("/pages/autoridad/verAutoridad.xhtml", "#_Toc263933934");

        // bibdigital
        helpCodes.put("/pages/bibdigital/asociacionAutomatica.xhtml",
                "#_Toc261877059");

        // editor de Catalogacion
        helpCodes.put("/pages/editorCatalogacion/editorCatalogacion.xhtml",
                "#_Toc263933967");
        helpCodes.put(
                "/pages/editorCatalogacion/editorCatalogacionAutoridad.xhtml",
                "#_Toc263933940");

        // ejemplar
        helpCodes.put("/pages/ejemplar/listadoEjemplares.xhtml",
                "#_Toc263933973");
        helpCodes.put("/pages/ejemplar/verEjemplar.xhtml", "#_Toc263933985");
        helpCodes.put("/pages/ejemplar/verEjemplarSoloLectura.xhtml",
                "#_Toc263933985");
        helpCodes
                .put(
                        "/pages/ejemplar/include/localizadores/localizadorRegistros.xhtml",
                        "#_TocA6");
        helpCodes.put(
                "/pages/ejemplar/include/localizadores/listLocalizador.xhtml",
                "#_Toc261877059");
        // localizadores
        // TODO
        helpCodes.put("/pages/localizadores/localizadorBusquedaActivos.xhtml",
                "#");
        helpCodes.put(
                "/pages/localizadores/localizadorEtiquetasAutoridad.xhtml",
                "#_Toc263933946");
        helpCodes
                .put(
                        "/pages/localizadores/localizadorEtiquetasControlAutoridad.xhtml",
                        "#_Toc263933944");
        helpCodes
                .put(
                        "/pages/localizadores/localizadorEtiquetasControlRegBibl.xhtml",
                        "#253");
        helpCodes.put("/pages/localizadores/localizadorEtiquetasRegBibl.xhtml",
                "#256");

        helpCodes.put(
                "/pages/localizadores/localizadorExportarRegistros.xhtml",
                "#_Toc263933969");
        helpCodes.put(
                "/pages/localizadores/localizadorImportarRegistros.xhtml",
                "#_Toc263933969");

        helpCodes
                .put(
                        "/pages/localizadores/localizadorPlantillasAsociarAutoridad.xhtml",
                        "#_Toc263933936");
        helpCodes.put(
                "/pages/localizadores/localizadorPlantillasAutoridad.xhtml",
                "#_Toc263933936");
        helpCodes.put("/pages/localizadores/localizadorPlantillasRegBib.xhtml",
                "#_Toc263933960");
        helpCodes.put("/pages/localizadores/localizadorSecundario.xhtml",
                "#_Toc263933991");
        //
        // helpCodes
        // .put(
        // "/pages/localizadores/localizadorSubcamposAutoridad.xhtml",
        // "#");
        // helpCodes.put("/pages/localizadores/localizadorSubcamposRegBibl.xhtml",
        // "#");
        helpCodes.put("/pages/localizadores/localizadorTransferir.xhtml",
                "#_Toc263933963");
        helpCodes.put(
                "/pages/localizadores/localizadorTransferirAutoridad.xhtml",
                "#_Toc263933939");

        // registroBibliografico
        helpCodes
                .put(
                        "/pages/registroBibliografico/listadoRegistroBibliografico.xhtml",
                        "#_Toc263933950");
        helpCodes.put(
                "/pages/registroBibliografico/verRegistroBibliografico.xhtml",
                "#_Toc263933957");
        // helpCodes
        // .put(
        // "/pages/registroBibliografico/verRegistroBibliograficoSoloLectura.xhtml",
        // "#_Toc263933957");
        /******************************************************************
         ********************** end CATALOGACION **************************
         ****************************************************************/

        // // ESTADISTICAS
        // gestion de informes
        helpCodes.put("/pages/gestionInformes/buscarGenerarInformes.xhtml",
                "#_Toc264012186");
        helpCodes.put("/pages/gestionInformes/introducirParametros.xhtml",
                "#_Toc264012186");
        // administrar informes
        helpCodes.put(
                "/pages/administrarInformes/asignarParametroInforme.xhtml",
                "#_Toc261877059");
        helpCodes.put("/pages/administrarInformes/buscarInformes.xhtml",
                "#_Toc264012177");
        helpCodes.put("/pages/administrarInformes/buscarParametros.xhtml",
                "#_Toc264012182");
        helpCodes.put("/pages/administrarInformes/crudInformes.xhtml",
                "#_Toc261877059");
        helpCodes.put("/pages/administrarInformes/crudParametros.xhtml",
                "#_Toc264012183");
        helpCodes.put("/pages/administrarInformes/crudPlantillas.xhtml",
                "#_Toc264012178");

        // //// LECTORES
        // LECTORES direcciones
        helpCodes.put("/pages/direcciones/form.xhtml", "#_Toc263695335");
        // LECTORES lectores
        helpCodes.put("/pages/lector/listadoLectores.xhtml", "#_Toc263695323");
        helpCodes.put("/pages/lector/edicionLectores.xhtml", "#_Toc263695322");
        helpCodes.put("/pages/lector/localizadores/localizadorLectores.xhtml",
                "#_Toc263695322");

        // // MENSAJERIA
        helpCodes.put("/pages/buscarCatalogoDSI.xhtml", "#_Toc264283898");
        helpCodes.put("/pages/consultarNotificacionesDSIbibliotecario.xhtml",
                "#_Toc264283902");
        helpCodes.put("/pages/consultarNotificacionesSubsistemas.xhtml",
                "#_Toc264283908");
        helpCodes.put("/pages/consultarTareasPendientes.xhtml",
                "#_Toc264283914");
        helpCodes.put("/pages/crudCatalogoDSI.xhtml", "#_Toc264283895");
        helpCodes.put("/pages/detalleNotificacion.xhtml", "#_Toc264283912");

        // // PROCESOS PLANIFICADOS
        // gestion de procesos
        helpCodes.put("/pages/gestionProcesos/buscar.xhtml", "#_Toc263347128");
        helpCodes.put("/pages/gestionProcesos/buscarEjecuciones.xhtml",
                "#_Toc263347132");
        helpCodes.put("/pages/gestionProcesos/buscarProcesos.xhtml",
                "#_Toc263347128");
        // planificacion de procesos
        helpCodes.put(
                "/pages/planificacionProcesos/planificacionProcesos.xhtml",
                "#_Toc263347131");

        // // PUBLICACIONES SERIADAS
        // cardex
        helpCodes.put("/pages/cardex/cardex.xhtml", "#_Toc264278888");
        helpCodes
                .put("/pages/cardex/localizadorEstado.xhtml", "#_Toc264278888");

        // modo visualizacion
        helpCodes.put("/pages/modoVisualizacion/form.xhtml", "#_Toc264278920");
        helpCodes.put(
                "/pages/modoVisualizacion/listadoModoVisualizacion.xhtml",
                "#_Toc264278919");
        helpCodes.put(
                "/pages/modoVisualizacion/localizadorModoVisualizacion.xhtml",
                "#_Toc264278919");

        // numero
        helpCodes.put("/pages/numero/form.xhtml", "#_Toc264278890");

        // pedido encuadernaciones
        helpCodes.put("/pages/pedidoEncuadernaciones/form.xhtml",
                "#_Toc261877059");

        // pedido suscripciones
        helpCodes
                .put("/pages/pedidoSuscripciones/form.xhtml", "#_Toc264278892");

        // perioricidad
        helpCodes.put("/pages/periodicidad/form.xhtml", "#_Toc264278907");
        helpCodes.put("/pages/periodicidad/listadoPeriodicidad.xhtml",
                "#_Toc264278904");
        helpCodes.put("/pages/periodicidad/localizadorPeriodicidad.xhtml",
                "#_Toc264278904");

        // suscripcion
        helpCodes.put("/pages/suscripcion/form.xhtml", "#_Toc264278887");
        helpCodes.put("/pages/suscripcion/listadoSuscripcion.xhtml",
                "#_Toc264278882");
        helpCodes.put("/pages/suscripcion/localizadorSuscripcion.xhtml",
                "#_Toc264278882");

    }

    /**
     * Metodo encargado de obtener un listado de los identificadores de ruta
     * anteriores al especificado
     * 
     * @param viewId
     * @return Lista de identificadores
     */
    public static final List<String> getRuta(final String viewId) {
        final List<String> res = new ArrayList<String>();

        final String[] rutas = rutaCodes.get(viewId);
        if (rutas != null) {
            res.addAll(Arrays.asList(rutas));
        }

        return res;
    }

    /**
     * Metodo encargado de construir un BreadCrumItem a partir de las
     * propiedades del viewId indicado
     * 
     * @param viewId
     * @return BreadCrumbItem
     */
    public static final BreadCrumbItem getItem(final String viewId) {
        BreadCrumbItem res = null;

        final String[] atributos = itemCodes.get(viewId);
        if (atributos != null && atributos.length == 3) {
            res = new BreadCrumbItem(viewId, getLabel(atributos[0]),
                    atributos[1], atributos[2]);
        }

        return res;
    }

    private static final String getLabel(final String key) {
        String res = "";
        final Locale locale = LocaleContextHolder.getLocale();

        try {
            final ResourceBundle properti = ResourceBundle.getBundle(
                    "org.librae.common.messages", locale);
            res = properti.getString(key);
        } catch (final Exception e) {
            // se ignora
            log.info("Se ignora la excepcion: " + e.getMessage());
        }

        return res;
    }

    public static final String getHelpCodes(final String viewId) {
        return helpCodes.get(viewId);
    }
}
