package by.step.thoughts.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.entity.BasketItem;
import by.step.thoughts.entity.relation.BasketItemAndProduct;

@Dao
public interface BasketItemDao {

    @Query("SELECT * FROM BasketItems")
    List<BasketItem> getAll();

    @Query("SELECT * FROM BasketItems WHERE productId = :productId LIMIT 1")
    BasketItem getByProductId(int productId);

    @Transaction
    @Query("SELECT * FROM BasketItems")
    List<BasketItemAndProduct> getBasketItemAndProducts();

    @Insert
    void insert(BasketItem... basketItems);

    @Delete
    void delete(BasketItem... basketItems);

    @Update
    void update(BasketItem... basketItems);
}
