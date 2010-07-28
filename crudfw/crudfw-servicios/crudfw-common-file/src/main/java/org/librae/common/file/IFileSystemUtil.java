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
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * General file manipulation utilities.
 * <p>
 * Facilities are provided in the following areas:
 * <ul>
 * <li>writing to a file
 * <li>reading from a file
 * <li>make a directory including parent directories
 * <li>copying files and directories
 * <li>deleting files and directories
 * <li>converting to and from a URL
 * <li>listing files and directories by filter and extension
 * <li>comparing file content
 * <li>file last changed date
 * </ul>
 * <p>
 * Origin of code: Excalibur, Alexandria, Commons-Utils
 * 
 * @author <a href="mailto:burton@relativity.yi.org">Kevin A. Burton</A>
 * @author <a href="mailto:sanders@apache.org">Scott Sanders</a>
 * @author <a href="mailto:dlr@finemaltcoding.com">Daniel Rall</a>
 * @author <a href="mailto:Christoph.Reck@dlr.de">Christoph.Reck</a>
 * @author <a href="mailto:peter@apache.org">Peter Donald</a>
 * @author <a href="mailto:jefft@apache.org">Jeff Turner</a>
 * @author Matthew Hawthorne
 * @author <a href="mailto:jeremias@apache.org">Jeremias Maerki</a>
 * @author Stephen Colebourne
 * @author Ian Springer
 * @author Chris Eldredge
 * @version $Id$
 */
public interface IFileSystemUtil {
    /**
     * The number of bytes in a kilobyte.
     */
    public static final Long   ONE_KB           = new Long(1024);

    /**
     * The number of bytes in a megabyte.
     */
    public static final Long   ONE_MB           = ONE_KB * ONE_KB;

    /**
     * The number of bytes in a gigabyte.
     */
    public static final Long   ONE_GB           = ONE_KB * ONE_MB;

    /**
     * An empty array of type <code>File</code>.
     */
    public static final File[] EMPTY_FILE_ARRAY = new File[0];

    /**
     * Returns a human-readable version of the file size (original is in bytes).
     * 
     * @param size
     *            The number of bytes.
     * @return A human-readable display value (includes units).
     * @todo need for I18N?
     */
    public abstract String byteCountToDisplaySize(Long size);

    /**
     * Implements the same behaviour as the "touch" utility on Unix. It creates
     * a new file with size 0 or, if the file exists already, it is opened and
     * closed without modifying it, but updating the file date and time.
     * 
     * @param file
     *            the File to touch
     * @throws IOException
     *             If an I/O problem occurs
     */
    public abstract void touch(File file) throws IOException;

    /**
     * Finds files within a given directory (and optionally its subdirectories)
     * which match an array of extensions.
     * 
     * @param directory
     *            the directory to search in
     * @param extensions
     *            an array of extensions, ex. {"java","xml"}. If this parameter
     *            is null, all files are returned.
     * @param recursive
     *            If true all subdirectories are searched, too.
     * @return an collection of java.io.File with the matching files
     */
    public abstract Collection listFiles(File directory, String[] extensions,
            Boolean recursive);

    /**
     * <p>
     * Compare the contents of two files to determine if they are equal or not.
     * </p>
     * <p>
     * Code origin: Avalon
     * </p>
     * 
     * @param file1
     *            the first file
     * @param file2
     *            the second file
     * @return true if the content of the files are equal or they both don't
     *         exist, false otherwise
     * @throws IOException
     *             in case of an I/O error
     */
    public abstract Boolean contentEquals(File file1, File file2)
            throws IOException;

    // -----------------------------------------------------------------------
    /**
     * Convert from a <code>URL</code> to a <code>File</code>.
     * <p>
     * From version 1.1 this method will decode the URL. Syntax such as
     * <code>file:///my%20docs/file.txt</code> will be correctly decoded to
     * <code>/my docs/file.txt</code>.
     * 
     * @param url
     *            the file URL to convert, null returns null
     * @return the equivalent <code>File</code> object, or <code>null</code> if
     *         the URL's protocol is not <code>file</code>
     * @throws IllegalArgumentException
     *             if the file is incorrectly encoded
     */
    public abstract File toFile(URL url);

