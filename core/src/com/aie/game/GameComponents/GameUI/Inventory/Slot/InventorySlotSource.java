package com.aie.game.GameComponents.GameUI.Inventory.Slot;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class InventorySlotSource extends Source {

    private DragAndDrop dragAndDrop;
    private com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlot sourceSlot;

    public InventorySlotSource(com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlot sourceSlot, DragAndDrop dragAndDrop) {
        super(sourceSlot.getTopInventoryItem());
        this.sourceSlot = sourceSlot;
        this.dragAndDrop = dragAndDrop;
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Payload payload = new Payload();

        Actor actor = getActor();
        if(actor == null) {
            return null;
        }

        com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlot source = (com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlot)actor.getParent();
        if(source == null) {
            return null;
        } else {
            sourceSlot = source;
        }

    sourceSlot.decrementItemCount(true);

    payload.setDragActor(getActor());
    dragAndDrop.setDragActorPosition(-x, -y + getActor().getHeight());

    return payload;
}

    @Override
    public void dragStop (InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        if(target == null) {
            sourceSlot.add(payload.getDragActor());
        }
    }

    public com.aie.game.GameComponents.GameUI.Inventory.Slot.InventorySlot getSourceSlot() {
        return sourceSlot;
    }
}
