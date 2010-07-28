package org.librae.catalogacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un AutoridadEtiquetaControl
 *
 * @author jcdiaz
 */
@Entity(name = AutoridadEtiquetaControl.TABLE_ENTITY)
@Table(name = AutoridadEtiquetaControl.TABLE_NAME)
public class AutoridadEtiquetaControl extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto AutoridadEtiquetaControl
     * necesita un Serial Version UID
     */
    private static final long   serialVersionUID         = -4901401112196313736L;

    public static final String  TABLE_ENTITY             = "org.librae.catalogacion.model.AutoridadEtiquetaControl";
    public static final String  TABLE_NAME               = "CAT_AUTORIDAD_ETIQUETA_CONTROL";
    private static final String ID_GENERATOR_NAME        = "generator_cat_autoridad_etiqueta_control";
    private static final String ID_SEQUENCE_NAME         = "SEQ_CAT_AUT_ETIQU_CONTROL";
    public static final String  COLUMN_NAME_ID           = "X_AUTORIDAD_ETIQUETA_CONTROL";
    public static final String  COLUMN_NAME_CODIGO       = "C_CODIGO";
    public static final String  COLUMN_NAME_VALOR        = "T_VALOR";
    public static final String  COLUMN_NAME_AUTORIDAD_FK = "AUT_X_AUTORIDAD";
    public static final String  AUTORIDAD                = "autoridad";

    /**
     *
     */
    private Long                id;

    /**
     *
     */
    private String              codigo;

    /**
     *
     */
    private String              valor;

    /**
     *
     */
    private Autoridad           autoridad;

    protected AutoridadEtiquetaControl() {
    }

    public AutoridadEtiquetaControl(String codigo, String valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = AutoridadEtiquetaControl.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = AutoridadEtiquetaControl.ID_SEQUENCE_NAME)
    @Column(name = AutoridadEtiquetaControl.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    @Column(name = AutoridadEtiquetaControl.COLUMN_NAME_CODIGO, length = 20)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the valor
     */
    @Column(name = AutoridadEtiquetaControl.COLUMN_NAME_VALOR, length = 255)
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
     * @return the Autoridad
     */
    @ManyToOne(targetEntity = Autoridad.class)
    @JoinColumn(name = AutoridadEtiquetaControl.COLUMN_NAME_AUTORIDAD_FK, nullable = false)
    public Autoridad getAutoridad() {
        return autoridad;
    }

    /**
     * @param Autoridad
     *            the Autoridad to set
     */
    public void setAutoridad(Autoridad autoridad) {
        this.autoridad = autoridad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AutoridadEtiquetaControl)) {
            return false;
        }

        final AutoridadEtiquetaControl other = (AutoridadEtiquetaControl) obj;

        if (this.id != other.getId()) {
            return false;
        }

        if (!this.codigo.equals(other.getCodigo())) {
            return false;
        }

        if (!this.valor.equals(other.getValor())) {
            return false;
        }

        if (this.autoridad == null && other.getAutoridad() != null) {
            return false;
        }
        if (this.autoridad != null
                && !this.autoridad.equals(other.getAutoridad())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result
                + ((this.codigo == null) ? 0 : this.codigo.hashCode());
        result = prime * result
                + ((this.valor == null) ? 0 : this.valor.hashCode());
        result = prime * result
                + ((this.autoridad == null) ? 0 : this.autoridad.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(
                AutoridadEtiquetaControl.COLUMN_NAME_ID, this.id).append(
                AutoridadEtiquetaControl.COLUMN_NAME_CODIGO, this.codigo)
                .append(AutoridadEtiquetaControl.COLUMN_NAME_VALOR, this.valor)
                .toString();
    }

}