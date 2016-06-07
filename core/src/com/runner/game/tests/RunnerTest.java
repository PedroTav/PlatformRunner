package com.runner.game.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.runner.game.RunnerPlatformer;
import com.runner.game.objects.Background;
import com.runner.game.objects.Brick;
import com.runner.game.objects.Checkpoint;
import com.runner.game.objects.Coin;
import com.runner.game.objects.Endflag;
import com.runner.game.objects.GameObject;
import com.runner.game.objects.Hero;
import com.runner.game.objects.Poker;
import com.runner.game.objects.Select;
import com.runner.game.objects.Trampoline;
import com.runner.game.objects.Water;
import com.runner.game.tools.SoundManager;
import com.runner.game.tools.TextureManager;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class RunnerTest {

	@Test
	public void testloadLevel() {
		RunnerPlatformer Runner = new RunnerPlatformer();
		Runner.create();
		Runner.addLevel("testlevel");
		Runner.loadLevel("testlevel");
		assertEquals(new Brick(0, 0), Runner.getObjectslist().get(0));
		assertEquals(new Water(0, 0), Runner.getObjectslist().get(1));
		assertEquals(new Coin(0, 0), Runner.getObjectslist().get(2));
		assertEquals(new Poker(0, 0), Runner.getObjectslist().get(3));
		assertEquals(new Trampoline(0, 0), Runner.getObjectslist().get(4));
		assertEquals(new Checkpoint(0, 0, 1), Runner.getObjectslist().get(5));
		assertEquals(new Endflag(0, 0), Runner.getObjectslist().get(6));
		
	}
	
	@Test
	public void testcollision() {
		RunnerPlatformer Runner = new RunnerPlatformer();
		Runner.create();
		GameObject Player = new Hero();
		GameObject Brick = new Brick(100,0);
		Player.setPosition(70, 0);
		assertEquals(Player.hits(Brick.getHitBox()), 3);
		
	}

}
