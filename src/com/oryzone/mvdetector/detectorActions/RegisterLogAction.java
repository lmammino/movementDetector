package com.oryzone.mvdetector.detectorActions;

import com.oryzone.mvdetector.gui.LogWindow;


/**
 * Class used to register log messages on the log window
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public class RegisterLogAction implements IDetectorAction
{
    
    protected LogWindow logWindow;
    protected String message;
    
    public RegisterLogAction()
    {
        this.logWindow = null;
        this.message = null;
    }


    public LogWindow getLogWindow()
    {
        return logWindow;
    }


    public RegisterLogAction setLogWindow(LogWindow logWindow)
    {
        this.logWindow = logWindow;
        return this;
    }


    public String getMessage()
    {
        return message;
    }


    public RegisterLogAction setMessage(String message)
    {
        this.message = message;
        return this;
    }
    
    
    @Override
    public void doAction()
    {
        if(this.logWindow != null && this.message != null)
        {
            this.logWindow.log(this.message);
            if(!this.logWindow.isVisible())
                this.logWindow.setVisible(true);
        }
    }
    
    
    
}
