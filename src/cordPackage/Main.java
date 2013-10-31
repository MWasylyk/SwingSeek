/* By Michael Wasylyk
 * Example swing application using the SwingSeek component
 */

package cordPackage;

import seekPackage.SeekBar;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	static SeekBar seekBar = new SeekBar();
	JFrame mainFrame = new JFrame();
	public static void main(String[] args) {
		// Instantiate the JFrame object
		@SuppressWarnings("unused")
		Main m = new Main();
		seekBar.setSeekLocation(0);
		final Timer timer = new Timer();

        // Test intellij ide git
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  if(seekBar.getSeekLocation()+1 < seekBar.getMaxTime()+1) {
					  seekBar.setSeekLocation(seekBar.getSeekLocation()+1);
				  }
			  }
		}, 0, 1000);
		
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
		seekBar.addMouseMotionListener(seekBar);
		
		// Set location of seekBar
		seekBar.setLocation(25, 25);
		
		// Set size of seekBar
		seekBar.setSize(550, 65);
		seekBar.setMaxTime(120);
		//seekBar.setSeekLocation(150);
		
		// Add JComponents to the main panel and the JFrame
		mainPanel.add(seekBar);
		mainFrame.add(mainPanel);
		mainFrame.setResizable(true);
	}

}