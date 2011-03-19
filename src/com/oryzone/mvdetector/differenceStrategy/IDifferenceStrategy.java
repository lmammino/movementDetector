package com.oryzone.mvdetector.differenceStrategy;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 * Interface that defines the methods of the difference strategy classes.
 * Difference strategy classes are class that should provide a methods
 * to calculate the difference between two image of the same size, and
 * also provide numerical values to represent the amount of difference.
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 * @see AbstractDifferenceStrategy
 */
public interface IDifferenceStrategy
{

    /**
     * Calculates the difference between the previous and the current
     * image
     * @param prevImage the previous image
     * @param currImage the current image
     * @return a integer that represents the amount of difference
     * between the two images
     */
    public int calculateDifference(IplImage prevImage, IplImage currImage);

    /**
     * Gets the amount of difference between the previous and current images.
     * This value is generally calculated after a call to {@link #calculateDifference}.
     * @return an integer representing the amount of difference
     */
    public int getDifferenceAmount();

    /**
     * Gets the percent of difference between the previous and current images.
     * This value is generally calculated after a call to {@link #calculateDifference}.
     * @return an float representing the percent of difference
     */
    public float getDifferencePercent();

    
    /**
     * Gets an image that graphically represents the difference between the
     * previous and the current image.
     * This value is generally calculated after a call to {@link #calculateDifference}.
     * @return an image that graphically represents the difference between the previous
     * and the current image
     */
    public IplImage getDifferenceImage();
}