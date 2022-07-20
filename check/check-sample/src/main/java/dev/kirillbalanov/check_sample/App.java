package dev.kirillbalanov.check_sample;

import android.app.Application;
import androidx.room.Room;
import dev.kirillbalanov.check_sample.db.AppDateBase;

public class App extends Application {

    public static final String DB_NAME = "checks.db";

    private static AppDateBase appDateBase;

    public static AppDateBase getAppDateBase() {
        return appDateBase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appDateBase = Room.databaseBuilder(this, AppDateBase.class, DB_NAME).build();
    }
}