package com.runner.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.runner.game.tools.TextureManager;

public class Select extends GameObject{

	Sprite sprite;
	int option_selected;
	String scene;
	
	public Select(float x, float y, String scene) {
		sprite = new Sprite(TextureManager.select, 0, 0, 22, 24);
		setPosition(x, y);
		this.scene = scene;
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

	
	public void moveLeft(float delta) {
	
	}
	
	public void move() {
		
		switch(scene) {
		case "menu":
			if(option_selected == 0){
				option_selected = 1;
				sprite.setPosition(263, 120);
			}
			else {
				option_selected = 0;
				sprite.setPosition(263, 220);
			}
			break;
		case "score":
			if(option_selected == 0){
				option_selected = 1;
				sprite.setPosition(463, 120);
			}
			else {
				option_selected = 0;
				sprite.setPosition(463, 220);
			}
			break;
		}
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

	@Override
	public void moveRight(float delta) {
		// TODO Auto-generated method stub
		
	}

	public int getOption() {
		return option_selected;
	}
	
}
