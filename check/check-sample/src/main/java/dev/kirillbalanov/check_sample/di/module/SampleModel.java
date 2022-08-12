package dev.kirillbalanov.check_sample.di.module;

import android.content.Context;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

@Module
public class SampleModel {
    private Context context;

    public SampleModel(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public SampleViewModel provideViewModel(){
        return new ViewModelProvider((ViewModelStoreOwner) this.context).get(SampleViewModel.class);
    }
}