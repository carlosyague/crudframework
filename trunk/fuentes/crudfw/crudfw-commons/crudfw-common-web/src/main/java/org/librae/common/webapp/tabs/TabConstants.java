package org.librae.common.webapp.tabs;

/**
 * Constantes del componente de pestañas
 * 
 * @author cyague
 */
public class TabConstants {

    public static final int    DEFAULT_ACTIVE_TAB              = 1;

    /**
     * Constantes Javascript
     */
    public static final String DEFAULT_JAVASCRIPT_FUNCTION_TAB = "pestana";
    public static final String JAVASCRIPT_FUNCTION_SUBTAB      = "subpestana";

    public static final String BEGIN_JAVASCRIPT_BLOCK          = "<script type='text/javascript'><!--";
    public static final String END_JAVASCRIPT_BLOCK            = "--></script>";

    /**
     * Constantes CSS
     */
    public static final String STYLE_CLASS_TITULO              = "titulo";
    public static final String STYLE_CLASS_TITULO_ACTIVA       = "titulo_activa";
    public static final String STYLE_CLASS_BLANK               = "blank";
    public static final String STYLE_DIV                       = "display:none;";

    // Pestañas
    public static final String TAB_DIV_CLASS                   = "pestana";
    public static final String TAB_PANEL_GRID_STYLE_CLASS      = "pestanas";

    // Subpestañas
    public static final String SUBTAB_DIV_CLASS                = "subpestana";
    public static final String SUBTAB_PANEL_GRID_STYLE_CLASS   = "subpestanas";
    public static final String PANEL_GRID_WRAPPER_STYLE_CLASS  = "border_subpest";

    public static final String PANEL_GRID_CELLPADDING          = "0";
    public static final String PANEL_GRID_CELLSPACING          = "0";
    public static final int    PANEL_GRID_BORDER               = 0;

    /**
     * Constantes Imágenes
     */

    // Pestañas
    public static final String IMG_TAB_1ST_DISABLE             = "/images/p01g.gif";
    public static final String IMG_TAB_ENABLE_1                = "/images/p01b.gif";
    public static final String IMG_TAB_ENABLE_2                = "/images/p02b.gif";
    public static final String IMG_TAB_DISABLE_2               = "/images/pi02g.gif";

    // Subpestañas
    public static final String IMG_SUBTAB_1ST_ENABLE           = "/images/sp01a.gif";
    public static final String IMG_SUBTAB_1ST_DISABLE          = "/images/sp01g.gif";
    public static final String IMG_SUBTAB_ENABLE_DISABLE       = "/images/spi02a.gif";
    public static final String IMG_SUBTAB_DISABLE_ENABLE       = "/images/spi01a.gif";
    public static final String IMG_SUBTAB_DISABLE_DISABLE      = "/images/spi01g.gif";
    public static final String IMG_SUBTAB_LAST_ENABLE          = "/images/sp02a.gif";
    public static final String IMG_SUBTAB_LAST_DISABLE         = "/images/sp02g.gif";

    /**
     * TabInfo Constants
     */
    public static final int    NON_ID                          = 0;

    public static final String COLOR_AMARILLO                  = "amarillo";
    public static final String COLOR_VERDE                     = "verde";
    public static final String COLOR_ROJO                      = "rojo";

}
