package com.oryzone.mvdetector;

import com.googlecode.javacv.*;
import static com.googlecode.javacv.cpp.opencv_core.*;


public class Detector
{
	public static int STATUS_STOPPED = 0;
	public static int STATUS_STARTED = 1;
	public static int STATUS_CAPTURING = 2;
	protected int status;
	
	protected DetectorOptions options;
	
	
	public Detector(DetectorOptions options)
	{
		this.options = options;
		this.status = Detector.STATUS_STOPPED;
	}
	
	public Detector()
	{
		this(new DetectorOptions());
	}
	
	
	public void start() throws Exception
	{
		this.status = Detector.STATUS_STARTED;
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		grabber.setImageWidth(640);
		grabber.setImageHeight(480);
		grabber.start();
		
		IplImage frame = grabber.grab();
		IplImage currImage = null;
		IplImage prevImage = null;
		
		CanvasFrame canvasFrame = new CanvasFrame("Capturing");
		canvasFrame.setCanvasSize(640, 480);
		
		ImageDifference diff = new ImageDifference();
		
		while(canvasFrame.isVisible() && (frame = grabber.grab()) != null)
		{
			this.status = Detector.STATUS_CAPTURING;
			
			if(currImage == null)
				currImage = frame.clone();
			else
			{
				prevImage = currImage.clone();
				currImage = frame.clone();
			}

			
			if(prevImage != null && currImage != null)
			{
				diff.setImages(prevImage, currImage);
				diff.calculateDifference(prevImage, currImage);
			
				canvasFrame.showImage(diff.getDiffImage());
			}
		}
		this.status = Detector.STATUS_STOPPED;
	}
	
	
	public void stop()
	{
		this.status = Detector.STATUS_STOPPED;
	}
	
	
	public int getStatus()
	{
		return this.status;
	}
	
	
}