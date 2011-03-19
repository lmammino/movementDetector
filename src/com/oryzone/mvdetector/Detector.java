package com.oryzone.mvdetector;

import com.googlecode.javacv.*;
import static com.googlecode.javacv.cpp.opencv_core.*;

/**
 * Class that handles the whole logic of capturing the video stream and
 * processing it
 * 
 * @author Andrea Mangano, Luciano Mammino
 * @version 1.0
 */
public class Detector
{
    /**
     * The current status of the detector
     */
    protected DetectorStatus status;

    /**
     * The options currently used by the detector
     */
    protected DetectorOptions options;

    /**
     * Creates a new Detector instance with a given set of options
     * 
     * @param options
     *            the options that the detector should use
     */
    public Detector(DetectorOptions options)
    {
		this.options = options;
		this.status = DetectorStatus.STOPPED;
    }

    /**
     * Creates a new Detector instance using the default options
     */
    public Detector()
    {
		this(new DetectorOptions());
    }

    /**
     * Starts the detection from the camera stream
     * 
     * @throws Exception
     *             in case of every kind of exception
     */
    public void start() throws Exception
    {
		this.status = DetectorStatus.STARTED;
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
	
		while (canvasFrame.isVisible() && (frame = grabber.grab()) != null)
		{
		    this.status = DetectorStatus.CAPTURING;
	
		    if (currImage == null)
				currImage = frame.clone();
		    else
		    {
				prevImage = currImage.clone();
				currImage = frame.clone();
		    }
	
		    if (prevImage != null && currImage != null)
		    {
				diff.setImages(prevImage, currImage);
				diff.calculateDifference(prevImage, currImage);
				canvasFrame.showImage(diff.getDiffImage());
		    }
		}
		this.status = DetectorStatus.STOPPED;
    }

    /**
     * Stops the detection
     */
    public void stop()
    {
		this.status = DetectorStatus.STOPPED;
    }

    /**
     * Gets the current detection status
     * @return the current status of the detector
     */
    public DetectorStatus getStatus()
    {
		return this.status;
    }

    /**
     * Enumerates all the possible status of the detector
     * 
     * @author Luciano Mammino, Andrea Mangano
     * @version 1.0
     */
    public enum DetectorStatus
    {
		/**
		 * Status acquired when the detector is stopped. It is the initial
		 * status of the detector.
		 */
		STOPPED,
	
		/**
		 * Transitional status between {@link #STOPPED} and {@link #CAPTURING}
		 */
		STARTED,
	
		/**
		 * Status acquired when the stream is being processed
		 */
		CAPTURING,
	
		/**
		 * Status acquired for a certain amount of time when an abnormal
		 * movement is detected
		 */
		WARNING
    }

}