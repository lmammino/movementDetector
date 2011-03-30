package com.oryzone.mvdetector.detectorEvents;

import com.oryzone.mvdetector.Detector;

/**
 * Event class to describe the Warning ended event
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 * @see WarningStartedEvent
 */
public class WarningEndedEvent extends DetectorEvent
{

    /**
     * Constructor
     * @param detector the detector that fired the event
     */
    public WarningEndedEvent(Detector detector)
    {
        super(detector);
    }

}