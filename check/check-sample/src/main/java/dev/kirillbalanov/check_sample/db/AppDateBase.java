package dev.kirillbalanov.check_sample.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dev.kirillbalanov.check_sample.pojo.Check;


@Database(entities = {Check.class}, version = 1, exportSchema = false)
public abstract class AppDateBase extends RoomDatabase{
    public static final String DB_NAME = "checks.db";
    public static AppDateBase dateBase;

    public static final Object LOCK = new Object();

    public static AppDateBase getInstance(Context context){
        synchronized (LOCK){
            if(dateBase == null){
                dateBase = Room.databaseBuilder(context, AppDateBase.class, DB_NAME).build();
            }
            return dateBase;
        }
    }

    public abstract ChecksDao checksDao();
}