package org.librae.common.service.impl;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Clase para la internacionalizacion de los combos
 */
public class ComboLocaleManager {

    /**
     * fichero de propiedades.
     */
    private static ResourceBundle properti = null;

    public ComboLocaleManager(String propertiFile) {
        final Locale locale = LocaleContextHolder.getLocale();

        try {
            if (properti==null)
                properti = ResourceBundle.getBundle(propertiFile, locale);
        } catch (final Exception e) {
            properti = null;
        }
    }

    public static String get(String code) {
    	String res = "";
    	try {
	        if ((properti != null) && (code!=null)) {
	            res = properti.getString(code);
	        }
    	} catch (Exception e) {
    	    res=code;
    	}
        return res;
    }

    /**
     * Se traduce si existe y si es traducible, sino se deja como esta.
     * 
     * @param code
     * @return
     */
	public static String getOptional(String code) {
		try {
			if ((properti != null) && (code!=null) && (code.length()>0)) {
				if (code.charAt(0) == '#') {
					code = code.replace("#", "");
					code = properti.getString(code);
				}
			}
		} catch (Exception e) {
    	}
		return (code == null) ? "" : code;
	}
}
