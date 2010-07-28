package org.librae.catalogacion.model;

import javax.persistence.CascadeType;
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
 * Bean para almacenar un RegistroEtiquetaControl
 *
 * @author jcdiaz
 */
@Entity(name = RegistroEtiquetaControl.TABLE_ENTITY)
@Table(name = RegistroEtiquetaControl.TABLE_NAME)
public class RegistroEtiquetaControl extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto RegistroEtiquetaControl necesita
     * un Serial Version UID
     */
    private static final long   serialVersionUID        = -4901401112196313736L;

    public static final String  TABLE_ENTITY            = "org.librae.catalogacion.model.RegistroEtiquetaControl";
    public static final String  TABLE_NAME              = "CAT_REGISTRO_ETIQUETA_CONTROL";
    private static final String ID_GENERATOR_NAME       = "generator_cat_registro_etiqueta_control";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CAT_REG_ETIQU_CONTROL";
    public static final String  COLUMN_NAME_ID          = "X_REGISTRO_ETIQUETA_CONTROL";
    public static final String  COLUMN_NAME_CODIGO      = "C_CODIGO";
    public static final String  COLUMN_NAME_VALOR       = "T_VALOR";
    public static final String  COLUMN_NAME_REGISTRO_FK = "REG_X_REGISTRO";
    public static final String  REGISTRO                = "registro";

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
    private Registro            registro;

    protected RegistroEtiquetaControl() {
    }

    public RegistroEtiquetaControl(String codigo, String valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = RegistroEtiquetaControl.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = RegistroEtiquetaControl.ID_SEQUENCE_NAME)
    @Column(name = RegistroEtiquetaControl.COLUMN_NAME_ID)
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
    @Column(name = RegistroEtiquetaControl.COLUMN_NAME_CODIGO, length = 20)
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
    @Column(name = RegistroEtiquetaControl.COLUMN_NAME_VALOR, length = 255)
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
     * @return the registro
     */
    @ManyToOne(targetEntity = Registro.class)
    @JoinColumn(name = RegistroEtiquetaControl.COLUMN_NAME_REGISTRO_FK, nullable = false)
    public Registro getRegistro() {
        return registro;
    }

    /**
     * @param registro
     *            the registro to set
     */
    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegistroEtiquetaControl)) {
            return false;
        }

        final RegistroEtiquetaControl other = (RegistroEtiquetaControl) obj;

        if (this.id != other.getId()) {
            return false;
        }

        if (!this.codigo.equals(other.getCodigo())) {
            return false;
        }

        if (!this.valor.equals(other.getValor())) {
            return false;
        }

        if (this.registro == null && other.getRegistro() != null) {
            return false;
        }
        if (this.registro != null && !this.registro.equals(other.getRegistro())) {
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
                + ((this.registro == null) ? 0 : this.registro.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(
                RegistroEtiquetaControl.COLUMN_NAME_ID, this.id).append(
                RegistroEtiquetaControl.COLUMN_NAME_CODIGO, this.codigo)
                .append(RegistroEtiquetaControl.COLUMN_NAME_VALOR, this.valor)
                .toString();
    }

}