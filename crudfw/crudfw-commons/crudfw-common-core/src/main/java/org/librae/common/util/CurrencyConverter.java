package org.librae.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is converts a Double to a double-digit String (and vise-versa) by
 * BeanUtils when copying properties.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class CurrencyConverter implements Converter {
    /**
     * el log.
     */
    private final Log     log       = LogFactory
                                            .getLog(CurrencyConverter.class);
    /**
     * el formatter.
     */
    private DecimalFormat formatter = new DecimalFormat("###,###.00");

    /**
     * Formatea en formato decimal.
     * 
     * @param df
     *            el formateador decimal
     */
    public final void setDecimalFormatter(final DecimalFormat df) {
        this.formatter = df;
    }

    /**
     * Convert a String to a Double and a Double to a String.
     * 
     * @param type
     *            the class type to output
     * @param value
     *            the object to convert
     * @return object the converted object (Double or String)
     */
    public final Object convert(final Class type, final Object value) {
        // for a null value, return null
        if (value == null) {
            return null;
        } else {
            if (value instanceof String) {
                if (log.isDebugEnabled()) {
                    log.debug("value (" + value + ") instance of String");
                }

                try {
                    if (StringUtils.isBlank(String.valueOf(value))) {
                        return null;
                    }

                    if (log.isDebugEnabled()) {
                        log.debug("converting '" + value + "' to a decimal");
                    }

                    // formatter.setDecimalSeparatorAlwaysShown(true);
                    final Number num = formatter.parse(String.valueOf(value));

                    return num.doubleValue();
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
            } else if (value instanceof Double) {
                if (log.isDebugEnabled()) {
                    log.debug("value (" + value + ") instance of Double");
                    log.debug("returning double: " + formatter.format(value));
                }

                return formatter.format(value);
            }
        }

        throw new ConversionException("Could not convert " + value + " to "
                + type.getName() + "!");
    }
}
