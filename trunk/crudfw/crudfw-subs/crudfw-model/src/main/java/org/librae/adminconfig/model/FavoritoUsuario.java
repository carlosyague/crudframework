package org.librae.adminconfig.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.common.model.BaseObject;

/**
 * Tabla con las relaciones entre los usuarios del Sistema de Gestión
 * Bibliotecario y los favoritos que ha seleccionado.
 * 
 * @author jcisneros
 */
@Entity(name = FavoritoUsuario.ENTITY_NAME)
@Table(name = FavoritoUsuario.TABLE_NAME)
public class FavoritoUsuario extends BaseObject {

    /**
     * BaseObject is Serializable, so Usuario needs a Serial Version UID
     */
    private static final long   serialVersionUID            = -3325944276356403387L;

    public static final String  ENTITY_NAME                 = "org.librae.adminconfig.model.FavoritoUsuario";
    public static final String  TABLE_NAME                  = "ADM_FAVORITO_USUARIO";
    private static final String ID_GENERATOR_NAME           = "generator_adm_usuario";
    private static final String ID_SEQUENCE_NAME            = "SEQ_ADM_FAVORITO_USUARIO";
    public static final String  COLUMN_NAME_ID              = "X_FAVORITO_USUARIO";
    public static final String  COLUMN_NAME_FAVORITO        = "T_URL";
    public static final String  COLUMN_NAME_USUARIO         = "USUARIO_X_USUARIO";
    public static final String  COLUMN_NAME_IDENTIFICADOR   = "N_IDENTIFICADOR";
    public static final String  COLUMN_NAME_TEXTO           = "T_TEXTO";
    public static final String  COLUMN_NAME_ORDEN           = "N_ORDEN";

    public static final String  PROPERTY_NAME_ID            = "id";
    public static final String  PROPERTY_NAME_USUARIO       = "usuario";
    public static final String  PROPERTY_NAME_FAVORITO      = "favorito";
    public static final String  PROPERTY_NAME_IDENTIFICADOR = "identificador";
    public static final String  PROPERTY_NAME_TEXTO         = "texto";
    public static final String  PROPERTY_NAME_ORDEN         = "orden";

    /**
     * Clave primaria autonumérica sin significado
     */
    private Long                id;

    /**
     * Código unívoco en LDAP
     */
    private Usuario             usuario;

    /**
     * Identificador que se debe de concatenar por la url. Junto con el
     * identificador que hay en el objeto favorito.
     */
    private Long                identificador;

    /**
     * Texto que se debe de concatenar en el menu. Junto con el texto que hay en
     * el objeto favorito.
     */
    private String              texto;

    private String              url;
    
    /**
     * Orden que establece el usuario en el menu de favoritos.
     */
    private Long                orden;

    protected FavoritoUsuario() {
        super();
    }

    public FavoritoUsuario(Usuario usuario, String favorito) {
        super();
        this.usuario = usuario;
        this.url = favorito;
    }

    public FavoritoUsuario(Usuario usuario, String favorito,
            Long identificador) {
        super();
        this.usuario = usuario;
        this.url = favorito;
        this.identificador = identificador;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = FavoritoUsuario.COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the usuario
     */
    @ManyToOne(targetEntity = Usuario.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = FavoritoUsuario.COLUMN_NAME_USUARIO)
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     *            the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the identificador
     */
    @Column(name = FavoritoUsuario.COLUMN_NAME_IDENTIFICADOR)
    public Long getIdentificador() {
        return identificador;
    }

    /**
     * @return the url
     */
    @Column(name = FavoritoUsuario.COLUMN_NAME_FAVORITO)
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * @param identificador
     *            the identificador to set
     */
    public void setIdentificador(final Long identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the texto
     */
    @Column(name = FavoritoUsuario.COLUMN_NAME_TEXTO)
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto
     *            the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the orden
     */
    @Column(name = FavoritoUsuario.COLUMN_NAME_ORDEN)
    public Long getOrden() {
        return orden;
    }

    /**
     * @param orden
     *            the orden to set
     */
    public void setOrden(Long orden) {
        this.orden = orden;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FavoritoUsuario)) {
            return false;
        }

        final FavoritoUsuario other = (FavoritoUsuario) obj;

        if (!getUsuario().equals(other.getUsuario())) {
            return false;
        }
        if (!getUrl().equals(other.getUrl())) {
        	return false;
        }
        if (!getIdentificador().equals(other.getIdentificador())) {
            return false;
        }

        return true;

    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime * result
                + ((getUrl() == null) ? 0 : getUrl().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(COLUMN_NAME_ID, getId())
                .append(COLUMN_NAME_FAVORITO, getUrl()).append(
                        COLUMN_NAME_USUARIO, getUsuario()).toString();
    }

}
