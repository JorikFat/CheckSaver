package dev.kirillbalanov.check_sample.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import dev.kirillbalanov.check_sample.pojo.Check;

@Database(entities = {Check.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase{
    public abstract ChecksDao checksDao();
}