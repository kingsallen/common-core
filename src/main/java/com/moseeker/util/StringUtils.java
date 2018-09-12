package com.moseeker.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.alibaba.fastjson.JSON;

public class StringUtils {

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().equals("");
	}

	public static boolean isNotNullOrEmpty(String str) {
		return str != null && !str.trim().equals("");
	}

	public static boolean isEmptyObject(Object obj) {
		return toString(obj).equals("");
	}

	public static String toString(Object obj) {
		if (obj != null && !"".equals(obj.toString())) {
			String objValue = obj.toString().trim();
			return objValue;
		} else {
			return "";
		}
	}

	public static boolean isEmptyList(List<?> list) {
		return list == null || list.isEmpty();
	}

	public static boolean isNotEmptyList(List<?> list) {
		return list != null && !list.isEmpty();
	}

	public static boolean isEmptySet(Set<?> set) {
		return set == null || set.isEmpty();
	}

	public static boolean isNotEmptySet(Set<?> set) {
		return set != null && !set.isEmpty();
	}

	public static boolean isEmptyMap(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static boolean isNotEmptyMap(Map<?, ?> map) {
		return map != null && !map.isEmpty();
	}

	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String converToArrayStr(Collection<Integer> collection) {
		if (collection != null && collection.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			collection.forEach((i) -> {
				sb.append(i).append(",");
			});
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			return sb.toString();
		} else {
			return null;
		}
	}

	public static String converToStr(Collection<Integer> collection) {
		if (collection != null && collection.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("(");
			collection.forEach((i) -> {
				sb.append(i).append(",");
			});
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
			return sb.toString();
		} else {
			return null;
		}
	}

	public static String converFromArrayToStr(int[] array) {
		if (array != null && array.length > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			int[] var2 = array;
			int var3 = array.length;
			for (int var4 = 0; var4 < var3; ++var4) {
				int i = var2[var4];
				sb.append(i).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			return sb.toString();
		} else {
			return null;
		}
	}

	public static String underLineToCamel(String str) {
		return underLineToCamel(str, false);
	}

	public static String underLineToCamel(String str, boolean firstUpper) {
		if (isNullOrEmpty(str)) {
			return str;
		} else {
			String[] splitArray = str.split("_");
			StringBuilder builder = new StringBuilder();
			int index = 0;
			if (!firstUpper && splitArray[0].length() > 0) {
				builder.append(splitArray[0].substring(0, 1).toLowerCase()).append(splitArray[0].substring(1));
				++index;
			}
			for (; index < splitArray.length; ++index) {
				if (splitArray[index].length() == 0) {
					builder.append("_");
				} else {
					builder.append(splitArray[index].substring(0, 1).toUpperCase()).append(splitArray[index].substring(1));
				}
			}
			return builder.toString();
		}
	}

	public static boolean lastContain(String context, String c) {
		if (isNotNullOrEmpty(context)) {
			int index = context.lastIndexOf(c);
			if (index > 0 && context.length() - c.length() == index) {
				return true;
			}
		}
		return false;
	}

	public static boolean firstContain(String context, String c) {
		if (isNotNullOrEmpty(context)) {
			int index = context.indexOf(c);
			if (index == 0 && context.contains(c)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isJsonNullOrEmpty(Object obj) {
		return obj instanceof String ? !org.apache.commons.lang.StringUtils.isNotBlank((String) obj) || obj.equals("[]") || obj.equals("{}") || obj.equals("null") : obj == null;
	}

	public static String humpName(String strName) {
		String[] strs = strName.split("_");
		if (strs.length <= 1) {
			return strName;
		} else {
			String name = strs[0];
			for (int i = 1; i < strs.length; ++i) {
				name = name + strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
			}
			return name;
		}
	}

	public static String underscoreName(String camelCaseName) {
		StringBuilder result = new StringBuilder();
		if (camelCaseName != null && camelCaseName.length() > 0) {
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); ++i) {
				char ch = camelCaseName.charAt(i);
				if (Character.isUpperCase(ch)) {
					result.append("_");
					result.append(Character.toLowerCase(ch));
				} else {
					result.append(ch);
				}
			}
		}
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> underscoreNameMap(Map<String, Object> resume) {
		if (resume != null && !resume.isEmpty()) {
			Map<String, Object> result = new HashMap<String, Object>();
			Iterator<String> var2 = resume.keySet().iterator();
			while (true) {
				while (var2.hasNext()) {
					String key = var2.next();
					String newKey = underscoreName(key);
					if (isNullOrEmpty(newKey)) {
						newKey = key;
					}
					if (resume.get(key) == null) {
						result.put(newKey, resume.get(key));
					} else if (resume.get(key) instanceof Map) {
						Map<String, Object> map = (Map<String, Object>) resume.get(key);
						Map<String, Object> result1 = new HashMap<String, Object>();
						if (map != null && !map.isEmpty()) {
							result1 = underscoreNameMap(map);
						}
						result.put(newKey, result1);
					} else {
						Object value;
						if (resume.get(key) instanceof List) {
							List<Object> list = (List<Object>) resume.get(key);
							List<Object> tempList = new ArrayList<Object>();
							if (!isEmptyList(list)) {
								Iterator<Object> var14 = list.iterator();

								while (var14.hasNext()) {
									value = var14.next();
									if (value instanceof Map) {
										tempList.add(underscoreNameMap((Map<String, Object>) value));
									} else {
										tempList.add(value);
									}
								}
							}
							result.put(newKey, tempList);
						} else if (!resume.get(key).getClass().isArray()) {
							result.put(newKey, resume.get(key));
						} else {
							int length = Array.getLength(resume.get(key));
							if (length != 0) {
								result.put(newKey, new Object[1]);
							} else {
								Object[] arr = new Object[length];
								for (int i = 0; i < Array.getLength(resume.get(key)); ++i) {
									value = Array.get(resume.get(key), i);
									if (value instanceof Map) {
										Map<String, Object> result2 = underscoreNameMap((Map<String, Object>) value);
										arr[i] = result2;
									} else {
										arr[i] = value;
									}
								}
								result.put(newKey, arr);
							}
						}
					}
				}
				return result;
			}
		} else {
			return null;
		}
	}

	public static String filterStringForSearch(String value) {
		if (isNotNullOrEmpty(value)) {
			if (value.contains("/")) {
				value = value.replaceAll("/", " ");
			}

			if (value.contains("OR")) {
				value = value.replaceAll("OR", " ");
			}

			if (value.contains("AND")) {
				value = value.replaceAll("AND", " ");
			}

			if (value.contains("(")) {
				value = value.replaceAll("\\(", " ");
			}

			if (value.contains(")")) {
				value = value.replaceAll("\\)", " ");
			}

			if (value.contains("+")) {
				value = value.replaceAll("\\+", " ");
			}

			if (value.contains("\\")) {
				value = value.replaceAll("\\\\", " ");
			}
			if (value.contains("（")) {
				value = value.replaceAll("（", " ");
			}
			if (value.contains("）")) {
				value = value.replaceAll("）", " ");
			}
			if (value.contains("-")) {
				value = value.replaceAll("-", " ");
			}
			if (value.contains("&")) {
				value = value.replaceAll("&", " ");
			}
			if (value.contains("+")) {
				value = value.replaceAll("\\+", " ");
			}
			if (value.contains("-")) {
				value = value.replaceAll("-", " ");
			}
			if (value.contains("|")) {
				value = value.replaceAll("|", " ");
			}
			if (value.contains("!")) {
				value = value.replaceAll("!", " ");
			}
			if (value.contains("{")) {
				value = value.replaceAll("\\{", " ");
			}
			if (value.contains("}")) {
				value = value.replaceAll("\\}", " ");
			}
			if (value.contains("^")) {
				value = value.replaceAll("\\^", " ");
			}
			if (value.contains("\"")) {
				value = value.replaceAll("\"", " ");
			}

			if (value.contains("~")) {
				value = value.replaceAll("~", " ");
			}
			if (value.contains("*")) {
				value = value.replaceAll("\\*", " ");
			}
			if (value.contains("?")) {
				value = value.replaceAll("\\?", " ");
			}
			if (value.contains(":")) {
				value = value.replaceAll(":", " ");
			}
			if (value.contains("'")) {
				value = value.replaceAll("'", " ");
			}
			if (value.contains("@")) {
				value = value.replaceAll("@", " ");
			}
			if (value.contains("%")) {
				value = value.replaceAll("%", " ");
			}
			if (value.contains("$")) {
				value = value.replaceAll("\\$", " ");
			}
			if (value.contains("#")) {
				value = value.replaceAll("#", " ");
			}
			if (value.contains("=")) {
				value = value.replaceAll("=", " ");
			}
			if (isNotNullOrEmpty(value)) {
				value = value.trim();
			}
		}
		return value;
	}

	public static boolean isEmptyOrZero(Integer i) {
		if (i == null || i <= 0) {
			return true;
		}
		return false;
	}

	public static Map<String, Object> splitHttpParam(String urlparam) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] param = urlparam.split("&");
		for (String keyvalue : param) {
			String[] pair = keyvalue.split("=");
			if (pair.length == 2) {
				map.put(pair[0], pair[1]);
			}
		}
		return map;
	}

	public static <T, R> R convertPOJOToVO(T object, Class<R> className) throws Exception {
		if (object == null) {
			return null;
		}
		String data = JSON.toJSONString(object);
		return JSON.parseObject(data, className);
	}

	public static <T, R> R convertVOoPOJO(T object, Class<R> className) throws Exception {
		if (object == null) {
			return null;
		}
		String data = JSON.toJSONString(object);
		return JSON.parseObject(data, className);
	}

	public static <T, R> List<R> convertPOJOToVO(List<T> objectList, Class<R> className) throws Exception {
		if (StringUtils.isEmptyList(objectList)) {
			return new ArrayList<>();
		}
		List<R> list = new ArrayList<R>();
		for (T object : objectList) {
			String data = JSON.toJSONString(object);
			R result = JSON.parseObject(data, className);
			list.add(result);
		}
		return list;
	}

	/*
	 * 获取一个对象中固定的列表
	 */
	public static List<Integer> getIdList(List<?> list, String code, boolean addZero) {
		if (isEmptyList(list)) {
			return null;
		}
		if (isNullOrEmpty(code)) {
			code = "id";
		}
		List<Integer> result = new ArrayList<>();
		for (Object object : list) {
			Map<?, ?> map = JSON.parseObject(JSON.toJSONString(object));
			Integer id = (Integer) map.get(code);
			if (id == null) {
				continue;
			}
			if (id == 0 && !addZero) {
				continue;
			}
			if (!result.contains(id)) {
				result.add(id);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <R> List<R> getFieldList(List<?> list, String code, Class<R> className) {
		if (isEmptyList(list)) {
			return null;
		}
		if (isNullOrEmpty(code)) {
			code = "id";
		}
		List<R> result = new ArrayList<>();
		for (Object object : list) {
			Map<?, ?> map = JSON.parseObject(JSON.toJSONString(object));
			Object data = map.get(code);
			if (data == null) {
				continue;
			}
			if (!result.contains(data)) {
				result.add((R) data);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T, R> List<R> getResultList(int code, List<T> list, String field, Class<R> classInit, String className) throws Exception {
		if (isEmptyList(list)) {
			return null;
		}
		for (T object : list) {
			Map<String, Object> map = JSON.parseObject(JSON.toJSONString(object));
			int id = (int) map.get(field);
			if (id == code) {
				Object obj = map.get(className);
				if (obj == null) {
					return null;
				}
				Object ss = object.getClass().getDeclaredMethod("get" + className.substring(0, 1).toUpperCase() + className.substring(1)).invoke(object);
				List<R> result = (List<R>) ss;
				return result;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T, R> R getResult(int code, List<T> list, String field, Class<R> classInit, String className) throws Exception {
		if (isEmptyList(list)) {
			return null;
		}
		for (T object : list) {
			Map<String, Object> map = JSON.parseObject(JSON.toJSONString(object));
			int id = (int) map.get(field);
			if (id == code) {
				Object obj = map.get(className);
				if (obj == null) {
					return null;
				}
				Object ss = object.getClass().getDeclaredMethod("get" + className.substring(0, 1).toUpperCase() + className.substring(1)).invoke(object);
				R result = (R) ss;
				return result;
			}

		}
		return null;
	}

	/*
	 * 将list转化成set
	 */
	public static <R> Set<R> convertListToSet(List<R> list) {
		if (StringUtils.isEmptyList(list)) {
			return null;
		}
		Set<R> result = new HashSet<>();
		for (R code : list) {
			result.add(code);
		}
		return result;
	}

	/*
	 * 将字符串转化为String的list
	 */
	public static List<String> converStringToStringList(String datas, String splitName) {
		if (StringUtils.isNullOrEmpty(datas)) {
			return null;
		}
		String[] dataArray = datas.split(splitName);
		List<String> result = new ArrayList<>();
		for (String data : dataArray) {
			if (isNotNullOrEmpty(data) && result.contains(data)) {
				result.add(data);
			}
		}
		return result;
	}

	/*
	 * 将字符串转化为int的list
	 */
	public static List<Integer> converStringToIntegerList(String datas, String split) {
		if (StringUtils.isNullOrEmpty(datas)) {
			return null;
		}
		String[] dataArray = datas.split(split);
		List<Integer> result = new ArrayList<>();
		for (String data : dataArray) {
			Integer i = Integer.parseInt(data);
			if (isNotNullOrEmpty(data) && result.contains(i)) {
				result.add(i);
			}
		}
		return result;
	}

}
