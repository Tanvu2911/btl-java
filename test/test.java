package test;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import login.login;
import login.selectcar;
import login.selectmap;


public class test extends JFrame {
	public static JFrame frame = new JFrame("Racing Car Game");
	
	
	
	public static void main(String[] args) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(380, 600);
	    Image logo = new ImageIcon(test.class.getResource("/jpn/logo.png")).getImage();
	    frame.setIconImage(logo);
		frame.setContentPane(new login());
		frame.pack();
	    frame.setLocationRelativeTo(null); 
	    frame.setVisible(true);
	}
}
