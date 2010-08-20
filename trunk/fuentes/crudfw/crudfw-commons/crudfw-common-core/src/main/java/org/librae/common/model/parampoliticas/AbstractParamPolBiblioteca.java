package org.librae.common.model.parampoliticas;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.librae.common.model.SpringRemotableLazyEntity;

@MappedSuperclass
public abstract class AbstractParamPolBiblioteca extends
        SpringRemotableLazyEntity<AbstractParamPolBiblioteca> implements
        IParamPolBiblioteca {

    /**
     * BaseObject is Serializable, so AbstractParamPolBiblioteca needs a Serial
     * Version UID
     */
    private static final long serialVersionUID                   = -5872758776095602513L;

    /**
     * Sólo aplica si D_GBS == "S" <br>
     * Implementado en Ab*NET <br>
     * Indica si SI/NO una sucursal admite que ejemplares prestados en otras
     * sucursales de su misma biblioteca sean devueltos en ella.
     */
    private Boolean           admiteDevolucionMismaBiblioteca    = IParamPolBiblioteca.DEFAULT_VALUE_ADMITE_DEVOLUCION_MISMA_BI;

    /**
     * Sólo aplica si D_GBS == "S"<br>
     * No implementado en Ab*NET.<br>
     * Indica si SI/NO una sucursal admite realizar préstamos con ejemplares de
     * otras sucursales de su misma biblioteca que fueron devueltos en ella en
     * lugar de serlo en la biblioteca propietaria.
     */
    private Boolean           admitePrestamoMismaBiblioteca      = IParamPolBiblioteca.DEFAULT_VALUE_ADMITE_PRESTAMO_MISMA_BIBL;

    /**
     * Indica si SI/NO una sucursal admite que ejemplares prestados en otras
     * sucursales de su misma biblioteca sean renovados en ella.
     */
    private Boolean           admiteRenovacionMismaBiblioteca    = IParamPolBiblioteca.DEFAULT_VALUE_ADMITE_RENOVACION_MISMA_BI;

    /**
     * Valor SI: Cuando sucede una devolución de un ejemplar que puede
     * cumplimentar una reserva (es decir, una devolución de un ejemplar de un
     * registro bibliográfico para el que existen reservas), la aplicación toma
     * la decisión de vincular el ejemplar devuelto con la primera reserva de la
     * cola que no tenga uno vinculado, si dejar alternativa al usuario.<br>
     * Valor NO: Cuando sucede una devolución de un ejemplar que puede
     * cumplimentar una reserva es el usuario quien tiene la opción de dejar que
     * el ejemplar se vincule con una reserva o por el contrario mantener la
     * reserva en espera de otra devolución (este caso es como si para la
     * reserva no se hubiese producido una devolución).
     */
    private Boolean           cumplimentarReservasEnDevoluciones = IParamPolBiblioteca.DEFAULT_VALUE_CUMPLIMENTAR_RESERV_EN_DEV;

    /**
     * 1: Preguntar al bibliotecario, en el momento de la devolución, si desea
     * emitir un recibo, y emitirlo o no según la respñuesta.<br>
     * 0: No emitir recibo en las devoluciones, sin dejar elección al usuario.
     */
    private Boolean           emitirReciboEnDevoluciones         = IParamPolBiblioteca.DEFAULT_VALUE_EMITIR_RECIBO_EN_DEVOLUCIO;

    /**
     * 1: Preguntgar al bibliotecario, en el momento de la renovación, si desea
     * emitir un recibo, y emitirlo o no según la respuesta.<br>
     * 0: No emitir recibo en ninguna operación de renovación, sin dejar
     * elección al bibliotecario
     */
    private Boolean           emitirReciboEnRenovaciones         = IParamPolBiblioteca.DEFAULT_VALUE_EMITIR_RECIBO_EN_RENOVACIO;

    /**
     * 1: Preguntgar al bibliotecario, en el momento de realizar una reserva, si
     * desea emitir un recibo, y emitirlo o no según la respuesta. 0: No emitir
     * recibo en ninguna operación de reserva, sin dejar elección al
     * bibliotecario
     */
    private Boolean           emitirReciboEnReservas             = IParamPolBiblioteca.DEFAULT_VALUE_EMITIR_RECIBO_EN_RESERVAS;

    /**
     * Sólo aplica si D_GBS == "S"<br>
     * Implementado en Ab*NET<br>
     * Indica si SI/NO una sucursal permite que sus ejemplares prestados sean
     * devueltos en otras sucursales de su misma biblioteca<br>
     */
    private Boolean           permiteDevolucionMismaBiblioteca   = IParamPolBiblioteca.DEFAULT_VALUE_PERMITE_DEV_MISMA_BIB;

    /**
     * No implementado en Ab*NET.<br>
     * Sólo aplica si D_GBS == "S"<br>
     * Indica si SI/NO una sucursal permite que los ejemplares devueltos en
     * otras sucursales de su misma biblioteca sean prestados en éstas sin que
     * vuelvan a la sucursal propietaria del ejemplar.
     */
    private Boolean           permitePrestamoMismaBiblioteca     = IParamPolBiblioteca.DEFAULT_VALUE_PERMITE_PRESTAMO_MISMA_BIB;

    /**
     * Solo aplica si D_GBS == "S"<br>
     * Indica si SI/NO una sucursal permite que los préstamos de sus ejemplares
     * sean renovados en otras sucursales de su misma biblioteca.
     */
    private Boolean           permiteRenovacionMismaBiblioteca   = IParamPolBiblioteca.DEFAULT_VALUE_PERMITE_RENOV_MISMA_BIB;

    /**
     * Sólo aplica en préstamos y renovaciones y si el lector está suspendido<br>
     * Valor 1: durante la operación de préstamo o renovación es el usuario
     * bibliotecario quien decide si continuar con la operación o abortarla.<br>
     * Valor 0: la aplicación no permite realizar préstamos, ni renovaciones ni
     * a lectores suspendidos, no dando elección al bibliotecario.<br>
     * En Ab*NET el parámetro equivalente se llama “Préstamo/Reserva si
     * suspendido”, pero no contempla la renovación y si contempla la reserva
     */
    private Boolean           prestamoRenovacionSiSuspendido     = IParamPolBiblioteca.DEFAULT_VALUE_PRESTAMO_RENOVACIO_SI_SUSP;

    /**
     * Sólo aplica en reservas y si el lector está suspendido<br>
     * Valor 1: durante la operación de reserva es el usuario bibliotecario
     * quien decide si continuar con la operación o abortarla si el lector está
     * suspsndido.<br>
     * Valor 0: la aplicación no permite realizar reservas a lectores
     * suspendidos, no dando elección al bibliotecario.<br>
     * En Ab*NET el parámetro equivalente se llama “Préstamo/Reserva si
     * suspendido”
     */
    private Boolean           reservaSiSuspendido                = IParamPolBiblioteca.DEFAULT_VALUE_RESERVA_SI_SUSPENDIDO;

    /**
     * Corresponde a los parámetros “Nº max. de préstamos en la biblioteca” y
     * "Nº max. de préstamos en la sucursal", de la política de préstamos de
     * Ab*NET, según que D_GBS sea B ó S resp.<br>
     * <br>
     * Si D_GBS == "B":<br>
     * Número máximo de ejemplares, que un lector puede tener a la vez en
     * préstamo, de la biblioteca (padre de la sucursal), en la que se está
     * realizando el préstamo, considerando todas sus sucursales hijas<br>
     * <br>
     * Si D_GBS == "S":<br>
     * Número máximo de ejemplares, de la sucursal en la que se está realizando
     * la operación de préstamo, que un lector puede tener a la vez en su poder.<br>
     * <br>
     * Es el mismo parámetro que N_MAX_PRESTAMOS_DOM del tipo de lector. La
     * diferencia es que aquel sirve para controlar esta cantidad a nivel de
     * tipos de lectores. Si ambos parámetros tienen un valor, se toma el más
     * específico, según se explica en la documentación.<br>
     * <br>
     * Un valor NULL en este campo significa que no hay máximo (o dicho de otra
     * forma, que el máximo es infinito)
     */
    private Long              numeroMaxPrestamosDom              = IParamPolBiblioteca.DEFAULT_VALUE_NUMERO_MAX_PRESTAMOS_DOM;

    /**
     * Nº máximo de reservas para una cola de reservas, que esta biblioteca
     * permite
     */
    private Long              numeroMaxReservasPorCola           = IParamPolBiblioteca.DEFAULT_VALUE_NUMERO_MAX_RESERV_POR_COLA;

    /**
     * @return the admiteDevolucionMismaBiblioteca
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_ADMITE_DEVOLUCION_MISMA_BI)
    public Boolean getAdmiteDevolucionMismaBiblioteca() {
        return admiteDevolucionMismaBiblioteca;
    }

    /**
     * @param admiteDevolucionMismaBiblioteca
     *            the admiteDevolucionMismaBiblioteca to set
     */
    public void setAdmiteDevolucionMismaBiblioteca(
            final Boolean admiteDevolucionMismaBiblioteca) {
        this.admiteDevolucionMismaBiblioteca = admiteDevolucionMismaBiblioteca;
    }

    /**
     * @return the admitePrestamoMismaBiblioteca
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_ADMITE_PRESTAMO_MISMA_BIBL)
    public Boolean getAdmitePrestamoMismaBiblioteca() {
        return admitePrestamoMismaBiblioteca;
    }

    /**
     * @param admitePrestamoMismaBiblioteca
     *            the admitePrestamoMismaBiblioteca to set
     */
    public void setAdmitePrestamoMismaBiblioteca(
            final Boolean admitePrestamoMismaBiblioteca) {
        this.admitePrestamoMismaBiblioteca = admitePrestamoMismaBiblioteca;
    }

    /**
     * @return the admiteRenovacionMismaBiblioteca
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_ADMITE_RENOVACION_MISMA_BI)
    public Boolean getAdmiteRenovacionMismaBiblioteca() {
        return admiteRenovacionMismaBiblioteca;
    }

    /**
     * @param admiteRenovacionMismaBiblioteca
     *            the admiteRenovacionMismaBiblioteca to set
     */
    public void setAdmiteRenovacionMismaBiblioteca(
            final Boolean admiteRenovacionMismaBiblioteca) {
        this.admiteRenovacionMismaBiblioteca = admiteRenovacionMismaBiblioteca;
    }

    /**
     * @return the cumplimentarReservasEnDevoluciones
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_CUMPLIMENTAR_RESERV_EN_DEV)
    public Boolean getCumplimentarReservasEnDevoluciones() {
        return cumplimentarReservasEnDevoluciones;
    }

    /**
     * @param cumplimentarReservasEnDevoluciones
     *            the cumplimentarReservasEnDevoluciones to set
     */
    public void setCumplimentarReservasEnDevoluciones(
            final Boolean cumplimentarReservasEnDevoluciones) {
        this.cumplimentarReservasEnDevoluciones = cumplimentarReservasEnDevoluciones;
    }

    /**
     * @return the emitirReciboEnDevoluciones
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_EMITIR_RECIBO_EN_DEVOLUCIO)
    public Boolean getEmitirReciboEnDevoluciones() {
        return emitirReciboEnDevoluciones;
    }

    /**
     * @param emitirReciboEnDevoluciones
     *            the emitirReciboEnDevoluciones to set
     */
    public void setEmitirReciboEnDevoluciones(
            final Boolean emitirReciboEnDevoluciones) {
        this.emitirReciboEnDevoluciones = emitirReciboEnDevoluciones;
    }

    /**
     * @return the emitirReciboEnRenovaciones
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_EMITIR_RECIBO_EN_RENOVACIO)
    public Boolean getEmitirReciboEnRenovaciones() {
        return emitirReciboEnRenovaciones;
    }

    /**
     * @param emitirReciboEnRenovaciones
     *            the emitirReciboEnRenovaciones to set
     */
    public void setEmitirReciboEnRenovaciones(
            final Boolean emitirReciboEnRenovaciones) {
        this.emitirReciboEnRenovaciones = emitirReciboEnRenovaciones;
    }

    /**
     * @return the emitirReciboEnReservas
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_EMITIR_RECIBO_EN_RESERVAS)
    public Boolean getEmitirReciboEnReservas() {
        return emitirReciboEnReservas;
    }

    /**
     * @param emitirReciboEnReservas
     *            the emitirReciboEnReservas to set
     */
    public void setEmitirReciboEnReservas(final Boolean emitirReciboEnReservas) {
        this.emitirReciboEnReservas = emitirReciboEnReservas;
    }

    /**
     * @return the permiteDevolucionMismaBiblioteca
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_PERMITE_DEV_MISMA_BIB)
    public Boolean getPermiteDevolucionMismaBiblioteca() {
        return permiteDevolucionMismaBiblioteca;
    }

    /**
     * @param permiteDevolucionMismaBiblioteca
     *            the permiteDevolucionMismaBiblioteca to set
     */
    public void setPermiteDevolucionMismaBiblioteca(
            final Boolean permiteDevolucionMismaBiblioteca) {
        this.permiteDevolucionMismaBiblioteca = permiteDevolucionMismaBiblioteca;
    }

    /**
     * @return the permitePrestamoMismaBiblioteca
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_PERMITE_PRESTAMO_MISMA_BIB)
    public Boolean getPermitePrestamoMismaBiblioteca() {
        return permitePrestamoMismaBiblioteca;
    }

    /**
     * @param permitePrestamoMismaBiblioteca
     *            the permitePrestamoMismaBiblioteca to set
     */
    public void setPermitePrestamoMismaBiblioteca(
            final Boolean permitePrestamoMismaBiblioteca) {
        this.permitePrestamoMismaBiblioteca = permitePrestamoMismaBiblioteca;
    }

    /**
     * @return the permiteRenovacionMismaBiblioteca
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_PERMITE_RENOV_MISMA_BIB)
    public Boolean getPermiteRenovacionMismaBiblioteca() {
        return permiteRenovacionMismaBiblioteca;
    }

    /**
     * @param permiteRenovacionMismaBiblioteca
     *            the permiteRenovacionMismaBiblioteca to set
     */
    public void setPermiteRenovacionMismaBiblioteca(
            final Boolean permiteRenovacionMismaBiblioteca) {
        this.permiteRenovacionMismaBiblioteca = permiteRenovacionMismaBiblioteca;
    }

    /**
     * @return the prestamoRenovacionSiSuspendido
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_PRESTAMO_RENOVACIO_SI_SUSP)
    public Boolean getPrestamoRenovacionSiSuspendido() {
        return prestamoRenovacionSiSuspendido;
    }

    /**
     * @param prestamoRenovacionSiSuspendido
     *            the prestamoRenovacionSiSuspendido to set
     */
    public void setPrestamoRenovacionSiSuspendido(
            final Boolean prestamoRenovacionSiSuspendido) {
        this.prestamoRenovacionSiSuspendido = prestamoRenovacionSiSuspendido;
    }

    /**
     * @return the reservaSiSuspendido
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_RESERVA_SI_SUSPENDIDO)
    public Boolean getReservaSiSuspendido() {
        return reservaSiSuspendido;
    }

    /**
     * @param reservaSiSuspendido
     *            the reservaSiSuspendido to set
     */
    public void setReservaSiSuspendido(final Boolean reservaSiSuspendido) {
        this.reservaSiSuspendido = reservaSiSuspendido;
    }

    /**
     * @return the numeroMaxPrestamosDom
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_NUMERO_MAX_PRESTAMOS_DOM)
    public Long getNumeroMaxPrestamosDom() {
        return numeroMaxPrestamosDom;
    }

    /**
     * @param numeroMaxPrestamosDom
     *            the numeroMaxPrestamosDom to set
     */
    public void setNumeroMaxPrestamosDom(final Long numeroMaxPrestamosDom) {
        this.numeroMaxPrestamosDom = numeroMaxPrestamosDom;
    }

    /**
     * @return the numeroMaxReservasPorCola
     */
    @Column(name = IParamPolBiblioteca.COLUMN_NAME_NUMERO_MAX_RESERV_POR_COLA)
    public Long getNumeroMaxReservasPorCola() {
        return numeroMaxReservasPorCola;
    }

    /**
     * @param numeroMaxReservasPorCola
     *            the numeroMaxReservasPorCola to set
     */
    public void setNumeroMaxReservasPorCola(final Long numeroMaxReservasPorCola) {
        this.numeroMaxReservasPorCola = numeroMaxReservasPorCola;
    }

    /**
     * Returns a multi-line String with key=value pairs.
     *
     * @return a String representation of this class.
     */
    @Override
    abstract public String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     *
     * @param o
     *            object to compare to
     * @return true/false based on equality tests
     */
    @Override
    abstract public boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     *
     * @return hashCode
     */
    @Override
    abstract public int hashCode();

    @Override
    public abstract AbstractParamPolBiblioteca newInstance();
}
