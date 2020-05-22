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
import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.relation.BasketItemAndProduct;

@Dao
public interface BasketItemDao extends DataAccessObject<BasketItem> {

    @Query("SELECT * FROM BasketItems")
    LiveData<List<BasketItem>> getAll();

    @Query("SELECT * FROM BasketItems WHERE productId = :productId LIMIT 1")
    LiveData<BasketItem> getByProductId(int productId);

    @Transaction
    @Query("SELECT * FROM BasketItems")
    LiveData<List<BasketItemAndProduct>> getBasketItemAndProducts();

    @Insert
    void insert(BasketItem... basketItems);

    @Delete
    void delete(BasketItem... basketItems);

    @Update
    void update(BasketItem... basketItems);
}
