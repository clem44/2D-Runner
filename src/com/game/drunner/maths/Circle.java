package com.game.drunner.maths;
import static java.lang.Math.*;

public class Circle extends Shape {


	float r;
	float diameter;
		
	

    public Circle() {
        r= 1;
        name= "Circle";
        calSize(0,0,r);
    }

    public Circle(int x, int y, int r) {
        super(x, y);
        this.r = r;
        name = "Circle";
        
        calSize(x,y,r);
    }

    @Override
	public void setSize(float w, float h) {
		// TODO Auto-generated method stub
		
	}
    private void calSize(float x, float y, float r){
    	width = r*2;
    	height = r*2;
    	
    }

	@Override
	public void setLocation(float x, float y) {
		// TODO Auto-generated method stub
		
	}
	public void calDiameter(float radius){
		diameter = radius*2;
	}
	
    public float getRadius() {
        return r;
    }

    public double getArea() {
        return Math.PI * r * r;
    }

    public double getPerimeter() {
        return 2 * Math.PI * r;
    }

	@Override
	public boolean equalTo(Shape s) {
		Circle rect = (Circle) s;
		if(this.x == rect.x && this.y == rect.y && this.width == rect.width
				&& this.height == rect.height){
			return true;
		}else
			return false;
		
	}

	@Override
	public Vector getCenter() {
		Vector vector =new Vector();
		vector.X = x + r;
		vector.Y = y + r;
		return vector;
	}

	/**
	 * @param x
	 * 			x coordinate 			
	 * @param y
	 * 			y coordinate
	 *            
	 * @return whether the other circle is contained in this circle.
	 */
	@Override
	public boolean contains(float x1, float y1) {		
		
//		return this.x <= x1 && this.x + this.width >= x1 && this.y <= y1
//				&& this.y + this.height >= y1;	
		float dx = x - x1;
		float dy = y - x1;
		double d;
		
		//Determine the straight-Line distance between the centers.
		d = sqrt((dy*dy) + (dx*dx));
		
		return d <= r*r;
		
	}
	
	/**
	 * @param Circle
	 *            the other {@link Circle}.
	 * @return whether the other circle is contained in this circle.
	 */
	public boolean contains(Circle c){
		
		float dx = x - c.x;
		float dy = y - c.y;
		double d;
		
		//Determine the straight-Line distance between the centers.
		d = sqrt((dy*dy) + (dx*dx));
		
		return d <= r*r;
	}
	/**
	 * 
	 * @param c
	 * 			Circle object
	 * @return 
	 * 			whether this circle overlaps the other circle.
	 */
	public boolean intersects(Circle c){
		
		//dx and dy are the vertical And horizontal distances between
	    //the circle centers
		
		float dx = x - c.x;
		float dy = y - c.y;
		double d;
		
		//Determine the straight-Line distance between the centers.
		d = sqrt((dy*dy) + (dx*dx));
		
		if(d > (r + c.r)){
			return false;
		}
		return false;
	}
	

	@Override
	public String toString() {
		
		return null;
	}

	@Override
	public Shape getBounds() {
		
		return null;
	}

}
