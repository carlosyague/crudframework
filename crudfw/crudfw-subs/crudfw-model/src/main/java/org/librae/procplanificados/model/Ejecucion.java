/**
 *
 */
package org.librae.procplanificados.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.parampoliticas.AbstractParamPolEjecucion;

/**
 * Bean para almacenar una Ejecucion
 *
 * @author amDelcampo
 */
@Entity(name = Ejecucion.NAME_ENTITY)
@Table(name = Ejecucion.TABLE_NAME)
public class Ejecucion extends AbstractParamPolEjecucion {

    /**
     * BaseObject es Serializable, por lo tanto Ejecucion necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID           = -3596563980145420063L;

    public static final String  NAME_ENTITY                = "org.librae.procplanificados.model.Ejecucion";
    public static final String  TABLE_NAME                 = "PRO_EJECUCION";
    private static final String ID_GENERATOR_NAME          = "generator_pro_ejecucion";
    private static final String ID_SEQUENCE_NAME           = "SEQ_PRO_EJECUCION";
    public static final String  COLUMN_NAME_ID             = "X_EJECUCION";
    public static final String  COLUMN_NAME_ESTADO         = "C_ESTADO";
    public static final String  COLUMN_NAME_FINEJECUCION   = "F_FINEJECUCION";
    public static final String  COLUMN_NAME_INIEJECUCION   = "F_INIEJECUCION";
    public static final String  COLUMN_NAME_LOG            = "T_LOG";
    public static final String  COLUMN_NAME_ULTEJECUCION   = "L_ULTIMA";
    public static final String  COLUMN_NAME_PROCESO_FK     = "PRO_X_PROCESO";

    public static final String  PROPERTY_NAME_ID           = "id";
    public static final String  PROPERTY_NAME_ESTADO       = "estado";
    public static final String  PROPERTY_NAME_FINEJECUCION = "finEjecucion";
    public static final String  PROPERTY_NAME_INIEJECUCION = "iniEjecucion";
    public static final String  PROPERTY_NAME_LOG          = "log";
    public static final String  PROPERTY_NAME_PROCESO_FK   = "proceso";
    public static final String  PROPERTY_NAME_ULTEJECUCION = "ultEjecucion";

    /**
     * clave primaria.
     */
    private Long                id;
    /**
     * Estado de la ejecución.
     */
    private String              estado;
    /**
     * Fecha de fin de ejecución.
     */
    private Date                finEjecucion;
    /**
     * Fecha de inicio de ejecuión.
     */
    private Date                iniEjecucion;
    /**
     * Log de la ejecución.
     */
    private char[]              log;
    /**
     * Referencia al Proceso ejecutado.
     */
    private Proceso             proceso;

    /**
     * La ejecución es la última del proceso.
     */
    private Boolean             ultEjecucion;

    /**
     * Constructor sin parámetros
     */
    public Ejecucion() {
        super();
    }

    /**
     * Constructor con parámetros. Por defecto no es la última ejecución.
     *
     * @param estado
     * @param iniEjecucion
     */
    public Ejecucion(String estado, Date iniEjecucion) {
        super();
        this.estado = estado;
        this.iniEjecucion = iniEjecucion;
        this.ultEjecucion = Boolean.FALSE;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Ejecucion.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Ejecucion.ID_SEQUENCE_NAME)
    @Column(name = Ejecucion.COLUMN_NAME_ID)
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
     * @return the estado
     */
    @Column(name = Ejecucion.COLUMN_NAME_ESTADO, length = 40)
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

    /**
     * @return the finEjecucion
     */
    @Column(name = Ejecucion.COLUMN_NAME_FINEJECUCION)
    public Date getFinEjecucion() {
        return finEjecucion;
    }

    /**
     * @param finEjecucion
     *            the finEjecucion to set
     */
    public void setFinEjecucion(Date finEjecucion) {
        this.finEjecucion = finEjecucion;
    }

    /**
     * @return the iniEjecucion
     */
    @Column(name = Ejecucion.COLUMN_NAME_INIEJECUCION)
    public Date getIniEjecucion() {
        return iniEjecucion;
    }

    /**
     * @param iniEjecucion
     *            the iniEjecucion to set
     */
    public void setIniEjecucion(Date iniEjecucion) {
        this.iniEjecucion = iniEjecucion;
    }

    /**
     * @return the urlFichero
     */
    @Column(name = Ejecucion.COLUMN_NAME_LOG)
    @Basic(fetch = FetchType.LAZY)
    @Lob
    public char[] getLog() {
        return log;
    }

    /**
     * @param urlFichero
     *            the urlFichero to set
     */
    public void setLog(char[] log) {
        this.log = log;
    }

    /**
     * @return the proceso
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Proceso.class)
    @JoinColumn(name = Ejecucion.COLUMN_NAME_PROCESO_FK)
    public Proceso getProceso() {
        return proceso;
    }

    /**
     * @param proceso
     *            the proceso to set
     */
    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    /**
     * @return the ultEjecucion
     */
    @Column(name = Ejecucion.COLUMN_NAME_ULTEJECUCION, nullable = false)
    public Boolean getUltEjecucion() {
        return ultEjecucion;
    }

    /**
     * @param ultEjecucion the ultEjecucion to set
     */
    public void setUltEjecucion(Boolean ultEjecucion) {
        this.ultEjecucion = ultEjecucion;
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
        if (!(obj instanceof Ejecucion)) {
            return false;
        }
        final Ejecucion other = (Ejecucion) obj;
        if (finEjecucion == null) {
            if (other.finEjecucion != null) {
                return false;
            }
        } else if (!finEjecucion.equals(other.finEjecucion)) {
            return false;
        }
        if (iniEjecucion == null) {
            if (other.iniEjecucion != null) {
                return false;
            }
        } else if (!iniEjecucion.equals(other.iniEjecucion)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        if (proceso == null) {
            if (other.proceso != null) {
                return false;
            }
        } else if (!proceso.equals(other.proceso)) {
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
                + ((proceso == null) ? 0 : proceso.getId().hashCode());
        result = prime * result
                + ((finEjecucion == null) ? 0 : finEjecucion.hashCode());
        result = prime * result
                + ((iniEjecucion == null) ? 0 : iniEjecucion.hashCode());
        result = prime * result + prime * result
                + ((proceso == null) ? 0 : proceso.hashCode());
        result = prime * result + prime * result
        + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Ejecucion.COLUMN_NAME_ID, id)
                .append(Ejecucion.COLUMN_NAME_ESTADO, estado)
                .append(Ejecucion.COLUMN_NAME_ULTEJECUCION, ultEjecucion)
                .append(Ejecucion.COLUMN_NAME_INIEJECUCION, iniEjecucion)
                .append(Ejecucion.COLUMN_NAME_FINEJECUCION, finEjecucion)
                .append(Ejecucion.COLUMN_NAME_PROCESO_FK, proceso).append("\n").toString();
    }
}
