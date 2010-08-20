package org.librae.common.model.parampoliticas;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.librae.common.model.SpringRemotableLazyEntity;

@MappedSuperclass
public abstract class AbstractParamPolTipoLector extends
        SpringRemotableLazyEntity<AbstractParamPolTipoLector> implements
        IParamPolTipoLector {

    /**
     * BaseObject is Serializable, so AbstractParamPolLectorTipo needs a Serial
     * Version UID
     */
    private static final long serialVersionUID         = -3585498768222671849L;

    /**
     * N�mero m�ximo de d�as de suspensi�n que se puede aplicar a un lector.
     * Valor inicial 30 d�as
     */
    private Integer           maxDiasSuspension        = IParamPolTipoLector.DEFAULT_VALUE_MAX_DIAS_SUSPENSION;

    /**
     * Valor 1: durante la operaci�n de pr�stamo o renovaci�n es el usuario
     * bibliotecario quien decide si continuar con la operaci�n o abortarla, en
     * caso de que el lector tenga alg�n pr�stamo sobrepasado. <br>
     * Valor 0: la aplicaci�n no permite realizar pr�stamos, ni renovaciones a
     * lectores con pr�stamos sobrepasados, no dando elecci�n al bibliotecario.
     * El par�metro �parecido� a �ste en Ab*NET es �Pr�stamo / Reserva si
     * sobrepasados�, sin embargo �ste no contempla el caso de las renovaciones.
     */

    private Boolean           prestamoRenovSobrepasado = IParamPolTipoLector.DEFAULT_VALUE_PRESTAMO_RENOV_SOBREPASADO;

    /**
     * El par�metro equivalente en Ab*NET es �Pr�stamos id�nticos�. <br>
     * Valor 1: si durante la operaci�n de pr�stamo o reserva de un ejemplar, se
     * detecta que el lector ya tiene en pr�stamo o reserva un ejemplar id�ntico
     * (con mismo t�tulo, autor y n� de volumen), es el usuario bibliotecario
     * quien decide si continuar con la operaci�n o abortarla. <br>
     * Valor 0: la aplicaci�n no permite realizar pr�stamos ni reservas de
     * ejemplares id�nticos a alguno que el lector ya tiene en pr�stamo o
     * reserva, no dando elecci�n al bibliotecario.
     */
    private Boolean           prestamoEjemplaresIdent  = IParamPolTipoLector.DEFAULT_VALUE_PRESTAMO_EJEMPLARES_IDENT;

    /**
     * M�ximo n�mero de ejemplares que un lector puede tener a la vez en
     * pr�stamo, para este tipo de lector. Si un lector L es de los tipos t1 y
     * t2, y este par�metro vale v1 para el tipo de lector t1, y v2 para el tipo
     * de lector t2, el m�ximo de pr�stamos para el lector es la suma v1 + v2
     * Est� relacionado sem�nticamente con el par�metro
     * EJEMPLARES_TIPOS.N_MAX_PRESTAMOS . No existe ninguna relaci�n matem�tica
     * entre el valor de este par�metro y los valores de
     * EJEMPLARES_TIPOS.N_MAX_PRESTAMOS de los diferentes tipos de ejemplares
     * que participan en los pr�stamos activos de un lector. As� por ejemplo,
     * sea T1 el tipo del lector, y sea M el valor de este par�metro para T1.
     * Sean E1, ... En los tipos de ejemplares correspondientes a pr�stamos
     * activos (T1,E1), (T1,E2), ..., (T1,En). Sean M1, M2, ..., Mn los valores
     * del par�metro EJEMPLARES_TIPOS.N_MAX_PRESTAMOS para los tipos E1, ...,
     * En, entonces, pueden darse cualesquiera de las siguientes relaciones. M
     * <= Mi i=1,...,n M <= M1 + M2 + ... + Mn M >= M1 + M2 + ... + Mn
     */
    private Integer           maxPrestamosDom          = IParamPolTipoLector.DEFAULT_VALUE_MAX_PRESTAMOS_DOM;

    /**
     * S�lo aplicable a las reservas por registro bibliogr�fico, para las
     * reservas por ejemplar no se permite m�s de una reserva del mismo lector
     * en la cola. <br>
     * 1: Indica que en una cola de reservas por registro bibliogr�fico, un
     * lector puede tener m�s de una reserva al mismo tiempo <br>
     * 0: en caso contrario
     */

    private Boolean           reservaEjemplaresIdent   = IParamPolTipoLector.DEFAULT_VALUE_RESERVA_EJEMPLARES_IDENT;

    /**
     * Valor 1: durante la operaci�n de reserva es el usuario bibliotecario
     * quien decide si continuar con la operaci�n o abortarla en caso de que el
     * lector tenga alg�n pr�stamo sobrepasado. <br>
     * Valor 0: la aplicaci�n no permite realizar reservas a lectores con
     * pr�stamos sobrepasados, no dando elecci�n al bibliotecario. El par�metro
     * �parecido� a �ste en Ab*NET es �Pr�stamo / Reserva si sobrepasados�, sin
     * embargo �ste no contempla el caso de las renovaciones.
     */
    private Boolean           reservaSobrepasados;

    /**
     * N�mero m�ximo de reservas que puede tener un lector para este tipo de
     * lector. Si un lector L es de los tipos t1 y t2, y este par�metro vale v1
     * para el tipo de lector t1, y v2 para el tipo de lector t2, el m�ximo de
     * reservas es la suma v1 + v2
     */
    private Integer           maxReservas              = IParamPolTipoLector.DEFAULT_VALUE_MAX_RESERVAS;

    /**
     * @return the maxDiasSuspension
     */
    @Column(name = IParamPolTipoLector.COLUMN_NAME_MAX_DIAS_SUSPENSION)
    public Integer getMaxDiasSuspension() {
        return maxDiasSuspension;
    }

    /**
     * @param maxDiasSuspension
     *            the maxDiasSuspension to set
     */
    public void setMaxDiasSuspension(final Integer maxDiasSuspension) {
        this.maxDiasSuspension = maxDiasSuspension;
    }

    /**
     * @return the prestamoRenovSobrepasado
     */
    @Column(name = IParamPolTipoLector.COLUMN_NAME_PRESTAMO_RENOV_SOBREPASADO)
    public Boolean getPrestamoRenovSobrepasado() {
        return prestamoRenovSobrepasado;
    }

    /**
     * @param prestamoRenovSobrepasado
     *            the prestamoRenovSobrepasado to set
     */
    public void setPrestamoRenovSobrepasado(
            final Boolean prestamoRenovSobrepasado) {
        this.prestamoRenovSobrepasado = prestamoRenovSobrepasado;
    }

    /**
     * @return the prestamoEjemplaresIdent
     */
    @Column(name = IParamPolTipoLector.COLUMN_NAME_PRESTAMO_EJEMPLARES_IDENT)
    public Boolean getPrestamoEjemplaresIdent() {
        return prestamoEjemplaresIdent;
    }

    /**
     * @param prestamoEjemplaresIdent
     *            the prestamoEjemplaresIdent to set
     */
    public void setPrestamoEjemplaresIdent(final Boolean prestamoEjemplaresIdent) {
        this.prestamoEjemplaresIdent = prestamoEjemplaresIdent;
    }

    /**
     * @return the maxPrestamosDom
     */
    @Column(name = IParamPolTipoLector.COLUMN_NAME_MAX_PRESTAMOS_DOM)
    public Integer getMaxPrestamosDom() {
        return maxPrestamosDom;
    }

    /**
     * @param maxPrestamosDom
     *            the maxPrestamosDom to set
     */
    public void setMaxPrestamosDom(final Integer maxPrestamosDom) {
        this.maxPrestamosDom = maxPrestamosDom;
    }

    /**
     * @return the reservaEjemplaresIdent
     */
    @Column(name = IParamPolTipoLector.COLUMN_NAME_RESERVA_EJEMPLARES_IDENT)
    public Boolean getReservaEjemplaresIdent() {
        return reservaEjemplaresIdent;
    }

    /**
     * @param reservaEjemplaresIdent
     *            the reservaEjemplaresIdent to set
     */
    public void setReservaEjemplaresIdent(final Boolean reservaEjemplaresIdent) {
        this.reservaEjemplaresIdent = reservaEjemplaresIdent;
    }

    /**
     * @return the reservaSobrepasados
     */
    @Column(name = IParamPolTipoLector.COLUMN_NAME_RESERVA_SI_SOBREPASADOS)
    public Boolean getReservaSobrepasados() {
        return reservaSobrepasados;
    }

    /**
     * @param reservaSobrepasados
     *            the reservaSobrepasados to set
     */
    public void setReservaSobrepasados(final Boolean reservaSobrepasados) {
        this.reservaSobrepasados = reservaSobrepasados;
    }

    /**
     * @return the maxReservas
     */
    @Column(name = IParamPolTipoLector.COLUMN_NAME_MAX_RESERVAS)
    public Integer getMaxReservas() {
        return maxReservas;
    }

    /**
     * @param maxReservas
     *            the maxReservas to set
     */
    public void setMaxReservas(final Integer maxReservas) {
        this.maxReservas = maxReservas;
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
    public abstract AbstractParamPolTipoLector newInstance();
}
