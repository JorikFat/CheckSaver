package dev.kirillbalanov.check_sample.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.kirillbalanov.check_sample.pojo.Check;
@Dao
public interface ChecksDao {
    @Query("SELECT * FROM checks")
    LiveData<List<Check>> getAllChecks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCheck(Check check);

    @Query("DELETE FROM checks")
    void deleteAllChecks();

    @Delete
    void delete(Check check);

    @Update
    void update(Check check);
}