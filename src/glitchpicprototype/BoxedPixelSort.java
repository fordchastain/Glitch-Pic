package glitchpicprototype;

import java.awt.image.BufferedImage;
import java.util.Random;

public class BoxedPixelSort extends PixelSort {
    private final double glitchAmount;
    
    public BoxedPixelSort(BufferedImage image, double percentage){
        super(image);
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
				randWid = rand.nextInt(getWidth());
				randWid2 = rand.nextInt(getWidth() - randWid);
				randHeight = rand.nextInt(getHeight());
				randHeight2 = rand.nextInt(getHeight() - randHeight);
				for (int i = randHeight; i < randHeight2; i++) {
					for (int j = randWid; j < randWid2; j++) {
						if (getHeight() - i > getHeight() / 30) {
							getImage().setRGB(j, i, getImage().getRGB(j, i+getHeight()/30));
						}
					}
				}
				vertCount++;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		while (horizCount < (int)(30 * glitchAmount)) {
			try {
				randWid = rand.nextInt(getWidth());
				randWid2 = rand.nextInt(getWidth() - randWid);
				randHeight = rand.nextInt(getHeight());
				randHeight2 = rand.nextInt(getHeight() - randHeight);
				for (int i = randHeight; i < randHeight2; i++) {
					for (int j = randWid; j < randWid2; j++) {
						getImage().setRGB(i, j, getImage().getRGB(i, j+getWidth()/30)+900);
					}
				}
				horizCount++;
			}
			catch (Exception e) {}
		}
		return getImage();
	}
}