    /**
     * Converts each of an array of <code>URL</code> to a <code>File</code>.
     * <p>
     * Returns an array of the same size as the input. If the input is null, an
     * empty array is returned. If the input contains null, the output array
     * contains null at the same index.
     * <p>
     * This method will decode the URL. Syntax such as
     * <code>file:///my%20docs/file.txt</code> will be correctly decoded to
     * <code>/my docs/file.txt</code>.
     * 
     * @param urls
     *            the file URLs to convert, null returns empty array
     * @return a non-null array of Files matching the input, with a null item if
     *         there was a null at that index in the input array
     * @throws IllegalArgumentException
     *             if any file is not a URL file
     * @throws IllegalArgumentException
     *             if any file is incorrectly encoded
     * @since Commons IO 1.1
     */
    public abstract File[] toFiles(URL[] urls);

    /**
     * Converts each of an array of <code>File</code> to a <code>URL</code>.
     * <p>
     * Returns an array of the same size as the input.
     * 
     * @param files
     *            the files to convert
     * @return an array of URLs matching the input
     * @throws IOException
     *             if a file cannot be converted
     */
    public abstract URL[] toURLs(File[] files) throws IOException;

    // -----------------------------------------------------------------------
    /**
     * Copies a file to a directory preserving the file date.
     * <p>
     * This method copies the contents of the specified source file to a file of
     * the same name in the specified destination directory. The destination
     * directory is created if it does not exist. If the destination file
     * exists, then this method will overwrite it.
     * 
     * @param srcFile
     *            an existing file to copy, must not be null
     * @param destDir
     *            the directory to place the copy in, must not be null
     * @throws NullPointerException
     *             if source or destination is null
     * @throws IOException
     *             if source or destination is invalid
     * @throws IOException
     *             if an IO error occurs during copying
     * @see #copyFile(File, File, Boolean)
     */
    public abstract void copyFileToDirectory(File srcFile, File destDir)
            throws IOException;

    /**
     * Copies a file to a new location preserving the file date.
     * <p>
     * This method copies the contents of the specified source file to the
     * specified destination file. The directory holding the destination file is
     * created if it does not exist. If the destination file exists, then this
     * method will overwrite it.
     * 
     * @param srcFile
     *            an existing file to copy, must not be null
     * @param destFile
     *            the new file, must not be null
     * @throws NullPointerException
     *             if source or destination is null
     * @throws IOException
     *             if source or destination is invalid
     * @throws IOException
     *             if an IO error occurs during copying
     * @see #copyFileToDirectory
     */
    public abstract void copyFile(File srcFile, File destFile)
            throws IOException;

    /**
     * Copies a file to a new location.
     * <p>
     * This method copies the contents of the specified source file to the
     * specified destination file. The directory holding the destination file is
     * created if it does not exist. If the destination file exists, then this
     * method will overwrite it.
     * 
     * @param srcFile
     *            an existing file to copy, must not be null
     * @param destFile
     *            the new file, must not be null
     * @param preserveFileDate
     *            true if the file date of the copy should be the same as the
     *            original
     * @throws NullPointerException
     *             if source or destination is null
     * @throws IOException
     *             if source or destination is invalid
     * @throws IOException
     *             if an IO error occurs during copying
     * @see #copyFileToDirectory
     */
    public abstract void copyFile(File srcFile, File destFile,
            Boolean preserveFileDate) throws IOException;

    // -----------------------------------------------------------------------
    /**
     * Copies a whole directory to a new location preserving the file dates.
     * <p>
     * This method copies the specified directory and all its child directories
     * and files to the specified destination. The destination is the new
     * location and name of the directory. If it already exists, the contents
     * will be overwritten.
     * 
     * @param srcDir
     *            an existing directory to copy, must not be null
     * @param destDir
     *            the new directory, must not be null
     * @throws NullPointerException
     *             if source or destination is null
     * @throws IOException
     *             if source or destination is invalid
     * @throws IOException
     *             if an IO error occurs during copying
     * @since Commons IO 1.1
     */
    public abstract void copyDirectory(File srcDir, File destDir)
            throws IOException;

    /**
     * Copies a whole directory to a new location.
     * <p>
     * This method copies the contents of the specified source directory to
     * within the specified destination directory. The destination directory is
     * created if it does not exist. If the destination directory did exist,
     * then this method merges the source with the destination, with the source
     * taking precedence.
     * 
     * @param srcDir
     *            an existing directory to copy, must not be null
     * @param destDir
     *            the new directory, must not be null
     * @param preserveFileDate
     *            true if the file date of the copy should be the same as the
     *            original
     * @throws NullPointerException
     *             if source or destination is null
     * @throws IOException
     *             if source or destination is invalid
     * @throws IOException
     *             if an IO error occurs during copying
     * @since Commons IO 1.1
     */
    public abstract void copyDirectory(File srcDir, File destDir,
            Boolean preserveFileDate) throws IOException;

