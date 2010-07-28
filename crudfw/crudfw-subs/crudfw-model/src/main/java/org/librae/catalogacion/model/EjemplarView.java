package org.librae.catalogacion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.librae.common.model.SpringRemotableLazyEntity;
import org.librae.common.service.impl.ComboLocaleManager;

/**
 * Bean para almacenar una Procedencia
 * 
 * @author jcdiaz
 */
@Entity(name = EjemplarView.ENTITY_NAME)
@Table(name = EjemplarView.TABLE_NAME)
public class EjemplarView extends SpringRemotableLazyEntity<EjemplarView> {

    /**
     * BaseObject es Serializable, por lo tanto Procedencia necesita un Serial
     * Version UID
     */
    private static final long  serialVersionUID                     = 1L;

    public static final String ENTITY_NAME                          = "org.librae.catalogacion.model.EjemplarView";

    /**
     * Constantes para referencia de los atributos de Procedencia
     */
    public static final String PROPTY_NAME_ID                       = "id";

    public static final String PROPTY_NAME_SITUACION                = "situacion";
    public static final String PROPTY_NAME_SITUACION_ID             = "ejemplarSituacion";

    public static final String PROPTY_NAME_CODIGO_BARRAS            = "codigoBarras";
    public static final String PROPTY_NAME_AUTOR                    = "autor";
    public static final String PROPTY_NAME_TITULO                   = "titulo";
    public static final String PROPTY_NAME_SUCURSAL                 = "sucursal";
    public static final String PROPTY_NAME_LOCALIZACION             = "localizacion";
    public static final String PROPTY_NAME_LOCALIZACION_ID          = "ejemplarLocalizacion";
    public static final String PROPTY_NAME_TIPO                     = "tipo";
    public static final String PROPTY_NAME_TIPO_ID                  = "ejemplarTipo";
    public static final String PROPTY_NAME_ESTADO                   = "estado";
    public static final String PROPTY_NAME_ESTADO_ID                = "ejemplarEstado";
    public static final String PROPTY_NAME_SIGNATURA                = "signatura";
    public static final String PROPTY_NAME_NUMERO_LECTOR            = "numeroLector";
    public static final String PROPTY_NAME_NOMBRE                   = "nombre";
    public static final String PROPTY_NAME_FECHA_PRESTAMO           = "fechaPrestamo";
    public static final String PROPTY_NAME_FECHA_DEVOLUCION         = "fechaDevolucion";
    public static final String PROPTY_NAME_FECHA_CAMBIO_SITUACION   = "fechaCambioSituacion";
    public static final String PROPTY_NAME_PROCEDENCIA              = "procedencia";
    public static final String PROPTY_NAME_PROCEDENCIA_TEXTO        = "procedencia_texto";
    public static final String PROPTY_NAME_SOPORTE                  = "soporte";
    public static final String PROPTY_NAME_SOPORTE_ID               = "ejemplarSoporte";
    public static final String PROPTY_NAME_SIGNATURA_SUPLEMENTARIA  = "signaturaSuplementaria";
    public static final String PROPTY_NAME_VOLUMEN                  = "volumen";
    public static final String PROPTY_NAME_FECHA_INGRESO_INVENTARIO = "fechaIngresoInventario";
    public static final String PROPTY_NAME_FECHA_REGISTRO           = "fechaRegistro";

    public static final String PROPTY_NAME_MATERIA                  = "materia";
    public static final String PROPTY_NAME_EDITORIAL                = "editorial";
    public static final String PROPTY_NAME_INTERNACIONAL_NUMBER     = "internacionalNumber";
    public static final String PROPTY_NAME_VISIBLE_OPAC             = "visibleOPACRegistro";
    public static final String PROPTY_NAME_PRINCIPAL                = "principal";
    public static final String PROPTY_NAME_BIBLIOTECA_ID            = "biblioteca";

    public static final String PROPTY_NAME_NUMERO_PRESTAMOS         = "numeroPrestamos";
    public static final String PROPTY_NAME_NUMERO_REGISTRO          = "numeroRegistro";
    public static final String PROPTY_NAME_TELEFONO                 = "telefono";
    public static final String PROPTY_NAME_PRESTAMO_ID              = "prestamo";
    public static final String PROPTY_NAME_RESERVA_ID               = "reserva";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String TABLE_NAME                           = "VIEW_CAT_LISTADO_EJEMPLARES";
    // private static final String ID_GENERATOR_NAME =
    // "generator_cat_view_listado_ejemplares";
    // private static final String ID_SEQUENCE_NAME =
    // "SEQ_VIEW_CAT_LISTADO_EJEMPLARES";

