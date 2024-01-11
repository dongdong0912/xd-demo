package com.example.xddemo.demo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;

/**
 * Author: xuedong
 * Date: 2023/12/6
 */
@Slf4j
public class PdfDemo {


    public static void main(String[] args) throws Exception {


        // 指定 PDF 文件路径
        String filePath = "/Users/xuedong/Desktop/33.pdf";
        String outPath = "/Users/xuedong/Desktop/331.pdf";

        manipulatePdf2(filePath, outPath);
    }

    private static void addText() {
        byte[] bytes = addContent("清晨的阳光透过树叶的缝隙洒在大地上，一切都显得宁静而美好。小溪轻轻流淌，悠扬的鸟鸣在空中回荡。远处的山峦如画，静默地守望着大地。微风吹过，带着花香弥漫在空气中。人们沐浴在自然的怀抱中，感受着它无尽的宁静与祥和。生活也许有繁忙与焦虑，但此刻，让我们暂时远离喧嚣，沉浸在大自然的怀抱中，感受它带给我们的宁静与温暖。", 3, 100, 800, 1100, 1100, test().toByteArray());
        test1(bytes);
    }

    public static void test1(byte[] byteArray) {

        String filePath = "/Users/xuedong/Desktop/20.pdf"; // 替换成实际的输出文件路径

        try {
            // 创建FileOutputStream以写入文件
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));

            // 将字节数组写入文件
            fileOutputStream.write(byteArray);

            // 关闭输出流
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ByteArrayOutputStream test() {
        File file = new File("/Users/xuedong/Desktop/getPdfView (1).pdf"); // 替换成实际的文件路径

        try {
            // 创建FileInputStream以读取文件内容测试
            FileInputStream fileInputStream = new FileInputStream(file);

            // 创建ByteArrayOutputStream以保存读取的文件内容
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            // 读取文件内容并写入ByteArrayOutputStream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            // 关闭输入流
            fileInputStream.close();

            // 获取ByteArrayOutputStream的字节数组
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            // 关闭ByteArrayOutputStream
            byteArrayOutputStream.close();

            return byteArrayOutputStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 硅基的调整
     *
     * @param src
     * @param dest
     * @throws IOException
     * @throws DocumentException
     */

    public static void manipulatePdf2(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        //添加一个遮挡处，可以把原内容遮住，后面在上面写入内容
        PdfContentByte canvas = stamper.getOverContent(1);  //可以遮挡文字

        float width = reader.getPageSize(1).getWidth();
        float height = reader.getPageSize(1).getHeight();

        System.out.println("Page " + 1 + " - Width: " + width + " | Height: " + height);

        canvas.saveState();
        //canvas.setColorFill(BaseColor.YELLOW);  //遮挡层颜色：黄色
        canvas.setColorFill(BaseColor.WHITE);  //遮挡层颜色：白色
        canvas.rectangle(170, 1090, 280, 50);
        canvas.fill();
        canvas.restoreState();


//        // 指定中文宋体字体路径
//        String fontPath = "/Users/xuedong/Desktop/ZYSong18030.ttf"; // 替换成实际的字体文件路径
//        // 使用BaseFont创建字体对象
//        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        Phrase signaturePhrase = new Phrase("天津滨海新区北塘街欣嘉园蓝卡社区卫生服务中心", new Font(baseFont, 14, Font.BOLD, BaseColor.BLACK));
//        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, signaturePhrase, 170f, 1115, 0);

        String fontPath = "/Users/xuedong/Desktop/ZYSong18030.ttf"; // 替换成实际的字体文件路径
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("天津医院"));
        paragraph.setFont(new Font(baseFont, 16, Font.BOLD, BaseColor.BLACK));
        // 使用ColumnText将带有自动换行的文本添加到PDF中
        ColumnText columnText = new ColumnText(canvas);
        columnText.setSimpleColumn(paragraph, 170, 1090, 450, 1130, 0, Element.ALIGN_LEFT);
        columnText.addElement(paragraph);
        columnText.go();

        stamper.close();
        reader.close();
        System.out.println("complete");
    }

    /**
     * 肝纤维的修改
     *
     * @param src
     * @param dest
     * @throws IOException
     * @throws DocumentException
     */
    public static void manipulatePdf1(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        //添加一个遮挡处，可以把原内容遮住，后面在上面写入内容
        PdfContentByte canvas = stamper.getOverContent(1);  //可以遮挡文字

        float width = reader.getPageSize(1).getWidth();
        float height = reader.getPageSize(1).getHeight();

        System.out.println("Page " + 1 + " - Width: " + width + " | Height: " + height);

        canvas.saveState();
        canvas.setColorFill(BaseColor.YELLOW);  //遮挡层颜色：黄色
        //canvas.setColorFill(BaseColor.WHITE);  //遮挡层颜色：白色
        canvas.rectangle(130, 780, 300, 50);
        canvas.fill();
        canvas.restoreState();


        // 指定中文宋体字体路径
        String fontPath = "/Users/xuedong/Desktop/ZYSong18030.ttf"; // 替换成实际的字体文件路径
        // 使用BaseFont创建字体对象
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Phrase signaturePhrase = new Phrase("天津滨海新区北塘街欣嘉园蓝卡社区卫生服务中心", new Font(baseFont, 15, Font.BOLD, BaseColor.BLACK));
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, signaturePhrase, 130f, 800, 0);

        stamper.close();
        reader.close();
        System.out.println("complete");
    }

