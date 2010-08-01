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
 * Bean para almacenar un RegistroEtiqueta
 * 
 * @author jcdiaz
 * @version 1.0
 * @author asalas
 * @param <T>
 */
@Entity(name = RegistroEtiqueta.ENTITY_NAME)
@Table(name = RegistroEtiqueta.TABLE_NAME)
public class RegistroEtiqueta extends EtiquetaMarc {

    /**
     * BaseObject es Serializable, por lo tanto RegistroEtiqueta necesita un
     * Serial Version UID
     */
    private static final long       serialVersionUID                            = 5946813453971992044L;

    public static final String      ENTITY_NAME                                 = "org.librae.catalogacion.model.RegistroEtiqueta";

    /**
     * Constantes para referencia de los atributos de RegistroEtiqueta
     */
    public static final String      PROPTY_NAME_REGISTRO                        = "registro";
    public static final String      PROPTY_NAME_AUTORIDAD_ETIQUETAS             = "autoridadEtiquetas";
    public static final String      PROPTY_NAME_REGISTRO_ENLAZADO               = "registroEnlazado";
    public static final String      PROPTY_NAME_ORDEN                           = "orden";
    /**
     * Constantes para referencia de los nombres de la Tabla, Secuencia para el
     * id, y nombres de las columnas en la Base de Datos
     */
    public static final String      TABLE_NAME                                  = "CAT_REGISTRO_ETIQUETA";
    private static final String     ID_GENERATOR_NAME                           = "generator_cat_registro_etiqueta";
    private static final String     ID_SEQUENCE_NAME                            = "SEQ_CAT_REGISTRO_ETIQUETA";

    public static final String      COLUMN_NAME_ID                              = "X_REGISTRO_ETIQUETA";

    public static final String      COLUMN_NAME_REGISTRO_ETIQUETA_FK            = "REG_ETIQ_X_AUTOR_ETIQ";

    public static final String      COLUMN_NAME_REGISTRO_FK                     = "REG_X_REGISTRO";

    public static final String      JOIN_REGISTRO_ETIQUETA_X_AUTORIDAD_ETIQUETA = "CAT_REG_ETIQ_X_CAT_AUTOR_ETIQ";

    public static final String      COLUMN_NAME_AUTORIDAD_ETIQUETA_FK           = "AUT_X_AUTORIDAD_ETIQUETA";

    public static final String      COLUMN_NAME_REGISTRO_ENLAZADO               = "N_REGISTRO_ENLAZADO";

    public static final String      COLUMN_NAME_ORDEN                           = "N_ORDEN";

    /**
     * Constante que se usa en el parseador de la clase
     * GeneracionCamposDublinCore.xml
     */
    public static final String      TODOS_LOS_ATRIBUTOS                         = "all";

    /**
     *
     */
    private List<RegistroSubcampo>  registroSubcampos;

    /**
     *
     */
    private Registro                registro;

    /**
     *
     */
    private List<AutoridadEtiqueta> autoridadEtiquetas;

    /**
     *
     */
    private Long                    registroEnlazado;

    /**
     * Constructor sin parámetros
     */
    protected RegistroEtiqueta() {
        super();
        this.registroSubcampos = new ArrayList<RegistroSubcampo>();
        this.autoridadEtiquetas = new ArrayList<AutoridadEtiqueta>();
        super.setOrdenRepetibilidad(new Integer(1));
    }

    /**
     * COnstructor con parámetros
     * 
     * @param codigo
     * @param indicador1
     * @param indicador2
     */
    public RegistroEtiqueta(final String codigo, final String indicador1,
            final String indicador2) {
        super(codigo, indicador1, indicador2);
        this.registroSubcampos = new ArrayList<RegistroSubcampo>();
        this.autoridadEtiquetas = new ArrayList<AutoridadEtiqueta>();
        super.setOrdenRepetibilidad(new Integer(1));
    }

