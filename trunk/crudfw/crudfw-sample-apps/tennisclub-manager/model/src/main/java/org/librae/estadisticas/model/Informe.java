package org.librae.estadisticas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.librae.adminconfig.model.Modulo;
import org.librae.common.model.BaseObject;

@Entity(name = Informe.NAME_ENTITY)
@Table(name = Informe.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Informe extends BaseObject {

    /**
     *
     */
    private static final long      serialVersionUID         = 1L;

    public static final String     NAME_ENTITY              = "org.librae.estadisticas.model.Informe";
    public static final String     TABLE_NAME               = "EIL_INFORMES";
    private static final String    ID_GENERATOR_NAME        = "generator_eil_informes";
    private static final String    ID_SEQUENCE_NAME         = "SEQ_EIL_INFORMES";
    public static final String     COLUMN_NAME_ID           = "X_INFORME";
    public static final String     COLUMN_NAME_NOMBRE       = "T_NOMBRE";
    public static final String     COLUMN_NAME_MODULO       = "ADM_MODULOS_X_MODULO";

    public static final String     PROPERTY_NAME_ID         = "id";
    public static final String     PROPERTY_NAME_NOMBRE     = "nombre";

    public static final String     PROPERTY_NAME_MODULO     = "modulo";
    public static final String     PROPERTY_NAME_FORMATOS   = "formatos";
    public static final String     PROPERTY_NAME_PLANTILLAS = "plantillas";
    public static final String     PROPERTY_NAME_PARAMETROS = "parametros";

    /**
     * Clave primaria.
     */
    private Long                   id;
    /**
     * Indica el modulo al que pertenece el informe.
     */
    private Modulo                 modulo;

    /**
     * Nombre del informe.
     */
    private String                 nombre;
    /**
     * Plantillas del informe.
     */
    private List<PlantillaInforme> plantillas;
    /**
     * Formatos del informe.
     */
    private List<FormatoInforme>   formatos;
    /**
     * Parametros del informe.
     */
    private List<ParametroInforme> parametros;

    /**
     * Constructor sin parametros
     */
    protected Informe() {

    }

    /**
     * @param modulo
     * @param formatoPorDefecto
     * @param plantillaPorDefecto
     * @param nombre
     */
    public Informe(String nombreFinal) {
        super();
        this.modulo = null;
        this.nombre = nombreFinal;
        this.plantillas = null;
        this.formatos = null;
        this.parametros = null;
    }

    /**
     * @return the id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Informe.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the modulo.
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Modulo.class)
    @JoinColumn(name = Informe.COLUMN_NAME_MODULO)
    public Modulo getModulo() {
        return modulo;
    }

    /**
     * @param moduloFinal
     *            the modulo to set.
     */
    public void setModulo(Modulo moduloFinal) {
        this.modulo = moduloFinal;
    }

    /**
     * @return the nombre
     */
    @Column(name = Informe.COLUMN_NAME_NOMBRE, nullable = true, length = 255)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombreFinal
     *            the nombre to set.
     */
    public void setNombre(String nombreFinal) {
        this.nombre = nombreFinal;
    }

    /**
     * @return the plantillas.
     */
    @OneToMany(mappedBy = PlantillaInforme.PROPERTY_NAME_INFORME_FK, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @OrderBy("defecto DESC")
    public List<PlantillaInforme> getPlantillas() {
        return plantillas;
    }

    /**
     * Devuelve un elemento concreto de la lista de plantillas informe
     * @param indice en la lista de plantillas
     * @return <code>PlantillaInforme</code>
     */
    @Transient
    public PlantillaInforme getPlantillaDefault() {
        PlantillaInforme plantillaDefault = null;

        if (plantillas != null && !plantillas.isEmpty())
        {
            plantillaDefault = plantillas.get(0);
        }

        return plantillaDefault;
    }

    /**
     * @param plantillasFinal
     *            the plantillas to set.
     */
    public void setPlantillas(List<PlantillaInforme> plantillasFinal) {
        this.plantillas = plantillasFinal;
    }

    /**
     * @return the formatos.
     */
    @OneToMany(mappedBy = FormatoInforme.PROPERTY_NAME_INFORME_FK, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @OrderBy("formatoPorDefecto DESC")
    public List<FormatoInforme> getFormatos() {
        return formatos;
    }

    /**
     * @param formatosFinal
     *            the formatos to set.
     */
    public void setFormatos(List<FormatoInforme> formatosFinal) {
        this.formatos = formatosFinal;
    }

    /**
     * @return the parametros.
     */
    @OneToMany(mappedBy = ParametroInforme.PROPERTY_NAME_INFORME_FK, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<ParametroInforme> getParametros() {
        return parametros;
    }

    /**
     * @param parametrosFinal
     *            the parametros to set.
     */
    public void setParametros(List<ParametroInforme> parametrosFinal) {
        this.parametros = parametrosFinal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Informe)) {
            return false;
        }
        final Informe other = (Informe) obj;
        if (modulo == null) {
            if (other.modulo != null) {
                return false;
            }
        } else if (!modulo.equals(other.modulo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Informe.COLUMN_NAME_ID, id)
                .append(Informe.COLUMN_NAME_MODULO, modulo).append(
                        Informe.COLUMN_NAME_NOMBRE, nombre).toString();
    }

}
