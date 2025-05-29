package com.example.xddemo.demo;/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.example.xddemo.demo.pdf.ShSignUtils;
import com.google.common.collect.Lists;
import com.wedoctor.cloud.open.api.bean.ApiRequestHeaderDTO;
import com.wedoctor.cloud.open.api.bean.cicReportSave.req.*;
import com.wedoctor.cloud.open.api.constant.Constant;
import com.wedoctor.cloud.open.api.util.AESUtil;
import com.wedoctor.cloud.open.api.util.BeanUtil;
import com.wedoctor.cloud.open.api.util.HttpClientUtil;
import com.wedoctor.cloud.open.api.util.RequestUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cn.hutool.core.io.resource.ClassPathResource;

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



        saiKeTest();


    }


    @SneakyThrows
    public static void saiKeTest() {


        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(Constant.APPKEY, "wy7SsUiaf91828Os");
        headerMap.put(Constant.METHOD, "guahao.cic.saike.save");
        headerMap.put(Constant.TIMESTAMP, String.valueOf(Instant.now().toEpochMilli()));

        Map<String, String> parameters = new HashMap<>();


        parameters.put("req", "1iYdLwu1ExWJOsWkdmbm1adtmjIUZsXKz+Ry7gFQpvt9B/YPO6WSrY4EnYmQeENiadqCB8dexGI/qieRzqaXGuFrz6GTZS5/rx/YWdNykuvsO19yxo6DVLtUmNVrDHEqRHkaG0gOYahplTo9FFjZc+fhG6F1d/6vY7a5ag/stgt7Vq2FMdLdCm86+2StVxctJ5U4TqEAiZRMize/KO5hEuz8Ok5I1+tfkn4GXNNjG3owKuB4j1+WMKhnWxICMwldRd7pcU9mM3TKz807AdbZ+MucDfKhuibZ9Fkre/jciWrBvVj98r8XidhdltuqZi4F840sMVtyhX7TiCqsepHPyV2Tv/ReS/DmwVc5UeVr2DvTyFR/wvrO0wtA5Yv8ckzPK/ks6Y3qStN5b2Y9aELTCAH4GxWPKJQWw7fa40QmvIe86fyy8QUwVB/BfiInjNkVFnbmSc2AI8RTZl+rVcHBEfJtxHirr/9+slLpEgI5A9CPKyaHaxSipxpE8npBi229BcVCyUdBBwdxze5wKKy3TbixN6neLHY47jtn+zPoULcZS4GiaRClC92US1pNdNbNogD2oKp6c25T/MYDAn5+zDTTYPawMp91WXgvxSXbE3Zll3RcJbIhaWFgQSL+xNpEAmBo3rPgikT57QZVvJq529rcs+iLK1QQCNeBtnvNbHDSPx0zkWBmkmoKvTYXVXf/FPDMm7xDe5ueQvlTaP/tqc1IPPoUzLpWjekjDoR8KqMjqJlLb1OSm7x7iC5KHdfZiqsLEO67V4bjZM4E2Ll8Xhu/gBt3XWoT6mMQinpyTAVTm0Tre41kemmxOJhSB7T5l3Rqwa+jYLQKW+t2xNObolNf8tQ4MrghuMEdCac4Ed4/zryXnoI9m8qYnjAbA0SbWKhmKKPTPMWz9ZYnQI9CEQOAkzkzNy5R7aXW8LgfozGFU8+xiDHY2bUBtmXDRmEc+Shg0cmFFogJHm3DfgtX/lRZwvI+5+F7BYXcRcwv4pISZb11WPX/Y0pYLxGgFWi+ChV+KWlLeX8Hnk4IkK9zaxVdpdg47Ope6NKcM+87C6mVCXytXKYMhpSFHyKiZig8ixODM+6MDr6gm8lc6T6xSjqx0rTPaScBFWAZ4C/XUIwRwH4X+REKhmHUeo8RlykGaI7Q7uze15dm/AgiGl9WF0z4z9zFplqMsTMyepKBwfuXC4Eidx/s/RVORH0wJ82OOyH4zNk6J2LqdI4b5N7mP6SCsE92OhXBFXl4yLmECPGhfN5Zuu0gxw5/CaOU0EinZ8yb/Y2IUyEBYsnEqx2ML8C6sey80hoKCThdp2SH5/88ut5uJGHcLmvC7KDZQFVf77b+x8eP9rdXVcG7neSfW75faqB1iaDIGmPXfcZALvc=");

        String s = postForm("http://openapi.guahao-test.com/router/rest", headerMap, parameters, "95a57SAU37G5797811v106lC36q1Nnca", HttpClients.createDefault());
        System.out.println(s);

    }

    @SneakyThrows
    public static void saiKeTest1() {

        final String SECRET_KEY = "VN5KbyfXmvml3H1fH2cfs+Oy7YdhRu1LFe5JK0U5VjI=";

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(Constant.APPKEY, "wy7SsUiaf91828Os");
        headerMap.put(Constant.METHOD, "guahao.cic.saike.save");
        headerMap.put(Constant.TIMESTAMP, String.valueOf(Instant.now().toEpochMilli()));

        Map<String, String> parameters = new HashMap<>();

        // 资源文件路径相对于 classpath 跟目录
        ClassPathResource resource = new ClassPathResource("saike.json");

        String content = "{\"deviceType\":\"X1\",\"deviceSN\":\"X1180601000097\",\"reportType\":2,\"drugFlag\":0,\"patientName\":\"钟菲菲\",\"patientAccount\":\"b660220a4657e8b9a9c7ffe507e099dd\",\"patientGender\":2,\"patientBirthday\":\"19550403\",\"patientNation\":\"\",\"patientMobile\":\"\",\"idCardNo\":\"\",\"contactAddress\":\"\",\"occupation\":\"\",\"residenceNo\":\"\",\"smoking\":\"\",\"disease\":\"\",\"weight\":66,\"height\":166,\"inspectTime\":\"2024-08-28 11:38:56\",\"doctorAccount\":\"13007187793\",\"hospitalName\":\"\",\"doctorName\":\"黄嘉诚\",\"itemName\":\"呼气测试\",\"finalQuality\":\"综合质控结果：B级\",\"fvcQuality\":\"B\",\"fev1Quality\":\"A\",\"doctorDiag\":\"轻度限制性通气功能障碍<br>小气道功能障碍\",\"refEquations\":1,\"pdfReport\":\"http://local.xeek.cn/NewCommon/report2/index.html?accountHash=YjY2MDIyMGE0NjU3ZThiOWE5YzdmZmU1MDdlMDk5ZGQ=&who=MTMwMDM5MTkxNDk=&out_hash=NzMyODQ1NA==&order=2\",\"reportContent\":\"\",\"details\":[{\"parameter\":\"FVC\",\"unit\":\"L\",\"preValue\":\"2.459\",\"bestValue\":\"1.716\",\"bestPre\":\"69.8\",\"lln\":\"1.967\",\"zScore\":\"-2.5\",\"test1Value\":\"1.552\",\"test2Value\":\"1.566\",\"test3Value\":\"1.716\"},{\"parameter\":\"PEF\",\"unit\":\"L/s\",\"preValue\":\"6.109\",\"bestValue\":\"1.991\",\"bestPre\":\"32.6\",\"lln\":\"4.887\",\"zScore\":\"-5.0\",\"test1Value\":\"2.133\",\"test2Value\":\"2.184\",\"test3Value\":\"1.991\"},{\"parameter\":\"FEV1\",\"unit\":\"L\",\"preValue\":\"1.978\",\"bestValue\":\"0.920\",\"bestPre\":\"46.5\",\"lln\":\"1.583\",\"zScore\":\"-4.4\",\"test1Value\":\"0.920\",\"test2Value\":\"0.844\",\"test3Value\":\"0.879\"},{\"parameter\":\"FEV1/FVC\",\"unit\":\"\",\"preValue\":\"0.800\",\"bestValue\":\"0.536\",\"bestPre\":\"67.0\",\"lln\":\"0.700\",\"zScore\":\"-4.3\",\"test1Value\":\"0.593\",\"test2Value\":\"0.539\",\"test3Value\":\"0.512\"},{\"parameter\":\"FEV1/VC Max\",\"unit\":\"\",\"preValue\":\"\",\"bestValue\":\"0.536\",\"bestPre\":\"\",\"lln\":\"\",\"zScore\":\"\",\"test1Value\":\"\",\"test2Value\":\"\",\"test3Value\":\"\"},{\"parameter\":\"FEV3\",\"unit\":\"\",\"preValue\":\"\",\"bestValue\":\"1.460\",\"bestPre\":\"\",\"lln\":\"\",\"zScore\":\"\",\"test1Value\":\"1.460\",\"test2Value\":\"1.429\",\"test3Value\":\"1.410\"},{\"parameter\":\"FEV3/FVC\",\"unit\":\"\",\"preValue\":\"\",\"bestValue\":\"0.941\",\"bestPre\":\"\",\"lln\":\"\",\"zScore\":\"\",\"test1Value\":\"0.941\",\"test2Value\":\"0.913\",\"test3Value\":\"0.822\"},{\"parameter\":\"FEV6\",\"unit\":\"L\",\"preValue\":\"\",\"bestValue\":\"1.716\",\"bestPre\":\"\",\"lln\":\"\",\"zScore\":\"\",\"test1Value\":\"1.552\",\"test2Value\":\"1.566\",\"test3Value\":\"1.716\"},{\"parameter\":\"MEP\",\"unit\":\"cmH2O\",\"preValue\":\"90\",\"bestValue\":\"37\",\"bestPre\":\"41.2\",\"lln\":\"63\",\"zScore\":\"-3.2\",\"test1Value\":\"32\",\"test2Value\":\"37\",\"test3Value\":\"30\"},{\"parameter\":\"FEF25\",\"unit\":\"L/s\",\"preValue\":\"5.283\",\"bestValue\":\"0.981\",\"bestPre\":\"18.6\",\"lln\":\"3.434\",\"zScore\":\"-3.8\",\"test1Value\":\"1.094\",\"test2Value\":\"1.057\",\"test3Value\":\"0.981\"},{\"parameter\":\"FEF50\",\"unit\":\"L/s\",\"preValue\":\"3.233\",\"bestValue\":\"0.460\",\"bestPre\":\"14.2\",\"lln\":\"2.101\",\"zScore\":\"-4.0\",\"test1Value\":\"0.565\",\"test2Value\":\"0.501\",\"test3Value\":\"0.460\"},{\"parameter\":\"FEF75\",\"unit\":\"L/s\",\"preValue\":\"0.956\",\"bestValue\":\"0.158\",\"bestPre\":\"16.5\",\"lln\":\"0.621\",\"zScore\":\"-3.9\",\"test1Value\":\"0.290\",\"test2Value\":\"0.197\",\"test3Value\":\"0.158\"},{\"parameter\":\"MMEF\",\"unit\":\"L/s\",\"preValue\":\"2.145\",\"bestValue\":\"0.414\",\"bestPre\":\"19.3\",\"lln\":\"1.394\",\"zScore\":\"-3.8\",\"test1Value\":\"0.519\",\"test2Value\":\"0.478\",\"test3Value\":\"0.414\"},{\"parameter\":\"FET\",\"unit\":\"S\",\"preValue\":\"\",\"bestValue\":\"5.6\",\"bestPre\":\"\",\"lln\":\"\",\"zScore\":\"\",\"test1Value\":\"3.8\",\"test2Value\":\"4.1\",\"test3Value\":\"5.6\"},{\"parameter\":\"Vexp\",\"unit\":\"L\",\"preValue\":\"\",\"bestValue\":\"0.026\",\"bestPre\":\"\",\"lln\":\"\",\"zScore\":\"\",\"test1Value\":\"0.032\",\"test2Value\":\"0.021\",\"test3Value\":\"0.026\"},{\"parameter\":\"Vexp % FVC\",\"unit\":\"\",\"preValue\":\"\",\"bestValue\":\"1.5\",\"bestPre\":\"\",\"lln\":\"\",\"zScore\":\"\",\"test1Value\":\"2.1\",\"test2Value\":\"1.3\",\"test3Value\":\"1.5\"},{\"parameter\":\"FIVC\",\"unit\":\"L\",\"preValue\":\"2.459\",\"bestValue\":\"1.553\",\"bestPre\":\"63.2\",\"lln\":\"1.967\",\"zScore\":\"-3.0\",\"test1Value\":\"1.524\",\"test2Value\":\"1.553\",\"test3Value\":\"1.480\"},{\"parameter\":\"PIF\",\"unit\":\"L/s\",\"preValue\":\"\",\"bestValue\":\"2.798\",\"bestPre\":\"\",\"lln\":\"\",\"zScore\":\"\",\"test1Value\":\"2.534\",\"test2Value\":\"2.798\",\"test3Value\":\"2.950\"},{\"parameter\":\"MIP\",\"unit\":\"cmH2O\",\"preValue\":\"74\",\"bestValue\":\"44\",\"bestPre\":\"59.6\",\"lln\":\"52\",\"zScore\":\"-2.2\",\"test1Value\":\"38\",\"test2Value\":\"42\",\"test3Value\":\"44\"}]}";
        try (InputStream in = resource.getStream()) {
            // 使用 Hutool IoUtil 读取为 UTF-8 字符串
            //content = IoUtil.readUtf8(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        parameters.put("req", AESUtil.encrypt(JSON.toJSONString(content), SECRET_KEY));

        String s = postForm("http://openapi.guahao-test.com/router/rest", headerMap, parameters, "95a57SAU37G5797811v106lC36q1Nnca", HttpClients.createDefault());
        System.out.println(s);

    }


    public static Req param() {
        Req req = new Req();
        ReportBaseInfoBODTO reportBaseInfoBO = new ReportBaseInfoBODTO();
        reportBaseInfoBO.setProjectCode("XM000008");
        reportBaseInfoBO.setConclusion("0907薛栋测试");
        reportBaseInfoBO.setPrescriptionDoctorName("1");
        reportBaseInfoBO.setPrescriptionDoctorCode("YH000003");
        reportBaseInfoBO.setReportingDoctorName("2");
        reportBaseInfoBO.setReportingDoctorCode("YH000009");
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
            String clientSign = ShSignUtils.getSign(headerMap, RequestUtil.parseInUrlParameters(url, parameters), appSecret);
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
