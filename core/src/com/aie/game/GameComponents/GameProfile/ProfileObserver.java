package com.aie.game.GameComponents.GameProfile;

import com.aie.game.GameComponents.Component.Type.ProfileManager;

public interface ProfileObserver {
    enum ProfileEvent {
        PROFILE_LOADED,
        SAVING_PROFILE,
        CLEAR_CURRENT_PROFILE
    }

    void onNotify(final ProfileManager profileManager, ProfileEvent event);
}
