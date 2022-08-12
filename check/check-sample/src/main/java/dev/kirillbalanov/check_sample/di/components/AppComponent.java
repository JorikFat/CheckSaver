package dev.kirillbalanov.check_sample.di.components;

import javax.inject.Singleton;
import dagger.Component;
import dev.kirillbalanov.check_sample.db.AppDateBase;
import dev.kirillbalanov.check_sample.di.module.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    AppDateBase getDateBase();
}