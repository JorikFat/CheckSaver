package dev.kirillbalanov.check_sample;

import android.app.Application;

import androidx.room.Room;

import dev.kirillbalanov.check_sample.db.AppDateBase;
import dev.kirillbalanov.check_sample.db.ChecksDao;

public class App extends Application {

    private AppDateBase dateBase;
    private ChecksDao checksDao;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        dateBase = Room.databaseBuilder(getApplicationContext(), AppDateBase.class, "app-db").allowMainThreadQueries().build(); //переделать на фоновый поток
        checksDao = dateBase.getChecks();
    }

    public AppDateBase getDateBase() {
        return dateBase;
    }

    public ChecksDao getChecksDao() {
        return checksDao;
    }

    public static App getInstance() {
        return instance;
    }
}
