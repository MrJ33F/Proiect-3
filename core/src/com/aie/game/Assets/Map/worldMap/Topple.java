package com.aie.game.Assets.Map.worldMap;

import com.aie.game.Assets.Audio.AudioObserver;
import com.aie.game.Assets.Map.Map;
import com.aie.game.Assets.Map.MapFactory;
import com.aie.game.GameComponents.Component.Interfaces.Component;
import com.aie.game.GameComponents.Component.Type.ProfileManager;
import com.aie.game.GameComponents.Entities.Entity;
import com.aie.game.GameComponents.Entities.EntityConfig;
import com.aie.game.GameComponents.Entities.EntityFactory;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;

import static com.aie.game.Assets.Audio.AudioObserver.AudioTypeEvent.TOPPLE_THEME;


public class Topple extends Map {

    private static String mapPath = "asset/map/Topple.tmx";
    private Json json;

    public Topple() {
        super(MapFactory.MapType.TOPPLE, mapPath);

        json = new Json();

        Entity innKeeper = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_INNKEEPER);
        initSpecialEntityPosition(innKeeper);
        mapEntities.add(innKeeper);

        Entity townfolk1 = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_FOLK1);
        initSpecialEntityPosition(townfolk1);
        mapEntities.add(townfolk1);

        Entity townfolk2 = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_FOLK2);
        initSpecialEntityPosition(townfolk2);
        mapEntities.add(townfolk2);

        Entity townfolk3 = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_FOLK3);
        initSpecialEntityPosition(townfolk3);
        mapEntities.add(townfolk3);

        Entity townfolk4 = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_FOLK4);
        initSpecialEntityPosition(townfolk4);
        mapEntities.add(townfolk4);
    }

    @Override
    public AudioObserver.AudioTypeEvent getMusicTheme() {
        return TOPPLE_THEME;
    }

    @Override
    public void unloadMusic() {
        notify(AudioObserver.AudioCommand.MUSIC_STOP, getMusicTheme());
    }

    @Override
    public void loadMusic() {
        notify(AudioObserver.AudioCommand.MUSIC_LOAD, getMusicTheme());
        notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, getMusicTheme());
    }

    private void initSpecialEntityPosition(Entity entity) {
        Vector2 position = new Vector2(0,0);

        if(specialNPCStartPositions.containsKey(entity.getEntityConfig().getEntityID())) {
            position = specialNPCStartPositions.get(entity.getEntityConfig().getEntityID());
        }
        entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, json.toJson(position));

        //Overwrite default if special config is found
        EntityConfig entityConfig = ProfileManager.getInstance().getProperty(entity.getEntityConfig().getEntityID(), EntityConfig.class);
        if(entityConfig != null ) {
            entity.setEntityConfig(entityConfig);
        }
    }
}
