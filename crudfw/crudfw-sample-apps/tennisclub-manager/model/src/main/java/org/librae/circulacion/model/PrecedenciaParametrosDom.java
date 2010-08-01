package org.librae.circulacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Precedencia de parámetros en las políticas de préstamo a domicilio.<br>
 * <br>
 * Cuando un parámetro figura con el mismo nombre en varias de las tablas
 * participantes en las políticas de préstamo a domicilio, y para la política
 * que se está aplicando más de uno de estos valores es NOT NULL, esta tabla
 * dice cual de las varias ocurrencias del parámetro se aplica.<br>
 * <br>
 * Tabla precargada. Datos para la precarga:<br>
 * 1.CIR_DOM_POLITICAS.Política de Préstamos.<br>
 * 2.EJEMPLARES_TIPOS.Tipo de ejemplar<br>
 * 3.LECTORES_TIPOS.Tipo de lector<br>
 * 4.BIBLIOTECAS.Sucursal<br>
 * 5.BIBLIOTECAS.Biblioteca<br>
 * 6.CIR_PARAMETROS.Parámetro de la aplicación (en su conjunto)<br>
 * <br>
 * Por ejemplo, para el parámetro N_MAX_PRESTAMOS_DOM se tienen dos valores en
 * tablas BIBLIOTECAS y LECTORES_TIPOS. Si las dos ocurrencias del parámetro son
 * NOT NULL para una política de préstamos, se aplica la ocurrencia de
 * LECTORES_TIPOS porque tiene más precedencia.
 *
 * @author asantamaría
 */
@Entity(name = PrecedenciaParametrosDom.ENTITY_NAME)
@Table(name = PrecedenciaParametrosDom.TABLE_NAME)
public class PrecedenciaParametrosDom extends BaseObject {

    /**
     * BaseObject is Serializable, so PrecedenciaParametrosDom needs a Serial
     * Version UID
     */
    private static final long   serialVersionUID                        = 6245974682779963953L;

    public static final String  ENTITY_NAME                             = "org.librae.circulacion.model.PrecedenciaParametrosDom";
    public static final String  TABLE_NAME                              = "CIR_DOM_PRECEDENCIA_PARAMETROS";
    public static final String  ID_GENERATOR_NAME                       = "generator_dom_precedencia_parametros";
    private static final String ID_SEQUENCE_NAME                        = "SEQ_DOM_PRECEDENCIA_PARAMETROS";
    public static final String  COLUMN_NAME_ID                          = "X_DOM_PRECEDENCIA_PARAMETRO";
    public static final String  COLUMN_NAME_NOMBRE_TABLA                = "T_NOMBRE_TABLA";
    public static final String  COLUMN_NAME_NOMBRE_TABLA_ALT            = "T_NOMBRE_TABLA_ALT";
    public static final String  COLUMN_NAME_NOMBRE_PRECEDENCIA_RELATIVA = "N_PRECEDENCIA_RELATIVA";

    public static final String  PROPTY_NAME_ID                          = "id";
    public static final String  PROPTY_NAME_NOMBRE_TABLA                = "nombreTabla";
    public static final String  PROPTY_NAME_NOMBRE_TABLA_ALT            = "nombreTablaAlt";
    public static final String  PROPTY_NAME_NOMBRE_PRECEDENCIA_RELATIVA = "precedenciaRelativa";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Nombre de tabla con parámetros de circulación. Ver notas a nivel de RA
     */
    private String              nombreTabla;

    /**
     * NOmbre de tabla en lenguaje entendible por el usuario. Ver notas a nivel
     * de RA
     */
    private String              nombreTablaAlt;

    /**
     * Precedencia relativa entre las tablas implicadas en la política de
     * préstamos<br>
     * 1 es la precedencia más alta, 2 la siguiente, y así sucesivamente
     */
    private Integer             precedenciaRelativa;

    /**
     * getter & setters
     */

    protected PrecedenciaParametrosDom() {
        super();
    }

    public PrecedenciaParametrosDom(String nombreTabla) {
        super();
        this.nombreTabla = nombreTabla;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = PrecedenciaParametrosDom.COLUMN_NAME_ID)
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
     * @return the nombreTabla
     */
    @Column(name = PrecedenciaParametrosDom.COLUMN_NAME_NOMBRE_TABLA,length=80)
    public String getNombreTabla() {
        return nombreTabla;
    }

    /**
     * @param nombreTabla
     *            the nombreTabla to set
     */
    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    /**
     * @return the nombreTablaAlt
     */
    @Column(name = PrecedenciaParametrosDom.COLUMN_NAME_NOMBRE_TABLA_ALT)
    public String getNombreTablaAlt() {
        return nombreTablaAlt;
    }

    /**
     * @param nombreTablaAlt
     *            the nombreTablaAlt to set
     */
    public void setNombreTablaAlt(String nombreTablaAlt) {
        this.nombreTablaAlt = nombreTablaAlt;
    }

    /**
     * @return the precedenciaRelativa
     */
    @Column(name = PrecedenciaParametrosDom.COLUMN_NAME_NOMBRE_PRECEDENCIA_RELATIVA)
    public Integer getPrecedenciaRelativa() {
        return precedenciaRelativa;
    }

    /**
     * @param precedenciaRelativa
     *            the precedenciaRelativa to set
     */
    public void setPrecedenciaRelativa(Integer precedenciaRelativa) {
        this.precedenciaRelativa = precedenciaRelativa;
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
        if (!(obj instanceof PrecedenciaParametrosDom)) {
            return false;
        }

        final PrecedenciaParametrosDom other = (PrecedenciaParametrosDom) obj;

        if (!this.getId().equals(other.getId())) {
            return false;
        }
        if (!this.getNombreTabla().equals(other.getNombreTabla())) {
            return false;
        }
        if (!this.getNombreTablaAlt().equals(other.getNombreTablaAlt())) {
            return false;
        }
        if (!this.getPrecedenciaRelativa().equals(
                other.getPrecedenciaRelativa())) {
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
        result += ((id == null) ? 0 : this.getId().hashCode());
        result = prime
                * result
                + ((this.getNombreTabla() == null) ? 0 : this.getNombreTabla()
                        .hashCode());
        result = prime
                * result
                + ((this.getNombreTablaAlt() == null) ? 0 : this
                        .getNombreTablaAlt().hashCode());
        result = prime
                * result
                + ((this.getPrecedenciaRelativa() == null) ? 0 : this
                        .getPrecedenciaRelativa().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(PROPTY_NAME_ID, this.getId())
                .append(PROPTY_NAME_NOMBRE_TABLA, this.getNombreTabla())
                .append(PROPTY_NAME_NOMBRE_TABLA_ALT, this.getNombreTablaAlt())
                .append(PROPTY_NAME_NOMBRE_PRECEDENCIA_RELATIVA,
                        this.getPrecedenciaRelativa()).toString();
    }

}