package org.librae.catalogacion.model;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.librae.lectores.model.LectorTipo;
import org.librae.common.model.BaseObject;
import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Bean para almacenar la imagen asociada a un Registro
 *
 * @author jcdiaz
 */
@Entity(name = RegistroImagen.ENTITY_NAME)
@Table(name = RegistroImagen.TABLE_NAME)
public class RegistroImagen extends SpringRemotableLazyEntity<RegistroImagen> {

    /**
     * BaseObject es Serializable, por lo tanto RegistroImagen necesita un Serial
     * Version UID
     */
    private static final long  serialVersionUID           = -1L;

    public static final String  ENTITY_NAME                       = "org.librae.catalogacion.model.RegistroImagen";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String  TABLE_NAME                        = "CAT_REGISTRO_IMAGEN";
    private static final String ID_GENERATOR_NAME                 = "generator_cat_registro_imagen";
    private static final String ID_SEQUENCE_NAME                  = "SEQ_CAT_REGISTRO_IMAGEN";
    /**
     * Constantes para referencia de los atributos de Autoridad
     */
    public static final String  PROPTY_NAME_ID                    = "id";
    public static final String  PROPTY_NAME_IMAGEN                = "imagen";
    public static final String  PROPTY_NAME_IMAGEN_NOMBRE_FICHERO = "imagenNombreFichero";
    public static final String  PROPTY_NAME_IMAGEN_CONTENT_TYPE   = "imagenContentType";

    public static final String  COLUMN_NAME_ID                    = "X_REGISTRO_IMAGEN";
    public static final String  COLUMN_NAME_IMAGEN                = "T_IMAGEN";
    public static final String  COLUMN_NAME_IMAGEN_NOMBRE_FICHERO = "T_IMAGEN_NOMBRE_FICHERO";
    public static final String  COLUMN_NAME_IMAGEN_CONTENT_TYPE   = "T_IMAGEN_CONTENT_TYPE";

    /**
     * Clave autonumérica secuencial sin significado funcional
     */
    private Long                id;

    /**
     *Fichero que identifica la imagen del registro
     */
    private byte[]              imagen;

    /**
     * Nombre del fichero que identifica la imagen del registro
     */
    private String              imagenNombreFichero;

    /**
     * ContentType del fichero que identifica la imagen del registro
     */
    private String              imagenContentType;

    //private Registro registro;




    /**
     * @return the registro
     */
//    @OneToOne(mappedBy = Registro.PROPTY_NAME_REGISTRO_IMAGEN, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    public Registro getRegistro() {
//        return registro;
//    }

    /**
     * @param registro the registro to set
     */
//    public void setRegistro(Registro registro) {
//        this.registro = registro;
//    }

    /**
     * Constructor sin parámetros
     */
    protected RegistroImagen() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param imagen
     * @param imagenContentType
     * @param imagenNombreFichero
     */
    public RegistroImagen(byte[] imagen, String imagenContentType,
            String imagenNombreFichero) {
        super();
        this.imagen = imagen;
        this.imagenContentType = imagenContentType;
        this.imagenNombreFichero = imagenNombreFichero;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = RegistroImagen.ID_GENERATOR_NAME)
    @SequenceGenerator(name = RegistroImagen.ID_GENERATOR_NAME, sequenceName = RegistroImagen.ID_SEQUENCE_NAME)
    @Column(name = RegistroImagen.COLUMN_NAME_ID, nullable = false)
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
     * @return the imagen
     */
    @Column(name = RegistroImagen.COLUMN_NAME_IMAGEN)
    @Lob
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen
     *            the imagen to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the imagenNombreFichero
     */
    @Column(name = RegistroImagen.COLUMN_NAME_IMAGEN_NOMBRE_FICHERO, length = 255)
    public String getImagenNombreFichero() {
        return imagenNombreFichero;
    }

    /**
     * @param imagenNombreFichero
     *            the imagenNombreFichero to set
     */
    public void setImagenNombreFichero(String imagenNombreFichero) {
        this.imagenNombreFichero = imagenNombreFichero;
    }

    /**
     * @return the imagenContentType
     */
    @Column(name = RegistroImagen.COLUMN_NAME_IMAGEN_CONTENT_TYPE, length = 255)
    public String getImagenContentType() {
        return imagenContentType;
    }

    /**
     * @param imagenContentType
     *            the imagenContentType to set
     */
    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + Arrays.hashCode(imagen);
        result = prime
                * result
                + ((imagenContentType == null) ? 0 : imagenContentType
                        .hashCode());
        result = prime
                * result
                + ((imagenNombreFichero == null) ? 0 : imagenNombreFichero
                        .hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RegistroImagen)) {
            return false;
        }
        RegistroImagen other = (RegistroImagen) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (!Arrays.equals(imagen, other.imagen)) {
            return false;
        }
        if (imagenContentType == null) {
            if (other.imagenContentType != null) {
                return false;
            }
        } else if (!imagenContentType.equals(other.imagenContentType)) {
            return false;
        }
        if (imagenNombreFichero == null) {
            if (other.imagenNombreFichero != null) {
                return false;
            }
        } else if (!imagenNombreFichero.equals(other.imagenNombreFichero)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(RegistroImagen.COLUMN_NAME_ID,
                id).append(RegistroImagen.COLUMN_NAME_IMAGEN,
                (imagen == null) ? 0 : imagen).append(
                RegistroImagen.COLUMN_NAME_IMAGEN_CONTENT_TYPE,
                (imagenContentType == null) ? 0 : imagenContentType).append(
                RegistroImagen.COLUMN_NAME_IMAGEN_NOMBRE_FICHERO,
                (imagenNombreFichero == null) ? 0 : imagenNombreFichero)
                .toString();
    }

    @Override
    public RegistroImagen newInstance() {
        return new RegistroImagen();
    }
}