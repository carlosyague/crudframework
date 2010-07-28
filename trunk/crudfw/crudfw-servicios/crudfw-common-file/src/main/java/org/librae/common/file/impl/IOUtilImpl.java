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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.librae.common.file.IIOUtil;

public class IOUtilImpl implements IIOUtil {

    /**
     * @see org.librae.common.file.IIOUtil#toString(java.io.InputStream)
     */
    public String toString(InputStream parameter1) throws IOException {
        return IOUtils.toString(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toString(java.io.InputStream,
     * java.lang.String)
     */
    public String toString(InputStream parameter1, String parameter2)
            throws IOException {
        return IOUtils.toString(parameter1, parameter2);
    }

    /*
     *
     */
    public String toString(byte[] parameter1) throws IOException {
        return IOUtils.toString(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toString(java.io.Reader)
     */
    public String toString(Reader parameter1) throws IOException {
        return IOUtils.toString(parameter1);
    }

   /*
    *
    */
    public String toString(byte[] parameter1, String parameter2)
            throws IOException {
        return IOUtils.toString(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#contentEquals(java.io.InputStream,
     * java.io.InputStream)
     */
    public Boolean contentEquals(InputStream parameter1, InputStream parameter2)
            throws IOException {
        return IOUtils.contentEquals(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#contentEquals(java.io.Reader,
     * java.io.Reader)
     */
    public Boolean contentEquals(Reader parameter1, Reader parameter2)
            throws IOException {
        return IOUtils.contentEquals(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toCharArray(java.io.InputStream)
     */
    public char[] toCharArray(InputStream parameter1) throws IOException {
        return IOUtils.toCharArray(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toCharArray(java.io.InputStream,
     * java.lang.String)
     */
    public char[] toCharArray(InputStream parameter1, String parameter2)
            throws IOException {
        return IOUtils.toCharArray(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toCharArray(java.io.Reader)
     */
    public char[] toCharArray(Reader parameter1) throws IOException {
        return IOUtils.toCharArray(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(java.lang.StringBuffer,
     * java.io.Writer)
     */
    public void write(StringBuffer parameter1, Writer parameter2)
            throws IOException {
        IOUtils.write(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(java.lang.StringBuffer,
     * java.io.OutputStream)
     */
    public void write(StringBuffer parameter1, OutputStream parameter2)
            throws IOException {
        IOUtils.write(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(java.lang.StringBuffer,
     * java.io.OutputStream, java.lang.String)
     */
    public void write(StringBuffer parameter1, OutputStream parameter2,
            String parameter3) throws IOException {
        IOUtils.write(parameter1, parameter2, parameter3);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(byte[], java.io.Writer,
     * java.lang.String)
     */
    public void write(byte[] parameter1, Writer parameter2, String parameter3)
            throws IOException {
        IOUtils.write(parameter1, parameter2, parameter3);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(java.lang.String,
     * java.io.OutputStream, java.lang.String)
     */
    public void write(String parameter1, OutputStream parameter2,
            String parameter3) throws IOException {
        IOUtils.write(parameter1, parameter2, parameter3);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(java.lang.String,
     * java.io.OutputStream)
     */
    public void write(String parameter1, OutputStream parameter2)
            throws IOException {
        IOUtils.write(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(java.lang.String,
     * java.io.Writer)
     */
    public void write(String parameter1, Writer parameter2) throws IOException {
        IOUtils.write(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(char[], java.io.OutputStream,
     * java.lang.String)
     */
    public void write(char[] parameter1, OutputStream parameter2,
            String parameter3) throws IOException {
        IOUtils.write(parameter1, parameter2, parameter3);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(char[], java.io.OutputStream)
     */
    public void write(char[] parameter1, OutputStream parameter2)
            throws IOException {
        IOUtils.write(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(char[], java.io.Writer)
     */
    public void write(char[] parameter1, Writer parameter2) throws IOException {
        IOUtils.write(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(byte[], java.io.OutputStream)
     */
    public void write(byte[] parameter1, OutputStream parameter2)
            throws IOException {
        IOUtils.write(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#write(byte[], java.io.Writer)
     */
    public void write(byte[] parameter1, Writer parameter2) throws IOException {
        IOUtils.write(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#copy(java.io.InputStream,
     * java.io.Writer)
     */
    public void copy(InputStream parameter1, Writer parameter2)
            throws IOException {
        IOUtils.copy(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#copy(java.io.InputStream,
     * java.io.Writer, java.lang.String)
     */
    public void copy(InputStream parameter1, Writer parameter2,
            String parameter3) throws IOException {
        IOUtils.copy(parameter1, parameter2, parameter3);
    }

    /**
     * @see org.librae.common.file.IIOUtil#copy(java.io.Reader,
     * java.io.OutputStream, java.lang.String)
     */
    public void copy(Reader parameter1, OutputStream parameter2,
            String parameter3) throws IOException {
        IOUtils.copy(parameter1, parameter2, parameter3);
    }

    /**
     * @see org.librae.common.file.IIOUtil#copy(java.io.Reader,
     * java.io.OutputStream)
     */
    public void copy(Reader parameter1, OutputStream parameter2)
            throws IOException {
        IOUtils.copy(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#copy(java.io.Reader, java.io.Writer)
     */
    public Integer copy(Reader parameter1, Writer parameter2)
            throws IOException {
        return IOUtils.copy(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#copy(java.io.InputStream,
     * java.io.OutputStream)
     */
    public Integer copy(InputStream parameter1, OutputStream parameter2)
            throws IOException {
        return IOUtils.copy(parameter1, parameter2);
    }

    /*
     *
     */
    public byte[] toByteArray(String parameter1) throws IOException {
        return IOUtils.toByteArray(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toByteArray(java.io.Reader,
     * java.lang.String)
     */
    public byte[] toByteArray(Reader parameter1, String parameter2)
            throws IOException {
        return IOUtils.toByteArray(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toByteArray(java.io.Reader)
     */
    public byte[] toByteArray(Reader parameter1) throws IOException {
        return IOUtils.toByteArray(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toByteArray(java.io.InputStream)
     */
    public byte[] toByteArray(InputStream parameter1) throws IOException {
        return IOUtils.toByteArray(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#closeQuietly(java.io.Writer)
     */
    public void closeQuietly(Writer parameter1) {
        IOUtils.closeQuietly(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#closeQuietly(java.io.InputStream)
     */
    public void closeQuietly(InputStream parameter1) {
        IOUtils.closeQuietly(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#closeQuietly(java.io.Reader)
     */
    public void closeQuietly(Reader parameter1) {
        IOUtils.closeQuietly(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#closeQuietly(java.io.OutputStream)
     */
    public void closeQuietly(OutputStream parameter1) {
        IOUtils.closeQuietly(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#readLines(java.io.InputStream,
     * java.lang.String)
     */
    public List readLines(InputStream parameter1, String parameter2)
            throws IOException {
        return IOUtils.readLines(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#readLines(java.io.InputStream)
     */
    public List readLines(InputStream parameter1) throws IOException {
        return IOUtils.readLines(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#readLines(java.io.Reader)
     */
    public List readLines(Reader parameter1) throws IOException {
        return IOUtils.readLines(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toInputStream(java.lang.String)
     */
    public InputStream toInputStream(String parameter1) {
        return IOUtils.toInputStream(parameter1);
    }

    /**
     * @see org.librae.common.file.IIOUtil#toInputStream(java.lang.String,
     * java.lang.String)
     */
    public InputStream toInputStream(String parameter1, String parameter2)
            throws IOException {
        return IOUtils.toInputStream(parameter1, parameter2);
    }

    /**
     * @see org.librae.common.file.IIOUtil#writeLines(java.util.Collection,
     * java.lang.String, java.io.OutputStream)
     */
    public void writeLines(Collection parameter1, String parameter2,
            OutputStream parameter3) throws IOException {
        IOUtils.writeLines(parameter1, parameter2, parameter3);
    }

    /**
     * @see org.librae.common.file.IIOUtil#writeLines(java.util.Collection,
     * java.lang.String, java.io.OutputStream, java.lang.String)
     */
    public void writeLines(Collection parameter1, String parameter2,
            OutputStream parameter3, String parameter4) throws IOException {
        IOUtils.writeLines(parameter1, parameter2, parameter3, parameter4);
    }

    /**
     * @see org.librae.common.file.IIOUtil#writeLines(java.util.Collection,
     * java.lang.String, java.io.Writer)
     */
    public void writeLines(Collection parameter1, String parameter2,
            Writer parameter3) throws IOException {
        IOUtils.writeLines(parameter1, parameter2, parameter3);
    }
}
