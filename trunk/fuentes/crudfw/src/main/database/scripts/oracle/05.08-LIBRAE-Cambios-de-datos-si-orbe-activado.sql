delete from ADM_ROLES_PERMISOS where X_ROLES_PERMISOS in (select rp.X_ROLES_PERMISOS from ADM_ROLES_PERMISOS rp, ADM_PERMISOS p where p.C_PERMISO='ADM_Cambiar_Clave' and p.X_PERMISO=rp.PER_X_PERMISO);
delete from ADM_MENUS where X_MENU in (select m.X_MENU from ADM_MENUS m, ADM_PERMISOS p where p.C_PERMISO='ADM_Cambiar_Clave' and p.X_PERMISO=m.PER_X_PERMISO);
delete from ADM_PERMISOS where C_PERMISO='ADM_Cambiar_Clave';

commit;