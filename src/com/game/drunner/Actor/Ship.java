package com.game.drunner.Actor;

import android.graphics.Color;
import android.util.Log;

import com.game.drunner.Settings;
import com.game.drunner.utils.WeaponManager;

import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;

public class Ship extends Actor {

	/**
	 * Represents the main character ship.
	 */
	private static final long serialVersionUID = 1L;
	Pixmap image;
	WeaponManager wm;
	public boolean debug;

	public Ship(Pixmap image, int col, int row) {
		super(image, col, row);
		debug = false;
	}

	/**
	 * Sets this object's {@link WeaponManager} to the passed WeaponManager
	 * @param wm
	 */
	public void setWeaponManager(WeaponManager wm) {
		this.wm = wm;
	}

	@Override
	public void isHit(int dmg){
		health-=dmg;
		if(health<=0)
			this.setAlive(false);
	}

	@Override
	public void update(float delta) {
		animate.update(delta);
		if (wm != null) {
			wm.update(delta);
		}

	}

	@Override
	public void draw(Graphics g) {

		if (alive)
			if (animate == null) {
				wm.draw(g);
				g.drawPixmap(actor, x, y);
			} else{
				if(debug)
					g.drawRect(x, y, width, height, (int)Color.RED);
				wm.draw(g);				
				g.drawPixmap(animate.getCurrentFrame(), x, y);
			}
	}

	@Override
	public void setLocation(float x, float y) {

		// Done to ensure the ship stays within the view bounds
		if (x + this.width <= Settings.viewWidth && x > 0)
			this.x = x;		
		else {	}

		if (y + this.height <= Settings.viewHeight && y>0){
			this.y = y;			
		}
		else {	}

	}

	@Override
	public void setCenter(float x, float y) {		

		if (x + (this.width/2) <= Settings.viewWidth && (x-width/2) > 0){
			this.x = x-(width/2);			
			}
		else {	}

		if (y + (this.height/2) <= Settings.viewHeight && (y-height/2) >0){
			this.y = y -(height/2);			
			}
		else {	}
	}
	


}
