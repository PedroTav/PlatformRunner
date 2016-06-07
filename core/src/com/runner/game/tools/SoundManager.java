package com.runner.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	public static Sound jump1;
	public static Sound jump2;
	public static Sound death1;
	public static Sound death2;
	public static Music background;
	
	public static void create() {
		background =  Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
		jump1 = Gdx.audio.newSound(Gdx.files.internal("jump1.wav"));
		jump2 = Gdx.audio.newSound(Gdx.files.internal("jump2.wav"));
		death1 = Gdx.audio.newSound(Gdx.files.internal("death1.wav"));
		death2 = Gdx.audio.newSound(Gdx.files.internal("death2.wav"));
	}
	
	
	public static void dispose() {
		background.dispose();
		jump1.dispose();
		jump2.dispose();
		death1.dispose();
		death2.dispose();
	}
}
