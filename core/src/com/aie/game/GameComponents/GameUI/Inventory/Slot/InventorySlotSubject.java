package com.aie.game.GameComponents.GameUI.Inventory.Slot;

public interface InventorySlotSubject {

    void addObserver(com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlotObserver inventorySlotObserver);
    void removeObserver(com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlotObserver inventorySlotObserver);
    void removeAllObservers();
    void notify(final com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlot slot, com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlotObserver.SlotEvent event);
}
