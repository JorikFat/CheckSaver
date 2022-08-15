package dev.kirillbalanov.check_sample.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dev.kirillbalanov.check_sample.App;

public class SampleViewModelFactory implements ViewModelProvider.Factory {

    public static SampleViewModelFactory getDataBase() {
        synchronized(SampleViewModelFactory.class){
            return new SampleViewModelFactory();
        }
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new SampleViewModel(App.getAppComponent().getDateBase());
    }
}