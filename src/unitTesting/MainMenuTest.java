/**
 * 
 */
package unitTesting;

import static org.junit.Assert.*;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.MenuElement;

import org.junit.Test;

import view.toolbars.MainMenu;

/**
 * @author cw692
 *
 */
public class MainMenuTest {

	/**
	 * Test method for testing if each element inside the JMenuBar is changed after a new font size passed in.
	 * Test: 1. if all menus are set to the selected font size
	 * Test: 2. if all menu items are set to the selected font size
	 * Test: 3. make sure only JSeperator items are not set to the selected font size
	 */
	@Test
	public void testSetFontSize() {
		MainMenu mainMenu = new MainMenu();
		int expectedFontSize = 18;
		mainMenu.setFontSize(expectedFontSize);
		ArrayList<Component> compList = new ArrayList<>();
		int count = 0;
		
		JMenuBar menuBar = mainMenu.getMenuBar();
		MenuElement[] menuEles= menuBar.getSubElements();
		  for(MenuElement ele: menuEles) {
			  Component component = ele.getComponent();
			  count++;
			  compList.add(component);
			// test if all menu has a font size as expected.
			  assertEquals(expectedFontSize, component.getFont().getSize());
			  
			  
			  if(component instanceof JMenu) {
				  JMenu menuComponent = (JMenu) component;
				  Component[] itemEles= menuComponent.getMenuComponents();
				  for(Component itemEle: itemEles) {
					  
					  if(itemEle instanceof JMenuItem) {
						  JMenuItem tempMenuItem = (JMenuItem) itemEle;
						  compList.add(tempMenuItem);
					  }
					  if(!(itemEle instanceof JSeparator)) { 
						  count++;
					  }
					  // test if all menuItem has a font size as expected.
					  assertEquals(expectedFontSize, itemEle.getFont().getSize());
				  }

			  }
		  }
		  System.out.println("Text in each JMenu is set to expected font size: pass");
		  System.out.println("Each menuItem is set to expected font size: pass");
		  
		  // make sure only JSeperator items are not set to the expected font size
		  assertEquals(count, compList.size());
		  System.out.println("Only JSeparator elements are no set to expected font size: pass");
	}

}