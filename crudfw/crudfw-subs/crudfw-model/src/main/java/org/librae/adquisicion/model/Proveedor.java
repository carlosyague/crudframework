package org.librae.adquisicion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.common.model.BaseObject;

/**
 * Bean para almacenar un Proveedor
 *
 * @author jcdiaz
 */
@Entity(name = Proveedor.NAME_ENTITY)
@Table(name = Proveedor.TABLE_NAME)
public class Proveedor extends BaseObject {

    /**
     * BaseObject es Serializable, por lo tanto Proveedor necesita un Serial
     * Version UID
     */
    private static final long     serialVersionUID                    = -7903197559835954176L;

    public static final String    NAME_ENTITY                         = "org.librae.adquisicion.model.Proveedor";
    public static final String    TABLE_NAME                          = "ADQ_PROVEEDORES";
    private static final String   ID_GENERATOR_NAME                   = "generator_adq_proveedores";
    private static final String   ID_SEQUENCE_NAME                    = "SEQ_ADQ_PROVEEDORES";
    public static final String    COLUMN_NAME_ID                      = "X_PROVEEDOR";
    public static final String    COLUMN_NAME_CIF                     = "T_CIF";
    public static final String    COLUMN_NAME_NOMBRE_FISCAL           = "T_NOMBRE_FISCAL";
    public static final String    COLUMN_NAME_NOMBRE_COMERCIAL        = "T_NOMBRE_COMERCIAL";
    public static final String    COLUMN_NAME_DIRECCION_WEB           = "T_DIRECCION_WEB";
    public static final String    COLUMN_NAME_INSTITUCION             = "L_INSTITUCION";
    public static final String    COLUMN_NAME_PROVEEDOR_BIBLIOTECA_FK = "PRO_X_PROVEEDOR_BIBLIOTECA";

    public static final String    PROPERTY_NAME_ID				      = "id";
    public static final String    PROPERTY_NAME_CIF				      = "cif";
    public static final String    PROPERTY_NAME_NOMBRE_FISCAL         = "nombreFiscal";
    public static final String    PROPERTY_NAME_NOMBRE_COMERCIAL      = "nombreComercial";
    public static final String    PROPERTY_NAME_DIRECCION_WEB  		  = "direccionWeb";
    public static final String    PROPERTY_NAME_INSTITUCION 		  = "institucion";
    public static final String    PROPERTY_NAME_PROVEEDOR_BIBLIOTECA_FK = "proveedorBiblioteca";
    public static final String    PROPERTY_NAME_DIRECCIONES_POSTALES = "direccionesPostales";

    /**
     * clave principal
     */
    private Long                  id;

    /**
     * CIF del proveedor
     */
    private String                cif;

    /**
     * nombre fiscal del proveedor
     */
    private String                nombreFiscal;

    /**
     * nombre comercial del proveedor
     */
    private String                nombreComercial;

    /**
     * direccion web del proveedor
     */
    private String                direccionWeb;

    /**
     * indica si es institución o no
     */
    private Boolean               institucion;

    /**
     * coleccion de direcciones asociadas al proveedor
     */
    private List<DireccionPostal> direccionesPostales;

    /**
     *
     */
    private ProveedorBiblioteca   proveedorBiblioteca;

	/**
     * Constructor sin parametros
     */
    protected Proveedor() {
        this.direccionesPostales = new ArrayList<DireccionPostal>();
    }

