/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.xddemo.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.xddemo.SignUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.xddemo.demo.Constant.*;


/**
 * 微医开放平台V2版本调用demo
 */
@Data
public class APIDemoV2Test {
    /**
     * 具体业务参数
     *
     * host
     * 测试环境：
     * 1. 开放体系统一访问路径
     * http://openapi.guahao-test.com/openapi  对应测试环境的AccessKey ID/AccessKey Secret
     *
     *
     * 生产环境：
     * 1. 开放体系统一访问路径
     * http://openapi.guahao.com/openapi  对应生产环境的AccessKey ID/AccessKey Secret
     *
     * TODO 请补充填入
     *
     */
    private static String host = "http://openapi.guahao-test.com/openapi";
    /**
     * 访问凭证，也就是微医云控制台“权限管理-人员权限管理”菜单下针对子账号的设置的访问凭证的AccessKey ID
     * TODO 请补充填入
     */
    private static String appKey = "";
    /**
     * AccessKey ID 对应的AccessKey Secret
     * TODO 请补充填入
     */
    private static String appSecret = "";
    /**
     * 微医云控制台“权限管理-人员权限管理”菜单下针对子账号的角色管理中“API预定义角色”或“API自定义角色”中关联API的id
     *  TODO 请补充填入
     */
    private static String method = "guahao.ai.eyeground.push";
    /**
     * 版本信息，此处填入2.0即可
     */
    private static String version = "2.0";
    /**
     *  version为2.0时必须：调用的API归属产品对应的产品编码
     *  微医云控制台“权限管理-人员权限管理”菜单下针对子账号的角色管理中“API预定义角色”或“API自定义角色”中关联API的所属产品对应的产品编码
     *   TODO 请补充填入
     */
    private static String productCode = "";
    /**
     *  version为2.0时必须：请求标识唯一id，长度不超过36位；用户须保证不同请求的唯一性
     */
    private static String messageId = UUID.randomUUID().toString();


    /**
     * 以请求参数为例：
     * {
     *     "queryParam":{
     *         "pageNumber":"1",
     *         "pageSize":10
     *     }
     * }
     *
     * 提供两种方式的样例，可任选一种进行，推荐使用Json方式请求
     */
    public static void main(String[] args) {
        /**
         * 以请求参数为例：
         * {
         *     "queryParam":{
         *         "pageNumber":"1",
         *         "pageSize":10
         *     }
         * }
         */
        // 示例1. Content-type为默认的application/json;/
       postJSON(host,buildHeader(),buildJsonParams(),appSecret);

        // 示例2. Content-type为默认的application/x-www-form-urlencoded;
//        postForm(host,buildHeader(),buildFromParams(),appSecret);

        // 示例3. GET 请求
//        getMethod(host,buildHeader(),appSecret);
    }



    private static Map<String,String> buildHeader(){
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(APPKEY, appKey);
        headerMap.put(METHOD, method);
        headerMap.put(TIMESTAMP, String.valueOf(Instant.now().toEpochMilli()));
        headerMap.put(VERSION, version);
        headerMap.put(PRODUCT_CODE, productCode);
        headerMap.put(MESSAGE_ID, messageId);
        System.out.println("header:"+JSON.toJSONString(headerMap));
        return headerMap;
    }

    /**
     * 以请求参数为例：
     * {
     *     "queryParam":{
     *         "pageNumber":"1",
     *         "pageSize":10
     *     }
     * }
     * @return
     */
    private static Map<String,String> buildFromParams(){
        Map<String, String>  queryParam = new HashMap<>();
        queryParam.put("pageNumber", "1");
        queryParam.put("pageSize", "10");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("queryParam", JSON.toJSONString(queryParam));
        System.out.println("params:"+JSON.toJSONString(parameters));
        return parameters;
    }

    private static Map<String,String> buildUploadFileNoFileParams(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("orginFileName", "wedoctor.jpg");
        parameters.put("bizCode", "rz-platform-upload");
        parameters.put("bizUnuiqId", "");
        return parameters;
    }

    /**
     * 以请求参数为例：
     * {
     *     "queryParam":{
     *         "pageNumber":"1",
     *         "pageSize":10
     *     }
     * }
     * @return
     */
    private static String buildJsonParams(){
        Map<String, String>  queryParam = new HashMap<>();
        queryParam.put("pageNumber", "1");
        queryParam.put("pageSize", "10");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("queryParam", queryParam);
        String jsonParams = jsonObject.toJSONString();
        System.out.println("jsonParams:"+JSON.toJSONString(jsonParams));
        return jsonParams;
    }

    private static void postForm(String url, Map<String, String> headerMap, Map<String, String> parameters, String appSecret) {
        try{
            String clientSign = SignUtil.getSign(headerMap, parseInUrlParameters(url,parameters), appSecret);
            System.out.println("sign:"+ clientSign);
            headerMap.put(SIGN, clientSign);
            String result = HttpClientUtil.doPost(url, headerMap, parameters);
            System.out.println("result:"+result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void getMethod(String url, Map<String, String> headerMap, String appSecret) {
        try{
            String clientSign = SignUtil.getSign(headerMap, parseInUrlParameters(url,null), appSecret);
            System.out.println("sign:"+ clientSign);
            headerMap.put(SIGN, clientSign);
            String result = HttpClientUtil.doGet(url, headerMap, null);
            System.out.println("result:"+result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void postJSON(String url, Map<String, String> headerMap, String json, String appSecret){
        try {
            if (null != json && !json.isEmpty()) {
                byte[] infoMd5 = SignUtil.encryptMD5(json.getBytes("UTF-8"));
                String contentMD5 = SignUtil.byte2hex(infoMd5);
                headerMap.put(CONTENT_MD5, contentMD5);
            }
            String clientSign = SignUtil.getSign(headerMap, parseInUrlParameters(url,null), appSecret);
            System.out.println("sign:"+ clientSign);
            headerMap.put(SIGN, clientSign);
            String result = HttpClientUtil.doPostJson(url, headerMap, json);
            System.out.printf("result:" + result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static Map<String,String> parseInUrlParameters(String url, Map<String, String> parameters) {
        if (StringUtils.isBlank(url)) {
            return parameters;
        }
        url = url.trim();
        String[] urlParts = url.split("\\?");
        //没有参数
        if (urlParts.length == 1) {
            return parameters;
        }
        //有参数
        String[] params = urlParts[1].split("&");
        Map<String, String> result = new HashMap<>();
        for (String param : params) {
            String[] keyValue = param.split("=");
            result.put(keyValue[0], keyValue[1]);
        }
        if (null != parameters && !parameters.isEmpty()){
            result.putAll(parameters);
        }
        return result;
    }

}
