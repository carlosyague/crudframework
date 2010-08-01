package org.librae.common.dao.hibernate;

/**
 * Enumeración de tipos de condicion.
 * 
 * @author ingenia
 */
public enum TipoCondicion {
	/**
	 * los posibles tipos.
	 */
	BETWEEN, MAYOR, MAYORIGUAL, MENOR, MENORIGUAL, IN, ISNULL, ISNOTNULL, EQUAL, NOTEQUAL, JOIN, STARTSWITH, ENDSWITH, CONTAINS
}
