/**
 *
 */
package org.librae.procplanificados.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.common.model.parampoliticas.AbstractParamPolProceso;

/**
 * Bean para almacenar un Proceso
 *
 * @author amDelcampo
 */
@Entity(name = Proceso.NAME_ENTITY)
@Table(name = Proceso.TABLE_NAME)
public class Proceso extends AbstractParamPolProceso {

    /**
     * BaseObject es Serializable, por lo tanto Proceso necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID            = 5654243342678073970L;

    public static final String  NAME_ENTITY                 = "org.librae.procplanificados.model.Proceso";
    public static final String  TABLE_NAME                  = "PRO_PROCESOS";
    private static final String ID_GENERATOR_NAME           = "generator_pro_procesos";
    private static final String ID_SEQUENCE_NAME            = "SEQ_PRO_PROCESOS";
    public static final String  COLUMN_NAME_ID              = "X_PROCESO";
    public static final String  COLUMN_NAME_ENABLED         = "C_ENABLED";
    public static final String  COLUMN_NAME_TIPOPATRON      = "C_TIPOPATRON";
    public static final String  COLUMN_NAME_FRECUENCIA      = "N_FRECUENCIA";
    public static final String  COLUMN_NAME_NOMBREPROCESO   = "N_PROCESO";
    public static final String  COLUMN_NAME_CRON            = "T_CRON";

    public static final String  PROPERTY_NAME_ID            = "id";
    public static final String  PROPERTY_NAME_ENABLED       = "procEnabled";
    public static final String  PROPERTY_NAME_TIPOPATRON    = "tipoPatron";
    public static final String  PROPERTY_NAME_FRECUENCIA    = "frecuencia";
    public static final String  PROPERTY_NAME_NOMBREPROCESO = "nombreProceso";
    public static final String  PROPERTY_NAME_CRON          = "cron";
    public static final String  PROPERTY_NAME_EJECUCIONES   = "ejecuciones";

    /**
     * clave primaria.
     */
    private Long                id;

    /**
     * Indica si el proceso planificado está activo o no.
     */
    private Boolean             procEnabled;

    /**
     * Tipo de patrón de planificación.
     */
    private Long                tipoPatron;

    /**
     * Frecuencia para planificaciones periódicas.
     */
    private Long                frecuencia;

    /**
     * Denominación del proceso.
     */
    private String              nombreProceso;

    /**
     * Patrón cron.
     */
    private String              cron;

    /**
     * Ejecuciones del proceso planificado.
     */
    private List<Ejecucion> ejecuciones = new LinkedList<Ejecucion>();

    /**
     * Constructor con parametros. Por defecto está activo.
     * @param nombreProcesoFinal
     * @param tipoPatronFinal
     */
    public Proceso(String nombreProcesoFinal, Long tipoPatronFinal){
        super();
        nombreProceso = nombreProcesoFinal;
        tipoPatron = tipoPatronFinal;
        procEnabled = Boolean.TRUE;
    }
    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Proceso.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Proceso.ID_SEQUENCE_NAME)
    @Column(name = Proceso.COLUMN_NAME_ID)
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
     * @return the procEnabled
     */
    @Column(name = Proceso.COLUMN_NAME_ENABLED)
    public Boolean getProcEnabled() {
        return procEnabled;
    }

    /**
     * @param procEnabled
     *            the procEnabled to set
     */
    public void setProcEnabled(Boolean procEnabled) {
        this.procEnabled = procEnabled;
    }

    /**
     * @return the tipoPatron
     */
    @Column(name = Proceso.COLUMN_NAME_TIPOPATRON)
    public Long getTipoPatron() {
        return tipoPatron;
    }

    /**
     * @param tipoPatron
     *            the tipoPatron to set
     */
    public void setTipoPatron(Long tipoPatron) {
        this.tipoPatron = tipoPatron;
    }

    /**
     * @return the frecuencia
     */
    @Column(name = Proceso.COLUMN_NAME_FRECUENCIA)
    public Long getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia
     *            the frecuencia to set
     */
    public void setFrecuencia(Long frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * @return the nombreProceso
     */
    @Column(name = Proceso.COLUMN_NAME_NOMBREPROCESO, unique = true, length = 255)
    public String getNombreProceso() {
        return nombreProceso;
    }

    /**
     * @param nombreProceso
     *            the nombreProceso to set
     */
    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    /**
     * @return the cron
     */
    @Column(name = Proceso.COLUMN_NAME_CRON, length = 255)
    public String getCron() {
        return cron;
    }

    /**
     * @param cron
     *            the cron to set
     */
    public void setCron(String cron) {
        this.cron = cron;
    }

    /**
     * @return the direccionesPostales
     */
    @OneToMany(mappedBy = Ejecucion.PROPERTY_NAME_PROCESO_FK, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @OrderBy(Ejecucion.PROPERTY_NAME_ULTEJECUCION + " DESC")
    public List<Ejecucion> getEjecuciones() {
        return ejecuciones;
    }

    /**
     * @param direccionesPostales
     *            the direccionesPostales to set
     */
    public void setEjecuciones(List<Ejecucion> ejecuciones) {
        this.ejecuciones = ejecuciones;
    }

    /**
     * Constructor sin parámetros
     */
    public Proceso() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Proceso)) {
            return false;
        }
        final Proceso other = (Proceso) obj;
        if (nombreProceso == null) {
            if (other.nombreProceso != null) {
                return false;
            }
        } else if (!nombreProceso.equals(other.nombreProceso)) {
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

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result
                + ((nombreProceso == null) ? 0 : nombreProceso.hashCode());
        result = prime * result
        + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Proceso.COLUMN_NAME_ID, id)
                .append(Proceso.COLUMN_NAME_ENABLED, procEnabled).append(
                        Proceso.COLUMN_NAME_FRECUENCIA, frecuencia).append(
                        Proceso.COLUMN_NAME_CRON, cron).append(
                        Proceso.COLUMN_NAME_TIPOPATRON, tipoPatron).append(
                        Proceso.COLUMN_NAME_NOMBREPROCESO, nombreProceso)
                .toString();
    }

}
