package org.librae.common.webapp.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;

/**
 * Clase usada como contenedor de los mensajes que se mostrarán al usuario.
 */
public class WindowMessages implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6245974682779963933L;

	/**
     * Lista de mensajes de error.
     */
    private List<String> errorMessages   = new ArrayList<String>();

    /**
     * Lista de mensajes de advertencia.
     */
    private List<String> warningMessages = new ArrayList<String>();

    /**
     * Lista de mensajes de información.
     */
    private List<String> infoMessages    = new ArrayList<String>();

    /**
     * Devuelve la lista de mensajes de error.
     * 
     * @return Lista de mensajes de error.
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * Devuelve la lista de mensajes de advertencia.
     * 
     * @return Lista de mensajes de advertencia.
     */
    public List<String> getWarningMessages() {
        return warningMessages;
    }

    /**
     * Devuelve la lista de mensajes de información.
     * 
     * @return Lista de mensajes de información.
     */
    public List<String> getInfoMessages() {
        return infoMessages;
    }

    /**
     * Constructor por defecto.
     */
    public WindowMessages() {
        super();
    }

    /**
     * Comprueba si existe algún mensaje de error, sea del tipo que sea.
     * 
     * @return <code>true</code> si no existe ningún mensaje de error y
     *         <code>false</code> en caso contrario.
     */
    public boolean isEmtpy() {
        return errorMessages.isEmpty() && warningMessages.isEmpty()
                && infoMessages.isEmpty();
    }

    /**
     * Constructor a partir de una excepción.
     * 
     * @param mfe
     *            Excepción del módulo de facturación.
     */
    public WindowMessages(LibraeException le) {
        if (le != null) {
            errorMessages.add(getMensaje(le.getMessage()));
        }
    }

    /**
     * Añade a la lista un mensaje de error.
     * 
     * @param mensaje
     *            Texto del mensaje a añadir.
     */
    public void addErrorMessage(String error) {
        errorMessages.add(getMensaje(error));
    }

    /**
     * Añade a la lista un mensaje de error.
     * 
     * @param codError
     *            codigo del properti global de mensajes de error
     */
    public void addErrorMessageByCode(String codError) {
        errorMessages.add(getMensaje(MensajesError.get(codError)));
    }

    /**
     * Añade a la lista un mensaje de error.
     * 
     * @param propertiFile
     *            ruta del archivo properti que contiene el codigo
     * @param codError
     *            codigo del properti global de mensajes de error
     */
    public void addErrorMessageByCode(String propertiFile, String codError) {
        errorMessages
                .add(getMensaje(MensajesError.get(propertiFile, codError)));
    }

    /**
     * Añade a la lista un mensaje de warning.
     * 
     * @param mensaje
     *            Texto del mensaje a añadir.
     */
    public void addWarningMessage(String warning) {
        warningMessages.add(getMensaje(warning));
    }

    /**
     * Añade a la lista un mensaje de warning.
     * 
     * @param codWarning
     *            codigo del properti global de mensajes de error
     */
    public void addWarningMessageByCode(String codWarning) {
        warningMessages.add(getMensaje(MensajesError.get(codWarning)));
    }

    /**
     * Añade a la lista un mensaje de warning.
     * 
     * @param propertiFile
     *            ruta del archivo properti que contiene el codigo
     * @param codWarning
     *            codigo del properti global de mensajes de error
     */
    public void addWarningMessageByCode(String propertiFile, String codWarning) {
        warningMessages.add(getMensaje(MensajesError.get(propertiFile,
                codWarning)));
    }

    /**
     * Añade a la lista un mensaje de info.
     * 
     * @param mensaje
     *            Texto del mensaje a añadir.
     */
    public void addInfoMessage(String warning) {
        infoMessages.add(getMensaje(warning));
    }

    /**
     * Añade a la lista un mensaje de info.
     * 
     * @param codInfo
     *            codigo del properti global de mensajes de error
     */
    public void addInfoMessageByCode(String codInfo) {
        infoMessages.add(getMensaje(MensajesError.get(codInfo)));
    }

    /**
     * Añade a la lista un mensaje de info.
     * 
     * @param propertiFile
     *            ruta del archivo properti que contiene el codigo
     * @param codInfo
     *            codigo del properti global de mensajes de error
     */
    public void addInfoMessageByCode(String propertiFile, String codInfo) {
        infoMessages.add(getMensaje(MensajesError.get(propertiFile, codInfo)));
    }

    /**
     * Método usado para asegurarse de que nunca se guardará un mensaje de error
     * nulo.
     * 
     * @param mensaje
     *            Mensaje original.
     * @return Mensaje original o el mensaje genérico en caso de que fuera nulo.
     */
    private String getMensaje(String mensaje) {
        return mensaje == null ? MensajesError.get("ERROR_GENERICO") : mensaje;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void setWarningMessages(List<String> warningMessages) {
        this.warningMessages = warningMessages;
    }

    public void setInfoMessages(List<String> infoMessages) {
        this.infoMessages = infoMessages;
    }
}
