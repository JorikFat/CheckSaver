package dev.kirillbalanov.check_sample;

import android.app.Application;
import dev.kirillbalanov.check_sample.di.components.AppComponent;
import dev.kirillbalanov.check_sample.di.components.DaggerAppComponent;
import dev.kirillbalanov.check_sample.di.module.AppModule;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}