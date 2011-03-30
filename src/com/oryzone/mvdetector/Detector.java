package com.oryzone.mvdetector;

import com.oryzone.mvdetector.detectorEvents.WarningSignalEvent;
import com.oryzone.mvdetector.detectorEvents.WarningEndedEvent;
import com.oryzone.mvdetector.detectorEvents.WarningStartedEvent;
import com.oryzone.mvdetector.detectorEvents.WarningListener;
import javax.swing.event.EventListenerList;
import java.util.Date;
import com.googlecode.javacv.*;
import static com.googlecode.javacv.cpp.opencv_core.*;

/**
 * Class that handles the whole logic of capturing the video stream and
 * processing it
 * 
 * @author Andrea Mangano, Luciano Mammino
 * @version 1.0
 */
public class Detector implements Runnable
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
     * The stream frame grabber
     */
    protected OpenCVFrameGrabber grabber;

    /**
     * The frame used to display the video stream
     */
    protected CanvasFrame canvasFrame;

    /**
     * The image difference processor
     */
    protected ImageDifference imageDifference;

    /**
     * The current thread
     */
    protected Thread thread;

    /**
     * Flag used to activate/deactivate the Thread
     */
    protected boolean isThreadActive = false;


    /**
     * The date when the last warning occurred
     */
    protected Date warningActivationDate;


    /**
     * The date when the last warning period ended
     */
    protected Date warningDeactivationDate;
    

    /**
     * List used to store the attached listeners for the warning events
     */
    protected EventListenerList warningListeners;

    
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
	this.grabber = new OpenCVFrameGrabber(0);
	this.canvasFrame = new CanvasFrame("Capturing");
	this.canvasFrame.setCanvasSize(640, 480);
	this.grabber.setImageWidth(640);
	this.grabber.setImageHeight(480);
	this.imageDifference = new ImageDifference();
        this.warningListeners = new EventListenerList();
    }

    /**
     * Creates a new Detector instance using the default options
     */
    public Detector()
    {
	this(new DetectorOptions());
    }

    /**
     * Gets the image difference instance
     * 
     * @return the current image difference instance
     */
    public ImageDifference getImageDifference()
    {
	return this.imageDifference;
    }

    /**
     * Starts the detection from the camera stream
     * 
     * @throws Exception
     *             in case of every kind of exception
     */
    public void start()
    {
	if (this.thread == null)
	    this.thread = new Thread(this);

	this.isThreadActive = true;
	this.thread.start();
    }

    public void run()
    {
	try
	{
	    this.canvasFrame.setCanvasSize(640, 480);

	    this.status = DetectorStatus.STARTED;
	    this.grabber.start();

	    IplImage frame = grabber.grab();
	    IplImage currImage = null;
	    IplImage prevImage = null;

            this.status = DetectorStatus.CAPTURING;
	    while ((frame = grabber.grab()) != null && this.isThreadActive)
	    {
		if (currImage == null)
		    currImage = frame.clone();
		else
		{
		    prevImage = currImage.clone();
		    currImage = frame.clone();
		}

		if (prevImage != null && currImage != null)
		{
		    this.imageDifference.setImages(prevImage, currImage);
		    this.imageDifference.calculateDifference(prevImage,
			    currImage);
		    this.canvasFrame.showImage(this.imageDifference
			    .getDiffImage());

                    this.handleWarning();
		}
	    }
	    this.status = DetectorStatus.STOPPED;
	} catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    /**
     * Stops the detection
     */
    public void stop()
    {
	this.canvasFrame.setVisible(false);
	this.isThreadActive = false;
	this.status = DetectorStatus.STOPPED;
	try
	{
	    grabber.stop();
	} catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    /**
     * Gets the current detection status
     * 
     * @return the current status of the detector
     */
    public DetectorStatus getStatus()
    {
	return this.status;
    }


    /**
     * handles Warning
     */
    protected void handleWarning()
    {
        boolean isWarning = false;

        if(this.imageDifference.differencePercent >= this.options.getWarningSensibility())
        {
            this.warningActivationDate = new Date();
            this.fireWarningSignalEvent(new WarningSignalEvent(this));
            isWarning = true;
        }

        
        if(this.status == DetectorStatus.WARNING)
        {
            
            Date now = new Date();


            if( now.getTime() > this.warningActivationDate.getTime() + (this.options.getWarningDuration() * 1000) )
            {
                //deactivate warning
                this.warningDeactivationDate = new Date();
                this.status = DetectorStatus.CAPTURING;
                this.fireWarningEndedEvent(new WarningEndedEvent(this));
            }


        }
        else
        {
            if(isWarning)
            {
                //activate warning
                this.status = DetectorStatus.WARNING;
                this.fireWarningStartedEvent(new WarningStartedEvent(this));
            }

        }
    }


    /**
     * Adds a new warning listener
     * @param listener the listener to add
     * @return the same instance of {@link Detector} for method chaining
     */
    public Detector addWarningListener(WarningListener listener)
    {
        this.warningListeners.add(WarningListener.class, listener);
        return this;
    }


    /**
     * Removes a previously attached warning listener
     * @param listener the previously attached listener
     * @return the same instance of {@link Detector} for method chaining
     */
    public Detector removeWarningListener(WarningListener listener)
    {
        this.warningListeners.remove(WarningListener.class, listener);
        return this;
    }


    /**
     * Fires the warning started event to all the attached listeners
     * @param event the event object
     */
    protected void fireWarningStartedEvent(WarningStartedEvent event)
    {
        WarningListener[] listeners = this.warningListeners.getListeners(WarningListener.class);
        for (int i = 0; i < listeners.length; i++)
        {
            listeners[i].onWarningStarted(event);
        }
    }


    /**
     * Fires the warning ended event to all the attached listeners
     * @param event the event object
     */
    protected void fireWarningEndedEvent(WarningEndedEvent event)
    {
        WarningListener[] listeners = this.warningListeners.getListeners(WarningListener.class);
        for (int i = 0; i < listeners.length; i++)
        {
            listeners[i].onWarningEnded(event);
        }
    }


    /**
     * Fires the warning ended event to all the attached listeners
     * @param event the event object
     */
    protected void fireWarningSignalEvent(WarningSignalEvent event)
    {
        WarningListener[] listeners = this.warningListeners.getListeners(WarningListener.class);
        for (int i = 0; i < listeners.length; i++)
        {
            listeners[i].onWarningSignal(event);
        }
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