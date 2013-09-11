// By Michael Wasylyk
package seekPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SeekBar extends JComponent implements MouseListener, MouseMotionListener{
	// Time location in seconds
	private int timeLocation;
	// Size of seek bar
	private int sizeInPx;
	// If SeekBar changes in size remember requested location for recalculation
	private int requestedLocation;
	// Time in minutes
	private double timeInMin;
	// Max size of SeekBar in seconds
	private double maxTime;
	// PX from left edge
	private int seekLeftOffset = 10;
	// Height in PX used for rendering
	private int seekThickness = 9;
	// Scale for arrow rendering
	private int arrowScale = seekThickness+4;
	// midPoint point for SeekBar rendering
	private int midPoint = getHeight()/2;
	// BookMark fill thickness
	private int markThickness = 10;
	// BookMark fill height
	private int markHeight = 33;
	// VARS FOR TIP TIME VIEW
	private int timeHeight = 11;
	// SIZE TO * FOR NUMBER OF CHARS
	private int timeTextScale = 6;
	// Seek bar rectangle for easier rendering and mouse events
	private Rectangle seekRect;
	// Colors used for rendering seek bar
	private Color seekBarBG = Color.DARK_GRAY, seekBarColor = Color.LIGHT_GRAY; 
	private int currentClick = -1;
	// ArrayList to hold BookMark objects
	// TODO will be used for loading and saving of marker location
	private ArrayList<BookMark> marks = new ArrayList<BookMark>();
	
	public SeekBar(){
		this.setDoubleBuffered(true);
		// Set max size of SeekBar in seconds
		maxTime = 300;
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;		
		// Set seek location to the begging
		timeLocation = getRequestedLocation(0);
		
		// Set location and size of seek bar rectangle
		seekRect = new Rectangle();
		// Left offset, vertical = (height/2 - seekThickness/2), length = (width - (left offset*2)), seekThicknes
		seekRect.setBounds(seekLeftOffset, midPoint - (seekThickness/2), getWidth()-(seekLeftOffset*2), seekThickness);
		
		recalcBookMarks();
	}
		
	// Paint SeekBar and ArrayList of BookMarks
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		// Draw SeekBar BG
		g2D.setColor(seekBarBG);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw SeekBar 
		g2D.setColor(seekBarColor);
		g2D.fill(seekRect);
		
		// Draw BookMarks 
		for(int i = 0; i < marks.size(); i++) {
			if(marks.get(i).wasClicked()) {
				g2D.setColor(Color.blue);
			} else {
				g2D.setColor(marks.get(i).getColor());	
			}
			//marks.get(i).setBounds(marks.get(i).getLocationMark() - markThickness, midPoint-(markHeight/2), markThickness, markHeight);
			g2D.fill(marks.get(i));
			
			String tempS = convertTime(marks.get(i).getTimeMark());
			
			// BG for time pop-up
			if(marks.get(i).isMousedOver() || marks.get(i).wasClicked()) {
				g2D.setColor(Color.black);
				g2D.fillRect(marks.get(i).getLocationMark()-(markThickness/2)-((timeTextScale*tempS.length())/2)
							, (int) (marks.get(i).getY()-timeHeight)
							, timeTextScale*tempS.length()
							, timeHeight);
				
				// Time text
				g2D.setColor(Color.white);
				g2D.drawString(tempS, 
						marks.get(i).getLocationMark()-(markThickness/2)-((timeTextScale*tempS.length())/2)
						, (int) (marks.get(i).getY()-1));
			}
			/* WIP RENDERING OPTIMIZATION
			AffineTransform backUp = g2D.getTransform();
			g2D.transform(marks.get(i).getTransform());
			g2D.draw(marks.get(i).getLabelMark(g2D));
			g2D.transform(backUp);
			*/
		} 		
		
		// Draw arrow that scales with thickness of SeekBar
		g2D.setColor(Color.green);
		Polygon arrowPoly = new Polygon(new int[] {timeLocation,timeLocation+arrowScale,timeLocation}, new int[] {midPoint-arrowScale,midPoint,midPoint+arrowScale}, 3);
		g2D.fillPolygon(arrowPoly);
				
		// Draw current time
		g2D.setColor(Color.white);
		// String used to print time 
		String tempS = String.valueOf(timeInMin);
		if(tempS.length() == 3) tempS += "0";
		g2D.drawString(tempS, timeLocation, this.getHeight()-2);
		
	}
	
	// Correct way to calculate time in min.sec
	private String convertTime(int timeInSec){
		
		double tempTime = roundTwoDec(TimeUnit.SECONDS.toMinutes(timeInSec) 
						+ (TimeUnit.SECONDS.toSeconds(timeInSec)%60.0/100.0));
		String tempS = String.valueOf(tempTime);
		if(tempS.length() == 3) tempS += "0";
		return tempS;
	}
	
	// Rounds a double to 2 decimal places for easy viewing
	private double roundTwoDec(double temp){
		temp = Math.round(temp * 100.0);
		temp /= 100.0;
		return temp;
	}
	
	// Sets the max time of the seek bar
	public void setMaxTime(int timeInSec){
		maxTime = timeInSec;
		seekRect.setBounds(seekLeftOffset, midPoint - (seekThickness/2), getWidth()-(seekLeftOffset*2), seekThickness);
		setSeekLocation(requestedLocation);
		recalcBookMarks();
		repaint();
	}
	
	// Sets physical size of seek bar
	public void setSize(int width, int height){
		super.setSize(width, height);
		seekRect.setBounds(seekLeftOffset, midPoint - (seekThickness/2), getWidth()-(seekLeftOffset*2), seekThickness);
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;
		// Recalculate location of current time
		setSeekLocation(requestedLocation);
		recalcBookMarks();
		repaint();
	}
	
	// Sets physical size of seek bar
	public void setSize(Dimension size){
		super.setSize(size);
		seekRect.setBounds(seekLeftOffset, midPoint - (seekThickness/2), getWidth()-(seekLeftOffset*2), seekThickness);
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;
		// Recalculate location of current time
		setSeekLocation(requestedLocation);
		recalcBookMarks();
		repaint();
	}
	
	// Calculates location of requested time based on SeekBar size
	public int getRequestedLocation(int time){
		// Width of Seekbar used for calculating marker location
		sizeInPx = getSeekLength();
		
		double tempTime;
		tempTime = (double)time/maxTime * sizeInPx;
		tempTime = (int)(Math.round(tempTime) + seekLeftOffset);
		repaint();
		return (int)tempTime;
	}
	
	// Subtract size of arrow pointer to find seek location
	public void setSeekLocation(int seekLoc){
		requestedLocation = seekLoc;
		timeInMin = roundTwoDec(TimeUnit.SECONDS.toMinutes(requestedLocation) 
				  + (TimeUnit.SECONDS.toSeconds(requestedLocation)%60.0/100.0));
		timeLocation = getRequestedLocation(seekLoc) - arrowScale;
		repaint();
	}
	
	// Gets physical size of seek bar in PX
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
			// WIP RENDERING OPTIMIZATION
			// marks.get(i).setLabelLocation(marks.get(i).getLocationMark()-(markThickness/2)-timeTextScale, midPoint+arrowScale*2);
						
			if(marks.get(i).isCalc()){
				marks.get(i).setLocationMark(getRequestedLocation(marks.get(i).getTimeMark()));
			} else {
				// If BookMark was manually, added reverse calculation
				double tempM = (double)(marks.get(i).getLocationMark()-seekLeftOffset)/sizeInPx * maxTime;
				marks.get(i).setTimeMark((int)tempM);
			}
			// Sets bounds of Rectangle object BookMark for easier rendering
			marks.get(i).setBounds(marks.get(i).getLocationMark() - markThickness, midPoint-(markHeight/2), markThickness, markHeight);
		}
	}
	
	private int isMouseOnMark(int x, int y){
		// If mouse x,y is inside one of the BookMarks return which index the BookMark is located
		for(int i = 0; i < marks.size(); i ++) {
			if(marks.get(i).contains(x, y)) {
				return i;
			}
		}
		if(seekRect.contains(x,y)){
			return -2;
		}
		return -1;
	}

	public void mousePressed(MouseEvent arg0) {
		int x = arg0.getX(), y = arg0.getY();
		// get current index of BookMark clicked on
		int tempIndex = isMouseOnMark(x,y);
		if(arg0.getButton() == MouseEvent.BUTTON1) {
			if(tempIndex >= 0) {
				// If mouse was pressed on BookMark save the current BookMark index for use later with dragging
				currentClick = tempIndex;
				// TODO ADD FINAL BOOKMARK SETTING
				// TODO FIGURE OUT WHICH DIALOG I WANT/ REMOVE
				for(int i = 0; i < marks.size(); i ++) {
					marks.get(i).setWasClicked(false);
				}
				marks.get(tempIndex).setWasClicked(true);
				
			} else if(tempIndex == -2){
				// If mouse was not clicked on a BookMark do not set -1 for false
				currentClick = -1;
				for(int i = 0; i < marks.size(); i ++) {
					marks.get(i).setWasClicked(false);
				}
				// Add BookMark at Mouse Location
				BookMark tempBook = new BookMark(x, false, Color.cyan);
				tempBook.setWasClicked(true);
				marks.add(tempBook);
				recalcBookMarks();
				
			}
		} else if(arg0.getButton() == MouseEvent.BUTTON3) { // When right clicked on bookmark remove it from screen
			// TODO add right click removal 
			if(tempIndex >= 0) {
				marks.remove(tempIndex);
				recalcBookMarks();
			}
		}
		repaint();
	}

	public void mouseMoved(MouseEvent arg0) {
		int x = arg0.getX(), y = arg0.getY();
		// If the mouse moved over a BookMark set it to render its time pop-up else set to false
		if(isMouseOnMark(x,y) >= 0) {
			marks.get(isMouseOnMark(x,y)).setMousedOver(true);
			repaint();
		} else {
			for(int i = 0; i < marks.size(); i ++) {
				marks.get(i).setMousedOver(false);
				repaint();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// When mouse is clicked on BookMark and then dragged move the mouse to current mouse location
 		int x = arg0.getX();
		if(currentClick >= 0) {
			marks.get(currentClick).setWasClicked(true);
			if(x > seekLeftOffset && x < getSeekLength() + seekLeftOffset) {
				marks.get(currentClick).setLocationMark(x);	
			}
			recalcBookMarks();
			repaint();
		}
		
	}

}
