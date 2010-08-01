package org.librae.circulacion.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.librae.adminconfig.model.Direccion;
import org.librae.catalogacion.model.EjemplarLocalizacion;
import org.librae.common.model.parampoliticas.AbstractParamPolBiblioteca;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;

/**
 * Bibliotecas participantes en un consorcio.<br>
 * Los requisitos para modelizar esta tabla son:<br>
 * - Que las bibliotecas de un consorcio se puedan buscar por dieferentes
 * criterios para seleccionar la del lector en el momento de hacer un préstamo
 * en red.<br>
 * - Que en un consorcio puedan participar bibliotecas con otros SIGB (por estas
 * dos razones se diplican aquí las columnas relevante de la entidad
 * BIBLIOTECAS, y la clave ajena BIB_X_BIBLIOTECA solo es relevante a efectos
 * informativos si la biblioteca está registrada en esta aplicación).<br>
 * - Cada biblioteca debe informar de la URL del servidor de NCIP
 * (T_URL_SRV_NCIP), y el puerto de escucha (N_PORT).
 * 
 * @author asantamaria
 */
@Entity(name = BibliotecaConsorcio.ENTITY_NAME)
@Table(name = BibliotecaConsorcio.TABLE_NAME)
public class BibliotecaConsorcio extends AbstractParamPolBiblioteca {

    /**
     * BaseObject is Serializable, so BibliotecaConsorcio needs a Serial Version
     * UID
     */
    private static final long    serialVersionUID                        = -984796517233381665L;

    public static final String   ENTITY_NAME                             = "org.librae.adminconfig.model.BibliotecaConsorcio";
    public static final String   TABLE_NAME                              = "ADM_CONSORCIOS_BIBLIOTECAS";
    public static final String   ID_GENERATOR_NAME                       = "generator_adm_consorcios_bibliotecas";
    private static final String  ID_SEQUENCE_NAME                        = "SEQ_ADM_CONSORCIOS_BIBLIOTECAS";
    public static final String   COLUMN_NAME_ID                          = "X_CONSORCIO_BIBLIOTECA";
    public static final String   COLUMN_NAME_CODIGO                      = "C_CONSORCIO_BIBLIOTECA";
    public static final String   COLUMN_NAME_NOMBRE                      = "T_BIBLIOTECA";
    public static final String   COLUMN_NAME_DESCRIPCION                 = "T_BIBLIOTECA_ALT";
    public static final String   COLUMN_NAME_PERSONA_CONTACTO            = "T_PERSONA_CONTACTO";
    public static final String   COLUMN_NAME_DIRECCION                   = "DIR_X_DIRECCION";
    public static final String   COLUMN_NAME_TELEFONO                    = "T_TELEFONO";
    public static final String   COLUMN_NAME_CONSORCIO                   = "CON_X_CONSORCIO";
    public static final String   COLUMN_NAME_EJEMPLAR_LOCALIZACION_DEV   = "EJE_LOC_X_EJEMPLAR_LOC_DEV";
    public static final String   COLUMN_NAME_FECHA_ADHESION              = "F_ADHESION";
    public static final String   COLUMN_NAME_FECHA_BAJA                  = "F_BAJA";
    public static final String   COLUMN_NAME_ACEPTA_DEV_DE_OTRAS_BIB     = "L_ACEPTA_DEV_DE_OTRAS_BIB";
    public static final String   COLUMN_NAME_FOTO_LECTOR_VIA_SOAP        = "L_FOTO_LECTOR_VIA_SOAP";
    public static final String   COLUMN_NAME_PERMITE_DEV_EN_OTRAS_BIB    = "L_PERMITE_DEV_EN_OTRAS_BIB";
    public static final String   COLUMN_NAME_MAX_PREST_BIB               = "N_MAX_PREST_BIB";
    public static final String   COLUMN_NAME_ESTA_EN_BIBLIOTECAS         = "L_ESTA_EN_BIBLIOTECAS";
    public static final String   COLUMN_NAME_ACEPTA_REN_DE_OTRAS_BIB     = "L_ACEPTA_REN_DE_OTRAS_BIB";
    public static final String   COLUMN_NAME_PERMITE_REN_EN_OTRAS_BIB    = "L_PERMITE_REN_EN_OTRAS_BIB";

