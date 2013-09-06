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
	// TODO FIGURE OUT IF SIZE VARS ARE NEEDED
	int sizeX = 100,sizeY = 20;
	// Width of seek bar used for calculating marker location
	int sizeInPx;
	// Px from left edge 
	int seekLeftOffset = 7;
	// Height in PX used for rendering
	int seekThickness = 4;
	// ArrayList to hold BookMark objects
	// TODO will be used for loading and saving of marker location
	ArrayList<BookMark> marks = new ArrayList<BookMark>();
	
	
	public MSeekBar(){
		this.setSize(this.getPreferredSize());
		sizeInPx = getSeekLength();
	}
	
	// Paint Seek Bar and ArrayList of BookMarks
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		// Seek Bar BG
		g2D.setColor(Color.black);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2D.setColor(Color.white);
		
		// left offset, vertical = (height/2 - seekThickness/2), length = (width - (left offset*2)), seekThicknes
		g2D.fillRect(seekLeftOffset, getHeight()/2 - (seekThickness/2), getWidth()-(seekLeftOffset*2), seekThickness);
	}
	
	// Move seek marker to current time and repaint
	public void setSeekLocation(double timeLocation){
		this.timeLocation = timeLocation;
		repaint();
	}
	
	private int getSeekLength() {
		return getWidth() - seekLeftOffset*3;
	}
	
	
	//TODO Find smallest size of seek bar possible
	public Dimension getPreferredSize() {
        return new Dimension(100, 20);
    }

}
