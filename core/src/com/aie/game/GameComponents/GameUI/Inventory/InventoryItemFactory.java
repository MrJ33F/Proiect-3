package com.aie.game.GameComponents.GameUI.Inventory;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Scaling;


import java.util.ArrayList;
import java.util.Hashtable;

import static com.aie.game.GameComponents.Managers.ResourceManager.ITEMS_TEXTURE_ATLAS;


public class InventoryItemFactory {

    private Json json = new Json();
    private static final String INVENTORY_ITEM = "scripts/inventory_items.json";
    private static InventoryItemFactory instance = null;
    private Hashtable<InventoryItem.ItemTypeID, com.aie.game.GameComponents.GameUI.Inventory.InventoryItem> inventoryItemList;

    public static InventoryItemFactory getInstance() {
        if(instance == null) {
            instance = new InventoryItemFactory();
        }

        return instance;
    }

    private InventoryItemFactory() {
        ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(INVENTORY_ITEM));
        inventoryItemList = new Hashtable<>();

        for(JsonValue jsonVal : list) {
            com.aie.game.GameComponents.GameUI.Inventory.InventoryItem inventoryItem = json.readValue(com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.class, jsonVal);
            inventoryItemList.put(inventoryItem.getItemTypeID(), inventoryItem);
        }
    }

    public com.aie.game.GameComponents.GameUI.Inventory.InventoryItem getInventoryItem(InventoryItem.ItemTypeID inventoryItemType) {
        com.aie.game.GameComponents.GameUI.Inventory.InventoryItem item = new com.aie.game.GameComponents.GameUI.Inventory.InventoryItem(inventoryItemList.get(inventoryItemType));
        item.setDrawable(new TextureRegionDrawable(ITEMS_TEXTURE_ATLAS.findRegion(item.getItemTypeID().toString())));
        item.setScaling(Scaling.none);
        return item;
    }

    /*
    public void testAllItemLoad() {
        for(ItemTypeID itemTypeID : ItemTypeID.values()) {
            InventoryItem item = new InventoryItem(inventoryItemList.get(itemTypeID));
            item.setDrawable(new TextureRegionDrawable(PlayerHUD.itemsTextureAtlas.findRegion(item.getItemTypeID().toString())));
            item.setScaling(Scaling.none);
        }
    }*/

}
