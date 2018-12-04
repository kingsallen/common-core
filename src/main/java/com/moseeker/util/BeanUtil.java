package com.moseeker.util;

import com.moseeker.constant.Constant;
import com.moseeker.exception.BaseException;
import com.moseeker.vo.Pagination;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @category Bean工具类
 */
public final class BeanUtil extends BeanUtils {

    public static <S, T> void copyPagination(Class<S> classOfS, Class<T> classOfT, Pagination<S> source, Pagination<T> target) throws BeansException, IllegalAccessException, InstantiationException {
        copyPagination(classOfS, classOfT, source, target, null, null);
    }

    public static <S, T> void copyPagination(Class<S> classOfS, Class<T> classOfT, Pagination<S> source, Pagination<T> target, String[] ignoreProperties) throws BeansException, IllegalAccessException, InstantiationException {
        copyPagination(classOfS, classOfT, source, target, ignoreProperties, null);
    }

    public static <S, T> void copyPagination(Class<S> classOfS, Class<T> classOfT, Pagination<S> source, Pagination<T> target, String[] ignoreProperties, String[] nullProperties) throws BeansException, InstantiationException, IllegalAccessException {
        if (source == null) {
            throw new BaseException(Constant.EMPTY, "参数source不能为null");
        }
        if (target == null) {
            target = new Pagination<>();
        }
        target.setPagination(source);
        if (source.getResults() != null) {
            List<T> list = new ArrayList<>();
            for (S s : source.getResults()) {
                T t = classOfT.newInstance();
                copyBean(classOfS, classOfT, s, t, ignoreProperties, nullProperties);
                list.add(t);
            }
            target.setResults(list);
        }
    }

    public static <K, S, T> void copyMap(Class<S> classOfS, Class<T> classOfT, Map<K, S> source, Map<K, T> target) throws BeansException, InstantiationException, IllegalAccessException {
        copyMap(classOfS, classOfT, source, target, null, null);
    }

    public static <K, S, T> void copyMap(Class<S> classOfS, Class<T> classOfT, Map<K, S> source, Map<K, T> target, String[] ignoreProperties) throws BeansException, InstantiationException, IllegalAccessException {
        copyMap(classOfS, classOfT, source, target, ignoreProperties, null);
    }

    public static <K, S, T> void copyMap(Class<S> classOfS, Class<T> classOfT, Map<K, S> source, Map<K, T> target, String[] ignoreProperties, String[] nullProperties) throws BeansException, InstantiationException, IllegalAccessException {
        if (source == null) {
            throw new BaseException(Constant.EMPTY, "参数source不能为null");
        }
        if (target == null) {
            target = new HashMap<>();
        }
        target.clear();
        for (K k : source.keySet()) {
            S s = source.get(k);
            T t = classOfT.newInstance();
            copyBean(classOfS, classOfT, s, t, ignoreProperties, nullProperties);
            target.put(k, t);
        }
    }

    public static <S, T> void copyList(Class<S> classOfS, Class<T> classOfT, List<S> source, List<T> target) throws BeansException, InstantiationException, IllegalAccessException {
        copyList(classOfS, classOfT, source, target, null, null);
    }

    public static <S, T> void copyList(Class<S> classOfS, Class<T> classOfT, List<S> source, List<T> target, String[] ignoreProperties) throws BeansException, InstantiationException, IllegalAccessException {
        copyList(classOfS, classOfT, source, target, ignoreProperties, null);
    }

    public static <S, T> void copyList(Class<S> classOfS, Class<T> classOfT, List<S> source, List<T> target, String[] ignoreProperties, String[] nullProperties) throws BeansException, IllegalAccessException, InstantiationException {
        if (source == null) {
            throw new BaseException(Constant.EMPTY, "参数source不能为null");
        }
        if (target == null) {
            target = new ArrayList<>();
        }
        target.clear();
        for (S s : source) {
            T t = classOfT.newInstance();
            copyBean(classOfS, classOfT, s, t, ignoreProperties, nullProperties);
            target.add(t);
        }
    }

    public static <S, T> void copyBean(Class<S> classOfS, Class<T> classOfT, S source, T target) throws BeansException, InstantiationException, IllegalAccessException {
        copyBean(classOfS, classOfT, source, target, null, null);
    }

    public static <S, T> void copyBean(Class<S> classOfS, Class<T> classOfT, S source, T target, String[] ignoreProperties) throws BeansException, InstantiationException, IllegalAccessException {
        copyBean(classOfS, classOfT, source, target, ignoreProperties, null);
    }

