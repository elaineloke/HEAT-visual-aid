/** 
 *
 * Copyright (c) 2005 University of Kent
 * Computing Laboratory, Canterbury, Kent, CT2 7NP, U.K
 *
 * This software is the confidential and proprietary information of the
 * Computing Laboratory of the University of Kent ("Confidential Information").
 * You shall not disclose such confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * the University.
 *
 */

//import com.incors.plaf.alloy.AlloyLookAndFeel;

import managers.FileManager;
import managers.InterpreterManager;
import managers.SettingsManager;
import managers.WindowManager;
import managers.UndoManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import javazoom.jl.player.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Main HEAT class
 */
public class Main {
  
  /**
   * Used to run HEAT
   * @param args
   */
	
public static void main(String[] args) {
	
	try {
		UIManager.setLookAndFeel( new FlatDarculaLaf() );
    } catch( Exception ex ) {
        System.err.println( "Failed to initialize Laf" );
    }
	
    new Zoom();
	
    Logger log = Logger.getLogger("heat");
    try {
        log.setUseParentHandlers(false);  // turn off logging on stdout console
        FileHandler handler = new FileHandler(System.getProperty("user.home") + File.separator + "heat.log");
        handler.setFormatter(new SimpleFormatter());
        log.addHandler(handler);
    } catch (Exception e) {
        System.out.println("Could not install file handler for logging.");
    }
    
    System.setProperty("com.apple.mrj.application.apple.menu.about.name", ""); // set name of main menu on Mac
    System.setProperty("apple.laf.useScreenMenuBar", "true");  // on Mac separate menu from window
	  
    SettingsManager sm = SettingsManager.getInstance();
    WindowManager wm = WindowManager.getInstance();

    sm.loadSettings();
    wm.createGUI();

    if (sm.isNewSettingsFile())
      wm.showWizardWindow();
      // will also start interpreter process
    else {
      // FileManager fm = FileManager.getInstance();
      // fm.saveTemporary();

      InterpreterManager im = InterpreterManager.getInstance();
      im.startProcess(false);
    }
    

    if (args.length > 0) {
    	wm.openFile(new java.io.File(args[0]));
        wm.showAll();
        /* Make edit area active */
        wm.getEditorWindow().grabFocus(); 
    } else {
        wm.getEditorWindow().setEditorContent("Use menu to open an existing or create a new program in the editor.");
        wm.setCloseEnabled(false);
        UndoManager.getInstance().reset();
        wm.onlyConsole();
        wm.getConsoleWindow().getFocus();
    }
    wm.setVisible();

    
    //try adding method here ;)
    final JFrame option = new JFrame();
    JButton yes = new JButton();
    JButton no = new JButton();
    JTextArea text = new JTextArea(5,40);
    
    final JFrame info = new JFrame();
    JTextArea commands = new JTextArea(0,0);
    
    JRootPane rootPane = option.getRootPane(); 

    commands.setText("Ctrl + Q - quit \nCtrl + O - open a file \n"
    		+ "Escape - closes window \nCtrl + P - print editor content or interpreter console \n"
    		+ "Ctrl + D - Opens HEAT options \nCtrl + Z - undo \nCtrl + Y - redo \n"
    		+ "Ctrl + F - find and replace in page \nCtrl + X - cut \nCtrl + C - copy \n"
    		+ "Ctrl + V - paste \nCtrl + L - load compile programme, load program into interpreter and compile it \n"
    		+ "Ctrl + I - interrupt interpreter \nCtrl + T - check properties \n"
    		+ "Ctrl + H - display help \nFor the console window: \nE - sends evaluation to interpreter \n"
    		+ "S - save path and continue");
    commands.setEditable(false);
    info.add(commands);
    info.pack();
    
    text.setText("Do you require keyboard shortcuts to be read aloud? If yes press enter");
    text.setLineWrap(true);
    text.setEditable(false);
    yes.setText("Yes");
    no.setText("No");
    option.setLayout(new BorderLayout());
    option.add(text, BorderLayout.NORTH);
    option.add(yes, BorderLayout.WEST);
    option.add(no, BorderLayout.EAST);
    option.pack();
    option.setVisible(true);
    
    try {
    	String x = new java.io.File("src").getAbsolutePath();
    	FileInputStream mp3_file = new FileInputStream(x + "\\audio" + "\\openingVoice.mp3");
    	Player mp3 = new Player(mp3_file);
    	System.out.println(x);
    	mp3.play();}
    catch(Exception e) {
    	System.out.println(e);
    }
    
    rootPane.setDefaultButton(yes);
    
    yes.addActionListener(new ActionListener() {
    	
    	@Override
        public void actionPerformed(ActionEvent e) {
            option.dispose();
            try {
            	String x = new java.io.File("src").getAbsolutePath();
            	FileInputStream mp3_file = new FileInputStream(x + "\\audio" + "\\commandsv.mp3");
            	Player mp3 = new Player(mp3_file);
            	System.out.println(x);
            	mp3.play();
            	Thread.currentThread();
				Thread.sleep(10000);
            	info.dispose();}
            catch(Exception e2) {
            	System.out.println(e2);
            }           
        }
    });
    
    no.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setVisible(true);
        	option.dispose();
        }
    });
    

    //end of added method
    
   }
}
  
