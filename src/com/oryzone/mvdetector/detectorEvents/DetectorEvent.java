/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oryzone.mvdetector.detectorEvents;

import com.oryzone.mvdetector.Detector;
import java.util.EventObject;

/**
 *
 * @author Luciano
 */
public class DetectorEvent extends EventObject {

    protected Detector detector;

    public DetectorEvent(Detector source)
    {
        super(source);
        this.detector = source;
    }

    public DetectorEvent()
    {
        this(null);
    }

    public Detector getDetector()
    {
        return this.detector;
    }

}
