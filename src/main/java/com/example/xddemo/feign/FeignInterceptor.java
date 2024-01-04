package com.example.xddemo.feign;

/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

/**
 * @author xuedong
 * @version V1.0
 * @since 2019-04-24 17:00
 */
@Configuration
@Slf4j
public class FeignInterceptor implements RequestInterceptor {


    private static final String POST_REQUEST_METHOD = "POST";
    private static final String GET_REQUEST_METHOD = "GET";
    private static final String CHARSET = "utf-8";
    private static final String URL_JOIN = "?";
    private static final String PARAM_JOIN = "&";
    private static final String PARAM_EVALUATE = "=";
    private static final String NEW_LINE = "\n";

    @Override
    public void apply(RequestTemplate template) {

        handleGet(template);
        handlePost(template);
    }

    private void handlePost(RequestTemplate template) {

        if (!Objects.equals(template.method(), POST_REQUEST_METHOD)) {
            return;
        }
        if (Objects.isNull(template.body())) {
            return;
        }
        String requestBody = StringUtils.toEncodedString(template.body(), Charset.forName(CHARSET));
        String param = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(requestBody)) {
            param = requestBody.replace(NEW_LINE, StringUtils.EMPTY);
        }

    }

    private void handleGet(RequestTemplate template) {

        if (!Objects.equals(template.method(), GET_REQUEST_METHOD)) {
            return;
        }

        if (Objects.isNull(template.body())) {
            return;
        }
        String param = StringUtils.toEncodedString(template.body(), Charset.forName(CHARSET));
        Map<String, Object> paramMap = JSON.parseObject(param, new TypeReference<Map<String, Object>>() {
        });
        template.append(getParam(paramMap));
        template.body(null, Charset.forName(CHARSET));

    }

    private String getParam(Map<String, Object> paramMap) {
        if (MapUtils.isEmpty(paramMap)) {
            return StringUtils.EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry entry : paramMap.entrySet()) {
            if (Objects.nonNull(entry.getValue())) {
                stringBuilder.append(entry.getKey()).append(PARAM_EVALUATE).append(entry.getValue()).append(PARAM_JOIN);
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return URL_JOIN + stringBuilder;
    }

}

