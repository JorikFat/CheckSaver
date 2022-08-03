package dev.kirillbalanov.check_sample.di.module;

import android.content.Context;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.room.Room;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dev.kirillbalanov.check_sample.db.AppDateBase;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

@Module
public class AppModule {
    private Context context;
    private static AppDateBase appDateBase;
    public static final String DB_NAME = "checks.db";

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return this.context;
    }

    public AppDateBase getDataBase(){
        return appDateBase = Room.databaseBuilder(context, AppDateBase.class, DB_NAME).build();
    }

    @Provides
    @Singleton
    public SampleViewModel getViewModel(){
        return new ViewModelProvider((ViewModelStoreOwner) context).get(SampleViewModel.class);
    }
}