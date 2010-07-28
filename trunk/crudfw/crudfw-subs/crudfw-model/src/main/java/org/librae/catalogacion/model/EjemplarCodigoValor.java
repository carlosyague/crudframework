package org.librae.catalogacion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar una EjemplarCodigoValor
 * 
 * @author jcdiaz
 */
@Entity(name = EjemplarCodigoValor.NAME_ENTITY)
@Table(name = EjemplarCodigoValor.TABLE_NAME)
public class EjemplarCodigoValor extends
        SpringRemotableLazyEntity<EjemplarCodigoValor> {

    /**
     * BaseObject es Serializable, por lo tanto EjemplarCodigoValor necesita un
     * Serial Version UID
     */

    private static final long   serialVersionUID                      = 3817019990810100066L;

    /**
     * Constantes para referencia de los atributos de LectorCodigoVal
     */
    public static final String  CODIGO                                = "codigo";
    public static final String  DESCRIPCIONCODIGOVAL                  = "descripcion";
    public static final String  IDD                                   = "id";

    public static final String  NAME_ENTITY                           = "org.librae.catalogacion.model.EjemplarCodigoValor";
    public static final String  TABLE_NAME                            = "CAT_EJEMPLAR_CODIGO_VALOR";
    private static final String ID_GENERATOR_NAME                     = "generator_cat_ejemplar_codigo_valor";
    private static final String ID_SEQUENCE_NAME                      = "SEQ_ADM_EJEMPLAR_CODIGO_VALOR";
    public static final String  COLUMN_NAME_ID                        = "X_EJEMPLAR_CODIGO_VALOR";
    public static final String  COLUMN_NAME_CODIGO                    = "C_CODIGO";
    public static final String  COLUMN_NAME_DESCRIPCION               = "T_DESCRIPCION";
    public static final String  COLUMN_NAME_VALOR                     = "C_VALOR";
    public static final String  COLUMN_NAME_EJEMPLAR_CODIGO_FK        = "EJE_X_EJEMPLAR_CODIGO";
    public static final String  COLUMN_NAME_EJEMPLAR_CODIGO_VALOLR_FK = "EJE_X_EJEMPLAR_CODIGO_VALOR";
    public static final String  EJEMPLARCODIGO                        = "ejemplarCodigo";

    /**
     * Clave primaria artificial. Identificador único de fila asignado por la
     * bd. Número secuencial
     */
    private Long                id;

    /**
     * Código único asignado por el usuario
     */
    private String              codigo;

    /**
     * Descripción (nombre) asignado por el usuario al código
     */
    private String              descripcion;

    /**
     * Descripción (nombre) asignado por el usuario al código
     */
    private String              valor;

    /**
     *
     */
    private EjemplarCodigo      ejemplarCodigo;

    /**
     *
     */
    private List<Ejemplar>      ejemplares;

    protected EjemplarCodigoValor() {
        ejemplares = new ArrayList<Ejemplar>();
    }

    public EjemplarCodigoValor(String codigo, String descripcion, String valor) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.valor = valor;
        ejemplares = new ArrayList<Ejemplar>();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = EjemplarCodigoValor.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = EjemplarCodigoValor.ID_SEQUENCE_NAME)
    @Column(name = EjemplarCodigoValor.COLUMN_NAME_ID)
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
    @Column(name = EjemplarCodigoValor.COLUMN_NAME_CODIGO)
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
     * @return the descripcion
     */
    @Column(name = EjemplarCodigoValor.COLUMN_NAME_DESCRIPCION)
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

    /**
     * @return the valor
     */
    @Column(name = EjemplarCodigoValor.COLUMN_NAME_VALOR)
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
     * @return the ejemplarCodigo
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = EjemplarCodigo.class)
    @JoinColumn(name = EjemplarCodigoValor.COLUMN_NAME_EJEMPLAR_CODIGO_FK)
    public EjemplarCodigo getEjemplarCodigo() {
        return ejemplarCodigo;
    }

    /**
     * @param ejemplarCodigo
     *            the ejemplarCodigo to set
     */
    public void setEjemplarCodigo(EjemplarCodigo ejemplarCodigo) {
        this.ejemplarCodigo = ejemplarCodigo;
    }

    /**
     * @return the ejemplares
     */
    @ManyToMany(mappedBy = "ejemplarCodigoValores", targetEntity = Ejemplar.class)
    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    /**
     * @param ejemplares
     *            the ejemplares to set
     */
    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof EjemplarCodigoValor)) {
            return false;
        }

        final EjemplarCodigoValor other = (EjemplarCodigoValor) obj;

        if (id != other.getId()) {
            return false;
        }

        if (!codigo.equals(other.getCodigo())) {
            return false;
        }

        if (!descripcion.equals(other.getDescripcion())) {
            return false;
        }

        if (!valor.equals(other.getValor())) {
            return false;
        }

        if (ejemplarCodigo == null && other.getEjemplarCodigo() != null) {
            return false;
        }
        if (ejemplarCodigo != null
                && !ejemplarCodigo.equals(other.getEjemplarCodigo())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        result = prime * result
                + ((ejemplarCodigo == null) ? 0 : ejemplarCodigo.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(
                EjemplarCodigoValor.COLUMN_NAME_ID, id).append(
                EjemplarCodigoValor.COLUMN_NAME_CODIGO, codigo).append(
                EjemplarCodigoValor.COLUMN_NAME_DESCRIPCION, descripcion)
                .append(EjemplarCodigoValor.COLUMN_NAME_VALOR, valor)
                .toString();
    }

    @Override
    public EjemplarCodigoValor newInstance() {
        return new EjemplarCodigoValor();
    }

}
