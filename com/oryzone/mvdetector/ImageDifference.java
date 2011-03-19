package com.oryzone.mvdetector;

import java.text.DecimalFormat;

import com.oryzone.mvdetector.differenceStrategy.GreyscaleDifferenceStrategy;
import com.oryzone.mvdetector.differenceStrategy.IDifferenceStrategy;
import com.oryzone.mvdetector.differenceStrategy.RgbDifferenceStrategy;
import static com.googlecode.javacv.cpp.opencv_core.*;

public class ImageDifference
{
	protected IplImage prevImage;
	protected IplImage currImage;
	protected IplImage diffImage;
	protected int differenceAmount;
	protected float differencePercent;
	protected IDifferenceStrategy differenceStrategy;
	
	public ImageDifference()
	{
		this.prevImage = null;
		this.currImage = null;
		this.diffImage = null;
		this.differenceAmount = 0;
		this.differenceStrategy = new GreyscaleDifferenceStrategy();
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
		this.differenceStrategy.calculateDifference(prevImage, currImage);
		this.diffImage = this.differenceStrategy.getDifferenceImage();
		this.differenceAmount = this.differenceStrategy.getDifferenceAmount();
		this.differencePercent = this.differenceStrategy.getDifferencePercent();
		
		System.out.println(new DecimalFormat("0.00").format(this.differencePercent * 100) + "%");
		
		return this.differenceAmount;
	}
	
	
	
}
