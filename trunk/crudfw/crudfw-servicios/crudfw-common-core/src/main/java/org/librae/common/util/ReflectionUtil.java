package org.librae.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Clase que contiene una serie de métodos estáticos que facilitan el trabajo
 * con los objetos.
 * 
 * @author jcisneros
 */
public class ReflectionUtil {

    /**
     * Devuelve el resultado del objecto por reflection.
     * 
     * @param objeto
     * @param propertyLabel
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static String getBeanValue(Object objeto, String propertyLabel)
            throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        final Class[] vacio = new Class[0];
        final Object[] vacioObjetos = new Object[0];

        final Method metodo = objeto.getClass()
                .getMethod(
                        "get"
                                + StringUtil
                                        .primeraLetraMayusculas(propertyLabel),
                        vacio);
        final Object resultadoMetodo = metodo.invoke(objeto, vacioObjetos);

        String value = null;
        if (resultadoMetodo != null) {
            value = resultadoMetodo.toString();
        }

        return value;
    }

    /**
     * Devuelve el resultado del objecto por reflection.
     * 
     * @param objeto
     * @param propertyLabel
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object getBean(Object objeto, String propertyLabel)
            throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        final Class[] vacio = new Class[0];
        final Object[] vacioObjetos = new Object[0];

        final Method metodo = objeto.getClass()
                .getMethod(
                        "get"
                                + StringUtil
                                        .primeraLetraMayusculas(propertyLabel),
                        vacio);
        final Object resultadoMetodo = metodo.invoke(objeto, vacioObjetos);

        return resultadoMetodo;
    }

    /**
     * Devuelve el resultado del objecto por reflection.
     * 
     * @param objeto
     * @param propertyLabel
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object getBeanArrayList(Object objeto, String propertyLabel)
            throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        final Class[] vacio = new Class[0];
        final Object[] vacioObjetos = new Object[0];

        final Method metodo = objeto.getClass().getMethod(
                "get"
                        + StringUtil.primeraLetraMayusculas(propertyLabel
                                .substring(0, propertyLabel.length() - 3)),
                vacio);

        final List resultadoMetodo = (List) metodo.invoke(objeto, vacioObjetos);

        return resultadoMetodo.get(0);
    }

    public static Constructor getConstructorTreeNodeBase4(Class treeNodeBase)
            throws SecurityException, NoSuchMethodException {
        final Constructor[] constructors = treeNodeBase
                .getDeclaredConstructors();
        final Constructor constructor = treeNodeBase
                .getConstructor(new Class[] { String.class, String.class,
                        String.class, boolean.class });
        return constructor;
    }

    public static Constructor getConstructorTreeNodeBase3(Class treeNodeBase)
            throws SecurityException, NoSuchMethodException {
        final Constructor[] constructors = treeNodeBase
                .getDeclaredConstructors();
        final Constructor constructor = treeNodeBase
                .getConstructor(new Class[] { String.class, String.class,
                        boolean.class });
        return constructor;
    }

    /**
     * Devuelve el resultado del objecto por reflection.
     * 
     * @param objeto
     * @param propertyLabel
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object getBeanTokeniker(Object objeto, String propertyLabel)
            throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        final Class[] vacio = new Class[0];
        final Object[] vacioObjetos = new Object[0];
        Method metodo = null;
        Object resultadoMetodo = objeto;

        final StringTokenizer st = new StringTokenizer(propertyLabel, ".");
        while (st.hasMoreTokens() && resultadoMetodo != null) {
            final String siguiente = st.nextToken();
            metodo = resultadoMetodo.getClass()
                    .getMethod(
                            "get"
                                    + StringUtil
                                            .primeraLetraMayusculas(siguiente),
                            vacio);
            resultadoMetodo = metodo.invoke(resultadoMetodo, vacioObjetos);
        }

        return resultadoMetodo;
    }

    /**
     * Devuelve el resultado del objecto por reflection.
     * 
     * @param objeto
     * @param propertyLabel
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static String executeMethod(Object objeto, String propertyLabel)
            throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        final Class[] vacio = new Class[0];
        final Object[] vacioObjetos = new Object[0];

        final Method metodo = objeto.getClass().getMethod(propertyLabel, vacio);
        final Object resultadoMetodo = metodo.invoke(objeto, vacioObjetos);

        String value = null;
        if (resultadoMetodo != null) {
            value = resultadoMetodo.toString();
        }

        return value;
    }

}
