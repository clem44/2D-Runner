package com.game.drunner;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

	//Android has its own builtin .settings for storing private primitive data
	//and can be accessed by calling sharedpreferences.
	//Sharepreferences is an object that allows you to modify the file with simple string/int values.
	public static final float scale_1 = 1;
	public static final float scale_2 = 1.25f;
	public static String MY_PREFS_NAME = "settings";
	public static SharedPreferences prefs;
	public static SharedPreferences.Editor editor;
	public static int score;
	public static int kills;
	public static int level;
	public static boolean soundOn;
	public static boolean musicOn;
	public static String plName ="";
	public static int viewWidth = 320;
	public static int viewHeight = 480;

	public static boolean soundEnabled = true;
	public static int[] highscores = new int[] { 100, 80, 50, 30, 10 };

	public Settings(MainGame game) {
		prefs = game.getApplicationContext()
				.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
		editor = prefs.edit();
		
		
		//Check if the preferences file has any values
		if(prefs.contains("highscore")){
			
		}
		else{
			editor.putString("name", "");
			editor.putInt("highscore", 0);
			editor.putBoolean("soundOn", true);
			editor.putBoolean("musicOn", true);
			editor.commit();
		}
	}

	public static void dispose() {
		
		editor.putString("name", plName);
		editor.putInt("highscore", score);
		editor.putBoolean("soundOn", soundOn);
		editor.putBoolean("musicOn", musicOn);
		editor.commit();

	}

}
