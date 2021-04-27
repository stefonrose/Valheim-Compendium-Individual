package com.theboys.valheimcompendium.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.parceler.Parcel;

import java.lang.reflect.Array;

@Parcel(analyze={Creature.class})
@ParseClassName("Creature")

public class Creature extends ParseObject{

    public static final String KEY_ID = "gameId";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_SPAWN = "spawn";
    public static final String KEY_DROP = "itemDrop";

    public Creature() {}

    public String getGameId() {return getString(KEY_ID); }

    public void setGameId(String id) { put(KEY_ID, id); }

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public String getOverview() {return getString(KEY_OVERVIEW); }

    public void setOverview(String overview) { put(KEY_OVERVIEW, overview); }

    public JSONArray getSpawn() {return getJSONArray(KEY_SPAWN); }

    //public void setSpawn() {}

    public JSONArray getItemDrop() {return getJSONArray(KEY_DROP); }

    //public void setItemDrop() {}

}
