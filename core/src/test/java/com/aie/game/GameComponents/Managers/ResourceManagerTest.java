package com.aie.game.GameComponents.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.aie.game.GdxRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(GdxRunner.class)
class ResourceManagerTest {

    @BeforeEach
    void init() {
        Gdx.gl = mock(GL20.class);
    }

    @Test
    void testResourceManager_ShouldSucceed() {
        ResourceManager resourceManager = new ResourceManager();

        assertThat(resourceManager).isNotNull()
                .hasFieldOrPropertyWithValue("isOptionScreen", false);
    }
}
