package com.theboys.valheimcompendium.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.parceler.Parcel;

import java.lang.reflect.Array;

@Parcel(analyze={Item.class, Entry.class})
@ParseClassName("Item")
public class Item extends Entry {

    public static final String KEY_BIOME = "biome";

    public Item() {}

    public JSONArray getBiomes() {return getJSONArray(KEY_BIOME); }

//    public void setBiomes() {}

}
