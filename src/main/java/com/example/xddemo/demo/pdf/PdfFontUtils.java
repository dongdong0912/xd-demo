/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.xddemo.demo.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;


/**
 * 通用的PDF逻辑
 *
 * @author june
 * @version V1.0
 * @since 2018-11-26 09:03
 */
@Slf4j
public class PdfFontUtils {

    public static BaseFont baseFont;

    static {
        try {

            String fontPath = "/Users/xuedong/Desktop/ZYSong18030.ttf"; // 替换成实际的字体文件路径
            baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            log.error("添加ZYSong18030.ttf字体异常", e);
            throw new RuntimeException("添加ZYSong18030.ttf字体异常", e);
        }
    }


    public static final Font normalBlack6 = new Font(baseFont, 6, Font.NORMAL, BaseColor.BLACK);
    public static final Font normalRed6 = new Font(baseFont, 6, Font.NORMAL, BaseColor.RED);
    public static final Font normalBlack8 = new Font(baseFont, 8, Font.NORMAL, BaseColor.BLACK);
    public static final Font normalRed8 = new Font(baseFont, 8, Font.NORMAL, BaseColor.RED);
    public static final Font normalBlack9 = new Font(baseFont, 9, Font.NORMAL, BaseColor.BLACK);
    public static final Font normalBlack10 = new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK);
    public static final Font normalRed10 = new Font(baseFont, 10, Font.NORMAL, BaseColor.RED);
    public static final Font normalRed12 = new Font(baseFont, 12, Font.NORMAL, BaseColor.RED);
    public static final Font normalBlack12 = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
    public static final Font normalBlack14 = new Font(baseFont, 14, Font.NORMAL, BaseColor.BLACK);
    public static final Font normalBlack15 = new Font(baseFont, 15, Font.NORMAL, BaseColor.BLACK);
    public static final Font normalBlack16 = new Font(baseFont, 16, Font.NORMAL, BaseColor.BLACK);

    public static final Font boldBlack8 = new Font(baseFont, 8, Font.BOLD, BaseColor.BLACK);
    public static final Font boldRed8 = new Font(baseFont, 8, Font.BOLD, BaseColor.RED);
    public static final Font boldRed10 = new Font(baseFont, 10, Font.BOLD, BaseColor.RED);
    public static final Font boldRed12 = new Font(baseFont, 12, Font.BOLD, BaseColor.RED);
    public static final Font boldBlack10 = new Font(baseFont, 10, Font.BOLD, BaseColor.BLACK);
    public static final Font boldBlack12 = new Font(baseFont, 12, Font.BOLD, BaseColor.BLACK);
    public static final Font boldBlack14 = new Font(baseFont, 14, Font.BOLD, BaseColor.BLACK);
    public static final Font boldBlack15 = new Font(baseFont, 15, Font.BOLD, BaseColor.BLACK);
    public static final Font boldRed14 = new Font(baseFont, 14, Font.BOLD, BaseColor.RED);
    public static final Font boldBlack18 = new Font(baseFont, 18, Font.BOLD, BaseColor.BLACK);


}
