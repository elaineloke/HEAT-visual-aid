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
 * @author Dean Ashton, John Travers
 *
 */

package view.toolbars;

import managers.ActionManager;
import managers.SettingsManager;
import utils.Settings;

import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.MenuElement;

import java.awt.Component;

// import com.apple.eawt.Application;




/**
 * The menus used within HEAT
 */
public class MainMenu {
  private JMenuBar jMenuBar = new JMenuBar();

  /* Program menu items */
  private JMenu jMenuFile = new JMenu();
  private JMenuItem jMenuItemOpen = new JMenuItem();
  private JMenuItem jMenuItemCloseFile = new JMenuItem();
  private JMenuItem jMenuItemPrint = new JMenuItem();
  private JMenuItem jMenuItemOptions = new JMenuItem();
  private JMenuItem jMenuItemExit = new JMenuItem();

  /* Edit menu items */
  private JMenu jMenuEdit = new JMenu();
  private JMenuItem jMenuItemCopy = new JMenuItem();
  private JMenuItem jMenuItemCut = new JMenuItem();
  private JMenuItem jMenuItemPaste = new JMenuItem();

  /* Run menu items */
  private JMenu jMenuRun = new JMenu();
  private JMenuItem jMenuItemCompile = new JMenuItem();
  private JMenuItem jMenuItemInterrupt = new JMenuItem();
  private JMenuItem jMenuItemTest = new JMenuItem();

  /* Help menu items */
  private JMenu jMenuHelp = new JMenu();
  private JMenuItem jMenuItemContents = new JMenuItem();
  private JMenuItem jMenuItemAbout = new JMenuItem();
  
  /* Theme selection menu items */
  private JMenu jMenuTheme = new JMenu();
  private JMenuItem jMenuItemLight = new JMenuItem();
  private JMenuItem jMenuItemDark = new JMenuItem();
  private JMenuItem jMenuItemDarkOrange = new JMenuItem();
  private JMenuItem jMenuItemHiberbee = new JMenuItem();
  private JMenuItem jMenuItemGreen = new JMenuItem();
  private JMenuItem jMenuItemVuesion = new JMenuItem();
  private JMenuItem jMenuItemContrast = new JMenuItem();

  private ActionManager.UndoAction undoAction = ActionManager.getInstance()
                                                             .getUndoAction();
  private ActionManager.RedoAction redoAction = ActionManager.getInstance()
                                                             .getRedoAction();
  private JMenuItem jMenuItemSearch = new JMenuItem();
  private SettingsManager sm = SettingsManager.getInstance();

