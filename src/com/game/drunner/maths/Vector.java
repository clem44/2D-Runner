package com.game.drunner.maths;

import android.graphics.Point;

/**
 * A class that stores a two point coordinate. Unlike the {@link Point} class it
 * can take floats.
 * 
 * @author Clemaurde
 * 
 */
public class Vector extends Point {

	public float X, Y;

	public Vector() {
		super();
		X = 0.0f;
		Y = 0.0f;
	}

	public Vector(float x, float y) {
		X = x;
		Y = y;
	}

	public void setPoint(float x, float y) {
		X = x;
		Y = y;
	}

	public void addPoint(float x, float y) {
		X += x;
		Y += y;
	}

	@Override
	public String toString() {
		return "Vector(" + X + "," + Y + ")";

	}

	public Vector normalize(float x, float y) {

		// find the vector's length
		float length = (float) Math.sqrt((x * x) + (y * y));

		// now normalize
		Vector nom = new Vector(x / length, y / length);
		return nom;
	}

}
