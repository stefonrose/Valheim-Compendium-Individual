package com.theboys.valheimcompendium;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your Parse models
        ParseObject.registerSubclass(Feature.class);

        // set applicationId, and server server based on the values in the back4app settings.
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("LIQUFzehlyIaRXvmD19OzPK2Cup4thQSqTD6dAbq") // should correspond to Application Id env variable
                .clientKey("7YI9dPfM0zSEFH9lWDXUFXYXAyCUn3cIIL3h8pau")  // should correspond to Client key env variable
                .server("https://parseapi.back4app.com").build());
    }
}
