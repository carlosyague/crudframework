/**
 *
 */
package org.librae.estadisticas.model;

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
 * @author jVillegas
 */
@Entity(name = PlantillaInforme.NAME_ENTITY)
@Table(name = PlantillaInforme.TABLE_NAME)
public class PlantillaInforme extends BaseObject {

    /**
     *
     */
    private static final long   serialVersionUID             = 1L;

    public static final String  NAME_ENTITY                  = "org.librae.estadisticas.model.PlantillaInforme";
    public static final String  TABLE_NAME                   = "EIL_PLANTILLAS_X_INFORME";
    private static final String ID_GENERATOR_NAME            = "generator_eil_plantillas_x_informe";
    private static final String ID_SEQUENCE_NAME             = "SEQ_EIL_PLANTILLAS_X_INFORME";
    public static final String  COLUMN_NAME_ID               = "X_PLANTILLA_INFORME";
    public static final String  COLUMN_NAME_NOMBRE           = "T_NOMBRE";
    public static final String  COLUMN_NAME_INFORME          = "EIL_INFORME_X_INFORME";
    public static final String  COLUMN_NAME_ACTIVO           = "L_ACTIVO";
    public static final String  COLUMN_NAME_DEFECTO          = "L_DEFECTO";
    public static final String  COLUMN_NAME_NOMBRE_ARCHIVO   = "T_NOMBRE_ARCHIVO";
    public static final String  COLUMN_NAME_NOTAS            = "T_NOTAS";

    public static final String  PROPERTY_NAME_ID             = "id";
    public static final String  PROPERTY_NAME_NOMBRE         = "nombre";
    public static final String  PROPERTY_NAME_INFORME_FK     = "informe";
    public static final String  PROPERTY_NAME_ACTIVO         = "activo";
    public static final String  PROPERTY_NAME_DEFECTO        = "defecto";
    public static final String  PROPERTY_NAME_NOMBRE_ARCHIVO = "nombreArchivo";
    public static final String  PROPERTY_NAME_NOTAS          = "notas";

    /**
     * Clave primaria.
     */
    private Long                id;
    /**
     * Referencia al informe.
     */
    private Informe             informe;
    /**
     * Especifica si la plantilla está activa o no.
     */
    private Boolean             activo;
    /**
     * Especifica si la plantilla va a estar marcada por defecto o no.
     */
    private Boolean             defecto;
    /**
     * Nombre descriptivo de la plantilla.
     */
    private String              nombre;
    /**
     * Nombre completo del archivo de la plantilla.
     */
    private String              nombreArchivo;
    /**
     * Notas de la plantilla.
     */
    private String              notas;


    /**
     * Constructor sin parametros
     */
    protected PlantillaInforme() {

    }

    /**
     * @param informeFinal
     * @param activoFinal
     * @param defectoFinal
     * @param nombreFinal
     * @param nombreArchivoFinal
     * @param notasFinal
     */
    public PlantillaInforme(Informe informeFinal, Boolean activoFinal, Boolean defectoFinal,
            String nombreFinal, String nombreArchivoFinal, String notasFinal, Boolean plantillaPorDefecto) {
        super();
        this.informe = informeFinal;
        this.activo = activoFinal;
        this.defecto = defectoFinal;
        this.nombre = nombreFinal;
        this.nombreArchivo = nombreArchivoFinal;
        this.notas = notasFinal;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = PlantillaInforme.COLUMN_NAME_ID)
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
     * @return the informe
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.estadisticas.model.Informe.class)
    @JoinColumn(name = PlantillaInforme.COLUMN_NAME_INFORME)
    public Informe getInforme() {
        return informe;
    }

    /**
     * @param informe
     *            the informe to set
     */
    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    /**
     * @return the activo
     */
    @Column(name = PlantillaInforme.COLUMN_NAME_ACTIVO)
    public Boolean getActivo() {
        return activo;
    }

    /**
     * @param activo
     *            the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the defecto
     */
    @Column(name = PlantillaInforme.COLUMN_NAME_DEFECTO,nullable=false)
    public Boolean getDefecto() {
        return defecto;
    }

    /**
     * @param defecto
     *            the defecto to set
     */
    public void setDefecto(Boolean defecto) {
        this.defecto = defecto;
    }

    /**
     * @return the nombre
     */
    @Column(name = PlantillaInforme.COLUMN_NAME_NOMBRE, length = 255)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombreArchivo
     */
    @Column(name = PlantillaInforme.COLUMN_NAME_NOMBRE_ARCHIVO, length = 255)
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo
     *            the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the notas
     */
    @Column(name = PlantillaInforme.COLUMN_NAME_NOTAS, length = 255)
    public String getNotas() {
        return notas;
    }

    /**
     * @param notas
     *            the notas to set
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlantillaInforme)) {
            return false;
        }
        final PlantillaInforme other = (PlantillaInforme) o;
        if (activo == null) {
            if (other.activo != null) {
                return false;
            }
        } else if (!activo.equals(other.activo)) {
            return false;
        }
        if (defecto == null) {
            if (other.defecto != null) {
                return false;
            }
        } else if (!defecto.equals(other.defecto)) {
            return false;
        }
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (nombreArchivo == null) {
            if (other.nombreArchivo != null) {
                return false;
            }
        } else if (!nombreArchivo.equals(other.nombreArchivo)) {
            return false;
        }
        if (notas == null) {
            if (other.notas != null) {
                return false;
            }
        } else if (!notas.equals(other.notas)) {
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((defecto == null) ? 0 : defecto.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((activo == null) ? 0 : activo.hashCode());
        result = prime * result + ((nombreArchivo == null) ? 0 : nombreArchivo.hashCode());
        result = prime * result + ((notas == null) ? 0 : notas.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(PlantillaInforme.COLUMN_NAME_ID, id)
                .append(PlantillaInforme.COLUMN_NAME_INFORME, informe).append(
                        PlantillaInforme.COLUMN_NAME_ACTIVO, activo).append(
                        PlantillaInforme.COLUMN_NAME_DEFECTO, defecto).append(
                        PlantillaInforme.COLUMN_NAME_NOMBRE, nombre).append(
                        PlantillaInforme.COLUMN_NAME_NOMBRE_ARCHIVO, nombreArchivo).append(
                        PlantillaInforme.COLUMN_NAME_NOTAS, notas)
                .toString();
    }




}
