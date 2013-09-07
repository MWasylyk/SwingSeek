// By Michael Wasylyk
package seekPackage;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JComponent;

public class SeekBar extends JComponent {
	// Time location in seconds
	int timeLocation;
	// If SeekBar changes in size remember requested location for recalculation
	int requestedLocation;
	// Max size of SeekBar in seconds
	int maxTime;
	// PX from left edge 
	int seekLeftOffset = 10;
	// Height in PX used for rendering
	int seekThickness = 5;
	// Scale for arrow rendering
	int arrowScale = seekThickness+2;
	// midPoint point for SeekBar rendering
	int midPoint = getHeight()/2;
	// ArrayList to hold BookMark objects
	// TODO will be used for loading and saving of marker location
	ArrayList<BookMark> marks = new ArrayList<BookMark>();
	
	
	public SeekBar(){
		// Set Size of SeekBar to the preferred size
		this.setSize(this.getPreferredSize());
		// Set max size of SeekBar in seconds
		maxTime = 300;
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;
		
		// Set seek location to the begging
		setSeekLocation(0);
	}
	
	// Paint SeekBar and ArrayList of BookMarks
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		// Draw SeekBar BG
		g2D.setColor(Color.black);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw SeekBar 
		g2D.setColor(Color.white);
		// Left offset, vertical = (height/2 - seekThickness/2), length = (width - (left offset*2)), seekThicknes
		g2D.fillRect(seekLeftOffset, midPoint - (seekThickness/2), getWidth()-(seekLeftOffset*2), seekThickness);
		
		// Draw arrow that scales with thickness of SeekBar
		g2D.setColor(Color.red);
		Polygon arrowPoly = new Polygon(new int[] {timeLocation,arrowScale+timeLocation,timeLocation}, new int[] {midPoint-arrowScale,midPoint,midPoint+arrowScale}, 3);
		g2D.fillPolygon(arrowPoly);
	}
	
	public void setSize(int width, int height){
		super.setSize(width, height);
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;
		// Recalculate location of current time
		setSeekLocation(requestedLocation);
	}
	
	public void setSize(Dimension size){
		super.setSize(size);
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;
		// Recalculate location of current time
		setSeekLocation(requestedLocation);
	}
	
	// Move seek marker to current time and repaint
	public void setSeekLocation(int time){
		requestedLocation = time;
		// Width of seek bar used for calculating marker location
		int sizeInPx = getSeekLength();
		
		double tempTime;
		tempTime = (double)requestedLocation/maxTime * sizeInPx;
		timeLocation = (int)(Math.round(tempTime) + seekLeftOffset) - arrowScale;
		repaint();
	}
	
	private int getSeekLength() {
		return getWidth() - seekLeftOffset*2;
	}
	
	
	//TODO Find smallest size of seek bar possible
	public Dimension getPreferredSize() {
        return new Dimension(100, 20);
    }

}
