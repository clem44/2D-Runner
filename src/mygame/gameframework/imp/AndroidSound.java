package mygame.gameframework.imp;

import mygame.gameframework.utils.Sound;

import android.media.SoundPool;

public class AndroidSound implements Sound {
	
	int soundId;
	SoundPool soundPool;
	
	public AndroidSound(SoundPool soundPool, int soundId) {
		this.soundPool = soundPool;
		this.soundId = soundId;
	}

	public void play(float volume) {
		soundPool.play(soundId,  volume, volume, 0, 0, 1);
	}
	
	public void dispose() {
		soundPool.unload(soundId);
	}
}
