package dev.kirillbalanov.check_sample.di.module;

import android.content.Context;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import dagger.Module;
import dagger.Provides;
import dev.kirillbalanov.check_sample.App;
import dev.kirillbalanov.check_sample.di.components.scope.ActivityScope;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModelFactory;

@Module
public class SampleActivityModule {
    private Context context;//todo заменить на SampleActivity, и сделать final

    public SampleActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    public SampleViewModel provideViewModel(){//todo получать database в параметрах
        //todo сократить строку до 100 символов
        return new ViewModelProvider((ViewModelStoreOwner) context, new SampleViewModelFactory(App.getAppComponent().getDateBase())).get(SampleViewModel.class);
    }
}