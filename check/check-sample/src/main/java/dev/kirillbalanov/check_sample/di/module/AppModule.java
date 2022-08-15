package dev.kirillbalanov.check_sample.di.module;

import android.content.Context;
import androidx.room.Room;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dev.kirillbalanov.check_sample.db.AppDateBase;

@Module
public class AppModule {
    private Context context;
    public static final String DB_NAME = "checks.db";

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public AppDateBase provideDataBase(){
        return Room.databaseBuilder(context, AppDateBase.class, DB_NAME).build();
    }
}