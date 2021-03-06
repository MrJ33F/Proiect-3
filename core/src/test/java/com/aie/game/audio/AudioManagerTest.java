package com.aie.game.audio;

import com.aie.game.Assets.Audio.AudioManager;
import com.aie.game.Assets.Audio.AudioObserver;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.aie.game.GdxRunner;
import com.aie.game.GameComponents.Managers.ResourceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.aie.game.Assets.Audio.AudioObserver.AudioTypeEvent.MENU_THEME;
import static com.aie.game.Assets.Audio.AudioObserver.AudioTypeEvent.TOPPLE_THEME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(GdxRunner.class)
class AudioManagerTest {

    @BeforeEach
    void init() {
        Gdx.gl = mock(GL20.class);
    }

    @Test
    void testGetInstance_ShouldSucceed() {
        AudioManager audioManager = AudioManager.getInstance();

        assertThat(audioManager).isNotNull();
    }

    @Test
    void testOnNotify_ShouldSucceedWithMusicPlayOnce() {
        new ResourceManager();
        AudioManager audioManager = AudioManager.getInstance();
        audioManager.setCurrentMusic(null);

        audioManager.onNotify(AudioObserver.AudioCommand.MUSIC_LOAD, MENU_THEME);
        audioManager.onNotify(AudioObserver.AudioCommand.MUSIC_PLAY_ONCE, MENU_THEME);

        assertThat(audioManager.getCurrentMusic()).isEqualTo(ResourceManager.getMusicAsset(MENU_THEME.getValue()));
    }

    @Test
    void testOnNotify_ShouldSucceedWithMusicPlayLoop() {
        new ResourceManager();
        AudioManager audioManager = AudioManager.getInstance();
        audioManager.setCurrentMusic(null);

        audioManager.onNotify(AudioObserver.AudioCommand.MUSIC_LOAD, TOPPLE_THEME);
        audioManager.onNotify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, TOPPLE_THEME);

        assertThat(audioManager.getCurrentMusic()).isEqualTo(ResourceManager.getMusicAsset(TOPPLE_THEME.getValue()));
    }

    @Test
    void testOnNotify_ShouldSucceedWithMusicStop() {
        new ResourceManager();
        AudioManager audioManager = AudioManager.getInstance();
        audioManager.setCurrentMusic(null);

        audioManager.onNotify(AudioObserver.AudioCommand.MUSIC_LOAD, MENU_THEME);
        audioManager.onNotify(AudioObserver.AudioCommand.MUSIC_PLAY_ONCE, MENU_THEME);
        audioManager.onNotify(AudioObserver.AudioCommand.MUSIC_STOP, MENU_THEME);

        assertThat(audioManager.getCurrentMusic().isPlaying()).isFalse();
    }
}
