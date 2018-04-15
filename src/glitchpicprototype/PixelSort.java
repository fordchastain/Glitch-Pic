/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glitchpicprototype;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Stanford Chastain
 */
public class PixelSort {
    private final BufferedImage img;
    private final int width;
    private final int height;
    private String targetRGB;
    private int start, end;
    
    public PixelSort(BufferedImage image){
        img = image;
        width = image.getWidth();
        height = image.getHeight(); 
    }
    
    public void setTargetRGB(String s){
        targetRGB = s;
    }
    
    public void setRange(int s, int f){
        start = s;
        end = f;
    }
    
    public BufferedImage horizontalPixelSort() {
        // sort horizontal rows by specifed rgb value and range
	for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
		try {
                    Color color = new Color(img.getRGB(j,  i));
                    if (targetRGB.equals("Blue") && color.getBlue() >= start && color.getBlue() <= end) {
			img.setRGB(j, i, img.getRGB(j - 1, i));
                    }
                    else if (targetRGB.equals("Red") && color.getRed() >= start && color.getRed() <= end) {
			img.setRGB(j, i, img.getRGB(j - 1, i));
                    }
                    else if (targetRGB.equals("Green") && color.getGreen() >= start && color.getGreen() <= end) {
			img.setRGB(j, i, img.getRGB(j - 1, i));
                    }
		}
                catch (Exception e) {}
            }
	}
	return img;
    }
    
    public BufferedImage verticalPixelSort() {
        // sort vertical columns by specifed rgb value and range
	for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
		try {
                    Color color = new Color(img.getRGB(j,  i));
                    if (targetRGB.equals("Blue") && color.getBlue() >= start && color.getBlue() <= end) {
			img.setRGB(j, i, img.getRGB(j, i - 1));
                    }
                    else if (targetRGB.equals("Red") && color.getRed() >= start && color.getRed() <= end) {
			img.setRGB(j, i, img.getRGB(j, i - 1));
                    }
                    else if (targetRGB.equals("Green") && color.getGreen() >= start && color.getGreen() <= end) {
			img.setRGB(j, i, img.getRGB(j, i - 1));
                    }
		}
                catch (Exception e) {}
            }
	}
	return img;
    }
}
