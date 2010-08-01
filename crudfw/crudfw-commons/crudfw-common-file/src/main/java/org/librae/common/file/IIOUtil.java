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
package org.librae.common.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

/**
 * General IO stream manipulation utilities.
 * <p>
 * This class provides static utility methods for input/output operations.
 * <ul>
 * <li>closeQuietly - these methods close a stream ignoring nulls and exceptions
 * <li>toXxx/read - these methods read data from a stream
 * <li>write - these methods write data to a stream
 * <li>copy - these methods copy all the data from one stream to another
 * <li>contentEquals - these methods compare the content of two streams
 * </ul>
 * <p>
 * The byte-to-char methods and char-to-byte methods involve a conversion step.
 * Two methods are provided in each case, one that uses the platform default
 * encoding and the other which allows you to specify an encoding. You are
 * encouraged to always specify an encoding because relying on the platform
 * default can lead to unexpected results, for example when moving from
 * development to production.
 * <p>
 * All the methods in this class that read a stream are buffered internally.
 * This means that there is no cause to use a <code>BufferedInputStream</code>
 * or <code>BufferedReader</code>. The default buffer size of 4K has been shown
 * to be efficient in tests.
 * <p>
 * Wherever possible, the methods in this class do <em>not</em> flush or close
 * the stream. This is to avoid making non-portable assumptions about the
 * streams' origin and further use. Thus the caller is still responsible for
 * closing streams after use.
 * <p>
 * Origin of code: Excalibur.
 * 
 * @author Peter Donald
 * @author Jeff Turner
 * @author Matthew Hawthorne
 * @author Stephen Colebourne
 * @author Gareth Davis
 * @author Ian Springer
 * @version $Id$
 */
public interface IIOUtil {
    /**
     * The Unix directory separator character.
     */
    public static final char   DIR_SEPARATOR_UNIX     = '/';

    /**
     * The Windows directory separator character.
     */
    public static final char   DIR_SEPARATOR_WINDOWS  = '\\';

    /**
     * The system directory separator character.
     */
    public static final char   DIR_SEPARATOR          = File.separatorChar;

    /**
     * The Unix line separator string.
     */
    public static final String LINE_SEPARATOR_UNIX    = "\n";

    /**
     * The Windows line separator string.
     */
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";

    // -----------------------------------------------------------------------
    /**
     * Unconditionally close an <code>Reader</code>.
     * <p>
     * Equivalent to {@link Reader#close()}, except any exceptions will be
     * ignored. This is typically used in finally blocks.
     * 
     * @param input
     *            the Reader to close, may be null or already closed
     */
    public abstract void closeQuietly(Reader input);

    /**
     * Unconditionally close a <code>Writer</code>.
     * <p>
     * Equivalent to {@link Writer#close()}, except any exceptions will be
     * ignored. This is typically used in finally blocks.
     * 
     * @param output
     *            the Writer to close, may be null or already closed
     */
    public abstract void closeQuietly(Writer output);

    /**
     * Unconditionally close an <code>InputStream</code>.
     * <p>
     * Equivalent to {@link InputStream#close()}, except any exceptions will be
     * ignored. This is typically used in finally blocks.
     * 
     * @param input
     *            the InputStream to close, may be null or already closed
     */
    public abstract void closeQuietly(InputStream input);

    /**
     * Unconditionally close an <code>OutputStream</code>.
     * <p>
     * Equivalent to {@link OutputStream#close()}, except any exceptions will be
     * ignored. This is typically used in finally blocks.
     * 
     * @param output
     *            the OutputStream to close, may be null or already closed
     */
    public abstract void closeQuietly(OutputStream output);

    // read toByteArray
    // -----------------------------------------------------------------------
    /**
     * Get the contents of an <code>InputStream</code> as a <code>byte[]</code>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param input
     *            the <code>InputStream</code> to read from
     * @return the requested byte array
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     */
    public abstract byte[] toByteArray(InputStream input) throws IOException;

    /**
     * Get the contents of a <code>Reader</code> as a <code>byte[]</code> using
     * the default character encoding of the platform.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * 
     * @param input
     *            the <code>Reader</code> to read from
     * @return the requested byte array
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     */
    public abstract byte[] toByteArray(Reader input) throws IOException;

