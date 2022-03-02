package managers;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

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

import utils.jsyntax.JEditTextArea;
import view.windows.HelpWindow;
import view.windows.OptionsWindow;

public class ThemeManager {
	
	private static Component JEditTextArea;
	WindowManager wm = new WindowManager();
	private JButton darkMode;
	private JButton lightMode;
	private JButton contrastMode;
	private JFrame themeSelector;
	private JTextArea themeText;	
	
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
	
	
		//popup window for theme selector
		
		public void popUpTheme() {
		
		themeSelector = new JFrame();
		darkMode = new JButton();
		lightMode = new JButton();
		contrastMode = new JButton();
		themeText = new JTextArea();
	
		themeText.setText("         Select your theme");
	
	
		themeText.setFont(new FontUIResource("Arial", 30, 30));
		themeText.setLineWrap(true);
		themeText.setEditable(false);
	
		darkMode.setText("Dark Mode");
		lightMode.setText("Light Mode");
		contrastMode.setText("Contrast Mode");
		
		darkMode.setPreferredSize(new Dimension(200,100));
		lightMode.setPreferredSize(new Dimension(200,100));
		contrastMode.setPreferredSize(new Dimension(200,100));
		darkMode.setFont(new FontUIResource("Arial", 30, 30));
		lightMode.setFont(new FontUIResource("Arial", 30, 30));
		contrastMode.setFont(new FontUIResource("Arial", 30, 30));
	
		themeSelector.setLayout(new BorderLayout());
		
		themeSelector.add(themeText, BorderLayout.NORTH);
		themeSelector.add(darkMode, BorderLayout.EAST);
		themeSelector.add(lightMode, BorderLayout.WEST);
		themeSelector.add(contrastMode, BorderLayout.SOUTH);
		themeSelector.pack();
		themeSelector.setLocationRelativeTo(null);
		
		themeSelector.setVisible(true);
	
		darkMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				try {
					UIManager.setLookAndFeel(new FlatHighContrastIJTheme());
				} catch (Exception e) {
					System.err.print("Could not set the selected dark theme.");
				}
				
				FlatLaf.updateUI();
				setThemeFontSize();
			}
		});
	}
		
		public void setThemeFontSize() {
			
			themeText.setFont(new FontUIResource("Arial", 30, 30));
			darkMode.setPreferredSize(new Dimension(200,100));
			lightMode.setPreferredSize(new Dimension(200,100));
			contrastMode.setPreferredSize(new Dimension(200,100));
			
			darkMode.setFont(new FontUIResource("Arial", 30, 30));
			lightMode.setFont(new FontUIResource("Arial", 30, 30));
			contrastMode.setFont(new FontUIResource("Arial", 30, 30));
		}
}
