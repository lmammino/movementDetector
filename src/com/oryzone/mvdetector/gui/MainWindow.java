package com.oryzone.mvdetector.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.oryzone.mvdetector.AppInfo;
import com.oryzone.mvdetector.Detector;

/**
 * Main GUI window.
 * Built with SWT.
 * @author Luciano Mammino, Andrea Mangano
 * @version 1.0
 */
public class MainWindow
{
    protected Display display;
    protected Shell shell;
    protected Canvas canvas;
    protected Detector detector;
    
    
    public MainWindow()
    {
	this.prepareApplication();
	this.display = new Display();
	
	this.shell = new Shell(this.display);
	this.initUI();
    }
    
    
    protected void prepareApplication()
    {
	Display.setAppName( AppInfo.NAME );
	Display.setAppVersion( AppInfo.VERSION );
    }
    
    
    protected void initUI()
    {
	this.shell.setText("MovementDetector");
        this.shell.setSize(640, 480);
        this.shell.setLayout(new GridLayout(1, true));
        
        Image img_record = null;
        Image img_stop = null;
        Image img_settings = null;
        
        try {
            img_record = new Image(this.display, "src/images/media-record.png");
            img_stop = new Image(this.display, "src/images/media-playback-stop.png");
            img_settings = new Image(this.display, "src/images/preferences-system.png");
        } catch (Exception e) {
            System.out.println("Cannot load images");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
        ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
        ToolItem btn_record = new ToolItem(toolBar, SWT.PUSH);
        btn_record.setText("Start");
        btn_record.setImage(img_record);
        ToolItem btn_stop = new ToolItem(toolBar, SWT.PUSH);
        btn_stop.setText("Stop");
        btn_stop.setImage(img_stop);
        new ToolItem(toolBar, SWT.SEPARATOR);
        ToolItem btn_settings = new ToolItem(toolBar, SWT.PUSH);
        btn_settings.setImage(img_settings);
        new ToolItem(toolBar, SWT.SEPARATOR);
        
        btn_record.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                
        	detector = new Detector();
        	try
		{
        	    detector.start();
		} catch (Exception e)
		{
		    e.printStackTrace();
		}
        	
            }
        });
        
        btn_stop.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                
        	if(detector != null)
        	    detector.stop();
            }
        });
        
        
        toolBar.pack();
        
        
        
    }
    
    
    public Display getDisplay()
    {
	return this.display;
    }
    
    
    public Shell getShell()
    {
	return this.shell;
    }
    
    
    public void show()
    {
	this.center();
	this.shell.open();
	while (!this.shell.isDisposed()) {
	    if (!this.display.readAndDispatch()) {
		this.display.sleep();
	    }
	}
    }
    
    public void center()
    {
	Rectangle bds = this.display.getBounds();

        Point p = this.shell.getSize();

        int nLeft = (bds.width - p.x) / 2;
        int nTop = (bds.height - p.y) / 2;

        this.shell.setBounds(nLeft, nTop, p.x, p.y);
    }
    
    
}
