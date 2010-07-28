package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.service.IBibliotecaManager;
import org.librae.adminconfig.service.IUsuarioBibliotecaRolManager;
import org.librae.adminconfig.service.IUsuarioManager;
import org.librae.adminconfig.webapp.delegate.IUsuarioDelegate;
import org.librae.adminconfig.webapp.form.UsuarioForm;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.MensajesError;
import org.librae.common.util.ConvertUtil;
import org.librae.common.webapp.delegate.impl.FavoritoDelegateImpl;

/**
 * Implementacion del interfaz IUsuarioDelegate.
 * 
 * @author jcisneros
 * @author aropero
 */
public class UsuarioDelegateImpl extends FavoritoDelegateImpl implements
        IUsuarioDelegate, Serializable {

    /**
     * Serial Version UID
     */
    private static final long            serialVersionUID = 2782127571601184032L;

    /**
     * Atributo para las trazas
     */
    protected final Log                  log              = LogFactory
                                                                  .getLog(UsuarioDelegateImpl.class);

    /**
     * Manager de usuario.
     */
    private IUsuarioManager              usuarioManager;

    /**
     * Manager de usuario.
     */
    private IBibliotecaManager           bibliotecaManager;

    /**
     * Manager de usuario.
     */
    private IUsuarioBibliotecaRolManager usuarioBibliotecaRolManager;

    /**
     * {@inheritDoc}
     */
    public void cambiarClave(final UsuarioForm usuarioForm) {
        usuarioManager.cambiarClave(usuarioForm.getUsuario(), ConvertUtil
                .encodeMD5(usuarioForm.getClaveNueva()), ConvertUtil
                .encodeMD5(usuarioForm.getClaveRepetida()));
    }

    /**
     * {@inheritDoc}
     */
    public List<Usuario> buscar(final Map<String, Object> criterios) {
        return usuarioManager.buscar(criterios);
    }

    public Usuario guardarUsuario(final UsuarioForm form,
            List<UsuarioBibliotecaRol> rolesAsignados) {
        return guardarUsuario(form, rolesAsignados, Boolean.FALSE);

    }

    /**
     * {@inheritDoc}
     */
    public Usuario guardarUsuario(final UsuarioForm form,
            List<UsuarioBibliotecaRol> rolesAsignados, Boolean orbeActivo) {
        Usuario usuario = null;

        if (form.getIdUsuario() == null) {
            /* Estamos creando */
            usuario = form.toEntity();
        } else {
            /* Estamos modificando */
            usuario = usuarioManager.get(form.getIdUsuario());
        }

        if ((form.getIdUsuario() == null)
                && (!form.getClaveRepetida().equals(form.getClave()))) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_CLAVE_REPETIDA_ERRONEA");
        }

        usuario = form.toEntity(usuario);
        usuario = usuarioManager.salvarUsuario(usuario, form.getTratamiento(),
                form.getTipoIdentificacion(), rolesAsignados, orbeActivo);

        if (orbeActivo) {
            form.setTipoIdentificacion(usuario.getTipoIdentificacion().getId());
        }
        return usuario;
    }

    /**
     * {@inheritDoc}
     */
    public void eliminar(final Long idUsuario) {
        usuarioManager.desactivar(idUsuario);
    }

    /**
     * {@inheritDoc}
     */
    public void prepararDatosVista(final UsuarioForm form) {
        Usuario usuario = null;
        if (form.getIdUsuario() != null) {
            usuario = usuarioManager.get(form.getIdUsuario());
            form.fromEntity(usuario);
        }

    }

    /**
     * {@inheritDoc}
     */
    public TreeNode getTreeData(Long idUsuario) throws Exception {
        return (TreeNode) bibliotecaManager.getTreeData(TreeNodeBase.class,
                idUsuario, null, new ArrayList<String>());
    }

    /**
     * {@inheritDoc}
     */
    public TipoIdentificacion getTipoIdentificacion(Long idTipoIdentificacion) {
        return usuarioManager.getTipoIdentificacion(idTipoIdentificacion);
    }

    /**
     * {@inheritDoc}
     */
    public void activar(Long idUsuario) {
        usuarioManager.activar(idUsuario);
    }

    /**
     * {@inheritDoc}
     */
    public List<UsuarioBibliotecaRol> buscarRoles(Long idUsuario) {
        final Map<String, Object> criterios = new HashMap<String, Object>();
        criterios.put("idUsuario", idUsuario);
        return usuarioBibliotecaRolManager.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public Biblioteca getBiblioteca(Long idBiblioteca) {
        return bibliotecaManager.get(idBiblioteca);
    }

    /**
     * {@inheritDoc}
     */
    public String getNombresFromRoles(List<String> roles) {
        return usuarioManager.getNombresFromRoles(roles);
    }

    // ================== Getters && Setters =====================

    public IUsuarioManager getUsuarioManager() {
        return usuarioManager;
    }

    public void setUsuarioManager(final IUsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
    }

    /**
     * @return the bibliotecaManager
     */
    public IBibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    /**
     * @param bibliotecaManager
     *            the bibliotecaManager to set
     */
    public void setBibliotecaManager(IBibliotecaManager bibliotecaManager) {
        this.bibliotecaManager = bibliotecaManager;
    }

    public IUsuarioBibliotecaRolManager getUsuarioBibliotecaRolManager() {
        return usuarioBibliotecaRolManager;
    }

    public void setUsuarioBibliotecaRolManager(
            IUsuarioBibliotecaRolManager usuarioBibliotecaRolManager) {
        this.usuarioBibliotecaRolManager = usuarioBibliotecaRolManager;
    }

    public Long getIdBibliotecaByDescripcion(String descripcion) {
        return bibliotecaManager.getIdBibliotecaByDescripcion(descripcion);
    }

    public Long getIdBibliotecaByCodigo(String descripcion) {
        return bibliotecaManager.getIdBibliotecaByCodigo(descripcion);
    }

}
