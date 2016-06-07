package com.runner.game;

import java.util.ArrayList;
import java.util.StringTokenizer;

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

public class RunnerPlatformer extends ApplicationAdapter {
	
	private OrthographicCamera camera;
	
	private SpriteBatch batch;
	
	private Hero player1;
	private Select option1;
	private Select option2;
	
	private ArrayList<String> levellist = new ArrayList<String>();
	private ArrayList<GameObject> objectslist = new ArrayList<GameObject>();
	private ArrayList<GameObject> deletelist = new ArrayList<GameObject>();
	private ArrayList<GameObject> backgroundlist = new ArrayList<GameObject>();
	
	private float stateTime;
	private int GameState;
	private BitmapFont font;
	private float fontPos;
	private long startTime;
	private long elapsedtime;
	
	
	@Override
	public void create () {
		
		TextureManager.create();
		SoundManager.create();
		SoundManager.background.setLooping(true);
		SoundManager.background.setVolume(0.1f);
		SoundManager.background.play();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		stateTime = 0f;
		GameState = 1;
		
		batch = new SpriteBatch();
		player1 = new Hero();
		
		option1 = new Select(263,220,"menu");
		option2 = new Select(463,220,"score");
		
		addLevel("level1");
		
		font = new BitmapFont(Gdx.files.internal("font.fnt"),
				Gdx.files.internal("font.png"), false);
		fontPos = 0;
		startTime = System.currentTimeMillis();
	}
	
	
	public ArrayList<String> getLevellist() {
		return levellist;
	}


	public ArrayList<GameObject> getObjectslist() {
		return objectslist;
	}


	public ArrayList<GameObject> getBackgroundlist() {
		return backgroundlist;
	}


