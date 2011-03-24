/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainWindow.java
 *
 * Created on 22-mar-2011, 0.04.32
 */
package com.oryzone.mvdetector.gui;

import com.oryzone.mvdetector.Detector;
import java.awt.Dimension;
import java.awt.Toolkit;


/**
 *
 * @author Luciano
 */
public class MainWindow extends javax.swing.JFrame
{

    protected OptionsWindow optionsWindow;
    protected ConsoleWindow console;
    protected Detector detector;


    public MainWindow()
    {
        initComponents();
        initCustomComponents();
        centerInScreen();

        console.toggle()
                .log("Application started!");
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btn_start = new javax.swing.JButton();
        btn_stop = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_options = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        canvas.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        canvas.setPreferredSize(new java.awt.Dimension(665, 460));
        canvas.setLayout(new java.awt.BorderLayout());
        getContentPane().add(canvas, java.awt.BorderLayout.CENTER);

        jToolBar1.setBorder(null);
        jToolBar1.setRollover(true);

        btn_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/media-record.png"))); // NOI18N
        btn_start.setText("Start");
        btn_start.setFocusable(false);
        btn_start.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_start.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_start);

        btn_stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/media-playback-stop.png"))); // NOI18N
        btn_stop.setText("Stop");
        btn_stop.setFocusable(false);
        btn_stop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_stop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_stop);
        jToolBar1.add(jSeparator1);

        btn_options.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/preferences-system.png"))); // NOI18N
        btn_options.setText("Options");
        btn_options.setFocusable(false);
        btn_options.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_options.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_optionsActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_options);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void initCustomComponents()
    {
        this.optionsWindow = new OptionsWindow();
        this.console = new ConsoleWindow();
    }

    private void centerInScreen()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width-this.getSize().width)/2,
                        (dim.height-this.getSize().height)/2);
    }

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        this.detector = new Detector();
        detector.start();
    }//GEN-LAST:event_btn_startActionPerformed

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
        this.detector.stop();
        this.detector = null;
    }//GEN-LAST:event_btn_stopActionPerformed

    private void btn_optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_optionsActionPerformed
        this.optionsWindow.setVisible(!(this.optionsWindow.isVisible()));
    }//GEN-LAST:event_btn_optionsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_options;
    private javax.swing.JButton btn_start;
    private javax.swing.JButton btn_stop;
    private javax.swing.JPanel canvas;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
