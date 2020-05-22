package by.step.thoughts.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.data.DataAccessObject;
import by.step.thoughts.entity.Purse;

@Dao
public interface PurseDao extends DataAccessObject<Purse> {

    @Query("SELECT id, money FROM Purses")
    LiveData<List<Purse>> getAll();

    @Query("SELECT id, money FROM Purses WHERE id = :id LIMIT 1")
    LiveData<Purse> getById(String id);

    @Insert
    void insert(Purse... purses);

    @Delete
    void delete(Purse... purses);

    @Update
    void update(Purse... purses);
}
