// By Michael Wasylyk
package seekPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class BookMark {
	// Time location in seconds
	private int timeMark;
	// True PX location on SeekBar
	private int locationMark;
	// TODO FIGUREOUT WHY I WANTED AN ID (FOR SQL DATABASE AND CONNECTIONS?!)
	private int markID;
	// Description of BookMark (time/name)
	private String nameString;
	// Add coloring to BookMarks for easier viewing
	private Color color;
	
	public BookMark(){
		timeMark = 1;
		nameString = Double.toString(timeMark);
		color = Color.BLUE;
	}
	
	public BookMark(int timeMark){
		this.timeMark = timeMark;
		nameString = Integer.toString(timeMark);
		color = Color.BLUE;
	}
	// TODO add color picker in MSeekBar and feed to constructor
	public BookMark(int timeMark, Color color){
		this.timeMark = timeMark;
		nameString = Integer.toString(timeMark);
		this.color = color;
	}	
	
	public BookMark(int timeMark, int markID, String nameString, Color color){
		this.timeMark = timeMark;
		this.markID = markID;
		this.nameString = nameString;
		this.color = color;
	}
	
	public void setMinuteMark(int minuteLocation) {
		timeMark = minuteLocation;
	}
	
	public void setLocationMark(int location){
		locationMark = location;
	}
	
	public void setName(String name) {
		nameString = name;
	}
	
	public void setColor(Color colorMark) {
		color = colorMark;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getMinuteMark() {
		return timeMark;
	}
	
	public int getLocationMark() {
		return locationMark;
	}
	
	public String getTipName() {
		return nameString;
	}

	public void mouseClicked(MouseEvent arg0) {
	
	}

	public void mouseEntered(MouseEvent arg0) {
	
	}

	public void mouseExited(MouseEvent arg0) {
	
	}

	// TODO when mouse is pressed on BookMark
	// show ToolTipEditor with time mark and name along with delete button
	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

}
