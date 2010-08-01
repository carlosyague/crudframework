package org.librae.adquisicion.model;

import java.util.Date;

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
import org.hibernate.annotations.ForeignKey;
import org.librae.adminconfig.model.Biblioteca;
import org.librae.catalogacion.model.Registro;
import org.librae.common.model.parampoliticas.AbstractParamPolDesiderata;
import org.librae.lectores.model.Lector;

/**
 * Bean para almacenar un Desiderata
 *
 * @author jcdiaz
 */
@Entity(name = Desiderata.NAME_ENTITY)
@Table(name = Desiderata.TABLE_NAME)
public class Desiderata extends AbstractParamPolDesiderata {

    /**
     * BaseObject es Serializable, por lo tanto Desiderata necesita un Serial
     * Version UID
     */
    private static final long   serialVersionUID                = 9150044729015312832L;

    public static final String  NAME_ENTITY                     = "org.librae.adquisicion.model.Desiderata";
    public static final String  TABLE_NAME                      = "ADQ_DESIDERATAS";
    private static final String ID_GENERATOR_NAME               = "generator_adq_desideratas";
    private static final String ID_SEQUENCE_NAME                = "SEQ_ADQ_DESIDERATAS";
    public static final String  COLUMN_NAME_ID                  = "X_DESIDERATA";
    public static final String  COLUMN_NAME_TITULO              = "T_TITULO";
    public static final String  COLUMN_NAME_AUTOR               = "T_AUTOR";
    public static final String  COLUMN_NAME_EDITORIAL           = "T_EDITORIAL";
    public static final String  COLUMN_NAME_EDICION             = "T_EDICION";
    public static final String  COLUMN_NAME_FECHA_PUBLICACION   = "T_FECHA_PUBLICACION";
    public static final String  COLUMN_NAME_FECHA_ESTADO        = "F_FECHA_ESTADO";
    public static final String  COLUMN_NAME_FECHA_SOLICITUD     = "F_FECHA_SOLICITUD";
    public static final String  COLUMN_NAME_ISBN                = "T_ISBN";
    public static final String  COLUMN_NAME_ESTADO              = "T_ESTADO";
    public static final String  COLUMN_NOTAS_BIBLIOTECARIO      = "T_NOTAS_BIBLIOTECARIO";
    public static final String  COLUMN_NAME_NOTAS               = "T_NOTAS";
    public static final String  COLUMN_NAME_RESERVAR            = "L_DESEO_RESERVAR";
    public static final String  COLUMN_NAME_BIBLIOTECA_FK       = "BIB_X_BIBLIOTECA";
    public static final String  COLUMN_NAME_LECTOR_FK           = "LEC_X_LECTOR";
    public static final String  COLUMN_NAME_MOTIVO_RECHAZO_FK   = "MOT_X_MOTIVO_RECHAZO";
    public static final String  COLUMN_NAME_REGISTRO_FK         = "REG_X_REGISTRO";
    public static final String  COLUMN_NAME_PEDIDO_FK           = "PED_X_PEDIDO";
    public static final String  COLUMN_NAME_TIPO_CANAL          = "T_TIPO_CANAL";
    public static final String  PEDIDO                          = "pedido";

    public static final String  PROPERTY_NAME_ID                = "id";
    public static final String  PROPERTY_NAME_TITULO            = "titulo";
    public static final String  PROPERTY_NAME_AUTOR             = "autor";
    public static final String  PROPERTY_NAME_EDITORIAL         = "editorial";
    public static final String  PROPERTY_NAME_EDICION           = "edicion";
    public static final String  PROPERTY_NAME_FECHA_PUBLICACION = "fechaPublicacion";
    public static final String  PROPERTY_NAME_FECHA_ESTADO      = "fechaEstado";
    public static final String  PROPERTY_NAME_FECHA_SOLICITUD   = "fechaSolicitud";
    public static final String  PROPERTY_NAME_ISBN              = "isbn";
    public static final String  PROPERTY_NAME_ESTADO            = "estado";
    public static final String  PROPERTY_NOTAS_BIBLIOTECARIO    = "notasBibliotecario";
    public static final String  PROPERTY_NAME_NOTAS             = "notas";
    public static final String  PROPERTY_NAME_RESERVAR          = "reservar";
    public static final String  PROPERTY_NAME_BIBLIOTECA_FK     = "biblioteca";
    public static final String  PROPERTY_NAME_LECTOR_FK         = "lector";
    public static final String  PROPERTY_NAME_MOTIVO_RECHAZO_FK = "motivoRechazo";
    public static final String  PROPERTY_NAME_REGISTRO_FK       = "registro";
    public static final String  PROPERTY_NAME_PEDIDO_FK         = "pedido";
    public static final String  PROPERTY_NAME_TIPO_CANAL        = "tipoCanal";

