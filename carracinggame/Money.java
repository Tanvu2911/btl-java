package carracinggame;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;



public class Money {
	int x;
	int y;
	int width;
	int height;
	Image image = new ImageIcon(getClass().getResource("/jpn/money.png")).getImage();
	
	public Money(int x, int y ,int width , int height) {
		this.x = x;
		this.y=y;
		this.width = width;
		this.height= height;
	}
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}