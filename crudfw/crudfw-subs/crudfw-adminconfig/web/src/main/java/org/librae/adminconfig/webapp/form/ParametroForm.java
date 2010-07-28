package org.librae.adminconfig.webapp.form;

import java.io.IOException;
import java.io.InputStream;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.librae.adminconfig.model.Parametro;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.form.IBaseForm;
import org.librae.common.webapp.util.LibraeUploadedFile;
import org.springframework.util.FileCopyUtils;

/**
 * Formulario para la edicion de un parametro.
 * 
 * @author jcisneros
 */
public class ParametroForm implements IBaseForm<Parametro> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8522996923345203875L;

	/**
     * Id del parametro de la aplicación Librea.
     */
    private Long               idParametro;

    /**
     * Codigo del parametro.
     */
    private String             codigo;

    /**
     * Valor del parametro.
     */
    private String             valor;

    /**
     * Descripcion del parametro.
     */
    private String             descripcion;

    private LibraeUploadedFile fichero;

    private String             fileName;

    private Boolean            readMode = false;

    /** modo de Edición o Creación de una política de reserva */
    private Boolean            creacion = true;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(final Parametro parametro) {
        setCodigo(parametro.getCodigo());
        setValor(parametro.getValor());
        setDescripcion(parametro.getDescripcion());
        if (parametro.getValorArchivo() != null) {
            setFileName(parametro.getNombreArchivo());
            setFichero(new LibraeUploadedFile(parametro.getNombreArchivo(),
                    parametro.getValorArchivo(), parametro
                            .getValorArchivoContentType()));
        }
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public Parametro toEntity() {
        final Parametro parametro = new Parametro(getCodigo(), null);
        toEntity(parametro);
        return parametro;
    }

    public Parametro toEntity(final Parametro parametro) {
        if ((parametro != null) && (parametro.getId() != null)
                && (parametro.getEliminable())
                && (!parametro.getCodigo().equals(getCodigo()))) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_PARAMETRO_CODIGO_NO_MODIFICABLE");
        } else {
            parametro.setCodigo(getCodigo());
        }
        parametro.setValor(getValor());
        parametro.setDescripcion(getDescripcion());

        // fichero
        if (fichero != null && fichero.getUploadedFile() != null) {
            try {
                final InputStream inputS = fichero.getUploadedFile()
                        .getInputStream();
                if (inputS != null) {
                    parametro.setValorArchivo(FileCopyUtils
                            .copyToByteArray(inputS));
                }
            } catch (final IOException e) {
                // se ignora
            }
            fileName = fichero.getFilename();
            fileName = fileName.substring(fileName.lastIndexOf('/') + 1)
                    .substring(fileName.lastIndexOf('\\') + 1);
            parametro.setNombreArchivo(fileName);
            parametro.setValorArchivoContentType(fichero.getContentType());
        }

        return parametro;
    }

    public UploadedFile getUploadedFile() {
        UploadedFile result = null;
        if (fichero != null) {
            result = fichero.getUploadedFile();
        }
        return result;
    }

    public void setUploadedFile(final UploadedFile fichero) {
        this.fichero = new LibraeUploadedFile(fichero);
    }

    // Getters && Setter

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    public Boolean getReadMode() {
        return readMode;
    }

    public void setReadMode(final Boolean readMode) {
        this.readMode = readMode;
    }

    public Boolean getCreacion() {
        return creacion;
    }

    public void setCreacion(final Boolean creacion) {
        this.creacion = creacion;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(final Long idParametro) {
        this.idParametro = idParametro;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the fichero
     */
    public LibraeUploadedFile getFichero() {
        return fichero;
    }

    /**
     * @param fichero
     *            the fichero to set
     */
    public void setFichero(LibraeUploadedFile fichero) {
        this.fichero = fichero;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
