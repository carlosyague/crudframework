package org.librae.adminconfig.webapp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.librae.adminconfig.model.Horario;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.common.util.DateUtil;
import org.librae.common.webapp.form.IBaseForm;

/**
 * Formulario para la edición de un horario.
 * 
 * @author aropero
 */
public class HorarioForm implements IBaseForm<Horario>, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -114753265249889581L;

    /**
     * Id del Horario
     */
    private Long              idHorario;

    /**
     * Nombre de la biblioteca
     */
    private String            biblioteca;

    /**
     * Fecha de la última actualización
     */
    private Date              fechaActualizacion;

    /** Modo de Edición o Lectura de un Horario */
    private Boolean           readMode;

    /**
     * Regla de navegacion que se devolverá tras guardar el horario
     */
    private String            navigationRuleBack;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(final Horario horario) {
        fechaActualizacion = horario.getFechaActualizacion();
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public Horario toEntity() {
        final Horario horario = new Horario(fechaActualizacion);
        toEntity(horario);
        return horario;
    }

    public Horario toEntity(final Horario horario) {
        horario.setFechaActualizacion(fechaActualizacion);
        return horario;
    }

    public Horario toEntitySinLista() {
        final Horario horario = new Horario(fechaActualizacion);
        toEntitySinLista(horario);
        return horario;
    }

    public Horario toEntitySinLista(Horario horario) {
        horario.setFechaActualizacion(fechaActualizacion);
        return horario;
    }

    // ================= Getters && Setter ================

    public String getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(final String biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Date getFechaActualizacion() {
        return (fechaActualizacion == null) ? DateUtil.getCurrentDate()
                : fechaActualizacion;
    }

    public void setFechaActualizacion(final Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Boolean getReadMode() {
        return readMode;
    }

    public void setReadMode(final Boolean readMode) {
        this.readMode = readMode;
    }

    public String getNavigationRuleBack() {
        return navigationRuleBack;
    }

    public void setNavigationRuleBack(final String navigationRuleBack) {
        this.navigationRuleBack = navigationRuleBack;
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(final Long idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * Equivale a <code>hibernate.initialize(proxy)</code> pero funciona :O
     * 
     * @param lazyEntities
     * @return List<HorarioInt>
     */
    public List<HorarioInt> hibernateInit(final List<HorarioInt> proxyLazyList) {
        List initializedList = new ArrayList();
        if (proxyLazyList != null) {
            initializedList = proxyLazyList;
        }
        final int initialization = initializedList.size();
        return initializedList;
    }
}
