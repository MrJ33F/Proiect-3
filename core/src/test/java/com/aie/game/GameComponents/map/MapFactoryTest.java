package com.aie.game.GameComponents.map;

import com.aie.game.Assets.Map.Map;
import com.aie.game.Assets.Map.MapFactory;
import com.aie.game.Assets.Map.worldMap.ToppleDownRoad;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.aie.game.GdxRunner;
import com.aie.game.Assets.Map.worldMap.Topple;
import com.aie.game.Assets.Map.worldMap.ToppleRoad1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@Disabled
@ExtendWith(GdxRunner.class)
public class MapFactoryTest {

    @BeforeEach
    void init() {
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = mock(GL20.class);
    }

    @Test
    public void testGetMap_ShouldSucceedWithTopple() {
        Map map = MapFactory.getMap(MapFactory.MapType.TOPPLE);

        assertThat(map).isInstanceOf(Topple.class);
        assertThat(MapFactory.getMapTable()).contains(entry(MapFactory.MapType.TOPPLE, map));
    }

    @Test
    public void testGetMap_ShouldSucceedWithToppleRoad1() {
        Map map = MapFactory.getMap(MapFactory.MapType.TOPPLE_ROAD_1);

        assertThat(map).isInstanceOf(ToppleRoad1.class);
        assertThat(MapFactory.getMapTable()).contains(entry(MapFactory.MapType.TOPPLE_ROAD_1, map));
    }
    @Test
    public void testGetMap_ShouldSuccedWithToppleDownRoad(){
        Map map = MapFactory.getMap(MapFactory.MapType.TOPPLE_DOWN_ROAD);

        assertThat(map).isInstanceOf(ToppleDownRoad.class);
        assertThat(MapFactory.MapType.TOPPLE_DOWN_ROAD);
    }

    @Test
    public void testClearCache_ShouldSucceed() {
        MapFactory.getMap(MapFactory.MapType.TOPPLE);
        assertThat(MapFactory.getMapTable()).containsKey(MapFactory.MapType.TOPPLE);

        MapFactory.clearCache();

        assertThat(MapFactory.getMapTable()).isEmpty();
    }
}
