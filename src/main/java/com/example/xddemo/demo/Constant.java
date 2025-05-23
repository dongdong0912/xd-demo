/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.xddemo.demo;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class Constant {
    /**
     * 访问凭证，也就是微医云控制台“权限管理-人员权限管理”菜单下针对子账号的设置的访问凭证的AccessKey ID
     */
    public static final String APPKEY = "appkey";
    /**
     * 调用的API接口服务名称
     */
    public static final String METHOD = "method";
    /**
     * 格式为Unix时间戳、单位毫秒
     */
    public static final String TIMESTAMP = "timestamp";
    /**
     * 版本，目前FORM表单报文使用1.0，JSON报文使用2.0，若为空时默认按照1.0也就是FORM表单报文进行解析
     */
    public static final String VERSION = "version";
    /**
     * version为2.0时必须：调用的API归属产品对应的产品编码
     */
    public static final String PRODUCT_CODE = "product-code";
    /**
     * version为2.0时必须：请求标识唯一id，长度不超过36位；用户须保证不同请求的唯一性
     */
    public static final String MESSAGE_ID = "message-id";
    /**
     * version为2.0时，且请求体为JSON报文并且JSON报文不为空时，对JSON报文体数据进行md5并转化为32为大写的字符串，用于加签时使用
     */
    public static final String CONTENT_MD5 = "content-md5";
    /**
     * 不参与签名，签名字段
     */
    public static final String SIGN = "sign";
    /**
     * AccessKey ID对应的AccessKey Secret
     */
    public static final String APPSECRET = "appsecret";

    public static final List<String> SIGN_HEAD_LIST = Arrays.asList(APPKEY,METHOD,TIMESTAMP,VERSION,PRODUCT_CODE,MESSAGE_ID,CONTENT_MD5);


    /**
     * 编码格式：utf-8
     */
    public static final Charset CHARSET = Charset.forName("UTF-8");

}
