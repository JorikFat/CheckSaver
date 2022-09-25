package dev.kirillbalanov.check_sample.di.components;

import dagger.Subcomponent;
import dev.kirillbalanov.check_sample.db.AppDataBase;
import dev.kirillbalanov.check_sample.di.components.scope.ActivityScope;
import dev.kirillbalanov.check_sample.di.module.SampleActivityModule;
import dev.kirillbalanov.check_sample.view.SampleActivity;

@ActivityScope
@Subcomponent(modules = {SampleActivityModule.class})
public interface SampleActivityComponent {
    void injectSampleActivity(SampleActivity sampleActivity);
}