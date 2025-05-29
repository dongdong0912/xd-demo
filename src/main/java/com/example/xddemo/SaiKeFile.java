package com.example.xddemo;

import cn.hutool.http.HttpUtil;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author xuedong
 * Date: 2025/5/27
 */
public class SaiKeFile {




    public static void main(String[] args) {
       // String html = "<html><body><h1>Hello PDF</h1><p>This is a paragraph.</p></body></html>";

        String url = "https://demo.xeek.cn/NewCommon/report2/index.html?accountHash=NWVmNzIyOTdmMzNhYjIyY2VmOTJjMDkzYTE2ZDUwZjI=&who=admin8&order=2";
        String html = HttpUtil.get(url);

        System.out.println(html);

        String outputPath = "/Users/xuedong/Desktop/saike.pdf";

        try (OutputStream os = new FileOutputStream(outputPath)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
            System.out.println("PDF created: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
