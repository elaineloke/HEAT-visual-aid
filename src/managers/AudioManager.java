package managers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.plaf.FontUIResource;

import javazoom.jl.player.Player;

public class AudioManager {
	
	private JFrame option;
	private JFrame info;
	private JButton yes;
	private JButton no;
	private JTextArea text;
	private JTextArea commands;
	
	public void createAudioPopUP() {

	
	 //audio method - create required objects
	option = new JFrame();
	yes = new JButton();
	no = new JButton();
	text = new JTextArea(5,45);
    
	info = new JFrame();
	commands = new JTextArea(0,0);
    
    JRootPane rootPane = option.getRootPane(); 
    
    //Adding text to all components and setting dimensions
    commands.setFont(new FontUIResource("Arial",20, 20));
    commands.setText("Ctrl + Q - quit \nCtrl + O - open a file \n"
    		+ "Escape - closes window \nCtrl + P - print editor content or interpreter console \n"
    		+ "Ctrl + D - Opens HEAT options \nCtrl + Z - undo \nCtrl + Y - redo \n"
    		+ "Ctrl + F - find and replace in page \nCtrl + X - cut \nCtrl + C - copy \n"
    		+ "Ctrl + V - paste \nCtrl + L - load compile programme, load program into interpreter and compile it \n"
    		+ "Ctrl + I - interrupt interpreter \nCtrl + T - check properties \n"
    		+ "Ctrl + H - display help \nFor the console window: \nE - sends evaluation to interpreter \n"
    		+ "S - save path and continue");
    commands.setEditable(false);
    info.add(commands);
    info.pack();
    
    text.setText("Do you require keyboard shortcuts to be read aloud? \n\nIf yes press enter");
    text.setFont(new FontUIResource("Arial",30, 30));
    text.setPreferredSize(new Dimension(80,80));
    text.setLineWrap(true);
    text.setEditable(false);
    
    yes.setText("Yes");
    yes.setPreferredSize(new Dimension(250,100));
    yes.setFont(new FontUIResource("Arial",20, 40));
    
    no.setText("No");
    no.setPreferredSize(new Dimension(250,100));
    no.setFont(new FontUIResource("Arial",20, 40));
    
    //Adding buttons to JFrame and adding a layout, making the yes/no pop up visible
    option.setPreferredSize(new Dimension(550,400));
    option.setLayout(new BorderLayout());
    option.add(text, BorderLayout.NORTH);
    option.add(yes, BorderLayout.WEST);
    option.add(no, BorderLayout.EAST);
    option.pack();
    option.setLocationRelativeTo(null);
    option.setVisible(true);
    
    
    //Audio asking if audio shortcuts are necessary
    try {
    	String audioPath = new java.io.File("src").getAbsolutePath();
    	FileInputStream mp3_file = new FileInputStream(audioPath + "\\audio" + "\\openingVoice.mp3");
    	Player mp3 = new Player(mp3_file);
    	System.out.println(audioPath);
    	mp3.play();}
    catch(Exception e) {
    	System.out.println(e);
    }
    
    //Causing yes button to activate when enter is pressed, and the audio short cuts mp3 to activate
    rootPane.setDefaultButton(yes);
    
    yes.addActionListener(new ActionListener() {
    	
    	@Override
        public void actionPerformed(ActionEvent e) {
            option.dispose();
            try {
            	String x = new java.io.File("src").getAbsolutePath();
            	FileInputStream mp3_file = new FileInputStream(x + "\\audio" + "\\commandsv.mp3");
            	Player mp3 = new Player(mp3_file);
            	System.out.println(x);
            	mp3.play();
            	info.dispose();}
            catch(Exception e2) {
            	System.out.println(e2);
            }           
        }
    });
    
    //Closes option window and shows text version of shortcuts
    no.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        info.setLocationRelativeTo(null);
    	info.setVisible(true);
    	option.dispose();
    }
});
	}

//end of audio method

}
