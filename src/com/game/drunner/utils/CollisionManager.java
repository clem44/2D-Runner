package com.game.drunner.utils;

import java.util.ArrayList;

import com.game.drunner.Actor.Bullet;
import com.game.drunner.Actor.Enemy;
import com.game.drunner.Actor.Ship;
import com.game.drunner.maths.Rectangle;

/**
 * A simple Object for checking intersects between rectangle objects and or
 * circle objects.
 * 
 * @author Clemaurde
 * 
 */
public class CollisionManager {

	Ship player;
	EnemyManager em;
	BulletManager bm;

	public CollisionManager() {

	}

	public CollisionManager(Ship player, EnemyManager em, BulletManager bm) {
		super();
		this.player = player;
		this.em = em;
		this.bm = bm;
	}

	/**
	 * 
	 * @param r1
	 *            - first Rectangle
	 * @param r2
	 *            - second Rectangle
	 * @return whether the two rectangle objects collided with another.
	 */
	public boolean isColliding(Rectangle r1, Rectangle r2) {

		return r1.intersects(r2);
	}

	/**
	 * This method checks for collisions between all active Actor objects i.e.
	 * enemies, player and bullets
	 */
	public void checkAllCollisions() {

		ArrayList<Enemy> enemies = em.getEnemies();
		ArrayList<Bullet> bullets = bm.getBullets();

		if (enemies != null)
			for (Enemy e : enemies) {
				if (isColliding(player, e)) {
					player.isHit(1);
				}

				if (bullets != null)
					for (int b =0; b<bullets.size();b++) {
						if(isColliding(bullets.get(b), e)) {
							bullets.get(b).setAlive(false);							
							e.isHit(5);
							//System.out.println("enemy hit");
						}
					}
			}
		

	}
}
