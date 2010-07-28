package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.List;

import org.librae.adminconfig.model.Codigo;
import org.librae.common.webapp.form.IBaseForm;

/**
 * Formulario para la inserccion de un codigo.
 * 
 * @author jcisneros
 */
public class CodigoForm implements Serializable, IBaseForm<Codigo> {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 8521996923345207875L;

    /**
     * Codigo del codigo.
     */
    private Long              idCodigo;

    /**
     * Codigo del codigo.
     */
    private String            codigo;

    /**
     * Nombre del nombre.
     */
    private String            nombre;

    /**
     * Tipo de Biblioteca.
     */
    private List<String>      tiposBiblioteca;

    /**
     * Tipo de Codigo.
     */
    private Long              tipoCodigo;

    /**
     * Modo de Edición o Lectura de una biblioteca
     */
    private Boolean           readMode         = false;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(final Codigo codigo) {
        setIdCodigo(codigo.getId());
        setCodigo(codigo.getCodigo());
        setNombre(codigo.getNombre());
        setTipoCodigo(codigo.getTipoCodigo().getId());
    }

    /**
     * Rellena el codigo a partir del formulario.
     * 
     * @param codigoObject
     * @return
     */
    public Codigo toEntity(Codigo codigoObject) {
        if (codigoObject == null) {
            codigoObject = new Codigo(codigo);
        } else {
            codigoObject.setCodigo(codigo);
        }
        codigoObject.setNombre(nombre);
        return codigoObject;
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public Codigo toEntity() {
        // TODO Auto-generated method stub
        return null;
    }

    // Getters && Setter

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public List<String> getTiposBiblioteca() {
        return tiposBiblioteca;
    }

    public void setTiposBiblioteca(final List<String> tiposBiblioteca) {
        this.tiposBiblioteca = tiposBiblioteca;
    }

    public Long getTipoCodigo() {
        return tipoCodigo;
    }

    public void setTipoCodigo(final Long tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    public Boolean getReadMode() {
        return readMode;
    }

    public Long getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(final Long idCodigo) {
        this.idCodigo = idCodigo;
    }

    public void setReadMode(final Boolean readMode) {
        this.readMode = readMode;
    }

}
