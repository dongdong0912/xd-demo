package com.example.xddemo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xuedong
 * Date: 2025/2/24
 */
@Data
public class StoreBO {


    /**
     * 医生姓名
     */
    @ExcelProperty(value = "医生姓名", index = 0)
    private String name;
}