    public static final String COLUMN_NAME_ID                       = "X_VIEW_CAT_LISTADO_EJEMPLARES";

    public static final String COLUMN_NAME_SITUACION                = "T_SITUACION";
    public static final String COLUMN_NAME_SITUACION_ID             = "X_EJEMPLAR_SITUACION";
    public static final String COLUMN_NAME_CODIGO_BARRAS            = "N_CODIGO_BARRAS";
    public static final String COLUMN_NAME_AUTOR                    = "R_AUTOR";
    public static final String COLUMN_NAME_TITULO                   = "R_TITULO";
    public static final String COLUMN_NAME_SUCURSAL                 = "T_BIBLIOTECA";
    public static final String COLUMN_NAME_LOCALIZACION             = "T_LOCALIZACION";
    public static final String COLUMN_NAME_LOCALIZACION_ID          = "X_EJEMPLAR_LOCALIZACION";
    public static final String COLUMN_NAME_TIPO                     = "T_TIPO";
    public static final String COLUMN_NAME_TIPO_ID                  = "X_EJEMPLAR_TIPO";
    public static final String COLUMN_NAME_ESTADO                   = "T_ESTADO";
    public static final String COLUMN_NAME_ESTADO_ID                = "EJE_X_EJEMPLAR_ESTADO";
    public static final String COLUMN_NAME_SIGNATURA                = "T_SIGNATURA";
    public static final String COLUMN_NAME_NUMERO_LECTOR            = "N_NUMERO_LECTOR";
    public static final String COLUMN_NAME_NOMBRE                   = "T_NOMBRE";
    public static final String COLUMN_NAME_FECHA_PRESTAMO           = "F_FECHA_PRESTAMO";
    public static final String COLUMN_NAME_FECHA_DEVOLUCION         = "F_FECHA_DEVOLUCION";
    public static final String COLUMN_NAME_CAMBIO_SITUACION         = "F_FECHA_CAMBIO_SITUACION";
    public static final String COLUMN_NAME_PROCEDENCIA              = "T_PROCEDENCIA";
    public static final String COLUMN_NAME_PROCEDENCIA_ID           = "PRO_X_PROCEDENCIA";
    public static final String COLUMN_NAME_SOPORTE                  = "T_SOPORTE";
    public static final String COLUMN_NAME_SOPORTE_ID               = "EJE_X_EJEMPLAR_SOPORTE";
    public static final String COLUMN_NAME_SIGNATURA_SUPLEMENTARIA  = "T_SIGNATURA_SUPLEMENTARIA";
    public static final String COLUMN_NAME_VOLUMEN                  = "C_VOLUMEN";
    public static final String COLUMN_NAME_FECHA_INGRESO_INVENTARIO = "F_FECHA_INGRESO_INVENTARIO";
    public static final String COLUMN_NAME_FECHA_REGISTRO           = "F_FECHA_REGISTRO";
    public static final String COLUMN_NAME_VISIBLE_OPAC             = "L_VISIBLE_OPAC";
    public static final String COLUMN_NAME_MATERIA                  = "R_MATERIA";
    public static final String COLUMN_NAME_EDITORIAL                = "R_EDITORIAL";
    public static final String COLUMN_NAME_INTERNACIONAL_NUMBER     = "R_INTERNACIONAL_NUMBER";
    public static final String COLUMN_NAME_PRINCIPAL                = "L_PRINCIPAL";
    public static final String COLUMN_NAME_BIBLIOTECA_ID            = "BIB_X_BIBLIOTECA";

    public static final String COLUMN_NAME_NUMERO_PRESTAMOS         = "N_NUMERO_PRESTAMOS";
    public static final String COLUMN_NAME_NUMERO_REGISTRO          = "N_NUMERO_REGISTRO";
    public static final String COLUMN_NAME_TELEFONO                 = "N_TELEFONO";
    public static final String COLUMN_NAME_PRESTAMO_ID              = "PRE_X_PRESTAMO";
    public static final String COLUMN_NAME_RESERVA_ID               = "RES_X_RESERVA";

    /**
     * Clave autonumérica secuencial sin significado funcional
     */
    private Long               id;

