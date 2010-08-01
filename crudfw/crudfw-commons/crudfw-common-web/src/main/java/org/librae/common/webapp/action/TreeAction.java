package org.librae.common.webapp.action;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.librae.common.exception.LibraeException;
import org.librae.common.service.ITreeManager;

/**
 * Action-JSF asociado al arbol de las bibliotecas.
 * 
 * @author jcisneros
 */
public class TreeAction implements Serializable {

    /**
     * Numero de serializacion.
     */
    private static final long serialVersionUID = 6953120216301897273L;
    TreeNode                  treeData         = null;
    ITreeManager              treeManager      = null;

    /**
     * Atributo para las trazas
     */
    protected final Log       log              = LogFactory
                                                       .getLog(TreeAction.class);

    /**
     * Obtiene el arbol de bibliotecas.
     * 
     * @return arbol de bibliotecas.
     */
    public TreeNode getTreeData() {
        try {
            if (treeData == null) {
                treeData = new TreeNodeBase("bibliotecas", "bibliotecas", false);
                try {
                    treeData.getChildren().add(
                            treeManager.getTreeData(TreeNodeBase.class));
                } catch (final Exception e) {
                    log.error(e);
                }
            }
        } catch (final LibraeException e) {
            log.info(e);
        } catch (final Exception e) {
            log.error(e);
        }
        return treeData;
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

}
