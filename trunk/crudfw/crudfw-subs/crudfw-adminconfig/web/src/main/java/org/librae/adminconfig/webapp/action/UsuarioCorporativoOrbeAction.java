package org.librae.adminconfig.webapp.action;

import java.io.Serializable;

import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.webapp.delegate.IUsuarioDelegate;
import org.librae.adminconfig.webapp.form.UsuarioForm;
import org.librae.common.Constants;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.webapp.action.BasePage;
import org.librae.common.webapp.util.WindowMessages;

/**
 * Action para las acciones del usuario.
 * 
 * @author jcisneros
 */
public class UsuarioCorporativoOrbeAction extends BasePage implements
        Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = -8930564567651768148L;

    /**
     * Delegado de usuarios.
     */
    private IUsuarioDelegate  delegate;

    /**
     * Formulario de usuarios.
     */
    private UsuarioForm       usuarioForm      = new UsuarioForm();

    public String getInit() {
        // super.insertarHistorial(delegate
        // .getMenuItem(super.getUrl(), null, null));
        return "";
    }

    /**
     * Modificar la clave de un usuario.
     * 
     * @return pagina de cambiar clave.
     */
    public String cambiarClave() {
        final WindowMessages wm = new WindowMessages();

        try {
            delegate.cambiarClave(usuarioForm);

            setMensajeExito("MENSAJE_EXITO_CLAVE",
                    MensajesError.PROPERTI_ADMINCONFIG);
            // wm.addInfoMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
            // "OK_CAMBIAR_CLAVE");
            // setWindowMessages(wm);
        } catch (final LibraeException e) {
            log.info("Error al cambiar la clave...", e);
            wm.addErrorMessage(e.getMessage());
            setWindowMessages(wm);
        } catch (final Exception e) {
            log.error("Error al cambiar la clave...", e);
            wm.addErrorMessage("ERROR_CAMBIAR_CLAVE");
            setWindowMessages(wm);
        }
        return "cambiarClave";
    }

    /**
     * Modificar la clave de un usuario.
     * 
     * @return pagina de cambiar clave.
     */
    public String cambiarClaveUsuario() {
        final WindowMessages wm = new WindowMessages();
        String page = "";
        try {
            final Usuario usuario = (Usuario) getSessionManager().getAttribute(
                    Constants.USUARIO_SESSION_PARAM);
            usuarioForm.setUsuario(usuario.getUsuario());
            delegate.cambiarClave(usuarioForm);

            wm.addInfoMessageByCode(MensajesError.PROPERTI_ADMINCONFIG,
                    "OK_CAMBIAR_CLAVE");
            page = "cerrarCambiarClave";
        } catch (final LibraeException e) {
            log.info("Error al cambiar la clave...", e);
            wm.addErrorMessage(e.getMessage());
        } catch (final Exception e) {
            log.error("Error al cambiar la clave...", e);
            wm.addErrorMessage("ERROR_CAMBIAR_CLAVE");
        } finally {
            setWindowMessages(wm);
        }
        return page;
    }

    // Getters && Setters

    public IUsuarioDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(final IUsuarioDelegate delegate) {
        this.delegate = delegate;
    }

    public UsuarioForm getUsuarioForm() {
        return usuarioForm;
    }

    public void setUsuarioForm(final UsuarioForm usuarioForm) {
        this.usuarioForm = usuarioForm;
    }

}
