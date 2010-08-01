package org.librae.circulacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.librae.common.model.SpringRemotableLazyEntity;

/**
 * Schemes de los Open Enumerated Types que implementa la aplicación.<br>
 * Los elementos con D_NIVEL_IMP1 == 'O', se precargan con los schemes
 * especificados en IMP1 que se utilicen (pag. 10 de IMP1) en nuestra
 * implementación de NCIP.<br>
 * <br>
 * IMP1 = Circulation Interchange Part 2: Protocol Implementation Profile 1<br>
 * <br>
 * Ejemplo para el elemnto de dato "Agency User Privilege Type" (IMP1 pag. 11):<br>
 * <br>
 * X_NCIP_SCHEMES = el_número_que_sea<br>
 * T_SCHEME =
 * http://www.niso.org/ncip/v1_0/imp1/schemes/agencyuserprivilegetype/
 * academic.scm<br>
 * T_NOMBRE_COMUN = NCIP Agency User Privilege Type Academic Scheme<br>
 * D_NIVEL_IMP1 = O<br>
 * <br>
 * X_NCIP_SCHEMES = el_número_que_sea<br>
 * T_SCHEME =
 * http://www.niso.org/ncip/v1_0/imp1/schemes/agencyuserprivilegetype/public.scm<br>
 * T_NOMBRE_COMUN = NCIP Agency User Privilege Type Public Scheme<br>
 * D_NIVEL_IMP1 = O<br>
 * <br>
 * NCIP define algunos campos de tipo Open Enumerated Type cuyo scheme y valores
 * no se define en IMP1, por ejemplo From System Id. Para cada uno de estos,
 * definir un Scheme genérico que será el que se emplee en la construcción de
 * los mensajes. Por ejemplo, para From System Id, definir el siguiente scheme:<br>
 * <br>
 * T_SCHEME = http://www.librea.es/FromSystemId.scm<br>
 * T_NOMBRE_COMUN = NCIP From System Id<br>
 * D_NIVEL_IMP1 = C<br>
 * 
 * @author asantamaria
 */
@Entity(name = EsquemaOET.ENTITY_NAME)
@Table(name = EsquemaOET.TABLE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = { EsquemaOET.COLUMN_NAME_ESQUEMA }) })
public class EsquemaOET extends SpringRemotableLazyEntity<EsquemaOET> {

    /**
     * BaseObject is Serializable, so EsquemaOET needs a Serial Version UID
     */
    private static final long   serialVersionUID         = 5037277012878161330L;

    public static final String  ENTITY_NAME              = "org.librae.circulacion.model.EsquemaOET";
    public static final String  TABLE_NAME               = "NCIP_SCHEMES_OET";
    public static final String  ID_GENERATOR_NAME        = "generator_ncip_schemes_oet";
    private static final String ID_SEQUENCE_NAME         = "SEQ_NCIP_SCHEMES_OET";
    public static final String  COLUMN_NAME_ID           = "X_NCIP_SCHEMES";
    public static final String  COLUMN_NAME_ESQUEMA      = "T_SCHEME";
    public static final String  COLUMN_NAME_NOMBRE_COMUN = "T_NOMBRE_COMUN";
    public static final String  COLUMN_NAME_NIVEL_IMP1   = "D_NIVEL_IMP1";

    /**
     * Clave primaria artificial
     */
    private Long                id;

    /**
     * Valor de dato "scheme" según la especificación IMP1.<br>
     * <br>
     * IMP1 = Circulation Interchange Part 2: Protocol Implementation Profile 1<br>
     * <br>
     * Por ser una URL, debe ser única entre todas las filas de esta tabla.
     */
    private String              esquema;

    /**
     * Valor de "Common Name" especificado para el Open Enumerated Type en IMP1.<br>
     * IMP1 = Circulation Interchange Part 2: Protocol Implementation Profile 1
     */
    private String              nombreComun;

    /**
     *Indica si el "SCHEME" es específico de nuestra aplicación (custom
     * scheme), obligatorio de implementar para que la aplicación sea conforme
     * con IMP1 (pag 10 de IMP1), o no obligatorio.<br>
     * Valores:<br>
     * C = Custom scheme<br>
     * O = Obligatorio de implementar para que la apl. sea conforme con IMP1<br>
     * N = No obligatorio según IMP1
     */
    private String              nivelIMP1;

    protected EsquemaOET() {
        super();
    }

    public EsquemaOET(String esquema) {
        super();
        this.esquema = esquema;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = EsquemaOET.COLUMN_NAME_ID)
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
     * @return the esquema
     */
    @Column(name = EsquemaOET.COLUMN_NAME_ESQUEMA)
    public String getEsquema() {
        return esquema;
    }

    /**
     * @param esquema
     *            the esquema to set
     */
    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    /**
     * @return the nombreComun
     */
    @Column(name = EsquemaOET.COLUMN_NAME_NOMBRE_COMUN)
    public String getNombreComun() {
        return nombreComun;
    }

    /**
     * @param nombreComun
     *            the nombreComun to set
     */
    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    /**
     * @return the nivelIMP1
     */
    @Column(name = EsquemaOET.COLUMN_NAME_NIVEL_IMP1)
    public String getNivelIMP1() {
        return nivelIMP1;
    }

    /**
     * @param nivelIMP1
     *            the nivelIMP1 to set
     */
    public void setNivelIMP1(String nivelIMP1) {
        this.nivelIMP1 = nivelIMP1;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof EsquemaOET)) {
            return false;
        }

        final EsquemaOET other = (EsquemaOET) obj;

        if (getEsquema() == null && other.getEsquema() != null) {
            return false;
        }
        if (getEsquema() != null
                && !getEsquema().equals(other.getEsquema())) {
            return false;
        }

        return true;

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime
                * result
                + ((getEsquema() == null) ? 0 : getEsquema()
                        .hashCode());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)

        .append(COLUMN_NAME_ID, getId())

        .append(COLUMN_NAME_ESQUEMA,
                (getEsquema() == null) ? 0 : getEsquema().toString())

        .toString();
    }

    @Override
    public EsquemaOET newInstance() {
        // TODO Auto-generated method stub
        return new EsquemaOET();
    }

}