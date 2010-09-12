package org.librae.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Se encarga de leer el archivo de configuración y tener una única instancia
 * disponible para toda la aplicación. Se presupone que el archivo de
 * propiedades está en la raiz del proyecto.
 * 
 * @author cyague
 */
public final class Propiedades {

    /** Nombre del archivo de propiedades por defecto. */
    private final static String DEFAULT_PROPERTIES = "configuration.properties";
    /** Archivo a usar para cargar las propiedades. */
    private String              archivo            = Propiedades.DEFAULT_PROPERTIES;
    /** Objeto con las propiedades a usar. */
    private Properties          lasPropiedades;

    /** Archivo log para notificar las posibles exceptiones. */
    private static Logger       log                = Logger
                                                           .getLogger(Propiedades.class);

    /** Instancia global del archivo de propiedades. */
    private static Propiedades  instance;

    /**
     * Constructor con el archivo por defecto indicado en la clase
     */
    private Propiedades() {
        this(Propiedades.DEFAULT_PROPERTIES);
    }

    /**
     * Constructor con el nombre del archivo como parámetro
     * 
     * @param archivo
     *            Nombre sin extensión del archivo .properties
     */
    private Propiedades(final String archivo) {
        this.archivo = archivo;
        this.cargarPropiedades();
    }

    /**
     * Carga las propiedades del archivo
     */

    private void cargarPropiedades() {
        this.lasPropiedades = new Properties();
        InputStream in;
        try {
            in = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(this.archivo);
            this.lasPropiedades.load(in);
            in.close();
        } catch (final IOException e) {
            Propiedades.log.error("Error al carga el fichero de propiedades: "
                    + this.archivo);
        }
        final Enumeration claves = this.lasPropiedades.propertyNames();
        while (claves.hasMoreElements()) {
            final String clave = (String) claves.nextElement();
            final String valor = this.lasPropiedades.getProperty(clave);
            Propiedades.log.info(clave + ": " + valor);
        }
    }

    /**
     * Obtiene la instancia en memoria. Si no existe, se crea. Patrón de diseño
     * Singleton.
     * 
     * @return Un objeto de tipo Propiedades.
     */
    public static synchronized Propiedades getInstance() {
        if (Propiedades.instance == null) {
            Propiedades.instance = new Propiedades();
        }
        return Propiedades.instance;
    }

    /**
     * Obtiene la instancia en memoria. Si no existe, se crea. Patrón de diseño
     * Singleton.
     * 
     * @return Un objeto de tipo Propiedades.
     */
    public static synchronized Propiedades getInstance(final String archivo) {
        if (Propiedades.instance == null) {
            Propiedades.instance = new Propiedades(archivo);
        }
        return Propiedades.instance;
    }

    /**
     * Devuelve el valor de una clave, es decir, devuelve una propiedad.
     * 
     * @param clave
     *            Nombre de la propiedad.
     * @return Valor de la propiedad.
     */
    public String getPropiedad(final String clave) {
        String aux = "";
        aux = this.lasPropiedades.getProperty(clave);
        if (aux == null || aux.equals("")) {
            Propiedades.log.error("No se ha encontrado la propiedad '" + clave
                    + "' en el archivo de configuración.");
        }
        return aux;
    }

    /**
     * Devuelve el conjunto completo de propiedades
     * 
     * @return pares atributo valor
     */
    public Properties getPropiedades() {
        return this.lasPropiedades;
    }

}
