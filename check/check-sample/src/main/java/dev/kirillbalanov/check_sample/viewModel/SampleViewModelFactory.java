package dev.kirillbalanov.check_sample.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dev.kirillbalanov.check_sample.App;

public class SampleViewModelFactory implements ViewModelProvider.Factory {

    private static SampleViewModelFactory instance;

    public static SampleViewModelFactory getInstance() {
        if(instance==null){
            synchronized(SampleViewModelFactory.class){
                return new SampleViewModelFactory();
            }
        }
        return instance;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(SampleViewModel.class)){
            return (T) new SampleViewModel(App.getAppComponent().getDateBase());
        }
        throw new IllegalArgumentException("Instance is empty");
    }
}