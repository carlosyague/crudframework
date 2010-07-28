package org.librae.adminconfig.webapp.delegate.impl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.BibliotecaView;
import org.librae.adminconfig.model.Permiso;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;
import org.librae.adminconfig.service.IBibliotecaManager;
import org.librae.adminconfig.service.IRolManager;
import org.librae.adminconfig.service.IUsuarioBibliotecaRolManager;
import org.librae.adminconfig.service.IUsuarioManager;
import org.librae.adminconfig.webapp.delegate.IAutorizarUsuarioDelegate;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.util.Propiedades;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.UserDetails;

/**
 * Implementacion del interfaz IAutorizarUsuarioDelegate.
 * 
 * @author jcisneros, cyague(autorización-CAS)
 */
public class AutorizarUsuarioDelegateImpl implements IAutorizarUsuarioDelegate,
        Serializable {

    /**
     *
     */
    private static final long            serialVersionUID             = -830034518476774260L;

    protected final Log                  log                          = LogFactory
                                                                              .getLog(AutorizarUsuarioDelegateImpl.class);

    private IBibliotecaManager           bibliotecaManager;
    private IRolManager                  rolManager;
    private IUsuarioManager              usuarioManager;
    private IUsuarioBibliotecaRolManager usuarioBibliotecaRolManager;

    public final static Long             ID_USUARIO_LOGADO_SIMULACION = new Long(
                                                                              -1);

    /**
     * @see org.librae.adminconfig.webapp.delegate.IAutorizarUsuarioDelegate#getRoles(java.lang.Long,
     *      java.lang.Long)
     */
    public List<String> getRoles(final Long idUsuario, final Long idBiblioteca) {
        return rolManager.getListaBibliotecaRol(idUsuario, idBiblioteca);
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IAutorizarUsuarioDelegate#setUserInSession()
     */
    public Usuario getUsuarioByCASUsername() {
        Usuario result = null;

        final String username = getUsernameByCookieCAS();
        log.debug("CAS username=" + username);

        result = usuarioManager.getUsuarioByUsername(username);
        log.debug("Usuario logado: " + result);

        return result;
    }

    /**
     * Devuelve el username que viene encriptado en la cookie insertada por CAS.
     * Para ello usamos el <code>SecurityContextHolder</code> de Spring-Security
     * 
     * @return
     */
    private String getUsernameByCookieCAS() {
        String result = null;

        final SecurityContext securityContext = SecurityContextHolder
                .getContext();
        if (securityContext != null) {
            final Authentication authentication = securityContext
                    .getAuthentication();

            if (authentication != null) {
                final Object objPrincipal = authentication.getPrincipal();

                if ((objPrincipal != null)
                        && (objPrincipal instanceof UserDetails)) {
                    result = ((UserDetails) objPrincipal).getUsername();
                }
            }
        }

        return result;
    }

    /**
     * @see org.librae.adminconfig.webapp.delegate.IAutorizarUsuarioDelegate#save(org.librae.adminconfig.model.Usuario,
     *      java.lang.Long, boolean)
     */
    public List<Permiso> save(final Usuario usuario, final Long idBiblioteca,
            final boolean defecto) {
        List<Permiso> permisos = null;
        if (usuario != null) {
            if (idBiblioteca == null) {
                throw ExceptionUtil
                        .crearLibraeException("ERROR_BIBILIOTECA_VACIA");
            }
            permisos = rolManager.autorizar(usuario, idBiblioteca, defecto);
            permisos.add(new Permiso(Constants.USER_ROLE, Constants.USER_ROLE));
            crearUsuarioAcegi(permisos);
        }

        return permisos;
    }

    public void seleccionaBibOrbe(Usuario usuario, Long idBiblioteca) {

        final Propiedades properties = Propiedades.getInstance();

        final String codBibliotecaNodoPrincipal = properties
                .getPropiedad(Constants.BIBLIOTECA_NODO_PRINCIPAL);
        final Biblioteca red = bibliotecaManager
                .getBibliotecaByCodigo(codBibliotecaNodoPrincipal);

        final String codRolOrbeInicialVacio = properties
                .getPropiedad(Constants.ROL_ORBE_INICIAL_VACIO);
        final Rol rolOrbeInicialVacio = rolManager
                .getRolByCodigo(codRolOrbeInicialVacio);

        final UsuarioBibliotecaRol ubr = usuarioBibliotecaRolManager
                .getUsuarioBibliotecaRolByIds(usuario.getId(), red.getId(),
                        rolOrbeInicialVacio.getId());

        if (ubr == null) {
            throw ExceptionUtil
                    .crearLibraeException("ERROR_USUARIO_BIBLIOTECA_ROL_NO_EXISTE");
        } else {
            final Biblioteca bibliotecaSeleccionada = bibliotecaManager
                    .get(idBiblioteca);
            ubr.setBiblioteca(bibliotecaSeleccionada);
            usuarioBibliotecaRolManager.save(ubr);

            usuario
                    .setBibliotecaPorDefecto(bibliotecaManager
                            .get(idBiblioteca));
            usuario.setBibliotecaActual(bibliotecaManager.get(idBiblioteca));

            usuarioManager.save(usuario);
        }

    }

    public boolean usuarioRecienLLegadoDeOrbe(Usuario usuario) {
        final Propiedades properties = Propiedades.getInstance();

        final String codBibliotecaNodoPrincipal = properties
                .getPropiedad(Constants.BIBLIOTECA_NODO_PRINCIPAL);
        final Biblioteca red = bibliotecaManager
                .getBibliotecaByCodigo(codBibliotecaNodoPrincipal);

        final String codRolOrbeInicialVacio = properties
                .getPropiedad(Constants.ROL_ORBE_INICIAL_VACIO);
        final Rol rolOrbeInicialVacio = rolManager
                .getRolByCodigo(codRolOrbeInicialVacio);

        final UsuarioBibliotecaRol ubr = usuarioBibliotecaRolManager
                .getUsuarioBibliotecaRolByIds(usuario.getId(), red.getId(),
                        rolOrbeInicialVacio.getId());

        return (ubr != null);
    }

    public String getUrlAutorizarOrbe() {
        final StringBuilder urlAutorizarOrbeSB = new StringBuilder();

        final Propiedades properties = Propiedades.getInstance();

        final String protocolo = properties
                .getPropiedad(Constants.HTTP_PROTOCOL);

        urlAutorizarOrbeSB.append(protocolo);
        urlAutorizarOrbeSB.append("://");

        final String host = properties.getPropiedad(Constants.HOST);

        urlAutorizarOrbeSB.append(host);

        final String puerto = properties.getPropiedad(Constants.PORT);

        if (puerto != null && !puerto.equals("")) {
            urlAutorizarOrbeSB.append(":");
            urlAutorizarOrbeSB.append(puerto);

        }

        final String urlAutorizarOrbe = properties
                .getPropiedad(Constants.URL_AUTORIZAR_ORBE);

        urlAutorizarOrbeSB.append(urlAutorizarOrbe);

        return urlAutorizarOrbeSB.toString();
    }

    /**
     * Crea un usuario Acegi.
     * 
     * @param permisos
     */
    public void crearUsuarioAcegi(final List<Permiso> permisos) {
        final Authentication usuarioAcegi = SecurityContextHolder.getContext()
                .getAuthentication();
        try {
            if (permisos != null && !permisos.isEmpty()) {
                final GrantedAuthority[] listaPermisos = new GrantedAuthority[permisos
                        .size()];
                for (int i = 0; i < permisos.size(); i++) {
                    if (permisos.get(i) != null) {
                        final Permiso permiso = permisos.get(i);
                        listaPermisos[i] = new GrantedAuthorityImpl(permiso
                                .getCodigo());
                    }
                }
                final UsernamePasswordAuthenticationToken nuevoUsuario = new UsernamePasswordAuthenticationToken(
                        usuarioAcegi.getPrincipal(), usuarioAcegi
                                .getCredentials(), listaPermisos);
                SecurityContextHolder.getContext().setAuthentication(
                        nuevoUsuario);
            }
        } catch (final Exception e) {
            log.error("Error creando el usuario acegi...", e);
        }
    }

    public List<Permiso> getPermisos() {
        List<Permiso> permisos = null;
        permisos = rolManager.getPermisos();
        return permisos;
    }

    public List<BibliotecaView> getBibliotecasByUsuario(final Usuario usuario) {
        final List<BibliotecaView> bibliotecas = bibliotecaManager
                .getBibliotecasByUsuario(usuario.getId());
        return bibliotecas;
    }

    public TreeNode getTreeData(Long idUsuario) throws Exception {
        return (TreeNode) bibliotecaManager.getTreeData(TreeNodeBase.class,
                idUsuario, null, new ArrayList<String>());
    }

    // Getters & Setters

    public IBibliotecaManager getBibliotecaManager() {
        return bibliotecaManager;
    }

    public void setBibliotecaManager(final IBibliotecaManager bibliotecaManager) {
        this.bibliotecaManager = bibliotecaManager;
    }

    public IRolManager getRolManager() {
        return rolManager;
    }

    public void setRolManager(final IRolManager rolManager) {
        this.rolManager = rolManager;
    }

    public IUsuarioManager getUsuarioManager() {
        return usuarioManager;
    }

    public void setUsuarioManager(final IUsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
    }

    /**
     * @return the usuarioBibliotecaRolManager
     */
    public IUsuarioBibliotecaRolManager getUsuarioBibliotecaRolManager() {
        return usuarioBibliotecaRolManager;
    }

    /**
     * @param usuarioBibliotecaRolManager
     *            the usuarioBibliotecaRolManager to set
     */
    public void setUsuarioBibliotecaRolManager(
            IUsuarioBibliotecaRolManager usuarioBibliotecaRolManager) {
        this.usuarioBibliotecaRolManager = usuarioBibliotecaRolManager;
    }

}
