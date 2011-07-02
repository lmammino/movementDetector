package com.oryzone.mvdetector.detectorActions;

import java.util.Date;
import com.oryzone.mvdetector.FaceDetector;
import java.io.File;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 * Action that saves a frame on the user directory and may optionally use
 * face detection
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public class SaveFrameAction implements IDetectorAction
{

    protected boolean useFaceDetection;
    protected FaceDetector detector;
    protected File path;
    protected IplImage frame;
    
    public SaveFrameAction()
    {
        this.useFaceDetection = false;
        this.detector = new FaceDetector();
        this.path = new File(System.getProperty("user.home") + File.separator + "MovementDetector");
        if(!this.path.exists())
            this.path.mkdir();
    }


    public boolean isUsingFaceDetection()
    {
        return useFaceDetection;
    }


    public SaveFrameAction setUseFaceDetection(boolean useFaceDetection)
    {
        this.useFaceDetection = useFaceDetection;
        return this;
    }


    public IplImage getFrame()
    {
        return frame;
    }


    public SaveFrameAction setFrame(IplImage frame)
    {
        this.frame = frame;
        return this;
    }
    
    

    @Override
    public void doAction()
    {
        IplImage frame = this.frame;
        
        if(this.useFaceDetection)
        {
            frame = this.detector.detectAndDraw(this.frame);
        }
        
        cvSaveImage(this.path.getAbsolutePath() + File.separator + (new Date()).getTime() + ".jpg", frame);
    }
    
}
