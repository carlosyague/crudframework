package org.librae.adquisicion.model;

import java.math.BigDecimal;
import java.util.Date;

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
 * Bean para almacenar un Presupuesto
 * 
 * @author jcdiaz
 */
@Entity(name = Transferencia.NAME_ENTITY)
@Table(name = Transferencia.TABLE_NAME)
public class Transferencia extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Presupuesto necesita un Serial
     * Version UID
     */
    private static final long     serialVersionUID                     = 5131467438798843830L;

    public static final String    NAME_ENTITY                          = "org.librae.adquisicion.model.Transferencia";
    public static final String    TABLE_NAME                           = "ADQ_TRANSFERENCIAS";
    private static final String   ID_GENERATOR_NAME                    = "generator_adq_transferencias";
    private static final String   ID_SEQUENCE_NAME                     = "SEQ_ADQ_TRANSFERENCIAS";
    public static final String    COLUMN_NAME_ID                       = "X_TRANSFERENCIA";
    public static final String    COLUMN_NAME_FECHA                    = "F_FECHA";
    public static final String    COLUMN_NAME_COMPROMETIDO_TRANSFERIDO = "I_COMPROMETIDO";
    public static final String    COLUMN_NAME_DISPONIBLE_TRANSFERIDO   = "I_DISPONIBLE";
    public static final String    COLUMN_NAME_PARTIDA_ORIGEN_FK        = "PAR_X_PARTIDA_ORIGEN";
    public static final String    COLUMN_NAME_PARTIDO_DESTINO_FK       = "PAR_X_PARTIDA_DESTINO";

    /**
     * clave principal
     */
    private Long                  id;

    /**
     * fecha de la transferencia
     */
    private Date                  fecha;

    /**
     * valor del comprometido transferido
     */
    private BigDecimal            comprometidoTransferido;

    /**
     * valor del disponible
     */
    private BigDecimal            disponibleTransferido;

    /**
     * referencia a la partida origen
     */
    private PartidaPresupuestaria partidaOrigen;

    /**
     * referencia a la partida destino
     */
    private PartidaPresupuestaria partidaDestino;

    /**
     * Constructor sin parametros
     */
    protected Transferencia() {
    }

    /**
     * Constructor con parametros
     * 
     * @param comprometidoTransferido
     * @param disponibleTransferido
     * @param fecha
     */
    public Transferencia(BigDecimal comprometidoTransferido,
            BigDecimal disponibleTransferido, Date fecha) {
        this.comprometidoTransferido = comprometidoTransferido;
        this.disponibleTransferido = disponibleTransferido;
        this.fecha = fecha;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Transferencia.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Transferencia.ID_SEQUENCE_NAME)
    @Column(name = Transferencia.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fecha
     */
    @Column(name = Transferencia.COLUMN_NAME_FECHA)
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha
     *            the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the comprometidoTransferido
     */
    @Column(name = Transferencia.COLUMN_NAME_COMPROMETIDO_TRANSFERIDO)
    public BigDecimal getComprometidoTransferido() {
        return comprometidoTransferido;
    }

    /**
     * @param comprometidoTransferido
     *            the comprometidoTransferido to set
     */
    public void setComprometidoTransferido(BigDecimal comprometidoTransferido) {
        this.comprometidoTransferido = comprometidoTransferido;
    }

    /**
     * @return the disponibleTransferido
     */
    @Column(name = Transferencia.COLUMN_NAME_DISPONIBLE_TRANSFERIDO)
    public BigDecimal getDisponibleTransferido() {
        return disponibleTransferido;
    }

    /**
     * @param disponibleTransferido
     *            the disponibleTransferido to set
     */
    public void setDisponibleTransferido(BigDecimal disponibleTransferido) {
        this.disponibleTransferido = disponibleTransferido;
    }

    /**
     * @return the partidaOrigen
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = PartidaPresupuestaria.class)
    @JoinColumn(name = Transferencia.COLUMN_NAME_PARTIDA_ORIGEN_FK)
    public PartidaPresupuestaria getPartidaOrigen() {
        return partidaOrigen;
    }

    /**
     * @param partidaOrigen
     *            the partidaOrigen to set
     */
    public void setPartidaOrigen(PartidaPresupuestaria partidaOrigen) {
        this.partidaOrigen = partidaOrigen;
    }

    /**
     * @return the partidaDestino
     */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = PartidaPresupuestaria.class)
    @JoinColumn(name = Transferencia.COLUMN_NAME_PARTIDO_DESTINO_FK)
    public PartidaPresupuestaria getPartidaDestino() {
        return partidaDestino;
    }

    /**
     * @param partidaDestino
     *            the partidaDestino to set
     */
    public void setPartidaDestino(PartidaPresupuestaria partidaDestino) {
        this.partidaDestino = partidaDestino;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime
                * result
                + ((comprometidoTransferido == null) ? 0
                        : comprometidoTransferido.hashCode());
        result = prime
                * result
                + ((disponibleTransferido == null) ? 0 : disponibleTransferido
                        .hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((partidaDestino == null) ? 0 : partidaDestino.hashCode());
        result = prime * result
                + ((partidaOrigen == null) ? 0 : partidaOrigen.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Transferencia)) {
            return false;
        }
        Transferencia other = (Transferencia) obj;
        if (comprometidoTransferido == null) {
            if (other.comprometidoTransferido != null) {
                return false;
            }
        } else if (!comprometidoTransferido
                .equals(other.comprometidoTransferido)) {
            return false;
        }
        if (disponibleTransferido == null) {
            if (other.disponibleTransferido != null) {
                return false;
            }
        } else if (!disponibleTransferido.equals(other.disponibleTransferido)) {
            return false;
        }
        if (fecha == null) {
            if (other.fecha != null) {
                return false;
            }
        } else if (!fecha.equals(other.fecha)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (partidaDestino == null) {
            if (other.partidaDestino != null) {
                return false;
            }
        } else if (!partidaDestino.equals(other.partidaDestino)) {
            return false;
        }
        if (partidaOrigen == null) {
            if (other.partidaOrigen != null) {
                return false;
            }
        } else if (!partidaOrigen.equals(other.partidaOrigen)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Transferencia.COLUMN_NAME_ID,
                this.id).append(Transferencia.COLUMN_NAME_FECHA, this.fecha)
                .append(Transferencia.COLUMN_NAME_COMPROMETIDO_TRANSFERIDO,
                        this.comprometidoTransferido).append(
                        Transferencia.COLUMN_NAME_DISPONIBLE_TRANSFERIDO,
                        this.disponibleTransferido).toString();
    }

}