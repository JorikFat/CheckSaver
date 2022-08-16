package dev.kirillbalanov.check_sample.di.module;

import android.content.Context;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.kirillbalanov.check_sample.App;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModelFactory;

@Module
public class SampleActivityModule {
    private Context context;

    public SampleActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public SampleViewModel provideViewModel(){
        return new ViewModelProvider((ViewModelStoreOwner) context, new SampleViewModelFactory(App.getAppComponent().getDateBase())).get(SampleViewModel.class);
    }
}