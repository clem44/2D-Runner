package com.game.drunner.Actor;

import android.graphics.Matrix;
import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;

/**
 *A simple image class that adds position and rotation to a {@link Pixmap} 
 * @author Clemaurde
 *
 */
@SuppressWarnings("serial")
public class Texture extends Actor {

	public Texture(Pixmap image) {
		super(image);
		
	}
	
	public Texture(Pixmap image, int col, int row) {
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
