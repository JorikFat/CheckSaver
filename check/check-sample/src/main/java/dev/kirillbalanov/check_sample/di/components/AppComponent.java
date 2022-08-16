package dev.kirillbalanov.check_sample.di.components;

import javax.inject.Singleton;
import dagger.Component;
import dev.kirillbalanov.check_sample.db.AppDataBase;
import dev.kirillbalanov.check_sample.di.module.AppModule;
import dev.kirillbalanov.check_sample.di.module.SampleActivityModule;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    AppDataBase getDateBase();
    SampleActivityComponent createActivityComponent(SampleActivityModule activityModule);
}