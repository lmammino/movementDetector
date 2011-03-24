package com.oryzone.mvdetector;

import java.io.Serializable;

/**
 * Class used to serialize and deserialize the Detector options
 * 
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public class DetectorOptions implements Serializable
{

    /**
     * Serial version UID used to serialize the class (automatically generated
     * by Eclipse)
     */
    private static final long serialVersionUID = 879312245269330066L;

    
    /**
     * The sensibility of the warning level. It is the frames difference
     * threshold limit after wich the warning mode starts.
     */
    protected float warningSensibility;

    
    /**
     * The duration of the warning mode in seconds
     */
    protected int warningDuration;


    /**
     * Constuctor. Creates an instance of the DetectorOptions with the default
     * options
     */
    public DetectorOptions()
    {
        this.warningSensibility = .05f;
        this.warningDuration = 5;
    }


    /**
     * @return the warningSensibility
     */
    public float getWarningSensibility()
    {
        return warningSensibility;
    }


    /**
     * @param warningSensibility the warningSensibility to set
     */
    public void setWarningSensibility(float warningSensibility)
    {
        this.warningSensibility = warningSensibility;
    }


    /**
     * @return the warningDuration
     */
    public int getWarningDuration()
    {
        return warningDuration;
    }


    /**
     * @param warningDuration the warningDuration to set
     */
    public void setWarningDuration(int warningDuration)
    {
        this.warningDuration = warningDuration;
    }

}
