package com.moseeker.util;

import java.lang.reflect.Array;
import java.util.*;

public class StringUtils {
    public StringUtils() {
    }

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
        if(obj != null && !"".equals(obj.toString()) && !"null".equals(obj.toString())) {
            String objValue = obj.toString().trim();
            return objValue;
        } else {
            return "";
        }
    }

    public static boolean isEmptyList(List list) {
        return list == null || list.size() <= 0;
    }

    public static boolean isEmptySet(Set set) {
        return set == null || set.size() <= 0;
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static String converToArrayStr(Collection<Integer> collection) {
        if(collection != null && collection.size() > 0) {
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
        if(collection != null && collection.size() > 0) {
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
        if(array != null && array.length > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            int[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
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
        if(isNullOrEmpty(str)) {
            return str;
        } else {
            String[] splitArray = str.split("_");
            StringBuilder builder = new StringBuilder();
            int index = 0;
            if(!firstUpper && splitArray[0].length() > 0) {
                builder.append(splitArray[0].substring(0, 1).toLowerCase()).append(splitArray[0].substring(1));
                ++index;
            }

            for(; index < splitArray.length; ++index) {
                if(splitArray[index].length() == 0) {
                    builder.append("_");
                } else {
                    builder.append(splitArray[index].substring(0, 1).toUpperCase()).append(splitArray[index].substring(1));
                }
            }

            return builder.toString();
        }
    }

//    public static <K, V, T extends Map<K, V>> T convertUnderKeyToCamel(T map) {
//        if(map == null) {
//            return map;
//        } else {
//            Iterator<Map.Entry<K, V>> mapIterator = map.entrySet().iterator();
//            HashMap tempMap = new HashMap();
//
//            while(mapIterator.hasNext()) {
//                Map.Entry<K, V> entry = (Map.Entry)mapIterator.next();
//                K tempKey = entry.getKey();
//                V tempValue = entry.getValue();
//                if(tempKey instanceof String) {
//                    tempKey = underLineToCamel((String)tempKey);
//                }
//
//                if(entry.getValue() == null) {
//                    tempValue = entry.getValue();
//                } else if(entry.getValue() instanceof Map) {
//                    tempValue = convertUnderKeyToCamel((Map) tempValue);
//                } else if(!(entry.getValue() instanceof List)) {
//                    if(entry.getValue().getClass().isArray()) {
//                        for(int i = 0; i < Array.getLength(entry.getValue()); ++i) {
//                            Object value = Array.get(entry.getValue(), i);
//                            if(value instanceof Map) {
//                                Array.set(entry.getValue(), i, convertUnderKeyToCamel((Map)value));
//                            }
//                        }
//                    }
//                } else {
//                    List tempList = new ArrayList();
//                    Iterator var7 = ((List)entry.getValue()).iterator();
//
//                    while(var7.hasNext()) {
//                        Object o = var7.next();
//                        if(o instanceof Map) {
//                            tempList.add(convertUnderKeyToCamel((Map)o));
//                        } else {
//                            tempList.add(o);
//                        }
//                    }
//
//                    ((List)entry.getValue()).clear();
//                    ((List)entry.getValue()).addAll(tempList);
//                }
//
//                tempMap.put(tempKey, tempValue);
//                mapIterator.remove();
//            }
//
//            map.putAll(tempMap);
//            return map;
//        }
//    }

    public static boolean lastContain(String context, String c) {
        if(isNotNullOrEmpty(context)) {
            int index = context.lastIndexOf(c);
            if(index > 0 && context.length() - c.length() == index) {
                return true;
            }
        }

        return false;
    }

    public static boolean firstContain(String context, String c) {
        if(isNotNullOrEmpty(context)) {
            int index = context.indexOf(c);
            if(index == 0 && context.contains(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isJsonNullOrEmpty(Object obj) {
        return obj instanceof String?!org.apache.commons.lang.StringUtils.isNotBlank((String)obj) || obj.equals("[]") || obj.equals("{}") || obj.equals("null"):obj == null;
    }

    public static String humpName(String strName) {
        String[] strs = strName.split("_");
        if(strs.length <= 1) {
            return strName;
        } else {
            String name = strs[0];

            for(int i = 1; i < strs.length; ++i) {
                name = name + strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
            }

            return name;
        }
    }

    public static String underscoreName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if(camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());

            for(int i = 1; i < camelCaseName.length(); ++i) {
                char ch = camelCaseName.charAt(i);
                if(Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }

    public static Map<String, Object> underscoreNameMap(Map<String, Object> resume) {
        if(resume != null && !resume.isEmpty()) {
            Map<String, Object> result = new HashMap();
            Iterator var2 = resume.keySet().iterator();

            while(true) {
                while(var2.hasNext()) {
                    String key = (String)var2.next();
                    String newKey = underscoreName(key);
                    if(isNullOrEmpty(newKey)) {
                        newKey = key;
                    }

                    if(resume.get(key) == null) {
                        result.put(newKey, resume.get(key));
                    } else if(resume.get(key) instanceof Map) {
                        Map<String, Object> map = (Map)resume.get(key);
                        Map<String, Object> result1 = new HashMap();
                        if(map != null && !map.isEmpty()) {
                            result1 = underscoreNameMap(map);
                        }

                        result.put(newKey, result1);
                    } else {
                        Object value;
                        if(resume.get(key) instanceof List) {
                            List<Object> list = (List)resume.get(key);
                            List tempList = new ArrayList();
                            if(!isEmptyList(list)) {
                                Iterator var14 = list.iterator();

                                while(var14.hasNext()) {
                                    value = var14.next();
                                    if(value instanceof Map) {
                                        tempList.add(underscoreNameMap((Map)value));
                                    } else {
                                        tempList.add(value);
                                    }
                                }
                            }

                            result.put(newKey, tempList);
                        } else if(!resume.get(key).getClass().isArray()) {
                            result.put(newKey, resume.get(key));
                        } else {
                            int length = Array.getLength(resume.get(key));
                            if(length != 0) {
                                result.put(newKey, new Object[1]);
                            } else {
                                Object[] arr = new Object[length];

                                for(int i = 0; i < Array.getLength(resume.get(key)); ++i) {
                                    value = Array.get(resume.get(key), i);
                                    if(value instanceof Map) {
                                        Map<String, Object> result2 = underscoreNameMap((Map)value);
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
        if(isNotNullOrEmpty(value)) {
            if(value.contains("/")) {
                value = value.replaceAll("/", " ");
            }

            if(value.contains("OR")) {
                value = value.replaceAll("OR", " ");
            }

            if(value.contains("AND")) {
                value = value.replaceAll("AND", " ");
            }

            if(value.contains("(")) {
                value = value.replaceAll("\\(", " ");
            }

            if(value.contains(")")) {
                value = value.replaceAll("\\)", " ");
            }

            if(value.contains("+")) {
                value = value.replaceAll("\\+", " ");
            }

            if(value.contains("\\")) {
                value = value.replaceAll("\\\\", " ");
            }

            if(value.contains("（")) {
                value = value.replaceAll("（", " ");
            }

            if(value.contains("）")) {
                value = value.replaceAll("）", " ");
            }

            if(value.contains("-")) {
                value = value.replaceAll("-", " ");
            }

            if(value.contains("&")) {
                value = value.replaceAll("&", " ");
            }

            if(value.contains("+")) {
                value = value.replaceAll("\\+", " ");
            }

            if(value.contains("-")) {
                value = value.replaceAll("-", " ");
            }

            if(value.contains("|")) {
                value = value.replaceAll("|", " ");
            }

            if(value.contains("!")) {
                value = value.replaceAll("!", " ");
            }

            if(value.contains("{")) {
                value = value.replaceAll("\\{", " ");
            }

            if(value.contains("}")) {
                value = value.replaceAll("\\}", " ");
            }

            if(value.contains("^")) {
                value = value.replaceAll("\\^", " ");
            }

            if(value.contains("\"")) {
                value = value.replaceAll("\"", " ");
            }

            if(value.contains("~")) {
                value = value.replaceAll("~", " ");
            }

            if(value.contains("*")) {
                value = value.replaceAll("\\*", " ");
            }

            if(value.contains("?")) {
                value = value.replaceAll("\\?", " ");
            }

            if(value.contains(":")) {
                value = value.replaceAll(":", " ");
            }

            if(value.contains("'")) {
                value = value.replaceAll("'", " ");
            }

            if(value.contains("@")) {
                value = value.replaceAll("@", " ");
            }

            if(value.contains("%")) {
                value = value.replaceAll("%", " ");
            }

            if(value.contains("$")) {
                value = value.replaceAll("\\$", " ");
            }

            if(value.contains("#")) {
                value = value.replaceAll("#", " ");
            }

            if(value.contains("=")) {
                value = value.replaceAll("=", " ");
            }

            if(isNotNullOrEmpty(value)) {
                value = value.trim();
            }
        }

        return value;
    }

    public static boolean isEmtryOrZore(Integer i){
        if(i == null || i <= 0){
            return true;
        }
        return false;
    }
}
