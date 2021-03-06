package com.game.drunner.Actor;

import android.graphics.Matrix;
import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;



public class Bullet extends Actor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bullet(Pixmap image) {
		super(image);
		
	}
	
	public Bullet(Pixmap image, int col, int row) {
		super(image, col, row);
		
	}
	
	@Override
	public void update(float delta) {
		if(animate != null)
			animate.update(delta);	

	}
	
	public void draw(Graphics g , Matrix m) {

		if (alive)
			if (animate == null){				
				g.drawPixmap(actor, m);
			}
			else
				g.drawPixmap(animate.getCurrentFrame(), m);
	}
	

}
