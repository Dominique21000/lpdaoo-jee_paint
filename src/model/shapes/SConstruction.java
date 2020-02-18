package model.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class SConstruction {
	boolean isSelected;
	private Point point;
	private int width = 0;
	private LinkedList<SSquare> ls;
	private LinkedList<SCircle> lc;
	private LinkedList<SPixel> lp;;
		
	
	public void addShape(SSquare s) {
		this.ls.push(s);
		
	}

	public void addShape(SCircle c) {
		this.lc.push(c);
		
	}

	public void addShape(SPixel p) {
		this.lp.push(p);
		
	}

	public void print() {
		System.out.print(this.toString().replace("\r\n", "\n"));		
	}

	public void select() {
		this.isSelected = true;		
	}
	
	public String toString() {
		String msg = "Assemblage : isSelected=" + this.isSelected;
		return msg;
	}

	public Object getLoc() {
		return new Point(this.point.x, this.point.y);
	}

	public Rectangle getBounds() {
		Point p = new Point(0,0);
		Rectangle R = new Rectangle(point.x, point.y, this.width, this.width);
		return R;
		
	}

	public void translate(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public void setLoc(Point expectedLocation) {
		// TODO Auto-generated method stub
		
	}

}
