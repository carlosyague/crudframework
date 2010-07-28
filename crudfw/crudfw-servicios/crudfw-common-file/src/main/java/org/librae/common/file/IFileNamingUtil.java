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

/**
 * General filename and filepath manipulation utilities.
 * <p>
 * When dealing with filenames you can hit problems when moving from a Windows
 * based development machine to a Unix based production machine. This class aims
 * to help avoid those problems.
 * <p>
 * <b>NOTE</b>: You may be able to avoid using this class entirely simply by
 * using JDK {@link java.io.File File} objects and the two argument constructor
 * {@link java.io.File#File(java.io.File, java.lang.String) File(File,String)}.
 * <p>
 * Most methods on this class are designed to work the same on both Unix and
 * Windows. Those that don't include 'System', 'Unix' or 'Windows' in their
 * name.
 * <p>
 * Most methods recognise both separators (forward and back), and both sets of
 * prefixes. See the javadoc of each method for details.
 * <p>
 * This class defines six components within a filename (example
 * C:\dev\project\file.txt):
 * <ul>
 * <li>the prefix - C:\</li>
 * <li>the path - dev\project\</li>
 * <li>the full path - C:\dev\project\</li>
 * <li>the name - file.txt</li>
 * <li>the base name - file</li>
 * <li>the extension - txt</li>
 * </ul>
 * Note that this class works best if directory filenames end with a separator.
 * If you omit the last separator, it is impossible to determine if the filename
 * corresponds to a file or a directory. As a result, we have chosen to say it
 * corresponds to a file.
 * <p>
 * This class only supports Unix and Windows style names. Prefixes are matched
 * as follows:
 * 
 * <pre>
 *  Windows:
 *  a\b\c.txt           --&gt; &quot;&quot;          --&gt; relative
 *  \a\b\c.txt          --&gt; &quot;\&quot;         --&gt; current drive absolute
 *  C:a\b\c.txt         --&gt; &quot;C:&quot;        --&gt; drive relative
 *  C:\a\b\c.txt        --&gt; &quot;C:\&quot;       --&gt; absolute
 *  \\server\a\b\c.txt  --&gt; &quot;\\server\&quot; --&gt; UNC
 * 
 *  Unix:
 *  a/b/c.txt           --&gt; &quot;&quot;          --&gt; relative
 *  /a/b/c.txt          --&gt; &quot;/&quot;         --&gt; absolute
 *  &tilde;/a/b/c.txt         --&gt; &quot;&tilde;/&quot;        --&gt; current user
 *  &tilde;                   --&gt; &quot;&tilde;/&quot;        --&gt; current user (slash added)
 *  &tilde;user/a/b/c.txt     --&gt; &quot;&tilde;user/&quot;    --&gt; named user
 *  &tilde;user               --&gt; &quot;&tilde;user/&quot;    --&gt; named user (slash added)
 * </pre>
 * 
 * Both prefix styles are matched always, irrespective of the machine that you
 * are currently running on.
 * <p>
 * Origin of code: Excalibur, Alexandria, Tomcat, Commons-Utils.
 * 
 * @author <a href="mailto:burton@relativity.yi.org">Kevin A. Burton</A>
 * @author <a href="mailto:sanders@apache.org">Scott Sanders</a>
 * @author <a href="mailto:dlr@finemaltcoding.com">Daniel Rall</a>
 * @author <a href="mailto:Christoph.Reck@dlr.de">Christoph.Reck</a>
 * @author <a href="mailto:peter@apache.org">Peter Donald</a>
 * @author <a href="mailto:jefft@apache.org">Jeff Turner</a>
 * @author Matthew Hawthorne
 * @author Martin Cooper
 * @author <a href="mailto:jeremias@apache.org">Jeremias Maerki</a>
 * @author Stephen Colebourne
 * @version $Id$
 * @since Commons IO 1.1
 */
public interface IFileNamingUtil {
    // -----------------------------------------------------------------------
    /**
     * Converts all separators to the Unix separator of forward slash.
     * 
     * @param path
     *            the path to be changed, null ignored
     * @return the updated path
     */
    public abstract String separatorsToUnix(String path);

