package org.librae.adminconfig.webapp.delegate.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.webapp.delegate.IDireccionDelegate;
import org.librae.adminconfig.webapp.form.DireccionForm;

/**
 * Delegado para la getion de direcciones.
 * 
 * @author aropero
 */
public class DireccionDelegateImpl implements IDireccionDelegate {

    protected final Log log = LogFactory.getLog(DireccionDelegateImpl.class);

    /**
     * @see org.librae.adminconfig.webapp.delegate.IDireccionDelegate#validar(org.librae.adminconfig.webapp.form.DireccionForm)
     */
    public String validar(DireccionForm form) {
        String res = "";

        if (form.getDireccion() == null || form.getDireccion().equals("")) {
            res = "ERROR_DATOS_DIRECCION";

        }
        return res;
    }

}
