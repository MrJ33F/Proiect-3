package com.aie.game.GameComponents.GameUI.Inventory.slot;

public interface InventorySlotObserver {
    enum SlotEvent {
        ADDED_ITEM,
        REMOVED_ITEM
    }

    void onNotify(final InventorySlot slot, SlotEvent event);
}
