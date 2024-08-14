package com.example.xddemo.demo.pdf;


import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 硅基pdf处理类
 * Author: xuedong
 * Date: 2023/12/6
 */
@Slf4j
public class GuiJiPdfManagerImpl {


    private static byte[] addConclusionPageAndSign(byte[] pdfBytes, PdfUpdateConclusionBO bo) {

        PdfReader reader = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            reader = new PdfReader(pdfBytes);
            addContent(reader, bos, bo);
            return bos.toByteArray();
        } catch (Exception e) {
            log.warn("硅基pdf指定位置插入文本异常", e);
        } finally {
            if (Objects.nonNull(reader)) {
                reader.close();
            }
        }
        return null;
    }

    public static void addContent(PdfReader reader, ByteArrayOutputStream bos, PdfUpdateConclusionBO bo) {
        try {
            int numPages = reader.getNumberOfPages();
            // 创建 PdfStamper 对象来修改现有 PDF 文件并添加新的 PDF 页面
            PdfStamper stamper = new PdfStamper(reader, bos);
            // 获取 PDF 的最后一页
            PdfImportedPage page = stamper.getImportedPage(reader, numPages);
            // 在原始 PDF 的末尾添加最后一页
            stamper.insertPage(numPages + 1, reader.getPageSize(numPages));
            PdfContentByte contentByte = stamper.getUnderContent(numPages + 1);
            contentByte.addTemplate(page, 0, 0);
            for (int i = 0; i < numPages; i++) {
                PdfContentByte pdfContentByte = stamper.getOverContent(i + 1);
                fill(pdfContentByte);
            }
            fillColor(contentByte);
            Phrase signaturePhrase = new Phrase("专家阅片结论", PdfFontUtils.boldBlack12);
            ColumnText.showTextAligned(contentByte, Element.ALIGN_CENTER, signaturePhrase, 110, 1050, 0);
            Paragraph paragraph = new Paragraph();
            List<String> contentList = bo.getContentList();
            if (CollectionUtils.isEmpty(contentList)) {
                return;
            }
            for (int i = 0; i < contentList.size(); i++) {
                if (!Objects.equals(0, i)) {
                    paragraph.add(Chunk.NEWLINE);
                }
                paragraph.add(new Chunk((i + 1) + "、" + contentList.get(i)));
            }
            paragraph.setFont(PdfFontUtils.normalBlack10);
            ColumnText columnText = new ColumnText(contentByte);
            columnText.setSimpleColumn(paragraph, 80, 600, 790, 1040, 0, Element.ALIGN_LEFT);
            columnText.addElement(paragraph);
            columnText.go();
            insertReportSignBO(contentByte, bo);
            Rectangle rectangle = new Rectangle(80, 870, 800, 1070);
            rectangle.setBackgroundColor(new BaseColor(244, 250, 250, 70));
            contentByte.rectangle(rectangle);
            // 关闭资源
            stamper.close();
            reader.close();
        } catch (Exception e) {
            log.error("硅基生成报告失败", e);
        }
    }

    private static void fillColor(PdfContentByte contentByte) {
        contentByte.saveState();
        contentByte.setColorFill(BaseColor.WHITE);
        contentByte.rectangle(0, 0, 800, 1095);
        contentByte.rectangle(900, 1100, 10, 10);
        contentByte.rectangle(720, 1120, 60, 30);
        contentByte.fill();
        contentByte.restoreState();
    }


    private static void insertReportSignBO(PdfContentByte over, PdfUpdateConclusionBO bo) {
        try {
            //日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //签名文字
            String signatureText = "签名:";
            if (StringUtils.isNotBlank(bo.getDoctorName())) {
                signatureText = signatureText + StringUtils.EMPTY + bo.getDoctorName();
            }
            //日期文字
            String dateText = "日期:" + dateFormat.format(new Date());
            // 计算文字绘制的位置
            float x = 520;
            float y = 900;
            if (StringUtils.isNotBlank(bo.getSignUrl())) {
                Image image = Image.getInstance(new URL(bo.getSignUrl()));
                image.scaleAbsolute(60, 60);
                image.setAbsolutePosition(550, 880);
                over.addImage(image);
            }
            //添加签名文字
            Phrase signaturePhrase = new Phrase(signatureText, PdfFontUtils.boldBlack10);
            ColumnText.showTextAligned(over, Element.ALIGN_LEFT, signaturePhrase, x, y, 0);
            //添加日期文字
            Phrase datePhrase = new Phrase(dateText, PdfFontUtils.boldBlack10);
            ColumnText.showTextAligned(over, Element.ALIGN_LEFT, datePhrase, x + 100, y, 0);
        } catch (Exception e) {
            log.warn("硅基添加签名异常", e);
        }
    }

    private static void fill(PdfContentByte contentByte) {
        contentByte.saveState();
        contentByte.setColorFill(BaseColor.WHITE);  //遮挡层颜色：白色
        contentByte.rectangle(720, 1120, 60, 30);
        contentByte.fill();
        contentByte.restoreState();
    }


    public static void main(String[] args) {

        // 指定 PDF 文件路径
        String filePath = "/Users/xuedong/Desktop/血糖.pdf";
        String outPath = "/Users/xuedong/Desktop/201.pdf";


        byte[] bytes = FileUtil.readBytes(filePath);

        PdfUpdateConclusionBO p = JSONObject.parseObject("{\"reportId\": \"1805075864745017346\", \"saveFlag\": 1, \"conclusionList\": [\"在本次血糖检测中，您的空腹血糖水平为[具体值]，餐后两小时血糖水平为[具体值]。根据这些结果，您的血糖水平处于[正常/异常]范围内。空腹血糖应在70-99 mg/dL之间，餐后两小时血糖应低于140 mg/dL。如果高于这些范围，可能有糖尿病或前驱糖尿病的风险。若您的血糖水平高于正常，建议尽快咨询医生。控制血糖的关键在于健康的生活方式，包括均衡饮食、定期运动和监测血糖。避免高糖、高脂肪食物，多摄入蔬菜、水果和全谷物，保持适当的体重和定期活动，有助于血糖管理。若血糖在正常范围内，仍需保持健康生活方式，定期监测血糖。高血糖和低血糖都可能对身体造成影响，因此及时管理血糖水平很重要。总结而言，无论检测结果如何，积极生活方式和定期体检是保障健康的关键。\", \"在本次血糖检测中，您的空腹血糖水平为[具体值]，餐后两小时血糖水平为[具体值]。根据这些结果，您的血糖水平处于[正常/异常]范围内。空腹血糖应在70-99 mg/dL之间，餐后两小时血糖应低于140 mg/dL。如果高于这些范围，可能有糖尿病或前驱糖尿病的风险。若您的血糖水平高于正常，建议尽快咨询医生。控制血糖的关键在于健康的生活方式，包括均衡饮食、定期运动和监测血糖。避免高糖、高脂肪食物，多摄入蔬菜、水果和全谷物，保持适当的体重和定期活动，有助于血糖管理。若血糖在正常范围内，仍需保持健康生活方式，定期监测血糖。高血糖和低血糖都可能对身体造成影响，因此及时管理血糖水平很重要。总结而言，无论检测结果如何，积极生活方式和定期体检是保障健康的关键。\", \"在本次血糖检测中，您的空腹血糖水平为[具体值]，餐后两小时血糖水平为[具体值]。根据这些结果，您的血糖水平处于[正常/异常]范围内。空腹血糖应在70-99 mg/dL之间，餐后两小时血糖应低于140 mg/dL。如果高于这些范围，可能有糖尿病或前驱糖尿病的风险。若您的血糖水平高于正常，建议尽快咨询医生。控制血糖的关键在于健康的生活方式，包括均衡饮食、定期运动和监测血糖。避免高糖、高脂肪食物，多摄入蔬菜、水果和全谷物，保持适当的体重和定期活动，有助于血糖管理。若血糖在正常范围内，仍需保持健康生活方式，定期监测血糖。高血糖和低血糖都可能对身体造成影响，因此及时管理血糖水平很重要。总结而言，无论检测结果如何，积极生活方式和定期体检是保障健康的关键。\", \"在本次血糖检测中，您的空腹血糖水平为[具体值]，餐后两小时血糖水平为[具体值]。根据这些结果，您的血糖水平处于[正常/异常]范围内。空腹血糖应在70-99 mg/dL之间，餐后两小时血糖应低于140 mg/dL。如果高于这些范围，可能有糖尿病或前驱糖尿病的风险。若您的血糖水平高于正常，建议尽快咨询医生。控制血糖的关键在于健康的生活方式，包括均衡饮食、定期运动和监测血糖。避免高糖、高脂肪食物，多摄入蔬菜、水果和全谷物，保持适当的体重和定期活动，有助于血糖管理。若血糖在正常范围内，仍需保持健康生活方式，定期监测血糖。高血糖和低血糖都可能对身体造成影响，因此及时管理血糖水平很重要。总结而言，无论检测结果如何，积极生活方式和定期体检是保障健康的关键。\", \"在本次血糖检测中，您的空腹血糖水平为[具体值]，餐后两小时血糖水平为[具体值]。根据这些结果，您的血糖水平处于[正常/异常]范围内。空腹血糖应在70-99 mg/dL之间，餐后两小时血糖应低于140 mg/dL。如果高于这些范围，可能有糖尿病或前驱糖尿病的风险。若您的血糖水平高于正常，建议尽快咨询医生。控制血糖的关键在于健康的生活方式，包括均衡饮食、定期运动和监测血糖。避免高糖、高脂肪食物，多摄入蔬菜、水果和全谷物，保持适当的体重和定期活动，有助于血糖管理。若血糖在正常范围内，仍需保持健康生活方式，定期监测血糖。高血糖和低血糖都可能对身体造成影响，因此及时管理血糖水平很重要。总结而言，无论检测结果如何，积极生活方式和定期体检是保障健康的关键。\"]}", PdfUpdateConclusionBO.class);

        byte[] bytes1 = addConclusionPageAndSign(bytes, p);
        FileUtil.writeBytes(bytes1, outPath);
    }
}