    /**
     * Get the contents of a <code>Reader</code> as a <code>byte[]</code> using
     * the specified character encoding.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * 
     * @param input
     *            the <code>Reader</code> to read from
     * @param encoding
     *            the encoding to use, null means platform default
     * @return the requested byte array
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract byte[] toByteArray(Reader input, String encoding)
            throws IOException;

    /**
     * Get the contents of an <code>InputStream</code> as a character array
     * using the default character encoding of the platform.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param is
     *            the <code>InputStream</code> to read from
     * @return the requested character array
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract char[] toCharArray(InputStream is) throws IOException;

    /**
     * Get the contents of an <code>InputStream</code> as a character array
     * using the specified character encoding.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param is
     *            the <code>InputStream</code> to read from
     * @param encoding
     *            the encoding to use, null means platform default
     * @return the requested character array
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract char[] toCharArray(InputStream is, String encoding)
            throws IOException;

    /**
     * Get the contents of a <code>Reader</code> as a character array.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * 
     * @param input
     *            the <code>Reader</code> to read from
     * @return the requested character array
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract char[] toCharArray(Reader input) throws IOException;

    // read toString
    // -----------------------------------------------------------------------
    /**
     * Get the contents of an <code>InputStream</code> as a String using the
     * default character encoding of the platform.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param input
     *            the <code>InputStream</code> to read from
     * @return the requested String
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     */
    public abstract String toString(InputStream input) throws IOException;

    /**
     * Get the contents of an <code>InputStream</code> as a String using the
     * specified character encoding.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param input
     *            the <code>InputStream</code> to read from
     * @param encoding
     *            the encoding to use, null means platform default
     * @return the requested String
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     */
    public abstract String toString(InputStream input, String encoding)
            throws IOException;

    /**
     * Get the contents of a <code>Reader</code> as a String.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * 
     * @param input
     *            the <code>Reader</code> to read from
     * @return the requested String
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     */
    public abstract String toString(Reader input) throws IOException;

    // readLines
    // -----------------------------------------------------------------------
    /**
     * Get the contents of an <code>InputStream</code> as a list of Strings, one
     * entry per line, using the default character encoding of the platform.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param input
     *            the <code>InputStream</code> to read from, not null
     * @return the list of Strings, never null
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract List readLines(InputStream input) throws IOException;

    /**
     * Get the contents of an <code>InputStream</code> as a list of Strings, one
     * entry per line, using the specified character encoding.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param input
     *            the <code>InputStream</code> to read from, not null
     * @param encoding
     *            the encoding to use, null means platform default
     * @return the list of Strings, never null
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract List readLines(InputStream input, String encoding)
            throws IOException;

    /**
     * Get the contents of a <code>Reader</code> as a list of Strings, one entry
     * per line.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * 
     * @param input
     *            the <code>Reader</code> to read from, not null
     * @return the list of Strings, never null
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract List readLines(Reader input) throws IOException;

    // -----------------------------------------------------------------------
    /**
     * Convert the specified string to an input stream, encoded as bytes using
     * the default character encoding of the platform.
     * 
     * @param input
     *            the string to convert
     * @return an input stream
     * @since Commons IO 1.1
     */
    public abstract InputStream toInputStream(String input);

    /**
     * Convert the specified string to an input stream, encoded as bytes using
     * the specified character encoding.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * 
     * @param input
     *            the string to convert
     * @param encoding
     *            the encoding to use, null means platform default
     * @throws IOException
     *             if the encoding is invalid
     * @return an input stream
     * @since Commons IO 1.1
     */
    public abstract InputStream toInputStream(String input, String encoding)
            throws IOException;

