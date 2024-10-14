package com.example.xddemo.demo;/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.collect.Lists;
import com.wedoctor.cloud.open.api.bean.ApiRequestHeaderDTO;
import com.wedoctor.cloud.open.api.bean.cicReportSave.req.*;
import com.wedoctor.cloud.open.api.bean.cicReportSave.resp.CicReportSaveRes;
import com.wedoctor.cloud.open.api.constant.Constant;
import com.wedoctor.cloud.open.api.util.BeanUtil;
import com.wedoctor.cloud.open.api.util.HttpClientUtil;
import com.wedoctor.cloud.open.api.util.RequestUtil;
import com.wedoctor.cloud.open.api.util.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.wedoctor.cloud.open.api.constant.Constant.SIGN;

/**
 * openapi对外测试
 */
public class APIClientV1Demo {
    private String appSecret;
    private String appKey;
    private String accessToken;
    private String openToken;
    private CloseableHttpClient httpClient;
    private String reqUrl;
    private String routerTag;
    private String method;


    public APIClientV1Demo(String appKey, String appSecret, String reqUrl) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.reqUrl = reqUrl;
    }

    public static void main(String[] args) {
        APIClientV1Demo clientV1Demo = new APIClientV1Demo("wyX49oeiAM15431D", "aQF76nwpH94Py32Y643G36ru8ol18m1a", "https://openapi.guahao-test.com/router/rest");
        CicReportSaveReq cicReportSaveReq = new CicReportSaveReq();
        cicReportSaveReq.setReq(param());
        cicReportSaveReq.setApiMethod("guahao.cic.report.save");
        cicReportSaveReq.setApiReqUrl("https://openapi.guahao-test.com/router/rest");
        cicReportSaveReq.setApiProductCode("1xqS469X9");
        CicReportSaveRes res = clientV1Demo.execute(cicReportSaveReq, new TypeReference<CicReportSaveRes>() {
        });
        System.out.println(JSON.toJSONString(res));
    }


    public static Req param() {
        Req req = new Req();
        ReportBaseInfoBODTO reportBaseInfoBO = new ReportBaseInfoBODTO();
        reportBaseInfoBO.setProjectCode("XM000013");
        reportBaseInfoBO.setConclusion("0907薛栋测试");
        reportBaseInfoBO.setPrescriptionDoctorName("1");
        reportBaseInfoBO.setPrescriptionDoctorCode("YH000005");
        reportBaseInfoBO.setReportingDoctorName("2");
        reportBaseInfoBO.setReportingDoctorCode("YH000006");
        reportBaseInfoBO.setReviewDate(new Date());
        reportBaseInfoBO.setExtId("022866666");

        req.setReportBaseInfoBO(reportBaseInfoBO);
        PeopleIdentifierBODTO peopleIdentifierBO = new PeopleIdentifierBODTO();
        peopleIdentifierBO.setName("薛栋1");
        peopleIdentifierBO.setGender(String.valueOf(1));
        peopleIdentifierBO.setIdCardNo("610581199906123178");
        peopleIdentifierBO.setPhoneNumber("13007187798");
        peopleIdentifierBO.setPresentIllness(null);
        peopleIdentifierBO.setPastIllness(null);
        req.setPeopleIdentifierBO(peopleIdentifierBO);


        HealthIndicatorBODTO healthIndicatorBO = new HealthIndicatorBODTO();
        healthIndicatorBO.setDeviceModel("");
        healthIndicatorBO.setBluetoothConnectCode("");
        healthIndicatorBO.setPhoneModel("");
        healthIndicatorBO.setSoftwareVersion("");
        healthIndicatorBO.setBloodSugar(7.8D);
        healthIndicatorBO.setMeasureTime("20211023T133245");
        healthIndicatorBO.setBloodPressureMeasureTimeLabel(0);

        AttachmentDTO attachmentBO = new AttachmentDTO();
        // byte[] bytes = FileUtil.readBytes("/Users/xuedong/Desktop/kr_temp_report.pdf");
        // String encode = cn.hutool.core.codec.Base64.encode(bytes);
        // attachmentBO.setFileContent(encode);
        attachmentBO.setFileName("123薛是多少大是多少栋.pdf");
        attachmentBO.setCode("bioLiverFunc");

        AttachmentDTO attachmentBO1 = new AttachmentDTO();
        attachmentBO1.setCode("diabeticRetinopathy");
        attachmentBO1.setValues(Lists.newArrayList("http://kano.guahao-test.com/Gs244324915"));
        healthIndicatorBO.setAttachment(Lists.newArrayList(attachmentBO1));
        healthIndicatorBO.setSource("cic");
        req.setHealthIndicatorBO(healthIndicatorBO);
        return req;
    }

    public static <T> String requestApi(ApiRequestHeaderDTO headerDTO, T requestBody) {
        try {
            if (null == headerDTO) {
                throw new NullPointerException("accessDTO is null ");
            }
            if (StringUtils.isBlank(headerDTO.getAppKey()) || StringUtils.isBlank(headerDTO.getAppSecret())) {
                throw new NullPointerException("appSecret is null or appKey is null ");
            }
            String bodyStr = null;
            Map<String, String> parameters = null;
            if (null != requestBody) {
                bodyStr = toUnderline(JSON.toJSONString(requestBody));
                parameters = JSON.parseObject(bodyStr, new TypeReference<HashMap<String, String>>() {
                });

            }
            return postForm(headerDTO.getUrl(), buildHeader(headerDTO), parameters, headerDTO.getAppSecret(), headerDTO.getHttpClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String postForm(String url, Map<String, String> headerMap, Map<String, String> parameters, String appSecret, CloseableHttpClient httpClient) throws Exception {
        try {
            String clientSign = SignUtil.getSign(headerMap, RequestUtil.parseInUrlParameters(url, parameters), appSecret);
            headerMap.put(SIGN, clientSign);
            return HttpClientUtil.doPost(url, headerMap, parameters, httpClient);
        } catch (Exception e) {
            throw new Exception("request is fail:" + e.getMessage());
        }
    }

    private static Map<String, String> buildHeader(ApiRequestHeaderDTO headerDTO) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(Constant.APPKEY, headerDTO.getAppKey());
        headerMap.put(Constant.METHOD, headerDTO.getMethod());
        headerMap.put(Constant.TIMESTAMP, String.valueOf(Instant.now().toEpochMilli()));
        headerMap.put(Constant.MESSAGE_ID, UUID.randomUUID().toString());
        headerMap.put(Constant.ACCESS_TOKEN, headerDTO.getAccessToken());
        headerMap.put(Constant.OPEN_TOKEN, headerDTO.getOpenToken());
        if (StringUtils.isNotBlank(headerDTO.getRouterTag())) {
            headerMap.put(Constant.ROUTER_TAG, headerDTO.getRouterTag());
        }
        return headerMap;
    }

    /**
     * __转-
     *
     * @param sourceStr 原str
     * @return 转换后的str
     */
    private static String toUnderline(String sourceStr) {
        if (StringUtils.isBlank(sourceStr) || !sourceStr.contains("__")) {
            return sourceStr;
        }
        return sourceStr.replaceAll("__", "-");
    }

    public <T> T execute(Object paramObject, TypeReference<T> paramTypeReference) {
        String str = execute(paramObject);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return JSON.parseObject(str, paramTypeReference, new Feature[0]);
    }

    public String execute(Object paramObject) {
        try {
            ApiRequestHeaderDTO localApiRequestHeaderDTO = new ApiRequestHeaderDTO();
            localApiRequestHeaderDTO.setAppKey(getAppKey());
            localApiRequestHeaderDTO.setAppSecret(getAppSecret());
            localApiRequestHeaderDTO.setAccessToken(getAccessToken());
            localApiRequestHeaderDTO.setOpenToken(getOpenToken());
            localApiRequestHeaderDTO.setHttpClient(getHttpClient());
            localApiRequestHeaderDTO.setRouterTag(getRouterTag());
            localApiRequestHeaderDTO.setUrl(!StringUtils.isBlank(getReqUrl()) ? getReqUrl() : BeanUtil.getSuperString(Constant.APIREQURL, paramObject));
            localApiRequestHeaderDTO.setMethod(!StringUtils.isBlank(getMethod()) ? getMethod() : BeanUtil.getSuperString(Constant.APIMETHOD, paramObject));
            return requestApi(localApiRequestHeaderDTO, paramObject);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenToken() {
        return openToken;
    }

    public void setOpenToken(String openToken) {
        this.openToken = openToken;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getRouterTag() {
        return routerTag;
    }

    public void setRouterTag(String routerTag) {
        this.routerTag = routerTag;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
