// By Michael Wasylyk
package wSeekBar;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;

public class MSeekBar extends JComponent {
	// Time location in seconds
	double timeLocation;
	// Width of seek bar used for calculating marker location
	int sizeInPx;
	// ArrayList to hold BookMark objects
	// TODO will be used for loading and saving of marker location
	ArrayList<BookMark> marks = new ArrayList<BookMark>();
	
	public MSeekBar(){
		// FOR_TESTING
		this.setSize(this.getPreferredSize());
	}
	
	// Move seek marker to current time and repaint
	public void setSeekLocation(double timeLocation){
		timeLocation = timeLocation;
		repaint();
	}
	
	// Paint Seek Bar and ArrayList of BookMarks
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.white);
		// Seek Bar BG
		g2D.fillRect(0, 0, 100, 20);
	}
	
	//TODO Find smallest size of seek bar possible
	public Dimension getPreferredSize() {
        return new Dimension(100,20);
    }
}
