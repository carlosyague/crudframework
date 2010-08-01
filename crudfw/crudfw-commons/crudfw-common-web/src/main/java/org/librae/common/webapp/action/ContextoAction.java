package org.librae.common.webapp.action;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.Constants;

/**
 * Action-JSF utilizado para la recuperacion del contexto.
 * 
 * @author jcisneros
 */
public class ContextoAction extends BasePage implements Serializable {

    protected final Log       log                         = LogFactory
                                                                  .getLog(ContextoAction.class);

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID            = 1L;

    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoBiblioteca            = null;

    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoParametro             = null;

    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoCatalogo              = null;

    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoRol                   = null;

    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoUsuario               = null;

    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoPoliticaPrestamo      = null;

    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoPoliticaReserva       = null;

    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoConsorcio             = null;

    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoTipoEjemplar          = null;
    
    /**
     * Guarda el contexto para el codigo de la biblioteca.
     */
    private final String      codigoBibliotecaConsorciada = null;

    /**
     * Guarda el contexto para el número de lector.
     */
    private final String      numeroLector                = null;

    /**
     * Guarda el contexto para el número de lector.
     */
    private final String      nombreLector                = null;

    /**
     * Guarda el contexto para el autor de un registro.
     */
    private final String      autorRegistro               = null;

    /**
     * Guarda el contexto para el título de un registro.
     */
    private final String      tituloRegistro              = null;

    /**
     * Guarda el contexto para el isbn de un registro.
     */
    private final String      isbnRegistro                = null;

    /**
     * Guarda el contexto para el código de barras de un ejemplar.
     */
    private final String      codigoBarrasEjemplar        = null;

    /**
     * Guarda el contexto para el código de un tipo lector.
     */
    private final String      codigoTipoLector            = null;

    /**
     * Guarda el contexto para la signatura de un ejemplar.
     */
    private final String      signaturaEjemplar           = null;

    /**
     * Guarda el contexto para la autoridad de una autoridad.
     */
    private final String      autoridadAutoridad          = null;

    /**
     * Guarda el contexto para la identificación de una petición
     */
    private final Long        identificadorPeticion       = null;

    /**
     * Guarda el contexto para la identificación de una reserva
     */
    private final Long        identificadorReserva        = null;

    /**
     * Guarda el contexto para la autoridad de una autoridad.
     */
    private final String      nombreTransformacion        = null;

    // Getters && Setters