    public static <S, T> void copyBean(Class<S> classOfS, Class<T> classOfT, S source, T target, String[] ignoreProperties, String[] nullProperties) throws BeansException, InstantiationException, IllegalAccessException {
        if (source == null) {
            throw new BaseException(Constant.EMPTY, "参数source不能为null");
        }
        if (target == null) {
            target = classOfT.newInstance();
        }
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPropertyDescriptors = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
        List<String> nullPropertiesList = (nullProperties != null) ? Arrays.asList(nullProperties) : new ArrayList<>();
        for (PropertyDescriptor targetPropertyDescriptor : targetPropertyDescriptors) {
            if (targetPropertyDescriptor.getWriteMethod() != null && (ignoreProperties == null || (!ignoreList.contains(targetPropertyDescriptor.getName())))) {
                PropertyDescriptor sourcePropertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), targetPropertyDescriptor.getName());
                if (sourcePropertyDescriptor != null && sourcePropertyDescriptor.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePropertyDescriptor.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        Method targetReadMethod = targetPropertyDescriptor.getReadMethod();
                        if (!Modifier.isPublic(targetReadMethod.getDeclaringClass().getModifiers())) {
                            targetReadMethod.setAccessible(true);
                        }
                        Object targetVal = targetReadMethod.invoke(target);
                        if (targetVal != null && value == null && !nullPropertiesList.contains(targetPropertyDescriptor.getName())) {
                            continue;
                        }
                        Method writeMethod = targetPropertyDescriptor.getWriteMethod();
                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        writeMethod.invoke(target, value);
                    } catch (Throwable e) {
                        throw new FatalBeanException("Could not copy properties from source to target", e);
                    }
                }
            }
        }
    }

    /**
     * @param source
     * @param target
     * @throws BeansException
     * @Deprecated 用copy方法替代
     */
    @Deprecated
    public static void copyProperties(Object source, Object target) throws BeansException {
        copyProperties(source, target, null, null);
    }

    /**
     * @param source
     * @param target
     * @param ignoreProperties
     * @throws BeansException
     * @Deprecated 用copy方法替代
     */
    @Deprecated
    public static void copyProperties(Object source, Object target, String[] ignoreProperties) throws BeansException {
        copyProperties(source, target, ignoreProperties, null);
    }

    /**
     * @param source
     * @param target
     * @param ignoreProperties
     * @param nullProperties
     * @throws BeansException
     * @Deprecated 用copy方法替代
     */
    @Deprecated
    public static void copyProperties(Object source, Object target, String[] ignoreProperties, String[] nullProperties) throws BeansException {
        if (source == null) {
            throw new BaseException(Constant.EMPTY, "Source must not be null");
        }
        if (target == null) {
            throw new BaseException(Constant.EMPTY, "Target must not be null");
        }
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPropertyDescriptors = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
        List<String> nullPropertiesList = (nullProperties != null) ? Arrays.asList(nullProperties) : new ArrayList<String>();
        for (PropertyDescriptor targetPropertyDescriptor : targetPropertyDescriptors) {
            if (targetPropertyDescriptor.getWriteMethod() != null && (ignoreProperties == null || (!ignoreList.contains(targetPropertyDescriptor.getName())))) {
                PropertyDescriptor sourcePropertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), targetPropertyDescriptor.getName());
                if (sourcePropertyDescriptor != null && sourcePropertyDescriptor.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePropertyDescriptor.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        Method targetReadMethod = targetPropertyDescriptor.getReadMethod();
                        if (!Modifier.isPublic(targetReadMethod.getDeclaringClass().getModifiers())) {
                            targetReadMethod.setAccessible(true);
                        }
                        Object targetVal = targetReadMethod.invoke(target);
                        if (targetVal != null && value == null && !nullPropertiesList.contains(targetPropertyDescriptor.getName())) {
                            continue;
                        }
                        Method writeMethod = targetPropertyDescriptor.getWriteMethod();
                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        writeMethod.invoke(target, value);
                    } catch (Throwable e) {
                        throw new FatalBeanException("Could not copy properties from source to target", e);
                    }
                }
            }
        }
    }

    public static void setPropertyVal(Object bean, String propertyName, Object propertyValue) throws Throwable {
        if (bean == null) {
            return;
        }
        if (StringUtils.isEmpty(propertyName)) {
            return;
        }
        PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(bean.getClass(), propertyName);
        if (propertyDescriptor != null) {
            if (propertyDescriptor.getReadMethod() != null) {
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(bean, propertyValue);
            }
        }
    }

    public static Map<String, Object> transBean2Map(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            // 过滤class属性
            if (!key.equals("class")) {// 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
                map.put(key, value);
            }
        }
        return map;
    }

}