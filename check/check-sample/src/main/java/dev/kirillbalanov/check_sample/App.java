package dev.kirillbalanov.check_sample;

import android.app.Application;
import dev.kirillbalanov.check_sample.db.AppDateBase;
import dev.kirillbalanov.check_sample.di.components.AppComponent;
import dev.kirillbalanov.check_sample.di.components.DaggerAppComponent;
import dev.kirillbalanov.check_sample.di.module.AppModule;

public class App extends Application {

    private static AppComponent appComponent;
    private static AppDateBase appDateBase;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();

        appDateBase = new AppModule(this).getDataBase();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
    public static AppDateBase getAppDateBase() {
        return appDateBase;
    }
}