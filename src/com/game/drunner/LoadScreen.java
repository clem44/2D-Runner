package com.game.drunner;


import android.os.Handler;
import mygame.gameframework.utils.Graphics.*;
import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Game;
import mygame.gameframework.utils.Screen;

/**
 * First screen displayed when app is ran. Load screen allows
 * the application's resources to be loaded from asset folder.
 * @author Clemaurde
 *
 */
public class LoadScreen extends Screen {

	Handler myhandler;
	Graphics graphics;
	
	public LoadScreen(Game game) {
		super(game);
		myhandler = new Handler();
		
		graphics = game.getGraphics();
		Resources.splashscreen = graphics.newPixmap("SplashScreen.jpg", PixmapFormat.ARGB8888);		
		Resources.mainMenu = graphics.newPixmap("d-runner.jpg", PixmapFormat.ARGB8888);
		Resources.background0 = graphics.newPixmap("bk1.jpg", PixmapFormat.ARGB8888);
		Resources.background1 = graphics.newPixmap("bk2.jpg", PixmapFormat.ARGB8888);
		Resources.background2 = graphics.newPixmap("bk3.jpg", PixmapFormat.ARGB8888);
		Resources.background3 = graphics.newPixmap("bk4.jpg", PixmapFormat.ARGB8888);
		Resources.buttons = graphics.newPixmap("buttonpack.png", PixmapFormat.ARGB8888);
		Resources.ship = graphics.newPixmap("shipPack.png", PixmapFormat.ARGB8888);
		Resources.weapon1 = graphics.newPixmap("weapon/ship_gun.png", PixmapFormat.ARGB8888);
		Resources.bullets = graphics.newPixmap("weapon/Bullet1.png", PixmapFormat.ARGB8888);
		Resources.enemy = graphics.newPixmap("enemypack.png", PixmapFormat.ARGB8888);
		Resources.click = game.getAudio().newSound("audio/click.ogg");
		Resources.shoot = game.getAudio().newSound("audio/lazerbullet.wav"); 
		myhandler.postDelayed(new myRunnable(), 1500);
	}

	@Override
	public void update(float deltaTime) {
		
		
	}

	@Override
	public void present(float deltaTime) {
		graphics.drawPixmap(Resources.splashscreen, 0, 0);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
		
	}
	
	public class myRunnable implements Runnable{

		@Override
		public void run() {			
			
			game.setScreen(new MenuScreen(game));
		}
		
	}

}