    // -----------------------------------------------------------------------
    /**
     * Copies bytes from the URL <code>source</code> to a file
     * <code>destination</code>. The directories up to <code>destination</code>
     * will be created if they don't already exist. <code>destination</code>
     * will be overwritten if it already exists.
     * 
     * @param source
     *            A <code>URL</code> to copy bytes from.
     * @param destination
     *            A non-directory <code>File</code> to write bytes to (possibly
     *            overwriting).
     * @throws IOException
     *             if
     *             <ul>
     *             <li><code>source</code> URL cannot be opened</li>
     *             <li><code>destination</code> cannot be written to</li>
     *             <li>an IO error occurs during copying</li>
     *             </ul>
     */
    public abstract void copyURLToFile(URL source, File destination)
            throws IOException;

    // -----------------------------------------------------------------------
    /**
     * Recursively delete a directory.
     * 
     * @param directory
     *            directory to delete
     * @throws IOException
     *             in case deletion is unsuccessful
     */
    public abstract void deleteDirectory(File directory) throws IOException;

    /**
     * Clean a directory without deleting it.
     * 
     * @param directory
     *            directory to clean
     * @throws IOException
     *             in case cleaning is unsuccessful
     */
    public abstract void cleanDirectory(File directory) throws IOException;

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Reads the contents of a file into a String.
     * </p>
     * 
     * @param file
     *            the file to read
     * @param encoding
     *            the encoding to use, null means platform default
     * @return The file contents or null if read failed.
     * @throws IOException
     *             in case of an I/O error
     * @throws UnsupportedEncodingException
     *             if the encoding is not supported by the VM
     */
    public abstract String readFileToString(File file, String encoding)
            throws IOException;

    /**
     * <p>
     * Reads the contents of a file into a String with platform default
     * encoding.
     * </p>
     * 
     * @param file
     *            the file to read
     * @return The file contents or null if read failed.
     * @throws IOException
     *             in case of an I/O error
     */
    public abstract String readFileToString(File file) throws IOException;

    /**
     * <p>
     * Reads the contents of a file into a byte array.
     * </p>
     * 
     * @param file
     *            the file to read
     * @return The file contents or null if read failed.
     * @throws IOException
     *             in case of an I/O error
     * @since Commons IO 1.1
     */
    public abstract byte[] readFileToByteArray(File file) throws IOException;

    /**
     * <p>
     * Reads the contents of a file line by line to a List of Strings.
     * </p>
     * <p>
     * There is no readLines method without encoding parameter because the
     * default encoding can differ between platforms and therefore results in
     * inconsistent results.
     * </p>
     * 
     * @param file
     *            the file to read
     * @param encoding
     *            the encoding to use, null means platform default
     * @return the list of Strings representing each line in the file
     * @throws IOException
     *             in case of an I/O error
     * @throws UnsupportedEncodingException
     *             if the encoding is not supported by the VM
     * @since Commons IO 1.1
     */
    public abstract List readLines(File file, String encoding)
            throws IOException;

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Writes a String to a file creating the file if it does not exist.
     * </p>
     * <p>
     * There is no writeStringToFile method without encoding parameter because
     * the default encoding can differ between platforms and therefore results
     * in inconsistent results.
     * </p>
     * 
     * @param file
     *            the file to write
     * @param data
     *            the content to write to the file
     * @param encoding
     *            the encoding to use, null means platform default
     * @throws IOException
     *             in case of an I/O error
     * @throws UnsupportedEncodingException
     *             if the encoding is not supported by the VM
     */
    public abstract void writeStringToFile(File file, String data,
            String encoding) throws IOException;

    /**
     * <p>
     * Writes a byte array to a file creating the file if it does not exist.
     * </p>
     * 
     * @param file
     *            the file to write to
     * @param data
     *            the content to write to the file
     * @throws IOException
     *             in case of an I/O error
     * @since Commons IO 1.1
     */
    public abstract void writeByteArrayToFile(File file, byte[] data)
            throws IOException;