    // FIXME El analista tiene que informar si es necesario el atributo
    // public static final String COLUMN_NAME_PEDIDO_FK = "X_DESIDERATA";

    /**
     * clave primaria
     */
    private Long                id;

    /**
     * titulo del registro solicitado
     */
    private String              titulo;

    /**
     * autor del registro solicitado
     */
    private String              autor;

    /**
     * editorial del registro solicitado
     */
    private String              editorial;

    /**
     * edición del registro solicitado
     */
    private String              edicion;

    /**
     * fecha de publicación del registro solicitado
     */
    private String              fechaPublicacion;

    /**
     * fecha del estado de la desiderata
     */
    private Date                fechaEstado;

    /**
     * fecha de solicitud de la desiderata
     */
    private Date                fechaSolicitud;

    /**
     * isbn del registro solicitado
     */
    private String              isbn;

    /**
     * notas para el bibliotecario
     */
    private String              notasBibliotecario;

    /**
     * notas de la desiderata
     */
    private String              notas;

    /**
     * indica si el usuario deseea ser notificado o no
     */
    private Boolean             reservar;

    /**
     * referencia a la biblioteca de la desiderata
     */
    private Biblioteca          biblioteca;

    /**
     * referencia al lector de la desiderata
     */
    private Lector              lector;

    /**
     * referencia al motivo de rechazo de la desiderata en caso de ser rechazada
     */
    private MotivoRechazo       motivoRechazo;

    /**
     * referencia a la biblioteca que gestiona el motivo
     */
    private Registro            registro;

    /**
     * referencia al pedido
     */
    private Pedido              pedido;

    /**
     * estado de la desiderata
     */
    private String              estado;

    /**
     * tipo de canal de la desiderata
     */
    private String              tipoCanal;

    /**
     * Constructor sin parámetros
     */
    protected Desiderata() {
        super();
    }

    /**
     * Constructor con parámetros
     *
     * @param autor
     * @param edicion
     * @param editorial
     * @param fechaPublicacion
     * @param isbn
     * @param notas
     * @param notasBibliotecario
     * @param reservar
     * @param titulo
     */
    public Desiderata(String autor, String edicion, String editorial,
            String fechaPublicacion, String isbn, String notas,
            String notasBibliotecario, Boolean reservar, String titulo,
            Biblioteca biblioteca) {
        super();
        this.autor = autor;
        this.edicion = edicion;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.isbn = isbn;
        this.notas = notas;
        this.notasBibliotecario = notasBibliotecario;
        this.reservar = reservar;
        this.titulo = titulo;
        this.biblioteca = biblioteca;
    }

    /**
     * constructor que crea una instancia de Lector que se utilizará en sesión
     * para mostrar la información obtenida de NCIP
     */
    public Desiderata(Long idNcip) {
        // establecemos el identificador remoto NCIP
        setIdNcip(idNcip);

        // establecemos la propiedad temporal para que este POJO
        // no se pueda guardar en BBDD, genericDao.save(lectorTemporal);
        // devolvería una excepcion
        setTemporal(true);
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = Desiderata.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = Desiderata.ID_SEQUENCE_NAME)
    @Column(name = Desiderata.COLUMN_NAME_ID)
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
     * @return the titulo
     */
    @Column(name = Desiderata.COLUMN_NAME_TITULO, length = 100)
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo
     *            the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autor
     */
    @Column(name = Desiderata.COLUMN_NAME_AUTOR, length = 100)
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor
     *            the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the editorial
     */
    @Column(name = Desiderata.COLUMN_NAME_EDITORIAL, length = 100)
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial
     *            the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * @return the edicion
     */
    @Column(name = Desiderata.COLUMN_NAME_EDICION, length = 100)
    public String getEdicion() {
        return edicion;
    }

    /**
     * @param edicion
     *            the edicion to set
     */
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    /**
     * @return the fechaPublicacion
     */
    @Column(name = Desiderata.COLUMN_NAME_FECHA_PUBLICACION, length = 20)
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion
     *            the fechaPublicacion to set
     */
    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return the fechaEstado
     */
    @Column(name = Desiderata.COLUMN_NAME_FECHA_ESTADO)
    public Date getFechaEstado() {
        return fechaEstado;
    }

