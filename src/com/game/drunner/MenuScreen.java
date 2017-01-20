package com.game.drunner;

import java.util.List;

import com.game.drunner.utils.Button;

import android.graphics.Point;
import android.util.Log;
import mygame.gameframework.utils.Game;
import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Input.TouchEvent;
import mygame.gameframework.utils.Screen;

public class MenuScreen extends Screen {

	Button playbutton;
	Button helpbutton;
	Point center;

	public MenuScreen(Game game) {
		super(game);

		center = new Point(game.getGraphics().getWidth() / 2, game
				.getGraphics().getHeight() / 2);
		System.out.println("canvas size: "+center);

		playbutton = new Button("PLAY",Resources.buttons);
		playbutton.setLocation(center.x - (playbutton.width/2), 180);
		
		helpbutton = new Button("HELP",Resources.buttons);
		helpbutton.setLocation(center.x - (helpbutton.width/2), 280);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			playbutton.touchUpdate(event);			
			helpbutton.touchUpdate(event);
			
		}
		
		if(playbutton.onRelease){
			game.setScreen(new GameScreen(game));
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Resources.mainMenu, 0, 0);
		playbutton.draw(g);
		helpbutton.draw(g);

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
		// TODO Auto-generated method stub

	}

}
