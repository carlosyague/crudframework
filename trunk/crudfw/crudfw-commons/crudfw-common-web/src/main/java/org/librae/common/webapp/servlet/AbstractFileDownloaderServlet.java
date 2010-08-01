package org.librae.common.webapp.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.service.GenericManager;
import org.librae.common.webapp.util.LibraeUploadedFile;
import org.springframework.web.HttpRequestHandler;

/**
 * Servlet de descarga de archivos almacenados en BBDD<br>
 * <u>Tipos Parametrizados</u>: <br>
 * <code>T</code> Clase del POJO o entidad correspondiente a la tabla de BBDD en
 * la que se almacena el archivo <br>
 * <code>PK</code> Clase corresponiente al identificador o clave primaria de
 * dicho POJO Es preciso extender esta clase y definir los métodos:<br>
 * <code>PK getPK(String id);</code> <br>
 * <code>LibraeUploadedFile createUploadedFile(final T entity);</code> <br>
 *
 * @author cyague
 */
public abstract class AbstractFileDownloaderServlet<T, PK extends Serializable>
        implements HttpRequestHandler {

    protected Log                 log                     = LogFactory
                                                                  .getLog(this
                                                                          .getClass());
    public final static String    REQUEST_PARAM_ID        = "id";

    public final static String    SERVLET_MODE_INLINE     = "inline";
    public final static String    SERVLET_MODE_ATTACHMENT = "attachment";

    private GenericManager<T, PK> manager;

    /**
     * Public methods
     */

    public GenericManager<T, PK> getManager() {
        return this.manager;
    }

    public void setManager(final GenericManager<T, PK> manager) {
        this.manager = manager;
    }

    public void handleRequest(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {

        final String id = request
                .getParameter(AbstractFileDownloaderServlet.REQUEST_PARAM_ID);

        final T entity = this.getEntity(id);

        if (entity != null) {
            final LibraeUploadedFile file = this.createUploadedFile(entity);
            final byte[] image = this.getImage(file);

            if (image != null) {

                final String contentType = this.getContentType(file);
                final String filename = this.getFilename(file);
                final int size = image.length;

                response.reset();
                response.setHeader("Title", filename);
                response.setHeader("Content-Type", contentType);
                response.setHeader("Content-Length", String.valueOf(size));
                response.setHeader("Content-Disposition", getDownloadMode()
                        + "; filename=\"" + filename + "\"");

                this.log.debug("[" + filename + " " + size + " bytes"
                        + "] Downloading..");

                this.writeImage(response.getOutputStream(), image);

                this.log.debug("[" + filename + " " + size + " bytes"
                        + "] Download finished.");
            }
        }
    }

    /**
     * Private methods
     */

    private void writeImage(final OutputStream os, final byte[] image) {
        try {
            os.write(image);
        } catch (final IOException e) {
            this.log.debug("ERROR [IOException]:" + e.getMessage());
            e.printStackTrace();
        }
    }

    private T getEntity(final String id) {
        final PK pkId = this.getPKWrapper(id);

        return this.getEntity(pkId);
    }

    protected T getEntity(final PK id) {
        T result = null;

        if (this.manager == null) {
            this.log.debug("The manager is null::id=" + id);
        } else {
            result = this.manager.get(id);

            if (result == null) {
                this.log.debug("The entity is null::id=" + id);
            }
        }
        return result;
    }

    /**
     * Wrapper methods
     */

    private PK getPKWrapper(final String id) {

        PK result = null;

        if (id != null) {
            result = this.getPK(id);
        }

        return result;
    }

    private byte[] getImage(final LibraeUploadedFile file) {

        byte[] result = null;

        if (file != null) {
            result = file.getByteArray();
        }

        return result;
    }

    private String getContentType(final LibraeUploadedFile file) {
        String result = null;

        if (file != null) {
            result = file.getContentType();
        }

        return result;
    }

    private String getFilename(final LibraeUploadedFile file) {
        String result = null;

        if (file != null) {
            result = file.getFilename();
        }

        return result;
    }

    /**
     * Abstract methods
     */

    /**
     * Converts a string value to PK type
     *
     * @param id
     * @return
     */
    protected abstract PK getPK(String id);

    /**
     * Creates an uploaded file from database entity (POJO)
     *
     * @param entity
     * @return
     */
    protected abstract LibraeUploadedFile createUploadedFile(final T entity);

    /**
     * Establece el tipo de servlet de descarga:<br>
     * Posibles valores:<br>
     * <code>AbstractFileDownloaderServlet.SERVLET_MODE_INLINE</code>, modo
     * apertura de ficheros en línea (valor por defecto) ó
     * <code>AbstractFileDownloaderServlet.SERVLET_MODE_ATTACHMENT</code>, modo
     * apertura/descarga de ficheros
     *
     * @return modo de descarga de ficheros del servidor
     */
    protected abstract String getDownloadMode();

}
