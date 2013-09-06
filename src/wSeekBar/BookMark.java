// By Michael Wasylyk
package wSeekBar;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class BookMark extends JComponent implements MouseListener{
	// Time location in seconds
	private double timeMark;
	// TODO FIGUREOUT WHY I WANTED AN ID
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
	
	public void setName(String name) {
		nameString = name;
	}
	
	public void setColor(Color colorMark) {
		color = colorMark;
	}
	
	public double getMinuteMark() {
		return timeMark;
	}
	
	public String getName() {
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
