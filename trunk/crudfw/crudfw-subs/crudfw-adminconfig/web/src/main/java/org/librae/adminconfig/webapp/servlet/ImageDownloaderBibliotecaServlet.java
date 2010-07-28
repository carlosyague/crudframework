package org.librae.adminconfig.webapp.servlet;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.webapp.servlet.AbstractFileDownloaderServlet;
import org.librae.common.webapp.util.LibraeUploadedFile;

/**
 * Servlet de descarga de archivos almacenados en BBDD<br>
 * <u>Entidad</u>: <code>Biblioteca</code><br>
 * <u>Contenido</u>: Logotipos de bibliotecas<br>
 * 
 * @author cyague
 */
public class ImageDownloaderBibliotecaServlet extends
        AbstractFileDownloaderServlet<Biblioteca, Long> {

    @Override
    protected Long getPK(final String id) {
        return Long.parseLong(id);
    }

    @Override
    public LibraeUploadedFile createUploadedFile(final Biblioteca entity) {
        final LibraeUploadedFile file = new LibraeUploadedFile(entity
                .getLogotipoNombreFichero(), entity.getLogotipo(), entity
                .getLogotipoContentType());
        return file;
    }
    
    @Override
    protected String getDownloadMode() {
        return AbstractFileDownloaderServlet.SERVLET_MODE_INLINE;
    }
}
