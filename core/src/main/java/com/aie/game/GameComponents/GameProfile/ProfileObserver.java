package com.aie.game.GameComponents.GameProfile;

public interface ProfileObserver {
    enum ProfileEvent {
        PROFILE_LOADED,
        SAVING_PROFILE,
        CLEAR_CURRENT_PROFILE
    }

    void onNotify(final ProfileManager profileManager, ProfileEvent event);
}
