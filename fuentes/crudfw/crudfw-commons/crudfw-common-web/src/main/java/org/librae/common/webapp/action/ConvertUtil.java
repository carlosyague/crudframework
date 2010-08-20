package org.librae.common.webapp.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.exception.ExceptionUtil;
import org.librae.common.exception.LibraeException;
import org.librae.common.exception.MensajesError;
import org.librae.common.service.impl.ComboLocaleManager;
import org.librae.common.util.ReflectionUtil;
import org.librae.common.util.StringUtil;

/**
 * Conversiones en la capa web.
 * 
 * @author jcisneros
 */
public class ConvertUtil {

    protected static final Log  log                   = LogFactory
                                                              .getLog(ConvertUtil.class);

    private static final String SEPARADOR_COMBO_LABEL = " -- ";

    /**
     * Transforma una lista en una lista de selectItem de jsf.
     * 
     * @param lista
     * @param value
     *            valor que saldra en la etiqueta
     * @param label
     *            valor que llegara al servidor.
     * @return listado de selectItems
     * @throws LibraeException
     */
    public static List<SelectItem> convertListItem(final List lista,
            final String value, final String label) throws LibraeException {

        final List<SelectItem> listaItems = new ArrayList<SelectItem>();
        if (lista != null) {
            final Iterator it = lista.iterator();
            try {
                while (it.hasNext()) {
                    final Object objeto = it.next();
                    final SelectItem i = new SelectItem();

                    final String idValue = ReflectionUtil.getBeanValue(objeto,
                            value);
                    i.setValue(idValue);

                    final String labelValue = getLocaleBeanValue(objeto, label);
                    i.setLabel(labelValue);

                    listaItems.add(i);
                }
            } catch (final Exception e) {
                log.error("Fallo al convertir la cadena en intems...");
                throw ExceptionUtil.crearLibraeException(MensajesError
                        .get("ERROR_CONVERT_LIST_ITEM"), e);
            }
        }
        return listaItems;
    }

    /**
     * Transforma una lista en una lista de selectItem de jsf.
     * 
     * @param lista
     * @param value
     *            valor que saldra en la etiqueta
     * @param label
     *            valor que llegara al servidor.
     * @return listado de selectItems
     * @throws LibraeException
     */
    public static List<SelectItem> convertListItem(final List lista,
            final String value, final String label1, final String label2)
            throws LibraeException {
        final List<SelectItem> listaItems = new ArrayList<SelectItem>();
        if (lista != null) {
            final Iterator it = lista.iterator();
            try {
                while (it.hasNext()) {
                    final Object objeto = it.next();
                    final SelectItem i = new SelectItem();

                    final String idValue = ReflectionUtil.getBeanValue(objeto,
                            value);
                    i.setValue(idValue);

                    final String labelValue1 = getLocaleBeanValue(objeto,
                            label1);
                    final String labelValue2 = getLocaleBeanValue(objeto,
                            label2);

                    final StringBuilder descripcion = new StringBuilder();
                    if (labelValue1 == null || labelValue2 == null
                            || labelValue1.equals("") || labelValue2.equals("")) {
                        descripcion.append(labelValue1);
                        descripcion.append(labelValue2);
                    } else {
                        descripcion.append(labelValue1);
                        descripcion.append(SEPARADOR_COMBO_LABEL);
                        descripcion.append(labelValue2);
                    }
                    i.setLabel(descripcion.toString());

                    listaItems.add(i);
                }
            } catch (final Exception e) {
                log.error("Fallo al convertir la cadena en intems...");
                throw ExceptionUtil.crearLibraeException(MensajesError
                        .get("ERROR_CONVERT_LIST_ITEM"), e);
            }
        }
        return listaItems;
    }

    /**
     * @param objeto
     * @param propertyLabel
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static String getLocaleBeanValue(Object objeto, String propertyLabel)
            throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        final String descripcion = ReflectionUtil.getBeanValue(objeto, propertyLabel);
        return ComboLocaleManager.getOptional(descripcion);
    }

    /**
     * Transforma una lista en una lista de selectItem de jsf.
     * 
     * @param lista
     * @param value
     *            valor que saldra en la etiqueta
     * @param label
     *            valor que llegara al servidor.
     * @return listado de selectItems
     * @throws LibraeException
     */
    public static List<SelectItem> convertListItemWithoutLabel(
            final List lista, final String value) throws LibraeException {

        final List<SelectItem> listaItems = new ArrayList<SelectItem>();

        for (int i = 0; lista != null && i < lista.size(); ++i) {
            final SelectItem item = new SelectItem();
            item.setValue(i);
            listaItems.add(item);
        }

        return listaItems;
    }

    /**
     * Obtiene el label de un combo internacionalizable
     * 
     * @param propertyKey
     * @return propertyValue
     */
    public static String getComboLabel(final String propertyKey)
            throws LibraeException {

        String result = "";

        if (propertyKey != null && propertyKey.length() > 0) {
            result = ComboLocaleManager.get(propertyKey);
        }

        return result;
    }

    public static List<Long> convertSelectItemToLong(
            List<SelectItem> listaSelectItem) {
        final List<Long> listaLongs = new ArrayList<Long>();
        for (final SelectItem item : listaSelectItem) {
            listaLongs.add((Long) item.getValue());
        }
        return listaLongs;
    }
}
