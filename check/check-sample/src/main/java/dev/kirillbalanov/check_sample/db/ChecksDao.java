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
    @Query("SELECT * FROM `Check`")
    List<Check> getAll();

    @Query("SELECT * FROM `Check`")
    LiveData<List<Check>> getAllLiveData();

    @Query("SELECT * FROM `Check` WHERE id = :checkId")
    Check findById(int checkId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Check check);

    @Update
    void update(Check check);

    @Delete
    void delete(Check check);

}
