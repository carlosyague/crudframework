package org.librae.catalogacion.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Bean para almacenar una AutoridadEtiqueta
 * 
 * @author jcdiaz
 * @version 2.0
 * @author asalas unificación de clases
 */
@Entity(name = AutoridadEtiqueta.ENTITY_NAME)
@Table(name = AutoridadEtiqueta.TABLE_NAME)
public class AutoridadEtiqueta extends EtiquetaMarc {

    /**
     * BaseObject es Serializable, por lo tanto AutoridadEtiqueta necesita un
     * Serial Version UID
     */
    private static final long       serialVersionUID                  = -1627805597598055491L;

    public static final String      ENTITY_NAME                       = "org.librae.catalogacion.model.AutoridadEtiqueta";

    /**
     * Constantes para referencia de los atributos de AutoridadEtiqueta
     */
    public static final String      PROPTY_NAME_AUTORIDAD             = "autoridad";
    public static final String      PROPTY_NAME_ORDEN                 = "orden";

    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String      TABLE_NAME                        = "CAT_AUTORIDAD_ETIQUETA";
    private static final String     ID_GENERATOR_NAME                 = "generator_cat_autoridad_etiqueta";
    private static final String     ID_SEQUENCE_NAME                  = "SEQ_CAT_AUTORIDAD_ETIQUETA";

    public static final String      COLUMN_NAME_ID                    = "X_AUTORIDAD_ETIQUETA";

    public static final String      COLUMN_NAME_AUTORIDAD_ETIQUETA_FK = "AUT_X_AUTORIDAD_ETIQUETA";
    public static final String      COLUMN_NAME_AUTORIDAD_FK          = "AUT_X_AUTORIDAD";
    public static final String      COLUMN_NAME_AUTORIDAD_VEASE       = "N_AUTORIDAD_VEASE";
    public static final String      COLUMN_NAME_ORDEN                 = "N_ORDEN";

    /**
     * Constante que se usa en el parseador de la clase
     * GeneracionCamposDublinCore.xml
     */
    public static final String      TODOS_LOS_ATRIBUTOS               = "all";

    /**
     *
     */
    private List<AutoridadSubcampo> autoridadSubcampos;

    /**
     *
     */
    private List<RegistroEtiqueta>  registroEtiquetas;
    /**
     *
     */
    private Autoridad               autoridad;

    private Long                    idAutoridadVease;

    /**
     * Constructor sin parámetros
     */
    protected AutoridadEtiqueta() {
        super();
        this.autoridadSubcampos = new ArrayList<AutoridadSubcampo>();
        this.registroEtiquetas = new ArrayList<RegistroEtiqueta>();
        super.setOrdenRepetibilidad(new Integer(1));
    }

    /**
     * Constructor con parámetros
     * 
     * @param codigo
     * @param indicador1
     * @param indicador2
     */
    public AutoridadEtiqueta(final String codigo, final String indicador1,
            final String indicador2) {
        super(codigo, indicador1, indicador2);
        this.autoridadSubcampos = new ArrayList<AutoridadSubcampo>();
        this.registroEtiquetas = new ArrayList<RegistroEtiqueta>();
        super.setOrdenRepetibilidad(new Integer(1));
    }

    /**
     * Constructor con parámetros
     * 
     * @param codigo
     * @param indicador1
     * @param indicador2
     * @param ordenRepetibilidad
     */
    public AutoridadEtiqueta(final String codigo, final String indicador1,
            final String indicador2, final Integer ordenRepetibilidad) {
        super(codigo, indicador1, indicador2);
        this.autoridadSubcampos = new ArrayList<AutoridadSubcampo>();
        this.registroEtiquetas = new ArrayList<RegistroEtiqueta>();
        super.setOrdenRepetibilidad(ordenRepetibilidad);
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = AutoridadEtiqueta.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = AutoridadEtiqueta.ID_SEQUENCE_NAME)
    @Column(name = AutoridadEtiqueta.COLUMN_NAME_ID, nullable = false)
    public Long getId() {
        return super.getId();
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        super.setId(id);
    }

