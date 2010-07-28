package org.librae.adminconfig.webapp.form;

import java.util.Date;

import org.librae.adminconfig.model.Calendario;
import org.librae.adminconfig.model.Festivo;
import org.librae.common.webapp.form.IBaseForm;

/**
 * Formulario para la creacion de calendarios y festivos
 */
public class CalendarioForm implements IBaseForm<Calendario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8521996923345207875L;

	/**
	 * Regla de navegacion que se devolverá tras guardar el calendario
	 */
	private String navigationRuleBack;

	/**
	 * Nombre de la biblioteca del calendario
	 */
	private String nombreBiblioteca;

	/**
	 * Dias laborables (0=no marcado; 1=marcado)
	 */
	private Boolean lunes = Boolean.FALSE;
	private Boolean martes = Boolean.FALSE;
	private Boolean miercoles = Boolean.FALSE;
	private Boolean jueves = Boolean.FALSE;
	private Boolean viernes = Boolean.FALSE;
	private Boolean sabado = Boolean.FALSE;
	private Boolean domingo = Boolean.FALSE;

	/**
	 * Fecha de la ultima actualizacion del calendario
	 */
	private Date ultimaActualizacion;

	/**
	 * Fechas festivas
	 */
	private Date[] festivos;

	/** modo de Edición o Lectura de un Calendario */
	private Boolean readMode;

	/**
	 * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
	 */
	public void fromEntity(final Calendario calendario) {
		if (calendario != null) {
			if (calendario.getDiasSemana() != null) {
				if (calendario.getDiasSemana().indexOf('L') != -1) {
					setLunes(Boolean.TRUE);
				}
				if (calendario.getDiasSemana().indexOf('M') != -1) {
					setMartes(Boolean.TRUE);
				}
				if (calendario.getDiasSemana().indexOf('X') != -1) {
					setMiercoles(Boolean.TRUE);
				}
				if (calendario.getDiasSemana().indexOf('J') != -1) {
					setJueves(Boolean.TRUE);
				}
				if (calendario.getDiasSemana().indexOf('V') != -1) {
					setViernes(Boolean.TRUE);
				}
				if (calendario.getDiasSemana().indexOf('S') != -1) {
					setSabado(Boolean.TRUE);
				}
				if (calendario.getDiasSemana().indexOf('D') != -1) {
					setDomingo(Boolean.TRUE);
				}
			}
			setUltimaActualizacion(calendario.getFechaActualizacion());
			if (calendario.getFestivos() != null
					&& !calendario.getFestivos().isEmpty()) {
				festivos = new Date[calendario.getFestivos().size()];
				int i = 0;
				for (final Festivo festivo : calendario.getFestivos()) {
					festivos[i] = festivo.getFechaFestivo();
					i++;
				}
			}
		}
	}

	/**
	 * @see org.librae.common.webapp.form.IBaseForm#toEntity()
	 */
	public Calendario toEntity() {
		return this.toEntity(new Calendario(""));
	}

	/**
	 * @see org.librae.common.webapp.form.IBaseForm#toEntity()
	 */
	public Calendario toEntity(final Calendario calendario) {
		calendario.setFechaActualizacion(getUltimaActualizacion());

		final StringBuilder laborables = new StringBuilder();
		if (getLunes().booleanValue()) {
			laborables.append('L');
		}
		if (getMartes().booleanValue()) {
			laborables.append('M');
		}
		if (getMiercoles().booleanValue()) {
			laborables.append('X');
		}
		if (getJueves().booleanValue()) {
			laborables.append('J');
		}
		if (getViernes().booleanValue()) {
			laborables.append('V');
		}
		if (getSabado().booleanValue()) {
			laborables.append('S');
		}
		if (getDomingo().booleanValue()) {
			laborables.append('D');
		}
		calendario.setDiasSemana(laborables.toString());

		return calendario;
	}

	// Getters && Setter

	public String getNavigationRuleBack() {
		return navigationRuleBack;
	}

	public void setNavigationRuleBack(final String navigationRuleBack) {
		this.navigationRuleBack = navigationRuleBack;
	}

	public String getNombreBiblioteca() {
		return nombreBiblioteca;
	}

	public void setNombreBiblioteca(final String nombreBiblioteca) {
		this.nombreBiblioteca = nombreBiblioteca;
	}

	public Boolean getLunes() {
		return lunes;
	}

	public void setLunes(final Boolean lunes) {
		this.lunes = lunes;
	}

	public Boolean getMartes() {
		return martes;
	}

	public void setMartes(final Boolean martes) {
		this.martes = martes;
	}

	public Boolean getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(final Boolean miercoles) {
		this.miercoles = miercoles;
	}

	public Boolean getJueves() {
		return jueves;
	}

	public void setJueves(final Boolean jueves) {
		this.jueves = jueves;
	}

	public Boolean getViernes() {
		return viernes;
	}

	public void setViernes(final Boolean viernes) {
		this.viernes = viernes;
	}

	public Boolean getSabado() {
		return sabado;
	}

	public void setSabado(final Boolean sabado) {
		this.sabado = sabado;
	}

	public Boolean getDomingo() {
		return domingo;
	}

	public void setDomingo(final Boolean domingo) {
		this.domingo = domingo;
	}

	public Date getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(final Date ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

	public Date[] getFestivos() {
		return festivos;
	}

	public void setFestivos(final Date[] festivos) {
		this.festivos = festivos;
	}

	public Boolean getReadMode() {
		return readMode;
	}

	public void setReadMode(final Boolean readMode) {
		this.readMode = readMode;
	}

}
