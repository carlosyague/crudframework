package model;

import java.io.Serializable;

/**
 * Objeto que guarda el codigo del permiso.
 * 
 * @author jcisneros
 */
public class CodPer implements Serializable {

	protected String cod;

	public CodPer(String cod) {
		this.cod = cod;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CodPer)) {
			return false;
		}

		final CodPer other = (CodPer) obj;

		if (!this.getCod().equals(other.getCod())) {
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
		result += ((cod == null) ? 0 : this.getCod().hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("cod: ");
		sb.append(getCod());
		return sb.toString();
	}
}
