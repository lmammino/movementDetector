package com.oryzone.mvdetector.detectorEvents;

import java.util.EventObject;

/**
 * Event class to describe the Warning started event
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 * @see WarningEndedEvent
 */
public class WarningStartedEvent extends EventObject
{
    public WarningStartedEvent(Object source)
    {
        super(source);
    }
}
