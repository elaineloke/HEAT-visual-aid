package managers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHiberbeeDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import com.formdev.flatlaf.ui.FlatPanelUI;

import utils.Settings;
import utils.jsyntax.JEditTextArea;
import view.windows.HelpWindow;
import view.windows.OptionsWindow;


/**
 * Theme Manager stores the methods needed to set the theme across the software
 * @author el345
 *
 */

public class ThemeManager {
	
	private static Component JEditTextArea;
	private static WindowManager wm = new WindowManager();
	private static SettingsManager sm = new SettingsManager();
	private JButton darkMode;
	private JButton lightMode;
	private JButton contrastMode;
	private JButton apply;
	private JFrame themeSelector;
	private JTextPane themeText;	
	
	//Changes the theme according to the selection
	
	public static void changeTheme(String themeName) {
		
		
		if(themeName.equals("SelectDarkTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatDarkLaf() );
			} catch( Exception ex ) {
		        System.err.println( "Error: The selected theme failed to be applied." );
		    }
			
			FlatLaf.updateUI();
			sm.setSetting(Settings.ACCESSIBILITY_THEME, "True");
			sm.saveSettings();
		}
		
		if(themeName.equals("SelectDarkOrangeTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatArcDarkOrangeIJTheme() );
			} catch( Exception ex ) {
		        System.err.println( "Error: The selected theme failed to be applied.");
		    }
			
			FlatLaf.updateUI();
			sm.setSetting(Settings.ACCESSIBILITY_THEME, "True");
			sm.saveSettings();
		}
		
		if(themeName.equals("SelectLightTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatLightLaf() );
			} catch( Exception ex ) {
		        System.err.println( "Error: The selected theme failed to be applied.");
		    }
			
			FlatLaf.updateUI();
			sm.setSetting(Settings.ACCESSIBILITY_THEME, "True");
			sm.saveSettings();
		}
		
		if(themeName.equals("SelectHiberbeeTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatHiberbeeDarkIJTheme() );
			} catch( Exception ex ) {
		        System.err.println( "Error: The selected theme failed to be applied.");
		    }
			
			FlatLaf.updateUI();
			sm.setSetting(Settings.ACCESSIBILITY_THEME, "True");
			sm.saveSettings();
		}
		
		if(themeName.equals("SelectGreenTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatGradiantoNatureGreenIJTheme() );
			} catch( Exception ex ) {
		        System.err.println(  "Error: The selected theme failed to be applied." );
		    }
			
			FlatLaf.updateUI();
			sm.setSetting(Settings.ACCESSIBILITY_THEME, "True");
			sm.saveSettings();
		}
		
		if(themeName.equals("SelectVuesionTheme")) {
			try {
				UIManager.setLookAndFeel(new FlatVuesionIJTheme() );
			} catch( Exception ex ) {
		        System.err.println(  "Error: The selected theme failed to be applied." );
		    }
			
			FlatLaf.updateUI();
			sm.setSetting(Settings.ACCESSIBILITY_THEME, "True");
			sm.saveSettings();
		}
		
		if(themeName.equals("SelectContrastTheme")) {
			
			FlatLaf.updateUI();
			try {
				UIManager.setLookAndFeel(new FlatHighContrastIJTheme() );
			} catch( Exception ex ) {
		        System.err.println(  "Error: The selected contrast theme failed to be applied." );
		    }
			
			FlatLaf.updateUI();
			sm.setSetting(Settings.ACCESSIBILITY_THEME, "True");
			sm.saveSettings();

		}
	}
	
	
		/*
		 *Creates a pop up window for user to select a theme when HEAT launches 
		 *Then implements the theme to the chosen selection
		 */
		
		public void popUpTheme() {
		
		themeSelector = new JFrame();
		darkMode = new JButton();
		lightMode = new JButton();
		contrastMode = new JButton();
		apply = new JButton();
		themeText = new JTextPane();
	
		themeText.setText("Select your theme");
		
		//align the theme text in the center
		StyledDocument doc = themeText.getStyledDocument();
		SimpleAttributeSet sas = new SimpleAttributeSet();
		StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), sas, false);
	
		themeText.setFont(new FontUIResource("Arial", 30, 30));
		themeText.setEditable(false);
	
		darkMode.setText("Dark Mode");
		lightMode.setText("Light Mode");
		contrastMode.setText("Contrast Mode");
		apply.setText("Apply");
		
		darkMode.setPreferredSize(new Dimension(200,100));
		lightMode.setPreferredSize(new Dimension(200,100));
		contrastMode.setPreferredSize(new Dimension(200,100));
		darkMode.setFont(new FontUIResource("Arial", 30, 30));
		lightMode.setFont(new FontUIResource("Arial", 30, 30));
		contrastMode.setFont(new FontUIResource("Arial", 30, 30));
		
		apply.setPreferredSize(new Dimension(200,100));
		apply.setFont(new FontUIResource("Arial", 30, 30));

		
		lightMode.setBackground(Color.white);
		lightMode.setOpaque(true);
		lightMode.setForeground(Color.black);
		lightMode.setBorder(new LineBorder(Color.gray));
		
		darkMode.setBackground(Color.darkGray);
		darkMode.setOpaque(true);
		darkMode.setForeground(Color.lightGray);
		darkMode.setBorder(new LineBorder(Color.gray));
		
		contrastMode.setBackground(Color.black);
		contrastMode.setOpaque(true);
		contrastMode.setForeground(Color.white);
		contrastMode.setBorder(new LineBorder(Color.cyan));
		
		themeSelector.setLayout(new BorderLayout());
		
		themeSelector.add(themeText, BorderLayout.NORTH);
		themeSelector.add(darkMode, BorderLayout.EAST);
		themeSelector.add(lightMode, BorderLayout.WEST);
		themeSelector.add(contrastMode, BorderLayout.CENTER);
		themeSelector.add(apply, BorderLayout.SOUTH);

		themeSelector.pack();		
		themeSelector.setVisible(true);
  	
		darkMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {

				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
				} catch (Exception e) {
					System.err.print("Could not set the selected dark theme.");
				}
				
				FlatLaf.updateUI();
				setThemeFontSize();

			}
		});
		
		lightMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				try {
					UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
				} catch (Exception e) {
					System.err.print("Could not set the selected dark theme.");
				}
				
				FlatLaf.updateUI();
				setThemeFontSize();
			}
		});
		
		contrastMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				try {
					UIManager.setLookAndFeel(new FlatHighContrastIJTheme());
				} catch (Exception e) {
					System.err.print("Could not set the selected dark theme.");
				}
				
				FlatLaf.updateUI();
				setThemeFontSize();
			}
		});
		
		apply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				themeSelector.dispose();
			}
		});
	}
		
		
		/*
		 * Sets the font size of the texts in the pop up window
		 */
		
		public void setThemeFontSize() {
			
			themeText.setFont(new FontUIResource("Arial", 30, 30));
			darkMode.setPreferredSize(new Dimension(200,100));
			lightMode.setPreferredSize(new Dimension(200,100));
			contrastMode.setPreferredSize(new Dimension(200,100));
			apply.setPreferredSize(new Dimension(200,100));		

			darkMode.setFont(new FontUIResource("Arial", 30, 30));
			lightMode.setFont(new FontUIResource("Arial", 30, 30));
			contrastMode.setFont(new FontUIResource("Arial", 30, 30));
			apply.setFont(new FontUIResource("Arial", 30, 30));

		}
		
//		public void darkTheme() {
//			
//		}
		

}
