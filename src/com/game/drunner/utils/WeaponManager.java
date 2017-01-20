package com.game.drunner.utils;

import java.util.ArrayList;

import android.graphics.Matrix;

import com.game.drunner.Actor.Ship;
import com.game.drunner.Actor.Texture;
import com.game.drunner.maths.Vector;

import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;



/**
 * Weapon management for the ship class. Provides weapon rotation functionality, image switching (weapon switching),
 * and weapon expansion.
 * @author Clemaurde
 *
 */
public class WeaponManager implements Updatables, Drawables {

	
	BulletManager bm;
	CollisionManager cm;
	ArrayList <Texture> wp;
	Pixmap img;
	Ship player;
	Vector position;
	Matrix matrix;
	int switcher;
	int add = 3;
	
	public WeaponManager(Pixmap img, Ship player){
		
		this.img = img;
		wp = new ArrayList<Texture>();
		wp.add(new Texture(img));
		this.player = player;
		matrix = new Matrix();
		matrix.preTranslate(player.x+2, player.y+2);
		switcher = 0;
		//bm = new BulletManager();
	}
	
	/**
	 * sets this object's {@link CollisionManager} to the new CollisionManager instance.
	 * @param cm 
	 * 				CollisionManager
	 */
	public void setCollisionManager(CollisionManager cm){
		this.cm = cm;
	}
	
	public void shoot(){
		if(bm != null)
			bm.addBullet(wp.get(switcher).getCenter().X-add, wp.get(switcher).y);
	}
	
	/**
	 * This method is called to switch the players weapon when a weapon upgrade is
	 * acquired.
	 */
	public void switchWeapon(){
		
	}

	@Override
	public void draw(Graphics g) {
		
		for(int i =0; i< wp.size();i++){
			//wp.get(i).draw(g, matrix);
			wp.get(i).draw(g);
			
			bm.draw(g);
		}
		
		
	}

	@Override
	public void update(float delta) {
		
		for(int i =0; i< wp.size();i++){
			wp.get(i).setLocation(player.x+2, player.y+2);
			//matrix.setTranslate(player.x+2, player.y+2);
			bm.update(delta);
		}
		
		
		
	}
	/**
	 * Sets the default bulletmanager. This allows the Weapon manager to generate bullets for its
	 * weapons.
	 * @param bm
	 * 				BulletManager
	 */
	public void setBulletManager(BulletManager bm){
		this.bm = bm;
	}
	
	public void calRotation(float x,float y){
		//matrix = new Matrix();
		float degrees = computeAngle(x, y);		
		float px = wp.get(0).width/2;
		float py = wp.get(0).height/2;
		//matrix.preRotate(degrees, px, py);
	}
	/**
	 * Calculates the angle from the center of the ship to the touchpoint.
	 * @param x
	 * 			coordinate x
	 * @param y
	 * 			coordinate y
	 * @return 
	 * 			degrees
	 */
	public float computeAngle(float x, float y)
	
	{
		float dx= x- player.getCenter().X;
		float dy= y - player.y;
		
		//float result = (float) Math.toDegrees(Math.atan2(dx, -dy));		
		//result -= Math.PI/2.0;		
		Vector nor = normalize(x,y);
		
		double RADS_TO_DEGREES = 180 / (Math.PI*2);
	    double result = Math.atan2(dx,-dy) * RADS_TO_DEGREES;
	    //180 + result * (180 / Math.PI);

	    if (result < 0)
	    {
	        result += 360;
	    }

	    return (float)result;
	}
	
	private Vector normalize(float x, float y){
		
		//find the vector's length
		float length = (float)Math.sqrt((x * x) + (y * y)) ;	
		
		//now normalize
		Vector nom = new Vector(x/length,y/length); 
		return nom;
	}
}