    public static final String   PROPERTY_NAME_ID                        = "id";
    public static final String   PROPERTY_NAME_CODIGO                    = "codigo";
    public static final String   PROPERTY_NAME_NOMBRE                    = "nombre";
    public static final String   PROPERTY_NAME_DESCRIPCION               = "descripcion";
    public static final String   PROPERTY_NAME_PERSONA_CONTACTO          = "personaContacto";
    public static final String   PROPERTY_NAME_DIRECCION                 = "direccion";
    public static final String   PROPERTY_NAME_TELEFONO                  = "telefono";
    public static final String   PROPERTY_NAME_CONSORCIO                 = "consorcio";
    public static final String   PROPERTY_NAME_EJEMPLAR_LOCALIZACION_DEV = "ejemplarLocalizacionDev";
    public static final String   PROPERTY_NAME_FECHA_ADHESION            = "fechaAdhesion";
    public static final String   PROPERTY_NAME_FECHA_BAJA                = "fechaBaja";
    public static final String   PROPERTY_NAME_ACEPTA_DEV_DE_OTRAS_BIB   = "aceptaDevDeOtrasBib";
    public static final String   PROPERTY_NAME_FOTO_LECTOR_VIA_SOAP      = "fotoLectorViaSoap";
    public static final String   PROPERTY_NAME_PERMITE_DEV_EN_OTRAS_BIB  = "permiteDevEnOtrasBib";
    public static final String   PROPERTY_NAME_MAX_PREST_BIB             = "maxPrestBib";
    public static final String   PROPERTY_NAME_ESTA_EN_BIBLIOTECAS       = "estaEnBibliotecas";

    /**
     * Identificador interno del parámetro
     */
    private Long                 id;

    /**
     * !! IMPORTANTE !!<br>
     * Para las bibliotecas con SIGB LibreA/LibrEx, este valor es EXACTAMENTE el
     * correspondiente a la biblioteca en BIBLIOTECAS.X_BIBLIOTECA,
     * independientemente de que la biblioteca participe en más de un consorcio
     * (los valores de esta columna no son únicos).<br>
     * <br>
     * Código identificativo por el que la aplicación APRC de este consorcio
     * conoce a la biblioteca.<br>
     */
    private String               codigo;

    /**
     * Descripción o nombre de biblioteca (texto libre) asignado por el usuario
     */
    private String               nombre;

    /**
     * Descripción (o nombre) ampliada o alternativa de biblioteca. Texto libre
     * asignado por el usuario.
     */
    private String               descripcion;

    /**
     * Persona o perosnas de contacto de la biblioteca
     */
    private String               personaContacto;

    /**
     * >> DIRECCIONES
     */
    private Direccion            direccion;

    /**
     * Lista de teléfonos de contacto de la biblioteca
     */
    private String               telefono;

    /**
     * Clave ajena a consorcios.<br>
     * Indica a que consorcio pertenece la biblioteca de esta fila
     */
    private Consorcio            consorcio;

    /**
     * >> EJEMPLARES_LOCALIZACIONES<br>
     * <br>
     * localización con el que cada biblioteca indica donde coloca los
     * ejemplares de otras bibliotecas que se devuelven en ella, antes de
     * trasladarlos a la biblioteca propietaria.<br>
     * La localización referenciada se envía en respuestas de NCIP como elemento
     * de dato Location Name Value<br>
     * NULL por defecto
     */
    private EjemplarLocalizacion ejemplarLocalizacionDev;
    /**
     * Fecha-Hora de adhesión de la biblioteca al consorcio
     */
    private Date                 fechaAdhesion;

    /**
     * Fecha de baja de una biblioteca en el consorcio.<br>
     * Si está cumplimentada, la biblioteca no pertenece al consorcio
     */
    private Date                 fechaBaja;

    /**
     * true si esta biblioteca del Consorcio acepta que se devuelvan en ella
     * ejemplares obtenidos mediante préstamo en red en otras bibliotecas.<br>
     * false en caso contrario
     */
    private Boolean              aceptaDevDeOtrasBib;

    /**
     * true si esta biblioteca del Consorcio acepta que se renueven en ella
     * ejemplares obtenidos mediante préstamo en red en otras bibliotecas.<br>
     * false en caso contrario
     */
    private Boolean              aceptaRenDeOtrasBib;

    /**
     * true si esta biblioteca publica un servicio web que permita a una
     * aplicación cliente como APRC obtener la fotografía, que no puede
     * obtenerse vía NCIP.<br>
     * false en caso contrario<br>
     * Sólo para los lectores cuya biblioteca tiene este flag a true, se
     * mostrará la foto en la aplicación APRC. Pensado para las bibliotecas con
     * nuestro SIGB, para que éstas al menos puedan mostrar la foto del lector
     * en la aplicación APRC (NCIP no contempla la transmisión de imágenes ni
     * datos binarios en general).
     */
    private Boolean              fotoLectorViaSoap;

