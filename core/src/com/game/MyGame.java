package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import GameGDX.Loader;
import GameGDX.Scene;
import Screen.GamePlayScreen;
import Screen.MenuScreen;
import Screen.ScrollParScreen;
import zen.IZen;

public class MyGame extends ApplicationAdapter {
	static IZen sdk;
	public MyGame(IZen izen) {
		sdk = izen;
	}

	@Override
	public void create() {
		new Loader();
		new Scene();
		new GamePlayScreen();
		new ScrollParScreen();
		new MenuScreen().Show();
	}

	@Override
	public void render() {
		Scene.Act();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Scene.Render();
	}
}
