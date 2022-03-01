package managers;

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

import view.windows.HelpWindow;
import view.windows.OptionsWindow;

public class ThemeManager {
	
	public static void changeTheme(String laf) {
		if(laf.equals("SelectDarkTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatDarkLaf() );
			} catch( Exception ex ) {
		        System.err.println( "Failed to initialize Laf" );
		    }
			
			FlatLaf.updateUI();
		}
		
		if(laf.equals("SelectDarkOrangeTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatArcDarkOrangeIJTheme() );
			} catch( Exception ex ) {
		        System.err.println( "Failed to initialize Laf" );
		    }
			
			FlatLaf.updateUI();
		}
		
		if(laf.equals("SelectLightTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatLightLaf() );
			} catch( Exception ex ) {
		        System.err.println( "Failed to initialize Laf" );
		    }
			
			FlatLaf.updateUI();
		}
		
		if(laf.equals("SelectHiberbeeTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatHiberbeeDarkIJTheme() );
			} catch( Exception ex ) {
		        System.err.println( "Failed to initialize Laf" );
		    }
			
			FlatLaf.updateUI();
		}
		
		if(laf.equals("SelectGreenTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatGradiantoNatureGreenIJTheme() );
			} catch( Exception ex ) {
		        System.err.println( "Failed to initialize Laf" );
		    }
			
			FlatLaf.updateUI();
		}
		
		if(laf.equals("SelectVuesionTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatVuesionIJTheme() );
			} catch( Exception ex ) {
		        System.err.println( "Failed to initialize Laf" );
		    }
			
			FlatLaf.updateUI();
		}
		
		if(laf.equals("SelectContrastTheme")) {
			
			FlatLaf.updateUI();
			try {
				UIManager.setLookAndFeel(new FlatHighContrastIJTheme() );
			} catch( Exception ex ) {
		        System.err.println( "Failed to initialize Laf" );
		    }
			
			FlatLaf.updateUI();
			
		}
	}
}
