package org.librae.common.model.parampoliticas;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

@MappedSuperclass
public abstract class AbstractParamPolEjemplarTipo extends
        SpringRemotableLazyEntity<AbstractParamPolEjemplarTipo> implements
        IParamPolEjemplarTipo {

    /**
     * BaseObject is Serializable, so AbstractParamPolEjemplarTipo needs a
     * Serial Version UID
     */
    private static final long serialVersionUID         = 7398884804244676718L;

    /**
     * Máximo número de ejemplares de un tipo determinado que un lector puede
     * tener a la vez en préstamo.
     */

    protected Integer         numMaximoPrestamos       = IParamPolEjemplarTipo.DEFAULT_VALUE_MAXPRESTAMOS;

    /**
     * Indica si SI/NO es el usuario bibliotecario quien puede tomar la decisión
     * de realizar una renovación de préstamo de un ejemplar que está reservado.
     * El valor SI indica que cuando la aplicación detecte esta situación, dará
     * al bibliotecario la posibilidad de decidir si continuar con la operación
     * o abortarla, y si el valor es NO la aplicación simplemente lo detectará,
     * informando de ello al bibliotecario, y abortará la operación de
     * renovación.
     */
    protected Boolean         renovarEjemplarReservado = IParamPolEjemplarTipo.DEFAULT_VALUE_RENOVAR_EJEMPLAR_RESERVADO;

    /**
     * @return the numMaximoPrestamos
     */
    @Column(name = IParamPolEjemplarTipo.COLUMN_NAME_MAXPRESTAMOS)
    public Integer getNumMaximoPrestamos() {
        return numMaximoPrestamos;
    }

    /**
     * @param numMaximoPrestamos
     *            the numMaximoPrestamos to set
     */
    public void setNumMaximoPrestamos(final Integer numMaximoPrestamos) {
        this.numMaximoPrestamos = numMaximoPrestamos;
    }

    /**
     * @return the renovarEjemplarReservado
     */
    @Column(name = IParamPolEjemplarTipo.COLUMN_NAME_RENOVAR_EJEMPLAR_RESERVADO)
    public Boolean getRenovarEjemplarReservado() {
        return renovarEjemplarReservado;
    }

    /**
     * @param renovarEjemplarReservado
     *            the renovarEjemplarReservado to set
     */
    public void setRenovarEjemplarReservado(
            final Boolean renovarEjemplarReservado) {
        this.renovarEjemplarReservado = renovarEjemplarReservado;
    }

    /**
     * Returns a multi-line String with key=value pairs.
     * 
     * @return a String representation of this class.
     */
    @Override
    public abstract String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     * 
     * @param o
     *            object to compare to
     * @return true/false based on equality tests
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     * 
     * @return hashCode
     */
    @Override
    public abstract int hashCode();

    @Override
    public abstract AbstractParamPolEjemplarTipo newInstance();
}