    /**
     * @param fechaEstado
     *            the fechaEstado to set
     */
    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    /**
     * @return the fechaSolicitud
     */
    @Column(name = Desiderata.COLUMN_NAME_FECHA_SOLICITUD)
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * @param fechaSolicitud
     *            the fechaSolicitud to set
     */
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    /**
     * @return the isbn
     */
    @Column(name = Desiderata.COLUMN_NAME_ISBN, length = 25)
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the notasBibliotecario
     */
    @Column(name = Desiderata.COLUMN_NOTAS_BIBLIOTECARIO, length = 400)
    public String getNotasBibliotecario() {
        return notasBibliotecario;
    }

    /**
     * @param notasBibliotecario
     *            the notasBibliotecario to set
     */
    public void setNotasBibliotecario(String notasBibliotecario) {
        this.notasBibliotecario = notasBibliotecario;
    }

    /**
     * @return the notas
     */
    @Column(name = Desiderata.COLUMN_NAME_NOTAS, length = 400)
    public String getNotas() {
        return notas;
    }

    /**
     * @param notas
     *            the notas to set
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * @return the reservar
     */
    @Column(name = Desiderata.COLUMN_NAME_RESERVAR)
    public Boolean getReservar() {
        return reservar;
    }

    /**
     * @param reservar
     *            the reservar to set
     */
    public void setReservar(Boolean reservar) {
        this.reservar = reservar;
    }

    /**
     * @return the biblioteca
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adminconfig.model.Biblioteca.class)
    @JoinColumn(name = Desiderata.COLUMN_NAME_BIBLIOTECA_FK)
    @ForeignKey(name = "FK_BIB_X_BIBLIOTECA_DES")
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca
     *            the biblioteca to set
     */
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    /**
     * @return the lector
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.lectores.model.Lector.class)
    @JoinColumn(name = Desiderata.COLUMN_NAME_LECTOR_FK)
    public Lector getLector() {
        return lector;
    }

    /**
     * @param lector
     *            the lector to set
     */
    public void setLector(Lector lector) {
        this.lector = lector;
    }

    /**
     * @return the motivoRechazo
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.MotivoRechazo.class)
    @JoinColumn(name = Desiderata.COLUMN_NAME_MOTIVO_RECHAZO_FK)
    public MotivoRechazo getMotivoRechazo() {
        return motivoRechazo;
    }

    /**
     * @param motivoRechazo
     *            the motivoRechazo to set
     */
    public void setMotivoRechazo(MotivoRechazo motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    /**
     * @return the registro
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.catalogacion.model.Registro.class)
    @JoinColumn(name = Desiderata.COLUMN_NAME_REGISTRO_FK)
    public Registro getRegistro() {
        return registro;
    }

    /**
     * @param registro
     *            the registro to set
     */
    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    /**
     * @return the pedido
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = org.librae.adquisicion.model.Pedido.class)
    @JoinColumn(name = Desiderata.COLUMN_NAME_PEDIDO_FK)
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido
     *            the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the estado
     */
    @Column(name = Desiderata.COLUMN_NAME_ESTADO, length = 40)
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }


    /**
     * @return the tipoCanal
     */
    @Column(name = Desiderata.COLUMN_NAME_TIPO_CANAL, length = 20)
    public String getTipoCanal() {
        return tipoCanal;
    }