    /**
     * Converts all separators to the Windows separator of backslash.
     * 
     * @param path
     *            the path to be changed, null ignored
     * @return the updated path
     */
    public abstract String separatorsToWindows(String path);

    /**
     * Converts all separators to the system separator.
     * 
     * @param path
     *            the path to be changed, null ignored
     * @return the updated path
     */
    public abstract String separatorsToSystem(String path);

    // -----------------------------------------------------------------------
    /**
     * Returns the length of the filename prefix, such as <code>C:/</code> or
     * <code>~/</code>.
     * <p>
     * This method will handle a file in either Unix or Windows format.
     * <p>
     * The prefix length includes the first slash in the full filename if
     * applicable. Thus, it is possible that the length returned is greater than
     * the length of the input string.
     * 
     * <pre>
     * Windows:
     * a\b\c.txt           --&gt; &quot;&quot;          --&gt; relative
     * \a\b\c.txt          --&gt; &quot;\&quot;         --&gt; current drive absolute
     * C:a\b\c.txt         --&gt; &quot;C:&quot;        --&gt; drive relative
     * C:\a\b\c.txt        --&gt; &quot;C:\&quot;       --&gt; absolute
     * \\server\a\b\c.txt  --&gt; &quot;\\server\&quot; --&gt; UNC
     * 
     * Unix:
     * a/b/c.txt           --&gt; &quot;&quot;          --&gt; relative
     * /a/b/c.txt          --&gt; &quot;/&quot;         --&gt; absolute
     * &tilde;/a/b/c.txt         --&gt; &quot;&tilde;/&quot;        --&gt; current user
     * &tilde;                   --&gt; &quot;&tilde;/&quot;        --&gt; current user (slash added)
     * &tilde;user/a/b/c.txt     --&gt; &quot;&tilde;user/&quot;    --&gt; named user
     * &tilde;user               --&gt; &quot;&tilde;user/&quot;    --&gt; named user (slash added)
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on. ie. both Unix and Windows prefixes are matched regardless.
     * 
     * @param filename
     *            the filename to find the prefix in, null returns -1
     * @return the length of the prefix, -1 if invalid or null
     */
    public abstract Integer getPrefixLength(String filename);

    /**
     * Returns the index of the last directory separator character.
     * <p>
     * This method will handle a file in either Unix or Windows format. The
     * position of the last forward or backslash is returned.
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on.
     * 
     * @param filename
     *            the filename to find the last path separator in, null returns
     *            -1
     * @return the index of the last separator character, or -1 if there is no
     *         such character
     */
    public abstract Integer indexOfLastSeparator(String filename);

    /**
     * Returns the index of the last extension separator character, which is a
     * dot.
     * <p>
     * This method also checks that there is no directory separator after the
     * last dot. To do this it uses {@link #indexOfLastSeparator(String)} which
     * will handle a file in either Unix or Windows format.
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on.
     * 
     * @param filename
     *            the filename to find the last path separator in, null returns
     *            -1
     * @return the index of the last separator character, or -1 if there is no
     *         such character
     */
    public abstract Integer indexOfExtension(String filename);

    // -----------------------------------------------------------------------
    /**
     * Gets the prefix from a full filename, such as <code>C:/</code> or
     * <code>~/</code>.
     * <p>
     * This method will handle a file in either Unix or Windows format. The
     * prefix includes the first slash in the full filename where applicable.
     * 
     * <pre>
     * Windows:
     * a\b\c.txt           --&gt; &quot;&quot;          --&gt; relative
     * \a\b\c.txt          --&gt; &quot;\&quot;         --&gt; current drive absolute
     * C:a\b\c.txt         --&gt; &quot;C:&quot;        --&gt; drive relative
     * C:\a\b\c.txt        --&gt; &quot;C:\&quot;       --&gt; absolute
     * \\server\a\b\c.txt  --&gt; &quot;\\server\&quot; --&gt; UNC
     * 
     * Unix:
     * a/b/c.txt           --&gt; &quot;&quot;          --&gt; relative
     * /a/b/c.txt          --&gt; &quot;/&quot;         --&gt; absolute
     * &tilde;/a/b/c.txt         --&gt; &quot;&tilde;/&quot;        --&gt; current user
     * &tilde;                   --&gt; &quot;&tilde;/&quot;        --&gt; current user (slash added)
     * &tilde;user/a/b/c.txt     --&gt; &quot;&tilde;user/&quot;    --&gt; named user
     * &tilde;user               --&gt; &quot;&tilde;user/&quot;    --&gt; named user (slash added)
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on. ie. both Unix and Windows prefixes are matched regardless.
     * 
     * @param filename
     *            the filename to query, null returns null
     * @return the prefix of the file, null if invalid
     */
    public abstract String getPrefix(String filename);

