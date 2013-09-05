// By Michael Wasylyk
package cordPackage;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.setVisible(true);
	}
	
	public Main(){
		setTitle("Main Window");
		setSize(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		init();
	}
	
	public void init(){
		JPanel mainPanel = new JPanel();
		this.setResizable(false);
		this.setLayout(null);
		mainPanel.setBounds(150, 150, 150, 150);
		mainPanel.setBackground(Color.blue);
		
		add(mainPanel);
	}

}
