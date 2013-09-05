// By Michael Wasylyk
package wSeekBar;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class BookMarks extends JComponent implements MouseListener{
	private int minuteMark;
	private int markID;
	private String nameString;
	@SuppressWarnings("unused")
	private Color color;
	
	public BookMarks(){
		minuteMark = 1;
		nameString = Integer.toString(minuteMark);
		color = Color.BLUE;
	}
	
	public BookMarks(int minuteMark){
		this.minuteMark = minuteMark;
		nameString = Integer.toString(minuteMark);
		color = Color.BLUE;
	}
	
	public BookMarks(int minuteMark, Color color){
		this.minuteMark = minuteMark;
		nameString = Integer.toString(minuteMark);
		this.color = color;
	}	
	
	public BookMarks(int minuteMark, int markID, String nameString, Color color){
		this.minuteMark = minuteMark;
		this.markID = markID;
		this.nameString = nameString;
		this.color = color;
	}
	
	public void setMinuteMark(int minuteLocation) {
		minuteMark = minuteLocation;
	}
	
	public void setName(String name) {
		nameString = name;
	}
	
	public void setColor(Color colorMark) {
		color = colorMark;
	}
	
	public int getMinuteMark() {
		return minuteMark;
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
