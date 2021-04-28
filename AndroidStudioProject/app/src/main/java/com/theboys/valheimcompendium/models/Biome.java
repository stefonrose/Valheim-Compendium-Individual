package com.theboys.valheimcompendium.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.parceler.Parcel;

import java.lang.reflect.Array;

@Parcel(analyze={Biome.class, Entry.class})
@ParseClassName("Biome")
public class Biome extends Entry {

    public static final String KEY_CREATURES = "creatures";
    public static final String KEY_ITEMS = "items";


    public Biome() {}

    public JSONArray getCreatures() {return getJSONArray(KEY_CREATURES); }

//    public void setCreatures() {}

    public JSONArray getItems() {return getJSONArray(KEY_ITEMS); }

//    public void setItems() {}

}
