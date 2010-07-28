package org.librae.catalogacion.model;

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
import javax.persistence.Transient;
import java.util.Date;

import org.librae.adminconfig.model.Usuario;
import org.librae.common.model.BaseObject;


@Entity(name = BusquedaExperta.ENTITY_NAME)
@Table(name = BusquedaExperta.TABLE_NAME)

public class BusquedaExperta extends BaseObject{


	protected BusquedaExperta(){
		super();
	}

	public BusquedaExperta(String consulta, String numeroRegistros) {
		super();
		this.consulta = consulta;
		this.numeroRegistros = numeroRegistros;
	}


	public BusquedaExperta(String consulta, String numeroRegistros,String tipo) {
		super();
		this.consulta = consulta;
		this.numeroRegistros = numeroRegistros;
		this.tipo = tipo;
	}


	public BusquedaExperta(String consulta, String numeroRegistros,String tipo, Date fecha) {
		super();
		this.consulta = consulta;
		this.numeroRegistros = numeroRegistros;
		this.tipo = tipo;
		this.fecha=fecha;
	}


	public BusquedaExperta(String consulta, String numeroRegistros,String tipo, Date fecha,Usuario usuario) {
		super();
		this.consulta = consulta;
		this.numeroRegistros = numeroRegistros;
		this.tipo = tipo;
		this.fecha=fecha;
		this.usuario=usuario;
	}
	public BusquedaExperta(String tipo, Usuario usuario) {
		super();
		this.tipo = tipo;
		this.usuario=usuario;
	}


	private static final long serialVersionUID = -838441462000792734L;
    public static final String  ENTITY_NAME        = "org.librae.catalogacion.model.BusquedaExperta";
    public static final String  TABLE_NAME          = "CAT_BUSQUEDA_EXPERTA";
    public static final String  PROPTY_NAME_ID     = "id";
    public static final String  PROPTY_CONSULTA     = "consulta";
    public static final String  PROPTY_NUMERO_REGISTROS     = "numeroRegistros";
    public static final String PROPTY_TIPO= "tipo";
    public static final String PROPTY_FECHA= "fecha";
    public static final String PROPTY_USUARIO= "usuario";


    private static final String ID_GENERATOR_NAME   = "generator_cat_busqueda_experta";
    private static final String ID_SEQUENCE_NAME    = "SEQ_CAT_BUSQUEDA_EXPERTA";

    public static final String  COLUMN_NAME_ID      = "X_BUSQUEDA_EXPERTA";
    public static final String  COLUMN_NAME_CONSULTA  = "T_CONSULTA";
    public static final String  COLUMN_NAME_NUMERO_REGISTROS = "T_NUMERO_REGISTROS";
    public static final String  COLUMN_NAME_TIPO = "T_TIPO";
    public static final String  COLUMN_NAME_FECHA = "F_FECHA";
    public static final String  COLUMN_NAME_USUARIO = "F_USUARIO";

    private Long                id;


	private String              consulta;
    private String numeroRegistros;
    private String tipo;

    private Date fecha;
    private Usuario usuario;




	private boolean checked;


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = BusquedaExperta.ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName =BusquedaExperta.ID_SEQUENCE_NAME)
    @Column(name = BusquedaExperta.COLUMN_NAME_ID, nullable = false)

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Column(name = BusquedaExperta.COLUMN_NAME_NUMERO_REGISTROS, nullable = false, unique = false, length = 10)
    public String getNumeroRegistros() {
		return numeroRegistros;
	}

	public void setNumeroRegistros(String numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}

	public String obtenerNumeroRegistros(){


    	return "";
    }


	@Column(name = BusquedaExperta.COLUMN_NAME_CONSULTA, nullable = false, unique = false, length = 1000)
	public String getConsulta() {
		return consulta;
	}


	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}


	@Column(name = BusquedaExperta.COLUMN_NAME_TIPO, nullable = false, unique = false, length = 100)
	 public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}


		@Column(name = BusquedaExperta.COLUMN_NAME_FECHA, nullable = false, unique = false)
		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}


		   @ ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = Usuario.class)
		    @JoinColumn(name = BusquedaExperta.COLUMN_NAME_USUARIO, nullable = false)
			public Usuario getUsuario() {
				return usuario;
			}

			public void setUsuario(Usuario usuario) {
				this.usuario = usuario;
			}



	 @Transient
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}



	public boolean equals(Object obj) {
		BusquedaExperta other =null;

		if (this == obj) {
            return true;
        }
        if (!(obj instanceof BusquedaExperta)) {
            return false;
        }
        other = (BusquedaExperta) obj;


if (!(id.equals(other.getId()))){
	return false;
}

/*
        if(!consulta.equals(other.getConsulta())){
             return false;
        }

       if(!numeroRegistros.equals(other.getNumeroRegistros())){
    	   return false;
       }*/

		return true;
	}

    public int hashCode() {
        final int prime = 48563;
        int result = prime;
        result = prime * result + ((consulta == null) ? 0 : consulta.hashCode());
        result = prime * result + ((numeroRegistros == null) ? 0 : numeroRegistros.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }



	public String toString() {

		return "consulta : " + consulta + "numero regsitros : " + numeroRegistros;
	}

}
