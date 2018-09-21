package com.moseeker.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moseeker.vo.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.StringUtils;

import com.moseeker.exception.BaseException;

/**
 * @category Bean工具类
 */
public final class BeanUtil extends BeanUtils {

	public static <T> void copy(Pagination<T> source, Pagination<T> target) throws BeansException, IllegalAccessException, InstantiationException {
		copy(source, target, null, null);
	}

	public static <T> void copy(Pagination<T> source, Pagination<T> target, String[] ignoreProperties) throws BeansException, IllegalAccessException, InstantiationException {
		copy(source, target, ignoreProperties, null);
	}

	public static <T> void copy(Pagination<T> source, Pagination<T> target, String[] ignoreProperties, String[] nullProperties) throws BeansException, InstantiationException, IllegalAccessException {
		if (source == null) {
			throw new BaseException(C.EMPTY, "参数source不能为null");
		}
		if (target == null) {
			target = new Pagination<>();
		}
		target.setPagination(source);
		if (source.getResults() != null) {
			copy(source.getResults(), target.getResults(), ignoreProperties, nullProperties);
		}
	}

	public static <K, T> void copy(Map<K, T> source, Map<K, T> target) throws BeansException, InstantiationException, IllegalAccessException {
		copy(source, target, null, null);
	}

	public static <K, T> void copy(Map<K, T> source, Map<K, T> target, String[] ignoreProperties) throws BeansException, InstantiationException, IllegalAccessException {
		copy(source, target, ignoreProperties, null);
	}

	@SuppressWarnings("unchecked")
	public static <K, T> void copy(Map<K, T> source, Map<K, T> target, String[] ignoreProperties, String[] nullProperties) throws BeansException, InstantiationException, IllegalAccessException {
		if (source == null) {
			throw new BaseException(C.EMPTY, "参数source不能为null");
		}
		if (target == null) {
			target = new HashMap<>();
		}
		target.clear();
		for (K k : source.keySet()) {
			T s = source.get(k);
			T t = (T) s.getClass().newInstance();
			copy(s, t, ignoreProperties, nullProperties);
			target.put(k, t);
		}
	}

	public static <T> void copy(List<T> source, List<T> target) throws BeansException, InstantiationException, IllegalAccessException {
		copy(source, target, null, null);
	}

	public static <T> void copy(List<T> source, List<T> target, String[] ignoreProperties) throws BeansException, InstantiationException, IllegalAccessException {
		copy(source, target, ignoreProperties, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> void copy(List<T> source, List<T> target, String[] ignoreProperties, String[] nullProperties) throws BeansException, IllegalAccessException, InstantiationException {
		if (source == null) {
			throw new BaseException(C.EMPTY, "参数source不能为null");
		}
		if (target == null) {
			target = new ArrayList<>();
		}
		target.clear();
		for (T s : source) {
			T t = (T) s.getClass().newInstance();
			copy(s, t, ignoreProperties, nullProperties);
			target.add(t);
		}
	}

	public static <T> void copy(T source, T target) throws BeansException, InstantiationException, IllegalAccessException {
		copy(source, target, null, null);
	}

	public static <T> void copy(T source, T target, String[] ignoreProperties) throws BeansException, InstantiationException, IllegalAccessException {
		copy(source, target, ignoreProperties, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> void copy(T source, T target, String[] ignoreProperties, String[] nullProperties) throws BeansException, InstantiationException, IllegalAccessException {
		if (source == null) {
			throw new BaseException(C.EMPTY, "参数source不能为null");
		}
		if (target == null) {
			target = (T) source.getClass().newInstance();
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
			throw new BaseException(C.EMPTY, "Source must not be null");
		}
		if (target == null) {
			throw new BaseException(C.EMPTY, "Target must not be null");
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