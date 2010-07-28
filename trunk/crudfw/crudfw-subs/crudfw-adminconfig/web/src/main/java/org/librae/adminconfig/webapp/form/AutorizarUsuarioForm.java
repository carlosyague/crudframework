package org.librae.adminconfig.webapp.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.librae.adminconfig.model.Rol;
import org.librae.common.webapp.form.IBaseForm;

/**
 * Formulario para la asignacion de permisos a roles.
 * 
 * @author jcisneros
 */
public class AutorizarUsuarioForm implements IBaseForm<Rol> {

	private static final long serialVersionUID = 8512996923345207875L;
	
	final static public Long      idRol             = null;
    final public List<SelectItem> permisosAsignados = new ArrayList<SelectItem>();

    public void fromEntity(final Rol rol) {
        // fromEntity
    }

    public Rol toEntity() {
        return new Rol(null);
    }

    // Getters && Setter

}
