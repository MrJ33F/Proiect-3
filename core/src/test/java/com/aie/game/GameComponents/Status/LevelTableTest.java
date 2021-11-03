package com.aie.game.GameComponents.Status;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import com.aie.game.GdxRunner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(GdxRunner.class)
public class LevelTableTest {

    @BeforeEach
    void init() {
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = mock(GL20.class);
    }

    @Test
    public void testGetLevelTables_ShouldSucceed() {
        Array<LevelTable> levelTables = LevelTable.getLevelTables("scripts/level_tables.json");

        Assertions.assertThat(levelTables).isNotNull();
        Assertions.assertThat(levelTables).hasSize(10);
    }
}
