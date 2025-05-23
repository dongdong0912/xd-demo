/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.xddemo.demo;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.xddemo.demo.Constant.CHARSET;


public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> headerMap,String contentType){
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头
        setHttpHeader(headerMap, httpGet);
        String defaultContentType = ContentType.APPLICATION_FORM_URLENCODED.getMimeType();
        if (StringUtils.isNotBlank(contentType)) {
            defaultContentType = contentType;
        }
        httpGet.setHeader("Content-type", defaultContentType);
        // 设置业务参数
        CloseableHttpResponse httpResponse = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpGet);
            int httpStatus = httpResponse.getStatusLine().getStatusCode();
            System.out.println("http status code："+httpStatus);
            return EntityUtils.toString(httpResponse.getEntity(), CHARSET);
        } catch (IOException e) {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException io) {
                    System.out.printf("响应体消费异常");
                    io.printStackTrace();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    public static String doPost(String url, Map<String, String> headerMap, Map<String, String> parameters) {
        return doPost(url, headerMap, parameters, null);
    }

    public static String doPost(String url, Map<String, String> headerMap, Map<String, String> parameters, String contentType) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        setHttpHeader(headerMap, httpPost);
        if (StringUtils.isNotBlank(contentType)) {
            httpPost.setHeader("Content-type", contentType);
        }

        // 设置业务参数
        List<NameValuePair> urlParameters = new ArrayList<>();
        setNameValuePair(parameters, urlParameters);
        HttpEntity postParams = new UrlEncodedFormEntity(urlParameters, CHARSET);
        httpPost.setEntity(postParams);
        CloseableHttpResponse httpResponse = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            int httpStatus = httpResponse.getStatusLine().getStatusCode();
            System.out.println("http status code："+httpStatus);
            return EntityUtils.toString(httpResponse.getEntity(), CHARSET);
        } catch (IOException e) {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException io) {
                    System.out.printf("响应体消费异常");
                    io.printStackTrace();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    public static String doPostJson(String url, Map<String, String> headerMap, String paramJson) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        setHttpHeader(headerMap, httpPost);

        httpPost.setHeader("Content-type", ContentType.APPLICATION_JSON.getMimeType());
        // 设置业务参数
        if (StringUtils.isNotBlank(paramJson)) {
            StringEntity requestEntity = new StringEntity(paramJson, CHARSET);
            requestEntity.setContentEncoding(CHARSET.toString());
            httpPost.setEntity(requestEntity);
        }
        CloseableHttpResponse httpResponse = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            int httpStatus = httpResponse.getStatusLine().getStatusCode();
            System.out.println("http status code："+httpStatus);
            return EntityUtils.toString(httpResponse.getEntity(), CHARSET);

        } catch (IOException e) {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException io) {
                    System.out.printf("响应体消费异常");
                    io.printStackTrace();
                }
            }
        }
        return StringUtils.EMPTY;
    }



    private static void setHttpHeader(Map<String, String> headers, HttpRequest httpPost) {
        if (null == headers || headers.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 设置业务参数，value为空的参数不向下层传递
     */
    private static void setNameValuePair(Map<String, String> parameters, List<NameValuePair> urlParameters) {
        if (null == parameters || parameters.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String value = entry.getValue();
            if (StringUtils.isNotBlank(value)) {
                urlParameters.add(new BasicNameValuePair(entry.getKey(), value));
            }
        }
    }
}
