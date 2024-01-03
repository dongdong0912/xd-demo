package com.example.xddemo.demo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageBackgroundWhite {
    public static void main(String[] args) throws IOException {
        // Load the original image
        File inputImageFile = new File("/Users/xuedong/Desktop/1.png");
        BufferedImage originalImage = ImageIO.read(inputImageFile);

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Create a new BufferedImage with the same dimensions and type
        BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());

        // Loop through each pixel and set non-transparent pixels to white
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = originalImage.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xFF; // Extract alpha channel
                if (alpha != 0) {
                    newImage.setRGB(x, y, 0xFFFFFFFF); // Set white color
                }
            }
        }

        // Save the modified image
        File outputImageFile = new File("/Users/xuedong/Desktop/2.png");
        ImageIO.write(newImage, "jpg", outputImageFile);

        System.out.println("Image processing complete.");


    }
}


