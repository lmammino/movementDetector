package com.oryzone.mvdetector;

/**
 * Class that runs up the app
 * 
 * @author Andrea Mangano<amangano@oryzone.com>
 * @author Luciano Mammino<lmammino@oryzone.com>
 * @version 1.0
 */
public class Main
{
    /**
     * Method called automatically as starting point of the application
     * 
     * @param args
     *            console args
     * @todo Catch all the generic exceptions and provide a method to handle
     *       them (logging and reporting)
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
	Detector d = new Detector();
	d.start();
    }
}
