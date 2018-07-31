package com.moseeker.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public interface BaseEnum<E extends Enum<E>> {

	String getKey();

	String getValue();
	
	public static <E> boolean isKeyExists(Class<E> enumClass, String key) {
		if (StringUtils.isEmpty(key)) {
			return false;
		}
		List<String> keys = getKeyList(enumClass);
		if (CollectionUtils.isEmpty(keys)) {
			return false;
		}
		return keys.contains(key);
	}
	
	public static <E> boolean isValueExists(Class<E> enumClass, String value) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}
		List<String> values = getValueList(enumClass);
		if (CollectionUtils.isEmpty(values)) {
			return false;
		}
		return values.contains(value);
	}

	public static <E> List<Map<String, String>> getMapList(Class<E> enumClass) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		if (!BaseEnum.class.isAssignableFrom(enumClass)) {
			return mapList;
		}
		for (E e : enumClass.getEnumConstants()) {
			BaseEnum<?> ce = BaseEnum.class.cast(e);
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", ce.getKey());
			map.put("value", ce.getValue());
			mapList.add(map);
		}
		return mapList;
	}

	public static <E> List<String> getKeyList(Class<E> enumClass) {
		List<String> list = new ArrayList<String>();
		if (!BaseEnum.class.isAssignableFrom(enumClass)) {
			return list;
		}
		for (E e : enumClass.getEnumConstants()) {
			BaseEnum<?> ce = BaseEnum.class.cast(e);
			list.add(ce.getKey());
		}
		return list;
	}

	public static <E> List<String> getValueList(Class<E> enumClass) {
		List<String> list = new ArrayList<String>();
		if (!BaseEnum.class.isAssignableFrom(enumClass)) {
			return list;
		}
		for (E e : enumClass.getEnumConstants()) {
			BaseEnum<?> ce = BaseEnum.class.cast(e);
			list.add(ce.getValue());
		}
		return list;
	}

	public static List<Map<String, String>> getMapList(BaseEnum<?>... enumData) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (BaseEnum<?> ed : enumData) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", ed.getKey());
			map.put("value", ed.getValue());
			mapList.add(map);
		}
		return mapList;
	}

	public static Map<String, String> getMap(BaseEnum<?> enumData) {
		Map<String, String> enumMap = new HashMap<String, String>();
		enumMap.put("key", enumData.getKey());
		enumMap.put("value", enumData.getValue());
		return enumMap;
	}

	public static <E> String key2Value(Class<E> enumClass, String key) {
		if (!BaseEnum.class.isAssignableFrom(enumClass)) {
			return "";
		}
		for (E e : enumClass.getEnumConstants()) {
			BaseEnum<?> ce = BaseEnum.class.cast(e);
			if (ce.getKey().equals(key)) {
				return ce.getValue();
			}
		}
		return "";
	}

	public static <E> E key2Enum(Class<E> enumClass, String key) {
		if (!BaseEnum.class.isAssignableFrom(enumClass)) {
			return null;
		}
		for (E e : enumClass.getEnumConstants()) {
			BaseEnum<?> ce = BaseEnum.class.cast(e);
			if (ce.getKey().equals(key)) {
				return e;
			}
		}
		return null;
	}

	public static <E> String value2Key(Class<E> enumClass, String value) {
		if (!BaseEnum.class.isAssignableFrom(enumClass)) {
			return "";
		}
		for (E e : enumClass.getEnumConstants()) {
			BaseEnum<?> ce = BaseEnum.class.cast(e);
			if (ce.getValue().equalsIgnoreCase(value)) {
				return ce.getKey();
			}
		}
		return "";
	}

}
