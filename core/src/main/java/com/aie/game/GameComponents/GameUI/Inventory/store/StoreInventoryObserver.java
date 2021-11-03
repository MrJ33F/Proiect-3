package com.aie.game.GameComponents.GameUI.Inventory.store;

public interface StoreInventoryObserver {
    enum StoreInventoryEvent {
        PLAYER_GP_TOTAL_UPDATED,
        PLAYER_INVENTORY_UPDATED
    }

    void onNotify(String value, StoreInventoryEvent event);
}
