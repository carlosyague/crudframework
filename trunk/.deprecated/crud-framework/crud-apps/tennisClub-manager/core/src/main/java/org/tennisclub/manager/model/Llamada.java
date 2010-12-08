package org.tennisclub.manager.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.tennisclub.manager.util.LlamadasDateUtil;
import org.tennisclub.manager.util.TokensUtil;

import es.uma.crudframework.exception.ExceptionUtil;
import es.uma.crudframework.model.BaseObject;


/**
 * Fecha   Tlf. destino / Agenda   Tipo de llamada     Destino           Hora     Duración       Importe<br>
 * 29 Jun. 952219240               Metropolitanas      MALAGA            13:53:59 0h 19m 16s     0,3504 <br>
 * @author cyague
 *
 */
@Entity(name = Llamada.ENTITY_NAME)
public class Llamada extends BaseObject {

	/**
	 * BaseObject implements Serializable
	 */
	private static final long serialVersionUID = -1290385814047596278L;

	/**
	 * POJO-JPA Constants
	 */
	public static final String ENTITY_NAME = "org.tennisclub.manager.model.Llamada";
	public static final String COLUMN_NAME_CONTACTO = "contacto";
	
	public static final Character TIPO_LLAMADA_INTERPROVINCIAL = 'N';
	public static final Character TIPO_LLAMADA_METROPOLITANA   = 'M';
	public static final Character TIPO_LLAMADA_MOVILES         = 'L';
	public static final Character TIPO_LLAMADA_INTERNACIONALES = 'I';
	public static final Character TIPO_LLAMADA_DESCONOCIDO     = 'K';
	
	public static final String TIPO_LLAMADA_INTERPROVINCIAL_STR = "Interprovinciales";
	public static final String TIPO_LLAMADA_METROPOLITANA_STR   = "Metropolitanas";
	public static final String TIPO_LLAMADA_MOVILES_STR         = "A móviles";
	public static final String TIPO_LLAMADA_INTERNACIONALES_STR = "Internacionales";
	public static final String TIPO_LLAMADA_DESCONOCIDO_STR     = "Desconocido";
	
	private static final String TOKEN_HOUR = "h";
	private static final String TOKEN_MIN  = "m";
	private static final String TOKEN_SEC  = "s";
    
	/**
	 * fields
	 */
	private String tfnoDestino;
	private String destino;
	private Date fechaHora;
	private Character tipoLlamada;
	private Integer duracionSg;
	private Float importe;
	private Contacto contacto;
	
	

	/**
	 * INICIO getter & setters<br>
	 * =======================
	 */
	
	/**
	 * @return the fechaHora
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	/**
	 * 
	 * @param fecha, for instance: "29 Jun."
	 * @param hora, for instance: "13:53:59"
	 */
	@Transient
	public void setFechaHora(String fecha, String hora) {
		Date date = LlamadasDateUtil.getDate(fecha, hora);
		
		setFechaHora(date);
	}

	/**
	 * @return the tfnoDestino
	 */
	public String getTfnoDestino() {
		return tfnoDestino;
	}

	/**
	 * @param tfnoDestino the tfnoDestino to set
	 */
	public void setTfnoDestino(String tfnoDestino) {
		this.tfnoDestino = tfnoDestino;
	}

	/**
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * @param destino the destino to set
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}

	/**
	 * @return the tipoLlamada
	 */
	public Character getTipoLlamada() {
		return tipoLlamada;
	}
	
	/**
	 * 
	 * @return TIPO_LLAMADA_xxx_STR
	 */
	@Transient
	public String getDetalleTipoLlamada() {
		String result = "";
		
		if (tipoLlamada.equals(TIPO_LLAMADA_INTERPROVINCIAL)) {
			result = TIPO_LLAMADA_INTERPROVINCIAL_STR;
			
		} else if (tipoLlamada.equals(TIPO_LLAMADA_METROPOLITANA)) {
			result = TIPO_LLAMADA_METROPOLITANA_STR;
			
		} else if (tipoLlamada.equals(TIPO_LLAMADA_MOVILES)) {
			result = TIPO_LLAMADA_MOVILES_STR;
			
		} else if (tipoLlamada.equals(TIPO_LLAMADA_INTERNACIONALES)) {
			result = TIPO_LLAMADA_INTERNACIONALES_STR;
			
		} else {
			result = TIPO_LLAMADA_DESCONOCIDO_STR;
		}
				
		return result;
	}

	/**
	 * @param tipoLlamada the tipoLlamada to set
	 */
	public void setTipoLlamada(Character tipoLlamada) {
		this.tipoLlamada = tipoLlamada;
	}
	
