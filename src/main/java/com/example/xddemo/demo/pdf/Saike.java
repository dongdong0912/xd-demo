package com.example.xddemo.demo.pdf;

import com.wedoctor.cloud.open.api.bean.ApiRequest;
import lombok.Data;

/**
 * @author xuedong
 * Date: 2025/5/26
 */
@Data
public class Saike extends ApiRequest {


    private String req;

    public Saike() {
        super("guahao.cic.saike.save" , "https://openapi.guahao-test.com/openapi" , "1xqS469X9");
    }
}
