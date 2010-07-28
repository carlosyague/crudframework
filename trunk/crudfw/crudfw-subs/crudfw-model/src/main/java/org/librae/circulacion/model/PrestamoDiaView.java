package org.librae.circulacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.catalogacion.model.Ejemplar;
import org.librae.common.model.BaseObject;

/**
 * Prestamos en el dia para la pantalla de prestamo a domicilio y prestamo en
 * sala.
 * 
 * @author jcisneros
 */
@Entity(name = PrestamoDiaView.ENTITY_NAME)
@Table(name = PrestamoDiaView.TABLE_NAME)
public class PrestamoDiaView extends BaseObject {

    /**
     * BaseObject is Serializable, so Prestamo needs a Serial Version UID
     */
    private static final long  serialVersionUID          = 6245974682779963953L;

    public static final String ENTITY_NAME               = "org.librae.circulacion.model.PrestamoDiaView";
    public static final String TABLE_NAME                = "CIR_VIEW_PRESTAMOS_DIA";
    public static final String ID_GENERATOR_NAME         = "generator_cir_prestamos_dia";

    public static final String COLUMN_NAME_ID            = "X_CIR_VIEW_PRESTAMOS_DIA";
    public static final String COLUMN_NAME_EJEMPLAR      = "EJEMPLAR_X_EJEMPLAR";
    public static final String COLUMN_NAME_TIPO_PRESTAMO = "TIPO_PRESTAMO_X_TIPO_PRESTAMO";
    public static final String COLUMN_NAME_DEVUELTO      = "L_DEVUELTO";

    public static final String PROPTY_NAME_ID            = "id";
    public static final String PROPTY_NAME_EJEMPLAR      = "ejemplar";
    public static final String PROPTY_NAME_TIPO_LECTOR   = "tipoLector";
    public static final String PROPTY_NAME_DEVUELTO      = "devuelto";
    public static final String PROPTY_NAME_PRESTAMO      = "tipoPrestamo";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private String             id;

    private Ejemplar           ejemplar;

    private TipoPrestamo       tipoPrestamo;

    private String             devuelto;

    protected PrestamoDiaView() {
        super();
    }

    public PrestamoDiaView(final Ejemplar ejemplar) {
        super();
        this.ejemplar = ejemplar;
    }

    public PrestamoDiaView(final Ejemplar ejemplar,
            final PoliticaPrestamoDom politica) {
        super();
        this.ejemplar = ejemplar;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PrestamoDiaView.COLUMN_NAME_ID)
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final String id) {
        this.id = id;
    }

    /**
     * @return the ejemplar
     */
    @ManyToOne(targetEntity = Ejemplar.class, fetch = FetchType.LAZY)
    @JoinColumn(name = PrestamoDiaView.COLUMN_NAME_EJEMPLAR, nullable = true)
    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    /**
     * @param ejemplar
     *            the ejemplar to set
     */
    public void setEjemplar(final Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    @ManyToOne(targetEntity = TipoPrestamo.class)
    @JoinColumn(name = PrestamoDiaView.COLUMN_NAME_TIPO_PRESTAMO, nullable = true)
    public TipoPrestamo getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(final TipoPrestamo tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    @Column(name = PrestamoDiaView.COLUMN_NAME_DEVUELTO)
    public String getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(final String devuelto) {
        this.devuelto = devuelto;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof PrestamoDiaView)) {
            return false;
        }

        final PrestamoDiaView other = (PrestamoDiaView) obj;

        if (!getId().equals(other.getId())) {
            return false;
        }
        return true;

    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime * result
                + ((getEjemplar() == null) ? 0 : getEjemplar().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(PrestamoDiaView.PROPTY_NAME_ID,
                getId()).toString();
    }
}