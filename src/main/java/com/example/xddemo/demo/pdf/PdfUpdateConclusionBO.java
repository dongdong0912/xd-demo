package com.example.xddemo.demo.pdf;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: xuedong
 * Date: 2023/12/6
 */
@Data
public class PdfUpdateConclusionBO {

    /**
     * 原始pdf文件
     */
    private MultipartFile file;
    /**
     * 标题
     */
    private String title;
    /**
     * 结论列表
     */
    private List<String> contentList;
    /**
     * 专家的签名图片
     */
    private String signUrl;

    /**
     * 签名医生姓名
     */
    private String doctorName;
    /**
     * 自定义抬头
     */
    private String customTitle;
}
