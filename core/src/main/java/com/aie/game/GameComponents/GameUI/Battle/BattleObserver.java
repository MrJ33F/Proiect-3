package com.aie.game.GameComponents.GameUI.Battle;

import com.aie.game.GameComponents.Entities.Entity;

public interface BattleObserver {
    enum BattleEvent {
        OPPONENT_ADDED,
        OPPONENT_HIT_DAMAGE,
        OPPONENT_DEFEATED,
        OPPONENT_TURN_DONE,
        PLAYER_ADDED,
        PLAYER_HIT_DAMAGE,
        PLAYER_RUNNING,
        PLAYER_TURN_DONE,
        PLAYER_TURN_START,
        PLAYER_USED_MAGIC,
        NONE
    }

    void onNotify(final Entity enemyEntity, BattleEvent event);
}
