package com.aie.game.GameComponents.GameUI.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.aie.game.GdxRunner;
import com.aie.game.GameComponents.Managers.ResourceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(GdxRunner.class)
public class InventoryUITest {

    @BeforeEach
    void init() {
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = mock(GL20.class);
        new ResourceManager();
    }

    @Test
    public void testInventoryUI_ShouldSucceed() {
        InventoryUI inventoryUI = new InventoryUI();

        assertThat(inventoryUI).isNotNull();
        assertThat(inventoryUI.getDragAndDrop()).isNotNull();
    }
}
