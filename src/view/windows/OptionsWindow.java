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
 * @author Dean Ashton
 * @author Olaf Chitil
 * 
 * Handles the Option Dialog for users to change several settings,
 * in particular information about the Haskell interpreter and font sizes.
 *
 */

package view.windows;

import managers.ActionManager;
import managers.SettingsManager;
import managers.WindowManager;

import utils.Settings;

import view.dialogs.FileDialogs;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.MenuElement;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


/**
 * Window for altering HEATs settings
 */
public class OptionsWindow {

  private JPanel panelOptions;
  private JTextField jTextFieldInterpreterPath; 
  private JTextField jTextFieldOptions;
  private JTextField jTextFieldLibraryPath;
  private JTextField jTextFieldTestFunction;
  private JTextField jTextFieldTestPositive;
  private JComboBox jcbOutputFontSize;
  private JComboBox jcbMenuFontSize;
  private JComboBox jcbCodeFontSize;
  private JDialog dialog;
  private JTabbedPane tabOptions = new JTabbedPane();
  private JPanel panelButtons;

  private SettingsManager sm = SettingsManager.getInstance();
  private WindowManager wm = WindowManager.getInstance();
  // a constant that is set to make differences between font size to enable emphasis.
  private static final int FONT_SIZE_DIFF = 4;
  
  
  /**
   * Creates a new OptionsWindow object.
   */
  public OptionsWindow() {
    try {
      jbInit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  private void jbInit() throws Exception {
    
    // panel for interpreter options:
    JPanel panelInterpreter = new JPanel(new GridLayout(0,1));
    JButton browse = new JButton("Browse");
    browse.setToolTipText("Browse for file");
    browse.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          interpreterPath_actionPerformed();
        }
      });
    JPanel panelInterpreterPath = new JPanel();
    panelInterpreterPath.add(new JLabel("Full path of the Haskell interpreter ghci (not ghc or winghci!): "));
    panelInterpreterPath.add(browse);
    panelInterpreter.add(panelInterpreterPath);
    jTextFieldInterpreterPath = new JTextField();
    panelInterpreter.add(jTextFieldInterpreterPath);
    panelInterpreter.add(new JSeparator(SwingConstants.HORIZONTAL));
    // panelInterpreter.add(new JLabel("")); // some vertical space
    
