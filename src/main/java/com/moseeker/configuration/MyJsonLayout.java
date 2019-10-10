package com.moseeker.configuration;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.LayoutBase;
import com.alibaba.fastjson.JSON;
import com.cwbase.logback.AdditionalField;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyJsonLayout extends LayoutBase<ILoggingEvent> {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");
    private Pattern MDC_VAR_PATTERN = Pattern.compile("\\@\\{([^}]*)\\}");

    private String NUMBER_REGEX = "\\d+";

    List<MyAdditionalField> additionalFields;

    public void setAdditionalField(MyAdditionalField additionalField) {
        if (additionalFields == null) {
            additionalFields = new ArrayList<MyAdditionalField>();
        }
        additionalFields.add(additionalField);
    }

    @Override
    public synchronized String doLayout(ILoggingEvent event) {
        Map<String, String> mdc = event.getMDCPropertyMap();
        Map<String, Object> map = new HashMap<>();
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
        if (additionalFields != null) {
            for (MyAdditionalField field : additionalFields) {
                String value = mdcSubst(field.getValue(), mdc);
                if (StringUtils.isEmpty(value)) {
                    continue;
                }
                if (field.getIsNumber() != null && field.getIsNumber().equals(1)) {
                    if (value.matches(NUMBER_REGEX)) {
                        map.put(field.getKey(), Integer.parseInt(value));
                    }
                } else {
                    map.put(field.getKey(), value);
                }
            }
        }
        return JSON.toJSONString(map);
    }

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
}
