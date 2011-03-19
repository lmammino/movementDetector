package com.oryzone.mvdetector.warningAction;

import java.util.Date;
import java.util.Hashtable;
import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public interface IWarningAction
{
    public void onWarningStart(IplImage frame, Date date);

    public void onWarningEnd(IplImage[] frames, Date started, Date ended);

    public boolean hasConfig();

    public JPanel getConfigurationPanel();

    public Hashtable<String, Object> getOptions();
}