    /**
     * 河南云心电
     *
     * @param src
     * @param dest
     * @throws IOException
     * @throws DocumentException
     */
    public static void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        //添加一个遮挡处，可以把原内容遮住，后面在上面写入内容
        PdfContentByte canvas = stamper.getOverContent(1);  //可以遮挡文字

        float width = reader.getPageSize(1).getWidth();
        float height = reader.getPageSize(1).getHeight();

        System.out.println("Page " + 1 + " - Width: " + width + " | Height: " + height);

        canvas.saveState();
        //canvas.setColorFill(BaseColor.YELLOW);  //遮挡层颜色：黄色
        canvas.setColorFill(BaseColor.WHITE);  //遮挡层颜色：白色
        canvas.rectangle(240, 805, 110, 20);
        canvas.fill();
        canvas.restoreState();


        // 指定中文宋体字体路径
        String fontPath = "/Users/xuedong/Desktop/ZYSong18030.ttf"; // 替换成实际的字体文件路径
        // 使用BaseFont创建字体对象
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Phrase signaturePhrase = new Phrase("社区卫生服务中心", new Font(baseFont, 12, Font.BOLD, BaseColor.BLACK));
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, signaturePhrase, 297.5f, 805, 0);

        stamper.close();
        reader.close();
        System.out.println("complete");
    }

    public static byte[] addContent(String text, int page, float llx, float lly, float urx, float ury, byte[] pdfFile) {
        PdfReader reader = null;
        PdfStamper stamper = null;
        PdfContentByte over = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            reader = new PdfReader(pdfFile);

            // 获取 PDF 的总页数
            int totalPages = reader.getNumberOfPages();

            // 获取最后一页的大小
            Rectangle lastPageSize = reader.getPageSize(totalPages);

            // 输出最后一页的宽和高
            System.out.println("Last Page Width: " + lastPageSize.getWidth());
            System.out.println("Last Page Height: " + lastPageSize.getHeight());

            stamper = new PdfStamper(reader, bos);
            over = stamper.getOverContent(totalPages);

            // 指定中文宋体字体路径
            String fontPath = "/Users/xuedong/Desktop/ZYSong18030.ttf"; // 替换成实际的字体文件路径
            // 使用BaseFont创建字体对象
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // Paragraph datePhrase = new Paragraph("在这个宽广的网络世界中，信息如同潮水般汹涌而至。数不胜数的网页、博客和社交媒体上充斥着各种各样的内容，从新闻\n事件到娱乐八卦，从科技前沿到文学艺术。人们在虚拟的空间里交流思想，分享见解，形成了\n一个错综复杂、丰富多彩的信息社会。在这个纷繁复杂的世界里，我们不仅仅是信息的接收者，更是信息的创造者和传播者。在键盘的敲击声中，我们书写着自己的故事，共同构建着这个数字时代的篇章。", new Font(baseFont, 12));


            Phrase signaturePhrase = new Phrase("结论", new Font(baseFont, 12, Font.BOLD, BaseColor.BLACK));
            ColumnText.showTextAligned(over, Element.ALIGN_LEFT, signaturePhrase, 60, 180, 0);


            Paragraph paragraph = new Paragraph();
            paragraph.add(new Chunk(text + "12k3HPP"));
            paragraph.setFont(new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK));
            // 使用ColumnText将带有自动换行的文本添加到PDF中
            ColumnText columnText = new ColumnText(over);
            columnText.setSimpleColumn(paragraph, 60, 100, 760, 170, 0, Element.ALIGN_LEFT);
            columnText.addElement(paragraph);
            columnText.go();

            test3(over, baseFont);
            stamper.close();
            return bos.toByteArray();
        } catch (Exception e) {
            log.error("指定位置插入文本异常 ：" + e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return pdfFile;
    }


    public static void test3(PdfContentByte over, BaseFont baseFont) throws Exception {


        //获取页面大小
        float pageWidth = PageSize.A4.getWidth();
        //签名文字
        String signatureText = "签名:";
        //日期文字
        String dateText = "日期:2023-12-06";
        // 计算文字绘制的位置
        float x = 520;
        float y = 80;
        float temp = baseFont.getAscentPoint("签名", 12);
        int imageY = (int) (50 + temp / 2 - 30);
        Image image = Image.getInstance(new URL("http://10.60.10.19/local-kano/lva207782"));
        image.scaleAbsolute(50, 50);
        image.setAbsolutePosition(550, 60);
        over.addImage(image);
        // 添加签名文字
        Phrase signaturePhrase = new Phrase(signatureText, new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK));
        ColumnText.showTextAligned(over, Element.ALIGN_LEFT, signaturePhrase, x, y, 0);
        //添加日期文字
        Phrase datePhrase = new Phrase(dateText, new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK));
        ColumnText.showTextAligned(over, Element.ALIGN_LEFT, datePhrase, x + 100, y, 0);
    }
}
