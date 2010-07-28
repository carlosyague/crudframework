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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.adminconfig.model.Codigo;
import org.librae.adminconfig.model.TipoCodigo;
import org.librae.common.model.BaseObject;

/**
 *Para cada biblioteca consorciada, indica cuales son los servicios de NCIP que
 * publica, y cual es la URL y el puerto de cada uno de ellos.
 * 
 * @author aropero
 */
@Entity(name = NcipServicio.ENTITY_NAME)
@Table(name = NcipServicio.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class NcipServicio extends BaseObject {

    /**
     * BaseObject is Serializable, so Consorcio needs a Serial Version UID
     */
    private static final long   serialVersionUID                   = -5228076118887814317L;

    public static final String  ENTITY_NAME                        = "org.librae.circulacion.model.NcipServicio";
    public static final String  TABLE_NAME                         = "CIR_NCIP_SERVICIOS";
    public static final String  ID_GENERATOR_NAME                  = "generator_cir_ncipServicios";
    private static final String ID_SEQUENCE_NAME                   = "SEQ_CIR_NCIPSERVICIOS";
    public static final String  COLUMN_NAME_ID                     = "X_NCIP_SERVICIOS";
    public static final String  COLUMN_NAME_NAME_SERVICIO_ID_NCIP  = "C_NCIP_SERVICIO";
    public static final String  COLUMN_NAME_CONSORCIO_BIBLIOTECA   = "CON_BIB_X_CONSORCIO_BIBLIOTECA";
    public static final String  COLUMN_NAME_NOMBRE                 = "T_NCIP_SERVICIO";
    public static final String  COLUMN_NAME_DESCRIPCION            = "T_NCIP_SERVICIO_ALT";
    public static final String  COLUMN_NAME_PUERTO                 = "T_PUERTO";
    public static final String  COLUMN_NAME_URL                    = "T_URL";

    public static final String  PROPERTY_NAME_ID                   = "id";
    public static final String  PROPERTY_NAME_SERVICIO_ID_NCIP     = "codigoServicioNCIP";
    public static final String  PROPERTY_NAME_CONSORCIO_BIBLIOTECA = "consorcioBiblioteca";
    public static final String  PROPERTY_NAME_NOMBRE               = "nombre";
    public static final String  PROPERTY_NAME_DESCRIPCION          = "descripcion";
    public static final String  PROPERTY_NAME_PUERTO               = "puerto";
    public static final String  PROPERTY_NAME_URL                  = "url";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Nombre de servicio NCIP, sin espacios intermedios, tal como se
     * especifican en el documento ncip_v1_01.dtd. Por ejemplo: 'CheckOutItem'
     */
    private CodigoNcipServicio  codigoServicioNCIP;

    /**
     * >> CONSORCIOS_BIBLIOTECAS
     */
    private BibliotecaConsorcio consorcioBiblioteca;

    /**
     * URL de llamada al servicio correspondiente
     */
    private String              url;

    protected NcipServicio() {
        super();
    }

    public NcipServicio(CodigoNcipServicio codigoServicioNCIP, String nombre) {
        super();
        this.codigoServicioNCIP = codigoServicioNCIP;
    }

    public NcipServicio(CodigoNcipServicio codigoServicioNCIP,
            BibliotecaConsorcio consorcioBiblioteca, String url) {
        super();
        this.codigoServicioNCIP = codigoServicioNCIP;
        this.consorcioBiblioteca = consorcioBiblioteca;
        this.url = url;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = NcipServicio.COLUMN_NAME_ID)
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
    @ManyToOne(targetEntity = CodigoNcipServicio.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = NcipServicio.COLUMN_NAME_NAME_SERVICIO_ID_NCIP)
    public CodigoNcipServicio getCodigoServicioNCIP() {
        return codigoServicioNCIP;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigoServicioNCIP(CodigoNcipServicio codigoServicioNCIP) {
        this.codigoServicioNCIP = codigoServicioNCIP;
    }

    /**
     * @return the consorcioBiblioteca
     */
    @ManyToOne(targetEntity = BibliotecaConsorcio.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = NcipServicio.COLUMN_NAME_CONSORCIO_BIBLIOTECA)
    public BibliotecaConsorcio getConsorcioBiblioteca() {
        return consorcioBiblioteca;
    }

    /**
     * @param consorcioBiblioteca
     *            the consorcioBiblioteca to set
     */
    public void setConsorcioBiblioteca(BibliotecaConsorcio consorcioBiblioteca) {
        this.consorcioBiblioteca = consorcioBiblioteca;
    }

    /**
     * @return the url
     */
    @Column(name = NcipServicio.COLUMN_NAME_URL)
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof NcipServicio)) {
            return false;
        }

        final NcipServicio other = (NcipServicio) obj;

        if (getCodigoServicioNCIP() == null
                && other.getCodigoServicioNCIP() != null) {
            return false;
        }
        if (getCodigoServicioNCIP() != null
                && !getCodigoServicioNCIP().equals(
                        other.getCodigoServicioNCIP())) {
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
        result = prime
                * result
                + ((getCodigoServicioNCIP() == null) ? 0
                        : getCodigoServicioNCIP().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, getId())

        .append(
                PROPERTY_NAME_SERVICIO_ID_NCIP,
                (getCodigoServicioNCIP() == null) ? ""
                        : getCodigoServicioNCIP().toString())

        .toString();
    }
}