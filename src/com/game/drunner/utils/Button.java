package com.game.drunner.utils;

import java.util.List;

import com.game.drunner.Settings;
import com.game.drunner.State;
import com.game.drunner.maths.Rectangle;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import mygame.gameframework.imp.AndroidPixmap;
import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;
import mygame.gameframework.utils.Graphics.PixmapFormat;
import mygame.gameframework.utils.Input.TouchEvent;

/**
 * <h2>Button<h2>
 * This button class takes two pixmap objects to represent the button states
 * when it is pressed and released;
 * 
 * @author Clemaurde
 * 
 */
public class Button extends Rectangle implements Drawables, Updatables {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pixmap buttonDown;
	private Pixmap buttonUp;
	protected State state;
	public boolean isTouched =false, onRelease;
	private float scale;
	private List<TouchEvent> touchEvents;
	private Handler handle;
	private String btnName;
	// public int x;
	// public int y;
	// public int width;
	// public int height;

	public Button() {
		super();
	}

	/**
	 * Constructor: sets the images for the buttons pressed and released states
	 * 
	 * @param btnUp
	 * @param btnDown
	 */
	public Button(String btnName ,Pixmap btnUp, Pixmap btnDown) {
		super();
		this.btnName = btnName;
		buttonDown = btnDown;
		buttonUp = btnUp;
		state = State.UP;
		scale = Settings.scale_2;
		handle = new Handler();

		this.setBounds(0, 0, buttonDown.getWidth(), buttonDown.getHeight());
	}

	public Button(String btnName,Pixmap buttons) {
		super();
		this.btnName = btnName;
		scale = Settings.scale_2;

		Bitmap resizedbitmap1 = Bitmap.createBitmap(
				((AndroidPixmap) buttons).bitmap, 216, 407, 230, 84);
		Bitmap resizedbitmap2 = Bitmap.createBitmap(
				((AndroidPixmap) buttons).bitmap, 448, 407, 230, 84);

		buttonUp = new AndroidPixmap(resizedbitmap1, PixmapFormat.ARGB8888);
		buttonDown = new AndroidPixmap(resizedbitmap2, PixmapFormat.ARGB8888);

		handle = new Handler();
		state = State.UP;

		this.setBounds(0, 0, buttonUp.getWidth() / scale, buttonUp.getHeight()
				/ scale);
		//System.out.println(this);
	}

	/**
	 * Sets the click state of the button i.e. whether it is down or up
	 * 
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Gains access to android's touch Input Events and uses it to detect if
	 * itself is clicked.
	 * 
	 * @param touchEvents
	 */
	public void setInputProcessor(List<TouchEvent> touchEvents) {
		this.touchEvents = touchEvents;
	}

	/**
	 * Takes screen touched coordinates and checks if itself has been touched. A
	 * less expensive way of getting events without using setInputProcessor.
	 * Since the class that passes the event will already make the call inside a
	 * loop, there is no need for created another iterator for TouchEvents.
	 * 
	 * @param event
	 */
	public void touchUpdate(TouchEvent event) {

		if (event.type == TouchEvent.TOUCH_DOWN) {
			//Log.d("Event TouchDown", event.x + " : " + event.y);
			if (this.contains(event.x, event.y)) {
				if (!isTouched) {
					this.setState(State.DOWN);
					isTouched = true;
					onRelease = false;
				}

			}
		}
		
		if (event.type == TouchEvent.TOUCH_UP) {
			//Log.d("Event TouchUp", event.x + " : " + event.y);
			if (this.contains(event.x, event.y)) {				
				if(isTouched)
					handle.postDelayed(new ClickDelay(), 100);
			}
		}
	}

	@Override
	public void setLocation(float x, float y) {
		super.setLocation(x, y);
	}

	@Override
	public void draw(Graphics g) {
				
		switch (state) {
		case UP:
			g.drawPixmap(buttonUp, x, y, Settings.scale_2, buttonUp.getWidth(),
					buttonUp.getHeight());
			break;
		case DOWN:
			g.drawPixmap(buttonDown, x, y, Settings.scale_2,
					buttonUp.getWidth(), buttonUp.getHeight());
		default:
			break;
		}
		g.drawText(btnName, getCenter().X ,getCenter().Y, 30, true);
		// state = State.UP;

	}

	@Override
	public void update(float delta) {

		if (touchEvents != null) {
			for (int i = 0; i < touchEvents.size(); i++) {
				TouchEvent event = touchEvents.get(i);
				if (event.type == TouchEvent.TOUCH_DOWN) {
					if (this.contains(event.x, event.y)) {
						this.setState(State.DOWN);
					}
				}
				if (event.type == TouchEvent.TOUCH_UP) {
					Log.d("Event TouchUp", event.x + " : " + event.y);
					if (this.contains(event.x, event.y)) {
						//System.out.println("clicked");
						this.setState(State.UP);
					}
				}
			}
		}

	}

	protected class ClickDelay implements Runnable {

		@Override
		public void run() {
			state = State.UP;
			isTouched = false;
			onRelease = true;

		}

	}

}
