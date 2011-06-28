package com.oryzone.mvdetector.detectorEvents;

import com.oryzone.mvdetector.Detector;
import java.util.EventObject;

/**
 * Base class for all the detector events
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 * @see WarningEndedEvent, WarningStartedEvent, WarningSignalEvent
 */
public class DetectorEvent extends EventObject {

    /**
     * The instance of the detector that fired the event
     */
    protected Detector detector;

    
    /**
     * Creates a new DetectorEvent
     * @param source the {@link Detector} which fired the event
     */
    public DetectorEvent(Detector source)
    {
        super(source);
        this.detector = source;
    }


    /**
     * Creates a new DetectorEvent without a source {@link Detector}
     */
    public DetectorEvent()
    {
        this(null);
    }


    /**
     * Gets the source {@link Detector}
     * @return the source 
     */
    public Detector getDetector()
    {
        return this.detector;
    }

}