    /**
     * COnstructor con parámetros
     * 
     * @param codigo
     * @param indicador1
     * @param indicador2
     * @param ordenRepetibilidad
     */
    public RegistroEtiqueta(final String codigo, final String indicador1,
            final String indicador2, final Integer ordenRepetibilidad) {
        super(codigo, indicador1, indicador2);
        this.registroSubcampos = new ArrayList<RegistroSubcampo>();
        this.autoridadEtiquetas = new ArrayList<AutoridadEtiqueta>();
        super.setOrdenRepetibilidad(ordenRepetibilidad);
    }

    public RegistroEtiqueta(final String codigo, final String indicador1,
            final String indicador2, final String subcamposXML,
            final Registro registro,
            final ArrayList<RegistroSubcampo> registroSubcampos,
            final ArrayList<AutoridadEtiqueta> autoridadEtiquetas,
            final Integer ordenRepetibilidad) {
        super(codigo, indicador1, indicador2, subcamposXML);
        this.registroSubcampos = registroSubcampos;
        this.autoridadEtiquetas = autoridadEtiquetas;
        super.setOrdenRepetibilidad(ordenRepetibilidad);
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = RegistroEtiqueta.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = RegistroEtiqueta.ID_SEQUENCE_NAME)
    @Column(name = RegistroEtiqueta.COLUMN_NAME_ID, nullable = false)
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
    @Column(name = RegistroEtiqueta.COLUMN_NAME_CODIGO, nullable = false, length = 20)
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
    @Column(name = RegistroEtiqueta.COLUMN_NAME_INDICADOR_1, length = 1)
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
    @Column(name = RegistroEtiqueta.COLUMN_NAME_INDICADOR_2, length = 1)
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
     * @return the registroSubcampos
     */
    @Transient
    public List<RegistroSubcampo> getRegistroSubcampos() {
        return this.registroSubcampos;
    }

    /**
     * @param registroSubcampos
     *            the registroSubcampos to set
     */
    public void setRegistroSubcampos(
            final List<RegistroSubcampo> registroSubcampos) {
        this.registroSubcampos = registroSubcampos;
    }

    /**
     * @return the registro FIXME
     */
    @ManyToOne(targetEntity = Registro.class)
    @JoinColumn(name = RegistroEtiqueta.COLUMN_NAME_REGISTRO_FK, nullable = false)
    public Registro getRegistro() {
        return this.registro;
    }

    /**
     * @return the autoridadEtiquetas FIXME
     */
    @ManyToMany
    @JoinTable(name = RegistroEtiqueta.JOIN_REGISTRO_ETIQUETA_X_AUTORIDAD_ETIQUETA, joinColumns = { @JoinColumn(name = RegistroEtiqueta.COLUMN_NAME_REGISTRO_ETIQUETA_FK) }, inverseJoinColumns = { @JoinColumn(name = RegistroEtiqueta.COLUMN_NAME_AUTORIDAD_ETIQUETA_FK) })
    public List<AutoridadEtiqueta> getAutoridadEtiquetas() {
        return autoridadEtiquetas;
    }

    /**
     * @param autoridadEtiquetas
     *            the autoridadEtiquetas to set
     */
    public void setAutoridadEtiquetas(
            final List<AutoridadEtiqueta> autoridadEtiquetas) {
        this.autoridadEtiquetas = autoridadEtiquetas;
    }

    /**
     * @param registro
     *            the registro to set
     */
    public void setRegistro(final Registro registro) {
        this.registro = registro;
    }

    public RegistroSubcampo getRegistroSubcampo(final String codigoSubcampo) {
        Iterator<RegistroSubcampo> itSubcampo = getRegistroSubcampos()
                .iterator();
        RegistroSubcampo resultado = null;
        while (itSubcampo.hasNext()) {
            RegistroSubcampo sub = itSubcampo.next();
            if (sub.getCampoMarc().equalsIgnoreCase(codigoSubcampo)) {
                resultado = sub;
                break;
            }

        }
        return resultado;
    }

