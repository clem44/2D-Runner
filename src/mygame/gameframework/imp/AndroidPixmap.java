package mygame.gameframework.imp;

import android.graphics.Bitmap;

import mygame.gameframework.utils.Graphics.PixmapFormat;
import mygame.gameframework.utils.Pixmap;

public class AndroidPixmap implements Pixmap {
    public Bitmap bitmap;
    PixmapFormat format;
    
    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public PixmapFormat getFormat() {
        return format;
    }

    public void dispose() {
        bitmap.recycle();
    }      
}