    /**
     * @return the codigo
     */
    @Column(name = AutoridadEtiqueta.COLUMN_NAME_CODIGO, nullable = false, length = 20)
    public String getCodigo() {
        return super.getCodigo();
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(final String codigo) {
        super.setCodigo(codigo);
    }

    /**
     * @return the indicador1
     */
    @Column(name = AutoridadEtiqueta.COLUMN_NAME_INDICADOR_1, length = 1)
    public String getIndicador1() {
        return super.getIndicador1();
    }

    /**
     * @param indicador1
     *            the indicador1 to set
     */
    public void setIndicador1(final String indicador1) {
        super.setIndicador1(indicador1);
    }

    /**
     * @return the indicador2
     */
    @Column(name = AutoridadEtiqueta.COLUMN_NAME_INDICADOR_2, length = 2)
    public String getIndicador2() {
        return super.getIndicador2();
    }

    /**
     * @param indicador2
     *            the indicador2 to set
     */
    public void setIndicador2(final String indicador2) {
        super.setIndicador2(indicador2);
    }

    /**
     * @return the autoridadSubcampos
     */
    // @OneToMany(mappedBy = AutoridadSubcampo.PROPTY_NAME_AUTORIDAD_ETIQUETA,
    // cascade = { CascadeType.ALL })
    // @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @Transient
    public List<AutoridadSubcampo> getAutoridadSubcampos() {
        return this.autoridadSubcampos;
    }

    /**
     * @param autoridadSubcampos
     *            the autoridadSubcampos to set
     */
    @Transient
    public void setAutoridadSubcampos(
            final List<AutoridadSubcampo> autoridadSubcampos) {
        this.autoridadSubcampos = autoridadSubcampos;
    }

    /**
     * @return the registroEtiquetas FIXME cascade = { CascadeType.ALL },
     */
    // (cascade = {}, mappedBy =
    // AutoridadEtiqueta.PROPTY_NAME_AUTORIDAD_ETIQUETAS, targetEntity =
    // org.librae.catalogacion.model.RegistroEtiqueta.class)
    // @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    @ManyToMany
    @JoinTable(name = RegistroEtiqueta.JOIN_REGISTRO_ETIQUETA_X_AUTORIDAD_ETIQUETA, joinColumns = { @JoinColumn(name = RegistroEtiqueta.COLUMN_NAME_AUTORIDAD_ETIQUETA_FK) }, inverseJoinColumns = { @JoinColumn(name = RegistroEtiqueta.COLUMN_NAME_REGISTRO_ETIQUETA_FK) })
    public List<RegistroEtiqueta> getRegistroEtiquetas() {
        return this.registroEtiquetas;
    }

    /**
     * @param registros
     *            the registros to set
     */
    public void setRegistroEtiquetas(
            final List<RegistroEtiqueta> registroEtiquetas) {
        this.registroEtiquetas = registroEtiquetas;
    }

    /**
     * @return the autoridad
     */
    @ManyToOne(targetEntity = Autoridad.class)
    @JoinColumn(name = AutoridadEtiqueta.COLUMN_NAME_AUTORIDAD_FK, nullable = false)
    public Autoridad getAutoridad() {
        return this.autoridad;
    }

    /**
     * @param autoridad
     *            the autoridad to set
     */
    public void setAutoridad(final Autoridad autoridad) {
        this.autoridad = autoridad;
    }

    public AutoridadSubcampo getAutoridadSubcampo(final String codigoSubcampo) {
        Iterator<AutoridadSubcampo> itSubcampo = getAutoridadSubcampos()
                .iterator();
        AutoridadSubcampo resultado = null;
        while (itSubcampo.hasNext()) {
            AutoridadSubcampo sub = itSubcampo.next();
            if (sub.getCampoMarc().equalsIgnoreCase(codigoSubcampo)) {
                resultado = sub;
                break;
            }

        }
        return resultado;
    }

    @Column(name = AutoridadEtiqueta.COLUMN_NAME_XML)
    @Lob
    public String getSubcamposXML() {
        return super.getSubcamposXML();
    }

    public void setSubcamposXML(String subcamposXML) {
        super.setSubcamposXML(subcamposXML);
    }

    @Column(name = AutoridadEtiqueta.COLUMN_NAME_AUTORIDAD_VEASE, nullable = true)
    public Long getIdAutoridadVease() {
        return idAutoridadVease;
    }

    public void setIdAutoridadVease(Long idAutoridadVease) {
        this.idAutoridadVease = idAutoridadVease;
    }

    /**
     * @return String con todos los subcampos de la etiqueta "this"
     */
    @Transient
    public String getSubcamposToStringBySubcampoXML() {
        AutoridadSubcampo sub;
        StringBuilder valor = new StringBuilder("");
        final Iterator<AutoridadSubcampo> iterador2 = this.autoridadSubcampos
                .iterator();
        while (iterador2.hasNext()) {
            sub = iterador2.next();
            valor.append(sub.getValor()).append(" ");
        }
        return ltrim(valor.toString());
    }

    /**
     * @return String con todos los subcampos de la etiqueta "this"
     */
    @Transient
    public String getSubcamposToString() {
        AutoridadSubcampo sub;
        StringBuilder valor = new StringBuilder("");
        final Iterator<AutoridadSubcampo> iterador2 = this.autoridadSubcampos
                .iterator();
        while (iterador2.hasNext()) {
            sub = iterador2.next();
            valor.append(sub.getValor()).append(" ");
        }
        return ltrim(valor.toString());
    }

    /**
     * @param List
     *            <String> con todos los subcampos que se han de retornar en el
     *            String resultado
     * @return String con los subcampos de la etiqueta "this", especificados
     *         como parámetros
     */
    @Transient
    public String getSubcamposToStringBySubfields(List<String> subfields) {
        AutoridadSubcampo sub;
        StringBuilder valor;
        if (TODOS_LOS_ATRIBUTOS.equals(subfields.get(0))) {
            valor = new StringBuilder(getSubcamposToString());
        } else {
            valor = new StringBuilder("");
            final Iterator<AutoridadSubcampo> iterador2 = this.autoridadSubcampos
                    .iterator();
            while (iterador2.hasNext()) {
                sub = (AutoridadSubcampo) iterador2.next();
                if (subfields.contains(sub.getCampoMarc())) {
                    valor.append(sub.getValor()).append(" ");
                }
            }
        }
        return ltrim(valor.toString());
    }

    /**
     * Equivale al ltrim de Oracle, solo que al pasarle null retorna la cadena
     * vac�a.
     * 
     * @param cadena
     * @return cadena de entrada pero sin espacios en blanco de comienzo
     */
    @Transient
    public String ltrim(String cadena) {
        // Si cadena es nulo, devolver cadena vac�a
        if (cadena == null || cadena.equals(""))
            return "";
        while (cadena.charAt(0) == ' ')
            cadena = cadena.substring(1);

        return cadena;
    }

    /**
     * Retorna un hashMap con clave campoMarc y valor, el número de veces que se
     * repite este subcampo dentro de la etiqueta
     * 
     * @return
     */
    @Transient
    public HashMap<String, Integer> retornaRepeticionesSubcampos() {
        HashMap<String, Integer> resultado = new HashMap<String, Integer>();
        int cnt;
        for (AutoridadSubcampo sub : this.autoridadSubcampos) {
            if (null == resultado.get(sub.getCampoMarc())) {
                resultado.put(sub.getCampoMarc(), new Integer(1));
            } else {
                cnt = resultado.get(sub.getCampoMarc());
                resultado.put(sub.getCampoMarc(), cnt++);
            }
        }
        return resultado;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result = prime * result
                + ((autoridad == null) ? 0 : autoridad.hashCode());
        result = prime
                * result
                + ((super.getCodigo() == null) ? 0 : super.getCodigo()
                        .hashCode());
        result = prime * result
                + ((super.getId() == null) ? 0 : super.getId().hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutoridadEtiqueta)) {
            return false;
        }
        final AutoridadEtiqueta other = (AutoridadEtiqueta) obj;
        if (autoridad == null) {
            if (other.autoridad != null) {
                return false;
            }
        } else if (!autoridad.equals(other.autoridad)) {
            return false;
        }
        if (super.getCodigo() == null) {
            if (other.getCodigo() != null) {
                return false;
            }
        } else if (!super.getCodigo().equals(other.getCodigo())) {
            return false;
        }
        if (super.getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!super.getId().equals(other.getId())) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this)

