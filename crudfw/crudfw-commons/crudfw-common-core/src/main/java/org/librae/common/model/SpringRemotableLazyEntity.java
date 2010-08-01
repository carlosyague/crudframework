package org.librae.common.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.librae.common.exception.ExceptionUtil;

public abstract class SpringRemotableLazyEntity<T> extends BaseObject {

    /**
     *
     */
    private static final long  serialVersionUID     = -454943228241197989L;

    public final static String PREFIX_METHOD_GETTER = "get";
    public final static String PREFIX_METHOD_SETTER = "set";

    public abstract T newInstance();

    /**
     * Resolver propiedades LAZY de todos los atributos de la clase this
     * 
     * @return
     */
    public T cloneNonLazy() {
        T result = null;
        final Class clazz = this.getClass();

        Object newInstance = null;

        try {
            newInstance = this.newInstance();

            result = cloneNonLazy(newInstance);

        } catch (final SecurityException e) {
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * Resolver propiedades LAZY de todos los atributos de la clase this y de
     * sus superclases
     * 
     * @return
     */
    public T cloneNonLazySuperClass() {
        T result = null;
        final Class clazz = this.getClass();

        Object newInstance = null;

        try {
            newInstance = this.newInstance();

            result = cloneNonLazySuperClass(newInstance);

        } catch (final SecurityException e) {
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        }

        return result;

    }

    public T cloneNonLazy(Object newInstance) {
        final Class clazz = this.getClass();
        final Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
            final Field field = fields[i];

            field.setAccessible(true);

            if (!isStatic(field) && !isFinal(field)) {

                try {
                    newInstance = setFieldValue(clazz, field, this, newInstance);

                } catch (final SecurityException e) {
                    e.printStackTrace();
                } catch (final NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                } catch (final InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        T result = null;
        if (newInstance instanceof SpringRemotableLazyEntity) {
            result = (T) newInstance;
        }

        return result;
    }

    public T cloneNonLazySuperClass(Object newInstance) {
        final Class clazz = this.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        final Field[] fieldsSuperClass = clazz.getSuperclass()
                .getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
            final Field field = fields[i];

            field.setAccessible(true);

            if (!isStatic(field) && !isFinal(field)) {

                try {
                    newInstance = setFieldValue(clazz, field, this, newInstance);

                } catch (final SecurityException e) {
                    e.printStackTrace();
                } catch (final NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                } catch (final InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < fieldsSuperClass.length; ++i) {
            final Field field = fieldsSuperClass[i];

            field.setAccessible(true);

            if (!isStatic(field) && !isFinal(field)) {

                try {
                    newInstance = setFieldValue(clazz, field, this, newInstance);

                } catch (final SecurityException e) {
                    e.printStackTrace();
                } catch (final NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                } catch (final InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        T result = null;
        if (newInstance instanceof SpringRemotableLazyEntity) {
            result = (T) newInstance;
        }

        return result;
    }

    private static Object setFieldValueByMethod(Class clazz, Field field,
            Object srcObj, Object targetObj) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {

        final Method getterMethod = getGetterMethodByField(clazz, field);
        final Method setterMethod = getSetterMethodByField(clazz, field);

        final Object getterResult = getterMethod
                .invoke(srcObj, (Object[]) null);

        if (getterResult != null) {
            // trato de ejecutar el setter

            Object setterArgument = null;

            if (getterResult instanceof SpringRemotableLazyEntity) {
                final SpringRemotableLazyEntity getterResultSRLazy = (SpringRemotableLazyEntity) getterResult;
                setterArgument = getterResultSRLazy.cloneNonLazy();

            } else if (getterResult instanceof BaseObject) {
                final String className = getterResult.getClass().getName();
                final StringBuilder msg = new StringBuilder(": " + className);
                throw ExceptionUtil.crearLibraeException(
                        "ERROR_CLONE_NON_LAZY", msg);
            } else {
                setterArgument = getterResult;
            }

            final Object[] setterArguments = { setterArgument };

            targetObj = setterMethod.invoke(targetObj, setterArguments);
        }

        return targetObj;
    }

    private static Object setFieldValue(Class clazz, Field field,
            Object srcObj, Object targetObj) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {

        final Object srcFieldValue = field.get(srcObj);

        if (srcFieldValue != null) {
            Object targetFieldValue = null;

            if (srcFieldValue instanceof SpringRemotableLazyEntity) {
                final SpringRemotableLazyEntity srcBaseObj = (SpringRemotableLazyEntity) srcFieldValue;
                targetFieldValue = srcBaseObj.cloneNonLazy();

            } else if (srcFieldValue instanceof BaseObject) {
                final String className = srcFieldValue.getClass().getName();
                final StringBuilder msg = new StringBuilder(": " + className);
                throw ExceptionUtil.crearLibraeException(
                        "ERROR_CLONE_NON_LAZY", msg);
            } else {
                targetFieldValue = srcFieldValue;
            }
            field.set(targetObj, targetFieldValue);
        }

        return targetObj;
    }

    private static Method getGetterMethodByField(Class clazz, Field field)
            throws SecurityException, NoSuchMethodException {
        final String methodName = getMethodNameByField(field,
                PREFIX_METHOD_GETTER);

        final Method method = clazz.getMethod(methodName, null);

        // forzamos la accesibilidad pública del métodos
        method.setAccessible(true);

        return method;
    }

    private static Method getSetterMethodByField(Class clazz, Field field)
            throws SecurityException, NoSuchMethodException {
        final String methodName = getMethodNameByField(field,
                PREFIX_METHOD_SETTER);

        final Method method = clazz.getMethod(methodName, field.getType());

        // forzamos la accesibilidad pública del métodos
        method.setAccessible(true);

        return method;
    }

    private static String getMethodNameByField(Field field, String prefix) {
        String result = null;

        final String fieldName = field.getName();
        result = createMethodNameByPrefix(fieldName, prefix);

        return result;
    }

    /**
     * returns a method name by a prefix
     * 
     * @param fieldName
     * @param methodType
     * @return
     */
    private static String createMethodNameByPrefix(final String fieldName,
            final String prefix) {
        final StringBuilder result = new StringBuilder();

        if (fieldName != null && fieldName.length() >= 1) {

            result.append(prefix).append(
                    fieldName.substring(0, 1).toUpperCase());

            if (fieldName.length() > 1) {
                result.append(fieldName.substring(1));
            }
        }

        return result.toString();
    }

    private static Boolean isStatic(Field field) {
        final int modifiers = field.getModifiers();

        return Modifier.isStatic(modifiers);
    }

    private static Boolean isFinal(Field field) {
        final int modifiers = field.getModifiers();

        return Modifier.isFinal(modifiers);
    }

    private static Class forceAccesibleFields(Class clazz, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        final Class result = clazz;

        final Field[] fields = result.getDeclaredFields();
        for (int i = 0; (fields != null) && (i < fields.length); ++i) {
            final Field field = fields[i];

            if (!isStatic(field) && !isFinal(field)) {
                field.setAccessible(true);
            }
        }

        return result;
    }

    private static Object createInstance(Class clazz, Object obj) {

        Object instance = null;
        try {
            final Class crackedClass = forceAccesibleFields(clazz, obj);
            instance = crackedClass.newInstance();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }
}
