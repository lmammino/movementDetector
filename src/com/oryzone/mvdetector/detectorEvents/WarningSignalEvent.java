package com.oryzone.mvdetector.detectorEvents;

import com.oryzone.mvdetector.Detector;

/**
 * Event class to describe the Warning signal event
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 * @see WarningStartedEvent
 */
public class WarningSignalEvent extends DetectorEvent
{

    /**
     * Constructor
     * @param detector the detector that fired the event
     */
    public WarningSignalEvent(Detector detector)
    {
        super(detector);
    }

}