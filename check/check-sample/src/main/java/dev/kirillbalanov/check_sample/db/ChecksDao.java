package dev.kirillbalanov.check_sample.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import dev.kirillbalanov.check_sample.pojo.Check;
@Dao
public interface ChecksDao {
    @Query("SELECT * FROM checks")
    LiveData<List<Check>> getAllChecks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChecks(List<Check> check);

    @Query("DELETE FROM checks")
    void deleteAllChecks();
}