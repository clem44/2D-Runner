package com.game.drunner.maths;

/**
 * Abstract class Shape
 * @author Clemaurde
 *
 */
public abstract class Shape {
	
	float x,y;
	float width,height;
	String name;
	
	public Shape(){
		
	}
	public Shape(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	abstract public void setLocation(float x, float y);
	abstract public void setSize(float w, float h);
	abstract public boolean contains(float x, float y);
	abstract public boolean equalTo(Shape s);
	abstract public Vector getCenter();
	abstract public Shape getBounds();
	
	/**
	 * Identifier for the shape type
	 */
	@Override
	public abstract String toString();

}
