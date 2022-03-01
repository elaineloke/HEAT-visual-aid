package managers;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHiberbeeDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import com.formdev.flatlaf.ui.FlatPanelUI;

import utils.jsyntax.JEditTextArea;
import view.windows.HelpWindow;
import view.windows.OptionsWindow;

public class ThemeManager {
	
	private static Component JEditTextArea;
	WindowManager wm = new WindowManager();
	
	
	public static void changeTheme(String themeName) {
		
		
		if(themeName.equals("SelectDarkTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatDarkLaf() );
			} catch( Exception ex ) {
		        System.err.println( "Error: The selected theme failed to be applied." );
		    }
			
			FlatLaf.updateUI();
		}
		
		if(themeName.equals("SelectDarkOrangeTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatArcDarkOrangeIJTheme() );
			} catch( Exception ex ) {
		        System.err.println( "Error: The selected theme failed to be applied.");
		    }
			
			FlatLaf.updateUI();
		}
		
		if(themeName.equals("SelectLightTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatLightLaf() );
			} catch( Exception ex ) {
		        System.err.println( "Error: The selected theme failed to be applied.");
		    }
			
			FlatLaf.updateUI();
		}
		
		if(themeName.equals("SelectHiberbeeTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatHiberbeeDarkIJTheme() );
			} catch( Exception ex ) {
		        System.err.println( "Error: The selected theme failed to be applied.");
		    }
			
			FlatLaf.updateUI();
		}
		
		if(themeName.equals("SelectGreenTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatGradiantoNatureGreenIJTheme() );
			} catch( Exception ex ) {
		        System.err.println(  "Error: The selected theme failed to be applied." );
		    }
			
			FlatLaf.updateUI();
		}
		
		if(themeName.equals("SelectVuesionTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatVuesionIJTheme() );
			} catch( Exception ex ) {
		        System.err.println(  "Error: The selected theme failed to be applied." );
		    }
			
			FlatLaf.updateUI();
		}
		
		if(themeName.equals("SelectContrastTheme")) {
			
			FlatLaf.updateUI();
			try {
				UIManager.setLookAndFeel(new FlatHighContrastIJTheme() );
			} catch( Exception ex ) {
		        System.err.println(  "Error: The selected contrast theme failed to be applied." );
		    }
			
			FlatLaf.updateUI();

		}
		
	}
}
