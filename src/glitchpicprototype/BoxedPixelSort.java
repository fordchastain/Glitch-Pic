/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glitchpicprototype;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Stanford Chastain
 */
public class BoxedPixelSort extends PixelSort {
    private final BufferedImage img1;
    private final int width;
    private final int height;
    private final double glitchAmount;
    
    public BoxedPixelSort(BufferedImage image, double percentage){
        super(image);
        img1 = image;
        width = image.getWidth();
        height = image.getHeight();
        glitchAmount = percentage / 100;
    }
    
    public BufferedImage glitchPhoto(){
	Random rand = new Random();
	int vertCount = 0, horizCount = 0;
	int randHeight;
	int randHeight2;	
	int randWid;
	int randWid2;
        
        // sort pixel boxes a number of times determined by glitchAmount
	while (vertCount < (int)(90 * glitchAmount)) {
            try {
		randWid = rand.nextInt(width);
		randWid2 = rand.nextInt(width - randWid);
		randHeight = rand.nextInt(height);
		randHeight2 = rand.nextInt(height - randHeight);
		for (int i = randHeight; i < randHeight2; i++) {
                    for (int j = randWid; j < randWid2; j++) {
			if (height - i > height / 30) {
                            img1.setRGB(j, i, img1.getRGB(j, i + height / 30));
			}
                    }
		}
		vertCount++;
            }
            catch (Exception e) {}
	}
	while (horizCount < (int)(30 * glitchAmount)) {
            try {
		randWid = rand.nextInt(width);
		randWid2 = rand.nextInt(width - randWid);
		randHeight = rand.nextInt(height);
		randHeight2 = rand.nextInt(height - randHeight);
		for (int i = randHeight; i < randHeight2; i++) {
                    for (int j = randWid; j < randWid2; j++) {
			img1.setRGB(i, j, img1.getRGB(i, j + width / 30) + 900);
                    }
		}
		horizCount++;
            }
            catch (Exception e) {}
	}
        return img1;
    }
}