    /**
     * <p>
     * Writes the <code>toString()</code> value of each item in a collection to
     * the specified <code>File</code> line by line. The specified character
     * encoding and the default line ending will be used.
     * </p>
     * <p>
     * There is no writeLines method without encoding parameter because the
     * default encoding can differ between platforms and therefore results in
     * inconsistent results.
     * </p>
     * 
     * @param file
     *            the file to write to
     * @param encoding
     *            the encoding to use, null means platform default
     * @param lines
     *            the lines to write, null entries produce blank lines
     * @throws IOException
     *             in case of an I/O error
     * @throws UnsupportedEncodingException
     *             if the encoding is not supported by the VM
     * @since Commons IO 1.1
     */
    public abstract void writeLines(File file, String encoding, Collection lines)
            throws IOException;

    /**
     * <p>
     * Writes the <code>toString()</code> value of each item in a collection to
     * the specified <code>File</code> line by line. The specified character
     * encoding and the line ending will be used.
     * </p>
     * <p>
     * There is no writeLines method without encoding parameter because the
     * default encoding can differ between platforms and therefore results in
     * inconsistent results.
     * </p>
     * 
     * @param file
     *            the file to write to
     * @param encoding
     *            the encoding to use, null means platform default
     * @param lines
     *            the lines to write, null entries produce blank lines
     * @param lineEnding
     *            the line separator to use, null is system default
     * @throws IOException
     *             in case of an I/O error
     * @throws UnsupportedEncodingException
     *             if the encoding is not supported by the VM
     * @since Commons IO 1.1
     */
    public abstract void writeLines(File file, String encoding,
            Collection lines, String lineEnding) throws IOException;

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Delete a file. If file is a directory, delete it and all sub-directories.
     * </p>
     * <p>
     * The difference between File.delete() and this method are:
     * </p>
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>You get exceptions when a file or directory cannot be deleted.
     * (java.io.File methods returns a Boolean)</li>
     * </ul>
     * 
     * @param file
     *            file or directory to delete.
     * @throws IOException
     *             in case deletion is unsuccessful
     */
    public abstract void forceDelete(File file) throws IOException;

    /**
     * Schedule a file to be deleted when JVM exits. If file is directory delete
     * it and all sub-directories.
     * 
     * @param file
     *            file or directory to delete.
     * @throws IOException
     *             in case deletion is unsuccessful
     */
    public abstract void forceDeleteOnExit(File file) throws IOException;

    /**
     * Make a directory, including any necessary but nonexistent parent
     * directories. If there already exists a file with specified name or the
     * directory cannot be created then an exception is thrown.
     * 
     * @param directory
     *            directory to create
     * @throws IOException
     *             if the directory cannot be created.
     */
    public abstract void forceMkdir(File directory) throws IOException;

    /**
     * Recursively count size of a directory (sum of the length of all files).
     * 
     * @param directory
     *            directory to inspect
     * @return size of directory in bytes, 0 if directory is security restricted
     */
    public abstract Long sizeOfDirectory(File directory);

    /**
     * Tests if the specified <code>File</code> is newer than the reference
     * <code>File</code>.
     * 
     * @param file
     *            the <code>File</code> of which the modification date must be
     *            compared.
     * @param reference
     *            the <code>File</code> of which the modification date is used.
     * @return true if the <code>File</code> exists and has been modified more
     *         recently than the reference <code>File</code>.
     */
    public abstract Boolean isFileNewer(File file, File reference);

    /**
     * Tests if the specified <code>File</code> is newer than the specified
     * <code>Date</code>.
     * 
     * @param file
     *            the <code>File</code> of which the modification date must be
     *            compared.
     * @param date
     *            the date reference
     * @return true if the <code>File</code> exists and has been modified after
     *         the given <code>Date</code>.
     */
    public abstract Boolean isFileNewer(File file, Date date);

    /**
     * Tests if the specified <code>File</code> is newer than the specified time
     * reference.
     * 
     * @param file
     *            the <code>File</code> of which the modification date must be
     *            compared.
     * @param timeMillis
     *            the time reference measured in milliseconds since the epoch
     *            (00:00:00 GMT, January 1, 1970)
     * @return true if the <code>File</code> exists and has been modified after
     *         the given time reference.
     */
    public abstract Boolean isFileNewer(File file, Long timeMillis);
}