    // write byte[]
    // -----------------------------------------------------------------------
    /**
     * Writes bytes from a <code>byte[]</code> to an <code>OutputStream</code>.
     * 
     * @param data
     *            the byte array to write, do not modify during output, null
     *            ignored
     * @param output
     *            the <code>OutputStream</code> to write to
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(byte[] data, OutputStream output)
            throws IOException;

    /**
     * Writes bytes from a <code>byte[]</code> to chars on a <code>Writer</code>
     * using the default character encoding of the platform.
     * <p>
     * This method uses {@link String#String(byte[])}.
     * 
     * @param data
     *            the byte array to write, do not modify during output, null
     *            ignored
     * @param output
     *            the <code>Writer</code> to write to
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(byte[] data, Writer output) throws IOException;

    /**
     * Writes bytes from a <code>byte[]</code> to chars on a <code>Writer</code>
     * using the specified character encoding.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method uses {@link String#String(byte[], String)}.
     * 
     * @param data
     *            the byte array to write, do not modify during output, null
     *            ignored
     * @param output
     *            the <code>Writer</code> to write to
     * @param encoding
     *            the encoding to use, null means platform default
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(byte[] data, Writer output, String encoding)
            throws IOException;

    // write char[]
    // -----------------------------------------------------------------------
    /**
     * Writes chars from a <code>char[]</code> to a <code>Writer</code> using
     * the default character encoding of the platform.
     * 
     * @param data
     *            the char array to write, do not modify during output, null
     *            ignored
     * @param output
     *            the <code>Writer</code> to write to
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(char[] data, Writer output) throws IOException;

    /**
     * Writes chars from a <code>char[]</code> to bytes on an
     * <code>OutputStream</code>.
     * <p>
     * This method uses {@link String#String(char[])} and
     * {@link String#getBytes()}.
     * 
     * @param data
     *            the char array to write, do not modify during output, null
     *            ignored
     * @param output
     *            the <code>OutputStream</code> to write to
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(char[] data, OutputStream output)
            throws IOException;

    /**
     * Writes chars from a <code>char[]</code> to bytes on an
     * <code>OutputStream</code> using the specified character encoding.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method uses {@link String#String(char[])} and
     * {@link String#getBytes(String)}.
     * 
     * @param data
     *            the char array to write, do not modify during output, null
     *            ignored
     * @param output
     *            the <code>OutputStream</code> to write to
     * @param encoding
     *            the encoding to use, null means platform default
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(char[] data, OutputStream output, String encoding)
            throws IOException;

    // write String
    // -----------------------------------------------------------------------
    /**
     * Writes chars from a <code>String</code> to a <code>Writer</code>.
     * 
     * @param data
     *            the <code>String</code> to write, null ignored
     * @param output
     *            the <code>Writer</code> to write to
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(String data, Writer output) throws IOException;

    /**
     * Writes chars from a <code>String</code> to bytes on an
     * <code>OutputStream</code> using the default character encoding of the
     * platform.
     * <p>
     * This method uses {@link String#getBytes()}.
     * 
     * @param data
     *            the <code>String</code> to write, null ignored
     * @param output
     *            the <code>OutputStream</code> to write to
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(String data, OutputStream output)
            throws IOException;

    /**
     * Writes chars from a <code>String</code> to bytes on an
     * <code>OutputStream</code> using the specified character encoding.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method uses {@link String#getBytes(String)}.
     * 
     * @param data
     *            the <code>String</code> to write, null ignored
     * @param output
     *            the <code>OutputStream</code> to write to
     * @param encoding
     *            the encoding to use, null means platform default
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(String data, OutputStream output, String encoding)
            throws IOException;

    // write StringBuffer
    // -----------------------------------------------------------------------
    /**
     * Writes chars from a <code>StringBuffer</code> to a <code>Writer</code>.
     * 
     * @param data
     *            the <code>StringBuffer</code> to write, null ignored
     * @param output
     *            the <code>Writer</code> to write to
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(StringBuffer data, Writer output)
            throws IOException;

    /**
     * Writes chars from a <code>StringBuffer</code> to bytes on an
     * <code>OutputStream</code> using the default character encoding of the
     * platform.
     * <p>
     * This method uses {@link String#getBytes()}.
     * 
     * @param data
     *            the <code>StringBuffer</code> to write, null ignored
     * @param output
     *            the <code>OutputStream</code> to write to
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(StringBuffer data, OutputStream output)
            throws IOException;

    /**
     * Writes chars from a <code>StringBuffer</code> to bytes on an
     * <code>OutputStream</code> using the specified character encoding.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method uses {@link String#getBytes(String)}.
     * 
     * @param data
     *            the <code>StringBuffer</code> to write, null ignored
     * @param output
     *            the <code>OutputStream</code> to write to
     * @param encoding
     *            the encoding to use, null means platform default
     * @throws NullPointerException
     *             if output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void write(StringBuffer data, OutputStream output,
            String encoding) throws IOException;

    // writeLines
    // -----------------------------------------------------------------------
    /**
     * Writes the <code>toString()</code> value of each item in a collection to
     * an <code>OutputStream</code> line by line, using the default character
     * encoding of the platform and the specified line ending.
     * 
     * @param lines
     *            the lines to write, null entries produce blank lines
     * @param lineEnding
     *            the line separator to use, null is system default
     * @param output
     *            the <code>OutputStream</code> to write to, not null, not
     *            closed
     * @throws NullPointerException
     *             if the output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void writeLines(Collection lines, String lineEnding,
            OutputStream output) throws IOException;

    /**
     * Writes the <code>toString()</code> value of each item in a collection to
     * an <code>OutputStream</code> line by line, using the specified character
     * encoding and the specified line ending.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * 
     * @param lines
     *            the lines to write, null entries produce blank lines
     * @param lineEnding
     *            the line separator to use, null is system default
     * @param output
     *            the <code>OutputStream</code> to write to, not null, not
     *            closed
     * @param encoding
     *            the encoding to use, null means platform default
     * @throws NullPointerException
     *             if the output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void writeLines(Collection lines, String lineEnding,
            OutputStream output, String encoding) throws IOException;

    /**
     * Writes the <code>toString()</code> value of each item in a collection to
     * a <code>Writer</code> line by line, using the specified line ending.
     * 
     * @param lines
     *            the lines to write, null entries produce blank lines
     * @param lineEnding
     *            the line separator to use, null is system default
     * @param writer
     *            the <code>Writer</code> to write to, not null, not closed
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void writeLines(Collection lines, String lineEnding,
            Writer writer) throws IOException;

    // copy from InputStream
    // -----------------------------------------------------------------------
    /**
     * Copy bytes from an <code>InputStream</code> to an
     * <code>OutputStream</code>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param input
     *            the <code>InputStream</code> to read from
     * @param output
     *            the <code>OutputStream</code> to write to
     * @return the number of bytes copied
     * @throws NullPointerException
     *             if the input or output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract Integer copy(InputStream input, OutputStream output)
            throws IOException;

    /**
     * Copy bytes from an <code>InputStream</code> to chars on a
     * <code>Writer</code> using the default character encoding of the platform.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * <p>
     * This method uses {@link InputStreamReader}.
     * 
     * @param input
     *            the <code>InputStream</code> to read from
     * @param output
     *            the <code>Writer</code> to write to
     * @throws NullPointerException
     *             if the input or output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void copy(InputStream input, Writer output)
            throws IOException;

    /**
     * Copy bytes from an <code>InputStream</code> to chars on a
     * <code>Writer</code> using the specified character encoding.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method uses {@link InputStreamReader}.
     * 
     * @param input
     *            the <code>InputStream</code> to read from
     * @param output
     *            the <code>Writer</code> to write to
     * @param encoding
     *            the encoding to use, null means platform default
     * @throws NullPointerException
     *             if the input or output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void copy(InputStream input, Writer output, String encoding)
            throws IOException;

    // copy from Reader
    // -----------------------------------------------------------------------
    /**
     * Copy chars from a <code>Reader</code> to a <code>Writer</code>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * 
     * @param input
     *            the <code>Reader</code> to read from
     * @param output
     *            the <code>Writer</code> to write to
     * @return the number of characters copied
     * @throws NullPointerException
     *             if the input or output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract Integer copy(Reader input, Writer output)
            throws IOException;

    /**
     * Copy chars from a <code>Reader</code> to bytes on an
     * <code>OutputStream</code> using the default character encoding of the
     * platform, and calling flush.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * <p>
     * Due to the implementation of OutputStreamWriter, this method performs a
     * flush.
     * <p>
     * This method uses {@link OutputStreamWriter}.
     * 
     * @param input
     *            the <code>Reader</code> to read from
     * @param output
     *            the <code>OutputStream</code> to write to
     * @throws NullPointerException
     *             if the input or output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void copy(Reader input, OutputStream output)
            throws IOException;

    /**
     * Copy chars from a <code>Reader</code> to bytes on an
     * <code>OutputStream</code> using the specified character encoding, and
     * calling flush.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * <p>
     * char encoding names can be found at <a
     * href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * Due to the implementation of OutputStreamWriter, this method performs a
     * flush.
     * <p>
     * This method uses {@link OutputStreamWriter}.
     * 
     * @param input
     *            the <code>Reader</code> to read from
     * @param output
     *            the <code>OutputStream</code> to write to
     * @param encoding
     *            the encoding to use, null means platform default
     * @throws NullPointerException
     *             if the input or output is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract void copy(Reader input, OutputStream output, String encoding)
            throws IOException;

    // content equals
    // -----------------------------------------------------------------------
    /**
     * Compare the contents of two Streams to determine if they are equal or
     * not.
     * <p>
     * This method buffers the input internally using
     * <code>BufferedInputStream</code> if they are not already buffered.
     * 
     * @param input1
     *            the first stream
     * @param input2
     *            the second stream
     * @return true if the content of the streams are equal or they both don't
     *         exist, false otherwise
     * @throws NullPointerException
     *             if either input is null
     * @throws IOException
     *             if an I/O error occurs
     */
    public abstract Boolean contentEquals(InputStream input1, InputStream input2)
            throws IOException;

    /**
     * Compare the contents of two Readers to determine if they are equal or
     * not.
     * <p>
     * This method buffers the input internally using
     * <code>BufferedReader</code> if they are not already buffered.
     * 
     * @param input1
     *            the first reader
     * @param input2
     *            the second reader
     * @return true if the content of the readers are equal or they both don't
     *         exist, false otherwise
     * @throws NullPointerException
     *             if either input is null
     * @throws IOException
     *             if an I/O error occurs
     * @since Commons IO 1.1
     */
    public abstract Boolean contentEquals(Reader input1, Reader input2)
            throws IOException;
}
