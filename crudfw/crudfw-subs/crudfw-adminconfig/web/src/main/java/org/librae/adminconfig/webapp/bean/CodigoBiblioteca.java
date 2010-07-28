package org.librae.adminconfig.webapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.common.Constants;
import org.librae.common.util.StringUtil;
import org.librae.common.webapp.action.ConvertUtil;

/**
 * Bean para el dataTable de la pagina de codigos, si es de tipo biblioteca es
 * util.
 * 
 * @author jcisneros
 */
public class CodigoBiblioteca implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID            = 1L;

    private Codigo            codigo                      = null;

    private Boolean           tipoBiblioteca              = false;

    private List<SelectItem>  tiposBiblioteca             = null;

    private List<String>      tiposBibliotecaSeleccionada = new ArrayList<String>();

    /**
     * Constructor.
     * 
     * @param codigo
     * @param tipos
     */
    public CodigoBiblioteca(final Codigo codigo,
            final List<TipoBiblioteca> tipos) {
        this.codigo = codigo;

        if (codigo.getTipoCodigo().getCodigo().equals(
                Constants.TIPO_CODIGO_BIBLIOTECA)) {

            tiposBiblioteca = ConvertUtil
                    .convertListItem(tipos, "id", "nombre");

            tipoBiblioteca = true;

            if (!StringUtil.esVacia(codigo.getAplicaGBS())) {
                for (final TipoBiblioteca tiposBiblioteca : tipos) {
                    if (codigo.getAplicaGBS().contains(
                            tiposBiblioteca.getCodigo())) {
                        tiposBibliotecaSeleccionada.add(tiposBiblioteca.getId()
                                .toString());
                    }
                }
            }
        }
    }

    // Getters && Setters

    public Codigo getCodigo() {
        return codigo;
    }

    public void setCodigo(final Codigo codigo) {
        this.codigo = codigo;
    }

    public List<SelectItem> getTiposBiblioteca() {
        return tiposBiblioteca;
    }

    public void setTiposBiblioteca(final List<SelectItem> tiposBiblioteca) {
        this.tiposBiblioteca = tiposBiblioteca;
    }

    public List<String> getTiposBibliotecaSeleccionada() {
        return tiposBibliotecaSeleccionada;
    }

    public void setTiposBibliotecaSeleccionada(
            final List<String> tiposBibliotecaSeleccionada) {
        this.tiposBibliotecaSeleccionada = tiposBibliotecaSeleccionada;
    }

    public Boolean getTipoBiblioteca() {
        return tipoBiblioteca;
    }

    public void setTipoBiblioteca(final Boolean tipoBiblioteca) {
        this.tipoBiblioteca = tipoBiblioteca;
    }

}
