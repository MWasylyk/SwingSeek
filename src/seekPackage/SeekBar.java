// By Michael Wasylyk
package seekPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SeekBar extends JComponent implements MouseListener, MouseMotionListener{
	// Time location in seconds
	int timeLocation;
	// Size of seek bar
	int sizeInPx;
	// If SeekBar changes in size remember requested location for recalculation
	int requestedLocation;
	// Max size of SeekBar in seconds
	double maxTime;
	// PX from left edge
	int seekLeftOffset = 10;
	// Height in PX used for rendering
	int seekThickness = 5;
	// Scale for arrow rendering
	int arrowScale = seekThickness+4;
	// midPoint point for SeekBar rendering
	int midPoint = getHeight()/2;
	// BookMark fill thickness
	int markThickness = 6;
	// BookMark fill height
	int markHeight = 20;
	// VARS FOR TIP TIME VIEW
	int timeHeight = 10;
	// SIZE TO * FOR NUMBER OF CHARS
	int timeTextScale = 6;
	// ArrayList to hold BookMark objects
	// TODO will be used for loading and saving of marker location
	ArrayList<BookMark> marks = new ArrayList<BookMark>();
	
	public SeekBar(){
		this.setDoubleBuffered(true);
		// Set max size of SeekBar in seconds
		maxTime = 300;
		// Recalculate midpoint of SeekBar for rendering 
		midPoint = getHeight()/2;		
		// Set seek location to the begging
		timeLocation = getRequestedLocation(0);

		//FOR TESTING
		marks.add(new BookMark(150));
		marks.add(new BookMark(90));
		
		recalcBookMarks();
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
		
		// Draw BookMarks 
		for(int i = 0; i < marks.size(); i++) {
			g2D.setColor(marks.get(i).getColor());
			marks.get(i).setBounds(marks.get(i).getLocationMark() - markThickness, midPoint-(markHeight/2), markThickness, markHeight);
			g2D.fill(marks.get(i));

			String tempS = String.valueOf(roundTwoDec(marks.get(i).getTimeMark()/60.0));
			
			// BG FOR TIME
			g2D.setColor(Color.white);
			g2D.fillRect(marks.get(i).getLocationMark()-(markThickness/2)-((timeTextScale*tempS.length())/2), midPoint+arrowScale, timeTextScale*tempS.length(), timeHeight);
			g2D.setColor(Color.black);
			
			g2D.drawString(String.valueOf(roundTwoDec(marks.get(i).getTimeMark()/60.0)), 
						marks.get(i).getLocationMark()-(markThickness/2)-((timeTextScale*tempS.length())/2)
						, midPoint+arrowScale*2);
			
			
		} 		
		// Draw arrow that scales with thickness of SeekBar
		g2D.setColor(Color.red);
		Polygon arrowPoly = new Polygon(new int[] {timeLocation,timeLocation+arrowScale,timeLocation}, new int[] {midPoint-arrowScale,midPoint,midPoint+arrowScale}, 3);
		g2D.fillPolygon(arrowPoly);
		
		// Draw current time
		g2D.setColor(Color.red);
		g2D.drawString(String.valueOf(roundTwoDec(requestedLocation/60.0)), timeLocation, midPoint-arrowScale-3);
		
	}
	
	private double roundTwoDec(double temp){
		temp = Math.round(temp * 100.0);
		temp /= 100.0;
		return temp;
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
			if(marks.get(i).isCalc()){
				System.out.println(marks.get(i).getTimeMark());
				marks.get(i).setLocationMark(getRequestedLocation(marks.get(i).getTimeMark()));
			} else {
				double tempM = (double)(marks.get(i).getLocationMark()-seekLeftOffset)/sizeInPx * maxTime;
				marks.get(i).setTimeMark((int)tempM);
			}
		}
	}
	
	private int clickedOnMark(int x, int y){
		// If mouse x,y is inside one of the BookMarks return which index the BookMark is located
		for(int i = 0; i < marks.size(); i ++) {
			if(marks.get(i).contains(x, y)) {
				return i;
			}
		}
		return -1;
	}

	public void mousePressed(MouseEvent arg0) {
		int x = arg0.getX(), y = arg0.getY();
		if(clickedOnMark(x,y) >= 0) {
			// TODO ADD FINAL BOOKMARK SETTING
			// TODO FIGURE OUT WHICH DIALOG I WANT/ REMOVE
			String markName = JOptionPane.showInputDialog("Set BookMark name", JOptionPane.OK_OPTION);
			double setMarkName = Double.parseDouble(marks.get(clickedOnMark(x,y)).getTipName())/60;
			setMarkName = Math.round(setMarkName * 100);
			setMarkName = setMarkName/100;
			markName = String.valueOf(setMarkName) + ", " + markName;
			System.out.println(markName);
		} else {
			// Add BookMark at Mouse Location'
			marks.add(new BookMark(x, false, Color.GRAY));
			recalcBookMarks();
			repaint();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		int x = arg0.getX(), y = arg0.getY();
		if(clickedOnMark(x,y) >= 0) {
			
		}
	}

}
