package dev.kirillbalanov.check_sample.di.components;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Component;
import dagger.Subcomponent;
import dev.kirillbalanov.check_sample.di.module.SampleActivityModule;
import dev.kirillbalanov.check_sample.view.SampleActivity;

@Singleton
//@Subcomponent(modules = {SampleActivityModule.class}) аннотация саб
@Component(modules = {SampleActivityModule.class})
public interface SampleActivityComponent {
    void injectSampleActivity(SampleActivity sampleActivity);
}