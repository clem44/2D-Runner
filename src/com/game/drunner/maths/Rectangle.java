package com.game.drunner.maths;

import java.io.Serializable;

import android.graphics.Point;

/**
 * Geometry: Rectangle object based off Java's Rectangle2D in the awt package {@link java.awt.geom.Rectangle2D}.
 * @author Clemaurde
 *
 */
public class Rectangle extends Shape implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public float x, y;
	public float width, height;

	public Rectangle() {
		super();
		name = "Rectangle";
	}

	public Rectangle(float x, float y, float w, float h) {
		super(x,y);
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		name = "Rectangle";
	}

	/**
	 * Sets the this rectangle's bounds this the new coordinates
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void setBounds(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}
	public void setBounds(Rectangle r) {
		this.x = r.x;
		this.y = r.y;
		width = r.width;
		height = r.height;
	}

	/**
	 *  Sets this Rectangle's size
	 */
	public void setSize(float w, float h) {
		width = w;
		height = h;
	}

	/**
	 * Sets this Rectangle's position to the new coordinates
	 * @param x
	 *            = x coordinate
	 * @param y
	 *            = y coordinate
	 */
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * adds the values to the current rectangle's position
	 * @param x 
	 * 			 point x coordinate
	 * @param y
	 * 			 point y coordinate
	 */
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	/**
	 * subtracts the values from the current rectangle's position
	 * @param x 
	 * 			 point x coordinate
	 * @param y
	 * 			 point y coordinate
	 */
	public void sub(float x, float y) {
		this.x -= x;
		this.y -= y;
	}

	/**
	 * @param x
	 *            point x coordinate
	 * @param y
	 *            point y coordinate
	 * @return whether the point is contained in the rectangle
	 */
	public boolean contains(float x, float y) {
		return this.x <= x && this.x + this.width >= x && this.y <= y
				&& this.y + this.height >= y;
	}
	
	public boolean contains(int x, int y) {
		//System.out.println("received: X"+x+" :Y"+y);
		boolean t= this.x <= x && this.x + this.width >= x && this.y <= y
				&& this.y + this.height >= y;
		//System.out.println("contains :"+t);		
		//System.out.println(this);	
		return t;
	}

	/**
	 * @param point
	 *            The coordinates vector
	 * @return whether the point is contained in the rectangle
	 */
	public boolean contains(Point point) {
		return contains(point.x, point.y);
	}

	/**
	 * @param rectangle
	 *            the other {@link Rectangle}.
	 * @return whether the other rectangle is contained in this rectangle.
	 */
	public boolean contains(Rectangle rectangle) {
		float xmin = rectangle.x;
		float xmax = xmin + rectangle.width;

		float ymin = rectangle.y;
		float ymax = ymin + rectangle.height;

		return ((xmin > x && xmin < x + width) && (xmax > x && xmax < x + width))
				&& ((ymin > y && ymin < y + height) && (ymax > y && ymax < y
						+ height));
	}
	
	/** @param r the other {@link Rectangle}
	 * @return whether this rectangle overlaps the other rectangle. */	
	public boolean intersects (Rectangle r) {
		return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
	}
	
	/** Sets the values of the given rectangle to this rectangle.
	 * @param rect the other rectangle
	 * @return this rectangle for chaining */
	public Rectangle set (Rectangle rect) {
		this.x = rect.x;
		this.y = rect.y;
		this.width = rect.width;
		this.height = rect.height;

		return this;
	}

	@Override
	public boolean equalTo(Shape s) {
		Rectangle rect = (Rectangle) s;
		if(this.x == rect.x && this.y == rect.y && this.width == rect.width
				&& this.height == rect.height){
			return true;
		}else
			return false;
		
	}

	@Override
	public Vector getCenter() {
		Vector vector = new Vector();
		vector.X = x + (width / 2);
		vector.Y = y + (height / 2);
		return vector;
	}

	@Override
	public String toString() {
		
		return ""+name+" | X"+x+" | Y:"+y+" |W:"+width+"| H:"+height;
	}

	@Override
	public Rectangle getBounds() {
		
		return this;
	}

}
