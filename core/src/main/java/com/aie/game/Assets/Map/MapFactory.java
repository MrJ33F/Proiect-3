package com.aie.game.Assets.Map;

import com.aie.game.Assets.Map.worldMap.Topple;
import com.aie.game.Assets.Map.worldMap.ToppleDownRoad;
import com.aie.game.Assets.Map.worldMap.ToppleRoad1;

import java.util.Hashtable;

public class MapFactory {
    //All maps for the game
    private static Hashtable<MapType, Map> mapTable = new Hashtable<>();

    public enum MapType {
        TOPPLE_ROAD_1,
        TOPPLE,
        TOPPLE_DOWN_ROAD
    }

    public static Hashtable<MapType, Map> getMapTable() {
        return mapTable;
    }

    public static Map getMap(MapType mapType) {
        Map map = null;
        switch(mapType) {
            case TOPPLE_ROAD_1:
                map = mapTable.get(MapType.TOPPLE_ROAD_1);
                if(map == null) {
                    map = new ToppleRoad1();
                    mapTable.put(MapType.TOPPLE_ROAD_1, map);
                }
                break;
            case TOPPLE:
                map = mapTable.get(MapType.TOPPLE);
                if(map == null) {
                    map = new Topple();
                    mapTable.put(MapType.TOPPLE, map);
                }
                break;
            case TOPPLE_DOWN_ROAD:
                map = mapTable.get(MapType.TOPPLE_DOWN_ROAD);
                if(map == null){
                    map = new ToppleDownRoad();
                    mapTable.put(MapType.TOPPLE_DOWN_ROAD, map);
                }
            default:
                break;
        }
        return map;
    }

    public static void clearCache() {
        for(Map map: mapTable.values()) {
            map.dispose();
        }
        mapTable.clear();
    }
}
