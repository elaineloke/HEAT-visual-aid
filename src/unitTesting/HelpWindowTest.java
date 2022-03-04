/**
 * 
 */
package unitTesting;

import static org.junit.Assert.*;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;

import org.junit.Test;

import view.windows.HelpWindow;

/**
 * @author cw692
 *
 */
public class HelpWindowTest {

	/**
	 * Test method for testing if each targeted elements is changed after a new font size passed in.
	 * Test: 1. if all JButtons are set to the selected font size
	 * Test: 2. if the tree is set to have a preferred tree set
	 * Test: 3. if all tree nodes is set to have selected font size
	 */
	@Test
	public void testSetFontSize() {
		HelpWindow hw = new HelpWindow();
		int expectedFontSize = 18;
		final int FONT_SIZE_DIFF = hw.getTreeRowHeight();
		hw.setFontSize(expectedFontSize);
		
		
		JPanel jPanel2 = hw.getJPanel2();
		 Component[] btnComps = jPanel2.getComponents();
		  for(Component btnComp: btnComps) {
			 if(btnComp instanceof JButton) {
				 JButton tempBtn = (JButton) btnComp;
				 //test if all JButtons change to selected font size
				 assertEquals(expectedFontSize, tempBtn.getFont().getSize());
			 }
		  }
		  System.out.println("All JButtons are set to expected font size: pass");
		  
		JTree tree = hw.getTree();
		//test if a specific row height is set, i.e. expectedFontSize + FONT_SIZE_DIFF
		assertEquals(expectedFontSize+FONT_SIZE_DIFF, tree.getRowHeight());
		System.out.println("Tree row height are set to expected row height: pass");
		//test if all tree nodes is set to have selected font size
		assertEquals(expectedFontSize, tree.getFont().getSize());
		System.out.println("Each tree node is set to expected font size: pass");

	}

}