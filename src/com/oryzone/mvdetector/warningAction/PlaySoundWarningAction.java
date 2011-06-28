package com.oryzone.mvdetector.warningAction;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.oryzone.mvdetector.Detector;
import com.oryzone.mvdetector.gui.ConsoleWindow;
import java.util.Date;

/**
 * Plays a sound when a warning signal occurs
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public class PlaySoundWarningAction extends AbstractWarningAction
{

    /**
     * The audio file to play
     */
    protected String audio;


    /**
     * Creates a new instance of PlaySoundWarningAction using a given console
     * instance a given audio file
     * @param console the console instance
     * @param detector the detector instance
     * @param audio the audio file to play
     */
    public PlaySoundWarningAction(ConsoleWindow console, Detector detector, String audio)
    {
        super(console, detector);
        this.audio = audio;
    }


    /**
     * @return the audio
     */
    public String getAudio()
    {
        return audio;
    }


    /**
     * @param audio the audio to set
     */
    public void setAudio(String audio)
    {
        this.audio = audio;
    }



    @Override
    public void onWarningSignal(IplImage frame, Date date)
    {
        super.onWarningSignal(frame, date);
        System.out.println("BEEEEEEEEEP!"); ///TODO add code to play sound
    }

}
