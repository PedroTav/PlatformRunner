package com.runner.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.runner.game.tools.TextureManager;

public class Brick extends GameObject {

	Rectangle hitBox;
	Sprite sprite;
	
	public Brick(int x, int y) {
		hitBox = new Rectangle(x, y, 70, 70);
		sprite = new Sprite(TextureManager.brick, 0, 0, 70, 70);
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
		hitBox.x = x;
		hitBox.y = y;
		sprite.setPosition(x, y);
	}

	@Override
	public void moveLeft(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
		
	}

	
	@Override
	public Rectangle getHitBox() {
		return hitBox;
	}

	@Override
	public int hitAction(int side) {
		// TODO Auto-generated method stub
		return 1;
	}

}
