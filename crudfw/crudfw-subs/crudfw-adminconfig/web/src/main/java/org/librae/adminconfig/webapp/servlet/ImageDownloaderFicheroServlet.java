package org.librae.adminconfig.webapp.servlet;

import org.librae.adminconfig.model.Parametro;
import org.librae.common.webapp.servlet.AbstractFileDownloaderServlet;
import org.librae.common.webapp.util.LibraeUploadedFile;

/**
 * Servlet de descarga de archivos almacenados en BBDD<br>
 * <u>Entidad</u>: <code>Parametro</code><br>
 * <u>Contenido</u>: Ficheros para parametros<br>
 * 
 * @author jcisneros
 */
public class ImageDownloaderFicheroServlet extends
        AbstractFileDownloaderServlet<Parametro, Long> {

    @Override
    protected Long getPK(final String id) {
        return Long.parseLong(id);
    }

    @Override
    public LibraeUploadedFile createUploadedFile(final Parametro entity) {
        final LibraeUploadedFile file = new LibraeUploadedFile(entity
                .getNombreArchivo(), entity.getValorArchivo(), entity
                .getValorArchivoContentType());
        return file;
    }

    @Override
    protected String getDownloadMode() {
        return AbstractFileDownloaderServlet.SERVLET_MODE_ATTACHMENT;
    }
}
