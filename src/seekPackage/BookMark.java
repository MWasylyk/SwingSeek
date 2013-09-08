// By Michael Wasylyk
package seekPackage;

import java.awt.Color;
import java.awt.Rectangle;

public class BookMark extends Rectangle{
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
	// Calculate pos or set at mouse
	private boolean calc;
	
	
	public BookMark(){
		timeMark = 1;
		nameString = Integer.toString(timeMark);
		color = Color.BLUE;
	}
	
	public BookMark(int timeMark){
		this.timeMark = timeMark;
		calc = true;
		nameString = Integer.toString(timeMark);
		color = Color.green;
	}
	public BookMark(int locMark,boolean isCalc, Color color){
		calc = isCalc;
		locationMark = locMark;
		nameString = Integer.toString(timeMark);
		this.color = color;
	}
	// TODO add color picker in MSeekBar and feed to constructor

	
	public void setTimeMark(int timeLocation) {
		timeMark = timeLocation;
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
	
	public int getTimeMark() {
		return timeMark;
	}
	
	public int getLocationMark() {
		return locationMark;
	}
	
	public String getTipName() {
		return nameString;
	}
	
	public void setTipName(String name) {
		nameString = name;
	}

	public boolean isCalc() {
		return calc;
	}

	public void setCalc(boolean calc) {
		this.calc = calc;
	}
}
