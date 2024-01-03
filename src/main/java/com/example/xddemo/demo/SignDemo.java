package com.example.xddemo.demo;

import com.google.common.collect.Maps;
import com.wedoctor.cloud.open.api.util.SignUtil;

import java.util.Map;

/**
 * openapi对外接口的签名验证
 * Author: xuedong
 * Date: 2023/12/13
 */
public class SignDemo {


    public static void main(String[] args) throws Exception {

        Map<String, String> headers = Maps.newHashMap();
        headers.put("appkey", "wyX49oeiAM15431D");
        headers.put("method", "guahao.cic.report.save");
        headers.put("message-id", "3bdf8368-359f-0452-d558-51950557ada2");
        headers.put("timestamp", "1701076532");
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("req", "{\"reportBaseInfoBO\":{\"projectCode\":\"XM000013\",\"reportingDoctorCode\":\"YH000006\",\"reportingDoctorName\":\"测试\",\"reviewDate\":\"2023-11-27 15:47:55\",\"prescriptionDoctorCode\":\"YH000005\",\"conclusion\":\"1、窦性心律\\\\r\\\\n2、频发房性早搏，部分成对，部分呈二联律、三联律，部分呈短阵房性心动过速,\\\\r\\\\n3、偶发室性早搏\\\\r\\\\n4、ST－T改变\\\\r\\\\n5、心率变异分析：下降（SDNN＜100ms）\"},\"peopleIdentifierBO\":{\"name\":\"张凯\",\"gender\":1,\"idCardNo\":\"411381199706203032\"},\"healthIndicatorBO\":{\"attachment\":[{\"code\":\"holter\",\"values\":[\"http://jstsvc.beneware.cn:8022/REPORT_DATA/2/18/06/H1806081SZJTY_1_0D9A32B4/H1806081SZJTY_1.pdf\"]}]}}");
        String appSecret = "aQF76nwpH94Py32Y643G36ru8ol18m1a";
        String sign = SignUtil.getSign(headers, parameters, appSecret);
        System.out.println(sign);


    }
}
