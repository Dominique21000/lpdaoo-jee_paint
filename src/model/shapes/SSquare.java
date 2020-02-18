package model.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.awt.Rectangle;

import junit.framework.TestCase;

public class SSquare extends TestCase{
	Point point;
	int width;
	boolean isSelected;
	
	public SSquare() {
		this.point = new Point(0,0);
		this.width = 1;
		isSelected = false;
	}
	
	public SSquare(Point point, int i) {
		this.point = point;
		this.width = i;
	}

	public SSquare(Point point, Point point2) throws Exception {
		int width = point2.x - point.x;
		int height = point2.y  - point.y;
		if (height != width) {
			
			throw new Exception("The two points does not form a square : width="+ width + " height=" + height); 
		}
		else {
			this.point = point;
			this.width = point2.x - point.x;
		}
	}

	public String toString() {
		
		return "Square : location=" + this.point.x + "|" + this.point.y + " ; width=" + this.width + " ; isSelected="+ this.isSelected;
	}
	
	public void print() {
		System.out.print(this.toString().replace("\r\n", "\n"));
	}
	
	public void select() {
		this.isSelected = true;
	}
	
	public void unSelect()
    {
        this.isSelected = false;
    }

    public void togleSelection() {
        this.isSelected = !this.isSelected;
    }
    
    public void setLoc(Point point) {
		
		this.point = point;
	}
	
	public Point getLoc() {
		return new Point(point); 
	}
	
	
	
	public void translate(int x, int y) {
		this.point.x += x;
		this.point.y += y;
	}

	public Rectangle getBounds() {
		Point p = new Point(0,0);
		Rectangle R = new Rectangle(point.x, point.y, this.width, this.width);
		return R;		
	}
}
