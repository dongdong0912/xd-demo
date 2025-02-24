package com.example.xddemo.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author xuedong
 * Date: 2025/2/11
 */
public class GeneratorQrCodeDemo {




    public static String generateQRCode(String url) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

            // Base64编码
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            String base64String = Base64.getEncoder().encodeToString(imageBytes);
            return base64String;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) {




    }
}
