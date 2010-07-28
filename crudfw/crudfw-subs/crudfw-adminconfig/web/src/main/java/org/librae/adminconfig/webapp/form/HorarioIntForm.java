package org.librae.adminconfig.webapp.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.webapp.bean.IntervaloHorario;
import org.librae.common.webapp.form.ISearchForm;

/**
 * Formulario para la creacion de horarios
 */
public class HorarioIntForm implements ISearchForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8522996922345207871L;

	private Long                   idHorarioInt;

    /**
     * Dias laborables (0=no marcado; 1=marcado)
     */
    private Boolean                lunes      = Boolean.FALSE;
    private Boolean                martes     = Boolean.FALSE;
    private Boolean                miercoles  = Boolean.FALSE;
    private Boolean                jueves     = Boolean.FALSE;
    private Boolean                viernes    = Boolean.FALSE;
    private Boolean                sabado     = Boolean.FALSE;
    private Boolean                domingo    = Boolean.FALSE;

    /**
     * Fecha de inicio del intervalo en que se aplica el horario. Primer día en
     * el que se aplica el horarioInt.
     */
    private Date                   comienzo;

    /**
     * Fecha de fin del intervalo en que se aplica el horarioInt. Último día que
     * se aplica el horarioInt.
     */
    private Date                   fin;

    /** Listado de los intervalos horarios */
    private List<IntervaloHorario> intervalos = new ArrayList<IntervaloHorario>();

    /**
     * Hora de inicio del intervalo horario
     */
    private String                 horaI;

    /**
     * minutos de inicio del intervalo horario
     */
    private String                 minutoI;

    /**
     * Hora de fin del intervalo horario
     */
    private String                 horaF;

    /**
     * minutos de fin del intervalo horario
     */
    private String                 minutoF;

    /** modo de Edición o Lectura de un horarioInt */
    private Boolean                readMode;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(final HorarioInt horarioInt) {
    	if (horarioInt != null) {
    		if (horarioInt.getDiaSemana() != null) {
	            if (horarioInt.getDiaSemana().indexOf("L") != -1) {
	                setLunes(Boolean.TRUE);
	            }
	            if (horarioInt.getDiaSemana().indexOf("M") != -1) {
	                setMartes(Boolean.TRUE);
	            }
	            if (horarioInt.getDiaSemana().indexOf("X") != -1) {
	                setMiercoles(Boolean.TRUE);
	            }
	            if (horarioInt.getDiaSemana().indexOf("J") != -1) {
	                setJueves(Boolean.TRUE);
	            }
	            if (horarioInt.getDiaSemana().indexOf("V") != -1) {
	                setViernes(Boolean.TRUE);
	            }
	            if (horarioInt.getDiaSemana().indexOf("S") != -1) {
	                setSabado(Boolean.TRUE);
	            }
	            if (horarioInt.getDiaSemana().indexOf("D") != -1) {
	                setDomingo(Boolean.TRUE);
	            }
    		}
        	setComienzo(horarioInt.getFechaInicio());
        	setFin(horarioInt.getFechaFin());
	        /* Listado de intervalos horarios */
	        if ((horarioInt.getHoraInicio1() != null)
	                && (horarioInt.getHoraFin1() != null)) {
	            final IntervaloHorario intervaloH1 = new IntervaloHorario(
	                    horarioInt.getHoraInicio1(), horarioInt.getHoraFin1());
	            intervalos.add(intervaloH1);
	        }
	        if ((horarioInt.getHoraInicio2() != null)
	                && (horarioInt.getHoraFin2() != null)) {
	            final IntervaloHorario intervaloH2 = new IntervaloHorario(
	                    horarioInt.getHoraInicio2(), horarioInt.getHoraFin2());
	            intervalos.add(intervaloH2);
	        }
	        if ((horarioInt.getHoraInicio3() != null)
	                && (horarioInt.getHoraFin3() != null)) {
	            final IntervaloHorario intervaloH3 = new IntervaloHorario(
	                    horarioInt.getHoraInicio3(), horarioInt.getHoraFin3());
	            intervalos.add(intervaloH3);
	        }
	        if ((horarioInt.getHoraInicio4() != null)
	                && (horarioInt.getHoraFin4() != null)) {
	            final IntervaloHorario intervaloH4 = new IntervaloHorario(
	                    horarioInt.getHoraInicio4(), horarioInt.getHoraFin4());
	            intervalos.add(intervaloH4);
	        }
	        if ((horarioInt.getHoraInicio5() != null)
	                && (horarioInt.getHoraFin5() != null)) {
	            final IntervaloHorario intervaloH5 = new IntervaloHorario(
	                    horarioInt.getHoraInicio5(), horarioInt.getHoraFin5());
	            intervalos.add(intervaloH5);
	        }
    	}
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public HorarioInt toEntity() {
        return this.toEntity(new HorarioInt(null));
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public HorarioInt toEntity(final HorarioInt horarioInt) {

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
        horarioInt.setDiaSemana(laborables.toString());

        horarioInt.setFechaInicio(comienzo);
        horarioInt.setFechaFin(fin);

        final Iterator<IntervaloHorario> it = intervalos.iterator();
        for (int i = 0; i <= intervalos.size() - 1; i++) {
            final IntervaloHorario intervaloH = it.next();
            if (i == 0) {
                horarioInt.setHoraInicio1(intervaloH.getHoraInicio());
                horarioInt.setHoraFin1(intervaloH.getHoraFin());
            } else if (i == 1) {
                horarioInt.setHoraInicio2(intervaloH.getHoraInicio());
                horarioInt.setHoraFin2(intervaloH.getHoraFin());
            } else if (i == 2) {
                horarioInt.setHoraInicio3(intervaloH.getHoraInicio());
                horarioInt.setHoraFin3(intervaloH.getHoraFin());
            } else if (i == 3) {
                horarioInt.setHoraInicio4(intervaloH.getHoraInicio());
                horarioInt.setHoraFin4(intervaloH.getHoraFin());
            } else if (i == 4) {
                horarioInt.setHoraInicio5(intervaloH.getHoraInicio());
                horarioInt.setHoraFin5(intervaloH.getHoraFin());
            }
        }

        return horarioInt;
    }

    /**
     * Método que sirve para actualizar los días de la semana que son
     * laborables.
     * 
     * @param diasSemana
     *            , String con los días de la semana.
     */
    public void actualizaDiasSemana(final String diasSemana) {
        if (diasSemana != null) {
            if (diasSemana.indexOf('L') != -1) {
                setLunes(Boolean.TRUE);
            }
            if (diasSemana.indexOf('M') != -1) {
                setMartes(Boolean.TRUE);
            }
            if (diasSemana.indexOf('X') != -1) {
                setMiercoles(Boolean.TRUE);
            }
            if (diasSemana.indexOf('J') != -1) {
                setJueves(Boolean.TRUE);
            }
            if (diasSemana.indexOf('V') != -1) {
                setViernes(Boolean.TRUE);
            }
            if (diasSemana.indexOf('S') != -1) {
                setSabado(Boolean.TRUE);
            }
            if (diasSemana.indexOf('D') != -1) {
                setDomingo(Boolean.TRUE);
            }
        } else {
            setLunes(Boolean.FALSE);
            setMartes(Boolean.FALSE);
            setMiercoles(Boolean.FALSE);
            setJueves(Boolean.FALSE);
            setViernes(Boolean.FALSE);
            setSabado(Boolean.FALSE);
            setDomingo(Boolean.FALSE);
        }
    }

    /**
     * Método que nos indique si se ha seleccionado algún día de la semana.
     * 
     * @param boolean , true si se ha seleccionado algún día de la semana, false
     *        en caso contrario.
     */
    public boolean algunaSeleccionDia() {

        boolean selected = true;

        if (getLunes().equals(Boolean.FALSE)
                && (getMartes().equals(Boolean.FALSE))
                && (getMiercoles().equals(Boolean.FALSE))
                && (getJueves().equals(Boolean.FALSE))
                && (getViernes().equals(Boolean.FALSE))
                && (getSabado().equals(Boolean.FALSE))
                && (getDomingo().equals(Boolean.FALSE))) {
            selected = false;
        }

        return selected;
    }

    // Getters && Setter

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

    public Boolean getReadMode() {
        return readMode;
    }

    public void setReadMode(final Boolean readMode) {
        this.readMode = readMode;
    }

    public Date getComienzo() {
        return comienzo;
    }

    public void setComienzo(final Date comienzo) {
        this.comienzo = comienzo;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(final Date fin) {
        this.fin = fin;
    }

    public Long getIdHorarioInt() {
        return idHorarioInt;
    }

    public void setIdHorarioInt(final Long idHorarioInt) {
        this.idHorarioInt = idHorarioInt;
    }

    public List<IntervaloHorario> getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(final List<IntervaloHorario> intervalos) {
        this.intervalos = intervalos;
    }

    public String getHoraI() {
        return horaI;
    }

    public void setHoraI(final String horaI) {
        this.horaI = horaI;
    }

    public String getHoraF() {
        return horaF;
    }

    public void setHoraF(final String horaF) {
        this.horaF = horaF;
    }

    public String getMinutoI() {
        return minutoI;
    }

    public void setMinutoI(final String minutoI) {
        this.minutoI = minutoI;
    }

    public String getMinutoF() {
        return minutoF;
    }

    public void setMinutoF(final String minutoF) {
        this.minutoF = minutoF;
    }

    public void fromMap(Map<String, Object> criterios) {
        // TODO Auto-generated method stub

    }

    public Map<String, Object> toMap() {
        // TODO Auto-generated method stub
        return null;
    }
}
