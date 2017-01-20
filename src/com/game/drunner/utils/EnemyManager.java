package com.game.drunner.utils;

import java.util.ArrayList;
import java.util.Random;

import com.game.drunner.Actor.Enemy;
import com.game.drunner.Actor.Ship;
import com.game.drunner.maths.Rectangle;
import com.game.drunner.maths.Vector;

import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;

/**
 * Manages the enemy objects created
 * 
 * @author Clemaurde
 * 
 */
public class EnemyManager implements Updatables, Drawables {

	ArrayList<Enemy> enemies;
	Ship player;
	Pixmap img;
	Vector position;
	float velocity = 2f;
	Random rand;
	CollisionManager cm;

	public EnemyManager(Pixmap image, int mesh, Ship player) {

		img = image;
		enemies = new ArrayList<>();
		rand = new Random();
		this.player = player;
	}

	/**
	 * sets this object's {@link CollisionManager} to the new CollisionManager
	 * instance.
	 * 
	 * @param cm
	 *            CollisionManager
	 */
	public void setCollisionManager(CollisionManager cm) {
		this.cm = cm;
	}

	/**
	 * Method for adding different enemy types. Used for Debugging
	 * 
	 * @param type
	 */
	public void addEnemy(int type) {

		switch (type) {
		case 1:
			for (int i = 0; i < 3; i++) {
				Enemy e = new Enemy(img, new Rectangle(2, 2, 100, 97));
				e.setLocation(Rand(3), -97);
				enemies.add(e);
			}
			break;
		case 2:
			for (int i = 0; i < 4; i++) {
				Enemy e1 = new Enemy(img, new Rectangle(104, 2, 100, 97));
				e1.setLocation(Rand(3), -97);
				enemies.add(e1);
			}
			break;
		case 3:
			for (int i = 0; i < 3; i++) {
				Enemy e2 = new Enemy(img, new Rectangle(206, 2, 100, 97));
				e2.setLocation(Rand(3), -97);
				enemies.add(e2);
			}
			break;
		}

	}

	@Override
	public void draw(Graphics g) {
		if (!enemies.isEmpty())
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).isVisible() || enemies.get(i).isAlive()) {
					enemies.get(i).draw(g);
				}
			}
	}

	@Override
	public void update(float delta) {
		if (!enemies.isEmpty()) {
			// System.out.println("BM: list not empty");
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).isVisible()|| enemies.get(i).isAlive()) {
					enemies.get(i).add(0, velocity);
					enemies.get(i).update(delta);
					// if tru animate..remove
					
					//
					if (enemies.get(i).y > 480)
						enemies.get(i).setVisible(false);
				}
			}

			for (int i = 0; i < enemies.size(); i++) {
				if (!enemies.get(i).isVisible()|| !enemies.get(i).isAlive()) {
					System.out.println("enemy removed");
					enemies.remove(enemies.get(i));

				}
			}
			
		}
		else{
			addEnemy(1);
		}

	}

	/**
	 * Generates a position on the grid to place a new enemy object
	 * 
	 * @param num
	 * @return grid column number
	 */
	public int Rand(int num) {

		int max = num;
		int min = 0;
		int answer = rand.nextInt((max - min + 1) + min);

		switch (answer) {
		case 0:
			return 0;
		case 1:
			return 106;
		case 2:
			return 212;		
		default:
			return 0;
		}

	}

	public ArrayList<Enemy> getEnemies() {
		if (!enemies.isEmpty())
			return enemies;
		else
			return null;
	}

}
