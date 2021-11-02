package com.aie.game.GameComponents.GameUI.Inventory.Slot;

public interface InventorySlotObserver {
    enum SlotEvent {
        ADDED_ITEM,
        REMOVED_ITEM
    }

    void onNotify(final com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlot slot, SlotEvent event);
}
