package com.aie.game.Assets.Map.worldMap;


import com.aie.game.Assets.Map.MapFactory;
import com.aie.game.GdxRunner;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@Disabled
@ExtendWith(GdxRunner.class)
public class ToppleDownRoad {

    @BeforeEach
    void init() {
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = mock(GL20.class);
    }

    @Test
    public void testToppleDownRoad_ShouldSucceed() {
        ToppleDownRoad toppleDownRoad = new ToppleDownRoad();



        assertThat(toppleDownRoad.getMusicTheme()).isNotNull();
        assertThat(toppleDownRoad.getCollisionLayer()).isNotNull();
        assertThat(toppleDownRoad.getEnemySpawnLayer()).isNotNull();
        assertThat(toppleDownRoad.getMapQuestEntities()).isEmpty();
        assertThat(toppleDownRoad.getQuestDiscoverLayer()).isNull();
        assertThat(toppleDownRoad.getQuestItemSpawnLayer()).isNull();
        assertThat(toppleDownRoad.getPortalLayer()).isNotNull();
        assertThat(toppleDownRoad.getPlayerStart()).isNotNull();
        assertThat(toppleDownRoad.getCurrentMapType()).isEqualTo(MapFactory.MapType.TOPPLE_DOWN_ROAD);
        assertThat(toppleDownRoad.getMapEntities().size).isEqualTo(1);
    }
}
