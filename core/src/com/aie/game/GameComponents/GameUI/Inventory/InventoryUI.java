package com.aie.game.GameComponents.GameUI.Inventory;

import com.aie.game.GameComponents.Component.Type.ProfileManager;
import com.aie.game.GameComponents.Entities.Entity;
import com.aie.game.GameComponents.GameUI.Inventory.Slot.*;
import com.aie.game.GameComponents.Managers.ResourceManager;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import static com.aie.game.GameComponents.Component.Interfaces.Component.MESSAGE_TOKEN;
import static com.aie.game.GameComponents.Managers.ResourceManager.ITEMS_TEXTURE_ATLAS;
import static com.aie.game.GameComponents.Managers.ResourceManager.STATUS_UI_TEXTURE_ATLAS;


public class InventoryUI extends Window implements com.aie.game.GameComponents.GameUI.Inventory.InventorySubject, InventorySlotObserver {

    public final static int NUM_SLOTS = 50;
    public static final String PLAYER_INVENTORY = "Player_Inventory";
    public static final String STORE_INVENTORY = "Store_Inventory";

    private int lengthSlotRow = 10;
    private Table inventorySlotTable;
    private Table playerSlotsTable;
    private Table equipSlots;
    private DragAndDrop dragAndDrop;
    private Array<Actor> inventoryActors;

    private Label DPValLabel;
    private int DPVal = 0;
    private Label APValLabel;
    private int APVal = 0;

    private final int slotWidth = 52;
    private final int slotHeight = 52;

    private Array<InventoryObserver> observers;

    private InventorySlotTooltip inventorySlotTooltip;

