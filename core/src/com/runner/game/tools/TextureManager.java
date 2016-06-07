package com.runner.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
	public static Texture brick;
	public static Texture coin;
	public static Texture hero;
	public static Texture water;
	public static Texture poker;
	public static Texture trampoline;
	public static Texture background;
	public static Texture select;
	public static Texture end;
	public static Texture checkpoint1;
	public static Texture checkpoint2;
	
	public static void create() {
		
		System.out.println(Gdx.files.internal("grass.png").toString());
		
		brick = new Texture(Gdx.files.internal("grass.png"));
		coin = new Texture(Gdx.files.internal("coin.png"));
		hero = new Texture(Gdx.files.internal("herosequence.png"));
		water = new Texture(Gdx.files.internal("water.png"));
		poker = new Texture(Gdx.files.internal("poker.png"));
		trampoline = new Texture(Gdx.files.internal("trampoline.png"));
		background = new Texture(Gdx.files.internal("clouds.png"));
		select = new Texture(Gdx.files.internal("select.png"));
		end = new Texture(Gdx.files.internal("end.png"));
		checkpoint1 = new Texture(Gdx.files.internal("checkpoint1.png"));
		checkpoint2 = new Texture(Gdx.files.internal("checkpoint2.png"));
	}
	
	public static void dispose() {
		
		brick.dispose();
		coin.dispose();
		hero.dispose();
		water.dispose();
		poker.dispose();
		trampoline.dispose();
		background.dispose();
		select.dispose();
		end.dispose();
		checkpoint1.dispose();
		checkpoint2.dispose();
		
	}
}
