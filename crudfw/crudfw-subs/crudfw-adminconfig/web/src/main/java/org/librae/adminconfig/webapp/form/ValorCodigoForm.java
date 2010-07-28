package org.librae.adminconfig.webapp.form;

import java.io.Serializable;

import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.ValorCodigo;
import org.librae.common.webapp.form.IBaseForm;

/**
 * Formulario para la inserccion de un valor de codigo.
 * 
 * @author jcisneros
 */
public class ValorCodigoForm implements Serializable, IBaseForm<ValorCodigo> {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Codigo del ValorCodigo.
     */
    private Codigo            codigo;

    /**
     * Descripcion del valor.
     */
    private String            descripcion;

    /**
     * Descripcion alternativa del valor.
     */
    private String            descripcionAlternativa;

    /**
     * Valor del codigo.
     */
    private String            valor;

    /**
     * Valor del codigo.
     */
    private Long              idValor;

    /**
     * Modo de Edición o Lectura de una biblioteca
     */
    private Boolean           readMode         = false;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public ValorCodigo toEntity() {
        final ValorCodigo valorCodigo = new ValorCodigo(valor);
        valorCodigo.setDescripcion(getDescripcion());
        valorCodigo.setDescripcionAlternativa(getDescripcionAlternativa());
        return valorCodigo;
    }

    public ValorCodigo toEntity(ValorCodigo valorCodigo) {
        if (valorCodigo == null) {
            valorCodigo = new ValorCodigo(valor);
        } else {
            valorCodigo.setValor(getValor());
        }
        valorCodigo.setDescripcion(getDescripcion());
        valorCodigo.setDescripcionAlternativa(getDescripcionAlternativa());
        return valorCodigo;
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(final ValorCodigo valorCodigo) {
        setDescripcion(valorCodigo.getDescripcion());
        setDescripcionAlternativa(valorCodigo.getDescripcionAlternativa());
        setValor(valorCodigo.getValor());
        setIdValor(valorCodigo.getId());
    }

    // Getters && Setter

    public Codigo getCodigo() {
        return codigo;
    }

    public void setCodigo(final Codigo codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(final String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionAlternativa() {
        return descripcionAlternativa;
    }

    public void setDescripcionAlternativa(final String descripcionAlternativa) {
        this.descripcionAlternativa = descripcionAlternativa;
    }

    public Boolean getReadMode() {
        return readMode;
    }

    public void setReadMode(final Boolean readMode) {
        this.readMode = readMode;
    }

    public Long getIdValor() {
        return idValor;
    }

    public void setIdValor(final Long idValor) {
        this.idValor = idValor;
    }

}
