package com.runner.game.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.runner.game.tools.Animation;
import com.runner.game.tools.SoundManager;
import com.runner.game.tools.TextureManager;

public class Hero extends GameObject {
	Rectangle bottom, left, right, top, hitBox;
	int action;
	float velocityY;
	Texture walkSheet;
	int state;
	float speed;
	TextureRegion currentFrame;
	Animation runAnimation;
	int score;
	int respawnX;
	int respawnY;


public Hero() {
	
	int FRAME_ROWS = 1;
	int FRAME_COLS = 6;
	
	hitBox = new Rectangle(0.0f, 0.0f, 41.0f, 71.0f);
	bottom = new Rectangle(0.0f, 0.0f, 41.0f, 9.0f);
	left = new Rectangle(0.0f, 9.0f, 21.0f, 62.0f);
	right = new Rectangle(21.0f, 9.0f, 21.0f, 62.0f);
	top = new Rectangle(0.0f, 62.0f, 41.0f, 9.0f);
	
	this.setPosition(0, 0);
	velocityY = 0;
	
	respawnX = 70;
	respawnY = 70;
	
	state = 0;
	speed = 200;
	score = 0;
	
	walkSheet = TextureManager.hero;
	TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
	TextureRegion[] walkFrames = new TextureRegion[6];
	
	int index = 0;
    for (int i = 0; i < FRAME_ROWS; i++) {
        for (int j = 0; j < FRAME_COLS; j++) {
            walkFrames[index++] = tmp[i][j];
        }
    }
	
    runAnimation = new Animation(walkFrames, 1/12f);
	
}

public int hits(Rectangle r) {
	
	if(left.overlaps(r)) {
		return 2;
	}
	
	if (right.overlaps(r)) {
		return 3;
	}
	
	if(top.overlaps(r)){
		return 4;
	}
	
	if(bottom.overlaps(r)) {
		return 1;
	}
	return -1;
}

public void action(int type, float x, float y) {
	if (type == 1 || type == 4) {
		speed = 200;
		velocityY = 0;
		setPosition(bottom.x, y);
		if(type == 1) state = 0;
	}
	
	if (type == 2 || type == 3) {
		velocityY = 0;
		setPosition(x, bottom.y);	
	}
}

public void update(float delta) {
	
	velocityY -= 20 * delta;
	bottom.y += velocityY;
	top.y +=velocityY;
	runAnimation.update(delta);
	
}

public void setPosition(float x, float y) {
	
	hitBox.x = x;
	hitBox.y = y;
	
	bottom.x = x;
	bottom.y = y;
	
	left.x = x;
	left.y = y + 9;
	
	right.x = x + 21;
	right.y = y + 9;
	
	top.x = x;
	top.y = y + 36;
	
	//sprite.setPosition(x, y);
}

public void moveLeft(float delta) {
	
	bottom.x -= (speed * delta);
	//sprite.setPosition(bottom.x, bottom.y);
	
}

public void moveRight(float delta) {
	
	bottom.x += (speed * delta);
	//sprite.setPosition(bottom.x,  bottom.y);
	
}

public void moveUp(float delta) {
	
	bottom.y += (speed * delta);
	//sprite.setPosition(bottom.x, bottom.y);
		
}

public void moveDown(float delta) {
	
	bottom.y -= (speed * delta);
	//sprite.setPosition(bottom.x, bottom.y);
		
}

public void jump() {
	
	Random r = new Random();
	int result = r.nextInt(2-1 + 1) + 1;
	
	velocityY = 10;
	state = 1;
	
	if(result == 1) SoundManager.jump1.play();
	else if(result == 2) SoundManager.jump2.play();
	
}

public void propel() {
	
	speed = 400;
	velocityY = 15;
	state = 1;
	
}

public void draw(SpriteBatch batch) {
	
	currentFrame = runAnimation.getFrame();
	batch.draw(runAnimation.getFrame(), bottom.x, bottom.y);
}

@Override
public Rectangle getHitBox(){
	
	return hitBox;
}

@Override
public int hitAction(int side) {
	// TODO Auto-generated method stub
	return 0;
}

public int getState() {
	
	return state;	
}

public float getX() {
	
	return bottom.x;	
}

public void score() {
	score += 1000;
}	

public int getScore() {
	return score;
}

public void resetScore() {
	respawnX = 70;
	respawnY = 70;
	score = 0;
}

public void respawn() {
	
	Random r = new Random();
	int result = r.nextInt(2-1 + 1) + 1;
	
	setPosition(respawnX, respawnY);
	if(result == 1) SoundManager.death1.play();
	else if(result == 2) SoundManager.death2.play();
	
}

public void checkpoint(int point) {
	
	switch(point){
	
	case 1:
		respawnX = 6160;
		respawnY = 70;
		break;
	}
	
}


}
