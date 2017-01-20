package com.game.drunner.utils;

import android.graphics.Bitmap;
import android.graphics.Point;
import mygame.gameframework.imp.AndroidPixmap;
import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;
import mygame.gameframework.utils.Graphics.PixmapFormat;

/*
 * 
 */
public class Animation implements Drawables, Updatables{

	//private Pixmap[] frames;
	public int length;
	private Pixmap[][] scenes;
	private float time;
	private float delay;
	public float deltaTime = 0.0f;
	private Point currentFrame;
	//private Point point ;
	private int col, row = 0;
	public int frameWidth;
	public int frameHeight;

	public Animation(Pixmap sprite, int col, int row) {

		this.col = col;
		this.row = row;
		frameWidth = sprite.getWidth() / col;
		frameHeight = sprite.getHeight() / row;
		
		
		scenes = new Pixmap[col][row];
		
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				
				Bitmap resizedbitmap1 = Bitmap.createBitmap(
						((AndroidPixmap) sprite).bitmap, i*frameWidth, j*frameHeight, frameWidth,
						frameHeight);
				
				scenes[i][j] = new AndroidPixmap(resizedbitmap1, PixmapFormat.ARGB8888);
			}
		}
		delay = 0.08f;
		currentFrame = new Point(0,0);
	}

	@Override
	public void update(float delta) {
		if (delay <= 0)
			return;
		time += delta;
		while (time >= delay) {
			step();
		}

		
	}
	/**
	 * Loops through each frame
	 */
	private void step() {
		time -= delay;
		currentFrame.x++;
		
		if (currentFrame.x == col) {
			currentFrame.x = 0;			
		}
		if(currentFrame.y == row){
			currentFrame.y = 0;
		}
	}
	/**
	 * Sets the frame position on the x axis of the animation grid
	 * 
	 */
	public void setFrameRow(int y){
		currentFrame.y = y;
	}
	/**
	 * Sets the frame position on the y axis of the animation grid
	 */
	public void setFrameCol(int x){
		currentFrame.x = x;
	}
	public Pixmap getCurrentFrame() {
		return scenes[currentFrame.x][currentFrame.y];
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
