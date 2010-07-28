package org.librae.adminconfig.webapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Codigo;
import org.librae.common.webapp.action.ConvertUtil;

/**
 * Bean para el dataTable de la pagina de Bibibliotecas.
 * 
 * @author jcisneros
 */
public class CodigoValor implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 1L;

    private Codigo            codigo           = null;

    private List<SelectItem>  valores          = null;

    public CodigoValor(final Codigo codigo) {
        valores = ConvertUtil.convertListItem(codigo.getValores(), "id",
                "valor");
        this.codigo = codigo;
    }

    // Getters && Setters

    public Codigo getCodigo() {
        return codigo;
    }

    public void setCodigo(final Codigo codigo) {
        this.codigo = codigo;
    }

    public List<SelectItem> getValores() {
        return valores;
    }

    public void setValores(final List<SelectItem> valores) {
        this.valores = valores;
    }

}
