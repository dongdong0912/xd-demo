package com.example.xddemo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: xuedong
 * Date: 2023/12/12
 */
@Setter
@Getter
public class UserImportBO {

    /**
     * 医生姓名
     */
    @ExcelProperty(value = "医生姓名", index = 0)
    private String name;


    /**
     * 医生手机号码
     */
    @ExcelProperty(value = "医生手机号码", index = 1)
    private String mobile;


    /**
     * 国家医保编码
     */
    @ExcelProperty(value = "国家医保编码", index = 2)
    private String miDoctorCode;

    /**
     * 医院的国家医保编码
     */
    @ExcelProperty(value = "医院的国家医保编码", index = 3)
    private String miHospitalCode;

    /**
     * 错误原因
     */

    @ExcelProperty(value = "错误原因",index = 4)
    private String errorMsg;


}
