package com.aie.game.GameComponents.GameUI.Battle;

import com.aie.game.GameComponents.Entities.Entity;
import com.badlogic.gdx.utils.Array;

public class BattleSubject {
    private Array<BattleObserver> observers;

    public BattleSubject() {
        observers = new Array<>();
    }

    public void addObserver(BattleObserver battleObserver) {
        observers.add(battleObserver);
    }

    public void removeObserver(BattleObserver battleObserver) {
        observers.removeValue(battleObserver, true);
    }

    protected void notify(final Entity entity, BattleObserver.BattleEvent event) {
        for(BattleObserver observer: observers) {
            observer.onNotify(entity, event);
        }
    }
}
