package org.librae.adminconfig.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.librae.adminconfig.dao.IBibliotecaDAO;
import org.librae.adminconfig.dao.IRolDAO;
import org.librae.adminconfig.dao.ITipoIdentificacionDAO;
import org.librae.adminconfig.dao.ITratamientoDAO;
import org.librae.adminconfig.dao.IUsuarioBibliotecaRolDAO;
import org.librae.adminconfig.dao.IUsuarioDAO;
import org.librae.adminconfig.facade.IArboledaFacade;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.adminconfig.model.Rol;
import org.librae.adminconfig.model.TipoIdentificacion;
import org.librae.adminconfig.model.Tratamiento;
import org.librae.adminconfig.model.Usuario;
import org.librae.adminconfig.model.UsuarioBibliotecaRol;

import org.librae.adminconfig.service.IUsuarioManager;
import org.librae.common.Constants;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.service.impl.GenericManagerImpl;
import org.librae.common.util.DateUtil;
import org.librae.common.util.Propiedades;
import org.librae.common.util.StringUtil;

/**
 * Implementación del Manager <br/> DAO: IUsuarioDAO <br/> Entidad: Usuario
 * 
 * @author asantamaria
 * @author jcisneros
 * @author aropero
 */
public class UsuarioManagerImpl extends GenericManagerImpl<Usuario, Long>
        implements IUsuarioManager, Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6232037012891600763L;
    IUsuarioDAO               usuarioDao;
    ITipoIdentificacionDAO    tipoIdentificacionDao;
    ITratamientoDAO           tratamientoDao;
    IBibliotecaDAO            bibliotecaDao;
    IArboledaFacade           arboledaFacade;
    IUsuarioBibliotecaRolDAO  usuarioBibliotecaRolDao;
    IRolDAO                   rolDao;

    /**
     * Constructor del Manager
     * 
     * @param dao
     *            objeto DAO a manejar
     */
    public UsuarioManagerImpl(final IUsuarioDAO usuarioDao) {
        super(usuarioDao);
        this.usuarioDao = usuarioDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Usuario> buscar(final Map<String, Object> criterios) {
        arboledaFacade.getBibliotecasByCriterios(criterios);
        return usuarioDao.buscar(criterios);
    }

    /**
     * {@inheritDoc}
     */
    public List<Usuario> buscarNonLazy(final Map<String, Object> criterios) {
        return toNonLazyEntities(buscar(criterios));
    }

    /**
     * {@inheritDoc}
     */
    public void cambiarClave(final String usuario, final String claveNueva,
            final String claveRepetida) {
        final Usuario usuarioGuardado = usuarioDao.getUsuarioByUsuario(usuario);
        if (usuarioGuardado == null) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_CODIGO_USUARIO_NO_EXISTE");
        }
        // if (!usuarioGuardado.getClave().equals(clave)) {
        // throw ExceptionUtil.crearLibraeException(
        // MensajesError.PROPERTI_ADMINCONFIG,
        // "ERROR_CLAVE_USUARIO_ERRONEA");
        // }
        if (!claveRepetida.equals(claveNueva)) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_CLAVE_NUEVA_REPETIDA_ERRONEA");
        }
        usuarioGuardado.setClave(claveNueva);
        usuarioDao.save(usuarioGuardado);
    }

    /**
     * {@inheritDoc}
     */
    public Usuario getUsuarioByUsername(final String usuario) {
        Usuario result = null;

        result = usuarioDao.getUsuarioByUsuario(usuario);

        return result;
    }

    public Usuario salvarUsuario(final Usuario usuario,
            final Long idTratamiento, final Long idTipoIdentificacion,
            List<UsuarioBibliotecaRol> rolesAsignados) {
        return salvarUsuario(usuario, idTratamiento, idTipoIdentificacion,
                rolesAsignados, Boolean.FALSE);
    }

    /**
     * {@inheritDoc}
     */
    public Usuario salvarUsuario(final Usuario usuario,
            final Long idTratamiento, final Long idTipoIdentificacion,
            List<UsuarioBibliotecaRol> rolesAsignados, Boolean orbeActivo) {
        Tratamiento tratamiento = null;
        TipoIdentificacion tipoIdentificacion = null;
        final Usuario usuarioRepetido = usuarioDao.getUsuarioByUsuario(usuario
                .getUsuario());
        if ((usuarioRepetido != null)
                && (!usuarioRepetido.getId().equals(usuario.getId()))) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_CODIGO_DUPLICADO_USUARIO");
        }

        if ((idTratamiento != null) && !(new Long(0).equals(idTratamiento))) {
            tratamiento = tratamientoDao.get(idTratamiento);
        }
        if (!orbeActivo && (idTipoIdentificacion != null)
                && !(new Long(0).equals(idTipoIdentificacion))) {
            tipoIdentificacion = tipoIdentificacionDao
                    .get(idTipoIdentificacion);

            usuario.setTipoIdentificacion(tipoIdentificacion);
        }
        if (usuario.getId() == null) {
            usuario.setActivo(true);
        }

        usuario.setTratamiento(tratamiento);

        if ((rolesAsignados == null) || (rolesAsignados.isEmpty())) {
            throw ExceptionUtil.crearLibraeException(
                    MensajesError.PROPERTI_ADMINCONFIG,
                    "ERROR_USUARIO_ROLES_VACIO");
        }
        if (usuario.getUsuarioBibliotecaRol() != null) {
            usuario.getUsuarioBibliotecaRol().clear();
        } else {
            usuario
                    .setUsuarioBibliotecaRol(new ArrayList<UsuarioBibliotecaRol>());
        }
        for (final UsuarioBibliotecaRol rol : rolesAsignados) {
            usuario.getUsuarioBibliotecaRol().add(rol);
        }

        return usuarioDao.save(usuario);
    }

    /**
     * {@inheritDoc}
     */
    public List<Usuario> getUsuariosById(final List<String> listadoIds) {
        return usuarioDao.getUsuariosById(listadoIds);
    }

    /**
     * {@inheritDoc}
     */
    public List<Usuario> getUsuariosBySucursalId(final String sucursalId) {
        return usuarioDao.getUsuariosBySucursalId(sucursalId);
    }

    /**
     * {@inheritDoc}
     */
    public List<Usuario> getUsuariosBySucIdXaPrestSeg(final String sucursalId) {
        return usuarioDao.getUsuariosBySucIdXaPrestSeg(sucursalId);
    }

    /**
     * {@inheritDoc}
     */
    public List<Usuario> getUsuariosByCodSucXaPrestSeg(final String codSucursal) {
        if (codSucursal == null) {
            throw new LibraeException("codSucursal no puede ser null");
        }

        final List<Long> listaBibliotecasIds = new ArrayList<Long>();
        final Map<String, Object> criterios = new HashMap<String, Object>();

        Biblioteca biblioteca = bibliotecaDao
                .getBibliotecaByCodigo(codSucursal);

        listaBibliotecasIds.add(biblioteca.getId());
        while (biblioteca.getPadre() != null) {
            listaBibliotecasIds.add(biblioteca.getPadre().getId());
            biblioteca = biblioteca.getPadre();
        }
        criterios.put("bibliotecas", listaBibliotecasIds);

        final List<Usuario> usuarios = usuarioDao.buscar(criterios);

        return usuarios;
    }

    /**
     * {@inheritDoc}
     */
    public TipoIdentificacion getTipoIdentificacion(Long idTipoIdentificacion) {
        return tipoIdentificacionDao.get(idTipoIdentificacion);
    }

    /**
     * {@inheritDoc}
     */
    public TipoIdentificacion getTipoIdentificacionByCod(
            String codTipoIdentificacion) {
        return tipoIdentificacionDao
                .getTipoIdentificacionByCodigo(codTipoIdentificacion);
    }

    /**
     * {@inheritDoc}
     */
    public void activar(Long idUsuario) {
        final Usuario usuario = usuarioDao.get(idUsuario);
        usuario.setActivo(Boolean.TRUE);
        usuario.setFechaBaja(null);
        usuarioDao.save(usuario);
        usuarioDao.flush();
    }

    /**
     * {@inheritDoc}
     */
    public void desactivar(Long idUsuario) {
        final Usuario usuario = usuarioDao.get(idUsuario);
        usuario.setActivo(Boolean.FALSE);
        usuario.setFechaBaja(DateUtil.getCurrentDate());
        usuarioDao.save(usuario);
        usuarioDao.flush();
    }

    /**
     * {@inheritDoc}
     */
    public String getNombresFromRoles(List<String> roles) {
        StringBuilder sb = new StringBuilder();
        for (final String idRol : roles) {
            if (!(new Long(0).equals(new Long(idRol)))) {
                final Rol rol = rolDao.get(new Long(idRol));
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(rol.getCodigo());
            }
        }
        if (roles.size() == 1 && new Long(roles.get(0)).equals(new Long(0))) {
            sb = new StringBuilder();
            sb.append(ComboLocaleManager.getOptional("todos"));
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    public Usuario getUsuarioByNIF(String nif) {
        final Usuario user = usuarioDao.getUsuarioByNIF(nif);
        return user;
    }

    public void asignaRolInicialOrbe(Usuario usuario) {

        final Propiedades properties = Propiedades
                .getInstance(Constants.ORBE_PROPERTIES);

        final String codBibliotecaNodoPrincipal = properties
                .getPropiedad(Constants.BIBLIOTECA_NODO_PRINCIPAL);
        final Biblioteca red = bibliotecaDao
                .getBibliotecaByCodigo(codBibliotecaNodoPrincipal);

        final String codRolOrbeInicialVacio = properties
                .getPropiedad(Constants.ROL_ORBE_INICIAL_VACIO);
        final Rol rolOrbeInicialVacio = rolDao
                .getRolByCodigo(codRolOrbeInicialVacio);

        usuario = usuarioDao.save(usuario);

        final UsuarioBibliotecaRol usuarioBibliotecaRol = new UsuarioBibliotecaRol(
                usuario, rolOrbeInicialVacio, red);

        usuarioBibliotecaRolDao.save(usuarioBibliotecaRol);

    }

    // Getters && Setters

    public IUsuarioDAO getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(final IUsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public ITipoIdentificacionDAO getTipoIdentificacionDao() {
        return tipoIdentificacionDao;
    }

    public void setTipoIdentificacionDao(
            final ITipoIdentificacionDAO tipoIdentificacionDao) {
        this.tipoIdentificacionDao = tipoIdentificacionDao;
    }

    public ITratamientoDAO getTratamientoDao() {
        return tratamientoDao;
    }

    public void setTratamientoDao(final ITratamientoDAO tratamientoDao) {
        this.tratamientoDao = tratamientoDao;
    }

    public IBibliotecaDAO getBibliotecaDao() {
        return bibliotecaDao;
    }

    public void setBibliotecaDao(final IBibliotecaDAO bibliotecaDao) {
        this.bibliotecaDao = bibliotecaDao;
    }

    /**
     * @return the arboledaFacade
     */
    public IArboledaFacade getArboledaFacade() {
        return arboledaFacade;
    }

    /**
     * @param arboledaFacade
     *            the arboledaFacade to set
     */
    public void setArboledaFacade(IArboledaFacade arboledaFacade) {
        this.arboledaFacade = arboledaFacade;
    }

    /**
     * @return the usuarioBibliotecaRolDao
     */
    public IUsuarioBibliotecaRolDAO getUsuarioBibliotecaRolDao() {
        return usuarioBibliotecaRolDao;
    }

    /**
     * @return the rolDao
     */
    public IRolDAO getRolDao() {
        return rolDao;
    }

    /**
     * @param rolDao
     *            the rolDao to set
     */
    public void setRolDao(IRolDAO rolDao) {
        this.rolDao = rolDao;
    }

    /**
     * @param usuarioBibliotecaRolDao
     *            the usuarioBibliotecaRolDao to set
     */
    public void setUsuarioBibliotecaRolDao(
            IUsuarioBibliotecaRolDAO usuarioBibliotecaRolDao) {
        this.usuarioBibliotecaRolDao = usuarioBibliotecaRolDao;
    }

}