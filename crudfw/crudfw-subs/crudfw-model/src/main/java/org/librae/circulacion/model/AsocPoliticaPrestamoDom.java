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

import org.hibernate.annotations.ForeignKey;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.catalogacion.model.EjemplarTipo;
import org.librae.common.model.BaseObject;
import org.librae.lectores.model.TipoLector;

/**
 * Contiene las asociaciones entre tipos de lectores, el tipo del ejemplar y la
 * biblioteca
 * 
 * @author cyague
 */
@Entity(name = AsocPoliticaPrestamoDom.ENTITY_NAME)
@Table(name = AsocPoliticaPrestamoDom.TABLE_NAME)
public class AsocPoliticaPrestamoDom extends BaseObject {

    /**
     * BaseObject is Serializable, so AsocPoliticaPrestamoDom needs a Serial
     * Version UID
     */
    private static final long   serialVersionUID          = 2077679663483853089L;

    /**
     * Constants<br>
     * =========
     */

    public static final String  ENTITY_NAME               = "org.librae.circulacion.model.AsocPoliticaPrestamoDom";
    public static final String  TABLE_NAME                = "CIR_DOM_POLITICAS_ASOC";
    private static final String ID_GENERATOR_NAME         = "generator_cir_dom_politicas_asoc";
    private static final String ID_SEQUENCE_NAME          = "SEQ_CIR_DOM_POLITICAS_ASOC";

    /**
     * Column names<br>
     * ============
     */
    public static final String  COLUMN_NAME_ID            = "X_DOM_POLITICA_ASOC";
    public static final String  COLUMN_NAME_BIBLIOTECA    = "BIB_X_BIBLIOTECA";
    public static final String  COLUMN_NAME_TIPO_LECTOR   = "LEC_X_LECTOR_TIPO";
    public static final String  COLUMN_NAME_EJEMPLAR_TIPO = "EJE_X_EJEMPLAR_TIPO";
    public static final String  COLUMN_NAME_POLITICA      = "POL_X_POLITICA_DOM";

    /**
     * Property names<br>
     * ==============
     */
    public static final String  PROPTY_NAME_ID            = "id";
    public static final String  PROPTY_NAME_BIBLIOTECA    = "biblioteca";
    public static final String  PROPTY_NAME_TIPO_LECTOR   = "tipoLector";
    public static final String  PROPTY_NAME_EJEMPLAR_TIPO = "ejemplarTipo";
    public static final String  PROPTY_NAME_POLITICA      = "politicaPrestamoDom";

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
     * Tipo de ejemplar
     */
    private EjemplarTipo        ejemplarTipo;

    /**
     * Política de Préstamo a Domicilio
     */
    private PoliticaPrestamoDom politicaPrestamoDom;

    /**
     * Constructors<br>
     * ============
     */

    protected AsocPoliticaPrestamoDom() {
        super();
    }

    public AsocPoliticaPrestamoDom(final Biblioteca biblioteca,
            final TipoLector tipoLector, final EjemplarTipo ejemplarTipo,
            final PoliticaPrestamoDom politicaPrestamoDom) {
        this.biblioteca = biblioteca;
        this.tipoLector = tipoLector;
        this.ejemplarTipo = ejemplarTipo;
        this.politicaPrestamoDom = politicaPrestamoDom;
    }

