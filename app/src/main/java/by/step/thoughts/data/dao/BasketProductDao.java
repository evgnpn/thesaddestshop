package by.step.thoughts.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.entity.BasketProduct;

@Dao
public interface BasketProductDao {

    @Query("SELECT id, productId FROM BasketProducts")
    List<BasketProduct> getAll();

    @Query("SELECT  id, productId FROM BasketProducts WHERE id = :id LIMIT 1")
    BasketProduct getById(String id);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(BasketProduct... basketProducts);

    @Delete
    void delete(BasketProduct... basketProducts);

    @Update
    void update(BasketProduct... basketProducts);
}
