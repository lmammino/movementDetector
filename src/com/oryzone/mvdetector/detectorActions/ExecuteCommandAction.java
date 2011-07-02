package com.oryzone.mvdetector.detectorActions;

import com.oryzone.mvdetector.gui.LogWindow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Implements the execute command action
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public class ExecuteCommandAction implements IDetectorAction
{
    
    protected String command;
    protected Runtime runtime;
    protected LogWindow logWindow;
    
    public ExecuteCommandAction()
    {
        this.command = null;
        this.logWindow = null;
        this.runtime = Runtime.getRuntime() ;
    }


    public String getCommand()
    {
        return command;
    }


    public ExecuteCommandAction setCommand(String command)
    {
        this.command = command;
        return this;
    }


    public LogWindow getLogWindow()
    {
        return logWindow;
    }


    public void setLogWindow(LogWindow logWindow)
    {
        this.logWindow = logWindow;
    }


    public Runtime getRuntime()
    {
        return runtime;
    }


    public void setRuntime(Runtime runtime)
    {
        this.runtime = runtime;
    }


    @Override
    public void doAction()
    {
        
        if(this.command != null & !this.command.trim().equals(""))
        {
            try
            {
                Process pr = this.runtime.exec(this.command);
                
                if(this.logWindow != null)
                {
                    this.logWindow.log("Executing: " + this.command);
                    pr.waitFor();
                    BufferedReader buf = new BufferedReader( new InputStreamReader( pr.getInputStream() ) ) ;

                    String line = "";
                    String output = "";
                    
                    while ( ( line = buf.readLine() ) != null ) 
                    {
                        output += line + "\n";
                    }
                    
                    this.logWindow.log(output);
                }
                
                
            } catch (Exception ex)
            {
                if(this.logWindow != null)
                {
                    this.logWindow.log("Error while executing command: " + this.command);
                }
                else
                {
                    Logger.getLogger(ExecuteCommandAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    
    
}
