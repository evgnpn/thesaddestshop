package by.step.thoughts.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.entity.Purchase;

@Dao
public interface PurchaseDao {

    @Query("SELECT id, amount, productId FROM Purchases")
    List<Purchase> getAll();

    @Query("SELECT id, amount, productId  FROM Purchases WHERE id = :id LIMIT 1")
    Purchase getById(int id);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Purchase... purchases);

    @Delete
    void delete(Purchase... purchases);

    @Update
    void update(Purchase... purchases);
}
