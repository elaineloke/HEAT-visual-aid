/**
 * 
 */
package unitTesting;

import static org.junit.Assert.*;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

import view.windows.AboutWindow;

/**
 * @author cw692
 *
 */
public class AboutWindowTest {

	/**
	 * Test method for testing if each targeted elements is changed after a new font size passed in.
	 * Test: 1. if check font size of each JLaebl and JButton element is set to expected size.
	 * Test: 2. check compList only include JLaebl and JButton elements for now and font size of each item is updated.
	 */
	@Test
	public void testSetFontSize() {
			AboutWindow aw = new AboutWindow();
			int expectedFontSize = 18;
			aw.setFontSize(expectedFontSize);
			int countForChangedComp = 0;
			int compNotChange = 0;
			
			JPanel jpMain = aw.getJpMain();
			Component[] components = jpMain.getComponents();
			
			ArrayList<Component> compList = new ArrayList<>();
			for(Component comp: components) {
				if(comp instanceof JPanel) {
				  	JPanel tempPanel = (JPanel) comp;
				  	Collections.addAll(compList, tempPanel.getComponents());
				}
			}
			for(Component comp: compList) {
				if(comp instanceof JLabel || comp instanceof JButton) {
					countForChangedComp++;
					JComponent tempJComp = (JComponent) comp;
					// check font size of each JLaebl and JButton element is set to expected size.
					assertEquals(expectedFontSize, tempJComp.getFont().getSize());
				}
				if(!(comp instanceof JLabel) && !(comp instanceof JButton)) {
					compNotChange++;
				}
			}
			System.out.println("Font size of each JLabel and JButton element is set to expected font size: pass");
			
			// check compList only include JLaebl and JButton elements for now and font size of each item is updated.
			assertEquals(countForChangedComp, compList.size()-compNotChange);
			System.out.println("only JLabel and JButton elements are set to expected font size: pass");
	}

}