    private String             situacion;
    private Long               ejemplarSituacion;
    private String             codigoBarras;
    private String             autor;
    private String             titulo;
    private String             sucursal;
    private String             localizacion;
    private Long               ejemplarLocalizacion;
    private String             tipo;
    private Long               ejemplarTipo;
    private String             estado;
    private Long               ejemplarEstado;
    private String             signatura;
    private String             signaturaSuplementaria;
    private String             numeroLector;
    private String             nombre;
    private Date               fechaPrestamo;
    private Date               fechaDevolucion;
    private Date               fechaCambioSituacion;
    private Long               procedencia;
    private String             procedencia_texto;
    private String             soporte;
    private Long               ejemplarSoporte;
    private String             volumen;
    private Date               fechaIngresoInventario;
    private Date               fechaRegistro;
    private Boolean            visibleOPACRegistro                  = true;
    private String             internacionalNumber;
    private String             editorial;
    private String             materia;
    private Boolean            principal;
    private Long               biblioteca;
    private boolean            ejemplarSelected;
    private Integer            numeroRegistro;
    private Integer            numeroPrestamos;
    private String             telefono;
    private Long               prestamo;
    private Long               reserva;

    /**
     * Constructor sin parámetros
     */
    protected EjemplarView() {
        super();
    }

    /**
     * Constructor con parámetros
     * 
     * @param codigo
     * @param procedencia
     */

    public EjemplarView(final String codBarras) {
        super();
        this.codigoBarras = codBarras;
    }

    /**
     * @return the id
     */
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO, generator =
    // EjemplarView.ID_GENERATOR_NAME)
    // @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName =
    // EjemplarView.ID_SEQUENCE_NAME)
    @Column(name = EjemplarView.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    @Column(name = EjemplarView.COLUMN_NAME_SITUACION)
    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    @Column(name = EjemplarView.COLUMN_NAME_SITUACION_ID)
    public Long getEjemplarSituacion() {
        return ejemplarSituacion;
    }

    public void setEjemplarSituacion(Long ejemplarSituacion) {
        this.ejemplarSituacion = ejemplarSituacion;
    }

    @Column(name = EjemplarView.COLUMN_NAME_LOCALIZACION_ID)
    public Long getEjemplarLocalizacion() {
        return ejemplarLocalizacion;
    }

    public void setEjemplarLocalizacion(Long ejemplarLocalizacion) {
        this.ejemplarLocalizacion = ejemplarLocalizacion;
    }

    @Column(name = EjemplarView.COLUMN_NAME_TIPO_ID)
    public Long getEjemplarTipo() {
        return ejemplarTipo;
    }

    public void setEjemplarTipo(Long ejemplarTipo) {
        this.ejemplarTipo = ejemplarTipo;
    }

    @Column(name = EjemplarView.COLUMN_NAME_ESTADO)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = EjemplarView.COLUMN_NAME_ESTADO_ID)
    public Long getEjemplarEstado() {
        return ejemplarEstado;
    }

    public void setEjemplarEstado(Long ejemplarEstado) {
        this.ejemplarEstado = ejemplarEstado;
    }

