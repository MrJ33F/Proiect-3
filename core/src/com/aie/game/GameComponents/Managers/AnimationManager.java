package com.aie.game.GameComponents.Managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationManager {

    public TextureRegion[][] setTextureRegionsDouble(Texture texture, int tileWidth, int tileHeight) {
        return TextureRegion.split(texture, tileWidth, tileHeight);
    }

    public Animation<TextureRegion> setAnimation(TextureRegion[] textureRegions) {
        return new Animation<>(0.1f, textureRegions);
    }
}
