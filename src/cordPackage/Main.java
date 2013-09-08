// By Michael Wasylyk
package cordPackage;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import seekPackage.SeekBar;

public class Main {
	SeekBar seekBar = new SeekBar();
	JFrame mainFrame = new JFrame();

	public static void main(String[] args) {
		// Instantiate the JFrame object
		@SuppressWarnings("unused")
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
		// Panel for holding main JComponents
		JPanel mainPanel = new JPanel();
		mainFrame.setSize(600, 150);
		
		// Setup code for the main panel
		mainPanel.setLayout(null);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.LIGHT_GRAY);
		
		seekBar.addMouseListener(seekBar);
		
		// Set location of seekBar
		seekBar.setLocation(25, 25);
		
		// Set size of seekBar
		seekBar.setSize(550, 65);
		seekBar.setMaxTime(300);
		seekBar.setSeekLocation(150);
		// Add JComponents to the main panel and the JFrame
		mainPanel.add(seekBar);
		mainFrame.add(mainPanel);
		mainFrame.setResizable(false);
	}


}