    /**
     * Gets the path from a full filename, which excludes the prefix.
     * <p>
     * This method will handle a file in either Unix or Windows format. The
     * method is entirely text based, and returns the text before and including
     * the last forward or backslash.
     * 
     * <pre>
     * C:\a\b\c.txt --&gt; a\b\
     * &tilde;/a/b/c.txt  --&gt; a/b
     * a.txt        --&gt; &quot;&quot;
     * a/b/c        --&gt; a/b
     * a/b/c/       --&gt; a/b/c
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on.
     * <p>
     * This method drops the prefix from the result. See
     * {@link #getFullPath(String)} for the method that retains the prefix.
     * 
     * @param filename
     *            the filename to query, null returns null
     * @return the path of the file, an empty string if none exists, null if
     *         invalid
     */
    public abstract String getPath(String filename);

    /**
     * Gets the path from a full filename, which excludes the prefix, and also
     * excluding the final directory separator.
     * <p>
     * This method will handle a file in either Unix or Windows format. The
     * method is entirely text based, and returns the text before the last
     * forward or backslash.
     * 
     * <pre>
     * C:\a\b\c.txt --&gt; a\b
     * &tilde;/a/b/c.txt  --&gt; a/b
     * a.txt        --&gt; &quot;&quot;
     * a/b/c        --&gt; a/b
     * a/b/c/       --&gt; a/b/c
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on.
     * <p>
     * This method drops the prefix from the result. See
     * {@link #getFullPathNoEndSeparator(String)} for the method that retains
     * the prefix.
     * 
     * @param filename
     *            the filename to query, null returns null
     * @return the path of the file, an empty string if none exists, null if
     *         invalid
     */
    public abstract String getPathNoEndSeparator(String filename);

    /**
     * Gets the full path from a full filename, which is the prefix + path.
     * <p>
     * This method will handle a file in either Unix or Windows format. The
     * method is entirely text based, and returns the text before and including
     * the last forward or backslash.
     * 
     * <pre>
     * C:\a\b\c.txt --&gt; C:\a\b\
     * &tilde;/a/b/c.txt  --&gt; &tilde;/a/b
     * a.txt        --&gt; &quot;&quot;
     * a/b/c        --&gt; a/b
     * a/b/c/       --&gt; a/b/c
     * C:           --&gt; C:
     * C:\          --&gt; C:\
     * &tilde;            --&gt; &tilde;
     * &tilde;/           --&gt; &tilde;
     * &tilde;user        --&gt; &tilde;user
     * &tilde;user/       --&gt; &tilde;user
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on.
     * 
     * @param filename
     *            the filename to query, null returns null
     * @return the path of the file, an empty string if none exists, null if
     *         invalid
     */
    public abstract String getFullPath(String filename);

    /**
     * Gets the full path from a full filename, which is the prefix + path, and
     * also excluding the final directory separator.
     * <p>
     * This method will handle a file in either Unix or Windows format. The
     * method is entirely text based, and returns the text before the last
     * forward or backslash.
     * 
     * <pre>
     * C:\a\b\c.txt --&gt; C:\a\b
     * &tilde;/a/b/c.txt  --&gt; &tilde;/a/b
     * a.txt        --&gt; &quot;&quot;
     * a/b/c        --&gt; a/b
     * a/b/c/       --&gt; a/b/c
     * C:           --&gt; C:
     * C:\          --&gt; C:\
     * &tilde;            --&gt; &tilde;
     * &tilde;/           --&gt; &tilde;
     * &tilde;user        --&gt; &tilde;user
     * &tilde;user/       --&gt; &tilde;user
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on.
     * 
     * @param filename
     *            the filename to query, null returns null
     * @return the path of the file, an empty string if none exists, null if
     *         invalid
     */
    public abstract String getFullPathNoEndSeparator(String filename);

