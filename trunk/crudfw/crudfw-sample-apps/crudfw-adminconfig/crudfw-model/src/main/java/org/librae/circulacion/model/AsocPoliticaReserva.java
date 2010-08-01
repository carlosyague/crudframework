package org.librae.circulacion.model;

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
import org.hibernate.annotations.ForeignKey;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.catalogacion.model.EjemplarTipo;
import org.librae.common.model.BaseObject;
import org.librae.lectores.model.TipoLector;

/**
 * Contiene las asociaciones entre tipos de lectores, el tipo del ejemplar y la
 * biblioteca
 *
 * @author impena
 */
@Entity(name = AsocPoliticaReserva.ENTITY_NAME)
@Table(name = AsocPoliticaReserva.TABLE_NAME)
public class AsocPoliticaReserva extends BaseObject {

    /**
     * BaseObject is Serializable, so AsocPoliticaPrestamoDom needs a Serial
     * Version UID
     */
    private static final long   serialVersionUID        = 2077679663483853089L;

    /**
     * Constants<br>
     * =========
     */

    public static final String  ENTITY_NAME             = "org.librae.circulacion.model.AsocPoliticaReserva";
    public static final String  TABLE_NAME              = "CIR_ASOC_POLITICAS_RESERVA";
    private static final String ID_GENERATOR_NAME       = "generator_cir_asoc_politicas_reserva";
    private static final String ID_SEQUENCE_NAME        = "SEQ_CIR_ASOC_POLITICAS_RESERVA";

    /**
     * Column names<br>
     * ============
     */
    public static final String  COLUMN_NAME_ID          = "X_RESERVA_POLITICA_ASOC";
    public static final String  COLUMN_NAME_BIBLIOTECA  = "BIB_X_BIBLIOTECA";
    public static final String  COLUMN_NAME_TIPO_LECTOR = "LEC_X_LECTOR_TIPO";
    public static final String  COLUMN_NAME_POLITICA    = "POL_X_POLITICA_RES";

    /**
     * Property names<br>
     * ==============
     */
    public static final String  PROPTY_NAME_ID          = "id";
    public static final String  PROPTY_NAME_BIBLIOTECA  = "biblioteca";
    public static final String  PROPTY_NAME_TIPO_LECTOR = "tipoLector";
    public static final String  PROPTY_NAME_POLITICA    = "politicaReserva";

    /**
     * Columns<br>
     * =======
     */

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Identificación de la biblioteca (referencia a BIBLIOTECAS.X_BIBLIOTECA)
     */
    private Biblioteca          biblioteca;

    /**
     * Tipo de lector
     */
    private TipoLector          tipoLector;

    /**
     * Política de Reserva
     */
    private PoliticaReserva     politicaReserva;

    /**
     * Constructors<br>
     * ============
     */

    protected AsocPoliticaReserva() {
        super();
    }

    public AsocPoliticaReserva(final Biblioteca biblioteca,
            final TipoLector tipoLector, final EjemplarTipo ejemplarTipo,
            final PoliticaReserva politicaReserva) {
        this.biblioteca = biblioteca;
        this.tipoLector = tipoLector;
        this.politicaReserva = politicaReserva;
    }

    /**
     * Getter & Setters<br>
     * ================
     */

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = AsocPoliticaReserva.ID_GENERATOR_NAME)
    @SequenceGenerator(name = AsocPoliticaReserva.ID_GENERATOR_NAME, sequenceName = AsocPoliticaReserva.ID_SEQUENCE_NAME)
    @Column(name = AsocPoliticaReserva.COLUMN_NAME_ID)
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
     * @return the biblioteca
     */
    @ManyToOne(targetEntity = Biblioteca.class, cascade = {
            CascadeType.PERSIST, CascadeType.ALL })
    @JoinColumn(name = AsocPoliticaReserva.COLUMN_NAME_BIBLIOTECA, nullable = true)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_ASOCRES")
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca
     *            the biblioteca to set
     */
    public void setBiblioteca(final Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    /**
     * @return the lectorTipoLectorTipo
     */
    @ManyToOne(targetEntity = TipoLector.class, cascade = {
            CascadeType.PERSIST, CascadeType.ALL })
    @JoinColumn(name = AsocPoliticaReserva.COLUMN_NAME_TIPO_LECTOR, nullable = true)
    public TipoLector getTipoLector() {
        return tipoLector;
    }

    /**
     * @param tipoLector
     *            the tipoLector to set
     */
    public void setTipoLector(final TipoLector tipoLector) {
        this.tipoLector = tipoLector;
    }

    /**
     * @return the politicaPrestamoDom
     */
    @ManyToOne(targetEntity = PoliticaReserva.class, cascade = {
            CascadeType.PERSIST, CascadeType.ALL })
    @JoinColumn(name = AsocPoliticaReserva.COLUMN_NAME_POLITICA, nullable = true)
    @ForeignKey(name = "FK_ASOC_POL_RES_X_POL_RES")
    public PoliticaReserva getPoliticaReserva() {
        return politicaReserva;
    }

    /**
     * @param politicaPrestamoDom
     *            the politicaPrestamoDom to set
     */
    public void setPoliticaReserva(PoliticaReserva politicaReserva) {
        this.politicaReserva = politicaReserva;
    }

    /**
     * overrided methods<br>
     * =================
     */

    @Override
    public boolean equals(final Object asociacion) {
        // if they are the same instance
        if (this == asociacion) {
            return true;
        }

        // if they are classify in different classes
        if (!(asociacion instanceof AsocPoliticaReserva)) {
            return false;
        }

        final AsocPoliticaReserva castingAsociacion = (AsocPoliticaReserva) asociacion;

        if ((getId() != null && (!getId().equals(castingAsociacion.getId())))
                || ((getId() == null) && castingAsociacion.getId() != null)) {
            return false;
        }
        if ((getBiblioteca() != null && (!getBiblioteca().equals(
                castingAsociacion.getBiblioteca())))
                || ((getBiblioteca() == null) && castingAsociacion
                        .getBiblioteca() != null)) {
            return false;
        }
        if ((getPoliticaReserva() != null && (!getPoliticaReserva().equals(
                castingAsociacion.getPoliticaReserva())))
                || ((getPoliticaReserva() == null) && castingAsociacion
                        .getPoliticaReserva() != null)) {
            return false;
        }
        if ((getTipoLector() != null && (!getTipoLector().equals(
                castingAsociacion.getTipoLector())))
                || ((getTipoLector() == null) && castingAsociacion
                        .getTipoLector() != null)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (getId() == null ? 0 : getId().hashCode());
        result = 37 * result
                + (getBiblioteca() == null ? 0 : getBiblioteca().hashCode());
        result = 37
                * result
                + (getPoliticaReserva() == null ? 0 : getPoliticaReserva()
                        .hashCode());
        result = 37 * result
                + (getTipoLector() == null ? 0 : getTipoLector().hashCode());
        return result;
    }

    @Override
    public String toString() {

        final ToStringBuilder tsb = new ToStringBuilder(this);

        tsb.append(AsocPoliticaReserva.PROPTY_NAME_ID, getId());
        tsb.append(AsocPoliticaReserva.PROPTY_NAME_POLITICA,
                getPoliticaReserva());
        tsb
                .append(AsocPoliticaReserva.PROPTY_NAME_TIPO_LECTOR,
                        getTipoLector());
        tsb.append(AsocPoliticaReserva.COLUMN_NAME_BIBLIOTECA, getBiblioteca());

        return tsb.toString();
    }

}
