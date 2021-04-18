package com.theboys.valheimcompendium;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Feature")
public class Feature extends ParseObject {


    public static final String KEY_FEATURE_NAME = "name";
    public static final String KEY_FEATURE_IMAGE = "image";


    public String getFeatureName() {
        return getString(KEY_FEATURE_NAME);
    }

    public void  setFeatureName(String name) {
        put(KEY_FEATURE_NAME, name);
    }

    public ParseFile getFeatureImage() {
        return getParseFile(KEY_FEATURE_IMAGE);
    }

    public void setFeatureImage(ParseFile parseFile) {
        put(KEY_FEATURE_IMAGE, parseFile);
    }
}
