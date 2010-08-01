package org.librae.lectores.model;

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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.exception.MensajesError;
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean que almacena un LectorEstado
 *
 * @author jcdiaz
 */
@Entity(name = LectorEstado.ENTITY_NAME)
@Table(name = LectorEstado.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class LectorEstado extends SpringRemotableLazyEntity<LectorEstado> {

    /**
     * BaseObject is Serializable, por lo tanto LectorEstado necesita un Serial
     * Version UID
     */
    private static final long  serialVersionUID     = -838800580219367420L;

    public static final String ENTITY_NAME          = "org.librae.lectores.model.LectorEstado";

    /**
     * Constantes para referencia de los atributos de LectorEstado
     */
    public static final String PROPTY_NAME_ID       = "id";
    public static final String PROPTY_NAME_CODIGO   = "codigo";
    public static final String PROPTY_NAME_ESTADO   = "estado";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String TABLE_NAME           = "LEC_LECTOR_ESTADOS";
    public static final String ID_GENERATOR_NAME    = "generator_lec_lector_estados";
    public static final String ID_SEQUENCE_NAME     = "SEQ_LEC_LECTOR_ESTADOS";

    public static final String COLUMN_NAME_ID       = "X_LECTOR_ESTADO";
    public static final String COLUMN_NAME_CODIGO   = "C_CODIGO";
    public static final String COLUMN_NAME_ESTADO   = "T_ESTADO";

    /**
     * Constantes para los posibles valores del estado de un lector
     */
    public static final String ESTADO_ACTIVO        = "A";
    public static final String ESTADO_BLOQUEADO     = "B";
    public static final String ESTADO_SUSPENDIDO    = "S";
    public static final String ESTADO_CARNET_ONLIVE = "O";
    public static final String ESTADO_CADUCADO      = "C";
    public static final String ESTADO_BAJA_TEMPORAL = "I";

    /**
     * Clave autonumérica secuencial sin significado funcional
     */
    private Long               id;

    /**
     * Código de estado del lector: Activo ->A, Bloqueado -> B, Suspendido -> S,
     * Carnet Online -> O
     */
    private String             codigo;

    /**
     * Texto descriptivo del estado del lector: Activo, Bloqueado, Suspendido,
     * Pendiente Validar Carnet Online
     */
    private String             estado;

    /**
     *
     */
    private List<Lector>       lectores;

    /**
     * Constructor sin parámetros
     */
    protected LectorEstado() {
        super();
        lectores = new ArrayList<Lector>();
    }

    /**
     * Constructor con parámetros
     */
    public LectorEstado(final String codigo, final String estado) {
        super();
        this.codigo = codigo;
        this.estado = estado;
    }

    /**
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = LectorEstado.ID_GENERATOR_NAME)
    @SequenceGenerator(name = LectorEstado.ID_GENERATOR_NAME, sequenceName = LectorEstado.ID_SEQUENCE_NAME)
    @Column(name = LectorEstado.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    @Column(name = LectorEstado.COLUMN_NAME_CODIGO, nullable = false, unique = true, length = 10)
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
     * @return the estado
     */
    @Column(name = LectorEstado.COLUMN_NAME_ESTADO, nullable = false, unique = true, length = 50)
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Transient
    public String getLocaleEstado() {
        String result = null;

        if (estado != null) {
            result = estado;

            if (result != null && result.contains("#")) {
                final String propertyKey = result.replace("#", "");

                result = MensajesError.get(MensajesError.PROPERTI_COMBOS,
                        propertyKey);
            }
        }

        return result;
    }

    /**
     * @return lectores
     */
    @OneToMany(mappedBy = Lector.PROPTY_NAME_ESTADO)
    public List<Lector> getLectores() {
        return lectores;
    }

    /**
     * @param lectores
     */
    public void setLectores(final List<Lector> lectores) {
        this.lectores = lectores;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LectorEstado)) {
            return false;
        }
        final LectorEstado other = (LectorEstado) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (estado == null) {
            if (other.estado != null) {
                return false;
            }
        } else if (!estado.equals(other.estado)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this)

        .append(LectorEstado.COLUMN_NAME_ID, id)

        .append(LectorEstado.COLUMN_NAME_CODIGO, (codigo == null) ? 0 : codigo)

        .append(LectorEstado.COLUMN_NAME_ESTADO, (estado == null) ? 0 : estado)

        .toString();
    }

    @Override
    public LectorEstado newInstance() {
        return new LectorEstado();
    }

}
