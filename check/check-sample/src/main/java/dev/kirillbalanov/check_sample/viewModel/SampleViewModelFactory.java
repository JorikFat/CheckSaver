package dev.kirillbalanov.check_sample.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dev.kirillbalanov.check_sample.db.AppDataBase;

public class SampleViewModelFactory implements ViewModelProvider.Factory {

    private AppDataBase appDataBase;

    public SampleViewModelFactory(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new SampleViewModel(appDataBase);
    }
}