package com.oryzone.mvdetector.warningAction;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.oryzone.mvdetector.Detector;
import com.oryzone.mvdetector.gui.LogWindow;
import java.util.Date;

/**
 *
 * @author Andrea Mangano, Luciano Mammino
 * @version 1.0
 * @see
 */
public class RegisterLogWarningAction extends AbstractWarningAction
{

    public RegisterLogWarningAction(LogWindow console, Detector detector)
    {
        super(console, detector);
    }


    @Override
    public void onWarningEnd(IplImage[] frames, Date started, Date ended)
    {
        this.console.log("Warning mode ended");
    }


    @Override
    public void onWarningStart(IplImage frame, Date date)
    {
        this.console.log("Warning mode started");
    }


    @Override
    public void onWarningSignal(IplImage frame, Date date)
    {
        this.console.log("Warning signal (" + this.detector.getImageDifference().getDifferencePercent()*100 + "% difference detected)");
    }


}
