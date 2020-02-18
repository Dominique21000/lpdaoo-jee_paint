package model.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.awt.Rectangle;

public class SPixel {
	int x;
	int y;
	boolean isSelected;
	
	public SPixel(int i, int j) {
		this.x = i;
		this.y = j;
	}

	public SPixel(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public SPixel() {
		this.x = 0;
		this.y = 0;
		this.isSelected = false;
		
	}

	public String toString() {
		String msg = "Pixel : " + this.x + "|" + this.y + " ; isSelected=" + this.isSelected;
		return msg;
		
	}
	
	public void print() {
		System.out.print(this.toString().replace("\r\n", "\n"));
	}

	public void select() {
		this.isSelected = true;
	}
	
	public void setLoc(Point point) {
		
		this.x = point.x;
		this.y = point.y;
	}
	
	public Point getLoc() {
		return new Point(this.x, this.y); 
	}
	
	
	public void translate(int tx, int ty) {
		this.x += tx;
		this.y += ty;
	}

	public Rectangle getBounds() {
		Point point = new Point(1,1);
		return new Rectangle(this.x, this.y, 1,1);
	}
		
}
