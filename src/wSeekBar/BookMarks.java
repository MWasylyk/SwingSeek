// By Michael Wasylyk
package wSeekBar;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class BookMarks extends JComponent implements MouseListener{
	private double timeMark;
	private int markID;
	private String nameString;
	@SuppressWarnings("unused")
	private Color color;
	
	public BookMarks(){
		timeMark = 1;
		nameString = Double.toString(timeMark);
		color = Color.BLUE;
	}
	
	public BookMarks(int timeMark){
		this.timeMark = timeMark;
		nameString = Integer.toString(timeMark);
		color = Color.BLUE;
	}
	
	public BookMarks(int timeMark, Color color){
		this.timeMark = timeMark;
		nameString = Integer.toString(timeMark);
		this.color = color;
	}	
	
	public BookMarks(int timeMark, int markID, String nameString, Color color){
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

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

}
