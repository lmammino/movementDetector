package com.oryzone.mvdetector.detectorEvents;

import java.util.EventListener;

/**
 * Interface that describes the methods that every class which wants to catch
 * warning events should expose
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public interface WarningListener extends EventListener
{

    /**
     * Called when the warning mode starts
     * @param e the event object
     */
    public void onWarningStarted(WarningStartedEvent e);


    /**
     * Called when the warning mode ends
     * @param e the event object
     */
    public void onWarningEnded(WarningEndedEvent e);

}
