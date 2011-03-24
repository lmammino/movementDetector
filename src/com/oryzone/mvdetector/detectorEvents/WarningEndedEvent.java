package com.oryzone.mvdetector.detectorEvents;

import java.util.EventObject;

/**
 * Event class to describe the Warning ended event
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 * @see WarningStartedEvent
 */
public class WarningEndedEvent extends EventObject
{
    public WarningEndedEvent(Object source)
    {
        super(source);
    }
}