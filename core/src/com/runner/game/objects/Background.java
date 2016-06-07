package com.runner.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.runner.game.tools.TextureManager;

public class Background extends GameObject {

	Sprite sprite;
	
	public Background(float x, float y) {
		sprite = new Sprite(TextureManager.background, 0, 0, 1024, 480);
		setPosition(x,y);
	}
	
	@Override
	public int hits(Rectangle r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void action(int type, float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
		
	}

	@Override
	public void moveLeft(float delta) {
		int speed = 25;
		setPosition(sprite.getX() - (speed * delta), sprite.getY());
		
	}

	@Override
	public void moveRight(float delta) {
		int speed = 10;
		setPosition(sprite.getX() + (speed * delta), sprite.getY());
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
		
	}

	@Override
	public Rectangle getHitBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hitAction(int side) {
		// TODO Auto-generated method stub
		return 0;
	}

}
