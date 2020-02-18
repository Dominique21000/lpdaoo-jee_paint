package model.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SRectangle {
	private Point start;
	private Point end;
	private boolean isSelected;
	
	public SRectangle() {
		this.start = new Point(1,1);
		this.end = new Point(3,5);
		this.isSelected = false;
	}
	
	public SRectangle(Point point, Point point2) {
		this.start = point;
		this.end = point2;
	}

	public SRectangle(Point point, int i, int j) {
		this.start = point;
		this.end = new Point(this.start.x+i, this.start.y+j);
	}

	public void print() {
		System.out.print(this.toString().replace("\r\n", "\n"));
	}
	
	public String toString() {
		String msg = "Rectangle : startPoint=" + this.start.x +"|" + this.start.y +" ; endPoint=" + (this.start.x + this.end.x - this.start.x)  + "|" + this.end.y + " ; isSelected="+
				this.isSelected;
		return msg;
	}

	public Point getLoc() {
		return new Point(this.start); 
	}

	public Rectangle getBounds() {
		Point p = new Point(0,0);
		Rectangle R = new Rectangle(this.start.x, this.start.y, this.end.x - this.start.x, this.end.y - this.start.y);
		return R;	
	}

	public void setLoc(Point debut) {
		this.start = new Point(debut);
		this.end.x = debut.x + (this.end.x -1);
		this.end.y = debut.y + (this.end.y -1);
	}

	public void translate(int i, int j) {
		this.start.x += i;
		this.start.y += j;
		this.end.x += i;
		this.end.y += j;		
	}

	public void select() {
		this.isSelected = true;		
	}	
}
