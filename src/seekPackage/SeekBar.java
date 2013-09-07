// By Michael Wasylyk
package seekPackage;

import java.awt.Color;
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
	// BookMark fill thickness
	int markThickness = 3;
	// BookMark fill height
	int markHeight = 15;	
	
	// ArrayList to hold BookMark objects
	// TODO will be used for loading and saving of marker location
	ArrayList<BookMark> marks = new ArrayList<BookMark>();
	
	public SeekBar(){
		
		
		//FOR TESTING
		marks.add(new BookMark(50, Color.green));
		marks.add(new BookMark(70, Color.green));
		
		// Set Size of SeekBar to the preferred size
		this.setSize(this.getPreferredSize());
		// Set max size of SeekBar in seconds
		maxTime = 300;
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;		
		// Set seek location to the begging
		timeLocation = getRequestedLocation(0);
		recalcBookMarks();
	}
	
	// Paint SeekBar and ArrayList of BookMarks
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		// Draw SeekBar BG
		g2D.setColor(Color.black);
		//g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw SeekBar 
		g2D.setColor(Color.white);
		// Left offset, vertical = (height/2 - seekThickness/2), length = (width - (left offset*2)), seekThicknes
		g2D.fillRect(seekLeftOffset, midPoint - (seekThickness/2), getWidth()-(seekLeftOffset*2), seekThickness);
		
		// Draw BookMarks ///REMOVE AND MOVE TO MAIN 
		for(int i = 0; i < marks.size(); i++) {
			g2D.setColor(marks.get(i).getColor());
			g2D.fillRect(marks.get(i).getLocationMark() - markThickness, midPoint-(markHeight/2), markThickness, markHeight);
		}
		
		// Draw arrow that scales with thickness of SeekBar
		g2D.setColor(Color.red);
		Polygon arrowPoly = new Polygon(new int[] {timeLocation,arrowScale+timeLocation,timeLocation}, new int[] {midPoint-arrowScale,midPoint,midPoint+arrowScale}, 3);
		g2D.fillPolygon(arrowPoly);
		
	}
	
	public void setMaxTime(int timeInSec){
		maxTime = timeInSec;
		setSeekLocation(requestedLocation);
		recalcBookMarks();
		repaint();
	}
	
	public void setSize(int width, int height){
		super.setSize(width, height);
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;
		// Recalculate location of current time
		setSeekLocation(requestedLocation);
		recalcBookMarks();
		repaint();
	}
	
	public void setSize(Dimension size){
		super.setSize(size);
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;
		// Recalculate location of current time
		setSeekLocation(requestedLocation);
		recalcBookMarks();
		repaint();
	}
	
	// Calculates location of requested time based on SeekBar size
	public int getRequestedLocation(int time){
		requestedLocation = time;
		// Width of Seekbar used for calculating marker location
		int sizeInPx = getSeekLength();
		
		double tempTime;
		tempTime = (double)requestedLocation/maxTime * sizeInPx;
		tempTime = (int)(Math.round(tempTime) + seekLeftOffset);
		repaint();
		return (int)tempTime;
	}
	
	// Subtract size of arrow pointer to find seek location
	public void setSeekLocation(int seekLoc){
		timeLocation = getRequestedLocation(seekLoc) - arrowScale;
		repaint();
	}
		
	private int getSeekLength() {
		return getWidth() - seekLeftOffset*2;
	}
	
	
	//TODO Find smallest size of seek bar possible
	public Dimension getPreferredSize() {
        return new Dimension(100, 20);
    }
	

	// Set true PX locations of BookMarks in marks ArrayList
	private void recalcBookMarks(){
		for(int i = 0; i < marks.size(); i ++) {
			marks.get(i).setLocationMark(getRequestedLocation(marks.get(i).getMinuteMark()));
		}
	}

}
