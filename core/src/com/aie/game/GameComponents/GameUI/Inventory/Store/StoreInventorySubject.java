package com.aie.game.GameComponents.GameUI.Inventory.Store;


public interface StoreInventorySubject {
    void addObserver(com.aie.game.GameComponents.GameUI.Inventory.Store.StoreInventoryObserver storeObserver);
    void removeObserver(com.aie.game.GameComponents.GameUI.Inventory.Store.StoreInventoryObserver storeObserver);
    void removeAllObservers();
    void notify(String value, com.aie.game.GameComponents.GameUI.Inventory.Store.StoreInventoryObserver.StoreInventoryEvent event);
}