	@Override
	public void render () {
		
		switch(GameState) {
		case 1:
			player1.setPosition(70,70);
			player1.resetScore();
			loadLevel(levellist.get(0));
			elapsedtime = System.currentTimeMillis() - startTime;
			mainMenu();
			break;
		case 2:
			mainGame();
			break;
		case 3:
			scoreScreen();
			break;
		case 4:
			quit();
			break;
		}
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		TextureManager.dispose();
		SoundManager.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	public void updateCamera() {
		
		camera.position.x = player1.getX() +390;
		camera.update();
	}
	
	public void addLevel(String lvl) {
		levellist.add(lvl);
	}
	
	public void loadLevel(String lvl) {
		
		objectslist.clear();
		
		FileHandle file = Gdx.files.internal(lvl);
		StringTokenizer tokens = new StringTokenizer(file.readString());
		
		int point = 1;
		
		while (tokens.hasMoreTokens()) {
			String type = tokens.nextToken();
			switch(type) {
				case "Brick":
					objectslist.add(new Brick(
							Integer.parseInt(tokens.nextToken()),
							Integer.parseInt(tokens.nextToken())));
					break;
				case "Water":
					objectslist.add(new Water(
							Integer.parseInt(tokens.nextToken()),
							Integer.parseInt(tokens.nextToken())));
					break;
				case "Coin":
					objectslist.add(new Coin(
							Integer.parseInt(tokens.nextToken()),
							Integer.parseInt(tokens.nextToken())));
					break;
				case "Trampoline":
					objectslist.add(new Trampoline(
							Integer.parseInt(tokens.nextToken()),
							Integer.parseInt(tokens.nextToken())));
					break;
				case "Poker":
					objectslist.add(new Poker(
							Integer.parseInt(tokens.nextToken()),
							Integer.parseInt(tokens.nextToken())));
					break;
				case "Background":
					backgroundlist.add(new Background(
							Integer.parseInt(tokens.nextToken()),
							Integer.parseInt(tokens.nextToken())));
					break;
				case "End":
					objectslist.add(new Endflag(
							Integer.parseInt(tokens.nextToken()),
							Integer.parseInt(tokens.nextToken())));
					break;
				case "Checkpoint":
					objectslist.add(new Checkpoint(
							Integer.parseInt(tokens.nextToken()),
							Integer.parseInt(tokens.nextToken()),
							point));
					point++;
					break;
			}
		}
		
	}
	
	public void mainGame() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();       
        
		for(GameObject t : backgroundlist) {
			t.draw(batch);
		}
		
		player1.draw(batch);
		
		for(GameObject t : objectslist) {
			t.draw(batch);
		}
		
		font.draw(batch, "Score: " + player1.getScore(), fontPos , 430);
		font.draw(batch, "Time: " + ((System.currentTimeMillis() - startTime - elapsedtime) / 1000) , fontPos+550, 430);
		batch.end();
		
		//Updates
		player1.update(Gdx.graphics.getDeltaTime());
		
		for(GameObject t : objectslist) {
			switch (player1.hits(t.getHitBox())) {
			case 1:
				switch(t.hitAction(1)) {
				case 1:
					player1.action(1, 0, t.getHitBox().y + t.getHitBox().height);
					break;
				case 2:
					player1.respawn();
					break;
				case 3:
					deletelist.add(t);
					player1.score();
					break;
				case 4:
					player1.propel();
					break;
				case 5:
					GameState = 3;
					break;
				case 6:
					break;
				}
				break;
			case 2:
				switch(t.hitAction(2)) {
				case 1:
					player1.action(2, t.getHitBox().x + t.getHitBox().width+1, 0);
					break;
				case 2:
					player1.respawn();
					break;
				case 3:
					deletelist.add(t);
					player1.score();
					break;
				case 5:
					GameState = 3;
					break;
				case 6:
					break;
				}
				break;
			case 3:
				switch(t.hitAction(3)) {
				case 1:
					player1.action(3, t.getHitBox().x - player1.getHitBox().width - 1, 0);
					break;
				case 2:
					player1.respawn();
					break;
				case 3:
					deletelist.add(t);
					player1.score();
					break;
				case 5:
					GameState = 3;
					break;
				case 6:
					player1.checkpoint(((Checkpoint) t).getPoint());
					break;
				}
				break;
			case 4:
				switch(t.hitAction(4)) {
				case 1:
					player1.action(4, 0, t.getHitBox().y - player1.getHitBox().height);
					break;
				case 2:
					player1.respawn();
					break;
				case 3:
					deletelist.add(t);
					player1.score();
					break;
				case 5:
					GameState = 3;
					break;
				case 6:
					break;
				}
				break;
			}
			
		}
		
		while (!deletelist.isEmpty()) {
			objectslist.remove(deletelist.get(0));
			deletelist.remove(0);	
		}
		
		fontPos = player1.getX();
		updateCamera();
		
		player1.moveRight(Gdx.graphics.getDeltaTime());
		for(GameObject t : backgroundlist) {
			t.moveLeft(Gdx.graphics.getDeltaTime());;
		}
		
		//Controls
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player1.moveLeft(Gdx.graphics.getDeltaTime());	
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player1.moveRight(Gdx.graphics.getDeltaTime());	
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player1.moveUp(Gdx.graphics.getDeltaTime());	
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player1.moveDown(Gdx.graphics.getDeltaTime());	
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			if(player1.getState() == 0){
				player1.jump();
			}
		}
	}
	
	public void mainMenu() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Texture img;
		Boolean state = false;
		
		img = new Texture(Gdx.files.internal("title.png"));
		Sprite title = new Sprite(img, 0, 0, 547, 105);
		title.setPosition(126, 300);
		
		img = new Texture(Gdx.files.internal("start.png"));
		Sprite start = new Sprite(img, 0, 0, 173, 77);
		start.setPosition(313, 200);
		
		img = new Texture(Gdx.files.internal("quit.png"));
		Sprite quit = new Sprite(img, 0, 0, 143, 72);
		quit.setPosition(328, 100);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();       
        
		for(GameObject t : backgroundlist) {
			t.draw(batch);
		}
		
		title.draw(batch);
		start.draw(batch);
		quit.draw(batch);
		
		option1.draw(batch);
		
		
		batch.end();
		
		for(GameObject t : backgroundlist) {
			t.moveLeft(Gdx.graphics.getDeltaTime());;
		}
		
		camera.position.x = 400;
		camera.position.y = 240;
		camera.update();

		
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			option1.move();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			option1.move();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			if(option1.getOption() == 0){
				GameState = 2;
			}
			else {
				GameState = 4;
			}
		}
		
	}
	
	public void scoreScreen() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Texture img;
		
		img = new Texture(Gdx.files.internal("title.png"));
		Sprite title = new Sprite(img, 0, 0, 547, 105);
		title.setPosition(126, 300);
		
		img = new Texture(Gdx.files.internal("replay.png"));
		Sprite replay = new Sprite(img, 0, 0, 210, 72);
		replay.setPosition(513, 200);
		
		img = new Texture(Gdx.files.internal("quit.png"));
		Sprite quit = new Sprite(img, 0, 0, 143, 72);
		quit.setPosition(528, 100);
	
		batch.setProjectionMatrix(camera.combined);
		camera.position.x = 400;
		camera.position.y = 240;
		camera.update();
		
		batch.begin();       
		
			for(GameObject t : backgroundlist) {
				t.draw(batch);
			}
			
			title.draw(batch);
			replay.draw(batch);
			quit.draw(batch);
			
			option2.draw(batch);
			
			font.draw(batch, "YOUR SCORE: " + player1.getScore(), 100 , 100);
			
		batch.end();
		
		for(GameObject t : backgroundlist) {
			t.moveLeft(Gdx.graphics.getDeltaTime());;
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			option2.move();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			option2.move();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			if(option2.getOption() == 0){
				GameState = 1;
			}
			else {
				GameState = 4;
			}
		}
		
	}
	
	public void quit(){
		
		Gdx.app.exit();
	}

}
