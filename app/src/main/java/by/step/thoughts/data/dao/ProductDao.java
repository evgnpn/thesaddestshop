package by.step.thoughts.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.step.thoughts.data.DataAccessObject;
import by.step.thoughts.entity.Product;

@Dao
public interface ProductDao extends DataAccessObject<Product> {

    @Query("SELECT * FROM Products")
    LiveData<List<Product>> getAll();

    @Query("SELECT * FROM Products WHERE id = :id LIMIT 1")
    LiveData<Product> getById(int id);

    @Insert
    void insert(Product... products);

    @Delete
    void delete(Product... products);

    @Update
    void update(Product... products);
}
