package com.oryzone.mvdetector;

import com.oryzone.mvdetector.gui.MainWindow;

/**
 * Class that runs up the application
 * 
 * @author Andrea Mangano, Luciano Mammino
 * @version 1.0
 */
public class Main
{
    /**
     * Method called automatically as starting point of the application
     * 
     * @param args
     *            console args
     * @throws Exception
     */
    ///TODO: Catch all the generic exceptions and provide a method to handle them (logging and reporting)
    public static void main(String[] args) throws Exception
    {
	//Detector d = new Detector();
	//d.start();
	
	MainWindow w = new MainWindow();
	w.show();
	w.getDisplay().dispose();
    }
}
