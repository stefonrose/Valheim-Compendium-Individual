package com.theboys.valheimcompendium.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.parceler.Parcel;

@Parcel(analyze={Concept.class})
@ParseClassName("Concept")
public class Concept extends ParseObject {

    public static final String KEY_ID = "gameId";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_OVERVIEW = "overview";

    public Concept() {}

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


}
