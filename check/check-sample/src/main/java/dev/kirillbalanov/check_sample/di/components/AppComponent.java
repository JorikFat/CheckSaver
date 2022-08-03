package dev.kirillbalanov.check_sample.di.components;

import dagger.Component;
import dev.kirillbalanov.check_sample.di.module.AppModule;
import dev.kirillbalanov.check_sample.view.SampleActivity;

@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(SampleActivity sampleActivity);
}