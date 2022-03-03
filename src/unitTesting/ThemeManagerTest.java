package unitTesting;

import static org.junit.Assert.*;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import managers.SettingsManager;
import managers.ThemeManager;
import utils.Settings;

/**
 * @author el345
 *
 */

public class ThemeManagerTest {

	@Test
	public void testAllThemes() {
		
		
		ThemeManager tm = new ThemeManager();
		SettingsManager sm = SettingsManager.getInstance();

		//test dark theme
		sm.loadSettings();
		tm.changeTheme("SelectDarkTheme");
		
		assertEquals("The settings must be set to true for dark theme", "True", sm.getSetting(Settings.ACCESSIBILITY_THEME));
		System.out.println("Dark theme change: pass");
	
		//test dark orange theme
		sm.loadSettings();
		tm.changeTheme("SelectDarkOrangeTheme");
		
		assertEquals("The settings must be set to true for dark orange theme", "True", sm.getSetting(Settings.ACCESSIBILITY_THEME));
		System.out.println("Dark orange theme change: pass");

		
		//test light theme
		sm.loadSettings();
		tm.changeTheme("SelectLightTheme");
		
		assertEquals("The settings must be set to true for light theme", "True", sm.getSetting(Settings.ACCESSIBILITY_THEME));
		System.out.println("Light theme change: pass");
		
		//test hiberbee dark theme
		sm.loadSettings();
		tm.changeTheme("SelectHiberbeeTheme");
		
		assertEquals("The settings must be set to true for hiberbee theme", "True", sm.getSetting(Settings.ACCESSIBILITY_THEME));
		System.out.println("Hiberbee dark theme change: pass");
		
		//test nature green theme
		sm.loadSettings();
		tm.changeTheme("SelectGreenTheme");
		
		assertEquals("The settings must be set to true for hiberbee theme", "True", sm.getSetting(Settings.ACCESSIBILITY_THEME));
		System.out.println("Nature green theme change: pass");
		
		//test vuesion theme
		sm.loadSettings();
		tm.changeTheme("SelectVuesionTheme");
		
		assertEquals("The settings must be set to true for vuesion theme", "True", sm.getSetting(Settings.ACCESSIBILITY_THEME));
		System.out.println("Vuesion theme change: pass");
		
		//test high contrast theme
		sm.loadSettings();
		tm.changeTheme("SelectContrastTheme");
		
		assertEquals("The settings must be set to true for high contrast theme", "True", sm.getSetting(Settings.ACCESSIBILITY_THEME));
		System.out.println("High contrast theme change: pass");

		
	}
}
