package org.librae.adminconfig.webapp.delegate.impl;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import org.librae.adminconfig.webapp.form.CalendarioForm;
import org.librae.adminconfig.webapp.delegate.ICalendarioDelegate;
import java.util.Iterator;

/**
 * Delegado para la getion de calendarios.
 *
 */
public class CalendarioDelegateImpl implements ICalendarioDelegate {

    /**
     * @see org.librae.adminconfig.webapp.delegate.ICalendarioDelegate#validar(CalendarioForm, CalendarioForm)
     */
    public String validar(CalendarioForm nuevoCalendario, CalendarioForm antiguoCalendario){
        String res="";

        if (nuevoCalendario==null){
            res="ERROR_NO_DATOS";
        }

        //1. debido a un bug del componente calendario hay que eliminar una posible
        //fecha de 1900
        if (res.equals("") && nuevoCalendario.getFestivos()!=null && nuevoCalendario.getFestivos().length==1){
            final Calendar d = new GregorianCalendar();
            d.setTime((nuevoCalendario.getFestivos())[0]);
            if (d!=null && d.get(Calendar.YEAR)==1900){
                nuevoCalendario.setFestivos(null);
            }
        }

        //2. Se debe seleccionar algún día de la semana
        if ("".equals(res) && !(nuevoCalendario.getLunes().booleanValue() ||
                nuevoCalendario.getMartes().booleanValue() ||
                nuevoCalendario.getMiercoles().booleanValue() ||
                nuevoCalendario.getJueves().booleanValue() ||
                nuevoCalendario.getViernes().booleanValue() ||
                nuevoCalendario.getSabado().booleanValue() ||
                nuevoCalendario.getDomingo().booleanValue())){

            res="ERROR_CALENDARIO_NO_LABORABLES";
        }

        //3. Los nuevos dias festivos deben ser mayores o iguales al dia de hoy
        if ("".equals(res) && antiguoCalendario!=null && antiguoCalendario.getFestivos()!=null &&
                antiguoCalendario.getFestivos().length!=0){
            //el antiguo calendario tenia festivos declarados
            final List nuevosFestivos=obtenerDiferencias(nuevoCalendario.getFestivos(), antiguoCalendario.getFestivos());
            final Iterator<Calendar> it = nuevosFestivos.iterator();
            final Calendar hoy = new GregorianCalendar();
            hoy.setTime(new Date());
            Calendar fecha = new GregorianCalendar();
            while (it.hasNext() && "".equals(res)){
                fecha=it.next();
                if (fecha.before(hoy)){
                    res="ERROR_CALENDARIO_NUEVO_FESTIVO_INVALIDO";
                }
            }
        }

        //4. No se permite la eliminacion de festivos menores al dia de hoy
        if (res.equals("") && antiguoCalendario!=null && antiguoCalendario.getFestivos()!=null &&
                antiguoCalendario.getFestivos().length!=0){
            //el antiguo calendario tenia festivos declarados
            final List festivosBorrados=obtenerDiferencias(antiguoCalendario.getFestivos(), nuevoCalendario.getFestivos());
            final Iterator<Calendar> it = festivosBorrados.iterator();
            final Calendar hoy = new GregorianCalendar();
            hoy.setTime(new Date());
            Calendar fecha = new GregorianCalendar();
            while (it.hasNext() && "".equals(res)){
                fecha=it.next();
                if (fecha.before(hoy)){
                    res="ERROR_CALENDARIO_BORRADO_FESTIVO_INVALIDO";
                }
            }
        }

        return res;
    }



    /**
     * Método encargadod e devolver una lista con todos los elementos que
     * se encuentran en arrayA y no estan en arrayB
     *
     * @return List
     */
    private List obtenerDiferencias (Date[] arrayA, Date[] arrayB){
        final List res = new ArrayList();

        for (int i=0; i<arrayA.length; i++){
            final Calendar fecha = new GregorianCalendar();
            fecha.setTime(arrayA[i]);
            if (!contiene(arrayB, fecha)){
                res.add(fecha);
            }
        }

        return res;
    }

    /**
     * Método encargado de comprobar si la fecha indicada se encuentra en la lista
     * Sin tener en cuenta la hora
     *
     * @return List
     */
    private boolean contiene(Date[] arrayFechas, Calendar fecha){
        boolean contiene=false;

        if (arrayFechas!=null && arrayFechas.length!=0){

            final Calendar f = new GregorianCalendar();
            for (int i=0; i<arrayFechas.length && !contiene; i++){
                f.setTime(arrayFechas[i]);
                contiene=(f.get(Calendar.YEAR)==fecha.get(Calendar.YEAR) &&
                        f.get(Calendar.DATE)==fecha.get(Calendar.DATE) &&
                        f.get(Calendar.MONTH)==fecha.get(Calendar.MONTH));
            }
        }

        return contiene;
    }

}
