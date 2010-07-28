package org.librae.adminconfig.webapp.delegate;

import java.util.List;

import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Calendario;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.model.TipoBiblioteca;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.webapp.bean.IntervaloHorario;
import org.librae.adminconfig.webapp.form.BibliotecaForm;
import org.librae.adminconfig.webapp.form.CalendarioForm;
import org.librae.adminconfig.webapp.form.DireccionForm;
import org.librae.adminconfig.webapp.form.HorarioForm;
import org.librae.common.webapp.delegate.IFavoritoDelegate;
import org.librae.common.webapp.delegate.ISearchDelegate;

/**
 * Interfaz del delegado para la getion de bibliotecas.
 * 
 * @author aropero
 */
public interface IBibliotecaDelegate extends ISearchDelegate<Biblioteca>,
        IFavoritoDelegate {

    /**
     * Método encargado de guardar la biblioteca en base de datos
     * 
     * @param form
     *            , Formulario de edición de una biblioteca.
     * @param padre
     *            , biblioteca padre (Árbol de bibliotecas)
     * @param calendarioForm
     *            , formulario del calendario asociado a la biblioteca
     * @param horarioForm
     *            , formulario del horario asociado a la biblioteca
     * @param direccionForm
     *            , formulario de la direccion asociada a la biblioteca
     * @return Biblioteca creada.
     **/
    public Biblioteca guardarBiblioteca(final BibliotecaForm form,
            final Biblioteca padre, CalendarioForm calendarioForm,
            final HorarioForm horarioForm, final DireccionForm direccionForm,
            List<HorarioInt> horarios);

    /**
     * Método encargado de eliminar una biblioteca de base de datos
     * 
     * @param idBiblioteca
     *            , identificador de la biblioteca a eliminar
     * @param usuario
     *            , usuario existente en la session.
     */
    public void eliminar(Long idBiblioteca, Usuario usuario);

    /**
     * Método encargado de preparar la visualización del formulario de edición
     * de una biblioteca
     * 
     * @param form
     *            , BibliotecaForm formulario de edición de biblioteca
     * @param calendarioForm
     * @return String con los valores asociados a los codigos.
     */
    public String prepararDatosVista(BibliotecaForm form,
            CalendarioForm calendarioForm);

    /**
     * Método que obtiene una Biblioteca a través de su id.
     * 
     * @param id
     *            , identificador de la biblioteca.
     * @return Biblioteca
     */
    public Biblioteca obtenerBiblioteca(Long id);

    /**
     * Método que obtiene todas las bibliotecas existentes en BBDD.
     * 
     * @return listado de bibliotecas.
     */
    public List<Biblioteca> obtenerBibliotecas();

    /**
     * Método que obtiene todas los tipos de bibliotecas existentes en BBDD.
     * 
     * @return listado de tipos de bibliotecas.
     */
    public List<TipoBiblioteca> obtenerTiposBiblioteca();

    /**
     * Método que obtiene un Calendario a través de su id.
     * 
     * @param idCalendario
     *            , identificador del Calendario.
     * @return Calendario
     */
    public Calendario obtenerCalendario(Long idCalendario);

    /**
     * Metodo que realiza las validaciones oportunas del formulario antes de
     * guardar una biblioteca en BBDD.
     * 
     * @param formulario
     *            , BibliotecaForm
     * @param nodo
     *            , nodo seleccionado en el Árbol de bibliotecas.
     * @param sizeMax
     *            , maximo tamaño de los ficheros upload definido en web.xml
     * @return Biblioteca padre
     */
    public Biblioteca validarForm(final BibliotecaForm formulario, Long nodo,
            long sizeMax);

    /**
     * Obtiene el tipo de la biblioteca por el codigo.
     * 
     * @param tipoBiblioteca
     * @return
     */
    public TipoBiblioteca getTipoBibliotecaByCodigo(String tipoBiblioteca);

    /**
     * Obtiene el tipo de identificacion por su identificador.
     * 
     * @param idTipoBiblioteca
     * @return
     */
    public TipoBiblioteca getTipoBiblioteca(Long idTipoBiblioteca);

    /**
     * Busca el identificador de una biblioteca que se le pase la descripcion.
     * 
     * @param descripcion
     * @return
     */
    public Long getIdBibliotecaByDescripcion(String descripcion);

    /**
     * Busca el identificador de una biblioteca que se le pase la descripcion
     * por codigo.
     * 
     * @param descripcion
     * @return
     */
    public Long getIdBibliotecaByCodigo(String descripcion);
}
