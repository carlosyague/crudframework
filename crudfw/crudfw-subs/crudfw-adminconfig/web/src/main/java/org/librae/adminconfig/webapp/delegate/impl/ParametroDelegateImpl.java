package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Parametro;
import org.librae.adminconfig.service.IParametroManager;
import org.librae.adminconfig.webapp.delegate.IParametroDelegate;
import org.librae.adminconfig.webapp.form.ParametroForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.springframework.dao.DataAccessException;

/**
 * Implementacion del interfaz IParametroDelegate.
 * 
 * @author jcisneros
 */
public class ParametroDelegateImpl implements IParametroDelegate, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2782127571601184032L;

    /**
     * Atributo para las trazas
     */
    protected final Log       log              = LogFactory
                                                       .getLog(UsuarioDelegateImpl.class);

    /**
     * Manager de catálogo.
     */
    private IParametroManager parametroManager;

    public void eliminar(Long idParametro) {
        try {
            parametroManager.eliminar(idParametro);
        } catch (final LibraeException le) {
            throw le;
        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException(dae,
                    MensajesError.PROPERTI_ADMINCONFIG);
        }
    }

    public Parametro guardarParametro(ParametroForm form) {
        Parametro parametro = null;
        try {
            if (form.getIdParametro() == null) {
                parametro = form.toEntity();
            } else {
                parametro = parametroManager.get(form.getIdParametro());
            }
            parametro = form.toEntity(parametro);
            parametro = parametroManager.guardarParametro(parametro);

        } catch (final DataAccessException dae) {
            throw ExceptionUtil.crearLibraeException("ERROR_GUARDAR_PARAMETRO",
                    MensajesError.PROPERTI_ADMINCONFIG, dae);
        }

        return parametro;
    }

    public Parametro getParametro(Long idParametro) {
        return parametroManager.get(idParametro);
    }

    // Getters && Setters

    public IParametroManager getParametroManager() {
        return parametroManager;
    }

    public void setParametroManager(IParametroManager parametroManager) {
        this.parametroManager = parametroManager;
    }
}
