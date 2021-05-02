package com.theboys.valheimcompendium;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;


@ParseClassName("Item")
public class Item extends ParseObject{

    public static final String KEY_ITEM_NAME = "itemName";
    public static final String KEY_ITEM_IMAGE = "image";
    public static final String KEY_ITEM_OVERVIEW = "overview";


    public String getItemName() {
        return getString(KEY_ITEM_NAME);
    }

    public void  setItemName(String itemName) {
        put(KEY_ITEM_NAME, itemName);
    }

    public ParseFile getItemImage() {
        return getParseFile(KEY_ITEM_IMAGE);
    }

    public void setItemImage(ParseFile parseFile) {
        put(KEY_ITEM_IMAGE, parseFile);
    }

    public String getItemOverview() {
        return getString(KEY_ITEM_OVERVIEW);
    }

    public void  setItemOverview(String overview) {
        put(KEY_ITEM_OVERVIEW, overview);
    }
}

