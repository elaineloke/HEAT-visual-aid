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

import managers.ActionManager;
import managers.FileManager;
import managers.InterpreterManager;
import managers.SettingsManager;
import managers.ThemeManager;
//import managers.ThemeManager;
import managers.WindowManager;
import view.toolbars.MainMenu;
import managers.UndoManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

//import javazoom.jl.player.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.io.File;

/**
 * Main HEAT class
 */
public class Main {
  
  /**
   * Used to run HEAT
   * @param args
   */
public static void main(String[] args) {
	
	//popup window for theme selector
	
//	final JFrame themeSelector = new JFrame();
//	final JButton darkMode = new JButton();
//	final JButton lightMode = new JButton();
//	final JButton contrastMode = new JButton();
//	
//	final JTextArea themeText = new JTextArea();
//	final JTextArea themeText2 = new JTextArea();
//
//	themeText.setText("         Select your theme");
//
//
//	themeText.setFont(new FontUIResource("Arial", 30, 30));
//	themeText.setLineWrap(true);
//	themeText.setEditable(false);
//
//	darkMode.setText("Dark Mode");
//	lightMode.setText("Light Mode");
//	contrastMode.setText("Contrast Mode");
//	
//	darkMode.setSize(new Dimension(200,100));
//	
//	darkMode.setPreferredSize(new Dimension(200,100));
//	lightMode.setPreferredSize(new Dimension(200,100));
//	contrastMode.setPreferredSize(new Dimension(200,100));
//	darkMode.setFont(new FontUIResource("Arial", 30, 30));
//	lightMode.setFont(new FontUIResource("Arial", 30, 30));
//	contrastMode.setFont(new FontUIResource("Arial", 30, 30));
//
//	themeSelector.setLayout(new BorderLayout());
//	
//	themeSelector.add(themeText, BorderLayout.NORTH);
//	themeSelector.add(darkMode, BorderLayout.EAST);
//	themeSelector.add(lightMode, BorderLayout.WEST);
//	themeSelector.add(contrastMode, BorderLayout.SOUTH);
//	themeSelector.pack();
//	themeSelector.setLocationRelativeTo(null);
//
//	darkMode.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent evt) {
//			// TODO Auto-generated method stub
//			try {
//				UIManager.setLookAndFeel(new FlatDarkLaf());
//			} catch (Exception e) {
//				System.err.print("Could not set the selected dark theme.");
//			}
//		
//			FlatLaf.updateUI();
//			
//			darkMode.setPreferredSize(new Dimension(200,100));
//			lightMode.setPreferredSize(new Dimension(200,100));
//			contrastMode.setPreferredSize(new Dimension(200,100));
//			
//			darkMode.setFont(new FontUIResource("Arial", 30, 30));
//			lightMode.setFont(new FontUIResource("Arial", 30, 30));
//			contrastMode.setFont(new FontUIResource("Arial", 30, 30));
//			
//			ThemeManager tm = new ThemeManager();
//			tm.setThemeFontSize();
//
//		}
//	});
//	
//	lightMode.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent evt) {
//			// TODO Auto-generated method stub
//			try {
//				UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
//			} catch (Exception e) {
//				System.err.print("Could not set the selected dark theme.");
//			}
//			
//			FlatLaf.updateUI();
//
//		}
//	});
//	
//	contrastMode.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent evt) {
//			// TODO Auto-generated method stub
//			try {
//				UIManager.setLookAndFeel(new FlatHighContrastIJTheme());
//			} catch (Exception e) {
//				System.err.print("Could not set the selected dark theme.");
//			}
//			
//			FlatLaf.updateUI();
//
//		}
//	});
	
	
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
    wm.setLookAndFeel();
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
	ThemeManager tm = new ThemeManager();
	tm.popUpTheme();
   }
		
}
	

  
