package com.example.xddemo.demo;

import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * sql解析学习
 * Author: xuedong
 * Date: 2024/1/2
 */
public class SqlDemo {

    public static void main(String[] args) throws Exception {

        String url="asa";

        url="三大事";


        URL urlObj = new URL("http://10.60.10.19/local-kano/MX2449313?token=V1.0_dGFtdFJUT1hEK21aNHR4TjZoMGxQQT09X1RJTUVfQUVTCOUSTOM");

        try (InputStream inputStream = urlObj.openStream()) {
        }
        String fileName = URLEncoder.encode(urlObj.getFile() + ".pdf", "UTF-8");
        System.out.println(url);

    }


}
