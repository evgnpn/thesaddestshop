package by.step.thoughts.data.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import by.step.thoughts.data.dao.PurseDao;
import by.step.thoughts.entity.Purse;

public class PurseRepository extends BaseRepository<Purse> {

    public PurseRepository(PurseDao purseDao) {
        super(purseDao);
    }

    @Query("SELECT * FROM Purses WHERE id = :id")
    public LiveData<Purse> getById(String id) {
        return ((PurseDao) getDao()).getById(id);
    }
}
