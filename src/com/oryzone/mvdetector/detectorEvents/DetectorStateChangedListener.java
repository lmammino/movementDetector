package com.oryzone.mvdetector.detectorEvents;

import java.util.EventListener;


/**
 * Interface that describes the methods that every class which wants to catch
 * Detector state changed events should expose
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public interface DetectorStateChangedListener extends EventListener
{
    
    /**
     * Events fired when the detector status changes
     * @param e the event object
     */
    public void onDetectorStateChanged(DetectorStateChangedEvent e);
    
}
