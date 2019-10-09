package com.moseeker.configuration;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.LayoutBase;
import com.alibaba.fastjson.JSON;
import com.cwbase.logback.AdditionalField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyJsonLayout extends LayoutBase<ILoggingEvent> {

    private final int DEFAULT_SIZE = 256;
    private final int UPPER_LIMIT = 2048;
    private final static char DBL_QUOTE = '"';
    private final static char COMMA = ',';

    //private Map<String,Object> map = new HashMap<>();
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");
    private Pattern MDC_VAR_PATTERN = Pattern.compile("\\@\\{([^}]*)\\}");

    List<AdditionalField> additionalFields;

    public void setAdditionalField(AdditionalField additionalField) {
        if (additionalFields == null) {
            additionalFields = new ArrayList<AdditionalField>();
        }
        additionalFields.add(additionalField);
    }

    @Override
    public synchronized String doLayout(ILoggingEvent event) {
        Map<String, String> mdc = event.getMDCPropertyMap();
        Map<String,Object> map = new HashMap<>();
        map.put("@timestamp", df.format(new Date(event.getTimeStamp())));
        map.put("level", event.getLevel().toString());
        map.put("thread", event.getThreadName());
        map.put("logger", event.getLoggerName());
        map.put("message", event.getFormattedMessage());
        IThrowableProxy tp = event.getThrowableProxy();
        if (tp != null) {
            String throwable = ThrowableProxyUtil.asString(tp);
            map.put("throwable", throwable);
        }
        if(additionalFields != null) {
            for(AdditionalField field : additionalFields) {
                map.put(field.getKey(),mdcSubst(field.getValue(),mdc));
            }
        }
        return JSON.toJSONString(map);
    }


//    private void appendKeyValue(StringBuilder buf, String key, String value,
//                                Map<String, String> mdc) {
//        if (value != null) {
//            buf.append(DBL_QUOTE);
//            buf.append(escape(key));
//            buf.append(DBL_QUOTE);
//            buf.append(':');
//            buf.append(DBL_QUOTE);
//            buf.append(escape(mdcSubst(value, mdc)));
//            buf.append(DBL_QUOTE);
//        } else {
//            buf.append(DBL_QUOTE);
//            buf.append(escape(key));
//            buf.append(DBL_QUOTE);
//            buf.append(':');
//            buf.append("null");
//        }
//    }

    private String mdcSubst(String v, Map<String, String> mdc) {
        if (mdc != null && v != null && v.contains("@{")) {
            Matcher m = MDC_VAR_PATTERN.matcher(v);
            StringBuffer sb = new StringBuffer(v.length());
            while (m.find()) {
                String val = mdc.get(m.group(1));
                if (val == null) {
                    return "";
                }
                m.appendReplacement(sb, Matcher.quoteReplacement(val));
            }
            m.appendTail(sb);
            return sb.toString();
        }
        return v;
    }

    @Override
    public String getContentType() {
        return "application/json";
    }

//    private String escape(String s) {
//        if (s == null)
//            return null;
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//            switch (ch) {
//                case '"':
//                    sb.append("\\\"");
//                    break;
////                case '\\':
////                    sb.append("\\\\");
////                    break;
////                case '\b':
////                    sb.append("\\b");
////                    break;
////                case '\f':
////                    sb.append("\\f");
////                    break;
////                case '\n':
////                    sb.append("\\n");
////                    break;
////                case '\r':
////                    sb.append("\\r");
////                    break;
////                case '\t':
////                    sb.append("\\t");
////                    break;
////                case '/':
////                    sb.append("\\/");
////                    break;
//                default:
//                    if (ch >= '\u0000' && ch <= '\u001F') {
//                        String ss = Integer.toHexString(ch);
//                        sb.append("\\u");
//                        for (int k = 0; k < 4 - ss.length(); k++) {
//                            sb.append('0');
//                        }
//                        sb.append(ss.toUpperCase());
//                    } else {
//                        sb.append(ch);
//                    }
//            }
//        }// for
//        return sb.toString();
//    }

}
