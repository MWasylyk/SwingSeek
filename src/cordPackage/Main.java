// By Michael Wasylyk
package cordPackage;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import seekPackage.SeekBar;

public class Main {
	SeekBar seekBar = new SeekBar();
	JFrame mainFrame = new JFrame();

	public static void main(String[] args) {
		// Instantiate the JFrame object
		Main m = new Main();
	}
	
	// Swing startup code
	public Main(){
		mainFrame.setTitle("Main Window");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		mainFrame.setVisible(true);
		init();
	}
	
	public void init(){
		// Layeredpane used for laying out jcomponents
		
		// Panel for holding main JComponents
		JLayeredPane mainPane = new JLayeredPane();
		mainFrame.setSize(300, 300);
		
		// Setup code for the main panel
		mainPane.setLayout(null);
		mainPane.setLocation(0,0);
		mainPane.setSize(300, 300);
		mainPane.setOpaque(true);
		mainPane.setBackground(Color.LIGHT_GRAY);
		
		seekBar.addMouseListener(seekBar);
		
		// Set location of seekBar
		seekBar.setLocation(20, 200);
		
		// Set size of seekBar
		seekBar.setSize(250, 65);
		seekBar.setMaxTime(300);
		seekBar.setSeekLocation(0);
		
		// Add JComponents to the main panel and the JFrame
		mainPane.add(seekBar,0);
		mainFrame.add(mainPane);
		mainFrame.setResizable(false);
	}


}
