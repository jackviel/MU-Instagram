package com.example.instagwam;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("6cxZ5cRYT669HkkQWXNjPgi25iqnCTgEvcTLhi2W")
                .clientKey("vAtEumqrNDJ93pWwwr4mQeCshJiMX9fzUdm9ca1T")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}

