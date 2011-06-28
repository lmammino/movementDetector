package com.oryzone.mvdetector;

import com.oryzone.mvdetector.differenceStrategy.RgbDifferenceStrategy;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.prefs.Preferences;

/**
 * Class used to serialize and deserialize the Detector options
 * 
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public class DetectorOptions implements Serializable
{
    /**
     * Preferences keys
     */
    protected final static String
            OPT_WARNINGSENSIBILITY = "warningSensibility",
            OPT_WARNINGDURATION = "warningDuration",
            OPT_FRAMEDIMENSIONWIDTH = "frameDimensionWidth",
            OPT_FRAMEDIMENSIONHEIGHT = "frameDimensionHeight",
            OPT_USECOLOREDDIFFERENCE = "useColoredDifference";


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
     * The size of the frame
     */
    protected Dimension frameDimension;


    /**
     * If <code>true</code> uses the {@link RgbDifferenceStrategy} as frame
     * differencing method.
     */
    protected boolean useColoredDifference;


    /**
     * Constuctor. Creates an instance of the DetectorOptions with the default
     * options
     */
    public DetectorOptions()
    {
        this.loadDefaults();
    }


    /**
     * Loads the default options
     */
    public void loadDefaults()
    {
        this.warningSensibility = .05f;
        this.warningDuration = 5;
        this.frameDimension = new Dimension(640, 480);
        this.useColoredDifference = false;
    }

    /**
     * Gets the preference object associated to the program
     * @return
     */
    protected Preferences getPreferences()
    {
        return Preferences.userNodeForPackage(Main.class);
    }


    /**
     * Loads the options frome the stored options
     */
    public void load()
    {
        Preferences pref = this.getPreferences();
        this.warningSensibility = pref.getFloat(DetectorOptions.OPT_WARNINGSENSIBILITY, this.warningSensibility);
        this.warningDuration = pref.getInt(DetectorOptions.OPT_WARNINGDURATION, this.warningDuration);
        this.setFrameDimension(new Dimension(pref.getInt(DetectorOptions.OPT_FRAMEDIMENSIONWIDTH, this.getFrameDimension().width), pref.getInt(DetectorOptions.OPT_FRAMEDIMENSIONHEIGHT, this.getFrameDimension().height)));
        this.setUseColoredDifference(pref.getBoolean(DetectorOptions.OPT_USECOLOREDDIFFERENCE, this.usingColoredDifference()));
    }


    /**
     * Saves the options
     */
    public void save()
    {
        Preferences pref = this.getPreferences();
        pref.putFloat(DetectorOptions.OPT_WARNINGSENSIBILITY, this.warningSensibility);
        pref.putInt(DetectorOptions.OPT_WARNINGDURATION, this.warningDuration);
        pref.putInt(DetectorOptions.OPT_FRAMEDIMENSIONWIDTH, this.getFrameDimension().width);
        pref.putInt(DetectorOptions.OPT_FRAMEDIMENSIONHEIGHT, this.getFrameDimension().height);
        pref.putBoolean(DetectorOptions.OPT_USECOLOREDDIFFERENCE, this.usingColoredDifference());
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


    /**
     * The size of the frame
     * @return the frameDimension
     */
    public Dimension getFrameDimension()
    {
        return frameDimension;
    }


    /**
     * The size of the frame
     * @param frameDimension the frameDimension to set
     */
    public void setFrameDimension(Dimension frameDimension)
    {
        this.frameDimension = frameDimension;
    }


    /**
     * If <code>true</code> uses the {@link RgbDifferenceStrategy} as frame
     * differencing method.
     * @return the useColoredDifference
     */
    public boolean usingColoredDifference()
    {
        return useColoredDifference;
    }


    /**
     * If <code>true</code> uses the {@link RgbDifferenceStrategy} as frame
     * differencing method.
     * @param useColoredDifference the useColoredDifference to set
     */
    public void setUseColoredDifference(boolean useColoredDifference)
    {
        this.useColoredDifference = useColoredDifference;
    }

}
