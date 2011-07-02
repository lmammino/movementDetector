package com.oryzone.mvdetector.detectorActions;

import java.awt.Toolkit;


/**
 * Play system beep action class
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public class PlayBeepAction implements IDetectorAction
{

    @Override
    public void doAction()
    {
        Toolkit.getDefaultToolkit().beep();
    }
    
}
