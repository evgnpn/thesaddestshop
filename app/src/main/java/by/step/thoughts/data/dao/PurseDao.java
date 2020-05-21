package by.step.thoughts.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.entity.Purse;

@Dao
public interface PurseDao {

    @Query("SELECT id, money FROM Purses")
    List<Purse> getAll();

    @Query("SELECT id, money FROM Purses WHERE id = :id LIMIT 1")
    Purse getById(String id);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Purse... purses);

    @Delete
    void delete(Purse... purses);

    @Update
    void update(Purse... purses);
}
