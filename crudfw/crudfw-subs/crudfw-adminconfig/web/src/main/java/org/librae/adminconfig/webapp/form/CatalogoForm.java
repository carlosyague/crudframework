package org.librae.adminconfig.webapp.form;

import org.librae.adminconfig.model.Catalogo;
import org.librae.common.webapp.form.IBaseForm;

/**
 * Formulario para la edicion de un catalogo.
 * 
 * @author impena
 */
public class CatalogoForm implements IBaseForm<Catalogo> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8522996923345207871L;

	/**
     * Clave primaria artificial, asignada por el SGBD, que identifica de forma
     * única cada fila.
     */
    private Long    id;

    /**
     * Código identificativo del catálogo introducido por el usuario.<br>
     * Valores únicos
     */
    private String  codigo;

    /**
     * Descripción del catálogo introducida por el usuario
     */
    private String  nombre;

    /**
     * Descripción alternativa o ampliada del catálogo, introducida por el
     * usuario
     */
    private String  descripcion;

    /** modo de Edición o Lectura de un catalogo */
    private Boolean readMode = false;

    /** modo de Edición o Creación de un catalogo */
    private Boolean creacion = true;

    /**
     * @see org.librae.common.webapp.form.IBaseForm#fromEntity(java.lang.Object)
     */
    public void fromEntity(Catalogo catalogo) {
        if (catalogo.getCodigo() != null) {
            this.codigo = catalogo.getCodigo();
        }
        if (catalogo.getNombre() != null) {
            this.nombre = catalogo.getNombre();
        }
        if (catalogo.getDescripcion() != null) {
            this.descripcion = catalogo.getDescripcion();
        }
    }

    /**
     * @see org.librae.common.webapp.form.IBaseForm#toEntity()
     */
    public Catalogo toEntity() {
        final Catalogo catalogo = new Catalogo(null, null);
        this.toEntity(catalogo);
        return catalogo;
    }

    public Catalogo toEntity(Catalogo catalogo) {
        catalogo.setCodigo(this.codigo);
        catalogo.setNombre(this.nombre);
        catalogo.setDescripcion(this.descripcion);
        return catalogo;
    }

    // ================ Getters && Setter ===================

    public Boolean getReadMode() {
        return this.readMode;
    }

    public void setReadMode(final Boolean readMode) {
        this.readMode = readMode;
    }

    public Boolean getCreacion() {
        return this.creacion;
    }

    public void setCreacion(final Boolean creacion) {
        this.creacion = creacion;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
