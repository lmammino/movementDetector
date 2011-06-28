package com.oryzone.mvdetector.warningAction;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.oryzone.mvdetector.Detector;
import com.oryzone.mvdetector.gui.ConsoleWindow;
import java.util.Date;

/**
 * Abstract class to ease the implementation of warning actions
 * @author Andrea Mangano, Luciano Mammino
 * @version 1.0
 * @see IWarningAction
 */
public class AbstractWarningAction implements IWarningAction{

    /**
     * The current console window instance
     */
    protected ConsoleWindow console;


    /**
     * The current detector instance
     */
    protected Detector detector;


    /**
     * Constructor. Creates a new warning action referencing to a given console
     * instance
     * @param console the console instance to use
     */
    public AbstractWarningAction(ConsoleWindow console, Detector detector)
    {
        this.console = console;
        this.detector = detector;
    }


    @Override
    public void onWarningStart(IplImage frame, Date date)
    {
        
    }


    @Override
    public void onWarningEnd(IplImage[] frames, Date started, Date ended)
    {

    }


    @Override
    public void onWarningSignal(IplImage frame, Date date)
    {

    }


    @Override
    public ConsoleWindow getConsole()
    {
        return this.console;
    }


    @Override
    public Detector getDetector()
    {
        return this.detector;
    }




}
