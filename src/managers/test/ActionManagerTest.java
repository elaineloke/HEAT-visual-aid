/**
 * 
 */
package managers.test;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.MenuElement;

import org.junit.Test;

import view.toolbars.MainMenu;
import view.windows.OptionsWindow;

/**
 * @author wcy12
 *
 */
public class ActionManagerTest {

	@Test
	public void changeMenuFontTest() {
		OptionsWindow ow = new OptionsWindow();
		String selectedFont = ow.getMenuFontSizeComboBox().getSelectedItem().toString();	
		int selectedFontInInt = Integer.parseInt(selectedFont);
		
		MainMenu mainMenu = new MainMenu();
		// expected to set the font in each JMenu and JMenuItem to 10px;
        mainMenu.setFontSize(selectedFontInInt);
        
        JMenuBar jMenuBar = mainMenu.getToolBar();
  	  	MenuElement[] menuEles= jMenuBar.getSubElements();
  	  	//a loop to iterate through the JMenuBar
  	  	for(MenuElement ele: menuEles) {
  	  		Component component = ele.getComponent();

  	  		if(component instanceof JMenu) {
  	  			JMenu menuComponent = (JMenu) component;
  	  			// check if each JMenu has the same font sizes int values as the selected value from JComboBox
  	  			assertEquals(selectedFontInInt, menuComponent.getFont().getSize());
  	  			Component[] itemEles= menuComponent.getMenuComponents();
  	  			for(Component itemEle: itemEles) {
  	  				if(itemEle instanceof JMenuItem) {
  	  				JMenuItem jMenuItem = (JMenuItem) itemEle;
  	  				// check if each JMenuItem has the same font sizes int values as the selected value from JComboBox
  	  				assertEquals(selectedFontInInt, jMenuItem.getFont().getSize());
  	  				}	
  	  			}
  	  		}
  	  	}
	}

}
