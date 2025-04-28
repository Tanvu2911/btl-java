package carracinggame;

import java.awt.Image;
import java.awt.Rectangle;

public class Car {
    public double dx, dy;
    public double x, y;
    public int width, height;
    public Image image;

    public Car(double x, double y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public void movex(double dx) { this.x += dx; }
    public void movey(double dy) { this.y += dy; }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}


