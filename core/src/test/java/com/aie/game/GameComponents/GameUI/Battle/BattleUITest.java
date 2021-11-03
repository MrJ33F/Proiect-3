package com.aie.game.GameComponents.GameUI.Battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.aie.game.GdxRunner;
import com.aie.game.GameComponents.Managers.ResourceManager;
import com.aie.game.GameComponents.GameProfile.ProfileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(GdxRunner.class)
public class BattleUITest {

    @BeforeEach
    void init() {
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = mock(GL20.class);
        new ResourceManager();
    }

    @Test
    public void testBattleUI_ShouldSucceed() {
        ProfileManager profileManager = ProfileManager.getInstance();
        profileManager.setProperty("currentPlayerAP", 5);
        profileManager.setProperty("currentPlayerDP", 5);

        BattleState battleState = new BattleState();
        BattleUI battleUI = new BattleUI(battleState);

        assertThat(battleUI).isNotNull();
        assertThat(battleUI.getChildren()).hasSize(2);
    }
}
