package org.librae.common.search.index;

/**
 * Constantes para la generación de archivos xlm para la comunicación con Solr
 *
 * @author mifranco
 */
public class Constants {

    public static final String  XML_ENCODING                = "UTF-8";
    public static final String  JAVA_ENCODING               = "UTF-8";
    public static final String  XML_VERSION                 = "1.0";
    public static final String  FIELD                       = "field";
    public static final String  NAME                        = "name";
    public static final String  ADD                         = "add";
    public static final String  DOCDOCUMENT                 = "doc";
    public static final String  IDREGISTRO                  = "id";
    public static final String  CODIGOREGISTROETIQUETA      = "codigoRegistroEtiqueta";
    public static final String  INDICADOR1REGISTROETIQUETA  = "indicador1RegistroEtiqueta";
    public static final String  INDICADOR2REGISTROETIQUETA  = "indicador2RegistroEtiqueta";
    public static final String  NOMBREREGISTROSUBCAMPO      = "nombreRegistroSubcampo";
    public static final String  TIPOREGISTRO                = "tipoRegistro";
    public static final String  TIPO                        = "tipo";
    public static final String  BIBLIOTECA                  = "biblioteca";
    public static final String  FORMATO                     = "formato";
    public static final String  ESTADO                      = "estado";
    public static final String  DESCRIPCION                 = "descripcion";
    /* Campos de Dublin Core almacenados como índices en SOLR para las búsquedas */
    public static final String  AUTOR_SEARCH                = "autorSearch";
    public static final String  TITULO_SEARCH               = "tituloSearch";
    public static final String  MATERIA_SEARCH              = "materiaSearch";
    public static final String  INTERNACIONAL_NUMBER_SEARCH = "internacionalNumberSearch";
    public static final String  EDITORIAL_SEARCH            = "editorialSearch";
    public static final String  ANYO_SEARCH                 = "anyoSearch";
    public static final String  EDICION_SEARCH              = "edicionSearch";
    public static final String  IDAUTORIDADES_SEARCH                 = "idAutoridadesSearch";
    /*
     * Campos de Dublin Core almacenados como índices en SOLR para la generación
     * de los DTO's en los listados
     */
    public static final String  AUTOR_VIEW                  = "autorView";
    public static final String  TITULO_VIEW                 = "tituloView";
    public static final String  MATERIA_VIEW                = "materiaView";
    public static final String  INTERNACIONAL_VIEW          = "internacionalNumberView";
    public static final String  EDITORIAL_VIEW              = "editorialView";
    public static final String  ANYO_VIEW                   = "anyoView";
    public static final String  EDICION_VIEW                = "edicionView";
    public static final String  LUGAR_PUBLICACION_VIEW      = "lugarPublicacionView";
    // Request Handlers
    public final static String  REQUEST_HANDLER_UPDATE      = "update";
    public final static String  REQUEST_HANDLER_SELECT      = "select";
    private final static String LUCENE_VERSION              = "2.2";
}
