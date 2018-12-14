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
    private String targetRGB, targetDirection;
    private int start, end;
    
    public PixelSort(BufferedImage image){
        img = image;
        width = image.getWidth();
        height = image.getHeight(); 
    }
    
    public void setTargetRGB(String s){
        targetRGB = s;
    }
    
    public void setTargetDirection(String s){
        targetDirection = s;
    }
    
    public void setRange(int s, int f){
        start = s;
        end = f;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public BufferedImage getImage() {
        return img;
    }
    
    public BufferedImage horizontalPixelSort() {
        // sort horizontal rows by specifed rgb value and range
        int theStart, theEnd, iterator;
        boolean condition;
        if (targetDirection.equals("East")){
            theStart = 0;
            theEnd = width;
            iterator = 1;
        }
        else {
            theStart = width - 1;
            theEnd = 0;
            iterator = -1;
        }
	for (int i = 0; i < height; i++) {
            condition = true;
            for (int j = theStart; condition; j+=iterator) {
		try {
                    Color color = new Color(img.getRGB(j,  i));
                    if (targetRGB.equals("Blue") && color.getBlue() >= start && 
                            color.getBlue() <= end) {
			img.setRGB(j, i, img.getRGB(j - iterator, i));
                    }
                    else if (targetRGB.equals("Red") && color.getRed() >= start 
                            && color.getRed() <= end) {
			img.setRGB(j, i, img.getRGB(j - iterator, i));
                    }
                    else if (targetRGB.equals("Green") && color.getGreen() >= 
                            start && color.getGreen() <= end) {
			img.setRGB(j, i, img.getRGB(j - iterator, i));
                    }
                    if (targetDirection.equals("East") && j == width - 1){
                        condition = false;
                    }
                    else if (targetDirection.equals("West") && j == 1){
                        condition = false;
                    }
		}
                catch (Exception e) {}
            }
	}
	return img;
    }
    
    public BufferedImage verticalPixelSort() {
        // sort vertical columns by specifed rgb value and range
        if (targetDirection.equals("North")){
            for (int i = height - 1; i >= 0; i--) {
                for (int j = 0; j < width; j++) {
                    try {
                        Color color = new Color(img.getRGB(j,  i));
                        if (targetRGB.equals("Blue") && color.getBlue() >= start && 
                                color.getBlue() <= end) {
                            img.setRGB(j, i, img.getRGB(j, i + 1));
                        }
                        else if (targetRGB.equals("Red") && color.getRed() >= start 
                                && color.getRed() <= end) {
                            img.setRGB(j, i, img.getRGB(j, i + 1));
                        }
                        else if (targetRGB.equals("Green") && color.getGreen() >= 
                                start && color.getGreen() <= end) {
                            img.setRGB(j, i, img.getRGB(j, i + 1));
                        }
                    }
                    catch (Exception e) {}
                }
            }
        }
        else {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    try {
                        Color color = new Color(img.getRGB(j,  i));
                        if (targetRGB.equals("Blue") && color.getBlue() >= start && 
                                color.getBlue() <= end) {
                            img.setRGB(j, i, img.getRGB(j, i - 1));
                        }
                        else if (targetRGB.equals("Red") && color.getRed() >= start 
                                && color.getRed() <= end) {
                            img.setRGB(j, i, img.getRGB(j, i - 1));
                        }
                        else if (targetRGB.equals("Green") && color.getGreen() >= 
                                start && color.getGreen() <= end) {
                            img.setRGB(j, i, img.getRGB(j, i - 1));
                        }
                    }
                    catch (Exception e) {}
                }
            }
        }
	return img;
    }
    
    public BufferedImage diagonalPixelSort(){
        switch (targetDirection) {
            case "Northeast":
                glitchNortheast();
                break;
            case "Southeast":
                glitchSoutheast();
                break;
            case "Northwest":
                glitchNorthwest();
                break;
            case "Southwest":
                glitchSouthwest();
                break;
            default:
                break;
        }
	return img;
    }
    
    public void glitchNortheast() {
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                try {
                    Color color = new Color(img.getRGB(j,  i));
                    if (targetRGB.equals("Blue") && color.getBlue() >= start &&
                            color.getBlue() <= end) {
                        img.setRGB(j, i, img.getRGB(j - 1, i+1));
                    }
                    else if (targetRGB.equals("Red") && color.getRed() >= start
                            && color.getRed() <= end) {
                        img.setRGB(j, i, img.getRGB(j - 1, i+1));
                    }
                    else if (targetRGB.equals("Green") && color.getGreen() >=
                            start && color.getGreen() <= end) {
                        img.setRGB(j, i, img.getRGB(j - 1, i+1));
                    }
                }
                catch (Exception e) {}
            }
        }   
    }
    
    public void glitchNorthwest() {
        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                try {
                    Color color = new Color(img.getRGB(j,  i));
                    if (targetRGB.equals("Blue") && color.getBlue() >= start &&
                            color.getBlue() <= end) {
                        img.setRGB(j, i, img.getRGB(j + 1, i+1));
                    }
                    else if (targetRGB.equals("Red") && color.getRed() >= start
                            && color.getRed() <= end) {
                        img.setRGB(j, i, img.getRGB(j + 1, i+1));
                    }
                    else if (targetRGB.equals("Green") && color.getGreen() >=
                            start && color.getGreen() <= end) {
                        img.setRGB(j, i, img.getRGB(j + 1, i+1));
                    }
                }
                catch (Exception e) {}
            }
        }   
    }
    
    public void glitchSoutheast() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                try {
                    Color color = new Color(img.getRGB(j,  i));
                    if (targetRGB.equals("Blue") && color.getBlue() >= start &&
                            color.getBlue() <= end) {
                        img.setRGB(j, i, img.getRGB(j - 1, i-1));
                    }
                    else if (targetRGB.equals("Red") && color.getRed() >= start
                            && color.getRed() <= end) {
                        img.setRGB(j, i, img.getRGB(j - 1, i-1));
                    }
                    else if (targetRGB.equals("Green") && color.getGreen() >=
                            start && color.getGreen() <= end) {
                        img.setRGB(j, i, img.getRGB(j - 1, i-1));
                    }
                }
                catch (Exception e) {}
            }
        }
    }
    
    public void glitchSouthwest() {
        for (int i = 0; i < height; i++) {
            for (int j = width - 1; j >= 0; j--) {
                try {
                    Color color = new Color(img.getRGB(j,  i));
                    if (targetRGB.equals("Blue") && color.getBlue() >= start && 
                            color.getBlue() <= end) {
                        img.setRGB(j, i, img.getRGB(j + 1, i-1));
                    }
                    else if (targetRGB.equals("Red") && color.getRed() >= start 
                            && color.getRed() <= end) {
                        img.setRGB(j, i, img.getRGB(j + 1, i-1));
                    }
                    else if (targetRGB.equals("Green") && color.getGreen() >= 
                            start && color.getGreen() <= end) {
                        img.setRGB(j, i, img.getRGB(j + 1, i-1));
                    }
                }
                catch (Exception e) {}
            }
        }
    }
}
