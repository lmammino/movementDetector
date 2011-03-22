package com.oryzone.mvdetector;

import com.oryzone.mvdetector.gui.MainWindow;
import javax.swing.UIManager;

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
    public static void main(String[] args)
<<<<<<< HEAD
    {
        Main.makeAppNative();
        Main.setSystemLookAndFeel();

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MainWindow().setVisible(true);
            }

        });
    }
    

    /**
     * Sets the system look and feel
     */
    protected static void setSystemLookAndFeel()
    {
        try
        {
            // set up default l&f and remember Menu Bar UI
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            String mbUI = UIManager.getString("MenuBarUI");

            // set up our custom l&f, then override Menu Bar UI
            UIManager.put("MenuBarUI", mbUI);
        } catch (Exception e)
        {
            System.err.println("Cannot set system Look and Feel");
        }
    }


    /**
     * Executes code to make the application look more native on different
     * operative systems
     */
    protected static void makeAppNative()
    {
        String lcOSName = System.getProperty("os.name").toLowerCase();
        boolean IS_OSX = lcOSName.startsWith("mac os x");

        if( IS_OSX )
            Main.makeAppOSXNative();

    }


    /**
     * Make the app OSX native
     */
    private static void makeAppOSXNative()
    {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.macos.smallTabs", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", AppInfo.NAME);
=======
    {	
	MainWindow w = new MainWindow();
	w.show();
	w.getDisplay().dispose();
>>>>>>> 29cfb7eb707ee6c3886fd3ca93bed632d55b68df
    }

}
