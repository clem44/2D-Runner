package com.game.drunner.utils;

import java.util.ArrayList;

import com.game.drunner.Settings;
import com.game.drunner.Actor.Bullet;
import com.game.drunner.Actor.Enemy;
import com.game.drunner.Actor.Texture;
import com.game.drunner.maths.Vector;


import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;

/**
 * Manages a collection of bullet objects {@link com.game.drunner.Actor.Bullet}
 * @author Clemaurde
 *
 */
public class BulletManager implements Updatables, Drawables {
	
	
	ArrayList <Bullet> bullets;
	Pixmap img;	
	Vector position;
	float velocity =  -8f;
	CollisionManager cm;
	//Matrix matrix;
	
	public BulletManager(){
		
	}
	
	public BulletManager(Pixmap img){
		
		this.img = img;
		bullets = new ArrayList<Bullet>();
		//bullets.add(new Texture(img));
	}
	
	/**
	 * sets this object's {@link CollisionManager} to the new CollisionManager instance.
	 * @param cm 
	 * 				CollisionManager
	 */
	public void setCollisionManager(CollisionManager cm){
		this.cm = cm;
	}
	
	
	/**
	 * Adds a new bullet object to the arraylist.
	 * @param x
	 * 			x-axis position
	 * @param y
	 * 			y-axis position
	 */
	public void addBullet(float x, float y){
		//System.out.println("addbullet");
		
		Bullet b = new Bullet(img);
		b.setLocation(x, y);		
		bullets.add(b);
	}
	@Override
	public void update(float delta) {
		if (!bullets.isEmpty()) {
			//System.out.println("BM: list not empty");
			for (int i = 0; i < bullets.size(); i++) {
				if (bullets.get(i).isVisible()) {
					bullets.get(i).add(0, velocity);
					bullets.get(i).update(delta);
					//
					
					//
					if(bullets.get(i).y< 0)
						bullets.get(i).setVisible(false);
				}
			}

			for (int i = 0; i < bullets.size(); i++) {
				if (!bullets.get(i).isVisible()) {					
					bullets.remove(bullets.get(i));
					//Constants.Ammo--;
				}
			}
		}
		
	}
	
	
	@Override
	public void draw(Graphics g) {
		if (!bullets.isEmpty())
			for (int i = 0; i < bullets.size(); i++) {
				if (bullets.get(i).isVisible()) {
					bullets.get(i).draw(g);
				}
			}	
	}

	public ArrayList<Bullet> getBullets() {
		if (!bullets.isEmpty())
			return bullets;
		else return null;
	}

}
