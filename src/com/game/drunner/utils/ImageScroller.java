package com.game.drunner.utils;

import java.util.ArrayList;

import com.game.drunner.maths.Rectangle;

import android.support.annotation.Nullable;

import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;

public class ImageScroller implements Drawables, Updatables {

	private ArrayList<Pixmap> images;
	private ArrayList<Rectangle> rect;
	private Pixmap image1;
	private int size;
	private int numOfSlides;
	private float imageHeight;
	private float imageWidth;
	private boolean verticalScroll;
	float velocity = 0.3f;

	public ImageScroller(Pixmap img, @Nullable Pixmap img2) {

	}

	/**
	 * 
	 * @param images
	 *            object array of pixmap images to be used in scrolling
	 * @param scrollVerticle
	 *            sets the background scrolling direction. If true then verticle
	 *            else direction is horizontal
	 */
	public ImageScroller(ArrayList<Pixmap> images, boolean scrollVertical) {

		this.images = new ArrayList<Pixmap>(images);
		rect = new ArrayList<Rectangle>();
		size = images.size();
		numOfSlides = size - 1;
		imageHeight = images.get(0).getHeight();
		imageWidth = images.get(0).getWidth();
		verticalScroll = scrollVertical;

		if (scrollVertical)
			setVerticalScroll();
		else
			setHorizontalScroll();

	}
/**
 * Sets the scrolling orientation to vertical. Images will scroll from top to bottom.
 */
	public void setVerticalScroll() {
		for (int i = 0; i < size; i++) {
			if (i == 0)
				rect.add(new Rectangle(0, 0, imageWidth, imageHeight));
			else
				rect.add(new Rectangle(0, rect.get(i - 1).y - imageHeight,
						imageWidth, imageHeight));
		}
	}

	/**
	 * Sets the scrolling orientation to horizontal. Images will scroll from right to left.
	 */
	public void setHorizontalScroll() {

	}

	@Override
	public void update(float delta) {

		// update images positions
		if (verticalScroll)
			for (int i = 0; i < rect.size(); i++) {
				rect.get(i).add(0, 0.5f);
				if (rect.get(i).y >=imageHeight) {
					rect.get(i).setLocation(0, -1*(imageHeight * numOfSlides));
				}
			}
		else{
			for (int i = 0; i < rect.size(); i++) {
				rect.get(i).sub(0.1f, 0);
				if (rect.get(i).y <= -1*imageHeight) {
					rect.get(i).setLocation(0, imageHeight * numOfSlides);
				}
			}
		}

	}

	@Override
	public void draw(Graphics g) {

		for (int i = 0; i < rect.size(); i++) {
			g.drawPixmap(images.get(i), rect.get(i).x, rect.get(i).y);
		}

	}

}
