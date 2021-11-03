package com.aie.game.GameComponents.GameScreen;

import com.aie.game.GameComponents.Component.Component;
import com.aie.game.GameComponents.Managers.ResourceManager;
import com.aie.game.GameComponents.GameProfile.ProfileManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.aie.game.GdxGame;
import com.aie.game.Assets.Audio.AudioObserver;
import com.aie.game.Assets.Map.MapManager;

import java.util.ArrayList;

import static com.aie.game.Assets.Audio.AudioObserver.AudioTypeEvent.GAME_OVER_THEME;

public class GameOverScreen extends BaseScreen {

    private Table gameOverTable;
    private Stage gameOverStage = new Stage();

    public GameOverScreen(GdxGame gdxGame, MapManager mapManager, ResourceManager resourceManager) {
        super(gdxGame, resourceManager);
        super.musicTheme = GAME_OVER_THEME;

        gameOverTable = createTable();
        gameOverTable.setFillParent(true);

        Label gameOverLabel = new Label("Sfarsitul jocului", ResourceManager.skin);
        gameOverLabel.setAlignment(Align.center);
        TextButton continueButton = new TextButton("Continua", ResourceManager.skin);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent input, float x, float y) {
                mapManager.getPlayer().setEntityEncounteredType(null);
                mapManager.getPlayer().sendMessage(Component.MESSAGE.RESET_POSITION);
                int hpMax = ProfileManager.getInstance().getProperty("HPMaxJucator", Integer.class);

                ProfileManager.getInstance().setProperty("HPcurentJucator", (int) Math.round(hpMax * 0.1));
                ProfileManager.getInstance().saveProfile();
                setScreenWithTransition((BaseScreen) gdxGame.getScreen(), gdxGame.getMenuScreen(), new ArrayList<>());
            }
        });

        gameOverTable.add(gameOverLabel);
        gameOverTable.row();
        gameOverTable.add(continueButton).padTop(5);
        gameOverTable.pack();
    }

    @Override
    public void show() {
        gameOverStage.addActor(gameOverTable);
        Gdx.input.setInputProcessor(gameOverStage);

        notify(AudioObserver.AudioCommand.MUSIC_LOAD, GAME_OVER_THEME);
        notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, GAME_OVER_THEME);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 0, 0, 0 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        gdxGame.getBatch().begin();
        //gdxGame.getBatch().draw();
        gdxGame.getBatch().end();

        gameOverStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        gameOverTable.remove();
    }
}
