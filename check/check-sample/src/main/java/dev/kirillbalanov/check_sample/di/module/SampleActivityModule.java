package dev.kirillbalanov.check_sample.di.module;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import dev.kirillbalanov.check_sample.db.AppDataBase;
import dev.kirillbalanov.check_sample.di.components.scope.ActivityScope;
import dev.kirillbalanov.check_sample.view.SampleActivity;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModelFactory;

@Module
public class SampleActivityModule {
    private final SampleActivity owner;

    public SampleActivityModule(SampleActivity owner) {
        this.owner = owner;
    }

    @Provides
    @ActivityScope
    public SampleViewModel provideViewModel(AppDataBase db){
        return new ViewModelProvider(owner, new SampleViewModelFactory(db)).get(SampleViewModel.class);
    }
}