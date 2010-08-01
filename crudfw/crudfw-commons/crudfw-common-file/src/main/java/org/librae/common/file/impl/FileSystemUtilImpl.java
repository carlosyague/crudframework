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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.librae.common.file.IFileSystemUtil;

public class FileSystemUtilImpl implements IFileSystemUtil {

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#contentEquals(java.io.File,
     * java.io.File)
     */
    public Boolean contentEquals(File parameter1, File parameter2)
            throws IOException {
        return FileUtils.contentEquals(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#listFiles(java.io.File,
     * java.lang.String[], Boolean)
     */
    public Collection listFiles(File parameter1, String[] parameter2,
            Boolean parameter3) {
        return FileUtils.listFiles(parameter1, parameter2, parameter3);
    }

    /*
     * 
     */
    public Collection listFiles(File parameter1, IOFileFilter parameter2,
            IOFileFilter parameter3) {
        return FileUtils.listFiles(parameter1, parameter2, parameter3);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#touch(java.io.File)
     */
    public void touch(File parameter1) throws IOException {
        FileUtils.touch(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#byteCountToDisplaySize(Long)
     */
    public String byteCountToDisplaySize(Long parameter1) {
        return FileUtils.byteCountToDisplaySize(parameter1);
    }

    /*
     * 
     */
    public File[] convertFileCollectionToFileArray(Collection parameter1) {
        return FileUtils.convertFileCollectionToFileArray(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#toFile(java.net.URL)
     */
    public File toFile(URL parameter1) {
        return FileUtils.toFile(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#toFiles(java.net.URL[])
     */
    public File[] toFiles(URL[] parameter1) {
        return FileUtils.toFiles(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#toURLs(java.io.File[])
     */
    public URL[] toURLs(File[] parameter1) throws IOException {
        return FileUtils.toURLs(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileSystemUtil#copyFileToDirectory(java.io.File,
     * java.io.File)
     */
    public void copyFileToDirectory(File parameter1, File parameter2)
            throws IOException {
        FileUtils.copyFileToDirectory(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#copyFile(java.io.File,
     * java.io.File, Boolean)
     */
    public void copyFile(File parameter1, File parameter2, Boolean parameter3)
            throws IOException {
        FileUtils.copyFile(parameter1, parameter2, parameter3);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#copyFile(java.io.File,
     * java.io.File)
     */
    public void copyFile(File parameter1, File parameter2) throws IOException {
        FileUtils.copyFile(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#copyDirectory(java.io.File,
     * java.io.File, Boolean)
     */
    public void copyDirectory(File parameter1, File parameter2,
            Boolean parameter3) throws IOException {
        FileUtils.copyDirectory(parameter1, parameter2, parameter3);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#copyDirectory(java.io.File,
     * java.io.File)
     */
    public void copyDirectory(File parameter1, File parameter2)
            throws IOException {
        FileUtils.copyDirectory(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#copyURLToFile(java.net.URL,
     * java.io.File)
     */
    public void copyURLToFile(URL parameter1, File parameter2)
            throws IOException {
        FileUtils.copyURLToFile(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#deleteDirectory(java.io.File)
     */
    public void deleteDirectory(File parameter1) throws IOException {
        FileUtils.deleteDirectory(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#cleanDirectory(java.io.File)
     */
    public void cleanDirectory(File parameter1) throws IOException {
        FileUtils.cleanDirectory(parameter1);
    }

    /*
     * 
     */
    public Boolean waitFor(File parameter1, Integer parameter2) {
        return FileUtils.waitFor(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileSystemUtil#readFileToString(java.io.File,
     * java.lang.String)
     */
    public String readFileToString(File parameter1, String parameter2)
            throws IOException {
        return FileUtils.readFileToString(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileSystemUtil#readFileToByteArray(java.io.File)
     */
    public byte[] readFileToByteArray(File parameter1) throws IOException {
        return FileUtils.readFileToByteArray(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#readLines(java.io.File,
     * java.lang.String)
     */
    public List readLines(File parameter1, String parameter2)
            throws IOException {
        return FileUtils.readLines(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileSystemUtil#writeStringToFile(java.io.File,
     * java.lang.String, java.lang.String)
     */
    public void writeStringToFile(File parameter1, String parameter2,
            String parameter3) throws IOException {
        FileUtils.writeStringToFile(parameter1, parameter2, parameter3);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileSystemUtil#writeByteArrayToFile(java.io.File,
     * byte[])
     */
    public void writeByteArrayToFile(File parameter1, byte[] parameter2)
            throws IOException {
        FileUtils.writeByteArrayToFile(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#writeLines(java.io.File,
     * java.lang.String, java.util.Collection, java.lang.String)
     */
    public void writeLines(File parameter1, String parameter2,
            Collection parameter3, String parameter4) throws IOException {
        FileUtils.writeLines(parameter1, parameter2, parameter3, parameter4);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#writeLines(java.io.File,
     * java.lang.String, java.util.Collection)
     */
    public void writeLines(File parameter1, String parameter2,
            Collection parameter3) throws IOException {
        FileUtils.writeLines(parameter1, parameter2, parameter3);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#forceDelete(java.io.File)
     */
    public void forceDelete(File parameter1) throws IOException {
        FileUtils.forceDelete(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileSystemUtil#forceDeleteOnExit(java.io.File)
     */
    public void forceDeleteOnExit(File parameter1) throws IOException {
        FileUtils.forceDeleteOnExit(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#forceMkdir(java.io.File)
     */
    public void forceMkdir(File parameter1) throws IOException {
        FileUtils.forceMkdir(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#sizeOfDirectory(java.io.File)
     */
    public Long sizeOfDirectory(File parameter1) {
        return FileUtils.sizeOfDirectory(parameter1);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#isFileNewer(java.io.File,
     * Long)
     */
    public Boolean isFileNewer(File parameter1, Long parameter2) {
        return FileUtils.isFileNewer(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#isFileNewer(java.io.File,
     * java.util.Date)
     */
    public Boolean isFileNewer(File parameter1, Date parameter2) {
        return FileUtils.isFileNewer(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.file.IFileSystemUtil#isFileNewer(java.io.File,
     * java.io.File)
     */
    public Boolean isFileNewer(File parameter1, File parameter2) {
        return FileUtils.isFileNewer(parameter1, parameter2);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.librae.common.file.IFileSystemUtil#readFileToString(java.io.File)
     */
    public String readFileToString(File file) throws IOException {
        return this.readFileToString(file, null);
    }
}
