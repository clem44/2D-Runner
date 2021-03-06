package mygame.gameframework.imp;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import mygame.gameframework.utils.Graphics;
import mygame.gameframework.utils.Pixmap;

public class AndroidGraphics implements Graphics {
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();
	Point screenSize;

	public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
		this.assets = assets;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);		
		
		this.paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		//paint.setDither(true);
		
		screenSize = new Point();
	}

	public void setScreenSize(int width, int height) {
		screenSize.set(width, height);
	}

	public Pixmap newPixmap(String fileName, PixmapFormat format) {
		Config config = null;
		if (format == PixmapFormat.RGB565)
			config = Config.RGB_565;
		else if (format == PixmapFormat.ARGB4444)
			config = Config.ARGB_4444;
		else
			config = Config.ARGB_8888;

		Options options = new Options();
		options.inPreferredConfig = config;

		InputStream in = null;
		Bitmap bitmap = null;
		try {
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in);
			if (bitmap == null)
				throw new RuntimeException("Couldn't load bitmap from asset '"
						+ fileName + "'");
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load bitmap from asset '"
					+ fileName + "'");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

		if (bitmap.getConfig() == Config.RGB_565)
			format = PixmapFormat.RGB565;
		else if (bitmap.getConfig() == Config.ARGB_4444)
			format = PixmapFormat.ARGB4444;
		else
			format = PixmapFormat.ARGB8888;

		return new AndroidPixmap(bitmap, format);
	}

	public void clear(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
				(color & 0xff));
	}

	@Override
	public int getScreenWidth() {
		return screenSize.x;
	}

	@Override
	public int getScreenHeight() {
		return screenSize.y;
	}

	public void drawPixel(int x, int y, int color) {
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}

	public void drawLine(int x, int y, int x2, int y2, int color) {
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	@Override	
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
	}
	@Override
	public void drawRect(float x, float y, float width, float height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.STROKE);
		canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
	}

	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight) {
		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX + srcWidth - 1;
		srcRect.bottom = srcY + srcHeight - 1;

		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + srcWidth - 1;
		dstRect.bottom = y + srcHeight - 1;

		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect,
				null);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, float x, float y, float scale,
			float srcWidth, float srcHeight) {
		// srcRect = srcXY;
		dstRect.left = (int) x;
		dstRect.top = (int) y;
		dstRect.right = (int) (x + srcWidth / scale);
		dstRect.bottom = (int) (y + srcHeight / scale);

		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, null, dstRect, null);

	}

	public void drawPixmap(Pixmap pixmap, float x, float y) {
		
		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, x, y, paint);
	}

	public int getWidth() {
		return frameBuffer.getWidth();
	}

	public int getHeight() {
		return frameBuffer.getHeight();
	}

	@Override
	public void drawText(String text, float x, float y, int size, boolean alignCenter) {
		if(alignCenter){
			float width = paint.measureText(text, 0, text.length());
			float x1 = x-(width/2);
			paint.setTextSize(size);
			canvas.drawText(text, x1, y+(size*0.30f), paint);	
		}
		else{
			paint.setTextSize(size);
			canvas.drawText(text, x, y, paint);	
		}
		
				
	}

	@Override
	public void drawPixmap(Pixmap pixmap, Matrix matrix) {
		
		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, matrix, paint);
		
	}
}
