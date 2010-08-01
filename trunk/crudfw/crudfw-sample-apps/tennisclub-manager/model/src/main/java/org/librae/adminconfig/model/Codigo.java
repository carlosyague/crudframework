package org.librae.adminconfig.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.common.model.BaseObject;

/**
 * Códigos definidos por el usuario, aplicables a grupos, bibliotecas y
 * sucursales<br>
 * <br>
 * Un código es un criterio para clasificar grupos de bibliotecas, bibliotecas o
 * sucursales, a efectos estadísticos y de listados. Un código es un conjunto de
 * valores. A cada grupo de bibliotecas, biblioteca o sucursal se le asigna un
 * valor del conjunto a modo de etiqueta.<br>
 * Cada código es aplicable o bien a grupos de bibliotecas, o bien a bibliotecas
 * o bien a sucursales, o bien a una combinación de estos tres.<br>
 * <br>
 * En esta tabla se definen los códigos, mientras que en BIBLIOCODV se definen
 * los valores de cada código.<br>
 * <br>
 * Ab*NET limita los códigos a dos por biblioteca y dos por sucursal. En nuestra
 * implementación no existe limitación.
 *
 * @author asantamaría
 */
@Entity(name = Codigo.ENTITY_NAME)
@Table(name = Codigo.TABLE_NAME)
public class Codigo extends BaseObject {

    /**
     * BaseObject is Serializable, so Codigo needs a Serial Version UID
     */
    private static final long   serialVersionUID        = -590281157172739844L;

    public static final String  ENTITY_NAME             = "org.librae.adminconfig.model.Codigo";
    public static final String  TABLE_NAME              = "ADM_CODIGOS";
    public static final String  ID_GENERATOR_NAME       = "generator_adm_codigos";
    private static final String ID_SEQUENCE_NAME        = "SEQ_ADM_CODIGOS";
    public static final String  COLUMN_NAME_ID          = "X_CODIGO";
    public static final String  COLUMN_NAME_NOMBRE      = "T_CODIGO";
    public static final String  COLUMN_NAME_CODIGO      = "C_CODIGO";
    public static final String  COLUMN_NAME_APLICA_GBS  = "D_APLICA_GBS";
    public static final String  COLUMN_NAME_TIPO_CODIGO = "TIP_X_TIPO_CODIGO";

    public static final String  PROPTY_NAME_ID          = "id";
    public static final String  PROPTY_NAME_NOMBRE      = "nombre";
    public static final String  PROPTY_NAME_CODIGO      = "codigo";
    public static final String  PROPTY_NAME_APLICA_GBS  = "aplicaGBS";
    public static final String  PROPTY_NAME_TIPO_CODIGO = "tipoCodigo";

    public static final String  APLICABLE_A_GRUPOS      = "G";
    public static final String  APLICABLE_A_BIBLIOTECAS = "B";
    public static final String  APLICABLE_A_SUCURSALES  = "S";

    /**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila. Número secuencial
     */
    private Long                id;

    /**
     * Nombre del código (conjunto de valores) asignado por el usuario
     */
    private String              nombre;

    /**
     * Código identificativo asignado por el usuario
     */
    private String              codigo;

    /**
     * Indica a que tipo de entidades de BIBLIO es aplicable este código (a
     * grupos de bibliotecas, a bibliotecas o/y a sucursales).<br>
     * Valor: cualquier combinación de los caracteres G, B y S, sin que se
     * repita ninguno.
     */
    private String              aplicaGBS;

    /**
     * Tipo de codigo.
     */
    private TipoCodigo          tipoCodigo;

    /**
     * Listado de permisos
     */
    private List<ValorCodigo>   valores                 = new ArrayList<ValorCodigo>();

    /**
     * Constructor.
     */
    protected Codigo() {
        super();
    }

    /**
     * Constructor.
     */
    public Codigo(final String codigo) {
        super();
        this.codigo = codigo;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Codigo.ID_GENERATOR_NAME)
    @SequenceGenerator(name = Codigo.ID_GENERATOR_NAME, sequenceName = Codigo.ID_SEQUENCE_NAME)
    @Column(name = Codigo.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    @Column(name = Codigo.COLUMN_NAME_NOMBRE,length=80)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the codigo
     */
    @Column(name = Codigo.COLUMN_NAME_CODIGO, nullable = false,length=40)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the aplicaGBS
     */
    @Column(name = Codigo.COLUMN_NAME_APLICA_GBS, nullable = true,length=3)
    public String getAplicaGBS() {
        return aplicaGBS;
    }

    /**
     * @param aplicaGBS
     *            the aplicaGBS to set
     */
    public void setAplicaGBS(final String aplicaGBS) {
        this.aplicaGBS = aplicaGBS;
    }

    /**
     * @return
     */
    @ManyToOne(targetEntity = TipoCodigo.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = Codigo.COLUMN_NAME_TIPO_CODIGO, nullable = false)
    public TipoCodigo getTipoCodigo() {
        return tipoCodigo;
    }

    /**
     * @param tipoCodigo
     */
    public void setTipoCodigo(final TipoCodigo tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    /**
     * @return
     */
    @OneToMany(targetEntity = ValorCodigo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = ValorCodigo.COLUMN_NAME_CODIGO)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<ValorCodigo> getValores() {
        return valores;
    }

    /**
     * @param valores
     */
    public void setValores(final List<ValorCodigo> valores) {
        this.valores = valores;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(final Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Codigo)) {
            return false;
        }

        final Codigo other = (Codigo) obj;

        if (getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (getCodigo() != null && !getCodigo().equals(other.getCodigo())) {
            return false;
        }

        return true;

    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());

        result = prime * result
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)

        .append(Codigo.PROPTY_NAME_ID, getId())

        .append(Codigo.PROPTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(Codigo.PROPTY_NAME_NOMBRE,
                (getNombre() == null) ? "" : getNombre().toString())

        .append(Codigo.PROPTY_NAME_APLICA_GBS,
                (getAplicaGBS() == null) ? "" : getAplicaGBS().toString())

        .toString();
    }

}