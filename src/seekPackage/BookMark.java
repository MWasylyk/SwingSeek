// By Michael Wasylyk
package seekPackage;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
public class BookMark extends Rectangle{
	// Time location in seconds
	private int timeMark;
	// True PX location on SeekBar
	private int locationMark;
	// TODO FIGUREOUT WHY I WANTED AN ID (FOR SQL DATABASE AND CONNECTIONS?!)
	@SuppressWarnings("unused")
	private int markID;
	// Description of BookMark (time/name)
	private String markerInfo;
	// Time location
	private String timeString;
	// Add coloring to BookMarks for easier viewing
	private Color color;
	// Calculate pos or set at mouse location
	private boolean calc;
	// If mouse is over book mark render time
	private boolean isMousedOver = false;
	// If bookmark was clicked change color and stay visible
	private boolean wasClicked = false;
	
	/* WIP RENDERING OPTIMIZATION
	// Font used for GlyphVector rendering
	private Font sysFont = new Font("Dialog", Font.PLAIN, 12);
	// Used for font sizing
	FontRenderContext fontRendering;
	// FORTESTING
	GlyphVector textVector;
	private int shapeX, shapeY;
	*/
	
	// TODO FIX WHEN YOU HAVE TIME 
	// TODO add color picker in MSeekBar and feed to constructor
	
	public BookMark(){
		timeMark = 1;
		markerInfo = Integer.toString(timeMark);
		color = Color.BLUE;
	}
	
	public BookMark(int timeMark){
		this.timeMark = timeMark;
		calc = true;
		markerInfo = Integer.toString(timeMark);
		color = Color.green;
	}
	public BookMark(int locMark,boolean isCalc, Color color){
		calc = isCalc;
		locationMark = locMark;
		markerInfo = Integer.toString(timeMark);
		this.color = color;
	}
	/*// WIP RENDERING OPIMIZATION
	public Shape getLabelMark(Graphics2D g2D){
		fontRendering = g2D.getFontMetrics(sysFont).getFontRenderContext();
		textVector = sysFont.createGlyphVector(fontRendering, String.valueOf(roundTwoDec(timeMark/60.0)));
		return textVector.getOutline();
	}
	
	public void setLabelLocation(int x, int y){
		shapeX = x;
		shapeY = y;
	}
	
	public AffineTransform getTransform(){
		AffineTransform af = new AffineTransform();
		af.translate(shapeX, shapeY);
		
		return af;
	}
	// Rounds a double to 2 dec places for easy viewing
	private double roundTwoDec(double temp){
		temp = Math.round(temp * 100.0);
		temp /= 100.0;
		return temp;
	}	
	*/
	
	public String getTimeString() {
		return timeString;
	}

	private void setTimeStringToTime() {
		timeString = convertTime(timeMark);
	}
	
	public void setTimeMark(int timeLocation) {
		timeMark = timeLocation;
		setTimeStringToTime();
	}
	
	public void setLocationMark(int location){
		locationMark = location;
	}
	
	public void setName(String name) {
		markerInfo = name;
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
	
	public String getMarkerInfo() {
		return markerInfo;
	}
	
	public void setMarkerInfo(String name) {
		markerInfo = name;
	}

	public boolean isCalc() {
		return calc;
	}

	public void setCalc(boolean calc) {
		this.calc = calc;
	}

	public boolean isMousedOver() {
		return isMousedOver;
	}

	public void setMousedOver(boolean isMousedOver) {
		this.isMousedOver = isMousedOver;
	}

	public boolean wasClicked() {
		return wasClicked;
	}

	public void setWasClicked(boolean wasClicked) {
		this.wasClicked = wasClicked;
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
}