        .append(AutoridadEtiqueta.PROPTY_NAME_ID, super.getId())

        .append(AutoridadEtiqueta.PROPTY_NAME_CODIGO,
                (super.getCodigo() == null) ? 0 : super.getCodigo())

        .append(AutoridadEtiqueta.PROPTY_NAME_AUTORIDAD,
                (this.autoridad == null) ? 0 : this.autoridad.toString())

        .toString();
    }

    @Override
    public AutoridadEtiqueta newInstance() {
        return new AutoridadEtiqueta();
    }

    @Column(name = AutoridadEtiqueta.COLUMN_NAME_ORDEN)
    public Integer getOrdenRepetibilidad() {
        return super.getOrdenRepetibilidad();
    }

    public void setOrdenRepetibilidad(Integer orden) {
        super.setOrdenRepetibilidad(orden);
    }

    /**
     * A partir de una autoridad etiqueta, se devuelve el código del tipo de
     * autoridad vinculada a la AutoridadEtiqueta, o null en c.c.
     * 
     * @return
     */
    @Transient
    public String getCodigoTipoAutoridad() {
        if (null != getAutoridad() && null != getAutoridad().getAutoridadTipo()) {
            return this.getAutoridad().getAutoridadTipo().getCodigo();
        } else
            return null;

    }

}