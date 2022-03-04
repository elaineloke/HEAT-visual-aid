/**
 * 
 * 
 */
package unitTesting;

import static org.junit.Assert.*;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.junit.Test;

import view.windows.OptionsWindow;

/**
 * @author cw692
 *
 */
public class OptionsWindowTest {
	
	/**
	 * Test method for testing if each targeted elements is changed after a new font size passed in.
	 * Test: 1. if all JButtons are set to the selected font size
	 * Test: 2. if all tabs of JTabbedPanes are set to the selected font size
	 * Test: 3. make sure only JSeperator items are not set to the selected font size
	 */
	@Test
	public void testPanelButtonFontSize() {
		OptionsWindow ow = new OptionsWindow();
		int expectedFontSize = 18;
		final int FONT_SIZE_DIFF = ow.getFontSizeDiff();
		
		JPanel panelButtons = ow.getPanelButtons();
		ow.setFontSize(expectedFontSize);
		
	  //test if all JButtons change to selected font size
	    Component[] panelBtns = panelButtons.getComponents();
	    for(Component panelBtn: panelBtns) {
	    	if(panelBtn instanceof JButton) {
	    		JButton tempBtn = (JButton) panelBtn;
	    		assertEquals(expectedFontSize-FONT_SIZE_DIFF, tempBtn.getFont().getSize());
	    	}
	    }
	    System.out.println("All JButton elements are set to expected font size: pass");
	    
	  //test if all tabs of JTabbedPane are changed to selected font size
	    JTabbedPane tabOptions = ow.getTabsOption();
	    assertEquals(expectedFontSize-FONT_SIZE_DIFF, tabOptions.getFont().getSize());
	    System.out.println("All JTabbedPane elements are set to expected font size: pass");
	  
	    Component[] tabs = tabOptions.getComponents();
	    ArrayList<Component> subSubComp = new ArrayList<>();
	    for(int index=0; index<tabs.length; index++) {
	    	JPanel tempPanel = (JPanel) tabs[index];
	    	Component[] subComps = tempPanel.getComponents();
	    	for(Component subComp: subComps) {
    			if(subComp instanceof JTextField) {
    				// test if all JTextFields change to selected font size
    				JTextField tempTextField = (JTextField) subComp;
    				assertEquals(expectedFontSize-FONT_SIZE_DIFF, tempTextField.getFont().getSize());
    			}
	    		if(subComp instanceof JPanel) {
	    			JPanel subSubPanel = (JPanel) subComp;
	    			Collections.addAll(subSubComp,subSubPanel.getComponents());
	    		}
	    	}
	    }
	    System.out.println("All JLaebl and JButton elements are set to expected font size: pass");
	    
	 // test if all JTextFields change to selected font size
	    for(Component comp: subSubComp) {
	    	assertEquals(expectedFontSize, comp.getFont().getSize());
	    }
	    System.out.println("All TextField elements are set to expected font size: pass");
	    
	}

}