    /**
     * Gets the name minus the path from a full filename.
     * <p>
     * This method will handle a file in either Unix or Windows format. The text
     * after the last forward or backslash is returned.
     * 
     * <pre>
     * a/b/c.txt --&gt; c.txt
     * a.txt     --&gt; a.txt
     * a/b/c     --&gt; c
     * a/b/c/    --&gt; &quot;&quot;
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on.
     * 
     * @param filename
     *            the filename to query, null returns null
     * @return the name of the file without the path, or an empty string if none
     *         exists
     */
    public abstract String getName(String filename);

    /**
     * Gets the base name, minus the full path and extension, from a full
     * filename.
     * <p>
     * This method will handle a file in either Unix or Windows format. The text
     * after the last forward or backslash and before the last dot is returned.
     * 
     * <pre>
     * a/b/c.txt --&gt; c
     * a.txt     --&gt; a
     * a/b/c     --&gt; c
     * a/b/c/    --&gt; &quot;&quot;
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on.
     * 
     * @param filename
     *            the filename to query, null returns null
     * @return the name of the file without the path, or an empty string if none
     *         exists
     */
    public abstract String getBaseName(String filename);

    /**
     * Gets the extension of a filename.
     * <p>
     * This method returns the textual part of the filename after the last dot.
     * There must be no directory separator after the dot.
     * 
     * <pre>
     * foo.txt      --&gt; &quot;txt&quot;
     * a/b/c.jpg    --&gt; &quot;jpg&quot;
     * a/b.txt/c    --&gt; &quot;&quot;
     * a/b/c        --&gt; &quot;&quot;
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is
     * running on.
     * 
     * @param filename
     *            the filename to retrieve the extension of.
     * @return the extension of the file or an empty string if none exists.
     */
    public abstract String getExtension(String filename);

    // -----------------------------------------------------------------------
    /**
     * Checks whether two filenames are equal exactly.
     * <p>
     * No processing is performed on the filenames other than comparison, thus
     * this is merely a null-safe case-sensitive equals.
     * 
     * @param filename1
     *            the first filename to query, may be null
     * @param filename2
     *            the second filename to query, may be null
     * @return true if the filenames are equal, null equals null
     */
    public abstract boolean equals(String filename1, String filename2);

    /**
     * Checks whether two filenames are equal using the case rules of the
     * system.
     * <p>
     * No processing is performed on the filenames other than comparison. The
     * check is case-sensitive on Unix and case-insensitive on Windows.
     * 
     * @param filename1
     *            the first filename to query, may be null
     * @param filename2
     *            the second filename to query, may be null
     * @return true if the filenames are equal, null equals null
     */
    public abstract boolean equalsOnSystem(String filename1, String filename2);

    // -----------------------------------------------------------------------
    /**
     * Checks whether two filenames are equal after both have been normalized.
     * <p>
     * Both filenames are first passed to {@link #normalize(String)}. The check
     * is then performed in a case-sensitive manner.
     * 
     * @param filename1
     *            the first filename to query, may be null
     * @param filename2
     *            the second filename to query, may be null
     * @return true if the filenames are equal, null equals null
     */
    public abstract boolean equalsNormalized(String filename1, String filename2);

    /**
     * Checks whether two filenames are equal after both have been normalized
     * and using the case rules of the system.
     * <p>
     * Both filenames are first passed to {@link #normalize(String)}. The check
     * is then performed case-sensitive on Unix and case-insensitive on Windows.
     * 
     * @param filename1
     *            the first filename to query, may be null
     * @param filename2
     *            the second filename to query, may be null
     * @return true if the filenames are equal, null equals null
     */
    public abstract boolean equalsNormalizedOnSystem(String filename1,
            String filename2);
}
