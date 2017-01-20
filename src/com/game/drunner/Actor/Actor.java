package com.game.drunner.Actor;

import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;

import com.game.drunner.State;
import com.game.drunner.maths.Rectangle;
import com.game.drunner.maths.Vector;
import com.game.drunner.utils.Animation;
import com.game.drunner.utils.Drawables;
import com.game.drunner.utils.Updatables;

public class Actor extends Rectangle implements Updatables, Drawables {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean hit;
	boolean alive, visible;
	protected Pixmap actor;
	protected State state;
	Animation animate;
	protected int health;

	public Actor(Pixmap image) {
		super();
		alive = true;
		visible = true;
		hit = false;
		actor = image;
		health = 100;
		this.setBounds(0, 0, image.getWidth(), image.getHeight());
	}

	public Actor(Pixmap image, int col, int row) {
		super();
		alive = true;
		visible = true;
		hit = false;
		health = 100;
		animate = new Animation(image, col, row);
		this.setBounds(0, 0, animate.frameWidth, animate.frameHeight);
	}

	@Override
	public void draw(Graphics g) {

		if (alive)
			if (animate == null)
				g.drawPixmap(actor, x, y);
			else
				g.drawPixmap(animate.getCurrentFrame(), x, y);
	}

	@Override
	public void update(float delta) {
		animate.update(delta);

	}
	/**
	 * Called if the player has been hit. i.e. collides into an enemy
	 * @return 
	 * 			boolean hit
	 */
	public void isHit(int dmg){
		;
	}
	/**
	 * Checks if the actor is still alive.
	 * @return
	 * 			boolean alive
	 */
	public boolean isAlive(){
		return alive;
	}
	/**
	 * Declares whether the actor is alive or not
	 * @param alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * Checks if the actor is still visible.
	 * @return
	 * 			boolean alive
	 */
	public boolean isVisible(){
		return visible;
	}
	/**
	 * Sets the actor's visibility
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setCenter(float x, float y, float w, float h){
		
		this.x = (x+w/2) - getCenter().X ;
		this.y = (y+h/2) - getCenter().Y ;
		
	}
	public void setCenter(float x, float y){
		
		this.x = x - getCenter().X;
		this.y = y - getCenter().Y;
		
	}
	
	@Override
	public Vector getCenter() {
		Vector vector = new Vector();
		vector.X = x + (width / 2);
		vector.Y = y + (height / 2);
		return vector;
	}


}
