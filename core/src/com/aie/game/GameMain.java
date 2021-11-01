package com.aie.game;

import com.aie.game.GameComponents.Managers.PreferenceManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class GameMain extends ApplicationAdapter {
	private SptriteBatch batch;
	private ResourceManager resourceManager;
	private PreferenceManager preferenceManager = new PreferenceManager();
	private MenuScreen menuScreen;
	private CharacterSelectionScreen characterSelectionScreen;
	private GameScreen gameScreen;

	public SpriteBatch getBatch() {return batch;}
	public MenuScreen getMenuScreen() {return menuScreen;}
	public CharacterSelectionScreen getGameScreen() {return characterSelectionScreen;}
	public GameScreen getGameScreen() {return gameScreen;}
	public void setGameScreen(GameScreen gameScreen) {this.gameScreen = gameScreen;}

	public PreferenceManager getPreferenceManager() {return preferenceManager;}


	@Override
	public void create () {
		batch = new SpriteBatch();
		resourceManager = new ResourceManager();
		menuScreen = new MenuScreen(this, resourceManager);
		characterSelectionScreen = new CharacterSelectionScreen(this, resourceManager);

		this.setScreen(menuScreen);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		menuScreen.dispose();
		characterSelectionScreen.dispose();
		gameScreen.dispose();
		resourceManager.dispose();
	}
}
