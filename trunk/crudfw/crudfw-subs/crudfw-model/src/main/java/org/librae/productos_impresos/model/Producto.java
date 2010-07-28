/**
 *
 */
package org.librae.productos_impresos.model;

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
import org.librae.adminconfig.model.Biblioteca;
import org.librae.common.model.parampoliticas.AbstractParamPolProductosImpresos;

/**
 * Bean para almacenar un Producto Impreso
 *
 * @author amDelcampo
 */
/**
 * @author amDelcampo
 */
@Entity(name = Producto.NAME_ENTITY)
@Table(name = Producto.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(mutable = false)
public class Producto extends AbstractParamPolProductosImpresos {

    /**
     * BaseObject es Serializable, por lo tanto Producto necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID            = 5287735351265221538L;

    public static final String  NAME_ENTITY                 = "org.librae.productos_impresos.model.Producto";
    public static final String  TABLE_NAME                  = "PRD_IMPRESOS";
    private static final String ID_GENERATOR_NAME           = "generator_prd_impresos";
    private static final String ID_SEQUENCE_NAME            = "SEQ_PRD_IMPRESOS";
    public static final String  COLUMN_NAME_ID              = "X_PRODUCTO";
    public static final String  COLUMN_NAME_DESCRIPCION     = "T_PRODUCTO";
    public static final String  COLUMN_NAME_TIPOPRODUCTO    = "N_TIPOPRODUCTO";
    public static final String  COLUMN_NAME_PLANTILLA       = "T_PLANTILLA";
    public static final String  COLUMN_NAME_POSICION        = "N_POSICION";
    public static final String  COLUMN_NAME_TIPOIMPRESION   = "C_TIPOIMPRESION";
    public static final String  COLUMN_NAME_BIBLIOTECA_FK   = "BIB_X_BIBLIOTECA";
    public static final String  COLUMN_NAME_TIPOCODBARRAS   = "N_TIPOCODBARRAS";

    public static final String  PROPERTY_NAME_ID            = "id";
    public static final String  PROPERTY_NAME_DESCRIPCION   = "descripcion";
    public static final String  PROPERTY_NAME_TIPOPRODUCTO  = "tipoProducto";
    public static final String  PROPERTY_NAME_PLANTILLA     = "plantilla";
    public static final String  PROPERTY_NAME_POSICION      = "posicion";
    public static final String  PROPERTY_NAME_TIPOIMPRESION = "tipoImpresion";
    public static final String  PROPERTY_NAME_BIBLIOTECA_FK = "biblioteca";
    public static final String  PROPERTY_NAME_TIPOCODBARRAS = "tipoCodBarras";

    /**
     * clave primaria.
     */
    private Long                id;
    /**
     * Descripción del producto impreso.
     */
    private String              descripcion;
    /**
     * Tipo de producto: carnet, tejuelo, carta reclamación, etc
     */
    private int                 tipoProducto;
    /**
     * Descripción de la plantilla asignada a cada producto.
     */
    private String              plantilla;
    /**
     * Posición de la impresión.
     */
    private Long                posicion;
    /**
     * tipo de impresión: ODT, PDF, impresión directa.
     */
    private String              tipoImpresion;
    /**
     * Biblioteca.
     */
    private Biblioteca          biblioteca;
    /**
     * Tipo codigo barras.
     */
    private Long          tipoCodBarras;

    /**
     * Constructor sin parámetros.
     */
    public Producto() {
        super();
    }

    /**
     * Constructor con parámetros.
     *
     * @param tipoProducto
     *            Tipo de producto.
     */
    public Producto(int tipoProductoFinal) {
        super();
        tipoProducto = tipoProductoFinal;
    }

    /**
     * Instancia de Producto por tipo.
     *
     * @param tipoProducto
     *            Tipo de producto.
     */
    public static Producto getInstance(int tipo) {
        return new Producto(tipo);
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Producto.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Producto.ID_SEQUENCE_NAME)
    @Column(name = Producto.COLUMN_NAME_ID)
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
     * @return the descripcion
     */
    @Column(name = Producto.COLUMN_NAME_DESCRIPCION, unique = true, length = 255)
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
     * @return the tipoProducto
     */
    @Column(name = Producto.COLUMN_NAME_TIPOPRODUCTO)
    public int getTipoProducto() {
        return tipoProducto;
    }

    /**
     * @param tipoProducto
     *            the tipoProducto to set
     */
    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     * @return the plantilla
     */
    @Column(name = Producto.COLUMN_NAME_PLANTILLA, length = 255)
    public String getPlantilla() {
        return plantilla;
    }

    /**
     * @param plantilla
     *            the plantilla to set
     */
    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    /**
     * @return the posicion
     */
    @Column(name = Producto.COLUMN_NAME_POSICION)
    public Long getPosicion() {
        return posicion;
    }

    /**
     * @param posicion
     *            the posicion to set
     */
    public void setPosicion(Long posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the tipoImpresion
     */
    @Column(name = Producto.COLUMN_NAME_TIPOIMPRESION, length = 40)
    public String getTipoImpresion() {
        return tipoImpresion;
    }

    /**
     * @param tipoImpresion
     *            the tipoImpresion to set
     */
    public void setTipoImpresion(String tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = Producto.COLUMN_NAME_BIBLIOTECA_FK)
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca
     *            the biblioteca to set
     */
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }



    /**
     * @return the tipoCodBarras
     */
    @Column(name = Producto.COLUMN_NAME_TIPOCODBARRAS)
    public Long getTipoCodBarras() {
        return tipoCodBarras;
    }

    /**
     * @param tipoCodBarras the tipoCodBarras to set
     */
    public void setTipoCodBarras(Long tipoCodBarras) {
        this.tipoCodBarras = tipoCodBarras;
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
        if (!(obj instanceof Producto)) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (descripcion == null) {
            if (other.descripcion != null) {
                return false;
            }
        } else if (!descripcion.equals(other.descripcion)) {
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
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.librae.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(Producto.COLUMN_NAME_ID, id)
                .append(Producto.COLUMN_NAME_DESCRIPCION, descripcion).append(
                        Producto.COLUMN_NAME_TIPOPRODUCTO, tipoProducto)
                .append(Producto.COLUMN_NAME_PLANTILLA, plantilla).append(
                        Producto.COLUMN_NAME_POSICION, posicion).append(
                        Producto.COLUMN_NAME_TIPOIMPRESION, tipoImpresion)
                .toString();
    }

}
