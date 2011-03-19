package com.oryzone.mvdetector.differenceStrategy;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public abstract class AbstractDifferenceStrategy implements IDifferenceStrategy
{

    protected IplImage differenceImage;
    protected int differenceAmount;
    protected float differencePercent;

    @Override
    public abstract int calculateDifference(IplImage prevImage,
	    IplImage currImage);

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
