package com.game.drunner;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.game.drunner.Actor.Ship;
import com.game.drunner.utils.BulletManager;
import com.game.drunner.utils.CollisionManager;
import com.game.drunner.utils.EnemyManager;
import com.game.drunner.utils.ImageScroller;
import com.game.drunner.utils.WeaponManager;

import mygame.gameframework.utils.Game;
import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;
import mygame.gameframework.utils.Screen;
import mygame.gameframework.utils.Input.TouchEvent;
/**
 * This object extends screen. The actual game play is shown in this screen.
 * @author Clemaurde
 *
 */
public class GameScreen extends Screen {

	ImageScroller map;
	private ArrayList<Pixmap> images;
	Ship player;
	WeaponManager wm;
	BulletManager bm;
	EnemyManager em;
	CollisionManager cm;
	
	private float timer1=0, timer2=0;
	private float volume = 4;

	// BulletManager bm;
	// game states
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";

	public GameScreen(Game game) {
		super(game);

		images = new ArrayList<Pixmap>();
		images.add(Resources.background0);
		images.add(Resources.background1);
		images.add(Resources.background2);
		images.add(Resources.background3);

		map = new ImageScroller(images, true);
		player = new Ship(Resources.ship, 4, 1);
		player.setLocation(120, 326);
		
		
		bm = new BulletManager(Resources.bullets);
		//bm.setCollisionManager(cm);
		wm = new WeaponManager(Resources.weapon1, player);
		wm.setBulletManager(bm);		
		player.setWeaponManager(wm);
		
		em = new EnemyManager(Resources.enemy, 2, player);
		//em.setCollisionManager(cm);
		em.addEnemy(1);
		
		cm = new CollisionManager(player,em,bm);

	}

	@Override
	public void update(float deltaTime) {
		
		map.update(deltaTime);
		// checks for touch events
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		switch(state){
		case Ready:
			
			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(i);
				if (event.type == TouchEvent.TOUCH_UP) {
					if(!player.contains(event.x,event.y)){
						wm.shoot();						
						Resources.shoot.play(volume);
					}
				}
				if (event.type == TouchEvent.TOUCH_DOWN) {
					
				}
				if (event.type == TouchEvent.TOUCH_DRAGGED) {
					//Log.d("Event Touchdragg", event.x + " : " + event.y);
					if(player.contains(event.x,event.y))
						player.setCenter(event.x, event.y);
					//Log.d(" final position", player.x + " : " + player.y);
				}

			}

			player.update(deltaTime);
			em.update(deltaTime);
			cm.checkAllCollisions();
			
			if(!player.isAlive())
				state = GameState.GameOver;		
			
			break;
		case GameOver:
			
			
			break;
		case Paused:
			break;
		case Running:
			break;
		default:
			break;
		}		
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();	
		
		timer1 +=deltaTime;
		map.draw(g);
		player.draw(g);
		em.draw(g);
		
		switch(state){
		case GameOver:
			
			g.drawText("Game over", g.getWidth()/2, g.getHeight()/2, 30, true);
			if(timer1>5)
				game.setScreen(new MenuScreen(game));
			break;
		case Paused:
			break;
		case Ready:
			break;
		case Running:
			break;
		default:
			break;
		
		}

	}

	@Override
	public void pause() {
	
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		
	}

}
