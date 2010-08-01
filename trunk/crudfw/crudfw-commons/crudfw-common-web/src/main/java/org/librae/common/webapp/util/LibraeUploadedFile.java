package org.librae.common.webapp.util;

import java.io.IOException;
import java.io.Serializable;

import org.apache.myfaces.custom.fileupload.UploadedFile;

public class LibraeUploadedFile implements Serializable {

    /**
     * 
     */
    private static final long  serialVersionUID        = 1L;

    public static final String IMAGE_FILE_SESSION_NAME = "IMAGE_FILE_UPLOADED";

    private UploadedFile       uploadedFile            = null;
    private String             contentType             = null;
    private byte[]             byteArray               = null;
    private String             filename                = null;
    private long               size                    = 0;

    public LibraeUploadedFile() {
        super();
    }

    public LibraeUploadedFile(final UploadedFile uploadedFile) {
        setUploadedFile(uploadedFile);
    }

    public LibraeUploadedFile(final String filename, final byte[] byteArray,
            final String contentType) {
        setContentType(contentType);
        setByteArray(byteArray);
        setFilename(filename);
        setSize(byteArray.length);
    }

    /**
     * @return the uploadedFile
     */
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    /**
     * @param uploadedFile
     *            the uploadedFile to set
     */
    public final void setUploadedFile(final UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;

        if (uploadedFile != null) {

            setContentType(uploadedFile.getContentType());
            try {
                setByteArray(uploadedFile.getBytes());
            } catch (final IOException e) {
                e.printStackTrace();
            }
            setFilename(uploadedFile.getName());
            setSize(uploadedFile.getSize());
        }
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     *            the contentType to set
     */
    public final void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the byteArray
     */
    public byte[] getByteArray() {
        return byteArray;
    }

    /**
     * @param byteArray
     *            the byteArray to set
     */
    public final void setByteArray(final byte[] byteArray) {
        this.byteArray = byteArray;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename
     *            the filename to set
     */
    public final void setFilename(final String filename) {
        this.filename = filename;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public final void setSize(final long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append((filename == null) ? "[empty file]" : filename);
        sb.append("::");
        sb.append("byteArray=").append(
                (getByteArray() == null) ? "0" : getByteArray().length).append(
                " bytes.");
        sb.append("::");
        sb.append("uploadedImage=").append(getUploadedFile());
        return sb.toString();
    }

}