    /**
     * Getter & Setters<br>
     * ================
     */

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = AsocPoliticaPrestamoDom.ID_GENERATOR_NAME)
    @SequenceGenerator(name = AsocPoliticaPrestamoDom.ID_GENERATOR_NAME, sequenceName = AsocPoliticaPrestamoDom.ID_SEQUENCE_NAME)
    @Column(name = AsocPoliticaPrestamoDom.COLUMN_NAME_ID)
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
    @JoinColumn(name = AsocPoliticaPrestamoDom.COLUMN_NAME_BIBLIOTECA, nullable = true)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_ASOCPRES")
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
    @JoinColumn(name = AsocPoliticaPrestamoDom.COLUMN_NAME_TIPO_LECTOR, nullable = true)
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
     * @return the ejemplarTipo
     */
    @ManyToOne(targetEntity = EjemplarTipo.class, cascade = {
            CascadeType.PERSIST, CascadeType.ALL })
    @JoinColumn(name = AsocPoliticaPrestamoDom.COLUMN_NAME_EJEMPLAR_TIPO, nullable = true)
    public EjemplarTipo getEjemplarTipo() {
        return ejemplarTipo;
    }

    /**
     * @param ejemplarTipo
     *            the ejemplarTipo to set
     */
    public void setEjemplarTipo(final EjemplarTipo ejemplarTipo) {
        this.ejemplarTipo = ejemplarTipo;
    }

    /**
     * @return the politicaPrestamoDom
     */
    @ManyToOne(targetEntity = PoliticaPrestamoDom.class, cascade = {
            CascadeType.PERSIST, CascadeType.ALL })
    @JoinColumn(name = AsocPoliticaPrestamoDom.COLUMN_NAME_POLITICA, nullable = true)
    @ForeignKey(name = "FK_ASOC_POL_PRES_X_POL_PRES")
    public PoliticaPrestamoDom getPoliticaPrestamoDom() {
        return politicaPrestamoDom;
    }

    /**
     * @param politicaPrestamoDom
     *            the politicaPrestamoDom to set
     */
    public void setPoliticaPrestamoDom(
            final PoliticaPrestamoDom politicaPrestamoDom) {
        this.politicaPrestamoDom = politicaPrestamoDom;
    }

    /**
     * overrided methods<br>
     * =================
     */

    @Override
    public boolean equals(final Object asociacion) {
        boolean result = true;
        boolean iguales = false;

        if ((this == asociacion)) {
            iguales = true;
        }
        if ((asociacion == null)) {
            result = false;
        }
        if (!(asociacion instanceof AsocPoliticaPrestamoDom)) {
            result = false;
        } else if (!iguales) {
            final AsocPoliticaPrestamoDom castingAsociacion = (AsocPoliticaPrestamoDom) asociacion;
            if ((getId() != null && (!getId().equals(castingAsociacion.getId())))
                    || ((getId() == null) && castingAsociacion.getId() != null)) {
                result = false;
            }
            if ((getBiblioteca() != null && (!getBiblioteca().equals(
                    castingAsociacion.getBiblioteca())))
                    || ((getBiblioteca() == null) && castingAsociacion
                            .getBiblioteca() != null)) {
                result = false;
            }
            if ((getEjemplarTipo() != null && (!getEjemplarTipo().equals(
                    castingAsociacion.getEjemplarTipo())))
                    || ((getEjemplarTipo() == null) && castingAsociacion
                            .getEjemplarTipo() != null)) {
                result = false;
            }
            if ((getPoliticaPrestamoDom() != null && (!getPoliticaPrestamoDom()
                    .equals(castingAsociacion.getPoliticaPrestamoDom())))
                    || ((getPoliticaPrestamoDom() == null) && castingAsociacion
                            .getPoliticaPrestamoDom() != null)) {
                result = false;
            }
            if ((getTipoLector() != null && (!getTipoLector().equals(
                    castingAsociacion.getTipoLector())))
                    || ((getTipoLector() == null) && castingAsociacion
                            .getTipoLector() != null)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (getId() == null ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public String toString() {

        final ToStringBuilder tsb = new ToStringBuilder(this);
        tsb.append(AsocPoliticaPrestamoDom.PROPTY_NAME_ID, getId());
        tsb.append(AsocPoliticaPrestamoDom.PROPTY_NAME_POLITICA,
                getPoliticaPrestamoDom());
        tsb.append(AsocPoliticaPrestamoDom.PROPTY_NAME_EJEMPLAR_TIPO,
                getEjemplarTipo());
        tsb.append(AsocPoliticaPrestamoDom.PROPTY_NAME_TIPO_LECTOR,
                getTipoLector());
        tsb.append(AsocPoliticaPrestamoDom.COLUMN_NAME_BIBLIOTECA,
                getBiblioteca());

        return tsb.toString();
    }

}
