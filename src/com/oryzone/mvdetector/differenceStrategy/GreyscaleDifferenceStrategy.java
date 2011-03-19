package com.oryzone.mvdetector.differenceStrategy;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_RGB2GRAY;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;

public class GreyscaleDifferenceStrategy extends AbstractDifferenceStrategy
{

    @Override
    public int calculateDifference(IplImage prevImage, IplImage currImage)
    {

	this.differenceAmount = 0;
	int width = currImage.width();
	int height = currImage.height();

	IplImage prevImageGS = IplImage.create(prevImage.width(),
		prevImage.height(), IPL_DEPTH_8U, 1);
	cvCvtColor(prevImage, prevImageGS, CV_RGB2GRAY);

	IplImage currImageGS = IplImage.create(currImage.width(),
		currImage.height(), IPL_DEPTH_8U, 1);
	cvCvtColor(currImage, currImageGS, CV_RGB2GRAY);

	BufferedImage cImage = currImageGS.getBufferedImage();
	BufferedImage pImage = prevImageGS.getBufferedImage();
	BufferedImage dImage = new BufferedImage(width, height,
		cImage.getType());

	for (int y = 0; y < height; y++)
	{
	    for (int x = 0; x < width; x++)
	    {
		int cColor = new Color(cImage.getRGB(x, y)).getRed();
		int pColor = new Color(pImage.getRGB(x, y)).getRed();

		int dColor = Math.abs(cColor - pColor);
		this.differenceAmount += dColor;

		Color color = new Color(Math.max(0,
			Math.min(255, cColor + dColor)), Math.max(0,
			Math.min(255, cColor + dColor)), Math.max(0,
			Math.min(255, cColor + dColor)));
		dImage.setRGB(x, y, color.getRGB());
	    }
	}

	this.differenceImage = IplImage.createFrom(dImage);
	this.differencePercent = (float) (this.differenceAmount)
		/ (255 * width * height);

	return this.differenceAmount;

    }

}
