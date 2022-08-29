package dev.kirillbalanov.check_sample;

import android.app.Application;
import dev.kirillbalanov.check_sample.di.components.AppComponent;
import dev.kirillbalanov.check_sample.di.components.DaggerAppComponent;
import dev.kirillbalanov.check_sample.di.components.SampleActivityComponent;
import dev.kirillbalanov.check_sample.di.module.AppModule;
import dev.kirillbalanov.check_sample.di.module.SampleActivityModule;
import dev.kirillbalanov.check_sample.view.SampleActivity;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    //todo перенести логику работы с Activity в нее же
    public static SampleActivityComponent initSampleActivityComponent(SampleActivity sampleActivity) {
        return appComponent.createActivityComponent(new SampleActivityModule(sampleActivity));
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}