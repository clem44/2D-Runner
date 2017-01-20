package com.game.drunner;

import mygame.gameframework.imp.AndroidGame;
import mygame.gameframework.utils.Screen;


public class MainGame extends AndroidGame {	
	
	
	Settings settings;
	
	@Override
	public Screen getStartScreen() {
		
		settings = new Settings(this);
		
		return new LoadScreen(this);
	}

	@Override
	public void dispose() {
		Settings.dispose();
		
	}
}
