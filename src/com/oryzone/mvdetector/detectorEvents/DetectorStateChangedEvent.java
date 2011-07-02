package com.oryzone.mvdetector.detectorEvents;

import com.oryzone.mvdetector.Detector;
import com.oryzone.mvdetector.Detector.DetectorState;


/**
 * Class used to fire detector state changed events
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public class DetectorStateChangedEvent extends DetectorEvent
{
    
    protected DetectorState oldState;
    protected DetectorState newState;


    
    public DetectorStateChangedEvent(Detector source, 
                                     DetectorState oldState, 
                                     DetectorState newState)
    {
        super(source);
        this.oldState = oldState;
        this.newState = newState;
    }


    public DetectorState getOldState()
    {
        return this.oldState;
    }

    
    public DetectorState getNewState()
    {
        return this.newState;
    }
    
}
