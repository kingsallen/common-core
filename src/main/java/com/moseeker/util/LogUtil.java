package com.moseeker.util;

import com.alibaba.fastjson.JSON;
import com.moseeker.constant.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class LogUtil {
    private final static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    private static void debug(String serviceName, String methodName, String msg, String filter1, String filter2, Map<String, Object> extraInfo) {
        logger.debug("[{}][{}][{}][{}][{}][{}]", serviceName, methodName, filter1, filter2, msg, JSON.toJSONString(extraInfo));
    }

    private static void info(String serviceName, String methodName, String msg, String filter1, String filter2, Map<String, Object> extraInfo) {
        logger.info("[{}][{}][{}][{}][{}][{}]", serviceName, methodName, filter1, filter2, msg, JSON.toJSONString(extraInfo));
    }

    private static void warn(String serviceName, String methodName, String msg, String filter1, String filter2, Map<String, Object> extraInfo) {
        logger.warn("[{}][{}][{}][{}][{}][{}]", serviceName, methodName, filter1, filter2, msg, JSON.toJSONString(extraInfo));
    }

    private static void error(String serviceName, String methodName, String msg, String filter1, String filter2, Map<String, Object> extraInfo) {
        logger.error("[{}][{}][{}][{}][{}][{}]", serviceName, methodName, filter1, filter2, msg, JSON.toJSONString(extraInfo));
    }

    public static boolean CommonLog(LogType logType, String serviceName, String methodName, String message, String filter1, String filter2, Map<String, Object> extraInfo) {
        try {

            switch (logType) {
                case Debug:
                    debug(serviceName, methodName, message, filter1, filter2, extraInfo);
                    break;
                case Info:
                    info(serviceName, methodName, message, filter1, filter2, extraInfo);
                    break;
                case Warning:
                    warn(serviceName, methodName, message, filter1, filter2, extraInfo);
                    break;
                case Error:
                    error(serviceName, methodName, message, filter1, filter2, extraInfo);
                    break;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean CommonLog(LogType logType, String message, String filter1, String filter2, Map<String, Object> extraInfo) {
        try {
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            StackTraceElement stackTraceElement = stacks[2];
            CommonLog(logType, stackTraceElement.getClassName(), stackTraceElement.getMethodName(), message, filter1, filter2, extraInfo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean CommonLog(LogType logType, String message) {
        try {
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            StackTraceElement stackTraceElement = stacks[2];
            CommonLog(logType, stackTraceElement.getClassName(), stackTraceElement.getMethodName(), message, "", "", null);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
