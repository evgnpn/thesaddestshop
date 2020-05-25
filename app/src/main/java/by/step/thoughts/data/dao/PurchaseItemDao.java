package by.step.thoughts.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.data.DataAccessObject;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.PurchaseItem;


@Dao
public interface PurchaseItemDao extends DataAccessObject<Purchase> {

    @Query("SELECT * FROM PurchaseItems")
    List<Purchase> getAll();

    @Query("SELECT * FROM PurchaseItems")
    LiveData<List<Purchase>> getAllLiveData();

    @Query("SELECT * FROM PurchaseItems WHERE id = :id LIMIT 1")
    LiveData<PurchaseItem> getByIdLiveData(int id);

    @Insert
    void insert(PurchaseItem... purchaseItems);

    @Delete
    void delete(PurchaseItem... purchaseItems);

    @Update
    void update(PurchaseItem... purchaseItems);
}
