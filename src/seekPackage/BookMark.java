// By Michael Wasylyk
package seekPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;

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
	/* WIP RENDERING OPTIMIZATION
	// Font used for GlyphVector rendering
	private Font sysFont = new Font("Dialog", Font.PLAIN, 12);
	// Used for font sizing
	FontRenderContext fontRendering;
	// FORTESTING
	GlyphVector textVector;
	private int shapeX, shapeY;
	*/
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
