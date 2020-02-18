package model.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class Shape {

	private boolean isSelected;
	
	public Shape() {
		this.isSelected = false;
	}

	public String toString() {
		String msg = "isSelected=" + this.isSelected;
		return msg;
	}
	
	public void print() {
		// TODO Auto-generated method stub
		
	}

	public Point getLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLoc(Point p) {
		// TODO Auto-generated method stub
		
	}

	public void translate(int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
