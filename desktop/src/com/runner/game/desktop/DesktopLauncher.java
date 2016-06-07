package com.runner.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.runner.game.RunnerPlatformer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new RunnerPlatformer(), config);
		config.title = "Runner!!!";
		config.width = 800;
		config.height = 480;
				 
	}
}
