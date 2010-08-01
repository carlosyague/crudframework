package org.librae.catalogacion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar una EjemplarCodigo
 * 
 * @author jcdiaz
 */
@Entity(name = EjemplarCodigo.NAME_ENTITY)
@Table(name = EjemplarCodigo.TABLE_NAME)
public class EjemplarCodigo extends
        SpringRemotableLazyEntity<EjemplarCodigo> {

    /**
     * BaseObject es Serializable, por lo tanto EjemplarCodigo necesita un
     * Serial Version UID
     */
    private static final long         serialVersionUID        = 9148591950729564770L;

    /**
     * Constantes para referencia de los atributos de Lector
     */
    public static final String        IDD                     = "id";
    public static final String        CODIGO                  = "codigo";
    public static final String        DESCRIPCIONCODIGO       = "descripcionCodigo";

    public static final String        NAME_ENTITY             = "org.librae.catalogacion.model.EjemplarCodigo";
    public static final String        TABLE_NAME              = "CAT_EJEMPLAR_CODIGO";
    private static final String       ID_GENERATOR_NAME       = "generator_cat_ejemplar_codigo";
    private static final String       ID_SEQUENCE_NAME        = "SEQ_CAT_EJEMPLAR_CODIGO";
    public static final String        COLUMN_NAME_ID          = "X_EJEMPLAR_CODIDO";
    public static final String        COLUMN_NAME_CODIGO      = "C_CODIGO";
    public static final String        COLUMN_NAME_DESCRIPCION = "T_DESCRIPCION";

    /**
     * Clave primaria artificial. Identificador único de fila asignado por la
     * bd. Número secuencial
     */
    private Long                      id;

    /**
     * Código único asignado por el usuario
     */
    private String                    codigo;

    /**
     * Descripción (nombre) asignado por el usuario al código
     */
    private String                    descripcion;

    /**
     *
     */
    private List<EjemplarCodigoValor> ejemplarCodigoValores;

    protected EjemplarCodigo() {
        ejemplarCodigoValores = new ArrayList<EjemplarCodigoValor>();
    }

    public EjemplarCodigo(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        ejemplarCodigoValores = new ArrayList<EjemplarCodigoValor>();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = EjemplarCodigo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = EjemplarCodigo.ID_SEQUENCE_NAME)
    @Column(name = EjemplarCodigo.COLUMN_NAME_ID)
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
    @Column(name = EjemplarCodigo.COLUMN_NAME_CODIGO)
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
    @Column(name = EjemplarCodigo.COLUMN_NAME_DESCRIPCION)
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
     * @return the ejemplarCodigoValores
     */
    @OneToMany(mappedBy = EjemplarCodigoValor.EJEMPLARCODIGO)
    public List<EjemplarCodigoValor> getEjemplarCodigoValores() {
        return ejemplarCodigoValores;
    }

    /**
     * @param ejemplarCodigoValores
     *            the ejemplarCodigoValores to set
     */
    public void setEjemplarCodigoValores(
            List<EjemplarCodigoValor> ejemplarCodigoValores) {
        this.ejemplarCodigoValores = ejemplarCodigoValores;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof EjemplarCodigo)) {
            return false;
        }

        final EjemplarCodigo other = (EjemplarCodigo) obj;

        if (id != other.getId()) {
            return false;
        }

        if (!codigo.equals(other.getCodigo())) {
            return false;
        }

        if (!descripcion.equals(other.getDescripcion())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime
                * result
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(EjemplarCodigo.COLUMN_NAME_ID,
                id).append(EjemplarCodigo.COLUMN_NAME_CODIGO, codigo)
                .append(EjemplarCodigo.COLUMN_NAME_DESCRIPCION,
                        descripcion).toString();
    }

    @Override
    public EjemplarCodigo newInstance() {
        return new EjemplarCodigo();
    }

}