    /**
     * @param tipoCanal
     *            the tipoCanal to set
     */
    public void setTipoCanal(String tipoCanal) {
        this.tipoCanal = tipoCanal;
    }


    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result + ((autor == null) ? 0 : autor.hashCode());
        result = prime * result
                + ((biblioteca == null) ? 0 : biblioteca.hashCode());
        result = prime * result + ((edicion == null) ? 0 : edicion.hashCode());
        result = prime * result
                + ((editorial == null) ? 0 : editorial.hashCode());
        result = prime * result
                + ((fechaEstado == null) ? 0 : fechaEstado.hashCode());
        result = prime
                * result
                + ((fechaPublicacion == null) ? 0 : fechaPublicacion.hashCode());
        result = prime * result
                + ((fechaSolicitud == null) ? 0 : fechaSolicitud.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        result = prime * result + ((lector == null) ? 0 : lector.hashCode());
        result = prime * result
                + ((motivoRechazo == null) ? 0 : motivoRechazo.hashCode());
        result = prime * result + ((notas == null) ? 0 : notas.hashCode());
        result = prime
                * result
                + ((notasBibliotecario == null) ? 0 : notasBibliotecario
                        .hashCode());
        result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
        result = prime * result
                + ((registro == null) ? 0 : registro.hashCode());
        result = prime * result
                + ((reservar == null) ? 0 : reservar.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
        if (!(obj instanceof Desiderata)) {
            return false;
        }
        final Desiderata other = (Desiderata) obj;
        if (autor == null) {
            if (other.autor != null) {
                return false;
            }
        } else if (!autor.equals(other.autor)) {
            return false;
        }
        if (biblioteca == null) {
            if (other.biblioteca != null) {
                return false;
            }
        } else if (!biblioteca.equals(other.biblioteca)) {
            return false;
        }
        if (edicion == null) {
            if (other.edicion != null) {
                return false;
            }
        } else if (!edicion.equals(other.edicion)) {
            return false;
        }
        if (editorial == null) {
            if (other.editorial != null) {
                return false;
            }
        } else if (!editorial.equals(other.editorial)) {
            return false;
        }
        if (fechaEstado == null) {
            if (other.fechaEstado != null) {
                return false;
            }
        } else if (!fechaEstado.equals(other.fechaEstado)) {
            return false;
        }
        if (fechaPublicacion == null) {
            if (other.fechaPublicacion != null) {
                return false;
            }
        } else if (!fechaPublicacion.equals(other.fechaPublicacion)) {
            return false;
        }
        if (fechaSolicitud == null) {
            if (other.fechaSolicitud != null) {
                return false;
            }
        } else if (!fechaSolicitud.equals(other.fechaSolicitud)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (isbn == null) {
            if (other.isbn != null) {
                return false;
            }
        } else if (!isbn.equals(other.isbn)) {
            return false;
        }
        if (lector == null) {
            if (other.lector != null) {
                return false;
            }
        } else if (!lector.equals(other.lector)) {
            return false;
        }
        if (motivoRechazo == null) {
            if (other.motivoRechazo != null) {
                return false;
            }
        } else if (!motivoRechazo.equals(other.motivoRechazo)) {
            return false;
        }
        if (notas == null) {
            if (other.notas != null) {
                return false;
            }
        } else if (!notas.equals(other.notas)) {
            return false;
        }
        if (notasBibliotecario == null) {
            if (other.notasBibliotecario != null) {
                return false;
            }
        } else if (!notasBibliotecario.equals(other.notasBibliotecario)) {
            return false;
        }
        if (pedido == null) {
            if (other.pedido != null) {
                return false;
            }
        } else if (!pedido.equals(other.pedido)) {
            return false;
        }
        if (registro == null) {
            if (other.registro != null) {
                return false;
            }
        } else if (!registro.equals(other.registro)) {
            return false;
        }
        if (reservar == null) {
            if (other.reservar != null) {
                return false;
            }
        } else if (!reservar.equals(other.reservar)) {
            return false;
        }
        if (titulo == null) {
            if (other.titulo != null) {
                return false;
            }
        } else if (!titulo.equals(other.titulo)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this).append(
                Desiderata.COLUMN_NAME_ID, id).append(
                Desiderata.COLUMN_NAME_ESTADO, estado).append(
                Desiderata.COLUMN_NAME_TITULO, titulo).append(
                Desiderata.COLUMN_NAME_AUTOR, autor).append(
                Desiderata.COLUMN_NAME_EDITORIAL, editorial).append(
                Desiderata.COLUMN_NAME_EDICION, edicion).append(
                Desiderata.COLUMN_NAME_FECHA_PUBLICACION, fechaPublicacion)
                .append(Desiderata.COLUMN_NAME_FECHA_ESTADO, fechaEstado)
                .append(Desiderata.COLUMN_NAME_FECHA_SOLICITUD, fechaSolicitud)
                .append(Desiderata.COLUMN_NAME_ISBN, isbn).append(
                        Desiderata.COLUMN_NOTAS_BIBLIOTECARIO,
                        notasBibliotecario).append(
                        Desiderata.COLUMN_NAME_NOTAS, notas).append(
                        Desiderata.COLUMN_NAME_RESERVAR, reservar);
        if (getBiblioteca() != null) {
            sb.append(Desiderata.COLUMN_NAME_BIBLIOTECA_FK, getBiblioteca()
                    .getId());
        }
        return sb.toString();
    }

}