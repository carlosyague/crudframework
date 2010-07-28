package org.librae.catalogacion.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Bean para almacenar un AutoridadSubcampo
 * 
 * @author jcdiaz
 * @version 2.0
 * @author asalas unificación de clases
 */
// @Entity(name = AutoridadSubcampo.ENTITY_NAME)
// @Table(name = AutoridadSubcampo.TABLE_NAME)
public class AutoridadSubcampo extends SubcampoMarc {

    /**
     * BaseObject es Serializable, por lo tanto AutoridadSubcampo necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID                  = -9137565770633715643L;

    public static final String  ENTITY_NAME                       = "org.librae.catalogacion.model.AutoridadSubcampo";

    /**
     * Constantes para referencia de los atributos de AutoridadSubcampo
     */
    public static final String  PROPTY_NAME_AUTORIDAD_ETIQUETA    = "autoridadEtiqueta";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME                        = "CAT_AUTORIDAD_SUBCAMPO";
    private static final String ID_GENERATOR_NAME                 = "generator_cat_autoridad_subcampo";
    private static final String ID_SEQUENCE_NAME                  = "SEQ_CAT_AUTORIDAD_SUBCAMPO";

    public static final String  COLUMN_NAME_ETIQUETA_AUTORIDAD_FK = "AUT_X_AUTORIDAD_ETIQUETA";

    /**
     * Constructor sin parámetros
     */
    protected AutoridadSubcampo() {
        super();
    }

    /**
     * Constructor con parámetros
     * 
     * @param nombre
     * @param campoMarc
     * @param valor
     */
    public AutoridadSubcampo(final String campoMarc, final String valor) {
        super(campoMarc, valor);
    }

    /**
     * @return the id
     */
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO, generator =
    // AutoridadSubcampo.ID_GENERATOR_NAME)
    // @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName =
    // AutoridadSubcampo.ID_SEQUENCE_NAME)
    // @Column(name = AutoridadSubcampo.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return super.getId();
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        super.setId(id);
    }

    /**
     * @return the nombre
     */
    // @Column(name = AutoridadSubcampo.COLUMN_NAME_NOMBRE, nullable = false)
    // public String getNombre() {
    // return super.getNombre();
    // }
    //
    // /**
    // * @param nombre
    // * the nombre to set
    // */
    // public void setNombre(final String nombre) {
    // super.setNombre(nombre);
    // }
    /**
     * @return the campoMarc
     */
    // @Column(name = AutoridadSubcampo.COLUMN_NAME_CODIGO_MARC)
    public String getCampoMarc() {
        return super.getCampoMarc();
    }

    /**
     * @param campoMarc
     *            the campoMarc to set
     */
    public void setCampoMarc(final String campoMarc) {
        super.setCampoMarc(campoMarc);
    }

    /**
     * @return the valor
     */
    // @Column(name = AutoridadSubcampo.COLUMN_NAME_VALOR, nullable = false)
    public String getValor() {
        return super.getValor();
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(final String valor) {
        super.setValor(valor);
    }

    /**
     * @return the autoridadEtiqueta
     */
    // @ManyToOne(targetEntity = AutoridadEtiqueta.class)
    // @JoinColumn(name = AutoridadSubcampo.COLUMN_NAME_ETIQUETA_AUTORIDAD_FK,
    // nullable = false)
    public AutoridadEtiqueta getAutoridadEtiqueta() {
        return super.getAutoridadEtiqueta();
    }

    /**
     * @param autoridadEtiqueta
     *            the autoridadEtiqueta to set
     */
    public void setAutoridadEtiqueta(final AutoridadEtiqueta autoridadEtiqueta) {
        super.setAutoridadEtiqueta(autoridadEtiqueta);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime
                * result
                + ((super.getAutoridadEtiqueta() == null) ? 0 : super
                        .getAutoridadEtiqueta().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        // result = prime * result
        // + ((getNombre() == null) ? 0 : getNombre().hashCode());
        result = prime * result
                + ((getValor() == null) ? 0 : getValor().hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutoridadSubcampo)) {
            return false;
        }
        final AutoridadSubcampo other = (AutoridadSubcampo) obj;
        if (super.getAutoridadEtiqueta() == null) {
            if (other.getAutoridadEtiqueta() != null) {
                return false;
            }
        } else if (!super.getAutoridadEtiqueta().equals(
                other.getAutoridadEtiqueta())) {
            return false;
        }
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!getId().equals(other.getId())) {
            return false;
        }
        // if (getNombre() == null) {
        // if (other.getNombre() != null) {
        // return false;
        // }
        // } else if (!getNombre().equals(other.getNombre())) {
        // return false;
        // }
        if (getValor() == null) {
            if (other.getValor() != null) {
                return false;
            }
        } else if (!getValor().equals(other.getValor())) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this)

        .append(AutoridadSubcampo.PROPTY_NAME_ID, super.getId())

        // .append(AutoridadSubcampo.PROPTY_NAME_NOMBRE,
                // (super.getNombre() == null) ? 0 : super.getNombre())

                .append(AutoridadSubcampo.COLUMN_NAME_VALOR,
                        (super.getValor() == null) ? 0 : super.getValor())

                .append(
                        AutoridadSubcampo.PROPTY_NAME_AUTORIDAD_ETIQUETA,
                        (super.getAutoridadEtiqueta() == null) ? 0 : super
                                .getAutoridadEtiqueta().toString())

                .toString();
    }
}