package com.game.drunner.Actor;

import com.game.drunner.maths.Rectangle;

import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;

public class Enemy extends Actor {

	/**
	 * 
	 */
	Rectangle rect;
	private static final long serialVersionUID = 1L;

	public Enemy(Pixmap image) {
		super(image);		
	}
	
	public Enemy(Pixmap image, Rectangle rec) {
		super(image);	
		health = 100;
		rect = rec;
		setBounds(rec);
	}
	
	

	@Override
	public void isHit(int dmg){
		health-=dmg;
		if(health<=0)
			this.setAlive(false);
	} 
	
	@Override
	public void update(float deltaTime){
		
	}
	
	@Override
	public void draw(Graphics g){
		g.drawPixmap(actor,(int)this.x,(int)this.y, (int)rect.x, (int) rect.y, (int)rect.width, (int)rect.height);
	}

}
