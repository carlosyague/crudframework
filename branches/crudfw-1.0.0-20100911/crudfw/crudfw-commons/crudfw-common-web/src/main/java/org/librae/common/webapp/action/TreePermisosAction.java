package org.librae.common.webapp.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.librae.common.Constants;
import org.librae.common.exception.LibraeException;
import org.librae.common.service.ITreeManager;
import org.librae.common.webapp.session.SessionManager;

/**
 * Action-JSF asociado al arbol de las bibliotecas.
 * 
 * @author jcisneros
 */
public class TreePermisosAction extends BasePage implements Serializable {

	/**
	 * Numero de serializacion.
	 */
	private static final long serialVersionUID = 6953120216301897273L;

	TreeNode treeData = null;

	ITreeManager treeManager = null;

	String permiso = null;

	String renderizar = "false";

	List<String> sugerencias = new ArrayList<String>();
	
	List<String> codigosBibliotecas = new ArrayList<String>();
	
	/**
	 * Obtiene el arbol de bibliotecas.
	 * 
	 * @return arbol de bibliotecas.
	 */
	public TreeNode getTreeData() {
		try {
			if (treeData == null) {
				Long idUsuario = null;
				final SessionManager session = getSessionManager();
				if (session != null) {
					idUsuario = (Long) session
							.getAttribute(Constants.ID_USUARIO_LOGADO);
				} else {
					treeData = new TreeNodeBase();
				}
				if (idUsuario != null) {
					treeData = (TreeNode) treeManager.getTreeData(
							TreeNodeBase.class, idUsuario, permiso, codigosBibliotecas);
				}
			}
		} catch (final LibraeException e) {
			log.info(e);
		} catch (final Exception e) {
			log.error(e);
		}
		return treeData;
	}

	public List<String>suggestItems(String cadena) {
		List<String> bibliotecas = new ArrayList<String>();
		if (treeData==null) {
			treeData=getTreeData();
			for (Object nodo : treeData.getChildren()) {
				sugerencias.addAll(getNombreBiblioteca((TreeNodeBase) nodo));
			}
		}
		bibliotecas = filtrarSugerencias(sugerencias, codigosBibliotecas, cadena);
		Collections.sort(bibliotecas);
		return bibliotecas;
	}

	private List<String> filtrarSugerencias(List<String> sugerencias, List<String> sugerenciasCodigos,String cadena) {
		List<String> bibliotecas = new ArrayList<String>();
		for (String biblioteca : sugerencias) {
			if (biblioteca.toLowerCase().startsWith(cadena.toLowerCase())) {
				bibliotecas.add(biblioteca);
			}
		}
		for (String biblioteca : sugerenciasCodigos) {
			if (biblioteca.toLowerCase().startsWith(cadena.toLowerCase())) {
				bibliotecas.add(biblioteca);
			}
		}
		return bibliotecas;
	}

	private List<String> getNombreBiblioteca(TreeNodeBase nodo) {
		List<String> bibliotecas = new ArrayList<String>();
		bibliotecas.add(nodo.getDescription());
		for (Object nodoHijo : nodo.getChildren()) {
			bibliotecas.addAll(getNombreBiblioteca((TreeNodeBase) nodoHijo));
		}
		return bibliotecas;
	}

	private void showTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,S");
		Date time = new Date();
		log.error("Tiempo en buscar el arbol..." + sdf.format(time));
	}

	// Getters && Setters

	public void setTreeData(TreeNode treeData) {
		this.treeData = treeData;
	}

	public ITreeManager getTreeManager() {
		return treeManager;
	}

	public void setTreeManager(ITreeManager treeManager) {
		this.treeManager = treeManager;
	}

	/**
	 * @return the permiso
	 */
	public String getPermiso() {
		return permiso;
	}

	/**
	 * @param permiso
	 *            the permiso to set
	 */
	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

	public String getRenderizar() {
		return renderizar;
	}

	public void setRenderizar(String renderizar) {
		this.renderizar = renderizar;
	}
}
