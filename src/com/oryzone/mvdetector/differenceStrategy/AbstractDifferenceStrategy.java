package com.oryzone.mvdetector.differenceStrategy;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 * Abstract class used as base to build specific difference strategies
 * classes such as {@link RgbDifferenceStrategy} and {@link GreyscaleDifferenceStrategy}
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 * @see IDifferenceStrategy
 */
public abstract class AbstractDifferenceStrategy implements IDifferenceStrategy
{

    /**
     * The image that contains the graphic difference between the previous and
     * the current images
     */
    protected IplImage differenceImage;
    
    /**
     * The amount of difference between the previous and the current image
     */
    protected int differenceAmount;
    
    /**
     * The percent of difference between the previous and the current image
     */
    protected float differencePercent;

    
    @Override
    public abstract int calculateDifference(IplImage prevImage, IplImage currImage);

    
    @Override
    public int getDifferenceAmount()
    {
    	return this.differenceAmount;
    }

    
    @Override
    public float getDifferencePercent()
    {
    	return this.differencePercent;
    }

    
    @Override
    public IplImage getDifferenceImage()
    {
    	return this.differenceImage;
    }

}
