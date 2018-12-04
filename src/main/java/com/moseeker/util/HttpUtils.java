package com.moseeker.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.StringPool;

/**
 * @Date: 2018/8/22
 * @Author: JackYang
 */
public class HttpUtils {

    /**
     * 发送Get请求
     * @param url : 请求的连接
     * @param params ： 请求参数，无参时传null
     * @return
     */
    public static HttpResponse sendGet(String url, Map<String,String> params){
        HttpRequest request = HttpRequest.get(url).charset(StringPool.UTF_8);
        if(params!=null) {
            request.query(params);
        }
        HttpResponse response = request.send();
        return response;
    }

    /**
     * 发送Post请求-json数据
     * @param url : 请求的连接
     * @param params ：  请求参数，无参时传null
     * @return
     */
    public static HttpResponse sendPostToJson(String url,Map<String,Object> params ){
        HttpRequest request = HttpRequest.post(url).charset(StringPool.UTF_8);
        request.contentType("application/json");
        request.charset("utf-8");
        //参数详情
        if(params!=null) {
            request.body(JSON.toJSONString(params));
        }
        HttpResponse response = request.send();
        return response;
    }

    /**
     * 发送Post请求
     * @param url : 请求的连接
     * @param params ：  请求参数，无参时传null
     * @return
     */
    public static HttpResponse sendPost(String url, Map<String, Object> params ){
        HttpRequest request = HttpRequest.post(url).charset(StringPool.UTF_8);
        //参数详情
        if(params!=null) {
            request.form(params);
        }
        HttpResponse response = request.send();
        return response;
    }


    /**
     * 发送Delete请求
     * @param url : 请求的连接
     * @param params ：  请求参数，无参时传null
     * @return
     */
    public static HttpResponse sendDelete(String url,Map<String,Object> params){
        HttpRequest request = HttpRequest.delete(url).charset(StringPool.UTF_8);
        if(params!=null) {
            request.form(params);
        }
        HttpResponse response = request.send();
        return response;
    }
}