    @Column(name = EjemplarView.COLUMN_NAME_CODIGO_BARRAS)
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Column(name = EjemplarView.COLUMN_NAME_AUTOR)
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Column(name = EjemplarView.COLUMN_NAME_TITULO)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name = EjemplarView.COLUMN_NAME_SUCURSAL)
    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    @Column(name = EjemplarView.COLUMN_NAME_LOCALIZACION)
    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    @Column(name = EjemplarView.COLUMN_NAME_TIPO)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(name = EjemplarView.COLUMN_NAME_SIGNATURA)
    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
    }

    @Column(name = EjemplarView.COLUMN_NAME_NUMERO_LECTOR)
    public String getNumeroLector() {
        return numeroLector;
    }

    public void setNumeroLector(String numeroLector) {
        this.numeroLector = numeroLector;
    }

    @Column(name = EjemplarView.COLUMN_NAME_NOMBRE)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = EjemplarView.COLUMN_NAME_FECHA_PRESTAMO)
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    @Column(name = EjemplarView.COLUMN_NAME_FECHA_DEVOLUCION)
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Column(name = EjemplarView.COLUMN_NAME_PROCEDENCIA_ID)
    public Long getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(Long procedencia) {
        this.procedencia = procedencia;
    }

    @Column(name = EjemplarView.COLUMN_NAME_PROCEDENCIA)
    public String getProcedencia_texto() {
        return procedencia_texto;
    }

    public void setProcedencia_texto(String procedencia_texto) {
        this.procedencia_texto = procedencia_texto;
    }

    @Column(name = EjemplarView.COLUMN_NAME_SOPORTE)
    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    @Column(name = EjemplarView.COLUMN_NAME_SOPORTE_ID)
    public Long getEjemplarSoporte() {
        return ejemplarSoporte;
    }

    public void setEjemplarSoporte(Long ejemplarSoporte) {
        this.ejemplarSoporte = ejemplarSoporte;
    }

    @Column(name = EjemplarView.COLUMN_NAME_SIGNATURA_SUPLEMENTARIA)
    public String getSignaturaSuplementaria() {
        return signaturaSuplementaria;
    }

    public void setSignaturaSuplementaria(String signaturaSuplementaria) {
        this.signaturaSuplementaria = signaturaSuplementaria;
    }

    @Column(name = EjemplarView.COLUMN_NAME_CAMBIO_SITUACION)
    public Date getFechaCambioSituacion() {
        return fechaCambioSituacion;
    }

    public void setFechaCambioSituacion(Date fechaCambioSituacion) {
        this.fechaCambioSituacion = fechaCambioSituacion;
    }

    @Column(name = EjemplarView.COLUMN_NAME_VOLUMEN)
    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    @Column(name = EjemplarView.COLUMN_NAME_FECHA_INGRESO_INVENTARIO)
    public Date getFechaIngresoInventario() {
        return fechaIngresoInventario;
    }

    public void setFechaIngresoInventario(Date fechaIngresoInventario) {
        this.fechaIngresoInventario = fechaIngresoInventario;
    }

    @Column(name = EjemplarView.COLUMN_NAME_FECHA_REGISTRO)
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Column(name = EjemplarView.COLUMN_NAME_VISIBLE_OPAC)
    public Boolean getVisibleOPACRegistro() {
        return visibleOPACRegistro;
    }

    public void setVisibleOPACRegistro(Boolean visibleOPACRegistro) {
        this.visibleOPACRegistro = visibleOPACRegistro;
    }

    @Column(name = EjemplarView.COLUMN_NAME_INTERNACIONAL_NUMBER)
    public String getInternacionalNumber() {
        return internacionalNumber;
    }

    public void setInternacionalNumber(String internacionalNumber) {
        this.internacionalNumber = internacionalNumber;
    }

    @Column(name = EjemplarView.COLUMN_NAME_EDITORIAL)
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Column(name = EjemplarView.COLUMN_NAME_MATERIA)
    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @Column(name = EjemplarView.COLUMN_NAME_PRINCIPAL)
    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    @Column(name = EjemplarView.COLUMN_NAME_BIBLIOTECA_ID)
    public Long getBiblioteca() {
        return biblioteca;
    }

    @Column(name = EjemplarView.COLUMN_NAME_NUMERO_REGISTRO)
    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    @Column(name = EjemplarView.COLUMN_NAME_NUMERO_PRESTAMOS)
    public Integer getNumeroPrestamos() {
        return numeroPrestamos;
    }

    public void setNumeroPrestamos(Integer numeroPrestamos) {
        this.numeroPrestamos = numeroPrestamos;
    }

    @Column(name = EjemplarView.COLUMN_NAME_TELEFONO)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setBiblioteca(Long biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Column(name = EjemplarView.COLUMN_NAME_PRESTAMO_ID)
    public Long getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Long prestamo) {
        this.prestamo = prestamo;
    }

    /*
     * @Column(name = EjemplarView.COLUMN_NAME_RESERVA_ID) public Long
     * getReserva() { return reserva; } public void setReserva(Long reserva) {
     * this.reserva = reserva; }
     */

    /**
     * @return the ejemplarSelected
     */
    @Transient
    public boolean isEjemplarSelected() {
        return ejemplarSelected;
    }

    /**
     * @param ejemplarSelected
     *            the ejemplarSelected to set
     */

    public void setEjemplarSelected(boolean ejemplarSelected) {
        this.ejemplarSelected = ejemplarSelected;
    }

    @Override
    public EjemplarView newInstance() {
        return new EjemplarView();
    }

    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Transient
    public String getSituacionLocale() {
        String s = "";
        if (situacion != null) {
            s = ComboLocaleManager.get(situacion.replace("#", ""));
        }
        return s;
    }

    @Transient
    public String getTipoLocale() {
        String s = "";
        if (tipo != null) {
            s = ComboLocaleManager.get(tipo.replace("#", ""));
        }
        return s;
    }

    @Transient
    public String getLocalizacionLocale() {
        String s = "";
        if (this.localizacion != null) {
            s = ComboLocaleManager.get(localizacion.replace("#", ""));
        }
        return s;
    }

    @Transient
    public String getEstadoLocale() {
        String s = "";
        if (this.estado != null) {
            s = ComboLocaleManager.get(estado.replace("#", ""));
        }
        return s;
    }

    @Transient
    public String getProcedenciaLocale() {
        String s = "";
        if (this.procedencia_texto != null) {
            s = ComboLocaleManager.get(procedencia_texto.replace("#", ""));
        }
        return s;
    }

}
