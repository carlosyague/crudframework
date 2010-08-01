package org.librae.catalogacion.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Bean para almacenar un RegistroSubcampo
 * 
 * @author jcdiaz
 * @version 2.0
 * @author asalas unificación de clases
 * @param <T>
 */
// @Entity(name = RegistroSubcampo.ENTITY_NAME)
// @Table(name = RegistroSubcampo.TABLE_NAME)
public class RegistroSubcampo extends SubcampoMarc {

    /**
     * BaseObject es Serializable, por lo tanto RegistroSubcampo necesita un
     * Serial Version UID
     */
    private static final long   serialVersionUID                 = -6284106563914527617L;

    public static final String  ENTITY_NAME                      = "org.librae.catalogacion.model.RegistroSubcampo";

    /**
     * Constantes para referencia de los atributos de RegistroSubcampo
     */
    public static final String  PROPTY_NAME_REGISTRO_ETIQUETA    = "registroEtiqueta";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME                       = "CAT_REGISTRO_SUBCAMPO";
    private static final String ID_GENERATOR_NAME                = "generator_cat_registro_subcampo";
    private static final String ID_SEQUENCE_NAME                 = "SEQ_CAT_REGISTRO_SUBCAMPO";

    public static final String  COLUMN_NAME_REGISTRO_ETIQUETA_FK = "REG_X_REGISTRO_ETIQUETA";

    /**
     * Constructor sin parámetros
     */
    protected RegistroSubcampo() {
        super();
    }

    /**
     * Constructor sin parámetros
     * 
     * @param nombre
     *            del subcampo
     * @param campoMarc
     *            descripción del subcampo (equivale a outputLabel de
     *            SubcampoXML )
     * @param valor
     *            dispuesto por el usuario
     */
    public RegistroSubcampo(final String campoMarc, final String valor) {
        super(campoMarc, valor);
    }

    /**
     * @return the id
     */
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO, generator =
    // RegistroSubcampo.ID_GENERATOR_NAME)
    // @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName =
    // RegistroSubcampo.ID_SEQUENCE_NAME)
    // @Column(name = RegistroSubcampo.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return super.getId();
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        super.setId(id);
    }

    /**
     * @return the nombre
     */
    // @Column(name = RegistroSubcampo.COLUMN_NAME_NOMBRE, nullable = false)
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
    // @Column(name = RegistroSubcampo.COLUMN_NAME_CAMPO_MARC)
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
    // @Column(name = RegistroSubcampo.COLUMN_NAME_VALOR, nullable = false)
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
     * @return the registroEtiqueta
     */
    // @ManyToOne(targetEntity = RegistroEtiqueta.class)
    // @JoinColumn(name = RegistroSubcampo.COLUMN_NAME_REGISTRO_ETIQUETA_FK,
    // nullable = false)
    public RegistroEtiqueta getRegistroEtiqueta() {
        return super.getRegistroEtiqueta();
    }

    /**
     * @param registroEtiqueta
     *            the registroEtiqueta to set
     */
    public void setRegistroEtiqueta(final RegistroEtiqueta registroEtiqueta) {
        super.setRegistroEtiqueta(registroEtiqueta);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        // result = prime * result
        // + ((getNombre() == null) ? 0 : getNombre().hashCode());
        result = prime
                * result
                + ((getRegistroEtiqueta() == null) ? 0 : getRegistroEtiqueta()
                        .hashCode());
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
        if (!(obj instanceof RegistroSubcampo)) {
            return false;
        }
        final RegistroSubcampo other = (RegistroSubcampo) obj;
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
        if (getRegistroEtiqueta() == null) {
            if (other.getRegistroEtiqueta() != null) {
                return false;
            }
        } else if (!getRegistroEtiqueta().equals(other.getRegistroEtiqueta())) {
            return false;
        }
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

        .append(RegistroSubcampo.PROPTY_NAME_ID, getId())

        // .append(RegistroSubcampo.PROPTY_NAME_NOMBRE,
                // (getNombre() == null) ? 0 : getNombre())

                .append(RegistroSubcampo.PROPTY_NAME_VALOR,
                        (getValor() == null) ? 0 : getValor())

                .append(
                        RegistroSubcampo.PROPTY_NAME_REGISTRO_ETIQUETA,
                        (super.getRegistroEtiqueta() == null) ? 0 : super
                                .getRegistroEtiqueta().toString())

                .toString();
    }

}