    /**
     * Constructor con parametros
     *
     * @param cif
     * @param direccionWeb
     * @param institucion
     * @param nombreComercial
     * @param nombreFiscal
     */
    public Proveedor(String cif, String direccionWeb, Boolean institucion,
            String nombreComercial, String nombreFiscal, ProveedorBiblioteca proveedorBiblioteca) {
        this.cif = cif;
        this.direccionWeb = direccionWeb;
        this.institucion = institucion;
        this.nombreComercial = nombreComercial;
        this.nombreFiscal = nombreFiscal;
        this.direccionesPostales = new ArrayList<DireccionPostal>();
        this.proveedorBiblioteca = proveedorBiblioteca;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Proveedor.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Proveedor.ID_SEQUENCE_NAME)
    @Column(name = Proveedor.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * /**
     *
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the cif
     */
    @Column(name = Proveedor.COLUMN_NAME_CIF, unique = true, length = 20)
    public String getCif() {
        return cif;
    }

    /**
     * @param cif
     *            the cif to set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * @return the nombreFiscal
     */
    @Column(name = Proveedor.COLUMN_NAME_NOMBRE_FISCAL, length = 255)
    public String getNombreFiscal() {
        return nombreFiscal;
    }

    /**
     * @param nombreFiscal
     *            the nombreFiscal to set
     */
    public void setNombreFiscal(String nombreFiscal) {
        this.nombreFiscal = nombreFiscal;
    }

    /**
     * @return the nombreComercial
     */
    @Column(name = Proveedor.COLUMN_NAME_NOMBRE_COMERCIAL, length = 255)
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * @param nombreComercial
     *            the nombreComercial to set
     */
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    /**
     * @return the direccionWeb
     */
    @Column(name = Proveedor.COLUMN_NAME_DIRECCION_WEB, length = 100)
    public String getDireccionWeb() {
        return direccionWeb;
    }

    /**
     * @param direccionWeb
     *            the direccionWeb to set
     */
    public void setDireccionWeb(String direccionWeb) {
        this.direccionWeb = direccionWeb;
    }

    /**
     * @return the institucion
     */
    @Column(name = Proveedor.COLUMN_NAME_INSTITUCION)
    public Boolean getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion
     *            the institucion to set
     */
    public void setInstitucion(Boolean institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the direccionesPostales
     */
    @OneToMany(mappedBy = DireccionPostal.PROPERTY_NAME_PROVEEDOR_FK, cascade = { CascadeType.ALL })
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @OrderBy("principal DESC")
    public List<DireccionPostal> getDireccionesPostales() {
        return direccionesPostales;
    }

    /**
     * @param direccionesPostales
     *            the direccionesPostales to set
     */
    public void setDireccionesPostales(List<DireccionPostal> direccionesPostales) {
        this.direccionesPostales = direccionesPostales;
    }

    /**
     * @return the proveedorBiblioteca
     */
    @OneToOne(cascade = { CascadeType.ALL }, targetEntity = org.librae.adquisicion.model.ProveedorBiblioteca.class)
    @JoinColumn(name = Proveedor.COLUMN_NAME_PROVEEDOR_BIBLIOTECA_FK)
    public ProveedorBiblioteca getProveedorBiblioteca() {
        return proveedorBiblioteca;
    }

    /**
     * @param proveedorBiblioteca
     *            the proveedorBiblioteca to set
     */
    public void setProveedorBiblioteca(ProveedorBiblioteca proveedorBiblioteca) {
        this.proveedorBiblioteca = proveedorBiblioteca;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((cif == null) ? 0 : cif.hashCode());
        result = prime * result
                + ((direccionWeb == null) ? 0 : direccionWeb.hashCode());
        result = prime
                * result
                + ((direccionesPostales == null) ? 0 : direccionesPostales
                        .hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((institucion == null) ? 0 : institucion.hashCode());
        result = prime * result
                + ((nombreComercial == null) ? 0 : nombreComercial.hashCode());
        result = prime * result
                + ((nombreFiscal == null) ? 0 : nombreFiscal.hashCode());
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
        if (!(obj instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) obj;
        if (cif == null) {
            if (other.cif != null) {
                return false;
            }
        } else if (!cif.equals(other.cif)) {
            return false;
        }
        if (direccionWeb == null) {
            if (other.direccionWeb != null) {
                return false;
            }
        } else if (!direccionWeb.equals(other.direccionWeb)) {
            return false;
        }
        if (direccionesPostales == null) {
            if (other.direccionesPostales != null) {
                return false;
            }
        } else if (!direccionesPostales.equals(other.direccionesPostales)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (institucion == null) {
            if (other.institucion != null) {
                return false;
            }
        } else if (!institucion.equals(other.institucion)) {
            return false;
        }
        if (nombreComercial == null) {
            if (other.nombreComercial != null) {
                return false;
            }
        } else if (!nombreComercial.equals(other.nombreComercial)) {
            return false;
        }
        if (nombreFiscal == null) {
            if (other.nombreFiscal != null) {
                return false;
            }
        } else if (!nombreFiscal.equals(other.nombreFiscal)) {
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
        return new ToStringBuilder(this).append(Proveedor.COLUMN_NAME_ID,
                this.id).append(Proveedor.COLUMN_NAME_CIF, this.cif).append(
                Proveedor.COLUMN_NAME_NOMBRE_FISCAL, this.nombreFiscal).append(
                Proveedor.COLUMN_NAME_NOMBRE_COMERCIAL, this.nombreComercial)
                .append(Proveedor.COLUMN_NAME_DIRECCION_WEB, this.direccionWeb)
                .append(Proveedor.COLUMN_NAME_INSTITUCION, this.institucion)
                .toString();
    }

}