    public void addSubcampo(RegistroSubcampo campo) {

        this.registroSubcampos.add(campo);

    }

    @Column(name = RegistroEtiqueta.COLUMN_NAME_XML)
    @Lob
    public String getSubcamposXML() {
        return super.getSubcamposXML();
    }

    public void setSubcamposXML(String subcamposXML) {
        super.setSubcamposXML(subcamposXML);
    }

    /**
     * @return String con todos los subcampos de la etiqueta "this"
     */
    @Transient
    public String getSubcamposToString() {
        RegistroSubcampo sub;
        StringBuilder valor = new StringBuilder("");
        final Iterator<RegistroSubcampo> iterador2 = this.registroSubcampos
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
        RegistroSubcampo sub;
        StringBuilder valor;
        if (TODOS_LOS_ATRIBUTOS.equals(subfields.get(0))) {
            valor = new StringBuilder(getSubcamposToString());
        } else {
            valor = new StringBuilder("");
            final Iterator<RegistroSubcampo> iterador2 = this.registroSubcampos
                    .iterator();
            while (iterador2.hasNext()) {
                sub = (RegistroSubcampo) iterador2.next();
                if (subfields.contains(sub.getCampoMarc())) {
                    valor.append(sub.getValor()).append(" ");
                }
            }
        }
        return ltrim(valor.toString());
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
        for (RegistroSubcampo sub : this.registroSubcampos) {
            if (null == resultado.get(sub.getCampoMarc())) {
                resultado.put(sub.getCampoMarc(), new Integer(1));
            } else {
                cnt = resultado.get(sub.getCampoMarc());
                resultado.put(sub.getCampoMarc(), cnt++);
            }
        }
        return resultado;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime
                * result
                + ((autoridadEtiquetas == null) ? 0 : autoridadEtiquetas
                        .hashCode());
        result = prime * result
                + ((registro == null) ? 0 : registro.hashCode());
        result = prime
                * result
                + ((registroSubcampos == null) ? 0 : registroSubcampos
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
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RegistroEtiqueta other = (RegistroEtiqueta) obj;
        if (autoridadEtiquetas == null) {
            if (other.autoridadEtiquetas != null) {
                return false;
            }
        } else if (!autoridadEtiquetas.equals(other.autoridadEtiquetas)) {
            return false;
        }
        if (registro == null) {
            if (other.registro != null) {
                return false;
            }
        } else if (!registro.equals(other.registro)) {
            return false;
        }
        if (registroSubcampos == null) {
            if (other.registroSubcampos != null) {
                return false;
            }
        } else if (!registroSubcampos.equals(other.registroSubcampos)) {
            return false;
        }
        return true;
    }

    /**
     * Sobreescribimos el toString para poder comparar RegistroEtiqueta con
     * EtiquetaForm
     */
    @Override
    public String toString() {

        return new ToStringBuilder(this)

        .append(RegistroEtiqueta.PROPTY_NAME_ID, super.getId())

        .append(RegistroEtiqueta.PROPTY_NAME_CODIGO,
                (super.getCodigo() == null) ? 0 : super.getCodigo())

        .append(RegistroEtiqueta.PROPTY_NAME_REGISTRO,
                (this.registro == null) ? 0 : this.registro.toString())

        .toString();
    }

    @Override
    public RegistroEtiqueta newInstance() {

        return new RegistroEtiqueta();
    }

    @Column(name = RegistroEtiqueta.COLUMN_NAME_REGISTRO_ENLAZADO)
    public Long getRegistroEnlazado() {
        return registroEnlazado;
    }

    public void setRegistroEnlazado(Long registroEnlazado) {
        this.registroEnlazado = registroEnlazado;
    }

    @Column(name = RegistroEtiqueta.COLUMN_NAME_ORDEN)
    public Integer getOrdenRepetibilidad() {
        return super.getOrdenRepetibilidad();
    }

    public void setOrdenRepetibilidad(Integer orden) {
        super.setOrdenRepetibilidad(orden);
    }

}