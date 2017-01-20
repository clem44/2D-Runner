package com.game.drunner;

import com.game.drunner.maths.Rectangle;

/**
 * World provides a 2D grid system for the game. The world is used to restrict movement of
 * objects within the world.
 * @author Clemaurde
 *
 */
public class World {

	static final int WORLD_WIDTH = 3;
	static final int WORLD_HEIGHT = 8;
	static final int SCORE_INCREMENT = 10;
	static final float TICK_INITIAL = 0.5f;
	static final float TICK_DECREMENT = 0.05f;
	
	private Rectangle[][] grid;

	public World() {
		
		grid = new Rectangle[WORLD_WIDTH][WORLD_HEIGHT];
		for(int i =0; i< WORLD_WIDTH; i++){
			for(int j=0; j< WORLD_HEIGHT;j++){
				grid[i][j] = new Rectangle(i*WORLD_WIDTH,j*WORLD_HEIGHT,
						WORLD_WIDTH, WORLD_HEIGHT);
			}
		}

	}
	
}
