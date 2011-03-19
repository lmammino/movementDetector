package com.oryzone.mvdetector.differenceStrategy;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class RgbDifferenceStrategy extends AbstractDifferenceStrategy
{

    @Override
    public int calculateDifference(IplImage prevImage, IplImage currImage)
    {
	this.differenceAmount = 0;
	int width = currImage.width();
	int height = currImage.height();
	
	BufferedImage cImage = currImage.getBufferedImage();
	BufferedImage pImage = prevImage.getBufferedImage();
	BufferedImage dImage = new BufferedImage(width, height, cImage.getType());
	
	
	for(int y = 0; y < height; y++)
	{
		for(int x = 0; x < width; x++)
		{
			Color cColor = new Color(cImage.getRGB(x,y));
			Color pColor = new Color(pImage.getRGB(x, y));
			
			int cR = cColor.getRed();
			int cG = cColor.getGreen();
			int cB = cColor.getBlue();
			
			int pR = pColor.getRed();
			int pG = pColor.getGreen();
			int pB = pColor.getBlue();
			
			int dR = Math.abs(cR - pR);
			if(dR > 30)
			    dR += 100;
			int dG = Math.abs(cG - pG);
			int dB = Math.abs(cB - pB);
			
			this.differenceAmount += (dR + dG + dB)/3;
			
			Color color = new Color(	Math.max(0, Math.min(255, cR + dR)), 
							Math.max(0, Math.min(255, cG + dG)), 
							Math.max(0, Math.min(255, cB + dB ))
						);
			dImage.setRGB(x, y, color.getRGB());
		}
	}
	
	this.differenceImage = IplImage.createFrom(dImage);
	this.differencePercent = (float)(this.differenceAmount) / (255 * width * height);
	
	return this.differenceAmount;
    }

}