  /**
   * Creates a new MainMenu object.
   */
  public MainMenu() {
    try {
      jbInit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Initialises the menus GUI compenetes
   *
   * @throws Exception if swing components fail to instantiate
   */
  public void jbInit() throws Exception {
    ActionManager am = ActionManager.getInstance();

    /* File Menu */
    jMenuFile.setText("Program");
    jMenuFile.setMnemonic('p');
    jMenuItemOpen.setAction(am.getOpenFileAction());
    // jMenuItemPrint.setText("Print");
    // jMenuItemPrint.setMnemonic('p');
    jMenuItemCloseFile.setAction(am.getCloseFileAction());
    jMenuItemCloseFile.setEnabled(false);
    // jMenuItemPrint.setAction(am.getPrintFileAction());
    jMenuItemOptions.setAction(am.getShowOptionsAction());
    jMenuItemOptions.setMnemonic('o');
    jMenuItemExit.setText("Quit");
    jMenuItemExit.setMnemonic('Q');
    jMenuItemExit.setAction(am.getExitProgramAction());
    jMenuFile.add(jMenuItemOpen);
    jMenuFile.add(jMenuItemCloseFile);
    jMenuFile.addSeparator();
    // printing is too buggy; not essential, hence better exclude
    // jMenuFile.add(jMenuItemPrint);
    // jMenuFile.addSeparator();
    jMenuFile.add(jMenuItemOptions);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuItemExit);

    /* Edit Menu */
    jMenuEdit.setText("Edit");
    jMenuEdit.add(undoAction);
    jMenuEdit.add(redoAction);
    jMenuEdit.addSeparator();
    jMenuEdit.setMnemonic('e');
    jMenuItemCut.setText("Cut");
    jMenuItemCut.setAction(am.getEditCutAction());
    jMenuItemCut.setMnemonic('t');
    jMenuItemCopy.setText("Copy");
    jMenuItemCopy.setAction(am.getEditCopyAction());
    jMenuItemCopy.setMnemonic('c');
    jMenuItemPaste.setText("Paste");
    jMenuItemPaste.setAction(am.getEditPasteAction());
    jMenuItemPaste.setMnemonic('p');
    jMenuItemSearch.setText("Find");
    jMenuItemSearch.setAction(am.getSearchAction());
    jMenuItemPaste.setMnemonic('f');
    jMenuEdit.add(jMenuItemCut);
    jMenuEdit.add(jMenuItemCopy);
    jMenuEdit.add(jMenuItemPaste);
    jMenuEdit.add(jMenuItemSearch);

    /* Run Menu */
    jMenuRun.setText("Run");
    jMenuRun.setMnemonic('r');
    jMenuItemCompile.setAction(am.getCompileAction());
    jMenuItemCompile.setText("Load & Compile");
    //jMenuItemCompile.setMnemonic('c');
    jMenuItemInterrupt.setAction(am.getInterruptAction());
    jMenuItemInterrupt.setText("Interrupt");
    //jMenuItemInterrupt.setMnemonic('i');
    jMenuItemTest.setAction(am.getTestAction());
    jMenuItemTest.setText("Test");
    //jMenuItemTest.setMnemonic('t');
    jMenuRun.add(jMenuItemCompile);
    jMenuRun.add(jMenuItemInterrupt);
    jMenuRun.add(jMenuItemTest);

    /* Help Menu */
    jMenuHelp.setText("Help");
    jMenuHelp.setMnemonic('H');
    jMenuItemContents.setAction(am.getShowHelpAction());
    jMenuItemAbout.setText("About");
    jMenuItemAbout.setAction(am.getShowAboutAction());
    jMenuItemAbout.setMnemonic('a');
    jMenuHelp.add(jMenuItemContents);
    jMenuHelp.addSeparator();
    jMenuHelp.add(jMenuItemAbout);
    
    /* Theme selector menu */
    jMenuTheme.setText("Theme Selector");
    jMenuTheme.setMnemonic('T');
    
    jMenuItemDark.setText("Dark theme");
    jMenuItemDark.setAction(am.selectDarkTheme());
    jMenuTheme.add(jMenuItemDark);
    
    jMenuItemDarkOrange.setText("Dark orange theme");
    jMenuItemDarkOrange.setAction(am.selectDarkOrangeTheme());
    jMenuTheme.add(jMenuItemDarkOrange);
    
    jMenuItemLight.setText("Light theme");
    jMenuItemLight.setAction(am.selectLightTheme());
    jMenuTheme.add(jMenuItemLight);
    
    jMenuItemHiberbee.setText("Hiberbee dark theme");
    jMenuItemHiberbee.setAction(am.selectHiberbeeTheme());
    jMenuTheme.add(jMenuItemHiberbee);
    
    jMenuItemGreen.setText("Gradianto nature green");
    jMenuItemGreen.setAction(am.selectGreenTheme());
    jMenuTheme.add(jMenuItemGreen);
    
    jMenuItemVuesion.setText("Vuesion");
    jMenuItemVuesion.setAction(am.selectVuesionTheme());
    jMenuTheme.add(jMenuItemVuesion);
    
    jMenuItemContrast.setText("Contrast");
    jMenuItemContrast.setAction(am.selectContrastTheme());
    jMenuTheme.add(jMenuItemContrast);

    /* Main Bar */
    jMenuBar.add(jMenuFile);
    jMenuBar.add(jMenuEdit);
    jMenuBar.add(jMenuRun);
    jMenuBar.add(jMenuHelp);
    jMenuBar.add(jMenuTheme);

    
    // Mac specific stuff
    // Application app = Application.getApplication();
    // app.setAboutHandler(null);
    // app.setPreferencesHandler(null);
    // app.setQuitHandler(null);
    
    /* Use font size from settings if it exists */
    String fontSize = sm.getSetting(Settings.MENU_FONT_SIZE);
    if ((fontSize != null) && (fontSize != "")) setFontSize(Integer.parseInt(fontSize));
  
  }

  /**
   * Returns the {@link JMenuBar} 
   *
   * @return the JMenuBar itself
   */
  public JMenuBar getMenuBar() {
    return jMenuBar;
  }

  /**
   * Updates undo and redo command states
   */
  public void updateUndoRedo() {
    undoAction.updateUndoState();
    redoAction.updateRedoState();
    ActionManager am = ActionManager.getInstance();
    am.getToolbarUndoAction().updateUndoState();
    am.getToolbarRedoAction().updateRedoState();
  }

  /**
   * Enables or disables the close file command
   *
   * @param enabled the desired state of the close command
   */
  public void setCloseEnabled(boolean enabled) {
    jMenuItemCloseFile.setEnabled(enabled);
  }
  
    
  public void setInterruptEnabled(boolean enabled) {
    jMenuItemInterrupt.setEnabled(enabled);
  }
  
  public void setTestEnabled(boolean enabled) {
    jMenuItemTest.setEnabled(enabled);
  }
  
  public void setCompileEnabled(boolean enabled) {
    jMenuItemCompile.setEnabled(enabled);
  }
  
  /**
   * Update menu font size
   * 
   * @param ptSize desired font size
   */
  public void setFontSize(int ptSize) {
	  MenuElement[] menuEles= jMenuBar.getSubElements();
	  for(MenuElement ele: menuEles) {
		  Component component = ele.getComponent();
		  component.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, ptSize));
		  
		  if(component instanceof JMenu) {
			  JMenu menuComponent = (JMenu) component;
			  Component[] itemEles= menuComponent.getMenuComponents();
			  for(Component itemEle: itemEles) {
				  itemEle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, ptSize));
			  }
		  }
	  }
  }
}
