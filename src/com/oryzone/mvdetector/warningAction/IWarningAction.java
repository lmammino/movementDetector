package com.oryzone.mvdetector.warningAction;

import java.util.Date;
import java.util.Hashtable;
import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 * Interface used to define a warning action.
 * A warning action can be registered to be executed when some
 * movements are detected and the application goes to warning mode.
 * When the warning mode starts the method {@link #onWarningStart} is fired,
 * instead, when the warning mode ends, the method {@link #onWarningEnd} is fired.
 * Each warning action is configurable and can expose its own configuration panel
 * as a Swing {@link javax.swing.JPanel JPanel}.
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public interface IWarningAction
{
    /**
     * Method automatically called when the application detects a movement and
     * goes to the warning mode
     * @param frame the frame that produced the warning mode
     * @param date the date when the warning mode started
     */
    public void onWarningStart(IplImage frame, Date date);

    /**
     * Method automatically called when the warning mode ends.
     * @param frames the array of frames recorded during the warning mode
     * @param started the date when the warning mode started
     * @param ended the date when the warning mode ended
     */
    public void onWarningEnd(IplImage[] frames, Date started, Date ended);

    
    /**
     * Returns <code>true</code> if the warning action has configuration,
     * <code>false</code> otherwise.
     * @return a boolean value
     */
    public boolean hasConfig();

    
    /**
     * Gets the configuration panel
     * @return the configuration panel as {@link javax.swing.JPanel JPanel}.
     */
    public JPanel getConfigurationPanel();

    
    /**
     * Gets the options
     * @return the options as an <code>Hashtable<String, Object></code>
     */
    public Hashtable<String, Object> getOptions();
}
