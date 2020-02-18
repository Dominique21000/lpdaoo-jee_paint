package model.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle{
	
	private boolean isSelected;
	Point point;
	int radius;

	public SCircle() {
		this.radius=1;
		this.point = new Point(0, 0);
		this.isSelected= false;
		
	}
	
	public SCircle(Point point2, int i) {
		this.point = point2;
		this.radius = i;
		if (this.radius < 0) this.radius = this.radius *-1; 
		
	}

	public String toString() {
		String st = "Circle : location=" + this.point.x + "|" + this.point.y + " ; radius="+ this.radius + 
				" ; isSelected="+ this.isSelected;
		return st;
		
	}
	
	public void print() {
		System.out.print(this.toString().replace("\r\n", "\n"));
	}

	public void select() {
		this.point = new Point(0, 0);
		this.isSelected = true;
	}

	public Point getLoc() {
		return new Point(this.point.x, this.point.y); 
	}

	public void setLoc(Point expectedLocation) {
		this.point = expectedLocation;
		
	}

	public Rectangle getBounds() {
		int x = point.x - this.radius;
		int y = point.y - this.radius;
		int cote = this.radius *2;
		Rectangle R = new Rectangle(x, y,cote , cote);
		return R;
	}

	public void translate(int i, int j) {
		this.point.x += i;
		this.point.y += j;		
	}
	
	
}
