/*
 * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andalucía Derechos de
 * explotación propiedad de la Junta de Andalucía. Éste programa es software
 * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los términos
 * de la Licencia EUPL European Public License publicada por el organismo IDABC
 * de la Comisión Europea, en su versión 1.0. o posteriores. Éste programa se
 * distribuye de buena fe, pero SIN NINGUNA GARANTÍA, incluso sin las presuntas
 * garantías implícitas de USABILIDAD o ADECUACIÓN A PROPÓSITO CONCRETO. Para
 * mas información consulte la Licencia EUPL European Public License. Usted
 * recibe una copia de la Licencia EUPL European Public License junto con este
 * programa, si por algún motivo no le es posible visualizarla, puede
 * consultarla en la siguiente URL:
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
 * copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reçu une
 * copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden
 * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */
package org.librae.common.file.impl;

import java.util.Collection;

import org.apache.commons.io.FilenameUtils;
import org.librae.common.file.IFileNamingUtil;

/**
 * Implementación de la interfaz IFileNamingUtil
 * 
 * @author cayetano
 */
public class FileNamingUtilImpl implements IFileNamingUtil {

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#separatorsToUnix(java.lang.String)
     */
    public String separatorsToUnix(String parameter1) {
        return FilenameUtils.separatorsToUnix(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#separatorsToWindows(java.lang.
     * String)
     */
    public String separatorsToWindows(String parameter1) {
        return FilenameUtils.separatorsToWindows(parameter1);
    }

    /*
     * @see (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#separatorsToSystem(java.lang.String
     * )
     */
    public String separatorsToSystem(String parameter1) {
        return FilenameUtils.separatorsToSystem(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileNamingUtil#equals(java.lang.String,
     * java.lang.String)
     */
    public boolean equals(String parameter1, String parameter2) {
        return FilenameUtils.equals(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileNamingUtil#getName(java.lang.String)
     */
    public String getName(String parameter1) {
        return FilenameUtils.getName(parameter1);
    }

    /*
     * 
     */
    public String concat(String parameter1, String parameter2) {
        return FilenameUtils.concat(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileNamingUtil#getPath(java.lang.String)
     */
    public String getPath(String parameter1) {
        return FilenameUtils.getPath(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#getPrefixLength(java.lang.String)
     */
    public Integer getPrefixLength(String parameter1) {
        return FilenameUtils.getPrefixLength(parameter1);
    }

    /*
     * 
     */
    public String normalize(String parameter1) {
        return FilenameUtils.normalize(parameter1);
    }

    /*
     * 
     */
    public String normalizeNoEndSeparator(String parameter1) {
        return FilenameUtils.normalizeNoEndSeparator(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#indexOfLastSeparator(java.lang
     * .String)
     */
    public Integer indexOfLastSeparator(String parameter1) {
        return FilenameUtils.indexOfLastSeparator(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#indexOfExtension(java.lang.String)
     */
    public Integer indexOfExtension(String parameter1) {
        return FilenameUtils.indexOfExtension(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileNamingUtil#getPrefix(java.lang.String)
     */
    public String getPrefix(String parameter1) {
        return FilenameUtils.getPrefix(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#getPathNoEndSeparator(java.lang
     * .String)
     */
    public String getPathNoEndSeparator(String parameter1) {
        return FilenameUtils.getPathNoEndSeparator(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileNamingUtil#getFullPath(java.lang.String)
     */
    public String getFullPath(String parameter1) {
        return FilenameUtils.getFullPath(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#getFullPathNoEndSeparator(java
     * .lang.String)
     */
    public String getFullPathNoEndSeparator(String parameter1) {
        return FilenameUtils.getFullPathNoEndSeparator(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileNamingUtil#getBaseName(java.lang.String)
     */
    public String getBaseName(String parameter1) {
        return FilenameUtils.getBaseName(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#getExtension(java.lang.String)
     */
    public String getExtension(String parameter1) {
        return FilenameUtils.getExtension(parameter1);
    }

    /*
     * 
     */
    public String removeExtension(String parameter1) {
        return FilenameUtils.removeExtension(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#equalsOnSystem(java.lang.String,
     * java.lang.String)
     */
    public boolean equalsOnSystem(String parameter1, String parameter2) {
        return FilenameUtils.equalsOnSystem(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#equalsNormalized(java.lang.String,
     * java.lang.String)
     */
    public boolean equalsNormalized(String parameter1, String parameter2) {
        return FilenameUtils.equalsNormalized(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileNamingUtil#equalsNormalizedOnSystem(java.
     * lang.String, java.lang.String)
     */
    public boolean equalsNormalizedOnSystem(String parameter1, String parameter2) {
        return FilenameUtils.equalsNormalizedOnSystem(parameter1, parameter2);
    }

    /*
     * 
     */
    public boolean isExtension(String parameter1, String parameter2) {
        return FilenameUtils.isExtension(parameter1, parameter2);
    }

    /*
     * 
     */
    public boolean isExtension(String parameter1, Collection parameter2) {
        return FilenameUtils.isExtension(parameter1, parameter2);
    }

    /*
     * 
     */
    public boolean isExtension(String parameter1, String[] parameter2) {
        return FilenameUtils.isExtension(parameter1, parameter2);
    }

    /*
     * 
     */
    public boolean wildcardMatch(String parameter1, String parameter2) {
        return FilenameUtils.wildcardMatch(parameter1, parameter2);
    }

    /*
     * 
     */
    public boolean wildcardMatchOnSystem(String parameter1, String parameter2) {
        return FilenameUtils.wildcardMatchOnSystem(parameter1, parameter2);
    }
}
