package org.librae.common.model.parampoliticas;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * @author cyague
 */
@MappedSuperclass
public abstract class AbstractParamPolPrestamo extends
        SpringRemotableLazyEntity<AbstractParamPolPrestamo> implements
        IParamPolPrestamo {

    /**
     * BaseObject is Serializable, so AbstractParamPolPrestamo needs a Serial
     * Version UID
     */
    private static final long serialVersionUID           = -2398879942797816645L;

    /**
     * Código único asignado por el usuario
     */
    protected String          codigo;

    /**
     * Descripción (nombre) asignado por el usuario a la política de préstamos
     */
    protected String          nombre;

    /**
     * Descripción alternativa introducida por el usuario
     */
    protected String          descripcionAlternativa;

    /**
     * Permitir el préstamo aunque el ejemplar esté reservado
     */
    protected Boolean         permitirAunqueReservado;

    /**
     * Número de días laborables que dura el préstamo. La fecha de devolución se
     * calcula en función de este parámetro, la fecha del préstamo, y el
     * calendario de la biblioteca. La fecha de devolución es un día laborable
     * tal que el préstamo dure el número de días hábiles que indica este
     * parámetro
     */
    protected Long            diasPrestamo;

    /**
     * Por cuantos días se renueva el préstamo en cada renovación
     */
    protected Long            diasRenovacion;

    /**
     * Número máximo de renovaciones que admite el préstamo: cuando número de
     * renovaciones realizadas == N_MAX_RENOVACIONES, el préstamo no se puede
     * renovar<br>
     * Si 0 el préstamo no admite renovaciones
     */
    protected Long            maxRenovaciones            = IParamPolPrestamo.DEFAULT_VALUE_MAX_RENOVACIONES;

    /**
     * Desde cuantos días antes de la fecha de devolución calculada, hasta esa
     * fecha d devolución, se puede realizar la renovación del préstamo. Si es
     * la renovación n-sima, desde cuantos días antes de F_DEVOLUCION + n *
     * N_DIAS_RENOVACION se puede realizar la renovación del préstamo.
     */
    protected Long            diasAntesFinal;

    /**
     * Días antes de suspensión.<br>
     * Número de días (de gracia) posteriores a la fecha de devolución prevista,
     * antes<br>
     * de que se haga efectiva la sanción de suspensión. Si el lector realiza la<br>
     * devolución con retraso pero dentro de este periodo, no se le aplica
     * sanción.<br>
     * <br>
     * Un valor 0 para este campo significa que la suspensión se aplica en
     * cuanto<br>
     * exista un retraso en la devolución, por pequeño que sea, sin conceder
     * días de<br>
     * gracia.
     */
    protected Long            diasAntesSuspension        = IParamPolPrestamo.DEFAULT_VALUE_DIAS_ANTES_SUSPENSION;

    /**
     * Días de suspensión (dssu en Ab*NET).<br>
     * Número de días con los que se va a sancional al lector que se retrase en
     * la<br>
     * devolución. El significado es número de días por ejemplar y día de
     * retraso en<br>
     * la devolución. Es decir, si un lector tiene en préstamo dos ejemplares, y
     * se<br>
     * retrasa en la devolución de ámbos dos días (despues de ddsu) se le
     * aplicará una<br>
     * suspensión de 4 días.<br>
     * <br>
     * Un valor 0 en esta propiedad sinifica que según esta política de
     * préstamos no<br>
     * se aplican días de suspensión por retrasos en las devoluciones.<br>
     */
    protected Long            diasSuspension;

    /**
     * Días máximos de suspensión por ejemplar (dmsu en Ab*NET).<br>
     * <br>
     * El significado de esta propiedad es por ejemplar: máximo número de días
     * por ejemplar que va a durar la suspensión. Por ejemplo, si un lector se
     * retrasa en el préstamo de 2 ejemplares, y el retraso es de 8 días en
     * ambos, si N_DIAS_SUSPENSION = 1 se aplicaría una suspensión de :<br>
     * <br>
     * para el primer ejemplar de N_DIAS_SUSPENSION x 8 = 8 días <br>
     * para el segundo ejemplar de N_DIAS_SUSPENSION x 8 = 8 días <br>
     * <br>
     * En teoría le correspondería una suspensión de 8 días por cada ejemplar.
     * Pero si suponemos que el valor esta propiedad es 6, entonces la formula
     * para calcular la suspensión real por ejemplar es:<br>
     * <br>
     * min( 8 , N_DIAS_MAX_SUSPENSION= 6 ) = 6 para el primer ejemplar <br>
     * min( 8 , N_DIAS_MAX_SUSPENSION= 6 ) = 6 para el segundo ejemplar <br>
     * Total: 12 días de suspensión <br>
     * <br>
     * Con que la suspensión real a aplicar serían 12 días ( 6 + 6) en lugar de
     * 16.
     */
    protected Long            maxDiasSuspensionEjemplar;

    /**
     * Número de días después de la fecha de devolución (prevista) del ejemplar,
     * que se va a esperar la aplicación antes de emitir la primera carta de
     * reclamación. En definitiva, para el préstamo a domicilio, la primera
     * reclamación no se enviará antes de CIR_PRESTAMOS.F_DEVOLUCION_POL +
     * N_DIAS_RECLAMACION_1A
     */
    protected Long            diasReclamacion1a          = IParamPolPrestamo.DEFAULT_VALUE_DIAS_RECLAMACION_1A;

    /**
     * Número de días después de la fecha de devolución (prevista) del ejemplar,
     * que se va a esperar la aplicación antes de emitir la segunda carta de
     * reclamación. En definitiva, para el préstamo a domicilio, la segunda
     * reclamación no se enviará antes de CIR_PRESTAMOS.F_DEVOLUCION_POL +
     * N_DIAS_RECLAMACION_2A
     */
    protected Long            diasReclamacion2a          = IParamPolPrestamo.DEFAULT_VALUE_DIAS_RECLAMACION_2A;

    /**
     * Número de días después de la fecha de devolución (prevista) del ejemplar,
     * que se va a esperar la aplicación antes de emitir la tercera carta de
     * reclamación. En definitiva, para el préstamo a domicilio, la tercera
     * reclamación no se enviará antes de CIR_PRESTAMOS.F_DEVOLUCION_POL +
     * N_DIAS_RECLAMACION_3A
     */
    protected Long            diasReclamacion3a          = IParamPolPrestamo.DEFAULT_VALUE_DIAS_RECLAMACION_3A;

    /**
     * Valor 1: durante la operación de préstamo un ejemplar que ha sido
     * devuelto en el mismo día por el mismo lector que lo quiere obtener en
     * préstamo, es el usuario bibliotecario quien decide si continuar con la
     * operación o abortarla.<br>
     * Valor 0: la aplicación no permite realizar préstamos de ejemplares
     * devueltos en el mismo día al mismo lector de la devolución, no dando
     * elección al bibliotecario.
     */
    protected Boolean         prestamoSiDevueltoMismoDia = IParamPolPrestamo.DEFAULT_VALUE_PRESTAMO_SI_DEV_MISMO_DIA;

    /**
     * @return the codigo
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_CODIGO,length=40)
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
     * @return the nombre
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_NOMBRE,length=80)
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
     * @return the descripcionAlternativa
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_DESCRIPCION_ALTERNATIVA)
    public String getDescripcionAlternativa() {
        return descripcionAlternativa;
    }

    /**
     * @param descripcionAlternativa
     *            the descripcionAlternativa to set
     */
    public void setDescripcionAlternativa(final String descripcionAlternativa) {
        this.descripcionAlternativa = descripcionAlternativa;
    }

    /**
     * @return the permitirAunqueReservado
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_PERMITIR_AUNQUE_RESERVADO)
    public Boolean getPermitirAunqueReservado() {
        return permitirAunqueReservado;
    }

    /**
     * @param permitirAunqueReservado
     *            the permitirAunqueReservado to set
     */
    public void setPermitirAunqueReservado(final Boolean permitirAunqueReservado) {
        this.permitirAunqueReservado = permitirAunqueReservado;
    }

    /**
     * @return the diasPrestamo
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_DIAS_PRESTAMO)
    public Long getDiasPrestamo() {
        return diasPrestamo;
    }

    /**
     * @param diasPrestamo
     *            the diasPrestamo to set
     */
    public void setDiasPrestamo(final Long diasPrestamo) {
        this.diasPrestamo = diasPrestamo;
    }

    /**
     * @return the diasRenovacion
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_DIAS_RENOVACION)
    public Long getDiasRenovacion() {
        return diasRenovacion;
    }

    /**
     * @param diasRenovacion
     *            the diasRenovacion to set
     */
    public void setDiasRenovacion(final Long diasRenovacion) {
        this.diasRenovacion = diasRenovacion;
    }

    /**
     * @return the maxRenovaciones
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_MAX_RENOVACIONES)
    public Long getMaxRenovaciones() {
        return maxRenovaciones;
    }

    /**
     * @param maxRenovaciones
     *            the maxRenovaciones to set
     */
    public void setMaxRenovaciones(final Long maxRenovaciones) {
        this.maxRenovaciones = maxRenovaciones;
    }

    /**
     * @return the diasAntesFinal
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_DIAS_ANTES_FINAL)
    public Long getDiasAntesFinal() {
        return diasAntesFinal;
    }

    /**
     * @param diasAntesFinal
     *            the diasAntesFinal to set
     */
    public void setDiasAntesFinal(final Long diasAntesFinal) {
        this.diasAntesFinal = diasAntesFinal;
    }

    /**
     * @return the diasAntesSuspension
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_DIAS_ANTES_SUSPENSION)
    public Long getDiasAntesSuspension() {
        return diasAntesSuspension;
    }

    /**
     * @param diasAntesSuspension
     *            the diasAntesSuspension to set
     */
    public void setDiasAntesSuspension(final Long diasAntesSuspension) {
        this.diasAntesSuspension = diasAntesSuspension;
    }

    /**
     * @return the diasSuspension
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_DIAS_SUSPENSION)
    public Long getDiasSuspension() {
        return diasSuspension;
    }

    /**
     * @param diasSuspension
     *            the diasSuspension to set
     */
    public void setDiasSuspension(final Long diasSuspension) {
        this.diasSuspension = diasSuspension;
    }

    /**
     * @return the maxDiasSuspensionEjemplar
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_MAX_DIAS_SUSPENSION_EJEMPLAR)
    public Long getMaxDiasSuspensionEjemplar() {
        return maxDiasSuspensionEjemplar;
    }

    /**
     * @param maxDiasSuspensionEjemplar
     *            the maxDiasSuspensionEjemplar to set
     */
    public void setMaxDiasSuspensionEjemplar(
            final Long maxDiasSuspensionEjemplar) {
        this.maxDiasSuspensionEjemplar = maxDiasSuspensionEjemplar;
    }

    /**
     * @return the diasReclamacion1a
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_DIAS_RECLAMACION_1A)
    public Long getDiasReclamacion1a() {
        return diasReclamacion1a;
    }

    /**
     * @param diasReclamacion1a
     *            the diasReclamacion1a to set
     */
    public void setDiasReclamacion1a(final Long diasReclamacion1a) {
        this.diasReclamacion1a = diasReclamacion1a;
    }

    /**
     * @return the diasReclamacion2a
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_DIAS_RECLAMACION_2A)
    public Long getDiasReclamacion2a() {
        return diasReclamacion2a;
    }

    /**
     * @param diasReclamacion2a
     *            the diasReclamacion2a to set
     */
    public void setDiasReclamacion2a(final Long diasReclamacion2a) {
        this.diasReclamacion2a = diasReclamacion2a;
    }

    /**
     * @return the diasReclamacion3a
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_DIAS_RECLAMACION_3A)
    public Long getDiasReclamacion3a() {
        return diasReclamacion3a;
    }

    /**
     * @param diasReclamacion3a
     *            the diasReclamacion3a to set
     */
    public void setDiasReclamacion3a(final Long diasReclamacion3a) {
        this.diasReclamacion3a = diasReclamacion3a;
    }

    /**
     * @return the prestamoSiDevueltoMismoDia
     */
    @Column(name = IParamPolPrestamo.COLUMN_NAME_PRESTAMO_SI_DEV_MISMO_DIA)
    public Boolean getPrestamoSiDevueltoMismoDia() {
        return prestamoSiDevueltoMismoDia;
    }

    /**
     * @param prestamoSiDevueltoMismoDia
     *            the prestamoSiDevueltoMismoDia to set
     */
    public void setPrestamoSiDevueltoMismoDia(
            final Boolean prestamoSiDevueltoMismoDia) {
        this.prestamoSiDevueltoMismoDia = prestamoSiDevueltoMismoDia;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    abstract public boolean equals(final Object obj);

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    abstract public int hashCode();

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    abstract public String toString();

    /**
     * @see org.librae.common.auditoria.model.IAuditable#getPK()
     */
    @Transient
    public Object getPK() {
        // Valor de la clave primaria para el objeto.
        return getCodigo();
    }

    @Override
    public abstract AbstractParamPolPrestamo newInstance();

}