package com.oryzone.mvdetector;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static com.googlecode.javacv.cpp.opencv_core.*;

public class ImageDifference
{
	protected IplImage prevImage;
	protected IplImage currImage;
	protected IplImage diffImage;
	protected int differenceAmount;
	protected float differencePercent;
	
	
	public ImageDifference()
	{
		this.prevImage = null;
		this.currImage = null;
		this.diffImage = null;
		this.differenceAmount = 0;
	}
	
	
	public IplImage getPrevImage() {
		return this.prevImage;
	}




	public void setPrevImage(IplImage prevImage) {
		this.prevImage = prevImage;
	}




	public IplImage getCurrImage() {
		return this.currImage;
	}




	public void setCurrImage(IplImage currImage) {
		this.currImage = currImage;
	}


	
	public void setImages(IplImage prevImage, IplImage currImage)
	{
		this.setPrevImage(prevImage);
		this.setCurrImage(currImage);
	}
	
	

	public IplImage getDiffImage() {
		return diffImage;
	}




	public int getDifferenceAmount() {
		return differenceAmount;
	}




	public int calculateDifference(IplImage prevImage, IplImage currImage)
	{
		int differenceAmount = 0;
		int width = currImage.width();
		int height = currImage.height();
		
		//cvSmooth(prevImage, prevImage, CV_MEDIAN, 7);
		//cvSmooth(currImage, currImage, CV_MEDIAN, 7);
		
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
				int dG = Math.abs(cG - pG);
				int dB = Math.abs(cB - pB);
				
				differenceAmount += (dR + dG + dB)/3;
				
				Color color = new Color(	Math.max(0, Math.min(255, cR - pR)), 
						Math.max(0, Math.min(255, cG - pG)), 
								Math.max(0, Math.min(255, cB - pB )));
				/*Color color = null;
				
				if(dR > 15 && dG > 15 && dB > 15)
					color = new Color( dR, dG, dB);
				else
					color = new Color( 0, 0, 0);*/
				//System.out.println(color.getRGB());
				//System.out.println(dR + ", " + dG + ", " + dB);
				dImage.setRGB(x, y, color.getRGB());
			}
		}
		
		this.diffImage = IplImage.createFrom(dImage);
		this.differenceAmount = differenceAmount;
		this.differencePercent = (float)(this.differenceAmount) / (255 * width * height);
		System.out.println(new DecimalFormat("0.00").format(this.differencePercent * 100) + "%");
		
		return this.differenceAmount;
	}
	
	
	
}
