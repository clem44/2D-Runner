package mygame.gameframework.utils;

import android.graphics.Matrix;



public interface Graphics {
	
	public static enum PixmapFormat {
		ARGB8888, ARGB4444, RGB565
	}
	
	public Pixmap newPixmap(String fileName, PixmapFormat format);
	
	public void clear(int color);
	
	public void drawPixel(int x, int y, int color);
	
	public void drawLine(int x, int y, int x2, int y2, int color);
	
	//public void drawLine(int x, int y, int x2, int y2, Color color);
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public void drawRect(int x, int y, int width, int height, int color);
	/**
	 * Draws 
	 * @param pixmap
	 * @param x
	 * @param y
	 * @param srcX = x location of mesh
	 * @param srcY = y location of mesh
	 * @param srcWidth = mesh width
	 * @param srcHeight = mesh height
	 */
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, 
			int srcWidth, int srcHeight);
	
	public void drawPixmap(Pixmap pixmap, float x, float y, float scale,
			float srcWidth, float srcHeight);
	
	public void drawPixmap(Pixmap pixmap, float x, float y);
	
	/**
	 * Draws text at specified coordinates
	 * @param text
	 * @param x
	 * @param y
	 * @param textSize
	 * @param alignCenter
	 */
	public void drawText(String text, float x, float y, int textSize, boolean alignCenter);
	
	public int getWidth();
	
	public int getHeight();
	
	public int getScreenWidth();
	
	public int getScreenHeight();
	
	public void setScreenSize(int width, int height);

	public void drawRect(float x, float y, float width, float height, int color);
	
	public void drawPixmap(Pixmap pixmap, Matrix matrix);

}
