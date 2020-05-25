package by.step.thoughts.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.data.DataAccessObject;
import by.step.thoughts.entity.Purchase;
import by.step.thoughts.entity.relation.purchase.PurchaseWithItemsAndProduct;


@Dao
public interface PurchaseDao extends DataAccessObject<Purchase> {

    @Query("SELECT * FROM Purchases")
    LiveData<List<Purchase>> getAll();

    @Query("SELECT * FROM Purchases WHERE id = :id LIMIT 1")
    LiveData<Purchase> getById(int id);

    @Transaction
    @Query("SELECT * FROM Purchases")
    LiveData<List<PurchaseWithItemsAndProduct>> purchaseWithItemsAndProducts();

    @Insert
    void insert(Purchase... purchases);

    @Delete
    void delete(Purchase... purchases);

    @Update
    void update(Purchase... purchases);
}
