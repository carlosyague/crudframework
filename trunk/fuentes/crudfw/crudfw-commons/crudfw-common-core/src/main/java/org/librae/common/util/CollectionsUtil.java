package org.librae.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;

/**
 * Clase que contiene una serie de métodos estáticos que facilitan el trabajo
 * con las listas.
 * 
 * @author aropero
 */
public class CollectionsUtil {

	protected static boolean             nullsAreHigh;
	
    /**
     * Convierte una lista de String a una lista de Long
     * 
     * @param listaId
     *            , listado de string
     * @return List, listado de Long
     * @throws LibraeException
     */
    public static List<Long> listStringToLong(final List<String> listaId)
            throws LibraeException {
        final List<Long> listaConvertida = new ArrayList<Long>();
        Long id = null;
        for (int i = 0; i < listaId.size(); i++) {
            try {
                if (!"0".equals(listaId.get(i))) {
                    id = new Long(listaId.get(i));
                }
            } catch (final ClassCastException e) {
                throw ExceptionUtil.crearLibraeException(
                        MensajesError.PROPERTI_ADMINCONFIG, "ERROR_GENERICO");
            }
            listaConvertida.add(id);
        }
        return listaConvertida;
    }
    
    /**
     * Convierte una lista a una lista de 1000 elementos.
     * 
     * @param listado
     *            , listado de long
     * @return List, listado de listas.
     * @throws LibraeException
     */
    public static List<List<Long>> listToInExpresion(final List<Long> listado) {
        final List<List<Long>> listaCompleta = new ArrayList<List<Long>>();
        for (int n = 0; n < listado.size(); n = n + 1000) {
            int fin = n + 1000;
            if (n + 1000 > listado.size()) {
                fin = listado.size();
            }
            listaCompleta.add(listado.subList(n, fin));
        }
        return listaCompleta;
    }
    

    /**
     * Ordena la lista.
     */
    public static List sort(final List list, String sortColumn, Boolean ascending) {
        Comparator comparator = new BeanComparator(sortColumn,
                new NullComparator(nullsAreHigh));
        if (!ascending) {
            comparator = new ReverseComparator(comparator);
        }
        if (list != null) {
            Collections.sort(list, comparator);
        }
        return list;
    }

}