    public String getNombreTransformacion() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_NOMBRE_TRANSFORMACION);

    }

    public void setNombreTransformacion(String nombreTransformacion) {
        setSessionManagerParam(Constants.CONTEXTO_NOMBRE_TRANSFORMACION,
                nombreTransformacion);
    }

    public Long getIdentificadorReserva() {
        return ((Long) getSessionManagerParam(Constants.CONTEXTO_ID_RESERVA));
    }

    public void setIdentificadorReserva(Long identificadorReserva) {
        setSessionManagerParam(Constants.CONTEXTO_ID_RESERVA,
                identificadorReserva);
    }

    public Long getIdentificadorPeticion() {
        return (Long) getSessionManagerParam(Constants.CONTEXTO_ID_PETICION);
    }

    public void setIdentificadorPeticion(Long identificadorPeticion) {
        setSessionManagerParam(Constants.CONTEXTO_ID_PETICION,
                identificadorPeticion);
    }

    /**
     * Recupera el codigo de la biblioteca.
     */
    public String getCodigoBiblioteca() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_BIBLIOTECA);
    }

    /**
     * Modifica el codigo de la biblioteca en el contexto.
     * 
     * @param codigoBiblioteca
     *            codigo de la biblioteca.
     */
    public void setCodigoBiblioteca(final String codigoBiblioteca) {
        setSessionManagerParam(Constants.CONTEXTO_CODIGO_BIBLIOTECA,
                codigoBiblioteca);
    }

    /**
     * @return the numeroLector
     */
    public String getNumeroLector() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_NUMERO_LECTOR);
    }

    /**
     * @param numeroLector
     *            the numeroLector to set
     */
    public void setNumeroLector(final String numeroLector) {
        setSessionManagerParam(Constants.CONTEXTO_NUMERO_LECTOR, numeroLector);
    }

    /**
     * @return the nombreLector
     */
    public String getNombreLector() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_NOMBRE_LECTOR);
    }

    /**
     * @param nombreLector
     *            the nombreLector to set
     */
    public void setNombreLector(final String nombreLector) {
        setSessionManagerParam(Constants.CONTEXTO_NOMBRE_LECTOR, nombreLector);
    }

    /**
     * @return the autorRegistro
     */
    public String getAutorRegistro() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_AUTOR_REGISTRO);
    }

    /**
     * @param autorRegistro
     *            the autorRegistro to set
     */
    public void setAutorRegistro(String autorRegistro) {
        setSessionManagerParam(Constants.CONTEXTO_AUTOR_REGISTRO, autorRegistro);
    }

    /**
     * @return the tituloRegistro
     */
    public String getTituloRegistro() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_TITULO_REGISTRO);
    }

    /**
     * @param tituloRegistro
     *            the tituloRegistro to set
     */
    public void setTituloRegistro(String tituloRegistro) {
        setSessionManagerParam(Constants.CONTEXTO_TITULO_REGISTRO,
                tituloRegistro);
    }

    /**
     * @return the isbnRegistro
     */
    public String getIsbnRegistro() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_ISBN_REGISTRO);
    }

    /**
     * @param isbnRegistro
     *            the isbnRegistro to set
     */
    public void setIsbnRegistro(String isbnRegistro) {
        setSessionManagerParam(Constants.CONTEXTO_ISBN_REGISTRO, isbnRegistro);
    }

    /**
     * @return the codigoBarrasEjemplar
     */
    public String getCodigoBarrasEjemplar() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_BARRAS_EJEMPLAR);
    }

    /**
     * @param codigoBarrasEjemplar
     *            the codigoBarrasEjemplar to set
     */
    public void setCodigoBarrasEjemplar(String codigoBarrasEjemplar) {
        setSessionManagerParam(Constants.CONTEXTO_CODIGO_BARRAS_EJEMPLAR,
                codigoBarrasEjemplar);
    }

    /**
     * @return the signaturaEjemplar
     */
    public String getSignaturaEjemplar() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_SIGNATURA_EJEMPLAR);
    }

    /**
     * @param signaturaEjemplar
     *            the signaturaEjemplar to set
     */
    public void setSignaturaEjemplar(String signaturaEjemplar) {
        setSessionManagerParam(Constants.CONTEXTO_SIGNATURA_EJEMPLAR,
                signaturaEjemplar);
    }

    /**
     * @return the autoridadAutoridad
     */
    public String getAutoridadAutoridad() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_AUTORIDAD_AUTORIDAD);
    }

    /**
     * @param autoridadAutoridad
     *            the autoridadAutoridad to set
     */
    public void setAutoridadAutoridad(String autoridadAutoridad) {
        setSessionManagerParam(Constants.CONTEXTO_AUTORIDAD_AUTORIDAD,
                autoridadAutoridad);
    }

    public String getCodigoParametro() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_PARAMETRO);
    }

    public void setCodigoParametro(String codigoParametro) {
        setSessionManagerParam(Constants.CONTEXTO_CODIGO_PARAMETRO,
                codigoParametro);
    }

    public String getCodigoCatalogo() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_CATALOGO);
    }

    public void setCodigoCatalogo(String codigoCatalogo) {
        setSessionManagerParam(Constants.CONTEXTO_CODIGO_CATALOGO,
                codigoCatalogo);
    }

    public String getCodigoRol() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_ROL);
    }

    public void setCodigoRol(String codigoRol) {
        setSessionManagerParam(Constants.CONTEXTO_CODIGO_ROL, codigoRol);
    }

    public String getCodigoUsuario() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_USUARIO);
    }

    public void setCodigoUsuario(String codigoUsuario) {
        setSessionManagerParam(Constants.CONTEXTO_CODIGO_USUARIO, codigoUsuario);
    }

    public String getCodigoPoliticaPrestamo() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_POLITCA_PRESTAMO);
    }

    public void setCodigoPoliticaPrestamo(String codigoPoliticaPrestamo) {
        setSessionManagerParam(Constants.CONTEXTO_CODIGO_POLITCA_PRESTAMO,
                codigoPoliticaPrestamo);
    }

    public String getCodigoPoliticaReserva() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_POLITCA_RESERVA);
    }

    public void setCodigoPoliticaReserva(String codigoPoliticaReserva) {
        setSessionManagerParam(Constants.CONTEXTO_CODIGO_POLITCA_RESERVA,
                codigoPoliticaReserva);
    }

    public String getCodigoConsorcio() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_CONSORCIO);
    }

    public void setCodigoConsorcio(String codigoConsorcio) {
        setSessionManagerParam(Constants.CONTEXTO_CODIGO_CONSORCIO,
                codigoConsorcio);
    }

    public String getCodigoBibliotecaConsorciada() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_BIBLIOTECA_CONSORCIADA);
    }

    public void setCodigoBibliotecaConsorciada(
            String codigoBibliotecaConsorciada) {
        setSessionManagerParam(
                Constants.CONTEXTO_CODIGO_BIBLIOTECA_CONSORCIADA,
                codigoBibliotecaConsorciada);
    }

    public String getCodigoTipoLector() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_TIPO_LECTOR);
    }
    
    public void setCodigoTipoLector(
            String codigoTipoLector){
        setSessionManagerParam(
                Constants.CONTEXTO_CODIGO_TIPO_LECTOR,
                codigoTipoLector);
    }


    public String getCodigoTipoEjemplar() {
        return (String) getSessionManagerParam(Constants.CONTEXTO_CODIGO_TIPO_EJEMPLAR);
    }
    
    public void setCodigoTipoEjemplar( 
        String codigoTipoEjemplar){
            setSessionManagerParam(
                    Constants.CONTEXTO_CODIGO_TIPO_EJEMPLAR,
                    codigoTipoEjemplar);
    }
}