    JPanel panelOptionsInfo = new JPanel();
    panelOptionsInfo.add(new JLabel("Command line options for the Haskell interpreter:"));
    panelInterpreter.add(panelOptionsInfo);
    jTextFieldOptions = new JTextField();
    panelInterpreter.add(jTextFieldOptions);
    panelInterpreter.add(new JSeparator(SwingConstants.HORIZONTAL));
    // panelInterpreter.add(new JLabel("")); // some vertical space
    JButton browseL = new JButton("Browse");
    browseL.setToolTipText("Browse for directory");
    browseL.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          libraryPath_actionPerformed();
        }
      });
    JPanel panelLibraryPath = new JPanel();
    panelLibraryPath.add(new JLabel("Directory for additional Haskell libraries: "));
    panelLibraryPath.add(browseL);
    panelInterpreter.add(panelLibraryPath);
    jTextFieldLibraryPath = new JTextField();
    panelInterpreter.add(jTextFieldLibraryPath);
    
    // panel for test settings
    JPanel panelTest = new JPanel(new GridLayout(0,1));
    JPanel testFunction = new JPanel(new GridLayout(0,1));
    testFunction.add(new JLabel("Test function applied to a test property"));
    testFunction.add(new JLabel("(e.g. \"quickCheck\" for QuickCheck or \"\" (nothing) for Boolean properties)"));
    jTextFieldTestFunction = new JTextField();
    testFunction.add(jTextFieldTestFunction);
    panelTest.add(testFunction);
    // panelTest.add(new JLabel(""));  // some vertical space
    panelTest.add(new JSeparator(SwingConstants.HORIZONTAL));
    JPanel testPositive = new JPanel(new GridLayout(0,1));
    testPositive.add(new JLabel("String that appears in successful test output"));
    testPositive.add(new JLabel("(e.g. \"+++ OK, passed\" for QuickCheck or \"True\" for Boolean properties)"));
    jTextFieldTestPositive = new JTextField();
    testPositive.add(jTextFieldTestPositive);
    panelTest.add(testPositive);
    
    // panel for setting font sizes
    JPanel panelFontSizes = new JPanel(new GridLayout(0,1));
    jcbOutputFontSize = new JComboBox();
    jcbMenuFontSize = new JComboBox();
    jcbCodeFontSize = new JComboBox();
 /* Populate the font size combo boxes */
    for (int i = 14; i <= 30; i+=2) {
      jcbOutputFontSize.addItem(String.valueOf(i));
      jcbMenuFontSize.addItem(String.valueOf(i));
      jcbCodeFontSize.addItem(String.valueOf(i));
    }
    JPanel editorFontSize = new JPanel();
    editorFontSize.add(new JLabel("Editor font size: "));
    editorFontSize.add(jcbCodeFontSize);
    JPanel menuFontSize = new JPanel();
    menuFontSize.add(new JLabel("Menu and Tree Window font size: "));
    menuFontSize.add(jcbMenuFontSize);
    JPanel interpreterFontSize = new JPanel();
    interpreterFontSize.add(new JLabel("Interpreter font size:"));
    interpreterFontSize.add(jcbOutputFontSize);
    panelFontSizes.add(editorFontSize);
    panelFontSizes.add(menuFontSize);
    panelFontSizes.add(interpreterFontSize);
    
    // combine panels on tabbed pane
    tabOptions.addTab("Haskell Interpreter", panelInterpreter);
    tabOptions.addTab("Property Tests", panelTest);
    tabOptions.addTab("Font Sizes", panelFontSizes);
    
    // buttons for applying options and cancellation
    JButton buttonApply = new JButton("Apply");
    buttonApply.setAction(ActionManager.getInstance().getSaveOptionsAction());
    JButton buttonCancel = new JButton("Cancel");
    buttonCancel.setToolTipText("Close options dialog without applying any changes");
    buttonCancel.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          close();
        }
      });
    panelButtons = new JPanel();
    panelButtons.add(buttonApply);
    panelButtons.add(buttonCancel);
    
    // put all together
    panelOptions = new JPanel(new BorderLayout());
    panelOptions.add(tabOptions,BorderLayout.CENTER);
    panelOptions.add(panelButtons,BorderLayout.PAGE_END);

    /* Use font size from settings if it exists */
    String fontSize = sm.getSetting(Settings.MENU_FONT_SIZE);
    if ((fontSize != null) && (fontSize != "")) setFontSize(Integer.parseInt(fontSize));
    
  }

  public int getFontSizeDiff() {
	  return FONT_SIZE_DIFF;
  }
  
  public JTabbedPane getTabsOption() {
	  return tabOptions;
  }
  
  public JPanel getPanelButtons() {
	  return panelButtons;
  }
  
  /**
   * Update font size used in option window
   * 
   * @param ptSize desired font size
   */
  public void setFontSize(int ptSize) {
	    Component[] panelBtns = panelButtons.getComponents();
	    for(Component panelBtn: panelBtns) {
	    	panelBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, ptSize-FONT_SIZE_DIFF));
	    }
	    //test merge
	    Component[] tabs = tabOptions.getComponents();
	    tabOptions.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, ptSize-FONT_SIZE_DIFF));
	    
	    ArrayList<Component> subSubComp = new ArrayList<>();
	    for(int index=0; index<tabs.length; index++) {
	    	//change tabs' font size
	    	// get 3 tabbed JPanel
	    	JPanel tempPanel = (JPanel) tabs[index];
	    	// get all sub-components inside 3 JPanels
	    	Component[] subComps = tempPanel.getComponents();
	    	for(Component subComp: subComps) {
    			if(subComp instanceof JTextField) {
    				// change font setting of JTextFields under 3 tabbed JPanel at this level
    				JTextField tempTextField = (JTextField) subComp;
    				tempTextField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, ptSize-FONT_SIZE_DIFF));
    			}
	    		if(subComp instanceof JPanel) {
	    			JPanel subSubPanel = (JPanel) subComp;
	    			// get all sub-sub-components inside subcomponents 
	    			Collections.addAll(subSubComp,subSubPanel.getComponents());
	    		}
	    	}
	    }
	    //change font setting of any components inside sub-components
	    for(Component comp: subSubComp) {
	    	comp.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, ptSize));
	    }
	    
  }

  /**
   * Displays the options window
   */
  public void show() {
    getProperties();

    dialog = new JDialog(wm.getMainScreenFrame(), "Options");
    dialog.setModal(true);
    dialog.getContentPane().add(panelOptions);      //(jTabbedPane1);
    dialog.setMinimumSize(new Dimension(1000,450));
    dialog.setSize(1400, 700);
    dialog.setLocationRelativeTo(wm.getMainScreenFrame());
    dialog.setVisible(true);
    
    dialog.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
  }

  /**
   * Closes the options window
   */
  public void close() {
    if (dialog != null)
      dialog.dispose();
  }

  /**
   * Gets the properties from the properties file, and sets them in the display
   */
  public void getProperties() {
    jTextFieldInterpreterPath.setText(sm.getSetting(Settings.INTERPRETER_PATH));
    jTextFieldOptions.setText(sm.getSetting(Settings.INTERPRETER_OPTS));
    jTextFieldLibraryPath.setText(sm.getSetting(Settings.LIBRARY_PATH));
    jTextFieldTestFunction.setText(sm.getSetting(Settings.TEST_FUNCTION));
    jTextFieldTestPositive.setText(sm.getSetting(Settings.TEST_POSITIVE));
    jcbOutputFontSize.setSelectedItem(sm.getSetting(Settings.OUTPUT_FONT_SIZE));
    jcbMenuFontSize.setSelectedItem(sm.getSetting(Settings.MENU_FONT_SIZE));
    jcbCodeFontSize.setSelectedItem(sm.getSetting(Settings.CODE_FONT_SIZE));
  }

 
  /**
   * Returns the current Interpreter path
   *
   * @return the Interpreter path
   */
  public String getInterpreterPath() {
    return jTextFieldInterpreterPath.getText();
  }
  
  /**
   * Returns the Interpreter options
   *
   * @return the Interpreter options
   */
  public String getInterpreterOpts() {
    return jTextFieldOptions.getText();
  }

  /**
   * Returns the location for temporary files
   *
   * @return the location for temporary files
   */
  public String getLibraryPath() {
    return jTextFieldLibraryPath.getText();
  }
  
  public String getTestFunction() {
      return jTextFieldTestFunction.getText();
  }

  public String getTestPositive() {
      return jTextFieldTestPositive.getText();
  }
  
  /**
   * Returns the desired font size for output window
   *
   * @return the output window font size
   */
  public String getOuputFontSize() {
    return (String) jcbOutputFontSize.getSelectedItem();
  }
  
  /**
   * Returns the desired font size for menu
   *
   * @return the menu font size
   */
  public String getMenuFontSize() {
    return (String) jcbMenuFontSize.getSelectedItem();
  }

  /**
   * Returns the desired font size for display window
   *
   * @return the display window font size
   */
  public String getCodeFontSize() {
    return (String) jcbCodeFontSize.getSelectedItem();
  }

  private void jButton2_actionPerformed(ActionEvent e) {
    close();
  }

//  private void jbUpdate_actionPerformed(ActionEvent e) {
//    close();
//  }

  
  public JComboBox getMenuFontSizeComboBox(){
	  return jcbMenuFontSize;
  }
  
  /**
   * Browse for an interpreter file with full path
   */
  private void interpreterPath_actionPerformed() {
    File selectedFile = FileDialogs.getInstance().showDefaultFileChooser(
            new File(jTextFieldInterpreterPath.getText()));

    if (selectedFile != null) {
      jTextFieldInterpreterPath.setText(selectedFile.getAbsolutePath());
    }
  }

  /**
   * Browse for a library path
   */
  private void libraryPath_actionPerformed() {
    File selectedFile = FileDialogs.getInstance().showDefaultDirChooser(
            new File(jTextFieldLibraryPath.getText()));

    if (selectedFile != null)
      jTextFieldLibraryPath.setText(selectedFile.getAbsolutePath());
  }
}
