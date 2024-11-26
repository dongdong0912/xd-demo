package com.example.xddemo.demo.pdf;

import com.google.common.collect.Lists;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.collections4.CollectionUtils;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * @author xuedong
 * Date: 2024/11/26
 */
public class YaPeiPdfDDemo {


    public static void main(String[] args) throws Exception {


        // 指定 PDF 文件路径
        String filePath = "/Users/xuedong/Desktop/雅培测试.pdf";
        String outPath = "/Users/xuedong/Desktop/11261.pdf";

        PdfReader reader = new PdfReader(filePath);

        addContent(reader, Files.newOutputStream(Paths.get(outPath)));

    }


    public static void addContent(PdfReader reader, OutputStream outputStream) {
        try {
            int numPages = reader.getNumberOfPages();
            // 创建 PdfStamper 对象来修改现有 PDF 文件并添加新的 PDF 页面
            PdfStamper stamper = new PdfStamper(reader, outputStream);
            // 获取 PDF 的最后一页
            PdfImportedPage page = stamper.getImportedPage(reader, numPages);
            // 在原始 PDF 的末尾添加最后一页
            stamper.insertPage(numPages + 1, reader.getPageSize(numPages));
            PdfContentByte contentByte = stamper.getUnderContent(numPages + 1);

            System.out.println(page.getHeight());
            System.out.println(page.getWidth());
            contentByte.addTemplate(page, 0, 0);

            // 填充颜色
            fillColor(contentByte);
            Phrase signaturePhrase = new Phrase("专家阅片结论:", PdfFontUtils.boldBlack10);
            ColumnText.showTextAligned(contentByte, Element.ALIGN_CENTER, signaturePhrase, 58, 755, 0);

            //结论
            Paragraph paragraph = new Paragraph();
            List<String> contentList = Lists.newArrayList();
            contentList.add("生活是一幅绚丽多彩的画卷，充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。鸟儿在枝头欢唱，实施十大 但是佛但是佛是但是地方阿水 发是的佛阿水佛阿水佛是的佛是地方 的佛但是佛是佛啊 仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目的地。生活中有欢笑，也有泪水；有成功的喜悦，也有失败的落寞。但正是这些酸甜苦辣，构成了生活的丰富内涵。我们在挫折中学会坚强，在困境中寻找希望。用心去感受生活的每一个瞬间，珍惜身边的人和事，让生活绽放出最美丽的色彩。");
            contentList.add("生活是一幅绚丽多彩的画卷，充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。鸟儿在枝头欢唱，实施十大 但是佛但是佛是但是地方阿水 发是的佛阿水佛阿水佛是的佛是地方 的佛但是佛是佛啊 仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目鸟儿在枝头欢唱，充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。鸟儿在枝头欢唱，实施十大 但是佛但是佛是但是地方阿水 发是的佛阿水佛阿水佛是的佛是地方 的佛但是佛是佛啊 仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目的地。生活中有欢笑，也有泪水；有成功的喜悦，也有失败的落寞。但正是这些酸甜苦辣，构成了生活的丰富内涵。我们在挫折中学会坚强，在困境中寻找希望。用心去感受生活的每一个瞬间，珍惜身边的人和事，让生活绽放出最美丽的色彩。");
            contentList.add("生活是一幅绚丽多彩的画卷，充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。鸟儿在枝头欢唱，实施十大 但是佛但是佛是但是地方阿水 发是的佛阿水佛阿水佛是的佛是地方 的佛但是佛是佛啊 仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目鸟儿在枝头欢唱，充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。鸟儿在枝头欢唱，实施十大 但是佛但是佛是但是地方阿水 发是的佛阿水佛阿水佛是的佛是地方 的佛但是佛是佛啊 仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目的地。生活中有欢笑，也有泪水；有成功的喜悦，也有失败的落寞。但正是这些酸甜苦辣，构成了生活的丰富内涵。我们在挫折中学会坚强，在困境中寻找希望。用心去感受生活的每一个瞬间，珍惜身边的人和事，让生活绽放出最美丽的色彩。");
            contentList.add("生活是一幅绚丽多彩的画卷，充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。鸟儿在枝头欢唱，仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目的地。生活中有欢笑，也有泪水；有成功的喜悦，也有失败的落寞。但正是这些酸甜苦辣，构成了生活的丰富内涵。我们在挫折中学会坚强，在困境中寻找希望。用心去感受生活的每一个瞬间，珍惜身边的人和事，让生活绽放出最美丽的色彩。");
            contentList.add("生活是一幅绚丽多彩的画卷，充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。鸟儿在枝头欢唱，充满了无尽的可能。清晨的第一缕阳光，温暖而明亮，唤醒了沉睡的世界。鸟儿在枝头欢唱，实施十大 但是佛但是佛是但是地方阿水 发是的佛阿水佛阿水佛是的佛是地方 的佛但是佛是佛啊 仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目仿佛在为新的一天奏响乐章。街道上，行人匆匆，各自奔赴着不同的目的地。生活中有欢笑，也有泪水；有成功的喜悦，也有失败的落寞。但正是这些酸甜苦辣，构成了生活的丰富内涵。我们在挫折中学会坚强，在困境中寻找希望。用心去感受生活的每一个瞬间，珍惜身边的人和事，让生活绽放出最美丽的色彩。");
            for (int i = 0; i < contentList.size(); i++) {
                if (!Objects.equals(0, i)) {
                    paragraph.add(Chunk.NEWLINE);
                }
                paragraph.add(new Chunk((i + 1) + "、" + contentList.get(i)));
            }
            paragraph.setFont(PdfFontUtils.normalBlack8);

            ColumnText columnText = new ColumnText(contentByte);
            //columnText.setSimpleColumn(paragraph, 80, 600, 790, 1040, 0, Element.ALIGN_LEFT);
            columnText.setSimpleColumn(paragraph, 30, 300, 570, 750, 0, Element.ALIGN_LEFT);
            columnText.addElement(paragraph);
            columnText.go();


            // 关闭资源
            stamper.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("异常");
        }
    }


    private static void fillColor(PdfContentByte contentByte) {
        contentByte.saveState();
        contentByte.setColorFill(BaseColor.WHITE);
        contentByte.rectangle(0, 0, 600, 768);
        contentByte.fill();
        contentByte.restoreState();
    }
}