	/**
	 * @param tipoLlamadaStr de tipo TIPO_LLAMADA_xxx_STR
	 */
	@Transient
	public void setTipoLlamada(String tipoLlamadaStr) {		
		Character result = null;
		
		if (tipoLlamadaStr.equals(TIPO_LLAMADA_INTERPROVINCIAL_STR)) {
			result = TIPO_LLAMADA_INTERPROVINCIAL;
			
		} else if (tipoLlamadaStr.equals(TIPO_LLAMADA_METROPOLITANA_STR)) {
			result = TIPO_LLAMADA_METROPOLITANA;
			
		} else if (tipoLlamadaStr.equals(TIPO_LLAMADA_MOVILES_STR)) {
			result = TIPO_LLAMADA_MOVILES;
			
		} else if (tipoLlamadaStr.equals(TIPO_LLAMADA_INTERNACIONALES_STR)) {
			result = TIPO_LLAMADA_INTERNACIONALES;
			
		} else {
			result = TIPO_LLAMADA_DESCONOCIDO;
		}
		
		setTipoLlamada(result);
	}

	/**
	 * @return the duracionSg
	 */
	public Integer getDuracionSg() {
		return duracionSg;
	}
		
	/**
	 * @return for duracion == 1156, returns "0h 19m 16s"
	 */
	@Transient
	public String getDetalleDuracion() {
		StringBuilder sb = new StringBuilder();
		
		Integer hours = 0;
		Integer mins = 0;
		Integer secs = 0;
		
		if (duracionSg > 3600) {
			hours = duracionSg / 3600;
			duracionSg = duracionSg % 3600;
		}
		
		if (duracionSg > 60) {
			mins = duracionSg / 60;
			duracionSg = duracionSg % 60;
		}
		
		secs = duracionSg;
		
		sb.append(hours).append(TOKEN_HOUR).append(" ");
		sb.append(mins).append(TOKEN_MIN).append(" ");
		sb.append(secs).append(TOKEN_SEC);
				
		return sb.toString();
	}

	/**
	 * @param duracionSg the duracionSg to set
	 */
	public void setDuracionSg(Integer duracionSg) {
		this.duracionSg = duracionSg;
	}
	
	/*
	 * @param duracionSg, for instance: "0h 19m 16s"
	 */
	@Transient
	public void setDuracionSg(String duracionHhMmSs) {
		final ArrayList<String> spacedTokens = TokensUtil.extractSpacedTokens(duracionHhMmSs);
		
		if (spacedTokens != null && spacedTokens.size() == 3) {
			String hoursStr = TokensUtil.extractTokenTime(spacedTokens.get(0),TOKEN_HOUR);
			String minsStr  = TokensUtil.extractTokenTime(spacedTokens.get(1),TOKEN_MIN);
			String secsStr  = TokensUtil.extractTokenTime(spacedTokens.get(2),TOKEN_SEC);
			
			if (hoursStr != null && minsStr != null && secsStr != null) {
				Integer duracion = 0;
				duracion += Integer.parseInt(hoursStr) * 3600;
				duracion += Integer.parseInt(minsStr) * 60;
				duracion += Integer.parseInt(secsStr);
				
				setDuracionSg(duracion);
			}
		} else {
			throw ExceptionUtil.crearCrudException("ERROR_SET_DURACION", new Exception("No se ha podido establecer la duración, revise si el formato es: '0h 19m 16s'"));
		}

	}
	
	
				
	/**
	 * @return the importe
	 */
	public Float getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(Float importe) {
		this.importe = importe;
	}

	/**
	 * @return the contacto
	 */
	@ManyToOne(targetEntity = Contacto.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = Llamada.COLUMN_NAME_CONTACTO)
	public Contacto getContacto() {
		return contacto;
	}

	/**
	 * @param contacto the contacto to set
	 */
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	
	/**
	 * FIN getter & setters<br>
	 * ====================
	 */
	
	
	/**
	 * INICIO override methods
	 */	
	
	@Override
	public boolean equals(Object obj) {
		// if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof Llamada)) {
            return false;
        }

        final Llamada other = (Llamada) obj;

        if (!this.getTfnoDestino().equals(other.getTfnoDestino())) {
            return false;
        }
        
        if (!this.getFechaHora().equals(other.getFechaHora())) {
            return false;
        }

        return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : this.getId().hashCode());
        result = prime
                * result
                + ((this.getTfnoDestino() == null) ? 0 : this.getTfnoDestino()
                        .hashCode());
        result = prime
        * result
        + ((this.getFechaHora() == null) ? 0 : this.getFechaHora()
                .hashCode());

        return result;
	}

	@Override
	public String toString() {
		final ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", this.getId());
		tsb.append("tfnoDestino", this.getTfnoDestino());
		tsb.append("fechaHora", this.getFechaHora());		
		return tsb.toString();
	}
	
	/**
	 * FIN override methods
	 */	

}