    public InventoryUI() {
        super("Inventory", ResourceManager.skin);

        observers = new Array<>();

        dragAndDrop = new DragAndDrop();
        inventoryActors = new Array<>();

        //create
        inventorySlotTable = new Table();
        inventorySlotTable.setName("Inventory_Slot_Table");

        playerSlotsTable = new Table();
        equipSlots = new Table();
        equipSlots.setName("Equipment_Slot_Table");

        equipSlots.defaults().space(10);
        inventorySlotTooltip = new InventorySlotTooltip(ResourceManager.skin);

        Label DPLabel = new Label("Defense: ", ResourceManager.skin);
        DPValLabel = new Label(String.valueOf(DPVal), ResourceManager.skin);

        Label APLabel = new Label("Attack : ", ResourceManager.skin);
        APValLabel = new Label(String.valueOf(APVal), ResourceManager.skin);

        Table labelTable = new Table();
        labelTable.add(DPLabel).align(Align.left);
        labelTable.add(DPValLabel).align(Align.center);
        labelTable.row();
        labelTable.row();
        labelTable.add(APLabel).align(Align.left);
        labelTable.add(APValLabel).align(Align.center);

        InventorySlot headSlot = new InventorySlot(com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.ARMOR_HELMET.getValue(),
                new Image(ITEMS_TEXTURE_ATLAS.findRegion("inv_helmet")));

        InventorySlot leftArmSlot = new InventorySlot(com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.WEAPON_ONEHAND.getValue() |
                com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.WEAPON_TWOHAND.getValue() | com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.ARMOR_SHIELD.getValue() |
                com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.WAND_ONEHAND.getValue() | com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.WAND_TWOHAND.getValue(),
                new Image(ITEMS_TEXTURE_ATLAS.findRegion("inv_weapon"))
        );

        InventorySlot rightArmSlot = new InventorySlot(com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.WEAPON_ONEHAND.getValue() |
                com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.WEAPON_TWOHAND.getValue() | com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.ARMOR_SHIELD.getValue() |
                com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.WAND_ONEHAND.getValue() | com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.WAND_TWOHAND.getValue(),
                new Image(ITEMS_TEXTURE_ATLAS.findRegion("inv_shield"))
        );

        InventorySlot chestSlot = new InventorySlot(com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.ARMOR_CHEST.getValue(),
                new Image(ITEMS_TEXTURE_ATLAS.findRegion("inv_chest")));

        InventorySlot legsSlot = new InventorySlot(com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemUseType.ARMOR_FEET.getValue(),
                new Image(ITEMS_TEXTURE_ATLAS.findRegion("inv_boot")));

        headSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        leftArmSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        rightArmSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        chestSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        legsSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));

        headSlot.addObserver(this);
        leftArmSlot.addObserver(this);
        rightArmSlot.addObserver(this);
        chestSlot.addObserver(this);
        legsSlot.addObserver(this);

        dragAndDrop.addTarget(new InventorySlotTarget(headSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(leftArmSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(chestSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(rightArmSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(legsSlot));

        playerSlotsTable.setBackground(new Image(new NinePatch(STATUS_UI_TEXTURE_ATLAS.createPatch("dialog"))).getDrawable());

        //layout
        handleLayoutInventorySlot();

        equipSlots.add();
        equipSlots.add(headSlot).size(slotWidth, slotHeight);
        equipSlots.row();

        equipSlots.add(leftArmSlot).size(slotWidth, slotHeight);
        equipSlots.add(chestSlot).size(slotWidth, slotHeight);
        equipSlots.add(rightArmSlot).size(slotWidth, slotHeight);
        equipSlots.row();

        equipSlots.add();
        equipSlots.right().add(legsSlot).size(slotWidth, slotHeight);

        playerSlotsTable.add(equipSlots);
        inventoryActors.add(inventorySlotTooltip);

        this.add(playerSlotsTable).padBottom(20);
        this.add(labelTable);
        this.row();
        this.add(inventorySlotTable).colspan(2);
        this.row();
        this.pack();
    }

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    public Table getInventorySlotTable() {
        return inventorySlotTable;
    }

    public Table getEquipSlotTable() {
        return equipSlots;
    }

    private void handleLayoutInventorySlot() {
        for(int i = 1; i <= NUM_SLOTS; i++) {
            InventorySlot inventorySlot = new InventorySlot();
            inventorySlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
            dragAndDrop.addTarget(new InventorySlotTarget(inventorySlot));

            inventorySlotTable.add(inventorySlot).size(slotWidth, slotHeight);

            inventorySlot.addListener(new ClickListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                    if(getTapCount() == 2) {
                        InventorySlot slot = (InventorySlot)event.getListenerActor();
                        if(slot.hasItem()) {
                            com.aie.game.GameComponents.GameUI.Inventory.InventoryItem item = slot.getTopInventoryItem();
                            if(item.isConsumable()) {
                                String itemInfo = item.getItemUseType() + MESSAGE_TOKEN + item.getItemUseTypeValue();
                                InventoryUI.this.notify(itemInfo, InventoryObserver.InventoryEvent.ITEM_CONSUMED);
                                slot.removeActor(item);
                                slot.remove(item);
                            }
                        }
                    }
                }
            });

            if(i % lengthSlotRow == 0) {
                inventorySlotTable.row();
            }
        }
    }

    public void resetEquipSlots() {
        DPVal = 0;
        APVal = 0;

        DPValLabel.setText(String.valueOf(DPVal));
        notify(String.valueOf(DPVal), InventoryObserver.InventoryEvent.UPDATED_DP);

        APValLabel.setText(String.valueOf(APVal));
        notify(String.valueOf(APVal), InventoryObserver.InventoryEvent.UPDATED_AP);
    }

    public static void clearInventoryItems(Table targetTable) {
        Array<Cell> cells = targetTable.getCells();
        for(int i = 0; i < cells.size; i++) {
            InventorySlot inventorySlot = (InventorySlot)cells.get(i).getActor();
            if(inventorySlot == null) {
                continue;
            }
            inventorySlot.clearAllInventoryItems(false);
        }
    }

    public static Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> removeInventoryItems(String name, Table inventoryTable) {
        Array<Cell> cells = inventoryTable.getCells();
        Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> items = new Array<>();
        for(int i = 0; i < cells.size; i++) {
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if(inventorySlot == null) {
                continue;
            }
            inventorySlot.removeAllInventoryItemsWithName(name);
        }
        return items;
    }

    public static void populateInventory(Table targetTable, Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> inventoryItems, DragAndDrop draganddrop, String defaultName, boolean disableNonDefaultItems) {
        clearInventoryItems(targetTable);

        Array<Cell> cells = targetTable.getCells();
        for(int i = 0; i < inventoryItems.size; i++) {
            com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation itemLocation = inventoryItems.get(i);
            com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemTypeID itemTypeID = com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemTypeID.valueOf(itemLocation.getItemTypeAtLocation());
            InventorySlot inventorySlot = ((InventorySlot)cells.get(itemLocation.getLocationIndex()).getActor());

            for(int index = 0; index < itemLocation.getNumberItemsAtLocation(); index++) {
                com.aie.game.GameComponents.GameUI.Inventory.InventoryItem item = InventoryItemFactory.getInstance().getInventoryItem(itemTypeID);
                String itemName =  itemLocation.getItemNameProperty();
                if(itemName == null || itemName.isEmpty()) {
                    item.setName(defaultName);
                } else {
                    item.setName(itemName);
                }

                inventorySlot.add(item);
                if(item.getName().equalsIgnoreCase(defaultName)) {
                    draganddrop.addSource(new InventorySlotSource(inventorySlot, draganddrop));
                } else if(!disableNonDefaultItems) {
                    draganddrop.addSource(new InventorySlotSource(inventorySlot, draganddrop));
                }
            }
        }
    }

    public static Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> getInventory(Table targetTable) {
        Array<Cell> cells = targetTable.getCells();
        Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> items = new Array<>();
        for(int i = 0; i < cells.size; i++) {
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if(inventorySlot == null) {
                continue;
            }
            int numItems = inventorySlot.getNumItems();
            if(numItems > 0) {
                items.add(new com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation(i, inventorySlot.getTopInventoryItem().getItemTypeID().toString(),
                        numItems, inventorySlot.getTopInventoryItem().getName()));
            }
        }
        return items;
    }

    public static Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> getInventoryFiltered(Table targetTable, String filterOutName) {
        Array<Cell> cells = targetTable.getCells();
        Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> items = new Array<>();
        for(int i = 0; i < cells.size; i++) {
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if(inventorySlot == null) {
                continue;
            }
            int numItems = inventorySlot.getNumItems();
            if(numItems > 0) {
                String topItemName = inventorySlot.getTopInventoryItem().getName();
                if(topItemName.equalsIgnoreCase(filterOutName)) {
                    continue;
                }
                //System.out.println("[i] " + i + " itemtype: " + inventorySlot.getTopInventoryItem().getItemTypeID().toString() + " numItems " + numItems);
                items.add(new com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation(i, inventorySlot.getTopInventoryItem().getItemTypeID().toString(),
                        numItems, inventorySlot.getTopInventoryItem().getName()));
            }
        }
        return items;
    }

    public static Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> getInventory(Table targetTable, String name) {
        Array<Cell> cells = targetTable.getCells();
        Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> items = new Array<>();
        for(int i = 0; i < cells.size; i++) {
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if(inventorySlot == null) {
                continue;
            }
            int numItems = inventorySlot.getNumItems(name);
            if(numItems > 0) {
                //System.out.println("[i] " + i + " itemtype: " + inventorySlot.getTopInventoryItem().getItemTypeID().toString() + " numItems " + numItems);
                items.add(new com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation(i, inventorySlot.getTopInventoryItem().getItemTypeID().toString(),
                        numItems, name));
            }
        }
        return items;
    }

    public static Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> getInventoryFiltered(Table sourceTable, Table targetTable, String filterOutName) {
        Array<com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation> items = getInventoryFiltered(targetTable, filterOutName);
        Array<Cell> sourceCells = sourceTable.getCells();
        int index = 0;
        for(com.aie.game.GameComponents.GameUI.Inventory.InventoryItemLocation item : items) {
            for(; index < sourceCells.size; index++) {
                InventorySlot inventorySlot = ((InventorySlot) sourceCells.get(index).getActor());
                if(inventorySlot == null) {
                    continue;
                }
                int numItems = inventorySlot.getNumItems();
                if(numItems == 0) {
                    item.setLocationIndex(index);
                    //System.out.println("[index] " + index + " itemtype: " + item.getItemTypeAtLocation() + " numItems " + numItems);
                    index++;
                    break;
                }
            }
            if(index == sourceCells.size) {
                //System.out.println("[index] " + index + " itemtype: " + item.getItemTypeAtLocation() + " numItems " + item.getNumberItemsAtLocation());
                item.setLocationIndex(index-1);
            }
        }
        return items;
    }


    public static void setInventoryItemNames(Table targetTable, String name) {
        Array<Cell> cells = targetTable.getCells();
        for(int i = 0; i < cells.size; i++) {
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if(inventorySlot == null) {
                continue;
            }
            inventorySlot.updateAllInventoryItemNames(name);
        }
    }

    public boolean doesInventoryHaveSpace() {
        Array<Cell> sourceCells = inventorySlotTable.getCells();
        int index = 0;

        for(; index < sourceCells.size; index++) {
            InventorySlot inventorySlot = ((InventorySlot) sourceCells.get(index).getActor());
            if(inventorySlot == null) {
                continue;
            }
            int numItems = inventorySlot.getNumItems();
            if(numItems == 0) {
                return true;
            } else {
                index++;
            }
        }
        return false;
    }

    public void addEntityToInventory(Entity entity, String itemName) {
        Array<Cell> sourceCells = inventorySlotTable.getCells();
        int index = 0;

            for(; index < sourceCells.size; index++) {
                InventorySlot inventorySlot = ((InventorySlot) sourceCells.get(index).getActor());
                if(inventorySlot == null) {
                    continue;
                }
                int numItems = inventorySlot.getNumItems();
                if(numItems == 0) {
                    com.aie.game.GameComponents.GameUI.Inventory.InventoryItem inventoryItem = InventoryItemFactory.getInstance().getInventoryItem(com.aie.game.GameComponents.GameUI.Inventory.InventoryItem.ItemTypeID.valueOf(entity.getEntityConfig().getItemTypeID()));
                    inventoryItem.setName(itemName);
                    inventorySlot.add(inventoryItem);
                    dragAndDrop.addSource(new InventorySlotSource(inventorySlot, dragAndDrop));
                    break;
                }
            }
    }

    public void removeQuestItemFromInventory(String questID) {
        Array<Cell> sourceCells = inventorySlotTable.getCells();
        for(int index = 0; index < sourceCells.size; index++) {
            InventorySlot inventorySlot = ((InventorySlot) sourceCells.get(index).getActor());
            if(inventorySlot == null) {
                continue;
            }
            com.aie.game.GameComponents.GameUI.Inventory.InventoryItem item = inventorySlot.getTopInventoryItem();
            if(item == null) {
                continue;
            }
            String inventoryItemName = item.getName();
            if(inventoryItemName != null && inventoryItemName.equals(questID)) {
                inventorySlot.clearAllInventoryItems(false);
            }
        }
    }

    public Array<Actor> getInventoryActors() {
        return inventoryActors;
    }

    @Override
    public void onNotify(InventorySlot slot, SlotEvent event) {
        switch(event) {
            case ADDED_ITEM:
                com.aie.game.GameComponents.GameUI.Inventory.InventoryItem addItem = slot.getTopInventoryItem();
                if(addItem == null) {
                    return;
                }

                if(addItem.isInventoryItemOffensive()) {
                    APVal += addItem.getItemUseTypeValue();
                    APValLabel.setText(String.valueOf(APVal));
                    notify(String.valueOf(APVal), InventoryObserver.InventoryEvent.UPDATED_AP);

                    ProfileManager.getInstance().setProperty("currentPlayerAP", APVal);

                    if(addItem.isInventoryItemOffensiveWand()) {
                        notify(String.valueOf(addItem.getItemUseTypeValue()), InventoryObserver.InventoryEvent.ADD_WAND_AP);
                    }

                } else if(addItem.isInventoryItemDefensive()) {
                    DPVal += addItem.getItemUseTypeValue();
                    DPValLabel.setText(String.valueOf(DPVal));
                    notify(String.valueOf(DPVal), InventoryObserver.InventoryEvent.UPDATED_DP);

                    ProfileManager.getInstance().setProperty("currentPlayerDP", DPVal);
                }
                break;
            case REMOVED_ITEM:
                com.aie.game.GameComponents.GameUI.Inventory.InventoryItem removeItem = slot.getTopInventoryItem();
                if(removeItem == null) {
                    return;
                }

                if(removeItem.isInventoryItemOffensive()) {
                    APVal -= removeItem.getItemUseTypeValue();
                    APValLabel.setText(String.valueOf(APVal));
                    notify(String.valueOf(APVal), InventoryObserver.InventoryEvent.UPDATED_AP);

                    ProfileManager.getInstance().setProperty("currentPlayerAP", APVal);

                    if(removeItem.isInventoryItemOffensiveWand()) {
                        notify(String.valueOf(removeItem.getItemUseTypeValue()), InventoryObserver.InventoryEvent.REMOVE_WAND_AP);
                    }

                } else if(removeItem.isInventoryItemDefensive()) {
                    DPVal -= removeItem.getItemUseTypeValue();
                    DPValLabel.setText(String.valueOf(DPVal));
                    notify(String.valueOf(DPVal), InventoryObserver.InventoryEvent.UPDATED_DP);

                    ProfileManager.getInstance().setProperty("currentPlayerDP", DPVal);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void addObserver(InventoryObserver inventoryObserver) {
        observers.add(inventoryObserver);
    }

    @Override
    public void removeObserver(InventoryObserver inventoryObserver) {
        observers.removeValue(inventoryObserver, true);
    }

    @Override
    public void removeAllObservers() {
        for(InventoryObserver observer: observers) {
            observers.removeValue(observer, true);
        }
    }

    @Override
    public void notify(String value, InventoryObserver.InventoryEvent event) {
        for(InventoryObserver observer: observers) {
            observer.onNotify(value, event);
        }
    }
}
