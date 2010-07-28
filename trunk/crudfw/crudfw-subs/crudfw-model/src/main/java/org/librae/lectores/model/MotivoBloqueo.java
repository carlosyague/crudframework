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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean que almacena un MotivoBloqueo
 *
 * @author jcdiaz
 */
@Entity(name = MotivoBloqueo.ENTITY_NAME)
@Table(name = MotivoBloqueo.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class MotivoBloqueo extends SpringRemotableLazyEntity<MotivoBloqueo> {

    /**
     * BaseObject is Serializable, por lo tanto desiderata necesita un Serial
     * Version UID
     */
    private static final long  serialVersionUID   = 8333355951358522686L;

    public static final String ENTITY_NAME        = "org.librae.lectores.model.MotivoBloqueo";

    /**
     * Constantes para referencia de los atributos de MotivoBloqueo
     */
    public static final String PROPTY_NAME_ID     = "id";
    public static final String PROPTY_NAME_MOTIVO = "motivo";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String TABLE_NAME         = "LEC_MOTIVO_BLOQUEOS";
    public static final String ID_GENERATOR_NAME  = "generator_lec_motivo_bloqueos";
    public static final String ID_SEQUENCE_NAME   = "SEQ_LEC_MOTIVO_BLOQUEO";

    public static final String COLUMN_NAME_ID     = "X_MOTIVO_BLOQUEO";
    public static final String COLUMN_NAME_MOTIVO = "T_MOTIVO";

    /**
     * clave primaria
     */
    private Long               id;

    /**
     * descripcion del motivo
     */
    private String             motivo;

    /**
     *
     */
    private List<Lector>       lectores;

    /**
     * Constructor sin parámetros
     */
    protected MotivoBloqueo() {
        super();
        lectores = new ArrayList<Lector>();
    }

    /**
     * Constructor sin parámetros
     */
    public MotivoBloqueo(final String motivo) {
        super();
        this.motivo = motivo;
        lectores = new ArrayList<Lector>();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = MotivoBloqueo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = MotivoBloqueo.ID_GENERATOR_NAME, sequenceName = MotivoBloqueo.ID_SEQUENCE_NAME)
    @Column(name = MotivoBloqueo.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the motivo
     */
    @Column(name = MotivoBloqueo.COLUMN_NAME_MOTIVO, nullable = false, unique = true, length = 255)
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo
     *            the motivo to set
     */
    public void setMotivo(final String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the lectores
     */
    @OneToMany(mappedBy = Lector.PROPTY_NAME_MOTIVO_BLOQUEO)
    public List<Lector> getLectores() {
        return lectores;
    }

    /**
     * @param lectores
     *            the lectores to set
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
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
        if (!(obj instanceof MotivoBloqueo)) {
            return false;
        }
        final MotivoBloqueo other = (MotivoBloqueo) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (motivo == null) {
            if (other.motivo != null) {
                return false;
            }
        } else if (!motivo.equals(other.motivo)) {
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

        .append(MotivoBloqueo.COLUMN_NAME_ID, id)

                .append(MotivoBloqueo.PROPTY_NAME_MOTIVO,
                        (motivo == null) ? 0 : motivo)

                .toString();
    }

    @Override
    public MotivoBloqueo newInstance() {
        return new MotivoBloqueo();
    }

}
