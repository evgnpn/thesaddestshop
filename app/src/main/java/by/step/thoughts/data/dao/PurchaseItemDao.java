package by.step.thoughts.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.data.DataAccessObject;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.PurchaseItem;


@Dao
public interface PurchaseItemDao extends DataAccessObject<PurchaseItem> {

    @Query("SELECT * FROM PurchaseItems")
    List<PurchaseItem> getAll();

    @Query("SELECT * FROM PurchaseItems")
    LiveData<List<PurchaseItem>> getAllLiveData();

    @Query("SELECT * FROM PurchaseItems WHERE id = :id LIMIT 1")
    LiveData<PurchaseItem> getByIdLiveData(int id);

    @Insert
    long[] insert(PurchaseItem... purchaseItems);

    @Delete
    void delete(PurchaseItem... purchaseItems);

    @Update
    void update(PurchaseItem... purchaseItems);
}
