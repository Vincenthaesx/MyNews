package com.example.megaport.mynews.Controllers.Utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

// ----------------------------------------------------------------------------------
// Class used to retrieve the context of the application anywhere it's needed
// ----------------------------------------------------------------------------------

public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
