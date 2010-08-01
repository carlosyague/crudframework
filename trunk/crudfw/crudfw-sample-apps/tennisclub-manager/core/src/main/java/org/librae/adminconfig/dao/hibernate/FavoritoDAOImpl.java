package org.librae.adminconfig.dao.hibernate;

import java.util.HashMap;

import org.librae.adminconfig.dao.IFavoritoDAO;

/**
 * Implementación del DAO para la entidad Favorito.
 * 
 * @author jcisneros
 */
public class FavoritoDAOImpl implements IFavoritoDAO {

    public String getFavorito(String url) {
        return favoritos.get(url);
    }

    public static HashMap<String, String> favoritos = new HashMap<String, String>();
    static {
        favoritos.put("/librae-adminconfig/pages/biblioteca/list.html",
                "Bibliotecas");

        favoritos.put("/librae-adminconfig/pages/biblioteca/form.html",
                "Detalle de la biblioteca");

        favoritos.put("/librae-adminconfig/pages/catalogo/list.html",
                "Cat\u00e1logos");

        favoritos.put("/librae-adminconfig/pages/catalogo/form.html",
                "Detalle del cat\u00e1logo");

        favoritos.put("/librae-adminconfig/pages/codigos/list.html",
                "C\u00f3digos");

        favoritos.put("/librae-adminconfig/pages/codigo/form.html",
                "Detalle del c\u00f3digo");

        favoritos.put("/librae-adminconfig/pages/parametro/list.html",
                "Par\u00e1metros");

        favoritos.put("/librae-adminconfig/pages/parametro/form.html",
                "Detalle del par\u00e1metro");

        favoritos.put("/librae-circulacion/pages/bibliotecaExt/list.html",
                "Bibliotecas externas");

        favoritos.put("/librae-circulacion/pages/bibliotecaExt/form.html",
                "Detalle de la biblioteca externa");

        favoritos.put("/librae-circulacion/pages/asocPolPrestamo/list.html",
                "Asociaciones de pol\u00edticas de pr\u00e9stamos");

        favoritos
                .put("/librae-circulacion/pages/asocPolPrestamo/form.html",
                        "Detalle de la asociaci\u00f3n de pol\u00edticas de pr\u00e9stamos");

        favoritos.put("/librae-circulacion/pages/asocPolReserva/list.html",
                "Asociaciones de las pol\u00edticas de reserva");

        favoritos.put("/librae-circulacion/pages/asocPolReserva/form.html",
                "Detalle de la asociaci\u00f3n de pol\u00edtica de reserva");

        favoritos.put("/librae-circulacion/pages/consorcio/list.html",
                "Consorcios");

        favoritos.put("/librae-circulacion/pages/consorcio/form.html",
                "Detalle del consorcio");

        favoritos.put("/librae-circulacion/pages/peticionPIB/list.html",
                "Peticiones de pr\u00e9stamos interbibliotecarios");

        favoritos.put("/librae-circulacion/pages/peticionPIB/form.html",
                "Detalle de la petici\u00f3n");

        favoritos.put(
                "/librae-circulacion/pages/politicaPrestamoDom/list.html",
                "Pol\u00edticas de pr\u00e9stamos");

        favoritos.put(
                "/librae-circulacion/pages/politicaPrestamoDom/form.html",
                "Detalle de la politica de pr\u00e9stamos");

        favoritos.put("/librae-circulacion/pages/politicaReserva/list.html",
                "Pol\u00edticas de reserva");

        favoritos.put("/librae-circulacion/pages/politicaReserva/form.html",
                "Detalle de la politica de reserva");

        favoritos
                .put("/librae-circulacion/pages/reserva/list.html", "Reservas");

        favoritos.put("/librae-circulacion/pages/reserva/form.html",
                "Detalle de la reserva");

        favoritos.put("/librae-importexport/pages/transformJob/list.html",
                "Transformaciones o jobs");

        favoritos.put("/librae-importexport/pages/transformJob/form.html",
                "Detalle de la transformacion o job");

        favoritos.put("/librae-adminconfig/pages/rol/list.html", "Roles");

        favoritos.put("/librae-adminconfig/pages/rol/form.html",
                "Detalle del rol");

        favoritos
                .put(
                        "/librae-adminconfig/pages/iaa/gestionUsuarios/cambiarClave.html",
                        "Cambiar clave");

        favoritos
                .put("/librae-adminconfig/pages/usuario/list.html", "Usuarios");

        favoritos.put("/librae-adminconfig/pages/usuario/form.html",
                "Detalle del usuario");

        favoritos.put("/librae-circulacion/pages/prestamoDom/prestamoDom.html",
                "Pr\u00e9stamo a domicilio");

        favoritos.put(
                "/librae-circulacion/pages/prestamoSala/prestamoSala.html",
                "Pr\u00e9stamo en sala");

        favoritos.put("/librae-lectores/pages/lector/listadoLectores.html",
                "Lectores");

        favoritos
                .put(
                        "/librae-catalogacion/pages/registroBibliografico/listadoRegistroBibliografico.html",
                        "Cat\u00e1logo");

        favoritos.put(
                "/librae-catalogacion/pages/ejemplar/listadoEjemplares.html",
                "Ejemplares");

        favoritos.put(
                "/librae-catalogacion/pages/autoridad/listadoAutoridad.html",
                "Autoridades");

        favoritos.put("/librae-mensajeria/pages/buscarCatalogoDSI.html",
                "Tipos de notificaciones del SIGB");

        favoritos
                .put(
                        "/librae-mensajeria/pages/consultarNotificacionesSubsistemas.html",
                        "Notificaciones generadas por los subsistemas");

        favoritos
                .put(
                        "/librae-mensajeria/pages/consultarNotificacionesDSIbibliotecario.html",
                        "Tipos de notificaciones del SIGB por un bibliotecario");

        favoritos.put(
                "/librae-mensajeria/pages/consultarTareasPendientes.html",
                "Tareas pendientes");

        favoritos.put(
                "/librae-mensajeria/pages/busquedaNotificacionesLector.html",
                "Notificaciones de lectores");

        favoritos
                .put(
                        "/librae-adquisicion/pages/desideratas/busquedaDesideratasBibliotecario.html",
                        "Desideratas");

        favoritos
                .put(
                        "/librae-adquisicion/pages/proveedores/busquedaProveedores.html",
                        "Proveedores");

        favoritos
                .put(
                        "/librae-estadisticas/pages/gestionInformes/buscarGenerarInformes.html",
                        "Generar estad\u00edsticas");

        favoritos
                .put(
                        "/librae-estadisticas/pages/administrarInformes/buscarParametros.html",
                        "Par\u00e1metros");

        favoritos
                .put(
                        "/librae-estadisticas/pages/administrarInformes/buscarInformes.html",
                        "Administrar estad\u00edsticas");

        favoritos.put(
                "/librae-procplanificados/pages/gestionProcesos/buscar.html",
                "Procesos planificados");
        
        favoritos.put(
                "/librae-pubseriadas/pages/suscripcion/listadoSuscripcion.html",
                "Recepci\u00f3n / Colecciones");
        favoritos.put(
                "/librae-pubseriadas/pages/periodicidad/listadoPeriodicidad.html",
                "Periodicidades");
        favoritos.put(
                "/librae-pubseriadas/pages/modoVisualizacion/listadoModoVisualizacion.html",
                "Modos de visualizaci\u00f3n");

    }
}
