package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.webapp.bean.IntervaloHorario;
import org.librae.adminconfig.webapp.form.HorarioForm;
import org.librae.adminconfig.webapp.form.HorarioIntForm;

/**
 * Interfaz del delegado para la gestión de intervalos de horarios.
 * 
 * @author aropero
 */
public interface IHorarioIntDelegate {

	/**
	 * Método encargado de preparar la visualización del formulario de edición
	 * de un HorarioInt
	 * 
	 * @param form
	 *            , HorarioIntForm formulario de edición de un HorarioInt
	 * @param key
	 *            , posición de listado que estamos editando.
	 * @param horarioForm
	 *            , formulario del Horario que estamos editando.
	 */
	public List<IntervaloHorario> prepararDatosVista(HorarioIntForm form, String key,
			HorarioForm horarioForm, List<HorarioInt> intervalos);

	/**
	 * Método que realiza las validaciones oportunas, lanzando los mensajes de
	 * error correspondientes si es necesario.
	 * 
	 * @param form
	 *            , formulario de HorarioInt
	 */
	public void validarIntervalo(HorarioIntForm form);

	/**
	 * Método que nos devuelve el listado de intervalos horarios del Horario
	 * modificado, según hayamos añadido o editado un intervalo del mismo.
	 * 
	 * @param horarioIntForm
	 *            , formulario del intervalo horario creado o modificado.
	 * @param key
	 *            , posición del HorarioInt a modificar.
	 * @param horariosInterv
	 *            , listado de intervalos horarios del Horario.
	 * @return listado de intervalos horarios del Horario modificado
	 */
	public List modificarIntervalos(HorarioIntForm horarioIntForm, String key,
			List horariosInterv);

	/**
	 * Actualizamos el listado de Horas de HorarioInt con el nuevo Horario
	 * creado.
	 * 
	 * @param horarioIntForm
	 *            , formulario de HorarioInt en session, sirve para recuperar
	 *            los datos que teniamos y actualizarlos con los cambios.
	 * @param form
	 *            , formulario de HorarioInt a actualizar
	 * @param sesion
	 * @return HorarioIntForm, formulario de HorarioInt actualizado
	 */
	public HorarioIntForm actualizarListIntervalos(
			HorarioIntForm horarioIntForm, HorarioIntForm form);

	/**
	 * Método que actualiza los días laborables según el Calendario que posee la
	 * Biblioteca (modo edición)
	 * 
	 * @param idBiblioteca
	 *            , ID de la biblioteca
	 * @return String, con los días de la semana.
	 */
	public String actLabSegunCalendario(Long idBiblioteca);

	/**
	 * Validamos el HorarioInt creado antes de añadirlo al Horario.
	 * 
	 * @param form
	 *            , HorarioIntForm a validar.
	 */
	public void validarFormulario(HorarioIntForm form, List<IntervaloHorario> intervalos);

	/**
	 * Método que elimina un HorarioInt.
	 * 
	 * @param formHorInt
	 *            , formulario de edición del HorarioInt a eliminar.
	 * @param key
	 *            , posición en el listado del HorarioInt a eliminar.
	 * @param intervHor
	 *            , listado de HorarioInt.
	 */
	public List eliminarIntervalo(HorarioIntForm formHorInt, String key,
			List intervHor);
}
