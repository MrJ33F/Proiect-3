package com.aie.game.GameComponents.GameUI.Inventory;

public interface InventoryObserver {
    enum InventoryEvent {
        UPDATED_AP,
        UPDATED_DP,
        ITEM_CONSUMED,
        ADD_WAND_AP,
        REMOVE_WAND_AP,
        NONE
    }

    void onNotify(final String value, InventoryEvent event);
}