    /**
     * true si esta biblioteca permite que sus ejemplares obtenidos mediante
     * préstamo en red sean devueltos en otras bibliotecas del Consorcio<br>
     * false en caso contrario
     */
    private Boolean              permiteDevEnOtrasBib;

    /**
     * true si esta biblioteca permite que sus ejemplares obtenidos mediante
     * préstamo en red sean devueltos en otras bibliotecas del Consorcio<br>
     * false en caso contrario
     */
    private Boolean              permiteRenEnOtrasBib;

    /**
     * true si esta biblioteca consorciada está en la tabla BIBLIOTECAS, es
     * decir, si C_CONSORCIO_BIBLIOTECA es clave ajena. false en caso contrario.
     */
    private Boolean              estaEnBibliotecas;

    /**
     * Nº máximo de préstamos que un lector puede hacer de ejemplares de esta
     * biblioteca, con la modalidad de préstamo en red. INcluyendo los préstamos
     * que el lector tiene en su propia biblioteca.
     */
    private Long                 maxPrestBib;

    /** Listado de NcipServicio */
    private List<NcipServicio>   ncipServicios;

    protected BibliotecaConsorcio() {
        super();
    }

    public BibliotecaConsorcio(String codigo, String nombre) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = BibliotecaConsorcio.ID_GENERATOR_NAME)
    @SequenceGenerator(name = BibliotecaConsorcio.ID_GENERATOR_NAME, sequenceName = BibliotecaConsorcio.ID_SEQUENCE_NAME)
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_ID)
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
     * @return the codigo
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_CODIGO, length = 40)
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_NOMBRE, length = 80)
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_DESCRIPCION)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the personaContacto
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_PERSONA_CONTACTO, length = 80)
    public String getPersonaContacto() {
        return personaContacto;
    }

    /**
     * @param personaContacto
     *            the personaContacto to set
     */
    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    /**
     * @return the direccion
     */
    @ManyToOne(targetEntity = Direccion.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = BibliotecaConsorcio.COLUMN_NAME_DIRECCION)
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * @param direccion
     *            the direccion to set
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_TELEFONO, length = 80)
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono
     *            the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the consorcio
     */
    @ManyToOne(targetEntity = Consorcio.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinColumn(name = BibliotecaConsorcio.COLUMN_NAME_CONSORCIO)
    @ForeignKey(name = "FK_SIGB_BIB_X_CONSORCIO")
    public Consorcio getConsorcio() {
        return consorcio;
    }

    /**
     * @param consorcio
     *            the consorcio to set
     */
    public void setConsorcio(Consorcio consorcio) {
        this.consorcio = consorcio;
    }

    /**
     * @return the ejemplarLocalizacionDev
     */

    @ManyToOne(targetEntity = EjemplarLocalizacion.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = BibliotecaConsorcio.COLUMN_NAME_EJEMPLAR_LOCALIZACION_DEV)
    public EjemplarLocalizacion getEjemplarLocalizacionDev() {
        return ejemplarLocalizacionDev;
    }

    /**
     * @param ejemplarLocalizacionDev
     *            the ejemplarLocalizacionDev to set
     */
    public void setEjemplarLocalizacionDev(
            EjemplarLocalizacion ejemplarLocalizacionDev) {
        this.ejemplarLocalizacionDev = ejemplarLocalizacionDev;
    }

    /**
     * @return the fechaAdhesion
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_FECHA_ADHESION)
    public Date getFechaAdhesion() {
        return fechaAdhesion;
    }

    /**
     * @param fechaAdhesion
     *            the fechaAdhesion to set
     */
    public void setFechaAdhesion(Date fechaAdhesion) {
        this.fechaAdhesion = fechaAdhesion;
    }

    /**
     * @return the fechaBaja
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_FECHA_BAJA)
    public Date getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja
     *            the fechaBaja to set
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * @return the aceptaDevDeOtrasBib
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_ACEPTA_DEV_DE_OTRAS_BIB)
    public Boolean getAceptaDevDeOtrasBib() {
        return aceptaDevDeOtrasBib;
    }

    /**
     * @param aceptaDevDeOtrasBib
     *            the aceptaDevDeOtrasBib to set
     */
    public void setAceptaDevDeOtrasBib(Boolean aceptaDevDeOtrasBib) {
        this.aceptaDevDeOtrasBib = aceptaDevDeOtrasBib;
    }

    /**
     * @return the fotoLectorViaSoap
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_FOTO_LECTOR_VIA_SOAP)
    public Boolean getFotoLectorViaSoap() {
        return fotoLectorViaSoap;
    }

    /**
     * @param fotoLectorViaSoap
     *            the fotoLectorViaSoap to set
     */
    public void setFotoLectorViaSoap(Boolean fotoLectorViaSoap) {
        this.fotoLectorViaSoap = fotoLectorViaSoap;
    }

    /**
     * @return the permiteDevEnOtrasBib
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_PERMITE_DEV_EN_OTRAS_BIB)
    public Boolean getPermiteDevEnOtrasBib() {
        return permiteDevEnOtrasBib;
    }

    /**
     * @param permiteDevEnOtrasBib
     *            the permiteDevEnOtrasBib to set
     */
    public void setPermiteDevEnOtrasBib(Boolean permiteDevEnOtrasBib) {
        this.permiteDevEnOtrasBib = permiteDevEnOtrasBib;
    }

    /**
     * @return the maxPrestBib
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_MAX_PREST_BIB)
    public Long getMaxPrestBib() {
        return maxPrestBib;
    }

    @Column(name = BibliotecaConsorcio.COLUMN_NAME_ACEPTA_REN_DE_OTRAS_BIB)
    public Boolean getAceptaRenDeOtrasBib() {
        return aceptaRenDeOtrasBib;
    }

    public void setAceptaRenDeOtrasBib(Boolean aceptaRenDeOtrasBib) {
        this.aceptaRenDeOtrasBib = aceptaRenDeOtrasBib;
    }

    @Column(name = BibliotecaConsorcio.COLUMN_NAME_PERMITE_REN_EN_OTRAS_BIB)
    public Boolean getPermiteRenEnOtrasBib() {
        return permiteRenEnOtrasBib;
    }

    public void setPermiteRenEnOtrasBib(Boolean permiteRenEnOtrasBib) {
        this.permiteRenEnOtrasBib = permiteRenEnOtrasBib;
    }

    /**
     * @param maxPrestBib
     *            the maxPrestBib to set
     */
    public void setMaxPrestBib(Long maxPrestBib) {
        this.maxPrestBib = maxPrestBib;
    }

    /**
     * @return the estaEnBibliotecas
     */
    @Column(name = BibliotecaConsorcio.COLUMN_NAME_ESTA_EN_BIBLIOTECAS)
    public Boolean getEstaEnBibliotecas() {
        return estaEnBibliotecas;
    }

    /**
     * @param estaEnBibliotecas
     *            the estaEnBibliotecas to set
     */
    public void setEstaEnBibliotecas(Boolean estaEnBibliotecas) {
        this.estaEnBibliotecas = estaEnBibliotecas;
    }

    /**
     * @return the ncipServicios
     */
    @OneToMany(targetEntity = NcipServicio.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = NcipServicio.COLUMN_NAME_CONSORCIO_BIBLIOTECA)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<NcipServicio> getNcipServicios() {
        return ncipServicios;
    }

    /**
     * @param ncipServicios
     *            the ncipServicios to set
     */
    public void setNcipServicios(List<NcipServicio> ncipServicios) {
        this.ncipServicios = ncipServicios;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof BibliotecaConsorcio)) {
            return false;
        }

        final BibliotecaConsorcio other = (BibliotecaConsorcio) obj;

        if (getCodigo() == null && other.getCodigo() != null) {
            return false;
        }
        if (getCodigo() != null && !getCodigo().equals(other.getCodigo())) {
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
                + ((getCodigo() == null) ? 0 : getCodigo().hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(BibliotecaConsorcio.PROPERTY_NAME_ID, getId())

        .append(BibliotecaConsorcio.PROPERTY_NAME_CODIGO,
                (getCodigo() == null) ? "" : getCodigo().toString())

        .append(BibliotecaConsorcio.PROPERTY_NAME_NOMBRE,
                (getNombre() == null) ? "" : getNombre().toString())

        .append(BibliotecaConsorcio.PROPERTY_NAME_DESCRIPCION,
                (getDescripcion() == null) ? "" : getDescripcion().toString())

        .toString();
    }

    public AbstractParamPolBiblioteca newInstance() {
        return new BibliotecaConsorcio();
    }
}