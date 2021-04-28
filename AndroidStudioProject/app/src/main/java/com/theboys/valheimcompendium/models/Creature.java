package com.theboys.valheimcompendium.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.parceler.Parcel;

import java.lang.reflect.Array;

@Parcel(analyze={Creature.class, Entry.class})
@ParseClassName("Creature")

public class Creature extends Entry{

    public static final String KEY_SPAWN = "spawn";
    public static final String KEY_DROP = "itemDrop";

    public Creature() {}

    public JSONArray getSpawn() {return getJSONArray(KEY_SPAWN); }

    //public void setSpawn() {}

    public JSONArray getItemDrop() {return getJSONArray(KEY_DROP); }

    //public void setItemDrop() {}

}
