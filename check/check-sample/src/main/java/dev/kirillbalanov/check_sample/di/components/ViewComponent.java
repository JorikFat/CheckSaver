package dev.kirillbalanov.check_sample.di.components;

import javax.inject.Singleton;
import dagger.Component;
import dev.kirillbalanov.check_sample.di.module.SampleModel;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

@Singleton
@Component(modules = SampleModel.class)
public interface ViewComponent {
    SampleViewModel getViewModel();
}