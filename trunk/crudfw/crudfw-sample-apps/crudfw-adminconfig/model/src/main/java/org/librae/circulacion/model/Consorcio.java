package org.librae.circulacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Consorcios de bibliotecas para préstamo en red.<br>
 * Un consorcio es una agrupación de bibliotecas en la que todas las bibliotecas
 * participantes acatan una misma política de préstamos en red.<br>
 * De un consorcio pueden formar parte bibliotecas con distinto SIGB (por tanto,
 * no registradas en esta aplicación).
 * 
 * @author asantamaria
 */
@Entity(name = Consorcio.ENTITY_NAME)
@Table(name = Consorcio.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Consorcio extends SpringRemotableLazyEntity<Consorcio> {

    /**
     * BaseObject is Serializable, so Consorcio needs a Serial Version UID
     */
    private static final long   serialVersionUID               = -5228076118887814317L;

    public static final String  ENTITY_NAME                    = "org.librae.adminconfig.model.Consorcio";
    public static final String  TABLE_NAME                     = "ADM_CONSORCIOS";
    public static final String  ID_GENERATOR_NAME              = "generator_adm_consorcios";
    private static final String ID_SEQUENCE_NAME               = "SEQ_ADM_CONSORCIOS";
    public static final String  COLUMN_NAME_ID                 = "X_CONSORCIO";
    public static final String  COLUMN_NAME_CODIGO             = "C_CONSORCIO";
    public static final String  COLUMN_NAME_NOMBRE             = "T_CONSORCIO";
    public static final String  COLUMN_NAME_DESCRIPCION        = "T_CONSORCIO_ALT";
    public static final String  COLUMN_NAME_URL_ADMIN_APRC     = "T_URL_ADMIN_APRC";
    public static final String  COLUMN_NAME_URL_GESTION_APRC   = "T_URL_GESTION_APRC";
    public static final String  COLUMN_NAME_DIAS_ANTES_FINAL   = "N_DIAS_ANTES_FINAL";
    public static final String  COLUMN_NAME_DIAS_PREST         = "N_DIAS_PREST";
    public static final String  COLUMN_NAME_DIAS_RENOVACION    = "N_DIAS_RENOVACION";
    public static final String  COLUMN_NAME_MAX_PREST_RED      = "N_MAX_PREST_RED";
    public static final String  COLUMN_NAME_MAX_RENOVACIONES   = "N_MAX_RENOVACIONES";

    public static final String  PROPERTY_NAME_ID               = "id";
    public static final String  PROPERTY_NAME_CODIGO           = "codigo";
    public static final String  PROPERTY_NAME_NOMBRE           = "nombre";
    public static final String  PROPERTY_NAME_DESCRIPCION      = "descripcion";
    public static final String  PROPERTY_NAME_URL_ADMIN_APRC   = "UrlAdminAprc";
    public static final String  PROPERTY_NAME_URL_GESTION_APRC = "UrlGestionAprc";
    public static final String  PROPERTY_NAME_DIAS_ANTES_FINAL = "DiasAntesFinal";
    public static final String  PROPERTY_NAME_DIAS_PREST       = "DiasPrest";
    public static final String  PROPERTY_NAME_DIAS_RENOVACION  = "DiasRenovacion";
    public static final String  PROPERTY_NAME_MAX_PREST_RED    = "MaxPrestRed";
    public static final String  PROPERTY_NAME_MAX_RENOVACIONES = "MaxRenovaciones";

    /**
     * Identificador interno del parámetro
     */
    private Long                id;

    /**
     * Código de consorcio asignado por el administrador.<br>
     * Unico para todos los consorcios de la tabla.
     */
    private String              codigo;

    /**
     * Descripción de consorcio
     */
    private String              nombre;

    /**
     * Descripción ampliada o alternativa de consorcio
     */
    private String              descripcion;

    /**
     * URL de la aplicación APRC (Aplicación de Préstamo en Red Consorciado) del
     * consorcio para realizar tareas de administración: alta de nuevas
     * bibliotecas en el consorcio, modificación de la política de préstamos del
     * consorcio, ...<br>
     * El valor de esta columna y de T_URL_GESTION_APRC reprsentan a la
     * aplicación APRC de esta columna
     */
    private String              UrlAdminAprc;

    /**
     * URL de la aplicación APRC (Aplicación de Préstamo en Red Consorciado) del
     * consorcio para realizar préstamos, renovaciones, ... (gestión)<br>
     * El valor de esta columna y de la columna T_URL_ADMIN_APRC representan a
     * la aplicación APRC del consorcio
     */
    private String              UrlGestionAprc;

    /**
     * Desde cuantos días antes de la fecha de devolución se puede renovar un
     * préstamo. Valor por defecto: 2 Ejemplo para la primera renovación: Fecha
     * del préstamo: f1 Fecha de devolución según política: f1 + N_DIAS_PREST =
     * f2 El préstamo se puede renovar entre las fechas f2 - N_DIAS_ANTES_FINAL
     * y f2
     */
    private Long                DiasAntesFinal;

    /**
     * Días de duración que se aplican a un préstamo en red realizado con la
     * aplicación APRC de este consorcio.<br>
     * Valor por defecto: 5
     */
    private Long                DiasPrest;

    /**
     * Por cuantos días se renueva un préstamo en cada renovación.<br>
     * Valor por defecto: 3
     */
    private Long                DiasRenovacion;

    /**
     * Número máximo de préstamos que un lector puede realizar entre todas las
     * bibliotecas del consorcio, incluyendo los préstamos realizados en la
     * suya.<br>
     * Valor por defecto: 10
     */
    private Long                MaxPrestRed;

    /**
     * Máximo número de veces que se puede renovar un préstamo en red.<br>
     * Valor por defecto: 1
     */
    private Long                MaxRenovaciones;

    protected Consorcio() {
        super();
    }

    public Consorcio(String codigo, String nombre) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = Consorcio.COLUMN_NAME_ID)
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
    @Column(name = Consorcio.COLUMN_NAME_CODIGO, length = 40)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    @Column(name = Consorcio.COLUMN_NAME_NOMBRE, length = 80)
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
     * @return the descripcion
     */
    @Column(name = Consorcio.COLUMN_NAME_DESCRIPCION)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the urlAdminAprc
     */
    @Column(name = Consorcio.COLUMN_NAME_URL_ADMIN_APRC)
    public String getUrlAdminAprc() {
        return UrlAdminAprc;
    }

    /**
     * @param urlAdminAprc
     *            the urlAdminAprc to set
     */
    public void setUrlAdminAprc(String urlAdminAprc) {
        UrlAdminAprc = urlAdminAprc;
    }

    /**
     * @return the urlGestionAprc
     */
    @Column(name = Consorcio.COLUMN_NAME_URL_GESTION_APRC)
    public String getUrlGestionAprc() {
        return UrlGestionAprc;
    }

    /**
     * @param urlGestionAprc
     *            the urlGestionAprc to set
     */
    public void setUrlGestionAprc(String urlGestionAprc) {
        UrlGestionAprc = urlGestionAprc;
    }

    /**
     * @return the diasAntesFinal
     */
    @Column(name = Consorcio.COLUMN_NAME_DIAS_ANTES_FINAL)
    public Long getDiasAntesFinal() {
        return DiasAntesFinal;
    }

    /**
     * @param diasAntesFinal
     *            the diasAntesFinal to set
     */
    public void setDiasAntesFinal(Long diasAntesFinal) {
        DiasAntesFinal = diasAntesFinal;
    }

    /**
     * @return the diasPrest
     */
    @Column(name = Consorcio.COLUMN_NAME_DIAS_PREST)
    public Long getDiasPrest() {
        return DiasPrest;
    }

    /**
     * @param diasPrest
     *            the diasPrest to set
     */
    public void setDiasPrest(Long diasPrest) {
        DiasPrest = diasPrest;
    }

    /**
     * @return the diasRenovacion
     */
    @Column(name = Consorcio.COLUMN_NAME_DIAS_RENOVACION)
    public Long getDiasRenovacion() {
        return DiasRenovacion;
    }

    /**
     * @param diasRenovacion
     *            the diasRenovacion to set
     */
    public void setDiasRenovacion(Long diasRenovacion) {
        DiasRenovacion = diasRenovacion;
    }

    /**
     * @return the maxPrestRed
     */
    @Column(name = Consorcio.COLUMN_NAME_MAX_PREST_RED)
    public Long getMaxPrestRed() {
        return MaxPrestRed;
    }

    /**
     * @param maxPrestRed
     *            the maxPrestRed to set
     */
    public void setMaxPrestRed(Long maxPrestRed) {
        MaxPrestRed = maxPrestRed;
    }

    /**
     * @return the maxRenovaciones
     */
    @Column(name = Consorcio.COLUMN_NAME_MAX_RENOVACIONES)
    public Long getMaxRenovaciones() {
        return MaxRenovaciones;
    }

    /**
     * @param maxRenovaciones
     *            the maxRenovaciones to set
     */
    public void setMaxRenovaciones(Long maxRenovaciones) {
        MaxRenovaciones = maxRenovaciones;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Consorcio)) {
            return false;
        }

        final Consorcio other = (Consorcio) obj;

        if (getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (getCodigo() != null
                && !getCodigo().equals(other.getCodigo())) {
            return false;
        }

        return true;

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime
                * result
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(PROPERTY_NAME_ID, getId())

        .append(PROPERTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(PROPERTY_NAME_NOMBRE,
                (getNombre() == null) ? "" : getNombre().toString())

        .append(
                PROPERTY_NAME_DESCRIPCION,
                (getDescripcion() == null) ? "" : getDescripcion()
                        .toString())

        .toString();
    }

    @Override
    public Consorcio newInstance() {

        return new Consorcio();
    }

}