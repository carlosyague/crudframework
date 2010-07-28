package org.librae.adminconfig.model;

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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Tabla con las direcciones
 * 
 * @author asantamaria
 */
@Entity(name = Direccion.ENTITY_NAME)
@Table(name = Direccion.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Direccion extends SpringRemotableLazyEntity<Direccion> {

    /**
     * BaseObject is Serializable, so Direccion needs a Serial Version UID
     */
    private static final long   serialVersionUID              = -7430959101571026246L;

    public static final String  ENTITY_NAME                   = "org.librae.adminconfig.model.Direccion";
    public static final String  TABLE_NAME                    = "ADM_DIRECCIONES";
    public static final String  ID_GENERATOR_NAME             = "generator_adm_direcciones";
    private static final String ID_SEQUENCE_NAME              = "SEQ_ADM_DIRECCIONES";

    public static final String  COLUMN_NAME_ID                = "X_DIRECCION";
    public static final String  COLUMN_NAME_NOMBRE_VIA        = "T_VIA";
    public static final String  COLUMN_NAME_NUMERO_VIA        = "T_NUMERO_VIA";
    public static final String  COLUMN_NAME_PISO              = "T_PISO";
    public static final String  COLUMN_NAME_CODIGO_POSTAL     = "T_CODIGO_POSTAL";
    public static final String  COLUMN_NAME_ACLARADOR         = "T_ACLARADOR";
    public static final String  COLUMN_NAME_NOMBRE_EDIFICIO   = "T_EDIFICIO";
    public static final String  COLUMN_NAME_PAIS              = "PAIS_X_PAIS";
    public static final String  COLUMN_NAME_COM_AUTONOMA      = "COM_X_COMAUTONOMA";
    public static final String  COLUMN_NAME_PROVINCIA         = "PROV_X_PROVINCIA";
    public static final String  COLUMN_NAME_MUNICIPIO         = "MUN_X_MUNICIPIO";
    public static final String  COLUMN_NAME_LOCALIDAD         = "LOC_X_LOCALIDAD";
    public static final String  COLUMN_NAME_LOCALIDAD_TEXTO   = "T_LOCALIDAD_TEXTO";
    public static final String  COLUMN_NAME_TIPO_VIA          = "TVI_X_TIPO_VIA";

    public static final String  PROPERTY_NAME_ID              = "id";
    public static final String  PROPERTY_NAME_NOMBRE_VIA      = "nombreVia";
    public static final String  PROPERTY_NUMERO_VIA           = "numeroVia";
    public static final String  PROPERTY_NAME_PISO            = "piso";
    public static final String  PROPERTY_NAME_CODIGO_POSTAL   = "codigoPostal";
    public static final String  PROPERTY_NAME_ACLARADOR       = "aclarador";
    public static final String  PROPERTY_NAME_NOMBRE_EDIFICIO = "nombreEdificio";
    public static final String  PROPERTY_NAME_PAIS            = "pais";
    public static final String  PROPERTY_NAME_COM_AUTONOMA    = "comAutonoma";
    public static final String  PROPERTY_NAME_PROVINCIA       = "provincia";
    public static final String  PROPERTY_NAME_MUNICIPIO       = "municipio";
    public static final String  PROPERTY_NAME_LOCALIDAD       = "localidad";
    public static final String  PROPERTY_NAME_LOCALIDAD_TEXTO = "localidadTexto";
    public static final String  PROPERTY_NAME_TIPO_VIA        = "tipoVia";

    /**
     * Id de la dirección, clave primaria de la tabla
     */
    private Long                id;

    /**
     * Calle
     */
    private String              nombreVia;

    /**
     * Número
     */
    private Long                numeroVia;

    /**
     * Piso, escalera, puerta, .. de la dirección
     */
    private String              piso;

    /**
     * Código Postal
     */
    private String              codigoPostal;

    /**
     * Aclarador, descripción alternativa y explicatoria de la dirección
     */
    private String              aclarador;

    /**
     * Nombre de edificio
     */
    private String              nombreEdificio;

    /**
     * >> PAISES
     */
    private Pais                pais;

    /**
     * >> COMUNIDADES AUTÓNOMAS
     */
    private ComunidadAutonoma   comAutonoma;

    /**
     * >> PROVINCIAS
     */
    private Provincia           provincia;

    /**
     * >> MUNICIPIOS
     */
    private Municipio           municipio;

    /**
     * >> LOCALIDADES
     */
    private Localidad           localidad;

    /**
     * Localidad de la dirección
     */
    private String              localidadTexto;

    /**
     * >> TIPOS_VIAS<br>
     * Tipo de vía
     */
    private TipoVia             tipoVia;

    protected Direccion() {
        super();
    }

    public Direccion(String nombreVia, Long numeroVia) {
        super();
        this.nombreVia = nombreVia;
        this.numeroVia = numeroVia;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Direccion.COLUMN_NAME_ID)
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
     * @return the nombreVia
     */
    @Column(name = Direccion.COLUMN_NAME_NOMBRE_VIA, length = 80)
    public String getNombreVia() {
        return nombreVia;
    }

    /**
     * @param nombreVia
     *            the nombreVia to set
     */
    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    /**
     * @return the numeroVia
     */
    @Column(name = Direccion.COLUMN_NAME_NUMERO_VIA)
    public Long getNumeroVia() {
        return numeroVia;
    }

    /**
     * @param numeroVia
     *            the numeroVia to set
     */
    public void setNumeroVia(Long numeroVia) {
        this.numeroVia = numeroVia;
    }

    /**
     * @return the piso
     */
    @Column(name = Direccion.COLUMN_NAME_PISO, length = 40)
    public String getPiso() {
        return piso;
    }

    /**
     * @param piso
     *            the piso to set
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * @return the codigoPostal
     */
    @Column(name = Direccion.COLUMN_NAME_CODIGO_POSTAL, length = 10)
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal
     *            the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return the aclarador
     */
    @Column(name = Direccion.COLUMN_NAME_ACLARADOR)
    public String getAclarador() {
        return aclarador;
    }

    /**
     * @param aclarador
     *            the aclarador to set
     */
    public void setAclarador(String aclarador) {
        this.aclarador = aclarador;
    }

    /**
     * @return the nombreEdificio
     */
    @Column(name = Direccion.COLUMN_NAME_NOMBRE_EDIFICIO, length = 80)
    public String getNombreEdificio() {
        return nombreEdificio;
    }

    /**
     * @param nombreEdificio
     *            the nombreEdificio to set
     */
    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    /**
     * @return the pais
     */
    @ManyToOne(targetEntity = Pais.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = Direccion.COLUMN_NAME_PAIS)
    public Pais getPais() {
        return pais;
    }

    /**
     * @param pais
     *            the pais to set
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * @return the comAutonoma
     */
    @ManyToOne(targetEntity = ComunidadAutonoma.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = Direccion.COLUMN_NAME_COM_AUTONOMA)
    public ComunidadAutonoma getComAutonoma() {
        return comAutonoma;
    }

    /**
     * @param comAutonoma
     *            the comAutonoma to set
     */
    public void setComAutonoma(ComunidadAutonoma comAutonoma) {
        this.comAutonoma = comAutonoma;
    }

    /**
     * @return the provincia
     */
    @ManyToOne(targetEntity = Provincia.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = Direccion.COLUMN_NAME_PROVINCIA)
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * @param provincia
     *            the provincia to set
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the municipio
     */
    @ManyToOne(targetEntity = Municipio.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = Direccion.COLUMN_NAME_MUNICIPIO)
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio
     *            the municipio to set
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the localidad
     */
    @ManyToOne(targetEntity = Localidad.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = Direccion.COLUMN_NAME_LOCALIDAD)
    public Localidad getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad
     *            the localidad to set
     */
    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    /**
     * @return the localidadTexto
     */
    @Column(name = Direccion.COLUMN_NAME_LOCALIDAD_TEXTO, length = 60)
    public String getLocalidadTexto() {
        return localidadTexto;
    }

    /**
     * @param localidadTexto
     *            the localidadTexto to set
     */
    public void setLocalidadTexto(String localidadTexto) {
        this.localidadTexto = localidadTexto;
    }

    /**
     * @return the tipoVia
     */
    @ManyToOne(targetEntity = TipoVia.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = Direccion.COLUMN_NAME_TIPO_VIA, updatable = false)
    public TipoVia getTipoVia() {
        return tipoVia;
    }

    /**
     * @param tipoVia
     *            the tipoVia to set
     */
    public void setTipoVia(TipoVia tipoVia) {
        this.tipoVia = tipoVia;
    }

    @Transient
    public String getDireccionCompleta() {
        // Nos devuelve la direccion con todos sus campos
        final StringBuilder sb = new StringBuilder();
        if (tipoVia != null) {
            sb.append(tipoVia.getCodigo()).append("  ");
        }
        if (nombreVia != null) {
            sb.append(nombreVia).append(", ");
        }
        if (numeroVia != null) {
            sb.append("numero " + numeroVia).append(", ");
        }
        if (piso != null) {
            sb.append("piso " + piso);
        }
        return sb.toString();
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
        if (!(obj instanceof Direccion)) {
            return false;
        }

        final Direccion other = (Direccion) obj;

        if (getTipoVia() == null && other.getTipoVia() != null) {
            return false;
        }
        if (getTipoVia() != null && !getTipoVia().equals(other.getTipoVia())) {
            return false;
        }

        if (getNombreVia() == null && other.getNombreVia() != null) {
            return false;
        }
        if (getNombreVia() != null
                && !getNombreVia().equals(other.getNombreVia())) {
            return false;
        }

        if (getNumeroVia() == null && other.getNumeroVia() != null) {
            return false;
        }
        if (getNumeroVia() != null
                && !getNumeroVia().equals(other.getNumeroVia())) {
            return false;
        }

        if (getPiso() == null && other.getPiso() != null) {
            return false;
        }
        if (getPiso() != null && !getPiso().equals(other.getPiso())) {
            return false;
        }

        if (getCodigoPostal() == null && other.getCodigoPostal() != null) {
            return false;
        }
        if (getCodigoPostal() != null
                && !getCodigoPostal().equals(other.getCodigoPostal())) {
            return false;
        }

        if (getLocalidad() == null && other.getLocalidad() != null) {
            return false;
        }
        if (getLocalidad() != null
                && !getLocalidad().equals(other.getLocalidad())) {
            return false;
        }

        if (getPais() == null && other.getPais() != null) {
            return false;
        }
        if (getPais() != null && !getPais().equals(other.getPais())) {
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
                + ((getTipoVia() == null) ? 0 : getTipoVia().hashCode());

        result = prime * result
                + ((getNombreVia() == null) ? 0 : getNombreVia().hashCode());

        result = prime
                * result
                + ((getNombreEdificio() == null) ? 0 : getNombreEdificio()
                        .hashCode());

        result = prime * result
                + ((getNumeroVia() == null) ? 0 : getNumeroVia().hashCode());

        result = prime * result
                + ((getPiso() == null) ? 0 : getPiso().hashCode());

        result = prime
                * result
                + ((getCodigoPostal() == null) ? 0 : getCodigoPostal()
                        .hashCode());

        result = prime * result
                + ((getLocalidad() == null) ? 0 : getLocalidad().hashCode());

        result = prime * result
                + ((getPais() == null) ? 0 : getPais().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(COLUMN_NAME_ID, getId())
                .append(COLUMN_NAME_TIPO_VIA,
                        (getTipoVia() == null) ? 0 : getTipoVia().toString())
                .append(
                        COLUMN_NAME_NOMBRE_VIA,
                        (getNombreVia() == null) ? 0 : getNombreVia()
                                .toString()).append(
                        COLUMN_NAME_NOMBRE_EDIFICIO,
                        (getNombreEdificio() == null) ? 0 : getNombreEdificio()
                                .toString()).append(
                        COLUMN_NAME_NUMERO_VIA,
                        (getNumeroVia() == null) ? 0 : getNumeroVia()
                                .toString()).append(COLUMN_NAME_PISO,
                        (getPiso() == null) ? 0 : getPiso().toString()).append(
                        COLUMN_NAME_CODIGO_POSTAL,
                        (getCodigoPostal() == null) ? 0 : getCodigoPostal()
                                .toString()).append(
                        COLUMN_NAME_LOCALIDAD,
                        (getLocalidad() == null) ? 0 : getLocalidad()
                                .toString()).append(COLUMN_NAME_TIPO_VIA,
                        (getPais() == null) ? 0 : getPais().toString())
                .toString();
    }

    @Override
    public Direccion newInstance() {
        return new Direccion();
    }

}