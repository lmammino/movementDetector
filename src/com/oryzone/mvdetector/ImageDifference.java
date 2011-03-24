package com.oryzone.mvdetector;

import java.text.DecimalFormat;

import com.oryzone.mvdetector.differenceStrategy.GreyscaleDifferenceStrategy;
import com.oryzone.mvdetector.differenceStrategy.IDifferenceStrategy;
import com.oryzone.mvdetector.differenceStrategy.RgbDifferenceStrategy;
import static com.googlecode.javacv.cpp.opencv_core.*;

/**
 * Class used by the <Detector> class to calculate the difference between frames
 * of the stream
 * 
 * @author Andrea Mangano, Luciano Mammino
 * @version 1.0
 */
public class ImageDifference
{
    /**
     * The previous image instance
     */
    protected IplImage prevImage;
    
    /**
     * The current image instance
     */
    protected IplImage currImage;
    
    /**
     * An image that represents the difference between the previous
     * and the current image
     */
    protected IplImage diffImage;
    
    /**
     * An integer value that indicates how much the previous and
     * the current image are different
     */
    protected int differenceAmount;
    
    /**
     * A float that indicates the difference percentage between the
     * previous and the current image
     */
    protected float differencePercent;
    
    /**
     * An instance of a strategy that is used to calculate
     * the difference between the previous and the current image
     */
    protected IDifferenceStrategy differenceStrategy;

    /**
     * Creates a new instance of the ImageDifference class
     */
    ///TODO: determinate the instance of the difference strategy via configuration
    public ImageDifference()
    {
	this.prevImage = null;
	this.currImage = null;
	this.diffImage = null;
	this.differenceAmount = 0;
	this.differenceStrategy = new GreyscaleDifferenceStrategy();
    }

    /**
     * Gets the previous image instance
     * @return the previous image
     */
    public IplImage getPrevImage()
    {
	return this.prevImage;
    }

    
    /**
     * Sets the previous image instance
     * @param prevImage the previous image instance
     * @return the same {@link ImageDifference} instance (to allow method
     * chaining)
     */
    public ImageDifference setPrevImage(IplImage prevImage)
    {
	this.prevImage = prevImage;
	return this;
    }

    
    /**
     * Gets the current image instance
     * @return the current image
     */
    public IplImage getCurrImage()
    {
	return this.currImage;
    }

    
    /**
     * Sets the current image instance
     * @param currImage the current image instance
     * @return the same {@link ImageDifference} instance (to allow method
     * chaining)
     */
    public ImageDifference setCurrImage(IplImage currImage)
    {
	this.currImage = currImage;
	return this;
    }

    
    /**
     * Sets the previous and the current image instances
     * @param prevImage the previous image instance
     * @param currImage the current image instance
     * @return the same {@link ImageDifference} instance (to allow method
     * chaining)
     */
    public ImageDifference setImages(IplImage prevImage, IplImage currImage)
    {
	this.setPrevImage(prevImage).setCurrImage(currImage);
	return this;
    }

    
    /**
     * Gets the image of builded as difference between the previous and current
     * image instances after the last {@link #calculateDifference} call
     * @return the difference image
     */
    public IplImage getDiffImage()
    {
	return this.diffImage;
    }

    
    /**
     * Gets the amount of difference calculated after the last {@link #calculateDifference} call
     * @return An integer value that indicates how much the previous and
     * the current image are different
     */
    public int getDifferenceAmount()
    {
	return this.differenceAmount;
    }
    
    
    /**
     * gets the amount of difference calculated after the last {@link #calculateDifference} call
     * @return A float that indicates the difference percentage between the
     * previous and the current image
     */
    public float getDifferencePercent()
    {
	return this.differencePercent;
    }

    
    /**
     * Calculates the difference between the previous image and the current image and
     * updates the fields {@link #diffImage}, {@link #differenceAmount} and {@link #differencePercent}
     * @param prevImage
     * @param currImage
     * @return An integer value that indicates how much the previous and
     * the current image are different
     */
    public int calculateDifference(IplImage prevImage, IplImage currImage)
    {
	this.differenceStrategy.calculateDifference(prevImage, currImage);
	this.diffImage = this.differenceStrategy.getDifferenceImage();
	this.differenceAmount = this.differenceStrategy.getDifferenceAmount();
	this.differencePercent = this.differenceStrategy.getDifferencePercent();

        ///TODO: remove debug line
	//System.out.println(new DecimalFormat("0.00")
	//	.format(this.differencePercent * 100) + "%");

	return this.differenceAmount;
    }

}
