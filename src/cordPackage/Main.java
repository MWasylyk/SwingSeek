// By Michael Wasylyk
package cordPackage;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import wSeekBar.MSeekBar;

public class Main extends JFrame {
	MSeekBar seekBar = new MSeekBar();

	public static void main(String[] args) {
		// Instantiate the JFrame object
		Main m = new Main();
		m.setVisible(true);
	}
	
	// Swing startup code
	public Main(){
		setTitle("Main Window");
		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		init();
	}
	
	public void init(){
		// Panel for holding main JComponents
		JPanel mainPanel = new JPanel();
		this.setSize(300, 300);
		this.setLayout(null);
		
		// Setup code for the main panel
		mainPanel.setLayout(null);
		mainPanel.setLocation(0,0);
		mainPanel.setSize(300, 300);
		mainPanel.setBackground(Color.lightGray);
		
		// Set location of seekBar
		seekBar.setLocation(20, 200);
		// Set size of seekBar
		seekBar.setSize(250, 20);
		
		// Add JComponents to the main panel and the JFrame
		mainPanel.add(seekBar);
		add(mainPanel);
		this.setResizable(false);
	}

}
